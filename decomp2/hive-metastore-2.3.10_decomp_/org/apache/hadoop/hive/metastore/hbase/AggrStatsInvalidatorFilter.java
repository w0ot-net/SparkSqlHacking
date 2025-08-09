package org.apache.hadoop.hive.metastore.hbase;

import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterBase;
import org.apache.hadoop.hbase.filter.Filter.ReturnCode;
import org.apache.hive.common.util.BloomFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AggrStatsInvalidatorFilter extends FilterBase {
   private static final Logger LOG = LoggerFactory.getLogger(AggrStatsInvalidatorFilter.class.getName());
   private final List entries;
   private final long runEvery;
   private final long maxCacheEntryLife;
   private transient long now;

   public static Filter parseFrom(byte[] serialized) throws DeserializationException {
      try {
         return new AggrStatsInvalidatorFilter(HbaseMetastoreProto.AggrStatsInvalidatorFilter.parseFrom(serialized));
      } catch (InvalidProtocolBufferException e) {
         throw new DeserializationException(e);
      }
   }

   AggrStatsInvalidatorFilter(HbaseMetastoreProto.AggrStatsInvalidatorFilter proto) {
      this.entries = proto.getToInvalidateList();
      this.runEvery = proto.getRunEvery();
      this.maxCacheEntryLife = proto.getMaxCacheEntryLife();
      this.now = System.currentTimeMillis();
   }

   public byte[] toByteArray() throws IOException {
      return HbaseMetastoreProto.AggrStatsInvalidatorFilter.newBuilder().addAllToInvalidate(this.entries).setRunEvery(this.runEvery).setMaxCacheEntryLife(this.maxCacheEntryLife).build().toByteArray();
   }

   public boolean filterAllRemaining() throws IOException {
      return false;
   }

   public Filter.ReturnCode filterKeyValue(Cell cell) throws IOException {
      if (Arrays.equals(CellUtil.cloneQualifier(cell), HBaseReadWrite.AGGR_STATS_BLOOM_COL)) {
         HbaseMetastoreProto.AggrStatsBloomFilter fromCol = HbaseMetastoreProto.AggrStatsBloomFilter.parseFrom(CellUtil.cloneValue(cell));
         BloomFilter bloom = null;
         if (this.now - this.maxCacheEntryLife > fromCol.getAggregatedAt()) {
            return ReturnCode.INCLUDE;
         } else if (this.now - this.runEvery * 2L <= fromCol.getAggregatedAt()) {
            return ReturnCode.NEXT_ROW;
         } else {
            for(HbaseMetastoreProto.AggrStatsInvalidatorFilter.Entry entry : this.entries) {
               if (entry.getDbName().equals(fromCol.getDbName()) && entry.getTableName().equals(fromCol.getTableName())) {
                  if (bloom == null) {
                     bloom = new BloomFilter(fromCol.getBloomFilter().getBitsList(), fromCol.getBloomFilter().getNumBits(), fromCol.getBloomFilter().getNumFuncs());
                  }

                  if (bloom.test(entry.getPartName().toByteArray())) {
                     return ReturnCode.INCLUDE;
                  }
               }
            }

            return ReturnCode.NEXT_ROW;
         }
      } else {
         return ReturnCode.NEXT_COL;
      }
   }
}
