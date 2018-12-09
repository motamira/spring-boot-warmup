package com.jumia.warmup.jsuserregistrationservice;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.jumia.warmup.jsuserregistrationservice.*")
@SpringBootApplication
public class JsUserRegistrationServiceApplication {

    static final Logger LOG = LoggerFactory.getLogger(JsUserRegistrationServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JsUserRegistrationServiceApplication.class, args);

        LOG.info(Constants.LOG.JUMIA_SERVICES_IS_AWESOME);
    }

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
