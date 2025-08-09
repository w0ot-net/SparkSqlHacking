package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import java.util.BitSet;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001da\u0001B\u0012%\u0001%B\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005{!Aa\t\u0001BC\u0002\u0013\u0005q\t\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003I\u0011!a\u0005A!b\u0001\n\u0003i\u0005\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u000b=\u0003A\u0011\u0001)\t\u000bQ\u0003A\u0011A+\t\u000ba\u0003A\u0011A-\t\u000b\u0001\u0004A\u0011A$\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\r\u0004A\u0011\u00012\t\u000b\u0011\u0004A\u0011A3\t\u000bI\u0004A\u0011A:\t\u000bU\u0004A\u0011\u0001<\t\u000bm\u0004A\u0011\u0001?\t\u000f\u0005e\u0001\u0001\"\u0011\u0002\u001c\u001d9\u0011Q\u0006\u0013\t\u0002\u0005=bAB\u0012%\u0011\u0003\t\t\u0004\u0003\u0004P'\u0011\u0005\u00111\u0007\u0005\u0007)N!\t!!\u000e\t\rQ\u001bB\u0011AA!\u0011%\tyeEI\u0001\n\u0003\t\t\u0006C\u0004\u0002hM!\t!!\u001b\t\u0013\u0005=4#%A\u0005\u0002\u0005E\u0003bBA9'\u0011\u0005\u00111\u000f\u0005\n\u0003s\u001a\u0012\u0013!C\u0001\u0003#Bq!a\u001f\u0014\t\u0007\ti\bC\u0004\u0002*N!\u0019!a+\t\u000f\u0005\u00057\u0003b\u0001\u0002D\"9\u00111Z\n\u0005\u0004\u00055\u0007bBAk'\u0011\r\u0011q\u001b\u0005\b\u0003?\u001cB1AAq\u0011%\u0011)aEI\u0001\n\u0003\t\tFA\u0005CSR4Vm\u0019;pe*\u0011QEJ\u0001\u0007Y&t\u0017\r\\4\u000b\u0003\u001d\naA\u0019:fKj,7\u0001A\n\u0005\u0001)\u0002t\u0007\u0005\u0002,]5\tAFC\u0001.\u0003\u0015\u00198-\u00197b\u0013\tyCF\u0001\u0004B]f\u0014VM\u001a\t\u0004cI\"T\"\u0001\u0013\n\u0005M\"#A\u0002,fGR|'\u000f\u0005\u0002,k%\u0011a\u0007\f\u0002\b\u0005>|G.Z1o!\u0011\t\u0004\b\u000e\u001e\n\u0005e\"#A\u0003,fGR|'\u000fT5lKB\u0011\u0011\u0007A\u0001\u0005I\u0006$\u0018-F\u0001>!\tq4)D\u0001@\u0015\t\u0001\u0015)\u0001\u0003vi&d'\"\u0001\"\u0002\t)\fg/Y\u0005\u0003\t~\u0012aAQ5u'\u0016$\u0018!\u00023bi\u0006\u0004\u0013A\u00027f]\u001e$\b.F\u0001I!\tY\u0013*\u0003\u0002KY\t\u0019\u0011J\u001c;\u0002\u000f1,gn\u001a;iA\u0005iQM\u001c4pe\u000e,G*\u001a8hi\",\u0012\u0001N\u0001\u000fK:4wN]2f\u0019\u0016tw\r\u001e5!\u0003\u0019a\u0014N\\5u}Q!!(\u0015*T\u0011\u0015Yt\u00011\u0001>\u0011\u00151u\u00011\u0001I\u0011\u001dau\u0001%AA\u0002Q\nQ!\u00199qYf$\"\u0001\u000e,\t\u000b]C\u0001\u0019\u0001%\u0002\u0003%\fa!\u001e9eCR,Gc\u0001.^=B\u00111fW\u0005\u000392\u0012A!\u00168ji\")q+\u0003a\u0001\u0011\")q,\u0003a\u0001i\u0005\ta/\u0001\u0006bGRLg/Z*ju\u0016\fAaY8qsV\t!(\u0001\u0003sKB\u0014\u0018AE1di&4XmS3zg&#XM]1u_J,\u0012A\u001a\t\u0004O>DeB\u00015n\u001d\tIG.D\u0001k\u0015\tY\u0007&\u0001\u0004=e>|GOP\u0005\u0002[%\u0011a\u000eL\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0018O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tqG&\u0001\u000bbGRLg/\u001a,bYV,7/\u0013;fe\u0006$xN]\u000b\u0002iB\u0019qm\u001c\u001b\u0002\u001d\u0005\u001cG/\u001b<f\u0013R,'/\u0019;peV\tq\u000fE\u0002h_b\u0004BaK=Ii%\u0011!\u0010\f\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u00191,gn\u001a;ig6\u000bGo\u00195\u0015\u0005Qj\b\"\u0002@\u0011\u0001\u0004y\u0018!B8uQ\u0016\u0014\b\u0007BA\u0001\u0003\u000f\u0001B!\r\u001a\u0002\u0004A!\u0011QAA\u0004\u0019\u0001!1\"!\u0003~\u0003\u0003\u0005\tQ!\u0001\u0002\f\t\u0019q\fJ\u0019\u0012\t\u00055\u00111\u0003\t\u0004W\u0005=\u0011bAA\tY\t9aj\u001c;iS:<\u0007cA\u0016\u0002\u0016%\u0019\u0011q\u0003\u0017\u0003\u0007\u0005s\u00170\u0001\u0005u_N#(/\u001b8h)\t\ti\u0002\u0005\u0003\u0002 \u0005\u001db\u0002BA\u0011\u0003G\u0001\"!\u001b\u0017\n\u0007\u0005\u0015B&\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003S\tYC\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003Ka\u0013!\u0003\"jiZ+7\r^8s!\t\t4c\u0005\u0002\u0014UQ\u0011\u0011q\u0006\u000b\u0004u\u0005]\u0002bBA\u001d+\u0001\u0007\u00111H\u0001\u0006E>|Gn\u001d\t\u0005W\u0005uB'C\u0002\u0002@1\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?)\u0019\t\u0019%a\u0013\u0002NQ\u0019!(!\u0012\t\u000f\u0005\u001dc\u00031\u0001\u0002J\u0005)AO];fgB!1&!\u0010I\u0011\u00151e\u00031\u0001I\u0011\u001dae\u0003%AA\u0002Q\nq\"\u00199qYf$C-\u001a4bk2$HEM\u000b\u0003\u0003'R3\u0001NA+W\t\t9\u0006\u0005\u0003\u0002Z\u0005\rTBAA.\u0015\u0011\ti&a\u0018\u0002\u0013Ut7\r[3dW\u0016$'bAA1Y\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00141\f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u0002>fe>\u001cH#\u0002\u001e\u0002l\u00055\u0004\"\u0002$\u0019\u0001\u0004A\u0005b\u0002'\u0019!\u0003\u0005\r\u0001N\u0001\u0010u\u0016\u0014xn\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005!qN\\3t)\u0015Q\u0014QOA<\u0011\u00151%\u00041\u0001I\u0011\u001da%\u0004%AA\u0002Q\nab\u001c8fg\u0012\"WMZ1vYR$#'\u0001\u0007dC:l\u0015\r\u001d,bYV,7/\u0006\u0003\u0002\u0000\u0005=E\u0003BAA\u00033\u0003\"\"a!\u0002\nj\"\u0014QRAJ\u001b\t\t)IC\u0002\u0002\b\u0012\nqa];qa>\u0014H/\u0003\u0003\u0002\f\u0006\u0015%\u0001D\"b]6\u000b\u0007OV1mk\u0016\u001c\b\u0003BA\u0003\u0003\u001f#q!!%\u001d\u0005\u0004\tYA\u0001\u0002WeA)\u0011'!&\u0002\u000e&\u0019\u0011q\u0013\u0013\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\u0005\b\u00037c\u00029AAO\u0003\ri\u0017M\u001c\t\u0007\u0003?\u000b)+!$\u000e\u0005\u0005\u0005&bAARY\u00059!/\u001a4mK\u000e$\u0018\u0002BAT\u0003C\u0013\u0001b\u00117bgN$\u0016mZ\u0001\tg\u000e\fG.\u0019:PMV!\u0011QVA_+\t\ty\u000b\u0005\u0005\u0002\u0004\u0006E\u0016QWA^\u0013\u0011\t\u0019,!\"\u0003\u0011M\u001b\u0017\r\\1s\u001f\u001a\u0004R!MA\\\u0003wK1!!/%\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\t\u0005\u0015\u0011Q\u0018\u0003\b\u0003\u007fk\"\u0019AA\u0006\u0005\u0005!\u0016\u0001E2b]&#XM]1uKZ\u000bG.^3t+\t\t)\r\u0005\u0004\u0002\u0004\u0006\u001d'\bN\u0005\u0005\u0003\u0013\f)IA\tDC:$&/\u0019<feN,g+\u00197vKN\f\u0001dY1o)J\fg/\u001a:tK.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t+\t\ty\rE\u0004\u0002\u0004\u0006E'\b\u0013\u001b\n\t\u0005M\u0017Q\u0011\u0002\u0019\u0007\u0006tGK]1wKJ\u001cXmS3z-\u0006dW/\u001a)bSJ\u001c\u0018AE2b]R\u0013\u0018M\\:g_Jlg+\u00197vKN,\"!!7\u0011\r\u0005\r\u00151\u001c\u001e5\u0013\u0011\ti.!\"\u0003%\r\u000bg\u000e\u0016:b]N4wN]7WC2,Xm]\u0001\fG\u0006tW*\u00199QC&\u00148/\u0006\u0003\u0002d\u00065HCBAs\u0003c\f)\u0010E\u0006\u0002\u0004\u0006\u001d(\b\u0013\u001b\u0002l\u0006=\u0018\u0002BAu\u0003\u000b\u00131cQ1o\u001b\u0006\u00048*Z=WC2,X\rU1jeN\u0004B!!\u0002\u0002n\u00129\u0011\u0011S\u0011C\u0002\u0005-\u0001#B\u0019\u0002\u0016\u0006-\bbBANC\u0001\u000f\u00111\u001f\t\u0007\u0003?\u000b)+a;\t\u000f\u0005]\u0018\u0005q\u0001\u0002z\u0006!!0\u001a:p!\u0019\tYP!\u0001\u0002l6\u0011\u0011Q \u0006\u0004\u0003\u007f4\u0013aB:u_J\fw-Z\u0005\u0005\u0005\u0007\tiP\u0001\u0003[KJ|\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3\u0007"
)
public class BitVector implements Vector, VectorLike$mcZ$sp {
   private final BitSet data;
   private final int length;
   private final boolean enforceLength;

   public static boolean $lessinit$greater$default$3() {
      return BitVector$.MODULE$.$lessinit$greater$default$3();
   }

   public static CanMapKeyValuePairs canMapPairs(final ClassTag man, final Zero zero) {
      return BitVector$.MODULE$.canMapPairs(man, zero);
   }

   public static CanTransformValues canTransformValues() {
      return BitVector$.MODULE$.canTransformValues();
   }

   public static CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return BitVector$.MODULE$.canTraverseKeyValuePairs();
   }

   public static CanTraverseValues canIterateValues() {
      return BitVector$.MODULE$.canIterateValues();
   }

   public static ScalarOf scalarOf() {
      return BitVector$.MODULE$.scalarOf();
   }

   public static CanMapValues canMapValues(final ClassTag man) {
      return BitVector$.MODULE$.canMapValues(man);
   }

   public static boolean ones$default$2() {
      return BitVector$.MODULE$.ones$default$2();
   }

   public static BitVector ones(final int length, final boolean enforceLength) {
      return BitVector$.MODULE$.ones(length, enforceLength);
   }

   public static boolean zeros$default$2() {
      return BitVector$.MODULE$.zeros$default$2();
   }

   public static BitVector zeros(final int length, final boolean enforceLength) {
      return BitVector$.MODULE$.zeros(length, enforceLength);
   }

   public static boolean apply$default$2() {
      return BitVector$.MODULE$.apply$default$2();
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcZ$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcZ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike$mcZ$sp.map$mcZ$sp$(this, fn, canMapValues);
   }

   public void foreach(final Function1 fn) {
      VectorLike$mcZ$sp.foreach$(this, fn);
   }

   public void foreach$mcZ$sp(final Function1 fn) {
      VectorLike$mcZ$sp.foreach$mcZ$sp$(this, fn);
   }

   public Set keySet() {
      return Vector.keySet$(this);
   }

   public int size() {
      return Vector.size$(this);
   }

   public Iterator iterator() {
      return Vector.iterator$(this);
   }

   public Iterator valuesIterator() {
      return Vector.valuesIterator$(this);
   }

   public Iterator keysIterator() {
      return Vector.keysIterator$(this);
   }

   public Vector copy$mcD$sp() {
      return Vector.copy$mcD$sp$(this);
   }

   public Vector copy$mcF$sp() {
      return Vector.copy$mcF$sp$(this);
   }

   public Vector copy$mcI$sp() {
      return Vector.copy$mcI$sp$(this);
   }

   public boolean equals(final Object p1) {
      return Vector.equals$(this, p1);
   }

   public int hashCode() {
      return Vector.hashCode$(this);
   }

   public DenseVector toDenseVector(final ClassTag cm) {
      return Vector.toDenseVector$(this, cm);
   }

   public DenseVector toDenseVector$mcD$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcD$sp$(this, cm);
   }

   public DenseVector toDenseVector$mcF$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcF$sp$(this, cm);
   }

   public DenseVector toDenseVector$mcI$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcI$sp$(this, cm);
   }

   public scala.collection.immutable.Vector toScalaVector() {
      return Vector.toScalaVector$(this);
   }

   public Object toArray(final ClassTag cm) {
      return Vector.toArray$(this, cm);
   }

   public double[] toArray$mcD$sp(final ClassTag cm) {
      return Vector.toArray$mcD$sp$(this, cm);
   }

   public float[] toArray$mcF$sp(final ClassTag cm) {
      return Vector.toArray$mcF$sp$(this, cm);
   }

   public int[] toArray$mcI$sp(final ClassTag cm) {
      return Vector.toArray$mcI$sp$(this, cm);
   }

   public Vector toVector(final ClassTag cm) {
      return Vector.toVector$(this, cm);
   }

   public Vector toVector$mcD$sp(final ClassTag cm) {
      return Vector.toVector$mcD$sp$(this, cm);
   }

   public Vector toVector$mcF$sp(final ClassTag cm) {
      return Vector.toVector$mcF$sp$(this, cm);
   }

   public Vector toVector$mcI$sp(final ClassTag cm) {
      return Vector.toVector$mcI$sp$(this, cm);
   }

   public Vector padTo(final int len, final Object elem, final ClassTag cm) {
      return Vector.padTo$(this, len, elem, cm);
   }

   public Vector padTo$mcD$sp(final int len, final double elem, final ClassTag cm) {
      return Vector.padTo$mcD$sp$(this, len, elem, cm);
   }

   public Vector padTo$mcF$sp(final int len, final float elem, final ClassTag cm) {
      return Vector.padTo$mcF$sp$(this, len, elem, cm);
   }

   public Vector padTo$mcI$sp(final int len, final int elem, final ClassTag cm) {
      return Vector.padTo$mcI$sp$(this, len, elem, cm);
   }

   public boolean exists(final Function1 f) {
      return Vector.exists$(this, f);
   }

   public boolean exists$mcD$sp(final Function1 f) {
      return Vector.exists$mcD$sp$(this, f);
   }

   public boolean exists$mcF$sp(final Function1 f) {
      return Vector.exists$mcF$sp$(this, f);
   }

   public boolean exists$mcI$sp(final Function1 f) {
      return Vector.exists$mcI$sp$(this, f);
   }

   public boolean forall(final Function1 f) {
      return Vector.forall$(this, f);
   }

   public boolean forall$mcD$sp(final Function1 f) {
      return Vector.forall$mcD$sp$(this, f);
   }

   public boolean forall$mcF$sp(final Function1 f) {
      return Vector.forall$mcF$sp$(this, f);
   }

   public boolean forall$mcI$sp(final Function1 f) {
      return Vector.forall$mcI$sp$(this, f);
   }

   public Object fold(final Object z, final Function2 op) {
      return Vector.fold$(this, z, op);
   }

   public Object fold$mcD$sp(final Object z, final Function2 op) {
      return Vector.fold$mcD$sp$(this, z, op);
   }

   public Object fold$mcF$sp(final Object z, final Function2 op) {
      return Vector.fold$mcF$sp$(this, z, op);
   }

   public Object fold$mcI$sp(final Object z, final Function2 op) {
      return Vector.fold$mcI$sp$(this, z, op);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return Vector.foldLeft$(this, z, op);
   }

   public Object foldLeft$mcD$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcD$sp$(this, z, op);
   }

   public Object foldLeft$mcF$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcF$sp$(this, z, op);
   }

   public Object foldLeft$mcI$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcI$sp$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return Vector.foldRight$(this, z, op);
   }

   public Object foldRight$mcD$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcD$sp$(this, z, op);
   }

   public Object foldRight$mcF$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcF$sp$(this, z, op);
   }

   public Object foldRight$mcI$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcI$sp$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return Vector.reduce$(this, op);
   }

   public Object reduce$mcD$sp(final Function2 op) {
      return Vector.reduce$mcD$sp$(this, op);
   }

   public Object reduce$mcF$sp(final Function2 op) {
      return Vector.reduce$mcF$sp$(this, op);
   }

   public Object reduce$mcI$sp(final Function2 op) {
      return Vector.reduce$mcI$sp$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return Vector.reduceLeft$(this, op);
   }

   public Object reduceLeft$mcD$sp(final Function2 op) {
      return Vector.reduceLeft$mcD$sp$(this, op);
   }

   public Object reduceLeft$mcF$sp(final Function2 op) {
      return Vector.reduceLeft$mcF$sp$(this, op);
   }

   public Object reduceLeft$mcI$sp(final Function2 op) {
      return Vector.reduceLeft$mcI$sp$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return Vector.reduceRight$(this, op);
   }

   public Object reduceRight$mcD$sp(final Function2 op) {
      return Vector.reduceRight$mcD$sp$(this, op);
   }

   public Object reduceRight$mcF$sp(final Function2 op) {
      return Vector.reduceRight$mcF$sp$(this, op);
   }

   public Object reduceRight$mcI$sp(final Function2 op) {
      return Vector.reduceRight$mcI$sp$(this, op);
   }

   public Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$(this, z, op, cm, cm1);
   }

   public Vector scan$mcD$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcD$sp$(this, z, op, cm, cm1);
   }

   public Vector scan$mcF$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcF$sp$(this, z, op, cm, cm1);
   }

   public Vector scan$mcI$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcI$sp$(this, z, op, cm, cm1);
   }

   public Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$(this, z, op, cm1);
   }

   public Vector scanLeft$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcD$sp$(this, z, op, cm1);
   }

   public Vector scanLeft$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcF$sp$(this, z, op, cm1);
   }

   public Vector scanLeft$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcI$sp$(this, z, op, cm1);
   }

   public Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$(this, z, op, cm1);
   }

   public Vector scanRight$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcD$sp$(this, z, op, cm1);
   }

   public Vector scanRight$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcF$sp$(this, z, op, cm1);
   }

   public Vector scanRight$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcI$sp$(this, z, op, cm1);
   }

   public Object map$mcB$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcB$sp$(this, fn, canMapValues);
   }

   public Object map$mcC$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcC$sp$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcD$sp$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcF$sp$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcI$sp$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcJ$sp$(this, fn, canMapValues);
   }

   public Object map$mcS$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcS$sp$(this, fn, canMapValues);
   }

   public Object map$mcV$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcV$sp$(this, fn, canMapValues);
   }

   public void foreach$mcB$sp(final Function1 fn) {
      VectorLike.foreach$mcB$sp$(this, fn);
   }

   public void foreach$mcC$sp(final Function1 fn) {
      VectorLike.foreach$mcC$sp$(this, fn);
   }

   public void foreach$mcD$sp(final Function1 fn) {
      VectorLike.foreach$mcD$sp$(this, fn);
   }

   public void foreach$mcF$sp(final Function1 fn) {
      VectorLike.foreach$mcF$sp$(this, fn);
   }

   public void foreach$mcI$sp(final Function1 fn) {
      VectorLike.foreach$mcI$sp$(this, fn);
   }

   public void foreach$mcJ$sp(final Function1 fn) {
      VectorLike.foreach$mcJ$sp$(this, fn);
   }

   public void foreach$mcS$sp(final Function1 fn) {
      VectorLike.foreach$mcS$sp$(this, fn);
   }

   public void foreach$mcV$sp(final Function1 fn) {
      VectorLike.foreach$mcV$sp$(this, fn);
   }

   public double apply$mcID$sp(final int i) {
      return TensorLike.apply$mcID$sp$(this, i);
   }

   public float apply$mcIF$sp(final int i) {
      return TensorLike.apply$mcIF$sp$(this, i);
   }

   public int apply$mcII$sp(final int i) {
      return TensorLike.apply$mcII$sp$(this, i);
   }

   public long apply$mcIJ$sp(final int i) {
      return TensorLike.apply$mcIJ$sp$(this, i);
   }

   public void update$mcID$sp(final int i, final double v) {
      TensorLike.update$mcID$sp$(this, i, v);
   }

   public void update$mcIF$sp(final int i, final float v) {
      TensorLike.update$mcIF$sp$(this, i, v);
   }

   public void update$mcII$sp(final int i, final int v) {
      TensorLike.update$mcII$sp$(this, i, v);
   }

   public void update$mcIJ$sp(final int i, final long v) {
      TensorLike.update$mcIJ$sp$(this, i, v);
   }

   public TensorKeys keys() {
      return TensorLike.keys$(this);
   }

   public TensorValues values() {
      return TensorLike.values$(this);
   }

   public TensorPairs pairs() {
      return TensorLike.pairs$(this);
   }

   public TensorActive active() {
      return TensorLike.active$(this);
   }

   public Object apply(final Object slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, slice, canSlice);
   }

   public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
      return TensorLike.apply$(this, slice1, slice2, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcID$sp$(this, f, bf);
   }

   public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
   }

   public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcII$sp$(this, f, bf);
   }

   public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$(this, f, bf);
   }

   public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcD$sp$(this, f, bf);
   }

   public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcF$sp$(this, f, bf);
   }

   public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcI$sp$(this, f, bf);
   }

   public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcJ$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike.foreachPair$(this, fn);
   }

   public void foreachPair$mcID$sp(final Function2 fn) {
      TensorLike.foreachPair$mcID$sp$(this, fn);
   }

   public void foreachPair$mcIF$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIF$sp$(this, fn);
   }

   public void foreachPair$mcII$sp(final Function2 fn) {
      TensorLike.foreachPair$mcII$sp$(this, fn);
   }

   public void foreachPair$mcIJ$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIJ$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike.foreachValue$(this, fn);
   }

   public void foreachValue$mcD$sp(final Function1 fn) {
      TensorLike.foreachValue$mcD$sp$(this, fn);
   }

   public void foreachValue$mcF$sp(final Function1 fn) {
      TensorLike.foreachValue$mcF$sp$(this, fn);
   }

   public void foreachValue$mcI$sp(final Function1 fn) {
      TensorLike.foreachValue$mcI$sp$(this, fn);
   }

   public void foreachValue$mcJ$sp(final Function1 fn) {
      TensorLike.foreachValue$mcJ$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike.forall$(this, (Function2)fn);
   }

   public boolean forall$mcID$sp(final Function2 fn) {
      return TensorLike.forall$mcID$sp$(this, fn);
   }

   public boolean forall$mcIF$sp(final Function2 fn) {
      return TensorLike.forall$mcIF$sp$(this, fn);
   }

   public boolean forall$mcII$sp(final Function2 fn) {
      return TensorLike.forall$mcII$sp$(this, fn);
   }

   public boolean forall$mcIJ$sp(final Function2 fn) {
      return TensorLike.forall$mcIJ$sp$(this, fn);
   }

   public boolean forall$mcJ$sp(final Function1 fn) {
      return TensorLike.forall$mcJ$sp$(this, fn);
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor.findAll$(this, f);
   }

   public IndexedSeq findAll$mcD$sp(final Function1 f) {
      return QuasiTensor.findAll$mcD$sp$(this, f);
   }

   public IndexedSeq findAll$mcF$sp(final Function1 f) {
      return QuasiTensor.findAll$mcF$sp$(this, f);
   }

   public IndexedSeq findAll$mcI$sp(final Function1 f) {
      return QuasiTensor.findAll$mcI$sp$(this, f);
   }

   public IndexedSeq findAll$mcJ$sp(final Function1 f) {
      return QuasiTensor.findAll$mcJ$sp$(this, f);
   }

   public BitSet data() {
      return this.data;
   }

   public int length() {
      return this.length;
   }

   public boolean enforceLength() {
      return this.enforceLength;
   }

   public boolean apply(final int i) {
      if (i >= 0 && i < this.length()) {
         return this.data().get(i);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(26)).append(i).append(" is not in the range [0, ").append(this.length()).append(")").toString());
      }
   }

   public void update(final int i, final boolean v) {
      if (i >= 0 && i < this.length()) {
         this.data().set(i, v);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(26)).append(i).append(" is not in the range [0, ").append(this.length()).append(")").toString());
      }
   }

   public int activeSize() {
      return this.data().cardinality();
   }

   public BitVector copy() {
      return new BitVector((BitSet)this.data().clone(), this.length(), BitVector$.MODULE$.$lessinit$greater$default$3());
   }

   public BitVector repr() {
      return this;
   }

   public Iterator activeKeysIterator() {
      int firstBit = this.data().nextSetBit(0);
      return firstBit < 0 ? .MODULE$.Iterator().empty() : new Iterator(firstBit) {
         private boolean nextReady;
         private int _next;
         // $FF: synthetic field
         private final BitVector $outer;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxBy$(this, f, cmp);
         }

         public Option maxByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxByOption$(this, f, cmp);
         }

         public Object minBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minBy$(this, f, cmp);
         }

         public Option minByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minByOption$(this, f, cmp);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public scala.collection.immutable.Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private boolean nextReady() {
            return this.nextReady;
         }

         private void nextReady_$eq(final boolean x$1) {
            this.nextReady = x$1;
         }

         private int _next() {
            return this._next;
         }

         private void _next_$eq(final int x$1) {
            this._next = x$1;
         }

         public boolean hasNext() {
            boolean var10000;
            label31: {
               if (this._next() >= 0) {
                  if (this.nextReady()) {
                     break label31;
                  }

                  this._next_$eq(this._next() + 1);
                  this._next_$eq(this.$outer.data().nextSetBit(this._next()));
                  this.nextReady_$eq(this._next() >= 0);
                  if (this.nextReady()) {
                     break label31;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }

         public int next() {
            if (!this.nextReady()) {
               this.hasNext();
               if (!this.nextReady()) {
                  throw new NoSuchElementException();
               }
            }

            this.nextReady_$eq(false);
            return this._next();
         }

         public {
            if (BitVector.this == null) {
               throw null;
            } else {
               this.$outer = BitVector.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.nextReady = true;
               this._next = firstBit$1;
            }
         }
      };
   }

   public Iterator activeValuesIterator() {
      return this.activeKeysIterator().map((JFunction1.mcZI.sp)(x$1) -> true);
   }

   public Iterator activeIterator() {
      return this.activeKeysIterator().map((x$2) -> $anonfun$activeIterator$1(BoxesRunTime.unboxToInt(x$2)));
   }

   public boolean lengthsMatch(final Vector other) {
      boolean var10000;
      if (!this.enforceLength()) {
         var10000 = true;
      } else {
         boolean var2;
         if (!(other instanceof BitVector)) {
            var2 = other.length() == this.length();
         } else {
            BitVector var4 = (BitVector)other;
            var2 = !var4.enforceLength() || var4.length() == this.length();
         }

         var10000 = var2;
      }

      return var10000;
   }

   public String toString() {
      return this.activeKeysIterator().mkString("BitVector(", ", ", ")");
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$activeIterator$1(final int x$2) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(x$2)), BoxesRunTime.boxToBoolean(true));
   }

   public BitVector(final BitSet data, final int length, final boolean enforceLength) {
      this.data = data;
      this.length = length;
      this.enforceLength = enforceLength;
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      VectorLike.$init$(this);
      Vector.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
