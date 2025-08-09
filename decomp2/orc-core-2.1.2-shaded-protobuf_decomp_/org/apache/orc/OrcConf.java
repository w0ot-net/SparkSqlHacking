package org.apache.orc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;

public enum OrcConf {
   STRIPE_SIZE("orc.stripe.size", "hive.exec.orc.default.stripe.size", 67108864L, "Define the default ORC stripe size, in bytes."),
   STRIPE_ROW_COUNT("orc.stripe.row.count", "orc.stripe.row.count", Integer.MAX_VALUE, "This value limit the row count in one stripe. \nThe number of stripe rows can be controlled at \n(0, \"orc.stripe.row.count\" + max(batchSize, \"orc.rows.between.memory.checks\"))"),
   BLOCK_SIZE("orc.block.size", "hive.exec.orc.default.block.size", 268435456L, "Define the default file system block size for ORC files."),
   ENABLE_INDEXES("orc.create.index", "orc.create.index", true, "Should the ORC writer create indexes as part of the file."),
   ROW_INDEX_STRIDE("orc.row.index.stride", "hive.exec.orc.default.row.index.stride", 10000, "Define the default ORC index stride in number of rows. (Stride is the\n number of rows an index entry represents.)"),
   BUFFER_SIZE("orc.compress.size", "hive.exec.orc.default.buffer.size", 262144, "Define the default ORC buffer size, in bytes."),
   BASE_DELTA_RATIO("orc.base.delta.ratio", "hive.exec.orc.base.delta.ratio", 8, "The ratio of base writer and delta writer in terms of STRIPE_SIZE and BUFFER_SIZE."),
   BLOCK_PADDING("orc.block.padding", "hive.exec.orc.default.block.padding", true, "Define whether stripes should be padded to the HDFS block boundaries."),
   COMPRESS("orc.compress", "hive.exec.orc.default.compress", "ZSTD", "Define the default compression codec for ORC file. It can be NONE, ZLIB, SNAPPY, LZO, LZ4, ZSTD, BROTLI."),
   WRITE_FORMAT("orc.write.format", "hive.exec.orc.write.format", "0.12", "Define the version of the file to write. Possible values are 0.11 and\n 0.12. If this parameter is not defined, ORC will use the run\n length encoding (RLE) introduced in Hive 0.12."),
   ENFORCE_COMPRESSION_BUFFER_SIZE("orc.buffer.size.enforce", "hive.exec.orc.buffer.size.enforce", false, "Defines whether to enforce ORC compression buffer size."),
   ENCODING_STRATEGY("orc.encoding.strategy", "hive.exec.orc.encoding.strategy", "SPEED", "Define the encoding strategy to use while writing data. Changing this\nwill only affect the light weight encoding for integers. This\nflag will not change the compression level of higher level\ncompression codec (like ZLIB)."),
   COMPRESSION_STRATEGY("orc.compression.strategy", "hive.exec.orc.compression.strategy", "SPEED", "Define the compression strategy to use while writing data.\nThis changes the compression level of higher level compression\ncodec (like ZLIB)."),
   COMPRESSION_ZSTD_LEVEL("orc.compression.zstd.level", "hive.exec.orc.compression.zstd.level", 3, "Define the compression level to use with ZStandard codec while writing data. The valid range is 1~22"),
   COMPRESSION_ZSTD_WINDOWLOG("orc.compression.zstd.windowlog", "hive.exec.orc.compression.zstd.windowlog", 0, "Set the maximum allowed back-reference distance for ZStandard codec, expressed as power of 2."),
   BLOCK_PADDING_TOLERANCE("orc.block.padding.tolerance", "hive.exec.orc.block.padding.tolerance", 0.05, "Define the tolerance for block padding as a decimal fraction of\nstripe size (for example, the default value 0.05 is 5% of the\nstripe size). For the defaults of 64Mb ORC stripe and 256Mb HDFS\nblocks, the default block padding tolerance of 5% will\nreserve a maximum of 3.2Mb for padding within the 256Mb block.\nIn that case, if the available size within the block is more than\n3.2Mb, a new smaller stripe will be inserted to fit within that\nspace. This will make sure that no stripe written will block\n boundaries and cause remote reads within a node local task."),
   BLOOM_FILTER_FPP("orc.bloom.filter.fpp", "orc.default.bloom.fpp", 0.01, "Define the default false positive probability for bloom filters."),
   USE_ZEROCOPY("orc.use.zerocopy", "hive.exec.orc.zerocopy", false, "Use zerocopy reads with ORC. (This requires Hadoop 2.3 or later.)"),
   SKIP_CORRUPT_DATA("orc.skip.corrupt.data", "hive.exec.orc.skip.corrupt.data", false, "If ORC reader encounters corrupt data, this value will be used to\ndetermine whether to skip the corrupt data or throw exception.\nThe default behavior is to throw exception."),
   TOLERATE_MISSING_SCHEMA("orc.tolerate.missing.schema", "hive.exec.orc.tolerate.missing.schema", true, "Writers earlier than HIVE-4243 may have inaccurate schema metadata.\nThis setting will enable best effort schema evolution rather\nthan rejecting mismatched schemas"),
   MEMORY_POOL("orc.memory.pool", "hive.exec.orc.memory.pool", (double)0.5F, "Maximum fraction of heap that can be used by ORC file writers"),
   DICTIONARY_KEY_SIZE_THRESHOLD("orc.dictionary.key.threshold", "hive.exec.orc.dictionary.key.size.threshold", 0.8, "If the number of distinct keys in a dictionary is greater than this\nfraction of the total number of non-null rows, turn off \ndictionary encoding.  Use 1 to always use dictionary encoding."),
   ROW_INDEX_STRIDE_DICTIONARY_CHECK("orc.dictionary.early.check", "hive.orc.row.index.stride.dictionary.check", true, "If enabled dictionary check will happen after first row index stride\n(default 10000 rows) else dictionary check will happen before\nwriting first stripe. In both cases, the decision to use\ndictionary or not will be retained thereafter."),
   DICTIONARY_IMPL("orc.dictionary.implementation", "orc.dictionary.implementation", "rbtree", "the implementation for the dictionary used for string-type column encoding.\nThe choices are:\n rbtree - use red-black tree as the implementation for the dictionary.\n hash - use hash table as the implementation for the dictionary."),
   BLOOM_FILTER_COLUMNS("orc.bloom.filter.columns", "orc.bloom.filter.columns", "", "List of columns to create bloom filters for when writing."),
   BLOOM_FILTER_WRITE_VERSION("orc.bloom.filter.write.version", "orc.bloom.filter.write.version", OrcFile.BloomFilterVersion.UTF8.toString(), "(Deprecated) Which version of the bloom filters should we write.\nThe choices are:\n  original - writes two versions of the bloom filters for use by\n             both old and new readers.\n  utf8 - writes just the new bloom filters."),
   IGNORE_NON_UTF8_BLOOM_FILTERS("orc.bloom.filter.ignore.non-utf8", "orc.bloom.filter.ignore.non-utf8", false, "Should the reader ignore the obsolete non-UTF8 bloom filters."),
   MAX_FILE_LENGTH("orc.max.file.length", "orc.max.file.length", Long.MAX_VALUE, "The maximum size of the file to read for finding the file tail. This\nis primarily used for streaming ingest to read intermediate\nfooters while the file is still open"),
   MAPRED_INPUT_SCHEMA("orc.mapred.input.schema", (String)null, (Object)null, "The schema that the user desires to read. The values are\ninterpreted using TypeDescription.fromString."),
   MAPRED_SHUFFLE_KEY_SCHEMA("orc.mapred.map.output.key.schema", (String)null, (Object)null, "The schema of the MapReduce shuffle key. The values are\ninterpreted using TypeDescription.fromString."),
   MAPRED_SHUFFLE_VALUE_SCHEMA("orc.mapred.map.output.value.schema", (String)null, (Object)null, "The schema of the MapReduce shuffle value. The values are\ninterpreted using TypeDescription.fromString."),
   MAPRED_OUTPUT_SCHEMA("orc.mapred.output.schema", (String)null, (Object)null, "The schema that the user desires to write. The values are\ninterpreted using TypeDescription.fromString."),
   INCLUDE_COLUMNS("orc.include.columns", "hive.io.file.readcolumn.ids", (Object)null, "The list of comma separated column ids that should be read with 0\nbeing the first column, 1 being the next, and so on. ."),
   KRYO_SARG("orc.kryo.sarg", "orc.kryo.sarg", (Object)null, "The kryo and base64 encoded SearchArgument for predicate pushdown."),
   KRYO_SARG_BUFFER("orc.kryo.sarg.buffer", (String)null, 8192, "The kryo buffer size for SearchArgument for predicate pushdown."),
   SARG_COLUMNS("orc.sarg.column.names", "orc.sarg.column.names", (Object)null, "The list of column names for the SearchArgument."),
   FORCE_POSITIONAL_EVOLUTION("orc.force.positional.evolution", "orc.force.positional.evolution", false, "Require schema evolution to match the top level columns using position\nrather than column names. This provides backwards compatibility with\nHive 2.1."),
   FORCE_POSITIONAL_EVOLUTION_LEVEL("orc.force.positional.evolution.level", "orc.force.positional.evolution.level", 1, "Require schema evolution to match the defined no. of level columns using position\nrather than column names. This provides backwards compatibility with Hive 2.1."),
   ROWS_BETWEEN_CHECKS("orc.rows.between.memory.checks", "orc.rows.between.memory.checks", 5000, "How often should MemoryManager check the memory sizes? Measured in rows\nadded to all of the writers.  Valid range is [1,10000] and is primarily meant fortesting.  Setting this too low may negatively affect performance. Use orc.stripe.row.count instead if the value larger than orc.stripe.row.count."),
   OVERWRITE_OUTPUT_FILE("orc.overwrite.output.file", "orc.overwrite.output.file", false, "A boolean flag to enable overwriting of the output file if it already exists.\n"),
   IS_SCHEMA_EVOLUTION_CASE_SENSITIVE("orc.schema.evolution.case.sensitive", "orc.schema.evolution.case.sensitive", true, "A boolean flag to determine if the comparision of field names in schema evolution is case sensitive .\n"),
   ALLOW_SARG_TO_FILTER("orc.sarg.to.filter", "orc.sarg.to.filter", false, "A boolean flag to determine if a SArg is allowed to become a filter"),
   READER_USE_SELECTED("orc.filter.use.selected", "orc.filter.use.selected", false, "A boolean flag to determine if the selected vector is supported by\nthe reading application. If false, the output of the ORC reader must have the filter\nreapplied to avoid using unset values in the unselected rows.\nIf unsure please leave this as false."),
   ALLOW_PLUGIN_FILTER("orc.filter.plugin", "orc.filter.plugin", false, "Enables the use of plugin filters during read. The plugin filters are discovered against the service org.apache.orc.filter.PluginFilterService, if multiple filters are determined, they are combined using AND. The order of application is non-deterministic and the filter functionality should not depend on the order of application."),
   PLUGIN_FILTER_ALLOWLIST("orc.filter.plugin.allowlist", "orc.filter.plugin.allowlist", "*", "A list of comma-separated class names. If specified it restricts the PluginFilters to just these classes as discovered by the PluginFilterService. The default of * allows all discovered classes and an empty string would not allow any plugins to be applied."),
   WRITE_VARIABLE_LENGTH_BLOCKS("orc.write.variable.length.blocks", (String)null, false, "A boolean flag as to whether the ORC writer should write variable length\nHDFS blocks."),
   DIRECT_ENCODING_COLUMNS("orc.column.encoding.direct", "orc.column.encoding.direct", "", "Comma-separated list of columns for which dictionary encoding is to be skipped."),
   ORC_MAX_DISK_RANGE_CHUNK_LIMIT("orc.max.disk.range.chunk.limit", "hive.exec.orc.max.disk.range.chunk.limit", 2147482623, "When reading stripes >2GB, specify max limit for the chunk size."),
   ORC_MIN_DISK_SEEK_SIZE("orc.min.disk.seek.size", "orc.min.disk.seek.size", 0, "When determining contiguous reads, gaps within this size are read contiguously and not seeked. Default value of zero disables this optimization"),
   ORC_MIN_DISK_SEEK_SIZE_TOLERANCE("orc.min.disk.seek.size.tolerance", "orc.min.disk.seek.size.tolerance", (double)0.0F, "Define the tolerance for extra bytes read as a result of orc.min.disk.seek.size. If the (bytesRead - bytesNeeded) / bytesNeeded is greater than this threshold then extra work is performed to drop the extra bytes from memory after the read."),
   ENCRYPTION("orc.encrypt", "orc.encrypt", (Object)null, "The list of keys and columns to encrypt with"),
   DATA_MASK("orc.mask", "orc.mask", (Object)null, "The masks to apply to the encrypted columns"),
   KEY_PROVIDER("orc.key.provider", "orc.key.provider", "hadoop", "The kind of KeyProvider to use for encryption."),
   PROLEPTIC_GREGORIAN("orc.proleptic.gregorian", "orc.proleptic.gregorian", false, "Should we read and write dates & times using the proleptic Gregorian calendar\ninstead of the hybrid Julian Gregorian? Hive before 3.1 and Spark before 3.0\nused hybrid."),
   PROLEPTIC_GREGORIAN_DEFAULT("orc.proleptic.gregorian.default", "orc.proleptic.gregorian.default", false, "This value controls whether pre-ORC 27 files are using the hybrid or proleptic\ncalendar. Only Hive 3.1 and the C++ library wrote using the proleptic, so hybrid\nis the default."),
   ROW_BATCH_SIZE("orc.row.batch.size", "orc.row.batch.size", 1024, "The number of rows to include in a orc vectorized reader batch. The value should be carefully chosen to minimize overhead and avoid OOMs in reading data."),
   ROW_BATCH_CHILD_LIMIT("orc.row.child.limit", "orc.row.child.limit", 32768, "The maximum number of child elements to buffer before the ORC row writer writes the batch to the file.");

   private final String attribute;
   private final String hiveConfName;
   private final Object defaultValue;
   private final String description;

   private OrcConf(String attribute, String hiveConfName, Object defaultValue, String description) {
      this.attribute = attribute;
      this.hiveConfName = hiveConfName;
      this.defaultValue = defaultValue;
      this.description = description;
   }

   public String getAttribute() {
      return this.attribute;
   }

   public String getHiveConfName() {
      return this.hiveConfName;
   }

   public Object getDefaultValue() {
      return this.defaultValue;
   }

   public String getDescription() {
      return this.description;
   }

   private String lookupValue(Properties tbl, Configuration conf) {
      String result = null;
      if (tbl != null) {
         result = tbl.getProperty(this.attribute);
      }

      if (result == null && conf != null) {
         result = conf.get(this.attribute);
         if (result == null && this.hiveConfName != null) {
            result = conf.get(this.hiveConfName);
         }
      }

      return result;
   }

   public int getInt(Properties tbl, Configuration conf) {
      String value = this.lookupValue(tbl, conf);
      return value != null ? Integer.parseInt(value) : ((Number)this.defaultValue).intValue();
   }

   public int getInt(Configuration conf) {
      return this.getInt((Properties)null, conf);
   }

   /** @deprecated */
   @Deprecated
   public void getInt(Configuration conf, int value) {
   }

   public void setInt(Configuration conf, int value) {
      conf.setInt(this.attribute, value);
   }

   public long getLong(Properties tbl, Configuration conf) {
      String value = this.lookupValue(tbl, conf);
      return value != null ? Long.parseLong(value) : ((Number)this.defaultValue).longValue();
   }

   public long getLong(Configuration conf) {
      return this.getLong((Properties)null, conf);
   }

   public void setLong(Configuration conf, long value) {
      conf.setLong(this.attribute, value);
   }

   public String getString(Properties tbl, Configuration conf) {
      String value = this.lookupValue(tbl, conf);
      return value == null ? (String)this.defaultValue : value;
   }

   public String getString(Configuration conf) {
      return this.getString((Properties)null, conf);
   }

   public List getStringAsList(Configuration conf) {
      String value = this.getString((Properties)null, conf);
      List<String> confList = new ArrayList();
      if (value != null && !value.isEmpty()) {
         for(String str : value.split(",")) {
            String trimStr = str.trim();
            if (!trimStr.isEmpty()) {
               confList.add(trimStr);
            }
         }

         return confList;
      } else {
         return confList;
      }
   }

   public void setString(Configuration conf, String value) {
      conf.set(this.attribute, value);
   }

   public boolean getBoolean(Properties tbl, Configuration conf) {
      String value = this.lookupValue(tbl, conf);
      return value != null ? Boolean.parseBoolean(value) : (Boolean)this.defaultValue;
   }

   public boolean getBoolean(Configuration conf) {
      return this.getBoolean((Properties)null, conf);
   }

   public void setBoolean(Configuration conf, boolean value) {
      conf.setBoolean(this.attribute, value);
   }

   public double getDouble(Properties tbl, Configuration conf) {
      String value = this.lookupValue(tbl, conf);
      return value != null ? Double.parseDouble(value) : ((Number)this.defaultValue).doubleValue();
   }

   public double getDouble(Configuration conf) {
      return this.getDouble((Properties)null, conf);
   }

   public void setDouble(Configuration conf, double value) {
      conf.setDouble(this.attribute, value);
   }

   // $FF: synthetic method
   private static OrcConf[] $values() {
      return new OrcConf[]{STRIPE_SIZE, STRIPE_ROW_COUNT, BLOCK_SIZE, ENABLE_INDEXES, ROW_INDEX_STRIDE, BUFFER_SIZE, BASE_DELTA_RATIO, BLOCK_PADDING, COMPRESS, WRITE_FORMAT, ENFORCE_COMPRESSION_BUFFER_SIZE, ENCODING_STRATEGY, COMPRESSION_STRATEGY, COMPRESSION_ZSTD_LEVEL, COMPRESSION_ZSTD_WINDOWLOG, BLOCK_PADDING_TOLERANCE, BLOOM_FILTER_FPP, USE_ZEROCOPY, SKIP_CORRUPT_DATA, TOLERATE_MISSING_SCHEMA, MEMORY_POOL, DICTIONARY_KEY_SIZE_THRESHOLD, ROW_INDEX_STRIDE_DICTIONARY_CHECK, DICTIONARY_IMPL, BLOOM_FILTER_COLUMNS, BLOOM_FILTER_WRITE_VERSION, IGNORE_NON_UTF8_BLOOM_FILTERS, MAX_FILE_LENGTH, MAPRED_INPUT_SCHEMA, MAPRED_SHUFFLE_KEY_SCHEMA, MAPRED_SHUFFLE_VALUE_SCHEMA, MAPRED_OUTPUT_SCHEMA, INCLUDE_COLUMNS, KRYO_SARG, KRYO_SARG_BUFFER, SARG_COLUMNS, FORCE_POSITIONAL_EVOLUTION, FORCE_POSITIONAL_EVOLUTION_LEVEL, ROWS_BETWEEN_CHECKS, OVERWRITE_OUTPUT_FILE, IS_SCHEMA_EVOLUTION_CASE_SENSITIVE, ALLOW_SARG_TO_FILTER, READER_USE_SELECTED, ALLOW_PLUGIN_FILTER, PLUGIN_FILTER_ALLOWLIST, WRITE_VARIABLE_LENGTH_BLOCKS, DIRECT_ENCODING_COLUMNS, ORC_MAX_DISK_RANGE_CHUNK_LIMIT, ORC_MIN_DISK_SEEK_SIZE, ORC_MIN_DISK_SEEK_SIZE_TOLERANCE, ENCRYPTION, DATA_MASK, KEY_PROVIDER, PROLEPTIC_GREGORIAN, PROLEPTIC_GREGORIAN_DEFAULT, ROW_BATCH_SIZE, ROW_BATCH_CHILD_LIMIT};
   }
}
