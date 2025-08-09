package org.apache.hadoop.hive.serde2.dynamic_type;

public class DynamicSerDeFunction extends DynamicSerDeStructBase {
   private static final int FD_FIELD_LIST = 2;

   public DynamicSerDeFunction(int i) {
      super(i);
   }

   public DynamicSerDeFunction(thrift_grammar p, int i) {
      super(p, i);
   }

   public DynamicSerDeFieldList getFieldList() {
      return (DynamicSerDeFieldList)this.jjtGetChild(2);
   }

   public String toString() {
      String result = "function " + this.name + " (";
      result = result + this.getFieldList().toString();
      result = result + ")";
      return result;
   }

   public byte getType() {
      return 1;
   }
}
