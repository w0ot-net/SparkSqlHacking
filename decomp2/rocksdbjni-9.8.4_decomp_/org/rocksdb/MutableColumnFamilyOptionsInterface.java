package org.rocksdb;

public interface MutableColumnFamilyOptionsInterface extends AdvancedMutableColumnFamilyOptionsInterface {
   MutableColumnFamilyOptionsInterface setWriteBufferSize(long var1);

   long writeBufferSize();

   MutableColumnFamilyOptionsInterface setDisableAutoCompactions(boolean var1);

   boolean disableAutoCompactions();

   MutableColumnFamilyOptionsInterface setLevel0FileNumCompactionTrigger(int var1);

   int level0FileNumCompactionTrigger();

   MutableColumnFamilyOptionsInterface setMaxCompactionBytes(long var1);

   long maxCompactionBytes();

   MutableColumnFamilyOptionsInterface setMaxBytesForLevelBase(long var1);

   long maxBytesForLevelBase();

   MutableColumnFamilyOptionsInterface setCompressionType(CompressionType var1);

   CompressionType compressionType();
}
