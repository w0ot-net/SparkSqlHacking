package com.univocity.parsers.annotations.helpers;

import java.lang.reflect.Method;

public enum MethodFilter {
   ONLY_GETTERS(new Filter() {
      public boolean reject(Method method) {
         return method.getReturnType() == Void.TYPE || method.getParameterTypes().length != 0;
      }
   }),
   ONLY_SETTERS(new Filter() {
      public boolean reject(Method method) {
         return method.getParameterTypes().length != 1;
      }
   });

   private Filter filter;

   private MethodFilter(Filter filter) {
      this.filter = filter;
   }

   public boolean reject(Method method) {
      return this.filter.reject(method);
   }

   public MethodDescriptor toDescriptor(String prefix, Method method) {
      if (this.reject(method)) {
         return null;
      } else {
         return this == ONLY_SETTERS ? MethodDescriptor.setter(prefix, method) : MethodDescriptor.getter(prefix, method);
      }
   }

   private interface Filter {
      boolean reject(Method var1);
   }
}
