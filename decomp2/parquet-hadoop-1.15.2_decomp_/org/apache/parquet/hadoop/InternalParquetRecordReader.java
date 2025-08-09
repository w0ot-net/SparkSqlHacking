package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.stream.LongStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.filter.UnboundRecordFilter;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.hadoop.api.InitContext;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.hadoop.metadata.FileMetaData;
import org.apache.parquet.hadoop.util.counters.BenchmarkCounter;
import org.apache.parquet.io.ColumnIOFactory;
import org.apache.parquet.io.MessageColumnIO;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.RecordReader;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class InternalParquetRecordReader {
   private static final Logger LOG = LoggerFactory.getLogger(InternalParquetRecordReader.class);
   private ColumnIOFactory columnIOFactory;
   private final FilterCompat.Filter filter;
   private boolean filterRecords;
   private MessageType requestedSchema;
   private MessageType fileSchema;
   private int columnCount;
   private final ReadSupport readSupport;
   private RecordMaterializer recordConverter;
   private Object currentValue;
   private long total;
   private long current;
   private int currentBlock;
   private ParquetFileReader reader;
   private long currentRowIdx;
   private PrimitiveIterator.OfLong rowIdxInFileItr;
   private RecordReader recordReader;
   private boolean strictTypeChecking;
   private long totalTimeSpentReadingBytes;
   private long totalTimeSpentProcessingRecords;
   private long startedAssemblingCurrentBlockAt;
   private long totalCountLoadedSoFar;
   private UnmaterializableRecordCounter unmaterializableRecordCounter;
   private PageReadStore currentRowGroup;

   public InternalParquetRecordReader(ReadSupport readSupport, FilterCompat.Filter filter) {
      this.columnIOFactory = null;
      this.filterRecords = true;
      this.current = 0L;
      this.currentBlock = -1;
      this.currentRowIdx = -1L;
      this.totalCountLoadedSoFar = 0L;
      this.readSupport = readSupport;
      this.filter = filter == null ? FilterCompat.NOOP : filter;
   }

   public InternalParquetRecordReader(ReadSupport readSupport) {
      this(readSupport, FilterCompat.NOOP);
   }

   /** @deprecated */
   @Deprecated
   public InternalParquetRecordReader(ReadSupport readSupport, UnboundRecordFilter filter) {
      this(readSupport, FilterCompat.get(filter));
   }

   private void checkRead() throws IOException {
      if (this.current == this.totalCountLoadedSoFar) {
         if (this.current != 0L) {
            this.totalTimeSpentProcessingRecords += System.currentTimeMillis() - this.startedAssemblingCurrentBlockAt;
            if (LOG.isInfoEnabled()) {
               LOG.info("Assembled and processed " + this.totalCountLoadedSoFar + " records from " + this.columnCount + " columns in " + this.totalTimeSpentProcessingRecords + " ms: " + (float)this.totalCountLoadedSoFar / (float)this.totalTimeSpentProcessingRecords + " rec/ms, " + (float)this.totalCountLoadedSoFar * (float)this.columnCount / (float)this.totalTimeSpentProcessingRecords + " cell/ms");
               long totalTime = this.totalTimeSpentProcessingRecords + this.totalTimeSpentReadingBytes;
               if (totalTime != 0L) {
                  long percentReading = 100L * this.totalTimeSpentReadingBytes / totalTime;
                  long percentProcessing = 100L * this.totalTimeSpentProcessingRecords / totalTime;
                  LOG.info("time spent so far " + percentReading + "% reading (" + this.totalTimeSpentReadingBytes + " ms) and " + percentProcessing + "% processing (" + this.totalTimeSpentProcessingRecords + " ms)");
               }
            }
         }

         if (this.currentRowGroup != null) {
            this.currentRowGroup.close();
         }

         LOG.info("at row " + this.current + ". reading next block");
         long t0 = System.currentTimeMillis();
         this.currentRowGroup = this.reader.readNextFilteredRowGroup();
         if (this.currentRowGroup == null) {
            throw new IOException("expecting more rows but reached last block. Read " + this.current + " out of " + this.total);
         }

         this.resetRowIndexIterator(this.currentRowGroup);
         long timeSpentReading = System.currentTimeMillis() - t0;
         this.totalTimeSpentReadingBytes += timeSpentReading;
         BenchmarkCounter.incrementTime(timeSpentReading);
         if (LOG.isInfoEnabled()) {
            LOG.info("block read in memory in {} ms. row count = {}", timeSpentReading, this.currentRowGroup.getRowCount());
         }

         LOG.debug("initializing Record assembly with requested schema {}", this.requestedSchema);
         MessageColumnIO columnIO = this.columnIOFactory.getColumnIO(this.requestedSchema, this.fileSchema, this.strictTypeChecking);
         this.recordReader = columnIO.getRecordReader(this.currentRowGroup, this.recordConverter, this.filterRecords ? this.filter : FilterCompat.NOOP);
         this.startedAssemblingCurrentBlockAt = System.currentTimeMillis();
         this.totalCountLoadedSoFar += this.currentRowGroup.getRowCount();
         ++this.currentBlock;
      }

   }

   public void close() throws IOException {
      if (this.currentRowGroup != null) {
         this.currentRowGroup.close();
      }

      if (this.reader != null) {
         this.reader.close();
      }

   }

   public Void getCurrentKey() throws IOException, InterruptedException {
      return null;
   }

   public Object getCurrentValue() throws IOException, InterruptedException {
      return this.currentValue;
   }

   public float getProgress() throws IOException, InterruptedException {
      return (float)this.current / (float)this.total;
   }

   public void initialize(ParquetFileReader reader, ParquetReadOptions options) {
      ParquetConfiguration conf = ((ParquetReadOptions)Objects.requireNonNull(options)).getConfiguration();

      for(String property : options.getPropertyNames()) {
         conf.set(property, options.getProperty(property));
      }

      this.reader = reader;
      FileMetaData parquetFileMetadata = reader.getFooter().getFileMetaData();
      this.fileSchema = parquetFileMetadata.getSchema();
      Map<String, String> fileMetadata = parquetFileMetadata.getKeyValueMetaData();
      ReadSupport.ReadContext readContext = this.readSupport.init(new InitContext(conf, toSetMultiMap(fileMetadata), this.fileSchema));
      this.columnIOFactory = new ColumnIOFactory(parquetFileMetadata.getCreatedBy());
      this.requestedSchema = readContext.getRequestedSchema();
      this.columnCount = this.requestedSchema.getPaths().size();
      reader.setRequestedSchema(this.requestedSchema);
      this.recordConverter = this.readSupport.prepareForRead(conf, fileMetadata, this.fileSchema, readContext);
      this.strictTypeChecking = options.isEnabled("parquet.strict.typing", true);
      this.total = reader.getFilteredRecordCount();
      this.unmaterializableRecordCounter = new UnmaterializableRecordCounter(options, this.total);
      this.filterRecords = options.useRecordFilter();
      LOG.info("RecordReader initialized will read a total of {} records.", this.total);
   }

   public void initialize(ParquetFileReader reader, Configuration configuration) throws IOException {
      this.reader = reader;
      FileMetaData parquetFileMetadata = reader.getFooter().getFileMetaData();
      this.fileSchema = parquetFileMetadata.getSchema();
      Map<String, String> fileMetadata = parquetFileMetadata.getKeyValueMetaData();
      ReadSupport.ReadContext readContext = this.readSupport.init(new InitContext(configuration, toSetMultiMap(fileMetadata), this.fileSchema));
      this.columnIOFactory = new ColumnIOFactory(parquetFileMetadata.getCreatedBy());
      this.requestedSchema = readContext.getRequestedSchema();
      this.columnCount = this.requestedSchema.getPaths().size();
      reader.setRequestedSchema(this.requestedSchema);
      this.recordConverter = this.readSupport.prepareForRead(configuration, fileMetadata, this.fileSchema, readContext);
      this.strictTypeChecking = configuration.getBoolean("parquet.strict.typing", true);
      this.total = reader.getFilteredRecordCount();
      this.unmaterializableRecordCounter = new UnmaterializableRecordCounter(configuration, this.total);
      this.filterRecords = configuration.getBoolean("parquet.filter.record-level.enabled", true);
      LOG.info("RecordReader initialized will read a total of {} records.", this.total);
   }

   public boolean nextKeyValue() throws IOException, InterruptedException {
      boolean recordFound = false;

      while(!recordFound) {
         if (this.current >= this.total) {
            return false;
         }

         try {
            this.checkRead();
            ++this.current;

            try {
               this.currentValue = this.recordReader.read();
               if (this.rowIdxInFileItr != null && this.rowIdxInFileItr.hasNext()) {
                  this.currentRowIdx = this.rowIdxInFileItr.next();
               } else {
                  this.currentRowIdx = -1L;
               }
            } catch (RecordMaterializer.RecordMaterializationException e) {
               this.unmaterializableRecordCounter.incErrors(e);
               LOG.debug("skipping a corrupt record");
               continue;
            }

            if (this.recordReader.shouldSkipCurrentRecord()) {
               LOG.debug("skipping record");
            } else if (this.currentValue == null) {
               this.current = this.totalCountLoadedSoFar;
               LOG.debug("filtered record reader reached end of block");
            } else {
               recordFound = true;
               LOG.debug("read value: {}", this.currentValue);
            }
         } catch (RuntimeException e) {
            throw new ParquetDecodingException(String.format("Can not read value at %d in block %d in file %s", this.current, this.currentBlock, this.reader.getFile()), e);
         }
      }

      return true;
   }

   private static Map toSetMultiMap(Map map) {
      Map<K, Set<V>> setMultiMap = new HashMap();

      for(Map.Entry entry : map.entrySet()) {
         setMultiMap.put(entry.getKey(), Collections.singleton(entry.getValue()));
      }

      return Collections.unmodifiableMap(setMultiMap);
   }

   public long getCurrentRowIndex() {
      return this.current != 0L && this.rowIdxInFileItr != null ? this.currentRowIdx : -1L;
   }

   private void resetRowIndexIterator(PageReadStore pages) {
      final Optional<Long> rowGroupRowIdxOffset = pages.getRowIndexOffset();
      if (!rowGroupRowIdxOffset.isPresent()) {
         this.rowIdxInFileItr = null;
      } else {
         this.currentRowIdx = -1L;
         final PrimitiveIterator.OfLong rowIdxInRowGroupItr;
         if (pages.getRowIndexes().isPresent()) {
            rowIdxInRowGroupItr = (PrimitiveIterator.OfLong)pages.getRowIndexes().get();
         } else {
            rowIdxInRowGroupItr = LongStream.range(0L, pages.getRowCount()).iterator();
         }

         this.rowIdxInFileItr = new PrimitiveIterator.OfLong() {
            public long nextLong() {
               return (Long)rowGroupRowIdxOffset.get() + rowIdxInRowGroupItr.nextLong();
            }

            public boolean hasNext() {
               return rowIdxInRowGroupItr.hasNext();
            }

            public Long next() {
               return (Long)rowGroupRowIdxOffset.get() + rowIdxInRowGroupItr.next();
            }
         };
      }
   }
}
