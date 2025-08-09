package org.glassfish.jersey.model;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.Encoded;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Context;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.internal.guava.Lists;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.ClassTypePair;
import org.glassfish.jersey.model.internal.spi.ParameterServiceProvider;

public class Parameter implements AnnotatedElement {
   private static final Logger LOGGER = Logger.getLogger(Parameter.class.getName());
   private static final List PARAM_CREATION_FACTORIES;
   private static final Map ANNOTATION_HELPER_MAP;
   private final Annotation[] annotations;
   private final Annotation sourceAnnotation;
   private final Source source;
   private final String sourceName;
   private final boolean encoded;
   private final String defaultValue;
   private final Class rawType;
   private final Type type;

   protected Parameter(Annotation[] markers, Annotation marker, Source source, String sourceName, Class rawType, Type type, boolean encoded, String defaultValue) {
      this.annotations = markers;
      this.sourceAnnotation = marker;
      this.source = source;
      this.sourceName = sourceName;
      this.rawType = rawType;
      this.type = type;
      this.encoded = encoded;
      this.defaultValue = defaultValue;
   }

   public static Parameter create(Class concreteClass, Class declaringClass, boolean encodeByDefault, Class rawType, Type type, Annotation[] annotations) {
      return create(concreteClass, declaringClass, encodeByDefault, rawType, type, annotations, Parameter.class);
   }

   protected static Parameter create(Class concreteClass, Class declaringClass, boolean encodeByDefault, Class rawType, Type type, Annotation[] annotations, Class parameterClass) {
      if (null == annotations) {
         return null;
      } else {
         Annotation paramAnnotation = null;
         Source paramSource = null;
         String paramName = null;
         boolean paramEncoded = encodeByDefault;
         String paramDefault = null;

         for(Annotation annotation : annotations) {
            if (ANNOTATION_HELPER_MAP.containsKey(annotation.annotationType())) {
               ParamAnnotationHelper helper = (ParamAnnotationHelper)ANNOTATION_HELPER_MAP.get(annotation.annotationType());
               paramAnnotation = annotation;
               paramSource = helper.getSource();
               paramName = helper.getValueOf(annotation);
            } else if (Encoded.class == annotation.annotationType()) {
               paramEncoded = true;
            } else if (DefaultValue.class == annotation.annotationType()) {
               paramDefault = ((DefaultValue)annotation).value();
            } else if (paramAnnotation == null || paramSource == Parameter.Source.UNKNOWN) {
               paramAnnotation = annotation;
               paramSource = Parameter.Source.UNKNOWN;
               paramName = getValue(annotation);
            }
         }

         if (paramAnnotation == null) {
            paramSource = Parameter.Source.ENTITY;
         }

         ClassTypePair ct = ReflectionHelper.resolveGenericType(concreteClass, declaringClass, rawType, type);
         if (paramSource == Parameter.Source.BEAN_PARAM) {
            return createBeanParameter(annotations, paramAnnotation, paramSource, paramName, ct.rawClass(), ct.type(), paramEncoded, paramDefault, parameterClass);
         } else {
            return createParameter(annotations, paramAnnotation, paramSource, paramName, ct.rawClass(), ct.type(), paramEncoded, paramDefault, parameterClass);
         }
      }
   }

   private static Parameter createBeanParameter(Annotation[] markers, Annotation marker, Source source, String sourceName, Class rawType, Type type, boolean encoded, String defaultValue, Class parameterClass) {
      for(ParamCreationFactory factory : PARAM_CREATION_FACTORIES) {
         if (factory.isFor(parameterClass)) {
            return factory.createBeanParameter(markers, marker, source, sourceName, rawType, type, encoded, defaultValue);
         }
      }

      if (LOGGER.isLoggable(Level.FINER)) {
         LOGGER.log(Level.FINER, LocalizationMessages.PARAM_CREATION_FACTORY_NOT_FOUND(parameterClass.getName()));
      }

      throw new IllegalStateException(LocalizationMessages.PARAM_CREATION_FACTORY_NOT_FOUND(parameterClass.getName()));
   }

   private static Parameter createParameter(Annotation[] markers, Annotation marker, Source source, String sourceName, Class rawType, Type type, boolean encoded, String defaultValue, Class parameterClass) {
      for(ParamCreationFactory factory : PARAM_CREATION_FACTORIES) {
         if (factory.isFor(parameterClass)) {
            return factory.createParameter(markers, marker, source, sourceName, rawType, type, encoded, defaultValue);
         }
      }

      if (LOGGER.isLoggable(Level.FINER)) {
         LOGGER.log(Level.FINER, LocalizationMessages.PARAM_CREATION_FACTORY_NOT_FOUND(parameterClass.getName()));
      }

      throw new IllegalStateException(LocalizationMessages.PARAM_CREATION_FACTORY_NOT_FOUND(parameterClass.getName()));
   }

   public static List create(Class concreteClass, Class declaringClass, Method javaMethod, boolean keepEncoded) {
      return createList(concreteClass, declaringClass, javaMethod, keepEncoded, Parameter.class);
   }

   protected static List createList(Class concreteClass, Class declaringClass, Method javaMethod, boolean keepEncoded, Class parameterClass) {
      AnnotatedMethod method = new AnnotatedMethod(javaMethod);
      return createList(concreteClass, declaringClass, null != method.getAnnotation(Encoded.class) || keepEncoded, method.getParameterTypes(), method.getGenericParameterTypes(), method.getParameterAnnotations(), parameterClass);
   }

   private static List createList(Class concreteClass, Class declaringClass, boolean keepEncoded, Class[] parameterTypes, Type[] genericParameterTypes, Annotation[][] parameterAnnotations, Class parameterClass) {
      List<PARAMETER> parameters = new ArrayList(parameterTypes.length);

      for(int i = 0; i < parameterTypes.length; ++i) {
         PARAMETER parameter = (PARAMETER)create(concreteClass, declaringClass, keepEncoded, parameterTypes[i], genericParameterTypes[i], parameterAnnotations[i], parameterClass);
         if (null == parameter) {
            return Collections.emptyList();
         }

         parameters.add(parameter);
      }

      return parameters;
   }

   protected static List createList(Class concreteClass, Class declaringClass, Constructor ctor, boolean keepEncoded, Class parameterClass) {
      Class[] parameterTypes = ctor.getParameterTypes();
      Type[] genericParameterTypes = ctor.getGenericParameterTypes();
      if (parameterTypes.length != genericParameterTypes.length) {
         Type[] _genericParameterTypes = new Type[parameterTypes.length];
         _genericParameterTypes[0] = parameterTypes[0];
         System.arraycopy(genericParameterTypes, 0, _genericParameterTypes, 1, genericParameterTypes.length);
         genericParameterTypes = _genericParameterTypes;
      }

      return createList(concreteClass, declaringClass, null != ctor.getAnnotation(Encoded.class) || keepEncoded, parameterTypes, genericParameterTypes, ctor.getParameterAnnotations(), parameterClass);
   }

   private static String getValue(Annotation a) {
      try {
         Method m = a.annotationType().getMethod("value");
         return m.getReturnType() != String.class ? null : (String)m.invoke(a);
      } catch (Exception ex) {
         if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.log(Level.FINER, String.format("Unable to get the %s annotation value property", a.getClass().getName()), ex);
         }

         return null;
      }
   }

   public Annotation getSourceAnnotation() {
      return this.sourceAnnotation;
   }

   public Source getSource() {
      return this.source;
   }

   public String getSourceName() {
      return this.sourceName;
   }

   public boolean isEncoded() {
      return this.encoded;
   }

   public boolean hasDefaultValue() {
      return this.defaultValue != null;
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public Class getRawType() {
      return this.rawType;
   }

   public Type getType() {
      return this.type;
   }

   public boolean isQualified() {
      return false;
   }

   public boolean isAnnotationPresent(Class annotationClass) {
      return this.getAnnotation(annotationClass) != null;
   }

   public Annotation getAnnotation(Class annotationClass) {
      if (annotationClass == null) {
         return null;
      } else {
         for(Annotation a : this.annotations) {
            if (a.annotationType() == annotationClass) {
               return (Annotation)annotationClass.cast(a);
            }
         }

         return null;
      }
   }

   public Annotation[] getAnnotations() {
      return (Annotation[])this.annotations.clone();
   }

   public Annotation[] getDeclaredAnnotations() {
      return (Annotation[])this.annotations.clone();
   }

   public String toString() {
      return String.format("Parameter [type=%s, source=%s, defaultValue=%s]", this.getRawType(), this.getSourceName(), this.getDefaultValue());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Parameter parameter = (Parameter)o;
         if (this.encoded != parameter.encoded) {
            return false;
         } else if (!Arrays.equals(this.annotations, parameter.annotations)) {
            return false;
         } else {
            if (this.defaultValue != null) {
               if (!this.defaultValue.equals(parameter.defaultValue)) {
                  return false;
               }
            } else if (parameter.defaultValue != null) {
               return false;
            }

            if (this.rawType != null) {
               if (!this.rawType.equals(parameter.rawType)) {
                  return false;
               }
            } else if (parameter.rawType != null) {
               return false;
            }

            if (this.source != parameter.source) {
               return false;
            } else {
               if (this.sourceAnnotation != null) {
                  if (!this.sourceAnnotation.equals(parameter.sourceAnnotation)) {
                     return false;
                  }
               } else if (parameter.sourceAnnotation != null) {
                  return false;
               }

               if (this.sourceName != null) {
                  if (!this.sourceName.equals(parameter.sourceName)) {
                     return false;
                  }
               } else if (parameter.sourceName != null) {
                  return false;
               }

               if (this.type != null) {
                  if (!this.type.equals(parameter.type)) {
                     return false;
                  }
               } else if (parameter.type != null) {
                  return false;
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.annotations != null ? Arrays.hashCode(this.annotations) : 0;
      result = 31 * result + (this.sourceAnnotation != null ? this.sourceAnnotation.hashCode() : 0);
      result = 31 * result + (this.source != null ? this.source.hashCode() : 0);
      result = 31 * result + (this.sourceName != null ? this.sourceName.hashCode() : 0);
      result = 31 * result + (this.encoded ? 1 : 0);
      result = 31 * result + (this.defaultValue != null ? this.defaultValue.hashCode() : 0);
      result = 31 * result + (this.rawType != null ? this.rawType.hashCode() : 0);
      result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
      return result;
   }

   static {
      List<ParameterServiceProvider> PARAMETER_SERVICE_PROVIDERS = Lists.newArrayList((Iterable)ServiceFinder.find(ParameterServiceProvider.class));
      PARAMETER_SERVICE_PROVIDERS.add(new ParameterService());
      PARAM_CREATION_FACTORIES = Collections.unmodifiableList((List)PARAMETER_SERVICE_PROVIDERS.stream().map((a) -> a.getParameterCreationFactory()).collect(Collectors.toList()));
      ANNOTATION_HELPER_MAP = Collections.unmodifiableMap((Map)PARAMETER_SERVICE_PROVIDERS.stream().map((a) -> a.getParameterAnnotationHelperMap()).collect(WeakHashMap::new, Map::putAll, Map::putAll));
   }

   public static enum Source {
      CONTEXT,
      COOKIE,
      ENTITY,
      FORM,
      HEADER,
      URI,
      MATRIX,
      PATH,
      QUERY,
      SUSPENDED,
      BEAN_PARAM,
      UNKNOWN;
   }

   public static class ParameterService implements ParameterServiceProvider {
      public Map getParameterAnnotationHelperMap() {
         Map<Class, ParamAnnotationHelper> m = new WeakHashMap();
         m.put(Context.class, new ParamAnnotationHelper() {
            public String getValueOf(Context a) {
               return null;
            }

            public Source getSource() {
               return Parameter.Source.CONTEXT;
            }
         });
         m.put(CookieParam.class, new ParamAnnotationHelper() {
            public String getValueOf(CookieParam a) {
               return a.value();
            }

            public Source getSource() {
               return Parameter.Source.COOKIE;
            }
         });
         m.put(FormParam.class, new ParamAnnotationHelper() {
            public String getValueOf(FormParam a) {
               return a.value();
            }

            public Source getSource() {
               return Parameter.Source.FORM;
            }
         });
         m.put(HeaderParam.class, new ParamAnnotationHelper() {
            public String getValueOf(HeaderParam a) {
               return a.value();
            }

            public Source getSource() {
               return Parameter.Source.HEADER;
            }
         });
         m.put(MatrixParam.class, new ParamAnnotationHelper() {
            public String getValueOf(MatrixParam a) {
               return a.value();
            }

            public Source getSource() {
               return Parameter.Source.MATRIX;
            }
         });
         m.put(PathParam.class, new ParamAnnotationHelper() {
            public String getValueOf(PathParam a) {
               return a.value();
            }

            public Source getSource() {
               return Parameter.Source.PATH;
            }
         });
         m.put(QueryParam.class, new ParamAnnotationHelper() {
            public String getValueOf(QueryParam a) {
               return a.value();
            }

            public Source getSource() {
               return Parameter.Source.QUERY;
            }
         });
         m.put(Suspended.class, new ParamAnnotationHelper() {
            public String getValueOf(Suspended a) {
               return Suspended.class.getName();
            }

            public Source getSource() {
               return Parameter.Source.SUSPENDED;
            }
         });
         m.put(BeanParam.class, new ParamAnnotationHelper() {
            public String getValueOf(BeanParam a) {
               return null;
            }

            public Source getSource() {
               return Parameter.Source.BEAN_PARAM;
            }
         });
         return m;
      }

      public ParamCreationFactory getParameterCreationFactory() {
         return new ParamCreationFactory() {
            public boolean isFor(Class clazz) {
               return clazz == Parameter.class;
            }

            public Parameter createParameter(Annotation[] markers, Annotation marker, Source source, String sourceName, Class rawType, Type type, boolean encoded, String defaultValue) {
               return new Parameter(markers, marker, source, sourceName, rawType, type, encoded, defaultValue);
            }

            public Parameter createBeanParameter(Annotation[] markers, Annotation marker, Source source, String sourceName, Class rawType, Type type, boolean encoded, String defaultValue) {
               return this.createParameter(markers, marker, source, sourceName, rawType, type, encoded, defaultValue);
            }
         };
      }
   }

   public interface ParamAnnotationHelper {
      String getValueOf(Annotation var1);

      Source getSource();
   }

   public interface ParamCreationFactory {
      boolean isFor(Class var1);

      Parameter createParameter(Annotation[] var1, Annotation var2, Source var3, String var4, Class var5, Type var6, boolean var7, String var8);

      Parameter createBeanParameter(Annotation[] var1, Annotation var2, Source var3, String var4, Class var5, Type var6, boolean var7, String var8);
   }
}
