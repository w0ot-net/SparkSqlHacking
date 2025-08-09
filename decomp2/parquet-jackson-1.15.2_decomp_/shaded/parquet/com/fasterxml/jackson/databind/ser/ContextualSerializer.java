package shaded.parquet.com.fasterxml.jackson.databind.ser;

import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;

public interface ContextualSerializer {
   JsonSerializer createContextual(SerializerProvider var1, BeanProperty var2) throws JsonMappingException;
}
