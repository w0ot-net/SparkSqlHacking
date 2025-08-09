package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema;
import org.apache.spark.sql.execution.SparkPlan;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class HiveScriptTransformationExec$ extends AbstractFunction4 implements Serializable {
   public static final HiveScriptTransformationExec$ MODULE$ = new HiveScriptTransformationExec$();

   public final String toString() {
      return "HiveScriptTransformationExec";
   }

   public HiveScriptTransformationExec apply(final String script, final Seq output, final SparkPlan child, final ScriptTransformationIOSchema ioschema) {
      return new HiveScriptTransformationExec(script, output, child, ioschema);
   }

   public Option unapply(final HiveScriptTransformationExec x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.script(), x$0.output(), x$0.child(), x$0.ioschema())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HiveScriptTransformationExec$.class);
   }

   private HiveScriptTransformationExec$() {
   }
}
