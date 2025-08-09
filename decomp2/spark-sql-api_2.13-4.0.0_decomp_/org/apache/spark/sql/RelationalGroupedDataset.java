package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Locale;
import org.apache.spark.annotation.Stable;
import scala.Function1;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ef!\u0002\r\u001a\u0003\u0003\u0011\u0003\"B\u0015\u0001\t\u0003Q\u0003\"B\u0017\u0001\r#q\u0003\"\u0002\u001c\u0001\r#9\u0004\"\u0002%\u0001\r#I\u0005\"B+\u0001\t\u00131\u0006\"\u0002/\u0001\t\u0013i\u0006\"\u00023\u0001\r\u0003)\u0007bBA\u0001\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003\u0003\u0001A\u0011AA\n\u0011\u001d\t\t\u0001\u0001C\u0001\u0003?Aq!!\u0001\u0001\t\u0003\t\t\u0004C\u0004\u0002J\u0001!\t!a\u0013\t\u000f\u00055\u0003\u0001\"\u0001\u0002P!9\u0011q\u000b\u0001\u0005\u0002\u0005e\u0003bBA0\u0001\u0011\u0005\u0011\u0011\r\u0005\b\u0003O\u0002A\u0011AA5\u0011\u001d\ty\u0007\u0001C\u0001\u0003cBq!a\u001e\u0001\t\u0003\tI\bC\u0004\u0002x\u0001!\t!a \t\u000f\u0005]\u0004\u0001\"\u0001\u0002\n\"9\u0011q\u000f\u0001\u0005\u0002\u0005U\u0005bBA<\u0001\u0019\u0005\u00111\u0014\u0005\b\u0003o\u0002a\u0011AAP\u0005a\u0011V\r\\1uS>t\u0017\r\\$s_V\u0004X\r\u001a#bi\u0006\u001cX\r\u001e\u0006\u00035m\t1a]9m\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7\u0001A\n\u0003\u0001\r\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001,!\ta\u0003!D\u0001\u001a\u0003\t!g-F\u00010!\t\u00014G\u0004\u0002-c%\u0011!'G\u0001\ba\u0006\u001c7.Y4f\u0013\t!TGA\u0005ECR\fgI]1nK*\u0011!'G\u0001\u0005i>$e\t\u0006\u00020q!)\u0011h\u0001a\u0001u\u00059\u0011mZ4D_2\u001c\bcA\u001eC\u000b:\u0011A(\u0011\b\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007f\u0005\na\u0001\u0010:p_Rt\u0014\"\u0001\u0014\n\u0005I*\u0013BA\"E\u0005\r\u0019V-\u001d\u0006\u0003e\u0015\u0002\"\u0001\f$\n\u0005\u001dK\"AB\"pYVlg.\u0001\u000btK2,7\r\u001e(v[\u0016\u0014\u0018nY\"pYVlgn\u001d\u000b\u0003u)CQa\u0013\u0003A\u00021\u000b\u0001bY8m\u001d\u0006lWm\u001d\t\u0004w\tk\u0005C\u0001(S\u001d\ty\u0005\u000b\u0005\u0002>K%\u0011\u0011+J\u0001\u0007!J,G-\u001a4\n\u0005M#&AB*ue&twM\u0003\u0002RK\u0005AAo\\!hO\u000e{G\u000e\u0006\u0002F/\")\u0001,\u0002a\u00013\u0006a1m\u001c7B]\u0012lU\r\u001e5pIB!AEW'N\u0013\tYVE\u0001\u0004UkBdWMM\u0001\u0018C\u001e<'/Z4bi\u0016tU/\\3sS\u000e\u001cu\u000e\\;n]N$2a\f0`\u0011\u0015Ye\u00011\u0001M\u0011\u0015\u0001g\u00011\u0001b\u0003!1WO\\2uS>t\u0007\u0003\u0002\u0013c\u000b\u0016K!aY\u0013\u0003\u0013\u0019+hn\u0019;j_:\f\u0014AA1t+\r1GN\u001e\u000b\u0004Obl\b\u0003\u0002\u0017iUVL!![\r\u0003--+\u0017PV1mk\u0016<%o\\;qK\u0012$\u0015\r^1tKR\u0004\"a\u001b7\r\u0001\u0011)Qn\u0002b\u0001]\n\t1*\u0005\u0002peB\u0011A\u0005]\u0005\u0003c\u0016\u0012qAT8uQ&tw\r\u0005\u0002%g&\u0011A/\n\u0002\u0004\u0003:L\bCA6w\t\u00159xA1\u0001o\u0005\u0005!\u0006bB=\b\u0003\u0003\u0005\u001dA_\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u0001\u0017|U&\u0011A0\u0007\u0002\b\u000b:\u001cw\u000eZ3s\u0011\u001dqx!!AA\u0004}\f!\"\u001a<jI\u0016t7-\u001a\u00133!\ra30^\u0001\u0004C\u001e<G#B\u0018\u0002\u0006\u0005%\u0001BBA\u0004\u0011\u0001\u0007\u0011,A\u0004bO\u001e,\u0005\u0010\u001d:\t\u000f\u0005-\u0001\u00021\u0001\u0002\u000e\u0005A\u0011mZ4FqB\u00148\u000f\u0005\u0003%\u0003\u001fI\u0016bAA\tK\tQAH]3qK\u0006$X\r\u001a \u0015\u0007=\n)\u0002C\u0004\u0002\u0018%\u0001\r!!\u0007\u0002\u000b\u0015D\bO]:\u0011\u000b9\u000bY\"T'\n\u0007\u0005uAKA\u0002NCB$2aLA\u0011\u0011\u001d\t9B\u0003a\u0001\u0003G\u0001b!!\n\u000205kUBAA\u0014\u0015\u0011\tI#a\u000b\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0003[\tAA[1wC&!\u0011QDA\u0014)\u0015y\u00131GA\u001c\u0011\u0019\t)d\u0003a\u0001\u000b\u0006!Q\r\u001f9s\u0011\u001d\t9b\u0003a\u0001\u0003s\u0001B\u0001JA\b\u000b\"\u001a1\"!\u0010\u0011\t\u0005}\u0012QI\u0007\u0003\u0003\u0003R1!a\u0011&\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u000f\n\tEA\u0004wCJ\f'oZ:\u0002\u000b\r|WO\u001c;\u0015\u0003=\nA!\\3b]R\u0019q&!\u0015\t\r-k\u0001\u0019AA*!\u0011!\u0013qB')\u00075\ti$A\u0002nCb$2aLA.\u0011\u0019Ye\u00021\u0001\u0002T!\u001aa\"!\u0010\u0002\u0007\u00054x\rF\u00020\u0003GBaaS\bA\u0002\u0005M\u0003fA\b\u0002>\u0005\u0019Q.\u001b8\u0015\u0007=\nY\u0007\u0003\u0004L!\u0001\u0007\u00111\u000b\u0015\u0004!\u0005u\u0012aA:v[R\u0019q&a\u001d\t\r-\u000b\u0002\u0019AA*Q\r\t\u0012QH\u0001\u0006a&4x\u000e\u001e\u000b\u0004W\u0005m\u0004BBA?%\u0001\u0007Q*A\u0006qSZ|GoQ8mk6tG#B\u0016\u0002\u0002\u0006\r\u0005BBA?'\u0001\u0007Q\nC\u0004\u0002\u0006N\u0001\r!a\"\u0002\rY\fG.^3t!\rY$I\u001d\u000b\u0006W\u0005-\u0015Q\u0012\u0005\u0007\u0003{\"\u0002\u0019A'\t\u000f\u0005\u0015E\u00031\u0001\u0002\u0010B)\u0011QEAIe&!\u00111SA\u0014\u0005\u0011a\u0015n\u001d;\u0015\u000b-\n9*!'\t\r\u0005uT\u00031\u0001F\u0011\u001d\t))\u0006a\u0001\u0003\u001f#2aKAO\u0011\u0019\tiH\u0006a\u0001\u000bR)1&!)\u0002$\"1\u0011QP\fA\u0002\u0015Cq!!\"\u0018\u0001\u0004\t9\tK\u0002\u0001\u0003O\u0003B!!+\u0002.6\u0011\u00111\u0016\u0006\u0004\u0003\u0007Z\u0012\u0002BAX\u0003W\u0013aa\u0015;bE2,\u0007"
)
public abstract class RelationalGroupedDataset {
   public Dataset agg(final Column expr, final Column... exprs) {
      return this.agg((Column)expr, (Seq).MODULE$.wrapRefArray(exprs));
   }

   public Dataset mean(final String... colNames) {
      return this.mean((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public Dataset max(final String... colNames) {
      return this.max((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public Dataset avg(final String... colNames) {
      return this.avg((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public Dataset min(final String... colNames) {
      return this.min((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public Dataset sum(final String... colNames) {
      return this.sum((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public abstract Dataset df();

   public abstract Dataset toDF(final Seq aggCols);

   public abstract Seq selectNumericColumns(final Seq colNames);

   private Column toAggCol(final Tuple2 colAndMethod) {
      Column col = this.df().col((String)colAndMethod._1());
      String var4 = ((String)colAndMethod._2()).toLowerCase(Locale.ROOT);
      switch (var4 == null ? 0 : var4.hashCode()) {
         case -892408046:
            if ("stddev".equals(var4)) {
               return functions$.MODULE$.stddev(col);
            }
            break;
         case -631448035:
            if ("average".equals(var4)) {
               return functions$.MODULE$.avg(col);
            }
            break;
         case 96978:
            if ("avg".equals(var4)) {
               return functions$.MODULE$.avg(col);
            }
            break;
         case 114211:
            if ("std".equals(var4)) {
               return functions$.MODULE$.stddev(col);
            }
            break;
         case 3347397:
            if ("mean".equals(var4)) {
               return functions$.MODULE$.avg(col);
            }
            break;
         case 3530753:
            if ("size".equals(var4)) {
               return functions$.MODULE$.count(col);
            }
            break;
         case 94851343:
            if ("count".equals(var4)) {
               return functions$.MODULE$.count(col);
            }
      }

      return Column$.MODULE$.fn(var4, .MODULE$.wrapRefArray(new Column[]{col}));
   }

   private Dataset aggregateNumericColumns(final Seq colNames, final Function1 function) {
      return this.toDF((Seq)this.selectNumericColumns(colNames).map(function));
   }

   public abstract KeyValueGroupedDataset as(final Encoder evidence$1, final Encoder evidence$2);

   public Dataset agg(final Tuple2 aggExpr, final Seq aggExprs) {
      return this.toDF((Seq)((IterableOps)aggExprs.$plus$colon(aggExpr)).map((colAndMethod) -> this.toAggCol(colAndMethod)));
   }

   public Dataset agg(final Map exprs) {
      return this.toDF(((IterableOnceOps)exprs.map((colAndMethod) -> this.toAggCol(colAndMethod))).toSeq());
   }

   public Dataset agg(final java.util.Map exprs) {
      return this.agg(scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(exprs).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Dataset agg(final Column expr, final Seq exprs) {
      return this.toDF((Seq)exprs.$plus$colon(expr));
   }

   public Dataset count() {
      Column var1 = functions$.MODULE$.count(functions$.MODULE$.lit(BoxesRunTime.boxToInteger(1))).as("count");
      return this.toDF(scala.collection.immutable.Nil..MODULE$.$colon$colon(var1));
   }

   public Dataset mean(final Seq colNames) {
      return this.aggregateNumericColumns(colNames, (e) -> functions$.MODULE$.avg(e));
   }

   public Dataset max(final Seq colNames) {
      return this.aggregateNumericColumns(colNames, (e) -> functions$.MODULE$.max(e));
   }

   public Dataset avg(final Seq colNames) {
      return this.aggregateNumericColumns(colNames, (e) -> functions$.MODULE$.avg(e));
   }

   public Dataset min(final Seq colNames) {
      return this.aggregateNumericColumns(colNames, (e) -> functions$.MODULE$.min(e));
   }

   public Dataset sum(final Seq colNames) {
      return this.aggregateNumericColumns(colNames, (e) -> functions$.MODULE$.sum(e));
   }

   public RelationalGroupedDataset pivot(final String pivotColumn) {
      return this.pivot(this.df().col(pivotColumn));
   }

   public RelationalGroupedDataset pivot(final String pivotColumn, final Seq values) {
      return this.pivot(this.df().col(pivotColumn), values);
   }

   public RelationalGroupedDataset pivot(final String pivotColumn, final List values) {
      return this.pivot(this.df().col(pivotColumn), values);
   }

   public RelationalGroupedDataset pivot(final Column pivotColumn, final List values) {
      return this.pivot(pivotColumn, scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(values).asScala().toSeq());
   }

   public abstract RelationalGroupedDataset pivot(final Column pivotColumn);

   public abstract RelationalGroupedDataset pivot(final Column pivotColumn, final Seq values);

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
