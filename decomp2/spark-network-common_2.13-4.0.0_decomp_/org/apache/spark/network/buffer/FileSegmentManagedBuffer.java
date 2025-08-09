package org.apache.spark.network.buffer;

import io.netty.channel.DefaultFileRegion;
import io.netty.handler.stream.ChunkedStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.StandardOpenOption;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.LimitedInputStream;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.io.ByteStreams;

public final class FileSegmentManagedBuffer extends ManagedBuffer {
   private final TransportConf conf;
   private final File file;
   private final long offset;
   private final long length;

   public FileSegmentManagedBuffer(TransportConf conf, File file, long offset, long length) {
      this.conf = conf;
      this.file = file;
      this.offset = offset;
      this.length = length;
   }

   public long size() {
      return this.length;
   }

   public ByteBuffer nioByteBuffer() throws IOException {
      FileChannel channel = null;

      ByteBuffer var14;
      try {
         channel = (new RandomAccessFile(this.file, "r")).getChannel();
         if (this.length >= (long)this.conf.memoryMapBytes()) {
            MappedByteBuffer e = channel.map(MapMode.READ_ONLY, this.offset, this.length);
            return e;
         }

         ByteBuffer buf = ByteBuffer.allocate((int)this.length);
         channel.position(this.offset);

         while(buf.remaining() != 0) {
            if (channel.read(buf) == -1) {
               throw new IOException(String.format("Reached EOF before filling buffer\noffset=%s\nfile=%s\nbuf.remaining=%s", this.offset, this.file.getAbsoluteFile(), buf.remaining()));
            }
         }

         buf.flip();
         var14 = buf;
      } catch (IOException e) {
         String errorMessage = "Error in reading " + String.valueOf(this);

         try {
            if (channel != null) {
               long size = channel.size();
               String var10000 = String.valueOf(this);
               errorMessage = "Error in reading " + var10000 + " (actual file length " + size + ")";
            }
         } catch (IOException var10) {
         }

         throw new IOException(errorMessage, e);
      } finally {
         JavaUtils.closeQuietly(channel);
      }

      return var14;
   }

   public InputStream createInputStream() throws IOException {
      FileInputStream is = null;
      boolean shouldClose = true;

      Object var12;
      try {
         is = new FileInputStream(this.file);
         ByteStreams.skipFully(is, this.offset);
         InputStream r = new LimitedInputStream(is, this.length);
         shouldClose = false;
         var12 = r;
      } catch (IOException e) {
         String errorMessage = "Error in reading " + String.valueOf(this);
         if (is != null) {
            long size = this.file.length();
            String var10000 = String.valueOf(this);
            errorMessage = "Error in reading " + var10000 + " (actual file length " + size + ")";
         }

         throw new IOException(errorMessage, e);
      } finally {
         if (shouldClose) {
            JavaUtils.closeQuietly(is);
         }

      }

      return (InputStream)var12;
   }

   public ManagedBuffer retain() {
      return this;
   }

   public ManagedBuffer release() {
      return this;
   }

   public Object convertToNetty() throws IOException {
      if (this.conf.lazyFileDescriptor()) {
         return new DefaultFileRegion(this.file, this.offset, this.length);
      } else {
         FileChannel fileChannel = FileChannel.open(this.file.toPath(), StandardOpenOption.READ);
         return new DefaultFileRegion(fileChannel, this.offset, this.length);
      }
   }

   public Object convertToNettyForSsl() throws IOException {
      return new ChunkedStream(this.createInputStream(), this.conf.sslShuffleChunkSize());
   }

   public File getFile() {
      return this.file;
   }

   public long getOffset() {
      return this.offset;
   }

   public long getLength() {
      return this.length;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("file", this.file).append("offset", this.offset).append("length", this.length).toString();
   }
}
