package org.apache.spark.unsafe;

import java.io.IOException;

public abstract class KVIterator {
   public abstract boolean next() throws IOException;

   public abstract Object getKey();

   public abstract Object getValue();

   public abstract void close();
}
