package org.apache.parquet.io;

import java.io.IOException;
import java.io.OutputStream;

public abstract class PositionOutputStream extends OutputStream {
   public abstract long getPos() throws IOException;
}
