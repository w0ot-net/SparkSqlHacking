package org.rocksdb;

public abstract class AbstractTableFilter extends RocksCallbackObject implements TableFilter {
   protected AbstractTableFilter() {
      super();
   }

   protected long initializeNative(long... var1) {
      return this.createNewTableFilter();
   }

   private native long createNewTableFilter();
}
