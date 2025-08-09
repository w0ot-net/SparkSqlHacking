package com.univocity.parsers.common.beans;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BeanHelper {
   private static final PropertyWrapper[] EMPTY = new PropertyWrapper[0];
   private static final Class introspectorClass = findIntrospectorImplementationClass();
   private static final Method beanInfoMethod = getBeanInfoMethod();
   private static final Method propertyDescriptorMethod;
   static Method PROPERTY_WRITE_METHOD;
   static Method PROPERTY_READ_METHOD;
   static Method PROPERTY_NAME_METHOD;
   private static final Map descriptors;

   private BeanHelper() {
   }

   public static PropertyWrapper[] getPropertyDescriptors(Class beanClass) {
      if (propertyDescriptorMethod == null) {
         return EMPTY;
      } else {
         PropertyWrapper[] out = null;
         WeakReference<PropertyWrapper[]> reference = (WeakReference)descriptors.get(beanClass);
         if (reference != null) {
            out = (PropertyWrapper[])reference.get();
         }

         if (out == null) {
            try {
               Object beanInfo = beanInfoMethod.invoke((Object)null, beanClass, Object.class);
               Object[] propertyDescriptors = propertyDescriptorMethod.invoke(beanInfo);
               out = new PropertyWrapper[propertyDescriptors.length];

               for(int i = 0; i < propertyDescriptors.length; ++i) {
                  out[i] = new PropertyWrapper(propertyDescriptors[i]);
               }
            } catch (Exception var6) {
               out = EMPTY;
            }

            descriptors.put(beanClass, new WeakReference(out));
         }

         return out;
      }
   }

   private static Class findIntrospectorImplementationClass() {
      try {
         return Class.forName("com.googlecode.openbeans.Introspector");
      } catch (Throwable var3) {
         try {
            return Class.forName("java.beans.Introspector");
         } catch (Throwable var2) {
            return null;
         }
      }
   }

   private static Method getBeanInfoMethod() {
      if (introspectorClass == null) {
         return null;
      } else {
         try {
            return introspectorClass.getMethod("getBeanInfo", Class.class, Class.class);
         } catch (Throwable var1) {
            return null;
         }
      }
   }

   private static Method getMethod(String methodName, Method method, boolean fromComponentType) {
      if (method == null) {
         return null;
      } else {
         try {
            Class<?> returnType = method.getReturnType();
            if (fromComponentType) {
               returnType = returnType.getComponentType();
            }

            return returnType.getMethod(methodName);
         } catch (Exception var4) {
            return null;
         }
      }
   }

   static {
      propertyDescriptorMethod = getMethod("getPropertyDescriptors", beanInfoMethod, false);
      PROPERTY_WRITE_METHOD = getMethod("getWriteMethod", propertyDescriptorMethod, true);
      PROPERTY_READ_METHOD = getMethod("getReadMethod", propertyDescriptorMethod, true);
      PROPERTY_NAME_METHOD = getMethod("getName", propertyDescriptorMethod, true);
      descriptors = new ConcurrentHashMap();
   }
}
