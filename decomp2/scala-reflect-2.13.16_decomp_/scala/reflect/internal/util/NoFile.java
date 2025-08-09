package scala.reflect.internal.util;

import java.io.BufferedOutputStream;
import java.io.File;
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
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.VirtualFile;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005e9Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQaF\u0001\u0005\u0002a\taAT8GS2,'BA\u0003\u0007\u0003\u0011)H/\u001b7\u000b\u0005\u001dA\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005%Q\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0017\u0005)1oY1mC\u000e\u0001\u0001C\u0001\b\u0002\u001b\u0005!!A\u0002(p\r&dWm\u0005\u0002\u0002#A\u0011!#F\u0007\u0002')\u0011A\u0003C\u0001\u0003S>L!AF\n\u0003\u0017YK'\u000f^;bY\u001aKG.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\u0001"
)
public final class NoFile {
   public static Nothing lookupNameUnchecked(final String name, final boolean directory) {
      return NoFile$.MODULE$.lookupNameUnchecked(name, directory);
   }

   public static AbstractFile lookupName(final String name, final boolean directory) {
      return NoFile$.MODULE$.lookupName(name, directory);
   }

   public static void delete() {
      NoFile$.MODULE$.delete();
   }

   public static void create() {
      NoFile$.MODULE$.create();
   }

   public static Iterator iterator() {
      return NoFile$.MODULE$.iterator();
   }

   public static long lastModified() {
      return NoFile$.MODULE$.lastModified();
   }

   public static boolean isVirtual() {
      return NoFile$.MODULE$.isVirtual();
   }

   public static boolean isDirectory() {
      return NoFile$.MODULE$.isDirectory();
   }

   public static AbstractFile container() {
      return NoFile$.MODULE$.container();
   }

   public static byte[] unsafeToByteArray() {
      return NoFile$.MODULE$.unsafeToByteArray();
   }

   public static OutputStream output() {
      return NoFile$.MODULE$.output();
   }

   public static InputStream input() {
      return NoFile$.MODULE$.input();
   }

   public static Option sizeOption() {
      return NoFile$.MODULE$.sizeOption();
   }

   public static File file() {
      return NoFile$.MODULE$.file();
   }

   public static VirtualFile absolute() {
      return NoFile$.MODULE$.absolute();
   }

   public static boolean equals(final Object that) {
      return NoFile$.MODULE$.equals(that);
   }

   public static int hashCode() {
      return NoFile$.MODULE$.hashCode();
   }

   public static String path() {
      return NoFile$.MODULE$.path();
   }

   public static String name() {
      return NoFile$.MODULE$.name();
   }

   public static String toString() {
      return NoFile$.MODULE$.toString();
   }

   public static AbstractFile subdirectoryNamed(final String name) {
      return NoFile$.MODULE$.subdirectoryNamed(name);
   }

   public static AbstractFile fileNamed(final String name) {
      return NoFile$.MODULE$.fileNamed(name);
   }

   public static AbstractFile lookupPathUnchecked(final String path, final boolean directory) {
      return NoFile$.MODULE$.lookupPathUnchecked(path, directory);
   }

   public static boolean isEmpty() {
      return NoFile$.MODULE$.isEmpty();
   }

   public static ByteBuffer toByteBuffer() {
      return NoFile$.MODULE$.toByteBuffer();
   }

   public static byte[] toByteArray() throws IOException {
      return NoFile$.MODULE$.toByteArray();
   }

   public static char[] toCharArray() throws IOException {
      return NoFile$.MODULE$.toCharArray();
   }

   public static URL toURL() {
      return NoFile$.MODULE$.toURL();
   }

   public static BufferedOutputStream bufferedOutput() {
      return NoFile$.MODULE$.bufferedOutput();
   }

   public static boolean isClassContainer() {
      return NoFile$.MODULE$.isClassContainer();
   }

   public static boolean exists() {
      return NoFile$.MODULE$.exists();
   }

   public static Option underlyingSource() {
      return NoFile$.MODULE$.underlyingSource();
   }

   public static boolean hasExtension(final String other) {
      return NoFile$.MODULE$.hasExtension(other);
   }

   public static String canonicalPath() {
      return NoFile$.MODULE$.canonicalPath();
   }

   public static LazyZip2 lazyZip(final Iterable that) {
      return NoFile$.MODULE$.lazyZip(that);
   }

   /** @deprecated */
   public static Iterable seq() {
      return NoFile$.MODULE$.seq();
   }

   public static IterableFactory iterableFactory() {
      return NoFile$.MODULE$.iterableFactory();
   }

   /** @deprecated */
   public static Iterable toIterable() {
      return NoFile$.MODULE$.toIterable();
   }

   public static IterableOps empty() {
      return NoFile$.MODULE$.empty();
   }

   /** @deprecated */
   public static Object $plus$plus$colon(final IterableOnce that) {
      return NoFile$.MODULE$.$plus$plus$colon(that);
   }

   public static Object tapEach(final Function1 f) {
      return NoFile$.MODULE$.tapEach(f);
   }

   public static Iterator inits() {
      return NoFile$.MODULE$.inits();
   }

   public static Iterator tails() {
      return NoFile$.MODULE$.tails();
   }

   public static Tuple3 unzip3(final Function1 asTriple) {
      return NoFile$.MODULE$.unzip3(asTriple);
   }

   public static Tuple2 unzip(final Function1 asPair) {
      return NoFile$.MODULE$.unzip(asPair);
   }

   public static Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return NoFile$.MODULE$.zipAll(that, thisElem, thatElem);
   }

   public static Object zipWithIndex() {
      return NoFile$.MODULE$.zipWithIndex();
   }

   public static Object zip(final IterableOnce that) {
      return NoFile$.MODULE$.zip(that);
   }

   public static Object $plus$plus(final IterableOnce suffix) {
      return NoFile$.MODULE$.$plus$plus(suffix);
   }

   public static Object concat(final IterableOnce suffix) {
      return NoFile$.MODULE$.concat(suffix);
   }

   public static Tuple2 partitionMap(final Function1 f) {
      return NoFile$.MODULE$.partitionMap(f);
   }

   public static Object collect(final PartialFunction pf) {
      return NoFile$.MODULE$.collect(pf);
   }

   public static Object flatten(final Function1 asIterable) {
      return NoFile$.MODULE$.flatten(asIterable);
   }

   public static Object flatMap(final Function1 f) {
      return NoFile$.MODULE$.flatMap(f);
   }

   public static Object map(final Function1 f) {
      return NoFile$.MODULE$.map(f);
   }

   public static Object scanRight(final Object z, final Function2 op) {
      return NoFile$.MODULE$.scanRight(z, op);
   }

   public static Object scanLeft(final Object z, final Function2 op) {
      return NoFile$.MODULE$.scanLeft(z, op);
   }

   public static Object scan(final Object z, final Function2 op) {
      return NoFile$.MODULE$.scan(z, op);
   }

   public static Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return NoFile$.MODULE$.groupMapReduce(key, f, reduce);
   }

   public static Map groupMap(final Function1 key, final Function1 f) {
      return NoFile$.MODULE$.groupMap(key, f);
   }

   public static Map groupBy(final Function1 f) {
      return NoFile$.MODULE$.groupBy(f);
   }

   public static Object slice(final int from, final int until) {
      return NoFile$.MODULE$.slice(from, until);
   }

   public static Object init() {
      return NoFile$.MODULE$.init();
   }

   public static Object tail() {
      return NoFile$.MODULE$.tail();
   }

   public static Iterator sliding(final int size, final int step) {
      return NoFile$.MODULE$.sliding(size, step);
   }

   public static Iterator sliding(final int size) {
      return NoFile$.MODULE$.sliding(size);
   }

   public static Iterator grouped(final int size) {
      return NoFile$.MODULE$.grouped(size);
   }

   public static Object dropWhile(final Function1 p) {
      return NoFile$.MODULE$.dropWhile(p);
   }

   public static Object dropRight(final int n) {
      return NoFile$.MODULE$.dropRight(n);
   }

   public static Object drop(final int n) {
      return NoFile$.MODULE$.drop(n);
   }

   public static Tuple2 span(final Function1 p) {
      return NoFile$.MODULE$.span(p);
   }

   public static Object takeWhile(final Function1 p) {
      return NoFile$.MODULE$.takeWhile(p);
   }

   public static Object takeRight(final int n) {
      return NoFile$.MODULE$.takeRight(n);
   }

   public static Object take(final int n) {
      return NoFile$.MODULE$.take(n);
   }

   public static Tuple2 splitAt(final int n) {
      return NoFile$.MODULE$.splitAt(n);
   }

   public static Tuple2 partition(final Function1 p) {
      return NoFile$.MODULE$.partition(p);
   }

   public static WithFilter withFilter(final Function1 p) {
      return NoFile$.MODULE$.withFilter(p);
   }

   public static Object filterNot(final Function1 pred) {
      return NoFile$.MODULE$.filterNot(pred);
   }

   public static Object filter(final Function1 pred) {
      return NoFile$.MODULE$.filter(pred);
   }

   public static Object transpose(final Function1 asIterable) {
      return NoFile$.MODULE$.transpose(asIterable);
   }

   /** @deprecated */
   public static View view(final int from, final int until) {
      return NoFile$.MODULE$.view(from, until);
   }

   public static int sizeCompare(final Iterable that) {
      return NoFile$.MODULE$.sizeCompare(that);
   }

   public static IterableOps sizeIs() {
      return NoFile$.MODULE$.sizeIs();
   }

   public static int sizeCompare(final int otherSize) {
      return NoFile$.MODULE$.sizeCompare(otherSize);
   }

   public static View view() {
      return NoFile$.MODULE$.view();
   }

   public static Option lastOption() {
      return NoFile$.MODULE$.lastOption();
   }

   public static Object last() {
      return NoFile$.MODULE$.last();
   }

   public static Option headOption() {
      return NoFile$.MODULE$.headOption();
   }

   public static Object head() {
      return NoFile$.MODULE$.head();
   }

   /** @deprecated */
   public static IterableFactory companion() {
      return NoFile$.MODULE$.companion();
   }

   /** @deprecated */
   public static Object repr() {
      return NoFile$.MODULE$.repr();
   }

   public static boolean isTraversableAgain() {
      return NoFile$.MODULE$.isTraversableAgain();
   }

   /** @deprecated */
   public static Iterable toTraversable() {
      return NoFile$.MODULE$.toTraversable();
   }

   public static Object toArray(final ClassTag evidence$2) {
      return NoFile$.MODULE$.toArray(evidence$2);
   }

   public static Buffer toBuffer() {
      return NoFile$.MODULE$.toBuffer();
   }

   /** @deprecated */
   public static Stream toStream() {
      return NoFile$.MODULE$.toStream();
   }

   public static IndexedSeq toIndexedSeq() {
      return NoFile$.MODULE$.toIndexedSeq();
   }

   public static Seq toSeq() {
      return NoFile$.MODULE$.toSeq();
   }

   public static scala.collection.immutable.Set toSet() {
      return NoFile$.MODULE$.toSet();
   }

   public static Map toMap(final .less.colon.less ev) {
      return NoFile$.MODULE$.toMap(ev);
   }

   public static Vector toVector() {
      return NoFile$.MODULE$.toVector();
   }

   public static List toList() {
      return NoFile$.MODULE$.toList();
   }

   /** @deprecated */
   public static Iterator toIterator() {
      return NoFile$.MODULE$.toIterator();
   }

   public static Object to(final Factory factory) {
      return NoFile$.MODULE$.to(factory);
   }

   public static StringBuilder addString(final StringBuilder b) {
      return NoFile$.MODULE$.addString(b);
   }

   public static StringBuilder addString(final StringBuilder b, final String sep) {
      return NoFile$.MODULE$.addString(b, sep);
   }

   public static StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return NoFile$.MODULE$.addString(b, start, sep, end);
   }

   public static String mkString() {
      return NoFile$.MODULE$.mkString();
   }

   public static String mkString(final String sep) {
      return NoFile$.MODULE$.mkString(sep);
   }

   public static String mkString(final String start, final String sep, final String end) {
      return NoFile$.MODULE$.mkString(start, sep, end);
   }

   public static boolean corresponds(final IterableOnce that, final Function2 p) {
      return NoFile$.MODULE$.corresponds(that, p);
   }

   /** @deprecated */
   public static Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return NoFile$.MODULE$.aggregate(z, seqop, combop);
   }

   public static Option collectFirst(final PartialFunction pf) {
      return NoFile$.MODULE$.collectFirst(pf);
   }

   public static Option minByOption(final Function1 f, final Ordering ord) {
      return NoFile$.MODULE$.minByOption(f, ord);
   }

   public static Object minBy(final Function1 f, final Ordering ord) {
      return NoFile$.MODULE$.minBy(f, ord);
   }

   public static Option maxByOption(final Function1 f, final Ordering ord) {
      return NoFile$.MODULE$.maxByOption(f, ord);
   }

   public static Object maxBy(final Function1 f, final Ordering ord) {
      return NoFile$.MODULE$.maxBy(f, ord);
   }

   public static Option maxOption(final Ordering ord) {
      return NoFile$.MODULE$.maxOption(ord);
   }

   public static Object max(final Ordering ord) {
      return NoFile$.MODULE$.max(ord);
   }

   public static Option minOption(final Ordering ord) {
      return NoFile$.MODULE$.minOption(ord);
   }

   public static Object min(final Ordering ord) {
      return NoFile$.MODULE$.min(ord);
   }

   public static Object product(final Numeric num) {
      return NoFile$.MODULE$.product(num);
   }

   public static Object sum(final Numeric num) {
      return NoFile$.MODULE$.sum(num);
   }

   public static int copyToArray(final Object xs, final int start, final int len) {
      return NoFile$.MODULE$.copyToArray(xs, start, len);
   }

   public static int copyToArray(final Object xs, final int start) {
      return NoFile$.MODULE$.copyToArray(xs, start);
   }

   public static int copyToArray(final Object xs) {
      return NoFile$.MODULE$.copyToArray(xs);
   }

   /** @deprecated */
   public static void copyToBuffer(final Buffer dest) {
      NoFile$.MODULE$.copyToBuffer(dest);
   }

   public static int size() {
      return NoFile$.MODULE$.size();
   }

   public static boolean nonEmpty() {
      return NoFile$.MODULE$.nonEmpty();
   }

   public static Option reduceRightOption(final Function2 op) {
      return NoFile$.MODULE$.reduceRightOption(op);
   }

   public static Option reduceLeftOption(final Function2 op) {
      return NoFile$.MODULE$.reduceLeftOption(op);
   }

   public static Object reduceRight(final Function2 op) {
      return NoFile$.MODULE$.reduceRight(op);
   }

   public static Object reduceLeft(final Function2 op) {
      return NoFile$.MODULE$.reduceLeft(op);
   }

   public static Option reduceOption(final Function2 op) {
      return NoFile$.MODULE$.reduceOption(op);
   }

   public static Object reduce(final Function2 op) {
      return NoFile$.MODULE$.reduce(op);
   }

   public static Object fold(final Object z, final Function2 op) {
      return NoFile$.MODULE$.fold(z, op);
   }

   /** @deprecated */
   public static Object $colon$bslash(final Object z, final Function2 op) {
      return NoFile$.MODULE$.$colon$bslash(z, op);
   }

   /** @deprecated */
   public static Object $div$colon(final Object z, final Function2 op) {
      return NoFile$.MODULE$.$div$colon(z, op);
   }

   public static Object foldRight(final Object z, final Function2 op) {
      return NoFile$.MODULE$.foldRight(z, op);
   }

   public static Object foldLeft(final Object z, final Function2 op) {
      return NoFile$.MODULE$.foldLeft(z, op);
   }

   public static Option find(final Function1 p) {
      return NoFile$.MODULE$.find(p);
   }

   public static int count(final Function1 p) {
      return NoFile$.MODULE$.count(p);
   }

   public static boolean exists(final Function1 p) {
      return NoFile$.MODULE$.exists(p);
   }

   public static boolean forall(final Function1 p) {
      return NoFile$.MODULE$.forall(p);
   }

   public static void foreach(final Function1 f) {
      NoFile$.MODULE$.foreach(f);
   }

   /** @deprecated */
   public static boolean hasDefiniteSize() {
      return NoFile$.MODULE$.hasDefiniteSize();
   }

   public static int knownSize() {
      return NoFile$.MODULE$.knownSize();
   }

   public static Stepper stepper(final StepperShape shape) {
      return NoFile$.MODULE$.stepper(shape);
   }
}
