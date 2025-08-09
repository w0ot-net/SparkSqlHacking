package jodd.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import jodd.typeconverter.Convert;

public abstract class AnnotationDataReader {
   protected final Annotation defaultAnnotation;
   protected final Class annotationClass;

   protected AnnotationDataReader(Class annotationClass, Class defaultAnnotationClass) {
      if (annotationClass == null) {
         Class[] genericSupertypes = ReflectUtil.getGenericSupertypes(this.getClass());
         if (genericSupertypes != null) {
            annotationClass = genericSupertypes[0];
         }

         if (annotationClass == null || annotationClass == Annotation.class) {
            throw new IllegalArgumentException("Missing annotation from generics supertype");
         }
      }

      this.annotationClass = annotationClass;
      if (defaultAnnotationClass != null && defaultAnnotationClass != annotationClass) {
         Annotation defaultAnnotation = annotationClass.getAnnotation(defaultAnnotationClass);
         if (defaultAnnotation == null) {
            try {
               defaultAnnotation = (Annotation)defaultAnnotationClass.newInstance();
            } catch (Exception var5) {
            }
         }

         this.defaultAnnotation = defaultAnnotation;
      } else {
         this.defaultAnnotation = null;
      }

   }

   public Class getAnnotationClass() {
      return this.annotationClass;
   }

   public boolean hasAnnotation(AccessibleObject accessibleObject) {
      return accessibleObject.isAnnotationPresent(this.annotationClass);
   }

   public AnnotationData readAnnotationData(AccessibleObject accessibleObject) {
      A annotation = (A)accessibleObject.getAnnotation(this.annotationClass);
      return annotation == null ? null : this.createAnnotationData(annotation);
   }

   protected abstract AnnotationData createAnnotationData(Annotation var1);

   protected String readStringElement(Annotation annotation, String name) {
      Object annotationValue = ReflectUtil.readAnnotationValue(annotation, name);
      if (annotationValue == null) {
         if (this.defaultAnnotation == null) {
            return null;
         }

         annotationValue = ReflectUtil.readAnnotationValue(this.defaultAnnotation, name);
         if (annotationValue == null) {
            return null;
         }
      }

      String value = Convert.toString(annotationValue);
      return value.trim();
   }

   protected Object readElement(Annotation annotation, String name) {
      Object annotationValue = ReflectUtil.readAnnotationValue(annotation, name);
      if (annotationValue == null && this.defaultAnnotation != null) {
         annotationValue = ReflectUtil.readAnnotationValue(this.defaultAnnotation, name);
      }

      return annotationValue;
   }

   public abstract static class AnnotationData {
      protected final Annotation annotation;

      protected AnnotationData(Annotation annotation) {
         this.annotation = annotation;
      }

      public Annotation getAnnotation() {
         return this.annotation;
      }
   }
}
