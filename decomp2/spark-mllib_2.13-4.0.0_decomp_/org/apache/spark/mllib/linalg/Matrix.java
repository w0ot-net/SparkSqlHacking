package org.apache.spark.mllib.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.types.SQLUserDefinedType;
import scala.Function1;
import scala.Function3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@SQLUserDefinedType(
   udt = MatrixUDT.class
)
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005gaB\r\u001b!\u0003\r\t#\n\u0005\u0006q\u0001!\t!\u000f\u0005\u0006{\u00011\tA\u0010\u0005\u0006\u0017\u00021\tA\u0010\u0005\b\u001b\u0002\u0011\r\u0011\"\u0001O\u0011\u0015)\u0006\u0001\"\u0001W\u0011\u0015q\u0006A\"\u0001`\u0011\u0015Q\u0007\u0001\"\u0001`\u0011\u0019a\u0007A\"\u0001\u001d[\")A\u000f\u0001D\u0001k\"11\u0010\u0001D\u00019qDqa \u0001\u0007\u0002q\t\t\u0001C\u0004\u0002\f\u00011\t!!\u0004\t\u000f\u0005]\u0001A\"\u0001\u0002\u000e!9\u00111\u0004\u0001\u0005\u0002\u0005u\u0001bBA\u000e\u0001\u0011\u0005\u00111\u0006\u0005\b\u00037\u0001A\u0011AA\u001c\u0011\u001d\t\t\u0005\u0001C!\u0003\u0007Bq!!\u0011\u0001\t\u0003\t)\u0006\u0003\u0005\u0002b\u00011\tAHA2\u0011\u001dy\bA\"\u0001\u001d\u0003_B\u0001\"a\u001d\u0001\r\u0003q\u0012Q\u000f\u0005\u0007\u0003\u007f\u0002a\u0011\u0001 \t\r\u0005\u001d\u0005A\"\u0001?\u0011\u001d\tY\t\u0001D\u0001\u0003\u001b\u0013a!T1ue&D(BA\u000e\u001d\u0003\u0019a\u0017N\\1mO*\u0011QDH\u0001\u0006[2d\u0017N\u0019\u0006\u0003?\u0001\nQa\u001d9be.T!!\t\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0013aA8sO\u000e\u00011c\u0001\u0001'YA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u0004\"!L\u001b\u000f\u00059\u001adBA\u00183\u001b\u0005\u0001$BA\u0019%\u0003\u0019a$o\\8u}%\t\u0011&\u0003\u00025Q\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001c8\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t!\u0004&\u0001\u0004%S:LG\u000f\n\u000b\u0002uA\u0011qeO\u0005\u0003y!\u0012A!\u00168ji\u00069a.^7S_^\u001cX#A \u0011\u0005\u001d\u0002\u0015BA!)\u0005\rIe\u000e\u001e\u0015\u0004\u0005\rK\u0005C\u0001#H\u001b\u0005)%B\u0001$\u001f\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0011\u0016\u0013QaU5oG\u0016\f\u0013AS\u0001\u0006c9\u0002d\u0006M\u0001\b]Vl7i\u001c7tQ\r\u00191)S\u0001\rSN$&/\u00198ta>\u001cX\rZ\u000b\u0002\u001fB\u0011q\u0005U\u0005\u0003#\"\u0012qAQ8pY\u0016\fg\u000eK\u0002\u0005\u0007N\u000b\u0013\u0001V\u0001\u0006c9\u001ad\u0006M\u0001\bi>\f%O]1z+\u00059\u0006cA\u0014Y5&\u0011\u0011\f\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003OmK!\u0001\u0018\u0015\u0003\r\u0011{WO\u00197fQ\r)1)S\u0001\bG>d\u0017\n^3s+\u0005\u0001\u0007cA\u0017bG&\u0011!m\u000e\u0002\t\u0013R,'/\u0019;peB\u0011A-Z\u0007\u00025%\u0011aM\u0007\u0002\u0007-\u0016\u001cGo\u001c:)\u0007\u0019\u0019\u0005.I\u0001j\u0003\u0015\u0011d\u0006\r\u00181\u0003\u001d\u0011xn^%uKJD3aB\"i\u0003!\t7O\u0011:fKj,W#\u00018\u0011\u0007=\u001c(,D\u0001q\u0015\tY\u0012OC\u0001s\u0003\u0019\u0011'/Z3{K&\u0011\u0011\u0004]\u0001\u0006CB\u0004H.\u001f\u000b\u00045ZD\b\"B<\n\u0001\u0004y\u0014!A5\t\u000beL\u0001\u0019A \u0002\u0003)D3!C\"T\u0003\u0015Ig\u000eZ3y)\ryTP \u0005\u0006o*\u0001\ra\u0010\u0005\u0006s*\u0001\raP\u0001\u0007kB$\u0017\r^3\u0015\u000fi\n\u0019!!\u0002\u0002\b!)qo\u0003a\u0001\u007f!)\u0011p\u0003a\u0001\u007f!1\u0011\u0011B\u0006A\u0002i\u000b\u0011A^\u0001\u0005G>\u0004\u00180\u0006\u0002\u0002\u0010A\u0011A\r\u0001\u0015\u0005\u0019\r\u000b\u0019\"\t\u0002\u0002\u0016\u0005)\u0011G\f\u001a/a\u0005IAO]1ogB|7/\u001a\u0015\u0004\u001b\r\u001b\u0016\u0001C7vYRL\u0007\u000f\\=\u0015\t\u0005}\u0011Q\u0005\t\u0004I\u0006\u0005\u0012bAA\u00125\tYA)\u001a8tK6\u000bGO]5y\u0011\u001d\t9C\u0004a\u0001\u0003?\t\u0011!\u001f\u0015\u0005\u001d\r\u000b\u0019\u0002\u0006\u0003\u0002.\u0005M\u0002c\u00013\u00020%\u0019\u0011\u0011\u0007\u000e\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\u0005\b\u0003Oy\u0001\u0019AA\u0017Q\u0011y1)a\u0005\u0015\t\u00055\u0012\u0011\b\u0005\u0007\u0003O\u0001\u0002\u0019A2)\tA\u0019\u0015QH\u0011\u0003\u0003\u007f\tQ!\r\u00185]A\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u000b\u0002B!a\u0012\u0002P9!\u0011\u0011JA&!\ty\u0003&C\u0002\u0002N!\na\u0001\u0015:fI\u00164\u0017\u0002BA)\u0003'\u0012aa\u0015;sS:<'bAA'QQ1\u0011QIA,\u00037Ba!!\u0017\u0013\u0001\u0004y\u0014\u0001C7bq2Kg.Z:\t\r\u0005u#\u00031\u0001@\u00031i\u0017\r\u001f'j]\u0016<\u0016\u000e\u001a;iQ\u0011\u00112)!\u0010\u0002\u00075\f\u0007\u000f\u0006\u0003\u0002\u0010\u0005\u0015\u0004bBA4'\u0001\u0007\u0011\u0011N\u0001\u0002MB)q%a\u001b[5&\u0019\u0011Q\u000e\u0015\u0003\u0013\u0019+hn\u0019;j_:\fD\u0003BA\b\u0003cBq!a\u001a\u0015\u0001\u0004\tI'A\u0007g_J,\u0017m\u00195BGRLg/\u001a\u000b\u0004u\u0005]\u0004bBA4+\u0001\u0007\u0011\u0011\u0010\t\bO\u0005mth\u0010.;\u0013\r\ti\b\u000b\u0002\n\rVt7\r^5p]N\n1B\\;n\u001d>t'0\u001a:pg\"\"acQABC\t\t))A\u00032]Ur\u0003'\u0001\u0006ok6\f5\r^5wKNDCaF\"\u0002\u0004\u0006!\u0011m]'M+\t\ty\t\u0005\u0003\u0002\u0012\u0006eUBAAJ\u0015\rY\u0012Q\u0013\u0006\u0004\u0003/s\u0012AA7m\u0013\rI\u00121\u0013\u0015\u00041\rC\u0017&\u0002\u0001\u0002\"\u0005}\u0015bAAQ5\ta1\u000b]1sg\u0016l\u0015\r\u001e:jq\":\u0001!!*\u00026\u0006]\u0006\u0003BAT\u0003ck!!!+\u000b\t\u0005-\u0016QV\u0001\u0006if\u0004Xm\u001d\u0006\u0004\u0003_s\u0012aA:rY&!\u00111WAU\u0005I\u0019\u0016\u000bT+tKJ$UMZ5oK\u0012$\u0016\u0010]3\u0002\u0007U$Go\t\u0002\u0002:B\u0019A-a/\n\u0007\u0005u&DA\u0005NCR\u0014\u0018\u000e_+E)\"\u001a\u0001aQ%"
)
public interface Matrix extends Serializable {
   void org$apache$spark$mllib$linalg$Matrix$_setter_$isTransposed_$eq(final boolean x$1);

   int numRows();

   int numCols();

   boolean isTransposed();

   // $FF: synthetic method
   static double[] toArray$(final Matrix $this) {
      return $this.toArray();
   }

   default double[] toArray() {
      double[] newArray = new double[this.numRows() * this.numCols()];
      this.foreachActive((i, j, v) -> {
         $anonfun$toArray$1(this, newArray, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
         return BoxedUnit.UNIT;
      });
      return newArray;
   }

   Iterator colIter();

   // $FF: synthetic method
   static Iterator rowIter$(final Matrix $this) {
      return $this.rowIter();
   }

   default Iterator rowIter() {
      return this.transpose().colIter();
   }

   breeze.linalg.Matrix asBreeze();

   double apply(final int i, final int j);

   int index(final int i, final int j);

   void update(final int i, final int j, final double v);

   Matrix copy();

   Matrix transpose();

   // $FF: synthetic method
   static DenseMatrix multiply$(final Matrix $this, final DenseMatrix y) {
      return $this.multiply(y);
   }

   default DenseMatrix multiply(final DenseMatrix y) {
      DenseMatrix C = DenseMatrix$.MODULE$.zeros(this.numRows(), y.numCols());
      BLAS$.MODULE$.gemm((double)1.0F, this, y, (double)0.0F, C);
      return C;
   }

   // $FF: synthetic method
   static DenseVector multiply$(final Matrix $this, final DenseVector y) {
      return $this.multiply(y);
   }

   default DenseVector multiply(final DenseVector y) {
      return this.multiply((Vector)y);
   }

   // $FF: synthetic method
   static DenseVector multiply$(final Matrix $this, final Vector y) {
      return $this.multiply(y);
   }

   default DenseVector multiply(final Vector y) {
      DenseVector output = new DenseVector(new double[this.numRows()]);
      BLAS$.MODULE$.gemv((double)1.0F, this, y, (double)0.0F, output);
      return output;
   }

   // $FF: synthetic method
   static String toString$(final Matrix $this) {
      return $this.toString();
   }

   default String toString() {
      return this.asBreeze().toString();
   }

   // $FF: synthetic method
   static String toString$(final Matrix $this, final int maxLines, final int maxLineWidth) {
      return $this.toString(maxLines, maxLineWidth);
   }

   default String toString(final int maxLines, final int maxLineWidth) {
      return this.asBreeze().toString(maxLines, maxLineWidth);
   }

   Matrix map(final Function1 f);

   Matrix update(final Function1 f);

   void foreachActive(final Function3 f);

   int numNonzeros();

   int numActives();

   org.apache.spark.ml.linalg.Matrix asML();

   // $FF: synthetic method
   static void $anonfun$toArray$1(final Matrix $this, final double[] newArray$1, final int i, final int j, final double v) {
      newArray$1[j * $this.numRows() + i] = v;
   }

   static void $init$(final Matrix $this) {
      $this.org$apache$spark$mllib$linalg$Matrix$_setter_$isTransposed_$eq(false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
