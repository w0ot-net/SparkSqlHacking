package org.snakeyaml.engine.v2.nodes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.exceptions.Mark;

public class MappingNode extends CollectionNode {
   private List value;

   public MappingNode(Tag tag, boolean resolved, List value, FlowStyle flowStyle, Optional startMark, Optional endMark) {
      super(tag, flowStyle, startMark, endMark);
      Objects.requireNonNull(value);
      this.value = value;
      this.resolved = resolved;
   }

   public MappingNode(Tag tag, List value, FlowStyle flowStyle) {
      this(tag, true, value, flowStyle, Optional.empty(), Optional.empty());
   }

   public NodeType getNodeType() {
      return NodeType.MAPPING;
   }

   public List getValue() {
      return this.value;
   }

   public void setValue(List merged) {
      Objects.requireNonNull(merged);
      this.value = merged;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();

      for(NodeTuple node : this.getValue()) {
         buf.append("{ key=");
         buf.append(node.getKeyNode());
         buf.append("; value=");
         if (node.getValueNode() instanceof CollectionNode) {
            buf.append(System.identityHashCode(node.getValueNode()));
         } else {
            buf.append(node);
         }

         buf.append(" }");
      }

      String values = buf.toString();
      return "<" + this.getClass().getName() + " (tag=" + this.getTag() + ", values=" + values + ")>";
   }
}
