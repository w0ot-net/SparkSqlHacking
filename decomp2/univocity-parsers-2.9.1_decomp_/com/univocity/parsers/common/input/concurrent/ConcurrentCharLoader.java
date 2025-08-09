package com.univocity.parsers.common.input.concurrent;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.input.BomInput;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.ArrayBlockingQueue;

class ConcurrentCharLoader implements Runnable {
   private final ArrayBlockingQueue buckets;
   private final CharBucket end;
   private final FixedInstancePool instances;
   private Entry currentBucket;
   private boolean finished = false;
   private boolean active;
   Reader reader;
   private Thread activeExecution;
   private Exception error;
   private final boolean closeOnStop;

   public ConcurrentCharLoader(Reader reader, final int bucketSize, int bucketQuantity, boolean closeOnStop) {
      this.closeOnStop = closeOnStop;
      this.end = new CharBucket(-1);
      this.buckets = new ArrayBlockingQueue(bucketQuantity);
      this.reader = reader;
      this.instances = new FixedInstancePool(bucketQuantity) {
         protected CharBucket newInstance() {
            return new CharBucket(bucketSize);
         }
      };
      this.finished = false;
      this.active = true;
   }

   private int readBucket() throws IOException, InterruptedException {
      Entry<CharBucket> bucket = this.instances.allocate();
      int length = ((CharBucket)bucket.get()).fill(this.reader);
      if (length != -1) {
         this.buckets.put(bucket);
      } else {
         this.instances.release(bucket);
      }

      return length;
   }

   public void run() {
      try {
         try {
            while(this.active && this.readBucket() != -1) {
            }
         } finally {
            this.buckets.put(this.end);
         }
      } catch (InterruptedException var12) {
         Thread.currentThread().interrupt();
      } catch (Exception e) {
         this.finished = true;
         this.setError(e);
      } finally {
         this.stopReading();
      }

   }

   private void setError(Exception e) {
      if (this.active) {
         this.error = e;
      }

   }

   public synchronized CharBucket nextBucket() {
      if (this.activeExecution == null && !this.finished) {
         int length = -1;

         try {
            length = this.readBucket();
            if (length >= 0 && length <= 4) {
               length = this.readBucket();
            }
         } catch (BomInput.BytesProcessedNotification e) {
            throw e;
         } catch (Exception e) {
            this.setError(e);
         }

         if (length != -1) {
            this.activeExecution = new Thread(this, "unVocity-parsers input reading thread");
            this.activeExecution.start();
         } else {
            this.finished = true;

            try {
               this.buckets.put(this.end);
            } catch (InterruptedException var10) {
               Thread.currentThread().interrupt();
            } finally {
               this.stopReading();
            }
         }
      }

      try {
         if (this.finished && this.buckets.size() <= 1) {
            return this.end;
         } else {
            if (this.currentBucket != null) {
               this.instances.release(this.currentBucket);
            }

            Object element = this.buckets.take();
            if (element == this.end) {
               this.finished = true;
               return this.end;
            } else {
               this.currentBucket = (Entry)element;
               return (CharBucket)this.currentBucket.get();
            }
         }
      } catch (InterruptedException var9) {
         Thread.currentThread().interrupt();
         this.finished = true;
         return this.end;
      }
   }

   public void stopReading() {
      this.active = false;

      try {
         if (this.closeOnStop) {
            this.reader.close();
         }
      } catch (IOException e) {
         throw new IllegalStateException("Error closing input", e);
      } finally {
         try {
            if (this.activeExecution != null) {
               this.activeExecution.interrupt();
            }
         } catch (Throwable ex) {
            throw new IllegalStateException("Error stopping input reader thread", ex);
         }

      }

   }

   void reportError() {
      if (this.error != null) {
         ArgumentUtils.throwUnchecked(this.error);
      }

   }
}
