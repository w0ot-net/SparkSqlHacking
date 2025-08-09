package org.apache.spark.graphx;

import org.apache.spark.internal.Logging;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-r!\u0002\u0004\b\u0011\u0003\u0001b!\u0002\n\b\u0011\u0003\u0019\u0002\"\u0002\u0011\u0002\t\u0003\t\u0003\"\u0002\u0012\u0002\t\u0003\u0019\u0003b\u0002@\u0002#\u0003%\ta \u0005\n\u0003;\t\u0011\u0013!C\u0001\u0003?\ta\u0001\u0015:fO\u0016d'B\u0001\u0005\n\u0003\u00199'/\u00199iq*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005E\tQ\"A\u0004\u0003\rA\u0013XmZ3m'\r\tAC\u0007\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005mqR\"\u0001\u000f\u000b\u0005uI\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005}a\"a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\tQ!\u00199qYf,B\u0001\n\u00177\u000fR)Q\u0005\u001d:usR!a%S+l)\u00119\u0003\bQ\"\u0011\tEA#&N\u0005\u0003S\u001d\u0011Qa\u0012:ba\"\u0004\"a\u000b\u0017\r\u0001\u0011)Qf\u0001b\u0001]\t\u0011a\u000bR\t\u0003_I\u0002\"!\u0006\u0019\n\u0005E2\"a\u0002(pi\"Lgn\u001a\t\u0003+MJ!\u0001\u000e\f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002,m\u0011)qg\u0001b\u0001]\t\u0011Q\t\u0012\u0005\bs\r\t\t\u0011q\u0001;\u0003))g/\u001b3f]\u000e,G%\r\t\u0004wyRS\"\u0001\u001f\u000b\u0005u2\u0012a\u0002:fM2,7\r^\u0005\u0003\u007fq\u0012\u0001b\u00117bgN$\u0016m\u001a\u0005\b\u0003\u000e\t\t\u0011q\u0001C\u0003))g/\u001b3f]\u000e,GE\r\t\u0004wy*\u0004b\u0002#\u0004\u0003\u0003\u0005\u001d!R\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004cA\u001e?\rB\u00111f\u0012\u0003\u0006\u0011\u000e\u0011\rA\f\u0002\u0002\u0003\")!j\u0001a\u0001\u0017\u0006)a\u000f\u001d:pOB1Q\u0003\u0014(+\r*J!!\u0014\f\u0003\u0013\u0019+hn\u0019;j_:\u001c\u0004CA(S\u001d\t\t\u0002+\u0003\u0002R\u000f\u00059\u0001/Y2lC\u001e,\u0017BA*U\u0005!1VM\u001d;fq&#'BA)\b\u0011\u001516\u00011\u0001X\u0003\u001d\u0019XM\u001c3Ng\u001e\u0004B!\u0006-[;&\u0011\u0011L\u0006\u0002\n\rVt7\r^5p]F\u0002B!E.+k%\u0011Al\u0002\u0002\f\u000b\u0012<W\r\u0016:ja2,G\u000fE\u0002_K\"t!a\u00183\u000f\u0005\u0001\u001cW\"A1\u000b\u0005\t|\u0011A\u0002\u001fs_>$h(C\u0001\u0018\u0013\t\tf#\u0003\u0002gO\nA\u0011\n^3sCR|'O\u0003\u0002R-A!Q#\u001b(G\u0013\tQgC\u0001\u0004UkBdWM\r\u0005\u0006Y\u000e\u0001\r!\\\u0001\t[\u0016\u0014x-Z'tOB)QC\u001c$G\r&\u0011qN\u0006\u0002\n\rVt7\r^5p]JBQ!]\u0002A\u0002\u001d\nQa\u001a:ba\"DQa]\u0002A\u0002\u0019\u000b!\"\u001b8ji&\fG.T:h\u0011\u001d)8\u0001%AA\u0002Y\fQ\"\\1y\u0013R,'/\u0019;j_:\u001c\bCA\u000bx\u0013\tAhCA\u0002J]RDqA_\u0002\u0011\u0002\u0003\u000710A\bbGRLg/\u001a#je\u0016\u001cG/[8o!\t\tB0\u0003\u0002~\u000f\tiQ\tZ4f\t&\u0014Xm\u0019;j_:\fq\"\u00199qYf$C-\u001a4bk2$HeM\u000b\t\u0003\u0003\t9\"!\u0007\u0002\u001cU\u0011\u00111\u0001\u0016\u0004m\u0006\u00151FAA\u0004!\u0011\tI!a\u0005\u000e\u0005\u0005-!\u0002BA\u0007\u0003\u001f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005Ea#\u0001\u0006b]:|G/\u0019;j_:LA!!\u0006\u0002\f\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b5\"!\u0019\u0001\u0018\u0005\u000b]\"!\u0019\u0001\u0018\u0005\u000b!#!\u0019\u0001\u0018\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIQ*\u0002\"!\t\u0002&\u0005\u001d\u0012\u0011F\u000b\u0003\u0003GQ3a_A\u0003\t\u0015iSA1\u0001/\t\u00159TA1\u0001/\t\u0015AUA1\u0001/\u0001"
)
public final class Pregel {
   public static EdgeDirection apply$default$4() {
      return Pregel$.MODULE$.apply$default$4();
   }

   public static int apply$default$3() {
      return Pregel$.MODULE$.apply$default$3();
   }

   public static Graph apply(final Graph graph, final Object initialMsg, final int maxIterations, final EdgeDirection activeDirection, final Function3 vprog, final Function1 sendMsg, final Function2 mergeMsg, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      return Pregel$.MODULE$.apply(graph, initialMsg, maxIterations, activeDirection, vprog, sendMsg, mergeMsg, evidence$1, evidence$2, evidence$3);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Pregel$.MODULE$.LogStringContext(sc);
   }
}
