package org.apache.spark.graphx.util.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.collection.OpenHashSet;
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
   bytes = "\u0006\u0005\u0005\u0005g!\u0002\r\u001a\u0001u)\u0003\u0002\u00034\u0001\u0005\u000b\u0007I\u0011A4\t\u00119\u0004!\u0011!Q\u0001\n!D\u0001b\u001c\u0001\u0003\u0002\u0004%\t\u0001\u001d\u0005\ti\u0002\u0011\t\u0019!C\u0001k\"A1\u0010\u0001B\u0001B\u0003&\u0011\u000f\u0003\u0005}\u0001\t\r\t\u0015a\u0003~\u0011)\t9\u0001\u0001B\u0002B\u0003-\u0011\u0011\u0002\u0005\b\u0003\u0017\u0001A\u0011AA\u0007\u0011\u001d\tY\u0001\u0001C\u0001\u0003;Aq!a\u0003\u0001\t\u0003\t\u0019\u0004C\u0004\u0002\f\u0001!\t!a\u0010\t\u0011\u00055\u0003\u00011A\u0005\nAD\u0011\"a\u0014\u0001\u0001\u0004%I!!\u0015\t\u000f\u0005U\u0003\u0001)Q\u0005c\"9\u0011q\u000b\u0001\u0005B\u0005e\u0003bBA.\u0001\u0011\u0005\u0011Q\f\u0005\b\u0003G\u0002A\u0011AA3\u0011\u001d\ti\u0007\u0001C\u0001\u0003_Bq!a\u001e\u0001\t\u0003\tI\bC\u0004\u0002\n\u0002!\t!a#\t\u000f\u0005\r\u0006\u0001\"\u0011\u0002&\"9\u0011Q\u0016\u0001\u0005\n\u0005=\u0006bBA[\u0001\u0011%\u0011q\u0017\u0002\u001e\u000fJ\f\u0007\u000f\u001b-Qe&l\u0017\u000e^5wK.+\u0017p\u00149f]\"\u000b7\u000f['ba*\u0011!dG\u0001\u000bG>dG.Z2uS>t'B\u0001\u000f\u001e\u0003\u0011)H/\u001b7\u000b\u0005yy\u0012AB4sCBD\u0007P\u0003\u0002!C\u0005)1\u000f]1sW*\u0011!eI\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\n1a\u001c:h+\r1shV\n\u0005\u0001\u001dj3\r\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VM\u001a\t\u0004]]RdBA\u00186\u001d\t\u0001D'D\u00012\u0015\t\u00114'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Q\u0013B\u0001\u001c*\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0011%#XM]1cY\u0016T!AN\u0015\u0011\t!ZTHV\u0005\u0003y%\u0012a\u0001V;qY\u0016\u0014\u0004C\u0001 @\u0019\u0001!\u0011\u0002\u0011\u0001!\u0002\u0003\u0005)\u0019A!\u0003\u0003-\u000b\"AQ#\u0011\u0005!\u001a\u0015B\u0001#*\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u000b$\n\u0005\u001dK#aA!os\"\"q(\u0013'R!\tA#*\u0003\u0002LS\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019SJ\u0014)P\u001d\tAc*\u0003\u0002PS\u0005!Aj\u001c8hc\u0011!s\u0006\u000e\u00162\u000b\r\u00126+\u0016+\u000f\u0005!\u001a\u0016B\u0001+*\u0003\rIe\u000e^\u0019\u0005I=\"$\u0006\u0005\u0002?/\u0012I\u0001\f\u0001Q\u0001\u0002\u0003\u0015\r!\u0011\u0002\u0002-\"*q+\u0013.]=F*1%\u0014(\\\u001fF\"Ae\f\u001b+c\u0015\u0019#kU/Uc\u0011!s\u0006\u000e\u00162\u000b\rz\u0006MY1\u000f\u0005!\u0002\u0017BA1*\u0003\u0019!u.\u001e2mKF\"Ae\f\u001b+!\tqC-\u0003\u0002fs\ta1+\u001a:jC2L'0\u00192mK\u000611.Z=TKR,\u0012\u0001\u001b\t\u0004S2lT\"\u00016\u000b\u0005iY'B\u0001\u000f \u0013\ti'NA\u0006Pa\u0016t\u0007*Y:i'\u0016$\u0018aB6fsN+G\u000fI\u0001\b?Z\fG.^3t+\u0005\t\bc\u0001\u0015s-&\u00111/\u000b\u0002\u0006\u0003J\u0014\u0018-_\u0001\f?Z\fG.^3t?\u0012*\u0017\u000f\u0006\u0002wsB\u0011\u0001f^\u0005\u0003q&\u0012A!\u00168ji\"9!\u0010BA\u0001\u0002\u0004\t\u0018a\u0001=%c\u0005AqL^1mk\u0016\u001c\b%\u0001\u0006fm&$WM\\2fIE\u0002BA`A\u0002{5\tqPC\u0002\u0002\u0002%\nqA]3gY\u0016\u001cG/C\u0002\u0002\u0006}\u0014\u0001b\u00117bgN$\u0016mZ\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004\u0003\u0002@\u0002\u0004Y\u000ba\u0001P5oSRtDCBA\b\u00033\tY\u0002\u0006\u0004\u0002\u0012\u0005U\u0011q\u0003\t\u0006\u0003'\u0001QHV\u0007\u00023!)A\u0010\u0003a\u0002{\"9\u0011q\u0001\u0005A\u0004\u0005%\u0001\"\u00024\t\u0001\u0004A\u0007\"B8\t\u0001\u0004\tH\u0003BA\u0010\u0003S!b!!\u0005\u0002\"\u0005\u0015\u0002\u0002CA\u0012\u0013\u0005\u0005\t9A?\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007C\u0005\u0002(%\t\t\u0011q\u0001\u0002\n\u0005QQM^5eK:\u001cW\r\n\u001b\t\u000f\u0005-\u0012\u00021\u0001\u0002.\u0005y\u0011N\\5uS\u0006d7)\u00199bG&$\u0018\u0010E\u0002)\u0003_I1!!\r*\u0005\rIe\u000e\u001e\u000b\u0003\u0003k!b!!\u0005\u00028\u0005m\u0002\u0002CA\u001d\u0015\u0005\u0005\t9A?\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007C\u0005\u0002>)\t\t\u0011q\u0001\u0002\n\u0005QQM^5eK:\u001cW\r\n\u001c\u0015\t\u0005\u0005\u00131\n\u000b\u0007\u0003#\t\u0019%a\u0012\t\u0011\u0005\u00153\"!AA\u0004u\f!\"\u001a<jI\u0016t7-\u001a\u00138\u0011%\tIeCA\u0001\u0002\b\tI!\u0001\u0006fm&$WM\\2fIaBQAZ\u0006A\u0002!\f!bX8mIZ\u000bG.^3t\u00039yv\u000e\u001c3WC2,Xm]0%KF$2A^A*\u0011\u001dQX\"!AA\u0002E\f1bX8mIZ\u000bG.^3tA\u0005!1/\u001b>f+\t\ti#A\u0003baBd\u0017\u0010F\u0002W\u0003?Ba!!\u0019\u0011\u0001\u0004i\u0014!A6\u0002\u0013\u001d,Go\u0014:FYN,G#\u0002,\u0002h\u0005%\u0004BBA1#\u0001\u0007Q\b\u0003\u0004\u0002lE\u0001\rAV\u0001\nK2\u001cXMV1mk\u0016\fa!\u001e9eCR,G#\u0002<\u0002r\u0005M\u0004BBA1%\u0001\u0007Q\b\u0003\u0004\u0002vI\u0001\rAV\u0001\u0002m\u0006A1/\u001a;NKJ<W\rF\u0004w\u0003w\ni(a \t\r\u0005\u00054\u00031\u0001>\u0011\u0019\t)h\u0005a\u0001-\"9\u0011\u0011Q\nA\u0002\u0005\r\u0015AB7fe\u001e,g\t\u0005\u0004)\u0003\u000b3fKV\u0005\u0004\u0003\u000fK#!\u0003$v]\u000e$\u0018n\u001c83\u0003-\u0019\u0007.\u00198hKZ\u000bG.^3\u0015\u000fY\u000bi)a$\u0002\u001a\"1\u0011\u0011\r\u000bA\u0002uB\u0001\"!%\u0015\t\u0003\u0007\u00111S\u0001\rI\u00164\u0017-\u001e7u-\u0006dW/\u001a\t\u0005Q\u0005Ue+C\u0002\u0002\u0018&\u0012\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\b\u00037#\u0002\u0019AAO\u0003)iWM]4f-\u0006dW/\u001a\t\u0006Q\u0005}eKV\u0005\u0004\u0003CK#!\u0003$v]\u000e$\u0018n\u001c82\u0003!IG/\u001a:bi>\u0014XCAAT!\u0011q\u0013\u0011\u0016\u001e\n\u0007\u0005-\u0016H\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003\u00119'o\\<\u0015\u0007Y\f\t\fC\u0004\u00024Z\u0001\r!!\f\u0002\u00179,woQ1qC\u000eLG/_\u0001\u0005[>4X\rF\u0003w\u0003s\u000bi\fC\u0004\u0002<^\u0001\r!!\f\u0002\r=dG\rU8t\u0011\u001d\tyl\u0006a\u0001\u0003[\taA\\3x!>\u001c\b"
)
public class GraphXPrimitiveKeyOpenHashMap implements Iterable, Serializable {
   public final OpenHashSet keySet;
   public Object _values;
   private final ClassTag evidence$2;
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

   public OpenHashSet keySet() {
      return this.keySet;
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
      return this.keySet().size();
   }

   public Object apply(final Object k) {
      int pos = this.keySet().getPos(k);
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos);
   }

   public Object getOrElse(final Object k, final Object elseValue) {
      int pos = this.keySet().getPos(k);
      return pos >= 0 ? scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), pos) : elseValue;
   }

   public void update(final Object k, final Object v) {
      int pos = this.keySet().addWithoutResize(k) & org.apache.spark.util.collection.OpenHashSet..MODULE$.POSITION_MASK();
      scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos, v);
      this.keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((Object)null);
   }

   public void setMerge(final Object k, final Object v, final Function2 mergeF) {
      int pos = this.keySet().addWithoutResize(k);
      int ind = pos & org.apache.spark.util.collection.OpenHashSet..MODULE$.POSITION_MASK();
      if ((pos & org.apache.spark.util.collection.OpenHashSet..MODULE$.NONEXISTENCE_MASK()) != 0) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), ind, v);
      } else {
         scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), ind, mergeF.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(this._values(), ind), v));
      }

      this.keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
      this._oldValues_$eq((Object)null);
   }

   public Object changeValue(final Object k, final Function0 defaultValue, final Function1 mergeValue) {
      int pos = this.keySet().addWithoutResize(k);
      if ((pos & org.apache.spark.util.collection.OpenHashSet..MODULE$.NONEXISTENCE_MASK()) != 0) {
         Object newValue = defaultValue.apply();
         scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), pos & org.apache.spark.util.collection.OpenHashSet..MODULE$.POSITION_MASK(), newValue);
         this.keySet().rehashIfNeeded(k, (JFunction1.mcVI.sp)(newCapacity) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(newCapacity), (JFunction2.mcVII.sp)(oldPos, newPos) -> this.org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(oldPos, newPos));
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
         private final GraphXPrimitiveKeyOpenHashMap $outer;

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
            this.pos_$eq(this.$outer.keySet().nextPos(this.pos()));
            if (this.pos() >= 0) {
               Tuple2 ret = new Tuple2(this.$outer.keySet().getValue(this.pos()), scala.runtime.ScalaRunTime..MODULE$.array_apply(this.$outer._values(), this.pos()));
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
            if (GraphXPrimitiveKeyOpenHashMap.this == null) {
               throw null;
            } else {
               this.$outer = GraphXPrimitiveKeyOpenHashMap.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = 0;
               this.nextPair = this.computeNextPair();
            }
         }
      };
   }

   public void org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$grow(final int newCapacity) {
      this._oldValues_$eq(this._values());
      this._values_$eq(this.evidence$2.newArray(newCapacity));
   }

   public void org$apache$spark$graphx$util$collection$GraphXPrimitiveKeyOpenHashMap$$move(final int oldPos, final int newPos) {
      scala.runtime.ScalaRunTime..MODULE$.array_update(this._values(), newPos, scala.runtime.ScalaRunTime..MODULE$.array_apply(this._oldValues(), oldPos));
   }

   public OpenHashSet keySet$mcI$sp() {
      return this.keySet();
   }

   public OpenHashSet keySet$mcJ$sp() {
      return this.keySet();
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

   public void setMerge$mcID$sp(final int k, final double v, final Function2 mergeF) {
      this.setMerge(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToDouble(v), mergeF);
   }

   public void setMerge$mcII$sp(final int k, final int v, final Function2 mergeF) {
      this.setMerge(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToInteger(v), mergeF);
   }

   public void setMerge$mcIJ$sp(final int k, final long v, final Function2 mergeF) {
      this.setMerge(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToLong(v), mergeF);
   }

   public void setMerge$mcJD$sp(final long k, final double v, final Function2 mergeF) {
      this.setMerge(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToDouble(v), mergeF);
   }

   public void setMerge$mcJI$sp(final long k, final int v, final Function2 mergeF) {
      this.setMerge(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToInteger(v), mergeF);
   }

   public void setMerge$mcJJ$sp(final long k, final long v, final Function2 mergeF) {
      this.setMerge(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToLong(v), mergeF);
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

   public GraphXPrimitiveKeyOpenHashMap(final OpenHashSet keySet, final Object _values, final ClassTag evidence$1, final ClassTag evidence$2) {
      boolean var8;
      Predef var10000;
      label25: {
         label27: {
            this.keySet = keySet;
            this._values = _values;
            this.evidence$2 = evidence$2;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            IterableOps.$init$(this);
            IterableFactoryDefaults.$init$(this);
            Iterable.$init$(this);
            var10000 = scala.Predef..MODULE$;
            ClassTag var10001 = scala.reflect.package..MODULE$.classTag(evidence$1);
            ClassTag var5 = scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Long());
            if (var10001 == null) {
               if (var5 == null) {
                  break label27;
               }
            } else if (var10001.equals(var5)) {
               break label27;
            }

            var10001 = scala.reflect.package..MODULE$.classTag(evidence$1);
            ClassTag var6 = scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Int());
            if (var10001 == null) {
               if (var6 == null) {
                  break label27;
               }
            } else if (var10001.equals(var6)) {
               break label27;
            }

            var8 = false;
            break label25;
         }

         var8 = true;
      }

      var10000.require(var8);
      this._oldValues = null;
   }

   public GraphXPrimitiveKeyOpenHashMap(final int initialCapacity, final ClassTag evidence$3, final ClassTag evidence$4) {
      this(new OpenHashSet(initialCapacity, evidence$3), evidence$4.newArray(initialCapacity), evidence$3, evidence$4);
   }

   public GraphXPrimitiveKeyOpenHashMap(final ClassTag evidence$5, final ClassTag evidence$6) {
      this(64, evidence$5, evidence$6);
   }

   public GraphXPrimitiveKeyOpenHashMap(final OpenHashSet keySet, final ClassTag evidence$7, final ClassTag evidence$8) {
      this(keySet, evidence$8.newArray(keySet.capacity()), evidence$7, evidence$8);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
