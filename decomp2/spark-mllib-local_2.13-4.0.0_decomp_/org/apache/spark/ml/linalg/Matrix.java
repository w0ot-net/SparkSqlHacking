package org.apache.spark.ml.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function3;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eha\u0002\u0015*!\u0003\r\t\u0003\u000e\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0019\u00021\t!\u0014\u0005\u00065\u00021\t!\u0014\u0005\b9\u0002\u0011\r\u0011\"\u0001^\u0011\u0019\u0011\u0007\u0001\"\u0001,;\"11\r\u0001C\u0001WuCQ\u0001\u001a\u0001\u0005\u0002\u0015DQ!\u001c\u0001\u0007\u00029DQa\u001e\u0001\u0005\u00029Da!\u001f\u0001\u0007\u0002-R\bbBA\u0002\u0001\u0019\u0005\u0011Q\u0001\u0005\t\u0003#\u0001a\u0011A\u0016\u0002\u0014!A\u0011\u0011\u0004\u0001\u0007\u0002-\nY\u0002C\u0004\u0002&\u00011\t!a\n\t\u000f\u00055\u0002A\"\u0001\u0002(!9\u0011\u0011\u0007\u0001\u0005\u0002\u0005M\u0002bBA\u0019\u0001\u0011\u0005\u0011\u0011\t\u0005\b\u0003c\u0001A\u0011AA'\u0011\u001d\t\u0019\u0006\u0001C!\u0003+Bq!a\u0015\u0001\t\u0003\t9\u0007\u0003\u0005\u0002t\u00011\t!LA;\u0011!\tI\u0002\u0001D\u0001W\u0005\u0005\u0005bBAC\u0001\u0019\u0005\u0011q\u0011\u0005\u0007\u0003/\u0003a\u0011A'\t\r\u0005m\u0005A\"\u0001N\u0011!\ty\n\u0001D\u0001W\u0005\u0005\u0006bBAW\u0001\u0011\u0005\u0011q\u0016\u0005\b\u0003g\u0003A\u0011AAX\u0011\u001d\t9\f\u0001C\u0001\u0003_C\u0001\"a/\u0001\r\u0003Y\u0013Q\u0018\u0005\b\u0003\u0003\u0004A\u0011AAb\u0011\u001d\t9\r\u0001C\u0001\u0003\u0007Dq!a3\u0001\t\u0003\t\u0019\rC\u0004\u0002P\u0002!\t!a\n\t\u000f\u0005M\u0007\u0001\"\u0001\u0002(!9\u0011q\u001b\u0001\u0005\u0002\u0005\u001d\u0002\u0002CAn\u0001\u0011\u00051&!8\t\u0011\u0005\u0015\b\u0001\"\u0001,\u0003OD\u0001\"a;\u0001\r\u0003Y\u0013Q\u001c\u0002\u0007\u001b\u0006$(/\u001b=\u000b\u0005)Z\u0013A\u00027j]\u0006dwM\u0003\u0002-[\u0005\u0011Q\u000e\u001c\u0006\u0003]=\nQa\u001d9be.T!\u0001M\u0019\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0014aA8sO\u000e\u00011c\u0001\u00016wA\u0011a'O\u0007\u0002o)\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;o\t1\u0011I\\=SK\u001a\u0004\"\u0001\u0010#\u000f\u0005u\u0012eB\u0001 B\u001b\u0005y$B\u0001!4\u0003\u0019a$o\\8u}%\t\u0001(\u0003\u0002Do\u00059\u0001/Y2lC\u001e,\u0017BA#G\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019u'\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0013B\u0011aGS\u0005\u0003\u0017^\u0012A!\u00168ji\u00069a.^7S_^\u001cX#\u0001(\u0011\u0005Yz\u0015B\u0001)8\u0005\rIe\u000e\u001e\u0015\u0004\u0005IC\u0006CA*W\u001b\u0005!&BA+.\u0003)\tgN\\8uCRLwN\\\u0005\u0003/R\u0013QaU5oG\u0016\f\u0013!W\u0001\u0006e9\u0002d\u0006M\u0001\b]Vl7i\u001c7tQ\r\u0019!\u000bW\u0001\rSN$&/\u00198ta>\u001cX\rZ\u000b\u0002=B\u0011agX\u0005\u0003A^\u0012qAQ8pY\u0016\fg\u000eK\u0002\u0005%b\u000b!\"[:D_2l\u0015M[8s\u0003)I7OU8x\u001b\u0006TwN]\u0001\bi>\f%O]1z+\u00051\u0007c\u0001\u001chS&\u0011\u0001n\u000e\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003m)L!a[\u001c\u0003\r\u0011{WO\u00197fQ\r9!\u000bW\u0001\bG>d\u0017\n^3s+\u0005y\u0007c\u0001\u001fqe&\u0011\u0011O\u0012\u0002\t\u0013R,'/\u0019;peB\u00111\u000f^\u0007\u0002S%\u0011Q/\u000b\u0002\u0007-\u0016\u001cGo\u001c:)\u0007!\u0011\u0006,A\u0004s_^LE/\u001a:)\u0007%\u0011\u0006,\u0001\u0005bg\n\u0013X-\u001a>f+\u0005Y\b\u0003\u0002?\u0002\u0002%l\u0011! \u0006\u0003UyT\u0011a`\u0001\u0007EJ,WM_3\n\u0005!j\u0018!B1qa2LH#B5\u0002\b\u0005-\u0001BBA\u0005\u0017\u0001\u0007a*A\u0001j\u0011\u0019\tia\u0003a\u0001\u001d\u0006\t!\u000eK\u0002\f%b\u000bQ!\u001b8eKb$RATA\u000b\u0003/Aa!!\u0003\r\u0001\u0004q\u0005BBA\u0007\u0019\u0001\u0007a*\u0001\u0004va\u0012\fG/\u001a\u000b\b\u0013\u0006u\u0011qDA\u0011\u0011\u0019\tI!\u0004a\u0001\u001d\"1\u0011QB\u0007A\u00029Ca!a\t\u000e\u0001\u0004I\u0017!\u0001<\u0002\t\r|\u0007/_\u000b\u0003\u0003S\u0001\"a\u001d\u0001)\u00079\u0011\u0006,A\u0005ue\u0006t7\u000f]8tK\"\u001aqB\u0015-\u0002\u00115,H\u000e^5qYf$B!!\u000e\u0002<A\u00191/a\u000e\n\u0007\u0005e\u0012FA\u0006EK:\u001cX-T1ue&D\bbBA\u001f!\u0001\u0007\u0011QG\u0001\u0002s\"\u001a\u0001C\u0015-\u0015\t\u0005\r\u0013\u0011\n\t\u0004g\u0006\u0015\u0013bAA$S\tYA)\u001a8tKZ+7\r^8s\u0011\u001d\ti$\u0005a\u0001\u0003\u0007B3!\u0005*Y)\u0011\t\u0019%a\u0014\t\r\u0005u\"\u00031\u0001sQ\r\u0011\"\u000bW\u0001\ti>\u001cFO]5oOR\u0011\u0011q\u000b\t\u0005\u00033\n\tG\u0004\u0003\u0002\\\u0005u\u0003C\u0001 8\u0013\r\tyfN\u0001\u0007!J,G-\u001a4\n\t\u0005\r\u0014Q\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005}s\u0007\u0006\u0004\u0002X\u0005%\u0014Q\u000e\u0005\u0007\u0003W\"\u0002\u0019\u0001(\u0002\u00115\f\u0007\u0010T5oKNDa!a\u001c\u0015\u0001\u0004q\u0015\u0001D7bq2Kg.Z,jIRD\u0007f\u0001\u000bS1\u0006\u0019Q.\u00199\u0015\t\u0005%\u0012q\u000f\u0005\b\u0003s*\u0002\u0019AA>\u0003\u00051\u0007#\u0002\u001c\u0002~%L\u0017bAA@o\tIa)\u001e8di&|g.\r\u000b\u0005\u0003S\t\u0019\tC\u0004\u0002zY\u0001\r!a\u001f\u0002\u001b\u0019|'/Z1dQ\u0006\u001bG/\u001b<f)\rI\u0015\u0011\u0012\u0005\b\u0003s:\u0002\u0019AAF!\u001d1\u0014Q\u0012(OS&K1!a$8\u0005%1UO\\2uS>t7\u0007\u000b\u0003\u0018%\u0006M\u0015EAAK\u0003\u0015\u0011dF\r\u00181\u0003-qW/\u001c(p]j,'o\\:)\u0007a\u0011\u0006,\u0001\u0006ok6\f5\r^5wKND3!\u0007*Y\u00039!xn\u00159beN,W*\u0019;sSb$B!a)\u0002*B\u00191/!*\n\u0007\u0005\u001d\u0016F\u0001\u0007Ta\u0006\u00148/Z'biJL\u0007\u0010\u0003\u0004\u0002,j\u0001\rAX\u0001\tG>dW*\u00196pe\u0006\u0001Bo\\*qCJ\u001cXmQ8m\u001b\u0006TwN]\u000b\u0003\u0003GCCa\u0007*\u0002\u0014\u0006\u0001Bo\\*qCJ\u001cXMU8x\u001b\u0006TwN\u001d\u0015\u00059I\u000b\u0019*\u0001\u0005u_N\u0003\u0018M]:fQ\u0011i\"+a%\u0002\u001bQ|G)\u001a8tK6\u000bGO]5y)\u0011\t)$a0\t\r\u0005-f\u00041\u0001_\u0003\u001d!x\u000eR3og\u0016,\"!!\u000e)\t}\u0011\u00161S\u0001\u0010i>$UM\\:f%><X*\u00196pe\"\"\u0001EUAJ\u0003=!x\u000eR3og\u0016\u001cu\u000e\\'bU>\u0014\b\u0006B\u0011S\u0003'\u000b!cY8naJ,7o]3e\u0007>dW*\u00196pe\"\"!EUAJ\u0003I\u0019w.\u001c9sKN\u001cX\r\u001a*po6\u000b'n\u001c:)\t\r\u0012\u00161S\u0001\u000bG>l\u0007O]3tg\u0016$\u0007\u0006\u0002\u0013S\u0003'\u000b1cZ3u\t\u0016t7/Z*ju\u0016LeNQ=uKN,\"!a8\u0011\u0007Y\n\t/C\u0002\u0002d^\u0012A\u0001T8oO\u0006!r-\u001a;Ta\u0006\u00148/Z*ju\u0016LeNQ=uKN$B!a8\u0002j\"1\u00111\u0016\u0014A\u0002y\u000babZ3u'&TX-\u00138CsR,7/K\u0003\u0001\u0003o\t)\u000bK\u0002\u0001%b\u0003"
)
public interface Matrix extends Serializable {
   void org$apache$spark$ml$linalg$Matrix$_setter_$isTransposed_$eq(final boolean x$1);

   int numRows();

   int numCols();

   boolean isTransposed();

   // $FF: synthetic method
   static boolean isColMajor$(final Matrix $this) {
      return $this.isColMajor();
   }

   default boolean isColMajor() {
      return !this.isTransposed();
   }

   // $FF: synthetic method
   static boolean isRowMajor$(final Matrix $this) {
      return $this.isRowMajor();
   }

   default boolean isRowMajor() {
      return this.isTransposed();
   }

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

   SparseMatrix toSparseMatrix(final boolean colMajor);

   // $FF: synthetic method
   static SparseMatrix toSparseColMajor$(final Matrix $this) {
      return $this.toSparseColMajor();
   }

   default SparseMatrix toSparseColMajor() {
      return this.toSparseMatrix(true);
   }

   // $FF: synthetic method
   static SparseMatrix toSparseRowMajor$(final Matrix $this) {
      return $this.toSparseRowMajor();
   }

   default SparseMatrix toSparseRowMajor() {
      return this.toSparseMatrix(false);
   }

   // $FF: synthetic method
   static SparseMatrix toSparse$(final Matrix $this) {
      return $this.toSparse();
   }

   default SparseMatrix toSparse() {
      return this.toSparseMatrix(this.isColMajor());
   }

   DenseMatrix toDenseMatrix(final boolean colMajor);

   // $FF: synthetic method
   static DenseMatrix toDense$(final Matrix $this) {
      return $this.toDense();
   }

   default DenseMatrix toDense() {
      return this.toDenseMatrix(this.isColMajor());
   }

   // $FF: synthetic method
   static DenseMatrix toDenseRowMajor$(final Matrix $this) {
      return $this.toDenseRowMajor();
   }

   default DenseMatrix toDenseRowMajor() {
      return this.toDenseMatrix(false);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseColMajor$(final Matrix $this) {
      return $this.toDenseColMajor();
   }

   default DenseMatrix toDenseColMajor() {
      return this.toDenseMatrix(true);
   }

   // $FF: synthetic method
   static Matrix compressedColMajor$(final Matrix $this) {
      return $this.compressedColMajor();
   }

   default Matrix compressedColMajor() {
      return (Matrix)(this.getDenseSizeInBytes() <= this.getSparseSizeInBytes(true) ? this.toDenseColMajor() : this.toSparseColMajor());
   }

   // $FF: synthetic method
   static Matrix compressedRowMajor$(final Matrix $this) {
      return $this.compressedRowMajor();
   }

   default Matrix compressedRowMajor() {
      return (Matrix)(this.getDenseSizeInBytes() <= this.getSparseSizeInBytes(false) ? this.toDenseRowMajor() : this.toSparseRowMajor());
   }

   // $FF: synthetic method
   static Matrix compressed$(final Matrix $this) {
      return $this.compressed();
   }

   default Matrix compressed() {
      long cscSize = this.getSparseSizeInBytes(true);
      long csrSize = this.getSparseSizeInBytes(false);
      if (this.getDenseSizeInBytes() <= .MODULE$.min(cscSize, csrSize)) {
         return this.toDense();
      } else {
         return cscSize <= csrSize ? this.toSparseColMajor() : this.toSparseRowMajor();
      }
   }

   // $FF: synthetic method
   static long getDenseSizeInBytes$(final Matrix $this) {
      return $this.getDenseSizeInBytes();
   }

   default long getDenseSizeInBytes() {
      return Matrices$.MODULE$.getDenseSize((long)this.numCols(), (long)this.numRows());
   }

   // $FF: synthetic method
   static long getSparseSizeInBytes$(final Matrix $this, final boolean colMajor) {
      return $this.getSparseSizeInBytes(colMajor);
   }

   default long getSparseSizeInBytes(final boolean colMajor) {
      int nnz = this.numNonzeros();
      long numPtrs = colMajor ? (long)this.numCols() + 1L : (long)this.numRows() + 1L;
      return Matrices$.MODULE$.getSparseSize((long)nnz, numPtrs);
   }

   long getSizeInBytes();

   // $FF: synthetic method
   static void $anonfun$toArray$1(final Matrix $this, final double[] newArray$1, final int i, final int j, final double v) {
      newArray$1[j * $this.numRows() + i] = v;
   }

   static void $init$(final Matrix $this) {
      $this.org$apache$spark$ml$linalg$Matrix$_setter_$isTransposed_$eq(false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
