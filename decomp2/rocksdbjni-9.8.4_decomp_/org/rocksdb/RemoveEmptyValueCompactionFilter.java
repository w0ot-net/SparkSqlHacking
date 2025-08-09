package org.rocksdb;

public class RemoveEmptyValueCompactionFilter extends AbstractCompactionFilter {
   public RemoveEmptyValueCompactionFilter() {
      super(createNewRemoveEmptyValueCompactionFilter0());
   }

   private static native long createNewRemoveEmptyValueCompactionFilter0();
}
