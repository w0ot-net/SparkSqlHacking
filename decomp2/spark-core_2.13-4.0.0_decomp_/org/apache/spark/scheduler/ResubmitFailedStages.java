package org.apache.spark.scheduler;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001<aa\u0003\u0007\t\u00022!bA\u0002\f\r\u0011\u0003cq\u0003C\u00032\u0003\u0011\u0005!\u0007C\u00044\u0003\u0005\u0005I\u0011\t\u001b\t\u000fu\n\u0011\u0011!C\u0001}!9!)AA\u0001\n\u0003\u0019\u0005bB%\u0002\u0003\u0003%\tE\u0013\u0005\b#\u0006\t\t\u0011\"\u0001S\u0011\u001d9\u0016!!A\u0005BaCq!W\u0001\u0002\u0002\u0013\u0005#\fC\u0004\\\u0003\u0005\u0005I\u0011\u0002/\u0002)I+7/\u001e2nSR4\u0015-\u001b7fIN#\u0018mZ3t\u0015\tia\"A\u0005tG\",G-\u001e7fe*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014x\r\u0005\u0002\u0016\u00035\tAB\u0001\u000bSKN,(-\\5u\r\u0006LG.\u001a3Ti\u0006<Wm]\n\u0006\u0003aq\u0012\u0005\n\t\u00033qi\u0011A\u0007\u0006\u00027\u0005)1oY1mC&\u0011QD\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Uy\u0012B\u0001\u0011\r\u0005E!\u0015iR*dQ\u0016$W\u000f\\3s\u000bZ,g\u000e\u001e\t\u00033\tJ!a\t\u000e\u0003\u000fA\u0013x\u000eZ;diB\u0011QE\f\b\u0003M1r!aJ\u0016\u000e\u0003!R!!\u000b\u0016\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aG\u0005\u0003[i\tq\u0001]1dW\u0006<W-\u0003\u00020a\ta1+\u001a:jC2L'0\u00192mK*\u0011QFG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A\u001b\u0011\u0005YZT\"A\u001c\u000b\u0005aJ\u0014\u0001\u00027b]\u001eT\u0011AO\u0001\u0005U\u00064\u0018-\u0003\u0002=o\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a\u0010\t\u00033\u0001K!!\u0011\u000e\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u0011;\u0005CA\rF\u0013\t1%DA\u0002B]fDq\u0001S\u0003\u0002\u0002\u0003\u0007q(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002\u0017B\u0019Aj\u0014#\u000e\u00035S!A\u0014\u000e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002Q\u001b\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t\u0019f\u000b\u0005\u0002\u001a)&\u0011QK\u0007\u0002\b\u0005>|G.Z1o\u0011\u001dAu!!AA\u0002\u0011\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u007f\u0005AAo\\*ue&tw\rF\u00016\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005i\u0006C\u0001\u001c_\u0013\tyvG\u0001\u0004PE*,7\r\u001e"
)
public final class ResubmitFailedStages {
   public static String toString() {
      return ResubmitFailedStages$.MODULE$.toString();
   }

   public static int hashCode() {
      return ResubmitFailedStages$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return ResubmitFailedStages$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return ResubmitFailedStages$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return ResubmitFailedStages$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return ResubmitFailedStages$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return ResubmitFailedStages$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return ResubmitFailedStages$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return ResubmitFailedStages$.MODULE$.productElementName(n);
   }
}
