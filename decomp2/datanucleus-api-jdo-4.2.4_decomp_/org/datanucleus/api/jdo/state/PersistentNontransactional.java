package org.datanucleus.api.jdo.state;

import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.api.jdo.exceptions.TransactionNotReadableException;
import org.datanucleus.api.jdo.exceptions.TransactionNotWritableException;
import org.datanucleus.state.IllegalStateTransitionException;
import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

class PersistentNontransactional extends LifeCycleState {
   protected PersistentNontransactional() {
      this.isPersistent = true;
      this.isDirty = false;
      this.isNew = false;
      this.isDeleted = false;
      this.isTransactional = false;
      this.stateType = 9;
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

   public LifeCycleState transitionCommit(ObjectProvider op, Transaction tx) {
      throw new IllegalStateTransitionException(this, "commit", op);
   }

   public LifeCycleState transitionRollback(ObjectProvider op, Transaction tx) {
      throw new IllegalStateTransitionException(this, "rollback", op);
   }

   public LifeCycleState transitionRefresh(ObjectProvider op) {
      op.refreshFieldsInFetchPlan();
      op.unloadNonFetchPlanFields();
      return this;
   }

   public LifeCycleState transitionEvict(ObjectProvider op) {
      op.clearNonPrimaryKeyFields();
      op.clearSavedFields();
      return this.changeState(op, 4);
   }

   public LifeCycleState transitionReadField(ObjectProvider op, boolean isLoaded) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (!tx.isActive() && !tx.getNontransactionalRead()) {
         throw new TransactionNotReadableException(Localiser.msg("027002"), op.getInternalObjectId());
      } else if (tx.isActive() && !tx.getOptimistic()) {
         op.saveFields();
         op.refreshLoadedFields();
         return this.changeState(op, 2);
      } else {
         return this;
      }
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (!tx.isActive() && !tx.getNontransactionalWrite()) {
         throw new TransactionNotWritableException(Localiser.msg("027001"), op.getInternalObjectId());
      } else if (tx.isActive()) {
         op.saveFields();
         return this.changeState(op, 3);
      } else {
         op.saveFields();
         return this.changeState(op, 10);
      }
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, boolean fgOnly) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (tx.isActive() && !tx.getOptimistic()) {
         op.saveFields();
         if (fgOnly) {
            op.loadUnloadedFieldsInFetchPlan();
         } else {
            op.loadUnloadedFields();
         }

         return this.changeState(op, 2);
      } else if (tx.isActive() && tx.getOptimistic()) {
         op.saveFields();
         if (fgOnly) {
            op.loadUnloadedFieldsInFetchPlan();
         } else {
            op.loadUnloadedFields();
         }

         return this;
      } else {
         if (fgOnly) {
            op.loadUnloadedFieldsInFetchPlan();
         } else {
            op.loadUnloadedFields();
         }

         return this;
      }
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, FetchPlan fetchPlan) {
      Transaction tx = op.getExecutionContext().getTransaction();
      if (tx.isActive() && !tx.getOptimistic()) {
         op.saveFields();
         op.loadUnloadedFieldsOfClassInFetchPlan(fetchPlan);
         return this.changeState(op, 2);
      } else if (tx.isActive() && tx.getOptimistic()) {
         op.saveFields();
         op.loadUnloadedFieldsOfClassInFetchPlan(fetchPlan);
         return this;
      } else {
         op.loadUnloadedFieldsOfClassInFetchPlan(fetchPlan);
         return this;
      }
   }

   public LifeCycleState transitionSerialize(ObjectProvider op) {
      Transaction tx = op.getExecutionContext().getTransaction();
      return (LifeCycleState)(tx.isActive() && !tx.getOptimistic() ? this.changeState(op, 2) : this);
   }

   public LifeCycleState transitionDetach(ObjectProvider op) {
      return this.changeState(op, 11);
   }

   public String toString() {
      return "P_NONTRANS";
   }
}
