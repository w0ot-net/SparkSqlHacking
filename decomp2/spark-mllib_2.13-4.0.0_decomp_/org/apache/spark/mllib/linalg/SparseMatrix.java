package org.apache.spark.mllib.linalg;

import breeze.linalg.CSCMatrix;
import breeze.storage.Zero.;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import org.apache.spark.sql.types.SQLUserDefinedType;
import scala.Function1;
import scala.Function3;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@SQLUserDefinedType(
   udt = MatrixUDT.class
)
@ScalaSignature(
   bytes = "\u0006\u0005\tee\u0001\u0002\u0016,\u0001YB\u0001\"\u0011\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005\u0007\"A\u0011\u000b\u0001BC\u0002\u0013\u0005!\t\u0003\u0005T\u0001\t\u0005\t\u0015!\u0003D\u0011!)\u0006A!b\u0001\n\u00031\u0006\u0002C.\u0001\u0005\u0003\u0005\u000b\u0011B,\t\u0011u\u0003!Q1A\u0005\u0002YC\u0001b\u0018\u0001\u0003\u0002\u0003\u0006Ia\u0016\u0005\tC\u0002\u0011)\u0019!C\u0001E\"A\u0001\u000e\u0001B\u0001B\u0003%1\r\u0003\u0005k\u0001\t\u0015\r\u0011\"\u0011l\u0011!\u0011\bA!A!\u0002\u0013a\u0007\"\u0002;\u0001\t\u0003)\bB\u0002;\u0001\t\u0003\tI\u0001C\u0004\u0002\u0018\u0001!\t%!\u0007\t\u000f\u0005\u0015\u0002\u0001\"\u0011\u0002(!A\u0011\u0011\u0006\u0001\u0005\u00025\nY\u0003C\u0004\u0002:\u0001!\t%a\u000f\t\u0011\u0005\u001d\u0003\u0001\"\u0001.\u0003\u0013B\u0001\"a\u0014\u0001\t\u0003i\u0013\u0011\u000b\u0005\b\u0003C\u0002A\u0011IA2\u0011!\tY\u0007\u0001C\u0001_\u00055\u0004\u0002CA(\u0001\u0011\u0005Q&!\u001f\t\u000f\u0005u\u0004\u0001\"\u0011\u0002d!A\u0011\u0011\u0011\u0001\u0005B=\n\u0019\tC\u0004\u0002\u000e\u0002!\t!a$\t\r\u0005e\u0005\u0001\"\u0011C\u0011\u0019\t\t\u000b\u0001C!\u0005\"9\u0011Q\u0015\u0001\u0005B\u0005\u001d\u0006bBAg\u0001\u0011\u0005\u0013qZ\u0004\b\u0003{\\\u0003\u0012AA\u0000\r\u0019Q3\u0006#\u0001\u0003\u0002!1A\u000f\tC\u0001\u0005'AqA!\u0006!\t\u0003\u00119\u0002C\u0004\u00030\u0001\"\tA!\r\t\u000f\te\u0002\u0005\"\u0003\u0003<!9!Q\u000b\u0011\u0005\u0002\t]\u0003b\u0002B2A\u0011\u0005!Q\r\u0005\b\u0005c\u0002C\u0011\u0001B:\u0011\u001d\u0011Y\b\tC\u0001\u0005{B\u0011B!\"!\u0003\u0003%IAa\"\u0003\u0019M\u0003\u0018M]:f\u001b\u0006$(/\u001b=\u000b\u00051j\u0013A\u00027j]\u0006dwM\u0003\u0002/_\u0005)Q\u000e\u001c7jE*\u0011\u0001'M\u0001\u0006gB\f'o\u001b\u0006\u0003eM\na!\u00199bG\",'\"\u0001\u001b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00019T\b\u0005\u00029w5\t\u0011HC\u0001;\u0003\u0015\u00198-\u00197b\u0013\ta\u0014H\u0001\u0004B]f\u0014VM\u001a\t\u0003}}j\u0011aK\u0005\u0003\u0001.\u0012a!T1ue&D\u0018a\u00028v[J{wo]\u000b\u0002\u0007B\u0011\u0001\bR\u0005\u0003\u000bf\u00121!\u00138uQ\r\tq)\u0014\t\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015>\n!\"\u00198o_R\fG/[8o\u0013\ta\u0015JA\u0003TS:\u001cW-I\u0001O\u0003\u0015\tdF\r\u00181\u0003!qW/\u001c*poN\u0004\u0003f\u0001\u0002H\u001b\u00069a.^7D_2\u001c\bfA\u0002H\u001b\u0006Aa.^7D_2\u001c\b\u0005K\u0002\u0005\u000f6\u000bqaY8m!R\u00148/F\u0001X!\rA\u0004lQ\u0005\u00033f\u0012Q!\u0011:sCfD3!B$N\u0003!\u0019w\u000e\u001c)ueN\u0004\u0003f\u0001\u0004H\u001b\u0006Q!o\\<J]\u0012L7-Z:)\u0007\u001d9U*A\u0006s_^Le\u000eZ5dKN\u0004\u0003f\u0001\u0005H\u001b\u00061a/\u00197vKN,\u0012a\u0019\t\u0004qa#\u0007C\u0001\u001df\u0013\t1\u0017H\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0013\u001dk\u0015a\u0002<bYV,7\u000f\t\u0015\u0004\u0015\u001dk\u0015\u0001D5t)J\fgn\u001d9pg\u0016$W#\u00017\u0011\u0005aj\u0017B\u00018:\u0005\u001d\u0011un\u001c7fC:D3aC$qC\u0005\t\u0018!B\u0019/g9\u0002\u0014!D5t)J\fgn\u001d9pg\u0016$\u0007\u0005K\u0002\r\u000fB\fa\u0001P5oSRtD\u0003\u0003<xsnlx0a\u0001\u0011\u0005y\u0002\u0001\"B!\u000e\u0001\u0004\u0019\u0005fA<H\u001b\")\u0011+\u0004a\u0001\u0007\"\u001a\u0011pR'\t\u000bUk\u0001\u0019A,)\u0007m<U\nC\u0003^\u001b\u0001\u0007q\u000bK\u0002~\u000f6CQ!Y\u0007A\u0002\rD3a`$N\u0011\u0015QW\u00021\u0001mQ\u0011\t\u0019a\u00129)\u000759\u0005\u000fF\u0006w\u0003\u0017\ti!a\u0004\u0002\u0012\u0005M\u0001\"B!\u000f\u0001\u0004\u0019\u0005\"B)\u000f\u0001\u0004\u0019\u0005\"B+\u000f\u0001\u00049\u0006\"B/\u000f\u0001\u00049\u0006\"B1\u000f\u0001\u0004\u0019\u0007f\u0001\bH\u001b\u00061Q-];bYN$2\u0001\\A\u000e\u0011\u001d\tib\u0004a\u0001\u0003?\t\u0011a\u001c\t\u0004q\u0005\u0005\u0012bAA\u0012s\t\u0019\u0011I\\=\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aQ\u0001\tCN\u0014%/Z3{KV\u0011\u0011Q\u0006\t\u0006\u0003_\t9\u0004Z\u0007\u0003\u0003cQ1\u0001LA\u001a\u0015\t\t)$\u0001\u0004ce\u0016,'0Z\u0005\u0004\u0001\u0006E\u0012!B1qa2LH#\u00023\u0002>\u0005\u0005\u0003BBA %\u0001\u00071)A\u0001j\u0011\u0019\t\u0019E\u0005a\u0001\u0007\u0006\t!\u000eK\u0002\u0013\u000fB\fQ!\u001b8eKb$RaQA&\u0003\u001bBa!a\u0010\u0014\u0001\u0004\u0019\u0005BBA\"'\u0001\u00071)\u0001\u0004va\u0012\fG/\u001a\u000b\t\u0003'\nI&a\u0017\u0002^A\u0019\u0001(!\u0016\n\u0007\u0005]\u0013H\u0001\u0003V]&$\bBBA )\u0001\u00071\t\u0003\u0004\u0002DQ\u0001\ra\u0011\u0005\u0007\u0003?\"\u0002\u0019\u00013\u0002\u0003Y\fAaY8qsV\ta\u000f\u000b\u0003\u0016\u000f\u0006\u001d\u0014EAA5\u0003\u0015\td\u0006\u000e\u00181\u0003\ri\u0017\r\u001d\u000b\u0004m\u0006=\u0004bBA9-\u0001\u0007\u00111O\u0001\u0002MB)\u0001(!\u001eeI&\u0019\u0011qO\u001d\u0003\u0013\u0019+hn\u0019;j_:\fDc\u0001<\u0002|!9\u0011\u0011O\fA\u0002\u0005M\u0014!\u0003;sC:\u001c\bo\\:fQ\rAr\t]\u0001\u000eM>\u0014X-Y2i\u0003\u000e$\u0018N^3\u0015\t\u0005M\u0013Q\u0011\u0005\b\u0003cJ\u0002\u0019AAD!!A\u0014\u0011R\"DI\u0006M\u0013bAAFs\tIa)\u001e8di&|gnM\u0001\bi>$UM\\:f+\t\t\t\nE\u0002?\u0003'K1!!&,\u0005-!UM\\:f\u001b\u0006$(/\u001b=)\u0007i9\u0005/A\u0006ok6tuN\u001c>fe>\u001c\b\u0006B\u000eH\u0003;\u000b#!a(\u0002\u000bErSG\f\u0019\u0002\u00159,X.Q2uSZ,7\u000f\u000b\u0003\u001d\u000f\u0006u\u0015aB2pY&#XM]\u000b\u0003\u0003S\u0003b!a+\u0002<\u0006\u0005g\u0002BAW\u0003osA!a,\u000266\u0011\u0011\u0011\u0017\u0006\u0004\u0003g+\u0014A\u0002\u001fs_>$h(C\u0001;\u0013\r\tI,O\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ti,a0\u0003\u0011%#XM]1u_JT1!!/:!\rq\u00141Y\u0005\u0004\u0003\u000b\\#A\u0002,fGR|'\u000f\u000b\u0003\u001e\u000f\u0006%\u0017EAAf\u0003\u0015\u0011d\u0006\r\u00181\u0003\u0011\t7/\u0014'\u0016\u0005\u0005E\u0007\u0003BAj\u00037l!!!6\u000b\u00071\n9NC\u0002\u0002Z>\n!!\u001c7\n\u0007)\n)\u000e\u000b\u0003\u001f\u000f\u0006%\u0007f\u0001\u0001H\u001b\":\u0001!a9\u0002t\u0006U\b\u0003BAs\u0003_l!!a:\u000b\t\u0005%\u00181^\u0001\u0006if\u0004Xm\u001d\u0006\u0004\u0003[|\u0013aA:rY&!\u0011\u0011_At\u0005I\u0019\u0016\u000bT+tKJ$UMZ5oK\u0012$\u0016\u0010]3\u0002\u0007U$Go\t\u0002\u0002xB\u0019a(!?\n\u0007\u0005m8FA\u0005NCR\u0014\u0018\u000e_+E)\u0006a1\u000b]1sg\u0016l\u0015\r\u001e:jqB\u0011a\bI\n\u0005A]\u0012\u0019\u0001\u0005\u0003\u0003\u0006\t=QB\u0001B\u0004\u0015\u0011\u0011IAa\u0003\u0002\u0005%|'B\u0001B\u0007\u0003\u0011Q\u0017M^1\n\t\tE!q\u0001\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003\u007f\fqA\u001a:p[\u000e{u\nF\u0004w\u00053\u0011YB!\b\t\u000b\u0005\u0013\u0003\u0019A\"\t\u000bE\u0013\u0003\u0019A\"\t\u000f\t}!\u00051\u0001\u0003\"\u00059QM\u001c;sS\u0016\u001c\bCBAV\u0005G\u00119#\u0003\u0003\u0003&\u0005}&\u0001C%uKJ\f'\r\\3\u0011\ra\u0012IcQ\"e\u0013\r\u0011Y#\u000f\u0002\u0007)V\u0004H.Z\u001a)\u0007\t:\u0005/A\u0003ta\u0016LX\rF\u0002w\u0005gAaA!\u000e$\u0001\u0004\u0019\u0015!\u00018)\u0007\r:\u0005/A\u0007hK:\u0014\u0016M\u001c3NCR\u0014\u0018\u000e\u001f\u000b\nm\nu\"q\bB!\u0005\u000bBQ!\u0011\u0013A\u0002\rCQ!\u0015\u0013A\u0002\rCaAa\u0011%\u0001\u0004!\u0017a\u00023f]NLG/\u001f\u0005\b\u0005\u000f\"\u0003\u0019\u0001B%\u0003\r\u0011hn\u001a\t\u0005\u0005\u0017\u0012\t&\u0004\u0002\u0003N)!!q\nB\u0006\u0003\u0011)H/\u001b7\n\t\tM#Q\n\u0002\u0007%\u0006tGm\\7\u0002\rM\u0004(/\u00198e)%1(\u0011\fB.\u0005;\u0012y\u0006C\u0003BK\u0001\u00071\tC\u0003RK\u0001\u00071\t\u0003\u0004\u0003D\u0015\u0002\r\u0001\u001a\u0005\b\u0005\u000f*\u0003\u0019\u0001B%Q\r)s\t]\u0001\bgB\u0014\u0018M\u001c3o)%1(q\rB5\u0005W\u0012i\u0007C\u0003BM\u0001\u00071\tC\u0003RM\u0001\u00071\t\u0003\u0004\u0003D\u0019\u0002\r\u0001\u001a\u0005\b\u0005\u000f2\u0003\u0019\u0001B%Q\r1s\t]\u0001\u0007gB$\u0017.Y4\u0015\u0007Y\u0014)\bC\u0004\u0003x\u001d\u0002\r!!1\u0002\rY,7\r^8sQ\r9s\t]\u0001\u0007MJ|W.\u0014'\u0015\u0007Y\u0014y\bC\u0004\u0003\u0002\"\u0002\r!!5\u0002\u00035DC\u0001K$\u0002J\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\u0012\t\u0005\u0005\u0017\u0013\t*\u0004\u0002\u0003\u000e*!!q\u0012B\u0006\u0003\u0011a\u0017M\\4\n\t\tM%Q\u0012\u0002\u0007\u001f\nTWm\u0019;)\u0007\u0001:\u0005\u000fK\u0002 \u000fB\u0004"
)
public class SparseMatrix implements Matrix {
   private final int numRows;
   private final int numCols;
   private final int[] colPtrs;
   private final int[] rowIndices;
   private final double[] values;
   private final boolean isTransposed;

   public static SparseMatrix fromML(final org.apache.spark.ml.linalg.SparseMatrix m) {
      return SparseMatrix$.MODULE$.fromML(m);
   }

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
      return this.asBreeze().hashCode();
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

   public DenseMatrix toDense() {
      return new DenseMatrix(this.numRows(), this.numCols(), this.toArray());
   }

   public int numNonzeros() {
      return scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.doubleArrayOps(this.values()), (JFunction1.mcZD.sp)(x$2) -> x$2 != (double)0);
   }

   public int numActives() {
      return this.values().length;
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

   public org.apache.spark.ml.linalg.SparseMatrix asML() {
      return new org.apache.spark.ml.linalg.SparseMatrix(this.numRows(), this.numCols(), this.colPtrs(), this.rowIndices(), this.values(), this.isTransposed());
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
