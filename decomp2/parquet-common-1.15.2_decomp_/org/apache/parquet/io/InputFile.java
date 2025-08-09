package org.apache.parquet.io;

import java.io.IOException;

public interface InputFile {
   long getLength() throws IOException;

   SeekableInputStream newStream() throws IOException;
}
