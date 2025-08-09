package org.apache.spark.ml.linalg;

import java.util.Random;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mu!\u0002\f\u0018\u0011\u0003\u0011c!\u0002\u0013\u0018\u0011\u0003)\u0003\"\u0002\u0017\u0002\t\u0003i\u0003\u0002\u0003\u0018\u0002\u0005\u0004%\t!G\u0018\t\rM\n\u0001\u0015!\u00031\u0011\u0019!\u0014\u0001\"\u0001\u001ak!)!*\u0001C\u0001\u0017\")A-\u0001C\u0001K\"1q.\u0001C\u00013ADQ\u0001_\u0001\u0005\u0002eDQ!`\u0001\u0005\u0002yDq!!\u0002\u0002\t\u0003\t9\u0001C\u0004\u0002\u0010\u0005!\t!!\u0005\t\u000f\u0005]\u0011\u0001\"\u0001\u0002\u001a!9\u0011QG\u0001\u0005\u0002\u0005]\u0002bBA#\u0003\u0011\u0005\u0011q\t\u0005\b\u0003#\nA\u0011AA*\u0011\u001d\ty&\u0001C\u0001\u0003CBq!!\u001b\u0002\t\u0003\tY\u0007C\u0004\u0002v\u0005!\t!a\u001e\t\u0011\u0005u\u0014\u0001\"\u0001\u001a\u0003\u007fB\u0001\"a$\u0002\t\u0003I\u0012\u0011S\u0001\t\u001b\u0006$(/[2fg*\u0011\u0001$G\u0001\u0007Y&t\u0017\r\\4\u000b\u0005iY\u0012AA7m\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7\u0001\u0001\t\u0003G\u0005i\u0011a\u0006\u0002\t\u001b\u0006$(/[2fgN\u0011\u0011A\n\t\u0003O)j\u0011\u0001\u000b\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\u000b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0011\u0013!B3naRLX#\u0001\u0019\u0011\u0005\r\n\u0014B\u0001\u001a\u0018\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0002\r\u0015l\u0007\u000f^=!\u0003-1'o\\7WK\u000e$xN]:\u0015\u0005YJ\u0004CA\u00128\u0013\tAtC\u0001\u0004NCR\u0014\u0018\u000e\u001f\u0005\u0006u\u0015\u0001\raO\u0001\bm\u0016\u001cGo\u001c:t!\raDi\u0012\b\u0003{\ts!AP!\u000e\u0003}R!\u0001Q\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013BA\")\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0012$\u0003\u0007M+\u0017O\u0003\u0002DQA\u00111\u0005S\u0005\u0003\u0013^\u0011aAV3di>\u0014\u0018!\u00023f]N,G\u0003\u0002\u001cM#NCQ!\u0014\u0004A\u00029\u000bqA\\;n%><8\u000f\u0005\u0002(\u001f&\u0011\u0001\u000b\u000b\u0002\u0004\u0013:$\b\"\u0002*\u0007\u0001\u0004q\u0015a\u00028v[\u000e{Gn\u001d\u0005\u0006)\u001a\u0001\r!V\u0001\u0007m\u0006dW/Z:\u0011\u0007\u001d2\u0006,\u0003\u0002XQ\t)\u0011I\u001d:bsB\u0011q%W\u0005\u00035\"\u0012a\u0001R8vE2,\u0007f\u0001\u0004]EB\u0011Q\fY\u0007\u0002=*\u0011qlG\u0001\u000bC:tw\u000e^1uS>t\u0017BA1_\u0005\u0015\u0019\u0016N\\2fC\u0005\u0019\u0017!\u0002\u001a/a9\u0002\u0014AB:qCJ\u001cX\r\u0006\u00047M\u001eD7.\u001c\u0005\u0006\u001b\u001e\u0001\rA\u0014\u0005\u0006%\u001e\u0001\rA\u0014\u0005\u0006S\u001e\u0001\rA[\u0001\bG>d\u0007\u000b\u001e:t!\r9cK\u0014\u0005\u0006Y\u001e\u0001\rA[\u0001\u000be><\u0018J\u001c3jG\u0016\u001c\b\"\u0002+\b\u0001\u0004)\u0006fA\u0004]E\u0006QaM]8n\u0005J,WM_3\u0015\u0005Y\n\b\"\u0002:\t\u0001\u0004\u0019\u0018A\u00022sK\u0016TX\rE\u0002uobk\u0011!\u001e\u0006\u00031YT\u0011A]\u0005\u0003qU\fQA_3s_N$2A\u000e>|\u0011\u0015i\u0015\u00021\u0001O\u0011\u0015\u0011\u0016\u00021\u0001OQ\rIALY\u0001\u0005_:,7\u000f\u0006\u00037\u007f\u0006\u0005\u0001\"B'\u000b\u0001\u0004q\u0005\"\u0002*\u000b\u0001\u0004q\u0005f\u0001\u0006]E\u0006\u0019Q-_3\u0015\u0007Y\nI\u0001\u0003\u0004\u0002\f-\u0001\rAT\u0001\u0002]\"\u001a1\u0002\u00182\u0002\u000bM\u0004X-_3\u0015\u0007Y\n\u0019\u0002\u0003\u0004\u0002\f1\u0001\rA\u0014\u0015\u0004\u0019q\u0013\u0017\u0001\u0002:b]\u0012$rANA\u000e\u0003;\ty\u0002C\u0003N\u001b\u0001\u0007a\nC\u0003S\u001b\u0001\u0007a\nC\u0004\u0002\"5\u0001\r!a\t\u0002\u0007Itw\r\u0005\u0003\u0002&\u0005=RBAA\u0014\u0015\u0011\tI#a\u000b\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0003[\tAA[1wC&!\u0011\u0011GA\u0014\u0005\u0019\u0011\u0016M\u001c3p[\"\u001aQ\u0002\u00182\u0002\rM\u0004(/\u00198e)%1\u0014\u0011HA\u001e\u0003{\t\t\u0005C\u0003N\u001d\u0001\u0007a\nC\u0003S\u001d\u0001\u0007a\n\u0003\u0004\u0002@9\u0001\r\u0001W\u0001\bI\u0016t7/\u001b;z\u0011\u001d\t\tC\u0004a\u0001\u0003GA3A\u0004/c\u0003\u0015\u0011\u0018M\u001c3o)\u001d1\u0014\u0011JA&\u0003\u001bBQ!T\bA\u00029CQAU\bA\u00029Cq!!\t\u0010\u0001\u0004\t\u0019\u0003K\u0002\u00109\n\fqa\u001d9sC:$g\u000eF\u00057\u0003+\n9&!\u0017\u0002\\!)Q\n\u0005a\u0001\u001d\")!\u000b\u0005a\u0001\u001d\"1\u0011q\b\tA\u0002aCq!!\t\u0011\u0001\u0004\t\u0019\u0003K\u0002\u00119\n\fA\u0001Z5bOR\u0019a'a\u0019\t\r\u0005\u0015\u0014\u00031\u0001H\u0003\u00191Xm\u0019;pe\"\u001a\u0011\u0003\u00182\u0002\u000f!|'O_2biR\u0019a'!\u001c\t\u000f\u0005=$\u00031\u0001\u0002r\u0005AQ.\u0019;sS\u000e,7\u000fE\u0002(-ZB3A\u0005/c\u0003\u001d1XM\u001d;dCR$2ANA=\u0011\u001d\tyg\u0005a\u0001\u0003cB3a\u0005/c\u000359W\r^*qCJ\u001cXmU5{KR1\u0011\u0011QAD\u0003\u0017\u00032aJAB\u0013\r\t)\t\u000b\u0002\u0005\u0019>tw\rC\u0004\u0002\nR\u0001\r!!!\u0002\u00159,X.Q2uSZ,7\u000fC\u0004\u0002\u000eR\u0001\r!!!\u0002\u000f9,X\u000e\u0015;sg\u0006aq-\u001a;EK:\u001cXmU5{KR1\u0011\u0011QAJ\u0003+CaAU\u000bA\u0002\u0005\u0005\u0005BB'\u0016\u0001\u0004\t\t\tK\u0002\u00029\nD3\u0001\u0001/c\u0001"
)
public final class Matrices {
   public static Matrix vertcat(final Matrix[] matrices) {
      return Matrices$.MODULE$.vertcat(matrices);
   }

   public static Matrix horzcat(final Matrix[] matrices) {
      return Matrices$.MODULE$.horzcat(matrices);
   }

   public static Matrix diag(final Vector vector) {
      return Matrices$.MODULE$.diag(vector);
   }

   public static Matrix sprandn(final int numRows, final int numCols, final double density, final Random rng) {
      return Matrices$.MODULE$.sprandn(numRows, numCols, density, rng);
   }

   public static Matrix randn(final int numRows, final int numCols, final Random rng) {
      return Matrices$.MODULE$.randn(numRows, numCols, rng);
   }

   public static Matrix sprand(final int numRows, final int numCols, final double density, final Random rng) {
      return Matrices$.MODULE$.sprand(numRows, numCols, density, rng);
   }

   public static Matrix rand(final int numRows, final int numCols, final Random rng) {
      return Matrices$.MODULE$.rand(numRows, numCols, rng);
   }

   public static Matrix speye(final int n) {
      return Matrices$.MODULE$.speye(n);
   }

   public static Matrix eye(final int n) {
      return Matrices$.MODULE$.eye(n);
   }

   public static Matrix ones(final int numRows, final int numCols) {
      return Matrices$.MODULE$.ones(numRows, numCols);
   }

   public static Matrix zeros(final int numRows, final int numCols) {
      return Matrices$.MODULE$.zeros(numRows, numCols);
   }

   public static Matrix sparse(final int numRows, final int numCols, final int[] colPtrs, final int[] rowIndices, final double[] values) {
      return Matrices$.MODULE$.sparse(numRows, numCols, colPtrs, rowIndices, values);
   }

   public static Matrix dense(final int numRows, final int numCols, final double[] values) {
      return Matrices$.MODULE$.dense(numRows, numCols, values);
   }
}
