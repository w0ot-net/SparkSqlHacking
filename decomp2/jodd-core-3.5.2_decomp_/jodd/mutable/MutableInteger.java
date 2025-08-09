package jodd.mutable;

public final class MutableInteger extends Number implements Comparable, Cloneable {
   public int value;

   public MutableInteger() {
   }

   public MutableInteger(int value) {
      this.value = value;
   }

   public MutableInteger(String value) {
      this.value = Integer.parseInt(value);
   }

   public MutableInteger(Number number) {
      this.value = number.intValue();
   }

   public int getValue() {
      return this.value;
   }

   public void setValue(int value) {
      this.value = value;
   }

   public void setValue(Number value) {
      this.value = value.intValue();
   }

   public String toString() {
      return Integer.toString(this.value);
   }

   public int hashCode() {
      return this.value;
   }

   public boolean equals(Object obj) {
      if (obj != null) {
         if (obj instanceof Integer) {
            return this.value == (Integer)obj;
         }

         if (obj instanceof MutableInteger) {
            return this.value == ((MutableInteger)obj).value;
         }
      }

      return false;
   }

   public int intValue() {
      return this.value;
   }

   public long longValue() {
      return (long)this.value;
   }

   public float floatValue() {
      return (float)this.value;
   }

   public double doubleValue() {
      return (double)this.value;
   }

   public int compareTo(MutableInteger other) {
      return this.value < other.value ? -1 : (this.value == other.value ? 0 : 1);
   }

   public MutableInteger clone() {
      return new MutableInteger(this.value);
   }
}
