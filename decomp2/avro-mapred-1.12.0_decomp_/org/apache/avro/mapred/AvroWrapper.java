package org.apache.avro.mapred;

public class AvroWrapper {
   private Object datum;

   public AvroWrapper() {
      this((Object)null);
   }

   public AvroWrapper(Object datum) {
      this.datum = datum;
   }

   public Object datum() {
      return this.datum;
   }

   public void datum(Object datum) {
      this.datum = datum;
   }

   public int hashCode() {
      return this.datum == null ? 0 : this.datum.hashCode();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         AvroWrapper that = (AvroWrapper)obj;
         if (this.datum == null) {
            return that.datum == null;
         } else {
            return this.datum.equals(that.datum);
         }
      }
   }

   public String toString() {
      return this.datum.toString();
   }
}
