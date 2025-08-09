package org.apache.hadoop.hive.serde2.objectinspector;

class ThriftStructObjectInspector extends ReflectionStructObjectInspector {
   public boolean shouldIgnoreField(String name) {
      return name.startsWith("__isset");
   }
}
