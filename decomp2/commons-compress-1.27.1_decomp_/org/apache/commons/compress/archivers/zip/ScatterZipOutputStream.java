package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.io.input.BoundedInputStream;

public class ScatterZipOutputStream implements Closeable {
   private final Queue items = new ConcurrentLinkedQueue();
   private final ScatterGatherBackingStore backingStore;
   private final StreamCompressor streamCompressor;
   private final AtomicBoolean isClosed = new AtomicBoolean();
   private ZipEntryWriter zipEntryWriter;

   public static ScatterZipOutputStream fileBased(File file) throws FileNotFoundException {
      return pathBased(file.toPath(), -1);
   }

   public static ScatterZipOutputStream fileBased(File file, int compressionLevel) throws FileNotFoundException {
      return pathBased(file.toPath(), compressionLevel);
   }

   public static ScatterZipOutputStream pathBased(Path path) throws FileNotFoundException {
      return pathBased(path, -1);
   }

   public static ScatterZipOutputStream pathBased(Path path, int compressionLevel) throws FileNotFoundException {
      ScatterGatherBackingStore bs = new FileBasedScatterGatherBackingStore(path);
      StreamCompressor sc = StreamCompressor.create(compressionLevel, bs);
      return new ScatterZipOutputStream(bs, sc);
   }

   public ScatterZipOutputStream(ScatterGatherBackingStore backingStore, StreamCompressor streamCompressor) {
      this.backingStore = backingStore;
      this.streamCompressor = streamCompressor;
   }

   public void addArchiveEntry(ZipArchiveEntryRequest zipArchiveEntryRequest) throws IOException {
      InputStream payloadStream = zipArchiveEntryRequest.getPayloadStream();

      try {
         this.streamCompressor.deflate(payloadStream, zipArchiveEntryRequest.getMethod());
      } catch (Throwable var6) {
         if (payloadStream != null) {
            try {
               payloadStream.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (payloadStream != null) {
         payloadStream.close();
      }

      this.items.add(new CompressedEntry(zipArchiveEntryRequest, this.streamCompressor.getCrc32(), this.streamCompressor.getBytesWrittenForLastEntry(), this.streamCompressor.getBytesRead()));
   }

   public void close() throws IOException {
      if (this.isClosed.compareAndSet(false, true)) {
         try {
            if (this.zipEntryWriter != null) {
               this.zipEntryWriter.close();
            }

            this.backingStore.close();
         } finally {
            this.streamCompressor.close();
         }

      }
   }

   public void writeTo(ZipArchiveOutputStream target) throws IOException {
      this.backingStore.closeForWriting();
      InputStream data = this.backingStore.getInputStream();

      try {
         for(CompressedEntry compressedEntry : this.items) {
            BoundedInputStream rawStream = ((BoundedInputStream.Builder)((BoundedInputStream.Builder)((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(data)).setMaxCount(compressedEntry.compressedSize)).setPropagateClose(false)).get();

            try {
               target.addRawArchiveEntry(compressedEntry.transferToArchiveEntry(), rawStream);
            } catch (Throwable var10) {
               if (rawStream != null) {
                  try {
                     rawStream.close();
                  } catch (Throwable var9) {
                     var10.addSuppressed(var9);
                  }
               }

               throw var10;
            }

            if (rawStream != null) {
               rawStream.close();
            }
         }
      } catch (Throwable var11) {
         if (data != null) {
            try {
               data.close();
            } catch (Throwable var8) {
               var11.addSuppressed(var8);
            }
         }

         throw var11;
      }

      if (data != null) {
         data.close();
      }

   }

   public ZipEntryWriter zipEntryWriter() throws IOException {
      if (this.zipEntryWriter == null) {
         this.zipEntryWriter = new ZipEntryWriter(this);
      }

      return this.zipEntryWriter;
   }

   private static final class CompressedEntry {
      final ZipArchiveEntryRequest zipArchiveEntryRequest;
      final long crc;
      final long compressedSize;
      final long size;

      CompressedEntry(ZipArchiveEntryRequest zipArchiveEntryRequest, long crc, long compressedSize, long size) {
         this.zipArchiveEntryRequest = zipArchiveEntryRequest;
         this.crc = crc;
         this.compressedSize = compressedSize;
         this.size = size;
      }

      public ZipArchiveEntry transferToArchiveEntry() {
         ZipArchiveEntry entry = this.zipArchiveEntryRequest.getZipArchiveEntry();
         entry.setCompressedSize(this.compressedSize);
         entry.setSize(this.size);
         entry.setCrc(this.crc);
         entry.setMethod(this.zipArchiveEntryRequest.getMethod());
         return entry;
      }
   }

   public static class ZipEntryWriter implements Closeable {
      private final Iterator itemsIterator;
      private final InputStream itemsIteratorData;

      public ZipEntryWriter(ScatterZipOutputStream scatter) throws IOException {
         scatter.backingStore.closeForWriting();
         this.itemsIterator = scatter.items.iterator();
         this.itemsIteratorData = scatter.backingStore.getInputStream();
      }

      public void close() throws IOException {
         if (this.itemsIteratorData != null) {
            this.itemsIteratorData.close();
         }

      }

      public void writeNextZipEntry(ZipArchiveOutputStream target) throws IOException {
         CompressedEntry compressedEntry = (CompressedEntry)this.itemsIterator.next();
         BoundedInputStream rawStream = ((BoundedInputStream.Builder)((BoundedInputStream.Builder)((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(this.itemsIteratorData)).setMaxCount(compressedEntry.compressedSize)).setPropagateClose(false)).get();

         try {
            target.addRawArchiveEntry(compressedEntry.transferToArchiveEntry(), rawStream);
         } catch (Throwable var7) {
            if (rawStream != null) {
               try {
                  rawStream.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (rawStream != null) {
            rawStream.close();
         }

      }
   }
}
