package scala.collection.parallel.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Parallelizable;
import scala.collection.Set;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.OldHashSet;
import scala.collection.immutable.OldHashSet$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.immutable.TrieIterator;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.ParSetLike;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=h\u0001B\u0013'\u0001=B\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\u0007;\u0002!\tA\n0\t\u000bu\u0003A\u0011\u00011\t\u000b\u0005\u0004A\u0011\t2\t\u000b\u0019\u0004A\u0011I4\t\u000b!\u0004A\u0011A5\t\u000b5\u0004A\u0011\t8\t\u000b=\u0004A\u0011\u00019\t\u000bM\u0004A\u0011\u0001;\t\u000bY\u0004A\u0011A<\t\u000bq\u0004A\u0011A?\t\r\u0005\r\u0001\u0001\"\u0011~\u0011\u001d\t)\u0001\u0001C)\u0003\u000f1a!a\u000b\u0001\u0001\u00055\u0002BCA\u0018\u001d\t\u0005\r\u0011\"\u0001\u00022!Q\u0011\u0011\b\b\u0003\u0002\u0004%\t!a\u000f\t\u0015\u0005\u001dcB!A!B\u0013\t\u0019\u0004C\u0005\u0002J9\u0011)\u0019!C\u0001{\"I\u00111\n\b\u0003\u0002\u0003\u0006IA \u0005\u0007;:!\t!!\u0014\t\u0011\u0005]c\u00021A\u0005\u0002uD\u0011\"!\u0017\u000f\u0001\u0004%\t!a\u0017\t\u000f\u0005}c\u0002)Q\u0005}\"1\u0011\u0011\r\b\u0005\u0002%Dq!a\u0019\u000f\t\u0013\t)\u0007C\u0004\u0002l9!\t!!\u001c\t\u000f\u0005Ud\u0002\"\u0001\u0002x!9\u0011\u0011\u0010\b\u0005\u0002\u0005m\u0004BBA?\u001d\u0011\u0005QpB\u0004\u0002\f\u001aB\t!!$\u0007\r\u00152\u0003\u0012AAH\u0011\u0019iv\u0004\"\u0001\u0002&\"9\u0011qU\u0010\u0005\u0002\u0005%\u0006bBA[?\u0011\r\u0011q\u0017\u0005\b\u0003\u001b|B\u0011AAh\u0011%\tynHA\u0001\n\u0013\t\tO\u0001\u0006QCJD\u0015m\u001d5TKRT!a\n\u0015\u0002\u0013%lW.\u001e;bE2,'BA\u0015+\u0003!\u0001\u0018M]1mY\u0016d'BA\u0016-\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002[\u0005)1oY1mC\u000e\u0001QC\u0001\u0019<'\u0019\u0001\u0011'\u000e#L+B\u0011!gM\u0007\u0002Y%\u0011A\u0007\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Y:\u0014(D\u0001'\u0013\tAdE\u0001\u0004QCJ\u001cV\r\u001e\t\u0003umb\u0001\u0001B\u0003=\u0001\t\u0007QHA\u0001U#\tq\u0014\t\u0005\u00023\u007f%\u0011\u0001\t\f\u0002\b\u001d>$\b.\u001b8h!\t\u0011$)\u0003\u0002DY\t\u0019\u0011I\\=\u0011\t\u0015C\u0015HS\u0007\u0002\r*\u0011qIK\u0001\bO\u0016tWM]5d\u0013\tIeI\u0001\nHK:,'/[2QCJ$V-\u001c9mCR,\u0007C\u0001\u001c\u0001!\u0019aU*\u000f&P!6\t\u0001&\u0003\u0002OQ\tQ\u0001+\u0019:TKRd\u0015n[3\u0011\u0007Y\u0002\u0011\bE\u0002R'fj\u0011A\u0015\u0006\u0003O)J!\u0001\u0016*\u0003\u0015=cG\rS1tQN+G\u000f\u0005\u0002W3:\u0011!gV\u0005\u000312\nq\u0001]1dW\u0006<W-\u0003\u0002[7\na1+\u001a:jC2L'0\u00192mK*\u0011\u0001\fL\u0001\u0005iJLW-\u0001\u0004=S:LGO\u0010\u000b\u0003\u001f~CQ\u0001\u0018\u0002A\u0002A#\u0012aT\u0001\nG>l\u0007/\u00198j_:,\u0012a\u0019\t\u0004\u000b\u0012T\u0015BA3G\u0005M9UM\\3sS\u000e\u0004\u0016M]\"p[B\fg.[8o\u0003\u0015)W\u000e\u001d;z+\u0005y\u0015\u0001C:qY&$H/\u001a:\u0016\u0003)\u00042\u0001T6:\u0013\ta\u0007F\u0001\tJi\u0016\u0014\u0018M\u00197f'Bd\u0017\u000e\u001e;fe\u0006\u00191/Z9\u0016\u0003A\u000ba\u0001J7j]V\u001cHCA(r\u0011\u0015\u0011\b\u00021\u0001:\u0003\u0005)\u0017!\u0002\u0013qYV\u001cHCA(v\u0011\u0015\u0011\u0018\u00021\u0001:\u0003!\u0019wN\u001c;bS:\u001cHC\u0001=|!\t\u0011\u00140\u0003\u0002{Y\t9!i\\8mK\u0006t\u0007\"\u0002:\u000b\u0001\u0004I\u0014\u0001B:ju\u0016,\u0012A \t\u0003e}L1!!\u0001-\u0005\rIe\u000e^\u0001\nW:|wO\\*ju\u0016\fQA]3vg\u0016,b!!\u0003\u0002\u0014\u0005eACBA\u0006\u0003;\t9\u0003E\u0004M\u0003\u001b\t\t\"a\u0006\n\u0007\u0005=\u0001F\u0001\u0005D_6\u0014\u0017N\\3s!\rQ\u00141\u0003\u0003\u0007\u0003+i!\u0019A\u001f\u0003\u0003M\u00032AOA\r\t\u0019\tY\"\u0004b\u0001{\t!A\u000b[1u\u0011\u001d\ty\"\u0004a\u0001\u0003C\tAa\u001c7eGB)!'a\t\u0002\f%\u0019\u0011Q\u0005\u0017\u0003\r=\u0003H/[8o\u0011\u001d\tI#\u0004a\u0001\u0003\u0017\tAA\\3xG\n\u0011\u0002+\u0019:ICND7+\u001a;Ji\u0016\u0014\u0018\r^8s'\rq\u0011G[\u0001\u0007iJLG/\u001a:\u0016\u0005\u0005M\u0002\u0003\u0002,\u00026eJ1!a\u000e\\\u0005!IE/\u001a:bi>\u0014\u0018A\u0003;sSR,'o\u0018\u0013fcR!\u0011QHA\"!\r\u0011\u0014qH\u0005\u0004\u0003\u0003b#\u0001B+oSRD\u0011\"!\u0012\u0011\u0003\u0003\u0005\r!a\r\u0002\u0007a$\u0013'A\u0004ue&$XM\u001d\u0011\u0002\u0005MT\u0018aA:{AQ1\u0011qJA*\u0003+\u00022!!\u0015\u000f\u001b\u0005\u0001\u0001bBA\u0018)\u0001\u0007\u00111\u0007\u0005\u0007\u0003\u0013\"\u0002\u0019\u0001@\u0002\u0003%\fQ![0%KF$B!!\u0010\u0002^!A\u0011Q\t\f\u0002\u0002\u0003\u0007a0\u0001\u0002jA\u0005\u0019A-\u001e9\u0002\u001f\u0011,\bO\u0012:p[&#XM]1u_J$B!a\u0014\u0002h!9\u0011\u0011N\rA\u0002\u0005M\u0012AA5u\u0003\u0015\u0019\b\u000f\\5u+\t\ty\u0007\u0005\u0003W\u0003cR\u0017bAA:7\n\u00191+Z9\u0002\t9,\u0007\u0010\u001e\u000b\u0002s\u00059\u0001.Y:OKb$X#\u0001=\u0002\u0013I,W.Y5oS:<\u0007f\u0002\u0001\u0002\u0002\u0006\u001d\u0015\u0011\u0012\t\u0004e\u0005\r\u0015bAACY\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0003\u0005Q\u0001+\u0019:ICND7+\u001a;\u0011\u0005Yz2#B\u0010\u0002\u0012\u0006]\u0005\u0003B#\u0002\u0014*K1!!&G\u00055\u0001\u0016M]*fi\u001a\u000b7\r^8ssB!\u0011\u0011TAR\u001b\t\tYJ\u0003\u0003\u0002\u001e\u0006}\u0015AA5p\u0015\t\t\t+\u0001\u0003kCZ\f\u0017b\u0001.\u0002\u001cR\u0011\u0011QR\u0001\f]\u0016<8i\\7cS:,'/\u0006\u0003\u0002,\u0006EVCAAW!\u001da\u0015QBAX\u0003g\u00032AOAY\t\u0015a\u0014E1\u0001>!\u00111\u0004!a,\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\r\u0005e\u0016QYAe+\t\tY\fE\u0005F\u0003{\u000b\t-a2\u0002L&\u0019\u0011q\u0018$\u0003\u001d\r\u000bgnQ8nE&tWM\u0012:p[B!a\u0007AAb!\rQ\u0014Q\u0019\u0003\u0007\u0003+\u0011#\u0019A\u001f\u0011\u0007i\nI\rB\u0003=E\t\u0007Q\b\u0005\u00037\u0001\u0005\u001d\u0017\u0001\u00034s_6$&/[3\u0016\t\u0005E\u0017q\u001b\u000b\u0005\u0003'\fI\u000e\u0005\u00037\u0001\u0005U\u0007c\u0001\u001e\u0002X\u0012)Ah\tb\u0001{!9\u00111\\\u0012A\u0002\u0005u\u0017!\u0001;\u0011\tE\u001b\u0016Q[\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003G\u0004B!!:\u0002l6\u0011\u0011q\u001d\u0006\u0005\u0003S\fy*\u0001\u0003mC:<\u0017\u0002BAw\u0003O\u0014aa\u00142kK\u000e$\b"
)
public class ParHashSet implements ParSet, Serializable {
   private static final long serialVersionUID = 1L;
   private final OldHashSet trie;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public static ParHashSet fromTrie(final OldHashSet t) {
      return ParHashSet$.MODULE$.fromTrie(t);
   }

   public static CanCombineFrom canBuildFrom() {
      return ParHashSet$.MODULE$.canBuildFrom();
   }

   public static Factory toFactory() {
      return ParHashSet$.MODULE$.toFactory();
   }

   public String stringPrefix() {
      return ParSet.stringPrefix$(this);
   }

   public ParSet toSet() {
      return ParSet.toSet$(this);
   }

   public ParIterable toIterable() {
      return ParIterable.toIterable$(this);
   }

   public ParSeq toSeq() {
      return ParIterable.toSeq$(this);
   }

   public final boolean apply(final Object elem) {
      return ParSetLike.apply$(this, elem);
   }

   public scala.collection.parallel.ParSet intersect(final scala.collection.parallel.ParSet that) {
      return ParSetLike.intersect$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet intersect(final Set that) {
      return ParSetLike.intersect$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet $amp(final scala.collection.parallel.ParSet that) {
      return ParSetLike.$amp$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet $amp(final Set that) {
      return ParSetLike.$amp$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet $bar(final scala.collection.parallel.ParSet that) {
      return ParSetLike.$bar$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet $bar(final Set that) {
      return ParSetLike.$bar$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet $amp$tilde(final scala.collection.parallel.ParSet that) {
      return ParSetLike.$amp$tilde$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet $amp$tilde(final Set that) {
      return ParSetLike.$amp$tilde$(this, (Set)that);
   }

   public boolean subsetOf(final scala.collection.parallel.ParSet that) {
      return ParSetLike.subsetOf$(this, that);
   }

   public boolean equals(final Object that) {
      return ParSetLike.equals$(this, that);
   }

   public int hashCode() {
      return ParSetLike.hashCode$(this);
   }

   public boolean canEqual(final Object other) {
      return ParSetLike.canEqual$(this, other);
   }

   public scala.collection.parallel.ParSet union(final Set that) {
      return ParSetLike.union$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet union(final scala.collection.parallel.ParSet that) {
      return ParSetLike.union$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet diff(final Set that) {
      return ParSetLike.diff$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet diff(final scala.collection.parallel.ParSet that) {
      return ParSetLike.diff$(this, (scala.collection.parallel.ParSet)that);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
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

   public Splitter iterator() {
      return ParIterableLike.iterator$(this);
   }

   public scala.collection.parallel.ParIterable par() {
      return ParIterableLike.par$(this);
   }

   public boolean isStrictSplitterCollection() {
      return ParIterableLike.isStrictSplitterCollection$(this);
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

   public boolean sameElements(final IterableOnce that) {
      return ParIterableLike.sameElements$(this, that);
   }

   public scala.collection.parallel.ParIterable zip(final scala.collection.parallel.ParIterable that) {
      return ParIterableLike.zip$(this, (scala.collection.parallel.ParIterable)that);
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

   public GenericParCompanion companion() {
      return ParHashSet$.MODULE$;
   }

   public ParHashSet empty() {
      return new ParHashSet();
   }

   public IterableSplitter splitter() {
      return new ParHashSetIterator(this.trie.iterator(), this.trie.size());
   }

   public OldHashSet seq() {
      return this.trie;
   }

   public ParHashSet $minus(final Object e) {
      return new ParHashSet((OldHashSet)this.trie.$minus(e));
   }

   public ParHashSet $plus(final Object e) {
      return new ParHashSet((OldHashSet)this.trie.$plus(e));
   }

   public boolean contains(final Object e) {
      return this.trie.contains(e);
   }

   public int size() {
      return this.trie.size();
   }

   public int knownSize() {
      return this.trie.size();
   }

   public Combiner reuse(final Option oldc, final Combiner newc) {
      if (oldc instanceof Some) {
         Some var5 = (Some)oldc;
         Combiner old = (Combiner)var5.value();
         return old;
      } else if (scala.None..MODULE$.equals(oldc)) {
         return newc;
      } else {
         throw new MatchError(oldc);
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

   public ParHashSet(final OldHashSet trie) {
      this.trie = trie;
      GenericTraversableTemplate.$init$(this);
      GenericParTemplate.$init$(this);
      IterableOnce.$init$(this);
      Parallelizable.$init$(this);
      CustomParallelizable.$init$(this);
      ParIterableLike.$init$(this);
      scala.collection.parallel.ParIterable.$init$(this);
      Function1.$init$(this);
      ParSetLike.$init$(this);
      scala.collection.parallel.ParSet.$init$(this);
      ParIterable.$init$(this);
      ParSet.$init$(this);
   }

   public ParHashSet() {
      this(OldHashSet$.MODULE$.empty());
   }

   public class ParHashSetIterator implements IterableSplitter {
      private Iterator triter;
      private final int sz;
      private int i;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final ParHashSet $outer;

      public Seq splitWithSignalling() {
         return IterableSplitter.splitWithSignalling$(this);
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

      public IterableSplitter.Taken newTaken(final int until) {
         return IterableSplitter.newTaken$(this, until);
      }

      public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
         return IterableSplitter.newSliceInternal$(this, it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.drop$(this, n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.take$(this, n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.slice$(this, from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.map$(this, f);
      }

      public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.appendParIterable$(this, that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.zipParSeq$(this, that);
      }

      public IterableSplitter.ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
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

      public scala.collection.immutable.Set toSet() {
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

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public Iterator triter() {
         return this.triter;
      }

      public void triter_$eq(final Iterator x$1) {
         this.triter = x$1;
      }

      public int sz() {
         return this.sz;
      }

      public int i() {
         return this.i;
      }

      public void i_$eq(final int x$1) {
         this.i = x$1;
      }

      public IterableSplitter dup() {
         Iterator var2 = this.triter();
         if (var2 instanceof TrieIterator) {
            TrieIterator var3 = (TrieIterator)var2;
            return this.dupFromIterator(var3.dupIterator());
         } else {
            Buffer buff = this.triter().toBuffer();
            this.triter_$eq(buff.iterator());
            return this.dupFromIterator(buff.iterator());
         }
      }

      private ParHashSetIterator dupFromIterator(final Iterator it) {
         ParHashSetIterator phit = this.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer().new ParHashSetIterator(it, this.sz());
         phit.i_$eq(this.i());
         return phit;
      }

      public Seq split() {
         if (this.remaining() < 2) {
            return new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$);
         } else {
            Iterator var4 = this.triter();
            if (var4 instanceof TrieIterator) {
               TrieIterator var5 = (TrieIterator)var4;
               int previousRemaining = this.remaining();
               Tuple2 var8 = var5.split();
               if (var8 != null) {
                  Tuple2 var9 = (Tuple2)var8._1();
                  Iterator snd = (Iterator)var8._2();
                  if (var9 != null) {
                     Iterator fst = (Iterator)var9._1();
                     int fstlength = var9._2$mcI$sp();
                     Tuple3 var7 = new Tuple3(fst, BoxesRunTime.boxToInteger(fstlength), snd);
                     Iterator fstx = (Iterator)var7._1();
                     int fstlengthx = BoxesRunTime.unboxToInt(var7._2());
                     Iterator snd = (Iterator)var7._3();
                     int sndlength = previousRemaining - fstlengthx;
                     return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer().new ParHashSetIterator(fstx, fstlengthx), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer().new ParHashSetIterator(snd, sndlength), scala.collection.immutable.Nil..MODULE$));
                  }
               }

               throw new MatchError(var8);
            } else {
               Buffer buff = this.triter().toBuffer();
               Tuple2 var19 = buff.splitAt(buff.length() / 2);
               if (var19 != null) {
                  Buffer fp = (Buffer)var19._1();
                  Buffer sp = (Buffer)var19._2();
                  Tuple2 var18 = new Tuple2(fp, sp);
                  Buffer fp = (Buffer)var18._1();
                  Buffer sp = (Buffer)var18._2();
                  return (Seq)(new scala.collection.immutable..colon.colon(fp, new scala.collection.immutable..colon.colon(sp, scala.collection.immutable.Nil..MODULE$))).map((b) -> this.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer().new ParHashSetIterator(b.iterator(), b.length()));
               } else {
                  throw new MatchError(var19);
               }
            }
         }
      }

      public Object next() {
         this.i_$eq(this.i() + 1);
         return this.triter().next();
      }

      public boolean hasNext() {
         return this.i() < this.sz();
      }

      public int remaining() {
         return this.sz() - this.i();
      }

      // $FF: synthetic method
      public ParHashSet scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer() {
         return this.$outer;
      }

      public ParHashSetIterator(final Iterator triter, final int sz) {
         this.triter = triter;
         this.sz = sz;
         if (ParHashSet.this == null) {
            throw null;
         } else {
            this.$outer = ParHashSet.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            this.i = 0;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
