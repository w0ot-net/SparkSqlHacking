package org.apache.hadoop.hive.serde2.dynamic_type;

public class SimpleNode implements Node {
   protected Node parent;
   protected Node[] children;
   protected int id;
   protected thrift_grammar parser;

   public SimpleNode(int i) {
      this.id = i;
   }

   public SimpleNode(thrift_grammar p, int i) {
      this(i);
      this.parser = p;
   }

   public void jjtOpen() {
   }

   public void jjtClose() {
   }

   public void jjtSetParent(Node n) {
      this.parent = n;
   }

   public Node jjtGetParent() {
      return this.parent;
   }

   public void jjtAddChild(Node n, int i) {
      if (this.children == null) {
         this.children = new Node[i + 1];
      } else if (i >= this.children.length) {
         Node[] c = new Node[i + 1];
         System.arraycopy(this.children, 0, c, 0, this.children.length);
         this.children = c;
      }

      this.children[i] = n;
   }

   public Node jjtGetChild(int i) {
      return this.children[i];
   }

   public int jjtGetNumChildren() {
      return this.children == null ? 0 : this.children.length;
   }

   public String toString() {
      return thrift_grammarTreeConstants.jjtNodeName[this.id];
   }

   public String toString(String prefix) {
      return prefix + this.toString();
   }

   public void dump(String prefix) {
      System.out.println(this.toString(prefix));
      if (this.children != null) {
         for(int i = 0; i < this.children.length; ++i) {
            SimpleNode n = (SimpleNode)this.children[i];
            if (n != null) {
               n.dump(prefix + " ");
            }
         }
      }

   }
}
