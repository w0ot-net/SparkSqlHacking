package org.sparkproject.jpmml.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.List;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;

public class XPathUtil {
   private XPathUtil() {
   }

   public static String formatElement(Class elementClazz) {
      return getElementName(elementClazz);
   }

   public static String formatElementOrAttribute(Field field) {
      return formatElementOrAttribute(field.getDeclaringClass(), field);
   }

   public static String formatElementOrAttribute(Class elementClazz, Field field) {
      XmlElement element = (XmlElement)field.getAnnotation(XmlElement.class);
      XmlElements elements = (XmlElements)field.getAnnotation(XmlElements.class);
      XmlAttribute attribute = (XmlAttribute)field.getAnnotation(XmlAttribute.class);
      if (element != null) {
         Class<?> childElementClazz = getElementType(field);

         try {
            String var11 = getElementName(elementClazz);
            return var11 + "/" + getElementName(childElementClazz);
         } catch (IllegalArgumentException var7) {
            String var10 = getElementName(elementClazz);
            return var10 + "/" + element.name();
         }
      } else if (elements != null) {
         Class<?> childElementClazz = getElementType(field);
         String var9 = getElementName(elementClazz);
         return var9 + "/<" + childElementClazz.getSimpleName() + ">";
      } else if (attribute != null) {
         String var10000 = getElementName(elementClazz);
         return var10000 + "@" + attribute.name();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static String formatAttribute(Field field, Object value) {
      return formatAttribute(field.getDeclaringClass(), field, value);
   }

   public static String formatAttribute(Class elementClazz, Field field, Object value) {
      XmlAttribute attribute = (XmlAttribute)field.getAnnotation(XmlAttribute.class);
      if (attribute != null) {
         String var10000 = formatElementOrAttribute(elementClazz, field);
         return var10000 + (value != null ? "=" + String.valueOf(value) : "");
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static Class getElementType(Field field) {
      Class<?> elementClazz = field.getType();
      if (List.class.isAssignableFrom(elementClazz)) {
         CollectionElementType collectionElementType = (CollectionElementType)field.getAnnotation(CollectionElementType.class);
         if (collectionElementType == null) {
            throw new IllegalArgumentException();
         }

         elementClazz = collectionElementType.value();
      }

      return elementClazz;
   }

   private static String getElementName(Class clazz) {
      while(clazz != null) {
         XmlRootElement rootElement = (XmlRootElement)clazz.getAnnotation(XmlRootElement.class);
         if (rootElement != null) {
            return rootElement.name();
         }

         clazz = clazz.getSuperclass();
      }

      throw new IllegalArgumentException();
   }
}
