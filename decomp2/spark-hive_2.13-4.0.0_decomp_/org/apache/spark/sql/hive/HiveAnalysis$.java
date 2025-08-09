package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.plans.logical.DeleteFromTable;
import org.apache.spark.sql.catalyst.plans.logical.InsertIntoDir;
import org.apache.spark.sql.catalyst.plans.logical.InsertIntoStatement;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.plans.logical.SubqueryAlias;
import org.apache.spark.sql.catalyst.rules.Rule;
import org.apache.spark.sql.execution.command.CreateTableCommand;
import org.apache.spark.sql.execution.command.DDLUtils.;
import org.apache.spark.sql.execution.datasources.CreateTable;
import org.apache.spark.sql.hive.execution.CreateHiveTableAsSelectCommand;
import org.apache.spark.sql.hive.execution.InsertIntoHiveDirCommand;
import org.apache.spark.sql.hive.execution.InsertIntoHiveTable$;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;

public final class HiveAnalysis$ extends Rule {
   public static final HiveAnalysis$ MODULE$ = new HiveAnalysis$();

   public LogicalPlan apply(final LogicalPlan plan) {
      return plan.resolveOperators(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final LogicalPlan x1, final Function1 default) {
            boolean var4 = false;
            CreateTable var5 = null;
            if (x1 instanceof InsertIntoStatement var7) {
               LogicalPlan r = var7.table();
               Map partSpec = var7.partitionSpec();
               LogicalPlan query = var7.query();
               boolean overwrite = var7.overwrite();
               boolean ifPartitionNotExists = var7.ifPartitionNotExists();
               if (r instanceof HiveTableRelation var13) {
                  if (.MODULE$.isHiveTable(var13.tableMeta()) && query.resolved()) {
                     return InsertIntoHiveTable$.MODULE$.apply(var13.tableMeta(), partSpec, query, overwrite, ifPartitionNotExists, (Seq)query.output().map((x$1) -> x$1.name()));
                  }
               }
            }

            if (x1 instanceof CreateTable) {
               var4 = true;
               var5 = (CreateTable)x1;
               CatalogTable tableDesc = var5.tableDesc();
               SaveMode mode = var5.mode();
               Option var16 = var5.query();
               if (scala.None..MODULE$.equals(var16) && .MODULE$.isHiveTable(tableDesc)) {
                  CreateTableCommand var10000;
                  boolean var10003;
                  label73: {
                     label72: {
                        var10000 = new CreateTableCommand;
                        SaveMode var17 = SaveMode.Ignore;
                        if (mode == null) {
                           if (var17 == null) {
                              break label72;
                           }
                        } else if (mode.equals(var17)) {
                           break label72;
                        }

                        var10003 = false;
                        break label73;
                     }

                     var10003 = true;
                  }

                  var10000.<init>(tableDesc, var10003);
                  return var10000;
               }
            }

            if (var4) {
               CatalogTable tableDesc = var5.tableDesc();
               SaveMode mode = var5.mode();
               Option var20 = var5.query();
               if (var20 instanceof Some) {
                  Some var21 = (Some)var20;
                  LogicalPlan query = (LogicalPlan)var21.value();
                  if (.MODULE$.isHiveTable(tableDesc) && query.resolved()) {
                     return new CreateHiveTableAsSelectCommand(tableDesc, query, (Seq)query.output().map((x$2) -> x$2.name()), mode);
                  }
               }
            }

            if (x1 instanceof InsertIntoDir var23) {
               boolean isLocal = var23.isLocal();
               CatalogStorageFormat storage = var23.storage();
               Option provider = var23.provider();
               LogicalPlan child = var23.child();
               boolean overwrite = var23.overwrite();
               if (.MODULE$.isHiveTable(provider) && child.resolved()) {
                  Path outputPath = new Path((URI)storage.locationUri().get());
                  if (overwrite) {
                     .MODULE$.verifyNotReadPath(child, outputPath, .MODULE$.verifyNotReadPath$default$3());
                  }

                  return new InsertIntoHiveDirCommand(isLocal, storage, child, overwrite, (Seq)child.output().map((x$3) -> x$3.name()));
               }
            }

            if (x1 instanceof DeleteFromTable var30) {
               LogicalPlan var31 = var30.table();
               if (var31 instanceof SubqueryAlias var32) {
                  LogicalPlan var33 = var32.child();
                  if (var33 instanceof HiveTableRelation var34) {
                     CatalogTable table = var34.tableMeta();
                     throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.unsupportedTableOperationError(table.identifier(), "DELETE");
                  }
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final LogicalPlan x1) {
            boolean var3 = false;
            CreateTable var4 = null;
            if (x1 instanceof InsertIntoStatement var6) {
               LogicalPlan r = var6.table();
               LogicalPlan query = var6.query();
               if (r instanceof HiveTableRelation var9) {
                  if (.MODULE$.isHiveTable(var9.tableMeta()) && query.resolved()) {
                     return true;
                  }
               }
            }

            if (x1 instanceof CreateTable) {
               var3 = true;
               var4 = (CreateTable)x1;
               CatalogTable tableDesc = var4.tableDesc();
               Option var11 = var4.query();
               if (scala.None..MODULE$.equals(var11) && .MODULE$.isHiveTable(tableDesc)) {
                  return true;
               }
            }

            if (var3) {
               CatalogTable tableDesc = var4.tableDesc();
               Option var13 = var4.query();
               if (var13 instanceof Some) {
                  Some var14 = (Some)var13;
                  LogicalPlan query = (LogicalPlan)var14.value();
                  if (.MODULE$.isHiveTable(tableDesc) && query.resolved()) {
                     return true;
                  }
               }
            }

            if (x1 instanceof InsertIntoDir var16) {
               Option provider = var16.provider();
               LogicalPlan child = var16.child();
               if (.MODULE$.isHiveTable(provider) && child.resolved()) {
                  return true;
               }
            }

            if (x1 instanceof DeleteFromTable var19) {
               LogicalPlan var20 = var19.table();
               if (var20 instanceof SubqueryAlias var21) {
                  LogicalPlan var22 = var21.child();
                  if (var22 instanceof HiveTableRelation) {
                     return true;
                  }
               }
            }

            return false;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   private HiveAnalysis$() {
   }
}
