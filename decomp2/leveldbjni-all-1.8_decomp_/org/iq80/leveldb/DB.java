package org.iq80.leveldb;

import java.io.Closeable;

public interface DB extends Iterable, Closeable {
   byte[] get(byte[] var1) throws DBException;

   byte[] get(byte[] var1, ReadOptions var2) throws DBException;

   DBIterator iterator();

   DBIterator iterator(ReadOptions var1);

   void put(byte[] var1, byte[] var2) throws DBException;

   void delete(byte[] var1) throws DBException;

   void write(WriteBatch var1) throws DBException;

   WriteBatch createWriteBatch();

   Snapshot put(byte[] var1, byte[] var2, WriteOptions var3) throws DBException;

   Snapshot delete(byte[] var1, WriteOptions var2) throws DBException;

   Snapshot write(WriteBatch var1, WriteOptions var2) throws DBException;

   Snapshot getSnapshot();

   long[] getApproximateSizes(Range... var1);

   String getProperty(String var1);

   void suspendCompactions() throws InterruptedException;

   void resumeCompactions();

   void compactRange(byte[] var1, byte[] var2) throws DBException;
}
