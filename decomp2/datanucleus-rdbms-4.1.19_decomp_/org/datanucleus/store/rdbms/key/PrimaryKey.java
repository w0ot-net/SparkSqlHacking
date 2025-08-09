package org.datanucleus.store.rdbms.key;

import org.datanucleus.store.rdbms.table.Table;

public class PrimaryKey extends CandidateKey {
   public PrimaryKey(Table table) {
      super(table);
      this.name = table.getStoreManager().getIdentifierFactory().newPrimaryKeyIdentifier(table).getName();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof PrimaryKey) ? false : super.equals(obj);
      }
   }

   public int hashCode() {
      return super.hashCode();
   }

   public String toString() {
      StringBuilder s = (new StringBuilder("PRIMARY KEY ")).append(getColumnList(this.columns));
      return s.toString();
   }
}
