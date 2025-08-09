package org.sparkproject.spark_core.protobuf;

public interface ValueOrBuilder extends MessageOrBuilder {
   boolean hasNullValue();

   int getNullValueValue();

   NullValue getNullValue();

   boolean hasNumberValue();

   double getNumberValue();

   boolean hasStringValue();

   String getStringValue();

   ByteString getStringValueBytes();

   boolean hasBoolValue();

   boolean getBoolValue();

   boolean hasStructValue();

   Struct getStructValue();

   StructOrBuilder getStructValueOrBuilder();

   boolean hasListValue();

   ListValue getListValue();

   ListValueOrBuilder getListValueOrBuilder();

   Value.KindCase getKindCase();
}
