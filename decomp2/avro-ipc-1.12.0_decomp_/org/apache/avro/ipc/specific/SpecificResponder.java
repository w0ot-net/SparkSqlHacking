package org.apache.avro.ipc.specific;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.ipc.generic.GenericResponder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class SpecificResponder extends GenericResponder {
   private Object impl;

   public SpecificResponder(Class iface, Object impl) {
      this(iface, impl, new SpecificData(impl.getClass().getClassLoader()));
   }

   public SpecificResponder(Protocol protocol, Object impl) {
      this(protocol, impl, new SpecificData(impl.getClass().getClassLoader()));
   }

   public SpecificResponder(Class iface, Object impl, SpecificData data) {
      this(data.getProtocol(iface), impl, data);
   }

   public SpecificResponder(Protocol protocol, Object impl, SpecificData data) {
      super(protocol, data);
      this.impl = impl;
   }

   public SpecificData getSpecificData() {
      return (SpecificData)this.getGenericData();
   }

   protected DatumWriter getDatumWriter(Schema schema) {
      return new SpecificDatumWriter(schema, this.getSpecificData());
   }

   protected DatumReader getDatumReader(Schema actual, Schema expected) {
      return new SpecificDatumReader(actual, expected, this.getSpecificData());
   }

   public void writeError(Schema schema, Object error, Encoder out) throws IOException {
      this.getDatumWriter(schema).write(error, out);
   }

   public Object respond(Protocol.Message message, Object request) throws Exception {
      int numParams = message.getRequest().getFields().size();
      Object[] params = new Object[numParams];
      Class[] paramTypes = new Class[numParams];
      int i = 0;

      try {
         for(Schema.Field param : message.getRequest().getFields()) {
            params[i] = ((GenericRecord)request).get(param.name());
            paramTypes[i] = this.getSpecificData().getClass(param.schema());
            ++i;
         }

         Method method = this.impl.getClass().getMethod(message.getName(), paramTypes);
         method.setAccessible(true);
         return method.invoke(this.impl, params);
      } catch (InvocationTargetException e) {
         Throwable error = e.getTargetException();
         if (error instanceof Exception) {
            throw (Exception)error;
         } else {
            throw new AvroRuntimeException(error);
         }
      } catch (IllegalAccessException | NoSuchMethodException e) {
         throw new AvroRuntimeException(e);
      }
   }
}
