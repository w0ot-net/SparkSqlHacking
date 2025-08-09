package org.apache.datasketches.req;

import java.util.Random;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;

class ReqCompactor {
   private static final double SQRT2 = Math.sqrt((double)2.0F);
   private final byte lgWeight;
   private final boolean hra;
   private long state;
   private float sectionSizeFlt;
   private int sectionSize;
   private byte numSections;
   private boolean coin;
   private FloatBuffer buf;
   private final ReqDebug reqDebug = null;

   ReqCompactor(byte lgWeight, boolean hra, int sectionSize, ReqDebug reqDebug) {
      this.lgWeight = lgWeight;
      this.hra = hra;
      this.sectionSize = sectionSize;
      this.sectionSizeFlt = (float)sectionSize;
      this.state = 0L;
      this.coin = false;
      this.numSections = 3;
      int nomCap = this.getNomCapacity();
      this.buf = new FloatBuffer(2 * nomCap, nomCap, hra);
   }

   ReqCompactor(ReqCompactor other) {
      this.lgWeight = other.lgWeight;
      this.hra = other.hra;
      this.sectionSizeFlt = other.sectionSizeFlt;
      this.numSections = other.numSections;
      this.sectionSize = other.sectionSize;
      this.state = other.state;
      this.coin = other.coin;
      this.buf = new FloatBuffer(other.buf);
   }

   ReqCompactor(byte lgWeight, boolean hra, long state, float sectionSizeFlt, byte numSections, FloatBuffer buf) {
      this.lgWeight = lgWeight;
      this.hra = hra;
      this.buf = buf;
      this.sectionSizeFlt = sectionSizeFlt;
      this.numSections = numSections;
      this.state = state;
      this.coin = false;
      this.sectionSize = nearestEven(sectionSizeFlt);
   }

   FloatBuffer compact(ReqSketch.CompactorReturn cReturn, Random rand) {
      if (this.reqDebug != null) {
         this.reqDebug.emitCompactingStart(this.lgWeight);
      }

      int startRetItems = this.buf.getCount();
      int startNomCap = this.getNomCapacity();
      int secsToCompact = Math.min(Util.numberOfTrailingOnes(this.state) + 1, this.numSections);
      long compactionRange = this.computeCompactionRange(secsToCompact);
      int compactionStart = (int)(compactionRange & 4294967295L);
      int compactionEnd = (int)(compactionRange >>> 32);

      assert compactionEnd - compactionStart >= 2;

      if ((this.state & 1L) == 1L) {
         this.coin = !this.coin;
      } else {
         this.coin = rand.nextBoolean();
      }

      FloatBuffer promote = this.buf.getEvensOrOdds(compactionStart, compactionEnd, this.coin);
      if (this.reqDebug != null) {
         this.reqDebug.emitCompactionDetail(compactionStart, compactionEnd, secsToCompact, promote.getCount(), this.coin);
      }

      this.buf.trimCount(this.buf.getCount() - (compactionEnd - compactionStart));
      ++this.state;
      this.ensureEnoughSections();
      cReturn.deltaRetItems = this.buf.getCount() - startRetItems + promote.getCount();
      cReturn.deltaNomSize = this.getNomCapacity() - startNomCap;
      if (this.reqDebug != null) {
         this.reqDebug.emitCompactionDone(this.lgWeight);
      }

      return promote;
   }

   FloatBuffer getBuffer() {
      return this.buf;
   }

   boolean getCoin() {
      return this.coin;
   }

   byte getLgWeight() {
      return this.lgWeight;
   }

   final int getNomCapacity() {
      return 2 * this.numSections * this.sectionSize;
   }

   int getSerializationBytes() {
      int count = this.buf.getCount();
      return 20 + count * 4;
   }

   int getNumSections() {
      return this.numSections;
   }

   int getSectionSize() {
      return this.sectionSize;
   }

   float getSectionSizeFlt() {
      return this.sectionSizeFlt;
   }

   long getState() {
      return this.state;
   }

   boolean isHighRankAccuracy() {
      return this.hra;
   }

   ReqCompactor merge(ReqCompactor other) {
      assert this.lgWeight == other.lgWeight;

      this.state |= other.state;

      while(this.ensureEnoughSections()) {
      }

      this.buf.sort();
      FloatBuffer otherBuf = new FloatBuffer(other.buf);
      otherBuf.sort();
      if (otherBuf.getCount() > this.buf.getCount()) {
         otherBuf.mergeSortIn(this.buf);
         this.buf = otherBuf;
      } else {
         this.buf.mergeSortIn(otherBuf);
      }

      return this;
   }

   private boolean ensureEnoughSections() {
      float szf;
      int ne;
      if (this.state >= 1L << this.numSections - 1 && this.sectionSize > 4 && (ne = nearestEven(szf = (float)((double)this.sectionSizeFlt / SQRT2))) >= 4) {
         this.sectionSizeFlt = szf;
         this.sectionSize = ne;
         this.numSections = (byte)(this.numSections << 1);
         this.buf.ensureCapacity(2 * this.getNomCapacity());
         if (this.reqDebug != null) {
            this.reqDebug.emitAdjSecSizeNumSec(this.lgWeight);
         }

         return true;
      } else {
         return false;
      }
   }

   private long computeCompactionRange(int secsToCompact) {
      int bufLen = this.buf.getCount();
      int nonCompact = this.getNomCapacity() / 2 + (this.numSections - secsToCompact) * this.sectionSize;
      nonCompact = (bufLen - nonCompact & 1) == 1 ? nonCompact + 1 : nonCompact;
      long low = this.hra ? 0L : (long)nonCompact;
      long high = this.hra ? (long)(bufLen - nonCompact) : (long)bufLen;
      return (high << 32) + low;
   }

   static final int nearestEven(float fltVal) {
      return (int)Math.round((double)fltVal / (double)2.0F) << 1;
   }

   byte[] toByteArray() {
      int bytes = this.getSerializationBytes();
      byte[] arr = new byte[bytes];
      WritableBuffer wbuf = WritableMemory.writableWrap(arr).asWritableBuffer();
      wbuf.putLong(this.state);
      wbuf.putFloat(this.sectionSizeFlt);
      wbuf.putByte(this.lgWeight);
      wbuf.putByte(this.numSections);
      wbuf.incrementPosition(2L);
      wbuf.putInt(this.buf.getCount());
      wbuf.putByteArray(this.buf.floatsToBytes(), 0, 4 * this.buf.getCount());

      assert wbuf.getPosition() == (long)bytes;

      return arr;
   }

   String toListPrefix() {
      int h = this.getLgWeight();
      int len = this.buf.getCount();
      int nom = this.getNomCapacity();
      int secSz = this.getSectionSize();
      int numSec = this.getNumSections();
      long num = this.getState();
      String prefix = String.format("  C:%d Len:%d NomSz:%d SecSz:%d NumSec:%d State:%d", h, len, nom, secSz, numSec, num);
      return prefix;
   }
}
