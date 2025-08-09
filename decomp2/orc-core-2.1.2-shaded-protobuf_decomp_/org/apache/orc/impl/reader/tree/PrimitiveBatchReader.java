package org.apache.orc.impl.reader.tree;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.impl.PositionProvider;
import org.apache.orc.impl.reader.StripePlanner;

public class PrimitiveBatchReader extends BatchReader {
   public PrimitiveBatchReader(TypeReader rowReader) {
      super(rowReader);
   }

   public void nextBatch(VectorizedRowBatch batch, int batchSize, TypeReader.ReadPhase readPhase) throws IOException {
      batch.cols[0].reset();
      batch.cols[0].ensureSize(batchSize, false);
      this.rootType.nextVector(batch.cols[0], (boolean[])null, batchSize, batch, readPhase);
      this.resetBatch(batch, batchSize);
   }

   public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
      this.rootType.startStripe(planner, readPhase);
   }

   public void skipRows(long rows, TypeReader.ReadPhase readPhase) throws IOException {
      this.rootType.skipRows(rows, readPhase);
   }

   public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
      this.rootType.seek(index, readPhase);
   }
}
