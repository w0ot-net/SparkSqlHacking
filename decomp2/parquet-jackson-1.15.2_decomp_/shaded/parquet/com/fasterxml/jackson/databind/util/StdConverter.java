package shaded.parquet.com.fasterxml.jackson.databind.util;

import java.lang.reflect.Type;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class StdConverter implements Converter {
   public abstract Object convert(Object var1);

   public JavaType getInputType(TypeFactory typeFactory) {
      return this._findConverterType(typeFactory).containedType(0);
   }

   public JavaType getOutputType(TypeFactory typeFactory) {
      return this._findConverterType(typeFactory).containedType(1);
   }

   protected JavaType _findConverterType(TypeFactory tf) {
      JavaType thisType = tf.constructType((Type)this.getClass());
      JavaType convType = thisType.findSuperType(Converter.class);
      if (convType != null && convType.containedTypeCount() >= 2) {
         return convType;
      } else {
         throw new IllegalStateException("Cannot find OUT type parameter for Converter of type " + this.getClass().getName());
      }
   }
}
