package jodd.mutable;

public final class MutableByte extends Number implements Comparable, Cloneable {
   public byte value;

   public MutableByte() {
   }

   public MutableByte(byte value) {
      this.value = value;
   }

   public MutableByte(String value) {
      this.value = Byte.parseByte(value);
   }

   public MutableByte(Number number) {
      this.value = number.byteValue();
   }

   public byte getValue() {
      return this.value;
   }

   public void setValue(byte value) {
      this.value = value;
   }

   public void setValue(Number value) {
      this.value = value.byteValue();
   }

   public String toString() {
      return Integer.toString(this.value);
   }

   public int hashCode() {
      return this.value;
   }

   public boolean equals(Object obj) {
      if (obj != null) {
         if (obj instanceof Byte) {
            return this.value == (Byte)obj;
         }

         if (obj instanceof MutableByte) {
            return this.value == ((MutableByte)obj).value;
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

   public int compareTo(MutableByte other) {
      return this.value < other.value ? -1 : (this.value == other.value ? 0 : 1);
   }

   public MutableByte clone() {
      return new MutableByte(this.value);
   }
}
