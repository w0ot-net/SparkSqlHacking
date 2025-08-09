package org.iq80.leveldb;

import java.util.Comparator;

public interface DBComparator extends Comparator {
   String name();

   byte[] findShortestSeparator(byte[] var1, byte[] var2);

   byte[] findShortSuccessor(byte[] var1);
}
