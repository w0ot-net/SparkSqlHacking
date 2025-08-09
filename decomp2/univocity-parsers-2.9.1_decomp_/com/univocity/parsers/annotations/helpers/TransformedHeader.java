package com.univocity.parsers.annotations.helpers;

import com.univocity.parsers.annotations.HeaderTransformer;
import com.univocity.parsers.annotations.Parsed;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TransformedHeader {
   private final AnnotatedElement target;
   private final Field field;
   private final Method method;
   private final HeaderTransformer transformer;
   private int index = -2;

   public TransformedHeader(AnnotatedElement target, HeaderTransformer transformer) {
      if (target instanceof Field) {
         this.field = (Field)target;
         this.method = null;
      } else {
         this.method = (Method)target;
         this.field = null;
      }

      this.target = target;
      this.transformer = transformer;
   }

   public String getHeaderName() {
      if (this.target == null) {
         return null;
      } else {
         String name = null;
         Parsed annotation = (Parsed)AnnotationHelper.findAnnotation(this.target, Parsed.class);
         if (annotation != null) {
            String[] field = (String[])AnnotationRegistry.getValue(this.target, annotation, "field", annotation.field());
            if (field.length == 0) {
               name = this.getTargetName();
            } else {
               name = field[0];
            }

            if (name.length() == 0) {
               name = this.getTargetName();
            }
         }

         if (this.transformer != null) {
            return this.field != null ? this.transformer.transformName(this.field, name) : this.transformer.transformName(this.method, name);
         } else {
            return name;
         }
      }
   }

   public int getHeaderIndex() {
      if (this.index == -2) {
         Parsed annotation = (Parsed)AnnotationHelper.findAnnotation(this.target, Parsed.class);
         if (annotation != null) {
            this.index = (Integer)AnnotationRegistry.getValue(this.target, annotation, "index", annotation.index());
            if (this.index != -1 && this.transformer != null) {
               if (this.field != null) {
                  this.index = this.transformer.transformIndex(this.field, this.index);
               } else {
                  this.index = this.transformer.transformIndex(this.method, this.index);
               }
            }
         } else {
            this.index = -1;
         }
      }

      return this.index;
   }

   public String getTargetName() {
      if (this.target == null) {
         return null;
      } else {
         return this.field != null ? this.field.getName() : this.method.getName();
      }
   }

   public AnnotatedElement getTarget() {
      return this.target;
   }

   public boolean isWriteOnly() {
      if (this.method != null) {
         return this.method.getParameterTypes().length != 0;
      } else {
         return false;
      }
   }

   public boolean isReadOly() {
      if (this.method == null) {
         return false;
      } else {
         return this.method.getParameterTypes().length == 0 && this.method.getReturnType() != Void.TYPE;
      }
   }

   public String describe() {
      return AnnotationHelper.describeElement(this.target);
   }
}
