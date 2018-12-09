package com.jumia.client.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.client.payloads.users.UserDTO;
import com.rabbitmq.client.Channel;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
public class Listener implements ChannelAwareMessageListener {

    @Autowired
    Validator validator;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("Message Received ..");

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("mapping input to dto");
            UserDTO user = mapper.readValue(new String(message.getBody()), UserDTO.class);

            System.out.println("validating");
            Set<ConstraintViolation<UserDTO>> errors = this.validator.validate(user);

            if (!errors.isEmpty()) {
                System.out.println(errors);
                throw new Exception("Validation failed.");
            }

            int statusCode = this.sendToService(message);

            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Service returned status code: " + statusCode);
                throw new Exception("Database failed.");
            }

            System.out.println("looks legit, send ack");
            // If everything is ok, send the ack ..
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
            System.out.println("error, sending nack");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    private int sendToService(Message message) throws Exception {
        URL obj = new URL("http://localhost:8081/api/users");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(message.getBody());
        os.flush();
        os.close();

        return con.getResponseCode();
    }
}
