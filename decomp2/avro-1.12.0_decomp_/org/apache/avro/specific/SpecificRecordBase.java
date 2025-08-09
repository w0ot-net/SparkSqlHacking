package org.apache.avro.specific;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Conversion;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.ResolvingDecoder;

public abstract class SpecificRecordBase implements SpecificRecord, Comparable, GenericRecord, Externalizable {
   public SpecificData getSpecificData() {
      return SpecificData.get();
   }

   public Conversion getConversion(int field) {
      return null;
   }

   public void put(String fieldName, Object value) {
      Schema.Field field = this.getSchema().getField(fieldName);
      if (field == null) {
         throw new AvroRuntimeException("Not a valid schema field: " + fieldName);
      } else {
         this.put(field.pos(), value);
      }
   }

   public Object get(String fieldName) {
      Schema.Field field = this.getSchema().getField(fieldName);
      if (field == null) {
         throw new AvroRuntimeException("Not a valid schema field: " + fieldName);
      } else {
         return this.get(field.pos());
      }
   }

   public Conversion getConversion(String fieldName) {
      return this.getConversion(this.getSchema().getField(fieldName).pos());
   }

   public boolean equals(Object that) {
      if (that == this) {
         return true;
      } else if (!(that instanceof SpecificRecord)) {
         return false;
      } else if (this.getClass() != that.getClass()) {
         return false;
      } else {
         return this.getSpecificData().compare(this, that, this.getSchema(), true) == 0;
      }
   }

   public int hashCode() {
      return this.getSpecificData().hashCode(this, this.getSchema());
   }

   public int compareTo(SpecificRecord that) {
      return this.getSpecificData().compare(this, that, this.getSchema());
   }

   public String toString() {
      return this.getSpecificData().toString(this);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      (new SpecificDatumWriter(this.getSchema())).write(this, SpecificData.getEncoder(out));
   }

   public void readExternal(ObjectInput in) throws IOException {
      (new SpecificDatumReader(this.getSchema())).read(this, SpecificData.getDecoder(in));
   }

   protected boolean hasCustomCoders() {
      return false;
   }

   public void customEncode(Encoder out) throws IOException {
      throw new UnsupportedOperationException();
   }

   public void customDecode(ResolvingDecoder in) throws IOException {
      throw new UnsupportedOperationException();
   }
}
