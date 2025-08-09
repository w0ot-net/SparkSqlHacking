package org.datanucleus.query.compiler;

public class ParameterNode extends Node {
   int position;

   public ParameterNode(NodeType nodeType, int position) {
      super(nodeType);
      this.position = position;
   }

   public ParameterNode(NodeType nodeType, Object nodeValue, int position) {
      super(nodeType, nodeValue);
      this.position = position;
   }

   public int getPosition() {
      return this.position;
   }
}
