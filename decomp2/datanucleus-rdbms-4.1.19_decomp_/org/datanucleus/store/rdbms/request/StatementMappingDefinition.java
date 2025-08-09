package org.datanucleus.store.rdbms.request;

import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;

class StatementMappingDefinition {
   private StatementMappingIndex[] updateFields;
   private StatementMappingIndex updateVersion;
   private StatementMappingIndex[] whereFields;
   private StatementMappingIndex whereDatastoreId;
   private StatementMappingIndex whereVersion;

   public StatementMappingIndex getWhereDatastoreId() {
      return this.whereDatastoreId;
   }

   public void setWhereDatastoreId(StatementMappingIndex datastoreId) {
      this.whereDatastoreId = datastoreId;
   }

   public StatementMappingIndex getUpdateVersion() {
      return this.updateVersion;
   }

   public void setUpdateVersion(StatementMappingIndex ver) {
      this.updateVersion = ver;
   }

   public StatementMappingIndex[] getUpdateFields() {
      return this.updateFields;
   }

   public void setUpdateFields(StatementMappingIndex[] fields) {
      this.updateFields = fields;
   }

   public StatementMappingIndex[] getWhereFields() {
      return this.whereFields;
   }

   public void setWhereFields(StatementMappingIndex[] fields) {
      this.whereFields = fields;
   }

   public StatementMappingIndex getWhereVersion() {
      return this.whereVersion;
   }

   public void setWhereVersion(StatementMappingIndex ver) {
      this.whereVersion = ver;
   }
}
