package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.MethodAccess;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BeanSerializer extends Serializer {
   static final Object[] noArgs = new Object[0];
   private CachedProperty[] properties;
   Object access;

   public BeanSerializer(Kryo kryo, Class type) {
      BeanInfo info;
      try {
         info = Introspector.getBeanInfo(type);
      } catch (IntrospectionException ex) {
         throw new KryoException("Error getting bean info.", ex);
      }

      PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
      Arrays.sort(descriptors, new Comparator() {
         public int compare(PropertyDescriptor o1, PropertyDescriptor o2) {
            return o1.getName().compareTo(o2.getName());
         }
      });
      ArrayList<CachedProperty> cachedProperties = new ArrayList(descriptors.length);
      int i = 0;

      for(int n = descriptors.length; i < n; ++i) {
         PropertyDescriptor property = descriptors[i];
         String name = property.getName();
         if (!name.equals("class")) {
            Method getMethod = property.getReadMethod();
            Method setMethod = property.getWriteMethod();
            if (getMethod != null && setMethod != null) {
               Serializer serializer = null;
               Class returnType = getMethod.getReturnType();
               if (kryo.isFinal(returnType)) {
                  serializer = kryo.getRegistration(returnType).getSerializer();
               }

               CachedProperty cachedProperty = new CachedProperty();
               cachedProperty.name = name;
               cachedProperty.getMethod = getMethod;
               cachedProperty.setMethod = setMethod;
               cachedProperty.serializer = serializer;
               cachedProperty.setMethodType = setMethod.getParameterTypes()[0];
               cachedProperties.add(cachedProperty);
            }
         }
      }

      this.properties = (CachedProperty[])cachedProperties.toArray(new CachedProperty[cachedProperties.size()]);

      try {
         this.access = MethodAccess.get(type);
         i = 0;

         for(int n = this.properties.length; i < n; ++i) {
            CachedProperty property = this.properties[i];
            property.getterAccessIndex = ((MethodAccess)this.access).getIndex(property.getMethod.getName(), property.getMethod.getParameterTypes());
            property.setterAccessIndex = ((MethodAccess)this.access).getIndex(property.setMethod.getName(), property.setMethod.getParameterTypes());
         }
      } catch (Throwable var16) {
      }

   }

   public void write(Kryo kryo, Output output, Object object) {
      Class type = object.getClass();
      int i = 0;

      for(int n = this.properties.length; i < n; ++i) {
         CachedProperty property = this.properties[i];

         try {
            if (Log.TRACE) {
               Log.trace("kryo", "Write property: " + property + " (" + type.getName() + ")");
            }

            Object value = property.get(object);
            Serializer serializer = property.serializer;
            if (serializer != null) {
               kryo.writeObjectOrNull(output, value, serializer);
            } else {
               kryo.writeClassAndObject(output, value);
            }
         } catch (IllegalAccessException ex) {
            throw new KryoException("Error accessing getter method: " + property + " (" + type.getName() + ")", ex);
         } catch (InvocationTargetException ex) {
            throw new KryoException("Error invoking getter method: " + property + " (" + type.getName() + ")", ex);
         } catch (KryoException ex) {
            ex.addTrace(property + " (" + type.getName() + ")");
            throw ex;
         } catch (RuntimeException runtimeEx) {
            KryoException ex = new KryoException(runtimeEx);
            ex.addTrace(property + " (" + type.getName() + ")");
            throw ex;
         }
      }

   }

   public Object read(Kryo kryo, Input input, Class type) {
      T object = (T)kryo.newInstance(type);
      kryo.reference(object);
      int i = 0;

      for(int n = this.properties.length; i < n; ++i) {
         CachedProperty property = this.properties[i];

         try {
            if (Log.TRACE) {
               Log.trace("kryo", "Read property: " + property + " (" + object.getClass() + ")");
            }

            Serializer serializer = property.serializer;
            Object value;
            if (serializer != null) {
               value = kryo.readObjectOrNull(input, property.setMethodType, serializer);
            } else {
               value = kryo.readClassAndObject(input);
            }

            property.set(object, value);
         } catch (IllegalAccessException ex) {
            throw new KryoException("Error accessing setter method: " + property + " (" + object.getClass().getName() + ")", ex);
         } catch (InvocationTargetException ex) {
            throw new KryoException("Error invoking setter method: " + property + " (" + object.getClass().getName() + ")", ex);
         } catch (KryoException ex) {
            ex.addTrace(property + " (" + object.getClass().getName() + ")");
            throw ex;
         } catch (RuntimeException runtimeEx) {
            KryoException ex = new KryoException(runtimeEx);
            ex.addTrace(property + " (" + object.getClass().getName() + ")");
            throw ex;
         }
      }

      return object;
   }

   public Object copy(Kryo kryo, Object original) {
      T copy = (T)kryo.newInstance(original.getClass());
      int i = 0;

      for(int n = this.properties.length; i < n; ++i) {
         CachedProperty property = this.properties[i];

         try {
            Object value = property.get(original);
            property.set(copy, value);
         } catch (KryoException ex) {
            ex.addTrace(property + " (" + copy.getClass().getName() + ")");
            throw ex;
         } catch (RuntimeException runtimeEx) {
            KryoException ex = new KryoException(runtimeEx);
            ex.addTrace(property + " (" + copy.getClass().getName() + ")");
            throw ex;
         } catch (Exception ex) {
            throw new KryoException("Error copying bean property: " + property + " (" + copy.getClass().getName() + ")", ex);
         }
      }

      return copy;
   }

   class CachedProperty {
      String name;
      Method getMethod;
      Method setMethod;
      Class setMethodType;
      Serializer serializer;
      int getterAccessIndex;
      int setterAccessIndex;

      public String toString() {
         return this.name;
      }

      Object get(Object object) throws IllegalAccessException, InvocationTargetException {
         return BeanSerializer.this.access != null ? ((MethodAccess)BeanSerializer.this.access).invoke(object, this.getterAccessIndex) : this.getMethod.invoke(object, BeanSerializer.noArgs);
      }

      void set(Object object, Object value) throws IllegalAccessException, InvocationTargetException {
         if (BeanSerializer.this.access != null) {
            ((MethodAccess)BeanSerializer.this.access).invoke(object, this.setterAccessIndex, value);
         } else {
            this.setMethod.invoke(object, value);
         }
      }
   }
}
