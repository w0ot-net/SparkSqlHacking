package shaded.parquet.com.fasterxml.jackson.databind.util;

import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;

public interface Converter {
   Object convert(Object var1);

   JavaType getInputType(TypeFactory var1);

   JavaType getOutputType(TypeFactory var1);

   public abstract static class None implements Converter {
   }
}
