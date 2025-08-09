package org.apache.orc.impl.reader.tree;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.impl.PositionProvider;
import org.apache.orc.impl.reader.StripePlanner;

public abstract class BatchReader {
   public final TypeReader rootType;
   protected int vectorColumnCount = -1;

   public BatchReader(TypeReader rootType) {
      this.rootType = rootType;
   }

   public abstract void startStripe(StripePlanner var1, TypeReader.ReadPhase var2) throws IOException;

   public void setVectorColumnCount(int vectorColumnCount) {
      this.vectorColumnCount = vectorColumnCount;
   }

   public abstract void nextBatch(VectorizedRowBatch var1, int var2, TypeReader.ReadPhase var3) throws IOException;

   protected void resetBatch(VectorizedRowBatch batch, int batchSize) {
      batch.selectedInUse = false;
      batch.size = batchSize;
   }

   public abstract void skipRows(long var1, TypeReader.ReadPhase var3) throws IOException;

   public abstract void seek(PositionProvider[] var1, TypeReader.ReadPhase var2) throws IOException;
}
