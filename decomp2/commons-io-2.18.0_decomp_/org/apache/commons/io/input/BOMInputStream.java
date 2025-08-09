package org.apache.commons.io.input;

import [Lorg.apache.commons.io.ByteOrderMark;;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;

public class BOMInputStream extends ProxyInputStream {
   private static final Comparator ByteOrderMarkLengthComparator = Comparator.comparing(ByteOrderMark::length).reversed();
   private final List boms;
   private ByteOrderMark byteOrderMark;
   private int fbIndex;
   private int fbLength;
   private int[] firstBytes;
   private final boolean include;
   private boolean markedAtStart;
   private int markFbIndex;

   public static Builder builder() {
      return new Builder();
   }

   private BOMInputStream(Builder builder) throws IOException {
      super((ProxyInputStream.AbstractBuilder)builder);
      if (IOUtils.length((Object[])builder.byteOrderMarks) == 0) {
         throw new IllegalArgumentException("No BOMs specified");
      } else {
         this.include = builder.include;
         List<ByteOrderMark> list = Arrays.asList(builder.byteOrderMarks);
         list.sort(ByteOrderMarkLengthComparator);
         this.boms = list;
      }
   }

   /** @deprecated */
   @Deprecated
   public BOMInputStream(InputStream delegate) {
      this(delegate, false, BOMInputStream.Builder.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public BOMInputStream(InputStream delegate, boolean include) {
      this(delegate, include, BOMInputStream.Builder.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public BOMInputStream(InputStream delegate, boolean include, ByteOrderMark... boms) {
      super(delegate);
      if (IOUtils.length((Object[])boms) == 0) {
         throw new IllegalArgumentException("No BOMs specified");
      } else {
         this.include = include;
         List<ByteOrderMark> list = Arrays.asList(boms);
         list.sort(ByteOrderMarkLengthComparator);
         this.boms = list;
      }
   }

   /** @deprecated */
   @Deprecated
   public BOMInputStream(InputStream delegate, ByteOrderMark... boms) {
      this(delegate, false, boms);
   }

   private ByteOrderMark find() {
      return (ByteOrderMark)this.boms.stream().filter(this::matches).findFirst().orElse((Object)null);
   }

   public ByteOrderMark getBOM() throws IOException {
      if (this.firstBytes == null) {
         this.fbLength = 0;
         int maxBomSize = ((ByteOrderMark)this.boms.get(0)).length();
         this.firstBytes = new int[maxBomSize];

         for(int i = 0; i < this.firstBytes.length; ++i) {
            this.firstBytes[i] = this.in.read();
            this.afterRead(this.firstBytes[i]);
            ++this.fbLength;
            if (this.firstBytes[i] < 0) {
               break;
            }
         }

         this.byteOrderMark = this.find();
         if (this.byteOrderMark != null && !this.include) {
            if (this.byteOrderMark.length() < this.firstBytes.length) {
               this.fbIndex = this.byteOrderMark.length();
            } else {
               this.fbLength = 0;
            }
         }
      }

      return this.byteOrderMark;
   }

   public String getBOMCharsetName() throws IOException {
      this.getBOM();
      return this.byteOrderMark == null ? null : this.byteOrderMark.getCharsetName();
   }

   public boolean hasBOM() throws IOException {
      return this.getBOM() != null;
   }

   public boolean hasBOM(ByteOrderMark bom) throws IOException {
      if (!this.boms.contains(bom)) {
         throw new IllegalArgumentException("Stream not configured to detect " + bom);
      } else {
         return Objects.equals(this.getBOM(), bom);
      }
   }

   public synchronized void mark(int readLimit) {
      this.markFbIndex = this.fbIndex;
      this.markedAtStart = this.firstBytes == null;
      this.in.mark(readLimit);
   }

   private boolean matches(ByteOrderMark bom) {
      for(int i = 0; i < bom.length(); ++i) {
         if (bom.get(i) != this.firstBytes[i]) {
            return false;
         }
      }

      return true;
   }

   public int read() throws IOException {
      this.checkOpen();
      int b = this.readFirstBytes();
      return b >= 0 ? b : this.in.read();
   }

   public int read(byte[] buf) throws IOException {
      return this.read(buf, 0, buf.length);
   }

   public int read(byte[] buf, int off, int len) throws IOException {
      int firstCount = 0;
      int b = 0;

      while(len > 0 && b >= 0) {
         b = this.readFirstBytes();
         if (b >= 0) {
            buf[off++] = (byte)(b & 255);
            --len;
            ++firstCount;
         }
      }

      int secondCount = this.in.read(buf, off, len);
      this.afterRead(secondCount);
      return secondCount < 0 ? (firstCount > 0 ? firstCount : -1) : firstCount + secondCount;
   }

   private int readFirstBytes() throws IOException {
      this.getBOM();
      return this.fbIndex < this.fbLength ? this.firstBytes[this.fbIndex++] : -1;
   }

   public synchronized void reset() throws IOException {
      this.fbIndex = this.markFbIndex;
      if (this.markedAtStart) {
         this.firstBytes = null;
      }

      this.in.reset();
   }

   public long skip(long n) throws IOException {
      int skipped;
      for(skipped = 0; n > (long)skipped && this.readFirstBytes() >= 0; ++skipped) {
      }

      return this.in.skip(n - (long)skipped) + (long)skipped;
   }

   public static class Builder extends ProxyInputStream.AbstractBuilder {
      private static final ByteOrderMark[] DEFAULT;
      private ByteOrderMark[] byteOrderMarks;
      private boolean include;

      public Builder() {
         this.byteOrderMarks = DEFAULT;
      }

      static ByteOrderMark getDefaultByteOrderMark() {
         return DEFAULT[0];
      }

      public BOMInputStream get() throws IOException {
         return new BOMInputStream(this);
      }

      public Builder setByteOrderMarks(ByteOrderMark... byteOrderMarks) {
         this.byteOrderMarks = byteOrderMarks != null ? (ByteOrderMark[])((ByteOrderMark;)byteOrderMarks).clone() : DEFAULT;
         return this;
      }

      public Builder setInclude(boolean include) {
         this.include = include;
         return this;
      }

      static {
         DEFAULT = new ByteOrderMark[]{ByteOrderMark.UTF_8};
      }
   }
}
