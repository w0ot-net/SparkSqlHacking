package jodd.mutable;

public final class MutableDouble extends Number implements Comparable, Cloneable {
   public double value;

   public MutableDouble() {
   }

   public MutableDouble(double value) {
      this.value = value;
   }

   public MutableDouble(String value) {
      this.value = Double.parseDouble(value);
   }

   public MutableDouble(Number number) {
      this.value = number.doubleValue();
   }

   public double getValue() {
      return this.value;
   }

   public void setValue(double value) {
      this.value = value;
   }

   public void setValue(Number value) {
      this.value = value.doubleValue();
   }

   public String toString() {
      return Double.toString(this.value);
   }

   public int hashCode() {
      long bits = Double.doubleToLongBits(this.value);
      return (int)(bits ^ bits >>> 32);
   }

   public boolean equals(Object obj) {
      if (obj != null) {
         if (obj instanceof Double) {
            return Double.doubleToLongBits(this.value) == Double.doubleToLongBits((Double)obj);
         }

         if (obj instanceof MutableDouble) {
            return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(((MutableDouble)obj).value);
         }
      }

      return false;
   }

   public int intValue() {
      return (int)this.value;
   }

   public long longValue() {
      return (long)this.value;
   }

   public float floatValue() {
      return (float)this.value;
   }

   public double doubleValue() {
      return this.value;
   }

   public boolean isNaN() {
      return Double.isNaN(this.value);
   }

   public boolean isInfinite() {
      return Double.isInfinite(this.value);
   }

   public int compareTo(MutableDouble other) {
      return Double.compare(this.value, other.value);
   }

   public MutableDouble clone() {
      return new MutableDouble(this.value);
   }
}
