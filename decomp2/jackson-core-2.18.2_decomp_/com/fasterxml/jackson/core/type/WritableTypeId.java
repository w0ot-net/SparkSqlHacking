package com.fasterxml.jackson.core.type;

import com.fasterxml.jackson.core.JsonToken;

public class WritableTypeId {
   public Object forValue;
   public Class forValueType;
   public Object id;
   public String asProperty;
   public Inclusion include;
   public JsonToken valueShape;
   public boolean wrapperWritten;
   public Object extra;

   public WritableTypeId() {
   }

   public WritableTypeId(Object value, JsonToken valueShape) {
      this(value, (JsonToken)valueShape, (Object)null);
   }

   public WritableTypeId(Object value, Class valueType, JsonToken valueShape) {
      this(value, (JsonToken)valueShape, (Object)null);
      this.forValueType = valueType;
   }

   public WritableTypeId(Object value, JsonToken valueShape, Object id) {
      this.forValue = value;
      this.id = id;
      this.valueShape = valueShape;
   }

   public static enum Inclusion {
      WRAPPER_ARRAY,
      WRAPPER_OBJECT,
      METADATA_PROPERTY,
      PAYLOAD_PROPERTY,
      PARENT_PROPERTY;

      public boolean requiresObjectContext() {
         return this == METADATA_PROPERTY || this == PAYLOAD_PROPERTY;
      }
   }
}
