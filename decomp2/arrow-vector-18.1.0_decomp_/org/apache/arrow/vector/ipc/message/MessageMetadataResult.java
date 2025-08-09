package org.apache.arrow.vector.ipc.message;

import java.nio.ByteBuffer;
import org.apache.arrow.flatbuf.Message;

public class MessageMetadataResult {
   private final int messageLength;
   private final ByteBuffer messageBuffer;
   private final Message message;

   MessageMetadataResult(int messageLength, ByteBuffer messageBuffer, Message message) {
      this.messageLength = messageLength;
      this.messageBuffer = messageBuffer;
      this.message = message;
   }

   public static MessageMetadataResult create(ByteBuffer buffer, int messageLength) {
      return new MessageMetadataResult(messageLength, buffer, Message.getRootAsMessage(buffer));
   }

   public int getMessageLength() {
      return this.messageLength;
   }

   public ByteBuffer getMessageBuffer() {
      return this.messageBuffer;
   }

   public int bytesAfterMessage() {
      return this.message.getByteBuffer().remaining();
   }

   public byte headerType() {
      return this.message.headerType();
   }

   public boolean messageHasBody() {
      return this.message.bodyLength() > 0L;
   }

   public long getMessageBodyLength() {
      return this.message.bodyLength();
   }

   public Message getMessage() {
      return this.message;
   }
}
