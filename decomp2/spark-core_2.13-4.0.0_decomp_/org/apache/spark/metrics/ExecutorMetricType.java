package org.apache.spark.metrics;

import org.apache.spark.memory.MemoryManager;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.LinkedHashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y4q!\u0004\b\u0011\u0002G\u0005r\u0003\u0003\u0004\u001f\u0001\u0019\u0005\u0001c\b\u0005\u0007]\u00011\t\u0001E\u0018\b\r-s\u0001\u0012\u0001\tM\r\u0019ia\u0002#\u0001\u0011\u001d\")q\n\u0002C\u0001!\"9\u0011\u000b\u0002b\u0001\n\u0003\u0011\u0006B\u00026\u0005A\u0003%1\u000b\u0003\u0006l\tA\u0005\t1!Q\u0001\n1Dq\u0001\u001f\u0003C\u0002\u0013\u0005\u0011\u0010\u0003\u0004{\t\u0001\u0006Ia\u001c\u0005\bw\u0012\u0011\r\u0011\"\u0001}\u0011\u0019iH\u0001)A\u0005k\n\u0011R\t_3dkR|'/T3ue&\u001cG+\u001f9f\u0015\ty\u0001#A\u0004nKR\u0014\u0018nY:\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u00011A\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t1\u0011I\\=SK\u001a\fqbZ3u\u001b\u0016$(/[2WC2,Xm\u001d\u000b\u0003A\u0019\u00022!G\u0011$\u0013\t\u0011#DA\u0003BeJ\f\u0017\u0010\u0005\u0002\u001aI%\u0011QE\u0007\u0002\u0005\u0019>tw\rC\u0003(\u0003\u0001\u0007\u0001&A\u0007nK6|'/_'b]\u0006<WM\u001d\t\u0003S1j\u0011A\u000b\u0006\u0003WA\ta!\\3n_JL\u0018BA\u0017+\u00055iU-\\8ss6\u000bg.Y4fe\u0006)a.Y7fgV\t\u0001\u0007E\u00022sqr!AM\u001c\u000f\u0005M2T\"\u0001\u001b\u000b\u0005U2\u0012A\u0002\u001fs_>$h(C\u0001\u001c\u0013\tA$$A\u0004qC\u000e\\\u0017mZ3\n\u0005iZ$aA*fc*\u0011\u0001H\u0007\t\u0003{\u0005s!AP \u0011\u0005MR\u0012B\u0001!\u001b\u0003\u0019\u0001&/\u001a3fM&\u0011!i\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001S\u0012\u0006\u0002\u0001F\u000f&S!A\u0012\b\u00021\u001d\u000b'OY1hK\u000e{G\u000e\\3di&|g.T3ue&\u001c7O\u0003\u0002I\u001d\u0005\u0011\u0002K]8dKN\u001cHK]3f\u001b\u0016$(/[2t\u0013\tQeBA\u000fTS:<G.\u001a,bYV,W\t_3dkR|'/T3ue&\u001cG+\u001f9f\u0003I)\u00050Z2vi>\u0014X*\u001a;sS\u000e$\u0016\u0010]3\u0011\u00055#Q\"\u0001\b\u0014\u0005\u0011A\u0012A\u0002\u001fj]&$h\bF\u0001M\u00035iW\r\u001e:jG\u001e+G\u000f^3sgV\t1\u000bE\u0002U3nk\u0011!\u0016\u0006\u0003-^\u000b\u0011\"[7nkR\f'\r\\3\u000b\u0005aS\u0012AC2pY2,7\r^5p]&\u0011!,\u0016\u0002\u000b\u0013:$W\r_3e'\u0016\f(\u0003\u0002/_C\n4A!\u0018\u0001\u00017\naAH]3gS:,W.\u001a8u}A\u0011\u0011dX\u0005\u0003Aj\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002N\u0001A\u00111\r[\u0007\u0002I*\u0011QMZ\u0001\u0003S>T\u0011aZ\u0001\u0005U\u00064\u0018-\u0003\u0002jI\na1+\u001a:jC2L'0\u00192mK\u0006qQ.\u001a;sS\u000e<U\r\u001e;feN\u0004\u0013a\u0001=%mA!\u0011$\\8v\u0013\tq'D\u0001\u0004UkBdWM\r\t\u0005aNdT/D\u0001r\u0015\t\u0011x+A\u0004nkR\f'\r\\3\n\u0005Q\f(!\u0004'j].,G\rS1tQ6\u000b\u0007\u000f\u0005\u0002\u001am&\u0011qO\u0007\u0002\u0004\u0013:$\u0018AD7fiJL7\rV8PM\u001a\u001cX\r^\u000b\u0002_\u0006yQ.\u001a;sS\u000e$vn\u00144gg\u0016$\b%\u0001\u0006ok6lU\r\u001e:jGN,\u0012!^\u0001\f]VlW*\u001a;sS\u000e\u001c\b\u0005"
)
public interface ExecutorMetricType {
   static int numMetrics() {
      return ExecutorMetricType$.MODULE$.numMetrics();
   }

   static LinkedHashMap metricToOffset() {
      return ExecutorMetricType$.MODULE$.metricToOffset();
   }

   static IndexedSeq metricGetters() {
      return ExecutorMetricType$.MODULE$.metricGetters();
   }

   long[] getMetricValues(final MemoryManager memoryManager);

   Seq names();
}
