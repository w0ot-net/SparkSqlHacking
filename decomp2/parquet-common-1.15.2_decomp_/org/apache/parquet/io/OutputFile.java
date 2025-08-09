package org.apache.parquet.io;

import java.io.IOException;

public interface OutputFile {
   PositionOutputStream create(long var1) throws IOException;

   PositionOutputStream createOrOverwrite(long var1) throws IOException;

   boolean supportsBlockSize();

   long defaultBlockSize();

   default String getPath() {
      return null;
   }
}
