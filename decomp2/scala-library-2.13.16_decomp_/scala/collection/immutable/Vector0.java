package scala.collection.immutable;

import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.IndexedSeqView;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.WithFilter;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%s!\u0002\n\u0014\u0011\u0013Qb!\u0002\u000f\u0014\u0011\u0013i\u0002\"B\u0013\u0002\t\u00031\u0003\"B\u0014\u0002\t\u0003A\u0003\"\u0002\u0018\u0002\t\u0003z\u0003\"B \u0002\t\u0003\u0002\u0005\"\u0002$\u0002\t\u0003:\u0005\"B'\u0002\t\u0003r\u0005\"\u0002-\u0002\t\u0003J\u0006\"B.\u0002\t\u0003J\u0006B\u0002/\u0002A\u0013EQ\f\u0003\u0004c\u0003\u0011E1c\u0019\u0005\u0007I\u0006!\tbE3\t\rM\fA\u0011C\nu\u0011\u00151\u0018\u0001\"\u0011x\u0011\u0019i\u0018\u0001)C)}\"A\u0011qC\u0001!\n#\nI\u0002\u0003\u0005\u0002,\u0005\u0001K\u0011KA\u0017\u0003\u001d1Vm\u0019;peBR!\u0001F\u000b\u0002\u0013%lW.\u001e;bE2,'B\u0001\f\u0018\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u00021\u0005)1oY1mC\u000e\u0001\u0001CA\u000e\u0002\u001b\u0005\u0019\"a\u0002,fGR|'\u000fM\n\u0003\u0003y\u00012aG\u0010\"\u0013\t\u00013CA\u0005CS\u001e4Vm\u0019;peB\u0011!eI\u0007\u0002/%\u0011Ae\u0006\u0002\b\u001d>$\b.\u001b8h\u0003\u0019a\u0014N\\5u}Q\t!$A\u0003baBd\u0017\u0010\u0006\u0002\"S!)!f\u0001a\u0001W\u0005)\u0011N\u001c3fqB\u0011!\u0005L\u0005\u0003[]\u00111!\u00138u\u0003\u001d)\b\u000fZ1uK\u0012,\"\u0001\r\u001c\u0015\u0007EbT\bE\u0002\u001ceQJ!aM\n\u0003\rY+7\r^8s!\t)d\u0007\u0004\u0001\u0005\u000b]\"!\u0019\u0001\u001d\u0003\u0003\t\u000b\"!I\u001d\u0011\u0005\tR\u0014BA\u001e\u0018\u0005\r\te.\u001f\u0005\u0006U\u0011\u0001\ra\u000b\u0005\u0006}\u0011\u0001\r\u0001N\u0001\u0005K2,W.\u0001\u0005baB,g\u000eZ3e+\t\tE\t\u0006\u0002C\u000bB\u00191DM\"\u0011\u0005U\"E!B\u001c\u0006\u0005\u0004A\u0004\"\u0002 \u0006\u0001\u0004\u0019\u0015!\u00039sKB,g\u000eZ3e+\tA5\n\u0006\u0002J\u0019B\u00191D\r&\u0011\u0005UZE!B\u001c\u0007\u0005\u0004A\u0004\"\u0002 \u0007\u0001\u0004Q\u0015aA7baV\u0011qJ\u0015\u000b\u0003!N\u00032a\u0007\u001aR!\t)$\u000bB\u00038\u000f\t\u0007\u0001\bC\u0003U\u000f\u0001\u0007Q+A\u0001g!\u0011\u0011c+I)\n\u0005];\"!\u0003$v]\u000e$\u0018n\u001c82\u0003\u0011!\u0018-\u001b7\u0016\u0003i\u00032a\u0007\u001a\"\u0003\u0011Ig.\u001b;\u0002\rMd\u0017nY31)\rQf\f\u0019\u0005\u0006?*\u0001\raK\u0001\u0003Y>DQ!\u0019\u0006A\u0002-\n!\u0001[5\u0002!Y,7\r^8s'2L7-Z\"pk:$X#A\u0016\u0002\u0017Y,7\r^8s'2L7-\u001a\u000b\u0003MF\u0004$aZ6\u0011\u0007\tB'.\u0003\u0002j/\t)\u0011I\u001d:bsB\u0011Qg\u001b\u0003\nY2\t\t\u0011!A\u0003\u00025\u0014Aa\u0018\u00132eE\u0011\u0011E\u001c\t\u0003E=L!\u0001]\f\u0003\r\u0005s\u0017PU3g\u0011\u0015\u0011H\u00021\u0001,\u0003\rIG\r_\u0001\u0018m\u0016\u001cGo\u001c:TY&\u001cW\r\u0015:fM&DH*\u001a8hi\"$\"aK;\t\u000bIl\u0001\u0019A\u0016\u0002\r\u0015\fX/\u00197t)\tA8\u0010\u0005\u0002#s&\u0011!p\u0006\u0002\b\u0005>|G.Z1o\u0011\u0015ah\u00021\u0001:\u0003\u0005y\u0017!\u00049sKB,g\u000eZ3e\u00032d\u0007'F\u0002\u0000\u0003\u000b!b!!\u0001\u0002\b\u0005M\u0001\u0003B\u000e3\u0003\u0007\u00012!NA\u0003\t\u00159tB1\u00019\u0011\u001d\tIa\u0004a\u0001\u0003\u0017\ta\u0001\u001d:fM&D\bCBA\u0007\u0003\u001f\t\u0019!D\u0001\u0016\u0013\r\t\t\"\u0006\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0005\u0007\u0003+y\u0001\u0019A\u0016\u0002\u0003-\fA\"\u00199qK:$W\rZ!mYB*B!a\u0007\u0002\"Q1\u0011QDA\u0012\u0003S\u0001Ba\u0007\u001a\u0002 A\u0019Q'!\t\u0005\u000b]\u0002\"\u0019\u0001\u001d\t\u000f\u0005\u0015\u0002\u00031\u0001\u0002(\u000511/\u001e4gSb\u0004b!!\u0004\u0002\u0010\u0005}\u0001BBA\u000b!\u0001\u00071&\u0001\u0003j_>\u0014G\u0003BA\u0018\u0003\u000f\u0002B!!\r\u0002B9!\u00111GA\u001f\u001d\u0011\t)$a\u000f\u000e\u0005\u0005]\"bAA\u001d3\u00051AH]8pizJ\u0011\u0001G\u0005\u0004\u0003\u007f9\u0012a\u00029bG.\fw-Z\u0005\u0005\u0003\u0007\n)EA\rJ]\u0012,\u0007pT;u\u001f\u001a\u0014u.\u001e8eg\u0016C8-\u001a9uS>t'bAA /!)!&\u0005a\u0001W\u0001"
)
public final class Vector0 {
   public static boolean equals(final Object o) {
      return Vector0$.MODULE$.equals(o);
   }

   public static Vector init() {
      return Vector0$.MODULE$.init();
   }

   public static Vector tail() {
      return Vector0$.MODULE$.tail();
   }

   public static Vector map(final Function1 f) {
      return Vector0$.MODULE$.map(f);
   }

   public static Vector prepended(final Object elem) {
      return Vector0$.MODULE$.prepended(elem);
   }

   public static Vector appended(final Object elem) {
      return Vector0$.MODULE$.appended(elem);
   }

   public static Vector updated(final int index, final Object elem) {
      return Vector0$.MODULE$.updated(index, elem);
   }

   public static Nothing$ apply(final int index) {
      return Vector0$.MODULE$.apply(index);
   }

   public static Vector slice(final int from, final int until) {
      return Vector0$.MODULE$.slice(from, until);
   }

   public static void foreach(final Function1 f) {
      Vector0$.MODULE$.foreach(f);
   }

   public static Object last() {
      return Vector0$.MODULE$.last();
   }

   public static Object head() {
      return Vector0$.MODULE$.head();
   }

   public static Stepper stepper(final StepperShape shape) {
      return Vector0$.MODULE$.stepper(shape);
   }

   public static Vector toVector() {
      return Vector0$.MODULE$.toVector();
   }

   public static int copyToArray(final Object xs, final int start, final int len) {
      return Vector0$.MODULE$.copyToArray(xs, start, len);
   }

   public static Vector dropRight(final int n) {
      return Vector0$.MODULE$.dropRight(n);
   }

   public static Vector takeRight(final int n) {
      return Vector0$.MODULE$.takeRight(n);
   }

   public static Vector drop(final int n) {
      return Vector0$.MODULE$.drop(n);
   }

   public static Vector take(final int n) {
      return Vector0$.MODULE$.take(n);
   }

   public static String className() {
      return Vector0$.MODULE$.className();
   }

   public static Vector appendedAll(final IterableOnce suffix) {
      return Vector0$.MODULE$.appendedAll(suffix);
   }

   public static Vector prependedAll(final IterableOnce prefix) {
      return Vector0$.MODULE$.prependedAll(prefix);
   }

   public static Iterator iterator() {
      return Vector0$.MODULE$.iterator();
   }

   public static int length() {
      return Vector0$.MODULE$.length();
   }

   public static SeqFactory iterableFactory() {
      return Vector0$.MODULE$.iterableFactory();
   }

   public static Object sorted(final Ordering ord) {
      return Vector0$.MODULE$.sorted(ord);
   }

   public static Object patch(final int from, final IterableOnce other, final int replaced) {
      return Vector0$.MODULE$.patch(from, other, replaced);
   }

   public static Object distinctBy(final Function1 f) {
      return Vector0$.MODULE$.distinctBy(f);
   }

   public static Object intersect(final scala.collection.Seq that) {
      return Vector0$.MODULE$.intersect(that);
   }

   public static Object diff(final scala.collection.Seq that) {
      return Vector0$.MODULE$.diff(that);
   }

   public static Object padTo(final int len, final Object elem) {
      return Vector0$.MODULE$.padTo(len, elem);
   }

   public static Object tapEach(final Function1 f) {
      return Vector0$.MODULE$.tapEach(f);
   }

   public static Tuple2 partitionMap(final Function1 f) {
      return Vector0$.MODULE$.partitionMap(f);
   }

   public static Object filterNot(final Function1 pred) {
      return Vector0$.MODULE$.filterNot(pred);
   }

   public static Object filter(final Function1 pred) {
      return Vector0$.MODULE$.filter(pred);
   }

   public static Object scanLeft(final Object z, final Function2 op) {
      return Vector0$.MODULE$.scanLeft(z, op);
   }

   public static Object zipWithIndex() {
      return Vector0$.MODULE$.zipWithIndex();
   }

   public static Object zip(final IterableOnce that) {
      return Vector0$.MODULE$.zip(that);
   }

   public static Object flatten(final Function1 toIterableOnce) {
      return Vector0$.MODULE$.flatten(toIterableOnce);
   }

   public static Object collect(final PartialFunction pf) {
      return Vector0$.MODULE$.collect(pf);
   }

   public static Object flatMap(final Function1 f) {
      return Vector0$.MODULE$.flatMap(f);
   }

   public static Tuple3 unzip3(final Function1 asTriple) {
      return Vector0$.MODULE$.unzip3(asTriple);
   }

   public static Tuple2 unzip(final Function1 asPair) {
      return Vector0$.MODULE$.unzip(asPair);
   }

   public static Tuple2 span(final Function1 p) {
      return Vector0$.MODULE$.span(p);
   }

   public static Tuple2 partition(final Function1 p) {
      return Vector0$.MODULE$.partition(p);
   }

   public static boolean sameElements(final IterableOnce o) {
      return Vector0$.MODULE$.sameElements(o);
   }

   public static boolean canEqual(final Object that) {
      return Vector0$.MODULE$.canEqual(that);
   }

   public static IndexedSeq toIndexedSeq() {
      return Vector0$.MODULE$.toIndexedSeq();
   }

   public static Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return Vector0$.MODULE$.search(elem, from, to, ord);
   }

   public static Searching.SearchResult search(final Object elem, final Ordering ord) {
      return Vector0$.MODULE$.search(elem, ord);
   }

   public static int lengthCompare(final scala.collection.Iterable that) {
      return Vector0$.MODULE$.lengthCompare(that);
   }

   public static int knownSize() {
      return Vector0$.MODULE$.knownSize();
   }

   public static int lengthCompare(final int len) {
      return Vector0$.MODULE$.lengthCompare(len);
   }

   public static Option headOption() {
      return Vector0$.MODULE$.headOption();
   }

   public static Object reverse() {
      return Vector0$.MODULE$.reverse();
   }

   /** @deprecated */
   public static IndexedSeqView view(final int from, final int until) {
      return Vector0$.MODULE$.view(from, until);
   }

   public static IndexedSeqView view() {
      return Vector0$.MODULE$.view();
   }

   public static Object foldRight(final Object z, final Function2 op) {
      return Vector0$.MODULE$.foldRight(z, op);
   }

   public static Iterator reverseIterator() {
      return Vector0$.MODULE$.reverseIterator();
   }

   public static Seq toSeq() {
      return Vector0$.MODULE$.toSeq();
   }

   public static String toString() {
      return Vector0$.MODULE$.toString();
   }

   public static int hashCode() {
      return Vector0$.MODULE$.hashCode();
   }

   public static boolean corresponds(final scala.collection.Seq that, final Function2 p) {
      return Vector0$.MODULE$.corresponds(that, p);
   }

   public static boolean isEmpty() {
      return Vector0$.MODULE$.isEmpty();
   }

   public static IterableOps lengthIs() {
      return Vector0$.MODULE$.lengthIs();
   }

   public static int sizeCompare(final scala.collection.Iterable that) {
      return Vector0$.MODULE$.sizeCompare(that);
   }

   public static int sizeCompare(final int otherSize) {
      return Vector0$.MODULE$.sizeCompare(otherSize);
   }

   public static Range indices() {
      return Vector0$.MODULE$.indices();
   }

   public static Object sortBy(final Function1 f, final Ordering ord) {
      return Vector0$.MODULE$.sortBy(f, ord);
   }

   public static Object sortWith(final Function2 lt) {
      return Vector0$.MODULE$.sortWith(lt);
   }

   public static Iterator combinations(final int n) {
      return Vector0$.MODULE$.combinations(n);
   }

   public static Iterator permutations() {
      return Vector0$.MODULE$.permutations();
   }

   /** @deprecated */
   public static Object reverseMap(final Function1 f) {
      return Vector0$.MODULE$.reverseMap(f);
   }

   public static boolean contains(final Object elem) {
      return Vector0$.MODULE$.contains(elem);
   }

   public static boolean containsSlice(final scala.collection.Seq that) {
      return Vector0$.MODULE$.containsSlice(that);
   }

   public static Option findLast(final Function1 p) {
      return Vector0$.MODULE$.findLast(p);
   }

   public static int lastIndexOfSlice(final scala.collection.Seq that) {
      return Vector0$.MODULE$.lastIndexOfSlice(that);
   }

   public static int lastIndexOfSlice(final scala.collection.Seq that, final int end) {
      return Vector0$.MODULE$.lastIndexOfSlice(that, end);
   }

   public static int indexOfSlice(final scala.collection.Seq that) {
      return Vector0$.MODULE$.indexOfSlice(that);
   }

   public static int indexOfSlice(final scala.collection.Seq that, final int from) {
      return Vector0$.MODULE$.indexOfSlice(that, from);
   }

   public static int lastIndexWhere(final Function1 p) {
      return Vector0$.MODULE$.lastIndexWhere(p);
   }

   public static int lastIndexWhere(final Function1 p, final int end) {
      return Vector0$.MODULE$.lastIndexWhere(p, end);
   }

   public static int lastIndexOf$default$2() {
      return Vector0$.MODULE$.lastIndexOf$default$2();
   }

   public static int lastIndexOf(final Object elem, final int end) {
      return Vector0$.MODULE$.lastIndexOf(elem, end);
   }

   public static int indexOf(final Object elem) {
      return Vector0$.MODULE$.indexOf(elem);
   }

   public static int indexOf(final Object elem, final int from) {
      return Vector0$.MODULE$.indexOf(elem, from);
   }

   public static int indexWhere(final Function1 p) {
      return Vector0$.MODULE$.indexWhere(p);
   }

   public static int indexWhere(final Function1 p, final int from) {
      return Vector0$.MODULE$.indexWhere(p, from);
   }

   /** @deprecated */
   public static int prefixLength(final Function1 p) {
      return Vector0$.MODULE$.prefixLength(p);
   }

   public static int segmentLength(final Function1 p, final int from) {
      return Vector0$.MODULE$.segmentLength(p, from);
   }

   public static int segmentLength(final Function1 p) {
      return Vector0$.MODULE$.segmentLength(p);
   }

   public static boolean isDefinedAt(final int idx) {
      return Vector0$.MODULE$.isDefinedAt(idx);
   }

   public static boolean endsWith(final scala.collection.Iterable that) {
      return Vector0$.MODULE$.endsWith(that);
   }

   public static int startsWith$default$2() {
      return Vector0$.MODULE$.startsWith$default$2();
   }

   public static boolean startsWith(final IterableOnce that, final int offset) {
      return Vector0$.MODULE$.startsWith(that, offset);
   }

   public static Object distinct() {
      return Vector0$.MODULE$.distinct();
   }

   public static int size() {
      return Vector0$.MODULE$.size();
   }

   /** @deprecated */
   public static Object union(final scala.collection.Seq that) {
      return Vector0$.MODULE$.union(that);
   }

   public static Object concat(final IterableOnce suffix) {
      return Vector0$.MODULE$.concat(suffix);
   }

   public static Object $colon$plus$plus(final IterableOnce suffix) {
      return Vector0$.MODULE$.$colon$plus$plus(suffix);
   }

   public static Object $plus$plus$colon(final IterableOnce prefix) {
      return Vector0$.MODULE$.$plus$plus$colon(prefix);
   }

   public static Object $colon$plus(final Object elem) {
      return Vector0$.MODULE$.$colon$plus(elem);
   }

   public static Object $plus$colon(final Object elem) {
      return Vector0$.MODULE$.$plus$colon(elem);
   }

   public static Function1 runWith(final Function1 action) {
      return Vector0$.MODULE$.runWith(action);
   }

   public static Object applyOrElse(final Object x, final Function1 default) {
      return Vector0$.MODULE$.applyOrElse(x, default);
   }

   public static Function1 lift() {
      return Vector0$.MODULE$.lift();
   }

   public static PartialFunction compose(final PartialFunction k) {
      return Vector0$.MODULE$.compose(k);
   }

   public static PartialFunction andThen(final PartialFunction k) {
      return Vector0$.MODULE$.andThen(k);
   }

   public static PartialFunction andThen(final Function1 k) {
      return Vector0$.MODULE$.andThen(k);
   }

   public static PartialFunction orElse(final PartialFunction that) {
      return Vector0$.MODULE$.orElse(that);
   }

   public static PartialFunction elementWise() {
      return Vector0$.MODULE$.elementWise();
   }

   public static Option unapply(final Object a) {
      return Vector0$.MODULE$.unapply(a);
   }

   public static Function1 compose(final Function1 g) {
      return Vector0$.MODULE$.compose(g);
   }

   public static LazyZip2 lazyZip(final scala.collection.Iterable that) {
      return Vector0$.MODULE$.lazyZip(that);
   }

   /** @deprecated */
   public static scala.collection.Iterable seq() {
      return Vector0$.MODULE$.seq();
   }

   /** @deprecated */
   public static scala.collection.Iterable toIterable() {
      return Vector0$.MODULE$.toIterable();
   }

   public static IterableOps empty() {
      return Vector0$.MODULE$.empty();
   }

   public static Iterator inits() {
      return Vector0$.MODULE$.inits();
   }

   public static Iterator tails() {
      return Vector0$.MODULE$.tails();
   }

   public static Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
      return Vector0$.MODULE$.zipAll(that, thisElem, thatElem);
   }

   public static Object $plus$plus(final IterableOnce suffix) {
      return Vector0$.MODULE$.$plus$plus(suffix);
   }

   public static Object scanRight(final Object z, final Function2 op) {
      return Vector0$.MODULE$.scanRight(z, op);
   }

   public static Object scan(final Object z, final Function2 op) {
      return Vector0$.MODULE$.scan(z, op);
   }

   public static Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return Vector0$.MODULE$.groupMapReduce(key, f, reduce);
   }

   public static Map groupMap(final Function1 key, final Function1 f) {
      return Vector0$.MODULE$.groupMap(key, f);
   }

   public static Map groupBy(final Function1 f) {
      return Vector0$.MODULE$.groupBy(f);
   }

   public static Iterator sliding(final int size, final int step) {
      return Vector0$.MODULE$.sliding(size, step);
   }

   public static Iterator sliding(final int size) {
      return Vector0$.MODULE$.sliding(size);
   }

   public static Iterator grouped(final int size) {
      return Vector0$.MODULE$.grouped(size);
   }

   public static Object dropWhile(final Function1 p) {
      return Vector0$.MODULE$.dropWhile(p);
   }

   public static Object takeWhile(final Function1 p) {
      return Vector0$.MODULE$.takeWhile(p);
   }

   public static Tuple2 splitAt(final int n) {
      return Vector0$.MODULE$.splitAt(n);
   }

   public static WithFilter withFilter(final Function1 p) {
      return Vector0$.MODULE$.withFilter(p);
   }

   public static Object transpose(final Function1 asIterable) {
      return Vector0$.MODULE$.transpose(asIterable);
   }

   public static IterableOps sizeIs() {
      return Vector0$.MODULE$.sizeIs();
   }

   public static Option lastOption() {
      return Vector0$.MODULE$.lastOption();
   }

   /** @deprecated */
   public static IterableFactory companion() {
      return Vector0$.MODULE$.companion();
   }

   /** @deprecated */
   public static Object repr() {
      return Vector0$.MODULE$.repr();
   }

   public static boolean isTraversableAgain() {
      return Vector0$.MODULE$.isTraversableAgain();
   }

   /** @deprecated */
   public static scala.collection.Iterable toTraversable() {
      return Vector0$.MODULE$.toTraversable();
   }

   public static Object toArray(final ClassTag evidence$2) {
      return Vector0$.MODULE$.toArray(evidence$2);
   }

   public static Buffer toBuffer() {
      return Vector0$.MODULE$.toBuffer();
   }

   /** @deprecated */
   public static Stream toStream() {
      return Vector0$.MODULE$.toStream();
   }

   public static Set toSet() {
      return Vector0$.MODULE$.toSet();
   }

   public static Map toMap(final $less$colon$less ev) {
      return Vector0$.MODULE$.toMap(ev);
   }

   public static List toList() {
      return Vector0$.MODULE$.toList();
   }

   /** @deprecated */
   public static Iterator toIterator() {
      return Vector0$.MODULE$.toIterator();
   }

   public static Object to(final Factory factory) {
      return Vector0$.MODULE$.to(factory);
   }

   public static StringBuilder addString(final StringBuilder b) {
      return Vector0$.MODULE$.addString(b);
   }

   public static StringBuilder addString(final StringBuilder b, final String sep) {
      return Vector0$.MODULE$.addString(b, sep);
   }

   public static StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return Vector0$.MODULE$.addString(b, start, sep, end);
   }

   public static String mkString() {
      return Vector0$.MODULE$.mkString();
   }

   public static String mkString(final String sep) {
      return Vector0$.MODULE$.mkString(sep);
   }

   public static String mkString(final String start, final String sep, final String end) {
      return Vector0$.MODULE$.mkString(start, sep, end);
   }

   public static boolean corresponds(final IterableOnce that, final Function2 p) {
      return Vector0$.MODULE$.corresponds(that, p);
   }

   /** @deprecated */
   public static Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return Vector0$.MODULE$.aggregate(z, seqop, combop);
   }

   public static Option collectFirst(final PartialFunction pf) {
      return Vector0$.MODULE$.collectFirst(pf);
   }

   public static Option minByOption(final Function1 f, final Ordering ord) {
      return Vector0$.MODULE$.minByOption(f, ord);
   }

   public static Object minBy(final Function1 f, final Ordering ord) {
      return Vector0$.MODULE$.minBy(f, ord);
   }

   public static Option maxByOption(final Function1 f, final Ordering ord) {
      return Vector0$.MODULE$.maxByOption(f, ord);
   }

   public static Object maxBy(final Function1 f, final Ordering ord) {
      return Vector0$.MODULE$.maxBy(f, ord);
   }

   public static Option maxOption(final Ordering ord) {
      return Vector0$.MODULE$.maxOption(ord);
   }

   public static Object max(final Ordering ord) {
      return Vector0$.MODULE$.max(ord);
   }

   public static Option minOption(final Ordering ord) {
      return Vector0$.MODULE$.minOption(ord);
   }

   public static Object min(final Ordering ord) {
      return Vector0$.MODULE$.min(ord);
   }

   public static Object product(final Numeric num) {
      return Vector0$.MODULE$.product(num);
   }

   public static Object sum(final Numeric num) {
      return Vector0$.MODULE$.sum(num);
   }

   public static int copyToArray(final Object xs, final int start) {
      return Vector0$.MODULE$.copyToArray(xs, start);
   }

   public static int copyToArray(final Object xs) {
      return Vector0$.MODULE$.copyToArray(xs);
   }

   /** @deprecated */
   public static void copyToBuffer(final Buffer dest) {
      Vector0$.MODULE$.copyToBuffer(dest);
   }

   public static boolean nonEmpty() {
      return Vector0$.MODULE$.nonEmpty();
   }

   public static Option reduceRightOption(final Function2 op) {
      return Vector0$.MODULE$.reduceRightOption(op);
   }

   public static Option reduceLeftOption(final Function2 op) {
      return Vector0$.MODULE$.reduceLeftOption(op);
   }

   public static Object reduceRight(final Function2 op) {
      return Vector0$.MODULE$.reduceRight(op);
   }

   public static Object reduceLeft(final Function2 op) {
      return Vector0$.MODULE$.reduceLeft(op);
   }

   public static Option reduceOption(final Function2 op) {
      return Vector0$.MODULE$.reduceOption(op);
   }

   public static Object reduce(final Function2 op) {
      return Vector0$.MODULE$.reduce(op);
   }

   public static Object fold(final Object z, final Function2 op) {
      return Vector0$.MODULE$.fold(z, op);
   }

   /** @deprecated */
   public static Object $colon$bslash(final Object z, final Function2 op) {
      return Vector0$.MODULE$.$colon$bslash(z, op);
   }

   /** @deprecated */
   public static Object $div$colon(final Object z, final Function2 op) {
      return Vector0$.MODULE$.$div$colon(z, op);
   }

   public static Object foldLeft(final Object z, final Function2 op) {
      return Vector0$.MODULE$.foldLeft(z, op);
   }

   public static Option find(final Function1 p) {
      return Vector0$.MODULE$.find(p);
   }

   public static int count(final Function1 p) {
      return Vector0$.MODULE$.count(p);
   }

   public static boolean exists(final Function1 p) {
      return Vector0$.MODULE$.exists(p);
   }

   public static boolean forall(final Function1 p) {
      return Vector0$.MODULE$.forall(p);
   }

   /** @deprecated */
   public static boolean hasDefiniteSize() {
      return Vector0$.MODULE$.hasDefiniteSize();
   }
}
