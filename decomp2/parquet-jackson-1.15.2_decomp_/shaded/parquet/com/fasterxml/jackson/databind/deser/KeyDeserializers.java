package shaded.parquet.com.fasterxml.jackson.databind.deser;

import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;

public interface KeyDeserializers {
   KeyDeserializer findKeyDeserializer(JavaType var1, DeserializationConfig var2, BeanDescription var3) throws JsonMappingException;
}
