package org.apache.avro.ipc.generic;

import java.io.IOException;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.ipc.Responder;

public abstract class GenericResponder extends Responder {
   private GenericData data;

   public GenericResponder(Protocol local) {
      this(local, GenericData.get());
   }

   public GenericResponder(Protocol local, GenericData data) {
      super(local);
      this.data = data;
   }

   public GenericData getGenericData() {
      return this.data;
   }

   protected DatumWriter getDatumWriter(Schema schema) {
      return new GenericDatumWriter(schema, this.data);
   }

   protected DatumReader getDatumReader(Schema actual, Schema expected) {
      return new GenericDatumReader(actual, expected, this.data);
   }

   public Object readRequest(Schema actual, Schema expected, Decoder in) throws IOException {
      return this.getDatumReader(actual, expected).read((Object)null, in);
   }

   public void writeResponse(Schema schema, Object response, Encoder out) throws IOException {
      this.getDatumWriter(schema).write(response, out);
   }

   public void writeError(Schema schema, Object error, Encoder out) throws IOException {
      if (error instanceof AvroRemoteException) {
         error = ((AvroRemoteException)error).getValue();
      }

      this.getDatumWriter(schema).write(error, out);
   }
}
