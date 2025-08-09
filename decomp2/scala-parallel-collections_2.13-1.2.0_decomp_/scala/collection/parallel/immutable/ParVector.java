package scala.collection.parallel.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
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
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.immutable.VectorIterator;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
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
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d\u0001\u0002\u000e\u001c\u0001\u0011B\u0001\"\u0015\u0001\u0003\u0002\u0003\u0006I!\u0012\u0005\u0006%\u0002!\ta\u0015\u0005\u0006+\u0002!\tE\u0016\u0005\u0007%\u0002!\t!a\u0005\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018!9\u00111\u0005\u0001\u0005\u0002\u0005\u0015\u0002bBA\u0014\u0001\u0011\u0005\u0013Q\u0005\u0005\b\u0003S\u0001A\u0011AA\u0016\u0011\u001d\t\u0019\u0004\u0001C!\u0003kAq!a\u000e\u0001\t\u0003\n)D\u0002\u0004\u0002:\u0001\u0001\u00111\b\u0005\u000b\u0003\u0007Z!\u0011!Q\u0001\n\u0005u\u0001BCA#\u0017\t\u0005\t\u0015!\u0003\u0002\u001e!1!k\u0003C\u0001\u0003\u000fBq!!\u0015\f\t\u0003\t)\u0003C\u0004\u0002T-!\t!a\u000b\t\u000f\u0005U3\u0002\"\u0001\u0002X!9\u0011qL\u0006\u0005\u0002\u0005\u0005tABA?7!\u0005qKB\u0003\u001b7!\u0005\u0001\fC\u0003S)\u0011\u00051\rC\u0003e)\u0011\rQ\rC\u0003r)\u0011\u0005!\u000fC\u0003{)\u0011\u00051\u0010C\u0005\u0002\u0004Q\t\t\u0011\"\u0003\u0002\u0006\tI\u0001+\u0019:WK\u000e$xN\u001d\u0006\u00039u\t\u0011\"[7nkR\f'\r\\3\u000b\u0005yy\u0012\u0001\u00039be\u0006dG.\u001a7\u000b\u0005\u0001\n\u0013AC2pY2,7\r^5p]*\t!%A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005\u0015\u00024C\u0002\u0001'Ue\u0002%\n\u0005\u0002(Q5\t\u0011%\u0003\u0002*C\t1\u0011I\\=SK\u001a\u00042a\u000b\u0017/\u001b\u0005Y\u0012BA\u0017\u001c\u0005\u0019\u0001\u0016M]*fcB\u0011q\u0006\r\u0007\u0001\t\u0019\t\u0004\u0001\"b\u0001e\t\tA+\u0005\u00024mA\u0011q\u0005N\u0005\u0003k\u0005\u0012qAT8uQ&tw\r\u0005\u0002(o%\u0011\u0001(\t\u0002\u0004\u0003:L\b\u0003\u0002\u001e>]}j\u0011a\u000f\u0006\u0003y}\tqaZ3oKJL7-\u0003\u0002?w\t\u0011r)\u001a8fe&\u001c\u0007+\u0019:UK6\u0004H.\u0019;f!\tY\u0003\u0001\u0005\u0004B\u0005:zD)R\u0007\u0002;%\u00111)\b\u0002\u000b!\u0006\u00148+Z9MS.,\u0007cA\u0016\u0001]A\u0019a\t\u0013\u0018\u000e\u0003\u001dS!\u0001H\u0010\n\u0005%;%A\u0002,fGR|'\u000f\u0005\u0002L\u001d:\u0011q\u0005T\u0005\u0003\u001b\u0006\nq\u0001]1dW\u0006<W-\u0003\u0002P!\na1+\u001a:jC2L'0\u00192mK*\u0011Q*I\u0001\u0007m\u0016\u001cGo\u001c:\u0002\rqJg.\u001b;?)\t!E\u000bC\u0003R\u0005\u0001\u0007Q)A\u0005d_6\u0004\u0018M\\5p]V\tq\u000b\u0005\u0002,)M\u0019A#\u0017/\u0011\u0007iRv(\u0003\u0002\\w\tQ\u0001+\u0019:GC\u000e$xN]=\u0011\u0005u\u0013W\"\u00010\u000b\u0005}\u0003\u0017AA5p\u0015\u0005\t\u0017\u0001\u00026bm\u0006L!a\u00140\u0015\u0003]\u000bAbY1o\u0005VLG\u000e\u001a$s_6,2A\u001a7p+\u00059\u0007#\u0002\u001eiU:\u0004\u0018BA5<\u00059\u0019\u0015M\\\"p[\nLg.\u001a$s_6\u00042a\u000b\u0001l!\tyC\u000eB\u0003n-\t\u0007!GA\u0001T!\tys\u000eB\u00032-\t\u0007!\u0007E\u0002,\u00019\f!B\\3x\u0005VLG\u000eZ3s+\t\u0019\b0F\u0001u!\u0011\tUo^=\n\u0005Yl\"\u0001C\"p[\nLg.\u001a:\u0011\u0005=BH!B\u0019\u0018\u0005\u0004\u0011\u0004cA\u0016\u0001o\u0006Ya.Z<D_6\u0014\u0017N\\3s+\tax0F\u0001~!\u0015\tUO`A\u0001!\tys\u0010B\u000321\t\u0007!\u0007E\u0002,\u0001y\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0002\u0011\t\u0005%\u0011qB\u0007\u0003\u0003\u0017Q1!!\u0004a\u0003\u0011a\u0017M\\4\n\t\u0005E\u00111\u0002\u0002\u0007\u001f\nTWm\u0019;\u0015\u0003\u0011\u000bQ!\u00199qYf$2ALA\r\u0011\u001d\tY\"\u0002a\u0001\u0003;\t1!\u001b3y!\r9\u0013qD\u0005\u0004\u0003C\t#aA%oi\u00061A.\u001a8hi\",\"!!\b\u0002\u0013-twn\u001e8TSj,\u0017\u0001C:qY&$H/\u001a:\u0016\u0005\u00055\u0002\u0003B!\u000209J1!!\r\u001e\u0005-\u0019V-]*qY&$H/\u001a:\u0002\u0007M,\u0017/F\u0001F\u0003!!xNV3di>\u0014(!\u0005)beZ+7\r^8s\u0013R,'/\u0019;peN)1\"!\u0010\u0002.A!a)a\u0010/\u0013\r\t\te\u0012\u0002\u000f-\u0016\u001cGo\u001c:Ji\u0016\u0014\u0018\r^8s\u0003\u0019y6\u000f^1si\u0006!q,\u001a8e)\u0019\tI%!\u0014\u0002PA\u0019\u00111J\u0006\u000e\u0003\u0001Aq!a\u0011\u000f\u0001\u0004\ti\u0002C\u0004\u0002F9\u0001\r!!\b\u0002\u0013I,W.Y5oS:<\u0017a\u00013va\u0006)1\u000f\u001d7jiV\u0011\u0011\u0011\f\t\u0006\r\u0006m\u0013\u0011J\u0005\u0004\u0003;:%aA*fc\u00061\u0001o\u001d9mSR$B!a\u0019\u0002tA1\u0011QMA9\u0003\u0013r1!a\u001aM\u001d\u0011\tI'a\u001c\u000e\u0005\u0005-$bAA7G\u00051AH]8pizJ\u0011AI\u0005\u0004\u0003;\u0002\u0006bBA;%\u0001\u0007\u0011qO\u0001\u0006g&TXm\u001d\t\u0006O\u0005e\u0014QD\u0005\u0004\u0003w\n#A\u0003\u001fsKB,\u0017\r^3e}\u0005I\u0001+\u0019:WK\u000e$xN\u001d"
)
public class ParVector implements ParSeq, Serializable {
   private final Vector vector;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public static CanCombineFrom canBuildFrom() {
      return ParVector$.MODULE$.canBuildFrom();
   }

   public static scala.collection.parallel.ParIterable iterate(final Object start, final int len, final Function1 f) {
      return ParVector$.MODULE$.iterate(start, len, f);
   }

   public static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Object step, final Integral evidence$2) {
      return ParVector$.MODULE$.range(start, end, step, evidence$2);
   }

   public static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Integral evidence$1) {
      return ParVector$.MODULE$.range(start, end, evidence$1);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return ParVector$.MODULE$.tabulate(n1, n2, n3, n4, n5, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return ParVector$.MODULE$.tabulate(n1, n2, n3, n4, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return ParVector$.MODULE$.tabulate(n1, n2, n3, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final Function2 f) {
      return ParVector$.MODULE$.tabulate(n1, n2, f);
   }

   public static scala.collection.parallel.ParIterable tabulate(final int n, final Function1 f) {
      return ParVector$.MODULE$.tabulate(n, f);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return ParVector$.MODULE$.fill(n1, n2, n3, n4, n5, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return ParVector$.MODULE$.fill(n1, n2, n3, n4, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return ParVector$.MODULE$.fill(n1, n2, n3, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final Function0 elem) {
      return ParVector$.MODULE$.fill(n1, n2, elem);
   }

   public static scala.collection.parallel.ParIterable fill(final int n, final Function0 elem) {
      return ParVector$.MODULE$.fill(n, elem);
   }

   public static scala.collection.parallel.ParIterable concat(final Seq xss) {
      return ParVector$.MODULE$.concat(xss);
   }

   public static Factory toFactory() {
      return ParVector$.MODULE$.toFactory();
   }

   public static scala.collection.parallel.ParIterable empty() {
      return ParVector$.MODULE$.empty();
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

   public scala.collection.parallel.ParSeq patch(final int from, final scala.collection.Seq patch, final int replaced) {
      return ParSeqLike.patch$(this, from, (scala.collection.Seq)patch, replaced);
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

   public scala.collection.parallel.ParSeq union(final scala.collection.Seq that) {
      return ParSeqLike.union$(this, (scala.collection.Seq)that);
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

   public scala.collection.parallel.ParSeq diff(final scala.collection.Seq that) {
      return ParSeqLike.diff$(this, (scala.collection.Seq)that);
   }

   public scala.collection.parallel.ParSeq intersect(final scala.collection.Seq that) {
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

   public ParVector$ companion() {
      return ParVector$.MODULE$;
   }

   public Object apply(final int idx) {
      return this.vector.apply(idx);
   }

   public int length() {
      return this.vector.length();
   }

   public int knownSize() {
      return this.vector.knownSize();
   }

   public SeqSplitter splitter() {
      ParVectorIterator pit = new ParVectorIterator(this.vector.startIndex(), this.vector.endIndex());
      this.vector.initIterator(pit);
      return pit;
   }

   public Vector seq() {
      return this.vector;
   }

   public Vector toVector() {
      return this.vector;
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

   public ParVector(final Vector vector) {
      this.vector = vector;
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

   public ParVector() {
      this((Vector)scala.collection.immutable.Vector..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   public class ParVectorIterator extends VectorIterator implements SeqSplitter {
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final ParVector $outer;

      public Seq splitWithSignalling() {
         return SeqSplitter.splitWithSignalling$(this);
      }

      public Seq psplitWithSignalling(final Seq sizes) {
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

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public int remaining() {
         return this.remainingElementCount();
      }

      public SeqSplitter dup() {
         return (new ParVector(this.remainingVector())).splitter();
      }

      public Seq split() {
         int rem = this.remaining();
         return (Seq)(rem >= 2 ? this.psplit(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{rem / 2, rem - rem / 2})) : new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$));
      }

      public Seq psplit(final Seq sizes) {
         ObjectRef remvector = ObjectRef.create(this.remainingVector());
         Builder splitted = scala.package..MODULE$.List().newBuilder();
         sizes.foreach((JFunction1.mcVI.sp)(sz) -> {
            splitted.$plus$eq(((Vector)remvector.elem).take(sz));
            remvector.elem = ((Vector)remvector.elem).drop(sz);
         });
         return ((List)splitted.result()).map((v) -> (ParVectorIterator)(new ParVector(v)).splitter());
      }

      // $FF: synthetic method
      public ParVector scala$collection$parallel$immutable$ParVector$ParVectorIterator$$$outer() {
         return this.$outer;
      }

      public ParVectorIterator(final int _start, final int _end) {
         if (ParVector.this == null) {
            throw null;
         } else {
            this.$outer = ParVector.this;
            super(_start, _end);
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
}
