package breeze.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BuildFrom;
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
import scala.collection.StrictOptimizedIterableOps;
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

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra\u0001\u0002\u0011\"\u0001!B\u0001\"\u0013\u0001\u0003\u0006\u0004%\tA\u0013\u0005\t\u001d\u0002\u0011\t\u0011)A\u0005\u0017\"Aq\n\u0001B\u0001B\u0003%\u0001\u000b\u0003\u0005T\u0001\t\u0005\t\u0015a\u0003U\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u001d1\u0007A1A\u0005\u0002\u001dDaa\u001b\u0001!\u0002\u0013A\u0007\"\u00027\u0001\t\u0003R\u0005\"\u00021\u0001\t\u0003i\u0007\"B9\u0001\t\u0013\u0011\b\"B;\u0001\t\u00131\b\"B>\u0001\t\u0003a\b\"\u0002@\u0001\t\u0003y\bbBA\u0004\u0001\u0011\u0005\u0013\u0011\u0002\u0005\b\u00037\u0001A\u0011IA\u000f\u0011\u001d\tI\u0003\u0001C\u0001\u0003WAq!!\f\u0001\t\u0003\ty\u0003C\u0004\u00028\u0001!\t!!\u000f\t\u000f\u0005]\u0003\u0001\"\u0001\u0002Z!9\u00111\u000f\u0001\u0005\u0002\u0005U\u0004bBAH\u0001\u0011\u0005\u0011\u0011\u0013\u0005\b\u00033\u0003A\u0011AAN\u0011\u001d\ty\n\u0001C\t\u0003CCq!a,\u0001\t#\n\t\fC\u0004\u00028\u0002!\t%!)\t\u000f\u0005e\u0006\u0001\"\u0011\u0002<\u001e9\u0011QX\u0011\t\u0002\u0005}fA\u0002\u0011\"\u0011\u0003\t\t\r\u0003\u0004a9\u0011\u0005\u00111\u0019\u0005\b\u0003\u000bdB\u0011AAd\u0011\u001d\t\u0019\u000f\bC\u0002\u0003K\u0014AAQ3b[*\u0011!eI\u0001\nS6lW\u000f^1cY\u0016T!\u0001J\u0013\u0002\u0015\r|G\u000e\\3di&|gNC\u0001'\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\u00158'\u0015\u0001!\u0006\r!G!\tYc&D\u0001-\u0015\u0005i\u0013!B:dC2\f\u0017BA\u0018-\u0005\u0019\te.\u001f*fMB\u0019\u0011gM\u001b\u000e\u0003IR!\u0001\n\u0017\n\u0005Q\u0012$\u0001C%uKJ\f'\r\\3\u0011\u0005Y:D\u0002\u0001\u0003\u0006q\u0001\u0011\r!\u000f\u0002\u0002)F\u0011!(\u0010\t\u0003WmJ!\u0001\u0010\u0017\u0003\u000f9{G\u000f[5oOB\u00111FP\u0005\u0003\u007f1\u00121!\u00118z!\u0015\t\u0014)N\"E\u0013\t\u0011%GA\u0006Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\bCA\u00194!\r)\u0005!N\u0007\u0002CA)\u0011gR\u001bD\t&\u0011\u0001J\r\u0002\u001b'R\u0014\u0018n\u0019;PaRLW.\u001b>fI&#XM]1cY\u0016|\u0005o]\u0001\b[\u0006D8+\u001b>f+\u0005Y\u0005CA\u0016M\u0013\tiEFA\u0002J]R\f\u0001\"\\1y'&TX\rI\u0001\u0003qN\u00042aK)6\u0013\t\u0011FF\u0001\u0006=e\u0016\u0004X-\u0019;fIz\n\u0011a\u001c\t\u0004+v+dB\u0001,\\\u001d\t9&,D\u0001Y\u0015\tIv%\u0001\u0004=e>|GOP\u0005\u0002[%\u0011A\fL\u0001\ba\u0006\u001c7.Y4f\u0013\tqvL\u0001\u0005Pe\u0012,'/\u001b8h\u0015\taF&\u0001\u0004=S:LGO\u0010\u000b\u0004E\u0012,GC\u0001#d\u0011\u0015\u0019V\u0001q\u0001U\u0011\u0015IU\u00011\u0001L\u0011\u0015yU\u00011\u0001Q\u0003\u0011AW-\u00199\u0016\u0003!\u00042!R56\u0013\tQ\u0017E\u0001\u0007CS:|W.[1m\u0011\u0016\f\u0007/A\u0003iK\u0006\u0004\b%\u0001\u0003tSj,GC\u00018q)\t!u\u000eC\u0003T\u0013\u0001\u000fA\u000bC\u0003J\u0013\u0001\u00071*\u0001\u0003ue&lGC\u00015t\u0011\u0015!(\u00021\u0001i\u0003\tA''A\u0002dCR$2\u0001[<z\u0011\u0015A8\u00021\u0001i\u0003\u0005A\u0007\"\u0002>\f\u0001\u0004)\u0014!\u0001=\u0002\u000b\u0011\u0002H.^:\u0015\u0005\u0011k\b\"\u0002>\r\u0001\u0004)\u0014\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005\u0005\u0001\u0003B\u0019\u0002\u0004UJ1!!\u00023\u0005!IE/\u001a:bi>\u0014\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005-\u0001\u0003BA\u0007\u0003+qA!a\u0004\u0002\u0012A\u0011q\u000bL\u0005\u0004\u0003'a\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0018\u0005e!AB*ue&twMC\u0002\u0002\u00141\na!Z9vC2\u001cH\u0003BA\u0010\u0003K\u00012aKA\u0011\u0013\r\t\u0019\u0003\f\u0002\b\u0005>|G.Z1o\u0011\u0019\t9c\u0004a\u0001{\u0005)q\u000e\u001e5fe\u0006\u0019Q.\u001b8\u0016\u0003U\nAAY3tiV\u0011\u0011\u0011\u0007\t\u0005W\u0005MR'C\u0002\u000261\u0012aa\u00149uS>t\u0017aA7baV!\u00111HA\")\u0011\ti$!\u0014\u0015\t\u0005}\u0012q\t\t\u0005\u000b\u0002\t\t\u0005E\u00027\u0003\u0007\"a!!\u0012\u0013\u0005\u0004I$!\u0001\"\t\u000f\u0005%#\u0003q\u0001\u0002L\u0005\u0011QM\u001e\t\u0005+v\u000b\t\u0005C\u0004\u0002PI\u0001\r!!\u0015\u0002\u0003\u0019\u0004baKA*k\u0005\u0005\u0013bAA+Y\tIa)\u001e8di&|g.M\u0001\bM2\fG/T1q+\u0011\tY&a\u0019\u0015\t\u0005u\u0013\u0011\u000e\u000b\u0005\u0003?\n)\u0007\u0005\u0003F\u0001\u0005\u0005\u0004c\u0001\u001c\u0002d\u00111\u0011QI\nC\u0002eBq!!\u0013\u0014\u0001\b\t9\u0007\u0005\u0003V;\u0006\u0005\u0004bBA('\u0001\u0007\u00111\u000e\t\u0007W\u0005MS'!\u001c\u0011\u000bE\ny'!\u0019\n\u0007\u0005E$G\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-A\u0004d_2dWm\u0019;\u0016\t\u0005]\u0014q\u0010\u000b\u0005\u0003s\n)\t\u0006\u0003\u0002|\u0005\u0005\u0005\u0003B#\u0001\u0003{\u00022ANA@\t\u0019\t)\u0005\u0006b\u0001s!9\u0011\u0011\n\u000bA\u0004\u0005\r\u0005\u0003B+^\u0003{Bq!a\"\u0015\u0001\u0004\tI)\u0001\u0002qMB11&a#6\u0003{J1!!$-\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0017AB2p]\u000e\fG\u000fF\u0002E\u0003'Cq!!&\u0016\u0001\u0004\t9*\u0001\u0003uQ\u0006$\b\u0003B\u0019\u0002pU\n!\u0002\n9mkN$\u0003\u000f\\;t)\r!\u0015Q\u0014\u0005\b\u0003+3\u0002\u0019AAL\u0003)qWm\u001e\"vS2$WM]\u000b\u0003\u0003G\u0003b!!*\u0002,V\"UBAAT\u0015\r\tIKM\u0001\b[V$\u0018M\u00197f\u0013\u0011\ti+a*\u0003\u000f\t+\u0018\u000e\u001c3fe\u0006aaM]8n'B,7-\u001b4jGR\u0019A)a-\t\u000f\u0005U\u0006\u00041\u0001\u0002\u0018\u0006!1m\u001c7m\u0003IqWm^*qK\u000eLg-[2Ck&dG-\u001a:\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003\u0011\u000bAAQ3b[B\u0011Q\tH\n\u00039)\"\"!a0\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005%\u00171\u001b\u000b\u0005\u0003\u0017\f\t\u000f\u0006\u0003\u0002N\u0006mG\u0003BAh\u0003+\u0004B!\u0012\u0001\u0002RB\u0019a'a5\u0005\u000bar\"\u0019A\u001d\t\u0013\u0005]g$!AA\u0004\u0005e\u0017AC3wS\u0012,gnY3%cA!Q+XAi\u0011\u001d\tiN\ba\u0001\u0003?\fQ!\u001b;f[N\u0004BaK)\u0002R\")\u0011J\ba\u0001\u0017\u0006a1-\u00198Ck&dGM\u0012:p[V1\u0011q]Az\u0003o$B!!;\u0002~BI\u0011'a;\u0002p\u0006U\u00181`\u0005\u0004\u0003[\u0014$!\u0003\"vS2$gI]8n!\u0011)\u0005!!=\u0011\u0007Y\n\u0019\u0010B\u00039?\t\u0007\u0011\bE\u00027\u0003o$a!!? \u0005\u0004I$!A+\u0011\t\u0015\u0003\u0011Q\u001f\u0005\n\u0003\u007f|\u0012\u0011!a\u0002\u0005\u0003\t!\"\u001a<jI\u0016t7-\u001a\u00133!\u0011)V,!>"
)
public class Beam implements Iterable, StrictOptimizedIterableOps {
   private final int maxSize;
   public final Ordering breeze$collection$immutable$Beam$$o;
   private final BinomialHeap heap;

   public static BuildFrom canBuildFrom(final Ordering evidence$2) {
      return Beam$.MODULE$.canBuildFrom(evidence$2);
   }

   public static Beam apply(final int maxSize, final Seq items, final Ordering evidence$1) {
      return Beam$.MODULE$.apply(maxSize, items, evidence$1);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

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

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
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

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
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

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
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

   public Object maxBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxBy$(this, f, cmp);
   }

   public Option maxByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxByOption$(this, f, cmp);
   }

   public Object minBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minBy$(this, f, cmp);
   }

   public Option minByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minByOption$(this, f, cmp);
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

   public int maxSize() {
      return this.maxSize;
   }

   public BinomialHeap heap() {
      return this.heap;
   }

   public int size() {
      return this.heap().size();
   }

   private BinomialHeap trim(final BinomialHeap h2) {
      BinomialHeap h;
      for(h = h2; h.size() > this.maxSize(); h = h.delMin()) {
      }

      return h;
   }

   public BinomialHeap breeze$collection$immutable$Beam$$cat(final BinomialHeap h, final Object x) {
      return h.size() < this.maxSize() ? h.$plus(x) : (this.breeze$collection$immutable$Beam$$o.compare(h.min(), x) < 0 ? h.delMin().$plus(x) : h);
   }

   public Beam $plus(final Object x) {
      return new Beam(x) {
         private final BinomialHeap heap;

         public BinomialHeap heap() {
            return this.heap;
         }

         public {
            this.heap = Beam.this.breeze$collection$immutable$Beam$$cat(Beam.this.heap(), x$5);
         }
      };
   }

   public Iterator iterator() {
      return this.heap().iterator();
   }

   public String toString() {
      return this.iterator().mkString("Beam(", ",", ")");
   }

   public boolean equals(final Object other) {
      boolean var2;
      if (other instanceof Beam) {
         Beam var4 = (Beam)other;
         var2 = this.maxSize() == var4.maxSize() && this.iterator().sameElements(var4.iterator());
      } else {
         var2 = false;
      }

      return var2;
   }

   public Object min() {
      return this.heap().head();
   }

   public Option best() {
      return this.heap().reduceOption((x$1, x$2) -> this.breeze$collection$immutable$Beam$$o.max(x$1, x$2));
   }

   public Beam map(final Function1 f, final Ordering ev) {
      return (Beam)this.strictOptimizedMap(Beam$.MODULE$.canBuildFrom(ev).newBuilder(this), f);
   }

   public Beam flatMap(final Function1 f, final Ordering ev) {
      return (Beam)this.strictOptimizedFlatMap(Beam$.MODULE$.canBuildFrom(ev).newBuilder(this), f);
   }

   public Beam collect(final PartialFunction pf, final Ordering ev) {
      return (Beam)this.strictOptimizedCollect(Beam$.MODULE$.canBuildFrom(ev).newBuilder(this), pf);
   }

   public Beam concat(final IterableOnce that) {
      return (Beam)that.iterator().foldLeft(this, (x$3, x$4) -> x$3.$plus(x$4));
   }

   public Beam $plus$plus(final IterableOnce that) {
      return this.concat(that);
   }

   public Builder newBuilder() {
      return Beam$.MODULE$.canBuildFrom(this.breeze$collection$immutable$Beam$$o).newBuilder(this);
   }

   public Beam fromSpecific(final IterableOnce coll) {
      return (Beam)((Builder)this.newBuilder().$plus$plus$eq(coll)).result();
   }

   public Builder newSpecificBuilder() {
      return Beam$.MODULE$.canBuildFrom(this.breeze$collection$immutable$Beam$$o).newBuilder(this);
   }

   public Beam empty() {
      return Beam$.MODULE$.apply(this.maxSize(), scala.collection.immutable.Nil..MODULE$, this.breeze$collection$immutable$Beam$$o);
   }

   public Beam(final int maxSize, final Seq xs, final Ordering o) {
      this.maxSize = maxSize;
      this.breeze$collection$immutable$Beam$$o = o;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      StrictOptimizedIterableOps.$init$(this);
      scala.Predef..MODULE$.assert(maxSize >= 0);
      this.heap = this.trim(BinomialHeap$.MODULE$.apply(xs, o));
   }

   public Beam(final int maxSize, final Ordering o) {
      this(maxSize, scala.package..MODULE$.Nil(), o);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
