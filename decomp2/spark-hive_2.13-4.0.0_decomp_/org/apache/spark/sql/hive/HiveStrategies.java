package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.expressions.AttributeSet;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.ExpressionSet;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.plans.logical.ScriptInputOutputSchema;
import org.apache.spark.sql.catalyst.plans.logical.ScriptTransformation;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema;
import org.apache.spark.sql.execution.SparkPlan;
import org.apache.spark.sql.execution.SparkPlanner;
import org.apache.spark.sql.execution.SparkStrategy;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema.;
import org.apache.spark.sql.hive.execution.HiveScriptTransformationExec;
import org.apache.spark.sql.hive.execution.HiveTableScanExec;
import scala.Option;
import scala.Tuple3;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005E4\u0011b\u0003\u0007\u0011\u0002\u0007\u0005ABF5\t\u000bu\u0001A\u0011A\u0010\t\u000f\r\u0002!\u0019!D\u0001I\u001d)\u0011\u0006\u0001E\u0001U\u0019)A\u0006\u0001E\u0001[!)A\t\u0002C\u0001\u000b\")a\t\u0002C\u0001\u000f\u001e)!\r\u0001E\u0001G\u001a)A\r\u0001E\u0001K\")A\t\u0003C\u0001M\")a\t\u0003C\u0001O\nq\u0001*\u001b<f'R\u0014\u0018\r^3hS\u0016\u001c(BA\u0007\u000f\u0003\u0011A\u0017N^3\u000b\u0005=\u0001\u0012aA:rY*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0005\u0002\u0001/A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002AA\u0011\u0001$I\u0005\u0003Ee\u0011A!\u00168ji\u0006a1\u000f]1sWN+7o]5p]V\tQ\u0005\u0005\u0002'O5\ta\"\u0003\u0002)\u001d\ta1\u000b]1sWN+7o]5p]\u0006Y\u0001*\u001b<f'\u000e\u0014\u0018\u000e\u001d;t!\tYC!D\u0001\u0001\u0005-A\u0015N^3TGJL\u0007\u000f^:\u0014\u0005\u0011q\u0003CA\u0018B\u001d\t\u0001dH\u0004\u00022y9\u0011!g\u000f\b\u0003gir!\u0001N\u001d\u000f\u0005UBT\"\u0001\u001c\u000b\u0005]r\u0012A\u0002\u001fs_>$h(C\u0001\u0016\u0013\t\u0019B#\u0003\u0002\u0012%%\u0011q\u0002E\u0005\u0003{9\tqa\u00197bgNL7-\u0003\u0002@\u0001\u00069\u0001/Y2lC\u001e,'BA\u001f\u000f\u0013\t\u00115I\u0001\u0005TiJ\fG/Z4z\u0015\ty\u0004)\u0001\u0004=S:LGO\u0010\u000b\u0002U\u0005)\u0011\r\u001d9msR\u0011\u0001J\u0016\t\u0004\u00136\u0003fB\u0001&M\u001d\t)4*C\u0001\u001b\u0013\ty\u0014$\u0003\u0002O\u001f\n\u00191+Z9\u000b\u0005}J\u0002CA)U\u001b\u0005\u0011&BA*\u000f\u0003%)\u00070Z2vi&|g.\u0003\u0002V%\nI1\u000b]1sWBc\u0017M\u001c\u0005\u0006/\u001a\u0001\r\u0001W\u0001\u0005a2\fg\u000e\u0005\u0002ZA6\t!L\u0003\u0002\\9\u00069An\\4jG\u0006d'BA/_\u0003\u0015\u0001H.\u00198t\u0015\tyf\"\u0001\u0005dCR\fG._:u\u0013\t\t'LA\u0006M_\u001eL7-\u00197QY\u0006t\u0017A\u0004%jm\u0016$\u0016M\u00197f'\u000e\fgn\u001d\t\u0003W!\u0011a\u0002S5wKR\u000b'\r\\3TG\u0006t7o\u0005\u0002\t]Q\t1\r\u0006\u0002IQ\")qK\u0003a\u00011J\u0019!\u000e\u001c8\u0007\t-\u0004\u0001!\u001b\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003[\u0002i\u0011\u0001\u0004\t\u0003#>L!\u0001\u001d*\u0003\u0019M\u0003\u0018M]6QY\u0006tg.\u001a:"
)
public interface HiveStrategies {
   HiveScripts$ HiveScripts();

   HiveTableScans$ HiveTableScans();

   SparkSession sparkSession();

   static void $init$(final HiveStrategies $this) {
   }

   public class HiveScripts$ extends SparkStrategy {
      public Seq apply(final LogicalPlan plan) {
         if (plan instanceof ScriptTransformation var4) {
            String script = var4.script();
            Seq output = var4.output();
            LogicalPlan child = var4.child();
            ScriptInputOutputSchema ioschema = var4.ioschema();
            ScriptTransformationIOSchema hiveIoSchema = .MODULE$.apply(ioschema);
            HiveScriptTransformationExec var10 = new HiveScriptTransformationExec(script, output, this.planLater(child), hiveIoSchema);
            return scala.collection.immutable.Nil..MODULE$.$colon$colon(var10);
         } else {
            return scala.collection.immutable.Nil..MODULE$;
         }
      }
   }

   public class HiveTableScans$ extends SparkStrategy {
      // $FF: synthetic field
      private final SparkPlanner $outer;

      public Seq apply(final LogicalPlan plan) {
         if (plan != null) {
            Option var4 = org.apache.spark.sql.catalyst.planning.PhysicalOperation..MODULE$.unapply(plan);
            if (!var4.isEmpty()) {
               Seq projectList = (Seq)((Tuple3)var4.get())._1();
               Seq filters = (Seq)((Tuple3)var4.get())._2();
               LogicalPlan relation = (LogicalPlan)((Tuple3)var4.get())._3();
               if (relation instanceof HiveTableRelation) {
                  HiveTableRelation var8 = (HiveTableRelation)relation;
                  AttributeSet partitionKeyIds = org.apache.spark.sql.catalyst.expressions.AttributeSet..MODULE$.apply(var8.partitionCols());
                  Seq normalizedFilters = org.apache.spark.sql.execution.datasources.DataSourceStrategy..MODULE$.normalizeExprs((Seq)filters.filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(x$5))), var8.output());
                  ExpressionSet partitionKeyFilters = org.apache.spark.sql.execution.datasources.DataSourceStrategy..MODULE$.getPushedDownFilters(var8.partitionCols(), normalizedFilters);
                  SparkPlan var12 = this.$outer.pruneFilterProject(projectList, (Seq)filters.filter((f) -> BoxesRunTime.boxToBoolean($anonfun$apply$6(partitionKeyIds, f))), (x) -> (Seq)scala.Predef..MODULE$.identity(x), (x$6) -> new HiveTableScanExec(x$6, var8, partitionKeyFilters.toSeq(), ((HiveStrategies)this.$outer).sparkSession()));
                  return scala.collection.immutable.Nil..MODULE$.$colon$colon(var12);
               }
            }
         }

         return scala.collection.immutable.Nil..MODULE$;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$apply$5(final Expression x$5) {
         return x$5.deterministic();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$apply$6(final AttributeSet partitionKeyIds$1, final Expression f) {
         return f.references().isEmpty() || !f.references().subsetOf(partitionKeyIds$1);
      }

      public HiveTableScans$() {
         if (HiveStrategies.this == null) {
            throw null;
         } else {
            this.$outer = HiveStrategies.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
