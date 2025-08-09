package org.datanucleus.identity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.datanucleus.enhancer.EnhancementHelper;
import org.datanucleus.exceptions.NucleusUserException;

public class ObjectId extends SingleFieldId {
   private Object key;

   public ObjectId(Class pcClass, Object param) {
      super(pcClass);
      this.assertKeyNotNull(param);
      String paramString = null;
      String keyString = null;
      String keyClassName = null;
      if (param instanceof String) {
         paramString = (String)param;
         if (paramString.length() < 3) {
            throw new NucleusUserException("ObjectId constructor from String was expecting a longer string than " + paramString);
         }

         int indexOfDelimiter = paramString.indexOf(":");
         if (indexOfDelimiter < 0) {
            throw new NucleusUserException("ObjectId constructor from String was expecting a delimiter of : but not present!");
         }

         keyString = paramString.substring(indexOfDelimiter + 1);
         keyClassName = paramString.substring(0, indexOfDelimiter);
         this.key = EnhancementHelper.construct(keyClassName, keyString);
      } else {
         this.key = param;
      }

      this.hashCode = this.targetClassName.hashCode() ^ this.key.hashCode();
   }

   public ObjectId() {
   }

   public Object getKey() {
      return this.key;
   }

   public Object getKeyAsObject() {
      return this.key;
   }

   public String toString() {
      return this.key.getClass().getName() + ":" + this.key.toString();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else {
         return !super.equals(obj) ? false : this.key.equals(((ObjectId)obj).key);
      }
   }

   public int compareTo(Object o) {
      if (o instanceof ObjectId) {
         ObjectId other = (ObjectId)o;
         int result = super.compare(other);
         if (result == 0) {
            if (other.key instanceof Comparable && this.key instanceof Comparable) {
               return ((Comparable)this.key).compareTo(other.key);
            } else {
               throw new ClassCastException("The key class (" + this.key.getClass().getName() + ") does not implement Comparable");
            }
         } else {
            return result;
         }
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
      this.key = in.readObject();
   }
}
