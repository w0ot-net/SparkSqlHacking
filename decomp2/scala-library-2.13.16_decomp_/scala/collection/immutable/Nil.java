package scala.collection.immutable;

import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.SeqView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(
   bytes = "\u0006\u0005m<Q\u0001E\t\t\u0002b1QAG\t\t\u0002nAQAM\u0001\u0005\u0002MBQ\u0001N\u0001\u0005BUBQAN\u0001\u0005B]BQaO\u0001\u0005BUBQ\u0001P\u0001\u0005BUBQ!P\u0001\u0005BUBQAP\u0001\u0005B}BQaQ\u0001\u0005B\u0011CQ!S\u0001\u0005B)CaAY\u0001!\u0002\u0013\u0019\u0007bB5\u0002\u0003\u0003%\tE\u001b\u0005\bg\u0006\t\t\u0011\"\u0001@\u0011\u001d!\u0018!!A\u0005\u0002UDq\u0001_\u0001\u0002\u0002\u0013\u0005\u00130A\u0002OS2T!AE\n\u0002\u0013%lW.\u001e;bE2,'B\u0001\u000b\u0016\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002-\u0005)1oY1mC\u000e\u0001\u0001CA\r\u0002\u001b\u0005\t\"a\u0001(jYN!\u0011\u0001H\u0012'!\rIRdH\u0005\u0003=E\u0011A\u0001T5tiB\u0011\u0001%I\u0007\u0002+%\u0011!%\u0006\u0002\b\u001d>$\b.\u001b8h!\t\u0001C%\u0003\u0002&+\t9\u0001K]8ek\u000e$\bCA\u00140\u001d\tASF\u0004\u0002*Y5\t!F\u0003\u0002,/\u00051AH]8pizJ\u0011AF\u0005\u0003]U\tq\u0001]1dW\u0006<W-\u0003\u00021c\ta1+\u001a:jC2L'0\u00192mK*\u0011a&F\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\tA\u0001[3bIV\tq$\u0001\u0006iK\u0006$w\n\u001d;j_:,\u0012\u0001\u000f\b\u0003AeJ!AO\u000b\u0002\t9{g.Z\u0001\u0005i\u0006LG.\u0001\u0003mCN$\u0018\u0001B5oSR\f\u0011b\u001b8po:\u001c\u0016N_3\u0016\u0003\u0001\u0003\"\u0001I!\n\u0005\t+\"aA%oi\u0006A\u0011\u000e^3sCR|'/F\u0001F!\r1uiH\u0007\u0002'%\u0011\u0001j\u0005\u0002\t\u0013R,'/\u0019;pe\u0006)QO\u001c>jaV\u00191J\u0015.\u0015\u00051c\u0006\u0003\u0002\u0011N\u001fbK!AT\u000b\u0003\rQ+\b\u000f\\33!\rIR\u0004\u0015\t\u0003#Jc\u0001\u0001B\u0003T\u0015\t\u0007AK\u0001\u0002BcE\u0011q$\u0016\t\u0003AYK!aV\u000b\u0003\u0007\u0005s\u0017\u0010E\u0002\u001a;e\u0003\"!\u0015.\u0005\u000bmS!\u0019\u0001+\u0003\u0005\u0005\u0013\u0004\"B/\u000b\u0001\bq\u0016AB1t!\u0006L'\u000f\u0005\u0003!?~\t\u0017B\u00011\u0016\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003!\u001bBK\u0016AC#naRLXK\u001c>jaB!\u0001%\u00143e\u001d\tI\u0002\u0001\u000b\u0002\fMB\u0011\u0001eZ\u0005\u0003QV\u0011\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Y\u0007C\u00017r\u001b\u0005i'B\u00018p\u0003\u0011a\u0017M\\4\u000b\u0003A\fAA[1wC&\u0011!/\u001c\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011QK\u001e\u0005\bo:\t\t\u00111\u0001A\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t!\u0010E\u0002G\u000fV\u0003"
)
public final class Nil {
   public static Iterator productIterator() {
      return Nil$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return Nil$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return Nil$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return Nil$.MODULE$.productPrefix();
   }

   public static Tuple2 unzip(final Function1 asPair) {
      return Nil$.MODULE$.unzip(asPair);
   }

   public static Iterator iterator() {
      return Nil$.MODULE$.iterator();
   }

   public static int knownSize() {
      return Nil$.MODULE$.knownSize();
   }

   public static Nothing$ init() {
      return Nil$.MODULE$.init();
   }

   public static Nothing$ last() {
      return Nil$.MODULE$.last();
   }

   public static Nothing$ tail() {
      return Nil$.MODULE$.tail();
   }

   public static None$ headOption() {
      return Nil$.MODULE$.headOption();
   }

   public static Nothing$ head() {
      return Nil$.MODULE$.head();
   }

   public static Iterator productElementNames() {
      return Nil$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return Nil$.MODULE$.productElementName(n);
   }

   public static boolean equals(final Object o) {
      return Nil$.MODULE$.equals(o);
   }

   public static List toList() {
      return Nil$.MODULE$.toList();
   }

   public static Tuple2 partition(final Function1 p) {
      return Nil$.MODULE$.partition(p);
   }

   public static List filterNot(final Function1 p) {
      return Nil$.MODULE$.filterNot(p);
   }

   public static List filter(final Function1 p) {
      return Nil$.MODULE$.filter(p);
   }

   public static List mapConserve(final Function1 f) {
      return Nil$.MODULE$.mapConserve(f);
   }

   public static boolean corresponds(final scala.collection.Seq that, final Function2 p) {
      return Nil$.MODULE$.corresponds(that, p);
   }

   public static Option find(final Function1 p) {
      return Nil$.MODULE$.find(p);
   }

   public static boolean contains(final Object elem) {
      return Nil$.MODULE$.contains(elem);
   }

   public static boolean exists(final Function1 p) {
      return Nil$.MODULE$.exists(p);
   }

   public static boolean forall(final Function1 p) {
      return Nil$.MODULE$.forall(p);
   }

   public static int lengthCompare(final int len) {
      return Nil$.MODULE$.lengthCompare(len);
   }

   public static int length() {
      return Nil$.MODULE$.length();
   }

   public static Object foldRight(final Object z, final Function2 op) {
      return Nil$.MODULE$.foldRight(z, op);
   }

   public static List reverse() {
      return Nil$.MODULE$.reverse();
   }

   public static void foreach(final Function1 f) {
      Nil$.MODULE$.foreach(f);
   }

   public static Tuple2 span(final Function1 p) {
      return Nil$.MODULE$.span(p);
   }

   public static List takeWhile(final Function1 p) {
      return Nil$.MODULE$.takeWhile(p);
   }

   public static List flatMap(final Function1 f) {
      return Nil$.MODULE$.flatMap(f);
   }

   public static List collect(final PartialFunction pf) {
      return Nil$.MODULE$.collect(pf);
   }

   public static List map(final Function1 f) {
      return Nil$.MODULE$.map(f);
   }

   public static List updated(final int index, final Object elem) {
      return Nil$.MODULE$.updated(index, elem);
   }

   public static Tuple2 splitAt(final int n) {
      return Nil$.MODULE$.splitAt(n);
   }

   public static List takeRight(final int n) {
      return Nil$.MODULE$.takeRight(n);
   }

   public static List slice(final int from, final int until) {
      return Nil$.MODULE$.slice(from, until);
   }

   public static List take(final int n) {
      return Nil$.MODULE$.take(n);
   }

   public static List appendedAll(final IterableOnce suffix) {
      return Nil$.MODULE$.appendedAll(suffix);
   }

   public static List prependedAll(final IterableOnce prefix) {
      return Nil$.MODULE$.prependedAll(prefix);
   }

   public static List prepended(final Object elem) {
      return Nil$.MODULE$.prepended(elem);
   }

   public static boolean isEmpty() {
      return Nil$.MODULE$.isEmpty();
   }

   public static List reverse_$colon$colon$colon(final List prefix) {
      return Nil$.MODULE$.reverse_$colon$colon$colon(prefix);
   }

   public static List $colon$colon$colon(final List prefix) {
      return Nil$.MODULE$.$colon$colon$colon(prefix);
   }

   public static List $colon$colon(final Object elem) {
      return Nil$.MODULE$.$colon$colon(elem);
   }

   public static SeqFactory iterableFactory() {
      return Nil$.MODULE$.iterableFactory();
   }

   public static Object sorted(final Ordering ord) {
      return Nil$.MODULE$.sorted(ord);
   }

   public static Object patch(final int from, final IterableOnce other, final int replaced) {
      return Nil$.MODULE$.patch(from, other, replaced);
   }

   public static Object distinctBy(final Function1 f) {
      return Nil$.MODULE$.distinctBy(f);
   }

   public static scala.collection.LinearSeq dropWhile(final Function1 p) {
      return Nil$.MODULE$.dropWhile(p);
   }

   public static scala.collection.LinearSeq drop(final int n) {
      return Nil$.MODULE$.drop(n);
   }

   public static Object intersect(final scala.collection.Seq that) {
      return Nil$.MODULE$.intersect(that);
   }

   public static Object diff(final scala.collection.Seq that) {
      return Nil$.MODULE$.diff(that);
   }

   public static Object padTo(final int len, final Object elem) {
      return Nil$.MODULE$.padTo(len, elem);
   }

   public static Object appended(final Object elem) {
      return Nil$.MODULE$.appended(elem);
   }

   public static Object dropRight(final int n) {
      return Nil$.MODULE$.dropRight(n);
   }

   public static Object tapEach(final Function1 f) {
      return Nil$.MODULE$.tapEach(f);
   }

   public static Tuple2 partitionMap(final Function1 f) {
      return Nil$.MODULE$.partitionMap(f);
   }

   public static Object scanLeft(final Object z, final Function2 op) {
      return Nil$.MODULE$.scanLeft(z, op);
   }

   public static Object zipWithIndex() {
      return Nil$.MODULE$.zipWithIndex();
   }

   public static Object zip(final IterableOnce that) {
      return Nil$.MODULE$.zip(that);
   }

   public static Object flatten(final Function1 toIterableOnce) {
      return Nil$.MODULE$.flatten(toIterableOnce);
   }

   public static Tuple3 unzip3(final Function1 asTriple) {
      return Nil$.MODULE$.unzip3(asTriple);
   }

   public static Iterator tails() {
      return Nil$.MODULE$.tails();
   }

   public static Option findLast(final Function1 p) {
      return Nil$.MODULE$.findLast(p);
   }

   public static int lastIndexWhere(final Function1 p, final int end) {
      return Nil$.MODULE$.lastIndexWhere(p, end);
   }

   public static int indexWhere(final Function1 p, final int from) {
      return Nil$.MODULE$.indexWhere(p, from);
   }

   public static int segmentLength(final Function1 p, final int from) {
      return Nil$.MODULE$.segmentLength(p, from);
   }

   public static boolean sameElements(final IterableOnce that) {
      return Nil$.MODULE$.sameElements(that);
   }

   public static Object foldLeft(final Object z, final Function2 op) {
      return Nil$.MODULE$.foldLeft(z, op);
   }

   public static Object apply(final int n) throws IndexOutOfBoundsException {
      return Nil$.MODULE$.apply(n);
   }

   public static boolean isDefinedAt(final int x) {
      return Nil$.MODULE$.isDefinedAt(x);
   }

   public static int lengthCompare(final scala.collection.Iterable that) {
      return Nil$.MODULE$.lengthCompare(that);
   }

   public static Seq toSeq() {
      return Nil$.MODULE$.toSeq();
   }

   public static String toString() {
      return Nil$.MODULE$.toString();
   }

   public static int hashCode() {
      return Nil$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object that) {
      return Nil$.MODULE$.canEqual(that);
   }

   public static Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return Nil$.MODULE$.search(elem, from, to, ord);
   }

   public static Searching.SearchResult search(final Object elem, final Ordering ord) {
      return Nil$.MODULE$.search(elem, ord);
   }

   public static IterableOps lengthIs() {
      return Nil$.MODULE$.lengthIs();
   }

   public static int sizeCompare(final scala.collection.Iterable that) {
      return Nil$.MODULE$.sizeCompare(that);
   }

   public static int sizeCompare(final int otherSize) {
      return Nil$.MODULE$.sizeCompare(otherSize);
   }

   public static Range indices() {
      return Nil$.MODULE$.indices();
   }

   public static Object sortBy(final Function1 f, final Ordering ord) {
      return Nil$.MODULE$.sortBy(f, ord);
   }

   public static Object sortWith(final Function2 lt) {
      return Nil$.MODULE$.sortWith(lt);
   }

   public static Iterator combinations(final int n) {
      return Nil$.MODULE$.combinations(n);
   }

   public static Iterator permutations() {
      return Nil$.MODULE$.permutations();
   }

   /** @deprecated */
   public static Object reverseMap(final Function1 f) {
      return Nil$.MODULE$.reverseMap(f);
   }

   public static boolean containsSlice(final scala.collection.Seq that) {
      return Nil$.MODULE$.containsSlice(that);
   }

   public static int lastIndexOfSlice(final scala.collection.Seq that) {
      return Nil$.MODULE$.lastIndexOfSlice(that);
   }

   public static int lastIndexOfSlice(final scala.collection.Seq that, final int end) {
      return Nil$.MODULE$.lastIndexOfSlice(that, end);
   }

   public static int indexOfSlice(final scala.collection.Seq that) {
      return Nil$.MODULE$.indexOfSlice(that);
   }

   public static int indexOfSlice(final scala.collection.Seq that, final int from) {
      return Nil$.MODULE$.indexOfSlice(that, from);
   }

   public static int lastIndexWhere(final Function1 p) {
      return Nil$.MODULE$.lastIndexWhere(p);
   }

   public static int lastIndexOf$default$2() {
      return Nil$.MODULE$.lastIndexOf$default$2();
   }

   public static int lastIndexOf(final Object elem, final int end) {
      return Nil$.MODULE$.lastIndexOf(elem, end);
   }

   public static int indexOf(final Object elem) {
      return Nil$.MODULE$.indexOf(elem);
   }

   public static int indexOf(final Object elem, final int from) {
      return Nil$.MODULE$.indexOf(elem, from);
   }

   public static int indexWhere(final Function1 p) {
      return Nil$.MODULE$.indexWhere(p);
   }

   /** @deprecated */
   public static int prefixLength(final Function1 p) {
      return Nil$.MODULE$.prefixLength(p);
   }

   public static int segmentLength(final Function1 p) {
      return Nil$.MODULE$.segmentLength(p);
   }

   public static boolean endsWith(final scala.collection.Iterable that) {
      return Nil$.MODULE$.endsWith(that);
   }

   public static int startsWith$default$2() {
      return Nil$.MODULE$.startsWith$default$2();
   }

   public static boolean startsWith(final IterableOnce that, final int offset) {
      return Nil$.MODULE$.startsWith(that, offset);
   }

   public static Iterator reverseIterator() {
      return Nil$.MODULE$.reverseIterator();
   }

   public static Object distinct() {
      return Nil$.MODULE$.distinct();
   }

   public static int size() {
      return Nil$.MODULE$.size();
   }

   /** @deprecated */
   public static Object union(final scala.collection.Seq that) {
      return Nil$.MODULE$.union(that);
   }

   public static Object concat(final IterableOnce suffix) {
      return Nil$.MODULE$.concat(suffix);
   }

   public static Object $colon$plus$plus(final IterableOnce suffix) {
      return Nil$.MODULE$.$colon$plus$plus(suffix);
   }

   public static Object $plus$plus$colon(final IterableOnce prefix) {
      return Nil$.MODULE$.$plus$plus$colon(prefix);
   }

   public static Object $colon$plus(final Object elem) {
      return Nil$.MODULE$.$colon$plus(elem);
   }

   public static Object $plus$colon(final Object elem) {
      return Nil$.MODULE$.$plus$colon(elem);
   }

   public static SeqView view() {
      return Nil$.MODULE$.view();
   }

   public static Function1 runWith(final Function1 action) {
      return Nil$.MODULE$.runWith(action);
   }

   public static Object applyOrElse(final Object x, final Function1 default) {
      return Nil$.MODULE$.applyOrElse(x, default);
   }

   public static Function1 lift() {
      return Nil$.MODULE$.lift();
   }

   public static PartialFunction compose(final PartialFunction k) {
      return Nil$.MODULE$.compose(k);
   }

   public static PartialFunction andThen(final PartialFunction k) {
      return Nil$.MODULE$.andThen(k);
   }

   public static PartialFunction andThen(final Function1 k) {
      return Nil$.MODULE$.andThen(k);
   }

   public static PartialFunction orElse(final PartialFunction that) {
      return Nil$.MODULE$.orElse(that);
   }

   public static PartialFunction elementWise() {
      return Nil$.MODULE$.elementWise();
   }

   public static Option unapply(final Object a) {
      return Nil$.MODULE$.unapply(a);
   }

   public static Function1 compose(final Function1 g) {
      return Nil$.MODULE$.compose(g);
   }

   public static LazyZip2 lazyZip(final scala.collection.Iterable that) {
      return Nil$.MODULE$.lazyZip(that);
   }

   /** @deprecated */
   public static scala.collection.Iterable seq() {
      return Nil$.MODULE$.seq();
   }

   /** @deprecated */
   public static scala.collection.Iterable toIterable() {
      return Nil$.MODULE$.toIterable();
   }

   public static IterableOps empty() {
      return Nil$.MODULE$.empty();
   }

   public static Iterator inits() {
      return Nil$.MODULE$.inits();
   }

   public static Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
      return Nil$.MODULE$.zipAll(that, thisElem, thatElem);
   }

   public static Object $plus$plus(final IterableOnce suffix) {
      return Nil$.MODULE$.$plus$plus(suffix);
   }

   public static Object scanRight(final Object z, final Function2 op) {
      return Nil$.MODULE$.scanRight(z, op);
   }

   public static Object scan(final Object z, final Function2 op) {
      return Nil$.MODULE$.scan(z, op);
   }

   public static Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return Nil$.MODULE$.groupMapReduce(key, f, reduce);
   }

   public static Map groupMap(final Function1 key, final Function1 f) {
      return Nil$.MODULE$.groupMap(key, f);
   }

   public static Map groupBy(final Function1 f) {
      return Nil$.MODULE$.groupBy(f);
   }

   public static Iterator sliding(final int size, final int step) {
      return Nil$.MODULE$.sliding(size, step);
   }

   public static Iterator sliding(final int size) {
      return Nil$.MODULE$.sliding(size);
   }

   public static Iterator grouped(final int size) {
      return Nil$.MODULE$.grouped(size);
   }

   public static WithFilter withFilter(final Function1 p) {
      return Nil$.MODULE$.withFilter(p);
   }

   public static Object transpose(final Function1 asIterable) {
      return Nil$.MODULE$.transpose(asIterable);
   }

   /** @deprecated */
   public static View view(final int from, final int until) {
      return Nil$.MODULE$.view(from, until);
   }

   public static IterableOps sizeIs() {
      return Nil$.MODULE$.sizeIs();
   }

   public static Option lastOption() {
      return Nil$.MODULE$.lastOption();
   }

   /** @deprecated */
   public static IterableFactory companion() {
      return Nil$.MODULE$.companion();
   }

   /** @deprecated */
   public static Object repr() {
      return Nil$.MODULE$.repr();
   }

   public static boolean isTraversableAgain() {
      return Nil$.MODULE$.isTraversableAgain();
   }

   /** @deprecated */
   public static scala.collection.Iterable toTraversable() {
      return Nil$.MODULE$.toTraversable();
   }

   public static Object toArray(final ClassTag evidence$2) {
      return Nil$.MODULE$.toArray(evidence$2);
   }

   public static Buffer toBuffer() {
      return Nil$.MODULE$.toBuffer();
   }

   /** @deprecated */
   public static Stream toStream() {
      return Nil$.MODULE$.toStream();
   }

   public static IndexedSeq toIndexedSeq() {
      return Nil$.MODULE$.toIndexedSeq();
   }

   public static Set toSet() {
      return Nil$.MODULE$.toSet();
   }

   public static Map toMap(final $less$colon$less ev) {
      return Nil$.MODULE$.toMap(ev);
   }

   public static Vector toVector() {
      return Nil$.MODULE$.toVector();
   }

   /** @deprecated */
   public static Iterator toIterator() {
      return Nil$.MODULE$.toIterator();
   }

   public static Object to(final Factory factory) {
      return Nil$.MODULE$.to(factory);
   }

   public static StringBuilder addString(final StringBuilder b) {
      return Nil$.MODULE$.addString(b);
   }

   public static StringBuilder addString(final StringBuilder b, final String sep) {
      return Nil$.MODULE$.addString(b, sep);
   }

   public static StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return Nil$.MODULE$.addString(b, start, sep, end);
   }

   public static String mkString() {
      return Nil$.MODULE$.mkString();
   }

   public static String mkString(final String sep) {
      return Nil$.MODULE$.mkString(sep);
   }

   public static String mkString(final String start, final String sep, final String end) {
      return Nil$.MODULE$.mkString(start, sep, end);
   }

   public static boolean corresponds(final IterableOnce that, final Function2 p) {
      return Nil$.MODULE$.corresponds(that, p);
   }

   /** @deprecated */
   public static Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return Nil$.MODULE$.aggregate(z, seqop, combop);
   }

   public static Option collectFirst(final PartialFunction pf) {
      return Nil$.MODULE$.collectFirst(pf);
   }

   public static Option minByOption(final Function1 f, final Ordering ord) {
      return Nil$.MODULE$.minByOption(f, ord);
   }

   public static Object minBy(final Function1 f, final Ordering ord) {
      return Nil$.MODULE$.minBy(f, ord);
   }

   public static Option maxByOption(final Function1 f, final Ordering ord) {
      return Nil$.MODULE$.maxByOption(f, ord);
   }

   public static Object maxBy(final Function1 f, final Ordering ord) {
      return Nil$.MODULE$.maxBy(f, ord);
   }

   public static Option maxOption(final Ordering ord) {
      return Nil$.MODULE$.maxOption(ord);
   }

   public static Object max(final Ordering ord) {
      return Nil$.MODULE$.max(ord);
   }

   public static Option minOption(final Ordering ord) {
      return Nil$.MODULE$.minOption(ord);
   }

   public static Object min(final Ordering ord) {
      return Nil$.MODULE$.min(ord);
   }

   public static Object product(final Numeric num) {
      return Nil$.MODULE$.product(num);
   }

   public static Object sum(final Numeric num) {
      return Nil$.MODULE$.sum(num);
   }

   public static int copyToArray(final Object xs, final int start, final int len) {
      return Nil$.MODULE$.copyToArray(xs, start, len);
   }

   public static int copyToArray(final Object xs, final int start) {
      return Nil$.MODULE$.copyToArray(xs, start);
   }

   public static int copyToArray(final Object xs) {
      return Nil$.MODULE$.copyToArray(xs);
   }

   /** @deprecated */
   public static void copyToBuffer(final Buffer dest) {
      Nil$.MODULE$.copyToBuffer(dest);
   }

   public static boolean nonEmpty() {
      return Nil$.MODULE$.nonEmpty();
   }

   public static Option reduceRightOption(final Function2 op) {
      return Nil$.MODULE$.reduceRightOption(op);
   }

   public static Option reduceLeftOption(final Function2 op) {
      return Nil$.MODULE$.reduceLeftOption(op);
   }

   public static Object reduceRight(final Function2 op) {
      return Nil$.MODULE$.reduceRight(op);
   }

   public static Object reduceLeft(final Function2 op) {
      return Nil$.MODULE$.reduceLeft(op);
   }

   public static Option reduceOption(final Function2 op) {
      return Nil$.MODULE$.reduceOption(op);
   }

   public static Object reduce(final Function2 op) {
      return Nil$.MODULE$.reduce(op);
   }

   public static Object fold(final Object z, final Function2 op) {
      return Nil$.MODULE$.fold(z, op);
   }

   /** @deprecated */
   public static Object $colon$bslash(final Object z, final Function2 op) {
      return Nil$.MODULE$.$colon$bslash(z, op);
   }

   /** @deprecated */
   public static Object $div$colon(final Object z, final Function2 op) {
      return Nil$.MODULE$.$div$colon(z, op);
   }

   public static int count(final Function1 p) {
      return Nil$.MODULE$.count(p);
   }

   /** @deprecated */
   public static boolean hasDefiniteSize() {
      return Nil$.MODULE$.hasDefiniteSize();
   }

   public static Stepper stepper(final StepperShape shape) {
      return Nil$.MODULE$.stepper(shape);
   }
}
