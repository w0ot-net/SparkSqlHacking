package org.apache.spark.sql.hive;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.plans.logical.InsertIntoStatement;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.plans.logical.Statistics;
import org.apache.spark.sql.catalyst.rules.Rule;
import org.apache.spark.sql.internal.SQLConf;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r3AAB\u0004\u0001%!A1\u0005\u0001B\u0001B\u0003%A\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005c\u0006C\u00036\u0001\u0011%a\u0007C\u0003@\u0001\u0011\u0005\u0003IA\nEKR,'/\\5oKR\u000b'\r\\3Ti\u0006$8O\u0003\u0002\t\u0013\u0005!\u0001.\u001b<f\u0015\tQ1\"A\u0002tc2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\t\u00011\u0003E\u0002\u00153mi\u0011!\u0006\u0006\u0003-]\tQA];mKNT!\u0001G\u0005\u0002\u0011\r\fG/\u00197zgRL!AG\u000b\u0003\tI+H.\u001a\t\u00039\u0005j\u0011!\b\u0006\u0003=}\tq\u0001\\8hS\u000e\fGN\u0003\u0002!/\u0005)\u0001\u000f\\1og&\u0011!%\b\u0002\f\u0019><\u0017nY1m!2\fg.A\u0004tKN\u001c\u0018n\u001c8\u0011\u0005\u00152S\"A\u0005\n\u0005\u001dJ!\u0001D*qCJ\\7+Z:tS>t\u0017A\u0002\u001fj]&$h\b\u0006\u0002+YA\u00111\u0006A\u0007\u0002\u000f!)1E\u0001a\u0001I\u0005!1m\u001c8g+\u0005y\u0003C\u0001\u00194\u001b\u0005\t$B\u0001\u001a\n\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u001b2\u0005\u001d\u0019\u0016\u000bT\"p]\u001a\f!\u0003[5wKR\u000b'\r\\3XSRD7\u000b^1ugR\u0011q'\u0010\t\u0003qmj\u0011!\u000f\u0006\u0003u]\tqaY1uC2|w-\u0003\u0002=s\t\t\u0002*\u001b<f)\u0006\u0014G.\u001a*fY\u0006$\u0018n\u001c8\t\u000by\"\u0001\u0019A\u001c\u0002\u0011I,G.\u0019;j_:\fQ!\u00199qYf$\"aG!\t\u000b\t+\u0001\u0019A\u000e\u0002\tAd\u0017M\u001c"
)
public class DetermineTableStats extends Rule {
   private final SparkSession session;

   public SQLConf conf() {
      return this.session.sessionState().conf();
   }

   public HiveTableRelation org$apache$spark$sql$hive$DetermineTableStats$$hiveTableWithStats(final HiveTableRelation relation) {
      CatalogTable table = relation.tableMeta();
      Seq partitionCols = relation.partitionCols();
      long var10000;
      if (this.conf().fallBackToHdfsForStatsEnabled() && partitionCols.isEmpty()) {
         try {
            Configuration hadoopConf = this.session.sessionState().newHadoopConf();
            Path tablePath = new Path(table.location());
            FileSystem fs = tablePath.getFileSystem(hadoopConf);
            var10000 = fs.getContentSummary(tablePath).getLength();
         } catch (IOException var16) {
            this.logWarning(() -> "Failed to get table size from HDFS.", var16);
            var10000 = this.conf().defaultSizeInBytes();
         }
      } else {
         var10000 = this.conf().defaultSizeInBytes();
      }

      long sizeInBytes = var10000;
      Some stats = new Some(new Statistics(.MODULE$.BigInt().apply(sizeInBytes), org.apache.spark.sql.catalyst.plans.logical.Statistics..MODULE$.apply$default$2(), org.apache.spark.sql.catalyst.plans.logical.Statistics..MODULE$.apply$default$3(), org.apache.spark.sql.catalyst.plans.logical.Statistics..MODULE$.apply$default$4()));
      CatalogTable x$2 = relation.copy$default$1();
      Seq x$3 = relation.copy$default$2();
      Seq x$4 = relation.copy$default$3();
      Option x$5 = relation.copy$default$5();
      return relation.copy(x$2, x$3, x$4, stats, x$5);
   }

   public LogicalPlan apply(final LogicalPlan plan) {
      return plan.resolveOperators(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final DetermineTableStats $outer;

         public final Object applyOrElse(final LogicalPlan x1, final Function1 default) {
            if (x1 instanceof HiveTableRelation var5) {
               if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isHiveTable(var5.tableMeta()) && var5.tableMeta().stats().isEmpty()) {
                  return this.$outer.org$apache$spark$sql$hive$DetermineTableStats$$hiveTableWithStats(var5);
               }
            }

            if (x1 instanceof InsertIntoStatement var6) {
               LogicalPlan relation = var6.table();
               if (relation instanceof HiveTableRelation var8) {
                  if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isHiveTable(var8.tableMeta()) && var8.tableMeta().stats().isEmpty()) {
                     return var6.copy(this.$outer.org$apache$spark$sql$hive$DetermineTableStats$$hiveTableWithStats(var8), var6.copy$default$2(), var6.copy$default$3(), var6.copy$default$4(), var6.copy$default$5(), var6.copy$default$6(), var6.copy$default$7());
                  }
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final LogicalPlan x1) {
            if (x1 instanceof HiveTableRelation var4) {
               if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isHiveTable(var4.tableMeta()) && var4.tableMeta().stats().isEmpty()) {
                  return true;
               }
            }

            if (x1 instanceof InsertIntoStatement var5) {
               LogicalPlan relation = var5.table();
               if (relation instanceof HiveTableRelation var7) {
                  if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isHiveTable(var7.tableMeta()) && var7.tableMeta().stats().isEmpty()) {
                     return true;
                  }
               }
            }

            return false;
         }

         public {
            if (DetermineTableStats.this == null) {
               throw null;
            } else {
               this.$outer = DetermineTableStats.this;
            }
         }
      });
   }

   public DetermineTableStats(final SparkSession session) {
      this.session = session;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
