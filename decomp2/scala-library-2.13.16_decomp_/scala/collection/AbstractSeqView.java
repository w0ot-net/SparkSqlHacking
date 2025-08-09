package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.collection.immutable.Range;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192QAA\u0002\u0002\u0002!AQ!\b\u0001\u0005\u0002y\u0011q\"\u00112tiJ\f7\r^*fcZKWm\u001e\u0006\u0003\t\u0015\t!bY8mY\u0016\u001cG/[8o\u0015\u00051\u0011!B:dC2\f7\u0001A\u000b\u0003\u0013A\u00192\u0001\u0001\u0006\u001b!\rYABD\u0007\u0002\u0007%\u0011Qb\u0001\u0002\r\u0003\n\u001cHO]1diZKWm\u001e\t\u0003\u001fAa\u0001\u0001\u0002\u0004\u0012\u0001\u0011\u0015\rA\u0005\u0002\u0002\u0003F\u00111c\u0006\t\u0003)Ui\u0011!B\u0005\u0003-\u0015\u0011qAT8uQ&tw\r\u0005\u0002\u00151%\u0011\u0011$\u0002\u0002\u0004\u0003:L\bcA\u0006\u001c\u001d%\u0011Ad\u0001\u0002\b'\u0016\fh+[3x\u0003\u0019a\u0014N\\5u}Q\tq\u0004E\u0002\f\u00019AC\u0001A\u0011%KA\u0011ACI\u0005\u0003G\u0015\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\r\u0001"
)
public abstract class AbstractSeqView extends AbstractView implements SeqView {
   private static final long serialVersionUID = 3L;

   public SeqView view() {
      return SeqView.view$(this);
   }

   public SeqView map(final Function1 f) {
      return SeqView.map$(this, f);
   }

   public SeqView appended(final Object elem) {
      return SeqView.appended$(this, elem);
   }

   public SeqView prepended(final Object elem) {
      return SeqView.prepended$(this, elem);
   }

   public SeqView reverse() {
      return SeqView.reverse$(this);
   }

   public SeqView take(final int n) {
      return SeqView.take$(this, n);
   }

   public SeqView drop(final int n) {
      return SeqView.drop$(this, n);
   }

   public SeqView takeRight(final int n) {
      return SeqView.takeRight$(this, n);
   }

   public SeqView dropRight(final int n) {
      return SeqView.dropRight$(this, n);
   }

   public SeqView tapEach(final Function1 f) {
      return SeqView.tapEach$(this, f);
   }

   public SeqView concat(final SeqOps suffix) {
      return SeqView.concat$(this, suffix);
   }

   public SeqView appendedAll(final SeqOps suffix) {
      return SeqView.appendedAll$(this, suffix);
   }

   public SeqView prependedAll(final SeqOps prefix) {
      return SeqView.prependedAll$(this, prefix);
   }

   public SeqView sorted(final Ordering ord) {
      return SeqView.sorted$(this, ord);
   }

   public String stringPrefix() {
      return SeqView.stringPrefix$(this);
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

   public final Object $plus$colon(final Object elem) {
      return SeqOps.$plus$colon$(this, elem);
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
}
