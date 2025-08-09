package io.fabric8.kubernetes.client.http;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;

public class BufferUtil {
   private BufferUtil() {
   }

   public static byte[] toArray(ByteBuffer buffer) {
      if (buffer.hasArray()) {
         byte[] array = buffer.array();
         int from = buffer.arrayOffset() + buffer.position();
         return Arrays.copyOfRange(array, from, from + buffer.remaining());
      } else {
         byte[] to = new byte[buffer.remaining()];
         buffer.slice().get(to);
         return to;
      }
   }

   public static byte[] toArray(Collection buffers) {
      byte[] ret = new byte[buffers.stream().mapToInt(Buffer::remaining).sum()];
      int offset = 0;

      for(ByteBuffer buffer : buffers) {
         buffer.slice().get(ret, offset, buffer.remaining());
         offset += buffer.remaining();
      }

      return ret;
   }

   public static ByteBuffer copy(ByteBuffer buffer) {
      if (buffer == null) {
         return null;
      } else {
         int position = buffer.position();
         ByteBuffer clone = ByteBuffer.allocate(buffer.remaining());
         clone.put(buffer);
         clone.flip();
         buffer.position(position);
         return clone;
      }
   }

   public static boolean isPlainText(ByteBuffer originalBuffer) {
      if (originalBuffer == null) {
         return false;
      } else {
         ByteBuffer buffer = originalBuffer.asReadOnlyBuffer();
         CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

         try {
            decoder.decode(buffer);
            return true;
         } catch (CharacterCodingException var4) {
            return false;
         }
      }
   }
}
