package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.DefaultMemoryRequestServer;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

public class SetOperationBuilder {
   private int bLgNomLongs = Integer.numberOfTrailingZeros(4096);
   private long bSeed = 9001L;
   private ResizeFactor bRF;
   private float bP = 1.0F;
   private MemoryRequestServer bMemReqSvr;

   public SetOperationBuilder() {
      this.bRF = ResizeFactor.X8;
      this.bMemReqSvr = new DefaultMemoryRequestServer();
   }

   public SetOperationBuilder setNominalEntries(int nomEntries) {
      this.bLgNomLongs = Integer.numberOfTrailingZeros(Util.ceilingPowerOf2(nomEntries));
      if (this.bLgNomLongs <= 26 && this.bLgNomLongs >= 4) {
         return this;
      } else {
         throw new SketchesArgumentException("Nominal Entries must be >= 16 and <= 67108864: " + nomEntries);
      }
   }

   public SetOperationBuilder setLogNominalEntries(int lgNomEntries) {
      this.bLgNomLongs = ThetaUtil.checkNomLongs(1 << lgNomEntries);
      return this;
   }

   public int getLgNominalEntries() {
      return this.bLgNomLongs;
   }

   public SetOperationBuilder setSeed(long seed) {
      this.bSeed = seed;
      return this;
   }

   public long getSeed() {
      return this.bSeed;
   }

   public SetOperationBuilder setP(float p) {
      if (!((double)p <= (double)0.0F) && !((double)p > (double)1.0F)) {
         this.bP = p;
         return this;
      } else {
         throw new SketchesArgumentException("p must be > 0 and <= 1.0: " + p);
      }
   }

   public float getP() {
      return this.bP;
   }

   public SetOperationBuilder setResizeFactor(ResizeFactor rf) {
      this.bRF = rf;
      return this;
   }

   public ResizeFactor getResizeFactor() {
      return this.bRF;
   }

   public SetOperationBuilder setMemoryRequestServer(MemoryRequestServer memReqSvr) {
      this.bMemReqSvr = memReqSvr;
      return this;
   }

   public MemoryRequestServer getMemoryRequestServer() {
      return this.bMemReqSvr;
   }

   public SetOperation build(Family family) {
      return this.build(family, (WritableMemory)null);
   }

   public SetOperation build(Family family, WritableMemory dstMem) {
      SetOperation setOp = null;
      switch (family) {
         case UNION:
            if (dstMem == null) {
               setOp = UnionImpl.initNewHeapInstance(this.bLgNomLongs, this.bSeed, this.bP, this.bRF);
            } else {
               setOp = UnionImpl.initNewDirectInstance(this.bLgNomLongs, this.bSeed, this.bP, this.bRF, this.bMemReqSvr, dstMem);
            }
            break;
         case INTERSECTION:
            if (dstMem == null) {
               setOp = IntersectionImpl.initNewHeapInstance(this.bSeed);
            } else {
               setOp = IntersectionImpl.initNewDirectInstance(this.bSeed, dstMem);
            }
            break;
         case A_NOT_B:
            if (dstMem != null) {
               throw new SketchesArgumentException("AnotB can not be persisted.");
            }

            setOp = new AnotBimpl(this.bSeed);
            break;
         default:
            throw new SketchesArgumentException("Given Family cannot be built as a SetOperation: " + family.toString());
      }

      return setOp;
   }

   public Union buildUnion() {
      return (Union)this.build(Family.UNION);
   }

   public Union buildUnion(WritableMemory dstMem) {
      return (Union)this.build(Family.UNION, dstMem);
   }

   public Intersection buildIntersection() {
      return (Intersection)this.build(Family.INTERSECTION);
   }

   public Intersection buildIntersection(WritableMemory dstMem) {
      return (Intersection)this.build(Family.INTERSECTION, dstMem);
   }

   public AnotB buildANotB() {
      return (AnotB)this.build(Family.A_NOT_B);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("SetOperationBuilder configuration:").append(Util.LS);
      sb.append("LgK:").append('\t').append(this.bLgNomLongs).append(Util.LS);
      sb.append("K:").append('\t').append(1 << this.bLgNomLongs).append(Util.LS);
      sb.append("Seed:").append('\t').append(this.bSeed).append(Util.LS);
      sb.append("p:").append('\t').append(this.bP).append(Util.LS);
      sb.append("ResizeFactor:").append('\t').append(this.bRF).append(Util.LS);
      String mrsStr = this.bMemReqSvr.getClass().getSimpleName();
      sb.append("MemoryRequestServer:").append('\t').append(mrsStr).append(Util.LS);
      return sb.toString();
   }
}
