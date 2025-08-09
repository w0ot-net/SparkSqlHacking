package org.apache.orc;

import java.io.Closeable;
import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public interface RecordReader extends Closeable {
   boolean nextBatch(VectorizedRowBatch var1) throws IOException;

   long getRowNumber() throws IOException;

   float getProgress() throws IOException;

   void close() throws IOException;

   void seekToRow(long var1) throws IOException;
}
