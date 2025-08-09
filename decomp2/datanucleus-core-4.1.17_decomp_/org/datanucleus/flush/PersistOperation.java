package org.datanucleus.flush;

import org.datanucleus.state.ObjectProvider;

public class PersistOperation implements Operation {
   ObjectProvider op;

   public PersistOperation(ObjectProvider op) {
      this.op = op;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public void perform() {
   }

   public String toString() {
      return "PERSIST : " + this.op;
   }
}
