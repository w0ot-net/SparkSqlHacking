package org.datanucleus.store.rdbms.key;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;

public class Index extends Key {
   private final boolean isUnique;
   private final String extendedIndexSettings;

   public Index(Table table, boolean isUnique, String extendedIndexSettings) {
      super(table);
      this.isUnique = isUnique;
      this.extendedIndexSettings = extendedIndexSettings;
   }

   public Index(CandidateKey ck) {
      super(ck.getTable());
      this.isUnique = true;
      this.extendedIndexSettings = null;
      this.columns.addAll(ck.getColumns());
   }

   public Index(ForeignKey fk) {
      super(fk.getTable());
      this.isUnique = false;
      this.extendedIndexSettings = null;
      this.columns.addAll(fk.getColumns());
   }

   public boolean getUnique() {
      return this.isUnique;
   }

   public void setColumn(int seq, Column col) {
      this.assertSameDatastoreObject(col);
      setMinSize(this.columns, seq + 1);
      if (this.columns.get(seq) != null) {
         throw (new NucleusException("Index part #" + seq + " for " + this.table + " already set")).setFatal();
      } else {
         this.columns.set(seq, col);
      }
   }

   public int size() {
      return this.columns.size();
   }

   public int hashCode() {
      return (this.isUnique ? 0 : 1) ^ super.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Index)) {
         return false;
      } else {
         Index idx = (Index)obj;
         return idx.isUnique != this.isUnique ? false : super.equals(obj);
      }
   }

   public String getExtendedIndexSettings() {
      return this.extendedIndexSettings;
   }

   public String toString() {
      return this.getColumnList();
   }
}
