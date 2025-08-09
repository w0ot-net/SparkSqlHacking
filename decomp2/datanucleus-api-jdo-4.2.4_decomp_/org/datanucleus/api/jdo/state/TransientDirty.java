package org.datanucleus.api.jdo.state;

import org.datanucleus.Transaction;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;

class TransientDirty extends LifeCycleState {
   TransientDirty() {
      this.isPersistent = false;
      this.isTransactional = true;
      this.isDirty = true;
      this.isNew = false;
      this.isDeleted = false;
      this.stateType = 6;
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      return this;
   }

   public LifeCycleState transitionMakePersistent(ObjectProvider op) {
      op.registerTransactional();
      return this.changeState(op, 1);
   }

   public LifeCycleState transitionCommit(ObjectProvider op, Transaction tx) {
      op.clearSavedFields();
      return this.changeTransientState(op, 5);
   }

   public LifeCycleState transitionRollback(ObjectProvider op, Transaction tx) {
      if (tx.getRestoreValues() || op.isRestoreValues()) {
         op.restoreFields();
      }

      return this.changeTransientState(op, 5);
   }

   public String toString() {
      return "T_DIRTY";
   }
}
