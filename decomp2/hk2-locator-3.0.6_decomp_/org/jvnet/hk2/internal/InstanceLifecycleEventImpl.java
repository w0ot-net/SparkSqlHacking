package org.jvnet.hk2.internal;

import java.util.Collections;
import java.util.Map;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InstanceLifecycleEvent;
import org.glassfish.hk2.api.InstanceLifecycleEventType;

public class InstanceLifecycleEventImpl implements InstanceLifecycleEvent {
   private final InstanceLifecycleEventType eventType;
   private final ActiveDescriptor descriptor;
   private final Object lifecycleObject;
   private final Map knownInjectees;

   InstanceLifecycleEventImpl(InstanceLifecycleEventType eventType, Object lifecycleObject, Map knownInjectees, ActiveDescriptor descriptor) {
      this.eventType = eventType;
      this.lifecycleObject = lifecycleObject;
      if (knownInjectees == null) {
         this.knownInjectees = null;
      } else {
         this.knownInjectees = Collections.unmodifiableMap(knownInjectees);
      }

      this.descriptor = descriptor;
   }

   InstanceLifecycleEventImpl(InstanceLifecycleEventType eventType, Object lifecycleObject, ActiveDescriptor descriptor) {
      this(eventType, lifecycleObject, (Map)null, descriptor);
   }

   public InstanceLifecycleEventType getEventType() {
      return this.eventType;
   }

   public ActiveDescriptor getActiveDescriptor() {
      return this.descriptor;
   }

   public Object getLifecycleObject() {
      return this.lifecycleObject;
   }

   public Map getKnownInjectees() {
      return this.knownInjectees;
   }

   public String toString() {
      String descName = this.descriptor == null ? "null" : this.descriptor.getImplementation();
      return "InstanceLifecycleEventImpl(" + this.eventType + "," + descName + "," + System.identityHashCode(this) + ")";
   }
}
