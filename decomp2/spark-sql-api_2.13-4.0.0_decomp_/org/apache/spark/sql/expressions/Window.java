package org.apache.spark.sql.expressions;

import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.Column;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005Q<Qa\u0004\t\t\u0002m1Q!\b\t\t\u0002yAQ!J\u0001\u0005\u0002\u0019BQaJ\u0001\u0005\u0002!BQaJ\u0001\u0005\u0002\u0015CQAT\u0001\u0005\u0002=CQAT\u0001\u0005\u0002MCQAV\u0001\u0005\u0002]CQaW\u0001\u0005\u0002]CQ\u0001X\u0001\u0005\u0002]CQ!X\u0001\u0005\u0002yCQaY\u0001\u0005\u0002\u0011DaaZ\u0001\u0005\u0002IAg\u0001B\u000f\u0011\u0001ADQ!J\u0007\u0005\nE\faaV5oI><(BA\t\u0013\u0003-)\u0007\u0010\u001d:fgNLwN\\:\u000b\u0005M!\u0012aA:rY*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005q\tQ\"\u0001\t\u0003\r]Kg\u000eZ8x'\t\tq\u0004\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003m\t1\u0002]1si&$\u0018n\u001c8CsR\u0019\u0011\u0006L\u001d\u0011\u0005qQ\u0013BA\u0016\u0011\u0005)9\u0016N\u001c3poN\u0003Xm\u0019\u0005\u0006[\r\u0001\rAL\u0001\bG>dg*Y7f!\tycG\u0004\u00021iA\u0011\u0011'I\u0007\u0002e)\u00111GG\u0001\u0007yI|w\u000e\u001e \n\u0005U\n\u0013A\u0002)sK\u0012,g-\u0003\u00028q\t11\u000b\u001e:j]\u001eT!!N\u0011\t\u000bi\u001a\u0001\u0019A\u001e\u0002\u0011\r|GNT1nKN\u00042\u0001\t\u001f/\u0013\ti\u0014E\u0001\u0006=e\u0016\u0004X-\u0019;fIzB#aA \u0011\u0005\u0001\u001bU\"A!\u000b\u0005\t\u000b\u0013AC1o]>$\u0018\r^5p]&\u0011A)\u0011\u0002\bm\u0006\u0014\u0018M]4t)\tIc\tC\u0003H\t\u0001\u0007\u0001*\u0001\u0003d_2\u001c\bc\u0001\u0011=\u0013B\u0011!jS\u0007\u0002%%\u0011AJ\u0005\u0002\u0007\u0007>dW/\u001c8)\u0005\u0011y\u0014aB8sI\u0016\u0014()\u001f\u000b\u0004SA\u000b\u0006\"B\u0017\u0006\u0001\u0004q\u0003\"\u0002\u001e\u0006\u0001\u0004Y\u0004FA\u0003@)\tIC\u000bC\u0003H\r\u0001\u0007\u0001\n\u000b\u0002\u0007\u007f\u0005\u0011RO\u001c2pk:$W\r\u001a)sK\u000e,G-\u001b8h+\u0005A\u0006C\u0001\u0011Z\u0013\tQ\u0016E\u0001\u0003M_:<\u0017AE;oE>,h\u000eZ3e\r>dGn\\<j]\u001e\f!bY;se\u0016tGOU8x\u0003-\u0011xn^:CKR<X-\u001a8\u0015\u0007%z\u0016\rC\u0003a\u0015\u0001\u0007\u0001,A\u0003ti\u0006\u0014H\u000fC\u0003c\u0015\u0001\u0007\u0001,A\u0002f]\u0012\fAB]1oO\u0016\u0014U\r^<fK:$2!K3g\u0011\u0015\u00017\u00021\u0001Y\u0011\u0015\u00117\u00021\u0001Y\u0003\u0011\u0019\b/Z2\u0016\u0003%B#!\u00016\u0011\u0005-lW\"\u00017\u000b\u0005\t#\u0012B\u00018m\u0005\u0019\u0019F/\u00192mK\"\u0012\u0001A[\n\u0003\u001b}!\u0012A\u001d\t\u000395A#!\u00046"
)
public class Window {
   public static WindowSpec orderBy(final Column... cols) {
      return Window$.MODULE$.orderBy(cols);
   }

   public static WindowSpec orderBy(final String colName, final String... colNames) {
      return Window$.MODULE$.orderBy(colName, colNames);
   }

   public static WindowSpec partitionBy(final Column... cols) {
      return Window$.MODULE$.partitionBy(cols);
   }

   public static WindowSpec partitionBy(final String colName, final String... colNames) {
      return Window$.MODULE$.partitionBy(colName, colNames);
   }

   public static WindowSpec rangeBetween(final long start, final long end) {
      return Window$.MODULE$.rangeBetween(start, end);
   }

   public static WindowSpec rowsBetween(final long start, final long end) {
      return Window$.MODULE$.rowsBetween(start, end);
   }

   public static long currentRow() {
      return Window$.MODULE$.currentRow();
   }

   public static long unboundedFollowing() {
      return Window$.MODULE$.unboundedFollowing();
   }

   public static long unboundedPreceding() {
      return Window$.MODULE$.unboundedPreceding();
   }

   public static WindowSpec orderBy(final Seq cols) {
      return Window$.MODULE$.orderBy(cols);
   }

   public static WindowSpec orderBy(final String colName, final Seq colNames) {
      return Window$.MODULE$.orderBy(colName, colNames);
   }

   public static WindowSpec partitionBy(final Seq cols) {
      return Window$.MODULE$.partitionBy(cols);
   }

   public static WindowSpec partitionBy(final String colName, final Seq colNames) {
      return Window$.MODULE$.partitionBy(colName, colNames);
   }

   private Window() {
   }
}
