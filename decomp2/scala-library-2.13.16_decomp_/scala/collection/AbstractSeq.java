package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.Range;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00012QAA\u0002\u0002\u0002!AQ!\b\u0001\u0005\u0002y\u00111\"\u00112tiJ\f7\r^*fc*\u0011A!B\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0011\u0002E\n\u0004\u0001)Q\u0002cA\u0006\r\u001d5\t1!\u0003\u0002\u000e\u0007\t\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006\u0014G.\u001a\t\u0003\u001fAa\u0001\u0001\u0002\u0004\u0012\u0001\u0011\u0015\rA\u0005\u0002\u0002\u0003F\u00111c\u0006\t\u0003)Ui\u0011!B\u0005\u0003-\u0015\u0011qAT8uQ&tw\r\u0005\u0002\u00151%\u0011\u0011$\u0002\u0002\u0004\u0003:L\bcA\u0006\u001c\u001d%\u0011Ad\u0001\u0002\u0004'\u0016\f\u0018A\u0002\u001fj]&$h\bF\u0001 !\rY\u0001A\u0004"
)
public abstract class AbstractSeq extends AbstractIterable implements Seq {
   public SeqFactory iterableFactory() {
      return Seq.iterableFactory$(this);
   }

   public boolean canEqual(final Object that) {
      return Seq.canEqual$(this, that);
   }

   public boolean equals(final Object o) {
      return Seq.equals$(this, o);
   }

   public int hashCode() {
      return Seq.hashCode$(this);
   }

   public String toString() {
      return Seq.toString$(this);
   }

   public String stringPrefix() {
      return Seq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   public SeqView view() {
      return SeqOps.view$(this);
   }

   public Object prepended(final Object elem) {
      return SeqOps.prepended$(this, elem);
   }

   public final Object $plus$colon(final Object elem) {
      return SeqOps.$plus$colon$(this, elem);
   }

   public Object appended(final Object elem) {
      return SeqOps.appended$(this, elem);
   }

   public final Object $colon$plus(final Object elem) {
      return SeqOps.$colon$plus$(this, elem);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return SeqOps.prependedAll$(this, prefix);
   }

   public final Object $plus$plus$colon(final IterableOnce prefix) {
      return SeqOps.$plus$plus$colon$(this, prefix);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return SeqOps.appendedAll$(this, suffix);
   }

   public final Object $colon$plus$plus(final IterableOnce suffix) {
      return SeqOps.$colon$plus$plus$(this, suffix);
   }

   public final Object concat(final IterableOnce suffix) {
      return SeqOps.concat$(this, suffix);
   }

   /** @deprecated */
   public final Object union(final Seq that) {
      return SeqOps.union$(this, that);
   }

   public final int size() {
      return SeqOps.size$(this);
   }

   public Object distinct() {
      return SeqOps.distinct$(this);
   }

   public Object distinctBy(final Function1 f) {
      return SeqOps.distinctBy$(this, f);
   }

   public Object reverse() {
      return SeqOps.reverse$(this);
   }

   public Iterator reverseIterator() {
      return SeqOps.reverseIterator$(this);
   }

   public boolean startsWith(final IterableOnce that, final int offset) {
      return SeqOps.startsWith$(this, that, offset);
   }

   public int startsWith$default$2() {
      return SeqOps.startsWith$default$2$(this);
   }

   public boolean endsWith(final Iterable that) {
      return SeqOps.endsWith$(this, that);
   }

   public boolean isDefinedAt(final int idx) {
      return SeqOps.isDefinedAt$(this, idx);
   }

   public Object padTo(final int len, final Object elem) {
      return SeqOps.padTo$(this, len, elem);
   }

   public final int segmentLength(final Function1 p) {
      return SeqOps.segmentLength$(this, p);
   }

   public int segmentLength(final Function1 p, final int from) {
      return SeqOps.segmentLength$(this, p, from);
   }

   /** @deprecated */
   public final int prefixLength(final Function1 p) {
      return SeqOps.prefixLength$(this, p);
   }

   public int indexWhere(final Function1 p, final int from) {
      return SeqOps.indexWhere$(this, p, from);
   }

   public int indexWhere(final Function1 p) {
      return SeqOps.indexWhere$(this, p);
   }

   public int indexOf(final Object elem, final int from) {
      return SeqOps.indexOf$(this, elem, from);
   }

   public int indexOf(final Object elem) {
      return SeqOps.indexOf$(this, elem);
   }

   public int lastIndexOf(final Object elem, final int end) {
      return SeqOps.lastIndexOf$(this, elem, end);
   }

   public int lastIndexOf$default$2() {
      return SeqOps.lastIndexOf$default$2$(this);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return SeqOps.lastIndexWhere$(this, p, end);
   }

   public int lastIndexWhere(final Function1 p) {
      return SeqOps.lastIndexWhere$(this, p);
   }

   public int indexOfSlice(final Seq that, final int from) {
      return SeqOps.indexOfSlice$(this, that, from);
   }

   public int indexOfSlice(final Seq that) {
      return SeqOps.indexOfSlice$(this, that);
   }

   public int lastIndexOfSlice(final Seq that, final int end) {
      return SeqOps.lastIndexOfSlice$(this, that, end);
   }

   public int lastIndexOfSlice(final Seq that) {
      return SeqOps.lastIndexOfSlice$(this, that);
   }

   public Option findLast(final Function1 p) {
      return SeqOps.findLast$(this, p);
   }

   public boolean containsSlice(final Seq that) {
      return SeqOps.containsSlice$(this, that);
   }

   public boolean contains(final Object elem) {
      return SeqOps.contains$(this, elem);
   }

   /** @deprecated */
   public Object reverseMap(final Function1 f) {
      return SeqOps.reverseMap$(this, f);
   }

   public Iterator permutations() {
      return SeqOps.permutations$(this);
   }

   public Iterator combinations(final int n) {
      return SeqOps.combinations$(this, n);
   }

   public Object sorted(final Ordering ord) {
      return SeqOps.sorted$(this, ord);
   }

   public Object sortWith(final Function2 lt) {
      return SeqOps.sortWith$(this, lt);
   }

   public Object sortBy(final Function1 f, final Ordering ord) {
      return SeqOps.sortBy$(this, f, ord);
   }

   public Range indices() {
      return SeqOps.indices$(this);
   }

   public final int sizeCompare(final int otherSize) {
      return SeqOps.sizeCompare$(this, otherSize);
   }

   public int lengthCompare(final int len) {
      return SeqOps.lengthCompare$(this, len);
   }

   public final int sizeCompare(final Iterable that) {
      return SeqOps.sizeCompare$(this, that);
   }

   public int lengthCompare(final Iterable that) {
      return SeqOps.lengthCompare$(this, that);
   }

   public final IterableOps lengthIs() {
      return SeqOps.lengthIs$(this);
   }

   public boolean isEmpty() {
      return SeqOps.isEmpty$(this);
   }

   public boolean sameElements(final IterableOnce that) {
      return SeqOps.sameElements$(this, that);
   }

   public boolean corresponds(final Seq that, final Function2 p) {
      return SeqOps.corresponds$(this, that, p);
   }

   public Object diff(final Seq that) {
      return SeqOps.diff$(this, that);
   }

   public Object intersect(final Seq that) {
      return SeqOps.intersect$(this, that);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return SeqOps.patch$(this, from, other, replaced);
   }

   public Object updated(final int index, final Object elem) {
      return SeqOps.updated$(this, index, elem);
   }

   public scala.collection.mutable.Map occCounts(final Seq sq) {
      return SeqOps.occCounts$(this, sq);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return SeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return SeqOps.search$(this, elem, from, to, ord);
   }

   public Option unapply(final Object a) {
      return PartialFunction.unapply$(this, a);
   }

   public PartialFunction elementWise() {
      return PartialFunction.elementWise$(this);
   }

   public PartialFunction orElse(final PartialFunction that) {
      return PartialFunction.orElse$(this, that);
   }

   public PartialFunction andThen(final Function1 k) {
      return PartialFunction.andThen$(this, (Function1)k);
   }

   public PartialFunction andThen(final PartialFunction k) {
      return PartialFunction.andThen$(this, (PartialFunction)k);
   }

   public PartialFunction compose(final PartialFunction k) {
      return PartialFunction.compose$(this, k);
   }

   public Function1 lift() {
      return PartialFunction.lift$(this);
   }

   public Object applyOrElse(final Object x, final Function1 default) {
      return PartialFunction.applyOrElse$(this, x, default);
   }

   public Function1 runWith(final Function1 action) {
      return PartialFunction.runWith$(this, action);
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
}
