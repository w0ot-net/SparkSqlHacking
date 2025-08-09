package org.iq80.leveldb;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Map;

public interface DBIterator extends Iterator, Closeable {
   void seek(byte[] var1);

   void seekToFirst();

   Map.Entry peekNext();

   boolean hasPrev();

   Map.Entry prev();

   Map.Entry peekPrev();

   void seekToLast();
}
