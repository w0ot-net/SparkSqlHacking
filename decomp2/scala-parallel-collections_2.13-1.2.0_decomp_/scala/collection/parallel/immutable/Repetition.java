package scala.collection.parallel.immutable;

import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Parallelizable;
import scala.collection.Seq;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.AugmentedSeqIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParSeqLike;
import scala.collection.parallel.PreciseSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb!\u0002\u0010 \u0001\u0005:\u0003\u0002C\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u0019\t\u0011y\u0002!Q1A\u0005\u0002}B\u0001b\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0005\u0006\u001b\u0002!\te\u0010\u0005\u0006\u001d\u0002!\te\u0014\u0005\u0006+\u0002!\tA\u0016\u0004\u00053\u0002\u0001!\f\u0003\u0005`\u0013\t\u0005\r\u0011\"\u0001@\u0011!\u0001\u0017B!a\u0001\n\u0003\t\u0007\u0002C4\n\u0005\u0003\u0005\u000b\u0015\u0002!\t\u0011!L!Q1A\u0005\u0002}B\u0001\"[\u0005\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\t{%\u0011\t\u0011)A\u0005c!)A)\u0003C\u0001U\")\u0001/\u0003C\u0001\u007f!)\u0011/\u0003C\u0001e\")a/\u0003C\u0001o\")\u00010\u0003C\u0001s\")!0\u0003C\u0001w\"9\u0011QA\u0005\u0005\u0002\u0005\u001dq!CA\u0005\u0001\u0005\u0005\t\u0012AA\u0006\r!I\u0006!!A\t\u0002\u00055\u0001B\u0002#\u0019\t\u0003\ty\u0001C\u0005\u0002\u0012a\t\n\u0011\"\u0001\u0002\u0014!I\u0011\u0011\u0006\r\u0012\u0002\u0013\u0005\u00111\u0003\u0005\n\u0003WA\u0012\u0013!C\u0001\u0003[Aa!!\r\u0001\t\u0003I(A\u0003*fa\u0016$\u0018\u000e^5p]*\u0011\u0001%I\u0001\nS6lW\u000f^1cY\u0016T!AI\u0012\u0002\u0011A\f'/\u00197mK2T!\u0001J\u0013\u0002\u0015\r|G\u000e\\3di&|gNC\u0001'\u0003\u0015\u00198-\u00197b+\tA3gE\u0002\u0001S5\u0002\"AK\u0016\u000e\u0003\u0015J!\u0001L\u0013\u0003\r\u0005s\u0017PU3g!\rqs&M\u0007\u0002?%\u0011\u0001g\b\u0002\u0007!\u0006\u00148+Z9\u0011\u0005I\u001aD\u0002\u0001\u0003\u0006i\u0001\u0011\rA\u000e\u0002\u0002)\u000e\u0001\u0011CA\u001c;!\tQ\u0003(\u0003\u0002:K\t9aj\u001c;iS:<\u0007C\u0001\u0016<\u0013\taTEA\u0002B]f\fA!\u001a7f[\u00061A.\u001a8hi\",\u0012\u0001\u0011\t\u0003U\u0005K!AQ\u0013\u0003\u0007%sG/A\u0004mK:<G\u000f\u001b\u0011\u0002\rqJg.\u001b;?)\r1u\t\u0013\t\u0004]\u0001\t\u0004\"B\u001f\u0005\u0001\u0004\t\u0004\"\u0002 \u0005\u0001\u0004\u0001\u0015!B1qa2LHCA\u0019L\u0011\u0015aU\u00011\u0001A\u0003\rIG\r_\u0001\nW:|wO\\*ju\u0016\f1a]3r+\u0005\u0001\u0006cA)Tc5\t!K\u0003\u0002!G%\u0011AK\u0015\u0002\u0004'\u0016\f\u0018AB;qI\u0006$X\rF\u00028/bCQ\u0001\u0014\u0005A\u0002\u0001CQ!\u0010\u0005A\u0002E\u00121\u0002U1s\u0013R,'/\u0019;peN\u0019\u0011\"K.\u0011\u0007qk\u0016'D\u0001\"\u0013\tq\u0016EA\u0006TKF\u001c\u0006\u000f\\5ui\u0016\u0014\u0018!A5\u0002\u000b%|F%Z9\u0015\u0005\t,\u0007C\u0001\u0016d\u0013\t!WE\u0001\u0003V]&$\bb\u00024\f\u0003\u0003\u0005\r\u0001Q\u0001\u0004q\u0012\n\u0014AA5!\u0003\u0015)h\u000e^5m\u0003\u0019)h\u000e^5mAQ!1.\u001c8p!\ta\u0017\"D\u0001\u0001\u0011\u001dy\u0006\u0003%AA\u0002\u0001Cq\u0001\u001b\t\u0011\u0002\u0003\u0007\u0001\tC\u0004>!A\u0005\t\u0019A\u0019\u0002\u0013I,W.Y5oS:<\u0017a\u00025bg:+\u0007\u0010^\u000b\u0002gB\u0011!\u0006^\u0005\u0003k\u0016\u0012qAQ8pY\u0016\fg.\u0001\u0003oKb$H#A\u0019\u0002\u0007\u0011,\b/F\u0001l\u0003\u0019\u00018\u000f\u001d7jiR\u0011A0 \t\u0004#N[\u0006\"\u0002@\u0016\u0001\u0004y\u0018!B:ju\u0016\u001c\b\u0003\u0002\u0016\u0002\u0002\u0001K1!a\u0001&\u0005)a$/\u001a9fCR,GMP\u0001\u0006gBd\u0017\u000e^\u000b\u0002y\u0006Y\u0001+\u0019:Ji\u0016\u0014\u0018\r^8s!\ta\u0007d\u0005\u0002\u0019SQ\u0011\u00111B\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005U!f\u0001!\u0002\u0018-\u0012\u0011\u0011\u0004\t\u0005\u00037\t)#\u0004\u0002\u0002\u001e)!\u0011qDA\u0011\u0003%)hn\u00195fG.,GMC\u0002\u0002$\u0015\n!\"\u00198o_R\fG/[8o\u0013\u0011\t9#!\b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005=\"fA\u0019\u0002\u0018\u0005A1\u000f\u001d7jiR,'\u000f"
)
public class Repetition implements ParSeq {
   private volatile ParIterator$ ParIterator$module;
   public final Object scala$collection$parallel$immutable$Repetition$$elem;
   private final int length;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public GenericParCompanion companion() {
      return ParSeq.companion$(this);
   }

   public ParSeq toSeq() {
      return ParSeq.toSeq$(this);
   }

   public ParIterable toIterable() {
      return ParIterable.toIterable$(this);
   }

   public String toString() {
      return scala.collection.parallel.ParSeq.toString$(this);
   }

   public String stringPrefix() {
      return scala.collection.parallel.ParSeq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$parallel$ParSeqLike$$super$sameElements(final IterableOnce that) {
      return ParIterableLike.sameElements$(this, that);
   }

   // $FF: synthetic method
   public scala.collection.parallel.ParSeq scala$collection$parallel$ParSeqLike$$super$zip(final scala.collection.parallel.ParIterable that) {
      return (scala.collection.parallel.ParSeq)ParIterableLike.zip$(this, (scala.collection.parallel.ParIterable)that);
   }

   public int hashCode() {
      return ParSeqLike.hashCode$(this);
   }

   public boolean equals(final Object that) {
      return ParSeqLike.equals$(this, that);
   }

   public boolean canEqual(final Object other) {
      return ParSeqLike.canEqual$(this, other);
   }

   public PreciseSplitter iterator() {
      return ParSeqLike.iterator$(this);
   }

   public final int size() {
      return ParSeqLike.size$(this);
   }

   public boolean isDefinedAt(final int idx) {
      return ParSeqLike.isDefinedAt$(this, idx);
   }

   public int segmentLength(final Function1 p, final int from) {
      return ParSeqLike.segmentLength$(this, p, from);
   }

   public int prefixLength(final Function1 p) {
      return ParSeqLike.prefixLength$(this, p);
   }

   public int indexOf(final Object elem) {
      return ParSeqLike.indexOf$(this, elem);
   }

   public int indexOf(final Object elem, final int from) {
      return ParSeqLike.indexOf$(this, elem, from);
   }

   public int indexWhere(final Function1 p) {
      return ParSeqLike.indexWhere$(this, p);
   }

   public int indexWhere(final Function1 p, final int from) {
      return ParSeqLike.indexWhere$(this, p, from);
   }

   public int lastIndexOf(final Object elem) {
      return ParSeqLike.lastIndexOf$(this, elem);
   }

   public int lastIndexOf(final Object elem, final int end) {
      return ParSeqLike.lastIndexOf$(this, elem, end);
   }

   public int lastIndexWhere(final Function1 p) {
      return ParSeqLike.lastIndexWhere$(this, p);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return ParSeqLike.lastIndexWhere$(this, p, end);
   }

   public scala.collection.parallel.ParSeq reverse() {
      return ParSeqLike.reverse$(this);
   }

   public scala.collection.parallel.ParSeq reverseMap(final Function1 f) {
      return ParSeqLike.reverseMap$(this, f);
   }

   public boolean startsWith(final IterableOnce that, final int offset) {
      return ParSeqLike.startsWith$(this, that, offset);
   }

   public int startsWith$default$2() {
      return ParSeqLike.startsWith$default$2$(this);
   }

   public boolean sameElements(final IterableOnce that) {
      return ParSeqLike.sameElements$(this, that);
   }

   public boolean endsWith(final scala.collection.parallel.ParSeq that) {
      return ParSeqLike.endsWith$(this, (scala.collection.parallel.ParSeq)that);
   }

   public boolean endsWith(final Iterable that) {
      return ParSeqLike.endsWith$(this, (Iterable)that);
   }

   public scala.collection.parallel.ParSeq patch(final int from, final Seq patch, final int replaced) {
      return ParSeqLike.patch$(this, from, (Seq)patch, replaced);
   }

   public scala.collection.parallel.ParSeq patch(final int from, final scala.collection.parallel.ParSeq patch, final int replaced) {
      return ParSeqLike.patch$(this, from, (scala.collection.parallel.ParSeq)patch, replaced);
   }

   public scala.collection.parallel.ParSeq updated(final int index, final Object elem) {
      return ParSeqLike.updated$(this, index, elem);
   }

   public scala.collection.parallel.ParSeq $plus$colon(final Object elem) {
      return ParSeqLike.$plus$colon$(this, elem);
   }

   public scala.collection.parallel.ParSeq $colon$plus(final Object elem) {
      return ParSeqLike.$colon$plus$(this, elem);
   }

   public scala.collection.parallel.ParSeq union(final scala.collection.parallel.ParSeq that) {
      return ParSeqLike.union$(this, (scala.collection.parallel.ParSeq)that);
   }

   public scala.collection.parallel.ParSeq union(final Seq that) {
      return ParSeqLike.union$(this, (Seq)that);
   }

   public scala.collection.parallel.ParSeq padTo(final int len, final Object elem) {
      return ParSeqLike.padTo$(this, len, elem);
   }

   public scala.collection.parallel.ParSeq zip(final scala.collection.parallel.ParIterable that) {
      return ParSeqLike.zip$(this, that);
   }

   public boolean corresponds(final scala.collection.parallel.ParSeq that, final Function2 p) {
      return ParSeqLike.corresponds$(this, that, p);
   }

   public scala.collection.parallel.ParSeq diff(final scala.collection.parallel.ParSeq that) {
      return ParSeqLike.diff$(this, (scala.collection.parallel.ParSeq)that);
   }

   public scala.collection.parallel.ParSeq diff(final Seq that) {
      return ParSeqLike.diff$(this, (Seq)that);
   }

   public scala.collection.parallel.ParSeq intersect(final Seq that) {
      return ParSeqLike.intersect$(this, that);
   }

   public scala.collection.parallel.ParSeq distinct() {
      return ParSeqLike.distinct$(this);
   }

   public SeqSplitter down(final IterableSplitter p) {
      return ParSeqLike.down$(this, p);
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

   public scala.collection.parallel.ParIterable repr() {
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

   public scala.collection.parallel.ParIterable tail() {
      return ParIterableLike.tail$(this);
   }

   public Object last() {
      return ParIterableLike.last$(this);
   }

   public Option lastOption() {
      return ParIterableLike.lastOption$(this);
   }

   public scala.collection.parallel.ParIterable init() {
      return ParIterableLike.init$(this);
   }

   public scala.collection.parallel.ParIterable par() {
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

   public scala.collection.parallel.ParIterable sequentially(final Function1 b) {
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

   public scala.collection.parallel.ParIterable map(final Function1 f) {
      return ParIterableLike.map$(this, f);
   }

   public scala.collection.parallel.ParIterable collect(final PartialFunction pf) {
      return ParIterableLike.collect$(this, pf);
   }

   public scala.collection.parallel.ParIterable flatMap(final Function1 f) {
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

   public scala.collection.parallel.ParIterable withFilter(final Function1 pred) {
      return ParIterableLike.withFilter$(this, pred);
   }

   public scala.collection.parallel.ParIterable filter(final Function1 pred) {
      return ParIterableLike.filter$(this, pred);
   }

   public scala.collection.parallel.ParIterable filterNot(final Function1 pred) {
      return ParIterableLike.filterNot$(this, pred);
   }

   public scala.collection.parallel.ParIterable $plus$plus(final IterableOnce that) {
      return ParIterableLike.$plus$plus$(this, that);
   }

   public Tuple2 partition(final Function1 pred) {
      return ParIterableLike.partition$(this, pred);
   }

   public ParMap groupBy(final Function1 f) {
      return ParIterableLike.groupBy$(this, f);
   }

   public scala.collection.parallel.ParIterable take(final int n) {
      return ParIterableLike.take$(this, n);
   }

   public scala.collection.parallel.ParIterable drop(final int n) {
      return ParIterableLike.drop$(this, n);
   }

   public scala.collection.parallel.ParIterable slice(final int unc_from, final int unc_until) {
      return ParIterableLike.slice$(this, unc_from, unc_until);
   }

   public Tuple2 splitAt(final int n) {
      return ParIterableLike.splitAt$(this, n);
   }

   public scala.collection.parallel.ParIterable scan(final Object z, final Function2 op) {
      return ParIterableLike.scan$(this, z, op);
   }

   public Iterable scanLeft(final Object z, final Function2 op) {
      return ParIterableLike.scanLeft$(this, z, op);
   }

   public Iterable scanRight(final Object z, final Function2 op) {
      return ParIterableLike.scanRight$(this, z, op);
   }

   public scala.collection.parallel.ParIterable takeWhile(final Function1 pred) {
      return ParIterableLike.takeWhile$(this, pred);
   }

   public Tuple2 span(final Function1 pred) {
      return ParIterableLike.span$(this, pred);
   }

   public scala.collection.parallel.ParIterable dropWhile(final Function1 pred) {
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

   public scala.collection.parallel.ParIterable zip(final Iterable that) {
      return ParIterableLike.zip$(this, (Iterable)that);
   }

   public scala.collection.parallel.ParIterable zipWithIndex() {
      return ParIterableLike.zipWithIndex$(this);
   }

   public scala.collection.parallel.ParIterable zipAll(final scala.collection.parallel.ParIterable that, final Object thisElem, final Object thatElem) {
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
   public scala.collection.parallel.ParIterable toTraversable() {
      return ParIterableLike.toTraversable$(this);
   }

   public ParSet toSet() {
      return ParIterableLike.toSet$(this);
   }

   public ParMap toMap(final .less.colon.less ev) {
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

   public scala.collection.immutable.Seq brokenInvariants() {
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

   public Combiner newBuilder() {
      return GenericParTemplate.newBuilder$(this);
   }

   public Combiner newCombiner() {
      return GenericParTemplate.newCombiner$(this);
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

   public scala.collection.parallel.ParIterable flatten(final Function1 asTraversable) {
      return GenericTraversableTemplate.flatten$(this, asTraversable);
   }

   public scala.collection.parallel.ParIterable transpose(final Function1 asTraversable) {
      return GenericTraversableTemplate.transpose$(this, asTraversable);
   }

   public ParIterator$ ParIterator() {
      if (this.ParIterator$module == null) {
         this.ParIterator$lzycompute$1();
      }

      return this.ParIterator$module;
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

   public int length() {
      return this.length;
   }

   public Object apply(final int idx) {
      if (0 <= idx && idx < this.length()) {
         return this.scala$collection$parallel$immutable$Repetition$$elem;
      } else {
         throw new IndexOutOfBoundsException(String.valueOf(BoxesRunTime.boxToInteger(idx)));
      }
   }

   public int knownSize() {
      return this.length();
   }

   public scala.collection.immutable.Seq seq() {
      return new scala.collection.immutable.Seq() {
         // $FF: synthetic field
         private final Repetition $outer;

         public Nothing parCombiner() {
            return CustomParallelizable.parCombiner$(this);
         }

         public final scala.collection.immutable.Seq toSeq() {
            return scala.collection.immutable.Seq.toSeq$(this);
         }

         public SeqFactory iterableFactory() {
            return scala.collection.immutable.Seq.iterableFactory$(this);
         }

         public int length() {
            return this.$outer.length();
         }

         public Object apply(final int idx) {
            return this.$outer.apply(idx);
         }

         public Iterator iterator() {
            return scala.package..MODULE$.Iterator().continually(() -> this.$outer.scala$collection$parallel$immutable$Repetition$$elem).take(this.length());
         }

         public ParSeq par() {
            return this.$outer;
         }

         public {
            if (Repetition.this == null) {
               throw null;
            } else {
               this.$outer = Repetition.this;
               scala.collection.immutable.Iterable.$init$(this);
               scala.collection.immutable.Seq.$init$(this);
               Parallelizable.$init$(this);
               CustomParallelizable.$init$(this);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Nothing update(final int idx, final Object elem) {
      throw new UnsupportedOperationException();
   }

   public ParIterator splitter() {
      return new ParIterator(this.ParIterator().$lessinit$greater$default$1(), this.ParIterator().$lessinit$greater$default$2(), this.ParIterator().$lessinit$greater$default$3());
   }

   private final void ParIterator$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ParIterator$module == null) {
            this.ParIterator$module = new ParIterator$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

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

   public Repetition(final Object elem, final int length) {
      this.scala$collection$parallel$immutable$Repetition$$elem = elem;
      this.length = length;
      GenericTraversableTemplate.$init$(this);
      GenericParTemplate.$init$(this);
      IterableOnce.$init$(this);
      Parallelizable.$init$(this);
      CustomParallelizable.$init$(this);
      ParIterableLike.$init$(this);
      scala.collection.parallel.ParIterable.$init$(this);
      ParSeqLike.$init$(this);
      scala.collection.parallel.ParSeq.$init$(this);
      ParIterable.$init$(this);
      ParSeq.$init$(this);
   }

   public class ParIterator implements SeqSplitter {
      private int i;
      private final int until;
      private final Object elem;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final Repetition $outer;

      public scala.collection.immutable.Seq splitWithSignalling() {
         return SeqSplitter.splitWithSignalling$(this);
      }

      public scala.collection.immutable.Seq psplitWithSignalling(final scala.collection.immutable.Seq sizes) {
         return SeqSplitter.psplitWithSignalling$(this, sizes);
      }

      public SeqSplitter.RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.newTaken$(this, until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.take$(this, n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.slice$(this, from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.map$(this, f);
      }

      public SeqSplitter.RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.appendParSeq$(this, that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.zipParSeq$(this, that);
      }

      public SeqSplitter.RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.reverse$(this);
      }

      public SeqSplitter.Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.patchParSeq$(this, from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public boolean shouldSplitFurther(final scala.collection.parallel.ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.buildString$(this, closure);
      }

      public String debugInformation() {
         return IterableSplitter.debugInformation$(this);
      }

      public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
         return IterableSplitter.newSliceInternal$(this, it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.drop$(this, n);
      }

      public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.appendParIterable$(this, that);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

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

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
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

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
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

      public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public scala.collection.immutable.Seq toSeq() {
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

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public int i() {
         return this.i;
      }

      public void i_$eq(final int x$1) {
         this.i = x$1;
      }

      public int until() {
         return this.until;
      }

      public int remaining() {
         return this.until() - this.i();
      }

      public boolean hasNext() {
         return this.i() < this.until();
      }

      public Object next() {
         this.i_$eq(this.i() + 1);
         return this.elem;
      }

      public ParIterator dup() {
         return this.scala$collection$parallel$immutable$Repetition$ParIterator$$$outer().new ParIterator(this.i(), this.until(), this.elem);
      }

      public scala.collection.immutable.Seq psplit(final scala.collection.immutable.Seq sizes) {
         scala.collection.immutable.Seq incr = (scala.collection.immutable.Seq)sizes.scanLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x$1, x$2) -> x$1 + x$2);
         return (scala.collection.immutable.Seq)((IterableOps)((IterableOps)incr.init()).zip((IterableOnce)incr.tail())).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$psplit$2(check$ifrefutable$1))).map((x$3) -> {
            if (x$3 != null) {
               int start = x$3._1$mcI$sp();
               int end = x$3._2$mcI$sp();
               return this.scala$collection$parallel$immutable$Repetition$ParIterator$$$outer().new ParIterator(this.i() + start, scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.i() + end), this.until()), this.elem);
            } else {
               throw new MatchError(x$3);
            }
         });
      }

      public scala.collection.immutable.Seq split() {
         return this.psplit(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{this.remaining() / 2, this.remaining() - this.remaining() / 2}));
      }

      // $FF: synthetic method
      public Repetition scala$collection$parallel$immutable$Repetition$ParIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$psplit$2(final Tuple2 check$ifrefutable$1) {
         return check$ifrefutable$1 != null;
      }

      public ParIterator(final int i, final int until, final Object elem) {
         this.i = i;
         this.until = until;
         this.elem = elem;
         if (Repetition.this == null) {
            throw null;
         } else {
            this.$outer = Repetition.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            AugmentedSeqIterator.$init$(this);
            SeqSplitter.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ParIterator$ {
      // $FF: synthetic field
      private final Repetition $outer;

      public int $lessinit$greater$default$1() {
         return 0;
      }

      public int $lessinit$greater$default$2() {
         return this.$outer.length();
      }

      public Object $lessinit$greater$default$3() {
         return this.$outer.scala$collection$parallel$immutable$Repetition$$elem;
      }

      public ParIterator$() {
         if (Repetition.this == null) {
            throw null;
         } else {
            this.$outer = Repetition.this;
            super();
         }
      }
   }
}
