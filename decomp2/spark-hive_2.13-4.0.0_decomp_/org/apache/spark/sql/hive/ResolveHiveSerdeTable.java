package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.rules.Rule;
import org.apache.spark.sql.execution.datasources.CreateTable;
import org.apache.spark.sql.hive.execution.HiveOptions;
import org.apache.spark.sql.internal.HiveSerDe;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2A!\u0002\u0004\u0001#!A!\u0005\u0001B\u0001B\u0003%1\u0005C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003-\u0001\u0011%Q\u0006C\u00037\u0001\u0011\u0005sGA\u000bSKN|GN^3ISZ,7+\u001a:eKR\u000b'\r\\3\u000b\u0005\u001dA\u0011\u0001\u00025jm\u0016T!!\u0003\u0006\u0002\u0007M\fHN\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\n\u0011\u0007MA\"$D\u0001\u0015\u0015\t)b#A\u0003sk2,7O\u0003\u0002\u0018\u0011\u0005A1-\u0019;bYf\u001cH/\u0003\u0002\u001a)\t!!+\u001e7f!\tY\u0002%D\u0001\u001d\u0015\tib$A\u0004m_\u001eL7-\u00197\u000b\u0005}1\u0012!\u00029mC:\u001c\u0018BA\u0011\u001d\u0005-aunZ5dC2\u0004F.\u00198\u0002\u000fM,7o]5p]B\u0011A%J\u0007\u0002\u0011%\u0011a\u0005\u0003\u0002\r'B\f'o[*fgNLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005%Z\u0003C\u0001\u0016\u0001\u001b\u00051\u0001\"\u0002\u0012\u0003\u0001\u0004\u0019\u0013A\u00053fi\u0016\u0014X.\u001b8f\u0011&4XmU3sI\u0016$\"A\f\u001b\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0005E2\u0012aB2bi\u0006dwnZ\u0005\u0003gA\u0012AbQ1uC2|w\rV1cY\u0016DQ!N\u0002A\u00029\nQ\u0001^1cY\u0016\fQ!\u00199qYf$\"A\u0007\u001d\t\u000be\"\u0001\u0019\u0001\u000e\u0002\tAd\u0017M\u001c"
)
public class ResolveHiveSerdeTable extends Rule {
   public final SparkSession org$apache$spark$sql$hive$ResolveHiveSerdeTable$$session;

   public CatalogTable org$apache$spark$sql$hive$ResolveHiveSerdeTable$$determineHiveSerde(final CatalogTable table) {
      if (table.storage().serde().nonEmpty()) {
         return table;
      } else if (table.bucketSpec().isDefined()) {
         throw new AnalysisException("_LEGACY_ERROR_TEMP_3082", .MODULE$.Map().empty());
      } else {
         CatalogStorageFormat defaultStorage = org.apache.spark.sql.internal.HiveSerDe..MODULE$.getDefaultStorage(this.conf());
         HiveOptions options = new HiveOptions(table.storage().properties());
         CatalogStorageFormat var10000;
         if (options.fileFormat().isDefined()) {
            Option var6 = org.apache.spark.sql.internal.HiveSerDe..MODULE$.sourceToSerDe((String)options.fileFormat().get());
            if (!(var6 instanceof Some)) {
               if (scala.None..MODULE$.equals(var6)) {
                  throw new IllegalArgumentException("invalid fileFormat: '" + options.fileFormat().get() + "'");
               }

               throw new MatchError(var6);
            }

            Some var7 = (Some)var6;
            HiveSerDe s = (HiveSerDe)var7.value();
            Option x$1 = s.inputFormat();
            Option x$2 = s.outputFormat();
            Option x$3 = s.serde();
            Option x$4 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$1();
            boolean x$5 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$5();
            Map x$6 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$6();
            var10000 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy(x$4, x$1, x$2, x$3, x$5, x$6);
         } else if (options.hasInputOutputFormat()) {
            Option x$7 = options.inputFormat();
            Option x$8 = options.outputFormat();
            Option x$9 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$1();
            Option x$10 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$4();
            boolean x$11 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$5();
            Map x$12 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$6();
            var10000 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy(x$9, x$7, x$8, x$10, x$11, x$12);
         } else {
            var10000 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty();
         }

         CatalogStorageFormat fileStorage = var10000;
         if (options.serde().isDefined()) {
            Option x$13 = options.serde();
            Option x$14 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$1();
            Option x$15 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$2();
            Option x$16 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$3();
            boolean x$17 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$5();
            Map x$18 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy$default$6();
            var10000 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty().copy(x$14, x$15, x$16, x$13, x$17, x$18);
         } else {
            var10000 = org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat..MODULE$.empty();
         }

         CatalogStorageFormat rowStorage = var10000;
         Option x$19 = fileStorage.inputFormat().orElse(() -> defaultStorage.inputFormat());
         Option x$20 = fileStorage.outputFormat().orElse(() -> defaultStorage.outputFormat());
         Option x$21 = rowStorage.serde().orElse(() -> fileStorage.serde()).orElse(() -> defaultStorage.serde());
         Map x$22 = options.serdeProperties();
         Option x$23 = table.storage().copy$default$1();
         boolean x$24 = table.storage().copy$default$5();
         CatalogStorageFormat storage = table.storage().copy(x$23, x$19, x$20, x$21, x$24, x$22);
         TableIdentifier x$26 = table.copy$default$1();
         CatalogTableType x$27 = table.copy$default$2();
         StructType x$28 = table.copy$default$4();
         Option x$29 = table.copy$default$5();
         Seq x$30 = table.copy$default$6();
         Option x$31 = table.copy$default$7();
         String x$32 = table.copy$default$8();
         long x$33 = table.copy$default$9();
         long x$34 = table.copy$default$10();
         String x$35 = table.copy$default$11();
         Map x$36 = table.copy$default$12();
         Option x$37 = table.copy$default$13();
         Option x$38 = table.copy$default$14();
         Option x$39 = table.copy$default$15();
         Option x$40 = table.copy$default$16();
         Seq x$41 = table.copy$default$17();
         boolean x$42 = table.copy$default$18();
         boolean x$43 = table.copy$default$19();
         Map x$44 = table.copy$default$20();
         Option x$45 = table.copy$default$21();
         return table.copy(x$26, x$27, storage, x$28, x$29, x$30, x$31, x$32, x$33, x$34, x$35, x$36, x$37, x$38, x$39, x$40, x$41, x$42, x$43, x$44, x$45);
      }
   }

   public LogicalPlan apply(final LogicalPlan plan) {
      return plan.resolveOperators(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final ResolveHiveSerdeTable $outer;

         public final Object applyOrElse(final LogicalPlan x1, final Function1 default) {
            if (x1 instanceof CreateTable var5) {
               CatalogTable t = var5.tableDesc();
               Option query = var5.query();
               if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isHiveTable(t)) {
                  String dbName = (String)t.identifier().database().getOrElse(() -> this.$outer.org$apache$spark$sql$hive$ResolveHiveSerdeTable$$session.catalog().currentDatabase());
                  Some x$1 = new Some(dbName);
                  String x$2 = t.identifier().copy$default$1();
                  Option x$3 = t.identifier().copy$default$3();
                  CatalogTable table = t.copy(t.identifier().copy(x$2, x$1, x$3), t.copy$default$2(), t.copy$default$3(), t.copy$default$4(), t.copy$default$5(), t.copy$default$6(), t.copy$default$7(), t.copy$default$8(), t.copy$default$9(), t.copy$default$10(), t.copy$default$11(), t.copy$default$12(), t.copy$default$13(), t.copy$default$14(), t.copy$default$15(), t.copy$default$16(), t.copy$default$17(), t.copy$default$18(), t.copy$default$19(), t.copy$default$20(), t.copy$default$21());
                  CatalogTable withStorage = this.$outer.org$apache$spark$sql$hive$ResolveHiveSerdeTable$$determineHiveSerde(table);
                  CatalogTable var10000;
                  if (query.isEmpty()) {
                     CatalogTable inferred = HiveUtils$.MODULE$.inferSchema(withStorage);
                     if (inferred.schema().length() <= 0) {
                        throw new AnalysisException("_LEGACY_ERROR_TEMP_3083", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("tableName"), inferred.identifier().toString())}))));
                     }

                     var10000 = inferred;
                  } else {
                     var10000 = withStorage;
                  }

                  CatalogTable withSchema = var10000;
                  return var5.copy(withSchema, var5.copy$default$2(), var5.copy$default$3());
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final LogicalPlan x1) {
            if (x1 instanceof CreateTable var4) {
               CatalogTable t = var4.tableDesc();
               if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isHiveTable(t)) {
                  return true;
               }
            }

            return false;
         }

         public {
            if (ResolveHiveSerdeTable.this == null) {
               throw null;
            } else {
               this.$outer = ResolveHiveSerdeTable.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public ResolveHiveSerdeTable(final SparkSession session) {
      this.org$apache$spark$sql$hive$ResolveHiveSerdeTable$$session = session;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
