package org.datanucleus.flush;

import org.datanucleus.state.ObjectProvider;

public class DeleteOperation implements Operation {
   ObjectProvider op;

   public DeleteOperation(ObjectProvider op) {
      this.op = op;
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public void perform() {
   }

   public String toString() {
      return "DELETE : " + this.op;
   }
}
