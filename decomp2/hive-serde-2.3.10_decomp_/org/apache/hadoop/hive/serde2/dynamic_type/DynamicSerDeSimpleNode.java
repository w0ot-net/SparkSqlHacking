package org.apache.hadoop.hive.serde2.dynamic_type;

public class DynamicSerDeSimpleNode extends SimpleNode {
   protected static final boolean thrift_mode = true;
   protected int fieldid;
   protected String name;

   public DynamicSerDeSimpleNode(int i) {
      super(i);
   }

   public DynamicSerDeSimpleNode(thrift_grammar p, int i) {
      super(p, i);
   }
}
