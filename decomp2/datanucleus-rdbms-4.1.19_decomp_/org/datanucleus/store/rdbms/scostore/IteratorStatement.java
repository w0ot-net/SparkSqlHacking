package org.datanucleus.store.rdbms.scostore;

import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.scostore.Store;

public class IteratorStatement {
   Store backingStore;
   SQLStatement sqlStmt = null;
   StatementClassMapping stmtClassMapping = null;
   StatementMappingIndex ownerMapIndex = null;

   public IteratorStatement(Store store, SQLStatement stmt, StatementClassMapping stmtClassMapping) {
      this.backingStore = store;
      this.sqlStmt = stmt;
      this.stmtClassMapping = stmtClassMapping;
   }

   public Store getBackingStore() {
      return this.backingStore;
   }

   public SQLStatement getSQLStatement() {
      return this.sqlStmt;
   }

   public StatementClassMapping getStatementClassMapping() {
      return this.stmtClassMapping;
   }

   public StatementMappingIndex getOwnerMapIndex() {
      return this.ownerMapIndex;
   }

   public void setOwnerMapIndex(StatementMappingIndex idx) {
      this.ownerMapIndex = idx;
   }
}
