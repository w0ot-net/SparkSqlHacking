package org.apache.orc.protobuf;

@CheckReturnValue
interface MessageInfo {
   ProtoSyntax getSyntax();

   boolean isMessageSetWireFormat();

   MessageLite getDefaultInstance();
}
