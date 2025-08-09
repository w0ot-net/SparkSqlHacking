package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192QAA\u0002\u0002\u0002!AQ!\b\u0001\u0005\u0002y\u0011a#\u00112tiJ\f7\r^%oI\u0016DX\rZ*fcZKWm\u001e\u0006\u0003\t\u0015\t!bY8mY\u0016\u001cG/[8o\u0015\u00051\u0011!B:dC2\f7\u0001A\u000b\u0003\u0013A\u00192\u0001\u0001\u0006\u001b!\rYABD\u0007\u0002\u0007%\u0011Qb\u0001\u0002\u0010\u0003\n\u001cHO]1diN+\u0017OV5foB\u0011q\u0002\u0005\u0007\u0001\t\u0019\t\u0002\u0001\"b\u0001%\t\t\u0011)\u0005\u0002\u0014/A\u0011A#F\u0007\u0002\u000b%\u0011a#\u0002\u0002\b\u001d>$\b.\u001b8h!\t!\u0002$\u0003\u0002\u001a\u000b\t\u0019\u0011I\\=\u0011\u0007-Yb\"\u0003\u0002\u001d\u0007\tq\u0011J\u001c3fq\u0016$7+Z9WS\u0016<\u0018A\u0002\u001fj]&$h\bF\u0001 !\rY\u0001A\u0004\u0015\u0005\u0001\u0005\"S\u0005\u0005\u0002\u0015E%\u00111%\u0002\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012a\u0001"
)
public abstract class AbstractIndexedSeqView extends AbstractSeqView implements IndexedSeqView {
   private static final long serialVersionUID = 3L;

   public IndexedSeqView view() {
      return IndexedSeqView.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return IndexedSeqView.view$(this, from, until);
   }

   public Iterator iterator() {
      return IndexedSeqView.iterator$(this);
   }

   public Iterator reverseIterator() {
      return IndexedSeqView.reverseIterator$(this);
   }

   public IndexedSeqView appended(final Object elem) {
      return IndexedSeqView.appended$(this, elem);
   }

   public IndexedSeqView prepended(final Object elem) {
      return IndexedSeqView.prepended$(this, elem);
   }

   public IndexedSeqView take(final int n) {
      return IndexedSeqView.take$(this, n);
   }

   public IndexedSeqView takeRight(final int n) {
      return IndexedSeqView.takeRight$(this, n);
   }

   public IndexedSeqView drop(final int n) {
      return IndexedSeqView.drop$(this, n);
   }

   public IndexedSeqView dropRight(final int n) {
      return IndexedSeqView.dropRight$(this, n);
   }

   public IndexedSeqView map(final Function1 f) {
      return IndexedSeqView.map$(this, f);
   }

   public IndexedSeqView reverse() {
      return IndexedSeqView.reverse$(this);
   }

   public IndexedSeqView slice(final int from, final int until) {
      return IndexedSeqView.slice$(this, from, until);
   }

   public IndexedSeqView tapEach(final Function1 f) {
      return IndexedSeqView.tapEach$(this, f);
   }

   public IndexedSeqView concat(final IndexedSeqOps suffix) {
      return IndexedSeqView.concat$(this, suffix);
   }

   public IndexedSeqView appendedAll(final IndexedSeqOps suffix) {
      return IndexedSeqView.appendedAll$(this, suffix);
   }

   public IndexedSeqView prependedAll(final IndexedSeqOps prefix) {
      return IndexedSeqView.prependedAll$(this, prefix);
   }

   public String stringPrefix() {
      return IndexedSeqView.stringPrefix$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IndexedSeqOps.stepper$(this, shape);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return IndexedSeqOps.foldRight$(this, z, op);
   }

   public Iterable reversed() {
      return IndexedSeqOps.reversed$(this);
   }

   public Object head() {
      return IndexedSeqOps.head$(this);
   }

   public Option headOption() {
      return IndexedSeqOps.headOption$(this);
   }

   public Object last() {
      return IndexedSeqOps.last$(this);
   }

   public final int lengthCompare(final int len) {
      return IndexedSeqOps.lengthCompare$(this, len);
   }

   public int knownSize() {
      return IndexedSeqOps.knownSize$(this);
   }

   public final int lengthCompare(final Iterable that) {
      return IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return IndexedSeqOps.search$(this, elem, from, to, ord);
   }
}
