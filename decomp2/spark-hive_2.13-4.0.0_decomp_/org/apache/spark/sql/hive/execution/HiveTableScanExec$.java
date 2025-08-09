package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;

public final class HiveTableScanExec$ implements Serializable {
   public static final HiveTableScanExec$ MODULE$ = new HiveTableScanExec$();

   public final String toString() {
      return "HiveTableScanExec";
   }

   public HiveTableScanExec apply(final Seq requestedAttributes, final HiveTableRelation relation, final Seq partitionPruningPred, final SparkSession sparkSession) {
      return new HiveTableScanExec(requestedAttributes, relation, partitionPruningPred, sparkSession);
   }

   public Option unapply(final HiveTableScanExec x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.requestedAttributes(), x$0.relation(), x$0.partitionPruningPred())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HiveTableScanExec$.class);
   }

   private HiveTableScanExec$() {
   }
}
