package org.apache.spark.api.python;

import net.razorvine.pickle.Pickler;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.Tuple2;
import scala.Array.;
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
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%wA\u0002\u000f\u001e\u0011\u0003\tsE\u0002\u0004*;!\u0005\u0011E\u000b\u0005\u0006o\u0005!\t!\u000f\u0004\u0005u\u0005\u00011\bC\u00038\u0007\u0011\u0005q\tC\u0003K\u0007\u0011\u00053\nC\u0004Z\u0003\u0001\u0007I\u0011\u0002.\t\u000fy\u000b\u0001\u0019!C\u0005?\"1Q-\u0001Q!\nmCQAZ\u0001\u0005\u0002\u001dDQ\u0001[\u0001\u0005\u0002%4aa`\u0001\u0001C\u0005\u0005\u0001BCA\u0012\u0017\t\u0005\t\u0015!\u0003\u0002&!1qg\u0003C\u0001\u0003OA\u0001\"Q\u0006C\u0002\u0013%\u0011Q\u0006\u0005\t\u0003oY\u0001\u0015!\u0003\u00020!I\u0011\u0011H\u0006A\u0002\u0013%\u00111\b\u0005\n\u0003\u0007Z\u0001\u0019!C\u0005\u0003\u000bB\u0001\"!\u0013\fA\u0003&\u0011Q\b\u0005\n\u0003\u0017Z!\u0019!C\u0005\u0003\u001bB\u0001\"a\u0018\fA\u0003%\u0011q\n\u0005\u0007\u0003CZA\u0011\t.\t\u000f\u0005\r4\u0002\"\u0011\u0002f!9\u0011qM\u0001\u0005\u0002\u0005%\u0004bBA>\u0003\u0011\u0005\u0011Q\u0010\u0005\b\u0003\u000f\u000bA\u0011BAE\u0011\u001d\t9*\u0001C\u0001\u00033Cq!a,\u0002\t\u0003\t\t,A\u0005TKJ$U-\u0016;jY*\u0011adH\u0001\u0007af$\bn\u001c8\u000b\u0005\u0001\n\u0013aA1qS*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014x\r\u0005\u0002)\u00035\tQDA\u0005TKJ$U-\u0016;jYN\u0019\u0011aK\u0019\u0011\u00051zS\"A\u0017\u000b\u00039\nQa]2bY\u0006L!\u0001M\u0017\u0003\r\u0005s\u0017PU3g!\t\u0011T'D\u00014\u0015\t!\u0014%\u0001\u0005j]R,'O\\1m\u0013\t14GA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012a\n\u0002\u0015\u0005f$X-\u0011:sCf\u001cuN\\:ueV\u001cGo\u001c:\u0014\u0005\ra\u0004CA\u001fG\u001b\u0005q$BA A\u0003\u001dy'M[3diNT!!\u0011\"\u0002\rAL7m\u001b7f\u0015\t\u0019E)A\u0005sCj|'O^5oK*\tQ)A\u0002oKRL!A\u000f \u0015\u0003!\u0003\"!S\u0002\u000e\u0003\u0005\t\u0011bY8ogR\u0014Xo\u0019;\u0015\u00051#\u0006CA'S\u001b\u0005q%BA(Q\u0003\u0011a\u0017M\\4\u000b\u0003E\u000bAA[1wC&\u00111K\u0014\u0002\u0007\u001f\nTWm\u0019;\t\u000bU+\u0001\u0019\u0001,\u0002\t\u0005\u0014xm\u001d\t\u0004Y]c\u0015B\u0001-.\u0005\u0015\t%O]1z\u0003-Ig.\u001b;jC2L'0\u001a3\u0016\u0003m\u0003\"\u0001\f/\n\u0005uk#a\u0002\"p_2,\u0017M\\\u0001\u0010S:LG/[1mSj,Gm\u0018\u0013fcR\u0011\u0001m\u0019\t\u0003Y\u0005L!AY\u0017\u0003\tUs\u0017\u000e\u001e\u0005\bI\u001e\t\t\u00111\u0001\\\u0003\rAH%M\u0001\rS:LG/[1mSj,G\rI\u0001\u000bS:LG/[1mSj,G#\u00011\u0002\u0017Q|'*\u0019<b\u0003J\u0014\u0018-\u001f\u000b\u0003Ur\u00042a[7p\u001b\u0005a'BA) \u0013\tqGNA\u0004KCZ\f'\u000b\u0012#1\u0005A\u001c\bc\u0001\u0017XcB\u0011!o\u001d\u0007\u0001\t%!(\"!A\u0001\u0002\u000b\u0005QOA\u0002`IE\n\"A^=\u0011\u00051:\u0018B\u0001=.\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\f>\n\u0005ml#aA!os\")QP\u0003a\u0001}\u0006!!N\u001d3e!\rYW.\u001f\u0002\u0013\u0003V$xNQ1uG\",G\rU5dW2,'o\u0005\u0003\fW\u0005\r\u0001CBA\u0003\u0003+\tYB\u0004\u0003\u0002\b\u0005Ea\u0002BA\u0005\u0003\u001fi!!a\u0003\u000b\u0007\u00055\u0001(\u0001\u0004=e>|GOP\u0005\u0002]%\u0019\u00111C\u0017\u0002\u000fA\f7m[1hK&!\u0011qCA\r\u0005!IE/\u001a:bi>\u0014(bAA\n[A!AfVA\u000f!\ra\u0013qD\u0005\u0004\u0003Ci#\u0001\u0002\"zi\u0016\fA!\u001b;feB)\u0011QAA\u000bsR!\u0011\u0011FA\u0016!\tI5\u0002C\u0004\u0002$5\u0001\r!!\n\u0016\u0005\u0005=\u0002\u0003BA\u0019\u0003gi\u0011\u0001Q\u0005\u0004\u0003k\u0001%a\u0002)jG.dWM]\u0001\ba&\u001c7\u000e\\3!\u0003\u0015\u0011\u0017\r^2i+\t\ti\u0004E\u0002-\u0003\u007fI1!!\u0011.\u0005\rIe\u000e^\u0001\nE\u0006$8\r[0%KF$2\u0001YA$\u0011!!\u0017#!AA\u0002\u0005u\u0012A\u00022bi\u000eD\u0007%\u0001\u0004ck\u001a4WM]\u000b\u0003\u0003\u001f\u0002R!!\u0015\u0002\\el!!a\u0015\u000b\t\u0005U\u0013qK\u0001\b[V$\u0018M\u00197f\u0015\r\tI&L\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA/\u0003'\u00121\"\u0011:sCf\u0014UO\u001a4fe\u00069!-\u001e4gKJ\u0004\u0013a\u00025bg:+\u0007\u0010^\u0001\u0005]\u0016DH\u000f\u0006\u0002\u0002\u001c\u0005a!.\u0019<b)>\u0004\u0016\u0010\u001e5p]R!\u00111NA7!\u0011YW.a\u0007\t\u000f\u0005=t\u00031\u0001\u0002r\u0005!!N\u0015#Ea\u0011\t\u0019(a\u001e\u0011\t-l\u0017Q\u000f\t\u0004e\u0006]DaCA=\u0003[\n\t\u0011!A\u0003\u0002U\u00141a\u0018\u00134\u00031\u0001\u0018\u0010\u001e5p]R{'*\u0019<b)\u0015q\u0018qPAB\u0011\u001d\t\t\t\u0007a\u0001\u0003W\nQ\u0001]=S\t\u0012Ca!!\"\u0019\u0001\u0004Y\u0016a\u00022bi\u000eDW\rZ\u0001\fG\",7m\u001b)jG.dW\r\u0006\u0003\u0002\f\u0006E\u0005#\u0002\u0017\u0002\u000en[\u0016bAAH[\t1A+\u001e9mKJBq!a%\u001a\u0001\u0004\t)*A\u0001u!\u0015a\u0013QR=z\u0003=\u0001\u0018-\u001b:S\t\u0012#v\u000eU=uQ>tGCBAN\u0003O\u000bY\u000b\u0005\u0004\u0002\u001e\u0006\r\u00161D\u0007\u0003\u0003?S1!!)\"\u0003\r\u0011H\rZ\u0005\u0005\u0003K\u000byJA\u0002S\t\u0012Cq!!)\u001b\u0001\u0004\tI\u000b\u0005\u0004\u0002\u001e\u0006\r\u0016Q\u0013\u0005\b\u0003[S\u0002\u0019AA\u001f\u0003%\u0011\u0017\r^2i'&TX-A\bqsRDwN\u001c+p!\u0006L'O\u0015#E+\u0019\t\u0019,a/\u0002BR1\u0011QWAc\u0003\u000f\u0004b!!(\u0002$\u0006]\u0006c\u0002\u0017\u0002\u000e\u0006e\u0016q\u0018\t\u0004e\u0006mFABA_7\t\u0007QOA\u0001L!\r\u0011\u0018\u0011\u0019\u0003\u0007\u0003\u0007\\\"\u0019A;\u0003\u0003YCq!!!\u001c\u0001\u0004\tY\n\u0003\u0004\u0002\u0006n\u0001\ra\u0017"
)
public final class SerDeUtil {
   public static RDD pythonToPairRDD(final RDD pyRDD, final boolean batched) {
      return SerDeUtil$.MODULE$.pythonToPairRDD(pyRDD, batched);
   }

   public static RDD pairRDDToPython(final RDD rdd, final int batchSize) {
      return SerDeUtil$.MODULE$.pairRDDToPython(rdd, batchSize);
   }

   public static JavaRDD pythonToJava(final JavaRDD pyRDD, final boolean batched) {
      return SerDeUtil$.MODULE$.pythonToJava(pyRDD, batched);
   }

   public static JavaRDD javaToPython(final JavaRDD jRDD) {
      return SerDeUtil$.MODULE$.javaToPython(jRDD);
   }

   public static JavaRDD toJavaArray(final JavaRDD jrdd) {
      return SerDeUtil$.MODULE$.toJavaArray(jrdd);
   }

   public static void initialize() {
      SerDeUtil$.MODULE$.initialize();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SerDeUtil$.MODULE$.LogStringContext(sc);
   }

   public static class ByteArrayConstructor extends net.razorvine.pickle.objects.ByteArrayConstructor {
      public Object construct(final Object[] args) {
         return args.length == 0 ? .MODULE$.emptyByteArray() : super.construct(args);
      }
   }

   public static class AutoBatchedPickler implements Iterator {
      private final Iterator iter;
      private final Pickler pickle;
      private int batch;
      private final ArrayBuffer buffer;

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

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
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

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Vector toVector() {
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

      private Pickler pickle() {
         return this.pickle;
      }

      private int batch() {
         return this.batch;
      }

      private void batch_$eq(final int x$1) {
         this.batch = x$1;
      }

      private ArrayBuffer buffer() {
         return this.buffer;
      }

      public boolean hasNext() {
         return this.iter.hasNext();
      }

      public byte[] next() {
         while(this.iter.hasNext() && this.buffer().length() < this.batch()) {
            this.buffer().$plus$eq(this.iter.next());
         }

         byte[] bytes = this.pickle().dumps(this.buffer().toArray(scala.reflect.ClassTag..MODULE$.Any()));
         int size = bytes.length;
         if (size < 1048576) {
            this.batch_$eq(this.batch() * 2);
         } else if (size > 10485760 && this.batch() > 1) {
            this.batch_$eq(this.batch() / 2);
         }

         this.buffer().clear();
         return bytes;
      }

      public AutoBatchedPickler(final Iterator iter) {
         this.iter = iter;
         IterableOnce.$init$(this);
         IterableOnceOps.$init$(this);
         Iterator.$init$(this);
         this.pickle = new Pickler(true, false);
         this.batch = 1;
         this.buffer = new ArrayBuffer();
      }
   }
}
