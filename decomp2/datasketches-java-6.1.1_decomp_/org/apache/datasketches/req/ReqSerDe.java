package org.apache.datasketches.req;

import java.util.ArrayList;
import java.util.List;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;

class ReqSerDe {
   private static final byte SER_VER = 1;
   private static final byte FAMILY_ID;

   static ReqSketch heapify(Memory mem) {
      Buffer buff = mem.asBuffer();
      byte preInts = buff.getByte();
      byte serVer = buff.getByte();

      assert serVer == 1;

      byte familyId = buff.getByte();

      assert familyId == 17;

      int flags = buff.getByte() & 255;
      boolean empty = (flags & 4) > 0;
      boolean hra = (flags & 8) > 0;
      boolean rawItems = (flags & 16) > 0;
      boolean lvl0Sorted = (flags & 32) > 0;
      int k = buff.getShort() & '\uffff';
      int numCompactors = buff.getByte() & 255;
      int numRawItems = buff.getByte() & 255;
      SerDeFormat deserFormat = getDeserFormat(empty, rawItems, numCompactors);
      switch (deserFormat) {
         case EMPTY:
            assert preInts == 2;

            return new ReqSketch(k, hra, (ReqDebug)null);
         case RAWITEMS:
            assert preInts == 2;

            ReqSketch sk = new ReqSketch(k, hra, (ReqDebug)null);

            for(int i = 0; i < numRawItems; ++i) {
               sk.update(buff.getFloat());
            }

            return sk;
         case EXACT:
            assert preInts == 2;

            Compactor compactor = extractCompactor(buff, lvl0Sorted, hra);
            long totalN = (long)compactor.count;
            float minItem = compactor.minItem;
            float maxItem = compactor.maxItem;
            List<ReqCompactor> compactors = new ArrayList();
            compactors.add(compactor.reqCompactor);
            ReqSketch sk = new ReqSketch(k, hra, totalN, minItem, maxItem, compactors);
            sk.setMaxNomSize(sk.computeMaxNomSize());
            sk.setRetainedItems(sk.computeTotalRetainedItems());
            return sk;
         default:
            assert preInts == 4;

            long totalN = buff.getLong();
            float minItem = buff.getFloat();
            float maxItem = buff.getFloat();
            List<ReqCompactor> compactors = new ArrayList();

            for(int i = 0; i < numCompactors; ++i) {
               boolean level0sorted = i == 0 ? lvl0Sorted : true;
               Compactor compactor = extractCompactor(buff, level0sorted, hra);
               compactors.add(compactor.reqCompactor);
            }

            ReqSketch sk = new ReqSketch(k, hra, totalN, minItem, maxItem, compactors);
            sk.setMaxNomSize(sk.computeMaxNomSize());
            sk.setRetainedItems(sk.computeTotalRetainedItems());
            return sk;
      }
   }

   static final Compactor extractCompactor(Buffer buff, boolean lvl0Sorted, boolean hra) {
      long state = buff.getLong();
      float sectionSizeFlt = buff.getFloat();
      int sectionSize = Math.round(sectionSizeFlt);
      byte lgWt = buff.getByte();
      byte numSections = buff.getByte();
      buff.incrementPosition(2L);
      int count = buff.getInt();
      float[] arr = new float[count];
      buff.getFloatArray(arr, 0, count);
      float minItem = Float.MAX_VALUE;
      float maxItem = Float.MIN_VALUE;

      for(int i = 0; i < count; ++i) {
         minItem = Math.min(minItem, arr[i]);
         maxItem = Math.max(maxItem, arr[i]);
      }

      int delta = 2 * sectionSize * numSections;
      int nomCap = 2 * delta;
      int cap = Math.max(count, nomCap);
      FloatBuffer fltBuf = FloatBuffer.reconstruct(arr, count, cap, delta, lvl0Sorted, hra);
      ReqCompactor reqCompactor = new ReqCompactor(lgWt, hra, state, sectionSizeFlt, numSections, fltBuf);
      return new Compactor(reqCompactor, minItem, maxItem, count);
   }

   private static byte getFlags(ReqSketch sk) {
      boolean rawItems = sk.getN() <= 4L;
      boolean level0Sorted = ((ReqCompactor)sk.getCompactors().get(0)).getBuffer().isSorted();
      int flags = (sk.isEmpty() ? 4 : 0) | (sk.getHighRankAccuracyMode() ? 8 : 0) | (rawItems ? 16 : 0) | (level0Sorted ? 32 : 0);
      return (byte)flags;
   }

   static SerDeFormat getSerFormat(ReqSketch sk) {
      if (sk.isEmpty()) {
         return ReqSerDe.SerDeFormat.EMPTY;
      } else if (sk.getN() <= 4L) {
         return ReqSerDe.SerDeFormat.RAWITEMS;
      } else {
         return sk.getNumLevels() == 1 ? ReqSerDe.SerDeFormat.EXACT : ReqSerDe.SerDeFormat.ESTIMATION;
      }
   }

   private static SerDeFormat getDeserFormat(boolean empty, boolean rawItems, int numCompactors) {
      if (numCompactors <= 1) {
         if (empty) {
            return ReqSerDe.SerDeFormat.EMPTY;
         } else {
            return rawItems ? ReqSerDe.SerDeFormat.RAWITEMS : ReqSerDe.SerDeFormat.EXACT;
         }
      } else {
         return ReqSerDe.SerDeFormat.ESTIMATION;
      }
   }

   static byte[] toByteArray(ReqSketch sk) {
      SerDeFormat serDeFormat = getSerFormat(sk);
      int bytes = getSerBytes(sk, serDeFormat);
      byte[] arr = new byte[bytes];
      WritableBuffer wbuf = WritableMemory.writableWrap(arr).asWritableBuffer();
      byte preInts = (byte)(serDeFormat == ReqSerDe.SerDeFormat.ESTIMATION ? 4 : 2);
      byte flags = getFlags(sk);
      byte numCompactors = sk.isEmpty() ? 0 : (byte)sk.getNumLevels();
      byte numRawItems = sk.getN() <= 4L ? (byte)((int)sk.getN()) : 0;
      wbuf.putByte(preInts);
      wbuf.putByte((byte)1);
      wbuf.putByte(FAMILY_ID);
      wbuf.putByte(flags);
      wbuf.putShort((short)sk.getK());
      wbuf.putByte(numCompactors);
      wbuf.putByte(numRawItems);
      switch (serDeFormat) {
         case EMPTY:
            assert wbuf.getPosition() == (long)bytes;

            return arr;
         case RAWITEMS:
            ReqCompactor c0 = (ReqCompactor)sk.getCompactors().get(0);
            FloatBuffer fbuf = c0.getBuffer();

            for(int i = 0; i < numRawItems; ++i) {
               wbuf.putFloat(fbuf.getItem(i));
            }

            assert wbuf.getPosition() == (long)bytes;

            return arr;
         case EXACT:
            ReqCompactor c0 = (ReqCompactor)sk.getCompactors().get(0);
            wbuf.putByteArray(c0.toByteArray(), 0, c0.getSerializationBytes());

            assert wbuf.getPosition() == (long)bytes;

            return arr;
         default:
            wbuf.putLong(sk.getN());
            wbuf.putFloat(sk.getMinItem());
            wbuf.putFloat(sk.getMaxItem());

            for(int i = 0; i < numCompactors; ++i) {
               ReqCompactor c = (ReqCompactor)sk.getCompactors().get(i);
               wbuf.putByteArray(c.toByteArray(), 0, c.getSerializationBytes());
            }

            assert wbuf.getPosition() == (long)bytes : wbuf.getPosition() + ", " + bytes;

            return arr;
      }
   }

   static int getSerBytes(ReqSketch sk, SerDeFormat serDeFormat) {
      switch (serDeFormat) {
         case EMPTY:
            return 8;
         case RAWITEMS:
            return ((ReqCompactor)sk.getCompactors().get(0)).getBuffer().getCount() * 4 + 8;
         case EXACT:
            return ((ReqCompactor)sk.getCompactors().get(0)).getSerializationBytes() + 8;
         default:
            int cBytes = 0;

            for(int i = 0; i < sk.getNumLevels(); ++i) {
               cBytes += ((ReqCompactor)sk.getCompactors().get(i)).getSerializationBytes();
            }

            return cBytes + 24;
      }
   }

   static {
      FAMILY_ID = (byte)Family.REQ.getID();
   }

   static enum SerDeFormat {
      EMPTY,
      RAWITEMS,
      EXACT,
      ESTIMATION;
   }

   static class Compactor {
      ReqCompactor reqCompactor;
      float minItem;
      float maxItem;
      int count;

      Compactor(ReqCompactor reqCompactor, float minItem, float maxItem, int count) {
         this.reqCompactor = reqCompactor;
         this.minItem = minItem;
         this.maxItem = maxItem;
         this.count = count;
      }
   }
}
