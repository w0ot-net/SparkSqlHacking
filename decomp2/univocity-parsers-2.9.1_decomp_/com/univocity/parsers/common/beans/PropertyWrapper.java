package com.univocity.parsers.common.beans;

import java.lang.reflect.Method;

public final class PropertyWrapper {
   private static final Method NO_METHOD = getNullMethod();
   private static final String NO_NAME = "!!NO_NAME!!";
   private final Object propertyDescriptor;
   private Method writeMethod;
   private Method readMethod;
   private String name;

   PropertyWrapper(Object propertyDescriptor) {
      this.propertyDescriptor = propertyDescriptor;
   }

   public final Method getWriteMethod() {
      if (this.writeMethod == null) {
         this.writeMethod = (Method)invoke(this.propertyDescriptor, BeanHelper.PROPERTY_WRITE_METHOD);
      }

      return this.writeMethod == NO_METHOD ? null : this.writeMethod;
   }

   public final Method getReadMethod() {
      if (this.readMethod == null) {
         this.readMethod = (Method)invoke(this.propertyDescriptor, BeanHelper.PROPERTY_READ_METHOD);
      }

      return this.readMethod == NO_METHOD ? null : this.readMethod;
   }

   public final String getName() {
      if (this.name == null) {
         this.name = (String)invoke(this.propertyDescriptor, BeanHelper.PROPERTY_NAME_METHOD);
      }

      return this.name == "!!NO_NAME!!" ? null : this.name;
   }

   private static Object invoke(Object propertyDescriptor, Method method) {
      try {
         return method.invoke(propertyDescriptor);
      } catch (Exception var3) {
         return null;
      }
   }

   private static Method getNullMethod() {
      try {
         return Object.class.getMethod("hashCode");
      } catch (NoSuchMethodException var1) {
         return null;
      }
   }
}
