package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;

public abstract class PositionedOutputStream extends OutputStream {
   public abstract void getPosition(PositionRecorder var1) throws IOException;

   public abstract long getBufferSize();

   public abstract void changeIv(Consumer var1);
}
