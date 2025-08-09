package org.apache.spark.sql.hive.client;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.RetryingMetaStoreClient;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.serde2.MetadataTypedColumnsetSerDe;
import org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe;
import org.apache.hadoop.hive.thrift.TFilterTransport;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.catalyst.catalog.BucketSpec;
import org.apache.spark.sql.catalyst.catalog.CatalogStatistics;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTablePartition;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.parser.ParseException;
import org.apache.spark.sql.hive.HiveExternalCatalog$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.transport.TEndpointTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.math.BigInt;
import scala.math.Ordered;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class HiveClientImpl$ implements Logging {
   public static final HiveClientImpl$ MODULE$ = new HiveClientImpl$();
   private static String INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER;
   private static final Set org$apache$spark$sql$hive$client$HiveClientImpl$$HiveStatisticsProperties;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      org$apache$spark$sql$hive$client$HiveClientImpl$$HiveStatisticsProperties = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"COLUMN_STATS_ACCURATE", "numFiles", "numPartitions", "numRows", "rawDataSize", "totalSize"})));
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private String INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER = "<derived from deserializer>";
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER;
   }

   public String INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER() {
      return !bitmap$0 ? this.INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER$lzycompute() : INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER;
   }

   public FieldSchema toHiveColumn(final StructField c) {
      String typeString = org.apache.spark.sql.internal.SQLConf..MODULE$.get().charVarcharAsString() ? c.dataType().catalogString() : (String)org.apache.spark.sql.catalyst.util.CharVarcharUtils..MODULE$.getRawTypeString(c.metadata()).getOrElse(() -> c.dataType().catalogString());
      return new FieldSchema(c.name(), typeString, (String)c.getComment().orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private DataType getSparkSQLDataType(final FieldSchema hc) {
      String typeStr = hc.getType().replaceAll("(?<=struct<|,)([^,<:]+)(?=:)", "`$1`");

      try {
         return org.apache.spark.sql.catalyst.parser.CatalystSqlParser..MODULE$.parseDataType(typeStr);
      } catch (ParseException var4) {
         throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.cannotRecognizeHiveTypeError(var4, typeStr, hc.getName());
      }
   }

   public StructField fromHiveColumn(final FieldSchema hc) {
      DataType columnType = this.getSparkSQLDataType(hc);
      StructField field = new StructField(hc.getName(), columnType, true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4());
      return (StructField)scala.Option..MODULE$.apply(hc.getComment()).map((comment) -> field.withComment(comment)).getOrElse(() -> field);
   }

   private Class toInputFormat(final String name) {
      return org.apache.spark.util.Utils..MODULE$.classForName(name, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3());
   }

   private Class toOutputFormat(final String name) {
      return org.apache.spark.util.Utils..MODULE$.classForName(name, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3());
   }

   public TableType toHiveTableType(final CatalogTableType catalogTableType) {
      CatalogTableType var10000 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.EXTERNAL();
      if (var10000 == null) {
         if (catalogTableType == null) {
            return TableType.EXTERNAL_TABLE;
         }
      } else if (var10000.equals(catalogTableType)) {
         return TableType.EXTERNAL_TABLE;
      }

      var10000 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.MANAGED();
      if (var10000 == null) {
         if (catalogTableType == null) {
            return TableType.MANAGED_TABLE;
         }
      } else if (var10000.equals(catalogTableType)) {
         return TableType.MANAGED_TABLE;
      }

      var10000 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
      if (var10000 == null) {
         if (catalogTableType == null) {
            return TableType.VIRTUAL_VIEW;
         }
      } else if (var10000.equals(catalogTableType)) {
         return TableType.VIRTUAL_VIEW;
      }

      throw new IllegalArgumentException("Unknown table type is found at toHiveTableType: " + catalogTableType);
   }

   public Table toHiveTable(final CatalogTable table, final Option userName) {
      Table hiveTable;
      label34: {
         hiveTable = new Table(table.database(), table.identifier().table());
         hiveTable.setTableType(this.toHiveTableType(table.tableType()));
         CatalogTableType var10000 = table.tableType();
         CatalogTableType var6 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.EXTERNAL();
         if (var10000 == null) {
            if (var6 != null) {
               break label34;
            }
         } else if (!var10000.equals(var6)) {
            break label34;
         }

         hiveTable.setProperty("EXTERNAL", "TRUE");
      }

      Tuple2 var8 = table.schema().partition((c) -> BoxesRunTime.boxToBoolean($anonfun$toHiveTable$1(table, c)));
      if (var8 == null) {
         throw new MatchError(var8);
      } else {
         Seq partSchema = (Seq)var8._1();
         Seq schema = (Seq)var8._2();
         Tuple2 var7 = new Tuple2(partSchema, schema);
         Seq partSchema = (Seq)var7._1();
         Seq schema = (Seq)var7._2();
         Seq partCols = (Seq)partSchema.map((x0$1) -> !HiveExternalCatalog$.MODULE$.isHiveCompatibleDataType(x0$1.dataType()) ? new FieldSchema(x0$1.name(), MODULE$.INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER(), (String)x0$1.getComment().orNull(scala..less.colon.less..MODULE$.refl())) : MODULE$.toHiveColumn(x0$1));
         hiveTable.setFields(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((scala.collection.Seq)schema.map((c) -> MODULE$.toHiveColumn(c))).asJava());
         hiveTable.setPartCols(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(partCols).asJava());
         scala.Option..MODULE$.apply(table.owner()).filter((x$26) -> BoxesRunTime.boxToBoolean($anonfun$toHiveTable$4(x$26))).orElse(() -> userName).foreach((x$1) -> {
            $anonfun$toHiveTable$6(hiveTable, x$1);
            return BoxedUnit.UNIT;
         });
         hiveTable.setCreateTime((int)TimeUnit.MILLISECONDS.toSeconds(table.createTime()));
         hiveTable.setLastAccessTime((int)TimeUnit.MILLISECONDS.toSeconds(table.lastAccessTime()));
         table.storage().locationUri().map((uri) -> org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.URIToString(uri)).foreach((loc) -> {
            $anonfun$toHiveTable$8(hiveTable, loc);
            return BoxedUnit.UNIT;
         });
         table.storage().inputFormat().map((name) -> MODULE$.toInputFormat(name)).foreach((x$1) -> {
            $anonfun$toHiveTable$10(hiveTable, x$1);
            return BoxedUnit.UNIT;
         });
         table.storage().outputFormat().map((name) -> MODULE$.toOutputFormat(name)).foreach((x$1) -> {
            $anonfun$toHiveTable$12(hiveTable, x$1);
            return BoxedUnit.UNIT;
         });
         hiveTable.setSerializationLib((String)table.storage().serde().getOrElse(() -> "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe"));
         table.storage().properties().foreach((x0$2) -> {
            if (x0$2 != null) {
               String k = (String)x0$2._1();
               String v = (String)x0$2._2();
               return hiveTable.setSerdeParam(k, v);
            } else {
               throw new MatchError(x0$2);
            }
         });
         table.properties().foreach((x0$3) -> {
            $anonfun$toHiveTable$15(hiveTable, x0$3);
            return BoxedUnit.UNIT;
         });
         table.comment().foreach((c) -> {
            $anonfun$toHiveTable$16(hiveTable, c);
            return BoxedUnit.UNIT;
         });
         table.collation().foreach((c) -> {
            $anonfun$toHiveTable$17(hiveTable, c);
            return BoxedUnit.UNIT;
         });
         table.viewText().foreach((t) -> {
            $anonfun$toHiveTable$18(hiveTable, t);
            return BoxedUnit.UNIT;
         });
         Option var14 = table.bucketSpec();
         if (var14 instanceof Some) {
            Some var15 = (Some)var14;
            BucketSpec bucketSpec = (BucketSpec)var15.value();
            if (!HiveExternalCatalog$.MODULE$.isDatasourceTable(table)) {
               hiveTable.setNumBuckets(bucketSpec.numBuckets());
               hiveTable.setBucketCols(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(bucketSpec.bucketColumnNames().toList()).asJava());
               if (bucketSpec.sortColumnNames().nonEmpty()) {
                  hiveTable.setSortCols(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(((IterableOnceOps)bucketSpec.sortColumnNames().map((col) -> new Order(col, 1))).toList()).asJava());
                  BoxedUnit var18 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var19 = BoxedUnit.UNIT;
               }

               return hiveTable;
            }
         }

         BoxedUnit var17 = BoxedUnit.UNIT;
         return hiveTable;
      }
   }

   public Option toHiveTable$default$2() {
      return scala.None..MODULE$;
   }

   public Partition toHivePartition(final CatalogTablePartition p, final Table ht) {
      org.apache.hadoop.hive.metastore.api.Partition tpart = new org.apache.hadoop.hive.metastore.api.Partition();
      Buffer partValues = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(ht.getPartCols()).asScala().map((hc) -> (String)p.spec().getOrElse(hc.getName(), () -> {
            String var10002 = hc.getName();
            throw new IllegalArgumentException("Partition spec is missing a value for column '" + var10002 + "': " + p.spec());
         }));
      StorageDescriptor storageDesc = new StorageDescriptor();
      SerDeInfo serdeInfo = new SerDeInfo();
      p.storage().locationUri().map((x$27) -> org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.URIToString(x$27)).foreach((x$1) -> {
         $anonfun$toHivePartition$4(storageDesc, x$1);
         return BoxedUnit.UNIT;
      });
      p.storage().inputFormat().foreach((x$1) -> {
         $anonfun$toHivePartition$5(storageDesc, x$1);
         return BoxedUnit.UNIT;
      });
      p.storage().outputFormat().foreach((x$1) -> {
         $anonfun$toHivePartition$6(storageDesc, x$1);
         return BoxedUnit.UNIT;
      });
      p.storage().serde().foreach((x$1) -> {
         $anonfun$toHivePartition$7(serdeInfo, x$1);
         return BoxedUnit.UNIT;
      });
      serdeInfo.setParameters(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(p.storage().properties()).asJava());
      storageDesc.setSerdeInfo(serdeInfo);
      tpart.setDbName(ht.getDbName());
      tpart.setTableName(ht.getTableName());
      tpart.setValues(scala.jdk.CollectionConverters..MODULE$.BufferHasAsJava(partValues).asJava());
      tpart.setSd(storageDesc);
      tpart.setCreateTime((int)TimeUnit.MILLISECONDS.toSeconds(p.createTime()));
      tpart.setLastAccessTime((int)TimeUnit.MILLISECONDS.toSeconds(p.lastAccessTime()));
      tpart.setParameters(scala.jdk.CollectionConverters..MODULE$.MutableMapHasAsJava((scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(p.parameters().toSeq())).asJava());
      return new Partition(ht, tpart);
   }

   public CatalogTablePartition fromHivePartition(final Partition hp) {
      org.apache.hadoop.hive.metastore.api.Partition apiPartition = hp.getTPartition();
      scala.collection.immutable.Map properties = hp.getParameters() != null ? scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(hp.getParameters()).asScala().toMap(scala..less.colon.less..MODULE$.refl()) : .MODULE$.Map().empty();
      scala.collection.immutable.Map x$1 = (scala.collection.immutable.Map)scala.Option..MODULE$.apply(hp.getSpec()).map((x$28) -> scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(x$28).asScala().toMap(scala..less.colon.less..MODULE$.refl())).getOrElse(() -> .MODULE$.Map().empty());
      CatalogStorageFormat x$2 = new CatalogStorageFormat(scala.Option..MODULE$.apply(org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.stringToURI(apiPartition.getSd().getLocation())), scala.Option..MODULE$.apply(apiPartition.getSd().getInputFormat()), scala.Option..MODULE$.apply(apiPartition.getSd().getOutputFormat()), scala.Option..MODULE$.apply(apiPartition.getSd().getSerdeInfo().getSerializationLib()), apiPartition.getSd().isCompressed(), (scala.collection.immutable.Map)scala.Option..MODULE$.apply(apiPartition.getSd().getSerdeInfo().getParameters()).map((x$29) -> scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(x$29).asScala().toMap(scala..less.colon.less..MODULE$.refl())).orNull(scala..less.colon.less..MODULE$.refl()));
      long x$3 = (long)apiPartition.getCreateTime() * 1000L;
      long x$4 = (long)apiPartition.getLastAccessTime() * 1000L;
      Option x$6 = this.org$apache$spark$sql$hive$client$HiveClientImpl$$readHiveStats(properties);
      return new CatalogTablePartition(x$1, x$2, properties, x$3, x$4, x$6);
   }

   public org.apache.hadoop.hive.metastore.api.Table extraFixesForNonView(final org.apache.hadoop.hive.metastore.api.Table tTable) {
      String var10000 = TableType.VIRTUAL_VIEW.toString();
      String var2 = tTable.getTableType();
      if (var10000 == null) {
         if (var2 == null) {
            return tTable;
         }
      } else if (var10000.equals(var2)) {
         return tTable;
      }

      Map parameters = tTable.getSd().getParameters();
      if (parameters != null) {
         String sf = (String)parameters.get("serialization.format");
         if (sf != null) {
            char[] b = sf.toCharArray();
            if (b.length == 1 && b[0] < '\n') {
               parameters.put("serialization.format", Integer.toString(b[0]));
            } else {
               BoxedUnit var9 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

      var10000 = MetadataTypedColumnsetSerDe.class.getName();
      String var6 = tTable.getSd().getSerdeInfo().getSerializationLib();
      if (var10000 == null) {
         if (var6 != null) {
            return tTable;
         }
      } else if (!var10000.equals(var6)) {
         return tTable;
      }

      if (tTable.getSd().getColsSize() > 0 && ((FieldSchema)tTable.getSd().getCols().get(0)).getType().indexOf(60) == -1) {
         tTable.getSd().getSerdeInfo().setSerializationLib(LazySimpleSerDe.class.getName());
      }

      return tTable;
   }

   public Option org$apache$spark$sql$hive$client$HiveClientImpl$$readHiveStats(final scala.collection.immutable.Map properties) {
      Option totalSize = properties.get("totalSize").filter((x$30) -> BoxesRunTime.boxToBoolean($anonfun$readHiveStats$1(x$30))).map((x$31) -> scala.package..MODULE$.BigInt().apply(x$31));
      Option rawDataSize = properties.get("rawDataSize").filter((x$32) -> BoxesRunTime.boxToBoolean($anonfun$readHiveStats$3(x$32))).map((x$33) -> scala.package..MODULE$.BigInt().apply(x$33));
      Option rowCount = properties.get("numRows").filter((x$34) -> BoxesRunTime.boxToBoolean($anonfun$readHiveStats$5(x$34))).map((x$35) -> scala.package..MODULE$.BigInt().apply(x$35));
      if (totalSize.isDefined() && ((Ordered)totalSize.get()).$greater(scala.math.BigInt..MODULE$.long2bigInt(0L))) {
         return new Some(new CatalogStatistics((BigInt)totalSize.get(), rowCount.filter((x$36) -> BoxesRunTime.boxToBoolean($anonfun$readHiveStats$7(x$36))), org.apache.spark.sql.catalyst.catalog.CatalogStatistics..MODULE$.apply$default$3()));
      } else {
         return (Option)(rawDataSize.isDefined() && ((Ordered)rawDataSize.get()).$greater(scala.math.BigInt..MODULE$.int2bigInt(0)) ? new Some(new CatalogStatistics((BigInt)rawDataSize.get(), rowCount.filter((x$37) -> BoxesRunTime.boxToBoolean($anonfun$readHiveStats$8(x$37))), org.apache.spark.sql.catalyst.catalog.CatalogStatistics..MODULE$.apply$default$3())) : scala.None..MODULE$);
      }
   }

   public Set org$apache$spark$sql$hive$client$HiveClientImpl$$HiveStatisticsProperties() {
      return org$apache$spark$sql$hive$client$HiveClientImpl$$HiveStatisticsProperties;
   }

   public HiveConf newHiveConf(final SparkConf sparkConf, final Iterable hadoopConf, final scala.collection.immutable.Map extraConfig, final Option classLoader) {
      HiveConf hiveConf = new HiveConf(SessionState.class);
      classLoader.foreach((x$1) -> {
         $anonfun$newHiveConf$1(hiveConf, x$1);
         return BoxedUnit.UNIT;
      });
      scala.collection.immutable.Map confMap = scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(hadoopConf.iterator()).asScala().map((kv) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(kv.getKey()), kv.getValue())).$plus$plus(() -> .MODULE$.wrapRefArray((Object[])sparkConf.getAll()).toMap(scala..less.colon.less..MODULE$.refl())).$plus$plus(() -> extraConfig).toMap(scala..less.colon.less..MODULE$.refl());
      confMap.foreach((x0$1) -> {
         $anonfun$newHiveConf$5(hiveConf, x0$1);
         return BoxedUnit.UNIT;
      });
      org.apache.spark.sql.internal.SQLConf..MODULE$.get().redactOptions(confMap).foreach((x0$2) -> {
         $anonfun$newHiveConf$6(x0$2);
         return BoxedUnit.UNIT;
      });
      hiveConf.setBoolean("hive.cbo.enable", false);
      if (hiveConf.getBoolean("hive.session.history.enabled", false)) {
         this.logWarning((Function0)(() -> "Detected HiveConf hive.session.history.enabled is true and will be reset to false to disable useless hive logic"));
         hiveConf.setBoolean("hive.session.history.enabled", false);
      }

      label21: {
         String engine = hiveConf.get("hive.execution.engine");
         String var8 = "mr";
         if (engine == null) {
            if (var8 == null) {
               break label21;
            }
         } else if (engine.equals(var8)) {
            break label21;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Detected HiveConf hive.execution.engine is '", "' and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ENGINE..MODULE$, engine)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"will be reset to 'mr' to disable useless hive logic"})))).log(scala.collection.immutable.Nil..MODULE$))));
         hiveConf.set("hive.execution.engine", "mr", org.apache.spark.deploy.SparkHadoopUtil..MODULE$.SOURCE_SPARK());
      }

      String cpType = hiveConf.get("datanucleus.connectionPoolingType");
      if ("bonecp".equalsIgnoreCase(cpType)) {
         hiveConf.set("datanucleus.connectionPoolingType", "DBCP", org.apache.spark.deploy.SparkHadoopUtil..MODULE$.SOURCE_SPARK());
      }

      return hiveConf;
   }

   public scala.collection.immutable.Map newHiveConf$default$3() {
      return .MODULE$.Map().empty();
   }

   public Option newHiveConf$default$4() {
      return scala.None..MODULE$;
   }

   public Hive getHive(final Configuration conf) {
      HiveConf var10000;
      if (conf instanceof HiveConf var5) {
         var10000 = var5;
      } else {
         var10000 = new HiveConf(conf, HiveConf.class);
      }

      HiveConf hiveConf = var10000;

      try {
         var8 = Hive.getWithoutRegisterFns(hiveConf);
      } catch (NoSuchMethodError var7) {
         var8 = Hive.get(hiveConf);
      }

      Hive hive = var8;
      scala.Option..MODULE$.apply(hiveConf.get("hive.thrift.client.max.message.size")).map((x$38) -> BoxesRunTime.boxToInteger($anonfun$getHive$1(x$38))).filter((JFunction1.mcZI.sp)(x$39) -> x$39 > 0).foreach((JFunction1.mcVI.sp)(maxMessageSize) -> {
         MODULE$.logDebug((Function0)(() -> "Trying to set metastore client thrift max message to " + maxMessageSize));
         MODULE$.configureMaxThriftMessageSize(hiveConf, hive.getMSC(), maxMessageSize);
      });
      return hive;
   }

   private Object getFieldValue(final Object obj, final String fieldName) {
      Field field = obj.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(obj);
   }

   private Object getFieldValue(final Object obj, final Class clazz, final String fieldName) {
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(obj);
   }

   private void configureMaxThriftMessageSize(final HiveConf hiveConf, final IMetaStoreClient msClient, final int maxMessageSize) {
      try {
         if (Proxy.isProxyClass(msClient.getClass())) {
            InvocationHandler var7 = Proxy.getInvocationHandler(msClient);
            if (var7.getClass().getName().endsWith("SynchronizedHandler")) {
               IMetaStoreClient wrappedMsc = (IMetaStoreClient)this.getFieldValue(var7, "client");
               this.configureMaxThriftMessageSize(hiveConf, wrappedMsc, maxMessageSize);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else if (var7 instanceof RetryingMetaStoreClient) {
               RetryingMetaStoreClient var9 = (RetryingMetaStoreClient)var7;
               IMetaStoreClient wrappedMsc = (IMetaStoreClient)this.getFieldValue(var9, "base");
               this.configureMaxThriftMessageSize(hiveConf, wrappedMsc, maxMessageSize);
               BoxedUnit var13 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var14 = BoxedUnit.UNIT;
            }

            BoxedUnit var15 = BoxedUnit.UNIT;
         } else {
            if (msClient instanceof HiveMetaStoreClient) {
               HiveMetaStoreClient var11 = (HiveMetaStoreClient)msClient;
               if (!var11.isLocalMetaStore()) {
                  this.configure$1(var11.getTTransport(), maxMessageSize);
                  BoxedUnit var17 = BoxedUnit.UNIT;
                  return;
               }
            }

            BoxedUnit var16 = BoxedUnit.UNIT;
         }
      } catch (NoClassDefFoundError var12) {
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$toHiveTable$1(final CatalogTable table$9, final StructField c) {
      return table$9.partitionColumnNames().contains(c.name());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toHiveTable$4(final String x$26) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(x$26));
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$6(final Table hiveTable$5, final String x$1) {
      hiveTable$5.setOwner(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$8(final Table hiveTable$5, final String loc) {
      hiveTable$5.getTTable().getSd().setLocation(loc);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$10(final Table hiveTable$5, final Class x$1) {
      hiveTable$5.setInputFormatClass(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$12(final Table hiveTable$5, final Class x$1) {
      hiveTable$5.setOutputFormatClass(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$15(final Table hiveTable$5, final Tuple2 x0$3) {
      if (x0$3 != null) {
         String k = (String)x0$3._1();
         String v = (String)x0$3._2();
         hiveTable$5.setProperty(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$3);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$16(final Table hiveTable$5, final String c) {
      hiveTable$5.setProperty("comment", c);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$17(final Table hiveTable$5, final String c) {
      hiveTable$5.setProperty("collation", c);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveTable$18(final Table hiveTable$5, final String t) {
      hiveTable$5.setViewOriginalText(t);
      hiveTable$5.setViewExpandedText(t);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHivePartition$4(final StorageDescriptor storageDesc$1, final String x$1) {
      storageDesc$1.setLocation(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHivePartition$5(final StorageDescriptor storageDesc$1, final String x$1) {
      storageDesc$1.setInputFormat(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHivePartition$6(final StorageDescriptor storageDesc$1, final String x$1) {
      storageDesc$1.setOutputFormat(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$toHivePartition$7(final SerDeInfo serdeInfo$1, final String x$1) {
      serdeInfo$1.setSerializationLib(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readHiveStats$1(final String x$30) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(x$30));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readHiveStats$3(final String x$32) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(x$32));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readHiveStats$5(final String x$34) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(x$34));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readHiveStats$7(final BigInt x$36) {
      return x$36.$greater(scala.math.BigInt..MODULE$.int2bigInt(0));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readHiveStats$8(final BigInt x$37) {
      return x$37.$greater(scala.math.BigInt..MODULE$.int2bigInt(0));
   }

   // $FF: synthetic method
   public static final void $anonfun$newHiveConf$1(final HiveConf hiveConf$1, final ClassLoader x$1) {
      hiveConf$1.setClassLoader(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$newHiveConf$5(final HiveConf hiveConf$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         hiveConf$1.set(k, v, org.apache.spark.deploy.SparkHadoopUtil..MODULE$.SOURCE_SPARK());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$newHiveConf$6(final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         String v = (String)x0$2._2();
         MODULE$.logDebug((Function0)(() -> "Applying Hadoop/Hive/Spark and extra properties to Hive Conf:" + k + "=" + v));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$getHive$1(final String x$38) {
      return (int)HiveConf.toSizeBytes(x$38);
   }

   private final void configure$1(final TTransport t, final int maxMessageSize$2) {
      while(t instanceof TFilterTransport) {
         TFilterTransport var6 = (TFilterTransport)t;
         TTransport wrappedTTransport = (TTransport)this.getFieldValue(var6, TFilterTransport.class, "wrapped");
         t = wrappedTTransport;
      }

      if (t instanceof TEndpointTransport var8) {
         TConfiguration tConf = var8.getConfiguration();
         int currentMaxMessageSize = tConf.getMaxMessageSize();
         if (currentMaxMessageSize != maxMessageSize$2) {
            this.logDebug((Function0)(() -> "Change the current metastore client thrift max message size from " + currentMaxMessageSize + " to " + maxMessageSize$2));
            tConf.setMaxMessageSize(maxMessageSize$2);
            var8.updateKnownMessageSize(0L);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var11 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var12 = BoxedUnit.UNIT;
      }

      BoxedUnit var13 = BoxedUnit.UNIT;
   }

   private HiveClientImpl$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
