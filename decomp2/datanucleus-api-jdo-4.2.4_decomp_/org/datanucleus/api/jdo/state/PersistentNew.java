package org.datanucleus.api.jdo.state;

import org.datanucleus.Transaction;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

class PersistentNew extends LifeCycleState {
   protected PersistentNew() {
      this.isPersistent = true;
      this.isDirty = true;
      this.isNew = true;
      this.isDeleted = false;
      this.isTransactional = true;
      this.stateType = 1;
   }

   public LifeCycleState transitionDeletePersistent(ObjectProvider op) {
      op.clearLoadedFlags();
      return this.changeState(op, 7);
   }

   public LifeCycleState transitionMakeNontransactional(ObjectProvider op) {
      throw new NucleusUserException(Localiser.msg("027013"), op.getInternalObjectId());
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      if (detachAllOnCommit) {
         return this.changeState(op, 0);
      } else {
         throw new NucleusUserException(Localiser.msg("027014"), op.getInternalObjectId());
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
      }

      return this.changeState(op, 0);
   }

   public LifeCycleState transitionDetach(ObjectProvider op) {
      return this.changeState(op, 11);
   }

   public String toString() {
      return "P_NEW";
   }
}
