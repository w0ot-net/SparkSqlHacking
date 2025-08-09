package org.apache.hadoop.hive.serde2.dynamic_type;

public class DynamicSerDeDefinition extends SimpleNode {
   public DynamicSerDeDefinition(int id) {
      super(id);
   }

   public DynamicSerDeDefinition(thrift_grammar p, int id) {
      super(p, id);
   }
}
