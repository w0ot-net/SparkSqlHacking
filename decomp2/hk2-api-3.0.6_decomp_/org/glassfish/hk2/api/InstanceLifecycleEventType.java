package org.glassfish.hk2.api;

public enum InstanceLifecycleEventType {
   PRE_PRODUCTION,
   POST_PRODUCTION,
   PRE_DESTRUCTION;

   // $FF: synthetic method
   private static InstanceLifecycleEventType[] $values() {
      return new InstanceLifecycleEventType[]{PRE_PRODUCTION, POST_PRODUCTION, PRE_DESTRUCTION};
   }
}
