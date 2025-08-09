package org.sparkproject.jpmml.model.visitors;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlEnumValue;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Collection;
import org.sparkproject.dmg.pmml.Apply;
import org.sparkproject.dmg.pmml.PMMLAttributes;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.VersionUtil;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;

public abstract class VersionInspector extends AbstractVisitor {
   public abstract void handleAdded(PMMLObject var1, AnnotatedElement var2, Added var3);

   public abstract void handleRemoved(PMMLObject var1, AnnotatedElement var2, Removed var3);

   public abstract void handleOptional(PMMLObject var1, AnnotatedElement var2, Optional var3);

   public abstract void handleRequired(PMMLObject var1, AnnotatedElement var2, Required var3);

   public VisitorAction visit(PMMLObject object) {
      for(Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
         this.inspect(object, clazz);
      }

      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         Object value = ReflectionUtil.getFieldValue(field, object);
         this.inspect(object, field, value);
         if (value instanceof Enum) {
            Enum<?> enumValue = (Enum)value;

            Field enumField;
            try {
               Class<?> enumClazz = enumValue.getClass();
               enumField = enumClazz.getField(enumValue.name());
            } catch (NoSuchFieldException nsfe) {
               throw new RuntimeException(nsfe);
            }

            this.inspect(object, enumField);
         }
      }

      return super.visit(object);
   }

   public VisitorAction visit(Apply apply) {
      String function = apply.getFunction();
      final Version version = VersionUtil.getVersion(function);
      if (version != null) {
         Added added = new Added() {
            public Class annotationType() {
               return Added.class;
            }

            public Version value() {
               return version;
            }

            public boolean removable() {
               return false;
            }
         };
         this.handleAdded(apply, PMMLAttributes.APPLY_FUNCTION, added);
      }

      return super.visit(apply);
   }

   private void inspect(PMMLObject object, AnnotatedElement element) {
      Added added = (Added)element.getAnnotation(Added.class);
      if (added != null) {
         this.handleAdded(object, element, added);
      }

      Removed removed = (Removed)element.getAnnotation(Removed.class);
      if (removed != null) {
         this.handleRemoved(object, element, removed);
      }

   }

   private void inspect(PMMLObject object, Field field, Object value) {
      Class<?> type = field.getType();
      if (type.isPrimitive()) {
         if (ReflectionUtil.isDefaultValue(value)) {
            return;
         }
      } else if (isNull(value)) {
         Optional optional = (Optional)field.getAnnotation(Optional.class);
         if (optional != null) {
            this.handleOptional(object, field, optional);
         }

         Required required = (Required)field.getAnnotation(Required.class);
         if (required != null) {
            this.handleRequired(object, field, required);
         }

         return;
      }

      this.inspect(object, field);
   }

   private static boolean isNull(Object value) {
      if (value instanceof Collection) {
         Collection<?> collection = (Collection)value;
         return collection.isEmpty();
      } else {
         return value == null;
      }
   }

   protected static boolean isAttribute(Field field) {
      XmlAttribute xmlAttribute = (XmlAttribute)field.getAnnotation(XmlAttribute.class);
      return xmlAttribute != null;
   }

   protected static boolean isEnumValue(Field field) {
      XmlEnumValue xmlEnumValue = (XmlEnumValue)field.getAnnotation(XmlEnumValue.class);
      return xmlEnumValue != null;
   }

   protected static boolean isElement(Field field) {
      XmlElement xmlElement = (XmlElement)field.getAnnotation(XmlElement.class);
      XmlElements xmlElements = (XmlElements)field.getAnnotation(XmlElements.class);
      return xmlElement != null || xmlElements != null;
   }
}
