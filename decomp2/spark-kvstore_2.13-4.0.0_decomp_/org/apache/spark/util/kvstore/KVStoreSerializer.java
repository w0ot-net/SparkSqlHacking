package org.apache.spark.util.kvstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.spark.annotation.Private;

@Private
public class KVStoreSerializer {
   protected final ObjectMapper mapper = new ObjectMapper();

   public byte[] serialize(Object o) throws Exception {
      if (o instanceof String str) {
         return str.getBytes(StandardCharsets.UTF_8);
      } else {
         ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         GZIPOutputStream out = new GZIPOutputStream(bytes);

         try {
            this.mapper.writeValue(out, o);
         } catch (Throwable var8) {
            try {
               out.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         out.close();
         return bytes.toByteArray();
      }
   }

   public Object deserialize(byte[] data, Class klass) throws Exception {
      if (klass.equals(String.class)) {
         return new String(data, StandardCharsets.UTF_8);
      } else {
         GZIPInputStream in = new GZIPInputStream(new ByteArrayInputStream(data));

         Object var4;
         try {
            var4 = this.mapper.readValue(in, klass);
         } catch (Throwable var7) {
            try {
               in.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         in.close();
         return var4;
      }
   }

   final byte[] serialize(long value) {
      return String.valueOf(value).getBytes(StandardCharsets.UTF_8);
   }

   final long deserializeLong(byte[] data) {
      return Long.parseLong(new String(data, StandardCharsets.UTF_8));
   }
}
