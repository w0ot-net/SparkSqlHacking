package org.datanucleus.api.jdo.state;

import javax.jdo.JDOUserException;
import org.datanucleus.Transaction;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

class PersistentNewDeleted extends LifeCycleState {
   protected PersistentNewDeleted() {
      this.isPersistent = true;
      this.isDirty = true;
      this.isNew = true;
      this.isDeleted = true;
      this.isTransactional = true;
      this.stateType = 7;
   }

   public LifeCycleState transitionMakeNontransactional(ObjectProvider op) {
      throw new NucleusUserException(Localiser.msg("027003"), op.getInternalObjectId());
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      throw new NucleusUserException(Localiser.msg("027004"), op.getInternalObjectId());
   }

   public LifeCycleState transitionCommit(ObjectProvider op, Transaction tx) {
      if (!tx.getRetainValues()) {
         op.clearFields();
      }

      return this.changeState(op, 0);
   }

   public LifeCycleState transitionRollback(ObjectProvider op, Transaction tx) {
      if (tx.getRestoreValues()) {
         op.restoreFields();
      }

      return this.changeState(op, 0);
   }

   public LifeCycleState transitionReadField(ObjectProvider op, boolean isLoaded) {
      throw new JDOUserException(Localiser.msg("027005"), op.getInternalObjectId());
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      throw new JDOUserException(Localiser.msg("027006"), op.getInternalObjectId());
   }

   public String toString() {
      return "P_NEW_DELETED";
   }
}
