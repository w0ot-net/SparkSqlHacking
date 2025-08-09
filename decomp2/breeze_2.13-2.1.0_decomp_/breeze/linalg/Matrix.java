package breeze.linalg;

import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import breeze.util.Terminal$;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function2;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.StringBuilder;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tmea\u0002\u0011\"!\u0003\r\tA\n\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006A\u0002!)!\u0019\u0005\u0006U\u0002!)a\u001b\u0005\u0006A\u00021\ta\u001c\u0005\u0006U\u00021\ta\u001d\u0005\u0006o\u0002!\t\u0001\u001f\u0005\u0006s\u00021\t\u0001\u001f\u0005\u0006u\u00021\t\u0001\u001f\u0005\u0006w\u0002!\t\u0001 \u0005\b\u0003\u0017\u0001A\u0011AA\u0007\u0011\u001d\ti\u0002\u0001C\u0001\u0003?Aq!a\t\u0001\t\u0003\t)\u0003C\u0004\u0002*\u0001!\t!a\u000b\t\u0013\u0005m\u0002!%A\u0005\u0002\u0005u\u0002\"CA*\u0001E\u0005I\u0011AA\u001f\u0011\u001d\tI\u0003\u0001C!\u0003+Bq!a\u0016\u0001\t\u0003\tI\u0006C\u0004\u0002\u0002\u00021\t!a!\t\u000f\u0005\u0015\u0005A\"\u0001\u0002\b\"I\u0011\u0011\u0014\u0001\u0012\u0002\u0013\u0005\u00111\u0014\u0005\b\u0003?\u0003A\u0011IAQ\u000f\u001d\ti+\tE\u0001\u0003_3a\u0001I\u0011\t\u0002\u0005E\u0006bBAa/\u0011\u0005\u00111\u0019\u0005\b\u0003\u000b<B\u0011AAd\u0011\u001d\t)p\u0006C\u0001\u0003oD\u0001B!\u000b\u0018\t\u0003\t#1\u0006\u0005\t\u0005\u007f9B\u0011A\u0011\u0003B!A!QK\f\u0005\u0002\u0005\u00129\u0006C\u0004\u0003r]!\u0019Aa\u001d\t\u000f\t%u\u0003b\u0001\u0003\f\n1Q*\u0019;sSbT!AI\u0012\u0002\r1Lg.\u00197h\u0015\u0005!\u0013A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005\u001d\"4c\u0001\u0001)]A\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t1\u0011I\\=SK\u001a\u0004Ba\f\u0019356\t\u0011%\u0003\u00022C\tQQ*\u0019;sSbd\u0015n[3\u0011\u0005M\"D\u0002\u0001\u0003\nk\u0001\u0001\u000b\u0011!AC\u0002Y\u0012\u0011AV\t\u0003oi\u0002\"!\u000b\u001d\n\u0005eR#a\u0002(pi\"Lgn\u001a\t\u0003SmJ!\u0001\u0010\u0016\u0003\u0007\u0005s\u0017\u0010\u000b\u00045}\u0005[\u0005+\u0016\t\u0003S}J!\u0001\u0011\u0016\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\t\u001bU\t\u0012\b\u0003S\rK!\u0001\u0012\u0016\u0002\r\u0011{WO\u00197fc\u0011!cIS\u0016\u000f\u0005\u001dSU\"\u0001%\u000b\u0005%+\u0013A\u0002\u001fs_>$h(C\u0001,c\u0015\u0019C*T(O\u001d\tIS*\u0003\u0002OU\u0005\u0019\u0011J\u001c;2\t\u00112%jK\u0019\u0006GE\u0013Fk\u0015\b\u0003SIK!a\u0015\u0016\u0002\u000b\u0019cw.\u0019;2\t\u00112%jK\u0019\u0006GY;\u0016\f\u0017\b\u0003S]K!\u0001\u0017\u0016\u0002\t1{gnZ\u0019\u0005I\u0019S5\u0006E\u00020\u0001I\na\u0001J5oSR$C#A/\u0011\u0005%r\u0016BA0+\u0005\u0011)f.\u001b;\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005I\u0012\u0007\"B2\u0003\u0001\u0004!\u0017!A5\u0011\t%*wmZ\u0005\u0003M*\u0012a\u0001V;qY\u0016\u0014\u0004CA\u0015i\u0013\tI'FA\u0002J]R\fa!\u001e9eCR,GcA/m[\")1m\u0001a\u0001I\")an\u0001a\u0001e\u0005\tQ\rF\u00023aFDQa\u0019\u0003A\u0002\u001dDQA\u001d\u0003A\u0002\u001d\f\u0011A\u001b\u000b\u0005;R,h\u000fC\u0003d\u000b\u0001\u0007q\rC\u0003s\u000b\u0001\u0007q\rC\u0003o\u000b\u0001\u0007!'\u0001\u0003tSj,W#A4\u0002\tI|wo]\u0001\u0005G>d7/\u0001\u0004lKf\u001cV\r^\u000b\u0002{B!a0!\u0002e\u001d\ry\u0018\u0011\u0001\t\u0003\u000f*J1!a\u0001+\u0003\u0019\u0001&/\u001a3fM&!\u0011qAA\u0005\u0005\r\u0019V\r\u001e\u0006\u0004\u0003\u0007Q\u0013\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005=\u0001CBA\t\u0003/\tY\"\u0004\u0002\u0002\u0014)\u0019\u0011Q\u0003\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u001a\u0005M!\u0001C%uKJ\fGo\u001c:\u0011\t%*GMM\u0001\u000fm\u0006dW/Z:Ji\u0016\u0014\u0018\r^8s+\t\t\t\u0003E\u0003\u0002\u0012\u0005]!'\u0001\u0007lKf\u001c\u0018\n^3sCR|'/\u0006\u0002\u0002(A)\u0011\u0011CA\fI\u0006AAo\\*ue&tw\r\u0006\u0004\u0002.\u0005M\u0012q\u0007\t\u0004}\u0006=\u0012\u0002BA\u0019\u0003\u0013\u0011aa\u0015;sS:<\u0007\u0002CA\u001b\u001bA\u0005\t\u0019A4\u0002\u00115\f\u0007\u0010T5oKND\u0001\"!\u000f\u000e!\u0003\u0005\raZ\u0001\t[\u0006Dx+\u001b3uQ\u0006\u0011Bo\\*ue&tw\r\n3fM\u0006,H\u000e\u001e\u00132+\t\tyDK\u0002h\u0003\u0003Z#!a\u0011\u0011\t\u0005\u0015\u0013qJ\u0007\u0003\u0003\u000fRA!!\u0013\u0002L\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u001bR\u0013AC1o]>$\u0018\r^5p]&!\u0011\u0011KA$\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0013i>\u001cFO]5oO\u0012\"WMZ1vYR$#\u0007\u0006\u0002\u0002.\u0005iAo\u001c#f]N,W*\u0019;sSb$b!a\u0017\u0002b\u0005E\u0004\u0003B\u0018\u0002^IJ1!a\u0018\"\u0005-!UM\\:f\u001b\u0006$(/\u001b=\t\u000f\u0005\r\u0014\u0003q\u0001\u0002f\u0005\u00111-\u001c\t\u0006\u0003O\niGM\u0007\u0003\u0003SR1!a\u001b+\u0003\u001d\u0011XM\u001a7fGRLA!a\u001c\u0002j\tA1\t\\1tgR\u000bw\rC\u0004\u0002tE\u0001\u001d!!\u001e\u0002\ti,'o\u001c\t\u0006\u0003o\niHM\u0007\u0003\u0003sR1!a\u001f$\u0003\u001d\u0019Ho\u001c:bO\u0016LA!a \u0002z\t!!,\u001a:p\u0003\u0011\u0019w\u000e]=\u0016\u0003i\u000bqA\u001a7biR,g\u000e\u0006\u0003\u0002\n\u0006=\u0005\u0003B\u0018\u0002\fJJ1!!$\"\u0005\u00191Vm\u0019;pe\"I\u0011\u0011S\n\u0011\u0002\u0003\u0007\u00111S\u0001\u0005m&,w\u000fE\u00020\u0003+K1!a&\"\u0005\u00111\u0016.Z<\u0002#\u0019d\u0017\r\u001e;f]\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u001e*\"\u00111SA!\u0003\u0019)\u0017/^1mgR!\u00111UAU!\rI\u0013QU\u0005\u0004\u0003OS#a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u0003W+\u0002\u0019\u0001\u001e\u0002\u0005A\f\u0014AB'biJL\u0007\u0010\u0005\u00020/M1q\u0003KAZ\u0003w\u0003RaLA[\u0003sK1!a.\"\u0005Ii\u0015\r\u001e:jq\u000e{gn\u001d;sk\u000e$xN]:\u0011\u0005=\u0002\u0001cA\u0018\u0002>&\u0019\u0011qX\u0011\u0003#1{w\u000f\u0015:j_JLG/_'biJL\u00070\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003_\u000bQA_3s_N,B!!3\u0002RR1\u00111ZAy\u0003g$b!!4\u0002f\u0006-\b\u0003B\u0018\u0001\u0003\u001f\u00042aMAi\t%)\u0014\u0004)A\u0001\u0002\u000b\u0007a\u0007K\u0006\u0002Rz\n).!7\u0002^\u0006\u0005\u0018GB\u0012C\u0007\u0006]G)\r\u0003%\r*[\u0013GB\u0012M\u001b\u0006mg*\r\u0003%\r*[\u0013GB\u0012R%\u0006}7+\r\u0003%\r*[\u0013GB\u0012W/\u0006\r\b,\r\u0003%\r*[\u0003\"CAt3\u0005\u0005\t9AAu\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003O\ni'a4\t\u0013\u00055\u0018$!AA\u0004\u0005=\u0018AC3wS\u0012,gnY3%eA1\u0011qOA?\u0003\u001fDQ!_\rA\u0002\u001dDQA_\rA\u0002\u001d\faa\u0019:fCR,W\u0003BA}\u0005\u0003!\u0002\"a?\u0003\u001c\tu!q\u0004\u000b\u0005\u0003{\u0014)\u0002\u0005\u00030\u0001\u0005}\bcA\u001a\u0003\u0002\u0011IQG\u0007Q\u0001\u0002\u0003\u0015\rA\u000e\u0015\f\u0005\u0003q$Q\u0001B\u0005\u0005\u001b\u0011\t\"\r\u0004$\u0005\u000e\u00139\u0001R\u0019\u0005I\u0019S5&\r\u0004$\u00196\u0013YAT\u0019\u0005I\u0019S5&\r\u0004$#J\u0013yaU\u0019\u0005I\u0019S5&\r\u0004$-^\u0013\u0019\u0002W\u0019\u0005I\u0019S5\u0006C\u0005\u0003\u0018i\t\t\u0011q\u0001\u0003\u001a\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\r\u0005]\u0014QPA\u0000\u0011\u0015I(\u00041\u0001h\u0011\u0015Q(\u00041\u0001h\u0011\u001d\u0011\tC\u0007a\u0001\u0005G\tA\u0001Z1uCB)\u0011F!\n\u0002\u0000&\u0019!q\u0005\u0016\u0003\u000b\u0005\u0013(/Y=\u0002\u0011i,'o\u001c*poN,BA!\f\u00036Q!!q\u0006B\u001f)\u0011\u0011\tDa\u000e\u0011\t=\u0002!1\u0007\t\u0004g\tUB!B\u001b\u001c\u0005\u00041\u0004\"\u0003B\u001d7\u0005\u0005\t9\u0001B\u001e\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\u0003O\niGa\r\t\u000bi\\\u0002\u0019A4\u0002\u0011i,'o\\\"pYN,BAa\u0011\u0003LQ!!Q\tB*)\u0011\u00119E!\u0014\u0011\t=\u0002!\u0011\n\t\u0004g\t-C!B\u001b\u001d\u0005\u00041\u0004\"\u0003B(9\u0005\u0005\t9\u0001B)\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0007\u0003O\niG!\u0013\t\u000bed\u0002\u0019A4\u0002\u0017\u0015l\u0007\u000f^=NCR\u0014\u0018\u000e_\u000b\u0005\u00053\u0012\t\u0007\u0006\u0004\u0003\\\t%$Q\u000e\u000b\u0005\u0005;\u0012\u0019\u0007\u0005\u00030\u0001\t}\u0003cA\u001a\u0003b\u0011)Q'\bb\u0001m!I!QM\u000f\u0002\u0002\u0003\u000f!qM\u0001\u000bKZLG-\u001a8dK\u00122\u0004CBA4\u0003[\u0012y\u0006\u0003\u0004\u0003lu\u0001\raZ\u0001\u0006?J|wo\u001d\u0005\u0007\u0005_j\u0002\u0019A4\u0002\u000b}\u001bw\u000e\\:\u00021\r\fg\u000e\u0016:bm\u0016\u00148/Z&fsZ\u000bG.^3QC&\u00148/\u0006\u0003\u0003v\t\u001dUC\u0001B<!%\u0011IHa \u0003\u0004\u0012\u0014))\u0004\u0002\u0003|)\u0019!QP\u0011\u0002\u000fM,\b\u000f]8si&!!\u0011\u0011B>\u0005a\u0019\u0015M\u001c+sCZ,'o]3LKf4\u0016\r\\;f!\u0006L'o\u001d\t\u0005_\u0001\u0011)\tE\u00024\u0005\u000f#Q!\u000e\u0010C\u0002Y\n\u0011cY1o)J\fg/\u001a:tKZ\u000bG.^3t+\u0011\u0011iI!'\u0016\u0005\t=\u0005\u0003\u0003B=\u0005#\u0013)Ja&\n\t\tM%1\u0010\u0002\u0012\u0007\u0006tGK]1wKJ\u001cXMV1mk\u0016\u001c\b\u0003B\u0018\u0001\u0005/\u00032a\rBM\t\u0015)tD1\u00017\u0001"
)
public interface Matrix extends MatrixLike {
   static CanTraverseValues canTraverseValues() {
      return Matrix$.MODULE$.canTraverseValues();
   }

   static CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return Matrix$.MODULE$.canTraverseKeyValuePairs();
   }

   static Matrix create(final int rows, final int cols, final Object data, final Zero evidence$3) {
      return Matrix$.MODULE$.create(rows, cols, data, evidence$3);
   }

   static Matrix zeros(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      return Matrix$.MODULE$.zeros(rows, cols, evidence$1, evidence$2);
   }

   static CanSlice2 canSliceWeirdRowsAndTensorBooleanCols(final Semiring evidence$33, final ClassTag evidence$34) {
      return Matrix$.MODULE$.canSliceWeirdRowsAndTensorBooleanCols(evidence$33, evidence$34);
   }

   static CanSlice2 canSliceTensorBooleanRowsAndWeirdCols(final Semiring evidence$31, final ClassTag evidence$32) {
      return Matrix$.MODULE$.canSliceTensorBooleanRowsAndWeirdCols(evidence$31, evidence$32);
   }

   static CanSlice2 canSliceTensorBooleanRowsAndCols(final Semiring evidence$29, final ClassTag evidence$30) {
      return Matrix$.MODULE$.canSliceTensorBooleanRowsAndCols(evidence$29, evidence$30);
   }

   static CanSlice2 canSliceRowAndTensorBooleanCols(final Semiring evidence$27, final ClassTag evidence$28) {
      return Matrix$.MODULE$.canSliceRowAndTensorBooleanCols(evidence$27, evidence$28);
   }

   static CanSlice2 canSliceTensorBooleanRowsAndCol(final Semiring evidence$25, final ClassTag evidence$26) {
      return Matrix$.MODULE$.canSliceTensorBooleanRowsAndCol(evidence$25, evidence$26);
   }

   static CanSlice2 canSliceTensorBooleanCols(final Semiring evidence$23, final ClassTag evidence$24) {
      return Matrix$.MODULE$.canSliceTensorBooleanCols(evidence$23, evidence$24);
   }

   static CanSlice2 canSliceTensorBooleanRows(final Semiring evidence$21, final ClassTag evidence$22) {
      return Matrix$.MODULE$.canSliceTensorBooleanRows(evidence$21, evidence$22);
   }

   static CanCreateZeros canCreateZeros(final ClassTag evidence$19, final Zero evidence$20) {
      return Matrix$.MODULE$.canCreateZeros(evidence$19, evidence$20);
   }

   static Rand rand$default$3() {
      return Matrix$.MODULE$.rand$default$3();
   }

   static Matrix rand(final int rows, final int cols, final Rand rand, final ClassTag evidence$17, final Zero evidence$18) {
      return Matrix$.MODULE$.rand(rows, cols, rand, evidence$17, evidence$18);
   }

   static Matrix tabulate(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return Matrix$.MODULE$.tabulate(rows, cols, f, evidence$15, evidence$16);
   }

   static Matrix fill(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return Matrix$.MODULE$.fill(rows, cols, v, evidence$13, evidence$14);
   }

   static Matrix ones(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return Matrix$.MODULE$.ones(rows, cols, evidence$10, evidence$11, evidence$12);
   }

   // $FF: synthetic method
   static Object apply$(final Matrix $this, final Tuple2 i) {
      return $this.apply(i);
   }

   default Object apply(final Tuple2 i) {
      return this.apply(i._1$mcI$sp(), i._2$mcI$sp());
   }

   // $FF: synthetic method
   static void update$(final Matrix $this, final Tuple2 i, final Object e) {
      $this.update(i, e);
   }

   default void update(final Tuple2 i, final Object e) {
      this.update(i._1$mcI$sp(), i._2$mcI$sp(), e);
   }

   Object apply(final int i, final int j);

   void update(final int i, final int j, final Object e);

   // $FF: synthetic method
   static int size$(final Matrix $this) {
      return $this.size();
   }

   default int size() {
      return this.rows() * this.cols();
   }

   int rows();

   int cols();

   // $FF: synthetic method
   static Set keySet$(final Matrix $this) {
      return $this.keySet();
   }

   default Set keySet() {
      return new MatrixKeySet(this.rows(), this.cols());
   }

   // $FF: synthetic method
   static Iterator iterator$(final Matrix $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return .MODULE$.Iterator().range(0, this.rows()).flatMap((i) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static Iterator valuesIterator$(final Matrix $this) {
      return $this.valuesIterator();
   }

   default Iterator valuesIterator() {
      return .MODULE$.Iterator().range(0, this.rows()).flatMap((i) -> $anonfun$valuesIterator$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static Iterator keysIterator$(final Matrix $this) {
      return $this.keysIterator();
   }

   default Iterator keysIterator() {
      return .MODULE$.Iterator().range(0, this.rows()).flatMap((i) -> $anonfun$keysIterator$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static String toString$(final Matrix $this, final int maxLines, final int maxWidth) {
      return $this.toString(maxLines, maxWidth);
   }

   default String toString(final int maxLines, final int maxWidth) {
      int showRows = this.rows() > maxLines ? maxLines - 1 : this.rows();
      ArrayBuffer colWidths = new ArrayBuffer();

      for(int col = 0; col < this.cols() && BoxesRunTime.unboxToInt(colWidths.sum(scala.math.Numeric.IntIsIntegral..MODULE$)) < maxWidth; ++col) {
         colWidths.$plus$eq(BoxesRunTime.boxToInteger(this.colWidth$1(col, showRows)));
      }

      if (colWidths.size() < this.cols()) {
         while(BoxesRunTime.unboxToInt(colWidths.sum(scala.math.Numeric.IntIsIntegral..MODULE$)) + Integer.toString(this.cols()).length() + 12 >= maxWidth) {
            if (colWidths.isEmpty()) {
               return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%d x %d matrix"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.rows()), BoxesRunTime.boxToInteger(this.cols())}));
            }

            colWidths.remove(colWidths.length() - 1);
         }
      }

      String newline = Terminal$.MODULE$.newline();
      StringBuilder rv = new StringBuilder();
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), showRows).foreach$mVc$sp((JFunction1.mcVI.sp)(row) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), colWidths.length()).foreach((col) -> $anonfun$toString$3(this, row, rv, colWidths, showRows, newline, BoxesRunTime.unboxToInt(col))));
      if (this.rows() > showRows) {
         rv.append(newline);
         rv.append("... (");
         rv.append(this.rows());
         rv.append(" total)");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return rv.toString();
   }

   // $FF: synthetic method
   static String toString$(final Matrix $this) {
      return $this.toString();
   }

   default String toString() {
      return this.toString(Terminal$.MODULE$.terminalHeight(), Terminal$.MODULE$.terminalWidth());
   }

   // $FF: synthetic method
   static int toString$default$1$(final Matrix $this) {
      return $this.toString$default$1();
   }

   default int toString$default$1() {
      return Terminal$.MODULE$.terminalHeight() - 3;
   }

   // $FF: synthetic method
   static int toString$default$2$(final Matrix $this) {
      return $this.toString$default$2();
   }

   default int toString$default$2() {
      return Terminal$.MODULE$.terminalWidth();
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$(final Matrix $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix(cm, zero);
   }

   default DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate(this.rows(), this.cols(), (i, j) -> $anonfun$toDenseMatrix$1(this, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j)), cm, zero);
   }

   Matrix copy();

   Vector flatten(final View view);

   // $FF: synthetic method
   static View flatten$default$1$(final Matrix $this) {
      return $this.flatten$default$1();
   }

   default View flatten$default$1() {
      return View.Prefer$.MODULE$;
   }

   // $FF: synthetic method
   static boolean equals$(final Matrix $this, final Object p1) {
      return $this.equals(p1);
   }

   default boolean equals(final Object p1) {
      if (!(p1 instanceof Matrix)) {
         return false;
      } else {
         Matrix var4 = (Matrix)p1;
         boolean var2 = this.rows() == var4.rows() && this.cols() == var4.cols() && this.keysIterator().forall((k) -> BoxesRunTime.boxToBoolean($anonfun$equals$1(this, var4, k)));
         return var2;
      }
   }

   // $FF: synthetic method
   static double apply$mcD$sp$(final Matrix $this, final Tuple2 i) {
      return $this.apply$mcD$sp(i);
   }

   default double apply$mcD$sp(final Tuple2 i) {
      return BoxesRunTime.unboxToDouble(this.apply(i));
   }

   // $FF: synthetic method
   static float apply$mcF$sp$(final Matrix $this, final Tuple2 i) {
      return $this.apply$mcF$sp(i);
   }

   default float apply$mcF$sp(final Tuple2 i) {
      return BoxesRunTime.unboxToFloat(this.apply(i));
   }

   // $FF: synthetic method
   static int apply$mcI$sp$(final Matrix $this, final Tuple2 i) {
      return $this.apply$mcI$sp(i);
   }

   default int apply$mcI$sp(final Tuple2 i) {
      return BoxesRunTime.unboxToInt(this.apply(i));
   }

   // $FF: synthetic method
   static long apply$mcJ$sp$(final Matrix $this, final Tuple2 i) {
      return $this.apply$mcJ$sp(i);
   }

   default long apply$mcJ$sp(final Tuple2 i) {
      return BoxesRunTime.unboxToLong(this.apply(i));
   }

   // $FF: synthetic method
   static void update$mcD$sp$(final Matrix $this, final Tuple2 i, final double e) {
      $this.update$mcD$sp(i, e);
   }

   default void update$mcD$sp(final Tuple2 i, final double e) {
      this.update(i, BoxesRunTime.boxToDouble(e));
   }

   // $FF: synthetic method
   static void update$mcF$sp$(final Matrix $this, final Tuple2 i, final float e) {
      $this.update$mcF$sp(i, e);
   }

   default void update$mcF$sp(final Tuple2 i, final float e) {
      this.update(i, BoxesRunTime.boxToFloat(e));
   }

   // $FF: synthetic method
   static void update$mcI$sp$(final Matrix $this, final Tuple2 i, final int e) {
      $this.update$mcI$sp(i, e);
   }

   default void update$mcI$sp(final Tuple2 i, final int e) {
      this.update(i, BoxesRunTime.boxToInteger(e));
   }

   // $FF: synthetic method
   static void update$mcJ$sp$(final Matrix $this, final Tuple2 i, final long e) {
      $this.update$mcJ$sp(i, e);
   }

   default void update$mcJ$sp(final Tuple2 i, final long e) {
      this.update(i, BoxesRunTime.boxToLong(e));
   }

   // $FF: synthetic method
   static double apply$mcD$sp$(final Matrix $this, final int i, final int j) {
      return $this.apply$mcD$sp(i, j);
   }

   default double apply$mcD$sp(final int i, final int j) {
      return BoxesRunTime.unboxToDouble(this.apply(i, j));
   }

   // $FF: synthetic method
   static float apply$mcF$sp$(final Matrix $this, final int i, final int j) {
      return $this.apply$mcF$sp(i, j);
   }

   default float apply$mcF$sp(final int i, final int j) {
      return BoxesRunTime.unboxToFloat(this.apply(i, j));
   }

   // $FF: synthetic method
   static int apply$mcI$sp$(final Matrix $this, final int i, final int j) {
      return $this.apply$mcI$sp(i, j);
   }

   default int apply$mcI$sp(final int i, final int j) {
      return BoxesRunTime.unboxToInt(this.apply(i, j));
   }

   // $FF: synthetic method
   static long apply$mcJ$sp$(final Matrix $this, final int i, final int j) {
      return $this.apply$mcJ$sp(i, j);
   }

   default long apply$mcJ$sp(final int i, final int j) {
      return BoxesRunTime.unboxToLong(this.apply(i, j));
   }

   // $FF: synthetic method
   static void update$mcD$sp$(final Matrix $this, final int i, final int j, final double e) {
      $this.update$mcD$sp(i, j, e);
   }

   default void update$mcD$sp(final int i, final int j, final double e) {
      this.update(i, j, BoxesRunTime.boxToDouble(e));
   }

   // $FF: synthetic method
   static void update$mcF$sp$(final Matrix $this, final int i, final int j, final float e) {
      $this.update$mcF$sp(i, j, e);
   }

   default void update$mcF$sp(final int i, final int j, final float e) {
      this.update(i, j, BoxesRunTime.boxToFloat(e));
   }

   // $FF: synthetic method
   static void update$mcI$sp$(final Matrix $this, final int i, final int j, final int e) {
      $this.update$mcI$sp(i, j, e);
   }

   default void update$mcI$sp(final int i, final int j, final int e) {
      this.update(i, j, BoxesRunTime.boxToInteger(e));
   }

   // $FF: synthetic method
   static void update$mcJ$sp$(final Matrix $this, final int i, final int j, final long e) {
      $this.update$mcJ$sp(i, j, e);
   }

   default void update$mcJ$sp(final int i, final int j, final long e) {
      this.update(i, j, BoxesRunTime.boxToLong(e));
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcD$sp$(final Matrix $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcD$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcF$sp$(final Matrix $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcF$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcF$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcI$sp$(final Matrix $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcI$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcJ$sp$(final Matrix $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcJ$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   // $FF: synthetic method
   static Matrix copy$mcD$sp$(final Matrix $this) {
      return $this.copy$mcD$sp();
   }

   default Matrix copy$mcD$sp() {
      return this.copy();
   }

   // $FF: synthetic method
   static Matrix copy$mcF$sp$(final Matrix $this) {
      return $this.copy$mcF$sp();
   }

   default Matrix copy$mcF$sp() {
      return this.copy();
   }

   // $FF: synthetic method
   static Matrix copy$mcI$sp$(final Matrix $this) {
      return $this.copy$mcI$sp();
   }

   default Matrix copy$mcI$sp() {
      return this.copy();
   }

   // $FF: synthetic method
   static Matrix copy$mcJ$sp$(final Matrix $this) {
      return $this.copy$mcJ$sp();
   }

   default Matrix copy$mcJ$sp() {
      return this.copy();
   }

   // $FF: synthetic method
   static Vector flatten$mcD$sp$(final Matrix $this, final View view) {
      return $this.flatten$mcD$sp(view);
   }

   default Vector flatten$mcD$sp(final View view) {
      return this.flatten(view);
   }

   // $FF: synthetic method
   static Vector flatten$mcF$sp$(final Matrix $this, final View view) {
      return $this.flatten$mcF$sp(view);
   }

   default Vector flatten$mcF$sp(final View view) {
      return this.flatten(view);
   }

   // $FF: synthetic method
   static Vector flatten$mcI$sp$(final Matrix $this, final View view) {
      return $this.flatten$mcI$sp(view);
   }

   default Vector flatten$mcI$sp(final View view) {
      return this.flatten(view);
   }

   // $FF: synthetic method
   static Vector flatten$mcJ$sp$(final Matrix $this, final View view) {
      return $this.flatten$mcJ$sp(view);
   }

   default Vector flatten$mcJ$sp(final View view) {
      return this.flatten(view);
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$iterator$2(final Matrix $this, final int i$1, final int j) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(i$1)), BoxesRunTime.boxToInteger(j))), $this.apply(i$1, j));
   }

   // $FF: synthetic method
   static Iterator $anonfun$iterator$1(final Matrix $this, final int i) {
      return .MODULE$.Iterator().range(0, $this.cols()).map((j) -> $anonfun$iterator$2($this, i, BoxesRunTime.unboxToInt(j)));
   }

   // $FF: synthetic method
   static Object $anonfun$valuesIterator$2(final Matrix $this, final int i$2, final int j) {
      return $this.apply(i$2, j);
   }

   // $FF: synthetic method
   static Iterator $anonfun$valuesIterator$1(final Matrix $this, final int i) {
      return .MODULE$.Iterator().range(0, $this.cols()).map((j) -> $anonfun$valuesIterator$2($this, i, BoxesRunTime.unboxToInt(j)));
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$keysIterator$2(final int i$3, final int j) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(i$3)), BoxesRunTime.boxToInteger(j));
   }

   // $FF: synthetic method
   static Iterator $anonfun$keysIterator$1(final Matrix $this, final int i) {
      return .MODULE$.Iterator().range(0, $this.cols()).map((j) -> $anonfun$keysIterator$2(i, BoxesRunTime.unboxToInt(j)));
   }

   private int colWidth$1(final int col, final int showRows$1) {
      return showRows$1 > 0 ? BoxesRunTime.unboxToInt(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), showRows$1).map((JFunction1.mcII.sp)(row) -> this.apply(row, col) != null ? this.apply(row, col).toString().length() + 2 : 3).max(scala.math.Ordering.Int..MODULE$)) : 0;
   }

   // $FF: synthetic method
   static Object $anonfun$toString$3(final Matrix $this, final int row$1, final StringBuilder rv$1, final ArrayBuffer colWidths$1, final int showRows$1, final String newline$1, final int col) {
      String cell = $this.apply(row$1, col) != null ? $this.apply(row$1, col).toString() : "--";
      rv$1.append(cell);
      rv$1.append(scala.collection.StringOps..MODULE$.$times$extension(" ", BoxesRunTime.unboxToInt(colWidths$1.apply(col)) - cell.length()));
      Object var9;
      if (col == colWidths$1.length() - 1) {
         if (col < $this.cols() - 1) {
            rv$1.append("...");
            if (row$1 == 0) {
               rv$1.append(" (");
               rv$1.append($this.cols());
               rv$1.append(" total)");
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }

         var9 = row$1 + 1 < showRows$1 ? rv$1.append(newline$1) : BoxedUnit.UNIT;
      } else {
         var9 = BoxedUnit.UNIT;
      }

      return var9;
   }

   // $FF: synthetic method
   static Object $anonfun$toDenseMatrix$1(final Matrix $this, final int i, final int j) {
      return $this.apply(i, j);
   }

   // $FF: synthetic method
   static boolean $anonfun$equals$1(final Matrix $this, final Matrix x2$1, final Tuple2 k) {
      return BoxesRunTime.equals($this.apply(k), x2$1.apply(k));
   }

   static void $init$(final Matrix $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
