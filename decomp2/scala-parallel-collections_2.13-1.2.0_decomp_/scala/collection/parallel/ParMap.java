package scala.collection.parallel;

import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Parallelizable;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate;
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.parallel.mutable.ParHashMap;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001ddaB\n\u0015!\u0003\r\ta\u0007\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006#\u0002!\tEU\u0004\u00067RA\t\u0001\u0018\u0004\u0006'QA\t!\u0018\u0005\u0006E\u001a!\ta\u0019\u0005\u0006\u001f\u001a!\t\u0001\u001a\u0005\u0006W\u001a!\t\u0001\u001c\u0005\u0006o\u001a!\u0019\u0001\u001f\u0004\b\u0003+1\u0011\u0011AA\f\u0011)\tIc\u0003B\u0001B\u0003%\u00111\u0004\u0005\u000b\u0003WY!\u0011!Q\u0001\n\u00055\u0002B\u00022\f\t\u0003\t\u0019\u0004C\u0004\u0002>-!\t!a\u0010\t\u000f\u0005\u001d3\u0002\"\u0001\u0002J!9\u0011QK\u0006\u0005\u0002\u0005]\u0003bBA1\u0017\u0011\u0005\u00131\r\u0002\u0007!\u0006\u0014X*\u00199\u000b\u0005U1\u0012\u0001\u00039be\u0006dG.\u001a7\u000b\u0005]A\u0012AC2pY2,7\r^5p]*\t\u0011$A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007qI3gE\u0003\u0001;\u0005:T\b\u0005\u0002\u001f?5\t\u0001$\u0003\u0002!1\t1\u0011I\\=SK\u001a\u0004RAI\u0013(eUj\u0011a\t\u0006\u0003IY\tqaZ3oKJL7-\u0003\u0002'G\t)r)\u001a8fe&\u001c\u0007+\u0019:NCB$V-\u001c9mCR,\u0007C\u0001\u0015*\u0019\u0001!QA\u000b\u0001C\u0002-\u0012\u0011aS\t\u0003Y=\u0002\"AH\u0017\n\u00059B\"a\u0002(pi\"Lgn\u001a\t\u0003=AJ!!\r\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002)g\u00111A\u0007\u0001CC\u0002-\u0012\u0011A\u0016\t\u0003m\u0001i\u0011\u0001\u0006\t\u0004maR\u0014BA\u001d\u0015\u0005-\u0001\u0016M]%uKJ\f'\r\\3\u0011\tyYtEM\u0005\u0003ya\u0011a\u0001V;qY\u0016\u0014\u0004c\u0002\u001c?OI*\u0004)Q\u0005\u0003\u007fQ\u0011!\u0002U1s\u001b\u0006\u0004H*[6f!\u00111\u0004a\n\u001a\u0011\t\t\u001buEM\u0007\u0002-%\u0011AI\u0006\u0002\u0004\u001b\u0006\u0004\u0018A\u0002\u0013j]&$H\u0005F\u0001H!\tq\u0002*\u0003\u0002J1\t!QK\\5u\u00031i\u0017\r]\"p[B\fg.[8o+\u0005a\u0005c\u0001\u0012Nk%\u0011aj\t\u0002\u0017\u000f\u0016tWM]5d!\u0006\u0014X*\u00199D_6\u0004\u0018M\\5p]\u0006)Q-\u001c9usV\t\u0001)\u0001\u0007tiJLgn\u001a)sK\u001aL\u00070F\u0001T!\t!\u0016,D\u0001V\u0015\t1v+\u0001\u0003mC:<'\"\u0001-\u0002\t)\fg/Y\u0005\u00035V\u0013aa\u0015;sS:<\u0017A\u0002)be6\u000b\u0007\u000f\u0005\u00027\rM\u0011aA\u0018\t\u0005E}+\u0014-\u0003\u0002aG\ti\u0001+\u0019:NCB4\u0015m\u0019;pef\u0004\"AQ\"\u0002\rqJg.\u001b;?)\u0005aVcA3iUV\ta\r\u0005\u00037\u0001\u001dL\u0007C\u0001\u0015i\t\u0015Q\u0003B1\u0001,!\tA#\u000eB\u00035\u0011\t\u00071&A\u0006oK^\u001cu.\u001c2j]\u0016\u0014XcA7tkV\ta\u000e\u0005\u00037_F4\u0018B\u00019\u0015\u0005!\u0019u.\u001c2j]\u0016\u0014\b\u0003\u0002\u0010<eR\u0004\"\u0001K:\u0005\u000b)J!\u0019A\u0016\u0011\u0005!*H!\u0002\u001b\n\u0005\u0004Y\u0003\u0003\u0002\u001c\u0001eR\fAbY1o\u0005VLG\u000e\u001a$s_6,\u0002\"_@\u0002\u0006\u00055\u0011\u0011C\u000b\u0002uB9!e_?\u0002\n\u0005M\u0011B\u0001?$\u00059\u0019\u0015M\\\"p[\nLg.\u001a$s_6\u0004RA\u000e\u0001\u007f\u0003\u0007\u0001\"\u0001K@\u0005\r\u0005\u0005!B1\u0001,\u0005\u00151%o\\7L!\rA\u0013Q\u0001\u0003\u0007\u0003\u000fQ!\u0019A\u0016\u0003\u000b\u0019\u0013x.\u001c,\u0011\ryY\u00141BA\b!\rA\u0013Q\u0002\u0003\u0006U)\u0011\ra\u000b\t\u0004Q\u0005EA!\u0002\u001b\u000b\u0005\u0004Y\u0003C\u0002\u001c\u0001\u0003\u0017\tyAA\u0006XSRDG)\u001a4bk2$XCBA\r\u0003?\t)c\u0005\u0003\f;\u0005m\u0001C\u0002\u001c\u0001\u0003;\t\u0019\u0003E\u0002)\u0003?!a!!\t\f\u0005\u0004Y#!A!\u0011\u0007!\n)\u0003B\u0004\u0002(-!)\u0019A\u0016\u0003\u0003\t\u000b!\"\u001e8eKJd\u00170\u001b8h\u0003\u0005!\u0007c\u0002\u0010\u00020\u0005u\u00111E\u0005\u0004\u0003cA\"!\u0003$v]\u000e$\u0018n\u001c82)\u0019\t)$!\u000f\u0002<A9\u0011qG\u0006\u0002\u001e\u0005\rR\"\u0001\u0004\t\u000f\u0005%b\u00021\u0001\u0002\u001c!9\u00111\u0006\bA\u0002\u00055\u0012\u0001B:ju\u0016,\"!!\u0011\u0011\u0007y\t\u0019%C\u0002\u0002Fa\u00111!\u00138u\u0003\r9W\r\u001e\u000b\u0005\u0003\u0017\n\t\u0006E\u0003\u001f\u0003\u001b\n\u0019#C\u0002\u0002Pa\u0011aa\u00149uS>t\u0007bBA*!\u0001\u0007\u0011QD\u0001\u0004W\u0016L\u0018\u0001C:qY&$H/\u001a:\u0016\u0005\u0005e\u0003#\u0002\u001c\u0002\\\u0005}\u0013bAA/)\t\u0001\u0012\n^3sC\ndWm\u00159mSR$XM\u001d\t\u0007=m\ni\"a\t\u0002\u000f\u0011,g-Y;miR!\u00111EA3\u0011\u001d\t\u0019F\u0005a\u0001\u0003;\u0001"
)
public interface ParMap extends GenericParMapTemplate, ParIterable, ParMapLike {
   static CanCombineFrom canBuildFrom() {
      return ParMap$.MODULE$.canBuildFrom();
   }

   static Factory toFactory() {
      return ParMap$.MODULE$.toFactory();
   }

   default GenericParMapCompanion mapCompanion() {
      return ParMap$.MODULE$;
   }

   default ParMap empty() {
      return new ParHashMap();
   }

   default String stringPrefix() {
      return "ParMap";
   }

   static void $init$(final ParMap $this) {
   }

   public abstract static class WithDefault implements ParMap {
      private final ParMap underlying;
      private final Function1 d;
      private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
      private volatile ParIterableLike.ScanNode$ ScanNode$module;
      private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

      public GenericParMapCompanion mapCompanion() {
         return ParMap.super.mapCompanion();
      }

      public ParMap empty() {
         return ParMap.super.empty();
      }

      public String stringPrefix() {
         return ParMap.super.stringPrefix();
      }

      public boolean canEqual(final Object that) {
         return ParMapLike.canEqual$(this, that);
      }

      public boolean equals(final Object that) {
         return ParMapLike.equals$(this, that);
      }

      public int hashCode() {
         return ParMapLike.hashCode$(this);
      }

      public ParMap updated(final Object key, final Object value) {
         return ParMapLike.updated$(this, key, value);
      }

      public Object apply(final Object key) {
         return ParMapLike.apply$(this, key);
      }

      public Object getOrElse(final Object key, final Function0 default) {
         return ParMapLike.getOrElse$(this, key, default);
      }

      public boolean contains(final Object key) {
         return ParMapLike.contains$(this, key);
      }

      public boolean isDefinedAt(final Object key) {
         return ParMapLike.isDefinedAt$(this, key);
      }

      public IterableSplitter keysIterator() {
         return ParMapLike.keysIterator$(this);
      }

      public IterableSplitter valuesIterator() {
         return ParMapLike.valuesIterator$(this);
      }

      public ParSet keySet() {
         return ParMapLike.keySet$(this);
      }

      public ParIterable keys() {
         return ParMapLike.keys$(this);
      }

      public ParIterable values() {
         return ParMapLike.values$(this);
      }

      public ParMap filterKeys(final Function1 p) {
         return ParMapLike.filterKeys$(this, p);
      }

      public ParMap mapValues(final Function1 f) {
         return ParMapLike.mapValues$(this, f);
      }

      public ParMap map(final Function1 f) {
         return ParMapLike.map$(this, f);
      }

      public ParMap collect(final PartialFunction pf) {
         return ParMapLike.collect$(this, pf);
      }

      public ParMap flatMap(final Function1 f) {
         return ParMapLike.flatMap$(this, f);
      }

      public ParMap concat(final IterableOnce that) {
         return ParMapLike.concat$(this, that);
      }

      public final ParMap $plus$plus(final IterableOnce xs) {
         return ParMapLike.$plus$plus$(this, xs);
      }

      public GenericParCompanion companion() {
         return ParIterable.companion$(this);
      }

      public void initTaskSupport() {
         ParIterableLike.initTaskSupport$(this);
      }

      public TaskSupport tasksupport() {
         return ParIterableLike.tasksupport$(this);
      }

      public void tasksupport_$eq(final TaskSupport ts) {
         ParIterableLike.tasksupport_$eq$(this, ts);
      }

      public ParIterable repr() {
         return ParIterableLike.repr$(this);
      }

      public final boolean isTraversableAgain() {
         return ParIterableLike.isTraversableAgain$(this);
      }

      public boolean hasDefiniteSize() {
         return ParIterableLike.hasDefiniteSize$(this);
      }

      public boolean isEmpty() {
         return ParIterableLike.isEmpty$(this);
      }

      public boolean nonEmpty() {
         return ParIterableLike.nonEmpty$(this);
      }

      public Object head() {
         return ParIterableLike.head$(this);
      }

      public Option headOption() {
         return ParIterableLike.headOption$(this);
      }

      public ParIterable tail() {
         return ParIterableLike.tail$(this);
      }

      public Object last() {
         return ParIterableLike.last$(this);
      }

      public Option lastOption() {
         return ParIterableLike.lastOption$(this);
      }

      public ParIterable init() {
         return ParIterableLike.init$(this);
      }

      public Splitter iterator() {
         return ParIterableLike.iterator$(this);
      }

      public ParIterable par() {
         return ParIterableLike.par$(this);
      }

      public boolean isStrictSplitterCollection() {
         return ParIterableLike.isStrictSplitterCollection$(this);
      }

      public Combiner reuse(final Option oldc, final Combiner newc) {
         return ParIterableLike.reuse$(this, oldc, newc);
      }

      public ParIterableLike.TaskOps task2ops(final ParIterableLike.StrictSplitterCheckTask tsk) {
         return ParIterableLike.task2ops$(this, tsk);
      }

      public ParIterableLike.NonDivisible wrap(final Function0 body) {
         return ParIterableLike.wrap$(this, body);
      }

      public ParIterableLike.SignallingOps delegatedSignalling2ops(final DelegatedSignalling it) {
         return ParIterableLike.delegatedSignalling2ops$(this, it);
      }

      public ParIterableLike.BuilderOps builder2ops(final Builder cb) {
         return ParIterableLike.builder2ops$(this, cb);
      }

      public ParIterable sequentially(final Function1 b) {
         return ParIterableLike.sequentially$(this, b);
      }

      public String mkString(final String start, final String sep, final String end) {
         return ParIterableLike.mkString$(this, start, sep, end);
      }

      public String mkString(final String sep) {
         return ParIterableLike.mkString$(this, sep);
      }

      public String mkString() {
         return ParIterableLike.mkString$(this);
      }

      public String toString() {
         return ParIterableLike.toString$(this);
      }

      public Object reduce(final Function2 op) {
         return ParIterableLike.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return ParIterableLike.reduceOption$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return ParIterableLike.fold$(this, z, op);
      }

      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return ParIterableLike.aggregate$(this, z, seqop, combop);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return ParIterableLike.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return ParIterableLike.foldRight$(this, z, op);
      }

      public Object reduceLeft(final Function2 op) {
         return ParIterableLike.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return ParIterableLike.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return ParIterableLike.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return ParIterableLike.reduceRightOption$(this, op);
      }

      public void foreach(final Function1 f) {
         ParIterableLike.foreach$(this, f);
      }

      public int count(final Function1 p) {
         return ParIterableLike.count$(this, p);
      }

      public Object sum(final Numeric num) {
         return ParIterableLike.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return ParIterableLike.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return ParIterableLike.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return ParIterableLike.max$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering cmp) {
         return ParIterableLike.maxBy$(this, f, cmp);
      }

      public Object minBy(final Function1 f, final Ordering cmp) {
         return ParIterableLike.minBy$(this, f, cmp);
      }

      public ParIterable map(final Function1 f) {
         return ParIterableLike.map$(this, f);
      }

      public ParIterable collect(final PartialFunction pf) {
         return ParIterableLike.collect$(this, pf);
      }

      public ParIterable flatMap(final Function1 f) {
         return ParIterableLike.flatMap$(this, f);
      }

      public boolean forall(final Function1 p) {
         return ParIterableLike.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return ParIterableLike.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return ParIterableLike.find$(this, p);
      }

      public CombinerFactory combinerFactory() {
         return ParIterableLike.combinerFactory$(this);
      }

      public CombinerFactory combinerFactory(final Function0 cbf) {
         return ParIterableLike.combinerFactory$(this, cbf);
      }

      public ParIterable withFilter(final Function1 pred) {
         return ParIterableLike.withFilter$(this, pred);
      }

      public ParIterable filter(final Function1 pred) {
         return ParIterableLike.filter$(this, pred);
      }

      public ParIterable filterNot(final Function1 pred) {
         return ParIterableLike.filterNot$(this, pred);
      }

      public ParIterable $plus$plus(final IterableOnce that) {
         return ParIterableLike.$plus$plus$(this, that);
      }

      public Tuple2 partition(final Function1 pred) {
         return ParIterableLike.partition$(this, pred);
      }

      public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
         return ParIterableLike.groupBy$(this, f);
      }

      public ParIterable take(final int n) {
         return ParIterableLike.take$(this, n);
      }

      public ParIterable drop(final int n) {
         return ParIterableLike.drop$(this, n);
      }

      public ParIterable slice(final int unc_from, final int unc_until) {
         return ParIterableLike.slice$(this, unc_from, unc_until);
      }

      public Tuple2 splitAt(final int n) {
         return ParIterableLike.splitAt$(this, n);
      }

      public ParIterable scan(final Object z, final Function2 op) {
         return ParIterableLike.scan$(this, z, op);
      }

      public Iterable scanLeft(final Object z, final Function2 op) {
         return ParIterableLike.scanLeft$(this, z, op);
      }

      public Iterable scanRight(final Object z, final Function2 op) {
         return ParIterableLike.scanRight$(this, z, op);
      }

      public ParIterable takeWhile(final Function1 pred) {
         return ParIterableLike.takeWhile$(this, pred);
      }

      public Tuple2 span(final Function1 pred) {
         return ParIterableLike.span$(this, pred);
      }

      public ParIterable dropWhile(final Function1 pred) {
         return ParIterableLike.dropWhile$(this, pred);
      }

      public void copyToArray(final Object xs) {
         ParIterableLike.copyToArray$(this, xs);
      }

      public void copyToArray(final Object xs, final int start) {
         ParIterableLike.copyToArray$(this, xs, start);
      }

      public void copyToArray(final Object xs, final int start, final int len) {
         ParIterableLike.copyToArray$(this, xs, start, len);
      }

      public boolean sameElements(final IterableOnce that) {
         return ParIterableLike.sameElements$(this, that);
      }

      public ParIterable zip(final ParIterable that) {
         return ParIterableLike.zip$(this, (ParIterable)that);
      }

      public ParIterable zip(final Iterable that) {
         return ParIterableLike.zip$(this, (Iterable)that);
      }

      public ParIterable zipWithIndex() {
         return ParIterableLike.zipWithIndex$(this);
      }

      public ParIterable zipAll(final ParIterable that, final Object thisElem, final Object thatElem) {
         return ParIterableLike.zipAll$(this, that, thisElem, thatElem);
      }

      public Object toParCollection(final Function0 cbf) {
         return ParIterableLike.toParCollection$(this, cbf);
      }

      public Object toParMap(final Function0 cbf, final .less.colon.less ev) {
         return ParIterableLike.toParMap$(this, cbf, ev);
      }

      public Object toArray(final ClassTag evidence$1) {
         return ParIterableLike.toArray$(this, evidence$1);
      }

      public List toList() {
         return ParIterableLike.toList$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return ParIterableLike.toIndexedSeq$(this);
      }

      /** @deprecated */
      public Stream toStream() {
         return ParIterableLike.toStream$(this);
      }

      public Iterator toIterator() {
         return ParIterableLike.toIterator$(this);
      }

      public Buffer toBuffer() {
         return ParIterableLike.toBuffer$(this);
      }

      /** @deprecated */
      public ParIterable toTraversable() {
         return ParIterableLike.toTraversable$(this);
      }

      public ParIterable toIterable() {
         return ParIterableLike.toIterable$(this);
      }

      public ParSeq toSeq() {
         return ParIterableLike.toSeq$(this);
      }

      public scala.collection.parallel.immutable.ParSet toSet() {
         return ParIterableLike.toSet$(this);
      }

      public scala.collection.parallel.immutable.ParMap toMap(final .less.colon.less ev) {
         return ParIterableLike.toMap$(this, ev);
      }

      public Vector toVector() {
         return ParIterableLike.toVector$(this);
      }

      public Object to(final Factory factory) {
         return ParIterableLike.to$(this, factory);
      }

      public int scanBlockSize() {
         return ParIterableLike.scanBlockSize$(this);
      }

      public Object $div$colon(final Object z, final Function2 op) {
         return ParIterableLike.$div$colon$(this, z, op);
      }

      public Object $colon$bslash(final Object z, final Function2 op) {
         return ParIterableLike.$colon$bslash$(this, z, op);
      }

      public String debugInformation() {
         return ParIterableLike.debugInformation$(this);
      }

      public Seq brokenInvariants() {
         return ParIterableLike.brokenInvariants$(this);
      }

      public ArrayBuffer debugBuffer() {
         return ParIterableLike.debugBuffer$(this);
      }

      public void debugclear() {
         ParIterableLike.debugclear$(this);
      }

      public ArrayBuffer debuglog(final String s) {
         return ParIterableLike.debuglog$(this, s);
      }

      public void printDebugBuffer() {
         ParIterableLike.printDebugBuffer$(this);
      }

      public Nothing parCombiner() {
         return CustomParallelizable.parCombiner$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Combiner newCombiner() {
         return GenericParMapTemplate.newCombiner$(this);
      }

      public Combiner genericMapCombiner() {
         return GenericParMapTemplate.genericMapCombiner$(this);
      }

      public Combiner newBuilder() {
         return GenericParTemplate.newBuilder$(this);
      }

      public Combiner genericBuilder() {
         return GenericParTemplate.genericBuilder$(this);
      }

      public Combiner genericCombiner() {
         return GenericParTemplate.genericCombiner$(this);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return GenericTraversableTemplate.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return GenericTraversableTemplate.unzip3$(this, asTriple);
      }

      public ParIterable flatten(final Function1 asTraversable) {
         return GenericTraversableTemplate.flatten$(this, asTraversable);
      }

      public ParIterable transpose(final Function1 asTraversable) {
         return GenericTraversableTemplate.transpose$(this, asTraversable);
      }

      public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
         return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
      }

      public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(final TaskSupport x$1) {
         this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
      }

      public ParIterableLike.ScanNode$ ScanNode() {
         if (this.ScanNode$module == null) {
            this.ScanNode$lzycompute$1();
         }

         return this.ScanNode$module;
      }

      public ParIterableLike.ScanLeaf$ ScanLeaf() {
         if (this.ScanLeaf$module == null) {
            this.ScanLeaf$lzycompute$1();
         }

         return this.ScanLeaf$module;
      }

      public int size() {
         return this.underlying.size();
      }

      public Option get(final Object key) {
         return this.underlying.get(key);
      }

      public IterableSplitter splitter() {
         return this.underlying.splitter();
      }

      public Object default(final Object key) {
         return this.d.apply(key);
      }

      private final void ScanNode$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.ScanNode$module == null) {
               this.ScanNode$module = new ParIterableLike.ScanNode$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void ScanLeaf$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.ScanLeaf$module == null) {
               this.ScanLeaf$module = new ParIterableLike.ScanLeaf$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public WithDefault(final ParMap underlying, final Function1 d) {
         this.underlying = underlying;
         this.d = d;
         GenericTraversableTemplate.$init$(this);
         GenericParTemplate.$init$(this);
         GenericParMapTemplate.$init$(this);
         IterableOnce.$init$(this);
         Parallelizable.$init$(this);
         CustomParallelizable.$init$(this);
         ParIterableLike.$init$(this);
         ParIterable.$init$(this);
         ParMapLike.$init$(this);
         ParMap.$init$(this);
      }
   }
}
