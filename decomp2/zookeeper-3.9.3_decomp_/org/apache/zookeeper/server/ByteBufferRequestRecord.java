package org.apache.zookeeper.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Supplier;
import org.apache.jute.Record;

public class ByteBufferRequestRecord implements RequestRecord {
   private final ByteBuffer request;
   private volatile Record record;

   public ByteBufferRequestRecord(ByteBuffer request) {
      this.request = request;
   }

   public Record readRecord(Supplier constructor) throws IOException {
      if (this.record != null) {
         return this.record;
      } else {
         this.record = (Record)constructor.get();
         this.request.rewind();
         ByteBufferInputStream.byteBuffer2Record(this.request, this.record);
         this.request.rewind();
         return this.record;
      }
   }

   public byte[] readBytes() {
      this.request.rewind();
      int len = this.request.remaining();
      byte[] b = new byte[len];
      this.request.get(b);
      this.request.rewind();
      return b;
   }

   public int limit() {
      return this.request.limit();
   }
}
