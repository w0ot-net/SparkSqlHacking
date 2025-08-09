package org.apache.avro.ipc.generic;

import java.io.IOException;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.ipc.Requestor;
import org.apache.avro.ipc.Transceiver;

public class GenericRequestor extends Requestor {
   GenericData data;

   public GenericRequestor(Protocol protocol, Transceiver transceiver) throws IOException {
      this(protocol, transceiver, GenericData.get());
   }

   public GenericRequestor(Protocol protocol, Transceiver transceiver, GenericData data) throws IOException {
      super(protocol, transceiver);
      this.data = data;
   }

   public GenericData getGenericData() {
      return this.data;
   }

   public void writeRequest(Schema schema, Object request, Encoder out) throws IOException {
      (new GenericDatumWriter(schema, this.data)).write(request, out);
   }

   public Object readResponse(Schema writer, Schema reader, Decoder in) throws IOException {
      return (new GenericDatumReader(writer, reader, this.data)).read((Object)null, in);
   }

   public Exception readError(Schema writer, Schema reader, Decoder in) throws IOException {
      Object error = (new GenericDatumReader(writer, reader, this.data)).read((Object)null, in);
      return (Exception)(error instanceof CharSequence ? new AvroRuntimeException(error.toString()) : new AvroRemoteException(error));
   }
}
