package org.datanucleus.identity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.datanucleus.exceptions.NucleusUserException;

public abstract class SingleFieldId implements Externalizable, Comparable {
   protected static final String STRING_DELIMITER = ":";
   protected String targetClassName;
   protected int hashCode;

   protected SingleFieldId(Class pcClass) {
      if (pcClass == null) {
         throw new NullPointerException();
      } else {
         this.targetClassName = pcClass.getName();
      }
   }

   public SingleFieldId() {
   }

   protected void assertKeyNotNull(Object key) {
      if (key == null) {
         throw new NucleusUserException("Cannot have an identity with null key");
      }
   }

   public String getTargetClassName() {
      return this.targetClassName;
   }

   public abstract Object getKeyAsObject();

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         SingleFieldId other = (SingleFieldId)obj;
         return this.targetClassName.equals(other.targetClassName);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.hashCode;
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeObject(this.targetClassName);
      out.writeInt(this.hashCode);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.targetClassName = (String)in.readObject();
      this.hashCode = in.readInt();
   }

   protected int compare(SingleFieldId o) {
      return this.targetClassName.compareTo(o.targetClassName);
   }
}
