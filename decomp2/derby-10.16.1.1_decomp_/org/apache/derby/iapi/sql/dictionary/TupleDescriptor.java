package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;

public class TupleDescriptor {
   private DataDictionary dataDictionary;

   public TupleDescriptor() {
   }

   public TupleDescriptor(DataDictionary var1) {
      this.dataDictionary = var1;
   }

   protected DataDictionary getDataDictionary() {
      return this.dataDictionary;
   }

   protected void setDataDictionary(DataDictionary var1) {
      this.dataDictionary = var1;
   }

   public boolean isPersistent() {
      return true;
   }

   DependableFinder getDependableFinder(int var1) {
      return this.dataDictionary.getDependableFinder(var1);
   }

   DependableFinder getColumnDependableFinder(int var1, byte[] var2) {
      return this.dataDictionary.getColumnDependableFinder(var1, var2);
   }

   public String getDescriptorType() {
      return null;
   }

   public String getDescriptorName() {
      return null;
   }
}
