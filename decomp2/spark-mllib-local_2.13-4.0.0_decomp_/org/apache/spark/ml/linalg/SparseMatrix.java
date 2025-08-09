package org.apache.spark.ml.linalg;

import breeze.linalg.CSCMatrix;
import breeze.storage.Zero.;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import scala.Function1;
import scala.Function3;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t]d\u0001\u0002\u0017.\u0001aB\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0001\u0012\u0005\t#\u0002\u0011\t\u0011)A\u0005\u000b\"A1\u000b\u0001BC\u0002\u0013\u0005A\t\u0003\u0005V\u0001\t\u0005\t\u0015!\u0003F\u0011!9\u0006A!b\u0001\n\u0003A\u0006\u0002C/\u0001\u0005\u0003\u0005\u000b\u0011B-\t\u0011}\u0003!Q1A\u0005\u0002aC\u0001\"\u0019\u0001\u0003\u0002\u0003\u0006I!\u0017\u0005\tG\u0002\u0011)\u0019!C\u0001I\"A!\u000e\u0001B\u0001B\u0003%Q\r\u0003\u0005m\u0001\t\u0015\r\u0011\"\u0011n\u0011!\t\bA!A!\u0002\u0013q\u0007\"\u0002:\u0001\t\u0003\u0019\bB\u0002:\u0001\t\u0003\t\u0019\u0001C\u0004\u0002\u0012\u0001!\t%a\u0005\t\u000f\u0005U\u0001\u0001\"\u0011\u0002\u0018!A\u00111\u0005\u0001\u0005\u0002=\n)\u0003C\u0004\u00024\u0001!\t%!\u000e\t\u0011\u0005}\u0002\u0001\"\u00010\u0003\u0003B\u0001\"a\u0012\u0001\t\u0003y\u0013\u0011\n\u0005\b\u00033\u0002A\u0011IA.\u0011!\ti\u0006\u0001C\u0001c\u0005}\u0003\u0002CA$\u0001\u0011\u0005q&a\u001b\t\u000f\u0005=\u0004\u0001\"\u0011\u0002\\!9\u0011\u0011\u000f\u0001\u0005B\u0005M\u0004BBA?\u0001\u0011\u0005C\t\u0003\u0004\u0002\u0000\u0001!\t\u0005\u0012\u0005\t\u0003\u0003\u0003A\u0011I\u0018\u0002\u0004\"A\u0011\u0011\u0012\u0001\u0005B=\nY\tC\u0004\u0002\u0016\u0002!\t%a&\t\u0011\u0005]\u0006\u0001\"\u00010\u0003s;q!a1.\u0011\u0003\t)M\u0002\u0004-[!\u0005\u0011q\u0019\u0005\u0007e\u0006\"\t!!7\t\u0011\u0005m\u0017\u0005\"\u00010\u0003;D\u0001\"a<\"\t\u0003y\u0013\u0011\u001f\u0005\b\u0003{\fC\u0011AA\u0000\u0011\u001d\u00119\"\tC\u0001\u00053AqA!\t\"\t\u0013\u0011\u0019\u0003C\u0004\u0003>\u0005\"\tAa\u0010\t\u000f\t-\u0013\u0005\"\u0001\u0003N!9!\u0011L\u0011\u0005\u0002\tm\u0003\"\u0003B2C\u0005\u0005I\u0011\u0002B3\u00051\u0019\u0006/\u0019:tK6\u000bGO]5y\u0015\tqs&\u0001\u0004mS:\fGn\u001a\u0006\u0003aE\n!!\u001c7\u000b\u0005I\u001a\u0014!B:qCJ\\'B\u0001\u001b6\u0003\u0019\t\u0007/Y2iK*\ta'A\u0002pe\u001e\u001c\u0001aE\u0002\u0001s}\u0002\"AO\u001f\u000e\u0003mR\u0011\u0001P\u0001\u0006g\u000e\fG.Y\u0005\u0003}m\u0012a!\u00118z%\u00164\u0007C\u0001!B\u001b\u0005i\u0013B\u0001\".\u0005\u0019i\u0015\r\u001e:jq\u00069a.^7S_^\u001cX#A#\u0011\u0005i2\u0015BA$<\u0005\rIe\u000e\u001e\u0015\u0004\u0003%{\u0005C\u0001&N\u001b\u0005Y%B\u0001'2\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u001d.\u0013QaU5oG\u0016\f\u0013\u0001U\u0001\u0006e9\u0002d\u0006M\u0001\t]Vl'k\\<tA!\u001a!!S(\u0002\u000f9,XnQ8mg\"\u001a1!S(\u0002\u00119,XnQ8mg\u0002B3\u0001B%P\u0003\u001d\u0019w\u000e\u001c)ueN,\u0012!\u0017\t\u0004ui+\u0015BA.<\u0005\u0015\t%O]1zQ\r)\u0011jT\u0001\tG>d\u0007\u000b\u001e:tA!\u001aa!S(\u0002\u0015I|w/\u00138eS\u000e,7\u000fK\u0002\b\u0013>\u000b1B]8x\u0013:$\u0017nY3tA!\u001a\u0001\"S(\u0002\rY\fG.^3t+\u0005)\u0007c\u0001\u001e[MB\u0011!hZ\u0005\u0003Qn\u0012a\u0001R8vE2,\u0007fA\u0005J\u001f\u00069a/\u00197vKN\u0004\u0003f\u0001\u0006J\u001f\u0006a\u0011n\u001d+sC:\u001c\bo\\:fIV\ta\u000e\u0005\u0002;_&\u0011\u0001o\u000f\u0002\b\u0005>|G.Z1o\u00035I7\u000f\u0016:b]N\u0004xn]3eA\u00051A(\u001b8jiz\"r\u0001^;xsnlx\u0010\u0005\u0002A\u0001!)1)\u0004a\u0001\u000b\"\u001aQ/S(\t\u000bMk\u0001\u0019A#)\u0007]Lu\nC\u0003X\u001b\u0001\u0007\u0011\fK\u0002z\u0013>CQaX\u0007A\u0002eC3a_%P\u0011\u0015\u0019W\u00021\u0001fQ\ri\u0018j\u0014\u0005\u0006Y6\u0001\rA\u001c\u0015\u0004\u001b%{Ec\u0003;\u0002\u0006\u0005\u001d\u0011\u0011BA\u0006\u0003\u001bAQa\u0011\bA\u0002\u0015CQa\u0015\bA\u0002\u0015CQa\u0016\bA\u0002eCQa\u0018\bA\u0002eCQa\u0019\bA\u0002\u0015D3AD%P\u0003!A\u0017m\u001d5D_\u0012,G#A#\u0002\r\u0015\fX/\u00197t)\rq\u0017\u0011\u0004\u0005\b\u00037\u0001\u0002\u0019AA\u000f\u0003\u0005y\u0007c\u0001\u001e\u0002 %\u0019\u0011\u0011E\u001e\u0003\u0007\u0005s\u00170\u0001\u0005bg\n\u0013X-\u001a>f+\t\t9\u0003E\u0003\u0002*\u0005Eb-\u0004\u0002\u0002,)\u0019a&!\f\u000b\u0005\u0005=\u0012A\u00022sK\u0016TX-C\u0002C\u0003W\tQ!\u00199qYf$RAZA\u001c\u0003wAa!!\u000f\u0013\u0001\u0004)\u0015!A5\t\r\u0005u\"\u00031\u0001F\u0003\u0005Q\u0017!B5oI\u0016DH#B#\u0002D\u0005\u0015\u0003BBA\u001d'\u0001\u0007Q\t\u0003\u0004\u0002>M\u0001\r!R\u0001\u0007kB$\u0017\r^3\u0015\u0011\u0005-\u0013\u0011KA*\u0003+\u00022AOA'\u0013\r\tye\u000f\u0002\u0005+:LG\u000f\u0003\u0004\u0002:Q\u0001\r!\u0012\u0005\u0007\u0003{!\u0002\u0019A#\t\r\u0005]C\u00031\u0001g\u0003\u00051\u0018\u0001B2paf,\u0012\u0001^\u0001\u0004[\u0006\u0004Hc\u0001;\u0002b!9\u00111\r\fA\u0002\u0005\u0015\u0014!\u00014\u0011\u000bi\n9G\u001a4\n\u0007\u0005%4HA\u0005Gk:\u001cG/[8ocQ\u0019A/!\u001c\t\u000f\u0005\rt\u00031\u0001\u0002f\u0005IAO]1ogB|7/Z\u0001\u000eM>\u0014X-Y2i\u0003\u000e$\u0018N^3\u0015\t\u0005-\u0013Q\u000f\u0005\b\u0003GJ\u0002\u0019AA<!!Q\u0014\u0011P#FM\u0006-\u0013bAA>w\tIa)\u001e8di&|gnM\u0001\f]Vlgj\u001c8{KJ|7/\u0001\u0006ok6\f5\r^5wKN\fa\u0002^8Ta\u0006\u00148/Z'biJL\u0007\u0010F\u0002u\u0003\u000bCa!a\"\u001d\u0001\u0004q\u0017\u0001C2pY6\u000b'n\u001c:\u0002\u001bQ|G)\u001a8tK6\u000bGO]5y)\u0011\ti)a%\u0011\u0007\u0001\u000by)C\u0002\u0002\u00126\u00121\u0002R3og\u0016l\u0015\r\u001e:jq\"1\u0011qQ\u000fA\u00029\fqaY8m\u0013R,'/\u0006\u0002\u0002\u001aB1\u00111TAV\u0003csA!!(\u0002(:!\u0011qTAS\u001b\t\t\tKC\u0002\u0002$^\na\u0001\u0010:p_Rt\u0014\"\u0001\u001f\n\u0007\u0005%6(A\u0004qC\u000e\\\u0017mZ3\n\t\u00055\u0016q\u0016\u0002\t\u0013R,'/\u0019;pe*\u0019\u0011\u0011V\u001e\u0011\u0007\u0001\u000b\u0019,C\u0002\u000266\u0012aAV3di>\u0014\u0018AD4fiNK'0Z%o\u0005f$Xm]\u000b\u0003\u0003w\u00032AOA_\u0013\r\tyl\u000f\u0002\u0005\u0019>tw\rK\u0002\u0001\u0013>\u000bAb\u00159beN,W*\u0019;sSb\u0004\"\u0001Q\u0011\u0014\t\u0005J\u0014\u0011\u001a\t\u0005\u0003\u0017\f).\u0004\u0002\u0002N*!\u0011qZAi\u0003\tIwN\u0003\u0002\u0002T\u0006!!.\u0019<b\u0013\u0011\t9.!4\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\u0015\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0003?\fY\u000fE\u0003;\u0003C\f)/C\u0002\u0002dn\u0012aa\u00149uS>t\u0007#\u0003\u001e\u0002h\u0016+\u0015,W3o\u0013\r\tIo\u000f\u0002\u0007)V\u0004H.\u001a\u001c\t\r\u000558\u00051\u0001u\u0003\t\u0019X.A\u0006ge>lg+Z2u_J\u001cHc\u0001;\u0002t\"9\u0011Q\u001f\u0013A\u0002\u0005]\u0018a\u0002<fGR|'o\u001d\t\u0007\u00037\u000bI0!-\n\t\u0005m\u0018q\u0016\u0002\u0004'\u0016\f\u0018a\u00024s_6\u001cuj\u0014\u000b\bi\n\u0005!1\u0001B\u0003\u0011\u0015\u0019U\u00051\u0001F\u0011\u0015\u0019V\u00051\u0001F\u0011\u001d\u00119!\na\u0001\u0005\u0013\tq!\u001a8ue&,7\u000f\u0005\u0004\u0002\u001c\n-!qB\u0005\u0005\u0005\u001b\tyK\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\u0019Q$\u0011C#FM&\u0019!1C\u001e\u0003\rQ+\b\u000f\\34Q\r)\u0013jT\u0001\u0006gB,\u00170\u001a\u000b\u0004i\nm\u0001B\u0002B\u000fM\u0001\u0007Q)A\u0001oQ\r1\u0013jT\u0001\u000eO\u0016t'+\u00198e\u001b\u0006$(/\u001b=\u0015\u0013Q\u0014)Ca\n\u0003*\t5\u0002\"B\"(\u0001\u0004)\u0005\"B*(\u0001\u0004)\u0005B\u0002B\u0016O\u0001\u0007a-A\u0004eK:\u001c\u0018\u000e^=\t\u000f\t=r\u00051\u0001\u00032\u0005\u0019!O\\4\u0011\t\tM\"\u0011H\u0007\u0003\u0005kQAAa\u000e\u0002R\u0006!Q\u000f^5m\u0013\u0011\u0011YD!\u000e\u0003\rI\u000bg\u000eZ8n\u0003\u0019\u0019\bO]1oIRIAO!\u0011\u0003D\t\u0015#q\t\u0005\u0006\u0007\"\u0002\r!\u0012\u0005\u0006'\"\u0002\r!\u0012\u0005\u0007\u0005WA\u0003\u0019\u00014\t\u000f\t=\u0002\u00061\u0001\u00032!\u001a\u0001&S(\u0002\u000fM\u0004(/\u00198e]RIAOa\u0014\u0003R\tM#Q\u000b\u0005\u0006\u0007&\u0002\r!\u0012\u0005\u0006'&\u0002\r!\u0012\u0005\u0007\u0005WI\u0003\u0019\u00014\t\u000f\t=\u0012\u00061\u0001\u00032!\u001a\u0011&S(\u0002\rM\u0004H-[1h)\r!(Q\f\u0005\b\u0005?R\u0003\u0019AAY\u0003\u00191Xm\u0019;pe\"\u001a!&S(\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t\u001d\u0004\u0003\u0002B5\u0005_j!Aa\u001b\u000b\t\t5\u0014\u0011[\u0001\u0005Y\u0006tw-\u0003\u0003\u0003r\t-$AB(cU\u0016\u001cG\u000fK\u0002\"\u0013>C3\u0001I%P\u0001"
)
public class SparseMatrix implements Matrix {
   private final int numRows;
   private final int numCols;
   private final int[] colPtrs;
   private final int[] rowIndices;
   private final double[] values;
   private final boolean isTransposed;

   public static SparseMatrix spdiag(final Vector vector) {
      return SparseMatrix$.MODULE$.spdiag(vector);
   }

   public static SparseMatrix sprandn(final int numRows, final int numCols, final double density, final Random rng) {
      return SparseMatrix$.MODULE$.sprandn(numRows, numCols, density, rng);
   }

   public static SparseMatrix sprand(final int numRows, final int numCols, final double density, final Random rng) {
      return SparseMatrix$.MODULE$.sprand(numRows, numCols, density, rng);
   }

   public static SparseMatrix speye(final int n) {
      return SparseMatrix$.MODULE$.speye(n);
   }

   public static SparseMatrix fromCOO(final int numRows, final int numCols, final Iterable entries) {
      return SparseMatrix$.MODULE$.fromCOO(numRows, numCols, entries);
   }

   public boolean isColMajor() {
      return Matrix.isColMajor$(this);
   }

   public boolean isRowMajor() {
      return Matrix.isRowMajor$(this);
   }

   public double[] toArray() {
      return Matrix.toArray$(this);
   }

   public Iterator rowIter() {
      return Matrix.rowIter$(this);
   }

   public DenseMatrix multiply(final DenseMatrix y) {
      return Matrix.multiply$(this, (DenseMatrix)y);
   }

   public DenseVector multiply(final DenseVector y) {
      return Matrix.multiply$(this, (DenseVector)y);
   }

   public DenseVector multiply(final Vector y) {
      return Matrix.multiply$(this, (Vector)y);
   }

   public String toString() {
      return Matrix.toString$(this);
   }

   public String toString(final int maxLines, final int maxLineWidth) {
      return Matrix.toString$(this, maxLines, maxLineWidth);
   }

   public SparseMatrix toSparseColMajor() {
      return Matrix.toSparseColMajor$(this);
   }

   public SparseMatrix toSparseRowMajor() {
      return Matrix.toSparseRowMajor$(this);
   }

   public SparseMatrix toSparse() {
      return Matrix.toSparse$(this);
   }

   public DenseMatrix toDense() {
      return Matrix.toDense$(this);
   }

   public DenseMatrix toDenseRowMajor() {
      return Matrix.toDenseRowMajor$(this);
   }

   public DenseMatrix toDenseColMajor() {
      return Matrix.toDenseColMajor$(this);
   }

   public Matrix compressedColMajor() {
      return Matrix.compressedColMajor$(this);
   }

   public Matrix compressedRowMajor() {
      return Matrix.compressedRowMajor$(this);
   }

   public Matrix compressed() {
      return Matrix.compressed$(this);
   }

   public long getDenseSizeInBytes() {
      return Matrix.getDenseSizeInBytes$(this);
   }

   public long getSparseSizeInBytes(final boolean colMajor) {
      return Matrix.getSparseSizeInBytes$(this, colMajor);
   }

   public void org$apache$spark$ml$linalg$Matrix$_setter_$isTransposed_$eq(final boolean x$1) {
   }

   public int numRows() {
      return this.numRows;
   }

   public int numCols() {
      return this.numCols;
   }

   public int[] colPtrs() {
      return this.colPtrs;
   }

   public int[] rowIndices() {
      return this.rowIndices;
   }

   public double[] values() {
      return this.values;
   }

   public boolean isTransposed() {
      return this.isTransposed;
   }

   public int hashCode() {
      return this.asBreeze().hashCode();
   }

   public boolean equals(final Object o) {
      if (!(o instanceof Matrix var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            breeze.linalg.Matrix var10000 = this.asBreeze();
            breeze.linalg.Matrix var5 = var4.asBreeze();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public breeze.linalg.Matrix asBreeze() {
      if (!this.isTransposed()) {
         return new CSCMatrix.mcD.sp(this.values(), this.numRows(), this.numCols(), this.colPtrs(), this.rowIndices(), .MODULE$.DoubleZero());
      } else {
         CSCMatrix breezeMatrix = new CSCMatrix.mcD.sp(this.values(), this.numCols(), this.numRows(), this.colPtrs(), this.rowIndices(), .MODULE$.DoubleZero());
         return (breeze.linalg.Matrix)breezeMatrix.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_CSC(scala.reflect.ClassTag..MODULE$.Double(), .MODULE$.DoubleZero(), breeze.math.Semiring..MODULE$.semiringD()));
      }
   }

   public double apply(final int i, final int j) {
      int ind = this.index(i, j);
      return ind < 0 ? (double)0.0F : this.values()[ind];
   }

   public int index(final int i, final int j) {
      scala.Predef..MODULE$.require(i >= 0 && i < this.numRows(), () -> {
         int var10000 = this.numRows();
         return "Expected 0 <= i < " + var10000 + ", got i = " + i + ".";
      });
      scala.Predef..MODULE$.require(j >= 0 && j < this.numCols(), () -> {
         int var10000 = this.numCols();
         return "Expected 0 <= j < " + var10000 + ", got j = " + j + ".";
      });
      return !this.isTransposed() ? Arrays.binarySearch(this.rowIndices(), this.colPtrs()[j], this.colPtrs()[j + 1], i) : Arrays.binarySearch(this.rowIndices(), this.colPtrs()[i], this.colPtrs()[i + 1], j);
   }

   public void update(final int i, final int j, final double v) {
      int ind = this.index(i, j);
      if (ind < 0) {
         throw new NoSuchElementException("The given row and column indices correspond to a zero value. Only non-zero elements in Sparse Matrices can be updated.");
      } else {
         this.values()[ind] = v;
      }
   }

   public SparseMatrix copy() {
      return new SparseMatrix(this.numRows(), this.numCols(), this.colPtrs(), this.rowIndices(), (double[])this.values().clone());
   }

   public SparseMatrix map(final Function1 f) {
      return new SparseMatrix(this.numRows(), this.numCols(), this.colPtrs(), this.rowIndices(), (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.values()), f, scala.reflect.ClassTag..MODULE$.Double()), this.isTransposed());
   }

   public SparseMatrix update(final Function1 f) {
      int len = this.values().length;

      for(int i = 0; i < len; ++i) {
         this.values()[i] = f.apply$mcDD$sp(this.values()[i]);
      }

      return this;
   }

   public SparseMatrix transpose() {
      return new SparseMatrix(this.numCols(), this.numRows(), this.colPtrs(), this.rowIndices(), this.values(), !this.isTransposed());
   }

   public void foreachActive(final Function3 f) {
      if (!this.isTransposed()) {
         for(int j = 0; j < this.numCols(); ++j) {
            int idx = this.colPtrs()[j];

            for(int idxEnd = this.colPtrs()[j + 1]; idx < idxEnd; ++idx) {
               f.apply(BoxesRunTime.boxToInteger(this.rowIndices()[idx]), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(this.values()[idx]));
            }
         }

      } else {
         for(int i = 0; i < this.numRows(); ++i) {
            int idx = this.colPtrs()[i];

            for(int idxEnd = this.colPtrs()[i + 1]; idx < idxEnd; ++idx) {
               int j = this.rowIndices()[idx];
               f.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(this.values()[idx]));
            }
         }

      }
   }

   public int numNonzeros() {
      return scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.doubleArrayOps(this.values()), (JFunction1.mcZD.sp)(x$2) -> x$2 != (double)0);
   }

   public int numActives() {
      return this.values().length;
   }

   public SparseMatrix toSparseMatrix(final boolean colMajor) {
      if (this.isColMajor() && !colMajor) {
         CSCMatrix breezeTransposed = (CSCMatrix)((CSCMatrix)this.asBreeze()).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_CSC(scala.reflect.ClassTag..MODULE$.Double(), .MODULE$.DoubleZero(), breeze.math.Semiring..MODULE$.semiringD()));
         return (SparseMatrix)Matrices$.MODULE$.fromBreeze(breezeTransposed).transpose();
      } else if (this.isRowMajor() && colMajor) {
         CSCMatrix breezeTransposed = (CSCMatrix)this.asBreeze();
         return (SparseMatrix)Matrices$.MODULE$.fromBreeze(breezeTransposed);
      } else {
         int nnz = this.numNonzeros();
         if (nnz == this.numActives()) {
            return this;
         } else {
            int[] rr = new int[nnz];
            double[] vv = new double[nnz];
            int numPtrs = this.isRowMajor() ? this.numRows() : this.numCols();
            int[] cc = new int[numPtrs + 1];
            int nzIdx = 0;

            int j;
            for(j = 0; j < numPtrs; ++j) {
               int idx = this.colPtrs()[j];
               int idxEnd = this.colPtrs()[j + 1];

               for(cc[j] = nzIdx; idx < idxEnd; ++idx) {
                  if (this.values()[idx] != (double)0.0F) {
                     vv[nzIdx] = this.values()[idx];
                     rr[nzIdx] = this.rowIndices()[idx];
                     ++nzIdx;
                  }
               }
            }

            cc[j] = nnz;
            return new SparseMatrix(this.numRows(), this.numCols(), cc, rr, vv, this.isTransposed());
         }
      }
   }

   public DenseMatrix toDenseMatrix(final boolean colMajor) {
      return colMajor ? new DenseMatrix(this.numRows(), this.numCols(), this.toArray()) : new DenseMatrix(this.numRows(), this.numCols(), this.transpose().toArray(), true);
   }

   public Iterator colIter() {
      if (!this.isTransposed()) {
         return scala.package..MODULE$.Iterator().tabulate(this.numCols(), (jx) -> $anonfun$colIter$6(this, BoxesRunTime.unboxToInt(jx)));
      } else {
         ArrayBuilder[] indicesArray = (ArrayBuilder[])scala.Array..MODULE$.fill(this.numCols(), () -> scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.apply(ArrayBuilder.class));
         ArrayBuilder[] valuesArray = (ArrayBuilder[])scala.Array..MODULE$.fill(this.numCols(), () -> scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(ArrayBuilder.class));

         for(int i = 0; i < this.numRows(); ++i) {
            int k = this.colPtrs()[i];

            for(int rowEnd = this.colPtrs()[i + 1]; k < rowEnd; ++k) {
               int j = this.rowIndices()[k];
               indicesArray[j].$plus$eq(BoxesRunTime.boxToInteger(i));
               valuesArray[j].$plus$eq(BoxesRunTime.boxToDouble(this.values()[k]));
            }
         }

         return scala.package..MODULE$.Iterator().tabulate(this.numCols(), (jx) -> $anonfun$colIter$5(this, indicesArray, valuesArray, BoxesRunTime.unboxToInt(jx)));
      }
   }

   public long getSizeInBytes() {
      return Matrices$.MODULE$.getSparseSize((long)this.numActives(), (long)this.colPtrs().length);
   }

   // $FF: synthetic method
   public static final SparseVector $anonfun$colIter$5(final SparseMatrix $this, final ArrayBuilder[] indicesArray$1, final ArrayBuilder[] valuesArray$1, final int j) {
      int[] ii = (int[])indicesArray$1[j].result();
      double[] vv = (double[])valuesArray$1[j].result();
      return new SparseVector($this.numRows(), ii, vv);
   }

   // $FF: synthetic method
   public static final SparseVector $anonfun$colIter$6(final SparseMatrix $this, final int j) {
      int colStart = $this.colPtrs()[j];
      int colEnd = $this.colPtrs()[j + 1];
      int[] ii = (int[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.intArrayOps($this.rowIndices()), colStart, colEnd);
      double[] vv = (double[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps($this.values()), colStart, colEnd);
      return new SparseVector($this.numRows(), ii, vv);
   }

   public SparseMatrix(final int numRows, final int numCols, final int[] colPtrs, final int[] rowIndices, final double[] values, final boolean isTransposed) {
      this.numRows = numRows;
      this.numCols = numCols;
      this.colPtrs = colPtrs;
      this.rowIndices = rowIndices;
      this.values = values;
      this.isTransposed = isTransposed;
      Matrix.$init$(this);
      scala.Predef..MODULE$.require(values.length == rowIndices.length, () -> {
         int var10000 = this.values().length;
         return "The number of row indices and values don't match! values.length: " + var10000 + ", rowIndices.length: " + this.rowIndices().length;
      });
      if (isTransposed) {
         scala.Predef..MODULE$.require(colPtrs.length == numRows + 1, () -> {
            int var10000 = this.numRows() + 1;
            return "Expecting " + var10000 + " colPtrs when numRows = " + this.numRows() + " but got " + this.colPtrs().length;
         });
      } else {
         scala.Predef..MODULE$.require(colPtrs.length == numCols + 1, () -> {
            int var10000 = this.numCols() + 1;
            return "Expecting " + var10000 + " colPtrs when numCols = " + this.numCols() + " but got " + this.colPtrs().length;
         });
      }

      scala.Predef..MODULE$.require(values.length == BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.intArrayOps(colPtrs))), () -> {
         int var10000 = this.values().length;
         return "The last value of colPtrs must equal the number of elements. values.length: " + var10000 + ", colPtrs.last: " + scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.intArrayOps(this.colPtrs()));
      });
   }

   public SparseMatrix(final int numRows, final int numCols, final int[] colPtrs, final int[] rowIndices, final double[] values) {
      this(numRows, numCols, colPtrs, rowIndices, values, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
