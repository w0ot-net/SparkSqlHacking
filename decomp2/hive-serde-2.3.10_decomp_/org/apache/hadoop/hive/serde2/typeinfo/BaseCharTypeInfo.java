package org.apache.hadoop.hive.serde2.typeinfo;

public abstract class BaseCharTypeInfo extends PrimitiveTypeInfo {
   private static final long serialVersionUID = 1L;
   private int length;

   public BaseCharTypeInfo() {
   }

   public BaseCharTypeInfo(String typeName) {
      super(typeName);
   }

   public BaseCharTypeInfo(String typeName, int length) {
      super(typeName);
      this.length = length;
   }

   public int getLength() {
      return this.length;
   }

   public void setLength(int length) {
      this.length = length;
   }

   public String getQualifiedName() {
      return getQualifiedName(this.typeName, this.length);
   }

   public static String getQualifiedName(String typeName, int length) {
      StringBuilder sb = new StringBuilder(typeName);
      sb.append("(");
      sb.append(length);
      sb.append(")");
      return sb.toString();
   }

   public void setTypeName(String typeName) {
   }
}
