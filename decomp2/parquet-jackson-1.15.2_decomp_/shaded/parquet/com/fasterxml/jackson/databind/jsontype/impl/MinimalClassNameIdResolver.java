package shaded.parquet.com.fasterxml.jackson.databind.jsontype.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonTypeInfo;
import shaded.parquet.com.fasterxml.jackson.databind.DatabindContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MapperConfig;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;

public class MinimalClassNameIdResolver extends ClassNameIdResolver {
   private static final long serialVersionUID = 1L;
   protected final String _basePackageName;
   protected final String _basePackagePrefix;

   protected MinimalClassNameIdResolver(JavaType baseType, TypeFactory typeFactory, PolymorphicTypeValidator ptv) {
      super(baseType, typeFactory, ptv);
      String base = baseType.getRawClass().getName();
      int ix = base.lastIndexOf(46);
      if (ix < 0) {
         this._basePackageName = "";
         this._basePackagePrefix = ".";
      } else {
         this._basePackagePrefix = base.substring(0, ix + 1);
         this._basePackageName = base.substring(0, ix);
      }

   }

   public static MinimalClassNameIdResolver construct(JavaType baseType, MapperConfig config, PolymorphicTypeValidator ptv) {
      return new MinimalClassNameIdResolver(baseType, config.getTypeFactory(), ptv);
   }

   public JsonTypeInfo.Id getMechanism() {
      return JsonTypeInfo.Id.MINIMAL_CLASS;
   }

   public String idFromValue(Object value) {
      String n = value.getClass().getName();
      return n.startsWith(this._basePackagePrefix) ? n.substring(this._basePackagePrefix.length() - 1) : n;
   }

   protected JavaType _typeFromId(String id, DatabindContext ctxt) throws IOException {
      if (id.startsWith(".")) {
         StringBuilder sb = new StringBuilder(id.length() + this._basePackageName.length());
         if (this._basePackageName.isEmpty()) {
            sb.append(id.substring(1));
         } else {
            sb.append(this._basePackageName).append(id);
         }

         id = sb.toString();
      }

      return super._typeFromId(id, ctxt);
   }
}
