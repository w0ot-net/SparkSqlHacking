package org.apache.avro.ipc.specific;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.ipc.Callback;
import org.apache.avro.ipc.Requestor;
import org.apache.avro.ipc.Transceiver;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class SpecificRequestor extends Requestor implements InvocationHandler {
   SpecificData data;

   public SpecificRequestor(Class iface, Transceiver transceiver) throws IOException {
      this(iface, transceiver, new SpecificData(iface.getClassLoader()));
   }

   protected SpecificRequestor(Protocol protocol, Transceiver transceiver) throws IOException {
      this(protocol, transceiver, SpecificData.get());
   }

   public SpecificRequestor(Class iface, Transceiver transceiver, SpecificData data) throws IOException {
      this(data.getProtocol(iface), transceiver, data);
   }

   public SpecificRequestor(Protocol protocol, Transceiver transceiver, SpecificData data) throws IOException {
      super(protocol, transceiver);
      this.data = data;
   }

   public SpecificData getSpecificData() {
      return this.data;
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      switch (method.getName()) {
         case "hashCode":
            return this.hashCode();
         case "equals":
            Object obj = args[0];
            return proxy == obj || obj != null && Proxy.isProxyClass(obj.getClass()) && this.equals(Proxy.getInvocationHandler(obj));
         case "toString":
            String protocol = "unknown";
            String remote = "unknown";
            Class<?>[] interfaces = proxy.getClass().getInterfaces();
            if (interfaces.length > 0) {
               try {
                  protocol = Class.forName(interfaces[0].getName()).getSimpleName();
               } catch (ClassNotFoundException var17) {
               }

               InvocationHandler handler = Proxy.getInvocationHandler(proxy);
               if (handler instanceof Requestor) {
                  try {
                     remote = ((Requestor)handler).getTransceiver().getRemoteName();
                  } catch (IOException var16) {
                  }
               }
            }

            return "Proxy[" + protocol + "," + remote + "]";
         default:
            try {
               Type[] parameterTypes = method.getParameterTypes();
               if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1] instanceof Class && Callback.class.isAssignableFrom((Class)parameterTypes[parameterTypes.length - 1])) {
                  Object[] finalArgs = Arrays.copyOf(args, args.length - 1);
                  Callback<?> callback = (Callback)args[args.length - 1];
                  this.request(method.getName(), finalArgs, callback);
                  return null;
               } else {
                  return this.request(method.getName(), args);
               }
            } catch (Exception var18) {
               Exception e = var18;

               for(Class exceptionClass : method.getExceptionTypes()) {
                  if (exceptionClass.isAssignableFrom(e.getClass())) {
                     throw e;
                  }
               }

               if (e instanceof RuntimeException) {
                  throw e;
               } else {
                  throw new AvroRuntimeException(e);
               }
            }
      }
   }

   protected DatumWriter getDatumWriter(Schema schema) {
      return new SpecificDatumWriter(schema, this.data);
   }

   /** @deprecated */
   @Deprecated
   protected DatumReader getDatumReader(Schema schema) {
      return this.getDatumReader(schema, schema);
   }

   protected DatumReader getDatumReader(Schema writer, Schema reader) {
      return new SpecificDatumReader(writer, reader, this.data);
   }

   public void writeRequest(Schema schema, Object request, Encoder out) throws IOException {
      Object[] args = request;
      int i = 0;

      for(Schema.Field param : schema.getFields()) {
         this.getDatumWriter(param.schema()).write(args[i++], out);
      }

   }

   public Object readResponse(Schema writer, Schema reader, Decoder in) throws IOException {
      return this.getDatumReader(writer, reader).read((Object)null, in);
   }

   public Exception readError(Schema writer, Schema reader, Decoder in) throws IOException {
      Object value = this.getDatumReader(writer, reader).read((Object)null, in);
      return (Exception)(value instanceof Exception ? (Exception)value : new AvroRuntimeException(value.toString()));
   }

   public static Object getClient(Class iface, Transceiver transceiver) throws IOException {
      return getClient(iface, transceiver, new SpecificData(iface.getClassLoader()));
   }

   public static Object getClient(Class iface, Transceiver transceiver, SpecificData data) throws IOException {
      Protocol protocol = data.getProtocol(iface);
      return Proxy.newProxyInstance(data.getClassLoader(), new Class[]{iface}, new SpecificRequestor(protocol, transceiver, data));
   }

   public static Object getClient(Class iface, SpecificRequestor requestor) throws IOException {
      return Proxy.newProxyInstance(requestor.data.getClassLoader(), new Class[]{iface}, requestor);
   }

   public static Protocol getRemote(Object proxy) throws IOException {
      return ((Requestor)Proxy.getInvocationHandler(proxy)).getRemote();
   }
}
