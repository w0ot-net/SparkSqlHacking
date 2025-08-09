package org.apache.spark.sql.hive.thriftserver.ui;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2;
import org.apache.spark.ui.PagedDataSource;
import scala.Option.;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q\u0001D\u0007\u0001\u001bmA\u0001\"\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\nq\u0001\u0011\t\u0011)A\u0005suB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005\u0011\")1\n\u0001C\u0001\u0019\"9!\u000b\u0001b\u0001\n\u0013\u0019\u0006BB.\u0001A\u0003%A\u000bC\u0003]\u0001\u0011\u0005S\fC\u0003_\u0001\u0011\u0005s\fC\u0003f\u0001\u0011%a\rC\u0003j\u0001\u0011%!NA\fTc2\u001cF/\u0019;t)\u0006\u0014G.\u001a#bi\u0006\u001cv.\u001e:dK*\u0011abD\u0001\u0003k&T!\u0001E\t\u0002\u0019QD'/\u001b4ug\u0016\u0014h/\u001a:\u000b\u0005I\u0019\u0012\u0001\u00025jm\u0016T!\u0001F\u000b\u0002\u0007M\fHN\u0003\u0002\u0017/\u0005)1\u000f]1sW*\u0011\u0001$G\u0001\u0007CB\f7\r[3\u000b\u0003i\t1a\u001c:h'\t\u0001A\u0004E\u0002\u001e?\u0005j\u0011A\b\u0006\u0003\u001dUI!\u0001\t\u0010\u0003\u001fA\u000bw-\u001a3ECR\f7k\\;sG\u0016\u0004\"AI\u0012\u000e\u00035I!\u0001J\u0007\u0003!M\u000bHn\u0015;biN$\u0016M\u00197f%><\u0018\u0001B5oM>\u001c\u0001\u0001E\u0002)eUr!!K\u0018\u000f\u0005)jS\"A\u0016\u000b\u000512\u0013A\u0002\u001fs_>$h(C\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001\u0014'A\u0004qC\u000e\\\u0017mZ3\u000b\u00039J!a\r\u001b\u0003\u0007M+\u0017O\u0003\u00021cA\u0011!EN\u0005\u0003o5\u0011Q\"\u0012=fGV$\u0018n\u001c8J]\u001a|\u0017\u0001\u00039bO\u0016\u001c\u0016N_3\u0011\u0005iZT\"A\u0019\n\u0005q\n$aA%oi&\u0011\u0001hH\u0001\u000bg>\u0014HoQ8mk6t\u0007C\u0001!E\u001d\t\t%\t\u0005\u0002+c%\u00111)M\u0001\u0007!J,G-\u001a4\n\u0005\u00153%AB*ue&twM\u0003\u0002Dc\u0005!A-Z:d!\tQ\u0014*\u0003\u0002Kc\t9!i\\8mK\u0006t\u0017A\u0002\u001fj]&$h\bF\u0003N\u001d>\u0003\u0016\u000b\u0005\u0002#\u0001!)Q%\u0002a\u0001O!)\u0001(\u0002a\u0001s!)a(\u0002a\u0001\u007f!)q)\u0002a\u0001\u0011\u0006!A-\u0019;b+\u0005!\u0006cA+[C5\taK\u0003\u0002X1\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u00033F\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0019d+A\u0003eCR\f\u0007%\u0001\u0005eCR\f7+\u001b>f+\u0005I\u0014!C:mS\u000e,G)\u0019;b)\r\u0001\u0017m\u0019\t\u0004QI\n\u0003\"\u00022\n\u0001\u0004I\u0014\u0001\u00024s_6DQ\u0001Z\u0005A\u0002e\n!\u0001^8\u0002!M\fHn\u0015;biN$\u0016M\u00197f%><HCA\u0011h\u0011\u0015A'\u00021\u00016\u00035)\u00070Z2vi&|g.\u00138g_\u0006AqN\u001d3fe&tw\rF\u0002l]>\u00042\u0001\u000b7\"\u0013\tiGG\u0001\u0005Pe\u0012,'/\u001b8h\u0011\u0015q4\u00021\u0001@\u0011\u001595\u00021\u0001I\u0001"
)
public class SqlStatsTableDataSource extends PagedDataSource {
   private final Seq data;

   private Seq data() {
      return this.data;
   }

   public int dataSize() {
      return this.data().size();
   }

   public Seq sliceData(final int from, final int to) {
      return (Seq)this.data().slice(from, to);
   }

   private SqlStatsTableRow sqlStatsTableRow(final ExecutionInfo executionInfo) {
      long duration = executionInfo.totalTime(executionInfo.closeTimestamp());
      long executionTime = executionInfo.totalTime(executionInfo.finishTimestamp());
      String detail = (String).MODULE$.apply(executionInfo.detail()).filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$sqlStatsTableRow$1(x$5))).getOrElse(() -> executionInfo.executePlan());
      Seq jobId = (Seq)executionInfo.jobId().toSeq().sorted(scala.math.Ordering.String..MODULE$);
      return new SqlStatsTableRow(jobId, duration, executionTime, executionInfo, detail);
   }

   private Ordering ordering(final String sortColumn, final boolean desc) {
      Ordering var10000;
      switch (sortColumn == null ? 0 : sortColumn.hashCode()) {
         case -1927368268:
            if (!"Duration".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$13) -> BoxesRunTime.boxToLong($anonfun$ordering$8(x$13)), scala.math.Ordering.Long..MODULE$);
            break;
         case -1338329542:
            if (!"Finish Time".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$10) -> BoxesRunTime.boxToLong($anonfun$ordering$5(x$10)), scala.math.Ordering.Long..MODULE$);
            break;
         case -127225803:
            if (!"Close Time".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$11) -> BoxesRunTime.boxToLong($anonfun$ordering$6(x$11)), scala.math.Ordering.Long..MODULE$);
            break;
         case -81180337:
            if (!"Statement".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$14) -> x$14.executionInfo().statement(), scala.math.Ordering.String..MODULE$);
            break;
         case 2645995:
            if (!"User".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$6) -> x$6.executionInfo().userName(), scala.math.Ordering.String..MODULE$);
            break;
         case 71743864:
            if (!"JobID".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$7) -> x$7.jobId().headOption(), scala.math.Ordering..MODULE$.Option(scala.math.Ordering.String..MODULE$));
            break;
         case 80204913:
            if (!"State".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$15) -> x$15.executionInfo().state(), HiveThriftServer2.ExecutionState$.MODULE$.ValueOrdering());
            break;
         case 361184267:
            if (!"Start Time".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$9) -> BoxesRunTime.boxToLong($anonfun$ordering$4(x$9)), scala.math.Ordering.Long..MODULE$);
            break;
         case 1763419925:
            if (!"Execution Time".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$12) -> BoxesRunTime.boxToLong($anonfun$ordering$7(x$12)), scala.math.Ordering.Long..MODULE$);
            break;
         case 1958081466:
            if (!"GroupID".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$8) -> x$8.executionInfo().groupId(), scala.math.Ordering.String..MODULE$);
            break;
         case 2043610225:
            if ("Detail".equals(sortColumn)) {
               var10000 = scala.package..MODULE$.Ordering().by((x$16) -> x$16.detail(), scala.math.Ordering.String..MODULE$);
               break;
            }

            throw new IllegalArgumentException("Unknown column: " + sortColumn);
         default:
            throw new IllegalArgumentException("Unknown column: " + sortColumn);
      }

      Ordering ordering = var10000;
      if (desc) {
         return ordering.reverse();
      } else {
         return ordering;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sqlStatsTableRow$1(final String x$5) {
      return !x$5.isEmpty();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$4(final SqlStatsTableRow x$9) {
      return x$9.executionInfo().startTimestamp();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$5(final SqlStatsTableRow x$10) {
      return x$10.executionInfo().finishTimestamp();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$6(final SqlStatsTableRow x$11) {
      return x$11.executionInfo().closeTimestamp();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$7(final SqlStatsTableRow x$12) {
      return x$12.executionTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$8(final SqlStatsTableRow x$13) {
      return x$13.duration();
   }

   public SqlStatsTableDataSource(final Seq info, final int pageSize, final String sortColumn, final boolean desc) {
      super(pageSize);
      this.data = (Seq)((SeqOps)info.map((executionInfo) -> this.sqlStatsTableRow(executionInfo))).sorted(this.ordering(sortColumn, desc));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
