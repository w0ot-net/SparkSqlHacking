package org.datanucleus.query.compiler;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.query.symbol.Symbol;

public class Node {
   protected NodeType nodeType;
   protected Object nodeValue;
   private int cursorPos = -1;
   protected Symbol symbol;
   protected Node parent;
   protected List childNodes = new ArrayList();
   protected List properties = null;

   public Node(NodeType nodeType) {
      this.nodeType = nodeType;
   }

   public Node(NodeType nodeType, Object nodeValue) {
      this.nodeType = nodeType;
      this.nodeValue = nodeValue;
   }

   public NodeType getNodeType() {
      return this.nodeType;
   }

   public void setNodeValue(Object val) {
      this.nodeValue = val;
   }

   public Object getNodeValue() {
      return this.nodeValue;
   }

   public boolean hasProperties() {
      return this.properties != null;
   }

   public List getProperties() {
      return this.properties;
   }

   public void addProperty(Node node) {
      if (this.properties == null) {
         this.properties = new ArrayList();
      }

      this.properties.add(node);
   }

   public void setPropertyAtPosition(int position, Node node) {
      if (this.properties != null) {
         if (position < this.properties.size()) {
            this.properties.set(position, node);
         }
      }
   }

   public List getChildNodes() {
      return this.childNodes;
   }

   public void removeChildNode(Node node) {
      this.childNodes.remove(node);
   }

   public Node insertChildNode(Node node) {
      this.childNodes.add(0, node);
      return node;
   }

   public Node insertChildNode(Node node, int position) {
      this.childNodes.add(position, node);
      return node;
   }

   public Node appendChildNode(Node node) {
      this.childNodes.add(node);
      return node;
   }

   public Node getChildNode(int index) {
      return (Node)this.childNodes.get(index);
   }

   public Node getFirstChild() {
      this.cursorPos = 0;
      return this.childNodes.size() < 1 ? null : (Node)this.childNodes.get(0);
   }

   public Node getNextChild() {
      ++this.cursorPos;
      return this.childNodes.size() <= this.cursorPos ? null : (Node)this.childNodes.get(this.cursorPos);
   }

   public boolean hasNextChild() {
      return this.cursorPos + 1 < this.childNodes.size();
   }

   public Symbol getSymbol() {
      return this.symbol;
   }

   public void setSymbol(Symbol symbol) {
      this.symbol = symbol;
   }

   public void setParent(Node parent) {
      this.parent = parent;
   }

   public Node getParent() {
      return this.parent;
   }

   public String getNodeId() {
      Node node = this;

      StringBuilder sb;
      for(sb = new StringBuilder(); node != null && node.getNodeType() == NodeType.IDENTIFIER; node = node.getParent()) {
         if (sb.length() > 0) {
            sb.insert(0, ".");
         }

         sb.insert(0, node.getNodeValue());
      }

      return sb.toString();
   }

   public String getNodeChildId() {
      Node node = this;

      StringBuilder sb;
      for(sb = new StringBuilder(); node != null && node.getNodeType() == NodeType.IDENTIFIER; node = node.getFirstChild()) {
         if (sb.length() > 0) {
            sb.append(".");
         }

         sb.append(node.getNodeValue());
      }

      return sb.toString();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.printTree(0));
      return sb.toString();
   }

   public Node clone(Node parent) {
      Node n = new Node(this.nodeType, this.nodeValue);
      n.parent = parent;
      if (!this.childNodes.isEmpty()) {
         for(Node child : this.childNodes) {
            Node c = child.clone(n);
            n.appendChildNode(c);
         }
      }

      if (this.properties != null && !this.properties.isEmpty()) {
         for(Node prop : this.properties) {
            Node p = prop.clone(n);
            n.addProperty(p);
         }
      }

      return n;
   }

   private String printTree(int indentation) {
      StringBuilder sb = new StringBuilder();
      sb.append(this.indent(indentation));
      String nodeTypeStr = this.nodeType.toString();
      sb.append("[" + nodeTypeStr + " : " + this.nodeValue);
      if (this.properties != null) {
         sb.append(this.indent(indentation)).append("(");

         for(int i = 0; i < this.properties.size(); ++i) {
            sb.append(((Node)this.properties.get(i)).printTree(indentation + 1));
            if (i < this.properties.size() - 1) {
               sb.append(",");
            }
         }

         sb.append(this.indent(indentation)).append(")");
      }

      if (this.childNodes.size() > 0) {
         sb.append(".");

         for(int i = 0; i < this.childNodes.size(); ++i) {
            sb.append(((Node)this.childNodes.get(i)).printTree(indentation + 1));
            if (i < this.childNodes.size() - 1) {
               sb.append(",");
            }
         }
      }

      sb.append("]");
      return sb.toString();
   }

   private String indent(int indentation) {
      StringBuilder sb = new StringBuilder();
      sb.append("\n");

      for(int i = 0; i < 4 * indentation; ++i) {
         sb.append(" ");
      }

      return sb.toString();
   }
}
