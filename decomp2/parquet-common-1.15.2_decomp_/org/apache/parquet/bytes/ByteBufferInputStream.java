package org.apache.parquet.bytes;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import org.apache.parquet.ShouldNeverHappenException;

public class ByteBufferInputStream extends InputStream {
   private final ByteBufferInputStream delegate;

   public static ByteBufferInputStream wrap(ByteBuffer... buffers) {
      return (ByteBufferInputStream)(buffers.length == 1 ? new SingleBufferInputStream(buffers[0]) : new MultiBufferInputStream(Arrays.asList(buffers)));
   }

   public static ByteBufferInputStream wrap(List buffers) {
      return (ByteBufferInputStream)(buffers.size() == 1 ? new SingleBufferInputStream((ByteBuffer)buffers.get(0)) : new MultiBufferInputStream(buffers));
   }

   ByteBufferInputStream() {
      this.delegate = null;
   }

   /** @deprecated */
   @Deprecated
   public ByteBufferInputStream(ByteBuffer buffer) {
      this.delegate = wrap(buffer);
   }

   /** @deprecated */
   @Deprecated
   public ByteBufferInputStream(ByteBuffer buffer, int offset, int count) {
      ByteBuffer temp = buffer.duplicate();
      temp.position(offset);
      ByteBuffer byteBuf = temp.slice();
      byteBuf.limit(count);
      this.delegate = wrap(byteBuf);
   }

   /** @deprecated */
   @Deprecated
   public ByteBuffer toByteBuffer() {
      try {
         return this.slice(this.available());
      } catch (EOFException e) {
         throw new ShouldNeverHappenException(e);
      }
   }

   public long position() {
      return this.delegate.position();
   }

   public void skipFully(long n) throws IOException {
      long skipped = this.skip(n);
      if (skipped < n) {
         throw new EOFException("Not enough bytes to skip: " + skipped + " < " + n);
      }
   }

   public int read(ByteBuffer out) {
      return this.delegate.read(out);
   }

   public ByteBuffer slice(int length) throws EOFException {
      return this.delegate.slice(length);
   }

   public List sliceBuffers(long length) throws EOFException {
      return this.delegate.sliceBuffers(length);
   }

   public ByteBufferInputStream sliceStream(long length) throws EOFException {
      return wrap(this.sliceBuffers(length));
   }

   public List remainingBuffers() {
      return this.delegate.remainingBuffers();
   }

   public ByteBufferInputStream remainingStream() {
      return wrap(this.remainingBuffers());
   }

   public int read() throws IOException {
      return this.delegate.read();
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.delegate.read(b, off, len);
   }

   public long skip(long n) {
      return this.delegate.skip(n);
   }

   public int available() {
      return this.delegate.available();
   }

   public void mark(int readlimit) {
      this.delegate.mark(readlimit);
   }

   public void reset() throws IOException {
      this.delegate.reset();
   }

   public boolean markSupported() {
      return this.delegate.markSupported();
   }
}
