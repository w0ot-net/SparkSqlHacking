package org.apache.commons.compress.archivers;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Objects;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOIterator;
import org.apache.commons.io.input.NullInputStream;

public abstract class ArchiveInputStream extends FilterInputStream {
   private static final int BYTE_MASK = 255;
   private final byte[] single;
   private long bytesRead;
   private Charset charset;

   public ArchiveInputStream() {
      this(NullInputStream.INSTANCE, (Charset)Charset.defaultCharset());
   }

   private ArchiveInputStream(InputStream inputStream, Charset charset) {
      super(inputStream);
      this.single = new byte[1];
      this.charset = Charsets.toCharset(charset);
   }

   protected ArchiveInputStream(InputStream inputStream, String charsetName) {
      this(inputStream, Charsets.toCharset(charsetName));
   }

   public boolean canReadEntryData(ArchiveEntry archiveEntry) {
      return true;
   }

   protected void count(int read) {
      this.count((long)read);
   }

   protected void count(long read) {
      if (read != -1L) {
         this.bytesRead += read;
      }

   }

   public void forEach(IOConsumer action) throws IOException {
      this.iterator().forEachRemaining((IOConsumer)Objects.requireNonNull(action));
   }

   public long getBytesRead() {
      return this.bytesRead;
   }

   public Charset getCharset() {
      return this.charset;
   }

   /** @deprecated */
   @Deprecated
   public int getCount() {
      return (int)this.bytesRead;
   }

   public abstract ArchiveEntry getNextEntry() throws IOException;

   public IOIterator iterator() {
      return new ArchiveEntryIOIterator();
   }

   public synchronized void mark(int readlimit) {
   }

   public boolean markSupported() {
      return false;
   }

   protected void pushedBackBytes(long pushedBack) {
      this.bytesRead -= pushedBack;
   }

   public int read() throws IOException {
      int num = this.read(this.single, 0, 1);
      return num == -1 ? -1 : this.single[0] & 255;
   }

   public synchronized void reset() throws IOException {
   }

   class ArchiveEntryIOIterator implements IOIterator {
      private ArchiveEntry next;

      public boolean hasNext() throws IOException {
         if (this.next == null) {
            this.next = ArchiveInputStream.this.getNextEntry();
         }

         return this.next != null;
      }

      public synchronized ArchiveEntry next() throws IOException {
         if (this.next != null) {
            E e = (E)this.next;
            this.next = null;
            return e;
         } else {
            return ArchiveInputStream.this.getNextEntry();
         }
      }

      public Iterator unwrap() {
         return null;
      }
   }
}
