package shaded.parquet.org.apache.thrift.protocol;

public class TField {
   public final String name;
   public final byte type;
   public final short id;

   public TField() {
      this("", (byte)0, (short)0);
   }

   public TField(String n, byte t, short i) {
      this.name = n;
      this.type = t;
      this.id = i;
   }

   public String toString() {
      return "<TField name:'" + this.name + "' type:" + this.type + " field-id:" + this.id + ">";
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + this.id;
      result = 31 * result + this.type;
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         TField otherField = (TField)obj;
         return this.type == otherField.type && this.id == otherField.id;
      }
   }
}
