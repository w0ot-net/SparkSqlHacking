package org.sparkproject.spark_core.protobuf;

@CheckReturnValue
interface SchemaFactory {
   Schema createSchema(Class messageType);
}
