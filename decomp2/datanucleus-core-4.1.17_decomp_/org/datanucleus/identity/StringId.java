package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class StringId extends SingleFieldId {
   private String key;

   public StringId(Class pcClass, String key) {
      super(pcClass);
      this.key = key;
      this.hashCode = this.targetClassName.hashCode() ^ key.hashCode();
   }

   public StringId() {
   }

   public String getKey() {
      return this.key;
   }

   public String getKeyAsObject() {
      return this.key;
   }

   public String toString() {
      return this.key;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         StringId other = (StringId)obj;
         return this.key.equals(other.key);
      }
   }

   public int compareTo(Object o) {
      if (o instanceof StringId) {
         StringId other = (StringId)o;
         int result = super.compare(other);
         return result == 0 ? this.key.compareTo(other.key) : result;
      } else if (o == null) {
         throw new ClassCastException("object is null");
      } else {
         throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
      }
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      super.writeExternal(out);
      out.writeObject(this.key);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      super.readExternal(in);
      this.key = (String)in.readObject();
   }
}
