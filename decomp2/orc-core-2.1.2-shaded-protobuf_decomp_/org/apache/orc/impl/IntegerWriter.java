package org.apache.orc.impl;

import java.io.IOException;
import java.util.function.Consumer;

public interface IntegerWriter {
   void getPosition(PositionRecorder var1) throws IOException;

   void write(long var1) throws IOException;

   void flush() throws IOException;

   long estimateMemory();

   void changeIv(Consumer var1);
}
