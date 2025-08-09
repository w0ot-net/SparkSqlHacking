package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import com.sun.istack.Nullable;
import jakarta.activation.DataHandler;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.awt.Image;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import org.glassfish.jaxb.core.v2.model.core.Adapter;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Receiver;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

public abstract class Accessor implements Receiver {
   public final Class valueType;
   private static List nonAbstractableClasses = Arrays.asList(Object.class, Calendar.class, Duration.class, XMLGregorianCalendar.class, Image.class, DataHandler.class, Source.class, Date.class, File.class, URI.class, URL.class, Class.class, String.class, Source.class);
   private static boolean accessWarned = false;
   private static final Accessor ERROR = new Accessor(Object.class) {
      public Object get(Object o) {
         return null;
      }

      public void set(Object o, Object o1) {
      }
   };
   public static final Accessor JAXB_ELEMENT_VALUE = new Accessor(Object.class) {
      public Object get(JAXBElement jaxbElement) {
         return jaxbElement.getValue();
      }

      public void set(JAXBElement jaxbElement, Object o) {
         jaxbElement.setValue(o);
      }
   };
   private static final Map uninitializedValues = new HashMap();

   public Class getValueType() {
      return this.valueType;
   }

   protected Accessor(Class valueType) {
      this.valueType = valueType;
   }

   public Accessor optimize(@Nullable JAXBContextImpl context) {
      return this;
   }

   public abstract Object get(Object var1) throws AccessorException;

   public abstract void set(Object var1, Object var2) throws AccessorException;

   public Object getUnadapted(Object bean) throws AccessorException {
      return this.get(bean);
   }

   public boolean isAdapted() {
      return false;
   }

   public void setUnadapted(Object bean, Object value) throws AccessorException {
      this.set(bean, value);
   }

   public void receive(UnmarshallingContext.State state, Object o) throws SAXException {
      try {
         this.set(state.getTarget(), o);
      } catch (AccessorException e) {
         Loader.handleGenericException(e, true);
      } catch (IllegalAccessError iae) {
         Loader.handleGenericError(iae);
      }

   }

   public boolean isValueTypeAbstractable() {
      return !nonAbstractableClasses.contains(this.getValueType());
   }

   public boolean isAbstractable(Class clazz) {
      return !nonAbstractableClasses.contains(clazz);
   }

   public final Accessor adapt(Class targetType, Class adapter) {
      return new AdaptedAccessor(targetType, this, adapter);
   }

   public final Accessor adapt(Adapter adapter) {
      return new AdaptedAccessor((Class)Utils.REFLECTION_NAVIGATOR.erasure((Type)adapter.defaultType), this, (Class)adapter.adapterType);
   }

   public static Accessor getErrorInstance() {
      return ERROR;
   }

   static {
      uninitializedValues.put(Byte.TYPE, (byte)0);
      uninitializedValues.put(Boolean.TYPE, false);
      uninitializedValues.put(Character.TYPE, '\u0000');
      uninitializedValues.put(Float.TYPE, 0.0F);
      uninitializedValues.put(Double.TYPE, (double)0.0F);
      uninitializedValues.put(Integer.TYPE, 0);
      uninitializedValues.put(Long.TYPE, 0L);
      uninitializedValues.put(Short.TYPE, Short.valueOf((short)0));
   }

   public static class FieldReflection extends Accessor {
      public final Field f;
      private static final Logger logger = org.glassfish.jaxb.core.Utils.getClassLogger();

      public FieldReflection(Field f) {
         this(f, false);
      }

      public FieldReflection(Field f, boolean supressAccessorWarnings) {
         super(f.getType());
         this.f = f;
         int mod = f.getModifiers();
         if (!Modifier.isPublic(mod) || Modifier.isFinal(mod) || !Modifier.isPublic(f.getDeclaringClass().getModifiers())) {
            try {
               f.setAccessible(true);
            } catch (SecurityException e) {
               if (!Accessor.accessWarned && !supressAccessorWarnings) {
                  logger.log(Level.WARNING, Messages.UNABLE_TO_ACCESS_NON_PUBLIC_FIELD.format(f.getDeclaringClass().getName(), f.getName()), e);
               }

               Accessor.accessWarned = true;
            }
         }

      }

      public Object get(Object bean) {
         try {
            return this.f.get(bean);
         } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
         }
      }

      public void set(Object bean, Object value) {
         try {
            if (value == null) {
               value = (ValueT)Accessor.uninitializedValues.get(this.valueType);
            }

            this.f.set(bean, value);
         } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
         }
      }

      public Accessor optimize(JAXBContextImpl context) {
         return this;
      }
   }

   public static final class ReadOnlyFieldReflection extends FieldReflection {
      public ReadOnlyFieldReflection(Field f, boolean supressAccessorWarnings) {
         super(f, supressAccessorWarnings);
      }

      public ReadOnlyFieldReflection(Field f) {
         super(f);
      }

      public void set(Object bean, Object value) {
      }

      public Accessor optimize(JAXBContextImpl context) {
         return this;
      }
   }

   public static class GetterSetterReflection extends Accessor {
      public final Method getter;
      public final Method setter;
      private static final Logger logger = org.glassfish.jaxb.core.Utils.getClassLogger();

      public GetterSetterReflection(Method getter, Method setter) {
         super(getter != null ? getter.getReturnType() : setter.getParameterTypes()[0]);
         this.getter = getter;
         this.setter = setter;
         if (getter != null) {
            this.makeAccessible(getter);
         }

         if (setter != null) {
            this.makeAccessible(setter);
         }

      }

      private void makeAccessible(Method m) {
         if (!Modifier.isPublic(m.getModifiers()) || !Modifier.isPublic(m.getDeclaringClass().getModifiers())) {
            try {
               m.setAccessible(true);
            } catch (SecurityException e) {
               if (!Accessor.accessWarned) {
                  logger.log(Level.WARNING, Messages.UNABLE_TO_ACCESS_NON_PUBLIC_FIELD.format(m.getDeclaringClass().getName(), m.getName()), e);
               }

               Accessor.accessWarned = true;
            }
         }

      }

      public Object get(Object bean) throws AccessorException {
         try {
            return this.getter.invoke(bean);
         } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
         } catch (InvocationTargetException e) {
            throw this.handleInvocationTargetException(e);
         }
      }

      public void set(Object bean, Object value) throws AccessorException {
         try {
            if (value == null) {
               value = (ValueT)Accessor.uninitializedValues.get(this.valueType);
            }

            this.setter.invoke(bean, value);
         } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
         } catch (InvocationTargetException e) {
            throw this.handleInvocationTargetException(e);
         }
      }

      private AccessorException handleInvocationTargetException(InvocationTargetException e) {
         Throwable t = e.getTargetException();
         if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
         } else if (t instanceof Error) {
            throw (Error)t;
         } else {
            return new AccessorException(t);
         }
      }

      public Accessor optimize(JAXBContextImpl context) {
         return this;
      }
   }

   public static class GetterOnlyReflection extends GetterSetterReflection {
      public GetterOnlyReflection(Method getter) {
         super(getter, (Method)null);
      }

      public void set(Object bean, Object value) throws AccessorException {
         throw new AccessorException(Messages.NO_SETTER.format(this.getter.toString()));
      }
   }

   public static class SetterOnlyReflection extends GetterSetterReflection {
      public SetterOnlyReflection(Method setter) {
         super((Method)null, setter);
      }

      public Object get(Object bean) throws AccessorException {
         throw new AccessorException(Messages.NO_GETTER.format(this.setter.toString()));
      }
   }
}
