package org.apache.spark.util.kvstore;

import java.io.Closeable;
import java.util.Iterator;
import java.util.List;
import org.apache.spark.annotation.Private;

@Private
public interface KVStoreIterator extends Iterator, Closeable {
   List next(int var1);

   boolean skip(long var1);
}
