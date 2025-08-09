package org.apache.spark.network.shuffle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.util.TransportConf;

public class SimpleDownloadFile implements DownloadFile {
   private final File file;
   private final TransportConf transportConf;

   public SimpleDownloadFile(File file, TransportConf transportConf) {
      this.file = file;
      this.transportConf = transportConf;
   }

   public boolean delete() {
      return this.file.delete();
   }

   public DownloadFileWritableChannel openForWriting() throws IOException {
      return new SimpleDownloadWritableChannel();
   }

   public String path() {
      return this.file.getAbsolutePath();
   }

   private class SimpleDownloadWritableChannel implements DownloadFileWritableChannel {
      private final WritableByteChannel channel;

      SimpleDownloadWritableChannel() throws FileNotFoundException {
         this.channel = Channels.newChannel(new FileOutputStream(SimpleDownloadFile.this.file));
      }

      public ManagedBuffer closeAndRead() throws IOException {
         this.channel.close();
         return new FileSegmentManagedBuffer(SimpleDownloadFile.this.transportConf, SimpleDownloadFile.this.file, 0L, SimpleDownloadFile.this.file.length());
      }

      public int write(ByteBuffer src) throws IOException {
         return this.channel.write(src);
      }

      public boolean isOpen() {
         return this.channel.isOpen();
      }

      public void close() throws IOException {
         this.channel.close();
      }
   }
}
