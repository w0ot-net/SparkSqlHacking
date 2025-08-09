package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCreateZerosLike;
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
import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t]b\u0001B\u000e\u001d\u0001\u0005B\u0001b\u0018\u0001\u0003\u0006\u0004%\t\u0001\u0019\u0005\tI\u0002\u0011\t\u0011)A\u0005C\"AQ\r\u0001BC\u0002\u0013\u0005a\r\u0003\u0005o\u0001\t\u0005\t\u0015!\u0003h\u0011!y\u0007AaA!\u0002\u0017\u0001\b\"\u0002<\u0001\t\u00039\b\"\u0002?\u0001\t\u0003i\bbBA\u0004\u0001\u0011\u0005\u0011\u0011\u0002\u0005\b\u0003/\u0001A\u0011AA\r\u0011\u001d\t\t\u0003\u0001C\u0001\u0003GAq!!\n\u0001\t\u0003\t\u0019\u0003C\u0004\u0002(\u0001!\t!!\u000b\t\u000f\u0005-\u0002\u0001\"\u0001\u0002.!9\u0011Q\u0007\u0001\u0005\u0002\u0005]\u0002bBA!\u0001\u0011\u0005\u00111\t\u0005\b\u0003\u000f\u0002A\u0011IA%\u000f\u001d\tY\u0006\bE\u0001\u0003;2aa\u0007\u000f\t\u0002\u0005}\u0003B\u0002<\u0013\t\u0003\t\t\u0007C\u0004\u0002dI!\u0019!!\u001a\t\u000f\u0005\u0005%\u0003b\u0001\u0002\u0004\"9\u0011Q\u0015\n\u0005\u0004\u0005\u001d\u0006bBAd%\u0011\r\u0011\u0011\u001a\u0005\b\u0003k\u0014B1AA|\u0011\u001d\u0011YA\u0005C\u0002\u0005\u001bAqA!\t\u0013\t\u0007\u0011\u0019CA\u0006TY&\u001cWMV3di>\u0014(BA\u000f\u001f\u0003\u0019a\u0017N\\1mO*\tq$\u0001\u0004ce\u0016,'0Z\u0002\u0001+\r\u0011#lL\n\u0005\u0001\rJS\u000b\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0004U-jS\"\u0001\u000f\n\u00051b\"A\u0002,fGR|'\u000f\u0005\u0002/_1\u0001A!\u0003\u0019\u0001A\u0003\u0005\tQ1\u00012\u0005\u00051\u0016C\u0001\u001a6!\t!3'\u0003\u00025K\t9aj\u001c;iS:<\u0007C\u0001\u00137\u0013\t9TEA\u0002B]fDcaL\u001d=\r.\u0003\u0006C\u0001\u0013;\u0013\tYTEA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012>}\u0001{dB\u0001\u0013?\u0013\tyT%\u0001\u0004E_V\u0014G.Z\u0019\u0005I\u0005+eE\u0004\u0002C\u000b6\t1I\u0003\u0002EA\u00051AH]8pizJ\u0011AJ\u0019\u0006G\u001dC%*\u0013\b\u0003I!K!!S\u0013\u0002\u0007%sG/\r\u0003%\u0003\u00163\u0013'B\u0012M\u001b>seB\u0001\u0013N\u0013\tqU%A\u0003GY>\fG/\r\u0003%\u0003\u00163\u0013'B\u0012R%R\u001bfB\u0001\u0013S\u0013\t\u0019V%\u0001\u0003M_:<\u0017\u0007\u0002\u0013B\u000b\u001a\u0002BA\u000b,.1&\u0011q\u000b\b\u0002\u000b-\u0016\u001cGo\u001c:MS.,\u0007\u0003\u0002\u0016\u000136\u0002\"A\f.\u0005\u0013m\u0003\u0001\u0015!A\u0001\u0006\u0004\t$!A&)\u0007iKT,M\u0003$\u000f\"s\u0016*\r\u0003%\u0003\u00163\u0013A\u0002;f]N|'/F\u0001b!\u0011Q#-W\u0017\n\u0005\rd\"A\u0002+f]N|'/A\u0004uK:\u001cxN\u001d\u0011\u0002\rMd\u0017nY3t+\u00059\u0007c\u00015l3:\u0011\u0011)[\u0005\u0003U\u0016\nq\u0001]1dW\u0006<W-\u0003\u0002m[\nQ\u0011J\u001c3fq\u0016$7+Z9\u000b\u0005),\u0013aB:mS\u000e,7\u000fI\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004cA9u[5\t!O\u0003\u0002tK\u00059!/\u001a4mK\u000e$\u0018BA;s\u0005!\u0019E.Y:t)\u0006<\u0017A\u0002\u001fj]&$h\bF\u0002yun$\"\u0001W=\t\u000b=4\u00019\u00019\t\u000b}3\u0001\u0019A1\t\u000b\u00154\u0001\u0019A4\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u00055r\bBB@\b\u0001\u0004\t\t!A\u0001j!\r!\u00131A\u0005\u0004\u0003\u000b)#aA%oi\u00061Q\u000f\u001d3bi\u0016$b!a\u0003\u0002\u0012\u0005M\u0001c\u0001\u0013\u0002\u000e%\u0019\u0011qB\u0013\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u007f\"\u0001\r!!\u0001\t\r\u0005U\u0001\u00021\u0001.\u0003\u00051\u0018\u0001B2paf,\"!a\u0007\u0011\t)\ni\"L\u0005\u0004\u0003?a\"a\u0003#f]N,g+Z2u_J\fa\u0001\\3oORDWCAA\u0001\u0003)\t7\r^5wKNK'0Z\u0001\u0005e\u0016\u0004(/F\u0001Y\u0003I\t7\r^5wK.+\u0017p]%uKJ\fGo\u001c:\u0016\u0005\u0005=\u0002#\u00025\u00022\u0005\u0005\u0011bAA\u001a[\nA\u0011\n^3sCR|'/\u0001\bbGRLg/Z%uKJ\fGo\u001c:\u0016\u0005\u0005e\u0002#\u00025\u00022\u0005m\u0002C\u0002\u0013\u0002>\u0005\u0005Q&C\u0002\u0002@\u0015\u0012a\u0001V;qY\u0016\u0014\u0014\u0001F1di&4XMV1mk\u0016\u001c\u0018\n^3sCR|'/\u0006\u0002\u0002FA!\u0001.!\r.\u0003!!xn\u0015;sS:<GCAA&!\u0011\ti%!\u0016\u000f\t\u0005=\u0013\u0011\u000b\t\u0003\u0005\u0016J1!a\u0015&\u0003\u0019\u0001&/\u001a3fM&!\u0011qKA-\u0005\u0019\u0019FO]5oO*\u0019\u00111K\u0013\u0002\u0017Mc\u0017nY3WK\u000e$xN\u001d\t\u0003UI\u0019\"AE\u0012\u0015\u0005\u0005u\u0013\u0001C:dC2\f'o\u00144\u0016\r\u0005\u001d\u0014\u0011PA?+\t\tI\u0007\u0005\u0005\u0002l\u0005E\u0014QOA>\u001b\t\tiGC\u0002\u0002pq\tqa];qa>\u0014H/\u0003\u0003\u0002t\u00055$\u0001C*dC2\f'o\u00144\u0011\r)\u0002\u0011qOA>!\rq\u0013\u0011\u0010\u0003\u00067R\u0011\r!\r\t\u0004]\u0005uDABA@)\t\u0007\u0011GA\u0001U\u0003M\u0019\u0017M\\'ba.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t+!\t))!%\u0002\u0016\u0006eE\u0003BAD\u0003?\u0003b\"a\u001b\u0002\n\u00065\u0015\u0011AAJ\u0003/\u000bi*\u0003\u0003\u0002\f\u00065$aE\"b]6\u000b\u0007oS3z-\u0006dW/\u001a)bSJ\u001c\bC\u0002\u0016\u0001\u0003\u001f\u000b\u0019\nE\u0002/\u0003##QaW\u000bC\u0002E\u00022ALAK\t\u0015\u0001TC1\u00012!\rq\u0013\u0011\u0014\u0003\u0007\u00037+\"\u0019A\u0019\u0003\u0005Y\u0013\u0004#\u0002\u0016\u0002\u001e\u0005]\u0005\"CAQ+\u0005\u0005\t9AAR\u0003))g/\u001b3f]\u000e,GE\r\t\u0005cR\f9*\u0001\u0007dC:l\u0015\r\u001d,bYV,7/\u0006\u0005\u0002*\u0006U\u0016\u0011XA_)\u0011\tY+!1\u0011\u0019\u0005-\u0014QVAY\u0003o\u000bY,a0\n\t\u0005=\u0016Q\u000e\u0002\r\u0007\u0006tW*\u00199WC2,Xm\u001d\t\u0007U\u0001\t\u0019,a.\u0011\u00079\n)\fB\u0003\\-\t\u0007\u0011\u0007E\u0002/\u0003s#Q\u0001\r\fC\u0002E\u00022ALA_\t\u0019\tYJ\u0006b\u0001cA)!&!\b\u0002<\"I\u00111\u0019\f\u0002\u0002\u0003\u000f\u0011QY\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003B9u\u0003w\u000b!cY1o\u0007J,\u0017\r^3[KJ|7\u000fT5lKV1\u00111ZAl\u00037$b!!4\u0002`\u0006\u0015\b\u0003CA6\u0003\u001f\f\u0019.!8\n\t\u0005E\u0017Q\u000e\u0002\u0013\u0007\u0006t7I]3bi\u0016TVM]8t\u0019&\\W\r\u0005\u0004+\u0001\u0005U\u0017\u0011\u001c\t\u0004]\u0005]G!B.\u0018\u0005\u0004\t\u0004c\u0001\u0018\u0002\\\u0012)\u0001g\u0006b\u0001cA)!&!\b\u0002Z\"I\u0011\u0011]\f\u0002\u0002\u0003\u000f\u00111]\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004\u0003B9u\u00033D\u0011\"a:\u0018\u0003\u0003\u0005\u001d!!;\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0002l\u0006E\u0018\u0011\\\u0007\u0003\u0003[T1!a<\u001f\u0003\u001d\u0019Ho\u001c:bO\u0016LA!a=\u0002n\n!!,\u001a:p\u0003A\u0019\u0017M\\%uKJ\fG/\u001a,bYV,7/\u0006\u0004\u0002z\n\u0015!\u0011B\u000b\u0003\u0003w\u0004\u0002\"a\u001b\u0002~\n\u0005!qA\u0005\u0005\u0003\u007f\fiGA\tDC:$&/\u0019<feN,g+\u00197vKN\u0004bA\u000b\u0001\u0003\u0004\t\u001d\u0001c\u0001\u0018\u0003\u0006\u0011)1\f\u0007b\u0001cA\u0019aF!\u0003\u0005\u000bAB\"\u0019A\u0019\u0002/\r\fg.\u0013;fe\u0006$XmS3z-\u0006dW/\u001a)bSJ\u001cXC\u0002B\b\u00057\u0011y\"\u0006\u0002\u0003\u0012AQ\u00111\u000eB\n\u0005/\t\tA!\b\n\t\tU\u0011Q\u000e\u0002\u0019\u0007\u0006tGK]1wKJ\u001cXmS3z-\u0006dW/\u001a)bSJ\u001c\bC\u0002\u0016\u0001\u00053\u0011i\u0002E\u0002/\u00057!QaW\rC\u0002E\u00022A\fB\u0010\t\u0015\u0001\u0014D1\u00012\u0003I\u0019\u0017M\u001c+sC:\u001chm\u001c:n-\u0006dW/Z:\u0016\r\t\u0015\"\u0011\u0007B\u001b+\t\u00119\u0003\u0005\u0005\u0002l\t%\"Q\u0006B\u001a\u0013\u0011\u0011Y#!\u001c\u0003%\r\u000bg\u000e\u0016:b]N4wN]7WC2,Xm\u001d\t\u0007U\u0001\u0011yCa\r\u0011\u00079\u0012\t\u0004B\u0003\\5\t\u0007\u0011\u0007E\u0002/\u0005k!Q\u0001\r\u000eC\u0002E\u0002"
)
public class SliceVector implements Vector {
   public final Tensor tensor;
   private final IndexedSeq slices;
   public final ClassTag breeze$linalg$SliceVector$$evidence$1;

   public static CanTransformValues canTransformValues() {
      return SliceVector$.MODULE$.canTransformValues();
   }

   public static CanTraverseKeyValuePairs canIterateKeyValuePairs() {
      return SliceVector$.MODULE$.canIterateKeyValuePairs();
   }

   public static CanTraverseValues canIterateValues() {
      return SliceVector$.MODULE$.canIterateValues();
   }

   public static CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$4, final Zero evidence$5) {
      return SliceVector$.MODULE$.canCreateZerosLike(evidence$4, evidence$5);
   }

   public static CanMapValues canMapValues(final ClassTag evidence$3) {
      return SliceVector$.MODULE$.canMapValues(evidence$3);
   }

   public static CanMapKeyValuePairs canMapKeyValuePairs(final ClassTag evidence$2) {
      return SliceVector$.MODULE$.canMapKeyValuePairs(evidence$2);
   }

   public static ScalarOf scalarOf() {
      return SliceVector$.MODULE$.scalarOf();
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

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$(this, fn, canMapValues);
   }

   public Object map$mcZ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcZ$sp$(this, fn, canMapValues);
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

   public void foreach(final Function1 fn) {
      VectorLike.foreach$(this, fn);
   }

   public void foreach$mcZ$sp(final Function1 fn) {
      VectorLike.foreach$mcZ$sp$(this, fn);
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

   public Tensor tensor() {
      return this.tensor;
   }

   public IndexedSeq slices() {
      return this.slices;
   }

   public Object apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public void update(final int i, final Object v) {
      this.tensor().update(this.slices().apply(i), v);
   }

   public DenseVector copy() {
      return (DenseVector)DenseVector$.MODULE$.apply((Seq)this.slices().map((i) -> this.tensor().apply(i)), this.breeze$linalg$SliceVector$$evidence$1);
   }

   public int length() {
      return this.slices().length();
   }

   public int activeSize() {
      return this.slices().length();
   }

   public SliceVector repr() {
      return this;
   }

   public Iterator activeKeysIterator() {
      return this.keysIterator();
   }

   public Iterator activeIterator() {
      return this.iterator();
   }

   public Iterator activeValuesIterator() {
      return this.valuesIterator();
   }

   public String toString() {
      return this.valuesIterator().mkString("SliceVector(", ", ", ")");
   }

   public Tensor tensor$mcID$sp() {
      return this.tensor();
   }

   public Tensor tensor$mcIF$sp() {
      return this.tensor();
   }

   public Tensor tensor$mcII$sp() {
      return this.tensor();
   }

   public Tensor tensor$mcIJ$sp() {
      return this.tensor();
   }

   public double apply$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(i));
   }

   public float apply$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(i));
   }

   public int apply$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(i));
   }

   public long apply$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(i));
   }

   public void update$mcD$sp(final int i, final double v) {
      this.update(i, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcF$sp(final int i, final float v) {
      this.update(i, BoxesRunTime.boxToFloat(v));
   }

   public void update$mcI$sp(final int i, final int v) {
      this.update(i, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.update(i, BoxesRunTime.boxToLong(v));
   }

   public DenseVector copy$mcD$sp() {
      return this.copy();
   }

   public DenseVector copy$mcF$sp() {
      return this.copy();
   }

   public DenseVector copy$mcI$sp() {
      return this.copy();
   }

   public DenseVector copy$mcJ$sp() {
      return this.copy();
   }

   public SliceVector repr$mcID$sp() {
      return this.repr();
   }

   public SliceVector repr$mcIF$sp() {
      return this.repr();
   }

   public SliceVector repr$mcII$sp() {
      return this.repr();
   }

   public SliceVector repr$mcIJ$sp() {
      return this.repr();
   }

   public Object apply$mcI$sp(final int i) {
      return this.tensor().apply(this.slices().apply(i));
   }

   public boolean specInstance$() {
      return false;
   }

   public SliceVector(final Tensor tensor, final IndexedSeq slices, final ClassTag evidence$1) {
      this.tensor = tensor;
      this.slices = slices;
      this.breeze$linalg$SliceVector$$evidence$1 = evidence$1;
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      VectorLike.$init$(this);
      Vector.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
