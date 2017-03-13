package app.util;

public abstract class MyAssert {
    public MyAssert() {
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void hasLength(String text, String message) {
        if (!Texts.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void hasLength(String text) {
        hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static void hasText(String text, String message) {
        if (!Texts.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void hasText(String text) {
        hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }


    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            Object[] arr$ = array;
            int len$ = array.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Object element = arr$[i$];
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }

    }

    public static void noNullElements(Object[] array) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
        }
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }

    public static void isInteger(String value, Integer min, Integer max, String msg) {
        hasText(value, msg);
        try {
            int val = Integer.parseInt(value);
            if (min != null) {
                if (val < min) {
                    throw new IllegalArgumentException(msg);
                }
            }
            if (max != null) {
                if (val > max) {
                    throw new IllegalArgumentException(msg);
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void isInteger(String value, String msg) {
        isInteger(value, null, null, msg);
    }
}