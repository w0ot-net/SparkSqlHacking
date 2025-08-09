package org.sparkproject.guava.eventbus;

import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Preconditions;

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
