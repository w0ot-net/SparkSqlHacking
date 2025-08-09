package org.apache.datasketches.tuple;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.theta.HashIterator;
import org.apache.datasketches.thetacommon.QuickSelect;

public class Union {
   private final SummarySetOperations summarySetOps_;
   private QuickSelectSketch qsk_;
   private long unionThetaLong_;
   private boolean empty_;

   public Union(SummarySetOperations summarySetOps) {
      this(4096, summarySetOps);
   }

   public Union(int nomEntries, SummarySetOperations summarySetOps) {
      this.summarySetOps_ = summarySetOps;
      this.qsk_ = new QuickSelectSketch(nomEntries, (SummaryFactory)null);
      this.unionThetaLong_ = this.qsk_.getThetaLong();
      this.empty_ = true;
   }

   public CompactSketch union(Sketch tupleSketchA, Sketch tupleSketchB) {
      this.reset();
      this.union(tupleSketchA);
      this.union(tupleSketchB);
      CompactSketch<S> csk = this.getResult(true);
      return csk;
   }

   public CompactSketch union(Sketch tupleSketch, org.apache.datasketches.theta.Sketch thetaSketch, Summary summary) {
      this.reset();
      this.union(tupleSketch);
      this.union(thetaSketch, summary);
      CompactSketch<S> csk = this.getResult(true);
      return csk;
   }

   public void union(Sketch tupleSketch) {
      if (tupleSketch != null && !tupleSketch.isEmpty()) {
         this.empty_ = false;
         this.unionThetaLong_ = Math.min(tupleSketch.thetaLong_, this.unionThetaLong_);
         TupleSketchIterator<S> it = tupleSketch.iterator();

         while(it.next()) {
            this.qsk_.merge(it.getHash(), it.getSummary(), this.summarySetOps_);
         }

         this.unionThetaLong_ = Math.min(this.unionThetaLong_, this.qsk_.thetaLong_);
      }
   }

   public void union(org.apache.datasketches.theta.Sketch thetaSketch, Summary summary) {
      if (summary == null) {
         throw new SketchesArgumentException("Summary cannot be null.");
      } else if (thetaSketch != null && !thetaSketch.isEmpty()) {
         this.empty_ = false;
         long thetaIn = thetaSketch.getThetaLong();
         this.unionThetaLong_ = Math.min(thetaIn, this.unionThetaLong_);
         HashIterator it = thetaSketch.iterator();

         while(it.next()) {
            this.qsk_.merge(it.get(), summary, this.summarySetOps_);
         }

         this.unionThetaLong_ = Math.min(this.unionThetaLong_, this.qsk_.thetaLong_);
      }
   }

   public CompactSketch getResult() {
      return this.getResult(false);
   }

   public CompactSketch getResult(boolean reset) {
      CompactSketch<S> result;
      if (this.empty_) {
         result = this.qsk_.compact();
      } else if (this.unionThetaLong_ >= this.qsk_.thetaLong_ && this.qsk_.getRetainedEntries() <= this.qsk_.getNominalEntries()) {
         result = this.qsk_.compact();
      } else {
         long tmpThetaLong = Math.min(this.unionThetaLong_, this.qsk_.thetaLong_);
         int numHashesIn = 0;
         TupleSketchIterator<S> it = this.qsk_.iterator();

         while(it.next()) {
            if (it.getHash() < tmpThetaLong) {
               ++numHashesIn;
            }
         }

         if (numHashesIn == 0) {
            result = new CompactSketch((long[])null, (Summary[])null, tmpThetaLong, this.empty_);
         } else {
            int numHashesOut;
            long thetaLongOut;
            if (numHashesIn <= this.qsk_.getNominalEntries()) {
               numHashesOut = numHashesIn;
               thetaLongOut = tmpThetaLong;
            } else {
               long[] tmpHashArr = new long[numHashesIn];
               it = this.qsk_.iterator();
               int i = 0;

               while(it.next()) {
                  long hash = it.getHash();
                  if (hash < tmpThetaLong) {
                     tmpHashArr[i++] = hash;
                  }
               }

               numHashesOut = this.qsk_.getNominalEntries();
               thetaLongOut = QuickSelect.select((long[])tmpHashArr, 0, numHashesIn - 1, numHashesOut);
            }

            long[] hashArr = new long[numHashesOut];
            S[] summaries = (S[])Util.newSummaryArray(this.qsk_.getSummaryTable(), numHashesOut);
            it = this.qsk_.iterator();
            int i = 0;

            while(it.next()) {
               long hash = it.getHash();
               if (hash < thetaLongOut) {
                  hashArr[i] = hash;
                  summaries[i] = it.getSummary().copy();
                  ++i;
               }
            }

            result = new CompactSketch(hashArr, summaries, thetaLongOut, this.empty_);
         }
      }

      if (reset) {
         this.reset();
      }

      return result;
   }

   public void reset() {
      this.qsk_.reset();
      this.unionThetaLong_ = this.qsk_.getThetaLong();
      this.empty_ = true;
   }
}
