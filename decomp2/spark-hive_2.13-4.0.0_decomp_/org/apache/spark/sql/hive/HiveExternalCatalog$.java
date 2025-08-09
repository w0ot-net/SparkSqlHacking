package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.catalog.BucketSpec;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.types.AnsiIntervalType;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.TimestampNTZType;
import org.apache.spark.sql.types.VariantType;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.StringOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;

public final class HiveExternalCatalog$ {
   public static final HiveExternalCatalog$ MODULE$ = new HiveExternalCatalog$();
   private static final String SPARK_SQL_PREFIX = "spark.sql.";
   private static final String DATASOURCE_PREFIX;
   private static final String DATASOURCE_PROVIDER;
   private static final String DATASOURCE_SCHEMA;
   private static final String DATASOURCE_SCHEMA_PREFIX;
   private static final String DATASOURCE_SCHEMA_NUMPARTCOLS;
   private static final String DATASOURCE_SCHEMA_NUMSORTCOLS;
   private static final String DATASOURCE_SCHEMA_NUMBUCKETS;
   private static final String DATASOURCE_SCHEMA_NUMBUCKETCOLS;
   private static final String DATASOURCE_SCHEMA_PART_PREFIX;
   private static final String DATASOURCE_SCHEMA_PARTCOL_PREFIX;
   private static final String DATASOURCE_SCHEMA_BUCKETCOL_PREFIX;
   private static final String DATASOURCE_SCHEMA_SORTCOL_PREFIX;
   private static final String STATISTICS_PREFIX;
   private static final String STATISTICS_TOTAL_SIZE;
   private static final String STATISTICS_NUM_ROWS;
   private static final String STATISTICS_COL_STATS_PREFIX;
   private static final String TABLE_PARTITION_PROVIDER;
   private static final String TABLE_PARTITION_PROVIDER_CATALOG;
   private static final String TABLE_PARTITION_PROVIDER_FILESYSTEM;
   private static final String CREATED_SPARK_VERSION;
   private static final Set HIVE_GENERATED_TABLE_PROPERTIES;
   private static final Set HIVE_GENERATED_STORAGE_PROPERTIES;
   private static final StructType EMPTY_DATA_SCHEMA;

   static {
      DATASOURCE_PREFIX = MODULE$.SPARK_SQL_PREFIX() + "sources.";
      DATASOURCE_PROVIDER = MODULE$.DATASOURCE_PREFIX() + "provider";
      DATASOURCE_SCHEMA = MODULE$.DATASOURCE_PREFIX() + "schema";
      DATASOURCE_SCHEMA_PREFIX = MODULE$.DATASOURCE_SCHEMA() + ".";
      DATASOURCE_SCHEMA_NUMPARTCOLS = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "numPartCols";
      DATASOURCE_SCHEMA_NUMSORTCOLS = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "numSortCols";
      DATASOURCE_SCHEMA_NUMBUCKETS = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "numBuckets";
      DATASOURCE_SCHEMA_NUMBUCKETCOLS = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "numBucketCols";
      DATASOURCE_SCHEMA_PART_PREFIX = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "part.";
      DATASOURCE_SCHEMA_PARTCOL_PREFIX = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "partCol.";
      DATASOURCE_SCHEMA_BUCKETCOL_PREFIX = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "bucketCol.";
      DATASOURCE_SCHEMA_SORTCOL_PREFIX = MODULE$.DATASOURCE_SCHEMA_PREFIX() + "sortCol.";
      STATISTICS_PREFIX = MODULE$.SPARK_SQL_PREFIX() + "statistics.";
      STATISTICS_TOTAL_SIZE = MODULE$.STATISTICS_PREFIX() + "totalSize";
      STATISTICS_NUM_ROWS = MODULE$.STATISTICS_PREFIX() + "numRows";
      STATISTICS_COL_STATS_PREFIX = MODULE$.STATISTICS_PREFIX() + "colStats.";
      TABLE_PARTITION_PROVIDER = MODULE$.SPARK_SQL_PREFIX() + "partitionProvider";
      TABLE_PARTITION_PROVIDER_CATALOG = "catalog";
      TABLE_PARTITION_PROVIDER_FILESYSTEM = "filesystem";
      CREATED_SPARK_VERSION = MODULE$.SPARK_SQL_PREFIX() + "create.version";
      HIVE_GENERATED_TABLE_PROPERTIES = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"transient_lastDdlTime"})));
      HIVE_GENERATED_STORAGE_PROPERTIES = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"serialization.format"})));
      EMPTY_DATA_SCHEMA = (new StructType()).add("col", "array<string>", true, "from deserializer");
   }

   public String SPARK_SQL_PREFIX() {
      return SPARK_SQL_PREFIX;
   }

   public String DATASOURCE_PREFIX() {
      return DATASOURCE_PREFIX;
   }

   public String DATASOURCE_PROVIDER() {
      return DATASOURCE_PROVIDER;
   }

   public String DATASOURCE_SCHEMA() {
      return DATASOURCE_SCHEMA;
   }

   public String DATASOURCE_SCHEMA_PREFIX() {
      return DATASOURCE_SCHEMA_PREFIX;
   }

   public String DATASOURCE_SCHEMA_NUMPARTCOLS() {
      return DATASOURCE_SCHEMA_NUMPARTCOLS;
   }

   public String DATASOURCE_SCHEMA_NUMSORTCOLS() {
      return DATASOURCE_SCHEMA_NUMSORTCOLS;
   }

   public String DATASOURCE_SCHEMA_NUMBUCKETS() {
      return DATASOURCE_SCHEMA_NUMBUCKETS;
   }

   public String DATASOURCE_SCHEMA_NUMBUCKETCOLS() {
      return DATASOURCE_SCHEMA_NUMBUCKETCOLS;
   }

   public String DATASOURCE_SCHEMA_PART_PREFIX() {
      return DATASOURCE_SCHEMA_PART_PREFIX;
   }

   public String DATASOURCE_SCHEMA_PARTCOL_PREFIX() {
      return DATASOURCE_SCHEMA_PARTCOL_PREFIX;
   }

   public String DATASOURCE_SCHEMA_BUCKETCOL_PREFIX() {
      return DATASOURCE_SCHEMA_BUCKETCOL_PREFIX;
   }

   public String DATASOURCE_SCHEMA_SORTCOL_PREFIX() {
      return DATASOURCE_SCHEMA_SORTCOL_PREFIX;
   }

   public String STATISTICS_PREFIX() {
      return STATISTICS_PREFIX;
   }

   public String STATISTICS_TOTAL_SIZE() {
      return STATISTICS_TOTAL_SIZE;
   }

   public String STATISTICS_NUM_ROWS() {
      return STATISTICS_NUM_ROWS;
   }

   public String STATISTICS_COL_STATS_PREFIX() {
      return STATISTICS_COL_STATS_PREFIX;
   }

   public String TABLE_PARTITION_PROVIDER() {
      return TABLE_PARTITION_PROVIDER;
   }

   public String TABLE_PARTITION_PROVIDER_CATALOG() {
      return TABLE_PARTITION_PROVIDER_CATALOG;
   }

   public String TABLE_PARTITION_PROVIDER_FILESYSTEM() {
      return TABLE_PARTITION_PROVIDER_FILESYSTEM;
   }

   public String CREATED_SPARK_VERSION() {
      return CREATED_SPARK_VERSION;
   }

   public Set HIVE_GENERATED_TABLE_PROPERTIES() {
      return HIVE_GENERATED_TABLE_PROPERTIES;
   }

   public Set HIVE_GENERATED_STORAGE_PROPERTIES() {
      return HIVE_GENERATED_STORAGE_PROPERTIES;
   }

   public StructType EMPTY_DATA_SCHEMA() {
      return EMPTY_DATA_SCHEMA;
   }

   private Seq getColumnNamesByType(final Map props, final String colType, final String typeName) {
      StringOps var10002 = scala.collection.StringOps..MODULE$;
      return (Seq)scala.Option..MODULE$.option2Iterable(props.get("spark.sql.sources.schema.num" + var10002.capitalize$extension(.MODULE$.augmentString(colType)) + "Cols")).toSeq().flatMap((numCols) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(numCols))).map((index) -> $anonfun$getColumnNamesByType$2(props, colType, typeName, numCols, BoxesRunTime.unboxToInt(index))));
   }

   public Seq org$apache$spark$sql$hive$HiveExternalCatalog$$getPartitionColumnsFromTableProperties(final CatalogTable metadata) {
      return this.getColumnNamesByType(metadata.properties(), "part", "partitioning columns");
   }

   public Option org$apache$spark$sql$hive$HiveExternalCatalog$$getBucketSpecFromTableProperties(final CatalogTable metadata) {
      return metadata.properties().get(this.DATASOURCE_SCHEMA_NUMBUCKETS()).map((numBuckets) -> new BucketSpec(scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(numBuckets)), MODULE$.getColumnNamesByType(metadata.properties(), "bucket", "bucketing columns"), MODULE$.getColumnNamesByType(metadata.properties(), "sort", "sorting columns")));
   }

   public boolean isDatasourceTable(final CatalogTable table) {
      boolean var10000;
      label25: {
         Option provider = table.provider().orElse(() -> table.properties().get(MODULE$.DATASOURCE_PROVIDER()));
         if (provider.isDefined()) {
            Some var3 = new Some(org.apache.spark.sql.execution.command.DDLUtils..MODULE$.HIVE_PROVIDER());
            if (provider == null) {
               if (var3 != null) {
                  break label25;
               }
            } else if (!provider.equals(var3)) {
               break label25;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean isHiveCompatibleDataType(final DataType dt) {
      while(!(dt instanceof AnsiIntervalType)) {
         if (dt instanceof TimestampNTZType) {
            return false;
         }

         if (dt instanceof VariantType) {
            return false;
         }

         if (dt instanceof StructType var5) {
            return var5.forall((f) -> BoxesRunTime.boxToBoolean($anonfun$isHiveCompatibleDataType$1(f)));
         }

         if (dt instanceof ArrayType var6) {
            dt = var6.elementType();
         } else {
            if (dt instanceof MapType var7) {
               if (this.isHiveCompatibleDataType(var7.keyType())) {
                  dt = var7.valueType();
                  continue;
               }

               return false;
            }

            if (dt instanceof StringType var8) {
               return var8.isUTF8BinaryCollation();
            }

            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final String $anonfun$getColumnNamesByType$2(final Map props$1, final String colType$1, final String typeName$1, final String numCols$1, final int index) {
      return (String)props$1.getOrElse(MODULE$.DATASOURCE_SCHEMA_PREFIX() + colType$1 + "Col." + index, () -> {
         throw new AnalysisException("_LEGACY_ERROR_TEMP_3089", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("typeName"), typeName$1), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("numCols"), numCols$1), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("index"), Integer.toString(index))}))));
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isHiveCompatibleDataType$1(final StructField f) {
      return !org.apache.spark.sql.catalyst.util.QuotingUtils..MODULE$.needQuote(f.name()) && MODULE$.isHiveCompatibleDataType(f.dataType());
   }

   private HiveExternalCatalog$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
