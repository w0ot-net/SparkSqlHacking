package org.datanucleus.store.rdbms.key;

import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;

public class CandidateKey extends Key {
   public CandidateKey(Table table) {
      super(table);
   }

   public void setColumn(int seq, Column col) {
      this.assertSameDatastoreObject(col);
      setMinSize(this.columns, seq + 1);
      if (this.columns.get(seq) != null) {
      }

      this.columns.set(seq, col);
   }

   public int size() {
      return this.columns.size();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof CandidateKey) ? false : super.equals(obj);
      }
   }

   public int hashCode() {
      return super.hashCode();
   }

   public String toString() {
      StringBuilder s = (new StringBuilder("UNIQUE ")).append(getColumnList(this.columns));
      return s.toString();
   }
}
