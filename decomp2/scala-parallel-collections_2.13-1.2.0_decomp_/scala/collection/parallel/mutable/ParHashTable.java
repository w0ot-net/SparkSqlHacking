package scala.collection.parallel.mutable;

import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.DebugUtils$;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.BufferSplitter;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.SeqSplitter;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\tUaa\u0002\u001a4!\u0003\r\t\u0001\u0010\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006Q\u0002!\t%\u001b\u0004\u0006[\u0002\t\tA\u001c\u0005\tu\u000e\u0011\t\u0019!C\u0005w\"Iqp\u0001BA\u0002\u0013%\u0011\u0011\u0001\u0005\n\u0003\u000f\u0019!\u0011!Q!\nqD\u0011\"!\u0003\u0004\u0005\u000b\u0007I\u0011B>\t\u0013\u0005-1A!A!\u0002\u0013a\b\"CA\u0007\u0007\t\u0015\r\u0011\"\u0003|\u0011%\tya\u0001B\u0001B\u0003%A\u0010\u0003\u0006\u0002\u0012\r\u0011\t\u0019!C\u0005\u0003'A!\"!\u0006\u0004\u0005\u0003\u0007I\u0011BA\f\u0011%\tYb\u0001B\u0001B\u0003&Q\u000bC\u0004\u0002\u001e\r!\t!a\b\t\u0013\u0005U2A1A\u0005\n\u0005]\u0002\u0002CA \u0007\u0001\u0006I!!\u000f\t\u0011\u0005\u00053\u00011A\u0005\nmD\u0011\"a\u0011\u0004\u0001\u0004%I!!\u0012\t\u000f\u0005%3\u0001)Q\u0005y\"9\u00111J\u0002\u0007\u0002\u00055\u0003bBA*\u0007\u0019\u0005\u0011Q\u000b\u0005\u0007\u0003K\u001aA\u0011A5\t\u000f\u0005\u001d4\u0001\"\u0001\u0002j!1\u00111N\u0002\u0005\u0002\u0011Da!!\u001c\u0004\t\u0003Y\b\u0002CA8\u0007\u0011\u0005S'!\u001d\t\u000f\u0005%5\u0001\"\u0001\u0002\f\"9\u0011QR\u0002\u0005\u0002\u0005=\u0005bBAR\u0007\u0011%\u0011Q\u0015\u0005\b\u0003c\u001bA\u0011CAZ\u0011\u001d\tYl\u0001C\t\u0003{;\u0001\"a24\u0011\u00039\u0014\u0011\u001a\u0004\beMB\taNAf\u0011\u001d\ti\"\tC\u0001\u0003\u001b4a!a4\"\u0001\u0005E\u0007\"CAkG\t\u0015\r\u0011\"\u0001|\u0011%\t9n\tB\u0001B\u0003%A\u0010\u0003\u0006\u0002Z\u000e\u0012)\u0019!C\u0001\u00037D!\"!<$\u0005\u0003\u0005\u000b\u0011BAo\u0011%\tyo\tBC\u0002\u0013\u00051\u0010C\u0005\u0002r\u000e\u0012\t\u0011)A\u0005y\"I\u00111_\u0012\u0003\u0006\u0004%\ta\u001f\u0005\n\u0003k\u001c#\u0011!Q\u0001\nqD\u0011\"a>$\u0005\u000b\u0007I\u0011A>\t\u0013\u0005e8E!A!\u0002\u0013a\bBCA~G\t\u0015\r\u0011\"\u0001\u0002~\"Q!\u0011A\u0012\u0003\u0002\u0003\u0006I!a@\t\u000f\u0005u1\u0005\"\u0001\u0003\u0004!A\u0011qN\u0012\u0005\u0002]\n\tH\u0001\u0007QCJD\u0015m\u001d5UC\ndWM\u0003\u00025k\u00059Q.\u001e;bE2,'B\u0001\u001c8\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\u001d:\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002u\u0005)1oY1mC\u000e\u0001Q\u0003B\u001fJ'Z\u001bB\u0001\u0001 C?B\u0011q\bQ\u0007\u0002s%\u0011\u0011)\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\u000b\r+uIU+\u000e\u0003\u0011S!\u0001N\u001c\n\u0005\u0019#%!\u0003%bg\"$\u0016M\u00197f!\tA\u0015\n\u0004\u0001\u0005\u000b)\u0003!\u0019A&\u0003\u0003-\u000b\"\u0001T(\u0011\u0005}j\u0015B\u0001(:\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0010)\n\u0005EK$aA!osB\u0011\u0001j\u0015\u0003\u0006)\u0002\u0011\ra\u0013\u0002\u0002-B\u0011\u0001J\u0016\u0003\u0006/\u0002\u0011\r\u0001\u0017\u0002\u0006\u000b:$(/_\t\u00033r\u0003\"a\u0010.\n\u0005mK$\u0001\u0002(vY2\u0004BaQ/H+&\u0011a\f\u0012\u0002\n\u0011\u0006\u001c\b.\u00128uef\u0004R\u0001Y1H%Vk\u0011aM\u0005\u0003EN\u0012AbV5uQ\u000e{g\u000e^3oiN\fa\u0001J5oSR$C#A3\u0011\u0005}2\u0017BA4:\u0005\u0011)f.\u001b;\u0002#\u0005dw/Y=t\u0013:LGoU5{K6\u000b\u0007/F\u0001k!\ty4.\u0003\u0002ms\t9!i\\8mK\u0006t'!D#oiJL\u0018\n^3sCR|'/\u0006\u0003pk\u0006\u001d2\u0003B\u0002?a^\u00042!\u001d:u\u001b\u0005)\u0014BA:6\u0005AIE/\u001a:bE2,7\u000b\u001d7jiR,'\u000f\u0005\u0002Ik\u0012)ao\u0001b\u0001\u0017\n\tA\u000b\u0005\u0002aq&\u0011\u0011p\r\u0002\r'&TX-T1q+RLGn]\u0001\u0004S\u0012DX#\u0001?\u0011\u0005}j\u0018B\u0001@:\u0005\rIe\u000e^\u0001\bS\u0012Dx\fJ3r)\r)\u00171\u0001\u0005\t\u0003\u000b)\u0011\u0011!a\u0001y\u0006\u0019\u0001\u0010J\u0019\u0002\t%$\u0007\u0010I\u0001\u0006k:$\u0018\u000e\\\u0001\u0007k:$\u0018\u000e\u001c\u0011\u0002\u0013Q|G/\u00197tSj,\u0017A\u0003;pi\u0006d7/\u001b>fA\u0005\u0011Qm]\u000b\u0002+\u00061Qm]0%KF$2!ZA\r\u0011!\t)\u0001DA\u0001\u0002\u0004)\u0016aA3tA\u00051A(\u001b8jiz\"\"\"!\t\u0002.\u0005=\u0012\u0011GA\u001a!\u0019\t\u0019c\u0001;\u0002&5\t\u0001\u0001E\u0002I\u0003O!\u0001\"!\u000b\u0004\t\u000b\u0007\u00111\u0006\u0002\t\u0013R,'OU3qeF\u0011A\n\u001d\u0005\u0006u:\u0001\r\u0001 \u0005\u0007\u0003\u0013q\u0001\u0019\u0001?\t\r\u00055a\u00021\u0001}\u0011\u0019\t\tB\u0004a\u0001+\u0006I\u0011\u000e^3si\u0006\u0014G.Z\u000b\u0003\u0003s\u0001BaPA\u001e9&\u0019\u0011QH\u001d\u0003\u000b\u0005\u0013(/Y=\u0002\u0015%$XM\u001d;bE2,\u0007%A\u0005ue\u00064XM]:fI\u0006iAO]1wKJ\u001cX\rZ0%KF$2!ZA$\u0011!\t)AEA\u0001\u0002\u0004a\u0018A\u0003;sCZ,'o]3eA\u0005QQM\u001c;ssJJG/Z7\u0015\u0007Q\fy\u0005\u0003\u0004\u0002RQ\u0001\r!V\u0001\u0002K\u0006Ya.Z<Ji\u0016\u0014\u0018\r^8s))\t)#a\u0016\u0002\\\u0005}\u00131\r\u0005\u0007\u00033*\u0002\u0019\u0001?\u0002\u000f%$\u0007P\u0012:p[\"1\u0011QL\u000bA\u0002q\f\u0001\"\u001b3y+:$\u0018\u000e\u001c\u0005\u0007\u0003C*\u0002\u0019\u0001?\u0002\u0013Q|G/\u00197TSj,\u0007BBA\t+\u0001\u0007Q+A\u0004iCNtU\r\u001f;\u0002\t9,\u0007\u0010\u001e\u000b\u0002i\u0006!1oY1o\u0003%\u0011X-\\1j]&tw-\u0001\teK\n,x-\u00138g_Jl\u0017\r^5p]V\u0011\u00111\u000f\t\u0005\u0003k\n\u0019I\u0004\u0003\u0002x\u0005}\u0004cAA=s5\u0011\u00111\u0010\u0006\u0004\u0003{Z\u0014A\u0002\u001fs_>$h(C\u0002\u0002\u0002f\na\u0001\u0015:fI\u00164\u0017\u0002BAC\u0003\u000f\u0013aa\u0015;sS:<'bAAAs\u0005\u0019A-\u001e9\u0016\u0005\u0005\u0015\u0012!B:qY&$XCAAI!\u0015\t\u0019*!(q\u001d\u0011\t)*!'\u000f\t\u0005e\u0014qS\u0005\u0002u%\u0019\u00111T\u001d\u0002\u000fA\f7m[1hK&!\u0011qTAQ\u0005\r\u0019V-\u001d\u0006\u0004\u00037K\u0014\u0001F2p]Z,'\u000f\u001e+p\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0006\u0003\u0002(\u00065\u0006\u0003B\"\u0002*RL1!a+E\u0005-\t%O]1z\u0005V4g-\u001a:\t\r\u0005=V\u00041\u0001V\u0003%\u0019\u0007.Y5oQ\u0016\fG-\u0001\u0006d_VtG/\u00127f[N$R\u0001`A[\u0003sCa!a.\u001f\u0001\u0004a\u0018\u0001\u00024s_6Da!!\u0003\u001f\u0001\u0004a\u0018\u0001E2pk:$()^2lKR\u001c\u0016N_3t)\u0015a\u0018qXAb\u0011\u0019\t\tm\ba\u0001y\u0006QaM]8n\u0005V\u001c7.\u001a;\t\r\u0005\u0015w\u00041\u0001}\u0003-)h\u000e^5m\u0005V\u001c7.\u001a;\u0002\u0019A\u000b'\u000fS1tQR\u000b'\r\\3\u0011\u0005\u0001\f3CA\u0011?)\t\tIM\u0001\u0005D_:$XM\u001c;t+\u0019\t\u0019.a9\u0002jN\u00111EP\u0001\u000bY>\fGMR1di>\u0014\u0018a\u00037pC\u00124\u0015m\u0019;pe\u0002\nQ\u0001^1cY\u0016,\"!!8\u0011\u000b}\nY$a8\u0011\r\rk\u0016\u0011]At!\rA\u00151\u001d\u0003\u0007\u0003K\u001c#\u0019A&\u0003\u0003\u0005\u00032\u0001SAu\t\u001996E1\u0001\u0002lF\u0019\u0011,a8\u0002\rQ\f'\r\\3!\u0003%!\u0018M\u00197f'&TX-\u0001\u0006uC\ndWmU5{K\u0002\n\u0011\u0002\u001e5sKNDw\u000e\u001c3\u0002\u0015QD'/Z:i_2$\u0007%A\u0005tK\u0016$g/\u00197vK\u0006Q1/Z3em\u0006dW/\u001a\u0011\u0002\u000fML'0Z7baV\u0011\u0011q \t\u0005\u007f\u0005mB0\u0001\u0005tSj,W.\u00199!)9\u0011)A!\u0003\u0003\f\t5!q\u0002B\t\u0005'\u0001rAa\u0002$\u0003C\f9/D\u0001\"\u0011\u0019\t)\u000e\ra\u0001y\"9\u0011\u0011\u001c\u0019A\u0002\u0005u\u0007BBAxa\u0001\u0007A\u0010\u0003\u0004\u0002tB\u0002\r\u0001 \u0005\u0007\u0003o\u0004\u0004\u0019\u0001?\t\u000f\u0005m\b\u00071\u0001\u0002\u0000\u0002"
)
public interface ParHashTable extends HashTable, WithContents {
   // $FF: synthetic method
   static boolean alwaysInitSizeMap$(final ParHashTable $this) {
      return $this.alwaysInitSizeMap();
   }

   default boolean alwaysInitSizeMap() {
      return true;
   }

   static void $init$(final ParHashTable $this) {
   }

   public abstract class EntryIterator implements IterableSplitter, SizeMapUtils {
      private int idx;
      private final int until;
      private final int totalsize;
      private HashEntry es;
      private final HashEntry[] itertable;
      private int traversed;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final ParHashTable $outer;

      public int calcNumElems(final int from, final int until, final int tableLength, final int sizeMapBucketSize) {
         return SizeMapUtils.calcNumElems$(this, from, until, tableLength, sizeMapBucketSize);
      }

      public Seq splitWithSignalling() {
         return IterableSplitter.splitWithSignalling$(this);
      }

      public boolean shouldSplitFurther(final scala.collection.parallel.ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.buildString$(this, closure);
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

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      private int idx() {
         return this.idx;
      }

      private void idx_$eq(final int x$1) {
         this.idx = x$1;
      }

      private int until() {
         return this.until;
      }

      private int totalsize() {
         return this.totalsize;
      }

      private HashEntry es() {
         return this.es;
      }

      private void es_$eq(final HashEntry x$1) {
         this.es = x$1;
      }

      private HashEntry[] itertable() {
         return this.itertable;
      }

      private int traversed() {
         return this.traversed;
      }

      private void traversed_$eq(final int x$1) {
         this.traversed = x$1;
      }

      public abstract Object entry2item(final HashEntry e);

      public abstract IterableSplitter newIterator(final int idxFrom, final int idxUntil, final int totalSize, final HashEntry es);

      public boolean hasNext() {
         return this.es() != null;
      }

      public Object next() {
         HashEntry res = this.es();
         this.es_$eq(this.es().next());
         this.scan();
         this.traversed_$eq(this.traversed() + 1);
         return this.entry2item(res);
      }

      public void scan() {
         while(this.es() == null && this.idx() < this.until()) {
            this.es_$eq(this.itertable()[this.idx()]);
            this.idx_$eq(this.idx() + 1);
         }

      }

      public int remaining() {
         return this.totalsize() - this.traversed();
      }

      public String debugInformation() {
         return this.buildString((append) -> {
            $anonfun$debugInformation$1(this, append);
            return BoxedUnit.UNIT;
         });
      }

      public IterableSplitter dup() {
         return this.newIterator(this.idx(), this.until(), this.totalsize(), this.es());
      }

      public Seq split() {
         if (this.remaining() > 1) {
            if (this.until() > this.idx()) {
               int divsz = (this.until() - this.idx()) / 2;
               int sidx = this.idx() + divsz + 1;
               int suntil = this.until();
               HashEntry ses = this.itertable()[sidx - 1];
               int stotal = this.calcNumElems(sidx - 1, suntil, this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().table().length, this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().sizeMapBucketSize());
               int fidx = this.idx();
               int funtil = this.idx() + divsz;
               HashEntry fes = this.es();
               int ftotal = this.totalsize() - stotal;
               return new scala.collection.immutable..colon.colon(this.newIterator(fidx, funtil, ftotal, fes), new scala.collection.immutable..colon.colon(this.newIterator(sidx, suntil, stotal, ses), scala.collection.immutable.Nil..MODULE$));
            } else {
               ArrayBuffer arr = this.convertToArrayBuffer(this.es());
               BufferSplitter arrpit = new BufferSplitter(arr, 0, arr.length(), this.signalDelegate());
               return arrpit.split();
            }
         } else {
            return new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$);
         }
      }

      private ArrayBuffer convertToArrayBuffer(final HashEntry chainhead) {
         ArrayBuffer buff = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);

         for(HashEntry curr = chainhead; curr != null; curr = curr.next()) {
            buff.$plus$eq(curr);
         }

         return (ArrayBuffer)buff.map((e) -> this.entry2item(e));
      }

      public int countElems(final int from, final int until) {
         int c = 0;
         int idx = from;

         for(HashEntry es = null; idx < until; ++idx) {
            for(HashEntry var6 = this.itertable()[idx]; var6 != null; var6 = var6.next()) {
               ++c;
            }
         }

         return c;
      }

      public int countBucketSizes(final int fromBucket, final int untilBucket) {
         int c = 0;

         for(int idx = fromBucket; idx < untilBucket; ++idx) {
            c += this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().sizemap()[idx];
         }

         return c;
      }

      // $FF: synthetic method
      public ParHashTable scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$debugInformation$1(final EntryIterator $this, final Function1 append) {
         append.apply("/--------------------\\");
         append.apply("Parallel hash table entry iterator");
         append.apply((new java.lang.StringBuilder(27)).append("total hash table elements: ").append($this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().tableSize()).toString());
         append.apply((new java.lang.StringBuilder(5)).append("pos: ").append($this.idx()).toString());
         append.apply((new java.lang.StringBuilder(7)).append("until: ").append($this.until()).toString());
         append.apply((new java.lang.StringBuilder(11)).append("traversed: ").append($this.traversed()).toString());
         append.apply((new java.lang.StringBuilder(11)).append("totalsize: ").append($this.totalsize()).toString());
         append.apply((new java.lang.StringBuilder(15)).append("current entry: ").append($this.es()).toString());
         append.apply((new java.lang.StringBuilder(23)).append("underlying from ").append($this.idx()).append(" until ").append($this.until()).toString());
         append.apply(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.refArrayOps((Object[])$this.itertable()), $this.idx(), $this.until())), (x) -> x != null ? x.toString() : "n/a", scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(" | "));
         append.apply("\\--------------------/");
      }

      public EntryIterator(final int idx, final int until, final int totalsize, final HashEntry es) {
         this.idx = idx;
         this.until = until;
         this.totalsize = totalsize;
         this.es = es;
         if (ParHashTable.this == null) {
            throw null;
         } else {
            this.$outer = ParHashTable.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            SizeMapUtils.$init$(this);
            this.itertable = ParHashTable.this.table();
            this.traversed = 0;
            this.scan();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Contents {
      private final int loadFactor;
      private final HashEntry[] table;
      private final int tableSize;
      private final int threshold;
      private final int seedvalue;
      private final int[] sizemap;

      public int loadFactor() {
         return this.loadFactor;
      }

      public HashEntry[] table() {
         return this.table;
      }

      public int tableSize() {
         return this.tableSize;
      }

      public int threshold() {
         return this.threshold;
      }

      public int seedvalue() {
         return this.seedvalue;
      }

      public int[] sizemap() {
         return this.sizemap;
      }

      public String debugInformation() {
         return DebugUtils$.MODULE$.buildString((append) -> {
            $anonfun$debugInformation$3(this, append);
            return BoxedUnit.UNIT;
         });
      }

      // $FF: synthetic method
      public static final void $anonfun$debugInformation$3(final Contents $this, final Function1 append) {
         append.apply("Hash table contents");
         append.apply("-------------------");
         append.apply((new java.lang.StringBuilder(9)).append("Table: [").append(DebugUtils$.MODULE$.arrayString($this.table(), 0, $this.table().length)).append("]").toString());
         append.apply((new java.lang.StringBuilder(12)).append("Table size: ").append($this.tableSize()).toString());
         append.apply((new java.lang.StringBuilder(13)).append("Load factor: ").append($this.loadFactor()).toString());
         append.apply((new java.lang.StringBuilder(11)).append("Seedvalue: ").append($this.seedvalue()).toString());
         append.apply((new java.lang.StringBuilder(11)).append("Threshold: ").append($this.threshold()).toString());
         append.apply((new java.lang.StringBuilder(11)).append("Sizemap: [").append(DebugUtils$.MODULE$.arrayString($this.sizemap(), 0, $this.sizemap().length)).append("]").toString());
      }

      public Contents(final int loadFactor, final HashEntry[] table, final int tableSize, final int threshold, final int seedvalue, final int[] sizemap) {
         this.loadFactor = loadFactor;
         this.table = table;
         this.tableSize = tableSize;
         this.threshold = threshold;
         this.seedvalue = seedvalue;
         this.sizemap = sizemap;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
