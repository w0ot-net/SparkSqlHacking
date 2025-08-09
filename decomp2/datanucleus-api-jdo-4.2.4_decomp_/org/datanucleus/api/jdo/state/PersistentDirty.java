package org.datanucleus.api.jdo.state;

import org.datanucleus.Transaction;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

class PersistentDirty extends LifeCycleState {
   protected PersistentDirty() {
      this.isPersistent = true;
      this.isDirty = true;
      this.isNew = false;
      this.isDeleted = false;
      this.isTransactional = true;
      this.stateType = 3;
   }

   public LifeCycleState transitionDeletePersistent(ObjectProvider op) {
      op.clearLoadedFlags();
      return this.changeState(op, 8);
   }

   public LifeCycleState transitionMakeNontransactional(ObjectProvider op) {
      throw new NucleusUserException(Localiser.msg("027011"), op.getInternalObjectId());
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      if (detachAllOnCommit) {
         return this.changeState(op, 0);
      } else {
         throw new NucleusUserException(Localiser.msg("027012"), op.getInternalObjectId());
      }
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

   public LifeCycleState transitionRefresh(ObjectProvider op) {
      op.clearSavedFields();
      op.refreshFieldsInFetchPlan();
      op.unloadNonFetchPlanFields();
      Transaction tx = op.getExecutionContext().getTransaction();
      return tx.isActive() && !tx.getOptimistic() ? this.changeState(op, 2) : this.changeState(op, 9);
   }

   public LifeCycleState transitionDetach(ObjectProvider op) {
      return this.changeState(op, 11);
   }

   public String toString() {
      return "P_DIRTY";
   }
}
