package org.apache.parquet.column.impl;

interface StatusManager {
   static StatusManager create() {
      return new StatusManager() {
         private boolean aborted;

         public void abort() {
            this.aborted = true;
         }

         public boolean isAborted() {
            return this.aborted;
         }
      };
   }

   void abort();

   boolean isAborted();
}
