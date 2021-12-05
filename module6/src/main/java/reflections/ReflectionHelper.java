package reflections;

import java.lang.reflect.Field;

public class ReflectionHelper {
    private enum Types {
        BYTE,
        BOOLEAN,
        SHORT,
        CHAR,
        INT,
        FLOAT,
        LONG,
        DOUBLE,
        STRING;

        public static Types getType(Class<?> clazz) {
            String className = clazz.getSimpleName().toUpperCase();
            return Types.valueOf(className);
        }
    }

    public static Object createInstance(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (IllegalArgumentException | SecurityException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object object,
                                     String fieldName,
                                     String value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            Types types = Types.getType(field.getType());
            switch (types) {
                case BYTE -> field.set(object, Byte.valueOf(value));
                case BOOLEAN -> field.set(object, Boolean.valueOf(value));
                case SHORT -> field.set(object, Short.valueOf(value));
                case CHAR -> field.set(object, value.charAt(0));
                case INT -> field.set(object, Integer.decode(value));
                case FLOAT -> field.set(object, Float.valueOf(value));
                case LONG -> field.set(object, Long.valueOf(value));
                case DOUBLE -> field.set(object, Double.valueOf(value));
                case STRING -> field.set(object, value);
            }

            field.setAccessible(false);
        } catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}