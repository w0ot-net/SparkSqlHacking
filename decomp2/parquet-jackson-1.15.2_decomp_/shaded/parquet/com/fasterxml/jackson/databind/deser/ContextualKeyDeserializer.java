package shaded.parquet.com.fasterxml.jackson.databind.deser;

import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;

public interface ContextualKeyDeserializer {
   KeyDeserializer createContextual(DeserializationContext var1, BeanProperty var2) throws JsonMappingException;
}
