package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}fA\u0002\u0015*\u0003\u0003Ys\u0006\u0003\u0005F\u0001\t\u0005\t\u0015!\u0003G\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0019\t\u0006A\"\u0001*%\")Q\u000b\u0001C\u0001-\")!\f\u0001C\u00017\")\u0011\u000e\u0001C\u0001U\")A\u000e\u0001C\u0001[\")a\u000e\u0001C\u0001-\")q\u000e\u0001C\u0001a\"1\u0011\u000f\u0001Q!\n]CaA\u001d\u0001!\u0002\u0013a\u0006BB:\u0001A\u0003&1\u000e\u0003\u0004u\u0001\u0001\u0006K!\u0018\u0005\u0007k\u0002\u0001\u000b\u0015B,\t\rY\u0004\u0001\u0015)\u00036\u0011\u00199\b\u0001)C\u0005q\"1!\u0010\u0001Q\u0005\nm,a! \u0001!\u0002\u0013q\bbBA\u0003\u0001\u0011%\u0011q\u0001\u0005\b\u0003#\u0001A\u0011BA\n\r\u0019\t9\u0002\u0001\u0002\u0002\u001a!Q\u00111D\u000b\u0003\u0002\u0003\u0006I!!\b\t\r5+B\u0011AA\u0010\u0011\u0015)V\u0003\"\u0011W\u0011\u0015QV\u0003\"\u0011\\\u0011\u0015IW\u0003\"\u0011k\u0011\u0015aW\u0003\"\u0011n\u0011\u0015qW\u0003\"\u0011W\u0011\u0015yW\u0003\"\u0011q\u0011\u0019\tV\u0003\"\u0012\u0002(!9\u00111\u0006\u0001\u0005\u0002\u00055\u0002\u0002CA\u0018\u0001\u0001&I!!\r\t\u0011\u0005U\u0002\u0001)C\u0005\u0003oA\u0001\"!\u0010\u0001A\u0013%\u0011q\b\u0005\t\u0003\u000b\u0002\u0001\u0015\"\u0003\u0002H!9\u0011Q\n\u0001\u0005\u0002\u0005=\u0003bBA)\u0001\u0011\u0005\u00111\u000b\u0005\t\u0003O\u0003\u0001\u0015\"\u0003\u0002*\"9\u00111\u0018\u0001\u0005\u0002\u0005u&\u0001\u0004+sS\u0016LE/\u001a:bi>\u0014(B\u0001\u0016,\u0003%IW.\\;uC\ndWM\u0003\u0002-[\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00039\nQa]2bY\u0006,\"\u0001M\u001e\u0014\u0007\u0001\tT\u0007\u0005\u00023g5\tQ&\u0003\u00025[\t1\u0011I\\=SK\u001a\u00042AN\u001c:\u001b\u0005Y\u0013B\u0001\u001d,\u0005!IE/\u001a:bi>\u0014\bC\u0001\u001e<\u0019\u0001!a\u0001\u0010\u0001\u0005\u0006\u0004q$!\u0001+\u0004\u0001E\u0011qH\u0011\t\u0003e\u0001K!!Q\u0017\u0003\u000f9{G\u000f[5oOB\u0011!gQ\u0005\u0003\t6\u00121!\u00118z\u0003\u0015)G.Z7t!\r\u0011t)S\u0005\u0003\u00116\u0012Q!\u0011:sCf\u00042AS&:\u001b\u0005I\u0013B\u0001'*\u0005!IE/\u001a:bE2,\u0017A\u0002\u001fj]&$h\b\u0006\u0002P!B\u0019!\nA\u001d\t\u000b\u0015\u0013\u0001\u0019\u0001$\u0002\u000f\u001d,G/\u00127f[R\u0011\u0011h\u0015\u0005\u0006)\u000e\u0001\r!M\u0001\u0002q\u0006I\u0011N\\5u\t\u0016\u0004H\u000f[\u000b\u0002/B\u0011!\u0007W\u0005\u000336\u00121!\u00138u\u00039Ig.\u001b;BeJ\f\u0017p\u0015;bG.,\u0012\u0001\u0018\t\u0004e\u001dk\u0006c\u0001\u001aH=B\u0019!jS0+\u0005e\u00027&A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017!C;oG\",7m[3e\u0015\t1W&\u0001\u0006b]:|G/\u0019;j_:L!\u0001[2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007j]&$\bk\\:Ti\u0006\u001c7.F\u0001l!\r\u0011tiV\u0001\u000bS:LG/\u0011:sCf$U#A/\u0002\u0011%t\u0017\u000e\u001e)pg\u0012\u000b1\"\u001b8jiN+(-\u0013;feV\tQ'A\u0003eKB$\b.\u0001\u0006beJ\f\u0017p\u0015;bG.\f\u0001\u0002]8t'R\f7m[\u0001\u0007CJ\u0014\u0018-\u001f#\u0002\tA|7\u000fR\u0001\bgV\u0014\u0017\n^3s\u0003!9W\r^#mK6\u001cHC\u0001$z\u0011\u0015!\u0006\u00031\u0001J\u0003A\u0019w\u000e\u001c7jg&|g\u000eV8BeJ\f\u0017\u0010\u0006\u0002Gy\")A+\u0005a\u0001\u0013\nq1\u000b\u001d7ji&#XM]1u_J\u001c\b#\u0002\u001a\u0000\u0003\u0007)\u0014bAA\u0001[\t1A+\u001e9mKJ\u0002BAM@6/\u00061\u0011n\u001d+sS\u0016$B!!\u0003\u0002\u0010A\u0019!'a\u0003\n\u0007\u00055QFA\u0004C_>dW-\u00198\t\u000bQ\u001b\u0002\u0019A\u0019\u0002\u0017%\u001c8i\u001c8uC&tWM\u001d\u000b\u0005\u0003\u0013\t)\u0002C\u0003U)\u0001\u0007\u0011GA\u0006EkBLE/\u001a:bi>\u00148CA\u000bP\u0003\tA8O\u000b\u0002GAR!\u0011\u0011EA\u0013!\r\t\u0019#F\u0007\u0002\u0001!9\u00111D\fA\u0002\u0005uAcA\u001d\u0002*!)AK\ba\u0001c\u0005YA-\u001e9Ji\u0016\u0014\u0018\r^8s+\u0005y\u0015a\u00038fo&#XM]1u_J$2aTA\u001a\u0011\u0019\tY\u0002\ta\u0001\r\u0006\u0001\u0012\u000e^3sCR|'oV5uQNK'0\u001a\u000b\u0005\u0003\u0007\tI\u0004\u0003\u0004\u0002<\u0005\u0002\rAR\u0001\u0004CJ\u0014\u0018\u0001E1se\u0006LHk\\%uKJ\fGo\u001c:t)\u0011\t\t%a\u0011\u0011\u0007\u0005\r\"\u0003\u0003\u0004\u0002<\t\u0002\rAR\u0001\u000bgBd\u0017\u000e^!se\u0006LH\u0003BA!\u0003\u0013Ba!a\u0013$\u0001\u00041\u0015AA1e\u0003\u001dA\u0017m\u001d(fqR,\"!!\u0003\u0002\t9,\u0007\u0010\u001e\u000b\u0002s!*Q%a\u0016\u0002lA)!'!\u0017\u0002^%\u0019\u00111L\u0017\u0003\rQD'o\\<t!\u0011\ty&!\u001a\u000f\u0007I\n\t'C\u0002\u0002d5\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002h\u0005%$A\u0006(p'V\u001c\u0007.\u00127f[\u0016tG/\u0012=dKB$\u0018n\u001c8\u000b\u0007\u0005\rT&M\u0004\u001f\u0003[\n\u0019)!*\u0011\t\u0005=\u0014Q\u0010\b\u0005\u0003c\nI\bE\u0002\u0002t5j!!!\u001e\u000b\u0007\u0005]T(\u0001\u0004=e>|GOP\u0005\u0004\u0003wj\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0000\u0005\u0005%AB*ue&twMC\u0002\u0002|5\n\u0014bIAC\u0003\u0017\u000bY*!$\u0016\t\u0005\u001d\u0015\u0011R\u000b\u0003\u0003[\"a\u0001P\u001fC\u0002\u0005M\u0015\u0002BAG\u0003\u001f\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n$bAAI[\u00051A\u000f\u001b:poN\f2aPAK!\u0011\ty&a&\n\t\u0005e\u0015\u0011\u000e\u0002\n)\"\u0014xn^1cY\u0016\f\u0014bIAO\u0003?\u000b\t+!%\u000f\u0007I\ny*C\u0002\u0002\u00126\nTA\t\u001a.\u0003G\u0013Qa]2bY\u0006\f4AJA/\u0003\u0015qW\r\u001f;1)\u0015I\u00141VAW\u0011\u0015)e\u00051\u0001G\u0011\u0019\tyK\na\u0001/\u0006\t\u0011\u000eK\u0002'\u0003g\u0003B!!.\u000286\tQ-C\u0002\u0002:\u0016\u0014q\u0001^1jYJ,7-A\u0003ta2LG/\u0006\u0002\u0002B\u0001"
)
public abstract class TrieIterator implements Iterator {
   private final Iterable[] elems;
   public int scala$collection$immutable$TrieIterator$$depth;
   public final Iterable[][] scala$collection$immutable$TrieIterator$$arrayStack;
   public int[] scala$collection$immutable$TrieIterator$$posStack;
   public Iterable[] scala$collection$immutable$TrieIterator$$arrayD;
   public int scala$collection$immutable$TrieIterator$$posD;
   public Iterator scala$collection$immutable$TrieIterator$$subIter;

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

   public Map toMap(final .less.colon.less ev) {
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

   public scala.collection.Iterable reversed() {
      return IterableOnceOps.reversed$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IterableOnce.stepper$(this, shape);
   }

   public int knownSize() {
      return IterableOnce.knownSize$(this);
   }

   public abstract Object getElem(final Object x);

   public int initDepth() {
      return 0;
   }

   public Iterable[][] initArrayStack() {
      return new Iterable[6][];
   }

   public int[] initPosStack() {
      return new int[6];
   }

   public Iterable[] initArrayD() {
      return this.elems;
   }

   public int initPosD() {
      return 0;
   }

   public Iterator initSubIter() {
      return null;
   }

   private Iterable[] getElems(final Iterable x) {
      AbstractIterable[] var10000;
      if (x instanceof OldHashMap.HashTrieMap) {
         OldHashMap.HashTrieMap var4 = (OldHashMap.HashTrieMap)x;
         var10000 = (AbstractIterable[])var4.elems();
      } else {
         if (!(x instanceof OldHashSet.HashTrieSet)) {
            throw new MatchError(x);
         }

         OldHashSet.HashTrieSet var5 = (OldHashSet.HashTrieSet)x;
         var10000 = (AbstractIterable[])var5.elems();
      }

      return (Iterable[])var10000;
   }

   private Iterable[] collisionToArray(final Iterable x) {
      AbstractIterable[] var10000;
      if (x instanceof OldHashMap.OldHashMapCollision1) {
         OldHashMap.OldHashMapCollision1 var4 = (OldHashMap.OldHashMapCollision1)x;
         var10000 = (AbstractIterable[])((IterableOnceOps)var4.kvs().map((xx) -> (OldHashMap)OldHashMap$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{xx}))))).toArray(scala.reflect.ClassTag..MODULE$.apply(OldHashMap.class));
      } else {
         if (!(x instanceof OldHashSet.OldHashSetCollision1)) {
            throw new MatchError(x);
         }

         OldHashSet.OldHashSetCollision1 var5 = (OldHashSet.OldHashSetCollision1)x;
         var10000 = (AbstractIterable[])((IterableOnceOps)var5.ks().map((xx) -> (OldHashSet)OldHashSet$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{xx})))).toArray(scala.reflect.ClassTag..MODULE$.apply(OldHashSet.class));
      }

      return (Iterable[])var10000;
   }

   private boolean isTrie(final Object x) {
      return x instanceof OldHashMap.HashTrieMap ? true : x instanceof OldHashSet.HashTrieSet;
   }

   private boolean isContainer(final Object x) {
      return x instanceof OldHashMap.OldHashMap1 ? true : x instanceof OldHashSet.OldHashSet1;
   }

   public TrieIterator dupIterator() {
      return new DupIterator(this.elems);
   }

   private TrieIterator newIterator(final Iterable[] xs) {
      return new TrieIterator(xs) {
         // $FF: synthetic field
         private final TrieIterator $outer;

         public final Object getElem(final Object x) {
            return this.$outer.getElem(x);
         }

         public {
            if (TrieIterator.this == null) {
               throw null;
            } else {
               this.$outer = TrieIterator.this;
            }
         }
      };
   }

   private Tuple2 iteratorWithSize(final Iterable[] arr) {
      return new Tuple2(this.newIterator(arr), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])arr), (x$1) -> BoxesRunTime.boxToInteger($anonfun$iteratorWithSize$1(x$1)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   private Tuple2 arrayToIterators(final Iterable[] arr) {
      Tuple2 var4 = scala.collection.ArrayOps..MODULE$.splitAt$extension(scala.Predef..MODULE$.refArrayOps((Object[])arr), arr.length / 2);
      if (var4 != null) {
         Iterable[] fst = (Iterable[])var4._1();
         Iterable[] snd = (Iterable[])var4._2();
         Tuple2 var3 = new Tuple2(fst, snd);
         Iterable[] fst = (Iterable[])var3._1();
         Iterable[] snd = (Iterable[])var3._2();
         return new Tuple2(this.iteratorWithSize(snd), this.newIterator(fst));
      } else {
         throw new MatchError(var4);
      }
   }

   private Tuple2 splitArray(final Iterable[] ad) {
      while(ad.length <= 1) {
         Iterable var5 = ad[0];
         if (var5 instanceof OldHashMap.OldHashMapCollision1 ? true : var5 instanceof OldHashSet.OldHashSetCollision1) {
            return this.arrayToIterators(this.collisionToArray(ad[0]));
         }

         ad = this.getElems(ad[0]);
      }

      return this.arrayToIterators(ad);
   }

   public boolean hasNext() {
      return this.scala$collection$immutable$TrieIterator$$subIter != null || this.scala$collection$immutable$TrieIterator$$depth >= 0;
   }

   public Object next() throws NoSuchElementException {
      if (this.scala$collection$immutable$TrieIterator$$subIter != null) {
         Object el = this.scala$collection$immutable$TrieIterator$$subIter.next();
         if (!this.scala$collection$immutable$TrieIterator$$subIter.hasNext()) {
            this.scala$collection$immutable$TrieIterator$$subIter = null;
         }

         return el;
      } else {
         return this.next0(this.scala$collection$immutable$TrieIterator$$arrayD, this.scala$collection$immutable$TrieIterator$$posD);
      }
   }

   private Object next0(final Iterable[] elems, final int i) {
      while(true) {
         if (i == elems.length - 1) {
            --this.scala$collection$immutable$TrieIterator$$depth;
            if (this.scala$collection$immutable$TrieIterator$$depth >= 0) {
               this.scala$collection$immutable$TrieIterator$$arrayD = this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$depth];
               this.scala$collection$immutable$TrieIterator$$posD = this.scala$collection$immutable$TrieIterator$$posStack[this.scala$collection$immutable$TrieIterator$$depth];
               this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$depth] = null;
            } else {
               this.scala$collection$immutable$TrieIterator$$arrayD = null;
               this.scala$collection$immutable$TrieIterator$$posD = 0;
            }
         } else {
            ++this.scala$collection$immutable$TrieIterator$$posD;
         }

         Iterable m = elems[i];
         if (this.isContainer(m)) {
            return this.getElem(m);
         }

         if (!this.isTrie(m)) {
            this.scala$collection$immutable$TrieIterator$$subIter = m.iterator();
            return this.next();
         }

         if (this.scala$collection$immutable$TrieIterator$$depth >= 0) {
            this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$depth] = this.scala$collection$immutable$TrieIterator$$arrayD;
            this.scala$collection$immutable$TrieIterator$$posStack[this.scala$collection$immutable$TrieIterator$$depth] = this.scala$collection$immutable$TrieIterator$$posD;
         }

         ++this.scala$collection$immutable$TrieIterator$$depth;
         this.scala$collection$immutable$TrieIterator$$arrayD = this.getElems(m);
         this.scala$collection$immutable$TrieIterator$$posD = 0;
         Iterable[] var10000 = this.getElems(m);
         i = 0;
         elems = var10000;
      }
   }

   public Tuple2 split() {
      if (this.scala$collection$immutable$TrieIterator$$arrayD != null && this.scala$collection$immutable$TrieIterator$$depth == 0 && this.scala$collection$immutable$TrieIterator$$posD == 0) {
         return this.splitArray(this.scala$collection$immutable$TrieIterator$$arrayD);
      } else if (this.scala$collection$immutable$TrieIterator$$subIter != null) {
         ArrayBuffer buff = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.empty().$plus$plus$eq(this.scala$collection$immutable$TrieIterator$$subIter);
         this.scala$collection$immutable$TrieIterator$$subIter = null;
         return new Tuple2(new Tuple2(buff.iterator(), BoxesRunTime.boxToInteger(buff.length())), this);
      } else if (this.scala$collection$immutable$TrieIterator$$depth > 0) {
         if (this.scala$collection$immutable$TrieIterator$$posStack[0] == this.scala$collection$immutable$TrieIterator$$arrayStack[0].length - 1) {
            Iterable[] snd = (Iterable[])((Object[])(new Iterable[]{(Iterable)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.scala$collection$immutable$TrieIterator$$arrayStack[0]))}));
            int szsnd = snd[0].size();
            --this.scala$collection$immutable$TrieIterator$$depth;
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.scala$collection$immutable$TrieIterator$$arrayStack.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> this.scala$collection$immutable$TrieIterator$$arrayStack[i - 1] = this.scala$collection$immutable$TrieIterator$$arrayStack[i]);
            this.scala$collection$immutable$TrieIterator$$arrayStack[this.scala$collection$immutable$TrieIterator$$arrayStack.length - 1] = (Iterable[])((Object[])(new Iterable[]{null}));
            this.scala$collection$immutable$TrieIterator$$posStack = (int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.intArrayOps(this.scala$collection$immutable$TrieIterator$$posStack))), new int[]{0}, scala.reflect.ClassTag..MODULE$.Int());
            return new Tuple2(new Tuple2(this.newIterator(snd), BoxesRunTime.boxToInteger(szsnd)), this);
         } else {
            Tuple2 var7 = scala.collection.ArrayOps..MODULE$.splitAt$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.scala$collection$immutable$TrieIterator$$arrayStack[0]), this.scala$collection$immutable$TrieIterator$$arrayStack[0].length - (this.scala$collection$immutable$TrieIterator$$arrayStack[0].length - this.scala$collection$immutable$TrieIterator$$posStack[0] + 1) / 2);
            if (var7 != null) {
               Iterable[] fst = (Iterable[])var7._1();
               Iterable[] snd = (Iterable[])var7._2();
               Tuple2 var6 = new Tuple2(fst, snd);
               Iterable[] fst = (Iterable[])var6._1();
               Iterable[] snd = (Iterable[])var6._2();
               this.scala$collection$immutable$TrieIterator$$arrayStack[0] = fst;
               return new Tuple2(this.iteratorWithSize(snd), this);
            } else {
               throw new MatchError(var7);
            }
         }
      } else if (this.scala$collection$immutable$TrieIterator$$posD == this.scala$collection$immutable$TrieIterator$$arrayD.length - 1) {
         Iterable m = this.scala$collection$immutable$TrieIterator$$arrayD[this.scala$collection$immutable$TrieIterator$$posD];
         return this.arrayToIterators(this.isTrie(m) ? this.getElems(m) : this.collisionToArray(m));
      } else {
         Tuple2 var14 = scala.collection.ArrayOps..MODULE$.splitAt$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.scala$collection$immutable$TrieIterator$$arrayD), this.scala$collection$immutable$TrieIterator$$arrayD.length - (this.scala$collection$immutable$TrieIterator$$arrayD.length - this.scala$collection$immutable$TrieIterator$$posD + 1) / 2);
         if (var14 != null) {
            Iterable[] fst = (Iterable[])var14._1();
            Iterable[] snd = (Iterable[])var14._2();
            Tuple2 var13 = new Tuple2(fst, snd);
            Iterable[] fst = (Iterable[])var13._1();
            Iterable[] snd = (Iterable[])var13._2();
            this.scala$collection$immutable$TrieIterator$$arrayD = fst;
            return new Tuple2(this.iteratorWithSize(snd), this);
         } else {
            throw new MatchError(var14);
         }
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$iteratorWithSize$1(final Iterable x$1) {
      return x$1.size();
   }

   public TrieIterator(final Iterable[] elems) {
      this.elems = elems;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      Iterator.$init$(this);
      this.scala$collection$immutable$TrieIterator$$depth = this.initDepth();
      this.scala$collection$immutable$TrieIterator$$arrayStack = this.initArrayStack();
      this.scala$collection$immutable$TrieIterator$$posStack = this.initPosStack();
      this.scala$collection$immutable$TrieIterator$$arrayD = this.initArrayD();
      this.scala$collection$immutable$TrieIterator$$posD = this.initPosD();
      this.scala$collection$immutable$TrieIterator$$subIter = this.initSubIter();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class DupIterator extends TrieIterator {
      // $FF: synthetic field
      private final TrieIterator $outer;

      public int initDepth() {
         return this.$outer.scala$collection$immutable$TrieIterator$$depth;
      }

      public Iterable[][] initArrayStack() {
         return this.$outer.scala$collection$immutable$TrieIterator$$arrayStack;
      }

      public int[] initPosStack() {
         return this.$outer.scala$collection$immutable$TrieIterator$$posStack;
      }

      public Iterable[] initArrayD() {
         return this.$outer.scala$collection$immutable$TrieIterator$$arrayD;
      }

      public int initPosD() {
         return this.$outer.scala$collection$immutable$TrieIterator$$posD;
      }

      public Iterator initSubIter() {
         return this.$outer.scala$collection$immutable$TrieIterator$$subIter;
      }

      public final Object getElem(final Object x) {
         return this.$outer.getElem(x);
      }

      public DupIterator(final Iterable[] xs) {
         if (TrieIterator.this == null) {
            throw null;
         } else {
            this.$outer = TrieIterator.this;
            super(xs);
         }
      }
   }
}
