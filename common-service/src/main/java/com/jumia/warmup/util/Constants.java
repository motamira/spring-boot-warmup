package com.jumia.warmup.util;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
public class Constants {

    public static final String ENTER_PERSONAL_DETAILS = "Please enter personal details!";

    public static final String ENTER_ACCOUNT_INFORMATION = "Please enter account information!";

    public static final String ENTER_CONTACTS = "Please enter contacts!";

    public static final String ENTER_FIRST_NAME = "Please enter first name!";

    public static final String ENTER_AGE = "Please enter age!";

    public static final String ENTER_VALID_AGE = "Please enter valid age!";

    public static final String ENTER_USERNAME = "Please enter username!";

    public static final String ENTER_VALID_USERNAME = "User name should be between 4 and 10 alphanumeric characters.";

    public static final String ENTER_PASSWORD = "Please enter password!";

    public static final String ENTER_VALID_PASSWORD = "Password should contain at least one capital letter, a number and a symbol.";

    public static final String ENTER_VALID_CELL_PHONE = "Please enter valid cell phone, i.e must contain the country indicative.";

    public static final String ENTER_EMAIL = "Please enter email!";

    public static final String ENTER_VALID_EMAIL = "Please enter valid email!";

    public static final String VALIDATION_FAILED = "Validation Failed";

    public static final String USER_PAYLOAD = "Registered user payload: ";

    public static final String WELCOME_MESSAGE = "Jumia Services is Awesome";

    public static final String USERNAME_REGEXP = "^[a-zA-Z0-9]{4,10}$";

    public static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*+=-_])(?=\\S+$).{3,}$";

    public static final String CELL_PHONE_REGEXP = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    public static final String USERS = "users";

    public static final String PERSONAL_DETAILS = "personal_details";

    public static final String ACCOUNT_INFORMATION = "account_information";

    public static final String FIRST_NAME = "FIRST_NAME";

    public static final String LAST_NAME = "LAST_NAME";

    public static final String USER_NAME = "USER_NAME";

    public static final String CELL_PHONE = "CELL_PHONE";

    public static final String USER_ALREADY_EXIST = "User with this userName already exist.";

    public static final String FIND_BY_USERNAME = "{'account_information.user_name': ?0}";

    public static final String USER_REST = "/api";

    public static final String USER_REST_REGISTER_USERS = "/users";

    public static final String COLON_WITH_SPACE = ": ";

    public static final String EMPTY_STRING = "";

    public static final String VALID_FIRST_NAME = "Mohammed";

    public static final String VALID_LAST_NAME = "Hanfy";

    public static final int VALID_AGE = 27;

    public static final int INVALID_AGE = -10;

    public static final String VALID_USERNAME = "mhanfy";

    public static final String INVALID_SHORT_USERNAME = "123";

    public static final String INVALID_LONG_USERNAME = "123456789012";

    public static final String INVALID_USERNAME = "1234@asdf";

    public static final String VALID_PASSWORD = "M_Hanfy_7";

    public static final String INVALID_PASSWORD = "asdfg";

    public static final String VALID_CELL_PHONE = "+20 1275284823";

    public static final String INVALID_CELL_PHONE = "123456789012";

    public static final String VALID_EMAIL = "mohammed.ahmed.hanfy@gmail.com";

    public static final String INVALID_EMAIL = "mohammed";

    public static final String IT_TEST_PROFILE = "it-test";

    public static final String DEV_INVALID_DATA_PROFILE = "dev-invalid-data";

    public static final String DEV_PROFILE = "dev";

    public static final String SERVER_URL = "http://localhost:";

    public static final String QUEUE_NAME = "${rabbitmq.queue.name}";

    public static final String TOPIC_EXCHANGE_NAME = "${rabbitmq.topic.exchange.name}";

    public static final String ROUTING_KEY = "${rabbitmq.routing.key}";

    public static final String DEAD_LETTER_QUEUE_NAME = "${rabbitmq.deadletter.queue.name}";

    public static final String DEAD_LETTER_FANOUT_EXCHANGE_NAME = "${rabbitmq.deadletter.fanout.exchange.name}";

    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    public static final String DEFAULT_LISTENER_METHOD = "onMessage";

    public static final int FIXED_MESSAGE_DELAY = 50000;

    public static final String USER_REGISTRATION_SERVICE_URL = "${user.registration.service.url}";

    public static final String ERROR_USER_ALREADY_EXIST = "User already exist.";

    public static final String ERROR_User_NOT_VALID = "User not valid.";

    public static final String ERROR_SERIVE_NOT_AVALIABLE = "Serive not avaliable.";

    public static final String ERROR_SOMETHING_WENT_WRONG = "Something went wrong.";

}
