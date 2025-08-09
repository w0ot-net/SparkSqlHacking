package org.rocksdb;

import java.util.List;

public interface AdvancedColumnFamilyOptionsInterface {
   AdvancedColumnFamilyOptionsInterface setMinWriteBufferNumberToMerge(int var1);

   int minWriteBufferNumberToMerge();

   AdvancedColumnFamilyOptionsInterface setMaxWriteBufferNumberToMaintain(int var1);

   int maxWriteBufferNumberToMaintain();

   AdvancedColumnFamilyOptionsInterface setInplaceUpdateSupport(boolean var1);

   boolean inplaceUpdateSupport();

   AdvancedColumnFamilyOptionsInterface setBloomLocality(int var1);

   int bloomLocality();

   AdvancedColumnFamilyOptionsInterface setCompressionPerLevel(List var1);

   List compressionPerLevel();

   AdvancedColumnFamilyOptionsInterface setNumLevels(int var1);

   int numLevels();

   AdvancedColumnFamilyOptionsInterface setLevelCompactionDynamicLevelBytes(boolean var1);

   boolean levelCompactionDynamicLevelBytes();

   AdvancedColumnFamilyOptionsInterface setMaxCompactionBytes(long var1);

   long maxCompactionBytes();

   ColumnFamilyOptionsInterface setCompactionStyle(CompactionStyle var1);

   CompactionStyle compactionStyle();

   AdvancedColumnFamilyOptionsInterface setCompactionPriority(CompactionPriority var1);

   CompactionPriority compactionPriority();

   AdvancedColumnFamilyOptionsInterface setCompactionOptionsUniversal(CompactionOptionsUniversal var1);

   CompactionOptionsUniversal compactionOptionsUniversal();

   AdvancedColumnFamilyOptionsInterface setCompactionOptionsFIFO(CompactionOptionsFIFO var1);

   CompactionOptionsFIFO compactionOptionsFIFO();

   AdvancedColumnFamilyOptionsInterface setOptimizeFiltersForHits(boolean var1);

   boolean optimizeFiltersForHits();

   AdvancedColumnFamilyOptionsInterface setForceConsistencyChecks(boolean var1);

   boolean forceConsistencyChecks();
}
