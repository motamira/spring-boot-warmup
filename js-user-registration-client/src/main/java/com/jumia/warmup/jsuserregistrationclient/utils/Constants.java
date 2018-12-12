package com.jumia.warmup.jsuserregistrationclient.utils;

public class Constants {
    
    public static final String USER_NAME_REGEX = "^[a-zA-Z0-9]{4,10}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*+=-_])(?=\\S+$).{3,}$";

    public static final String EMPTY_FIRST_NAME_ERROR_MESSAGE = "First name should not be empty.";
    public static final String EMPTY_USER_NAME_ERROR_MESSAGE = "User name should not be empty.";
    public static final String INVALID_USER_NAME_ERROR_MESSAGE = "User name should be between 4 and 10 alphanumeric characters.";
    public static final String EMPTY_PASSWORD_ERROR_MESSAGE = "Password should not be empty.";
    public static final String INVALID_PASSWORD_ERROR_MESSAGE = "Password should contain at least one capital letter, a number and a "
        + "symbol.";
    public static final String EMPTY_EMAIL_ERROR_MESSAGE = "Email should not be empty.";
    public static final String INVALID_EMAIL_ERROR_MESSAGE = "Invalid E-mail";
    public static final String EMPTY_ACCOUNT_INFO_ERROR_MESSAGE = "Account info should not be empty.";
    public static final String EMPTY_CONTACTS_ERROR_MESSAGE = "Contacts should not be empty.";
    public static final String EMPTY_PERSONAL_DETAILS_ERROR_MESSAGE = "Personal details should not be empty.";

    public static final String $_SPRING_RABBITMQ_QUEUE = "${spring.rabbitmq.queue}";
    public static final String $_SPRING_RABBITMQ_EXCHANGE = "${spring.rabbitmq.exchange}";
    public static final String $_SPRING_RABBITMQ_ROUTING_KEY = "${spring.rabbitmq.routing.key}";
    public static final String $_SPRING_RABBITMQ_DEAD_QUEUE = "${spring.rabbitmq.dead.queue}";
    public static final String $_SPRING_RABBITMQ_DEAD_EXCHANGE = "${spring.rabbitmq.dead.exchange}";
    public static final String $_USER_REGISTRATION_SERVICE_URL = "${user.registration.service.url}";

    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    public static final int FIVE_MS = 5000;

    public static final String CHANNEL_EXCEPTION = ">>>>>>>>>>>>>>>>>>>>>>> Channel exception";
    public static final String USER_DTO_RECEIVED = ">>>>>>>>>>>>>>>>>>>>>>> UserDTO Received: ";
    public static final String VALIDATION_FAILED = ">>>>>>>>>>>>>>>>>>>>>>> Validation Failed";
    public static final String USER_ALREADY_EXISTS = ">>>>>>>>>>>>>>>>>>>>>>> User already exists ";
    public static final String INTERNAL_SERVER_ERROR = ">>>>>>>>>>>>>>>>>>>>> Internal Server Error";
    public static final String CLIENT_SERVER_ERROR = ">>>>>>>>>>>>>>>>>>>>> Client Server Error";
}

