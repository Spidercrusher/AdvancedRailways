package spidercrusher.advancedrailways.util;

public class MissingEnumConstantException extends EnumConstantNotPresentException {
    /**
     * Can be thrown when an Enum Constant is not used in a switch or if-else statement
     * (Useful to make sure you are 'notified' when you implement a new Enum Constant, but forget to add it to the necessary switches and if-else statements)
     *
     * @param enumType     the type of the missing enum constant
     * @param constantName the name of the missing enum constant
     */

    public MissingEnumConstantException(Class<? extends Enum> enumType, String constantName) {
        super(enumType, constantName);
    }
}
