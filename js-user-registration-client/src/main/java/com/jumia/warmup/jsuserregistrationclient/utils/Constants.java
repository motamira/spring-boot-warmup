package com.jumia.warmup.jsuserregistrationclient.utils;

public class Constants {

    public class Regex {

        public static final String USER_NAME = "^[a-zA-Z0-9]{4,10}$";

        public static final String PASSWORD = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*+=-_])(?=\\S+$).{3,}$";
    }

    public class ErrorMessages {

        public static final String EMPTY_FIRST_NAME = "First name should not be empty.";

        public static final String EMPTY_USER_NAME = "User name should not be empty.";
        public static final String INVALID_USER_NAME = "User name should be between 4 and 10 alphanumeric characters.";

        public static final String EMPTY_PASSWORD = "Password should not be empty.";
        public static final String INVALID_PASSWORD = "Password should contain at least one capital letter, a number and a symbol.";

        public static final String EMPTY_EMAIL = "Email should not be empty.";
        public static final String INVALID_EMAIL = "Invalid E-mail";
        public static final String EMPTY_ACCOUNT_INFO = "Account info should not be empty.";
        public static final String EMPTY_CONTACTS = "Contacts should not be empty.";
        public static final String EMPTY_PERSONAL_DETAILS = "Personal details should not be empty.";
    }
}

