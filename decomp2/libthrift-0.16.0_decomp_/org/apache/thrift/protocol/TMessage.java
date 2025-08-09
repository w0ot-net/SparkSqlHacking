package org.apache.thrift.protocol;

public final class TMessage {
   public final String name;
   public final byte type;
   public final int seqid;

   public TMessage() {
      this("", (byte)0, 0);
   }

   public TMessage(String n, byte t, int s) {
      this.name = n;
      this.type = t;
      this.seqid = s;
   }

   public String toString() {
      return "<TMessage name:'" + this.name + "' type: " + this.type + " seqid:" + this.seqid + ">";
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      result = 31 * result + this.seqid;
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
         TMessage other = (TMessage)obj;
         if (this.name == null) {
            if (other.name != null) {
               return false;
            }
         } else if (!this.name.equals(other.name)) {
            return false;
         }

         if (this.seqid != other.seqid) {
            return false;
         } else {
            return this.type == other.type;
         }
      }
   }
}
