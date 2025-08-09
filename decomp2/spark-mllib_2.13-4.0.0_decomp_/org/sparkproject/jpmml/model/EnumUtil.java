package org.sparkproject.jpmml.model;

import jakarta.xml.bind.annotation.XmlEnumValue;
import java.lang.reflect.Field;
import java.util.Objects;
import org.sparkproject.dmg.pmml.PMMLObject;

public class EnumUtil {
   private EnumUtil() {
   }

   public static Field getEnumField(PMMLObject object, Enum value) {
      for(Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
         Field[] fields = clazz.getDeclaredFields();

         for(Field field : fields) {
            if (Objects.equals(field.getType(), value.getClass())) {
               return field;
            }
         }
      }

      throw new IllegalArgumentException();
   }

   public static String getEnumValue(Enum value) {
      Class<?> clazz = value.getClass();

      Field field;
      try {
         field = clazz.getField(value.name());
      } catch (NoSuchFieldException nsfe) {
         throw new RuntimeException(nsfe);
      }

      XmlEnumValue enumValue = (XmlEnumValue)field.getAnnotation(XmlEnumValue.class);
      if (enumValue != null) {
         return enumValue.value();
      } else {
         throw new IllegalArgumentException();
      }
   }
}
