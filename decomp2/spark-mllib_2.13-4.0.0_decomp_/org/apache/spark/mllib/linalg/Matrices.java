package org.apache.spark.mllib.linalg;

import java.util.Random;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=t!\u0002\n\u0014\u0011\u0003qb!\u0002\u0011\u0014\u0011\u0003\t\u0003\"\u0002\u0015\u0002\t\u0003I\u0003\"\u0002\u0016\u0002\t\u0003Y\u0003\"B$\u0002\t\u0003A\u0005B\u0002+\u0002\t\u0003)R\u000bC\u0003^\u0003\u0011\u0005a\fC\u0003c\u0003\u0011\u00051\rC\u0003h\u0003\u0011\u0005\u0001\u000eC\u0003m\u0003\u0011\u0005Q\u000eC\u0003s\u0003\u0011\u00051\u000fC\u0004\u0002\u0004\u0005!\t!!\u0002\t\u000f\u0005M\u0011\u0001\"\u0001\u0002\u0016!9\u0011qD\u0001\u0005\u0002\u0005\u0005\u0002bBA\u0017\u0003\u0011\u0005\u0011q\u0006\u0005\b\u0003{\tA\u0011AA \u0011\u001d\tI%\u0001C\u0001\u0003\u0017Bq!!\u0015\u0002\t\u0003\t\u0019&\u0001\u0005NCR\u0014\u0018nY3t\u0015\t!R#\u0001\u0004mS:\fGn\u001a\u0006\u0003-]\tQ!\u001c7mS\nT!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u0002\u0001!\ty\u0012!D\u0001\u0014\u0005!i\u0015\r\u001e:jG\u0016\u001c8CA\u0001#!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012AH\u0001\u0006I\u0016t7/\u001a\u000b\u0005Y=\"d\u0007\u0005\u0002 [%\u0011af\u0005\u0002\u0007\u001b\u0006$(/\u001b=\t\u000bA\u001a\u0001\u0019A\u0019\u0002\u000f9,XNU8xgB\u00111EM\u0005\u0003g\u0011\u00121!\u00138u\u0011\u0015)4\u00011\u00012\u0003\u001dqW/\\\"pYNDQaN\u0002A\u0002a\naA^1mk\u0016\u001c\bcA\u0012:w%\u0011!\b\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003GqJ!!\u0010\u0013\u0003\r\u0011{WO\u00197fQ\r\u0019q(\u0012\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005^\t!\"\u00198o_R\fG/[8o\u0013\t!\u0015IA\u0003TS:\u001cW-I\u0001G\u0003\u0015\td\u0006\r\u00181\u0003\u0019\u0019\b/\u0019:tKR1A&\u0013&L\u001dBCQ\u0001\r\u0003A\u0002EBQ!\u000e\u0003A\u0002EBQ\u0001\u0014\u0003A\u00025\u000bqaY8m!R\u00148\u000fE\u0002$sEBQa\u0014\u0003A\u00025\u000b!B]8x\u0013:$\u0017nY3t\u0011\u00159D\u00011\u00019Q\r!qHU\u0011\u0002'\u0006)\u0011G\f\u001a/a\u0005QaM]8n\u0005J,WM_3\u0015\u000512\u0006\"B,\u0006\u0001\u0004A\u0016A\u00022sK\u0016TX\rE\u0002Z9nj\u0011A\u0017\u0006\u0003)mS\u0011aV\u0005\u0003]i\u000bQA_3s_N$2\u0001L0a\u0011\u0015\u0001d\u00011\u00012\u0011\u0015)d\u00011\u00012Q\r1qHU\u0001\u0005_:,7\u000fF\u0002-I\u0016DQ\u0001M\u0004A\u0002EBQ!N\u0004A\u0002EB3aB S\u0003\r)\u00170\u001a\u000b\u0003Y%DQA\u001b\u0005A\u0002E\n\u0011A\u001c\u0015\u0004\u0011}\u0012\u0016!B:qKf,GC\u0001\u0017o\u0011\u0015Q\u0017\u00021\u00012Q\rIq\b]\u0011\u0002c\u0006)\u0011GL\u001a/a\u0005!!/\u00198e)\u0011aC/\u001e<\t\u000bAR\u0001\u0019A\u0019\t\u000bUR\u0001\u0019A\u0019\t\u000b]T\u0001\u0019\u0001=\u0002\u0007Itw\r\u0005\u0002z}6\t!P\u0003\u0002|y\u0006!Q\u000f^5m\u0015\u0005i\u0018\u0001\u00026bm\u0006L!a >\u0003\rI\u000bg\u000eZ8nQ\rQqHU\u0001\u0007gB\u0014\u0018M\u001c3\u0015\u00131\n9!!\u0003\u0002\f\u0005=\u0001\"\u0002\u0019\f\u0001\u0004\t\u0004\"B\u001b\f\u0001\u0004\t\u0004BBA\u0007\u0017\u0001\u00071(A\u0004eK:\u001c\u0018\u000e^=\t\u000b]\\\u0001\u0019\u0001=)\u0007-y\u0004/A\u0003sC:$g\u000eF\u0004-\u0003/\tI\"a\u0007\t\u000bAb\u0001\u0019A\u0019\t\u000bUb\u0001\u0019A\u0019\t\u000b]d\u0001\u0019\u0001=)\u00071y$+A\u0004taJ\fg\u000e\u001a8\u0015\u00131\n\u0019#!\n\u0002(\u0005%\u0002\"\u0002\u0019\u000e\u0001\u0004\t\u0004\"B\u001b\u000e\u0001\u0004\t\u0004BBA\u0007\u001b\u0001\u00071\bC\u0003x\u001b\u0001\u0007\u0001\u0010K\u0002\u000e\u007fA\fA\u0001Z5bOR\u0019A&!\r\t\u000f\u0005Mb\u00021\u0001\u00026\u00051a/Z2u_J\u00042aHA\u001c\u0013\r\tId\u0005\u0002\u0007-\u0016\u001cGo\u001c:)\u00079y$+A\u0004i_JT8-\u0019;\u0015\u00071\n\t\u0005C\u0004\u0002D=\u0001\r!!\u0012\u0002\u00115\fGO]5dKN\u00042aI\u001d-Q\ryq\b]\u0001\bm\u0016\u0014HoY1u)\ra\u0013Q\n\u0005\b\u0003\u0007\u0002\u0002\u0019AA#Q\r\u0001r\b]\u0001\u0007MJ|W.\u0014'\u0015\u00071\n)\u0006C\u0004\u0002XE\u0001\r!!\u0017\u0002\u00035\u0004B!a\u0017\u0002d5\u0011\u0011Q\f\u0006\u0004)\u0005}#bAA1/\u0005\u0011Q\u000e\\\u0005\u0004]\u0005u\u0003\u0006B\t@\u0003O\n#!!\u001b\u0002\u000bIr\u0003G\f\u0019)\u0007\u0005yT\tK\u0002\u0001\u007f\u0015\u0003"
)
public final class Matrices {
   public static Matrix fromML(final org.apache.spark.ml.linalg.Matrix m) {
      return Matrices$.MODULE$.fromML(m);
   }

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
