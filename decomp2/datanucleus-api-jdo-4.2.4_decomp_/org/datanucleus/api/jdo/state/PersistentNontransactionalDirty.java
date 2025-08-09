package org.datanucleus.api.jdo.state;

import javax.jdo.JDOUserException;
import org.datanucleus.Transaction;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

class PersistentNontransactionalDirty extends LifeCycleState {
   protected PersistentNontransactionalDirty() {
      this.isPersistent = true;
      this.isDirty = true;
      this.isNew = false;
      this.isDeleted = false;
      this.isTransactional = false;
      this.stateType = 10;
   }

   public LifeCycleState transitionMakeTransactional(ObjectProvider op, boolean refreshFields) {
      return this;
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
         return this.changeState(op, 10);
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

   public LifeCycleState transitionReadField(ObjectProvider op, boolean isLoaded) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (!tx.isActive() && !tx.getNontransactionalRead()) {
         throw new JDOUserException(Localiser.msg("027002"), op.getInternalObjectId());
      } else {
         return this;
      }
   }

   public LifeCycleState transitionBegin(ObjectProvider op, Transaction tx) {
      op.saveFields();
      op.enlistInTransaction();
      return this;
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionDetach(ObjectProvider op) {
      return this.changeState(op, 11);
   }

   public String toString() {
      return "P_NONTRANS_DIRTY";
   }
}
