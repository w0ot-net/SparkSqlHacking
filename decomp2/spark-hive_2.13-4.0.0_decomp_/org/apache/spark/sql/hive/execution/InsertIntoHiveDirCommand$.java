package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class InsertIntoHiveDirCommand$ extends AbstractFunction5 implements Serializable {
   public static final InsertIntoHiveDirCommand$ MODULE$ = new InsertIntoHiveDirCommand$();

   public final String toString() {
      return "InsertIntoHiveDirCommand";
   }

   public InsertIntoHiveDirCommand apply(final boolean isLocal, final CatalogStorageFormat storage, final LogicalPlan query, final boolean overwrite, final Seq outputColumnNames) {
      return new InsertIntoHiveDirCommand(isLocal, storage, query, overwrite, outputColumnNames);
   }

   public Option unapply(final InsertIntoHiveDirCommand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToBoolean(x$0.isLocal()), x$0.storage(), x$0.query(), BoxesRunTime.boxToBoolean(x$0.overwrite()), x$0.outputColumnNames())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InsertIntoHiveDirCommand$.class);
   }

   private InsertIntoHiveDirCommand$() {
   }
}
