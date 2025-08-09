package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.AccessPattern;

public abstract class StdScalarDeserializer extends StdDeserializer {
   private static final long serialVersionUID = 1L;

   protected StdScalarDeserializer(Class vc) {
      super(vc);
   }

   protected StdScalarDeserializer(JavaType valueType) {
      super(valueType);
   }

   protected StdScalarDeserializer(StdScalarDeserializer src) {
      super((StdDeserializer)src);
   }

   public LogicalType logicalType() {
      return LogicalType.OtherScalar;
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return Boolean.FALSE;
   }

   public AccessPattern getNullAccessPattern() {
      return AccessPattern.ALWAYS_NULL;
   }

   public AccessPattern getEmptyAccessPattern() {
      return AccessPattern.CONSTANT;
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      return typeDeserializer.deserializeTypedFromScalar(p, ctxt);
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt, Object intoValue) throws IOException {
      ctxt.handleBadMerge(this);
      return this.deserialize(p, ctxt);
   }
}
