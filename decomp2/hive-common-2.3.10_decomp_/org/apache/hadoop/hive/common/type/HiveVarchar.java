package org.apache.hadoop.hive.common.type;

public class HiveVarchar extends HiveBaseChar implements Comparable {
   public static final int MAX_VARCHAR_LENGTH = 65535;

   public HiveVarchar() {
   }

   public HiveVarchar(String val, int len) {
      this.setValue(val, len);
   }

   public HiveVarchar(HiveVarchar hc, int len) {
      this.setValue(hc, len);
   }

   public void setValue(String val) {
      super.setValue((String)val, -1);
   }

   public void setValue(HiveVarchar hc) {
      super.setValue((String)hc.getValue(), -1);
   }

   public int compareTo(HiveVarchar rhs) {
      return rhs == this ? 0 : this.getValue().compareTo(rhs.getValue());
   }

   public boolean equals(Object rhs) {
      return rhs == this ? true : this.getValue().equals(((HiveVarchar)rhs).getValue());
   }
}
