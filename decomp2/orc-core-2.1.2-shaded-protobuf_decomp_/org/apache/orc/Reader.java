package org.apache.orc;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;

public interface Reader extends Closeable {
   long getNumberOfRows();

   long getRawDataSize();

   long getRawDataSizeOfColumns(List var1);

   long getRawDataSizeFromColIndices(List var1);

   List getMetadataKeys();

   ByteBuffer getMetadataValue(String var1);

   boolean hasMetadataValue(String var1);

   CompressionKind getCompressionKind();

   int getCompressionSize();

   int getRowIndexStride();

   List getStripes();

   long getContentLength();

   ColumnStatistics[] getStatistics();

   TypeDescription getSchema();

   /** @deprecated */
   List getTypes();

   OrcFile.Version getFileVersion();

   OrcFile.WriterVersion getWriterVersion();

   String getSoftwareVersion();

   OrcProto.FileTail getFileTail();

   EncryptionKey[] getColumnEncryptionKeys();

   DataMaskDescription[] getDataMasks();

   EncryptionVariant[] getEncryptionVariants();

   List getVariantStripeStatistics(EncryptionVariant var1) throws IOException;

   Options options();

   RecordReader rows() throws IOException;

   RecordReader rows(Options var1) throws IOException;

   List getVersionList();

   int getMetadataSize();

   /** @deprecated */
   List getOrcProtoStripeStatistics();

   List getStripeStatistics() throws IOException;

   List getStripeStatistics(boolean[] var1) throws IOException;

   /** @deprecated */
   List getOrcProtoFileStatistics();

   ByteBuffer getSerializedFileFooter();

   boolean writerUsedProlepticGregorian();

   boolean getConvertToProlepticGregorian();

   public static class Options implements Cloneable {
      private boolean[] include;
      private long offset = 0L;
      private long length = Long.MAX_VALUE;
      private int positionalEvolutionLevel;
      private SearchArgument sarg = null;
      private String[] columnNames = null;
      private Boolean useZeroCopy = null;
      private Boolean skipCorruptRecords = null;
      private TypeDescription schema = null;
      private String[] preFilterColumns = null;
      Consumer skipRowCallback = null;
      private DataReader dataReader = null;
      private Boolean tolerateMissingSchema = null;
      private boolean forcePositionalEvolution;
      private boolean isSchemaEvolutionCaseAware;
      private boolean includeAcidColumns;
      private boolean allowSARGToFilter;
      private boolean useSelected;
      private boolean allowPluginFilters;
      private List pluginAllowListFilters;
      private int minSeekSize;
      private double minSeekSizeTolerance;
      private int rowBatchSize;

      public Options() {
         this.isSchemaEvolutionCaseAware = (Boolean)OrcConf.IS_SCHEMA_EVOLUTION_CASE_SENSITIVE.getDefaultValue();
         this.includeAcidColumns = true;
         this.allowSARGToFilter = false;
         this.useSelected = false;
         this.allowPluginFilters = false;
         this.pluginAllowListFilters = null;
         this.minSeekSize = (Integer)OrcConf.ORC_MIN_DISK_SEEK_SIZE.getDefaultValue();
         this.minSeekSizeTolerance = (Double)OrcConf.ORC_MIN_DISK_SEEK_SIZE_TOLERANCE.getDefaultValue();
         this.rowBatchSize = (Integer)OrcConf.ROW_BATCH_SIZE.getDefaultValue();
      }

      public Options(Configuration conf) {
         this.isSchemaEvolutionCaseAware = (Boolean)OrcConf.IS_SCHEMA_EVOLUTION_CASE_SENSITIVE.getDefaultValue();
         this.includeAcidColumns = true;
         this.allowSARGToFilter = false;
         this.useSelected = false;
         this.allowPluginFilters = false;
         this.pluginAllowListFilters = null;
         this.minSeekSize = (Integer)OrcConf.ORC_MIN_DISK_SEEK_SIZE.getDefaultValue();
         this.minSeekSizeTolerance = (Double)OrcConf.ORC_MIN_DISK_SEEK_SIZE_TOLERANCE.getDefaultValue();
         this.rowBatchSize = (Integer)OrcConf.ROW_BATCH_SIZE.getDefaultValue();
         this.useZeroCopy = OrcConf.USE_ZEROCOPY.getBoolean(conf);
         this.skipCorruptRecords = OrcConf.SKIP_CORRUPT_DATA.getBoolean(conf);
         this.tolerateMissingSchema = OrcConf.TOLERATE_MISSING_SCHEMA.getBoolean(conf);
         this.forcePositionalEvolution = OrcConf.FORCE_POSITIONAL_EVOLUTION.getBoolean(conf);
         this.positionalEvolutionLevel = OrcConf.FORCE_POSITIONAL_EVOLUTION_LEVEL.getInt(conf);
         this.isSchemaEvolutionCaseAware = OrcConf.IS_SCHEMA_EVOLUTION_CASE_SENSITIVE.getBoolean(conf);
         this.allowSARGToFilter = OrcConf.ALLOW_SARG_TO_FILTER.getBoolean(conf);
         this.useSelected = OrcConf.READER_USE_SELECTED.getBoolean(conf);
         this.allowPluginFilters = OrcConf.ALLOW_PLUGIN_FILTER.getBoolean(conf);
         this.pluginAllowListFilters = OrcConf.PLUGIN_FILTER_ALLOWLIST.getStringAsList(conf);
         this.minSeekSize = OrcConf.ORC_MIN_DISK_SEEK_SIZE.getInt(conf);
         this.minSeekSizeTolerance = OrcConf.ORC_MIN_DISK_SEEK_SIZE_TOLERANCE.getDouble(conf);
         this.rowBatchSize = OrcConf.ROW_BATCH_SIZE.getInt(conf);
      }

      public Options include(boolean[] include) {
         this.include = include;
         return this;
      }

      public Options range(long offset, long length) {
         this.offset = offset;
         this.length = length;
         return this;
      }

      public Options schema(TypeDescription schema) {
         this.schema = schema;
         return this;
      }

      public Options setRowFilter(String[] filterColumnNames, Consumer filterCallback) {
         this.preFilterColumns = filterColumnNames;
         this.skipRowCallback = filterCallback;
         return this;
      }

      public Options searchArgument(SearchArgument sarg, String[] columnNames) {
         this.sarg = sarg;
         this.columnNames = columnNames;
         return this;
      }

      public Options allowSARGToFilter(boolean allowSARGToFilter) {
         this.allowSARGToFilter = allowSARGToFilter;
         return this;
      }

      public boolean isAllowSARGToFilter() {
         return this.allowSARGToFilter;
      }

      public Options useZeroCopy(boolean value) {
         this.useZeroCopy = value;
         return this;
      }

      public Options dataReader(DataReader value) {
         this.dataReader = value;
         return this;
      }

      public Options skipCorruptRecords(boolean value) {
         this.skipCorruptRecords = value;
         return this;
      }

      public Options tolerateMissingSchema(boolean value) {
         this.tolerateMissingSchema = value;
         return this;
      }

      public Options forcePositionalEvolution(boolean value) {
         this.forcePositionalEvolution = value;
         return this;
      }

      public Options positionalEvolutionLevel(int value) {
         this.positionalEvolutionLevel = value;
         return this;
      }

      public Options isSchemaEvolutionCaseAware(boolean value) {
         this.isSchemaEvolutionCaseAware = value;
         return this;
      }

      public Options includeAcidColumns(boolean includeAcidColumns) {
         this.includeAcidColumns = includeAcidColumns;
         return this;
      }

      public boolean[] getInclude() {
         return this.include;
      }

      public long getOffset() {
         return this.offset;
      }

      public long getLength() {
         return this.length;
      }

      public TypeDescription getSchema() {
         return this.schema;
      }

      public SearchArgument getSearchArgument() {
         return this.sarg;
      }

      public Consumer getFilterCallback() {
         return this.skipRowCallback;
      }

      public String[] getPreFilterColumnNames() {
         return this.preFilterColumns;
      }

      public String[] getColumnNames() {
         return this.columnNames;
      }

      public long getMaxOffset() {
         long result = this.offset + this.length;
         if (result < 0L) {
            result = Long.MAX_VALUE;
         }

         return result;
      }

      public Boolean getUseZeroCopy() {
         return this.useZeroCopy;
      }

      public Boolean getSkipCorruptRecords() {
         return this.skipCorruptRecords;
      }

      public DataReader getDataReader() {
         return this.dataReader;
      }

      public boolean getForcePositionalEvolution() {
         return this.forcePositionalEvolution;
      }

      public int getPositionalEvolutionLevel() {
         return this.positionalEvolutionLevel;
      }

      public boolean getIsSchemaEvolutionCaseAware() {
         return this.isSchemaEvolutionCaseAware;
      }

      public boolean getIncludeAcidColumns() {
         return this.includeAcidColumns;
      }

      public Options clone() {
         try {
            Options result = (Options)super.clone();
            if (this.dataReader != null) {
               result.dataReader = this.dataReader.clone();
            }

            return result;
         } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException("uncloneable", e);
         }
      }

      public String toString() {
         StringBuilder buffer = new StringBuilder();
         buffer.append("{include: ");
         if (this.include == null) {
            buffer.append("null");
         } else {
            buffer.append("[");

            for(int i = 0; i < this.include.length; ++i) {
               if (i != 0) {
                  buffer.append(", ");
               }

               buffer.append(this.include[i]);
            }

            buffer.append("]");
         }

         buffer.append(", offset: ");
         buffer.append(this.offset);
         buffer.append(", length: ");
         buffer.append(this.length);
         if (this.sarg != null) {
            buffer.append(", sarg: ");
            buffer.append(this.sarg);
         }

         if (this.schema != null) {
            buffer.append(", schema: ");
            this.schema.printToBuffer(buffer);
         }

         buffer.append(", includeAcidColumns: ").append(this.includeAcidColumns);
         buffer.append(", allowSARGToFilter: ").append(this.allowSARGToFilter);
         buffer.append(", useSelected: ").append(this.useSelected);
         buffer.append("}");
         return buffer.toString();
      }

      public boolean getTolerateMissingSchema() {
         return this.tolerateMissingSchema != null ? this.tolerateMissingSchema : (Boolean)OrcConf.TOLERATE_MISSING_SCHEMA.getDefaultValue();
      }

      public boolean useSelected() {
         return this.useSelected;
      }

      public Options useSelected(boolean newValue) {
         this.useSelected = newValue;
         return this;
      }

      public boolean allowPluginFilters() {
         return this.allowPluginFilters;
      }

      public Options allowPluginFilters(boolean allowPluginFilters) {
         this.allowPluginFilters = allowPluginFilters;
         return this;
      }

      public List pluginAllowListFilters() {
         return this.pluginAllowListFilters;
      }

      public Options pluginAllowListFilters(String... allowLists) {
         this.pluginAllowListFilters = Arrays.asList(allowLists);
         return this;
      }

      public int minSeekSize() {
         return this.minSeekSize;
      }

      public Options minSeekSize(int minSeekSize) {
         this.minSeekSize = minSeekSize;
         return this;
      }

      public double minSeekSizeTolerance() {
         return this.minSeekSizeTolerance;
      }

      public Options minSeekSizeTolerance(double value) {
         this.minSeekSizeTolerance = value;
         return this;
      }

      public int getRowBatchSize() {
         return this.rowBatchSize;
      }

      public Options rowBatchSize(int value) {
         this.rowBatchSize = value;
         return this;
      }
   }
}
