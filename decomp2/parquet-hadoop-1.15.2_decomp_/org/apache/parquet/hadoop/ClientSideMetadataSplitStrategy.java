package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.filter2.compat.RowGroupFilter;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ClientSideMetadataSplitStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(ClientSideMetadataSplitStrategy.class);

   List getSplits(Configuration configuration, List footers, long maxSplitSize, long minSplitSize, ReadSupport.ReadContext readContext) throws IOException {
      List<ParquetInputSplit> splits = new ArrayList();
      FilterCompat.Filter filter = ParquetInputFormat.getFilter(configuration);
      long rowGroupsDropped = 0L;
      long totalRowGroups = 0L;

      for(Footer footer : footers) {
         Path file = footer.getFile();
         LOG.debug("{}", file);
         FileSystem fs = file.getFileSystem(configuration);
         FileStatus fileStatus = fs.getFileStatus(file);
         ParquetMetadata parquetMetaData = footer.getParquetMetadata();
         List<BlockMetaData> blocks = parquetMetaData.getBlocks();
         totalRowGroups += (long)blocks.size();
         List<BlockMetaData> filteredBlocks = RowGroupFilter.filterRowGroups(filter, blocks, parquetMetaData.getFileMetaData().getSchema());
         rowGroupsDropped += (long)(blocks.size() - filteredBlocks.size());
         if (!filteredBlocks.isEmpty()) {
            BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(fileStatus, 0L, fileStatus.getLen());
            splits.addAll(generateSplits(filteredBlocks, fileBlockLocations, fileStatus, readContext.getRequestedSchema().toString(), readContext.getReadSupportMetadata(), minSplitSize, maxSplitSize));
         }
      }

      if (rowGroupsDropped > 0L && totalRowGroups > 0L) {
         int percentDropped = (int)((double)rowGroupsDropped / (double)totalRowGroups * (double)100.0F);
         LOG.info("Dropping {} row groups that do not pass filter predicate! ({}%)", rowGroupsDropped, percentDropped);
      } else {
         LOG.info("There were no row groups that could be dropped due to filter predicates");
      }

      return splits;
   }

   static List generateSplits(List rowGroupBlocks, BlockLocation[] hdfsBlocksArray, FileStatus fileStatus, String requestedSchema, Map readSupportMetadata, long minSplitSize, long maxSplitSize) throws IOException {
      List<SplitInfo> splitRowGroups = generateSplitInfo(rowGroupBlocks, hdfsBlocksArray, minSplitSize, maxSplitSize);
      List<ParquetInputSplit> resultSplits = new ArrayList();

      for(SplitInfo splitInfo : splitRowGroups) {
         ParquetInputSplit split = splitInfo.getParquetInputSplit(fileStatus, requestedSchema, readSupportMetadata);
         resultSplits.add(split);
      }

      return resultSplits;
   }

   static List generateSplitInfo(List rowGroupBlocks, BlockLocation[] hdfsBlocksArray, long minSplitSize, long maxSplitSize) {
      if (maxSplitSize >= minSplitSize && maxSplitSize >= 0L && minSplitSize >= 0L) {
         HDFSBlocks hdfsBlocks = new HDFSBlocks(hdfsBlocksArray);
         hdfsBlocks.checkBelongingToANewHDFSBlock((BlockMetaData)rowGroupBlocks.get(0));
         SplitInfo currentSplit = new SplitInfo(hdfsBlocks.getCurrentBlock());
         List<SplitInfo> splitRowGroups = new ArrayList();
         checkSorted(rowGroupBlocks);

         for(BlockMetaData rowGroupMetadata : rowGroupBlocks) {
            if (hdfsBlocks.checkBelongingToANewHDFSBlock(rowGroupMetadata) && currentSplit.getCompressedByteSize() >= minSplitSize && currentSplit.getCompressedByteSize() > 0L || currentSplit.getCompressedByteSize() >= maxSplitSize) {
               splitRowGroups.add(currentSplit);
               currentSplit = new SplitInfo(hdfsBlocks.getCurrentBlock());
            }

            currentSplit.addRowGroup(rowGroupMetadata);
         }

         if (currentSplit.getRowGroupCount() > 0) {
            splitRowGroups.add(currentSplit);
         }

         return splitRowGroups;
      } else {
         throw new ParquetDecodingException("maxSplitSize and minSplitSize should be positive and max should be greater or equal to the minSplitSize: maxSplitSize = " + maxSplitSize + "; minSplitSize is " + minSplitSize);
      }
   }

   private static void checkSorted(List rowGroupBlocks) {
      long previousOffset = 0L;

      for(BlockMetaData rowGroup : rowGroupBlocks) {
         long currentOffset = rowGroup.getStartingPos();
         if (currentOffset < previousOffset) {
            throw new ParquetDecodingException("row groups are not sorted: previous row groups starts at " + previousOffset + ", current row group starts at " + currentOffset);
         }
      }

   }

   private static class HDFSBlocks {
      BlockLocation[] hdfsBlocks;
      int currentStartHdfsBlockIndex;
      int currentMidPointHDFSBlockIndex;

      private HDFSBlocks(BlockLocation[] hdfsBlocks) {
         this.currentStartHdfsBlockIndex = 0;
         this.currentMidPointHDFSBlockIndex = 0;
         this.hdfsBlocks = hdfsBlocks;
         Comparator<BlockLocation> comparator = new Comparator() {
            public int compare(BlockLocation b1, BlockLocation b2) {
               return Long.signum(b1.getOffset() - b2.getOffset());
            }
         };
         Arrays.sort(hdfsBlocks, comparator);
      }

      private long getHDFSBlockEndingPosition(int hdfsBlockIndex) {
         BlockLocation hdfsBlock = this.hdfsBlocks[hdfsBlockIndex];
         return hdfsBlock.getOffset() + hdfsBlock.getLength() - 1L;
      }

      private boolean checkBelongingToANewHDFSBlock(BlockMetaData rowGroupMetadata) {
         boolean isNewHdfsBlock = false;
         long rowGroupMidPoint = rowGroupMetadata.getStartingPos() + rowGroupMetadata.getCompressedSize() / 2L;

         while(rowGroupMidPoint > this.getHDFSBlockEndingPosition(this.currentMidPointHDFSBlockIndex)) {
            isNewHdfsBlock = true;
            ++this.currentMidPointHDFSBlockIndex;
            if (this.currentMidPointHDFSBlockIndex >= this.hdfsBlocks.length) {
               throw new ParquetDecodingException("the row group is not in hdfs blocks in the file: midpoint of row groups is " + rowGroupMidPoint + ", the end of the hdfs block is " + this.getHDFSBlockEndingPosition(this.currentMidPointHDFSBlockIndex - 1));
            }
         }

         while(rowGroupMetadata.getStartingPos() > this.getHDFSBlockEndingPosition(this.currentStartHdfsBlockIndex)) {
            ++this.currentStartHdfsBlockIndex;
            if (this.currentStartHdfsBlockIndex >= this.hdfsBlocks.length) {
               throw new ParquetDecodingException("The row group does not start in this file: row group offset is " + rowGroupMetadata.getStartingPos() + " but the end of hdfs blocks of file is " + this.getHDFSBlockEndingPosition(this.currentStartHdfsBlockIndex));
            }
         }

         return isNewHdfsBlock;
      }

      public BlockLocation getCurrentBlock() {
         return this.hdfsBlocks[this.currentStartHdfsBlockIndex];
      }
   }

   static class SplitInfo {
      List rowGroups = new ArrayList();
      BlockLocation hdfsBlock;
      long compressedByteSize = 0L;

      public SplitInfo(BlockLocation currentBlock) {
         this.hdfsBlock = currentBlock;
      }

      private void addRowGroup(BlockMetaData rowGroup) {
         this.rowGroups.add(rowGroup);
         this.compressedByteSize += rowGroup.getCompressedSize();
      }

      public long getCompressedByteSize() {
         return this.compressedByteSize;
      }

      public List getRowGroups() {
         return this.rowGroups;
      }

      int getRowGroupCount() {
         return this.rowGroups.size();
      }

      public ParquetInputSplit getParquetInputSplit(FileStatus fileStatus, String requestedSchema, Map readSupportMetadata) throws IOException {
         MessageType requested = MessageTypeParser.parseMessageType(requestedSchema);
         long length = 0L;

         for(BlockMetaData block : this.getRowGroups()) {
            for(ColumnChunkMetaData column : block.getColumns()) {
               if (requested.containsPath(column.getPath().toArray())) {
                  length += column.getTotalSize();
               }
            }
         }

         BlockMetaData lastRowGroup = (BlockMetaData)this.getRowGroups().get(this.getRowGroupCount() - 1);
         long end = lastRowGroup.getStartingPos() + lastRowGroup.getTotalByteSize();
         long[] rowGroupOffsets = new long[this.getRowGroupCount()];

         for(int i = 0; i < rowGroupOffsets.length; ++i) {
            rowGroupOffsets[i] = ((BlockMetaData)this.getRowGroups().get(i)).getStartingPos();
         }

         return new ParquetInputSplit(fileStatus.getPath(), this.hdfsBlock.getOffset(), end, length, this.hdfsBlock.getHosts(), rowGroupOffsets);
      }
   }
}
