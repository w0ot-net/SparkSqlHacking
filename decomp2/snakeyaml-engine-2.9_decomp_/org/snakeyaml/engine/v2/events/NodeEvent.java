package org.snakeyaml.engine.v2.events;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.exceptions.Mark;

public abstract class NodeEvent extends Event {
   private final Optional anchor;

   public NodeEvent(Optional anchor, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      Objects.requireNonNull(anchor);
      this.anchor = anchor;
   }

   public Optional getAnchor() {
      return this.anchor;
   }
}
