package org.apache.orc.protobuf;

@CheckReturnValue
interface SchemaFactory {
   Schema createSchema(Class messageType);
}
