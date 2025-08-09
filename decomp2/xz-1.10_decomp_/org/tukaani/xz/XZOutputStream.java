package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.EncoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexEncoder;

public class XZOutputStream extends FinishableOutputStream {
   private final ArrayCache arrayCache;
   private OutputStream out;
   private final StreamFlags streamFlags;
   private final Check check;
   private final IndexEncoder index;
   private BlockOutputStream blockEncoder;
   private FilterEncoder[] filters;
   private boolean filtersSupportFlushing;
   private IOException exception;
   private boolean finished;
   private final byte[] tempBuf;

   public XZOutputStream(OutputStream out, FilterOptions filterOptions) throws IOException {
      this(out, (FilterOptions)filterOptions, 4);
   }

   public XZOutputStream(OutputStream out, FilterOptions filterOptions, ArrayCache arrayCache) throws IOException {
      this(out, (FilterOptions)filterOptions, 4, arrayCache);
   }

   public XZOutputStream(OutputStream out, FilterOptions filterOptions, int checkType) throws IOException {
      this(out, new FilterOptions[]{filterOptions}, checkType);
   }

   public XZOutputStream(OutputStream out, FilterOptions filterOptions, int checkType, ArrayCache arrayCache) throws IOException {
      this(out, new FilterOptions[]{filterOptions}, checkType, arrayCache);
   }

   public XZOutputStream(OutputStream out, FilterOptions[] filterOptions) throws IOException {
      this(out, (FilterOptions[])filterOptions, 4);
   }

   public XZOutputStream(OutputStream out, FilterOptions[] filterOptions, ArrayCache arrayCache) throws IOException {
      this(out, (FilterOptions[])filterOptions, 4, arrayCache);
   }

   public XZOutputStream(OutputStream out, FilterOptions[] filterOptions, int checkType) throws IOException {
      this(out, filterOptions, checkType, ArrayCache.getDefaultCache());
   }

   public XZOutputStream(OutputStream out, FilterOptions[] filterOptions, int checkType, ArrayCache arrayCache) throws IOException {
      this.streamFlags = new StreamFlags();
      this.index = new IndexEncoder();
      this.blockEncoder = null;
      this.exception = null;
      this.finished = false;
      this.tempBuf = new byte[1];
      this.arrayCache = arrayCache;
      this.out = out;
      this.setFiltersForNextBlock(filterOptions);
      this.streamFlags.checkType = checkType;
      this.check = Check.getInstance(checkType);
      this.encodeStreamHeader();
   }

   public void updateFilters(FilterOptions filterOptions) throws XZIOException {
      FilterOptions[] opts = new FilterOptions[1];
      opts[0] = filterOptions;
      this.updateFilters(opts);
   }

   public void updateFilters(FilterOptions[] filterOptions) throws XZIOException {
      if (this.blockEncoder != null) {
         throw new UnsupportedOptionsException("Changing filter options in the middle of a XZ Block not implemented");
      } else {
         this.setFiltersForNextBlock(filterOptions);
      }
   }

   private void setFiltersForNextBlock(FilterOptions[] filterOptions) throws XZIOException {
      if (filterOptions.length >= 1 && filterOptions.length <= 4) {
         this.filtersSupportFlushing = true;
         FilterEncoder[] newFilters = new FilterEncoder[filterOptions.length];

         for(int i = 0; i < filterOptions.length; ++i) {
            newFilters[i] = filterOptions[i].getFilterEncoder();
            this.filtersSupportFlushing &= newFilters[i].supportsFlushing();
         }

         RawCoder.validate(newFilters);
         this.filters = newFilters;
      } else {
         throw new UnsupportedOptionsException("XZ filter chain must be 1-4 filters");
      }
   }

   public void write(int b) throws IOException {
      this.tempBuf[0] = (byte)b;
      this.write(this.tempBuf, 0, 1);
   }

   public void write(byte[] buf, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len >= 0 && off + len <= buf.length) {
         if (this.exception != null) {
            throw this.exception;
         } else if (this.finished) {
            throw new XZIOException("Stream finished or closed");
         } else {
            try {
               if (this.blockEncoder == null) {
                  this.blockEncoder = new BlockOutputStream(this.out, this.filters, this.check, this.arrayCache);
               }

               this.blockEncoder.write(buf, off, len);
            } catch (IOException e) {
               this.exception = e;
               throw e;
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void endBlock() throws IOException {
      if (this.exception != null) {
         throw this.exception;
      } else if (this.finished) {
         throw new XZIOException("Stream finished or closed");
      } else {
         if (this.blockEncoder != null) {
            try {
               this.blockEncoder.finish();
               this.index.add(this.blockEncoder.getUnpaddedSize(), this.blockEncoder.getUncompressedSize());
               this.blockEncoder = null;
            } catch (IOException e) {
               this.exception = e;
               throw e;
            }
         }

      }
   }

   public void flush() throws IOException {
      if (this.exception != null) {
         throw this.exception;
      } else if (this.finished) {
         throw new XZIOException("Stream finished or closed");
      } else {
         try {
            if (this.blockEncoder != null) {
               if (this.filtersSupportFlushing) {
                  this.blockEncoder.flush();
               } else {
                  this.endBlock();
                  this.out.flush();
               }
            } else {
               this.out.flush();
            }

         } catch (IOException e) {
            this.exception = e;
            throw e;
         }
      }
   }

   public void finish() throws IOException {
      if (!this.finished) {
         this.endBlock();

         try {
            this.index.encode(this.out);
            this.encodeStreamFooter();
         } catch (IOException e) {
            this.exception = e;
            throw e;
         }

         this.finished = true;
      }

   }

   public void close() throws IOException {
      if (this.out != null) {
         try {
            this.finish();
         } catch (IOException var2) {
         }

         try {
            this.out.close();
         } catch (IOException e) {
            if (this.exception == null) {
               this.exception = e;
            }
         }

         this.out = null;
      }

      if (this.exception != null) {
         throw this.exception;
      }
   }

   private void encodeStreamFlags(byte[] buf, int off) {
      buf[off] = 0;
      buf[off + 1] = (byte)this.streamFlags.checkType;
   }

   private void encodeStreamHeader() throws IOException {
      this.out.write(XZ.HEADER_MAGIC);
      byte[] buf = new byte[2];
      this.encodeStreamFlags(buf, 0);
      this.out.write(buf);
      EncoderUtil.writeCRC32(this.out, buf);
   }

   private void encodeStreamFooter() throws IOException {
      byte[] buf = new byte[6];
      long backwardSize = this.index.getIndexSize() / 4L - 1L;

      for(int i = 0; i < 4; ++i) {
         buf[i] = (byte)((int)(backwardSize >>> i * 8));
      }

      this.encodeStreamFlags(buf, 4);
      EncoderUtil.writeCRC32(this.out, buf);
      this.out.write(buf);
      this.out.write(XZ.FOOTER_MAGIC);
   }
}
