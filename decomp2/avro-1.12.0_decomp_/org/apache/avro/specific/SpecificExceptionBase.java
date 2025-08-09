package org.apache.avro.specific;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.Schema;

public abstract class SpecificExceptionBase extends AvroRemoteException implements SpecificRecord, Externalizable {
   public SpecificExceptionBase() {
   }

   public SpecificExceptionBase(Throwable value) {
      super(value);
   }

   public SpecificExceptionBase(Object value) {
      super(value);
   }

   public SpecificExceptionBase(Object value, Throwable cause) {
      super(value, cause);
   }

   public abstract Schema getSchema();

   public abstract Object get(int field);

   public abstract void put(int field, Object value);

   public boolean equals(Object that) {
      if (that == this) {
         return true;
      } else if (!(that instanceof SpecificExceptionBase)) {
         return false;
      } else if (this.getClass() != that.getClass()) {
         return false;
      } else {
         return this.getSpecificData().compare(this, that, this.getSchema()) == 0;
      }
   }

   public int hashCode() {
      return SpecificData.get().hashCode(this, this.getSchema());
   }

   public abstract void writeExternal(ObjectOutput out) throws IOException;

   public abstract void readExternal(ObjectInput in) throws IOException;

   public SpecificData getSpecificData() {
      return SpecificData.get();
   }
}
