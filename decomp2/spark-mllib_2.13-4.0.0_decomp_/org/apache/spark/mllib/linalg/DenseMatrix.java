package org.apache.spark.mllib.linalg;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.sql.types.SQLUserDefinedType;
import org.sparkproject.guava.base.Objects;
import scala.Function1;
import scala.Function3;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@SQLUserDefinedType(
   udt = MatrixUDT.class
)
@ScalaSignature(
   bytes = "\u0006\u0005\t}c\u0001B\u0014)\u0001MB\u0001B\u0010\u0001\u0003\u0006\u0004%\ta\u0010\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005\u0001\"Aa\n\u0001BC\u0002\u0013\u0005q\b\u0003\u0005Q\u0001\t\u0005\t\u0015!\u0003A\u0011!\u0011\u0006A!b\u0001\n\u0003\u0019\u0006\u0002C.\u0001\u0005\u0003\u0005\u000b\u0011\u0002+\t\u0011u\u0003!Q1A\u0005ByC\u0001\"\u001a\u0001\u0003\u0002\u0003\u0006Ia\u0018\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006O\u0002!\ta\u001d\u0005\u0006q\u0002!\t%\u001f\u0005\u0007\u007f\u0002!\t%!\u0001\t\u0011\u0005\r\u0001\u0001\"\u0001+\u0003\u000bA\u0001\"a\u0005\u0001\t\u0003Q\u0013Q\u0003\u0005\b\u0003'\u0001A\u0011IA\u000e\u0011!\t)\u0003\u0001C\u0001U\u0005\u001d\u0002\u0002CA\u0017\u0001\u0011\u0005!&a\f\t\u000f\u0005}\u0002\u0001\"\u0011\u0002B!A\u0011\u0011\n\u0001\u0005\u00021\nY\u0005\u0003\u0005\u0002.\u0001!\tAKA,\u0011\u001d\tY\u0006\u0001C!\u0003\u0003B\u0001\"a\u0018\u0001\t\u0003b\u0013\u0011\r\u0005\u0007\u0003W\u0002A\u0011I \t\r\u0005M\u0004\u0001\"\u0011@\u0011\u001d\t9\b\u0001C\u0001\u0003sBq!a!\u0001\t\u0003\n)\tC\u0004\u0002,\u0002!\t%!,\b\u000f\u0005m\u0007\u0006#\u0001\u0002^\u001a1q\u0005\u000bE\u0001\u0003?DaaZ\u000f\u0005\u0002\u0005E\bbBAz;\u0011\u0005\u0011Q\u001f\u0005\b\u0003{lB\u0011AA\u0000\u0011\u001d\u00119!\bC\u0001\u0005\u0013AqA!\u0005\u001e\t\u0003\u0011\u0019\u0002C\u0004\u0003,u!\tA!\f\t\u000f\t]R\u0004\"\u0001\u0003:!9!\u0011I\u000f\u0005\u0002\t\r\u0003\"\u0003B&;\u0005\u0005I\u0011\u0002B'\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u000b\u0005%R\u0013A\u00027j]\u0006dwM\u0003\u0002,Y\u0005)Q\u000e\u001c7jE*\u0011QFL\u0001\u0006gB\f'o\u001b\u0006\u0003_A\na!\u00199bG\",'\"A\u0019\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001!$\b\u0005\u00026q5\taGC\u00018\u0003\u0015\u00198-\u00197b\u0013\tIdG\u0001\u0004B]f\u0014VM\u001a\t\u0003wqj\u0011\u0001K\u0005\u0003{!\u0012a!T1ue&D\u0018a\u00028v[J{wo]\u000b\u0002\u0001B\u0011Q'Q\u0005\u0003\u0005Z\u00121!\u00138uQ\r\tAI\u0013\t\u0003\u000b\"k\u0011A\u0012\u0006\u0003\u000f2\n!\"\u00198o_R\fG/[8o\u0013\tIeIA\u0003TS:\u001cW-I\u0001L\u0003\u0015\td\u0006\r\u00181\u0003!qW/\u001c*poN\u0004\u0003f\u0001\u0002E\u0015\u00069a.^7D_2\u001c\bfA\u0002E\u0015\u0006Aa.^7D_2\u001c\b\u0005K\u0002\u0005\t*\u000baA^1mk\u0016\u001cX#\u0001+\u0011\u0007U*v+\u0003\u0002Wm\t)\u0011I\u001d:bsB\u0011Q\u0007W\u0005\u00033Z\u0012a\u0001R8vE2,\u0007fA\u0003E\u0015\u00069a/\u00197vKN\u0004\u0003f\u0001\u0004E\u0015\u0006a\u0011n\u001d+sC:\u001c\bo\\:fIV\tq\f\u0005\u00026A&\u0011\u0011M\u000e\u0002\b\u0005>|G.Z1oQ\r9AiY\u0011\u0002I\u0006)\u0011GL\u001a/a\u0005i\u0011n\u001d+sC:\u001c\bo\\:fI\u0002B3\u0001\u0003#d\u0003\u0019a\u0014N\\5u}Q)\u0011N\u001b7oaB\u00111\b\u0001\u0005\u0006}%\u0001\r\u0001\u0011\u0015\u0004U\u0012S\u0005\"\u0002(\n\u0001\u0004\u0001\u0005f\u00017E\u0015\")!+\u0003a\u0001)\"\u001aa\u000e\u0012&\t\u000buK\u0001\u0019A0)\u0007A$5\rK\u0002\n\t\u000e$B!\u001b;vm\")aH\u0003a\u0001\u0001\")aJ\u0003a\u0001\u0001\")!K\u0003a\u0001)\"\u001a!\u0002\u0012&\u0002\r\u0015\fX/\u00197t)\ty&\u0010C\u0003|\u0017\u0001\u0007A0A\u0001p!\t)T0\u0003\u0002\u007fm\t\u0019\u0011I\\=\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001Q\u0001\tCN\u0014%/Z3{KV\u0011\u0011q\u0001\t\u0006\u0003\u0013\t\tbV\u0007\u0003\u0003\u0017Q1!KA\u0007\u0015\t\ty!\u0001\u0004ce\u0016,'0Z\u0005\u0004{\u0005-\u0011!B1qa2LHcA,\u0002\u0018!1\u0011\u0011\u0004\bA\u0002\u0001\u000b\u0011!\u001b\u000b\u0006/\u0006u\u0011q\u0004\u0005\u0007\u00033y\u0001\u0019\u0001!\t\r\u0005\u0005r\u00021\u0001A\u0003\u0005Q\u0007fA\bEG\u0006)\u0011N\u001c3fqR)\u0001)!\u000b\u0002,!1\u0011\u0011\u0004\tA\u0002\u0001Ca!!\t\u0011\u0001\u0004\u0001\u0015AB;qI\u0006$X\r\u0006\u0005\u00022\u0005]\u0012\u0011HA\u001e!\r)\u00141G\u0005\u0004\u0003k1$\u0001B+oSRDa!!\u0007\u0012\u0001\u0004\u0001\u0005BBA\u0011#\u0001\u0007\u0001\t\u0003\u0004\u0002>E\u0001\raV\u0001\u0002m\u0006!1m\u001c9z+\u0005I\u0007\u0006\u0002\nE\u0003\u000b\n#!a\u0012\u0002\u000bErCG\f\u0019\u0002\u00075\f\u0007\u000fF\u0002j\u0003\u001bBq!a\u0014\u0014\u0001\u0004\t\t&A\u0001g!\u0015)\u00141K,X\u0013\r\t)F\u000e\u0002\n\rVt7\r^5p]F\"2![A-\u0011\u001d\ty\u0005\u0006a\u0001\u0003#\n\u0011\u0002\u001e:b]N\u0004xn]3)\u0007U!5-A\u0007g_J,\u0017m\u00195BGRLg/\u001a\u000b\u0005\u0003c\t\u0019\u0007C\u0004\u0002PY\u0001\r!!\u001a\u0011\u0011U\n9\u0007\u0011!X\u0003cI1!!\u001b7\u0005%1UO\\2uS>t7'A\u0006ok6tuN\u001c>fe>\u001c\b\u0006B\fE\u0003_\n#!!\u001d\u0002\u000bErSG\f\u0019\u0002\u00159,X.Q2uSZ,7\u000f\u000b\u0003\u0019\t\u0006=\u0014\u0001\u0003;p'B\f'o]3\u0016\u0005\u0005m\u0004cA\u001e\u0002~%\u0019\u0011q\u0010\u0015\u0003\u0019M\u0003\u0018M]:f\u001b\u0006$(/\u001b=)\u0007e!5-A\u0004d_2LE/\u001a:\u0016\u0005\u0005\u001d\u0005CBAE\u00033\u000byJ\u0004\u0003\u0002\f\u0006Ue\u0002BAG\u0003'k!!a$\u000b\u0007\u0005E%'\u0001\u0004=e>|GOP\u0005\u0002o%\u0019\u0011q\u0013\u001c\u0002\u000fA\f7m[1hK&!\u00111TAO\u0005!IE/\u001a:bi>\u0014(bAALmA\u00191(!)\n\u0007\u0005\r\u0006F\u0001\u0004WK\u000e$xN\u001d\u0015\u00055\u0011\u000b9+\t\u0002\u0002*\u0006)!G\f\u0019/a\u0005!\u0011m]'M+\t\ty\u000b\u0005\u0003\u00022\u0006eVBAAZ\u0015\rI\u0013Q\u0017\u0006\u0004\u0003oc\u0013AA7m\u0013\r9\u00131\u0017\u0015\u00057\u0011\u000b9\u000bK\u0002\u0001\t*Cs\u0001AAa\u0003#\f\u0019\u000e\u0005\u0003\u0002D\u00065WBAAc\u0015\u0011\t9-!3\u0002\u000bQL\b/Z:\u000b\u0007\u0005-G&A\u0002tc2LA!a4\u0002F\n\u00112+\u0015'Vg\u0016\u0014H)\u001a4j]\u0016$G+\u001f9f\u0003\r)H\r^\u0012\u0003\u0003+\u00042aOAl\u0013\r\tI\u000e\u000b\u0002\n\u001b\u0006$(/\u001b=V\tR\u000b1\u0002R3og\u0016l\u0015\r\u001e:jqB\u00111(H\n\u0005;Q\n\t\u000f\u0005\u0003\u0002d\u00065XBAAs\u0015\u0011\t9/!;\u0002\u0005%|'BAAv\u0003\u0011Q\u0017M^1\n\t\u0005=\u0018Q\u001d\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003;\fQA_3s_N$R![A|\u0003sDQAP\u0010A\u0002\u0001CQAT\u0010A\u0002\u0001C3a\b#d\u0003\u0011yg.Z:\u0015\u000b%\u0014\tAa\u0001\t\u000by\u0002\u0003\u0019\u0001!\t\u000b9\u0003\u0003\u0019\u0001!)\u0007\u0001\"5-A\u0002fs\u0016$2!\u001bB\u0006\u0011\u0019\u0011i!\ta\u0001\u0001\u0006\ta\u000eK\u0002\"\t\u000e\fAA]1oIR9\u0011N!\u0006\u0003\u0018\te\u0001\"\u0002 #\u0001\u0004\u0001\u0005\"\u0002(#\u0001\u0004\u0001\u0005b\u0002B\u000eE\u0001\u0007!QD\u0001\u0004e:<\u0007\u0003\u0002B\u0010\u0005Ki!A!\t\u000b\t\t\r\u0012\u0011^\u0001\u0005kRLG.\u0003\u0003\u0003(\t\u0005\"A\u0002*b]\u0012|W\u000eK\u0002#\t\u000e\fQA]1oI:$r!\u001bB\u0018\u0005c\u0011\u0019\u0004C\u0003?G\u0001\u0007\u0001\tC\u0003OG\u0001\u0007\u0001\tC\u0004\u0003\u001c\r\u0002\rA!\b)\u0007\r\"5-\u0001\u0003eS\u0006<GcA5\u0003<!9!Q\b\u0013A\u0002\u0005}\u0015A\u0002<fGR|'\u000fK\u0002%\t\u000e\faA\u001a:p[6cEcA5\u0003F!9!qI\u0013A\u0002\u0005=\u0016!A7)\t\u0015\"\u0015qU\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u001f\u0002BA!\u0015\u0003X5\u0011!1\u000b\u0006\u0005\u0005+\nI/\u0001\u0003mC:<\u0017\u0002\u0002B-\u0005'\u0012aa\u00142kK\u000e$\bfA\u000fEG\"\u001aA\u0004R2"
)
public class DenseMatrix implements Matrix {
   private final int numRows;
   private final int numCols;
   private final double[] values;
   private final boolean isTransposed;

   public static DenseMatrix fromML(final org.apache.spark.ml.linalg.DenseMatrix m) {
      return DenseMatrix$.MODULE$.fromML(m);
   }

   public static DenseMatrix diag(final Vector vector) {
      return DenseMatrix$.MODULE$.diag(vector);
   }

   public static DenseMatrix randn(final int numRows, final int numCols, final Random rng) {
      return DenseMatrix$.MODULE$.randn(numRows, numCols, rng);
   }

   public static DenseMatrix rand(final int numRows, final int numCols, final Random rng) {
      return DenseMatrix$.MODULE$.rand(numRows, numCols, rng);
   }

   public static DenseMatrix eye(final int n) {
      return DenseMatrix$.MODULE$.eye(n);
   }

   public static DenseMatrix ones(final int numRows, final int numCols) {
      return DenseMatrix$.MODULE$.ones(numRows, numCols);
   }

   public static DenseMatrix zeros(final int numRows, final int numCols) {
      return DenseMatrix$.MODULE$.zeros(numRows, numCols);
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

   public void org$apache$spark$mllib$linalg$Matrix$_setter_$isTransposed_$eq(final boolean x$1) {
   }

   public int numRows() {
      return this.numRows;
   }

   public int numCols() {
      return this.numCols;
   }

   public double[] values() {
      return this.values;
   }

   public boolean isTransposed() {
      return this.isTransposed;
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

   public int hashCode() {
      return Objects.hashCode(new Object[]{.MODULE$.int2Integer(this.numRows()), .MODULE$.int2Integer(this.numCols()), this.toArray()});
   }

   public breeze.linalg.Matrix asBreeze() {
      if (!this.isTransposed()) {
         return new breeze.linalg.DenseMatrix.mcD.sp(this.numRows(), this.numCols(), this.values());
      } else {
         breeze.linalg.DenseMatrix breezeMatrix = new breeze.linalg.DenseMatrix.mcD.sp(this.numCols(), this.numRows(), this.values());
         return (breeze.linalg.Matrix)breezeMatrix.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM());
      }
   }

   public double apply(final int i) {
      return this.values()[i];
   }

   public double apply(final int i, final int j) {
      return this.values()[this.index(i, j)];
   }

   public int index(final int i, final int j) {
      .MODULE$.require(i >= 0 && i < this.numRows(), () -> {
         int var10000 = this.numRows();
         return "Expected 0 <= i < " + var10000 + ", got i = " + i + ".";
      });
      .MODULE$.require(j >= 0 && j < this.numCols(), () -> {
         int var10000 = this.numCols();
         return "Expected 0 <= j < " + var10000 + ", got j = " + j + ".";
      });
      return !this.isTransposed() ? i + this.numRows() * j : j + this.numCols() * i;
   }

   public void update(final int i, final int j, final double v) {
      this.values()[this.index(i, j)] = v;
   }

   public DenseMatrix copy() {
      return new DenseMatrix(this.numRows(), this.numCols(), (double[])this.values().clone());
   }

   public DenseMatrix map(final Function1 f) {
      return new DenseMatrix(this.numRows(), this.numCols(), (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.doubleArrayOps(this.values()), f, scala.reflect.ClassTag..MODULE$.Double()), this.isTransposed());
   }

   public DenseMatrix update(final Function1 f) {
      int len = this.values().length;

      for(int i = 0; i < len; ++i) {
         this.values()[i] = f.apply$mcDD$sp(this.values()[i]);
      }

      return this;
   }

   public DenseMatrix transpose() {
      return new DenseMatrix(this.numCols(), this.numRows(), this.values(), !this.isTransposed());
   }

   public void foreachActive(final Function3 f) {
      if (!this.isTransposed()) {
         for(int j = 0; j < this.numCols(); ++j) {
            int i = 0;

            for(int indStart = j * this.numRows(); i < this.numRows(); ++i) {
               f.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(this.values()[indStart + i]));
            }
         }

      } else {
         for(int i = 0; i < this.numRows(); ++i) {
            int j = 0;

            for(int indStart = i * this.numCols(); j < this.numCols(); ++j) {
               f.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(this.values()[indStart + j]));
            }
         }

      }
   }

   public int numNonzeros() {
      return scala.collection.ArrayOps..MODULE$.count$extension(.MODULE$.doubleArrayOps(this.values()), (JFunction1.mcZD.sp)(x$1) -> x$1 != (double)0);
   }

   public int numActives() {
      return this.values().length;
   }

   public SparseMatrix toSparse() {
      ArrayBuilder spVals = new ArrayBuilder.ofDouble();
      int[] colPtrs = new int[this.numCols() + 1];
      ArrayBuilder rowIndices = new ArrayBuilder.ofInt();
      int nnz = 0;

      for(int j = 0; j < this.numCols(); colPtrs[j] = nnz) {
         for(int i = 0; i < this.numRows(); ++i) {
            double v = this.values()[this.index(i, j)];
            if (v != (double)0.0F) {
               rowIndices.$plus$eq(BoxesRunTime.boxToInteger(i));
               spVals.$plus$eq(BoxesRunTime.boxToDouble(v));
               ++nnz;
            }
         }

         ++j;
      }

      return new SparseMatrix(this.numRows(), this.numCols(), colPtrs, (int[])rowIndices.result(), (double[])spVals.result());
   }

   public Iterator colIter() {
      return this.isTransposed() ? scala.package..MODULE$.Iterator().tabulate(this.numCols(), (j) -> $anonfun$colIter$1(this, BoxesRunTime.unboxToInt(j))) : scala.package..MODULE$.Iterator().tabulate(this.numCols(), (j) -> $anonfun$colIter$2(this, BoxesRunTime.unboxToInt(j)));
   }

   public org.apache.spark.ml.linalg.DenseMatrix asML() {
      return new org.apache.spark.ml.linalg.DenseMatrix(this.numRows(), this.numCols(), this.values(), this.isTransposed());
   }

   // $FF: synthetic method
   public static final DenseVector $anonfun$colIter$1(final DenseMatrix $this, final int j) {
      double[] col = new double[$this.numRows()];
      BLAS$.MODULE$.nativeBLAS().dcopy($this.numRows(), $this.values(), j, $this.numCols(), col, 0, 1);
      return new DenseVector(col);
   }

   // $FF: synthetic method
   public static final DenseVector $anonfun$colIter$2(final DenseMatrix $this, final int j) {
      return new DenseVector((double[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.doubleArrayOps($this.values()), j * $this.numRows(), (j + 1) * $this.numRows()));
   }

   public DenseMatrix(final int numRows, final int numCols, final double[] values, final boolean isTransposed) {
      this.numRows = numRows;
      this.numCols = numCols;
      this.values = values;
      this.isTransposed = isTransposed;
      Matrix.$init$(this);
      .MODULE$.require(values.length == numRows * numCols, () -> {
         int var10000 = this.values().length;
         return "The number of values supplied doesn't match the size of the matrix! values.length: " + var10000 + ", numRows * numCols: " + this.numRows() * this.numCols();
      });
   }

   public DenseMatrix(final int numRows, final int numCols, final double[] values) {
      this(numRows, numCols, values, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
