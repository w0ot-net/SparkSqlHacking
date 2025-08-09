package org.apache.spark.network.shuffledb;

import java.io.Closeable;
import java.util.Iterator;
import org.apache.spark.annotation.Private;

@Private
public interface DBIterator extends Iterator, Closeable {
   void seek(byte[] var1);

   default void remove() {
      throw new UnsupportedOperationException();
   }
}
