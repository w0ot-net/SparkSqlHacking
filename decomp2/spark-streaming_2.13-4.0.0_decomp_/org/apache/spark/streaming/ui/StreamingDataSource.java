package org.apache.spark.streaming.ui;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ui.PagedDataSource;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114Qa\u0003\u0007\u0001\u0019YA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\na\u0001\u0011\t\u0011)A\u0005cUB\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005\u0001\")1\t\u0001C\u0001\t\"9!\n\u0001b\u0001\n\u0013Y\u0005BB*\u0001A\u0003%A\nC\u0003U\u0001\u0011ES\u000bC\u0003W\u0001\u0011Es\u000bC\u0003]\u0001\u0011%QLA\nTiJ,\u0017-\\5oO\u0012\u000bG/Y*pkJ\u001cWM\u0003\u0002\u000e\u001d\u0005\u0011Q/\u001b\u0006\u0003\u001fA\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c\"\u0001A\f\u0011\u0007aQB$D\u0001\u001a\u0015\ti\u0001#\u0003\u0002\u001c3\ty\u0001+Y4fI\u0012\u000bG/Y*pkJ\u001cW\r\u0005\u0002\u001e=5\tA\"\u0003\u0002 \u0019\tY!)\u0019;dQVKE)\u0019;b\u0003\u0011IgNZ8\u0004\u0001A\u00191%\f\u000f\u000f\u0005\u0011RcBA\u0013)\u001b\u00051#BA\u0014\"\u0003\u0019a$o\\8u}%\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Y\u00059\u0001/Y2lC\u001e,'\"A\u0015\n\u00059z#aA*fc*\u00111\u0006L\u0001\ta\u0006<WmU5{KB\u0011!gM\u0007\u0002Y%\u0011A\u0007\f\u0002\u0004\u0013:$\u0018B\u0001\u0019\u001b\u0003)\u0019xN\u001d;D_2,XN\u001c\t\u0003qqr!!\u000f\u001e\u0011\u0005\u0015b\u0013BA\u001e-\u0003\u0019\u0001&/\u001a3fM&\u0011QH\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005mb\u0013\u0001\u00023fg\u000e\u0004\"AM!\n\u0005\tc#a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\u00153u\tS%\u0011\u0005u\u0001\u0001\"\u0002\u0011\u0006\u0001\u0004\u0011\u0003\"\u0002\u0019\u0006\u0001\u0004\t\u0004\"\u0002\u001c\u0006\u0001\u00049\u0004\"B \u0006\u0001\u0004\u0001\u0015\u0001\u00023bi\u0006,\u0012\u0001\u0014\t\u0004\u001bJcR\"\u0001(\u000b\u0005=\u0003\u0016!C5n[V$\u0018M\u00197f\u0015\t\tF&\u0001\u0006d_2dWm\u0019;j_:L!A\f(\u0002\u000b\u0011\fG/\u0019\u0011\u0002\u0011\u0011\fG/Y*ju\u0016,\u0012!M\u0001\ng2L7-\u001a#bi\u0006$2A\t-[\u0011\u0015I\u0016\u00021\u00012\u0003\u00111'o\\7\t\u000bmK\u0001\u0019A\u0019\u0002\u0005Q|\u0017\u0001C8sI\u0016\u0014\u0018N\\4\u0015\u0007y\u000b7\rE\u0002$?rI!\u0001Y\u0018\u0003\u0011=\u0013H-\u001a:j]\u001eDQA\u0019\u0006A\u0002]\naaY8mk6t\u0007\"B \u000b\u0001\u0004\u0001\u0005"
)
public class StreamingDataSource extends PagedDataSource {
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

   private Ordering ordering(final String column, final boolean desc) {
      Ordering var10000;
      switch (column == null ? 0 : column.hashCode()) {
         case -1547717086:
            if (!"Records".equals(column)) {
               throw new IllegalArgumentException("Unknown Column: " + column);
            }

            var10000 = .MODULE$.Ordering().by((x$6) -> BoxesRunTime.boxToLong($anonfun$ordering$2(x$6)), scala.math.Ordering.Long..MODULE$);
            break;
         case -791410697:
            if (!"Scheduling Delay".equals(column)) {
               throw new IllegalArgumentException("Unknown Column: " + column);
            }

            var10000 = .MODULE$.Ordering().by((x$7) -> BoxesRunTime.boxToLong($anonfun$ordering$3(x$7)), scala.math.Ordering.Long..MODULE$);
            break;
         case -572465817:
            if (!"Total Delay".equals(column)) {
               throw new IllegalArgumentException("Unknown Column: " + column);
            }

            var10000 = .MODULE$.Ordering().by((x$9) -> BoxesRunTime.boxToLong($anonfun$ordering$7(x$9)), scala.math.Ordering.Long..MODULE$);
            break;
         case 734311354:
            if (!"Processing Time".equals(column)) {
               throw new IllegalArgumentException("Unknown Column: " + column);
            }

            var10000 = .MODULE$.Ordering().by((x$8) -> BoxesRunTime.boxToLong($anonfun$ordering$5(x$8)), scala.math.Ordering.Long..MODULE$);
            break;
         case 1666265139:
            if ("Batch Time".equals(column)) {
               var10000 = .MODULE$.Ordering().by((x$5) -> BoxesRunTime.boxToLong($anonfun$ordering$1(x$5)), scala.math.Ordering.Long..MODULE$);
               break;
            }

            throw new IllegalArgumentException("Unknown Column: " + column);
         default:
            throw new IllegalArgumentException("Unknown Column: " + column);
      }

      Ordering ordering = var10000;
      if (desc) {
         return ordering.reverse();
      } else {
         return ordering;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$1(final BatchUIData x$5) {
      return x$5.batchTime().milliseconds();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$2(final BatchUIData x$6) {
      return x$6.numRecords();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$3(final BatchUIData x$7) {
      return BoxesRunTime.unboxToLong(x$7.schedulingDelay().getOrElse((JFunction0.mcJ.sp)() -> Long.MAX_VALUE));
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$5(final BatchUIData x$8) {
      return BoxesRunTime.unboxToLong(x$8.processingDelay().getOrElse((JFunction0.mcJ.sp)() -> Long.MAX_VALUE));
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$7(final BatchUIData x$9) {
      return BoxesRunTime.unboxToLong(x$9.totalDelay().getOrElse((JFunction0.mcJ.sp)() -> Long.MAX_VALUE));
   }

   public StreamingDataSource(final Seq info, final int pageSize, final String sortColumn, final boolean desc) {
      super(pageSize);
      this.data = (Seq)info.sorted(this.ordering(sortColumn, desc));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
