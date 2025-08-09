package scala.collection.parallel.mutable;

import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.concurrent.TrieMap;
import scala.collection.concurrent.TrieMapIterator;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.Signalling;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.SeqSplitter;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m4Q\u0001E\t\u0001+eA\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\ty\u0001\u0011\t\u0011)A\u0005{!A\u0001\t\u0001B\u0001B\u0003%\u0011\tC\u0003E\u0001\u0011\u0005Q\t\u0003\u0005L\u0001!\u0015\r\u0011\"\u0001M\u0011\u001di\u0005\u00011A\u0005\u00021CqA\u0014\u0001A\u0002\u0013\u0005q\n\u0003\u0004V\u0001\u0001\u0006K!\u000f\u0005\u0006-\u0002!\tf\u0016\u0005\u0006=\u0002!\te\u0018\u0005\u0006W\u0002!\t\u0001\u001c\u0005\u0006[\u0002!\tE\u001c\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006q\u0002!\t%\u001f\u0005\u0006u\u0002!\t\u0001\u0014\u0002\u0013!\u0006\u0014HK]5f\u001b\u0006\u00048\u000b\u001d7jiR,'O\u0003\u0002\u0013'\u00059Q.\u001e;bE2,'B\u0001\u000b\u0016\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\f\u0018\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u00021\u0005)1oY1mCV\u0019!dI\u0018\u0014\u0007\u0001Y\u0012\u0007\u0005\u0003\u001d?\u0005rS\"A\u000f\u000b\u0005y)\u0012AC2p]\u000e,(O]3oi&\u0011\u0001%\b\u0002\u0010)JLW-T1q\u0013R,'/\u0019;peB\u0011!e\t\u0007\u0001\t\u0015!\u0003A1\u0001'\u0005\u0005Y5\u0001A\t\u0003O-\u0002\"\u0001K\u0015\u000e\u0003]I!AK\f\u0003\u000f9{G\u000f[5oOB\u0011\u0001\u0006L\u0005\u0003[]\u00111!\u00118z!\t\u0011s\u0006B\u00031\u0001\t\u0007aEA\u0001W!\r\u00114'N\u0007\u0002'%\u0011Ag\u0005\u0002\u0011\u0013R,'/\u00192mKN\u0003H.\u001b;uKJ\u0004B\u0001\u000b\u001c\"]%\u0011qg\u0006\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u00071,g\u000f\u0005\u0002)u%\u00111h\u0006\u0002\u0004\u0013:$\u0018AA2u!\u0011ab(\t\u0018\n\u0005}j\"a\u0002+sS\u0016l\u0015\r]\u0001\t[V\u001cH/\u00138jiB\u0011\u0001FQ\u0005\u0003\u0007^\u0011qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0005\r\"K%\n\u0005\u0003H\u0001\u0005rS\"A\t\t\u000ba\"\u0001\u0019A\u001d\t\u000bq\"\u0001\u0019A\u001f\t\u000b\u0001#\u0001\u0019A!\u0002\u0013Q|G/\u00197tSj,W#A\u001d\u0002\u0011%$XM]1uK\u0012\fA\"\u001b;fe\u0006$X\rZ0%KF$\"\u0001U*\u0011\u0005!\n\u0016B\u0001*\u0018\u0005\u0011)f.\u001b;\t\u000fQ;\u0011\u0011!a\u0001s\u0005\u0019\u0001\u0010J\u0019\u0002\u0013%$XM]1uK\u0012\u0004\u0013a\u00038fo&#XM]1u_J$BA\u0012-[9\")\u0011,\u0003a\u0001s\u0005!q\f\\3w\u0011\u0015Y\u0016\u00021\u0001>\u0003\ry6\r\u001e\u0005\u0006;&\u0001\r!Q\u0001\n?6,8\u000f^%oSR\f!c\u001d5pk2$7\u000b\u001d7ji\u001a+(\u000f\u001e5feV\u0011\u0001m\u001a\u000b\u0004\u0003\u0006L\u0007\"\u00022\u000b\u0001\u0004\u0019\u0017\u0001B2pY2\u00042A\r3g\u0013\t)7CA\u0006QCJLE/\u001a:bE2,\u0007C\u0001\u0012h\t\u0015A'B1\u0001'\u0005\u0005\u0019\u0006\"\u00026\u000b\u0001\u0004I\u0014\u0001\u00059be\u0006dG.\u001a7jg6dUM^3m\u0003\r!W\u000f]\u000b\u0002\r\u0006!a.\u001a=u)\u0005)\u0014!B:qY&$X#A9\u0011\u0007I,\u0018G\u0004\u0002)g&\u0011AoF\u0001\ba\u0006\u001c7.Y4f\u0013\t1xOA\u0002TKFT!\u0001^\f\u0002!%\u001c(+Z7bS:LgnZ\"iK\u0006\u0004X#A!\u0002\u0013I,W.Y5oS:<\u0007"
)
public class ParTrieMapSplitter extends TrieMapIterator implements IterableSplitter {
   private int totalsize;
   private final TrieMap ct;
   private int iterated;
   private Signalling signalDelegate;
   private volatile boolean bitmap$0;

   public Seq splitWithSignalling() {
      return IterableSplitter.splitWithSignalling$(this);
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

   public Signalling signalDelegate() {
      return this.signalDelegate;
   }

   public void signalDelegate_$eq(final Signalling x$1) {
      this.signalDelegate = x$1;
   }

   private int totalsize$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.totalsize = (new ParTrieMap(this.ct)).size();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.totalsize;
   }

   public int totalsize() {
      return !this.bitmap$0 ? this.totalsize$lzycompute() : this.totalsize;
   }

   public int iterated() {
      return this.iterated;
   }

   public void iterated_$eq(final int x$1) {
      this.iterated = x$1;
   }

   public ParTrieMapSplitter newIterator(final int _lev, final TrieMap _ct, final boolean _mustInit) {
      return new ParTrieMapSplitter(_lev, _ct, _mustInit);
   }

   public boolean shouldSplitFurther(final scala.collection.parallel.ParIterable coll, final int parallelismLevel) {
      int maxsplits = 3 + Integer.highestOneBit(parallelismLevel);
      return this.level() < maxsplits;
   }

   public ParTrieMapSplitter dup() {
      ParTrieMapSplitter it = this.newIterator(0, this.ct, false);
      this.dupTo(it);
      it.iterated_$eq(this.iterated());
      return it;
   }

   public Tuple2 next() {
      this.iterated_$eq(this.iterated() + 1);
      return super.next();
   }

   public Seq split() {
      return (Seq)this.subdivide();
   }

   public boolean isRemainingCheap() {
      return false;
   }

   public int remaining() {
      return this.totalsize() - this.iterated();
   }

   public ParTrieMapSplitter(final int lev, final TrieMap ct, final boolean mustInit) {
      super(lev, ct, mustInit);
      this.ct = ct;
      RemainsIterator.$init$(this);
      AugmentedIterableIterator.$init$(this);
      DelegatedSignalling.$init$(this);
      IterableSplitter.$init$(this);
      this.iterated = 0;
   }
}
