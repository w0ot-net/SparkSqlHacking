package org.apache.orc.impl;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;

public interface IntegerReader {
   void seek(PositionProvider var1) throws IOException;

   void skip(long var1) throws IOException;

   boolean hasNext() throws IOException;

   long next() throws IOException;

   void nextVector(ColumnVector var1, long[] var2, int var3) throws IOException;

   void nextVector(ColumnVector var1, int[] var2, int var3) throws IOException;
}
