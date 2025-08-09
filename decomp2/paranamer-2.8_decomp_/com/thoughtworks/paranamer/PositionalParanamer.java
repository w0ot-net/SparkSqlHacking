package com.thoughtworks.paranamer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class PositionalParanamer implements Paranamer {
   private final String prefix;
   public static final String __PARANAMER_DATA = "<init> java.lang.String prefix \nlookupParameterNames java.lang.reflect.AccessibleObject methodOrConstructor \nlookupParameterNames java.lang.reflect.AccessibleObject,boolean methodOrCtor,throwExceptionIfMissing \n";

   public PositionalParanamer() {
      this("arg");
   }

   public PositionalParanamer(String prefix) {
      this.prefix = prefix;
   }

   public String[] lookupParameterNames(AccessibleObject methodOrConstructor) {
      return this.lookupParameterNames(methodOrConstructor, true);
   }

   public String[] lookupParameterNames(AccessibleObject methodOrCtor, boolean throwExceptionIfMissing) {
      int count = this.count(methodOrCtor);
      String[] result = new String[count];

      for(int i = 0; i < result.length; ++i) {
         result[i] = this.prefix + i;
      }

      return result;
   }

   private int count(AccessibleObject methodOrCtor) {
      if (methodOrCtor instanceof Method) {
         Method method = (Method)methodOrCtor;
         return method.getParameterTypes().length;
      } else {
         Constructor<?> constructor = (Constructor)methodOrCtor;
         return constructor.getParameterTypes().length;
      }
   }
}
