package org.snakeyaml.engine.v2.events;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.exceptions.Mark;

public abstract class CollectionStartEvent extends NodeEvent {
   private final Optional tag;
   private final boolean implicit;
   private final FlowStyle flowStyle;

   public CollectionStartEvent(Optional anchor, Optional tag, boolean implicit, FlowStyle flowStyle, Optional startMark, Optional endMark) {
      super(anchor, startMark, endMark);
      Objects.requireNonNull(tag);
      this.tag = tag;
      this.implicit = implicit;
      Objects.requireNonNull(flowStyle);
      this.flowStyle = flowStyle;
   }

   public Optional getTag() {
      return this.tag;
   }

   public boolean isImplicit() {
      return this.implicit;
   }

   public FlowStyle getFlowStyle() {
      return this.flowStyle;
   }

   public boolean isFlow() {
      return FlowStyle.FLOW == this.flowStyle;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      this.getAnchor().ifPresent((a) -> builder.append(" &" + a));
      if (!this.implicit) {
         this.getTag().ifPresent((theTag) -> builder.append(" <" + theTag + ">"));
      }

      return builder.toString();
   }
}
