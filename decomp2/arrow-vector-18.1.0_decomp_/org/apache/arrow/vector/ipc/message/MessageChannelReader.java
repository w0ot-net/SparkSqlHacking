package org.apache.arrow.vector.ipc.message;

import java.io.IOException;
import org.apache.arrow.flatbuf.Message;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.ipc.ReadChannel;

public class MessageChannelReader implements AutoCloseable {
   protected ReadChannel in;
   protected BufferAllocator allocator;

   public MessageChannelReader(ReadChannel in, BufferAllocator allocator) {
      this.in = in;
      this.allocator = allocator;
   }

   public MessageResult readNext() throws IOException {
      MessageMetadataResult result = MessageSerializer.readMessage(this.in);
      if (result == null) {
         return null;
      } else {
         Message message = result.getMessage();
         ArrowBuf bodyBuffer = null;
         if (result.messageHasBody()) {
            long bodyLength = result.getMessageBodyLength();
            bodyBuffer = MessageSerializer.readMessageBody(this.in, bodyLength, this.allocator);
         }

         return new MessageResult(message, bodyBuffer);
      }
   }

   public long bytesRead() {
      return this.in.bytesRead();
   }

   public void close() throws IOException {
      this.in.close();
   }
}
