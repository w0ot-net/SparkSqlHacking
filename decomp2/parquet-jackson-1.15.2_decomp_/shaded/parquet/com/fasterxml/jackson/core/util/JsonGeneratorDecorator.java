package shaded.parquet.com.fasterxml.jackson.core.util;

import shaded.parquet.com.fasterxml.jackson.core.JsonFactory;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;

public interface JsonGeneratorDecorator {
   JsonGenerator decorate(JsonFactory var1, JsonGenerator var2);
}
