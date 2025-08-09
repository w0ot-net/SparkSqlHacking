package org.apache.datasketches.tuple;

import java.nio.ByteBuffer;
import org.apache.datasketches.hash.MurmurHash3;
import org.apache.datasketches.memory.Memory;

public class UpdatableSketch extends QuickSelectSketch {
   public UpdatableSketch(int nomEntries, int lgResizeFactor, float samplingProbability, SummaryFactory summaryFactory) {
      super(nomEntries, lgResizeFactor, samplingProbability, summaryFactory);
   }

   /** @deprecated */
   @Deprecated
   public UpdatableSketch(Memory srcMem, SummaryDeserializer deserializer, SummaryFactory summaryFactory) {
      super(srcMem, deserializer, summaryFactory);
   }

   public UpdatableSketch(UpdatableSketch sketch) {
      super(sketch);
   }

   public UpdatableSketch copy() {
      return new UpdatableSketch(this);
   }

   public void update(long key, Object value) {
      this.update(new long[]{key}, value);
   }

   public void update(double key, Object value) {
      this.update(Util.doubleToLongArray(key), value);
   }

   public void update(String key, Object value) {
      this.update(Util.stringToByteArray(key), value);
   }

   public void update(byte[] key, Object value) {
      if (key != null && key.length != 0) {
         this.insertOrIgnore(MurmurHash3.hash(key, 9001L)[0] >>> 1, value);
      }
   }

   public void update(ByteBuffer buffer, Object value) {
      if (buffer != null && buffer.hasRemaining()) {
         this.insertOrIgnore(MurmurHash3.hash(buffer, 9001L)[0] >>> 1, value);
      }
   }

   public void update(int[] key, Object value) {
      if (key != null && key.length != 0) {
         this.insertOrIgnore(MurmurHash3.hash(key, 9001L)[0] >>> 1, value);
      }
   }

   public void update(long[] key, Object value) {
      if (key != null && key.length != 0) {
         this.insertOrIgnore(MurmurHash3.hash(key, 9001L)[0] >>> 1, value);
      }
   }

   void insertOrIgnore(long hash, Object value) {
      this.setEmpty(false);
      if (hash < this.getThetaLong()) {
         int index = this.findOrInsert(hash);
         if (index < 0) {
            index = ~index;
            this.insertSummary(index, this.getSummaryFactory().newSummary());
         }

         ((UpdatableSummary[])this.summaryTable_)[index].update(value);
         this.rebuildIfNeeded();
      }
   }
}
