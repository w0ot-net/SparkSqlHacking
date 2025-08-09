package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class DocumentEndEvent extends Event {
   private final boolean explicit;

   public DocumentEndEvent(boolean explicit, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      this.explicit = explicit;
   }

   public DocumentEndEvent(boolean explicit) {
      this(explicit, Optional.empty(), Optional.empty());
   }

   public boolean isExplicit() {
      return this.explicit;
   }

   public Event.ID getEventId() {
      return Event.ID.DocumentEnd;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder("-DOC");
      if (this.isExplicit()) {
         builder.append(" ...");
      }

      return builder.toString();
   }
}
