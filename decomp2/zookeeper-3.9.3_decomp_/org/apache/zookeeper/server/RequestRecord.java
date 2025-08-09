package org.apache.zookeeper.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Supplier;
import org.apache.jute.Record;

public interface RequestRecord {
   static RequestRecord fromBytes(ByteBuffer buffer) {
      return new ByteBufferRequestRecord(buffer);
   }

   static RequestRecord fromBytes(byte[] bytes) {
      return fromBytes(ByteBuffer.wrap(bytes));
   }

   static RequestRecord fromRecord(Record record) {
      return new SimpleRequestRecord(record);
   }

   Record readRecord(Supplier var1) throws IOException;

   byte[] readBytes();

   int limit();
}
