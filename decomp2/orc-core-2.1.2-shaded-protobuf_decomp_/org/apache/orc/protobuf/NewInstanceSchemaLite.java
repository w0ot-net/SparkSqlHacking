package org.apache.orc.protobuf;

@CheckReturnValue
final class NewInstanceSchemaLite implements NewInstanceSchema {
   public Object newInstance(Object defaultInstance) {
      return ((GeneratedMessageLite)defaultInstance).newMutableInstance();
   }
}
