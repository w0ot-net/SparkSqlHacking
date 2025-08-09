package org.apache.hadoop.hive.serde2.objectinspector;

import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;

public interface PrimitiveObjectInspector extends ObjectInspector {
   PrimitiveTypeInfo getTypeInfo();

   PrimitiveCategory getPrimitiveCategory();

   Class getPrimitiveWritableClass();

   Object getPrimitiveWritableObject(Object var1);

   Class getJavaPrimitiveClass();

   Object getPrimitiveJavaObject(Object var1);

   Object copyObject(Object var1);

   boolean preferWritable();

   int precision();

   int scale();

   public static enum PrimitiveCategory {
      VOID,
      BOOLEAN,
      BYTE,
      SHORT,
      INT,
      LONG,
      FLOAT,
      DOUBLE,
      STRING,
      DATE,
      TIMESTAMP,
      BINARY,
      DECIMAL,
      VARCHAR,
      CHAR,
      INTERVAL_YEAR_MONTH,
      INTERVAL_DAY_TIME,
      UNKNOWN;
   }
}
