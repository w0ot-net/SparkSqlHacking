package org.apache.zookeeper.server;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Record;

public class ByteBufferOutputStream extends OutputStream {
   private final ByteBuffer bb;

   public ByteBufferOutputStream(ByteBuffer bb) {
      this.bb = bb;
   }

   public void write(int b) throws IOException {
      this.bb.put((byte)b);
   }

   public void write(@Nonnull byte[] b) throws IOException {
      this.bb.put(b);
   }

   public void write(@Nonnull byte[] b, int off, int len) throws IOException {
      this.bb.put(b, off, len);
   }

   public static void record2ByteBuffer(Record record, ByteBuffer bb) throws IOException {
      BinaryOutputArchive oa = BinaryOutputArchive.getArchive(new ByteBufferOutputStream(bb));
      record.serialize(oa, "request");
   }
}
