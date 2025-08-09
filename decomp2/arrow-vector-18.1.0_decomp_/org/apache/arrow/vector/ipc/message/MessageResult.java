package org.apache.arrow.vector.ipc.message;

import org.apache.arrow.flatbuf.Message;
import org.apache.arrow.memory.ArrowBuf;

public class MessageResult {
   private final Message message;
   private final ArrowBuf bodyBuffer;

   MessageResult(Message message, ArrowBuf bodyBuffer) {
      this.message = message;
      this.bodyBuffer = bodyBuffer;
   }

   public Message getMessage() {
      return this.message;
   }

   public ArrowBuf getBodyBuffer() {
      return this.bodyBuffer;
   }
}
