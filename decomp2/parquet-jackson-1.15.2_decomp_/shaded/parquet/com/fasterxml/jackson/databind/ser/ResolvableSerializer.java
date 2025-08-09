package shaded.parquet.com.fasterxml.jackson.databind.ser;

import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;

public interface ResolvableSerializer {
   void resolve(SerializerProvider var1) throws JsonMappingException;
}
