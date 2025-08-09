package org.datanucleus.api.jdo.state;

import org.datanucleus.state.LifeCycleState;
import org.datanucleus.state.ObjectProvider;

class DetachedClean extends LifeCycleState {
   protected DetachedClean() {
      this.isPersistent = false;
      this.isDirty = false;
      this.isNew = false;
      this.isDeleted = false;
      this.isTransactional = false;
      this.stateType = 11;
   }

   public String toString() {
      return "DETACHED_CLEAN";
   }

   public LifeCycleState transitionAttach(ObjectProvider op) {
      return this.changeState(op, 2);
   }
}
