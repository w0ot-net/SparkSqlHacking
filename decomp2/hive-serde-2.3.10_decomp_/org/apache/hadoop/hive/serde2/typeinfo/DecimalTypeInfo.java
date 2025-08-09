package org.apache.hadoop.hive.serde2.typeinfo;

public class DecimalTypeInfo extends PrimitiveTypeInfo {
   private static final long serialVersionUID = 1L;
   private int precision;
   private int scale;

   public DecimalTypeInfo() {
      super("decimal");
   }

   public DecimalTypeInfo(int precision, int scale) {
      super("decimal");
      HiveDecimalUtils.validateParameter(precision, scale);
      this.precision = precision;
      this.scale = scale;
   }

   public String getTypeName() {
      return this.getQualifiedName();
   }

   public void setTypeName(String typeName) {
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         DecimalTypeInfo dti = (DecimalTypeInfo)other;
         return this.precision() == dti.precision() && this.scale() == dti.scale();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 31 * (17 + this.precision) + this.scale;
   }

   public String toString() {
      return this.getQualifiedName();
   }

   public String getQualifiedName() {
      return getQualifiedName(this.precision, this.scale);
   }

   public static String getQualifiedName(int precision, int scale) {
      StringBuilder sb = new StringBuilder("decimal");
      sb.append("(");
      sb.append(precision);
      sb.append(",");
      sb.append(scale);
      sb.append(")");
      return sb.toString();
   }

   public int precision() {
      return this.precision;
   }

   public int scale() {
      return this.scale;
   }

   public boolean accept(TypeInfo other) {
      if (other != null && this.getClass() == other.getClass()) {
         DecimalTypeInfo dti = (DecimalTypeInfo)other;
         return this.precision() - this.scale() >= dti.precision() - dti.scale();
      } else {
         return false;
      }
   }

   public int getPrecision() {
      return this.precision;
   }

   public void setPrecision(int precision) {
      this.precision = precision;
   }

   public int getScale() {
      return this.scale;
   }

   public void setScale(int scale) {
      this.scale = scale;
   }
}
