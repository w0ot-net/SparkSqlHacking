package com.univocity.parsers.annotations;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class HeaderTransformer {
   public final String transformName(AnnotatedElement element, String name) {
      return element instanceof Field ? this.transformName((Field)element, name) : this.transformName((Method)element, name);
   }

   public final int transformIndex(AnnotatedElement element, int index) {
      return element instanceof Field ? this.transformIndex((Field)element, index) : this.transformIndex((Method)element, index);
   }

   public String transformName(Field field, String name) {
      return name;
   }

   public int transformIndex(Field field, int index) {
      return index;
   }

   public String transformName(Method method, String name) {
      return name;
   }

   public int transformIndex(Method method, int index) {
      return index;
   }
}
