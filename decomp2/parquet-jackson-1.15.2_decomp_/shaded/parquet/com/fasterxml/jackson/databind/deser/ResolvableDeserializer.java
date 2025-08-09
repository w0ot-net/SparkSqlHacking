package shaded.parquet.com.fasterxml.jackson.databind.deser;

import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;

public interface ResolvableDeserializer {
   void resolve(DeserializationContext var1) throws JsonMappingException;
}
