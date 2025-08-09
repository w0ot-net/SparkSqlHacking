package org.apache.arrow.vector.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.ipc.ReadChannel;
import org.apache.arrow.vector.ipc.WriteChannel;
import org.apache.arrow.vector.ipc.message.MessageChannelReader;
import org.apache.arrow.vector.ipc.message.MessageResult;
import org.apache.arrow.vector.ipc.message.MessageSerializer;
import org.apache.arrow.vector.types.pojo.Schema;

public class SchemaUtility {
   private SchemaUtility() {
   }

   public static Schema deserialize(byte[] bytes, BufferAllocator allocator) throws IOException {
      try (MessageChannelReader schemaReader = new MessageChannelReader(new ReadChannel(new ByteArrayReadableSeekableByteChannel(bytes)), allocator)) {
         MessageResult result = schemaReader.readNext();
         if (result == null) {
            throw new IOException("Unexpected end of input. Missing schema.");
         } else {
            return MessageSerializer.deserializeSchema(result.getMessage());
         }
      }
   }

   public static byte[] serialize(Schema schema) throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      MessageSerializer.serialize(new WriteChannel(Channels.newChannel(out)), schema);
      return out.toByteArray();
   }
}
