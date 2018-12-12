package com.jumia.warmup.jsuserregistrationservice.utils;

public class Constants {

    public static final String USER_NAME_REGEX = "^[a-zA-Z0-9]{4,10}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*+=-_])(?=\\S+$).{3,}$";
    public static final int MAX_AGE = 100;

    public static final String EMPTY_FIRST_NAME_ERROR_MESSAGE = "First name should not be empty.";
    public static final String EMPTY_USER_NAME_ERROR_MESSAGE = "User name should not be empty.";
    public static final String INVALID_AGE_ERROR_MESSAGE = "Invalid age.";
    public static final String INVALID_USER_NAME_ERROR_MESSAGE = "User name should be between 4 and 10 alphanumeric characters.";
    public static final String EMPTY_PASSWORD_ERROR_MESSAGE = "Password should not be empty.";
    public static final String INVALID_PASSWORD_ERROR_MESSAGE = "Password should contain at least one capital letter, a number and a "
        + "symbol.";
    public static final String EMPTY_EMAIL_ERROR_MESSAGE = "Email should not be empty.";
    public static final String INVALID_EMAIL_ERROR_MESSAGE = "Invalid E-mail";
    public static final String EMPTY_ACCOUNT_INFO_ERROR_MESSAGE = "Account info should not be empty.";
    public static final String EMPTY_CONTACTS_ERROR_MESSAGE = "Contacts should not be empty.";
    public static final String EMPTY_PERSONAL_DETAILS_ERROR_MESSAGE = "Personal details should not be empty.";

    public static final String JUMIA_SERVICES_IS_AWESOME = "Jumia Services is Awesome";

    public static final String USER_NAME_INDEX = "user_name_index";

    public static final String REGISTERED_USER = "RegisteredUser: ";
    public static final String VALIDATION_FAILED = "Validation Failed";
    public static final String USER_ALREADY_EXISTS = "User already exists ";

    public static final String API_USERS = "/api/users";

    public static final String VALID_FIRST_NAME = "sara";
    public static final String VALID_SECOND_NAME = "salah";
    public static final int VALID_AGE = 10;
    public static final String VALID_USER_NAME = "saraTest";
    public static final String VALID_PASSWORD = "aaZZa44@";
    public static final String VALID_EMAIL = "someEmail@gmail.com";
    public static final String VALID_PHONE_NUMBER = "+00 000 000 000";

    public static final String EMPTY_FIRST_NAME = "";
    public static final String EMPTY_USER_NAME = "";
    public static final String INVALID_USER_NAME_LESS_THAN_4_CHARS = "aaa";
    public static final String INVALID_USER_NAME_GREATER_THAN_10_CHARS = "asdhyuikjhdthtr";
    public static final String EMPTY_PASSWORD = "";
    public static final String INVALID_PASSWORD_NO_CAPITAL_CHARS = "somepassword12@#";
    public static final String INVALID_PASSWORD_NO_NUMBERS = "somePassword$%";
    public static final String EMPTY_EMAIL = "";
    public static final String INVALID_EMAIL = "hjjhjjhjh.com";
}

