package org.apache.hadoop.hive.serde2.objectinspector;

public interface UnionObject {
   byte getTag();

   Object getObject();
}
