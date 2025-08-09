package org.apache.avro.ipc.reflect;

import java.io.IOException;
import java.lang.reflect.Proxy;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.ipc.Transceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

public class ReflectRequestor extends SpecificRequestor {
   public ReflectRequestor(Class iface, Transceiver transceiver) throws IOException {
      this(iface, transceiver, new ReflectData(iface.getClassLoader()));
   }

   protected ReflectRequestor(Protocol protocol, Transceiver transceiver) throws IOException {
      this(protocol, transceiver, ReflectData.get());
   }

   public ReflectRequestor(Class iface, Transceiver transceiver, ReflectData data) throws IOException {
      this(data.getProtocol(iface), transceiver, data);
   }

   public ReflectRequestor(Protocol protocol, Transceiver transceiver, ReflectData data) throws IOException {
      super((Protocol)protocol, transceiver, data);
   }

   public ReflectData getReflectData() {
      return (ReflectData)this.getSpecificData();
   }

   protected DatumWriter getDatumWriter(Schema schema) {
      return new ReflectDatumWriter(schema, this.getReflectData());
   }

   protected DatumReader getDatumReader(Schema writer, Schema reader) {
      return new ReflectDatumReader(writer, reader, this.getReflectData());
   }

   public static Object getClient(Class iface, Transceiver transceiver) throws IOException {
      return getClient(iface, transceiver, new ReflectData(iface.getClassLoader()));
   }

   public static Object getClient(Class iface, Transceiver transceiver, ReflectData reflectData) throws IOException {
      Protocol protocol = reflectData.getProtocol(iface);
      return Proxy.newProxyInstance(reflectData.getClassLoader(), new Class[]{iface}, new ReflectRequestor(protocol, transceiver, reflectData));
   }

   public static Object getClient(Class iface, ReflectRequestor rreq) throws IOException {
      return Proxy.newProxyInstance(rreq.getReflectData().getClassLoader(), new Class[]{iface}, rreq);
   }
}
