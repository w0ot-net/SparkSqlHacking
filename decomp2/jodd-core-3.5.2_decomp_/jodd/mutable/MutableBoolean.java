package jodd.mutable;

public final class MutableBoolean implements Comparable, Cloneable {
   public boolean value;

   public MutableBoolean() {
   }

   public MutableBoolean(boolean value) {
      this.value = value;
   }

   public MutableBoolean(String value) {
      this.value = Boolean.valueOf(value);
   }

   public MutableBoolean(Boolean value) {
      this.value = value;
   }

   public MutableBoolean(Number number) {
      this.value = number.intValue() != 0;
   }

   public boolean getValue() {
      return this.value;
   }

   public void setValue(boolean value) {
      this.value = value;
   }

   public void setValue(Boolean value) {
      this.value = value;
   }

   public String toString() {
      return Boolean.toString(this.value);
   }

   public int hashCode() {
      return this.value ? 1231 : 1237;
   }

   public boolean equals(Object obj) {
      if (obj != null) {
         if (obj instanceof Boolean) {
            return this.value == (Boolean)obj;
         }

         if (obj instanceof MutableBoolean) {
            return this.value == ((MutableBoolean)obj).value;
         }
      }

      return false;
   }

   public int compareTo(MutableBoolean o) {
      return this.value == o.value ? 0 : (!this.value ? -1 : 1);
   }

   public MutableBoolean clone() {
      return new MutableBoolean(this.value);
   }
}
