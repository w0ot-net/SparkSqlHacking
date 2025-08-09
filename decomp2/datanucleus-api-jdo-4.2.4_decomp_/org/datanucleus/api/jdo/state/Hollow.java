package org.datanucleus.api.jdo.state;

import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.api.jdo.exceptions.TransactionNotReadableException;
import org.datanucleus.api.jdo.exceptions.TransactionNotWritableException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.state.IllegalStateTransitionException;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

class Hollow extends LifeCycleState {
   protected Hollow() {
      this.isPersistent = true;
      this.isDirty = false;
      this.isNew = false;
      this.isDeleted = false;
      this.isTransactional = false;
      this.stateType = 4;
   }

   public LifeCycleState transitionDeletePersistent(ObjectProvider op) {
      op.clearLoadedFlags();
      return this.changeState(op, 8);
   }

   public LifeCycleState transitionMakeTransactional(ObjectProvider op, boolean refreshFields) {
      if (refreshFields) {
         op.refreshLoadedFields();
      }

      return this.changeState(op, 2);
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      if (useFetchPlan) {
         op.loadUnloadedFieldsInFetchPlan();
      }

      return this.changeState(op, 0);
   }

   public LifeCycleState transitionCommit(ObjectProvider op) {
      throw new IllegalStateTransitionException(this, "commit", op);
   }

   public LifeCycleState transitionRollback(ObjectProvider op) {
      throw new IllegalStateTransitionException(this, "rollback", op);
   }

   public LifeCycleState transitionReadField(ObjectProvider op, boolean isLoaded) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (!tx.isActive() && !tx.getNontransactionalRead()) {
         throw new TransactionNotReadableException(Localiser.msg("027000"), op.getInternalObjectId());
      } else if (!tx.isActive() && op.getClassMetaData().getIdentityType() == IdentityType.NONDURABLE) {
         throw new NucleusUserException("Not able to read fields of nondurable object when in HOLLOW state");
      } else {
         return !tx.getOptimistic() && tx.isActive() ? this.changeState(op, 2) : this.changeState(op, 9);
      }
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (!tx.isActive() && !tx.getNontransactionalWrite()) {
         throw new TransactionNotWritableException(Localiser.msg("027001"), op.getInternalObjectId());
      } else {
         return this.changeState(op, tx.isActive() ? 3 : 9);
      }
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, boolean fgOnly) {
      if (fgOnly) {
         op.loadUnloadedFieldsInFetchPlan();
      } else {
         op.loadUnloadedFields();
      }

      Transaction tx = op.getExecutionContext().getTransaction();
      if (!tx.getOptimistic() && tx.isActive()) {
         return this.changeState(op, 2);
      } else {
         return tx.getOptimistic() ? this.changeState(op, 9) : super.transitionRetrieve(op, fgOnly);
      }
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, FetchPlan fetchPlan) {
      op.loadUnloadedFieldsOfClassInFetchPlan(fetchPlan);
      Transaction tx = op.getExecutionContext().getTransaction();
      if (!tx.getOptimistic() && tx.isActive()) {
         return this.changeState(op, 2);
      } else {
         return tx.getOptimistic() ? this.changeState(op, 9) : super.transitionRetrieve(op, fetchPlan);
      }
   }

   public LifeCycleState transitionRefresh(ObjectProvider op) {
      op.clearSavedFields();
      op.refreshFieldsInFetchPlan();
      op.unloadNonFetchPlanFields();
      return this;
   }

   public LifeCycleState transitionDetach(ObjectProvider op) {
      return this.changeState(op, 11);
   }

   public LifeCycleState transitionSerialize(ObjectProvider op) {
      Transaction tx = op.getExecutionContext().getTransaction();
      return (LifeCycleState)(tx.isActive() && !tx.getOptimistic() ? this.changeState(op, 2) : this);
   }

   public String toString() {
      return "HOLLOW";
   }
}
