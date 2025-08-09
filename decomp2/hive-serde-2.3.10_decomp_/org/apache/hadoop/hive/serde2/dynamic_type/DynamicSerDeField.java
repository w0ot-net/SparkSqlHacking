package org.apache.hadoop.hive.serde2.dynamic_type;

public class DynamicSerDeField extends DynamicSerDeSimpleNode {
   private static final int FD_REQUIREDNESS = 0;
   private static final int FD_FIELD_TYPE = 1;

   public boolean isSkippable() {
      return ((DynamicSerDeFieldRequiredness)this.jjtGetChild(0)).getRequiredness() == DynamicSerDeFieldRequiredness.RequirednessTypes.Skippable;
   }

   public DynamicSerDeFieldType getFieldType() {
      return (DynamicSerDeFieldType)this.jjtGetChild(1);
   }

   public DynamicSerDeField(int i) {
      super(i);
   }

   public DynamicSerDeField(thrift_grammar p, int i) {
      super(p, i);
   }
}
