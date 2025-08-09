package org.apache.orc.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.function.Consumer;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.hive.ql.io.sarg.PredicateLeaf;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;
import org.apache.hadoop.hive.ql.io.sarg.PredicateLeaf.Operator;
import org.apache.hadoop.hive.ql.io.sarg.PredicateLeaf.Type;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument.TruthValue;
import org.apache.hadoop.hive.ql.util.TimestampUtils;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.io.Text;
import org.apache.orc.BooleanColumnStatistics;
import org.apache.orc.CollectionColumnStatistics;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.CompressionCodec;
import org.apache.orc.DataReader;
import org.apache.orc.DateColumnStatistics;
import org.apache.orc.DecimalColumnStatistics;
import org.apache.orc.DoubleColumnStatistics;
import org.apache.orc.IntegerColumnStatistics;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcFilterContext;
import org.apache.orc.OrcProto;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.StringColumnStatistics;
import org.apache.orc.StripeInformation;
import org.apache.orc.TimestampColumnStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.filter.BatchFilter;
import org.apache.orc.impl.filter.FilterFactory;
import org.apache.orc.impl.reader.ReaderEncryption;
import org.apache.orc.impl.reader.StripePlanner;
import org.apache.orc.impl.reader.tree.BatchReader;
import org.apache.orc.impl.reader.tree.TypeReader;
import org.apache.orc.util.BloomFilter;
import org.apache.orc.util.BloomFilterIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordReaderImpl implements RecordReader {
   static final Logger LOG = LoggerFactory.getLogger(RecordReaderImpl.class);
   private static final boolean isLogDebugEnabled;
   public static final OrcProto.ColumnStatistics EMPTY_COLUMN_STATISTICS;
   protected final Path path;
   private final long firstRow;
   private final List stripes = new ArrayList();
   private OrcProto.StripeFooter stripeFooter;
   private final long totalRowCount;
   protected final TypeDescription schema;
   private final boolean[] fileIncluded;
   private final long rowIndexStride;
   private long rowInStripe = 0L;
   private long followRowInStripe = 0L;
   private int currentStripe = -1;
   private long rowBaseInStripe = 0L;
   private long rowCountInStripe = 0L;
   private final BatchReader reader;
   private final OrcIndex indexes;
   private final boolean[] rowIndexColsToRead;
   private final SargApplier sargApp;
   private boolean[] includedRowGroups = null;
   private final DataReader dataReader;
   private final int maxDiskRangeChunkLimit;
   private final StripePlanner planner;
   private final TypeReader.ReadPhase startReadPhase;
   private boolean needsFollowColumnsRead;
   private final boolean noSelectedVector;
   private final boolean skipBloomFilters;
   static final String[] BAD_CPP_BLOOM_FILTER_VERSIONS;
   private static final String TRANSLATED_SARG_SEPARATOR = "_";

   static int findColumns(SchemaEvolution evolution, String columnName) {
      TypeDescription fileColumn = findColumnType(evolution, columnName);
      return fileColumn == null ? -1 : fileColumn.getId();
   }

   static TypeDescription findColumnType(SchemaEvolution evolution, String columnName) {
      try {
         TypeDescription readerColumn = evolution.getReaderBaseSchema().findSubtype(columnName, evolution.isSchemaEvolutionCaseAware);
         return evolution.getFileType(readerColumn);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Filter could not find column with name: " + columnName + " on " + String.valueOf(evolution.getReaderBaseSchema()), e);
      }
   }

   static TypeDescription findMostCommonColumn(SchemaEvolution evolution, String columnName) {
      try {
         TypeDescription readerColumn = evolution.getReaderBaseSchema().findSubtype(columnName, evolution.isSchemaEvolutionCaseAware);

         do {
            TypeDescription fileColumn = evolution.getFileType(readerColumn);
            if (fileColumn != null) {
               return fileColumn;
            }

            readerColumn = readerColumn.getParent();
         } while(readerColumn != null);

         return null;
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Filter could not find column with name: " + columnName + " on " + String.valueOf(evolution.getReaderBaseSchema()), e);
      }
   }

   public static int[] mapSargColumnsToOrcInternalColIdx(List sargLeaves, SchemaEvolution evolution) {
      int[] result = new int[sargLeaves.size()];

      for(int i = 0; i < sargLeaves.size(); ++i) {
         int colNum = -1;

         try {
            String colName = ((PredicateLeaf)sargLeaves.get(i)).getColumnName();
            colNum = findColumns(evolution, colName);
         } catch (IllegalArgumentException e) {
            LOG.debug("{}", e.getMessage());
         }

         result[i] = colNum;
      }

      return result;
   }

   protected RecordReaderImpl(ReaderImpl fileReader, Reader.Options options) throws IOException {
      OrcFile.WriterVersion writerVersion = fileReader.getWriterVersion();
      SchemaEvolution evolution;
      if (options.getSchema() == null) {
         LOG.debug("Reader schema not provided -- using file schema " + String.valueOf(fileReader.getSchema()));
         evolution = new SchemaEvolution(fileReader.getSchema(), (TypeDescription)null, options);
      } else {
         evolution = new SchemaEvolution(fileReader.getSchema(), options.getSchema(), options);
         if (LOG.isDebugEnabled() && evolution.hasConversion()) {
            Logger var10000 = LOG;
            String var10001 = fileReader.path.toString();
            var10000.debug("ORC file " + var10001 + " has data type conversion --\nreader schema: " + options.getSchema().toString() + "\nfile schema:   " + String.valueOf(fileReader.getSchema()));
         }
      }

      this.noSelectedVector = !options.useSelected();
      LOG.debug("noSelectedVector={}", this.noSelectedVector);
      this.schema = evolution.getReaderSchema();
      this.path = fileReader.path;
      this.rowIndexStride = (long)fileReader.rowIndexStride;
      boolean ignoreNonUtf8BloomFilter = OrcConf.IGNORE_NON_UTF8_BLOOM_FILTERS.getBoolean(fileReader.conf);
      ReaderEncryption encryption = fileReader.getEncryption();
      this.fileIncluded = evolution.getFileIncluded();
      SearchArgument sarg = options.getSearchArgument();
      boolean[] rowIndexCols = new boolean[evolution.getFileIncluded().length];
      if (sarg != null && this.rowIndexStride > 0L) {
         this.sargApp = new SargApplier(sarg, this.rowIndexStride, evolution, writerVersion, fileReader.useUTCTimestamp, fileReader.writerUsedProlepticGregorian(), fileReader.options.getConvertToProlepticGregorian());
         this.sargApp.setRowIndexCols(rowIndexCols);
      } else {
         this.sargApp = null;
      }

      long rows = 0L;
      long skippedRows = 0L;
      long offset = options.getOffset();
      long maxOffset = options.getMaxOffset();

      for(StripeInformation stripe : fileReader.getStripes()) {
         long stripeStart = stripe.getOffset();
         if (offset > stripeStart) {
            skippedRows += stripe.getNumberOfRows();
         } else if (stripeStart < maxOffset) {
            this.stripes.add(stripe);
            rows += stripe.getNumberOfRows();
         }
      }

      this.maxDiskRangeChunkLimit = OrcConf.ORC_MAX_DISK_RANGE_CHUNK_LIMIT.getInt(fileReader.conf);
      Boolean zeroCopy = options.getUseZeroCopy();
      if (zeroCopy == null) {
         zeroCopy = OrcConf.USE_ZEROCOPY.getBoolean(fileReader.conf);
      }

      if (options.getDataReader() != null) {
         this.dataReader = options.getDataReader().clone();
      } else {
         InStream.StreamOptions unencryptedOptions = InStream.options().withCodec(OrcCodecPool.getCodec(fileReader.getCompressionKind())).withBufferSize(fileReader.getCompressionSize());
         DataReaderProperties.Builder builder = DataReaderProperties.builder().withCompression(unencryptedOptions).withFileSystemSupplier(fileReader.getFileSystemSupplier()).withPath(fileReader.path).withMaxDiskRangeChunkLimit(this.maxDiskRangeChunkLimit).withZeroCopy(zeroCopy).withMinSeekSize(options.minSeekSize()).withMinSeekSizeTolerance(options.minSeekSizeTolerance());
         FSDataInputStream file = fileReader.takeFile();
         if (file != null) {
            builder.withFile(file);
         }

         this.dataReader = RecordReaderUtils.createDefaultDataReader(builder.build());
      }

      this.firstRow = skippedRows;
      this.totalRowCount = rows;
      Boolean skipCorrupt = options.getSkipCorruptRecords();
      if (skipCorrupt == null) {
         skipCorrupt = OrcConf.SKIP_CORRUPT_DATA.getBoolean(fileReader.conf);
      }

      String[] filterCols = null;
      Consumer<OrcFilterContext> filterCallBack = null;
      String filePath = options.allowPluginFilters() ? fileReader.getFileSystem().makeQualified(fileReader.path).toString() : null;
      BatchFilter filter = FilterFactory.createBatchFilter(options, evolution.getReaderBaseSchema(), evolution.isSchemaEvolutionCaseAware(), fileReader.getFileVersion(), false, filePath, fileReader.conf);
      if (filter != null) {
         filterCallBack = filter;
         filterCols = filter.getColumnNames();
      }

      SortedSet<Integer> filterColIds = new TreeSet();
      if (filterCols != null) {
         for(String colName : filterCols) {
            TypeDescription expandCol = findColumnType(evolution, colName);
            if (expandCol == null || expandCol.getId() == -1) {
               filterColIds.add(-1);
               expandCol = findMostCommonColumn(evolution, colName);
            }

            while(expandCol != null && expandCol.getId() != -1) {
               filterColIds.add(expandCol.getId());
               rowIndexCols[expandCol.getId()] = true;
               expandCol = expandCol.getParent();
            }
         }

         this.startReadPhase = TypeReader.ReadPhase.LEADERS;
         LOG.debug("Using startReadPhase: {} with filter columns: {}", this.startReadPhase, filterColIds);
      } else {
         this.startReadPhase = TypeReader.ReadPhase.ALL;
      }

      boolean hasTrue = false;

      for(boolean value : rowIndexCols) {
         if (value) {
            hasTrue = true;
            break;
         }
      }

      this.rowIndexColsToRead = hasTrue ? rowIndexCols : null;
      TreeReaderFactory.ReaderContext readerContext = (new TreeReaderFactory.ReaderContext()).setSchemaEvolution(evolution).setFilterCallback(filterColIds, filterCallBack).skipCorrupt(skipCorrupt).fileFormat(fileReader.getFileVersion()).useUTCTimestamp(fileReader.useUTCTimestamp).setProlepticGregorian(fileReader.writerUsedProlepticGregorian(), fileReader.options.getConvertToProlepticGregorian()).setEncryption(encryption);
      this.reader = TreeReaderFactory.createRootReader(evolution.getReaderSchema(), readerContext);
      this.skipBloomFilters = this.hasBadBloomFilters(fileReader.getFileTail().getFooter());
      int columns = evolution.getFileSchema().getMaximumId() + 1;
      this.indexes = new OrcIndex(new OrcProto.RowIndex[columns], new OrcProto.Stream.Kind[columns], new OrcProto.BloomFilterIndex[columns]);
      this.planner = new StripePlanner(evolution.getFileSchema(), encryption, this.dataReader, writerVersion, ignoreNonUtf8BloomFilter, (long)this.maxDiskRangeChunkLimit, filterColIds);

      try {
         this.advanceToNextRow(this.reader, 0L, true);
      } catch (Exception e) {
         this.close();
         long stripeId = this.stripes.size() == 0 ? 0L : ((StripeInformation)this.stripes.get(0)).getStripeId();
         throw new IOException(String.format("Problem opening stripe %d footer in %s.", stripeId, this.path), e);
      }
   }

   private boolean hasBadBloomFilters(OrcProto.Footer footer) {
      if (footer.getWriter() != 1) {
         return false;
      } else if (!footer.hasSoftwareVersion()) {
         return true;
      } else {
         String fullVersion = footer.getSoftwareVersion();
         String version = fullVersion;
         if (fullVersion.contains("-")) {
            version = fullVersion.substring(0, fullVersion.indexOf(45));
         }

         for(String v : BAD_CPP_BLOOM_FILTER_VERSIONS) {
            if (v.equals(version)) {
               return true;
            }
         }

         return false;
      }
   }

   public OrcProto.StripeFooter readStripeFooter(StripeInformation stripe) throws IOException {
      return this.dataReader.readStripeFooter(stripe);
   }

   static ValueRange getValueRange(ColumnStatistics index, PredicateLeaf predicate, boolean useUTCTimestamp) {
      if (index.getNumberOfValues() == 0L) {
         return new ValueRange(predicate, index.hasNull());
      } else if (index instanceof IntegerColumnStatistics) {
         IntegerColumnStatistics stats = (IntegerColumnStatistics)index;
         Long min = stats.getMinimum();
         Long max = stats.getMaximum();
         return new ValueRange(predicate, min, max, stats.hasNull());
      } else if (index instanceof CollectionColumnStatistics) {
         CollectionColumnStatistics stats = (CollectionColumnStatistics)index;
         Long min = stats.getMinimumChildren();
         Long max = stats.getMaximumChildren();
         return new ValueRange(predicate, min, max, stats.hasNull());
      } else if (index instanceof DoubleColumnStatistics) {
         DoubleColumnStatistics stats = (DoubleColumnStatistics)index;
         Double min = stats.getMinimum();
         Double max = stats.getMaximum();
         return new ValueRange(predicate, min, max, stats.hasNull());
      } else if (index instanceof StringColumnStatistics) {
         StringColumnStatistics stats = (StringColumnStatistics)index;
         return new ValueRange(predicate, stats.getLowerBound(), stats.getUpperBound(), stats.hasNull(), stats.getMinimum() == null, stats.getMaximum() == null);
      } else if (index instanceof DateColumnStatistics) {
         DateColumnStatistics stats = (DateColumnStatistics)index;
         ChronoLocalDate min = stats.getMinimumLocalDate();
         ChronoLocalDate max = stats.getMaximumLocalDate();
         return new ValueRange(predicate, min, max, stats.hasNull());
      } else if (index instanceof DecimalColumnStatistics) {
         DecimalColumnStatistics stats = (DecimalColumnStatistics)index;
         HiveDecimal min = stats.getMinimum();
         HiveDecimal max = stats.getMaximum();
         return new ValueRange(predicate, min, max, stats.hasNull());
      } else if (index instanceof TimestampColumnStatistics) {
         TimestampColumnStatistics stats = (TimestampColumnStatistics)index;
         Timestamp min = useUTCTimestamp ? stats.getMinimumUTC() : stats.getMinimum();
         Timestamp max = useUTCTimestamp ? stats.getMaximumUTC() : stats.getMaximum();
         return new ValueRange(predicate, min, max, stats.hasNull());
      } else if (index instanceof BooleanColumnStatistics) {
         BooleanColumnStatistics stats = (BooleanColumnStatistics)index;
         Boolean min = stats.getFalseCount() == 0L;
         Boolean max = stats.getTrueCount() != 0L;
         return new ValueRange(predicate, min, max, stats.hasNull());
      } else {
         return new ValueRange(predicate, (Comparable)null, (Comparable)null, index.hasNull(), false, false, true, false);
      }
   }

   static SearchArgument.TruthValue evaluatePredicateProto(OrcProto.ColumnStatistics statsProto, PredicateLeaf predicate, OrcProto.Stream.Kind kind, OrcProto.ColumnEncoding encoding, OrcProto.BloomFilter bloomFilter, OrcFile.WriterVersion writerVersion, TypeDescription type) {
      return evaluatePredicateProto(statsProto, predicate, kind, encoding, bloomFilter, writerVersion, type, true, false);
   }

   static SearchArgument.TruthValue evaluatePredicateProto(OrcProto.ColumnStatistics statsProto, PredicateLeaf predicate, OrcProto.Stream.Kind kind, OrcProto.ColumnEncoding encoding, OrcProto.BloomFilter bloomFilter, OrcFile.WriterVersion writerVersion, TypeDescription type, boolean writerUsedProlepticGregorian, boolean useUTCTimestamp) {
      if (statsProto == EMPTY_COLUMN_STATISTICS) {
         return TruthValue.YES_NO_NULL;
      } else {
         ColumnStatistics cs = ColumnStatisticsImpl.deserialize((TypeDescription)null, statsProto, writerUsedProlepticGregorian, true);
         ValueRange range = getValueRange(cs, predicate, useUTCTimestamp);
         TypeDescription.Category category = type.getCategory();
         if (category == TypeDescription.Category.TIMESTAMP) {
            if (!writerVersion.includes(OrcFile.WriterVersion.ORC_135)) {
               LOG.debug("Not using predication pushdown on {} because it doesn't include ORC-135. Writer version: {}", predicate.getColumnName(), writerVersion);
               return range.addNull(TruthValue.YES_NO);
            }

            if (predicate.getType() != Type.TIMESTAMP && predicate.getType() != Type.DATE && predicate.getType() != Type.STRING) {
               return range.addNull(TruthValue.YES_NO);
            }
         } else {
            if (writerVersion == OrcFile.WriterVersion.ORC_135 && category == TypeDescription.Category.DECIMAL && type.getPrecision() <= 18) {
               LOG.debug("Not using predicate push down on {}, because the file doesn't include ORC-517. Writer version: {}", predicate.getColumnName(), writerVersion);
               return TruthValue.YES_NO_NULL;
            }

            if ((category == TypeDescription.Category.DOUBLE || category == TypeDescription.Category.FLOAT) && cs instanceof DoubleColumnStatistics) {
               DoubleColumnStatistics dstas = (DoubleColumnStatistics)cs;
               if (Double.isNaN(dstas.getSum())) {
                  LOG.debug("Not using predication pushdown on {} because stats contain NaN values", predicate.getColumnName());
                  return dstas.hasNull() ? TruthValue.YES_NO_NULL : TruthValue.YES_NO;
               }
            }
         }

         return evaluatePredicateRange(predicate, range, BloomFilterIO.deserialize(kind, encoding, writerVersion, type.getCategory(), bloomFilter), useUTCTimestamp);
      }
   }

   public static SearchArgument.TruthValue evaluatePredicate(ColumnStatistics stats, PredicateLeaf predicate, BloomFilter bloomFilter) {
      return evaluatePredicate(stats, predicate, bloomFilter, false);
   }

   public static SearchArgument.TruthValue evaluatePredicate(ColumnStatistics stats, PredicateLeaf predicate, BloomFilter bloomFilter, boolean useUTCTimestamp) {
      ValueRange range = getValueRange(stats, predicate, useUTCTimestamp);
      return evaluatePredicateRange(predicate, range, bloomFilter, useUTCTimestamp);
   }

   static SearchArgument.TruthValue evaluatePredicateRange(PredicateLeaf predicate, ValueRange range, BloomFilter bloomFilter, boolean useUTCTimestamp) {
      if (!range.isValid()) {
         return TruthValue.NO;
      } else if (!range.hasValues()) {
         return predicate.getOperator() == Operator.IS_NULL ? TruthValue.YES : TruthValue.NULL;
      } else if (!range.isComparable()) {
         return range.hasNulls ? TruthValue.YES_NO_NULL : TruthValue.YES_NO;
      } else {
         Comparable baseObj = (Comparable)predicate.getLiteral();
         Comparable predObj = getBaseObjectForComparison(predicate.getType(), baseObj);
         SearchArgument.TruthValue result = evaluatePredicateMinMax(predicate, predObj, range);
         return shouldEvaluateBloomFilter(predicate, result, bloomFilter) ? evaluatePredicateBloomFilter(predicate, predObj, bloomFilter, range.hasNulls, useUTCTimestamp) : result;
      }
   }

   private static boolean shouldEvaluateBloomFilter(PredicateLeaf predicate, SearchArgument.TruthValue result, BloomFilter bloomFilter) {
      return bloomFilter != null && result != TruthValue.NO_NULL && result != TruthValue.NO && (predicate.getOperator().equals(Operator.EQUALS) || predicate.getOperator().equals(Operator.NULL_SAFE_EQUALS) || predicate.getOperator().equals(Operator.IN));
   }

   private static SearchArgument.TruthValue evaluatePredicateMinMax(PredicateLeaf predicate, Comparable predObj, ValueRange range) {
      switch (predicate.getOperator()) {
         case NULL_SAFE_EQUALS:
            Location loc = range.compare(predObj);
            if (loc != RecordReaderImpl.Location.BEFORE && loc != RecordReaderImpl.Location.AFTER) {
               return TruthValue.YES_NO;
            }

            return TruthValue.NO;
         case EQUALS:
            Location loc = range.compare(predObj);
            if (range.isSingleton() && loc == RecordReaderImpl.Location.MIN) {
               return range.addNull(TruthValue.YES);
            } else {
               if (loc != RecordReaderImpl.Location.BEFORE && loc != RecordReaderImpl.Location.AFTER) {
                  return range.addNull(TruthValue.YES_NO);
               }

               return range.addNull(TruthValue.NO);
            }
         case LESS_THAN:
            Location loc = range.compare(predObj);
            if (loc == RecordReaderImpl.Location.AFTER) {
               return range.addNull(TruthValue.YES);
            } else {
               if (loc != RecordReaderImpl.Location.BEFORE && loc != RecordReaderImpl.Location.MIN) {
                  return range.addNull(TruthValue.YES_NO);
               }

               return range.addNull(TruthValue.NO);
            }
         case LESS_THAN_EQUALS:
            Location loc = range.compare(predObj);
            if (loc != RecordReaderImpl.Location.AFTER && loc != RecordReaderImpl.Location.MAX && (loc != RecordReaderImpl.Location.MIN || !range.isSingleton())) {
               return loc == RecordReaderImpl.Location.BEFORE ? range.addNull(TruthValue.NO) : range.addNull(TruthValue.YES_NO);
            } else {
               return range.addNull(TruthValue.YES);
            }
         case IN:
            if (range.isSingleton()) {
               for(Object arg : predicate.getLiteralList()) {
                  predObj = getBaseObjectForComparison(predicate.getType(), (Comparable)arg);
                  if (range.compare(predObj) == RecordReaderImpl.Location.MIN) {
                     return range.addNull(TruthValue.YES);
                  }
               }

               return range.addNull(TruthValue.NO);
            } else {
               for(Object arg : predicate.getLiteralList()) {
                  predObj = getBaseObjectForComparison(predicate.getType(), (Comparable)arg);
                  Location loc = range.compare(predObj);
                  if (loc == RecordReaderImpl.Location.MIN || loc == RecordReaderImpl.Location.MIDDLE || loc == RecordReaderImpl.Location.MAX) {
                     return range.addNull(TruthValue.YES_NO);
                  }
               }

               return range.addNull(TruthValue.NO);
            }
         case BETWEEN:
            List<Object> args = predicate.getLiteralList();
            if (args != null && !args.isEmpty()) {
               Comparable predObj1 = getBaseObjectForComparison(predicate.getType(), (Comparable)args.get(0));
               Location loc = range.compare(predObj1);
               if (loc != RecordReaderImpl.Location.BEFORE && loc != RecordReaderImpl.Location.MIN) {
                  if (loc == RecordReaderImpl.Location.AFTER) {
                     return range.addNull(TruthValue.NO);
                  }

                  return range.addNull(TruthValue.YES_NO);
               }

               Comparable predObj2 = getBaseObjectForComparison(predicate.getType(), (Comparable)args.get(1));
               Location loc2 = range.compare(predObj2);
               if (loc2 != RecordReaderImpl.Location.AFTER && loc2 != RecordReaderImpl.Location.MAX) {
                  if (loc2 == RecordReaderImpl.Location.BEFORE) {
                     return range.addNull(TruthValue.NO);
                  }

                  return range.addNull(TruthValue.YES_NO);
               }

               return range.addNull(TruthValue.YES);
            }

            return range.addNull(TruthValue.YES_NO);
         case IS_NULL:
            return range.hasNulls ? TruthValue.YES_NO : TruthValue.NO;
         default:
            return range.addNull(TruthValue.YES_NO);
      }
   }

   private static SearchArgument.TruthValue evaluatePredicateBloomFilter(PredicateLeaf predicate, Object predObj, BloomFilter bloomFilter, boolean hasNull, boolean useUTCTimestamp) {
      switch (predicate.getOperator()) {
         case NULL_SAFE_EQUALS:
            return checkInBloomFilter(bloomFilter, predObj, false, useUTCTimestamp);
         case EQUALS:
            return checkInBloomFilter(bloomFilter, predObj, hasNull, useUTCTimestamp);
         case LESS_THAN:
         case LESS_THAN_EQUALS:
         default:
            return hasNull ? TruthValue.YES_NO_NULL : TruthValue.YES_NO;
         case IN:
            for(Object arg : predicate.getLiteralList()) {
               Object predObjItem = getBaseObjectForComparison(predicate.getType(), (Comparable)arg);
               SearchArgument.TruthValue result = checkInBloomFilter(bloomFilter, predObjItem, hasNull, useUTCTimestamp);
               if (result == TruthValue.YES_NO_NULL || result == TruthValue.YES_NO) {
                  return result;
               }
            }

            return hasNull ? TruthValue.NO_NULL : TruthValue.NO;
      }
   }

   private static SearchArgument.TruthValue checkInBloomFilter(BloomFilter bf, Object predObj, boolean hasNull, boolean useUTCTimestamp) {
      SearchArgument.TruthValue result = hasNull ? TruthValue.NO_NULL : TruthValue.NO;
      if (predObj instanceof Long) {
         if (bf.testLong((Long)predObj)) {
            result = TruthValue.YES_NO_NULL;
         }
      } else if (predObj instanceof Double) {
         if (bf.testDouble((Double)predObj)) {
            result = TruthValue.YES_NO_NULL;
         }
      } else if (!(predObj instanceof String) && !(predObj instanceof Text) && !(predObj instanceof HiveDecimalWritable) && !(predObj instanceof BigDecimal)) {
         if (predObj instanceof Timestamp) {
            if (useUTCTimestamp) {
               if (bf.testLong(((Timestamp)predObj).getTime())) {
                  result = TruthValue.YES_NO_NULL;
               }
            } else if (bf.testLong(SerializationUtils.convertToUtc(TimeZone.getDefault(), ((Timestamp)predObj).getTime()))) {
               result = TruthValue.YES_NO_NULL;
            }
         } else if (predObj instanceof ChronoLocalDate) {
            if (bf.testLong(((ChronoLocalDate)predObj).toEpochDay())) {
               result = TruthValue.YES_NO_NULL;
            }
         } else if (predObj == null && !hasNull) {
            result = TruthValue.NO;
         } else {
            result = TruthValue.YES_NO_NULL;
         }
      } else if (bf.testString(predObj.toString())) {
         result = TruthValue.YES_NO_NULL;
      }

      if (result == TruthValue.YES_NO_NULL && !hasNull) {
         result = TruthValue.YES_NO;
      }

      LOG.debug("Bloom filter evaluation: {}", result);
      return result;
   }

   private static Comparable getBaseObjectForComparison(PredicateLeaf.Type type, Comparable obj) {
      if (obj == null) {
         return null;
      } else {
         switch (type) {
            case BOOLEAN:
               if (obj instanceof Boolean) {
                  return obj;
               }

               return Boolean.valueOf(obj.toString());
            case DATE:
               if (obj instanceof ChronoLocalDate) {
                  return obj;
               }

               if (obj instanceof Date) {
                  return ((Date)obj).toLocalDate();
               }

               if (obj instanceof java.util.Date) {
                  return LocalDateTime.ofInstant(((java.util.Date)obj).toInstant(), ZoneOffset.UTC).toLocalDate();
               }

               if (obj instanceof String) {
                  return LocalDate.parse((String)obj);
               }

               if (obj instanceof Timestamp) {
                  return ((Timestamp)obj).toLocalDateTime().toLocalDate();
               }
               break;
            case DECIMAL:
               if (obj instanceof Boolean) {
                  return new HiveDecimalWritable((Boolean)obj ? HiveDecimal.ONE : HiveDecimal.ZERO);
               }

               if (obj instanceof Integer) {
                  return new HiveDecimalWritable((long)(Integer)obj);
               }

               if (obj instanceof Long) {
                  return new HiveDecimalWritable((Long)obj);
               }

               if (obj instanceof Float || obj instanceof Double || obj instanceof String) {
                  return new HiveDecimalWritable(obj.toString());
               }

               if (obj instanceof BigDecimal) {
                  return new HiveDecimalWritable(HiveDecimal.create((BigDecimal)obj));
               }

               if (obj instanceof HiveDecimal) {
                  return new HiveDecimalWritable((HiveDecimal)obj);
               }

               if (obj instanceof HiveDecimalWritable) {
                  return obj;
               }

               if (obj instanceof Timestamp) {
                  return new HiveDecimalWritable(Double.toString(TimestampUtils.getDouble((Timestamp)obj)));
               }
               break;
            case FLOAT:
               if (obj instanceof Number) {
                  return ((Number)obj).doubleValue();
               }

               if (obj instanceof HiveDecimal) {
                  return ((HiveDecimal)obj).doubleValue();
               }

               if (obj instanceof String) {
                  return Double.valueOf(obj.toString());
               }

               if (obj instanceof Timestamp) {
                  return TimestampUtils.getDouble((Timestamp)obj);
               }
               break;
            case LONG:
               if (obj instanceof Number) {
                  return ((Number)obj).longValue();
               }

               if (obj instanceof HiveDecimal) {
                  return ((HiveDecimal)obj).longValue();
               }

               if (obj instanceof String) {
                  return Long.valueOf(obj.toString());
               }
               break;
            case STRING:
               if (obj instanceof ChronoLocalDate) {
                  ChronoLocalDate date = (ChronoLocalDate)obj;
                  return date.format(DateTimeFormatter.ISO_LOCAL_DATE.withChronology(date.getChronology()));
               }

               return obj.toString();
            case TIMESTAMP:
               if (obj instanceof Timestamp) {
                  return obj;
               }

               if (obj instanceof Integer) {
                  return new Timestamp(((Number)obj).longValue());
               }

               if (obj instanceof Float) {
                  return TimestampUtils.doubleToTimestamp(((Float)obj).doubleValue());
               }

               if (obj instanceof Double) {
                  return TimestampUtils.doubleToTimestamp((Double)obj);
               }

               if (obj instanceof HiveDecimal) {
                  return TimestampUtils.decimalToTimestamp((HiveDecimal)obj);
               }

               if (obj instanceof HiveDecimalWritable) {
                  return TimestampUtils.decimalToTimestamp(((HiveDecimalWritable)obj).getHiveDecimal());
               }

               if (obj instanceof java.util.Date) {
                  return new Timestamp(((java.util.Date)obj).getTime());
               }

               if (obj instanceof ChronoLocalDate) {
                  return new Timestamp(((ChronoLocalDate)obj).atTime(LocalTime.MIDNIGHT).toInstant(ZoneOffset.UTC).getEpochSecond() * 1000L);
               }
         }

         throw new SargCastException(String.format("ORC SARGS could not convert from %s to %s", obj.getClass().getSimpleName(), type));
      }
   }

   protected boolean[] pickRowGroups() throws IOException {
      if (this.rowIndexColsToRead != null) {
         this.readCurrentStripeRowIndex();
      }

      return this.sargApp == null ? null : this.sargApp.pickRowGroups((StripeInformation)this.stripes.get(this.currentStripe), this.indexes.getRowGroupIndex(), this.skipBloomFilters ? null : this.indexes.getBloomFilterKinds(), this.stripeFooter.getColumnsList(), this.skipBloomFilters ? null : this.indexes.getBloomFilterIndex(), false);
   }

   private void clearStreams() {
      this.planner.clearStreams();
   }

   private void readStripe() throws IOException {
      StripeInformation stripe = this.beginReadStripe();
      this.planner.parseStripe(stripe, this.fileIncluded);
      this.includedRowGroups = this.pickRowGroups();
      if (this.includedRowGroups != null) {
         while(this.rowInStripe < this.rowCountInStripe && !this.includedRowGroups[(int)(this.rowInStripe / this.rowIndexStride)]) {
            this.rowInStripe = Math.min(this.rowCountInStripe, this.rowInStripe + this.rowIndexStride);
         }
      }

      if (this.rowInStripe < this.rowCountInStripe) {
         this.planner.readData(this.indexes, this.includedRowGroups, false, this.startReadPhase);
         this.reader.startStripe(this.planner, this.startReadPhase);
         this.needsFollowColumnsRead = true;
         if (this.rowInStripe != 0L) {
            this.seekToRowEntry(this.reader, (int)(this.rowInStripe / this.rowIndexStride), this.startReadPhase);
         }
      }

   }

   private StripeInformation beginReadStripe() throws IOException {
      StripeInformation stripe = (StripeInformation)this.stripes.get(this.currentStripe);
      this.stripeFooter = this.readStripeFooter(stripe);
      this.clearStreams();
      this.rowCountInStripe = stripe.getNumberOfRows();
      this.rowInStripe = 0L;
      this.followRowInStripe = 0L;
      this.rowBaseInStripe = 0L;

      for(int i = 0; i < this.currentStripe; ++i) {
         this.rowBaseInStripe += ((StripeInformation)this.stripes.get(i)).getNumberOfRows();
      }

      OrcProto.RowIndex[] rowIndex = this.indexes.getRowGroupIndex();

      for(int i = 0; i < rowIndex.length; ++i) {
         rowIndex[i] = null;
      }

      return stripe;
   }

   private void advanceStripe() throws IOException {
      this.rowInStripe = this.rowCountInStripe;

      while(this.rowInStripe >= this.rowCountInStripe && this.currentStripe < this.stripes.size() - 1) {
         ++this.currentStripe;
         this.readStripe();
      }

   }

   private int computeRGIdx(long rowIdx) {
      return this.rowIndexStride == 0L ? 0 : (int)(rowIdx / this.rowIndexStride);
   }

   private boolean advanceToNextRow(BatchReader reader, long nextRow, boolean canAdvanceStripe) throws IOException {
      long nextRowInStripe = nextRow - this.rowBaseInStripe;
      if (this.rowIndexStride != 0L && this.includedRowGroups != null && nextRowInStripe < this.rowCountInStripe) {
         int rowGroup = this.computeRGIdx(nextRowInStripe);
         if (!this.includedRowGroups[rowGroup]) {
            while(rowGroup < this.includedRowGroups.length && !this.includedRowGroups[rowGroup]) {
               ++rowGroup;
            }

            if (rowGroup >= this.includedRowGroups.length) {
               if (canAdvanceStripe) {
                  this.advanceStripe();
               }

               return canAdvanceStripe;
            }

            nextRowInStripe = Math.min(this.rowCountInStripe, (long)rowGroup * this.rowIndexStride);
         }
      }

      if (nextRowInStripe >= this.rowCountInStripe) {
         if (canAdvanceStripe) {
            this.advanceStripe();
         }

         return canAdvanceStripe;
      } else {
         if (nextRowInStripe != this.rowInStripe) {
            if (this.rowIndexStride != 0L) {
               int rowGroup = (int)(nextRowInStripe / this.rowIndexStride);
               this.seekToRowEntry(reader, rowGroup, this.startReadPhase);
               reader.skipRows(nextRowInStripe - (long)rowGroup * this.rowIndexStride, this.startReadPhase);
            } else {
               reader.skipRows(nextRowInStripe - this.rowInStripe, this.startReadPhase);
            }

            this.rowInStripe = nextRowInStripe;
         }

         return true;
      }
   }

   public boolean nextBatch(VectorizedRowBatch batch) throws IOException {
      try {
         int batchSize;
         do {
            if (this.rowInStripe >= this.rowCountInStripe) {
               ++this.currentStripe;
               if (this.currentStripe >= this.stripes.size()) {
                  batch.size = 0;
                  return false;
               }

               this.readStripe();
               this.followRowInStripe = this.rowInStripe;
            }

            batchSize = this.computeBatchSize((long)batch.getMaxSize());
            this.reader.setVectorColumnCount(batch.getDataColumnCount());
            this.reader.nextBatch(batch, batchSize, this.startReadPhase);
            if (this.startReadPhase == TypeReader.ReadPhase.LEADERS && batch.size > 0) {
               this.reader.nextBatch(batch, batchSize, this.prepareFollowReaders(this.rowInStripe, this.followRowInStripe));
               this.followRowInStripe = this.rowInStripe + (long)batchSize;
            }

            this.rowInStripe += (long)batchSize;
            this.advanceToNextRow(this.reader, this.rowInStripe + this.rowBaseInStripe, true);
         } while(batchSize != 0 && batch.size == 0);

         if (this.noSelectedVector) {
            batch.size = batchSize;
            batch.selectedInUse = false;
         }

         return batchSize != 0;
      } catch (IOException e) {
         throw new IOException("Error reading file: " + String.valueOf(this.path), e);
      }
   }

   private TypeReader.ReadPhase prepareFollowReaders(long toFollowRow, long fromFollowRow) throws IOException {
      int needRG = this.computeRGIdx(toFollowRow);
      int readRG = this.computeRGIdx(fromFollowRow - 1L);
      long skipRows;
      if (needRG == readRG && toFollowRow >= fromFollowRow) {
         skipRows = toFollowRow - fromFollowRow;
      } else {
         skipRows = toFollowRow - (long)needRG * this.rowIndexStride;
      }

      if (this.needsFollowColumnsRead) {
         this.needsFollowColumnsRead = false;
         this.planner.readFollowData(this.indexes, this.includedRowGroups, needRG, false);
         this.reader.startStripe(this.planner, TypeReader.ReadPhase.FOLLOWERS);
      }

      TypeReader.ReadPhase result = TypeReader.ReadPhase.FOLLOWERS;
      if (needRG == readRG && toFollowRow >= fromFollowRow) {
         if (skipRows > 0L) {
            this.seekToRowEntry(this.reader, readRG, TypeReader.ReadPhase.LEADER_PARENTS);
            this.reader.skipRows(fromFollowRow - (long)readRG * this.rowIndexStride, TypeReader.ReadPhase.LEADER_PARENTS);
            this.reader.skipRows(skipRows, TypeReader.ReadPhase.FOLLOWERS_AND_PARENTS);
            result = TypeReader.ReadPhase.FOLLOWERS_AND_PARENTS;
         }
      } else {
         this.seekToRowEntry(this.reader, needRG, TypeReader.ReadPhase.FOLLOWERS_AND_PARENTS);
         this.reader.skipRows(skipRows, TypeReader.ReadPhase.FOLLOWERS_AND_PARENTS);
         result = TypeReader.ReadPhase.FOLLOWERS_AND_PARENTS;
      }

      return result;
   }

   private int computeBatchSize(long targetBatchSize) {
      int batchSize;
      if (this.rowIndexStride != 0L && (this.includedRowGroups != null || this.startReadPhase != TypeReader.ReadPhase.ALL) && this.rowInStripe < this.rowCountInStripe) {
         int startRowGroup = (int)(this.rowInStripe / this.rowIndexStride);
         if (this.includedRowGroups != null && !this.includedRowGroups[startRowGroup]) {
            while(startRowGroup < this.includedRowGroups.length && !this.includedRowGroups[startRowGroup]) {
               ++startRowGroup;
            }
         }

         int endRowGroup = startRowGroup;
         if (this.includedRowGroups != null && this.startReadPhase == TypeReader.ReadPhase.ALL) {
            while(endRowGroup < this.includedRowGroups.length && this.includedRowGroups[endRowGroup]) {
               ++endRowGroup;
            }
         } else {
            endRowGroup = startRowGroup + 1;
         }

         long markerPosition = Math.min((long)endRowGroup * this.rowIndexStride, this.rowCountInStripe);
         batchSize = (int)Math.min(targetBatchSize, markerPosition - this.rowInStripe);
         if (isLogDebugEnabled && (long)batchSize < targetBatchSize) {
            LOG.debug("markerPosition: " + markerPosition + " batchSize: " + batchSize);
         }
      } else {
         batchSize = (int)Math.min(targetBatchSize, this.rowCountInStripe - this.rowInStripe);
      }

      return batchSize;
   }

   public void close() throws IOException {
      this.clearStreams();
      this.dataReader.close();
   }

   public long getRowNumber() {
      return this.rowInStripe + this.rowBaseInStripe + this.firstRow;
   }

   public float getProgress() {
      return ((float)this.rowBaseInStripe + (float)this.rowInStripe) / (float)this.totalRowCount;
   }

   private int findStripe(long rowNumber) {
      for(int i = 0; i < this.stripes.size(); ++i) {
         StripeInformation stripe = (StripeInformation)this.stripes.get(i);
         if (stripe.getNumberOfRows() > rowNumber) {
            return i;
         }

         rowNumber -= stripe.getNumberOfRows();
      }

      throw new IllegalArgumentException("Seek after the end of reader range");
   }

   private void readCurrentStripeRowIndex() throws IOException {
      this.planner.readRowIndex(this.rowIndexColsToRead, this.indexes);
   }

   public OrcIndex readRowIndex(int stripeIndex, boolean[] included, boolean[] readCols) throws IOException {
      if (stripeIndex != this.currentStripe || readCols != null && !Arrays.equals(readCols, this.rowIndexColsToRead)) {
         StripePlanner copy = new StripePlanner(this.planner);
         if (included == null) {
            included = new boolean[this.schema.getMaximumId() + 1];
            Arrays.fill(included, true);
         }

         copy.parseStripe((StripeInformation)this.stripes.get(stripeIndex), included);
         return copy.readRowIndex(readCols, (OrcIndex)null);
      } else {
         return this.rowIndexColsToRead != null ? this.indexes : this.planner.readRowIndex(readCols, this.indexes);
      }
   }

   private void seekToRowEntry(BatchReader reader, int rowEntry, TypeReader.ReadPhase readPhase) throws IOException {
      OrcProto.RowIndex[] rowIndices = this.indexes.getRowGroupIndex();
      PositionProvider[] index = new PositionProvider[rowIndices.length];

      for(int i = 0; i < index.length; ++i) {
         if (rowIndices[i] != null) {
            OrcProto.RowIndexEntry entry = rowIndices[i].getEntry(rowEntry);
            if (rowEntry == 0 && entry.getPositionsCount() == 0) {
               index[i] = new ZeroPositionProvider();
            } else {
               index[i] = new PositionProviderImpl(entry);
            }
         }
      }

      reader.seek(index, readPhase);
   }

   public void seekToRow(long rowNumber) throws IOException {
      if (rowNumber < 0L) {
         throw new IllegalArgumentException("Seek to a negative row number " + rowNumber);
      } else if (rowNumber < this.firstRow) {
         throw new IllegalArgumentException("Seek before reader range " + rowNumber);
      } else {
         rowNumber -= this.firstRow;
         int rightStripe = this.findStripe(rowNumber);
         if (rightStripe != this.currentStripe) {
            this.currentStripe = rightStripe;
            this.readStripe();
         }

         if (this.rowIndexColsToRead == null) {
            this.readCurrentStripeRowIndex();
         }

         this.advanceToNextRow(this.reader, rowNumber, true);
      }
   }

   public static String encodeTranslatedSargColumn(int rootColumn, Integer indexInSourceTable) {
      return rootColumn + "_" + (indexInSourceTable == null ? -1 : indexInSourceTable);
   }

   public static int[] mapTranslatedSargColumns(List types, List sargLeaves) {
      int[] result = new int[sargLeaves.size()];
      OrcProto.Type lastRoot = null;
      String lastRootStr = null;

      for(int i = 0; i < result.length; ++i) {
         String[] rootAndIndex = ((PredicateLeaf)sargLeaves.get(i)).getColumnName().split("_");

         assert rootAndIndex.length == 2;

         String rootStr = rootAndIndex[0];
         String indexStr = rootAndIndex[1];
         int index = Integer.parseInt(indexStr);
         if (index == -1) {
            result[i] = -1;
         } else {
            assert index >= 0;

            if (!rootStr.equals(lastRootStr)) {
               lastRoot = (OrcProto.Type)types.get(Integer.parseInt(rootStr));
               lastRootStr = rootStr;
            }

            result[i] = lastRoot.getSubtypes(index);
         }
      }

      return result;
   }

   public CompressionCodec getCompressionCodec() {
      return this.dataReader.getCompressionOptions().getCodec();
   }

   public int getMaxDiskRangeChunkLimit() {
      return this.maxDiskRangeChunkLimit;
   }

   SargApplier getSargApp() {
      return this.sargApp;
   }

   static {
      isLogDebugEnabled = LOG.isDebugEnabled();
      EMPTY_COLUMN_STATISTICS = org.apache.orc.OrcProto.ColumnStatistics.newBuilder().setNumberOfValues(0L).setHasNull(false).setBytesOnDisk(0L).build();
      BAD_CPP_BLOOM_FILTER_VERSIONS = new String[]{"1.6.0", "1.6.1", "1.6.2", "1.6.3", "1.6.4", "1.6.5", "1.6.6", "1.6.7", "1.6.8", "1.6.9", "1.6.10", "1.6.11", "1.7.0"};
   }

   public static final class PositionProviderImpl implements PositionProvider {
      private final OrcProto.RowIndexEntry entry;
      private int index;

      public PositionProviderImpl(OrcProto.RowIndexEntry entry) {
         this(entry, 0);
      }

      public PositionProviderImpl(OrcProto.RowIndexEntry entry, int startPos) {
         this.entry = entry;
         this.index = startPos;
      }

      public long getNext() {
         return this.entry.getPositions(this.index++);
      }
   }

   public static final class ZeroPositionProvider implements PositionProvider {
      public long getNext() {
         return 0L;
      }
   }

   static enum Location {
      BEFORE,
      MIN,
      MIDDLE,
      MAX,
      AFTER;

      // $FF: synthetic method
      private static Location[] $values() {
         return new Location[]{BEFORE, MIN, MIDDLE, MAX, AFTER};
      }
   }

   static class ValueRange {
      final Comparable lower;
      final Comparable upper;
      final boolean onlyLowerBound;
      final boolean onlyUpperBound;
      final boolean hasNulls;
      final boolean hasValue;
      final boolean comparable;

      ValueRange(PredicateLeaf predicate, Comparable lower, Comparable upper, boolean hasNulls, boolean onlyLowerBound, boolean onlyUpperBound, boolean hasValue, boolean comparable) {
         PredicateLeaf.Type type = predicate.getType();
         this.lower = RecordReaderImpl.getBaseObjectForComparison(type, lower);
         this.upper = RecordReaderImpl.getBaseObjectForComparison(type, upper);
         this.hasNulls = hasNulls;
         this.onlyLowerBound = onlyLowerBound;
         this.onlyUpperBound = onlyUpperBound;
         this.hasValue = hasValue;
         this.comparable = comparable;
      }

      ValueRange(PredicateLeaf predicate, Comparable lower, Comparable upper, boolean hasNulls, boolean onlyLowerBound, boolean onlyUpperBound) {
         this(predicate, lower, upper, hasNulls, onlyLowerBound, onlyUpperBound, lower != null, lower != null);
      }

      ValueRange(PredicateLeaf predicate, Comparable lower, Comparable upper, boolean hasNulls) {
         this(predicate, lower, upper, hasNulls, false, false);
      }

      ValueRange(PredicateLeaf predicate, boolean hasNulls) {
         this(predicate, (Comparable)null, (Comparable)null, hasNulls, false, false);
      }

      boolean hasValues() {
         return this.hasValue;
      }

      boolean isComparable() {
         return this.hasValue && this.comparable;
      }

      boolean isValid() {
         return this.hasValue || this.hasNulls;
      }

      Location compare(Comparable point) {
         int minCompare = point.compareTo(this.lower);
         if (minCompare < 0) {
            return RecordReaderImpl.Location.BEFORE;
         } else if (minCompare == 0) {
            return this.onlyLowerBound ? RecordReaderImpl.Location.BEFORE : RecordReaderImpl.Location.MIN;
         } else {
            int maxCompare = point.compareTo(this.upper);
            if (maxCompare > 0) {
               return RecordReaderImpl.Location.AFTER;
            } else if (maxCompare == 0) {
               return this.onlyUpperBound ? RecordReaderImpl.Location.AFTER : RecordReaderImpl.Location.MAX;
            } else {
               return RecordReaderImpl.Location.MIDDLE;
            }
         }
      }

      boolean isSingleton() {
         return this.lower != null && !this.onlyLowerBound && !this.onlyUpperBound && this.lower.equals(this.upper);
      }

      SearchArgument.TruthValue addNull(SearchArgument.TruthValue value) {
         if (this.hasNulls) {
            switch (value) {
               case YES -> {
                  return TruthValue.YES_NULL;
               }
               case NO -> {
                  return TruthValue.NO_NULL;
               }
               case YES_NO -> {
                  return TruthValue.YES_NO_NULL;
               }
               default -> {
                  return value;
               }
            }
         } else {
            return value;
         }
      }
   }

   static class SargCastException extends IllegalArgumentException {
      SargCastException(String string) {
         super(string);
      }
   }

   public static class SargApplier {
      public static final boolean[] READ_ALL_RGS = null;
      public static final boolean[] READ_NO_RGS = new boolean[0];
      private final OrcFile.WriterVersion writerVersion;
      private final SearchArgument sarg;
      private final List sargLeaves;
      private final int[] filterColumns;
      private final long rowIndexStride;
      private final SchemaEvolution evolution;
      private final long[] exceptionCount;
      private final boolean useUTCTimestamp;
      private final boolean writerUsedProlepticGregorian;
      private final boolean convertToProlepticGregorian;

      /** @deprecated */
      public SargApplier(SearchArgument sarg, long rowIndexStride, SchemaEvolution evolution, OrcFile.WriterVersion writerVersion, boolean useUTCTimestamp) {
         this(sarg, rowIndexStride, evolution, writerVersion, useUTCTimestamp, false, false);
      }

      public SargApplier(SearchArgument sarg, long rowIndexStride, SchemaEvolution evolution, OrcFile.WriterVersion writerVersion, boolean useUTCTimestamp, boolean writerUsedProlepticGregorian, boolean convertToProlepticGregorian) {
         this.writerVersion = writerVersion;
         this.sarg = sarg;
         this.sargLeaves = sarg.getLeaves();
         this.writerUsedProlepticGregorian = writerUsedProlepticGregorian;
         this.convertToProlepticGregorian = convertToProlepticGregorian;
         this.filterColumns = RecordReaderImpl.mapSargColumnsToOrcInternalColIdx(this.sargLeaves, evolution);
         this.rowIndexStride = rowIndexStride;
         this.evolution = evolution;
         this.exceptionCount = new long[this.sargLeaves.size()];
         this.useUTCTimestamp = useUTCTimestamp;
      }

      public void setRowIndexCols(boolean[] rowIndexCols) {
         for(int i : this.filterColumns) {
            if (i > 0) {
               rowIndexCols[i] = true;
            }
         }

      }

      public boolean[] pickRowGroups(StripeInformation stripe, OrcProto.RowIndex[] indexes, OrcProto.Stream.Kind[] bloomFilterKinds, List encodings, OrcProto.BloomFilterIndex[] bloomFilterIndices, boolean returnNone) throws IOException {
         long rowsInStripe = stripe.getNumberOfRows();
         int groupsInStripe = (int)((rowsInStripe + this.rowIndexStride - 1L) / this.rowIndexStride);
         boolean[] result = new boolean[groupsInStripe];
         SearchArgument.TruthValue[] leafValues = new SearchArgument.TruthValue[this.sargLeaves.size()];
         boolean hasSelected = false;
         boolean hasSkipped = false;
         SearchArgument.TruthValue[] exceptionAnswer = new SearchArgument.TruthValue[leafValues.length];

         for(int rowGroup = 0; rowGroup < result.length; ++rowGroup) {
            for(int pred = 0; pred < leafValues.length; ++pred) {
               int columnIx = this.filterColumns[pred];
               if (columnIx == -1) {
                  leafValues[pred] = TruthValue.YES_NO_NULL;
               } else if (exceptionAnswer[pred] != null) {
                  leafValues[pred] = exceptionAnswer[pred];
               } else {
                  if (indexes[columnIx] == null) {
                     RecordReaderImpl.LOG.warn("Index is not populated for " + columnIx);
                     return READ_ALL_RGS;
                  }

                  OrcProto.RowIndexEntry entry = indexes[columnIx].getEntry(rowGroup);
                  if (entry == null) {
                     throw new AssertionError("RG is not populated for " + columnIx + " rg " + rowGroup);
                  }

                  OrcProto.ColumnStatistics stats = RecordReaderImpl.EMPTY_COLUMN_STATISTICS;
                  if (entry.hasStatistics()) {
                     stats = entry.getStatistics();
                  }

                  OrcProto.BloomFilter bf = null;
                  OrcProto.Stream.Kind bfk = null;
                  if (bloomFilterIndices != null && bloomFilterIndices[columnIx] != null) {
                     bfk = bloomFilterKinds[columnIx];
                     bf = bloomFilterIndices[columnIx].getBloomFilter(rowGroup);
                  }

                  if (this.evolution != null && this.evolution.isPPDSafeConversion(columnIx)) {
                     PredicateLeaf predicate = (PredicateLeaf)this.sargLeaves.get(pred);

                     try {
                        leafValues[pred] = RecordReaderImpl.evaluatePredicateProto(stats, predicate, bfk, (OrcProto.ColumnEncoding)encodings.get(columnIx), bf, this.writerVersion, this.evolution.getFileSchema().findSubtype(columnIx), this.writerUsedProlepticGregorian, this.useUTCTimestamp);
                     } catch (Exception e) {
                        int var10002 = this.exceptionCount[pred]++;
                        if (e instanceof SargCastException) {
                           Logger var27 = RecordReaderImpl.LOG;
                           String var10001 = e.getMessage();
                           var27.info("Skipping ORC PPD - " + var10001 + " on " + String.valueOf(predicate));
                        } else {
                           String var10000 = e.getClass().getSimpleName();
                           String reason = var10000 + " when evaluating predicate. Skipping ORC PPD. Stats: " + String.valueOf(stats) + " Predicate: " + String.valueOf(predicate);
                           RecordReaderImpl.LOG.warn(reason, e);
                        }

                        boolean hasNoNull = stats.hasHasNull() && !stats.getHasNull();
                        if (!predicate.getOperator().equals(Operator.NULL_SAFE_EQUALS) && !hasNoNull) {
                           exceptionAnswer[pred] = TruthValue.YES_NO_NULL;
                        } else {
                           exceptionAnswer[pred] = TruthValue.YES_NO;
                        }

                        leafValues[pred] = exceptionAnswer[pred];
                     }
                  } else {
                     leafValues[pred] = TruthValue.YES_NO_NULL;
                  }

                  if (RecordReaderImpl.LOG.isTraceEnabled()) {
                     RecordReaderImpl.LOG.trace("Stats = " + String.valueOf(stats));
                     Logger var28 = RecordReaderImpl.LOG;
                     String var29 = String.valueOf(this.sargLeaves.get(pred));
                     var28.trace("Setting " + var29 + " to " + String.valueOf(leafValues[pred]));
                  }
               }
            }

            result[rowGroup] = this.sarg.evaluate(leafValues).isNeeded();
            hasSelected = hasSelected || result[rowGroup];
            hasSkipped = hasSkipped || !result[rowGroup];
            if (RecordReaderImpl.LOG.isDebugEnabled()) {
               long var30 = this.rowIndexStride * (long)rowGroup;
               RecordReaderImpl.LOG.debug("Row group " + var30 + " to " + (this.rowIndexStride * (long)(rowGroup + 1) - 1L) + " is " + (result[rowGroup] ? "" : "not ") + "included.");
            }
         }

         return hasSkipped ? (!hasSelected && returnNone ? READ_NO_RGS : result) : READ_ALL_RGS;
      }

      long[] getExceptionCount() {
         return this.exceptionCount;
      }
   }
}
