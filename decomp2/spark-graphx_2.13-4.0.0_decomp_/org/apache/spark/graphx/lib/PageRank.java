package org.apache.spark.graphx.lib;

import org.apache.spark.graphx.Graph;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t-u!\u0002\u000b\u0016\u0011\u0003\u0001c!\u0002\u0012\u0016\u0011\u0003\u0019\u0003\"\u0002\u0019\u0002\t\u0003\t\u0004\"\u0002\u001a\u0002\t\u0003\u0019\u0004b\u00021\u0002#\u0003%\t!\u0019\u0005\u0006_\u0006!I\u0001\u001d\u0005\b\u00037\tA\u0011AA\u000f\u0011%\tI%AI\u0001\n\u0003\tY\u0005C\u0005\u0002R\u0005\t\n\u0011\"\u0001\u0002T!9\u00111D\u0001\u0005\u0002\u0005u\u0003bBAC\u0003\u0011\u0005\u0011q\u0011\u0005\b\u0003\u000b\u000bA\u0011AAX\u0011\u001d\t9.\u0001C\u0001\u00033D\u0011Ba\u0006\u0002#\u0003%\tA!\u0007\t\u000f\t}\u0011\u0001\"\u0001\u0003\"!I!QI\u0001\u0012\u0002\u0013\u0005!q\t\u0005\b\u0005\u001b\nA\u0011\u0001B(\u0011%\u0011\u0019(AI\u0001\n\u0003\u0011)\bC\u0005\u0003|\u0005\t\n\u0011\"\u0001\u0003~!9!1Q\u0001\u0005\n\t\u0015\u0015\u0001\u0003)bO\u0016\u0014\u0016M\\6\u000b\u0005Y9\u0012a\u00017jE*\u0011\u0001$G\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u0005iY\u0012!B:qCJ\\'B\u0001\u000f\u001e\u0003\u0019\t\u0007/Y2iK*\ta$A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\"\u00035\tQC\u0001\u0005QC\u001e,'+\u00198l'\r\tAE\u000b\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-rS\"\u0001\u0017\u000b\u00055J\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005=b#a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0001\n1A];o+\r!t\t\u0016\u000b\u0005kYKf\fF\u00027{A\u0003Ba\u000e\u001d;u5\tq#\u0003\u0002:/\t)qI]1qQB\u0011QeO\u0005\u0003y\u0019\u0012a\u0001R8vE2,\u0007b\u0002 \u0004\u0003\u0003\u0005\u001daP\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u0001!D\u000b6\t\u0011I\u0003\u0002CM\u00059!/\u001a4mK\u000e$\u0018B\u0001#B\u0005!\u0019E.Y:t)\u0006<\u0007C\u0001$H\u0019\u0001!Q\u0001S\u0002C\u0002%\u0013!A\u0016#\u0012\u0005)k\u0005CA\u0013L\u0013\taeEA\u0004O_RD\u0017N\\4\u0011\u0005\u0015r\u0015BA('\u0005\r\te.\u001f\u0005\b#\u000e\t\t\u0011q\u0001S\u0003))g/\u001b3f]\u000e,GE\r\t\u0004\u0001\u000e\u001b\u0006C\u0001$U\t\u0015)6A1\u0001J\u0005\t)E\tC\u0003X\u0007\u0001\u0007\u0001,A\u0003he\u0006\u0004\b\u000e\u0005\u00038q\u0015\u001b\u0006\"\u0002.\u0004\u0001\u0004Y\u0016a\u00028v[&#XM\u001d\t\u0003KqK!!\u0018\u0014\u0003\u0007%sG\u000fC\u0004`\u0007A\u0005\t\u0019\u0001\u001e\u0002\u0013I,7/\u001a;Qe>\u0014\u0017!\u0004:v]\u0012\"WMZ1vYR$3'F\u0002c[:,\u0012a\u0019\u0016\u0003u\u0011\\\u0013!\u001a\t\u0003M.l\u0011a\u001a\u0006\u0003Q&\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005)4\u0013AC1o]>$\u0018\r^5p]&\u0011An\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002%\u0005\u0005\u0004IE!B+\u0005\u0005\u0004I\u0015!\u0003:v]V\u0003H-\u0019;f)\u00151\u0014o\u001d=z\u0011\u0015\u0011X\u00011\u00017\u0003%\u0011\u0018M\\6He\u0006\u0004\b\u000eC\u0003u\u000b\u0001\u0007Q/\u0001\u0007qKJ\u001cxN\\1mSj,G\r\u0005\u0002&m&\u0011qO\n\u0002\b\u0005>|G.Z1o\u0011\u0015yV\u00011\u0001;\u0011\u0015QX\u00011\u0001|\u0003\r\u0019(o\u0019\t\u0004y\u0006UabA?\u0002\u00129\u0019a0a\u0004\u000f\u0007}\fiA\u0004\u0003\u0002\u0002\u0005-a\u0002BA\u0002\u0003\u0013i!!!\u0002\u000b\u0007\u0005\u001dq$\u0001\u0004=e>|GOP\u0005\u0002=%\u0011A$H\u0005\u00035mI!\u0001G\r\n\u0007\u0005Mq#A\u0004qC\u000e\\\u0017mZ3\n\t\u0005]\u0011\u0011\u0004\u0002\t-\u0016\u0014H/\u001a=JI*\u0019\u00111C\f\u0002\u001dI,hnV5uQ>\u0003H/[8ogV1\u0011qDA\u0016\u0003k!\"\"!\t\u00028\u0005m\u0012QHA )\u00151\u00141EA\u0017\u0011%\t)CBA\u0001\u0002\b\t9#\u0001\u0006fm&$WM\\2fIM\u0002B\u0001Q\"\u0002*A\u0019a)a\u000b\u0005\u000b!3!\u0019A%\t\u0013\u0005=b!!AA\u0004\u0005E\u0012AC3wS\u0012,gnY3%iA!\u0001iQA\u001a!\r1\u0015Q\u0007\u0003\u0006+\u001a\u0011\r!\u0013\u0005\u0007/\u001a\u0001\r!!\u000f\u0011\r]B\u0014\u0011FA\u001a\u0011\u0015Qf\u00011\u0001\\\u0011\u001dyf\u0001%AA\u0002iB\u0011\"!\u0011\u0007!\u0003\u0005\r!a\u0011\u0002\u000bM\u00148-\u00133\u0011\t\u0015\n)e_\u0005\u0004\u0003\u000f2#AB(qi&|g.\u0001\rsk:<\u0016\u000e\u001e5PaRLwN\\:%I\u00164\u0017-\u001e7uIM*RAYA'\u0003\u001f\"Q\u0001S\u0004C\u0002%#Q!V\u0004C\u0002%\u000b\u0001D];o/&$\bn\u00149uS>t7\u000f\n3fM\u0006,H\u000e\u001e\u00135+\u0019\t)&!\u0017\u0002\\U\u0011\u0011q\u000b\u0016\u0004\u0003\u0007\"G!\u0002%\t\u0005\u0004IE!B+\t\u0005\u0004IUCBA0\u0003W\n)\b\u0006\u0007\u0002b\u0005]\u00141PA?\u0003\u007f\n\t\tF\u00037\u0003G\ni\u0007C\u0005\u0002f%\t\t\u0011q\u0001\u0002h\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\t\u0001\u001b\u0015\u0011\u000e\t\u0004\r\u0006-D!\u0002%\n\u0005\u0004I\u0005\"CA8\u0013\u0005\u0005\t9AA9\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0005\u0001\u000e\u000b\u0019\bE\u0002G\u0003k\"Q!V\u0005C\u0002%CaaV\u0005A\u0002\u0005e\u0004CB\u001c9\u0003S\n\u0019\bC\u0003[\u0013\u0001\u00071\fC\u0003`\u0013\u0001\u0007!\bC\u0004\u0002B%\u0001\r!a\u0011\t\r\u0005\r\u0015\u00021\u0001v\u0003)qwN]7bY&TX\rZ\u0001#eVtw+\u001b;i\u001fB$\u0018n\u001c8t/&$\b\u000e\u0015:fm&|Wo\u001d)bO\u0016\u0014\u0016M\\6\u0016\r\u0005%\u0015QSAP)1\tY)!)\u0002&\u0006\u001d\u0016\u0011VAV)\u00151\u0014QRAL\u0011%\tyICA\u0001\u0002\b\t\t*\u0001\u0006fm&$WM\\2fI]\u0002B\u0001Q\"\u0002\u0014B\u0019a)!&\u0005\u000b!S!\u0019A%\t\u0013\u0005e%\"!AA\u0004\u0005m\u0015AC3wS\u0012,gnY3%qA!\u0001iQAO!\r1\u0015q\u0014\u0003\u0006+*\u0011\r!\u0013\u0005\u0007/*\u0001\r!a)\u0011\r]B\u00141SAO\u0011\u0015Q&\u00021\u0001\\\u0011\u0015y&\u00021\u0001;\u0011\u001d\t\tE\u0003a\u0001\u0003\u0007Ba!!,\u000b\u0001\u00041\u0014\u0001\u00049sKJ\u000bgn[$sCBDWCBAY\u0003{\u000b9\r\u0006\b\u00024\u0006%\u0017QZAh\u0003#\f\u0019.!6\u0015\u000bY\n),a0\t\u0013\u0005]6\"!AA\u0004\u0005e\u0016AC3wS\u0012,gnY3%sA!\u0001iQA^!\r1\u0015Q\u0018\u0003\u0006\u0011.\u0011\r!\u0013\u0005\n\u0003\u0003\\\u0011\u0011!a\u0002\u0003\u0007\f1\"\u001a<jI\u0016t7-\u001a\u00132aA!\u0001iQAc!\r1\u0015q\u0019\u0003\u0006+.\u0011\r!\u0013\u0005\u0007/.\u0001\r!a3\u0011\r]B\u00141XAc\u0011\u0015Q6\u00021\u0001\\\u0011\u0015y6\u00021\u0001;\u0011\u001d\t\te\u0003a\u0001\u0003\u0007Ba!a!\f\u0001\u0004)\bBBAW\u0017\u0001\u0007a'A\u0010sk:\u0004\u0016M]1mY\u0016d\u0007+\u001a:t_:\fG.\u001b>fIB\u000bw-\u001a*b].,b!a7\u0002z\n\rACCAo\u0005\u000b\u0011IAa\u0003\u0003\u000eQ1\u0011q\\Ay\u0003w\u0004Ra\u000e\u001d\u0002bj\u0002B!a9\u0002n6\u0011\u0011Q\u001d\u0006\u0005\u0003O\fI/\u0001\u0004mS:\fGn\u001a\u0006\u0004\u0003WL\u0012AA7m\u0013\u0011\ty/!:\u0003\rY+7\r^8s\u0011%\t\u0019\u0010DA\u0001\u0002\b\t)0A\u0006fm&$WM\\2fIE\n\u0004\u0003\u0002!D\u0003o\u00042ARA}\t\u0015AEB1\u0001J\u0011%\ti\u0010DA\u0001\u0002\b\ty0A\u0006fm&$WM\\2fIE\u0012\u0004\u0003\u0002!D\u0005\u0003\u00012A\u0012B\u0002\t\u0015)FB1\u0001J\u0011\u00199F\u00021\u0001\u0003\bA1q\u0007OA|\u0005\u0003AQA\u0017\u0007A\u0002mCqa\u0018\u0007\u0011\u0002\u0003\u0007!\bC\u0004\u0003\u00101\u0001\rA!\u0005\u0002\u000fM|WO]2fgB!QEa\u0005|\u0013\r\u0011)B\n\u0002\u0006\u0003J\u0014\u0018-_\u0001*eVt\u0007+\u0019:bY2,G\u000eU3sg>t\u0017\r\\5{K\u0012\u0004\u0016mZ3SC:\\G\u0005Z3gCVdG\u000fJ\u001a\u0016\u000b\t\u0014YB!\b\u0005\u000b!k!\u0019A%\u0005\u000bUk!\u0019A%\u0002'I,h.\u00168uS2\u001cuN\u001c<fe\u001e,gnY3\u0016\r\t\r\"q\u0006B\u001d)!\u0011)Ca\u000f\u0003@\t\rC#\u0002\u001c\u0003(\tE\u0002\"\u0003B\u0015\u001d\u0005\u0005\t9\u0001B\u0016\u0003-)g/\u001b3f]\u000e,G%M\u001a\u0011\t\u0001\u001b%Q\u0006\t\u0004\r\n=B!\u0002%\u000f\u0005\u0004I\u0005\"\u0003B\u001a\u001d\u0005\u0005\t9\u0001B\u001b\u0003-)g/\u001b3f]\u000e,G%\r\u001b\u0011\t\u0001\u001b%q\u0007\t\u0004\r\neB!B+\u000f\u0005\u0004I\u0005BB,\u000f\u0001\u0004\u0011i\u0004\u0005\u00048q\t5\"q\u0007\u0005\u0007\u0005\u0003r\u0001\u0019\u0001\u001e\u0002\u0007Q|G\u000eC\u0004`\u001dA\u0005\t\u0019\u0001\u001e\u0002;I,h.\u00168uS2\u001cuN\u001c<fe\u001e,gnY3%I\u00164\u0017-\u001e7uIM*RA\u0019B%\u0005\u0017\"Q\u0001S\bC\u0002%#Q!V\bC\u0002%\u000baD];o+:$\u0018\u000e\\\"p]Z,'oZ3oG\u0016<\u0016\u000e\u001e5PaRLwN\\:\u0016\r\tE#Q\fB4))\u0011\u0019F!\u001b\u0003n\t=$\u0011\u000f\u000b\u0006m\tU#q\f\u0005\n\u0005/\u0002\u0012\u0011!a\u0002\u00053\n1\"\u001a<jI\u0016t7-\u001a\u00132kA!\u0001i\u0011B.!\r1%Q\f\u0003\u0006\u0011B\u0011\r!\u0013\u0005\n\u0005C\u0002\u0012\u0011!a\u0002\u0005G\n1\"\u001a<jI\u0016t7-\u001a\u00132mA!\u0001i\u0011B3!\r1%q\r\u0003\u0006+B\u0011\r!\u0013\u0005\u0007/B\u0001\rAa\u001b\u0011\r]B$1\fB3\u0011\u0019\u0011\t\u0005\u0005a\u0001u!9q\f\u0005I\u0001\u0002\u0004Q\u0004\"CA!!A\u0005\t\u0019AA\"\u0003!\u0012XO\\+oi&d7i\u001c8wKJ<WM\\2f/&$\bn\u00149uS>t7\u000f\n3fM\u0006,H\u000e\u001e\u00134+\u0015\u0011'q\u000fB=\t\u0015A\u0015C1\u0001J\t\u0015)\u0016C1\u0001J\u0003!\u0012XO\\+oi&d7i\u001c8wKJ<WM\\2f/&$\bn\u00149uS>t7\u000f\n3fM\u0006,H\u000e\u001e\u00135+\u0019\t)Fa \u0003\u0002\u0012)\u0001J\u0005b\u0001\u0013\u0012)QK\u0005b\u0001\u0013\u0006\u0001bn\u001c:nC2L'0\u001a*b].\u001cV/\u001c\u000b\u0006m\t\u001d%\u0011\u0012\u0005\u0006eN\u0001\rA\u000e\u0005\u0006iN\u0001\r!\u001e"
)
public final class PageRank {
   public static Option runUntilConvergenceWithOptions$default$4() {
      return PageRank$.MODULE$.runUntilConvergenceWithOptions$default$4();
   }

   public static double runUntilConvergenceWithOptions$default$3() {
      return PageRank$.MODULE$.runUntilConvergenceWithOptions$default$3();
   }

   public static Graph runUntilConvergenceWithOptions(final Graph graph, final double tol, final double resetProb, final Option srcId, final ClassTag evidence$15, final ClassTag evidence$16) {
      return PageRank$.MODULE$.runUntilConvergenceWithOptions(graph, tol, resetProb, srcId, evidence$15, evidence$16);
   }

   public static double runUntilConvergence$default$3() {
      return PageRank$.MODULE$.runUntilConvergence$default$3();
   }

   public static Graph runUntilConvergence(final Graph graph, final double tol, final double resetProb, final ClassTag evidence$13, final ClassTag evidence$14) {
      return PageRank$.MODULE$.runUntilConvergence(graph, tol, resetProb, evidence$13, evidence$14);
   }

   public static double runParallelPersonalizedPageRank$default$3() {
      return PageRank$.MODULE$.runParallelPersonalizedPageRank$default$3();
   }

   public static Graph runParallelPersonalizedPageRank(final Graph graph, final int numIter, final double resetProb, final long[] sources, final ClassTag evidence$11, final ClassTag evidence$12) {
      return PageRank$.MODULE$.runParallelPersonalizedPageRank(graph, numIter, resetProb, sources, evidence$11, evidence$12);
   }

   public static Graph runWithOptionsWithPreviousPageRank(final Graph graph, final int numIter, final double resetProb, final Option srcId, final boolean normalized, final Graph preRankGraph, final ClassTag evidence$9, final ClassTag evidence$10) {
      return PageRank$.MODULE$.runWithOptionsWithPreviousPageRank(graph, numIter, resetProb, srcId, normalized, preRankGraph, evidence$9, evidence$10);
   }

   public static Graph runWithOptionsWithPreviousPageRank(final Graph graph, final int numIter, final double resetProb, final Option srcId, final Graph preRankGraph, final ClassTag evidence$7, final ClassTag evidence$8) {
      return PageRank$.MODULE$.runWithOptionsWithPreviousPageRank(graph, numIter, resetProb, srcId, preRankGraph, evidence$7, evidence$8);
   }

   public static Graph runWithOptions(final Graph graph, final int numIter, final double resetProb, final Option srcId, final boolean normalized, final ClassTag evidence$5, final ClassTag evidence$6) {
      return PageRank$.MODULE$.runWithOptions(graph, numIter, resetProb, srcId, normalized, evidence$5, evidence$6);
   }

   public static Option runWithOptions$default$4() {
      return PageRank$.MODULE$.runWithOptions$default$4();
   }

   public static double runWithOptions$default$3() {
      return PageRank$.MODULE$.runWithOptions$default$3();
   }

   public static Graph runWithOptions(final Graph graph, final int numIter, final double resetProb, final Option srcId, final ClassTag evidence$3, final ClassTag evidence$4) {
      return PageRank$.MODULE$.runWithOptions(graph, numIter, resetProb, srcId, evidence$3, evidence$4);
   }

   public static double run$default$3() {
      return PageRank$.MODULE$.run$default$3();
   }

   public static Graph run(final Graph graph, final int numIter, final double resetProb, final ClassTag evidence$1, final ClassTag evidence$2) {
      return PageRank$.MODULE$.run(graph, numIter, resetProb, evidence$1, evidence$2);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return PageRank$.MODULE$.LogStringContext(sc);
   }
}
