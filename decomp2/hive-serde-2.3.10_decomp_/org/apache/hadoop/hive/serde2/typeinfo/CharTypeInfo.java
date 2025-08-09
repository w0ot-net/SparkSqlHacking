package org.apache.hadoop.hive.serde2.typeinfo;

public class CharTypeInfo extends BaseCharTypeInfo {
   private static final long serialVersionUID = 1L;

   public CharTypeInfo() {
      super("char");
   }

   public CharTypeInfo(int length) {
      super("char", length);
      BaseCharUtils.validateCharParameter(length);
   }

   public String getTypeName() {
      return this.getQualifiedName();
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         CharTypeInfo pti = (CharTypeInfo)other;
         return this.typeName.equals(pti.typeName) && this.getLength() == pti.getLength();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.getQualifiedName().hashCode();
   }

   public String toString() {
      return this.getQualifiedName();
   }
}
