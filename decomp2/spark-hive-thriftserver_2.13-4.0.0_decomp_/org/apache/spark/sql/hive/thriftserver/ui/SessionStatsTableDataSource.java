package org.apache.spark.sql.hive.thriftserver.ui;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ui.PagedDataSource;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Qa\u0003\u0007\u0001\u0019iA\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006IA\n\u0005\ni\u0001\u0011\t\u0011)A\u0005keB\u0001B\u000f\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005\t\")q\t\u0001C\u0001\u0011\"9a\n\u0001b\u0001\n\u0013y\u0005BB,\u0001A\u0003%\u0001\u000bC\u0003Y\u0001\u0011\u0005\u0013\fC\u0003[\u0001\u0011\u00053\fC\u0003a\u0001\u0011%\u0011MA\u000eTKN\u001c\u0018n\u001c8Ti\u0006$8\u000fV1cY\u0016$\u0015\r^1T_V\u00148-\u001a\u0006\u0003\u001b9\t!!^5\u000b\u0005=\u0001\u0012\u0001\u0004;ie&4Go]3sm\u0016\u0014(BA\t\u0013\u0003\u0011A\u0017N^3\u000b\u0005M!\u0012aA:rY*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014xm\u0005\u0002\u00017A\u0019AD\b\u0011\u000e\u0003uQ!!\u0004\u000b\n\u0005}i\"a\u0004)bO\u0016$G)\u0019;b'>,(oY3\u0011\u0005\u0005\u0012S\"\u0001\u0007\n\u0005\rb!aC*fgNLwN\\%oM>\fA!\u001b8g_\u000e\u0001\u0001cA\u00142A9\u0011\u0001F\f\b\u0003S1j\u0011A\u000b\u0006\u0003W\u0015\na\u0001\u0010:p_Rt\u0014\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=\u0002\u0014a\u00029bG.\fw-\u001a\u0006\u0002[%\u0011!g\r\u0002\u0004'\u0016\f(BA\u00181\u0003!\u0001\u0018mZ3TSj,\u0007C\u0001\u001c8\u001b\u0005\u0001\u0014B\u0001\u001d1\u0005\rIe\u000e^\u0005\u0003iy\t!b]8si\u000e{G.^7o!\ta\u0004I\u0004\u0002>}A\u0011\u0011\u0006M\u0005\u0003\u007fA\na\u0001\u0015:fI\u00164\u0017BA!C\u0005\u0019\u0019FO]5oO*\u0011q\bM\u0001\u0005I\u0016\u001c8\r\u0005\u00027\u000b&\u0011a\t\r\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q)\u0011JS&M\u001bB\u0011\u0011\u0005\u0001\u0005\u0006I\u0015\u0001\rA\n\u0005\u0006i\u0015\u0001\r!\u000e\u0005\u0006u\u0015\u0001\ra\u000f\u0005\u0006\u0007\u0016\u0001\r\u0001R\u0001\u0005I\u0006$\u0018-F\u0001Q!\r\tf\u000bI\u0007\u0002%*\u00111\u000bV\u0001\nS6lW\u000f^1cY\u0016T!!\u0016\u0019\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00023%\u0006)A-\u0019;bA\u0005AA-\u0019;b'&TX-F\u00016\u0003%\u0019H.[2f\t\u0006$\u0018\rF\u0002'9zCQ!X\u0005A\u0002U\nAA\u001a:p[\")q,\u0003a\u0001k\u0005\u0011Ao\\\u0001\t_J$WM]5oOR\u0019!-\u001a4\u0011\u0007\u001d\u001a\u0007%\u0003\u0002eg\tAqJ\u001d3fe&tw\rC\u0003;\u0015\u0001\u00071\bC\u0003D\u0015\u0001\u0007A\t"
)
public class SessionStatsTableDataSource extends PagedDataSource {
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

   private Ordering ordering(final String sortColumn, final boolean desc) {
      Ordering var10000;
      switch (sortColumn == null ? 0 : sortColumn.hashCode()) {
         case -1927368268:
            if (!"Duration".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = .MODULE$.Ordering().by((x$22) -> BoxesRunTime.boxToLong($anonfun$ordering$17(x$22)), scala.math.Ordering.Long..MODULE$);
            break;
         case -1338329542:
            if (!"Finish Time".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = .MODULE$.Ordering().by((x$21) -> BoxesRunTime.boxToLong($anonfun$ordering$16(x$21)), scala.math.Ordering.Long..MODULE$);
            break;
         case -639710459:
            if (!"Session ID".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = .MODULE$.Ordering().by((x$19) -> x$19.sessionId(), scala.math.Ordering.String..MODULE$);
            break;
         case 2343:
            if (!"IP".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = .MODULE$.Ordering().by((x$18) -> x$18.ip(), scala.math.Ordering.String..MODULE$);
            break;
         case 2645995:
            if (!"User".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = .MODULE$.Ordering().by((x$17) -> x$17.userName(), scala.math.Ordering.String..MODULE$);
            break;
         case 361184267:
            if (!"Start Time".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = .MODULE$.Ordering().by((x$20) -> BoxesRunTime.boxToLong($anonfun$ordering$15(x$20)), scala.math.Ordering.Long..MODULE$);
            break;
         case 1041216089:
            if ("Total Execute".equals(sortColumn)) {
               var10000 = .MODULE$.Ordering().by((x$23) -> BoxesRunTime.boxToLong($anonfun$ordering$18(x$23)), scala.math.Ordering.Long..MODULE$);
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
   public static final long $anonfun$ordering$15(final SessionInfo x$20) {
      return x$20.startTimestamp();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$16(final SessionInfo x$21) {
      return x$21.finishTimestamp();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$17(final SessionInfo x$22) {
      return x$22.totalTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$18(final SessionInfo x$23) {
      return x$23.totalExecution();
   }

   public SessionStatsTableDataSource(final Seq info, final int pageSize, final String sortColumn, final boolean desc) {
      super(pageSize);
      this.data = (Seq)info.sorted(this.ordering(sortColumn, desc));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
