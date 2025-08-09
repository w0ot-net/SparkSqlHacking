package org.sparkproject.spark_core.protobuf;

@CheckReturnValue
interface MessageInfo {
   ProtoSyntax getSyntax();

   boolean isMessageSetWireFormat();

   MessageLite getDefaultInstance();
}
