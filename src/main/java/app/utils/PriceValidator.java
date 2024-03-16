package app.utils;

// Валидация телефона
public class PriceValidator {

    // Regex for phone number xxx xxx-xxxx
    private final static String PRICE_RGX = "\\d+(\\.\\d{1,2})?";


    private PriceValidator() {
    }

    public static boolean isPriceValid(Double price) {
        return price.isEmpty() || !phone.matches(PHONE_RGX);
    }
}
