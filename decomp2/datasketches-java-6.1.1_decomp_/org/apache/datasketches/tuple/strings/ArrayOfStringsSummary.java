package org.apache.datasketches.tuple.strings;

import [Ljava.lang.String;;
import java.nio.charset.StandardCharsets;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.tuple.UpdatableSummary;
import org.apache.datasketches.tuple.Util;

public final class ArrayOfStringsSummary implements UpdatableSummary {
   private String[] nodesArr = null;

   ArrayOfStringsSummary() {
      this.nodesArr = null;
   }

   ArrayOfStringsSummary(String[] nodesArr) {
      this.nodesArr = (String[])((String;)nodesArr).clone();
      checkNumNodes(nodesArr.length);
   }

   ArrayOfStringsSummary(Memory mem) {
      Buffer buf = mem.asBuffer();
      int totBytes = buf.getInt();
      checkInBytes(mem, totBytes);
      int nodes = buf.getByte();
      checkNumNodes(nodes);
      String[] nodesArr = new String[nodes];

      for(int i = 0; i < nodes; ++i) {
         int len = buf.getInt();
         byte[] byteArr = new byte[len];
         buf.getByteArray(byteArr, 0, len);
         nodesArr[i] = new String(byteArr, StandardCharsets.UTF_8);
      }

      this.nodesArr = nodesArr;
   }

   public ArrayOfStringsSummary copy() {
      ArrayOfStringsSummary nodes = new ArrayOfStringsSummary(this.nodesArr);
      return nodes;
   }

   public byte[] toByteArray() {
      ComputeBytes cb = new ComputeBytes(this.nodesArr);
      int totBytes = cb.totBytes_;
      byte[] out = new byte[totBytes];
      WritableMemory wmem = WritableMemory.writableWrap(out);
      WritableBuffer wbuf = wmem.asWritableBuffer();
      wbuf.putInt(totBytes);
      wbuf.putByte(cb.numNodes_);

      for(int i = 0; i < cb.numNodes_; ++i) {
         wbuf.putInt(cb.nodeLengthsArr_[i]);
         wbuf.putByteArray(cb.nodeBytesArr_[i], 0, cb.nodeLengthsArr_[i]);
      }

      assert wbuf.getPosition() == (long)totBytes;

      return out;
   }

   public ArrayOfStringsSummary update(String[] value) {
      if (this.nodesArr == null) {
         this.nodesArr = (String[])((String;)value).clone();
      }

      return this;
   }

   public int hashCode() {
      return (int)Util.stringArrHash(this.nodesArr);
   }

   public boolean equals(Object summary) {
      if (summary != null && summary instanceof ArrayOfStringsSummary) {
         String thatStr = Util.stringConcat(((ArrayOfStringsSummary)summary).nodesArr);
         String thisStr = Util.stringConcat(this.nodesArr);
         return thisStr.equals(thatStr);
      } else {
         return false;
      }
   }

   public String[] getValue() {
      return (String[])this.nodesArr.clone();
   }

   static void checkNumNodes(int numNodes) {
      if (numNodes > 127) {
         throw new SketchesArgumentException("Number of nodes cannot exceed 127.");
      }
   }

   static void checkInBytes(Memory mem, int totBytes) {
      if (mem.getCapacity() < (long)totBytes) {
         throw new SketchesArgumentException("Incoming Memory has insufficient capacity.");
      }
   }

   private static class ComputeBytes {
      final byte numNodes_;
      final int[] nodeLengthsArr_;
      final byte[][] nodeBytesArr_;
      final int totBytes_;

      ComputeBytes(String[] nodesArr) {
         this.numNodes_ = (byte)nodesArr.length;
         ArrayOfStringsSummary.checkNumNodes(this.numNodes_);
         this.nodeLengthsArr_ = new int[this.numNodes_];
         this.nodeBytesArr_ = new byte[this.numNodes_][];
         int sumNodeBytes = 0;

         for(int i = 0; i < this.numNodes_; ++i) {
            this.nodeBytesArr_[i] = nodesArr[i].getBytes(StandardCharsets.UTF_8);
            this.nodeLengthsArr_[i] = this.nodeBytesArr_[i].length;
            sumNodeBytes += this.nodeLengthsArr_[i];
         }

         this.totBytes_ = sumNodeBytes + (this.numNodes_ + 1) * 4 + 1;
      }
   }
}
