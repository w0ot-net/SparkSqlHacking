package org.glassfish.jersey.internal.inject;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.ClassTypePair;
import org.glassfish.jersey.message.internal.HttpDateFormat;

@Singleton
public class ParamConverters {
   private static class ParamConverterCompliance {
      protected final boolean canReturnNull;

      private ParamConverterCompliance(boolean canReturnNull) {
         this.canReturnNull = canReturnNull;
      }

      protected Object nullOrThrow() {
         if (this.canReturnNull) {
            return null;
         } else {
            throw new IllegalArgumentException(LocalizationMessages.METHOD_PARAMETER_CANNOT_BE_NULL("value"));
         }
      }
   }

   private abstract static class AbstractStringReader extends ParamConverterCompliance implements ParamConverter {
      private AbstractStringReader(boolean canReturnNull) {
         super(canReturnNull, null);
      }

      public Object fromString(String value) {
         if (value == null) {
            return this.nullOrThrow();
         } else {
            try {
               return this._fromString(value);
            } catch (InvocationTargetException ex) {
               if (value.isEmpty()) {
                  return null;
               } else {
                  Throwable cause = ex.getCause();
                  if (cause instanceof WebApplicationException) {
                     throw (WebApplicationException)cause;
                  } else {
                     throw new ExtractorException(cause);
                  }
               }
            } catch (Exception ex) {
               throw new ProcessingException(ex);
            }
         }
      }

      protected abstract Object _fromString(String var1) throws Exception;

      public String toString(Object value) throws IllegalArgumentException {
         return value == null ? (String)this.nullOrThrow() : value.toString();
      }
   }

   @Singleton
   public static class StringConstructor extends ParamConverterCompliance implements ParamConverterProvider {
      private StringConstructor(boolean canReturnNull) {
         super(canReturnNull, null);
      }

      public ParamConverter getConverter(final Class rawType, Type genericType, Annotation[] annotations) {
         final Constructor constructor = (Constructor)AccessController.doPrivileged(ReflectionHelper.getStringConstructorPA(rawType));
         return constructor == null ? null : new AbstractStringReader(this.canReturnNull) {
            protected Object _fromString(String value) throws Exception {
               return rawType.cast(constructor.newInstance(value));
            }
         };
      }
   }

   @Singleton
   public static class TypeValueOf extends ParamConverterCompliance implements ParamConverterProvider {
      private TypeValueOf(boolean canReturnNull) {
         super(canReturnNull, null);
      }

      public ParamConverter getConverter(final Class rawType, Type genericType, Annotation[] annotations) {
         final Method valueOf = (Method)AccessController.doPrivileged(ReflectionHelper.getValueOfStringMethodPA(rawType));
         return valueOf == null ? null : new AbstractStringReader(this.canReturnNull) {
            public Object _fromString(String value) throws Exception {
               return rawType.cast(valueOf.invoke((Object)null, value));
            }
         };
      }
   }

   @Singleton
   public static class TypeFromString extends ParamConverterCompliance implements ParamConverterProvider {
      private TypeFromString(boolean canReturnNull) {
         super(canReturnNull, null);
      }

      public ParamConverter getConverter(final Class rawType, Type genericType, Annotation[] annotations) {
         final Method fromStringMethod = (Method)AccessController.doPrivileged(ReflectionHelper.getFromStringStringMethodPA(rawType));
         return fromStringMethod == null ? null : new AbstractStringReader(this.canReturnNull) {
            public Object _fromString(String value) throws Exception {
               return rawType.cast(fromStringMethod.invoke((Object)null, value));
            }
         };
      }
   }

   @Singleton
   public static class TypeFromStringEnum extends TypeFromString {
      private TypeFromStringEnum(boolean canReturnNull) {
         super(canReturnNull, null);
      }

      public ParamConverter getConverter(Class rawType, Type genericType, Annotation[] annotations) {
         return !Enum.class.isAssignableFrom(rawType) ? null : super.getConverter(rawType, genericType, annotations);
      }
   }

   @Singleton
   public static class CharacterProvider extends ParamConverterCompliance implements ParamConverterProvider {
      private CharacterProvider(boolean canReturnNull) {
         super(canReturnNull, null);
      }

      public ParamConverter getConverter(final Class rawType, Type genericType, Annotation[] annotations) {
         return rawType.equals(Character.class) ? new ParamConverter() {
            public Object fromString(String value) {
               if (value != null && !value.isEmpty()) {
                  if (value.length() == 1) {
                     return rawType.cast(value.charAt(0));
                  } else {
                     throw new ExtractorException(LocalizationMessages.ERROR_PARAMETER_INVALID_CHAR_VALUE(value));
                  }
               } else {
                  return CharacterProvider.this.nullOrThrow();
               }
            }

            public String toString(Object value) {
               return value == null ? (String)CharacterProvider.this.nullOrThrow() : value.toString();
            }
         } : null;
      }
   }

   @Singleton
   public static class DateProvider extends ParamConverterCompliance implements ParamConverterProvider {
      private DateProvider(boolean canReturnNull) {
         super(canReturnNull, null);
      }

      public ParamConverter getConverter(final Class rawType, Type genericType, Annotation[] annotations) {
         return rawType != Date.class ? null : new ParamConverter() {
            public Object fromString(String value) {
               if (value == null) {
                  return DateProvider.this.nullOrThrow();
               } else {
                  try {
                     return rawType.cast(HttpDateFormat.readDate(value));
                  } catch (ParseException ex) {
                     throw new ExtractorException(ex);
                  }
               }
            }

            public String toString(Object value) throws IllegalArgumentException {
               return value == null ? (String)DateProvider.this.nullOrThrow() : value.toString();
            }
         };
      }
   }

   @Singleton
   public static class OptionalCustomProvider extends ParamConverterCompliance implements ParamConverterProvider {
      private final InjectionManager manager;

      public OptionalCustomProvider(InjectionManager manager, boolean canReturnNull) {
         super(canReturnNull, null);
         this.manager = manager;
      }

      public ParamConverter getConverter(Class rawType, final Type genericType, final Annotation[] annotations) {
         return rawType != Optional.class ? null : new ParamConverter() {
            public Object fromString(String value) {
               if (value == null) {
                  return Optional.empty();
               } else {
                  List<ClassTypePair> ctps = ReflectionHelper.getTypeArgumentAndClass(genericType);
                  ClassTypePair ctp = ctps.size() == 1 ? (ClassTypePair)ctps.get(0) : null;
                  boolean empty = value.isEmpty();

                  for(ParamConverterProvider provider : Providers.getProviders(OptionalCustomProvider.this.manager, ParamConverterProvider.class)) {
                     ParamConverter<?> converter = provider.getConverter(ctp.rawClass(), ctp.type(), annotations);
                     if (converter != null) {
                        if (empty) {
                           return Optional.empty();
                        }

                        return Optional.of(value).map((s) -> converter.fromString(value));
                     }
                  }

                  return OptionalCustomProvider.this.nullOrThrow();
               }
            }

            public String toString(Object value) throws IllegalArgumentException {
               return null;
            }
         };
      }
   }

   @Singleton
   public static class OptionalProvider implements ParamConverterProvider {
      public ParamConverter getConverter(Class rawType, Type genericType, Annotation[] annotations) {
         final Optionals optionals = ParamConverters.OptionalProvider.Optionals.getOptional(rawType);
         return optionals == null ? null : new ParamConverter() {
            public Object fromString(String value) {
               return value != null && !value.isEmpty() ? optionals.of(value) : optionals.empty();
            }

            public String toString(Object value) throws IllegalArgumentException {
               return null;
            }
         };
      }

      private static enum Optionals {
         OPTIONAL_INT(OptionalInt.class) {
            Object empty() {
               return OptionalInt.empty();
            }

            Object of(Object value) {
               return OptionalInt.of(Integer.parseInt((String)value));
            }
         },
         OPTIONAL_DOUBLE(OptionalDouble.class) {
            Object empty() {
               return OptionalDouble.empty();
            }

            Object of(Object value) {
               return OptionalDouble.of(Double.parseDouble((String)value));
            }
         },
         OPTIONAL_LONG(OptionalLong.class) {
            Object empty() {
               return OptionalLong.empty();
            }

            Object of(Object value) {
               return OptionalLong.of(Long.parseLong((String)value));
            }
         };

         private final Class clazz;

         private Optionals(Class clazz) {
            this.clazz = clazz;
         }

         private static Optionals getOptional(Class clazz) {
            for(Optionals optionals : values()) {
               if (optionals.clazz == clazz) {
                  return optionals;
               }
            }

            return null;
         }

         abstract Object empty();

         abstract Object of(Object var1);
      }
   }

   @Singleton
   public static class AggregatedProvider implements ParamConverterProvider {
      private final ParamConverterProvider[] providers;

      @Inject
      public AggregatedProvider(@Context InjectionManager manager, @Context Configuration configuration) {
         boolean canThrowNull = !(Boolean)CommonProperties.getValue(configuration.getProperties(), "jersey.config.paramconverters.throw.iae", (Object)Boolean.FALSE);
         this.providers = new ParamConverterProvider[]{new DateProvider(canThrowNull), new TypeFromStringEnum(canThrowNull), new TypeValueOf(canThrowNull), new CharacterProvider(canThrowNull), new TypeFromString(canThrowNull), new StringConstructor(canThrowNull), new OptionalCustomProvider(manager, canThrowNull), new OptionalProvider()};
      }

      public ParamConverter getConverter(Class rawType, Type genericType, Annotation[] annotations) {
         for(ParamConverterProvider p : this.providers) {
            ParamConverter<T> reader = p.getConverter(rawType, genericType, annotations);
            if (reader != null) {
               return reader;
            }
         }

         return null;
      }
   }
}
