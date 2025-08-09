package org.datanucleus.api.jdo.state;

import org.datanucleus.Transaction;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;

class TransientClean extends LifeCycleState {
   TransientClean() {
      this.isPersistent = false;
      this.isTransactional = true;
      this.isDirty = false;
      this.isNew = false;
      this.isDeleted = false;
      this.stateType = 5;
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      return this;
   }

   public LifeCycleState transitionMakeNontransactional(ObjectProvider op) {
      LifeCycleState var2;
      try {
         var2 = this.changeTransientState(op, 0);
      } finally {
         op.disconnect();
      }

      return var2;
   }

   public LifeCycleState transitionMakePersistent(ObjectProvider op) {
      op.registerTransactional();
      return this.changeState(op, 1);
   }

   public LifeCycleState transitionReadField(ObjectProvider op, boolean isLoaded) {
      return this;
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (tx.isActive()) {
         op.saveFields();
         return this.changeTransientState(op, 6);
      } else {
         return this;
      }
   }

   public LifeCycleState transitionCommit(ObjectProvider op, Transaction tx) {
      return this;
   }

   public LifeCycleState transitionRollback(ObjectProvider op, Transaction tx) {
      return this;
   }

   public String toString() {
      return "T_CLEAN";
   }
}
