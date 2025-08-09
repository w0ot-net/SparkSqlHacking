package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.analysis.CastSupport;
import org.apache.spark.sql.catalyst.catalog.CatalogStatistics;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTablePartition;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.expressions.AliasHelper;
import org.apache.spark.sql.catalyst.expressions.Attribute;
import org.apache.spark.sql.catalyst.expressions.AttributeMap;
import org.apache.spark.sql.catalyst.expressions.AttributeSet;
import org.apache.spark.sql.catalyst.expressions.Cast;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.ExpressionSet;
import org.apache.spark.sql.catalyst.expressions.NamedExpression;
import org.apache.spark.sql.catalyst.expressions.PredicateHelper;
import org.apache.spark.sql.catalyst.expressions.PythonUDF;
import org.apache.spark.sql.catalyst.plans.logical.Aggregate;
import org.apache.spark.sql.catalyst.plans.logical.ColumnStat;
import org.apache.spark.sql.catalyst.plans.logical.Filter;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.plans.logical.Project;
import org.apache.spark.sql.catalyst.plans.logical.statsEstimation.FilterEstimation;
import org.apache.spark.sql.catalyst.rules.Rule;
import org.apache.spark.sql.execution.datasources.DataSourceStrategy.;
import org.apache.spark.sql.internal.SQLConf;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005Y4Qa\u0002\u0005\u0001\u0019QA\u0001\"\r\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\u0006o\u0001!\t\u0001\u000f\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\t\u0002!I!\u0012\u0005\u0006I\u0002!I!\u001a\u0005\u0006e\u0002!\te\u001d\u0002\u0019!J,h.\u001a%jm\u0016$\u0016M\u00197f!\u0006\u0014H/\u001b;j_:\u001c(BA\u0005\u000b\u0003%)\u00070Z2vi&|gN\u0003\u0002\f\u0019\u0005!\u0001.\u001b<f\u0015\tia\"A\u0002tc2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\n\u0005\u0001U)3\u0006E\u0002\u00177ui\u0011a\u0006\u0006\u00031e\tQA];mKNT!A\u0007\u0007\u0002\u0011\r\fG/\u00197zgRL!\u0001H\f\u0003\tI+H.\u001a\t\u0003=\rj\u0011a\b\u0006\u0003A\u0005\nq\u0001\\8hS\u000e\fGN\u0003\u0002#3\u0005)\u0001\u000f\\1og&\u0011Ae\b\u0002\f\u0019><\u0017nY1m!2\fg\u000e\u0005\u0002'S5\tqE\u0003\u0002)3\u0005A\u0011M\\1msNL7/\u0003\u0002+O\tY1)Y:u'V\u0004\bo\u001c:u!\tas&D\u0001.\u0015\tq\u0013$A\u0006fqB\u0014Xm]:j_:\u001c\u0018B\u0001\u0019.\u0005=\u0001&/\u001a3jG\u0006$X\rS3ma\u0016\u0014\u0018aB:fgNLwN\\\u0002\u0001!\t!T'D\u0001\r\u0013\t1DB\u0001\u0007Ta\u0006\u00148nU3tg&|g.\u0001\u0004=S:LGO\u0010\u000b\u0003sm\u0002\"A\u000f\u0001\u000e\u0003!AQ!\r\u0002A\u0002M\nAaY8oMV\ta\b\u0005\u0002@\u00056\t\u0001I\u0003\u0002B\u0019\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002D\u0001\n91+\u0015'D_:4\u0017AF4fiB\u000b'\u000f^5uS>t7*Z=GS2$XM]:\u0015\u0007\u0019KE\f\u0005\u0002-\u000f&\u0011\u0001*\f\u0002\u000e\u000bb\u0004(/Z:tS>t7+\u001a;\t\u000b)#\u0001\u0019A&\u0002\u000f\u0019LG\u000e^3sgB\u0019AJV-\u000f\u00055\u001bfB\u0001(R\u001b\u0005y%B\u0001)3\u0003\u0019a$o\\8u}%\t!+A\u0003tG\u0006d\u0017-\u0003\u0002U+\u00069\u0001/Y2lC\u001e,'\"\u0001*\n\u0005]C&aA*fc*\u0011A+\u0016\t\u0003YiK!aW\u0017\u0003\u0015\u0015C\bO]3tg&|g\u000eC\u0003^\t\u0001\u0007a,\u0001\u0005sK2\fG/[8o!\ty&-D\u0001a\u0015\t\t\u0017$A\u0004dCR\fGn\\4\n\u0005\r\u0004'!\u0005%jm\u0016$\u0016M\u00197f%\u0016d\u0017\r^5p]\u0006yQ\u000f\u001d3bi\u0016$\u0016M\u00197f\u001b\u0016$\u0018\r\u0006\u0003gS*\u0004\bCA0h\u0013\tA\u0007M\u0001\u0007DCR\fGn\\4UC\ndW\rC\u0003^\u000b\u0001\u0007a\fC\u0003l\u000b\u0001\u0007A.\u0001\tqeVtW\r\u001a)beRLG/[8ogB\u0019AJV7\u0011\u0005}s\u0017BA8a\u0005U\u0019\u0015\r^1m_\u001e$\u0016M\u00197f!\u0006\u0014H/\u001b;j_:DQ!]\u0003A\u0002\u0019\u000b1\u0003]1si&$\u0018n\u001c8LKf4\u0015\u000e\u001c;feN\fQ!\u00199qYf$\"!\b;\t\u000bU4\u0001\u0019A\u000f\u0002\tAd\u0017M\u001c"
)
public class PruneHiveTablePartitions extends Rule implements CastSupport, PredicateHelper {
   public final SparkSession org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$session;

   public Seq splitConjunctivePredicates(final Expression condition) {
      return PredicateHelper.splitConjunctivePredicates$(this, condition);
   }

   public Option findExpressionAndTrackLineageDown(final Expression exp, final LogicalPlan plan) {
      return PredicateHelper.findExpressionAndTrackLineageDown$(this, exp, plan);
   }

   public Seq splitDisjunctivePredicates(final Expression condition) {
      return PredicateHelper.splitDisjunctivePredicates$(this, condition);
   }

   public Expression buildBalancedPredicate(final Seq expressions, final Function2 op) {
      return PredicateHelper.buildBalancedPredicate$(this, expressions, op);
   }

   public boolean canEvaluate(final Expression expr, final LogicalPlan plan) {
      return PredicateHelper.canEvaluate$(this, expr, plan);
   }

   public boolean canEvaluateWithinJoin(final Expression expr) {
      return PredicateHelper.canEvaluateWithinJoin$(this, expr);
   }

   public Option extractPredicatesWithinOutputSet(final Expression condition, final AttributeSet outputSet) {
      return PredicateHelper.extractPredicatesWithinOutputSet$(this, condition, outputSet);
   }

   public boolean isNullIntolerant(final Expression expr) {
      return PredicateHelper.isNullIntolerant$(this, expr);
   }

   public Seq outputWithNullability(final Seq output, final Seq nonNullAttrExprIds) {
      return PredicateHelper.outputWithNullability$(this, output, nonNullAttrExprIds);
   }

   public boolean isLikelySelective(final Expression e) {
      return PredicateHelper.isLikelySelective$(this, e);
   }

   public AttributeMap getAliasMap(final Project plan) {
      return AliasHelper.getAliasMap$(this, plan);
   }

   public AttributeMap getAliasMap(final Aggregate plan) {
      return AliasHelper.getAliasMap$(this, plan);
   }

   public AttributeMap getAliasMap(final Seq exprs) {
      return AliasHelper.getAliasMap$(this, exprs);
   }

   public Expression replaceAlias(final Expression expr, final AttributeMap aliasMap) {
      return AliasHelper.replaceAlias$(this, expr, aliasMap);
   }

   public NamedExpression replaceAliasButKeepName(final NamedExpression expr, final AttributeMap aliasMap) {
      return AliasHelper.replaceAliasButKeepName$(this, expr, aliasMap);
   }

   public Expression trimAliases(final Expression e) {
      return AliasHelper.trimAliases$(this, e);
   }

   public Expression trimNonTopLevelAliases(final Expression e) {
      return AliasHelper.trimNonTopLevelAliases$(this, e);
   }

   public Cast cast(final Expression child, final DataType dataType) {
      return CastSupport.cast$(this, child, dataType);
   }

   public SQLConf conf() {
      return this.org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$session.sessionState().conf();
   }

   public ExpressionSet org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$getPartitionKeyFilters(final Seq filters, final HiveTableRelation relation) {
      Seq normalizedFilters = .MODULE$.normalizeExprs((Seq)filters.filter((f) -> BoxesRunTime.boxToBoolean($anonfun$getPartitionKeyFilters$1(f))), relation.output());
      AttributeSet partitionColumnSet = org.apache.spark.sql.catalyst.expressions.AttributeSet..MODULE$.apply(relation.partitionCols());
      return org.apache.spark.sql.catalyst.expressions.ExpressionSet..MODULE$.apply((IterableOnce)normalizedFilters.flatMap((x$2) -> this.extractPredicatesWithinOutputSet(x$2, partitionColumnSet)));
   }

   public CatalogTable org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$updateTableMeta(final HiveTableRelation relation, final Seq prunedPartitions, final ExpressionSet partitionKeyFilters) {
      Seq sizeOfPartitions = (Seq)prunedPartitions.map((partition) -> BoxesRunTime.boxToLong($anonfun$updateTableMeta$1(partition)));
      if (sizeOfPartitions.forall((JFunction1.mcZJ.sp)(x$5x) -> x$5x > 0L)) {
         Option filteredStats = (new FilterEstimation(new Filter((Expression)partitionKeyFilters.reduce(org.apache.spark.sql.catalyst.expressions.And..MODULE$), relation))).estimate();
         Option colStats = filteredStats.map((x$6x) -> (Map)x$6x.attributeStats().map((x0$1) -> {
               if (x0$1 != null) {
                  Attribute attr = (Attribute)x0$1._1();
                  ColumnStat colStat = (ColumnStat)x0$1._2();
                  return new Tuple2(attr.name(), colStat.toCatalogColumnStat(attr.name(), attr.dataType()));
               } else {
                  throw new MatchError(x0$1);
               }
            }));
         Option rowCount = prunedPartitions.forall((x$7x) -> BoxesRunTime.boxToBoolean($anonfun$updateTableMeta$7(x$7x))) ? scala.Option..MODULE$.apply(((IterableOnceOps)prunedPartitions.map((x$10x) -> (BigInt)((CatalogStatistics)x$10x.stats().get()).rowCount().get())).sum(scala.math.Numeric.BigIntIsIntegral..MODULE$)) : filteredStats.flatMap((x$11x) -> x$11x.rowCount());
         Some x$1 = new Some(new CatalogStatistics(scala.package..MODULE$.BigInt().apply(BoxesRunTime.unboxToLong(sizeOfPartitions.sum(scala.math.Numeric.LongIsIntegral..MODULE$))), rowCount, (Map)colStats.getOrElse(() -> scala.Predef..MODULE$.Map().empty())));
         TableIdentifier x$2 = relation.tableMeta().copy$default$1();
         CatalogTableType x$3 = relation.tableMeta().copy$default$2();
         CatalogStorageFormat x$4 = relation.tableMeta().copy$default$3();
         StructType x$5 = relation.tableMeta().copy$default$4();
         Option x$6 = relation.tableMeta().copy$default$5();
         Seq x$7 = relation.tableMeta().copy$default$6();
         Option x$8 = relation.tableMeta().copy$default$7();
         String x$9 = relation.tableMeta().copy$default$8();
         long x$10 = relation.tableMeta().copy$default$9();
         long x$11 = relation.tableMeta().copy$default$10();
         String x$12 = relation.tableMeta().copy$default$11();
         Map x$13 = relation.tableMeta().copy$default$12();
         Option x$14 = relation.tableMeta().copy$default$14();
         Option x$15 = relation.tableMeta().copy$default$15();
         Option x$16 = relation.tableMeta().copy$default$16();
         Seq x$17 = relation.tableMeta().copy$default$17();
         boolean x$18 = relation.tableMeta().copy$default$18();
         boolean x$19 = relation.tableMeta().copy$default$19();
         Map x$20 = relation.tableMeta().copy$default$20();
         Option x$21 = relation.tableMeta().copy$default$21();
         return relation.tableMeta().copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$1, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);
      } else {
         return relation.tableMeta();
      }
   }

   public LogicalPlan apply(final LogicalPlan plan) {
      return plan.resolveOperators(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final PruneHiveTablePartitions $outer;

         public final Object applyOrElse(final LogicalPlan x1, final Function1 default) {
            if (x1 != null) {
               Option var5 = org.apache.spark.sql.catalyst.planning.PhysicalOperation..MODULE$.unapply(x1);
               if (!var5.isEmpty()) {
                  Seq projections = (Seq)((Tuple3)var5.get())._1();
                  Seq filters = (Seq)((Tuple3)var5.get())._2();
                  LogicalPlan relation = (LogicalPlan)((Tuple3)var5.get())._3();
                  if (relation instanceof HiveTableRelation) {
                     HiveTableRelation var9 = (HiveTableRelation)relation;
                     if (filters.nonEmpty() && var9.isPartitioned() && var9.prunedPartitions().isEmpty()) {
                        ExpressionSet partitionKeyFilters = this.$outer.org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$getPartitionKeyFilters(filters, var9);
                        if (partitionKeyFilters.nonEmpty()) {
                           Seq newPartitions = org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.listPartitionsByFilter(this.$outer.conf(), this.$outer.org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$session.sessionState().catalog(), var9.tableMeta(), partitionKeyFilters.toSeq());
                           CatalogTable newTableMeta = this.$outer.org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$updateTableMeta(var9, newPartitions, partitionKeyFilters);
                           Some x$2 = new Some(newPartitions);
                           Seq x$3 = var9.copy$default$2();
                           Seq x$4 = var9.copy$default$3();
                           Option x$5 = var9.copy$default$4();
                           HiveTableRelation newRelation = var9.copy(newTableMeta, x$3, x$4, x$5, x$2);
                           return new Project(projections, new Filter((Expression)filters.reduceLeft(org.apache.spark.sql.catalyst.expressions.And..MODULE$), newRelation));
                        }

                        return x1;
                     }
                  }
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final LogicalPlan x1) {
            if (x1 != null) {
               Option var4 = org.apache.spark.sql.catalyst.planning.PhysicalOperation..MODULE$.unapply(x1);
               if (!var4.isEmpty()) {
                  Seq filters = (Seq)((Tuple3)var4.get())._2();
                  LogicalPlan relation = (LogicalPlan)((Tuple3)var4.get())._3();
                  if (relation instanceof HiveTableRelation) {
                     HiveTableRelation var7 = (HiveTableRelation)relation;
                     if (filters.nonEmpty() && var7.isPartitioned() && var7.prunedPartitions().isEmpty()) {
                        return true;
                     }
                  }
               }
            }

            return false;
         }

         public {
            if (PruneHiveTablePartitions.this == null) {
               throw null;
            } else {
               this.$outer = PruneHiveTablePartitions.this;
            }
         }
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitionKeyFilters$2(final Expression x$1) {
      return x$1 instanceof PythonUDF;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitionKeyFilters$1(final Expression f) {
      return f.deterministic() && !org.apache.spark.sql.catalyst.expressions.SubqueryExpression..MODULE$.hasSubquery(f) && !f.exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getPartitionKeyFilters$2(x$1)));
   }

   // $FF: synthetic method
   public static final long $anonfun$updateTableMeta$2(final String x$3) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$3));
   }

   // $FF: synthetic method
   public static final long $anonfun$updateTableMeta$3(final String x$4) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   // $FF: synthetic method
   public static final long $anonfun$updateTableMeta$1(final CatalogTablePartition partition) {
      Option rawDataSize = partition.parameters().get("rawDataSize").map((x$3) -> BoxesRunTime.boxToLong($anonfun$updateTableMeta$2(x$3)));
      Option totalSize = partition.parameters().get("totalSize").map((x$4) -> BoxesRunTime.boxToLong($anonfun$updateTableMeta$3(x$4)));
      if (rawDataSize.isDefined() && BoxesRunTime.unboxToLong(rawDataSize.get()) > 0L) {
         return BoxesRunTime.unboxToLong(rawDataSize.get());
      } else {
         return totalSize.isDefined() && BoxesRunTime.unboxToLong(totalSize.get()) > 0L ? BoxesRunTime.unboxToLong(totalSize.get()) : 0L;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateTableMeta$9(final BigInt x$9) {
      return x$9.$greater(scala.math.BigInt..MODULE$.int2bigInt(0));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateTableMeta$7(final CatalogTablePartition x$7) {
      return x$7.stats().flatMap((x$8) -> x$8.rowCount()).exists((x$9) -> BoxesRunTime.boxToBoolean($anonfun$updateTableMeta$9(x$9)));
   }

   public PruneHiveTablePartitions(final SparkSession session) {
      this.org$apache$spark$sql$hive$execution$PruneHiveTablePartitions$$session = session;
      CastSupport.$init$(this);
      AliasHelper.$init$(this);
      PredicateHelper.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
