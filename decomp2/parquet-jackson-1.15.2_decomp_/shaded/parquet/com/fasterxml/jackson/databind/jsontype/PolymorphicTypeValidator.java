package shaded.parquet.com.fasterxml.jackson.databind.jsontype;

import java.io.Serializable;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MapperConfig;

public abstract class PolymorphicTypeValidator implements Serializable {
   private static final long serialVersionUID = 1L;

   public abstract Validity validateBaseType(MapperConfig var1, JavaType var2);

   public abstract Validity validateSubClassName(MapperConfig var1, JavaType var2, String var3) throws JsonMappingException;

   public abstract Validity validateSubType(MapperConfig var1, JavaType var2, JavaType var3) throws JsonMappingException;

   public static enum Validity {
      ALLOWED,
      DENIED,
      INDETERMINATE;
   }

   public abstract static class Base extends PolymorphicTypeValidator implements Serializable {
      private static final long serialVersionUID = 1L;

      public Validity validateBaseType(MapperConfig config, JavaType baseType) {
         return PolymorphicTypeValidator.Validity.INDETERMINATE;
      }

      public Validity validateSubClassName(MapperConfig config, JavaType baseType, String subClassName) throws JsonMappingException {
         return PolymorphicTypeValidator.Validity.INDETERMINATE;
      }

      public Validity validateSubType(MapperConfig config, JavaType baseType, JavaType subType) throws JsonMappingException {
         return PolymorphicTypeValidator.Validity.INDETERMINATE;
      }
   }
}
