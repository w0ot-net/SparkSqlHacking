package shaded.parquet.com.fasterxml.jackson.databind.deser;

import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;

public interface ContextualDeserializer {
   JsonDeserializer createContextual(DeserializationContext var1, BeanProperty var2) throws JsonMappingException;
}
