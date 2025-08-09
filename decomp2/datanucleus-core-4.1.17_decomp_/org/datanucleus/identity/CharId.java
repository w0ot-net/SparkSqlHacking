package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CharId extends SingleFieldId {
   private char key;

   public CharId(Class pcClass, char key) {
      super(pcClass);
      this.key = key;
      this.hashCode = this.targetClassName.hashCode() ^ key;
   }

   public CharId(Class pcClass, Character key) {
      this(pcClass, key != null ? key : '\u0000');
      this.assertKeyNotNull(key);
   }

   public CharId(Class pcClass, String str) {
      this(pcClass, str.charAt(0));
      this.assertKeyNotNull(str);
      if (str.length() != 1) {
         throw new IllegalArgumentException("Cannot have a char as id when string is of length " + str.length());
      }
   }

   public CharId() {
   }

   public char getKey() {
      return this.key;
   }

   public Character getKeyAsObject() {
      return this.key;
   }

   public String toString() {
      return String.valueOf(this.key);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         CharId other = (CharId)obj;
         return this.key == other.key;
      }
   }

   public int compareTo(Object o) {
      if (o instanceof CharId) {
         CharId other = (CharId)o;
         int result = super.compare(other);
         return result == 0 ? this.key - other.key : result;
      } else if (o == null) {
         throw new ClassCastException("object is null");
      } else {
         throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
      }
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      super.writeExternal(out);
      out.writeChar(this.key);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      super.readExternal(in);
      this.key = in.readChar();
   }
}
