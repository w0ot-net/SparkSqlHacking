package org.apache.hadoop.hive.serde2.lazy;

public interface LazyObjectBase {
   void init(ByteArrayRef var1, int var2, int var3);

   void setNull();

   Object getObject();
}
