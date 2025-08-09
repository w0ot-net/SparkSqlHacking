package org.rocksdb;

import java.util.Collection;
import java.util.List;

public interface ColumnFamilyOptionsInterface extends AdvancedColumnFamilyOptionsInterface {
   long DEFAULT_COMPACTION_MEMTABLE_MEMORY_BUDGET = 536870912L;

   ColumnFamilyOptionsInterface oldDefaults(int var1, int var2);

   ColumnFamilyOptionsInterface optimizeForSmallDb();

   ColumnFamilyOptionsInterface optimizeForSmallDb(Cache var1);

   ColumnFamilyOptionsInterface optimizeForPointLookup(long var1);

   ColumnFamilyOptionsInterface optimizeLevelStyleCompaction();

   ColumnFamilyOptionsInterface optimizeLevelStyleCompaction(long var1);

   ColumnFamilyOptionsInterface optimizeUniversalStyleCompaction();

   ColumnFamilyOptionsInterface optimizeUniversalStyleCompaction(long var1);

   ColumnFamilyOptionsInterface setComparator(BuiltinComparator var1);

   ColumnFamilyOptionsInterface setComparator(AbstractComparator var1);

   ColumnFamilyOptionsInterface setMergeOperatorName(String var1);

   ColumnFamilyOptionsInterface setMergeOperator(MergeOperator var1);

   ColumnFamilyOptionsInterface setCompactionFilter(AbstractCompactionFilter var1);

   AbstractCompactionFilter compactionFilter();

   ColumnFamilyOptionsInterface setCompactionFilterFactory(AbstractCompactionFilterFactory var1);

   AbstractCompactionFilterFactory compactionFilterFactory();

   ColumnFamilyOptionsInterface useFixedLengthPrefixExtractor(int var1);

   ColumnFamilyOptionsInterface useCappedPrefixExtractor(int var1);

   ColumnFamilyOptionsInterface setLevelZeroFileNumCompactionTrigger(int var1);

   int levelZeroFileNumCompactionTrigger();

   ColumnFamilyOptionsInterface setLevelZeroSlowdownWritesTrigger(int var1);

   int levelZeroSlowdownWritesTrigger();

   ColumnFamilyOptionsInterface setLevelZeroStopWritesTrigger(int var1);

   int levelZeroStopWritesTrigger();

   ColumnFamilyOptionsInterface setMaxBytesForLevelMultiplier(double var1);

   double maxBytesForLevelMultiplier();

   ColumnFamilyOptionsInterface setMaxTableFilesSizeFIFO(long var1);

   long maxTableFilesSizeFIFO();

   MemTableConfig memTableConfig();

   ColumnFamilyOptionsInterface setMemTableConfig(MemTableConfig var1);

   String memTableFactoryName();

   TableFormatConfig tableFormatConfig();

   ColumnFamilyOptionsInterface setTableFormatConfig(TableFormatConfig var1);

   String tableFactoryName();

   ColumnFamilyOptionsInterface setCfPaths(Collection var1);

   List cfPaths();

   ColumnFamilyOptionsInterface setBottommostCompressionType(CompressionType var1);

   CompressionType bottommostCompressionType();

   ColumnFamilyOptionsInterface setBottommostCompressionOptions(CompressionOptions var1);

   CompressionOptions bottommostCompressionOptions();

   ColumnFamilyOptionsInterface setCompressionOptions(CompressionOptions var1);

   CompressionOptions compressionOptions();

   ColumnFamilyOptionsInterface setSstPartitionerFactory(SstPartitionerFactory var1);

   SstPartitionerFactory sstPartitionerFactory();

   ColumnFamilyOptionsInterface setMemtableMaxRangeDeletions(int var1);

   int memtableMaxRangeDeletions();

   ColumnFamilyOptionsInterface setCompactionThreadLimiter(ConcurrentTaskLimiter var1);

   ConcurrentTaskLimiter compactionThreadLimiter();
}
