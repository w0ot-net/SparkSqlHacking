package org.apache.spark.sql.hive;

import org.apache.spark.sql.catalyst.analysis.resolver.TreeNodeResolver;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.classic.SparkSession;
import org.apache.spark.sql.execution.datasources.DataSourceResolver;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4A\u0001C\u0005\u0001)!AQ\u0004\u0001B\u0001B\u0003%a\u0004\u0003\u0005%\u0001\t\u0005\t\u0015!\u0003&\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u001dq\u0003A1A\u0005\n=Baa\r\u0001!\u0002\u0013\u0001\u0004\"\u0002\u001b\u0001\t\u0003*\u0004\"B3\u0001\t\u00131'A\u0007#bi\u0006\u001cv.\u001e:dK^KG\u000f\u001b%jm\u0016\u0014Vm]8mm\u0016\u0014(B\u0001\u0006\f\u0003\u0011A\u0017N^3\u000b\u00051i\u0011aA:rY*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001)\u0002C\u0001\f\u001c\u001b\u00059\"B\u0001\r\u001a\u0003-!\u0017\r^1t_V\u00148-Z:\u000b\u0005iY\u0011!C3yK\u000e,H/[8o\u0013\tarC\u0001\nECR\f7k\\;sG\u0016\u0014Vm]8mm\u0016\u0014\u0018\u0001D:qCJ\\7+Z:tS>t\u0007CA\u0010#\u001b\u0005\u0001#BA\u0011\f\u0003\u001d\u0019G.Y:tS\u000eL!a\t\u0011\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0002\u0017!Lg/Z\"bi\u0006dwn\u001a\t\u0003M\u001dj\u0011!C\u0005\u0003Q%\u0011!\u0003S5wKN+7o]5p]\u000e\u000bG/\u00197pO\u00061A(\u001b8jiz\"2a\u000b\u0017.!\t1\u0003\u0001C\u0003\u001e\u0007\u0001\u0007a\u0004C\u0003%\u0007\u0001\u0007Q%A\nsK2\fG/[8o\u0007>tg/\u001a:tS>t7/F\u00011!\t1\u0013'\u0003\u00023\u0013\t\u0019\"+\u001a7bi&|gnQ8om\u0016\u00148/[8og\u0006!\"/\u001a7bi&|gnQ8om\u0016\u00148/[8og\u0002\nqB]3t_24Xm\u00149fe\u0006$xN\u001d\u000b\u0004m\u0019C\u0005cA\u001c;y5\t\u0001HC\u0001:\u0003\u0015\u00198-\u00197b\u0013\tY\u0004H\u0001\u0004PaRLwN\u001c\t\u0003{\u0011k\u0011A\u0010\u0006\u0003\u007f\u0001\u000bq\u0001\\8hS\u000e\fGN\u0003\u0002B\u0005\u0006)\u0001\u000f\\1og*\u00111iC\u0001\tG\u0006$\u0018\r\\=ti&\u0011QI\u0010\u0002\f\u0019><\u0017nY1m!2\fg\u000eC\u0003H\r\u0001\u0007A(\u0001\u0005pa\u0016\u0014\u0018\r^8s\u0011\u0015Ie\u00011\u0001K\u0003!\u0011Xm]8mm\u0016\u0014\bCA&c\u001d\tauL\u0004\u0002N;:\u0011aj\u0017\b\u0003\u001fjs!\u0001U-\u000f\u0005ECfB\u0001*X\u001d\t\u0019f+D\u0001U\u0015\t)6#\u0001\u0004=e>|GOP\u0005\u0002%%\u0011\u0001#E\u0005\u0003\u001d=I!\u0001D\u0007\n\u0005\r[\u0011B\u0001/C\u0003!\tg.\u00197zg&\u001c\u0018BA%_\u0015\ta&)\u0003\u0002aC\u00069\u0001/Y2lC\u001e,'BA%_\u0013\t\u0019GMA\nM_\u001eL7-\u00197QY\u0006t'+Z:pYZ,'O\u0003\u0002aC\u0006A\"/Z:pYZ,\u0007*\u001b<f)\u0006\u0014G.\u001a*fY\u0006$\u0018n\u001c8\u0015\u0005q:\u0007\"\u00025\b\u0001\u0004I\u0017!\u00055jm\u0016$\u0016M\u00197f%\u0016d\u0017\r^5p]B\u0011!.\\\u0007\u0002W*\u0011ANQ\u0001\bG\u0006$\u0018\r\\8h\u0013\tq7NA\tISZ,G+\u00192mKJ+G.\u0019;j_:\u0004"
)
public class DataSourceWithHiveResolver extends DataSourceResolver {
   private final RelationConversions relationConversions;

   private RelationConversions relationConversions() {
      return this.relationConversions;
   }

   public Option resolveOperator(final LogicalPlan operator, final TreeNodeResolver resolver) {
      Option var5 = super.resolveOperator(operator, resolver);
      if (var5 instanceof Some var6) {
         LogicalPlan relationAfterDataSourceResolver = (LogicalPlan)var6.value();
         LogicalPlan var10000;
         if (relationAfterDataSourceResolver instanceof HiveTableRelation var10) {
            var10000 = this.resolveHiveTableRelation(var10);
         } else {
            var10000 = relationAfterDataSourceResolver;
         }

         LogicalPlan result = var10000;
         return new Some(result);
      } else {
         return .MODULE$;
      }
   }

   private LogicalPlan resolveHiveTableRelation(final HiveTableRelation hiveTableRelation) {
      return (LogicalPlan)(this.relationConversions().doConvertHiveTableRelationForRead(hiveTableRelation) ? this.relationConversions().convertHiveTableRelationForRead(hiveTableRelation) : hiveTableRelation);
   }

   public DataSourceWithHiveResolver(final SparkSession sparkSession, final HiveSessionCatalog hiveCatalog) {
      super(sparkSession);
      this.relationConversions = new RelationConversions(hiveCatalog);
   }
}
