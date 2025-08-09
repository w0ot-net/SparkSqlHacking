package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.internal.ObjectUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public abstract class AbstractMemoryHttpData extends AbstractHttpData {
   private ByteBuf byteBuf;
   private int chunkPosition;

   protected AbstractMemoryHttpData(String name, Charset charset, long size) {
      super(name, charset, size);
      this.byteBuf = Unpooled.EMPTY_BUFFER;
   }

   public void setContent(ByteBuf buffer) throws IOException {
      ObjectUtil.checkNotNull(buffer, "buffer");
      long localsize = (long)buffer.readableBytes();

      try {
         this.checkSize(localsize);
      } catch (IOException e) {
         buffer.release();
         throw e;
      }

      if (this.definedSize > 0L && this.definedSize < localsize) {
         buffer.release();
         throw new IOException("Out of size: " + localsize + " > " + this.definedSize);
      } else {
         if (this.byteBuf != null) {
            this.byteBuf.release();
         }

         this.byteBuf = buffer;
         this.size = localsize;
         this.setCompleted();
      }
   }

   public void setContent(InputStream inputStream) throws IOException {
      ObjectUtil.checkNotNull(inputStream, "inputStream");
      byte[] bytes = new byte[16384];
      ByteBuf buffer = Unpooled.buffer();
      int written = 0;

      try {
         for(int read = inputStream.read(bytes); read > 0; read = inputStream.read(bytes)) {
            buffer.writeBytes(bytes, 0, read);
            written += read;
            this.checkSize((long)written);
         }
      } catch (IOException e) {
         buffer.release();
         throw e;
      }

      this.size = (long)written;
      if (this.definedSize > 0L && this.definedSize < this.size) {
         buffer.release();
         throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
      } else {
         if (this.byteBuf != null) {
            this.byteBuf.release();
         }

         this.byteBuf = buffer;
         this.setCompleted();
      }
   }

   public void addContent(ByteBuf buffer, boolean last) throws IOException {
      if (buffer != null) {
         long localsize = (long)buffer.readableBytes();

         try {
            this.checkSize(this.size + localsize);
         } catch (IOException e) {
            buffer.release();
            throw e;
         }

         if (this.definedSize > 0L && this.definedSize < this.size + localsize) {
            buffer.release();
            throw new IOException("Out of size: " + (this.size + localsize) + " > " + this.definedSize);
         }

         this.size += localsize;
         if (this.byteBuf == null) {
            this.byteBuf = buffer;
         } else if (localsize == 0L) {
            buffer.release();
         } else if (this.byteBuf.readableBytes() == 0) {
            this.byteBuf.release();
            this.byteBuf = buffer;
         } else if (this.byteBuf instanceof CompositeByteBuf) {
            CompositeByteBuf cbb = (CompositeByteBuf)this.byteBuf;
            cbb.addComponent(true, buffer);
         } else {
            CompositeByteBuf cbb = Unpooled.compositeBuffer(Integer.MAX_VALUE);
            cbb.addComponents(true, new ByteBuf[]{this.byteBuf, buffer});
            this.byteBuf = cbb;
         }
      }

      if (last) {
         this.setCompleted();
      } else {
         ObjectUtil.checkNotNull(buffer, "buffer");
      }

   }

   public void setContent(File file) throws IOException {
      ObjectUtil.checkNotNull(file, "file");
      long newsize = file.length();
      if (newsize > 2147483647L) {
         throw new IllegalArgumentException("File too big to be loaded in memory");
      } else {
         this.checkSize(newsize);
         RandomAccessFile accessFile = new RandomAccessFile(file, "r");

         ByteBuffer byteBuffer;
         try {
            FileChannel fileChannel = accessFile.getChannel();

            try {
               byte[] array = new byte[(int)newsize];
               byteBuffer = ByteBuffer.wrap(array);

               for(int read = 0; (long)read < newsize; read += fileChannel.read(byteBuffer)) {
               }
            } finally {
               fileChannel.close();
            }
         } finally {
            accessFile.close();
         }

         byteBuffer.flip();
         if (this.byteBuf != null) {
            this.byteBuf.release();
         }

         this.byteBuf = Unpooled.wrappedBuffer(Integer.MAX_VALUE, new ByteBuffer[]{byteBuffer});
         this.size = newsize;
         this.setCompleted();
      }
   }

   public void delete() {
      if (this.byteBuf != null) {
         this.byteBuf.release();
         this.byteBuf = null;
      }

   }

   public byte[] get() {
      if (this.byteBuf == null) {
         return Unpooled.EMPTY_BUFFER.array();
      } else {
         byte[] array = new byte[this.byteBuf.readableBytes()];
         this.byteBuf.getBytes(this.byteBuf.readerIndex(), array);
         return array;
      }
   }

   public String getString() {
      return this.getString(HttpConstants.DEFAULT_CHARSET);
   }

   public String getString(Charset encoding) {
      if (this.byteBuf == null) {
         return "";
      } else {
         if (encoding == null) {
            encoding = HttpConstants.DEFAULT_CHARSET;
         }

         return this.byteBuf.toString(encoding);
      }
   }

   public ByteBuf getByteBuf() {
      return this.byteBuf;
   }

   public ByteBuf getChunk(int length) throws IOException {
      if (this.byteBuf != null && length != 0 && this.byteBuf.readableBytes() != 0) {
         int sizeLeft = this.byteBuf.readableBytes() - this.chunkPosition;
         if (sizeLeft == 0) {
            this.chunkPosition = 0;
            return Unpooled.EMPTY_BUFFER;
         } else {
            int sliceLength = length;
            if (sizeLeft < length) {
               sliceLength = sizeLeft;
            }

            ByteBuf chunk = this.byteBuf.retainedSlice(this.chunkPosition, sliceLength);
            this.chunkPosition += sliceLength;
            return chunk;
         }
      } else {
         this.chunkPosition = 0;
         return Unpooled.EMPTY_BUFFER;
      }
   }

   public boolean isInMemory() {
      return true;
   }

   public boolean renameTo(File dest) throws IOException {
      ObjectUtil.checkNotNull(dest, "dest");
      if (this.byteBuf == null) {
         if (!dest.createNewFile()) {
            throw new IOException("file exists already: " + dest);
         } else {
            return true;
         }
      } else {
         int length = this.byteBuf.readableBytes();
         long written = 0L;
         RandomAccessFile accessFile = new RandomAccessFile(dest, "rw");

         try {
            FileChannel fileChannel = accessFile.getChannel();

            try {
               if (this.byteBuf.nioBufferCount() == 1) {
                  for(ByteBuffer byteBuffer = this.byteBuf.nioBuffer(); written < (long)length; written += (long)fileChannel.write(byteBuffer)) {
                  }
               } else {
                  for(ByteBuffer[] byteBuffers = this.byteBuf.nioBuffers(); written < (long)length; written += fileChannel.write(byteBuffers)) {
                  }
               }

               fileChannel.force(false);
            } finally {
               fileChannel.close();
            }
         } finally {
            accessFile.close();
         }

         return written == (long)length;
      }
   }

   public File getFile() throws IOException {
      throw new IOException("Not represented by a file");
   }

   public HttpData touch() {
      return this.touch((Object)null);
   }

   public HttpData touch(Object hint) {
      if (this.byteBuf != null) {
         this.byteBuf.touch(hint);
      }

      return this;
   }
}
