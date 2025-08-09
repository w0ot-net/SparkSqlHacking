package org.snakeyaml.engine.v2.nodes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.exceptions.Mark;

public abstract class CollectionNode extends Node {
   private FlowStyle flowStyle;

   public CollectionNode(Tag tag, FlowStyle flowStyle, Optional startMark, Optional endMark) {
      super(tag, startMark, endMark);
      this.setFlowStyle(flowStyle);
   }

   public abstract List getValue();

   public FlowStyle getFlowStyle() {
      return this.flowStyle;
   }

   public void setFlowStyle(FlowStyle flowStyle) {
      Objects.requireNonNull(flowStyle, "Flow style must be provided.");
      this.flowStyle = flowStyle;
   }

   public void setEndMark(Optional endMark) {
      this.endMark = endMark;
   }
}
