package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Deque;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;

public class ParallelScatterZipCreator {
   private final Deque streams;
   private final ExecutorService executorService;
   private final ScatterGatherBackingStoreSupplier backingStoreSupplier;
   private final Deque futures;
   private final long startedAt;
   private long compressionDoneAt;
   private long scatterDoneAt;
   private final int compressionLevel;
   private final ThreadLocal tlScatterStreams;

   public ParallelScatterZipCreator() {
      this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
   }

   public ParallelScatterZipCreator(ExecutorService executorService) {
      this(executorService, new DefaultBackingStoreSupplier((Path)null));
   }

   public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier backingStoreSupplier) {
      this(executorService, backingStoreSupplier, -1);
   }

   public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier backingStoreSupplier, int compressionLevel) throws IllegalArgumentException {
      this.streams = new ConcurrentLinkedDeque();
      this.futures = new ConcurrentLinkedDeque();
      this.startedAt = System.currentTimeMillis();
      this.tlScatterStreams = new ThreadLocal() {
         protected ScatterZipOutputStream initialValue() {
            try {
               ScatterZipOutputStream scatterStream = ParallelScatterZipCreator.this.createDeferred(ParallelScatterZipCreator.this.backingStoreSupplier);
               ParallelScatterZipCreator.this.streams.add(scatterStream);
               return scatterStream;
            } catch (IOException e) {
               throw new UncheckedIOException(e);
            }
         }
      };
      if ((compressionLevel < 0 || compressionLevel > 9) && compressionLevel != -1) {
         throw new IllegalArgumentException("Compression level is expected between -1~9");
      } else {
         this.backingStoreSupplier = backingStoreSupplier;
         this.executorService = executorService;
         this.compressionLevel = compressionLevel;
      }
   }

   public void addArchiveEntry(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier source) {
      this.submitStreamAwareCallable(this.createCallable(zipArchiveEntry, source));
   }

   public void addArchiveEntry(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
      this.submitStreamAwareCallable(this.createCallable(zipArchiveEntryRequestSupplier));
   }

   private void closeAll() {
      for(ScatterZipOutputStream scatterStream : this.streams) {
         try {
            scatterStream.close();
         } catch (IOException var4) {
         }
      }

   }

   public final Callable createCallable(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier source) {
      int method = zipArchiveEntry.getMethod();
      if (method == -1) {
         throw new IllegalArgumentException("Method must be set on zipArchiveEntry: " + zipArchiveEntry);
      } else {
         ZipArchiveEntryRequest zipArchiveEntryRequest = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, source);
         return () -> {
            ScatterZipOutputStream scatterStream = (ScatterZipOutputStream)this.tlScatterStreams.get();
            scatterStream.addArchiveEntry(zipArchiveEntryRequest);
            return scatterStream;
         };
      }
   }

   public final Callable createCallable(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
      return () -> {
         ScatterZipOutputStream scatterStream = (ScatterZipOutputStream)this.tlScatterStreams.get();
         scatterStream.addArchiveEntry(zipArchiveEntryRequestSupplier.get());
         return scatterStream;
      };
   }

   private ScatterZipOutputStream createDeferred(ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier) throws IOException {
      ScatterGatherBackingStore bs = scatterGatherBackingStoreSupplier.get();
      StreamCompressor sc = StreamCompressor.create(this.compressionLevel, bs);
      return new ScatterZipOutputStream(bs, sc);
   }

   public ScatterStatistics getStatisticsMessage() {
      return new ScatterStatistics(this.compressionDoneAt - this.startedAt, this.scatterDoneAt - this.compressionDoneAt);
   }

   public final void submit(Callable callable) {
      this.submitStreamAwareCallable(() -> {
         callable.call();
         return (ScatterZipOutputStream)this.tlScatterStreams.get();
      });
   }

   public final void submitStreamAwareCallable(Callable callable) {
      this.futures.add(this.executorService.submit(callable));
   }

   public void writeTo(ZipArchiveOutputStream targetStream) throws IOException, InterruptedException, ExecutionException {
      try {
         try {
            for(Future future : this.futures) {
               future.get();
            }
         } finally {
            this.executorService.shutdown();
         }

         this.executorService.awaitTermination(60000L, TimeUnit.SECONDS);
         this.compressionDoneAt = System.currentTimeMillis();

         for(Future future : this.futures) {
            ScatterZipOutputStream scatterStream = (ScatterZipOutputStream)future.get();
            scatterStream.zipEntryWriter().writeNextZipEntry(targetStream);
         }

         for(ScatterZipOutputStream scatterStream : this.streams) {
            scatterStream.close();
         }

         this.scatterDoneAt = System.currentTimeMillis();
      } finally {
         this.closeAll();
      }

   }
}
