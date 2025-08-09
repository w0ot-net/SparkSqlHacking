package io.vertx.core.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import java.lang.reflect.InvocationTargetException;

public class ClusterSerializableUtils {
   public static ClusterSerializable copy(ClusterSerializable obj) {
      Buffer buffer = Buffer.buffer();
      obj.writeToBuffer(buffer);

      try {
         ClusterSerializable copy = (ClusterSerializable)obj.getClass().getConstructor().newInstance();
         copy.readFromBuffer(0, buffer);
         return copy;
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
         throw new RuntimeException(e);
      }
   }

   private ClusterSerializableUtils() {
   }
}
