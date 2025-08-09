package scala.reflect.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Stepper;
import scala.collection.StepperShape;
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
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q<Q\u0001F\u000b\t\u0002q1QAH\u000b\t\u0002}AQaI\u0001\u0005\u0002\u0011BQ!J\u0001\u0005\u0002\u0019BQaJ\u0001\u0005\u0002\u0019BQ\u0001K\u0001\u0005\u0002%BQAL\u0001\u0005\u0002%BQaL\u0001\u0005\u0002ABQ\u0001O\u0001\u0005\u0002eBQ!P\u0001\u0005\u0002yBQAQ\u0001\u0005ByBQaQ\u0001\u0005\u0002\u0011CQ\u0001T\u0001\u0005\u00025CQ!U\u0001\u0005\u0002ICQAY\u0001\u0005\u0002\rDQ\u0001V\u0001\u0005\u0002\u0019DQaZ\u0001\u0005\u0002!DQ\u0001\\\u0001\u0005\u0002\u0019DQ!\\\u0001\u0005B9DQ!^\u0001\u0005BY\faBT8BEN$(/Y2u\r&dWM\u0003\u0002\u0017/\u0005\u0011\u0011n\u001c\u0006\u00031e\tqA]3gY\u0016\u001cGOC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!H\u0001\u000e\u0003U\u0011aBT8BEN$(/Y2u\r&dWm\u0005\u0002\u0002AA\u0011Q$I\u0005\u0003EU\u0011A\"\u00112tiJ\f7\r\u001e$jY\u0016\fa\u0001P5oSRtD#\u0001\u000f\u0002\u0011\u0005\u00147o\u001c7vi\u0016,\u0012\u0001I\u0001\nG>tG/Y5oKJ\faa\u0019:fCR,G#\u0001\u0016\u0011\u0005-bS\"A\r\n\u00055J\"\u0001B+oSR\fa\u0001Z3mKR,\u0017\u0001\u00024jY\u0016,\u0012!\r\t\u0003eYj\u0011a\r\u0006\u0003-QR\u0011!N\u0001\u0005U\u00064\u0018-\u0003\u00028g\t!a)\u001b7f\u0003\u0015Ig\u000e];u+\u0005Q\u0004C\u0001\u001a<\u0013\ta4GA\u0006J]B,Ho\u0015;sK\u0006l\u0017aC5t\t&\u0014Xm\u0019;pef,\u0012a\u0010\t\u0003W\u0001K!!Q\r\u0003\u000f\t{w\u000e\\3b]\u0006I\u0011n\u001d,jeR,\u0018\r\\\u0001\tSR,'/\u0019;peV\tQ\tE\u0002G\u0013\u0002r!aK$\n\u0005!K\u0012a\u00029bG.\fw-Z\u0005\u0003\u0015.\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003\u0011f\tA\u0002\\1ti6{G-\u001b4jK\u0012,\u0012A\u0014\t\u0003W=K!\u0001U\r\u0003\t1{gnZ\u0001\u000bY>|7.\u001e9OC6,Gc\u0001\u0011TA\")A+\u0004a\u0001+\u0006!a.Y7f!\t1VL\u0004\u0002X7B\u0011\u0001,G\u0007\u00023*\u0011!lG\u0001\u0007yI|w\u000e\u001e \n\u0005qK\u0012A\u0002)sK\u0012,g-\u0003\u0002_?\n11\u000b\u001e:j]\u001eT!\u0001X\r\t\u000b\u0005l\u0001\u0019A \u0002\u0013\u0011L'/Z2u_JL\u0018a\u00057p_.,\bOT1nKVs7\r[3dW\u0016$Gc\u0001\u0011eK\")AK\u0004a\u0001+\")\u0011M\u0004a\u0001\u007fU\tQ+\u0001\u0004pkR\u0004X\u000f^\u000b\u0002SB\u0011!G[\u0005\u0003WN\u0012AbT;uaV$8\u000b\u001e:fC6\fA\u0001]1uQ\u0006YAo\u001c\"zi\u0016\f%O]1z+\u0005y\u0007cA\u0016qe&\u0011\u0011/\u0007\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003WML!\u0001^\r\u0003\t\tKH/Z\u0001\ti>\u001cFO]5oOR\tq\u000f\u0005\u0002yw6\t\u0011P\u0003\u0002{i\u0005!A.\u00198h\u0013\tq\u0016\u0010"
)
public final class NoAbstractFile {
   public static String toString() {
      return NoAbstractFile$.MODULE$.toString();
   }

   public static byte[] toByteArray() {
      return NoAbstractFile$.MODULE$.toByteArray();
   }

   public static String path() {
      return NoAbstractFile$.MODULE$.path();
   }

   public static OutputStream output() {
      return NoAbstractFile$.MODULE$.output();
   }

   public static String name() {
      return NoAbstractFile$.MODULE$.name();
   }

   public static AbstractFile lookupNameUnchecked(final String name, final boolean directory) {
      return NoAbstractFile$.MODULE$.lookupNameUnchecked(name, directory);
   }

   public static AbstractFile lookupName(final String name, final boolean directory) {
      return NoAbstractFile$.MODULE$.lookupName(name, directory);
   }

   public static long lastModified() {
      return NoAbstractFile$.MODULE$.lastModified();
   }

   public static Iterator iterator() {
      return NoAbstractFile$.MODULE$.iterator();
   }

   public static boolean isVirtual() {
      return NoAbstractFile$.MODULE$.isVirtual();
   }

   public static boolean isDirectory() {
      return NoAbstractFile$.MODULE$.isDirectory();
   }

   public static InputStream input() {
      return NoAbstractFile$.MODULE$.input();
   }

   public static java.io.File file() {
      return NoAbstractFile$.MODULE$.file();
   }

   public static void delete() {
      NoAbstractFile$.MODULE$.delete();
   }

   public static void create() {
      NoAbstractFile$.MODULE$.create();
   }

   public static AbstractFile container() {
      return NoAbstractFile$.MODULE$.container();
   }

   public static AbstractFile absolute() {
      return NoAbstractFile$.MODULE$.absolute();
   }

   public static AbstractFile subdirectoryNamed(final String name) {
      return NoAbstractFile$.MODULE$.subdirectoryNamed(name);
   }

   public static AbstractFile fileNamed(final String name) {
      return NoAbstractFile$.MODULE$.fileNamed(name);
   }

   public static AbstractFile lookupPathUnchecked(final String path, final boolean directory) {
      return NoAbstractFile$.MODULE$.lookupPathUnchecked(path, directory);
   }

   public static boolean isEmpty() {
      return NoAbstractFile$.MODULE$.isEmpty();
   }

   public static byte[] unsafeToByteArray() {
      return NoAbstractFile$.MODULE$.unsafeToByteArray();
   }

   public static ByteBuffer toByteBuffer() {
      return NoAbstractFile$.MODULE$.toByteBuffer();
   }

   public static char[] toCharArray() throws IOException {
      return NoAbstractFile$.MODULE$.toCharArray();
   }

   public static URL toURL() {
      return NoAbstractFile$.MODULE$.toURL();
   }

   public static Option sizeOption() {
      return NoAbstractFile$.MODULE$.sizeOption();
   }

   public static BufferedOutputStream bufferedOutput() {
      return NoAbstractFile$.MODULE$.bufferedOutput();
   }

   public static boolean isClassContainer() {
      return NoAbstractFile$.MODULE$.isClassContainer();
   }

   public static boolean exists() {
      return NoAbstractFile$.MODULE$.exists();
   }

   public static Option underlyingSource() {
      return NoAbstractFile$.MODULE$.underlyingSource();
   }

   public static boolean hasExtension(final String other) {
      return NoAbstractFile$.MODULE$.hasExtension(other);
   }

   public static String canonicalPath() {
      return NoAbstractFile$.MODULE$.canonicalPath();
   }

   public static LazyZip2 lazyZip(final Iterable that) {
      return NoAbstractFile$.MODULE$.lazyZip(that);
   }

   /** @deprecated */
   public static Iterable seq() {
      return NoAbstractFile$.MODULE$.seq();
   }

   public static IterableFactory iterableFactory() {
      return NoAbstractFile$.MODULE$.iterableFactory();
   }

   /** @deprecated */
   public static Iterable toIterable() {
      return NoAbstractFile$.MODULE$.toIterable();
   }

   public static IterableOps empty() {
      return NoAbstractFile$.MODULE$.empty();
   }

   /** @deprecated */
   public static Object $plus$plus$colon(final IterableOnce that) {
      return NoAbstractFile$.MODULE$.$plus$plus$colon(that);
   }

   public static Object tapEach(final Function1 f) {
      return NoAbstractFile$.MODULE$.tapEach(f);
   }

   public static Iterator inits() {
      return NoAbstractFile$.MODULE$.inits();
   }

   public static Iterator tails() {
      return NoAbstractFile$.MODULE$.tails();
   }

   public static Tuple3 unzip3(final Function1 asTriple) {
      return NoAbstractFile$.MODULE$.unzip3(asTriple);
   }

   public static Tuple2 unzip(final Function1 asPair) {
      return NoAbstractFile$.MODULE$.unzip(asPair);
   }

   public static Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return NoAbstractFile$.MODULE$.zipAll(that, thisElem, thatElem);
   }

   public static Object zipWithIndex() {
      return NoAbstractFile$.MODULE$.zipWithIndex();
   }

   public static Object zip(final IterableOnce that) {
      return NoAbstractFile$.MODULE$.zip(that);
   }

   public static Object $plus$plus(final IterableOnce suffix) {
      return NoAbstractFile$.MODULE$.$plus$plus(suffix);
   }

   public static Object concat(final IterableOnce suffix) {
      return NoAbstractFile$.MODULE$.concat(suffix);
   }

   public static Tuple2 partitionMap(final Function1 f) {
      return NoAbstractFile$.MODULE$.partitionMap(f);
   }

   public static Object collect(final PartialFunction pf) {
      return NoAbstractFile$.MODULE$.collect(pf);
   }

   public static Object flatten(final Function1 asIterable) {
      return NoAbstractFile$.MODULE$.flatten(asIterable);
   }

   public static Object flatMap(final Function1 f) {
      return NoAbstractFile$.MODULE$.flatMap(f);
   }

   public static Object map(final Function1 f) {
      return NoAbstractFile$.MODULE$.map(f);
   }

   public static Object scanRight(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.scanRight(z, op);
   }

   public static Object scanLeft(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.scanLeft(z, op);
   }

   public static Object scan(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.scan(z, op);
   }

   public static Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return NoAbstractFile$.MODULE$.groupMapReduce(key, f, reduce);
   }

   public static Map groupMap(final Function1 key, final Function1 f) {
      return NoAbstractFile$.MODULE$.groupMap(key, f);
   }

   public static Map groupBy(final Function1 f) {
      return NoAbstractFile$.MODULE$.groupBy(f);
   }

   public static Object slice(final int from, final int until) {
      return NoAbstractFile$.MODULE$.slice(from, until);
   }

   public static Object init() {
      return NoAbstractFile$.MODULE$.init();
   }

   public static Object tail() {
      return NoAbstractFile$.MODULE$.tail();
   }

   public static Iterator sliding(final int size, final int step) {
      return NoAbstractFile$.MODULE$.sliding(size, step);
   }

   public static Iterator sliding(final int size) {
      return NoAbstractFile$.MODULE$.sliding(size);
   }

   public static Iterator grouped(final int size) {
      return NoAbstractFile$.MODULE$.grouped(size);
   }

   public static Object dropWhile(final Function1 p) {
      return NoAbstractFile$.MODULE$.dropWhile(p);
   }

   public static Object dropRight(final int n) {
      return NoAbstractFile$.MODULE$.dropRight(n);
   }

   public static Object drop(final int n) {
      return NoAbstractFile$.MODULE$.drop(n);
   }

   public static Tuple2 span(final Function1 p) {
      return NoAbstractFile$.MODULE$.span(p);
   }

   public static Object takeWhile(final Function1 p) {
      return NoAbstractFile$.MODULE$.takeWhile(p);
   }

   public static Object takeRight(final int n) {
      return NoAbstractFile$.MODULE$.takeRight(n);
   }

   public static Object take(final int n) {
      return NoAbstractFile$.MODULE$.take(n);
   }

   public static Tuple2 splitAt(final int n) {
      return NoAbstractFile$.MODULE$.splitAt(n);
   }

   public static Tuple2 partition(final Function1 p) {
      return NoAbstractFile$.MODULE$.partition(p);
   }

   public static WithFilter withFilter(final Function1 p) {
      return NoAbstractFile$.MODULE$.withFilter(p);
   }

   public static Object filterNot(final Function1 pred) {
      return NoAbstractFile$.MODULE$.filterNot(pred);
   }

   public static Object filter(final Function1 pred) {
      return NoAbstractFile$.MODULE$.filter(pred);
   }

   public static Object transpose(final Function1 asIterable) {
      return NoAbstractFile$.MODULE$.transpose(asIterable);
   }

   /** @deprecated */
   public static View view(final int from, final int until) {
      return NoAbstractFile$.MODULE$.view(from, until);
   }

   public static int sizeCompare(final Iterable that) {
      return NoAbstractFile$.MODULE$.sizeCompare(that);
   }

   public static IterableOps sizeIs() {
      return NoAbstractFile$.MODULE$.sizeIs();
   }

   public static int sizeCompare(final int otherSize) {
      return NoAbstractFile$.MODULE$.sizeCompare(otherSize);
   }

   public static View view() {
      return NoAbstractFile$.MODULE$.view();
   }

   public static Option lastOption() {
      return NoAbstractFile$.MODULE$.lastOption();
   }

   public static Object last() {
      return NoAbstractFile$.MODULE$.last();
   }

   public static Option headOption() {
      return NoAbstractFile$.MODULE$.headOption();
   }

   public static Object head() {
      return NoAbstractFile$.MODULE$.head();
   }

   /** @deprecated */
   public static IterableFactory companion() {
      return NoAbstractFile$.MODULE$.companion();
   }

   /** @deprecated */
   public static Object repr() {
      return NoAbstractFile$.MODULE$.repr();
   }

   public static boolean isTraversableAgain() {
      return NoAbstractFile$.MODULE$.isTraversableAgain();
   }

   /** @deprecated */
   public static Iterable toTraversable() {
      return NoAbstractFile$.MODULE$.toTraversable();
   }

   public static Object toArray(final ClassTag evidence$2) {
      return NoAbstractFile$.MODULE$.toArray(evidence$2);
   }

   public static Buffer toBuffer() {
      return NoAbstractFile$.MODULE$.toBuffer();
   }

   /** @deprecated */
   public static Stream toStream() {
      return NoAbstractFile$.MODULE$.toStream();
   }

   public static IndexedSeq toIndexedSeq() {
      return NoAbstractFile$.MODULE$.toIndexedSeq();
   }

   public static Seq toSeq() {
      return NoAbstractFile$.MODULE$.toSeq();
   }

   public static Set toSet() {
      return NoAbstractFile$.MODULE$.toSet();
   }

   public static Map toMap(final .less.colon.less ev) {
      return NoAbstractFile$.MODULE$.toMap(ev);
   }

   public static Vector toVector() {
      return NoAbstractFile$.MODULE$.toVector();
   }

   public static List toList() {
      return NoAbstractFile$.MODULE$.toList();
   }

   /** @deprecated */
   public static Iterator toIterator() {
      return NoAbstractFile$.MODULE$.toIterator();
   }

   public static Object to(final Factory factory) {
      return NoAbstractFile$.MODULE$.to(factory);
   }

   public static StringBuilder addString(final StringBuilder b) {
      return NoAbstractFile$.MODULE$.addString(b);
   }

   public static StringBuilder addString(final StringBuilder b, final String sep) {
      return NoAbstractFile$.MODULE$.addString(b, sep);
   }

   public static StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return NoAbstractFile$.MODULE$.addString(b, start, sep, end);
   }

   public static String mkString() {
      return NoAbstractFile$.MODULE$.mkString();
   }

   public static String mkString(final String sep) {
      return NoAbstractFile$.MODULE$.mkString(sep);
   }

   public static String mkString(final String start, final String sep, final String end) {
      return NoAbstractFile$.MODULE$.mkString(start, sep, end);
   }

   public static boolean corresponds(final IterableOnce that, final Function2 p) {
      return NoAbstractFile$.MODULE$.corresponds(that, p);
   }

   /** @deprecated */
   public static Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return NoAbstractFile$.MODULE$.aggregate(z, seqop, combop);
   }

   public static Option collectFirst(final PartialFunction pf) {
      return NoAbstractFile$.MODULE$.collectFirst(pf);
   }

   public static Option minByOption(final Function1 f, final Ordering ord) {
      return NoAbstractFile$.MODULE$.minByOption(f, ord);
   }

   public static Object minBy(final Function1 f, final Ordering ord) {
      return NoAbstractFile$.MODULE$.minBy(f, ord);
   }

   public static Option maxByOption(final Function1 f, final Ordering ord) {
      return NoAbstractFile$.MODULE$.maxByOption(f, ord);
   }

   public static Object maxBy(final Function1 f, final Ordering ord) {
      return NoAbstractFile$.MODULE$.maxBy(f, ord);
   }

   public static Option maxOption(final Ordering ord) {
      return NoAbstractFile$.MODULE$.maxOption(ord);
   }

   public static Object max(final Ordering ord) {
      return NoAbstractFile$.MODULE$.max(ord);
   }

   public static Option minOption(final Ordering ord) {
      return NoAbstractFile$.MODULE$.minOption(ord);
   }

   public static Object min(final Ordering ord) {
      return NoAbstractFile$.MODULE$.min(ord);
   }

   public static Object product(final Numeric num) {
      return NoAbstractFile$.MODULE$.product(num);
   }

   public static Object sum(final Numeric num) {
      return NoAbstractFile$.MODULE$.sum(num);
   }

   public static int copyToArray(final Object xs, final int start, final int len) {
      return NoAbstractFile$.MODULE$.copyToArray(xs, start, len);
   }

   public static int copyToArray(final Object xs, final int start) {
      return NoAbstractFile$.MODULE$.copyToArray(xs, start);
   }

   public static int copyToArray(final Object xs) {
      return NoAbstractFile$.MODULE$.copyToArray(xs);
   }

   /** @deprecated */
   public static void copyToBuffer(final Buffer dest) {
      NoAbstractFile$.MODULE$.copyToBuffer(dest);
   }

   public static int size() {
      return NoAbstractFile$.MODULE$.size();
   }

   public static boolean nonEmpty() {
      return NoAbstractFile$.MODULE$.nonEmpty();
   }

   public static Option reduceRightOption(final Function2 op) {
      return NoAbstractFile$.MODULE$.reduceRightOption(op);
   }

   public static Option reduceLeftOption(final Function2 op) {
      return NoAbstractFile$.MODULE$.reduceLeftOption(op);
   }

   public static Object reduceRight(final Function2 op) {
      return NoAbstractFile$.MODULE$.reduceRight(op);
   }

   public static Object reduceLeft(final Function2 op) {
      return NoAbstractFile$.MODULE$.reduceLeft(op);
   }

   public static Option reduceOption(final Function2 op) {
      return NoAbstractFile$.MODULE$.reduceOption(op);
   }

   public static Object reduce(final Function2 op) {
      return NoAbstractFile$.MODULE$.reduce(op);
   }

   public static Object fold(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.fold(z, op);
   }

   /** @deprecated */
   public static Object $colon$bslash(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.$colon$bslash(z, op);
   }

   /** @deprecated */
   public static Object $div$colon(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.$div$colon(z, op);
   }

   public static Object foldRight(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.foldRight(z, op);
   }

   public static Object foldLeft(final Object z, final Function2 op) {
      return NoAbstractFile$.MODULE$.foldLeft(z, op);
   }

   public static Option find(final Function1 p) {
      return NoAbstractFile$.MODULE$.find(p);
   }

   public static int count(final Function1 p) {
      return NoAbstractFile$.MODULE$.count(p);
   }

   public static boolean exists(final Function1 p) {
      return NoAbstractFile$.MODULE$.exists(p);
   }

   public static boolean forall(final Function1 p) {
      return NoAbstractFile$.MODULE$.forall(p);
   }

   public static void foreach(final Function1 f) {
      NoAbstractFile$.MODULE$.foreach(f);
   }

   /** @deprecated */
   public static boolean hasDefiniteSize() {
      return NoAbstractFile$.MODULE$.hasDefiniteSize();
   }

   public static int knownSize() {
      return NoAbstractFile$.MODULE$.knownSize();
   }

   public static Stepper stepper(final StepperShape shape) {
      return NoAbstractFile$.MODULE$.stepper(shape);
   }
}
