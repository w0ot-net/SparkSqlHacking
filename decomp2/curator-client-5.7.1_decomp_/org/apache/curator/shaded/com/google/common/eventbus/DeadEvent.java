package org.apache.curator.shaded.com.google.common.eventbus;

import org.apache.curator.shaded.com.google.common.base.MoreObjects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
public class DeadEvent {
   private final Object source;
   private final Object event;

   public DeadEvent(Object source, Object event) {
      this.source = Preconditions.checkNotNull(source);
      this.event = Preconditions.checkNotNull(event);
   }

   public Object getSource() {
      return this.source;
   }

   public Object getEvent() {
      return this.event;
   }

   public String toString() {
      return MoreObjects.toStringHelper((Object)this).add("source", this.source).add("event", this.event).toString();
   }
}
