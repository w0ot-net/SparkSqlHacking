package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.impl.CodecManager;
import io.vertx.core.impl.SerializableUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class SerializableCodec implements MessageCodec {
   private final CodecManager codecManager;

   public SerializableCodec(CodecManager codecManager) {
      this.codecManager = codecManager;
   }

   public void encodeToWire(Buffer buffer, Object o) {
      byte[] bytes = SerializableUtils.toBytes(o);
      buffer.appendInt(bytes.length);
      buffer.appendBytes(bytes);
   }

   public Object decodeFromWire(int pos, Buffer buffer) {
      int length = buffer.getInt(pos);
      pos += 4;
      byte[] bytes = buffer.getBytes(pos, pos + length);
      return SerializableUtils.fromBytes(bytes, (x$0) -> new CheckedClassNameObjectInputStream(x$0));
   }

   public Object transform(Object o) {
      return SerializableUtils.fromBytes(SerializableUtils.toBytes(o), ObjectInputStream::new);
   }

   public String name() {
      return "serializable";
   }

   public byte systemCodecID() {
      return 17;
   }

   private class CheckedClassNameObjectInputStream extends ObjectInputStream {
      CheckedClassNameObjectInputStream(InputStream in) throws IOException {
         super(in);
      }

      protected Class resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
         String name = desc.getName();
         if (!SerializableCodec.this.codecManager.acceptSerializable(name)) {
            throw new InvalidClassException("Class not allowed: " + name);
         } else {
            return super.resolveClass(desc);
         }
      }
   }
}
