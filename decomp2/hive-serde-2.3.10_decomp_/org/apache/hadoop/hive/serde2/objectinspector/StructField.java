package org.apache.hadoop.hive.serde2.objectinspector;

public interface StructField {
   String getFieldName();

   ObjectInspector getFieldObjectInspector();

   int getFieldID();

   String getFieldComment();
}
