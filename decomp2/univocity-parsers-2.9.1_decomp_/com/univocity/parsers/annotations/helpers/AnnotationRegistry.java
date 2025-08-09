package com.univocity.parsers.annotations.helpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class AnnotationRegistry {
   private static final Map modifiedAnnotations = new HashMap();

   static final synchronized void setValue(AnnotatedElement annotatedElement, Annotation annotation, String attribute, Object newValue) {
      FieldAnnotations attributes = (FieldAnnotations)modifiedAnnotations.get(annotatedElement);
      if (attributes == null) {
         attributes = new FieldAnnotations();
         modifiedAnnotations.put(annotatedElement, attributes);
      }

      attributes.setValue(annotation, attribute, newValue);
   }

   public static final synchronized Object getValue(AnnotatedElement annotatedElement, Annotation annotation, String attribute, Object valueIfNull) {
      if (annotatedElement == null) {
         return valueIfNull;
      } else {
         Object value = getValue(annotatedElement, annotation, attribute);
         return value == null ? valueIfNull : value;
      }
   }

   static final synchronized Object getValue(AnnotatedElement annotatedElement, Annotation annotation, String attribute) {
      FieldAnnotations attributes = (FieldAnnotations)modifiedAnnotations.get(annotatedElement);
      return attributes == null ? null : attributes.getValue(annotation, attribute);
   }

   public static final void reset() {
      modifiedAnnotations.clear();
   }

   private static class FieldAnnotations {
      private Map annotations;

      private FieldAnnotations() {
         this.annotations = new HashMap();
      }

      private void setValue(Annotation annotation, String attribute, Object newValue) {
         AnnotationAttributes attributes = (AnnotationAttributes)this.annotations.get(annotation);
         if (attributes == null) {
            attributes = new AnnotationAttributes();
            this.annotations.put(annotation, attributes);
         }

         attributes.setAttribute(attribute, newValue);
      }

      private Object getValue(Annotation annotation, String attribute) {
         AnnotationAttributes attributes = (AnnotationAttributes)this.annotations.get(annotation);
         return attributes == null ? null : attributes.getAttribute(attribute);
      }
   }

   private static class AnnotationAttributes {
      private Map attributes;

      private AnnotationAttributes() {
         this.attributes = new HashMap();
      }

      private void setAttribute(String attribute, Object newValue) {
         if (!this.attributes.containsKey(attribute)) {
            this.attributes.put(attribute, newValue);
         } else {
            Object existingValue = this.attributes.get(attribute);
            if (existingValue == null || newValue == null) {
               return;
            }

            Class originalClass = existingValue.getClass();
            Class newClass = newValue.getClass();
            if (originalClass != newClass && newClass.isArray() && newClass.getComponentType() == existingValue.getClass()) {
               Object array = Array.newInstance(originalClass, 1);
               Array.set(array, 0, existingValue);
               this.attributes.put(attribute, array);
            }
         }

      }

      private Object getAttribute(String attribute) {
         return this.attributes.get(attribute);
      }
   }
}
