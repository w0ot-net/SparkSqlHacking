package org.sparkproject.spark_core.protobuf;

@CheckReturnValue
final class NewInstanceSchemaLite implements NewInstanceSchema {
   public Object newInstance(Object defaultInstance) {
      return ((GeneratedMessageLite)defaultInstance).newMutableInstance();
   }
}
