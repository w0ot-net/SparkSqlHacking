package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.TOperationType;

public enum OperationType {
   UNKNOWN_OPERATION(TOperationType.UNKNOWN),
   EXECUTE_STATEMENT(TOperationType.EXECUTE_STATEMENT),
   PROCEDURAL_SQL(TOperationType.PROCEDURAL_SQL),
   GET_TYPE_INFO(TOperationType.GET_TYPE_INFO),
   GET_CATALOGS(TOperationType.GET_CATALOGS),
   GET_SCHEMAS(TOperationType.GET_SCHEMAS),
   GET_TABLES(TOperationType.GET_TABLES),
   GET_TABLE_TYPES(TOperationType.GET_TABLE_TYPES),
   GET_COLUMNS(TOperationType.GET_COLUMNS),
   GET_FUNCTIONS(TOperationType.GET_FUNCTIONS);

   private TOperationType tOperationType;

   private OperationType(TOperationType tOpType) {
      this.tOperationType = tOpType;
   }

   public static OperationType getOperationType(TOperationType tOperationType) {
      for(OperationType opType : values()) {
         if (tOperationType.equals(opType.tOperationType)) {
            return opType;
         }
      }

      return UNKNOWN_OPERATION;
   }

   public TOperationType toTOperationType() {
      return this.tOperationType;
   }

   // $FF: synthetic method
   private static OperationType[] $values() {
      return new OperationType[]{UNKNOWN_OPERATION, EXECUTE_STATEMENT, PROCEDURAL_SQL, GET_TYPE_INFO, GET_CATALOGS, GET_SCHEMAS, GET_TABLES, GET_TABLE_TYPES, GET_COLUMNS, GET_FUNCTIONS};
   }
}
