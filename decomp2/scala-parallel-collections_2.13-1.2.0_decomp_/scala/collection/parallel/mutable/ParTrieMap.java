package scala.collection.parallel.mutable;

import java.io.Serializable;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
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
import scala.collection.concurrent.BasicNode;
import scala.collection.concurrent.CNode;
import scala.collection.concurrent.INode;
import scala.collection.concurrent.LNode;
import scala.collection.concurrent.MainNode;
import scala.collection.concurrent.SNode;
import scala.collection.concurrent.TNode;
import scala.collection.concurrent.TrieMap;
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
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Shrinkable;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.Task;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\t5b\u0001\u0002\u0015*\u0005IB\u0001\"\u001a\u0001\u0003\u0006\u0004%IA\u001a\u0005\tO\u0002\u0011\t\u0011)A\u0005+\"1\u0001\u000e\u0001C\u0001[%DQ\u0001\u001b\u0001\u0005\u0002-DQ\u0001\u001c\u0001\u0005B5DQ!\u001d\u0001\u0005BIDaa\u001d\u0001!\n#\"\b\"\u0002?\u0001\t\u00032\u0007\"B?\u0001\t\u0003q\bbBA\u0003\u0001\u0011\u0005\u0013q\u0001\u0005\u0007\u0003\u001f\u0001A\u0011A6\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u0011q\u0004\u0001\u0005\u0002\u0005\u0005\u0002bBA\u0015\u0001\u0011\u0005\u00111\u0006\u0005\b\u0003c\u0001A\u0011AA\u001a\u0011\u001d\t9\u0004\u0001C\u0001\u0003sAq!!\u0011\u0001\t\u0003\t\u0019\u0005C\u0004\u0002H\u0001!\t%!\u0013\t\u000f\u0005E\u0003\u0001\"\u0011\u0002J!9\u00111\u000b\u0001\u0005B\u0005UcABA4\u0001\u0001\tI\u0007\u0003\u0006\u0002tU\u0011\t\u0011)A\u0005\u0003\u0017B!\"!\u001e\u0016\u0005\u0003\u0005\u000b\u0011BA&\u0011)\t9(\u0006B\u0001B\u0003%\u0011\u0011\u0010\u0005\u0007QV!\t!!\"\t\u0013\u0005=Q\u00031A\u0005\u0002\u0005%\u0003\"CAG+\u0001\u0007I\u0011AAH\u0011!\t)*\u0006Q!\n\u0005-\u0003bBAL+\u0011\u0005\u0011\u0011\u0014\u0005\b\u0003C+B\u0011AAR\u0011\u001d\t\t,\u0006C\u0001\u0003gCq!a/\u0016\t\u0003\nilB\u0004\u0002D&B\t!!2\u0007\r!J\u0003\u0012AAd\u0011\u0019A'\u0005\"\u0001\u0002\\\"1\u0011O\tC\u0001\u0003;Daa\u001d\u0012\u0005\u0002\u0005-\bbBA\u007fE\u0011\r\u0011q \u0005\n\u0005G\u0011\u0013\u0011!C\u0005\u0005K\u0011!\u0002U1s)JLW-T1q\u0015\tQ3&A\u0004nkR\f'\r\\3\u000b\u00051j\u0013\u0001\u00039be\u0006dG.\u001a7\u000b\u00059z\u0013AC2pY2,7\r^5p]*\t\u0001'A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007Mr\u0004jE\u0004\u0001iaR\u0015k\u00170\u0011\u0005U2T\"A\u0018\n\u0005]z#AB!osJ+g\r\u0005\u0003:uq:U\"A\u0015\n\u0005mJ#A\u0002)be6\u000b\u0007\u000f\u0005\u0002>}1\u0001A!B \u0001\u0005\u0004\u0001%!A&\u0012\u0005\u0005#\u0005CA\u001bC\u0013\t\u0019uFA\u0004O_RD\u0017N\\4\u0011\u0005U*\u0015B\u0001$0\u0005\r\te.\u001f\t\u0003{!#Q!\u0013\u0001C\u0002\u0001\u0013\u0011A\u0016\t\u0006\u0017:ct\tU\u0007\u0002\u0019*\u0011Q*L\u0001\bO\u0016tWM]5d\u0013\tyEJA\u000bHK:,'/[2QCJl\u0015\r\u001d+f[Bd\u0017\r^3\u0011\u0005e\u0002\u0001cB\u001dSy\u001d\u0003F+V\u0005\u0003'&\u0012!\u0002U1s\u001b\u0006\u0004H*[6f!\u0011I\u0004\u0001P$\u0011\tYKFhR\u0007\u0002/*\u0011\u0001,L\u0001\u000bG>t7-\u001e:sK:$\u0018B\u0001.X\u0005\u001d!&/[3NCB\u0004B!\u000f/=\u000f&\u0011Q,\u000b\u0002\u0013!\u0006\u0014HK]5f\u001b\u0006\u00048i\\7cS:,'\u000f\u0005\u0002`E:\u0011Q\u0007Y\u0005\u0003C>\nq\u0001]1dW\u0006<W-\u0003\u0002dI\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011mL\u0001\u0006GR\u0014\u0018.Z\u000b\u0002+\u000611\r\u001e:jK\u0002\na\u0001P5oSRtDC\u0001+k\u0011\u0015)7\u00011\u0001V)\u0005!\u0016\u0001D7ba\u000e{W\u000e]1oS>tW#\u00018\u0011\u0007-{\u0007+\u0003\u0002q\u0019\n1r)\u001a8fe&\u001c\u0007+\u0019:NCB\u001cu.\u001c9b]&|g.A\u0003f[B$\u00180F\u0001U\u0003-qWm^\"p[\nLg.\u001a:\u0016\u0003U\u0004BA^<z)6\t1&\u0003\u0002yW\tA1i\\7cS:,'\u000f\u0005\u00036ur:\u0015BA>0\u0005\u0019!V\u000f\u001d7fe\u0005\u00191/Z9\u0002\u0011M\u0004H.\u001b;uKJ,\u0012a \t\u0006s\u0005\u0005AhR\u0005\u0004\u0003\u0007I#A\u0005)beR\u0013\u0018.Z'baN\u0003H.\u001b;uKJ\fQa\u00197fCJ$\"!!\u0003\u0011\u0007U\nY!C\u0002\u0002\u000e=\u0012A!\u00168ji\u00061!/Z:vYR\f1aZ3u)\u0011\t)\"a\u0007\u0011\tU\n9bR\u0005\u0004\u00033y#AB(qi&|g\u000e\u0003\u0004\u0002\u001e1\u0001\r\u0001P\u0001\u0004W\u0016L\u0018a\u00019viR1\u0011QCA\u0012\u0003KAa!!\b\u000e\u0001\u0004a\u0004BBA\u0014\u001b\u0001\u0007q)A\u0003wC2,X-\u0001\u0004va\u0012\fG/\u001a\u000b\u0007\u0003\u0013\ti#a\f\t\r\u0005ua\u00021\u0001=\u0011\u0019\t9C\u0004a\u0001\u000f\u00061!/Z7pm\u0016$B!!\u0006\u00026!1\u0011QD\bA\u0002q\na!\u00193e\u001f:,G\u0003BA\u001e\u0003{i\u0011\u0001\u0001\u0005\u0007\u0003\u007f\u0001\u0002\u0019A=\u0002\u0005-4\u0018aC:vER\u0014\u0018m\u0019;P]\u0016$B!a\u000f\u0002F!1\u0011QD\tA\u0002q\nAa]5{KV\u0011\u00111\n\t\u0004k\u00055\u0013bAA(_\t\u0019\u0011J\u001c;\u0002\u0013-twn\u001e8TSj,\u0017\u0001D:ue&tw\r\u0015:fM&DXCAA,!\u0011\tI&a\u0019\u000e\u0005\u0005m#\u0002BA/\u0003?\nA\u0001\\1oO*\u0011\u0011\u0011M\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002f\u0005m#AB*ue&twM\u0001\u0003TSj,7\u0003B\u000b5\u0003W\u0002rA^A7\u0003\u0017\n\t(C\u0002\u0002p-\u0012A\u0001V1tWB\u0019\u00111H\u000b\u0002\r=4gm]3u\u0003\u001dAwn^7b]f\fQ!\u0019:sCf\u0004R!NA>\u0003\u007fJ1!! 0\u0005\u0015\t%O]1z!\r1\u0016\u0011Q\u0005\u0004\u0003\u0007;&!\u0003\"bg&\u001cgj\u001c3f)!\t\t(a\"\u0002\n\u0006-\u0005bBA:3\u0001\u0007\u00111\n\u0005\b\u0003kJ\u0002\u0019AA&\u0011\u001d\t9(\u0007a\u0001\u0003s\n!B]3tk2$x\fJ3r)\u0011\tI!!%\t\u0013\u0005M5$!AA\u0002\u0005-\u0013a\u0001=%c\u00059!/Z:vYR\u0004\u0013\u0001\u00027fC\u001a$B!!\u0003\u0002\u001c\"9\u0011QT\u000fA\u0002\u0005}\u0015\u0001\u00029sKZ\u0004R!NA\f\u0003\u0017\nQa\u001d9mSR,\"!!*\u0011\r\u0005\u001d\u0016QVA9\u001b\t\tIKC\u0002\u0002,6\n\u0011\"[7nkR\f'\r\\3\n\t\u0005=\u0016\u0011\u0016\u0002\u0004'\u0016\f\u0018AE:i_VdGm\u00159mSR4UO\u001d;iKJ,\"!!.\u0011\u0007U\n9,C\u0002\u0002:>\u0012qAQ8pY\u0016\fg.A\u0003nKJ<W\r\u0006\u0003\u0002\n\u0005}\u0006bBAaA\u0001\u0007\u0011\u0011O\u0001\u0005i\"\fG/\u0001\u0006QCJ$&/[3NCB\u0004\"!\u000f\u0012\u0014\u000b\t\nI-!5\u0011\r-\u000bY\rUAh\u0013\r\ti\r\u0014\u0002\u000e!\u0006\u0014X*\u00199GC\u000e$xN]=\u0011\u0005YK\u0006\u0003BAj\u00033l!!!6\u000b\t\u0005]\u0017qL\u0001\u0003S>L1aYAk)\t\t)-\u0006\u0004\u0002`\u0006\u0015\u0018\u0011^\u000b\u0003\u0003C\u0004b!\u000f\u0001\u0002d\u0006\u001d\bcA\u001f\u0002f\u0012)q\b\nb\u0001\u0001B\u0019Q(!;\u0005\u000b%##\u0019\u0001!\u0016\r\u00055\u0018Q_A}+\t\ty\u000f\u0005\u0004wo\u0006E\u00181 \t\u0007ki\f\u00190a>\u0011\u0007u\n)\u0010B\u0003@K\t\u0007\u0001\tE\u0002>\u0003s$Q!S\u0013C\u0002\u0001\u0003b!\u000f\u0001\u0002t\u0006]\u0018\u0001D2b]\n+\u0018\u000e\u001c3Ge>lWC\u0003B\u0001\u0005\u001b\u0011\u0019Ba\u0007\u0003 U\u0011!1\u0001\t\n\u0017\n\u0015!\u0011\u0002B\f\u0005CI1Aa\u0002M\u00059\u0019\u0015M\\\"p[\nLg.\u001a$s_6\u0004b!\u000f\u0001\u0003\f\tE\u0001cA\u001f\u0003\u000e\u00111!q\u0002\u0014C\u0002\u0001\u0013QA\u0012:p[.\u00032!\u0010B\n\t\u0019\u0011)B\nb\u0001\u0001\n)aI]8n-B1QG\u001fB\r\u0005;\u00012!\u0010B\u000e\t\u0015ydE1\u0001A!\ri$q\u0004\u0003\u0006\u0013\u001a\u0012\r\u0001\u0011\t\u0007s\u0001\u0011IB!\b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t\u001d\u0002\u0003BA-\u0005SIAAa\u000b\u0002\\\t1qJ\u00196fGR\u0004"
)
public final class ParTrieMap implements ParMap, ParTrieMapCombiner, Serializable {
   private final TrieMap scala$collection$parallel$mutable$ParTrieMap$$ctrie;
   private transient volatile TaskSupport _combinerTaskSupport;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public static CanCombineFrom canBuildFrom() {
      return ParTrieMap$.MODULE$.canBuildFrom();
   }

   public static Factory toFactory() {
      return ParTrieMap$.MODULE$.toFactory();
   }

   public Combiner combine(final Combiner other) {
      return ParTrieMapCombiner.combine$(this, other);
   }

   public boolean canBeShared() {
      return ParTrieMapCombiner.canBeShared$(this);
   }

   public TaskSupport combinerTaskSupport() {
      return Combiner.combinerTaskSupport$(this);
   }

   public void combinerTaskSupport_$eq(final TaskSupport cts) {
      Combiner.combinerTaskSupport_$eq$(this, cts);
   }

   public Object resultWithTaskSupport() {
      return Combiner.resultWithTaskSupport$(this);
   }

   public Object fromSequential(final IterableOnce seq) {
      return Combiner.fromSequential$(this, seq);
   }

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public ParMap withDefault(final Function1 d) {
      return ParMap.withDefault$(this, d);
   }

   public ParMap withDefaultValue(final Object d) {
      return ParMap.withDefaultValue$(this, d);
   }

   public ParMap $plus(final Tuple2 kv) {
      return ParMapLike.$plus$(this, kv);
   }

   public ParMap $minus(final Object key) {
      return ParMapLike.$minus$(this, key);
   }

   public ParMap clone() {
      return ParMapLike.clone$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
   }

   public final Shrinkable $minus$eq(final Object elem) {
      return Shrinkable.$minus$eq$(this, elem);
   }

   /** @deprecated */
   public Shrinkable $minus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Shrinkable.$minus$eq$(this, elem1, elem2, elems);
   }

   public Shrinkable subtractAll(final IterableOnce xs) {
      return Shrinkable.subtractAll$(this, xs);
   }

   public final Shrinkable $minus$minus$eq(final IterableOnce xs) {
      return Shrinkable.$minus$minus$eq$(this, xs);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public GenericParCompanion companion() {
      return ParIterable.companion$(this);
   }

   public ParIterable toIterable() {
      return ParIterable.toIterable$(this);
   }

   public ParSeq toSeq() {
      return ParIterable.toSeq$(this);
   }

   public boolean canEqual(final Object that) {
      return scala.collection.parallel.ParMapLike.canEqual$(this, that);
   }

   public boolean equals(final Object that) {
      return scala.collection.parallel.ParMapLike.equals$(this, that);
   }

   public int hashCode() {
      return scala.collection.parallel.ParMapLike.hashCode$(this);
   }

   public scala.collection.parallel.ParMap updated(final Object key, final Object value) {
      return scala.collection.parallel.ParMapLike.updated$(this, key, value);
   }

   public Object default(final Object key) {
      return scala.collection.parallel.ParMapLike.default$(this, key);
   }

   public Object apply(final Object key) {
      return scala.collection.parallel.ParMapLike.apply$(this, key);
   }

   public Object getOrElse(final Object key, final Function0 default) {
      return scala.collection.parallel.ParMapLike.getOrElse$(this, key, default);
   }

   public boolean contains(final Object key) {
      return scala.collection.parallel.ParMapLike.contains$(this, key);
   }

   public boolean isDefinedAt(final Object key) {
      return scala.collection.parallel.ParMapLike.isDefinedAt$(this, key);
   }

   public IterableSplitter keysIterator() {
      return scala.collection.parallel.ParMapLike.keysIterator$(this);
   }

   public IterableSplitter valuesIterator() {
      return scala.collection.parallel.ParMapLike.valuesIterator$(this);
   }

   public scala.collection.parallel.ParSet keySet() {
      return scala.collection.parallel.ParMapLike.keySet$(this);
   }

   public scala.collection.parallel.ParIterable keys() {
      return scala.collection.parallel.ParMapLike.keys$(this);
   }

   public scala.collection.parallel.ParIterable values() {
      return scala.collection.parallel.ParMapLike.values$(this);
   }

   public scala.collection.parallel.ParMap filterKeys(final Function1 p) {
      return scala.collection.parallel.ParMapLike.filterKeys$(this, p);
   }

   public scala.collection.parallel.ParMap mapValues(final Function1 f) {
      return scala.collection.parallel.ParMapLike.mapValues$(this, f);
   }

   public scala.collection.parallel.ParMap map(final Function1 f) {
      return scala.collection.parallel.ParMapLike.map$(this, f);
   }

   public scala.collection.parallel.ParMap collect(final PartialFunction pf) {
      return scala.collection.parallel.ParMapLike.collect$(this, pf);
   }

   public scala.collection.parallel.ParMap flatMap(final Function1 f) {
      return scala.collection.parallel.ParMapLike.flatMap$(this, f);
   }

   public scala.collection.parallel.ParMap concat(final IterableOnce that) {
      return scala.collection.parallel.ParMapLike.concat$(this, that);
   }

   public final scala.collection.parallel.ParMap $plus$plus(final IterableOnce xs) {
      return scala.collection.parallel.ParMapLike.$plus$plus$(this, xs);
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

   public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
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

   public scala.collection.parallel.ParIterable flatten(final Function1 asTraversable) {
      return GenericTraversableTemplate.flatten$(this, asTraversable);
   }

   public scala.collection.parallel.ParIterable transpose(final Function1 asTraversable) {
      return GenericTraversableTemplate.transpose$(this, asTraversable);
   }

   public TaskSupport _combinerTaskSupport() {
      return this._combinerTaskSupport;
   }

   public void _combinerTaskSupport_$eq(final TaskSupport x$1) {
      this._combinerTaskSupport = x$1;
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

   public TrieMap scala$collection$parallel$mutable$ParTrieMap$$ctrie() {
      return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie;
   }

   public GenericParMapCompanion mapCompanion() {
      return ParTrieMap$.MODULE$;
   }

   public ParTrieMap empty() {
      return ParTrieMap$.MODULE$.empty();
   }

   public Combiner newCombiner() {
      return ParTrieMap$.MODULE$.newCombiner();
   }

   public TrieMap seq() {
      return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie();
   }

   public ParTrieMapSplitter splitter() {
      return new ParTrieMapSplitter(0, (TrieMap)this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().readOnlySnapshot(), true);
   }

   public void clear() {
      this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().clear();
   }

   public ParTrieMap result() {
      return this;
   }

   public Option get(final Object key) {
      return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().get(key);
   }

   public Option put(final Object key, final Object value) {
      return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().put(key, value);
   }

   public void update(final Object key, final Object value) {
      this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().update(key, value);
   }

   public Option remove(final Object key) {
      return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().remove(key);
   }

   public ParTrieMap addOne(final Tuple2 kv) {
      this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().$plus$eq(kv);
      return this;
   }

   public ParTrieMap subtractOne(final Object key) {
      this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().$minus$eq(key);
      return this;
   }

   public int size() {
      INode in = this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().readRoot(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie().readRoot$default$1());
      MainNode r = in.gcasRead(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
      if (r instanceof TNode) {
         TNode var5 = (TNode)r;
         return var5.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
      } else if (r instanceof LNode) {
         LNode var6 = (LNode)r;
         return var6.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
      } else if (r instanceof CNode) {
         CNode var7 = (CNode)r;
         this.tasksupport().executeAndWaitResult(new Size(0, var7.array().length, var7.array()));
         return var7.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$$ctrie());
      } else {
         throw new MatchError(r);
      }
   }

   public int knownSize() {
      return -1;
   }

   public String stringPrefix() {
      return "ParTrieMap";
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

   public ParTrieMap(final TrieMap ctrie) {
      this.scala$collection$parallel$mutable$ParTrieMap$$ctrie = ctrie;
      GenericTraversableTemplate.$init$(this);
      GenericParTemplate.$init$(this);
      GenericParMapTemplate.$init$(this);
      IterableOnce.$init$(this);
      Parallelizable.$init$(this);
      CustomParallelizable.$init$(this);
      ParIterableLike.$init$(this);
      scala.collection.parallel.ParIterable.$init$(this);
      scala.collection.parallel.ParMapLike.$init$(this);
      scala.collection.parallel.ParMap.$init$(this);
      ParIterable.$init$(this);
      Growable.$init$(this);
      Shrinkable.$init$(this);
      Cloneable.$init$(this);
      ParMapLike.$init$(this);
      ParMap.$init$(this);
      Builder.$init$(this);
      Combiner.$init$(this);
      ParTrieMapCombiner.$init$(this);
   }

   public ParTrieMap() {
      this(new TrieMap());
   }

   public class Size implements Task {
      private final int offset;
      private final int howmany;
      private final BasicNode[] array;
      private int result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParTrieMap $outer;

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public void signalAbort() {
         Task.signalAbort$(this);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public int result() {
         return this.result;
      }

      public void result_$eq(final int x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         int sz = 0;
         int i = this.offset;

         for(int until = this.offset + this.howmany; i < until; ++i) {
            BasicNode var6 = this.array[i];
            if (var6 instanceof SNode) {
               ++sz;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               if (!(var6 instanceof INode)) {
                  throw new MatchError(var6);
               }

               INode var7 = (INode)var6;
               sz += var7.cachedSize(this.scala$collection$parallel$mutable$ParTrieMap$Size$$$outer().scala$collection$parallel$mutable$ParTrieMap$$ctrie());
               BoxedUnit var8 = BoxedUnit.UNIT;
            }
         }

         this.result_$eq(sz);
      }

      public Seq split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParTrieMap$Size$$$outer().new Size(this.offset, fp, this.array), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParTrieMap$Size$$$outer().new Size(this.offset + fp, this.howmany - fp, this.array), scala.collection.immutable.Nil..MODULE$));
      }

      public boolean shouldSplitFurther() {
         return this.howmany > 1;
      }

      public void merge(final Size that) {
         this.result_$eq(this.result() + that.result());
      }

      // $FF: synthetic method
      public ParTrieMap scala$collection$parallel$mutable$ParTrieMap$Size$$$outer() {
         return this.$outer;
      }

      public Size(final int offset, final int howmany, final BasicNode[] array) {
         this.offset = offset;
         this.howmany = howmany;
         this.array = array;
         if (ParTrieMap.this == null) {
            throw null;
         } else {
            this.$outer = ParTrieMap.this;
            super();
            Task.$init$(this);
            this.result = -1;
         }
      }
   }
}
