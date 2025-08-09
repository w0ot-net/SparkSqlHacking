package shaded.parquet.com.fasterxml.jackson.databind.introspect;

import shaded.parquet.com.fasterxml.jackson.databind.EnumNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public class EnumNamingStrategyFactory {
   private EnumNamingStrategyFactory() {
   }

   public static EnumNamingStrategy createEnumNamingStrategyInstance(Object namingDef, boolean canOverrideAccessModifiers) {
      if (namingDef == null) {
         return null;
      } else if (namingDef instanceof EnumNamingStrategy) {
         return (EnumNamingStrategy)namingDef;
      } else if (!(namingDef instanceof Class)) {
         throw new IllegalArgumentException(String.format("AnnotationIntrospector returned EnumNamingStrategy definition of type %s; expected type `Class<EnumNamingStrategy>` instead", ClassUtil.classNameOf(namingDef)));
      } else {
         Class<?> namingClass = (Class)namingDef;
         if (namingClass == EnumNamingStrategy.class) {
            return null;
         } else if (!EnumNamingStrategy.class.isAssignableFrom(namingClass)) {
            throw new IllegalArgumentException(String.format("Problem with AnnotationIntrospector returned Class %s; expected `Class<EnumNamingStrategy>`", ClassUtil.classNameOf(namingClass)));
         } else {
            return (EnumNamingStrategy)ClassUtil.createInstance(namingClass, canOverrideAccessModifiers);
         }
      }
   }
}
