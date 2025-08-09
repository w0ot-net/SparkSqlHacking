package org.apache.spark.util.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee!\u0002\r\u001a\u0001u\u0019\u0003\u0002\u00033\u0001\u0005\u0003\u0005\u000b\u0011B3\t\u0011!\u0004!1!Q\u0001\f%D\u0001b\u001c\u0001\u0003\u0004\u0003\u0006Y\u0001\u001d\u0005\u0006c\u0002!\tA\u001d\u0005\u0006c\u0002!\t!\u001f\u0005\u000b\u007f\u0002\u0001\r\u00111A\u0005\u0012\u0005\u0005\u0001bCA\u0005\u0001\u0001\u0007\t\u0019!C\t\u0003\u0017A1\"a\u0006\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0002\u0004!Y\u0011\u0011\u0004\u0001A\u0002\u0003\u0007I\u0011BA\u000e\u0011-\t\u0019\u0003\u0001a\u0001\u0002\u0004%I!!\n\t\u0017\u0005%\u0002\u00011A\u0001B\u0003&\u0011Q\u0004\u0005\n\u0003W\u0001\u0001\u0019!C\u0005\u00037A\u0011\"!\f\u0001\u0001\u0004%I!a\f\t\u0011\u0005M\u0002\u0001)Q\u0005\u0003;Aq!!\u000e\u0001\t\u0003\n9\u0004C\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005\u001d\u0003\u0001\"\u0001\u0002J!9\u0011Q\n\u0001\u0005\u0002\u0005=\u0003bBA,\u0001\u0011\u0005\u0011\u0011\f\u0005\b\u0003C\u0002A\u0011AA2\u0011\u001d\tY\b\u0001C!\u0003{Bq!!\"\u0001\t\u0013\t9\tC\u0004\u0002\u000e\u0002!I!a$\u0003/A\u0013\u0018.\\5uSZ,7*Z=Pa\u0016t\u0007*Y:i\u001b\u0006\u0004(B\u0001\u000e\u001c\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u00039u\tA!\u001e;jY*\u0011adH\u0001\u0006gB\f'o\u001b\u0006\u0003A\u0005\na!\u00199bG\",'\"\u0001\u0012\u0002\u0007=\u0014x-F\u0002%{U\u001bB\u0001A\u0013,CB\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u00042\u0001L\u001b9\u001d\ti3G\u0004\u0002/e5\tqF\u0003\u00021c\u00051AH]8piz\u001a\u0001!C\u0001)\u0013\t!t%A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001C%uKJ\f'\r\\3\u000b\u0005Q:\u0003\u0003\u0002\u0014:wQK!AO\u0014\u0003\rQ+\b\u000f\\33!\taT\b\u0004\u0001\u0005\u0013y\u0002\u0001\u0015!A\u0001\u0006\u0004y$!A&\u0012\u0005\u0001\u001b\u0005C\u0001\u0014B\u0013\t\u0011uEA\u0004O_RD\u0017N\\4\u0011\u0005\u0019\"\u0015BA#(\u0005\r\te.\u001f\u0015\u0005{\u001dSu\n\u0005\u0002'\u0011&\u0011\u0011j\n\u0002\fgB,7-[1mSj,G-M\u0003$\u00172sUJ\u0004\u0002'\u0019&\u0011QjJ\u0001\u0005\u0019>tw-\r\u0003%[IB\u0013'B\u0012Q#N\u0013fB\u0001\u0014R\u0013\t\u0011v%A\u0002J]R\fD\u0001J\u00173QA\u0011A(\u0016\u0003\n-\u0002\u0001\u000b\u0011!AC\u0002}\u0012\u0011A\u0016\u0015\u0006+\u001eC&\fX\u0019\u0006G-c\u0015,T\u0019\u0005I5\u0012\u0004&M\u0003$!F[&+\r\u0003%[IB\u0013'B\u0012^=\u0002|fB\u0001\u0014_\u0013\tyv%\u0001\u0004E_V\u0014G.Z\u0019\u0005I5\u0012\u0004\u0006\u0005\u0002-E&\u00111m\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0010S:LG/[1m\u0007\u0006\u0004\u0018mY5usB\u0011aEZ\u0005\u0003O\u001e\u00121!\u00138u\u0003))g/\u001b3f]\u000e,G%\r\t\u0004U6\\T\"A6\u000b\u00051<\u0013a\u0002:fM2,7\r^\u0005\u0003].\u0014\u0001b\u00117bgN$\u0016mZ\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u00016n)\u00061A(\u001b8jiz\"\"a\u001d=\u0015\u0007Q4x\u000f\u0005\u0003v\u0001m\"V\"A\r\t\u000b!$\u00019A5\t\u000b=$\u00019\u00019\t\u000b\u0011$\u0001\u0019A3\u0015\u0003i$2\u0001^>~\u0011\u001daX!!AA\u0004%\f!\"\u001a<jI\u0016t7-\u001a\u00134\u0011\u001dqX!!AA\u0004A\f!\"\u001a<jI\u0016t7-\u001a\u00135\u0003\u001dy6.Z=TKR,\"!a\u0001\u0011\tU\f)aO\u0005\u0004\u0003\u000fI\"aC(qK:D\u0015m\u001d5TKR\f1bX6fsN+Go\u0018\u0013fcR!\u0011QBA\n!\r1\u0013qB\u0005\u0004\u0003#9#\u0001B+oSRD\u0011\"!\u0006\b\u0003\u0003\u0005\r!a\u0001\u0002\u0007a$\u0013'\u0001\u0005`W\u0016L8+\u001a;!\u0003\u001dyf/\u00197vKN,\"!!\b\u0011\t\u0019\ny\u0002V\u0005\u0004\u0003C9#!B!se\u0006L\u0018aC0wC2,Xm]0%KF$B!!\u0004\u0002(!I\u0011Q\u0003\u0006\u0002\u0002\u0003\u0007\u0011QD\u0001\t?Z\fG.^3tA\u0005Qql\u001c7e-\u0006dW/Z:\u0002\u001d}{G\u000e\u001a,bYV,7o\u0018\u0013fcR!\u0011QBA\u0019\u0011%\t)\"DA\u0001\u0002\u0004\ti\"A\u0006`_2$g+\u00197vKN\u0004\u0013\u0001B:ju\u0016,\u0012!Z\u0001\tG>tG/Y5ogR!\u0011QHA\"!\r1\u0013qH\u0005\u0004\u0003\u0003:#a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u0003\u000b\u0002\u0002\u0019A\u001e\u0002\u0003-\fQ!\u00199qYf$2\u0001VA&\u0011\u0019\t)%\u0005a\u0001w\u0005Iq-\u001a;Pe\u0016c7/\u001a\u000b\u0006)\u0006E\u00131\u000b\u0005\u0007\u0003\u000b\u0012\u0002\u0019A\u001e\t\r\u0005U#\u00031\u0001U\u0003%)Gn]3WC2,X-\u0001\u0004va\u0012\fG/\u001a\u000b\u0007\u0003\u001b\tY&!\u0018\t\r\u0005\u00153\u00031\u0001<\u0011\u0019\tyf\u0005a\u0001)\u0006\ta/A\u0006dQ\u0006tw-\u001a,bYV,Gc\u0002+\u0002f\u0005\u001d\u0014\u0011\u000f\u0005\u0007\u0003\u000b\"\u0002\u0019A\u001e\t\u0011\u0005%D\u0003\"a\u0001\u0003W\nA\u0002Z3gCVdGOV1mk\u0016\u0004BAJA7)&\u0019\u0011qN\u0014\u0003\u0011q\u0012\u0017P\\1nKzBq!a\u001d\u0015\u0001\u0004\t)(\u0001\u0006nKJ<WMV1mk\u0016\u0004RAJA<)RK1!!\u001f(\u0005%1UO\\2uS>t\u0017'\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\ty\b\u0005\u0003-\u0003\u0003C\u0014bAABo\tA\u0011\n^3sCR|'/\u0001\u0003he><H\u0003BA\u0007\u0003\u0013Ca!a#\u0017\u0001\u0004)\u0017a\u00038fo\u000e\u000b\u0007/Y2jif\fA!\\8wKR1\u0011QBAI\u0003+Ca!a%\u0018\u0001\u0004)\u0017AB8mIB{7\u000f\u0003\u0004\u0002\u0018^\u0001\r!Z\u0001\u0007]\u0016<\bk\\:"
)
public class PrimitiveKeyOpenHashMap implements Iterable, Serializable {
   private final ClassTag evidence$2;
   public OpenHashSet _keySet;
   public Object _values;
   public Object _oldValues;

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public String stringPrefix() {
      return Iterable.stringPrefix$(this);
   }

   public String toString() {
      return Iterable.toString$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return IterableFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return IterableFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return IterableFactoryDefaults.empty$(this);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public View view() {
      return IterableOps.view$(this);
   }

   public int sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   public int sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public Object filter(final Function1 pred) {
      return IterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return IterableOps.filterNot$(this, pred);
   }

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 partition(final Function1 p) {
      return IterableOps.partition$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IterableOps.takeRight$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return IterableOps.span$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IterableOps.dropRight$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return IterableOps.scanLeft$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object map(final Function1 f) {
      return IterableOps.map$(this, f);
   }

   public Object flatMap(final Function1 f) {
      return IterableOps.flatMap$(this, f);
   }

   public Object flatten(final Function1 asIterable) {
      return IterableOps.flatten$(this, asIterable);
   }

   public Object collect(final PartialFunction pf) {
      return IterableOps.collect$(this, pf);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return IterableOps.partitionMap$(this, f);
   }

   public Object concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zip(final IterableOnce that) {
      return IterableOps.zip$(this, that);
   }

   public Object zipWithIndex() {
      return IterableOps.zipWithIndex$(this);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return IterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return IterableOps.unzip3$(this, asTriple);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   public Object tapEach(final Function1 f) {
      return IterableOps.tapEach$(this, f);
   }

   /** @deprecated */
   public Object $plus$plus$colon(final IterableOnce that) {
      return IterableOps.$plus$plus$colon$(this, that);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
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

   public boolean isEmpty() {
      return IterableOnceOps.isEmpty$(this);
   }

   public boolean nonEmpty() {
      return IterableOnceOps.nonEmpty$(this);
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

   public Iterable reversed() {
      return IterableOnceOps.reversed$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IterableOnce.stepper$(this, shape);
   }

   public int knownSize() {
      return IterableOnce.knownSize$(this);
   }

   public OpenHashSet _keySet() {
      return this._keySet;
   }

   public void _keySet_$eq(final OpenHashSet x$1) {
      this._keySet = x$1;
   }

   public Object _values() {
      return this._values;
   }

   public void _values_$eq(final Object x$1) {
      this._values = x$1;
   }

   public Object _oldValues() {
      return this._oldValues;
   }

   public void _oldValues_$eq(final Object x$1) {
      this._oldValues = x$1;
   }

   public int size() {
      return this._keySet().size();
   }

   public boolean contains(final Object k) {
      return this._keySet().getPos(k) != OpenHashSet$.MODULE$.INVALID_POS();
   }

   public Object apply(final Object k) {
      int pos = this._keySet().getPos(k);
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos);
   }

   public Object getOrElse(final Object k, final Object elseValue) {
      int pos = this._keySet().getPos(k);
      return pos >= 0 ? scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos) : elseValue;
   }

   public void update(final Object k, final Object v) {
      int pos = this._keySet().addWithoutResize(k) & OpenHashSet$.MODULE$.POSITION_MASK();
      scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos, v);
      this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((Object)null);
   }

   public Object changeValue(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      int pos = this._keySet().addWithoutResize(k);
      if ((pos & OpenHashSet$.MODULE$.NONEXISTENCE_MASK()) != 0) {
         Object newValue = defaultValue.apply();
         scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos & OpenHashSet$.MODULE$.POSITION_MASK(), newValue);
         this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$move(oldPos, newPos));
         return newValue;
      } else {
         scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos, mergeValue.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos)));
         return scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos);
      }
   }

   public Iterator iterator() {
      return new Iterator() {
         private int pos;
         private Tuple2 nextPair;
         // $FF: synthetic field
         private final PrimitiveKeyOpenHashMap $outer;

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

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private int pos() {
            return this.pos;
         }

         private void pos_$eq(final int x$1) {
            this.pos = x$1;
         }

         private Tuple2 nextPair() {
            return this.nextPair;
         }

         private void nextPair_$eq(final Tuple2 x$1) {
            this.nextPair = x$1;
         }

         private Tuple2 computeNextPair() {
            this.pos_$eq(this.$outer._keySet().nextPos(this.pos()));
            if (this.pos() >= 0) {
               Tuple2 ret = new Tuple2(this.$outer._keySet().getValue(this.pos()), scala.runtime.ScalaRunTime..MODULE$.array_apply(this.$outer._values(), this.pos()));
               this.pos_$eq(this.pos() + 1);
               return ret;
            } else {
               return null;
            }
         }

         public boolean hasNext() {
            return this.nextPair() != null;
         }

         public Tuple2 next() {
            Tuple2 pair = this.nextPair();
            this.nextPair_$eq(this.computeNextPair());
            return pair;
         }

         public {
            if (PrimitiveKeyOpenHashMap.this == null) {
               throw null;
            } else {
               this.$outer = PrimitiveKeyOpenHashMap.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = 0;
               this.nextPair = this.computeNextPair();
            }
         }
      };
   }

   public void org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$grow(final int newCapacity) {
      this._oldValues_$eq(this._values());
      this._values_$eq(this.evidence$2.newArray(newCapacity));
   }

   public void org$apache$spark$util$collection$PrimitiveKeyOpenHashMap$$move(final int oldPos, final int newPos) {
      scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), newPos, scala.runtime.ScalaRunTime..MODULE$.array_apply(this._oldValues(), oldPos));
   }

   public OpenHashSet _keySet$mcI$sp() {
      return this._keySet();
   }

   public OpenHashSet _keySet$mcJ$sp() {
      return this._keySet();
   }

   public void _keySet$mcI$sp_$eq(final OpenHashSet x$1) {
      this._keySet_$eq(x$1);
   }

   public void _keySet$mcJ$sp_$eq(final OpenHashSet x$1) {
      this._keySet_$eq(x$1);
   }

   public double[] _values$mcD$sp() {
      return (double[])this._values();
   }

   public int[] _values$mcI$sp() {
      return (int[])this._values();
   }

   public long[] _values$mcJ$sp() {
      return (long[])this._values();
   }

   public void _values$mcD$sp_$eq(final double[] x$1) {
      this._values_$eq(x$1);
   }

   public void _values$mcI$sp_$eq(final int[] x$1) {
      this._values_$eq(x$1);
   }

   public void _values$mcJ$sp_$eq(final long[] x$1) {
      this._values_$eq(x$1);
   }

   public double[] _oldValues$mcD$sp() {
      return (double[])this._oldValues();
   }

   public int[] _oldValues$mcI$sp() {
      return (int[])this._oldValues();
   }

   public long[] _oldValues$mcJ$sp() {
      return (long[])this._oldValues();
   }

   public void _oldValues$mcD$sp_$eq(final double[] x$1) {
      this._oldValues_$eq(x$1);
   }

   public void _oldValues$mcI$sp_$eq(final int[] x$1) {
      this._oldValues_$eq(x$1);
   }

   public void _oldValues$mcJ$sp_$eq(final long[] x$1) {
      this._oldValues_$eq(x$1);
   }

   public boolean contains$mcI$sp(final int k) {
      return this.contains(BoxesRunTime.boxToInteger(k));
   }

   public boolean contains$mcJ$sp(final long k) {
      return this.contains(BoxesRunTime.boxToLong(k));
   }

   public double apply$mcID$sp(final int k) {
      return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(k)));
   }

   public int apply$mcII$sp(final int k) {
      return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(k)));
   }

   public long apply$mcIJ$sp(final int k) {
      return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToInteger(k)));
   }

   public double apply$mcJD$sp(final long k) {
      return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToLong(k)));
   }

   public int apply$mcJI$sp(final long k) {
      return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToLong(k)));
   }

   public long apply$mcJJ$sp(final long k) {
      return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToLong(k)));
   }

   public double getOrElse$mcID$sp(final int k, final double elseValue) {
      return BoxesRunTime.unboxToDouble(this.getOrElse(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToDouble(elseValue)));
   }

   public int getOrElse$mcII$sp(final int k, final int elseValue) {
      return BoxesRunTime.unboxToInt(this.getOrElse(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToInteger(elseValue)));
   }

   public long getOrElse$mcIJ$sp(final int k, final long elseValue) {
      return BoxesRunTime.unboxToLong(this.getOrElse(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToLong(elseValue)));
   }

   public double getOrElse$mcJD$sp(final long k, final double elseValue) {
      return BoxesRunTime.unboxToDouble(this.getOrElse(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToDouble(elseValue)));
   }

   public int getOrElse$mcJI$sp(final long k, final int elseValue) {
      return BoxesRunTime.unboxToInt(this.getOrElse(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToInteger(elseValue)));
   }

   public long getOrElse$mcJJ$sp(final long k, final long elseValue) {
      return BoxesRunTime.unboxToLong(this.getOrElse(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToLong(elseValue)));
   }

   public void update$mcID$sp(final int k, final double v) {
      this.update(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToDouble(v));
   }

   public void update$mcII$sp(final int k, final int v) {
      this.update(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToInteger(v));
   }

   public void update$mcIJ$sp(final int k, final long v) {
      this.update(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToLong(v));
   }

   public void update$mcJD$sp(final long k, final double v) {
      this.update(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToDouble(v));
   }

   public void update$mcJI$sp(final long k, final int v) {
      this.update(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJJ$sp(final long k, final long v) {
      this.update(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToLong(v));
   }

   public double changeValue$mcID$sp(final int k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToDouble(this.changeValue(BoxesRunTime.boxToInteger(k), defaultValue, mergeValue));
   }

   public int changeValue$mcII$sp(final int k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToInt(this.changeValue(BoxesRunTime.boxToInteger(k), defaultValue, mergeValue));
   }

   public long changeValue$mcIJ$sp(final int k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToLong(this.changeValue(BoxesRunTime.boxToInteger(k), defaultValue, mergeValue));
   }

   public double changeValue$mcJD$sp(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToDouble(this.changeValue(BoxesRunTime.boxToLong(k), defaultValue, mergeValue));
   }

   public int changeValue$mcJI$sp(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToInt(this.changeValue(BoxesRunTime.boxToLong(k), defaultValue, mergeValue));
   }

   public long changeValue$mcJJ$sp(final long k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToLong(this.changeValue(BoxesRunTime.boxToLong(k), defaultValue, mergeValue));
   }

   public boolean specInstance$() {
      return false;
   }

   public PrimitiveKeyOpenHashMap(final int initialCapacity, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.evidence$2 = evidence$2;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      if (!this.specInstance$()) {
         boolean var7;
         Predef var10000;
         label27: {
            label31: {
               var10000 = scala.Predef..MODULE$;
               ClassTag var10001 = scala.reflect.package..MODULE$.classTag(evidence$1);
               ClassTag var4 = scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Long());
               if (var10001 == null) {
                  if (var4 == null) {
                     break label31;
                  }
               } else if (var10001.equals(var4)) {
                  break label31;
               }

               var10001 = scala.reflect.package..MODULE$.classTag(evidence$1);
               ClassTag var5 = scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Int());
               if (var10001 == null) {
                  if (var5 == null) {
                     break label31;
                  }
               } else if (var10001.equals(var5)) {
                  break label31;
               }

               var7 = false;
               break label27;
            }

            var7 = true;
         }

         var10000.require(var7);
         this._keySet_$eq(new OpenHashSet(initialCapacity, evidence$1));
         this._values_$eq(evidence$2.newArray(this._keySet().capacity()));
         this._oldValues = null;
      }

   }

   public PrimitiveKeyOpenHashMap(final ClassTag evidence$3, final ClassTag evidence$4) {
      this(64, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
