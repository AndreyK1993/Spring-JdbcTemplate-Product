package app.utils;

// Валидация телефона
public class PhoneValidator {

    // Regex for phone number xxx xxx-xxxx
    private final static String PHONE_RGX =
            "[0-9]{3} [0-9]{3}-[0-9]{4}";

    private PhoneValidator() {
    }

    public static boolean isPhoneValid(String phone) {
        return phone.isEmpty() || !phone.matches(PHONE_RGX);
    }
}
