package org.sparkproject.spark_core.protobuf;

final class NewInstanceSchemaFull implements NewInstanceSchema {
   public Object newInstance(Object defaultInstance) {
      return ((Message)defaultInstance).toBuilder().buildPartial();
   }
}
