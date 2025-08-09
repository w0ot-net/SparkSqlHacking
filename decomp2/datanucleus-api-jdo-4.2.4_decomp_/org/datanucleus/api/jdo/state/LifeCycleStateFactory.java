package org.datanucleus.api.jdo.state;

import org.datanucleus.state.LifeCycleState;

public abstract class LifeCycleStateFactory {
   private static LifeCycleState[] states = new LifeCycleState[13];

   public static final LifeCycleState getLifeCycleState(int stateType) {
      return states[stateType];
   }

   static {
      states[4] = new Hollow();
      states[2] = new PersistentClean();
      states[3] = new PersistentDirty();
      states[1] = new PersistentNew();
      states[7] = new PersistentNewDeleted();
      states[8] = new PersistentDeleted();
      states[9] = new PersistentNontransactional();
      states[5] = new TransientClean();
      states[6] = new TransientDirty();
      states[10] = new PersistentNontransactionalDirty();
      states[11] = new DetachedClean();
      states[12] = new DetachedDirty();
      states[0] = null;
   }
}
