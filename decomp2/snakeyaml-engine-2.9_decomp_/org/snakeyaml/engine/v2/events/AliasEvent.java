package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class AliasEvent extends NodeEvent {
   private final Anchor alias;

   public AliasEvent(Optional anchor, Optional startMark, Optional endMark) {
      super(anchor, startMark, endMark);
      this.alias = (Anchor)anchor.orElseThrow(() -> new NullPointerException("Anchor is required in AliasEvent"));
   }

   public AliasEvent(Optional anchor) {
      this(anchor, Optional.empty(), Optional.empty());
   }

   public Event.ID getEventId() {
      return Event.ID.Alias;
   }

   public String toString() {
      return "=ALI *" + this.alias;
   }

   public Anchor getAlias() {
      return this.alias;
   }
}
