package org.apache.datasketches.req;

public interface ReqDebug {
   void emitStart(ReqSketch var1);

   void emitStartCompress();

   void emitCompressDone();

   void emitAllHorizList();

   void emitMustAddCompactor();

   void emitCompactingStart(byte var1);

   void emitNewCompactor(byte var1);

   void emitAdjSecSizeNumSec(byte var1);

   void emitCompactionDetail(int var1, int var2, int var3, int var4, boolean var5);

   void emitCompactionDone(byte var1);
}
