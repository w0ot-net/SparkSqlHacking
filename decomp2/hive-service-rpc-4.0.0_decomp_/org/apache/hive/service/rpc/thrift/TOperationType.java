package org.apache.hive.service.rpc.thrift;

import org.apache.thrift.TEnum;
import org.apache.thrift.annotation.Nullable;

public enum TOperationType implements TEnum {
   EXECUTE_STATEMENT(0),
   GET_TYPE_INFO(1),
   GET_CATALOGS(2),
   GET_SCHEMAS(3),
   GET_TABLES(4),
   GET_TABLE_TYPES(5),
   GET_COLUMNS(6),
   GET_FUNCTIONS(7),
   UNKNOWN(8),
   PROCEDURAL_SQL(9);

   private final int value;

   private TOperationType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static TOperationType findByValue(int value) {
      switch (value) {
         case 0:
            return EXECUTE_STATEMENT;
         case 1:
            return GET_TYPE_INFO;
         case 2:
            return GET_CATALOGS;
         case 3:
            return GET_SCHEMAS;
         case 4:
            return GET_TABLES;
         case 5:
            return GET_TABLE_TYPES;
         case 6:
            return GET_COLUMNS;
         case 7:
            return GET_FUNCTIONS;
         case 8:
            return UNKNOWN;
         case 9:
            return PROCEDURAL_SQL;
         default:
            return null;
      }
   }
}
