package shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors;

import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;

public interface JsonFormatVisitable {
   void acceptJsonFormatVisitor(JsonFormatVisitorWrapper var1, JavaType var2) throws JsonMappingException;
}
