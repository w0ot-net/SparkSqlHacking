package org.datanucleus.api.jdo.state;

import javax.jdo.JDOUserException;
import org.datanucleus.Transaction;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

class PersistentDeleted extends LifeCycleState {
   protected PersistentDeleted() {
      this.isPersistent = true;
      this.isDirty = true;
      this.isNew = false;
      this.isDeleted = true;
      this.isTransactional = true;
      this.stateType = 8;
   }

   public LifeCycleState transitionMakeNontransactional(ObjectProvider op) {
      throw new JDOUserException(Localiser.msg("027007"), op.getInternalObjectId());
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      throw new JDOUserException(Localiser.msg("027008"), op.getInternalObjectId());
   }

   public LifeCycleState transitionCommit(ObjectProvider op, Transaction tx) {
      if (!tx.getRetainValues()) {
         op.clearFields();
      }

      return this.changeState(op, 0);
   }

   public LifeCycleState transitionRollback(ObjectProvider op, Transaction tx) {
      if (tx.getRetainValues()) {
         if (tx.getRestoreValues()) {
            op.restoreFields();
         }

         return this.changeState(op, 9);
      } else {
         op.clearNonPrimaryKeyFields();
         op.clearSavedFields();
         return this.changeState(op, 4);
      }
   }

   public LifeCycleState transitionReadField(ObjectProvider op, boolean isLoaded) {
      throw new JDOUserException(Localiser.msg("027009"), op.getInternalObjectId());
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      throw new JDOUserException(Localiser.msg("027010"), op.getInternalObjectId());
   }

   public String toString() {
      return "P_DELETED";
   }
}
