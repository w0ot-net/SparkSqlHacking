package org.datanucleus.api.jdo.state;

import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;

class DetachedDirty extends LifeCycleState {
   protected DetachedDirty() {
      this.isPersistent = false;
      this.isDirty = true;
      this.isNew = false;
      this.isDeleted = false;
      this.isTransactional = false;
      this.stateType = 12;
   }

   public String toString() {
      return "DETACHED_DIRTY";
   }

   public LifeCycleState transitionAttach(ObjectProvider op) {
      return this.changeState(op, 3);
   }
}
