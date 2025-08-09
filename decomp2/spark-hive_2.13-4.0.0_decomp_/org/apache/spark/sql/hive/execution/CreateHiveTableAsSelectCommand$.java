package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class CreateHiveTableAsSelectCommand$ extends AbstractFunction4 implements Serializable {
   public static final CreateHiveTableAsSelectCommand$ MODULE$ = new CreateHiveTableAsSelectCommand$();

   public final String toString() {
      return "CreateHiveTableAsSelectCommand";
   }

   public CreateHiveTableAsSelectCommand apply(final CatalogTable tableDesc, final LogicalPlan query, final Seq outputColumnNames, final SaveMode mode) {
      return new CreateHiveTableAsSelectCommand(tableDesc, query, outputColumnNames, mode);
   }

   public Option unapply(final CreateHiveTableAsSelectCommand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.tableDesc(), x$0.query(), x$0.outputColumnNames(), x$0.mode())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CreateHiveTableAsSelectCommand$.class);
   }

   private CreateHiveTableAsSelectCommand$() {
   }
}
