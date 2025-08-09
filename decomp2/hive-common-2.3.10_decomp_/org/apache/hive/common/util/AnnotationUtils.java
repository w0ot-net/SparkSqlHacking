package org.apache.hive.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtils {
   public static Annotation getAnnotation(Class clazz, Class annotationClass) {
      synchronized(annotationClass) {
         return clazz.getAnnotation(annotationClass);
      }
   }

   public static Annotation getAnnotation(Method method, Class annotationClass) {
      synchronized(annotationClass) {
         return method.getAnnotation(annotationClass);
      }
   }
}
