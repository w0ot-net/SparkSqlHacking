package org.rocksdb;

public interface MutableOptionKey {
   String name();

   ValueType getValueType();

   public static enum ValueType {
      DOUBLE,
      LONG,
      INT,
      BOOLEAN,
      INT_ARRAY,
      ENUM;
   }
}
