package org.apache.hadoop.hive.serde2.dynamic_type;

public class DynamicSerDeStruct extends DynamicSerDeStructBase {
   private static final int FD_FIELD_LIST = 0;

   public DynamicSerDeStruct(int i) {
      super(i);
   }

   public DynamicSerDeStruct(thrift_grammar p, int i) {
      super(p, i);
   }

   public String toString() {
      String result = "struct " + this.name + "(";
      result = result + this.getFieldList().toString();
      result = result + ")";
      return result;
   }

   public DynamicSerDeFieldList getFieldList() {
      return (DynamicSerDeFieldList)this.jjtGetChild(0);
   }

   public byte getType() {
      return 12;
   }
}
