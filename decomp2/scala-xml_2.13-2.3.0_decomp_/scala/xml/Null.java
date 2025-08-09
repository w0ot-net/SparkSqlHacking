package scala.xml;

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
import scala.collection.Seq;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
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
   bytes = "\u0006\u0005\u0005\u0015x!B\u0011#\u0011\u0003;c!B\u0015#\u0011\u0003S\u0003\"\u0002 \u0002\t\u0003y\u0004\"\u0002!\u0002\t\u0003\n\u0005\"B&\u0002\t\u0003b\u0005\"\u0002)\u0002\t\u0003\n\u0006bB-\u0002#\u0003%\tA\u0017\u0005\u0006K\u0006!\tE\u001a\u0005\u0006m\u0006!\te\u001e\u0005\u0006u\u0006!\te\u001f\u0005\b\u0003\u0013\tA\u0011IA\u0006\u0011\u0019I\u0018\u0001\"\u0011\u0002\u000e!9\u0011QC\u0001\u0005B\u0005]\u0001bBA\u0010\u0003\u0011\u0005\u0013\u0011\u0005\u0005\b\u0003S\tA\u0011IA\u0006\u0011\u0019\tY#\u0001C!\u0019\"9\u00111F\u0001\u0005B\u00055\u0002bBA\u001a\u0003\u0011\u0005\u0013Q\u0007\u0005\b\u0003\u0003\nA\u0011KA\"\u0011\u001d\t\t&\u0001C!\u0003'Bq!!\u0015\u0002\t\u0003\n\u0019\bC\u0004\u0002~\u0005!\t&a \t\u000f\u0005u\u0014\u0001\"\u0015\u0002\u0014\"9\u0011QS\u0001\u0005B\u0005]\u0005bBAM\u0003\u0011\u0005\u00131\u0014\u0005\b\u0003?\u000bA\u0011IAQ\u0011\u001d\t)+\u0001C!\u0003OCq!!*\u0002\t\u0003\n\t\fC\u0005\u0002:\u0006\t\t\u0011\"\u0011\u0002<\"A\u00111Z\u0001\u0002\u0002\u0013\u0005A\nC\u0005\u0002N\u0006\t\t\u0011\"\u0001\u0002P\"I\u0011Q[\u0001\u0002\u0002\u0013\u0005\u0013q\u001b\u0005\n\u00037\f\u0011\u0011!C\u0005\u0003;\fAAT;mY*\u00111\u0005J\u0001\u0004q6d'\"A\u0013\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011\u0001&A\u0007\u0002E\t!a*\u001e7m'\u0011\t1F\f\u001a\u0011\u0005!b\u0013BA\u0017#\u0005!iU\r^1ECR\f\u0007CA\u00181\u001b\u0005!\u0013BA\u0019%\u0005\u001d\u0001&o\u001c3vGR\u0004\"aM\u001e\u000f\u0005QJdBA\u001b9\u001b\u00051$BA\u001c'\u0003\u0019a$o\\8u}%\tQ%\u0003\u0002;I\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001f>\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQD%\u0001\u0004=S:LGO\u0010\u000b\u0002O\u0005A\u0011\u000e^3sCR|'/F\u0001C!\r\u0019e\tS\u0007\u0002\t*\u0011Q\tJ\u0001\u000bG>dG.Z2uS>t\u0017BA$E\u0005!IE/\u001a:bi>\u0014\bCA\u0018J\u0013\tQEEA\u0004O_RD\u0017N\\4\u0002\tML'0Z\u000b\u0002\u001bB\u0011qFT\u0005\u0003\u001f\u0012\u00121!\u00138u\u0003\u0019\t\u0007\u000f]3oIR\u00191F\u0015+\t\u000bM+\u0001\u0019A\u0016\u0002\u00035Dq!V\u0003\u0011\u0002\u0003\u0007a+A\u0003tG>\u0004X\r\u0005\u0002)/&\u0011\u0001L\t\u0002\u0011\u001d\u0006lWm\u001d9bG\u0016\u0014\u0015N\u001c3j]\u001e\f\u0001#\u00199qK:$G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003mS#A\u0016/,\u0003u\u0003\"AX2\u000e\u0003}S!\u0001Y1\u0002\u0013Ut7\r[3dW\u0016$'B\u00012%\u0003)\tgN\\8uCRLwN\\\u0005\u0003I~\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u00191\u0017\u000e\u001c;feR\u0011qM\u001c\t\u0003Q.t!\u0001K5\n\u0005)\u0014\u0013aH*dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7MU3ukJtG+\u001f9fg&\u0011A.\u001c\u0002\u000b\u001dVdGNR5mi\u0016\u0014(B\u00016#\u0011\u0015yw\u00011\u0001q\u0003\u00051\u0007\u0003B\u0018rWML!A\u001d\u0013\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CA\u0018u\u0013\t)HEA\u0004C_>dW-\u00198\u0002\t\r|\u0007/\u001f\u000b\u0003WaDQ!\u001f\u0005A\u0002-\nAA\\3yi\u0006aq-\u001a;OC6,7\u000f]1dKR\u0011Ap \t\u0003QvL!A`7\u0003!9+H\u000e\\$fi:\u000bW.Z:qC\u000e,\u0007bBA\u0001\u0013\u0001\u0007\u00111A\u0001\u0006_^tWM\u001d\t\u0004Q\u0005\u0015\u0011bAA\u0004E\t!aj\u001c3f\u0003\u001dA\u0017m\u001d(fqR,\u0012a]\u000b\u0003\u0003\u001f\u00012\u0001[A\t\u0013\r\t\u0019\"\u001c\u0002\t\u001dVdGNT3yi\u0006\u00191.Z=\u0016\u0005\u0005e\u0001c\u00015\u0002\u001c%\u0019\u0011QD7\u0003\u000f9+H\u000e\\&fs\u0006)a/\u00197vKV\u0011\u00111\u0005\t\u0004Q\u0006\u0015\u0012bAA\u0014[\nIa*\u001e7m-\u0006dW/Z\u0001\u000bSN\u0004&/\u001a4jq\u0016$\u0017A\u00027f]\u001e$\b\u000eF\u0002N\u0003_Aa!!\r\u0011\u0001\u0004i\u0015!A5\u0002\u001bM$(/[2u?\u0012*\u0017\u000fJ3r)\r\u0019\u0018q\u0007\u0005\b\u0003s\t\u0002\u0019AA\u001e\u0003\u0015yG\u000f[3s!\rA\u0013QH\u0005\u0004\u0003\u007f\u0011#\u0001C#rk\u0006d\u0017\u000e^=\u0002!\t\f7/[:G_JD\u0015m\u001d5D_\u0012,WCAA#!\u0015\u0019\u0015qIA&\u0013\r\tI\u0005\u0012\u0002\u0004'\u0016\f\bcA\u0018\u0002N%\u0019\u0011q\n\u0013\u0003\u0007\u0005s\u00170A\u0003baBd\u0017\u0010\u0006\u0005\u0002V\u0005m\u0013qNA9!\rA\u0017qK\u0005\u0004\u00033j'A\u0003(vY2\f\u0005\u000f\u001d7zg!9\u0011QL\nA\u0002\u0005}\u0013!\u00038b[\u0016\u001c\b/Y2f!\u0011\t\t'!\u001b\u000f\t\u0005\r\u0014Q\r\t\u0003k\u0011J1!a\u001a%\u0003\u0019\u0001&/\u001a3fM&!\u00111NA7\u0005\u0019\u0019FO]5oO*\u0019\u0011q\r\u0013\t\u000bU\u001b\u0002\u0019\u0001,\t\u000f\u0005U1\u00031\u0001\u0002`Q!\u0011QOA>!\rA\u0017qO\u0005\u0004\u0003sj'A\u0003(vY2\f\u0005\u000f\u001d7zc!9\u0011Q\u0003\u000bA\u0002\u0005}\u0013!\u0003;p'R\u0014\u0018N\\42)\u0011\t\t)a\"\u0011\u0007=\n\u0019)C\u0002\u0002\u0006\u0012\u0012A!\u00168ji\"9\u0011\u0011R\u000bA\u0002\u0005-\u0015AA:c!\u0011\ti)a$\u000f\u0005=J\u0014bAAI{\ti1\u000b\u001e:j]\u001e\u0014U/\u001b7eKJ,\"!a\u0018\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0018\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0005\u0003\u0017\u000bi\nC\u0004\u0002\nb\u0001\r!a#\u0002\u0015],G\u000e\u001c4pe6,G\rF\u0002t\u0003GCQ!V\rA\u0002Y\u000baA]3n_Z,G\u0003BAU\u0003_\u00032\u0001[AV\u0013\r\ti+\u001c\u0002\u000b\u001dVdGNU3n_Z,\u0007bBA\u000b5\u0001\u0007\u0011q\f\u000b\t\u0003S\u000b\u0019,!.\u00028\"9\u0011QL\u000eA\u0002\u0005}\u0003\"B+\u001c\u0001\u00041\u0006bBA\u000b7\u0001\u0007\u0011qL\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005u\u0006\u0003BA`\u0003\u0013l!!!1\u000b\t\u0005\r\u0017QY\u0001\u0005Y\u0006twM\u0003\u0002\u0002H\u0006!!.\u0019<b\u0013\u0011\tY'!1\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111JAi\u0011!\t\u0019NHA\u0001\u0002\u0004i\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002ZB!1IRA&\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\u000e\u0005\u0003\u0002@\u0006\u0005\u0018\u0002BAr\u0003\u0003\u0014aa\u00142kK\u000e$\b"
)
public final class Null {
   public static Iterator productIterator() {
      return Null$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return Null$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return Null$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return Null$.MODULE$.productPrefix();
   }

   public static Null$ remove(final String namespace, final NamespaceBinding scope, final String key) {
      return Null$.MODULE$.remove(namespace, scope, key);
   }

   public static Null$ remove(final String key) {
      return Null$.MODULE$.remove(key);
   }

   public static boolean wellformed(final NamespaceBinding scope) {
      return Null$.MODULE$.wellformed(scope);
   }

   public static StringBuilder buildString(final StringBuilder sb) {
      return Null$.MODULE$.buildString(sb);
   }

   public static String toString() {
      return Null$.MODULE$.toString();
   }

   public static Seq apply(final String key) {
      return Null$.MODULE$.apply(key);
   }

   public static scala.runtime.Null apply(final String namespace, final NamespaceBinding scope, final String key) {
      return Null$.MODULE$.apply(namespace, scope, key);
   }

   public static boolean strict_$eq$eq(final Equality other) {
      return Null$.MODULE$.strict_$eq$eq(other);
   }

   public static int length(final int i) {
      return Null$.MODULE$.length(i);
   }

   public static int length() {
      return Null$.MODULE$.length();
   }

   public static boolean isPrefixed() {
      return Null$.MODULE$.isPrefixed();
   }

   public static scala.runtime.Null value() {
      return Null$.MODULE$.value();
   }

   public static scala.runtime.Null key() {
      return Null$.MODULE$.key();
   }

   public static scala.runtime.Null next() {
      return Null$.MODULE$.next();
   }

   public static boolean hasNext() {
      return Null$.MODULE$.hasNext();
   }

   public static scala.runtime.Null getNamespace(final Node owner) {
      return Null$.MODULE$.getNamespace(owner);
   }

   public static MetaData copy(final MetaData next) {
      return Null$.MODULE$.copy(next);
   }

   public static MetaData filter(final Function1 f) {
      return Null$.MODULE$.filter(f);
   }

   public static NamespaceBinding append$default$2() {
      return Null$.MODULE$.append$default$2();
   }

   public static MetaData append(final MetaData m, final NamespaceBinding scope) {
      return Null$.MODULE$.append(m, scope);
   }

   public static int size() {
      return Null$.MODULE$.size();
   }

   public static Iterator iterator() {
      return Null$.MODULE$.iterator();
   }

   public static Iterator productElementNames() {
      return Null$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return Null$.MODULE$.productElementName(n);
   }

   public static MetaData remove(final String namespace, final Node owner, final String key) {
      return Null$.MODULE$.remove(namespace, (Node)owner, key);
   }

   public static Option get(final String uri, final NamespaceBinding scope, final String key) {
      return Null$.MODULE$.get(uri, scope, key);
   }

   public static Option get(final String uri, final Node owner, final String key) {
      return Null$.MODULE$.get(uri, owner, key);
   }

   public static Option get(final String key) {
      return Null$.MODULE$.get(key);
   }

   public static Map asAttrMap() {
      return Null$.MODULE$.asAttrMap();
   }

   public static String prefixedKey() {
      return Null$.MODULE$.prefixedKey();
   }

   public static MetaData reverse() {
      return Null$.MODULE$.reverse();
   }

   public static boolean canEqual(final Object other) {
      return Null$.MODULE$.canEqual(other);
   }

   public static Seq apply(final String namespace_uri, final Node owner, final String key) {
      return Null$.MODULE$.apply(namespace_uri, (Node)owner, key);
   }

   public static boolean xml_$bang$eq(final Object other) {
      return Null$.MODULE$.xml_$bang$eq(other);
   }

   public static boolean xml_$eq$eq(final Object other) {
      return Null$.MODULE$.xml_$eq$eq(other);
   }

   public static boolean equals(final Object other) {
      return Null$.MODULE$.equals(other);
   }

   public static int hashCode() {
      return Null$.MODULE$.hashCode();
   }

   public static boolean strict_$bang$eq(final Equality other) {
      return Null$.MODULE$.strict_$bang$eq(other);
   }

   public static LazyZip2 lazyZip(final Iterable that) {
      return Null$.MODULE$.lazyZip(that);
   }

   /** @deprecated */
   public static Iterable seq() {
      return Null$.MODULE$.seq();
   }

   public static IterableFactory iterableFactory() {
      return Null$.MODULE$.iterableFactory();
   }

   /** @deprecated */
   public static Iterable toIterable() {
      return Null$.MODULE$.toIterable();
   }

   public static IterableOps empty() {
      return Null$.MODULE$.empty();
   }

   /** @deprecated */
   public static Object $plus$plus$colon(final IterableOnce that) {
      return Null$.MODULE$.$plus$plus$colon(that);
   }

   public static Object tapEach(final Function1 f) {
      return Null$.MODULE$.tapEach(f);
   }

   public static Iterator inits() {
      return Null$.MODULE$.inits();
   }

   public static Iterator tails() {
      return Null$.MODULE$.tails();
   }

   public static Tuple3 unzip3(final Function1 asTriple) {
      return Null$.MODULE$.unzip3(asTriple);
   }

   public static Tuple2 unzip(final Function1 asPair) {
      return Null$.MODULE$.unzip(asPair);
   }

   public static Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return Null$.MODULE$.zipAll(that, thisElem, thatElem);
   }

   public static Object zipWithIndex() {
      return Null$.MODULE$.zipWithIndex();
   }

   public static Object zip(final IterableOnce that) {
      return Null$.MODULE$.zip(that);
   }

   public static Object $plus$plus(final IterableOnce suffix) {
      return Null$.MODULE$.$plus$plus(suffix);
   }

   public static Object concat(final IterableOnce suffix) {
      return Null$.MODULE$.concat(suffix);
   }

   public static Tuple2 partitionMap(final Function1 f) {
      return Null$.MODULE$.partitionMap(f);
   }

   public static Object collect(final PartialFunction pf) {
      return Null$.MODULE$.collect(pf);
   }

   public static Object flatten(final Function1 asIterable) {
      return Null$.MODULE$.flatten(asIterable);
   }

   public static Object flatMap(final Function1 f) {
      return Null$.MODULE$.flatMap(f);
   }

   public static Object map(final Function1 f) {
      return Null$.MODULE$.map(f);
   }

   public static Object scanRight(final Object z, final Function2 op) {
      return Null$.MODULE$.scanRight(z, op);
   }

   public static Object scanLeft(final Object z, final Function2 op) {
      return Null$.MODULE$.scanLeft(z, op);
   }

   public static Object scan(final Object z, final Function2 op) {
      return Null$.MODULE$.scan(z, op);
   }

   public static Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return Null$.MODULE$.groupMapReduce(key, f, reduce);
   }

   public static Map groupMap(final Function1 key, final Function1 f) {
      return Null$.MODULE$.groupMap(key, f);
   }

   public static Map groupBy(final Function1 f) {
      return Null$.MODULE$.groupBy(f);
   }

   public static Object slice(final int from, final int until) {
      return Null$.MODULE$.slice(from, until);
   }

   public static Object init() {
      return Null$.MODULE$.init();
   }

   public static Object tail() {
      return Null$.MODULE$.tail();
   }

   public static Iterator sliding(final int size, final int step) {
      return Null$.MODULE$.sliding(size, step);
   }

   public static Iterator sliding(final int size) {
      return Null$.MODULE$.sliding(size);
   }

   public static Iterator grouped(final int size) {
      return Null$.MODULE$.grouped(size);
   }

   public static Object dropWhile(final Function1 p) {
      return Null$.MODULE$.dropWhile(p);
   }

   public static Object dropRight(final int n) {
      return Null$.MODULE$.dropRight(n);
   }

   public static Object drop(final int n) {
      return Null$.MODULE$.drop(n);
   }

   public static Tuple2 span(final Function1 p) {
      return Null$.MODULE$.span(p);
   }

   public static Object takeWhile(final Function1 p) {
      return Null$.MODULE$.takeWhile(p);
   }

   public static Object takeRight(final int n) {
      return Null$.MODULE$.takeRight(n);
   }

   public static Object take(final int n) {
      return Null$.MODULE$.take(n);
   }

   public static Tuple2 splitAt(final int n) {
      return Null$.MODULE$.splitAt(n);
   }

   public static Tuple2 partition(final Function1 p) {
      return Null$.MODULE$.partition(p);
   }

   public static WithFilter withFilter(final Function1 p) {
      return Null$.MODULE$.withFilter(p);
   }

   public static Object filterNot(final Function1 pred) {
      return Null$.MODULE$.filterNot(pred);
   }

   public static Object transpose(final Function1 asIterable) {
      return Null$.MODULE$.transpose(asIterable);
   }

   /** @deprecated */
   public static View view(final int from, final int until) {
      return Null$.MODULE$.view(from, until);
   }

   public static int sizeCompare(final Iterable that) {
      return Null$.MODULE$.sizeCompare(that);
   }

   public static IterableOps sizeIs() {
      return Null$.MODULE$.sizeIs();
   }

   public static int sizeCompare(final int otherSize) {
      return Null$.MODULE$.sizeCompare(otherSize);
   }

   public static View view() {
      return Null$.MODULE$.view();
   }

   public static Option lastOption() {
      return Null$.MODULE$.lastOption();
   }

   public static Object last() {
      return Null$.MODULE$.last();
   }

   public static Option headOption() {
      return Null$.MODULE$.headOption();
   }

   public static Object head() {
      return Null$.MODULE$.head();
   }

   /** @deprecated */
   public static IterableFactory companion() {
      return Null$.MODULE$.companion();
   }

   /** @deprecated */
   public static Object repr() {
      return Null$.MODULE$.repr();
   }

   public static boolean isTraversableAgain() {
      return Null$.MODULE$.isTraversableAgain();
   }

   /** @deprecated */
   public static Iterable toTraversable() {
      return Null$.MODULE$.toTraversable();
   }

   public static Object toArray(final ClassTag evidence$2) {
      return Null$.MODULE$.toArray(evidence$2);
   }

   public static Buffer toBuffer() {
      return Null$.MODULE$.toBuffer();
   }

   /** @deprecated */
   public static Stream toStream() {
      return Null$.MODULE$.toStream();
   }

   public static IndexedSeq toIndexedSeq() {
      return Null$.MODULE$.toIndexedSeq();
   }

   public static scala.collection.immutable.Seq toSeq() {
      return Null$.MODULE$.toSeq();
   }

   public static Set toSet() {
      return Null$.MODULE$.toSet();
   }

   public static Map toMap(final .less.colon.less ev) {
      return Null$.MODULE$.toMap(ev);
   }

   public static Vector toVector() {
      return Null$.MODULE$.toVector();
   }

   public static List toList() {
      return Null$.MODULE$.toList();
   }

   /** @deprecated */
   public static Iterator toIterator() {
      return Null$.MODULE$.toIterator();
   }

   public static Object to(final Factory factory) {
      return Null$.MODULE$.to(factory);
   }

   public static StringBuilder addString(final StringBuilder b) {
      return Null$.MODULE$.addString(b);
   }

   public static StringBuilder addString(final StringBuilder b, final String sep) {
      return Null$.MODULE$.addString(b, sep);
   }

   public static StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return Null$.MODULE$.addString(b, start, sep, end);
   }

   public static String mkString() {
      return Null$.MODULE$.mkString();
   }

   public static String mkString(final String sep) {
      return Null$.MODULE$.mkString(sep);
   }

   public static String mkString(final String start, final String sep, final String end) {
      return Null$.MODULE$.mkString(start, sep, end);
   }

   public static boolean corresponds(final IterableOnce that, final Function2 p) {
      return Null$.MODULE$.corresponds(that, p);
   }

   /** @deprecated */
   public static Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return Null$.MODULE$.aggregate(z, seqop, combop);
   }

   public static Option collectFirst(final PartialFunction pf) {
      return Null$.MODULE$.collectFirst(pf);
   }

   public static Option minByOption(final Function1 f, final Ordering ord) {
      return Null$.MODULE$.minByOption(f, ord);
   }

   public static Object minBy(final Function1 f, final Ordering ord) {
      return Null$.MODULE$.minBy(f, ord);
   }

   public static Option maxByOption(final Function1 f, final Ordering ord) {
      return Null$.MODULE$.maxByOption(f, ord);
   }

   public static Object maxBy(final Function1 f, final Ordering ord) {
      return Null$.MODULE$.maxBy(f, ord);
   }

   public static Option maxOption(final Ordering ord) {
      return Null$.MODULE$.maxOption(ord);
   }

   public static Object max(final Ordering ord) {
      return Null$.MODULE$.max(ord);
   }

   public static Option minOption(final Ordering ord) {
      return Null$.MODULE$.minOption(ord);
   }

   public static Object min(final Ordering ord) {
      return Null$.MODULE$.min(ord);
   }

   public static Object product(final Numeric num) {
      return Null$.MODULE$.product(num);
   }

   public static Object sum(final Numeric num) {
      return Null$.MODULE$.sum(num);
   }

   public static int copyToArray(final Object xs, final int start, final int len) {
      return Null$.MODULE$.copyToArray(xs, start, len);
   }

   public static int copyToArray(final Object xs, final int start) {
      return Null$.MODULE$.copyToArray(xs, start);
   }

   public static int copyToArray(final Object xs) {
      return Null$.MODULE$.copyToArray(xs);
   }

   /** @deprecated */
   public static void copyToBuffer(final Buffer dest) {
      Null$.MODULE$.copyToBuffer(dest);
   }

   public static boolean nonEmpty() {
      return Null$.MODULE$.nonEmpty();
   }

   public static boolean isEmpty() {
      return Null$.MODULE$.isEmpty();
   }

   public static Option reduceRightOption(final Function2 op) {
      return Null$.MODULE$.reduceRightOption(op);
   }

   public static Option reduceLeftOption(final Function2 op) {
      return Null$.MODULE$.reduceLeftOption(op);
   }

   public static Object reduceRight(final Function2 op) {
      return Null$.MODULE$.reduceRight(op);
   }

   public static Object reduceLeft(final Function2 op) {
      return Null$.MODULE$.reduceLeft(op);
   }

   public static Option reduceOption(final Function2 op) {
      return Null$.MODULE$.reduceOption(op);
   }

   public static Object reduce(final Function2 op) {
      return Null$.MODULE$.reduce(op);
   }

   public static Object fold(final Object z, final Function2 op) {
      return Null$.MODULE$.fold(z, op);
   }

   /** @deprecated */
   public static Object $colon$bslash(final Object z, final Function2 op) {
      return Null$.MODULE$.$colon$bslash(z, op);
   }

   /** @deprecated */
   public static Object $div$colon(final Object z, final Function2 op) {
      return Null$.MODULE$.$div$colon(z, op);
   }

   public static Object foldRight(final Object z, final Function2 op) {
      return Null$.MODULE$.foldRight(z, op);
   }

   public static Object foldLeft(final Object z, final Function2 op) {
      return Null$.MODULE$.foldLeft(z, op);
   }

   public static Option find(final Function1 p) {
      return Null$.MODULE$.find(p);
   }

   public static int count(final Function1 p) {
      return Null$.MODULE$.count(p);
   }

   public static boolean exists(final Function1 p) {
      return Null$.MODULE$.exists(p);
   }

   public static boolean forall(final Function1 p) {
      return Null$.MODULE$.forall(p);
   }

   public static void foreach(final Function1 f) {
      Null$.MODULE$.foreach(f);
   }

   /** @deprecated */
   public static boolean hasDefiniteSize() {
      return Null$.MODULE$.hasDefiniteSize();
   }

   public static int knownSize() {
      return Null$.MODULE$.knownSize();
   }

   public static Stepper stepper(final StepperShape shape) {
      return Null$.MODULE$.stepper(shape);
   }
}
