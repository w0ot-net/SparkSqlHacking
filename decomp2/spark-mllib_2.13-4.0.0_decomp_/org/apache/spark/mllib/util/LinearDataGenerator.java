package org.apache.spark.mllib.util;

import java.util.List;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%t!\u0002\u0007\u000e\u0011\u0003Ab!\u0002\u000e\u000e\u0011\u0003Y\u0002\"\u0002\u0012\u0002\t\u0003\u0019\u0003\"\u0002\u0013\u0002\t\u0003)\u0003\"B(\u0002\t\u0003\u0001\u0006bB2\u0002#\u0003%\t\u0001\u001a\u0005\u0006\u001f\u0006!\tA\u001c\u0005\u0006\u001f\u0006!\t!\u001f\u0005\b\u0003\u001b\tA\u0011AA\b\u0011%\tY$AI\u0001\n\u0003\ti\u0004\u0003\u0005\u0002B\u0005\t\n\u0011\"\u0001e\u0011\u001d\t\u0019%\u0001C\u0001\u0003\u000b\n1\u0003T5oK\u0006\u0014H)\u0019;b\u000f\u0016tWM]1u_JT!AD\b\u0002\tU$\u0018\u000e\u001c\u0006\u0003!E\tQ!\u001c7mS\nT!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001!\tI\u0012!D\u0001\u000e\u0005Ma\u0015N\\3be\u0012\u000bG/Y$f]\u0016\u0014\u0018\r^8s'\t\tA\u0004\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\t\u0011dZ3oKJ\fG/\u001a'j]\u0016\f'/\u00138qkR\f5\u000fT5tiR1ae\r\u001d>\u0005\u0012\u00032aJ\u0016.\u001b\u0005A#B\u0001\b*\u0015\u0005Q\u0013\u0001\u00026bm\u0006L!\u0001\f\u0015\u0003\t1K7\u000f\u001e\t\u0003]Ej\u0011a\f\u0006\u0003a=\t!B]3he\u0016\u001c8/[8o\u0013\t\u0011tF\u0001\u0007MC\n,G.\u001a3Q_&tG\u000fC\u00035\u0007\u0001\u0007Q'A\u0005j]R,'oY3qiB\u0011QDN\u0005\u0003oy\u0011a\u0001R8vE2,\u0007\"B\u001d\u0004\u0001\u0004Q\u0014aB<fS\u001eDGo\u001d\t\u0004;m*\u0014B\u0001\u001f\u001f\u0005\u0015\t%O]1z\u0011\u0015q4\u00011\u0001@\u0003\u001dq\u0007k\\5oiN\u0004\"!\b!\n\u0005\u0005s\"aA%oi\")1i\u0001a\u0001\u007f\u0005!1/Z3e\u0011\u0015)5\u00011\u00016\u0003\r)\u0007o\u001d\u0015\u0004\u0007\u001dk\u0005C\u0001%L\u001b\u0005I%B\u0001&\u0012\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0019&\u0013QaU5oG\u0016\f\u0013AT\u0001\u0006a9Bd\u0006M\u0001\u0014O\u0016tWM]1uK2Kg.Z1s\u0013:\u0004X\u000f\u001e\u000b\u0007#vsv\fY1\u0011\u0007ISVF\u0004\u0002T1:\u0011AkV\u0007\u0002+*\u0011akF\u0001\u0007yI|w\u000e\u001e \n\u0003}I!!\u0017\u0010\u0002\u000fA\f7m[1hK&\u00111\f\u0018\u0002\u0004'\u0016\f(BA-\u001f\u0011\u0015!D\u00011\u00016\u0011\u0015ID\u00011\u0001;\u0011\u0015qD\u00011\u0001@\u0011\u0015\u0019E\u00011\u0001@\u0011\u001d)E\u0001%AA\u0002UB3\u0001B$N\u0003u9WM\\3sCR,G*\u001b8fCJLe\u000e];uI\u0011,g-Y;mi\u0012*T#A3+\u0005U27&A4\u0011\u0005!dW\"A5\u000b\u0005)\\\u0017!C;oG\",7m[3e\u0015\tQe$\u0003\u0002nS\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0015\u0011E{\u0007/]:vm^DQ\u0001\u000e\u0004A\u0002UBQ!\u000f\u0004A\u0002iBQA\u001d\u0004A\u0002i\nQ\u0001_'fC:DQ\u0001\u001e\u0004A\u0002i\n\u0011\u0002\u001f,be&\fgnY3\t\u000by2\u0001\u0019A \t\u000b\r3\u0001\u0019A \t\u000b\u00153\u0001\u0019A\u001b)\u0007\u00199U\nF\u0006RundXP`@\u0002\u0002\u0005\r\u0001\"\u0002\u001b\b\u0001\u0004)\u0004\"B\u001d\b\u0001\u0004Q\u0004\"\u0002:\b\u0001\u0004Q\u0004\"\u0002;\b\u0001\u0004Q\u0004\"\u0002 \b\u0001\u0004y\u0004\"B\"\b\u0001\u0004y\u0004\"B#\b\u0001\u0004)\u0004BBA\u0003\u000f\u0001\u0007Q'\u0001\u0005ta\u0006\u00148/\u001b;zQ\u00119q)!\u0003\"\u0005\u0005-\u0011!B\u0019/m9\u0002\u0014!E4f]\u0016\u0014\u0018\r^3MS:,\u0017M\u001d*E\tRq\u0011\u0011CA\u000f\u0003S\ti#!\r\u00024\u0005]\u0002#BA\n\u00033iSBAA\u000b\u0015\r\t9\"E\u0001\u0004e\u0012$\u0017\u0002BA\u000e\u0003+\u00111A\u0015#E\u0011\u001d\ty\u0002\u0003a\u0001\u0003C\t!a]2\u0011\t\u0005\r\u0012QE\u0007\u0002#%\u0019\u0011qE\t\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\r\u0005-\u0002\u00021\u0001@\u0003%qW\r_1na2,7\u000f\u0003\u0004\u00020!\u0001\raP\u0001\n]\u001a,\u0017\r^;sKNDQ!\u0012\u0005A\u0002UB\u0001\"!\u000e\t!\u0003\u0005\raP\u0001\u0007]B\f'\u000f^:\t\u000fQB\u0001\u0013!a\u0001k!\u001a\u0001bR'\u00027\u001d,g.\u001a:bi\u0016d\u0015N\\3beJ#E\t\n3fM\u0006,H\u000e\u001e\u00136+\t\tyD\u000b\u0002@M\u0006Yr-\u001a8fe\u0006$X\rT5oK\u0006\u0014(\u000b\u0012#%I\u00164\u0017-\u001e7uIY\nA!\\1j]R!\u0011qIA'!\ri\u0012\u0011J\u0005\u0004\u0003\u0017r\"\u0001B+oSRDq!a\u0014\f\u0001\u0004\t\t&\u0001\u0003be\u001e\u001c\b\u0003B\u000f<\u0003'\u0002B!!\u0016\u0002^9!\u0011qKA-!\t!f$C\u0002\u0002\\y\ta\u0001\u0015:fI\u00164\u0017\u0002BA0\u0003C\u0012aa\u0015;sS:<'bAA.=!\u001a1bR')\u0007\u00059U\nK\u0002\u0001\u000f6\u0003"
)
public final class LinearDataGenerator {
   public static void main(final String[] args) {
      LinearDataGenerator$.MODULE$.main(args);
   }

   public static double generateLinearRDD$default$6() {
      return LinearDataGenerator$.MODULE$.generateLinearRDD$default$6();
   }

   public static int generateLinearRDD$default$5() {
      return LinearDataGenerator$.MODULE$.generateLinearRDD$default$5();
   }

   public static RDD generateLinearRDD(final SparkContext sc, final int nexamples, final int nfeatures, final double eps, final int nparts, final double intercept) {
      return LinearDataGenerator$.MODULE$.generateLinearRDD(sc, nexamples, nfeatures, eps, nparts, intercept);
   }

   public static Seq generateLinearInput(final double intercept, final double[] weights, final double[] xMean, final double[] xVariance, final int nPoints, final int seed, final double eps, final double sparsity) {
      return LinearDataGenerator$.MODULE$.generateLinearInput(intercept, weights, xMean, xVariance, nPoints, seed, eps, sparsity);
   }

   public static Seq generateLinearInput(final double intercept, final double[] weights, final double[] xMean, final double[] xVariance, final int nPoints, final int seed, final double eps) {
      return LinearDataGenerator$.MODULE$.generateLinearInput(intercept, weights, xMean, xVariance, nPoints, seed, eps);
   }

   public static double generateLinearInput$default$5() {
      return LinearDataGenerator$.MODULE$.generateLinearInput$default$5();
   }

   public static Seq generateLinearInput(final double intercept, final double[] weights, final int nPoints, final int seed, final double eps) {
      return LinearDataGenerator$.MODULE$.generateLinearInput(intercept, weights, nPoints, seed, eps);
   }

   public static List generateLinearInputAsList(final double intercept, final double[] weights, final int nPoints, final int seed, final double eps) {
      return LinearDataGenerator$.MODULE$.generateLinearInputAsList(intercept, weights, nPoints, seed, eps);
   }
}
