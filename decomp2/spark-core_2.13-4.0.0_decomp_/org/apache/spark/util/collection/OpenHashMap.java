package org.apache.spark.util.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
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
   bytes = "\u0006\u0005\u0005uf!\u0002\u0010 \u0001\rJ\u0003\u0002C3\u0001\u0005\u0003\u0005\u000b\u0011\u00024\t\u0011%\u0004!1!Q\u0001\f)D\u0001\u0002\u001d\u0001\u0003\u0004\u0003\u0006Y!\u001d\u0005\u0006e\u0002!\ta\u001d\u0005\u0006e\u0002!\tA\u001f\u0005\n\u0003\u0003\u0001\u0001\u0019!C\t\u0003\u0007A\u0011\"a\u0003\u0001\u0001\u0004%\t\"!\u0004\t\u0011\u0005e\u0001\u0001)Q\u0005\u0003\u000bA1\"a\u0007\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002\u001e!Y\u0011Q\u0005\u0001A\u0002\u0003\u0007I\u0011BA\u0014\u0011-\tY\u0003\u0001a\u0001\u0002\u0003\u0006K!a\b\t\u0013\u00055\u0002\u00011A\u0005\n\u0005u\u0001\"CA\u0018\u0001\u0001\u0007I\u0011BA\u0019\u0011!\t)\u0004\u0001Q!\n\u0005}\u0001\"CA \u0001\u0001\u0007I\u0011BA!\u0011%\tI\u0005\u0001a\u0001\n\u0013\tY\u0005\u0003\u0005\u0002P\u0001\u0001\u000b\u0015BA\"\u0011%\t\t\u0006\u0001a\u0001\n\u0013\t\u0019\u0006C\u0005\u0002V\u0001\u0001\r\u0011\"\u0003\u0002X!9\u00111\f\u0001!B\u0013a\u0005bBA/\u0001\u0011\u0005\u0013q\f\u0005\b\u0003C\u0002A\u0011AA2\u0011\u001d\tI\u0007\u0001C\u0001\u0003WBq!a\u001c\u0001\t\u0003\t\t\bC\u0004\u0002|\u0001!\t!! \t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\b\"9\u0011q\u0014\u0001\u0005B\u0005\u0005\u0006bBAU\u0001\u0011%\u00111\u0016\u0005\b\u0003c\u0003A\u0011BAZ\u0005-y\u0005/\u001a8ICNDW*\u00199\u000b\u0005\u0001\n\u0013AC2pY2,7\r^5p]*\u0011!eI\u0001\u0005kRLGN\u0003\u0002%K\u0005)1\u000f]1sW*\u0011aeJ\u0001\u0007CB\f7\r[3\u000b\u0003!\n1a\u001c:h+\rQ3)T\n\u0005\u0001-\n$\r\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TF\u0001\u0004B]f\u0014VM\u001a\t\u0004emrdBA\u001a:\u001d\t!\u0004(D\u00016\u0015\t1t'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005q\u0013B\u0001\u001e.\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0011%#XM]1cY\u0016T!AO\u0017\u0011\t1z\u0014\tT\u0005\u0003\u00016\u0012a\u0001V;qY\u0016\u0014\u0004C\u0001\"D\u0019\u0001!Q\u0001\u0012\u0001C\u0002\u0015\u0013\u0011aS\t\u0003\r&\u0003\"\u0001L$\n\u0005!k#a\u0002(pi\"Lgn\u001a\t\u0003Y)K!aS\u0017\u0003\u0007\u0005s\u0017\u0010\u0005\u0002C\u001b\u0012Ia\n\u0001Q\u0001\u0002\u0003\u0015\r!\u0012\u0002\u0002-\"*Q\nU*Y;B\u0011A&U\u0005\u0003%6\u00121b\u001d9fG&\fG.\u001b>fIF*1\u0005V+X-:\u0011A&V\u0005\u0003-6\nA\u0001T8oOF\"Ae\r\u001d/c\u0015\u0019\u0013L\u0017/\\\u001d\ta#,\u0003\u0002\\[\u0005\u0019\u0011J\u001c;2\t\u0011\u001a\u0004HL\u0019\u0006Gy{\u0016\r\u0019\b\u0003Y}K!\u0001Y\u0017\u0002\r\u0011{WO\u00197fc\u0011!3\u0007\u000f\u0018\u0011\u0005I\u001a\u0017B\u00013>\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003=Ig.\u001b;jC2\u001c\u0015\r]1dSRL\bC\u0001\u0017h\u0013\tAWFA\u0002J]R\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rYg.Q\u0007\u0002Y*\u0011Q.L\u0001\be\u00164G.Z2u\u0013\tyGN\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003))g/\u001b3f]\u000e,GE\r\t\u0004W:d\u0015A\u0002\u001fj]&$h\b\u0006\u0002usR\u0019Qo\u001e=\u0011\tY\u0004\u0011\tT\u0007\u0002?!)\u0011\u000e\u0002a\u0002U\")\u0001\u000f\u0002a\u0002c\")Q\r\u0002a\u0001MR\t1\u0010F\u0002vyzDq!`\u0003\u0002\u0002\u0003\u000f!.\u0001\u0006fm&$WM\\2fIMBqa`\u0003\u0002\u0002\u0003\u000f\u0011/\u0001\u0006fm&$WM\\2fIQ\nqaX6fsN+G/\u0006\u0002\u0002\u0006A!a/a\u0002B\u0013\r\tIa\b\u0002\f\u001fB,g\u000eS1tQN+G/A\u0006`W\u0016L8+\u001a;`I\u0015\fH\u0003BA\b\u0003+\u00012\u0001LA\t\u0013\r\t\u0019\"\f\u0002\u0005+:LG\u000fC\u0005\u0002\u0018\u001d\t\t\u00111\u0001\u0002\u0006\u0005\u0019\u0001\u0010J\u0019\u0002\u0011}[W-_*fi\u0002\nqa\u0018<bYV,7/\u0006\u0002\u0002 A!A&!\tM\u0013\r\t\u0019#\f\u0002\u0006\u0003J\u0014\u0018-_\u0001\f?Z\fG.^3t?\u0012*\u0017\u000f\u0006\u0003\u0002\u0010\u0005%\u0002\"CA\f\u0015\u0005\u0005\t\u0019AA\u0010\u0003!yf/\u00197vKN\u0004\u0013AC0pY\u00124\u0016\r\\;fg\u0006qql\u001c7e-\u0006dW/Z:`I\u0015\fH\u0003BA\b\u0003gA\u0011\"a\u0006\u000e\u0003\u0003\u0005\r!a\b\u0002\u0017}{G\u000e\u001a,bYV,7\u000f\t\u0015\u0004\u001d\u0005e\u0002c\u0001\u0017\u0002<%\u0019\u0011QH\u0017\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018!\u00045bm\u0016tU\u000f\u001c7WC2,X-\u0006\u0002\u0002DA\u0019A&!\u0012\n\u0007\u0005\u001dSFA\u0004C_>dW-\u00198\u0002#!\fg/\u001a(vY24\u0016\r\\;f?\u0012*\u0017\u000f\u0006\u0003\u0002\u0010\u00055\u0003\"CA\f!\u0005\u0005\t\u0019AA\"\u00039A\u0017M^3Ok2dg+\u00197vK\u0002\n\u0011B\\;mYZ\u000bG.^3\u0016\u00031\u000bQB\\;mYZ\u000bG.^3`I\u0015\fH\u0003BA\b\u00033B\u0001\"a\u0006\u0014\u0003\u0003\u0005\r\u0001T\u0001\u000b]VdGNV1mk\u0016\u0004\u0013\u0001B:ju\u0016,\u0012AZ\u0001\tG>tG/Y5ogR!\u00111IA3\u0011\u0019\t9G\u0006a\u0001\u0003\u0006\t1.A\u0003baBd\u0017\u0010F\u0002M\u0003[Ba!a\u001a\u0018\u0001\u0004\t\u0015aA4fiR!\u00111OA=!\u0011a\u0013Q\u000f'\n\u0007\u0005]TF\u0001\u0004PaRLwN\u001c\u0005\u0007\u0003OB\u0002\u0019A!\u0002\rU\u0004H-\u0019;f)\u0019\ty!a \u0002\u0002\"1\u0011qM\rA\u0002\u0005Ca!a!\u001a\u0001\u0004a\u0015!\u0001<\u0002\u0017\rD\u0017M\\4f-\u0006dW/\u001a\u000b\b\u0019\u0006%\u00151RAK\u0011\u0019\t9G\u0007a\u0001\u0003\"A\u0011Q\u0012\u000e\u0005\u0002\u0004\ty)\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X\r\u0005\u0003-\u0003#c\u0015bAAJ[\tAAHY=oC6,g\bC\u0004\u0002\u0018j\u0001\r!!'\u0002\u00155,'oZ3WC2,X\rE\u0003-\u00037cE*C\u0002\u0002\u001e6\u0012\u0011BR;oGRLwN\\\u0019\u0002\u0011%$XM]1u_J,\"!a)\u0011\tI\n)KP\u0005\u0004\u0003Ok$\u0001C%uKJ\fGo\u001c:\u0002\t\u001d\u0014xn\u001e\u000b\u0005\u0003\u001f\ti\u000b\u0003\u0004\u00020r\u0001\rAZ\u0001\f]\u0016<8)\u00199bG&$\u00180\u0001\u0003n_Z,GCBA\b\u0003k\u000bI\f\u0003\u0004\u00028v\u0001\rAZ\u0001\u0007_2$\u0007k\\:\t\r\u0005mV\u00041\u0001g\u0003\u0019qWm\u001e)pg\u0002"
)
public class OpenHashMap implements Iterable, Serializable {
   private final ClassTag evidence$2;
   public OpenHashSet org$apache$spark$util$collection$OpenHashMap$$_keySet;
   public Object _values;
   public transient Object _oldValues;
   public boolean org$apache$spark$util$collection$OpenHashMap$$haveNullValue;
   public Object nullValue;

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
      return this.org$apache$spark$util$collection$OpenHashMap$$_keySet;
   }

   public void _keySet_$eq(final OpenHashSet x$1) {
      this.org$apache$spark$util$collection$OpenHashMap$$_keySet = x$1;
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

   public boolean org$apache$spark$util$collection$OpenHashMap$$haveNullValue() {
      return this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue;
   }

   public void org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(final boolean x$1) {
      this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue = x$1;
   }

   public Object nullValue() {
      return this.nullValue;
   }

   public void nullValue_$eq(final Object x$1) {
      this.nullValue = x$1;
   }

   public int size() {
      return this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue() ? this._keySet().size() + 1 : this._keySet().size();
   }

   public boolean contains(final Object k) {
      if (k == null) {
         return this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue();
      } else {
         return this._keySet().getPos(k) != OpenHashSet$.MODULE$.INVALID_POS();
      }
   }

   public Object apply(final Object k) {
      if (k == null) {
         return this.nullValue();
      } else {
         int pos = this._keySet().getPos(k);
         return pos < 0 ? null : scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos);
      }
   }

   public Option get(final Object k) {
      if (k == null) {
         return (Option)(this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue() ? new Some(this.nullValue()) : scala.None..MODULE$);
      } else {
         int pos = this._keySet().getPos(k);
         return (Option)(pos < 0 ? scala.None..MODULE$ : new Some(scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos)));
      }
   }

   public void update(final Object k, final Object v) {
      if (k == null) {
         this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
         this.nullValue_$eq(v);
      } else {
         int pos = this._keySet().addWithoutResize(k) & OpenHashSet$.MODULE$.POSITION_MASK();
         scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos, v);
         this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
         this._oldValues_$eq((Object)null);
      }
   }

   public Object changeValue(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      if (k == null) {
         if (this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue()) {
            this.nullValue_$eq(mergeValue.apply(this.nullValue()));
         } else {
            this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue_$eq(true);
            this.nullValue_$eq(defaultValue.apply());
         }

         return this.nullValue();
      } else {
         int pos = this._keySet().addWithoutResize(k);
         if ((pos & OpenHashSet$.MODULE$.NONEXISTENCE_MASK()) != 0) {
            Object newValue = defaultValue.apply();
            scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos & OpenHashSet$.MODULE$.POSITION_MASK(), newValue);
            this._keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$util$collection$OpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$util$collection$OpenHashMap$$move(oldPos, newPos));
            return newValue;
         } else {
            scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos, mergeValue.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos)));
            return scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos);
         }
      }
   }

   public Iterator iterator() {
      return new Iterator() {
         private int pos;
         private Tuple2 nextPair;
         // $FF: synthetic field
         private final OpenHashMap $outer;

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
            if (this.pos() == -1) {
               if (this.$outer.org$apache$spark$util$collection$OpenHashMap$$haveNullValue()) {
                  this.pos_$eq(this.pos() + 1);
                  return new Tuple2((Object)null, this.$outer.nullValue());
               }

               this.pos_$eq(this.pos() + 1);
            }

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
            if (OpenHashMap.this == null) {
               throw null;
            } else {
               this.$outer = OpenHashMap.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = -1;
               this.nextPair = this.computeNextPair();
            }
         }
      };
   }

   public void org$apache$spark$util$collection$OpenHashMap$$grow(final int newCapacity) {
      this._oldValues_$eq(this._values());
      this._values_$eq(this.evidence$2.newArray(newCapacity));
   }

   public void org$apache$spark$util$collection$OpenHashMap$$move(final int oldPos, final int newPos) {
      scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), newPos, scala.runtime.ScalaRunTime..MODULE$.array_apply(this._oldValues(), oldPos));
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

   public double nullValue$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.nullValue());
   }

   public int nullValue$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.nullValue());
   }

   public long nullValue$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.nullValue());
   }

   public void nullValue$mcD$sp_$eq(final double x$1) {
      this.nullValue_$eq(BoxesRunTime.boxToDouble(x$1));
   }

   public void nullValue$mcI$sp_$eq(final int x$1) {
      this.nullValue_$eq(BoxesRunTime.boxToInteger(x$1));
   }

   public void nullValue$mcJ$sp_$eq(final long x$1) {
      this.nullValue_$eq(BoxesRunTime.boxToLong(x$1));
   }

   public double apply$mcD$sp(final Object k) {
      return BoxesRunTime.unboxToDouble(this.apply(k));
   }

   public int apply$mcI$sp(final Object k) {
      return BoxesRunTime.unboxToInt(this.apply(k));
   }

   public long apply$mcJ$sp(final Object k) {
      return BoxesRunTime.unboxToLong(this.apply(k));
   }

   public void update$mcD$sp(final Object k, final double v) {
      this.update(k, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcI$sp(final Object k, final int v) {
      this.update(k, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final Object k, final long v) {
      this.update(k, BoxesRunTime.boxToLong(v));
   }

   public double changeValue$mcD$sp(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToDouble(this.changeValue(k, defaultValue, mergeValue));
   }

   public int changeValue$mcI$sp(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToInt(this.changeValue(k, defaultValue, mergeValue));
   }

   public long changeValue$mcJ$sp(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      return BoxesRunTime.unboxToLong(this.changeValue(k, defaultValue, mergeValue));
   }

   public boolean specInstance$() {
      return false;
   }

   public OpenHashMap(final int initialCapacity, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.evidence$2 = evidence$2;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      if (!this.specInstance$()) {
         this.org$apache$spark$util$collection$OpenHashMap$$_keySet = new OpenHashSet(initialCapacity, evidence$1);
         this._values_$eq(evidence$2.newArray(this._keySet().capacity()));
         this._oldValues = null;
         this.org$apache$spark$util$collection$OpenHashMap$$haveNullValue = false;
         this.nullValue = null;
      }

   }

   public OpenHashMap(final ClassTag evidence$3, final ClassTag evidence$4) {
      this(64, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
