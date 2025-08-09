package org.iq80.leveldb;

import java.io.Closeable;

public interface WriteBatch extends Closeable {
   WriteBatch put(byte[] var1, byte[] var2);

   WriteBatch delete(byte[] var1);
}
