package org.apache.hadoop.hive.serde2.typeinfo;

public class VarcharTypeInfo extends BaseCharTypeInfo {
   private static final long serialVersionUID = 1L;

   public VarcharTypeInfo() {
      super("varchar");
   }

   public VarcharTypeInfo(int length) {
      super("varchar", length);
      BaseCharUtils.validateVarcharParameter(length);
   }

   public String getTypeName() {
      return this.getQualifiedName();
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         VarcharTypeInfo pti = (VarcharTypeInfo)other;
         return this.getLength() == pti.getLength();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.getLength();
   }

   public String toString() {
      return this.getQualifiedName();
   }
}
