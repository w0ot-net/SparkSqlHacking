package org.apache.spark.sql.hive.execution;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.ErrorMsg;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.FileSinkDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.spark.SparkException;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.expressions.Attribute;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.hive.client.HiveClientImpl$;
import org.apache.spark.sql.internal.SessionState;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\raa\u0002\u0004\b!\u0003\r\t\u0001\u0006\u0005\u00067\u0001!\t\u0001\b\u0005\u0006A\u0001!\t!\t\u0005\u0006m\u0001!\ta\u000e\u0005\u00069\u0002!\t!\u0018\u0005\u0006I\u0002!\t!\u001a\u0002\u0012-F:&/\u001b;fg\"Kg/Z+uS2\u001c(B\u0001\u0005\n\u0003%)\u00070Z2vi&|gN\u0003\u0002\u000b\u0017\u0005!\u0001.\u001b<f\u0015\taQ\"A\u0002tc2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0003\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003u\u0001\"A\u0006\u0010\n\u0005}9\"\u0001B+oSR\f\u0001cZ3u!\u0006\u0014H/\u001b;j_:\u001c\u0006/Z2\u0015\u0005\t\u0002\u0004\u0003B\u0012+[5r!\u0001\n\u0015\u0011\u0005\u0015:R\"\u0001\u0014\u000b\u0005\u001d\u001a\u0012A\u0002\u001fs_>$h(\u0003\u0002*/\u00051\u0001K]3eK\u001aL!a\u000b\u0017\u0003\u00075\u000b\u0007O\u0003\u0002*/A\u00111EL\u0005\u0003_1\u0012aa\u0015;sS:<\u0007\"B\u0019\u0003\u0001\u0004\u0011\u0014!\u00039beRLG/[8o!\u0011\u0019#&L\u001a\u0011\u0007Y!T&\u0003\u00026/\t1q\n\u001d;j_:\f!dZ3u\tft\u0017-\\5d!\u0006\u0014H/\u001b;j_:\u001cu\u000e\\;n]N$B\u0001O%R%B\u0019\u0011HP!\u000f\u0005ibdBA\u0013<\u0013\u0005A\u0012BA\u001f\u0018\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0010!\u0003\u0007M+\u0017O\u0003\u0002>/A\u0011!iR\u0007\u0002\u0007*\u0011A)R\u0001\fKb\u0004(/Z:tS>t7O\u0003\u0002G\u0017\u0005A1-\u0019;bYf\u001cH/\u0003\u0002I\u0007\nI\u0011\t\u001e;sS\n,H/\u001a\u0005\u0006\u0015\u000e\u0001\raS\u0001\u0006i\u0006\u0014G.\u001a\t\u0003\u0019>k\u0011!\u0014\u0006\u0003\u001d\u0016\u000bqaY1uC2|w-\u0003\u0002Q\u001b\na1)\u0019;bY><G+\u00192mK\")\u0011g\u0001a\u0001e!)1k\u0001a\u0001)\u0006)\u0011/^3ssB\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\bY><\u0017nY1m\u0015\tIV)A\u0003qY\u0006t7/\u0003\u0002\\-\nYAj\\4jG\u0006d\u0007\u000b\\1o\u0003u9W\r^(qi&|gn],ji\"D\u0015N^3Ck\u000e\\W\r^,sSR,GC\u0001\u0012_\u0011\u0015yF\u00011\u0001a\u0003)\u0011WoY6fiN\u0003Xm\u0019\t\u0004-Q\n\u0007C\u0001'c\u0013\t\u0019WJ\u0001\u0006Ck\u000e\\W\r^*qK\u000e\fQd]3ukBD\u0015\rZ8pa\u000e{gN\u001a$pe\u000e{W\u000e\u001d:fgNLwN\u001c\u000b\u0005;\u0019\u001c8\u0010C\u0003h\u000b\u0001\u0007\u0001.\u0001\u0007gS2,7+\u001b8l\u0007>tg\r\u0005\u0002jc6\t!N\u0003\u0002lY\u0006!\u0001\u000f\\1o\u0015\tig.\u0001\u0002rY*\u0011!b\u001c\u0006\u0003a>\ta\u0001[1e_>\u0004\u0018B\u0001:k\u000511\u0015\u000e\\3TS:\\G)Z:d\u0011\u0015!X\u00011\u0001v\u0003)A\u0017\rZ8pa\u000e{gN\u001a\t\u0003mfl\u0011a\u001e\u0006\u0003q>\fAaY8oM&\u0011!p\u001e\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8\t\u000bq,\u0001\u0019A?\u0002\u0019M\u0004\u0018M]6TKN\u001c\u0018n\u001c8\u0011\u0005y|X\"A\u0006\n\u0007\u0005\u00051B\u0001\u0007Ta\u0006\u00148nU3tg&|g\u000e"
)
public interface V1WritesHiveUtils {
   // $FF: synthetic method
   static Map getPartitionSpec$(final V1WritesHiveUtils $this, final Map partition) {
      return $this.getPartitionSpec(partition);
   }

   default Map getPartitionSpec(final Map partition) {
      return (Map)partition.map((x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Option var4 = (Option)x0$1._2();
            if (var4 instanceof Some) {
               Some var5 = (Some)var4;
               String var6 = (String)var5.value();
               if (var6 == null) {
                  return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.DEFAULT_PARTITION_NAME());
               }
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Option var8 = (Option)x0$1._2();
            if (var8 instanceof Some) {
               Some var9 = (Some)var8;
               String value = (String)var9.value();
               return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), value);
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Option var12 = (Option)x0$1._2();
            if (scala.None..MODULE$.equals(var12)) {
               return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), "");
            }
         }

         throw new MatchError(x0$1);
      });
   }

   // $FF: synthetic method
   static Seq getDynamicPartitionColumns$(final V1WritesHiveUtils $this, final CatalogTable table, final Map partition, final LogicalPlan query) {
      return $this.getDynamicPartitionColumns(table, partition, query);
   }

   default Seq getDynamicPartitionColumns(final CatalogTable table, final Map partition, final LogicalPlan query) {
      int numDynamicPartitions = partition.values().count((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getDynamicPartitionColumns$1(x$1)));
      int numStaticPartitions = partition.values().count((x$2) -> BoxesRunTime.boxToBoolean($anonfun$getDynamicPartitionColumns$2(x$2)));
      Map partitionSpec = this.getPartitionSpec(partition);
      Table hiveQlTable = HiveClientImpl$.MODULE$.toHiveTable(table, HiveClientImpl$.MODULE$.toHiveTable$default$2());
      TableDesc tableDesc = new TableDesc(hiveQlTable.getInputFormatClass(), hiveQlTable.getOutputFormatClass(), hiveQlTable.getMetadata());
      String partitionColumns = tableDesc.getProperties().getProperty("partition_columns");
      String[] partitionColumnNames = (String[])scala.Option..MODULE$.apply(partitionColumns).map((x$3) -> x$3.split("/")).getOrElse(() -> (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)));
      Set var10000 = scala.Predef..MODULE$.wrapRefArray((Object[])partitionColumnNames).toSet();
      Set var11 = partition.keySet();
      if (var10000 == null) {
         if (var11 != null) {
            throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.requestedPartitionsMismatchTablePartitionsError(table, partition);
         }
      } else if (!var10000.equals(var11)) {
         throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.requestedPartitionsMismatchTablePartitionsError(table, partition);
      }

      SessionState sessionState = org.apache.spark.sql.SparkSession..MODULE$.active().sessionState();
      Configuration hadoopConf = sessionState.newHadoopConf();
      if (numDynamicPartitions > 0) {
         if (!scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(hadoopConf.get("hive.exec.dynamic.partition", "true")))) {
            throw new SparkException(ErrorMsg.DYNAMIC_PARTITION_DISABLED.getMsg());
         }

         if (numStaticPartitions == 0 && hadoopConf.get("hive.exec.dynamic.partition.mode", "strict").equalsIgnoreCase("strict")) {
            throw new SparkException(ErrorMsg.DYNAMIC_PARTITION_STRICT_MODE.getMsg());
         }

         boolean[] isDynamic = (boolean[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])partitionColumnNames), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$getDynamicPartitionColumns$5(partitionSpec, x$4)), scala.reflect.ClassTag..MODULE$.Boolean());
         if (scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.booleanArrayOps((boolean[])scala.collection.ArrayOps..MODULE$.init$extension(scala.Predef..MODULE$.booleanArrayOps(isDynamic))), scala.Predef..MODULE$.wrapBooleanArray((boolean[])scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.booleanArrayOps(isDynamic))))), new Tuple2.mcZZ.sp(true, false))) {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3079", scala.Predef..MODULE$.Map().empty());
         }
      }

      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.takeRight$extension(scala.Predef..MODULE$.refArrayOps((Object[])partitionColumnNames), numDynamicPartitions)), (name) -> {
         Attribute attr = (Attribute)query.resolve(scala.collection.immutable.Nil..MODULE$.$colon$colon(name), sessionState.analyzer().resolver()).getOrElse(() -> {
            throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.cannotResolveAttributeError(name, ((IterableOnceOps)query.output().map((x$5) -> x$5.name())).mkString(", "));
         });
         return attr.withName(name.toLowerCase(Locale.ROOT));
      }, scala.reflect.ClassTag..MODULE$.apply(Attribute.class))).toImmutableArraySeq();
   }

   // $FF: synthetic method
   static Map getOptionsWithHiveBucketWrite$(final V1WritesHiveUtils $this, final Option bucketSpec) {
      return $this.getOptionsWithHiveBucketWrite(bucketSpec);
   }

   default Map getOptionsWithHiveBucketWrite(final Option bucketSpec) {
      return (Map)bucketSpec.map((x$6) -> (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.sql.execution.datasources.BucketingUtils..MODULE$.optionForHiveCompatibleBucketWrite()), "true")})))).getOrElse(() -> scala.Predef..MODULE$.Map().empty());
   }

   // $FF: synthetic method
   static void setupHadoopConfForCompression$(final V1WritesHiveUtils $this, final FileSinkDesc fileSinkConf, final Configuration hadoopConf, final SparkSession sparkSession) {
      $this.setupHadoopConfForCompression(fileSinkConf, hadoopConf, sparkSession);
   }

   default void setupHadoopConfForCompression(final FileSinkDesc fileSinkConf, final Configuration hadoopConf, final SparkSession sparkSession) {
      String var6 = fileSinkConf.getTableInfo().getOutputFileFormatClassName().toLowerCase(Locale.ROOT);
      switch (var6 == null ? 0 : var6.hashCode()) {
         default:
            boolean isCompressed = var6.endsWith("orcoutputformat") ? false : scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(hadoopConf.get("hive.exec.compress.output", "false")));
            if (isCompressed) {
               hadoopConf.set("mapreduce.output.fileoutputformat.compress", "true");
               fileSinkConf.setCompressed(true);
               fileSinkConf.setCompressCodec(hadoopConf.get("mapreduce.output.fileoutputformat.compress.codec"));
               fileSinkConf.setCompressType(hadoopConf.get("mapreduce.output.fileoutputformat.compress.type"));
            } else {
               HiveOptions$.MODULE$.getHiveWriteCompression(fileSinkConf.getTableInfo(), sparkSession.sessionState().conf()).foreach((x0$1) -> {
                  $anonfun$setupHadoopConfForCompression$1(hadoopConf, x0$1);
                  return BoxedUnit.UNIT;
               });
            }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$getDynamicPartitionColumns$1(final Option x$1) {
      return x$1.isEmpty();
   }

   // $FF: synthetic method
   static boolean $anonfun$getDynamicPartitionColumns$2(final Option x$2) {
      return x$2.nonEmpty();
   }

   // $FF: synthetic method
   static boolean $anonfun$getDynamicPartitionColumns$5(final Map partitionSpec$1, final String x$4) {
      return ((String)partitionSpec$1.apply(x$4)).isEmpty();
   }

   // $FF: synthetic method
   static void $anonfun$setupHadoopConfForCompression$1(final Configuration hadoopConf$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String compression = (String)x0$1._1();
         String codec = (String)x0$1._2();
         hadoopConf$1.set(compression, codec);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   static void $init$(final V1WritesHiveUtils $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
