package jodd.mutable;

public final class MutableFloat extends Number implements Comparable, Cloneable {
   public float value;

   public MutableFloat() {
   }

   public MutableFloat(float value) {
      this.value = value;
   }

   public MutableFloat(String value) {
      this.value = Float.parseFloat(value);
   }

   public MutableFloat(Number number) {
      this.value = number.floatValue();
   }

   public float getValue() {
      return this.value;
   }

   public void setValue(float value) {
      this.value = value;
   }

   public void setValue(Number value) {
      this.value = value.floatValue();
   }

   public String toString() {
      return Float.toString(this.value);
   }

   public int hashCode() {
      return Float.floatToIntBits(this.value);
   }

   public boolean equals(Object obj) {
      if (obj != null) {
         if (obj instanceof Float) {
            return Float.floatToIntBits(this.value) == Float.floatToIntBits((Float)obj);
         }

         if (obj instanceof MutableFloat) {
            return Float.floatToIntBits(this.value) == Float.floatToIntBits(((MutableFloat)obj).value);
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
      return this.value;
   }

   public double doubleValue() {
      return (double)this.value;
   }

   public boolean isNaN() {
      return Float.isNaN(this.value);
   }

   public boolean isInfinite() {
      return Float.isInfinite(this.value);
   }

   public int compareTo(MutableFloat other) {
      return Float.compare(this.value, other.value);
   }

   public MutableFloat clone() {
      return new MutableFloat(this.value);
   }
}
