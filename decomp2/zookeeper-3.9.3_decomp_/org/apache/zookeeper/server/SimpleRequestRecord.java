package org.apache.zookeeper.server;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.util.function.Supplier;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Record;

public class SimpleRequestRecord implements RequestRecord {
   private final Record record;
   private volatile byte[] bytes;

   public SimpleRequestRecord(Record record) {
      this.record = record;
   }

   public Record readRecord(Supplier constructor) {
      return this.record;
   }

   @SuppressFBWarnings({"EI_EXPOSE_REP"})
   public byte[] readBytes() {
      if (this.bytes != null) {
         return this.bytes;
      } else {
         try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] var3;
            try {
               BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
               this.record.serialize(boa, "request");
               this.bytes = baos.toByteArray();
               var3 = this.bytes;
            } catch (Throwable var5) {
               try {
                  baos.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }

               throw var5;
            }

            baos.close();
            return var3;
         } catch (IOException e) {
            throw new UncheckedIOException(e);
         }
      }
   }

   public int limit() {
      byte[] bytes = this.readBytes();
      return ByteBuffer.wrap(bytes).limit();
   }
}
