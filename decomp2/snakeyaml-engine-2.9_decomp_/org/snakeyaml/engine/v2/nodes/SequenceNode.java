package org.snakeyaml.engine.v2.nodes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.exceptions.Mark;

public class SequenceNode extends CollectionNode {
   private final List value;

   public SequenceNode(Tag tag, boolean resolved, List value, FlowStyle flowStyle, Optional startMark, Optional endMark) {
      super(tag, flowStyle, startMark, endMark);
      Objects.requireNonNull(value, "value in a Node is required.");
      this.value = value;
      this.resolved = resolved;
   }

   public SequenceNode(Tag tag, List value, FlowStyle flowStyle) {
      this(tag, true, value, flowStyle, Optional.empty(), Optional.empty());
   }

   public NodeType getNodeType() {
      return NodeType.SEQUENCE;
   }

   public List getValue() {
      return this.value;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();

      for(Node node : this.getValue()) {
         if (node instanceof CollectionNode) {
            buf.append(System.identityHashCode(node));
         } else {
            buf.append(node.toString());
         }

         buf.append(",");
      }

      if (buf.length() > 0) {
         buf.deleteCharAt(buf.length() - 1);
      }

      return "<" + this.getClass().getName() + " (tag=" + this.getTag() + ", value=[" + buf + "])>";
   }
}
