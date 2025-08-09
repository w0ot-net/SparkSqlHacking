package org.apache.hadoop.hive.serde2.objectinspector;

class ProtocolBuffersStructObjectInspector extends ReflectionStructObjectInspector {
   public boolean shouldIgnoreField(String name) {
      return name.startsWith("has");
   }
}
