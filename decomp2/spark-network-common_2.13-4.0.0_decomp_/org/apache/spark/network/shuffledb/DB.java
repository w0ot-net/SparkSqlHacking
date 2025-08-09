package org.apache.spark.network.shuffledb;

import java.io.Closeable;
import org.apache.spark.annotation.Private;

@Private
public interface DB extends Closeable {
   void put(byte[] var1, byte[] var2);

   byte[] get(byte[] var1);

   void delete(byte[] var1);

   DBIterator iterator();
}
