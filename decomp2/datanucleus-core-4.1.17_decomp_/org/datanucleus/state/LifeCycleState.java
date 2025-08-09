package org.datanucleus.state;

import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class LifeCycleState {
   public static final int TRANSIENT = 0;
   public static final int P_NEW = 1;
   public static final int P_CLEAN = 2;
   public static final int P_DIRTY = 3;
   public static final int HOLLOW = 4;
   public static final int T_CLEAN = 5;
   public static final int T_DIRTY = 6;
   public static final int P_NEW_DELETED = 7;
   public static final int P_DELETED = 8;
   public static final int P_NONTRANS = 9;
   public static final int P_NONTRANS_DIRTY = 10;
   public static final int DETACHED_CLEAN = 11;
   public static final int DETACHED_DIRTY = 12;
   public static final int TOTAL = 13;
   public static final int ILLEGAL_STATE = 13;
   protected boolean isDirty;
   protected boolean isNew;
   protected boolean isDeleted;
   protected boolean isTransactional;
   protected boolean isPersistent;
   protected int stateType;

   public final int stateType() {
      return this.stateType;
   }

   protected final LifeCycleState changeState(ObjectProvider op, int newStateType) {
      LifeCycleState newState = op.getExecutionContext().getNucleusContext().getApiAdapter().getLifeCycleState(newStateType);
      if (NucleusLogger.LIFECYCLE.isDebugEnabled()) {
         NucleusLogger.LIFECYCLE.debug(Localiser.msg("027016", StringUtils.toJVMIDString(op.getObject()), IdentityUtils.getPersistableIdentityForId(op.getInternalObjectId()), this, newState));
      }

      if (this.isTransactional) {
         if (newState == null || !newState.isTransactional) {
            op.evictFromTransaction();
         }
      } else if (newState != null && newState.isTransactional) {
         op.enlistInTransaction();
      }

      if (newState == null) {
         op.disconnect();
      }

      return newState;
   }

   protected final LifeCycleState changeTransientState(ObjectProvider op, int newStateType) {
      LifeCycleState newState = op.getExecutionContext().getNucleusContext().getApiAdapter().getLifeCycleState(newStateType);

      try {
         op.enlistInTransaction();
      } catch (Exception var5) {
      }

      return newState;
   }

   public LifeCycleState transitionMakePersistent(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionDeletePersistent(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionMakeTransactional(ObjectProvider op, boolean refreshFields) {
      return this;
   }

   public LifeCycleState transitionMakeNontransactional(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionMakeTransient(ObjectProvider op, boolean useFetchPlan, boolean detachAllOnCommit) {
      return this;
   }

   public LifeCycleState transitionBegin(ObjectProvider op, Transaction tx) {
      return this;
   }

   public LifeCycleState transitionCommit(ObjectProvider op, Transaction tx) {
      return this;
   }

   public LifeCycleState transitionRollback(ObjectProvider op, Transaction tx) {
      return this;
   }

   public LifeCycleState transitionRefresh(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionEvict(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionReadField(ObjectProvider op, boolean isLoaded) {
      return this;
   }

   public LifeCycleState transitionWriteField(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, boolean fgOnly) {
      return this;
   }

   public LifeCycleState transitionRetrieve(ObjectProvider op, FetchPlan fetchPlan) {
      return this;
   }

   public LifeCycleState transitionDetach(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionAttach(ObjectProvider op) {
      return this;
   }

   public LifeCycleState transitionSerialize(ObjectProvider op) {
      return this;
   }

   public final boolean isDirty() {
      return this.isDirty;
   }

   public final boolean isNew() {
      return this.isNew;
   }

   public final boolean isDeleted() {
      return this.isDeleted;
   }

   public final boolean isTransactional() {
      return this.isTransactional;
   }

   public final boolean isPersistent() {
      return this.isPersistent;
   }

   public abstract String toString();
}
