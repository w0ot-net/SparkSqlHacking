package org.apache.hadoop.hive.serde2.objectinspector;

public interface ObjectInspector extends Cloneable {
   String getTypeName();

   Category getCategory();

   public static enum Category {
      PRIMITIVE,
      LIST,
      MAP,
      STRUCT,
      UNION;
   }
}
