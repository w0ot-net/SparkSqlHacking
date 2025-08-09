package org.apache.avro.ipc.reflect;

import java.io.IOException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

public class ReflectResponder extends SpecificResponder {
   public ReflectResponder(Class iface, Object impl) {
      this(iface, impl, new ReflectData(impl.getClass().getClassLoader()));
   }

   public ReflectResponder(Protocol protocol, Object impl) {
      this(protocol, impl, new ReflectData(impl.getClass().getClassLoader()));
   }

   public ReflectResponder(Class iface, Object impl, ReflectData data) {
      this(data.getProtocol(iface), impl, data);
   }

   public ReflectResponder(Protocol protocol, Object impl, ReflectData data) {
      super((Protocol)protocol, impl, data);
   }

   public ReflectData getReflectData() {
      return (ReflectData)this.getSpecificData();
   }

   protected DatumWriter getDatumWriter(Schema schema) {
      return new ReflectDatumWriter(schema, this.getReflectData());
   }

   protected DatumReader getDatumReader(Schema actual, Schema expected) {
      return new ReflectDatumReader(actual, expected, this.getReflectData());
   }

   public void writeError(Schema schema, Object error, Encoder out) throws IOException {
      if (error instanceof CharSequence) {
         error = error.toString();
      }

      super.writeError(schema, error, out);
   }
}
