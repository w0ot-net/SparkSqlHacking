package org.apache.hadoop.hive.serde2.dynamic_type;

public class DynamicSerDeFieldType extends DynamicSerDeSimpleNode {
   private static final int FD_FIELD_TYPE = 0;

   public DynamicSerDeFieldType(int i) {
      super(i);
   }

   public DynamicSerDeFieldType(thrift_grammar p, int i) {
      super(p, i);
   }

   protected DynamicSerDeTypeBase getMyType() {
      return (DynamicSerDeTypeBase)this.jjtGetChild(0);
   }
}
