package jodd.typeconverter;

import java.util.Collection;

public class TypeConverterManager {
   private static final TypeConverterManagerBean TYPE_CONVERTER_MANAGER_BEAN = new TypeConverterManagerBean();

   public static TypeConverterManagerBean getDefaultTypeConverterManager() {
      return TYPE_CONVERTER_MANAGER_BEAN;
   }

   public static void registerDefaults() {
      TYPE_CONVERTER_MANAGER_BEAN.registerDefaults();
   }

   public static void register(Class type, TypeConverter typeConverter) {
      TYPE_CONVERTER_MANAGER_BEAN.register(type, typeConverter);
   }

   public static void unregister(Class type) {
      TYPE_CONVERTER_MANAGER_BEAN.unregister(type);
   }

   public static TypeConverter lookup(Class type) {
      return TYPE_CONVERTER_MANAGER_BEAN.lookup(type);
   }

   public static Object convertType(Object value, Class destinationType) {
      return TYPE_CONVERTER_MANAGER_BEAN.convertType(value, destinationType);
   }

   public static Collection convertToCollection(Object value, Class destinationType, Class componentType) {
      return TYPE_CONVERTER_MANAGER_BEAN.convertToCollection(value, destinationType, componentType);
   }
}
