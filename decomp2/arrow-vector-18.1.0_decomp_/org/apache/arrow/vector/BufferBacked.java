package org.apache.arrow.vector;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;

public interface BufferBacked {
   void load(ArrowFieldNode var1, ArrowBuf var2);

   ArrowBuf unLoad();
}
