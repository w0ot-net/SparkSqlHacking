package org.datanucleus.flush;

import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.ListStore;

public class ListAddAtOperation extends CollectionAddOperation {
   final int index;

   public ListAddAtOperation(ObjectProvider op, ListStore store, int index, Object value) {
      super(op, store, value);
      this.index = index;
   }

   public ListAddAtOperation(ObjectProvider op, int fieldNum, int index, Object value) {
      super(op, fieldNum, value);
      this.index = index;
   }

   public void perform() {
      if (this.store != null) {
         ((ListStore)this.store).add(this.op, this.value, this.index, -1);
      }

   }

   public String toString() {
      return "LIST ADD-AT : " + this.op + " field=" + this.getMemberMetaData().getName() + " index=" + this.index;
   }
}
