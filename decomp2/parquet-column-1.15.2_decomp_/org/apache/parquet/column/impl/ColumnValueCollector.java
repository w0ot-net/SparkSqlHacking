package org.apache.parquet.column.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.column.values.bloomfilter.AdaptiveBlockSplitBloomFilter;
import org.apache.parquet.column.values.bloomfilter.BlockSplitBloomFilter;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;
import org.apache.parquet.io.api.Binary;

class ColumnValueCollector {
   private final ColumnDescriptor path;
   private final boolean statisticsEnabled;
   private final boolean sizeStatisticsEnabled;
   private BloomFilterWriter bloomFilterWriter;
   private BloomFilter bloomFilter;
   private Statistics statistics;
   private SizeStatistics.Builder sizeStatisticsBuilder;

   ColumnValueCollector(ColumnDescriptor path, BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      this.path = path;
      this.statisticsEnabled = props.getStatisticsEnabled(path);
      this.sizeStatisticsEnabled = props.getSizeStatisticsEnabled(path);
      this.resetPageStatistics();
      this.initBloomFilter(bloomFilterWriter, props);
   }

   void resetPageStatistics() {
      this.statistics = this.statisticsEnabled ? Statistics.createStats(this.path.getPrimitiveType()) : Statistics.noopStats(this.path.getPrimitiveType());
      this.sizeStatisticsBuilder = this.sizeStatisticsEnabled ? SizeStatistics.newBuilder(this.path.getPrimitiveType(), this.path.getMaxRepetitionLevel(), this.path.getMaxDefinitionLevel()) : SizeStatistics.noopBuilder(this.path.getPrimitiveType(), this.path.getMaxRepetitionLevel(), this.path.getMaxDefinitionLevel());
   }

   void writeNull(int repetitionLevel, int definitionLevel) {
      this.statistics.incrementNumNulls();
      this.sizeStatisticsBuilder.add(repetitionLevel, definitionLevel);
   }

   void write(boolean value, int repetitionLevel, int definitionLevel) {
      this.statistics.updateStats(value);
      this.sizeStatisticsBuilder.add(repetitionLevel, definitionLevel);
   }

   void write(int value, int repetitionLevel, int definitionLevel) {
      this.statistics.updateStats(value);
      this.sizeStatisticsBuilder.add(repetitionLevel, definitionLevel);
      this.bloomFilter.insertHash(this.bloomFilter.hash(value));
   }

   void write(long value, int repetitionLevel, int definitionLevel) {
      this.statistics.updateStats(value);
      this.sizeStatisticsBuilder.add(repetitionLevel, definitionLevel);
      this.bloomFilter.insertHash(this.bloomFilter.hash(value));
   }

   void write(float value, int repetitionLevel, int definitionLevel) {
      this.statistics.updateStats(value);
      this.sizeStatisticsBuilder.add(repetitionLevel, definitionLevel);
      this.bloomFilter.insertHash(this.bloomFilter.hash(value));
   }

   void write(double value, int repetitionLevel, int definitionLevel) {
      this.statistics.updateStats(value);
      this.sizeStatisticsBuilder.add(repetitionLevel, definitionLevel);
      this.bloomFilter.insertHash(this.bloomFilter.hash(value));
   }

   void write(Binary value, int repetitionLevel, int definitionLevel) {
      this.statistics.updateStats(value);
      this.sizeStatisticsBuilder.add(repetitionLevel, definitionLevel, value);
      this.bloomFilter.insertHash(this.bloomFilter.hash(value));
   }

   void initBloomFilter(BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      this.bloomFilterWriter = bloomFilterWriter;
      if (bloomFilterWriter == null) {
         this.bloomFilter = new BloomFilter() {
            public void writeTo(OutputStream out) throws IOException {
            }

            public void insertHash(long hash) {
            }

            public boolean findHash(long hash) {
               return false;
            }

            public int getBitsetSize() {
               return 0;
            }

            public long hash(int value) {
               return 0L;
            }

            public long hash(long value) {
               return 0L;
            }

            public long hash(double value) {
               return 0L;
            }

            public long hash(float value) {
               return 0L;
            }

            public long hash(Binary value) {
               return 0L;
            }

            public long hash(Object value) {
               return 0L;
            }

            public BloomFilter.HashStrategy getHashStrategy() {
               return null;
            }

            public BloomFilter.Algorithm getAlgorithm() {
               return null;
            }

            public BloomFilter.Compression getCompression() {
               return null;
            }
         };
      } else {
         int maxBloomFilterSize = props.getMaxBloomFilterBytes();
         OptionalLong ndv = props.getBloomFilterNDV(this.path);
         OptionalDouble fpp = props.getBloomFilterFPP(this.path);
         if (ndv.isPresent()) {
            int optimalNumOfBits = BlockSplitBloomFilter.optimalNumOfBits(ndv.getAsLong(), fpp.getAsDouble());
            this.bloomFilter = new BlockSplitBloomFilter(optimalNumOfBits / 8, maxBloomFilterSize);
         } else if (props.getAdaptiveBloomFilterEnabled(this.path)) {
            int numCandidates = props.getBloomFilterCandidatesCount(this.path);
            this.bloomFilter = new AdaptiveBlockSplitBloomFilter(maxBloomFilterSize, numCandidates, fpp.getAsDouble(), this.path);
         } else {
            this.bloomFilter = new BlockSplitBloomFilter(maxBloomFilterSize, maxBloomFilterSize);
         }

      }
   }

   void finalizeColumnChunk() {
      if (this.bloomFilterWriter != null) {
         this.bloomFilterWriter.writeBloomFilter(this.bloomFilter);
      }

   }

   Statistics getStatistics() {
      return this.statistics;
   }

   SizeStatistics getSizeStatistics() {
      return this.sizeStatisticsBuilder.build();
   }
}
