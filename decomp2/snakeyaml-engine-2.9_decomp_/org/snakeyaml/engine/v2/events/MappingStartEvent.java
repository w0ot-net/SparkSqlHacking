package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class MappingStartEvent extends CollectionStartEvent {
   public MappingStartEvent(Optional anchor, Optional tag, boolean implicit, FlowStyle flowStyle, Optional startMark, Optional endMark) {
      super(anchor, tag, implicit, flowStyle, startMark, endMark);
   }

   public MappingStartEvent(Optional anchor, Optional tag, boolean implicit, FlowStyle flowStyle) {
      this(anchor, tag, implicit, flowStyle, Optional.empty(), Optional.empty());
   }

   public Event.ID getEventId() {
      return Event.ID.MappingStart;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder("+MAP");
      if (this.getFlowStyle() == FlowStyle.FLOW) {
         builder.append(" {}");
      }

      builder.append(super.toString());
      return builder.toString();
   }
}
