package org.apache.commons.lang3;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.exception.UncheckedException;

public class AnnotationUtils {
   private static final ToStringStyle TO_STRING_STYLE = new ToStringStyle() {
      private static final long serialVersionUID = 1L;

      {
         this.setDefaultFullDetail(true);
         this.setArrayContentDetail(true);
         this.setUseClassName(true);
         this.setUseShortClassName(true);
         this.setUseIdentityHashCode(false);
         this.setContentStart("(");
         this.setContentEnd(")");
         this.setFieldSeparator(", ");
         this.setArrayStart("[");
         this.setArrayEnd("]");
      }

      protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
         if (value instanceof Annotation) {
            value = AnnotationUtils.toString((Annotation)value);
         }

         super.appendDetail(buffer, fieldName, value);
      }

      protected String getShortClassName(Class cls) {
         Stream var10000 = ClassUtils.getAllInterfaces(cls).stream();
         Objects.requireNonNull(Annotation.class);
         return (String)var10000.filter(Annotation.class::isAssignableFrom).findFirst().map((iface) -> "@" + iface.getName()).orElse("");
      }
   };

   private static boolean annotationArrayMemberEquals(Annotation[] a1, Annotation[] a2) {
      if (a1.length != a2.length) {
         return false;
      } else {
         for(int i = 0; i < a1.length; ++i) {
            if (!equals(a1[i], a2[i])) {
               return false;
            }
         }

         return true;
      }
   }

   private static boolean arrayMemberEquals(Class componentType, Object o1, Object o2) {
      if (componentType.isAnnotation()) {
         return annotationArrayMemberEquals((Annotation[])o1, (Annotation[])o2);
      } else if (componentType.equals(Byte.TYPE)) {
         return Arrays.equals((byte[])o1, (byte[])o2);
      } else if (componentType.equals(Short.TYPE)) {
         return Arrays.equals((short[])o1, (short[])o2);
      } else if (componentType.equals(Integer.TYPE)) {
         return Arrays.equals((int[])o1, (int[])o2);
      } else if (componentType.equals(Character.TYPE)) {
         return Arrays.equals((char[])o1, (char[])o2);
      } else if (componentType.equals(Long.TYPE)) {
         return Arrays.equals((long[])o1, (long[])o2);
      } else if (componentType.equals(Float.TYPE)) {
         return Arrays.equals((float[])o1, (float[])o2);
      } else if (componentType.equals(Double.TYPE)) {
         return Arrays.equals((double[])o1, (double[])o2);
      } else {
         return componentType.equals(Boolean.TYPE) ? Arrays.equals((boolean[])o1, (boolean[])o2) : Arrays.equals(o1, o2);
      }
   }

   private static int arrayMemberHash(Class componentType, Object o) {
      if (componentType.equals(Byte.TYPE)) {
         return Arrays.hashCode((byte[])o);
      } else if (componentType.equals(Short.TYPE)) {
         return Arrays.hashCode((short[])o);
      } else if (componentType.equals(Integer.TYPE)) {
         return Arrays.hashCode((int[])o);
      } else if (componentType.equals(Character.TYPE)) {
         return Arrays.hashCode((char[])o);
      } else if (componentType.equals(Long.TYPE)) {
         return Arrays.hashCode((long[])o);
      } else if (componentType.equals(Float.TYPE)) {
         return Arrays.hashCode((float[])o);
      } else if (componentType.equals(Double.TYPE)) {
         return Arrays.hashCode((double[])o);
      } else {
         return componentType.equals(Boolean.TYPE) ? Arrays.hashCode((boolean[])o) : Arrays.hashCode(o);
      }
   }

   public static boolean equals(Annotation a1, Annotation a2) {
      if (a1 == a2) {
         return true;
      } else if (a1 != null && a2 != null) {
         Class<? extends Annotation> type1 = a1.annotationType();
         Class<? extends Annotation> type2 = a2.annotationType();
         Validate.notNull(type1, "Annotation %s with null annotationType()", a1);
         Validate.notNull(type2, "Annotation %s with null annotationType()", a2);
         if (!type1.equals(type2)) {
            return false;
         } else {
            try {
               for(Method m : type1.getDeclaredMethods()) {
                  if (m.getParameterTypes().length == 0 && isValidAnnotationMemberType(m.getReturnType())) {
                     Object v1 = m.invoke(a1);
                     Object v2 = m.invoke(a2);
                     if (!memberEquals(m.getReturnType(), v1, v2)) {
                        return false;
                     }
                  }
               }

               return true;
            } catch (ReflectiveOperationException var10) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static int hashCode(Annotation a) {
      int result = 0;
      Class<? extends Annotation> type = a.annotationType();

      for(Method m : type.getDeclaredMethods()) {
         try {
            Object value = m.invoke(a);
            if (value == null) {
               throw new IllegalStateException(String.format("Annotation method %s returned null", m));
            }

            result += hashMember(m.getName(), value);
         } catch (ReflectiveOperationException ex) {
            throw new UncheckedException(ex);
         }
      }

      return result;
   }

   private static int hashMember(String name, Object value) {
      int part1 = name.hashCode() * 127;
      if (ObjectUtils.isArray(value)) {
         return part1 ^ arrayMemberHash(value.getClass().getComponentType(), value);
      } else {
         return value instanceof Annotation ? part1 ^ hashCode((Annotation)value) : part1 ^ value.hashCode();
      }
   }

   public static boolean isValidAnnotationMemberType(Class type) {
      if (type == null) {
         return false;
      } else {
         if (type.isArray()) {
            type = type.getComponentType();
         }

         return type.isPrimitive() || type.isEnum() || type.isAnnotation() || String.class.equals(type) || Class.class.equals(type);
      }
   }

   private static boolean memberEquals(Class type, Object o1, Object o2) {
      if (o1 == o2) {
         return true;
      } else if (o1 != null && o2 != null) {
         if (type.isArray()) {
            return arrayMemberEquals(type.getComponentType(), o1, o2);
         } else {
            return type.isAnnotation() ? equals((Annotation)o1, (Annotation)o2) : o1.equals(o2);
         }
      } else {
         return false;
      }
   }

   public static String toString(Annotation a) {
      ToStringBuilder builder = new ToStringBuilder(a, TO_STRING_STYLE);

      for(Method m : a.annotationType().getDeclaredMethods()) {
         if (m.getParameterTypes().length <= 0) {
            try {
               builder.append(m.getName(), m.invoke(a));
            } catch (ReflectiveOperationException ex) {
               throw new UncheckedException(ex);
            }
         }
      }

      return builder.build();
   }
}
