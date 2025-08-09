package io.vertx.core.eventbus.impl.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.impl.CodecManager;
import io.vertx.core.impl.ClusterSerializableUtils;
import io.vertx.core.shareddata.impl.ClusterSerializable;

public class ClusterSerializableCodec implements MessageCodec {
   private final CodecManager codecManager;

   public ClusterSerializableCodec(CodecManager codecManager) {
      this.codecManager = codecManager;
   }

   public void encodeToWire(Buffer buffer, ClusterSerializable obj) {
      byte[] classNameBytes = obj.getClass().getName().getBytes(CharsetUtil.UTF_8);
      buffer.appendInt(classNameBytes.length).appendBytes(classNameBytes);
      obj.writeToBuffer(buffer);
   }

   public ClusterSerializable decodeFromWire(int pos, Buffer buffer) {
      int len = buffer.getInt(pos);
      pos += 4;
      byte[] classNameBytes = buffer.getBytes(pos, pos + len);
      String className = new String(classNameBytes, CharsetUtil.UTF_8);
      if (!this.codecManager.acceptClusterSerializable(className)) {
         throw new RuntimeException("Class not allowed: " + className);
      } else {
         pos += len;

         ClusterSerializable clusterSerializable;
         try {
            Class<?> clazz = getClassLoader().loadClass(className);
            clusterSerializable = (ClusterSerializable)clazz.newInstance();
         } catch (Exception e) {
            throw new RuntimeException(e);
         }

         clusterSerializable.readFromBuffer(pos, buffer);
         return clusterSerializable;
      }
   }

   private static ClassLoader getClassLoader() {
      ClassLoader tccl = Thread.currentThread().getContextClassLoader();
      return tccl != null ? tccl : ClusterSerializableCodec.class.getClassLoader();
   }

   public ClusterSerializable transform(ClusterSerializable obj) {
      return ClusterSerializableUtils.copy(obj);
   }

   public String name() {
      return "clusterserializable";
   }

   public byte systemCodecID() {
      return 16;
   }
}
