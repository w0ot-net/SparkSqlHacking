package org.datanucleus.api.jdo.state;

import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;

class PersistentClean extends LifeCycleState {
   protected PersistentClean() {
      this.isPersistent = true;
      this.isDirty = false;
      this.isNew = false;
      this.isDeleted = false;
      this.isTransactional = true;
      this.stateType = 2;
   }

   public LifeCycleState transitionDeletePersistent(ObjectProvider op) {
      op.clearLoadedFlags();
      return this.changeState(op, 8);
   }

   public LifeCycleState transitionMakeNontransactional(ObjectProvider op) {
      op.clearSavedFields();
      return this.changeState(op, 9);
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      if (useFetchPlan) {
         op.loadUnloadedFieldsInFetchPlan();
      }

      return this.changeState(op, 0);
   }

   public LifeCycleState transitionCommit(ObjectProvider op, Transaction tx) {
      op.clearSavedFields();
      if (tx.getRetainValues()) {
         return this.changeState(op, 9);
      } else {
         op.clearNonPrimaryKeyFields();
         return this.changeState(op, 4);
      }
   }

   public LifeCycleState transitionRollback(ObjectProvider op, Transaction tx) {
      if (tx.getRestoreValues()) {
         op.restoreFields();
         return this.changeState(op, 9);
      } else {
         op.clearNonPrimaryKeyFields();
         op.clearSavedFields();
         return this.changeState(op, 4);
      }
   }

   public LifeCycleState transitionEvict(ObjectProvider op) {
      op.clearNonPrimaryKeyFields();
      op.clearSavedFields();
      return this.changeState(op, 4);
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (tx.getRestoreValues()) {
         op.saveFields();
      }

      return this.changeState(op, 3);
   }

   public LifeCycleState transitionRefresh(ObjectProvider op) {
      op.clearSavedFields();
      op.refreshFieldsInFetchPlan();
      op.unloadNonFetchPlanFields();
      Transaction tx = op.getExecutionContext().getTransaction();
      return tx.isActive() ? this.changeState(op, 2) : this.changeState(op, 9);
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, boolean fgOnly) {
      if (fgOnly) {
         op.loadUnloadedFieldsInFetchPlan();
      } else {
         op.loadUnloadedFields();
      }

      return this;
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, FetchPlan fetchPlan) {
      op.loadUnloadedFieldsOfClassInFetchPlan(fetchPlan);
      return this;
   }

   public LifeCycleState transitionDetach(ObjectProvider op) {
      return this.changeState(op, 11);
   }

   public String toString() {
      return "P_CLEAN";
   }
}
