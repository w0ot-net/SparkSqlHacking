package spire.math.prime;

import algebra.ring.Signed;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
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
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.BigInt;
import scala.math.Numeric;
import scala.math.Ordered;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import spire.algebra.UniqueFactorizationDomain;
import spire.math.SafeLong;
import spire.math.SafeLong$;

@ScalaSignature(
   bytes = "\u0006\u0005\t}u!\u0002\u001f>\u0011\u0003!e!\u0002$>\u0011\u00039\u0005\"\u0002,\u0002\t\u00039\u0006b\u0002-\u0002\u0005\u0004%\t!\u0017\u0005\b\u0005/\n\u0001\u0015!\u0003[\u0011!\u0011I&\u0001b\u0001\n\u0003I\u0006b\u0002B.\u0003\u0001\u0006IA\u0017\u0005\b\u0005;\nA\u0011\u0001B0\u0011\u001d\u0011i&\u0001C\u0001\u0005WBqA!\u0018\u0002\t\u0003\u0011)\bC\u0004\u0003^\u0005!\tA!\u001f\t\u0013\tu\u0013!!A\u0005\u0002\n}\u0004\"\u0003BC\u0003\u0005\u0005I\u0011\u0011BD\u0011%\u0011)*AA\u0001\n\u0013\u00119J\u0002\u0003G{\u0001[\u0006BCA\u0005\u001d\tU\r\u0011\"\u0001\u0002\f!Q\u0011Q\u0004\b\u0003\u0012\u0003\u0006I!!\u0004\t\u0015\u0005}aB!f\u0001\n\u0003\t\t\u0003\u0003\u0006\u000289\u0011\t\u0012)A\u0005\u0003GAaA\u0016\b\u0005\u0002\u0005e\u0002bBA \u001d\u0011\u0005\u0011\u0011\t\u0005\t\u0003\u0007rA\u0011A\u001f\u0002F!Q\u00111\n\b\t\u0006\u0004%\t!!\u0011\t\u000f\u00055c\u0002\"\u0011\u0002P!9\u0011q\u000b\b\u0005\u0002\u0005e\u0003bBA.\u001d\u0011\u0005\u0011Q\f\u0005\b\u0003KrA\u0011AA\u0006\u0011\u001d\t9G\u0004C\u0001\u0003SBq!!\u001d\u000f\t\u0003\t\u0019\bC\u0004\u0002\u00009!\t!!!\t\u000f\u0005\u0015e\u0002\"\u0001\u0002\b\"9\u0011Q\u0011\b\u0005\u0002\u00055\u0005bBAI\u001d\u0011\u0005\u00111\u0013\u0005\b\u0003/sA\u0011AAM\u0011\u0019\tiJ\u0004C\u00013\"9\u0011q\u0014\b\u0005\u0002\u0005\u0005\u0006bBAP\u001d\u0011\u0005\u0011Q\u0015\u0005\b\u0003SsA\u0011AAV\u0011\u001d\tIK\u0004C\u0001\u0003_Cq!a-\u000f\t\u0003\t)\fC\u0004\u00024:!\t!!/\t\u0011\u0005uf\u0002\"\u0001>\u0003\u007fCq!!3\u000f\t\u0003\tY\rC\u0004\u0002J:!\t!a4\t\u000f\u0005Mg\u0002\"\u0001\u0002V\"9\u00111\u001b\b\u0005\u0002\u0005e\u0007bBAo\u001d\u0011\u0005\u0011q\u001c\u0005\b\u0003;tA\u0011AAs\u0011\u001d\tIO\u0004C\u0001\u0003WD\u0011\"a<\u000f\u0003\u0003%\t!!=\t\u0013\u0005]h\"%A\u0005\u0002\u0005e\b\"\u0003B\b\u001dE\u0005I\u0011\u0001B\t\u0011%\u0011)BDA\u0001\n\u0003\u00129\u0002C\u0005\u0003$9\t\t\u0011\"\u0001\u0002Z!I!Q\u0005\b\u0002\u0002\u0013\u0005!q\u0005\u0005\n\u0005gq\u0011\u0011!C!\u0005kA\u0011B!\u0011\u000f\u0003\u0003%\tAa\u0011\t\u0013\t\u001dc\"!A\u0005B\t%\u0003\"\u0003B'\u001d\u0005\u0005I\u0011\tB(\u0011%\u0011\tFDA\u0001\n\u0003\u0012\u0019&A\u0004GC\u000e$xN]:\u000b\u0005yz\u0014!\u00029sS6,'B\u0001!B\u0003\u0011i\u0017\r\u001e5\u000b\u0003\t\u000bQa\u001d9je\u0016\u001c\u0001\u0001\u0005\u0002F\u00035\tQHA\u0004GC\u000e$xN]:\u0014\u0007\u0005Ae\n\u0005\u0002J\u00196\t!JC\u0001L\u0003\u0015\u00198-\u00197b\u0013\ti%J\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001fRk\u0011\u0001\u0015\u0006\u0003#J\u000b!![8\u000b\u0003M\u000bAA[1wC&\u0011Q\u000b\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0011\u000bAA_3s_V\t!\f\u0005\u0002F\u001dMAa\u0002\u0013/ky~\f)\u0001E\u0002^G\u001at!AX1\u000e\u0003}S!\u0001Y!\u0002\u000f\u0005dw-\u001a2sC&\u0011!mX\u0001\u001a+:L\u0017/^3GC\u000e$xN]5{CRLwN\u001c#p[\u0006Lg.\u0003\u0002eK\niA)Z2p[B|7/\u001b;j_:T!AY0\u0011\u0005\u001dDW\"A \n\u0005%|$\u0001C*bM\u0016duN\\4\u0011\u0007-\u001chO\u0004\u0002mc:\u0011Q\u000e]\u0007\u0002]*\u0011qnQ\u0001\u0007yI|w\u000e\u001e \n\u0003-K!A\u001d&\u0002\u000fA\f7m[1hK&\u0011A/\u001e\u0002\t\u0013R,'/\u00192mK*\u0011!O\u0013\t\u0005\u0013^4\u00170\u0003\u0002y\u0015\n1A+\u001e9mKJ\u0002\"!\u0013>\n\u0005mT%aA%oiB\u00191. .\n\u0005y,(aB(sI\u0016\u0014X\r\u001a\t\u0004\u0013\u0006\u0005\u0011bAA\u0002\u0015\n9\u0001K]8ek\u000e$\bcA6\u0002\b%\u0011Q+^\u0001\tK2,W.\u001a8ugV\u0011\u0011Q\u0002\t\u0007\u0003\u001f\t9BZ=\u000f\t\u0005E\u00111\u0003\t\u0003[*K1!!\u0006K\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011DA\u000e\u0005\ri\u0015\r\u001d\u0006\u0004\u0003+Q\u0015!C3mK6,g\u000e^:!\u0003\u0011\u0019\u0018n\u001a8\u0016\u0005\u0005\r\u0002\u0003BA\u0013\u0003cqA!a\n\u000209!\u0011\u0011FA\u0017\u001d\ri\u00171F\u0005\u0002\u0005&\u0011\u0001-Q\u0005\u0003e~KA!a\r\u00026\t!1+[4o\u0015\t\u0011x,A\u0003tS\u001et\u0007\u0005F\u0003[\u0003w\ti\u0004C\u0004\u0002\nM\u0001\r!!\u0004\t\u000f\u0005}1\u00031\u0001\u0002$\u0005!QO\\5u+\u00051\u0017\u0001\u00029s_\u0012$2AZA$\u0011\u001d\tI%\u0006a\u0001\u0003\u001b\t\u0011!\\\u0001\u0006m\u0006dW/Z\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011\u000b\t\u0005\u0003\u001f\t\u0019&\u0003\u0003\u0002V\u0005m!AB*ue&tw-\u0001\u0004tS\u001etW/\\\u000b\u0002s\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0002`A!1.!\u0019w\u0013\r\t\u0019'\u001e\u0002\t\u0013R,'/\u0019;pe\u0006)Ao\\'ba\u0006iQO\\5rk\u00164\u0015m\u0019;peN,\"!a\u001b\u0011\u000b\u0005=\u0011Q\u000e4\n\t\u0005=\u00141\u0004\u0002\u0004'\u0016$\u0018\u0001C2p]R\f\u0017N\\:\u0015\t\u0005U\u00141\u0010\t\u0004\u0013\u0006]\u0014bAA=\u0015\n9!i\\8mK\u0006t\u0007BBA?9\u0001\u0007a-A\u0001q\u0003\r9W\r\u001e\u000b\u0004s\u0006\r\u0005BBA?;\u0001\u0007a-A\u0004d_6\u0004\u0018M]3\u0015\u0007e\fI\t\u0003\u0004\u0002\fz\u0001\rAW\u0001\u0004e\"\u001cHcA=\u0002\u0010\"1\u00111R\u0010A\u0002e\f1aZ2e)\rQ\u0016Q\u0013\u0005\u0007\u0003\u0017\u0003\u0003\u0019\u0001.\u0002\u00071\u001cW\u000eF\u0002[\u00037Ca!a#\"\u0001\u0004Q\u0016\u0001D;oCJLx\fJ7j]V\u001c\u0018!\u0002\u0013qYV\u001cHc\u0001.\u0002$\"1\u00111R\u0012A\u0002i#2AWAT\u0011\u0019\tY\t\na\u0001M\u00061A%\\5okN$2AWAW\u0011\u0019\tY)\na\u00015R\u0019!,!-\t\r\u0005-e\u00051\u0001g\u0003\u0019!C/[7fgR\u0019!,a.\t\r\u0005-u\u00051\u0001[)\rQ\u00161\u0018\u0005\u0007\u0003\u0017C\u0003\u0019\u00014\u0002\u0005ElG\u0003BAa\u0003\u000f\u0004\"\"SAbs\u00065\u0011QBA\u0007\u0013\r\t)M\u0013\u0002\u0007)V\u0004H.\u001a\u001b\t\r\u0005-\u0015\u00061\u0001[\u0003\u0011!C-\u001b<\u0015\u0007i\u000bi\r\u0003\u0004\u0002\f*\u0002\rA\u0017\u000b\u00045\u0006E\u0007BBAFW\u0001\u0007a-\u0001\u0005%a\u0016\u00148-\u001a8u)\rQ\u0016q\u001b\u0005\u0007\u0003\u0017c\u0003\u0019\u0001.\u0015\u0007i\u000bY\u000e\u0003\u0004\u0002\f6\u0002\rAZ\u0001\rI\u0011Lg\u000f\n9fe\u000e,g\u000e\u001e\u000b\u0005\u0003C\f\u0019\u000f\u0005\u0003JojS\u0006BBAF]\u0001\u0007!\f\u0006\u0003\u0002b\u0006\u001d\bBBAF_\u0001\u0007a-A\u0002q_^$2AWAw\u0011\u0019\tY\t\ra\u0001s\u0006!1m\u001c9z)\u0015Q\u00161_A{\u0011%\tI!\rI\u0001\u0002\u0004\ti\u0001C\u0005\u0002 E\u0002\n\u00111\u0001\u0002$\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA~U\u0011\ti!!@,\u0005\u0005}\b\u0003\u0002B\u0001\u0005\u0017i!Aa\u0001\u000b\t\t\u0015!qA\u0001\nk:\u001c\u0007.Z2lK\u0012T1A!\u0003K\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005\u001b\u0011\u0019AA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0003\u0014)\"\u00111EA\u007f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!\u0011\u0004\t\u0005\u00057\u0011\t#\u0004\u0002\u0003\u001e)\u0019!q\u0004*\u0002\t1\fgnZ\u0005\u0005\u0003+\u0012i\"\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\t%\"q\u0006\t\u0004\u0013\n-\u0012b\u0001B\u0017\u0015\n\u0019\u0011I\\=\t\u0011\tEb'!AA\u0002e\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B\u001c!\u0019\u0011IDa\u0010\u0003*5\u0011!1\b\u0006\u0004\u0005{Q\u0015AC2pY2,7\r^5p]&!\u00111\rB\u001e\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA;\u0005\u000bB\u0011B!\r9\u0003\u0003\u0005\rA!\u000b\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u00053\u0011Y\u0005\u0003\u0005\u00032e\n\t\u00111\u0001z\u0003!A\u0017m\u001d5D_\u0012,G#A=\u0002\r\u0015\fX/\u00197t)\u0011\t)H!\u0016\t\u0013\tE2(!AA\u0002\t%\u0012!\u0002>fe>\u0004\u0013aA8oK\u0006!qN\\3!\u0003\u0015\t\u0007\u000f\u001d7z)\rQ&\u0011\r\u0005\b\u0005G:\u0001\u0019\u0001B3\u0003\u0005q\u0007cA%\u0003h%\u0019!\u0011\u000e&\u0003\t1{gn\u001a\u000b\u00045\n5\u0004b\u0002B2\u0011\u0001\u0007!q\u000e\t\u0004W\nE\u0014b\u0001B:k\n1!)[4J]R$2A\u0017B<\u0011\u0019\u0011\u0019'\u0003a\u0001MR\u0019!La\u001f\t\u000f\tu$\u00021\u0001\u0002R\u0005\t1\u000fF\u0003[\u0005\u0003\u0013\u0019\tC\u0004\u0002\n-\u0001\r!!\u0004\t\u000f\u0005}1\u00021\u0001\u0002$\u00059QO\\1qa2LH\u0003\u0002BE\u0005#\u0003R!\u0013BF\u0005\u001fK1A!$K\u0005\u0019y\u0005\u000f^5p]B1\u0011j^A\u0007\u0003GA\u0001Ba%\r\u0003\u0003\u0005\rAW\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001BM!\u0011\u0011YBa'\n\t\tu%Q\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public class Factors implements UniqueFactorizationDomain.Decomposition, Iterable, Ordered, Product, Serializable {
   private SafeLong value;
   private final Map elements;
   private final Signed.Sign sign;
   private volatile boolean bitmap$0;

   public static Option unapply(final Factors x$0) {
      return Factors$.MODULE$.unapply(x$0);
   }

   public static Factors apply(final Map elements, final Signed.Sign sign) {
      return Factors$.MODULE$.apply(elements, sign);
   }

   public static Factors apply(final String s) {
      return Factors$.MODULE$.apply(s);
   }

   public static Factors apply(final SafeLong n) {
      return Factors$.MODULE$.apply(n);
   }

   public static Factors apply(final BigInt n) {
      return Factors$.MODULE$.apply(n);
   }

   public static Factors apply(final long n) {
      return Factors$.MODULE$.apply(n);
   }

   public static Factors one() {
      return Factors$.MODULE$.one();
   }

   public static Factors zero() {
      return Factors$.MODULE$.zero();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public String stringPrefix() {
      return Iterable.stringPrefix$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return IterableFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return IterableFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return IterableFactoryDefaults.empty$(this);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public View view() {
      return IterableOps.view$(this);
   }

   public int sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   public int sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public Object filter(final Function1 pred) {
      return IterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return IterableOps.filterNot$(this, pred);
   }

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 partition(final Function1 p) {
      return IterableOps.partition$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IterableOps.takeRight$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return IterableOps.span$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IterableOps.dropRight$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return IterableOps.scanLeft$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object map(final Function1 f) {
      return IterableOps.map$(this, f);
   }

   public Object flatMap(final Function1 f) {
      return IterableOps.flatMap$(this, f);
   }

   public Object flatten(final Function1 asIterable) {
      return IterableOps.flatten$(this, asIterable);
   }

   public Object collect(final PartialFunction pf) {
      return IterableOps.collect$(this, pf);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return IterableOps.partitionMap$(this, f);
   }

   public Object concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zip(final IterableOnce that) {
      return IterableOps.zip$(this, that);
   }

   public Object zipWithIndex() {
      return IterableOps.zipWithIndex$(this);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return IterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return IterableOps.unzip3$(this, asTriple);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   public Object tapEach(final Function1 f) {
      return IterableOps.tapEach$(this, f);
   }

   /** @deprecated */
   public Object $plus$plus$colon(final IterableOnce that) {
      return IterableOps.$plus$plus$colon$(this, that);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
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

   public int count(final Function1 p) {
      return IterableOnceOps.count$(this, p);
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

   public Object fold(final Object z, final Function2 op) {
      return IterableOnceOps.fold$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return IterableOnceOps.reduce$(this, op);
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

   public boolean isEmpty() {
      return IterableOnceOps.isEmpty$(this);
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

   public Object sum(final Numeric num) {
      return IterableOnceOps.sum$(this, num);
   }

   public Object product(final Numeric num) {
      return IterableOnceOps.product$(this, num);
   }

   public Object min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   public Option minOption(final Ordering ord) {
      return IterableOnceOps.minOption$(this, ord);
   }

   public Object max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   public Option maxOption(final Ordering ord) {
      return IterableOnceOps.maxOption$(this, ord);
   }

   public Object maxBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxBy$(this, f, cmp);
   }

   public Option maxByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxByOption$(this, f, cmp);
   }

   public Object minBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minBy$(this, f, cmp);
   }

   public Option minByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minByOption$(this, f, cmp);
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

   public byte unit$mcB$sp() {
      return UniqueFactorizationDomain.Decomposition.unit$mcB$sp$(this);
   }

   public int unit$mcI$sp() {
      return UniqueFactorizationDomain.Decomposition.unit$mcI$sp$(this);
   }

   public long unit$mcJ$sp() {
      return UniqueFactorizationDomain.Decomposition.unit$mcJ$sp$(this);
   }

   public short unit$mcS$sp() {
      return UniqueFactorizationDomain.Decomposition.unit$mcS$sp$(this);
   }

   public Map elements() {
      return this.elements;
   }

   public Signed.Sign sign() {
      return this.sign;
   }

   public SafeLong unit() {
      return SafeLong$.MODULE$.apply(this.sign().toInt());
   }

   public SafeLong prod(final Map m) {
      return (SafeLong)m.foldLeft(SafeLong$.MODULE$.one(), (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            SafeLong t = (SafeLong)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var5 != null) {
               SafeLong p = (SafeLong)var5._1();
               int e = var5._2$mcI$sp();
               SafeLong var2 = t.$times(p.pow(e));
               return var2;
            }
         }

         throw new MatchError(var3);
      });
   }

   private SafeLong value$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            Signed.Sign var3 = this.sign();
            SafeLong var1;
            if (algebra.ring.Signed.Positive..MODULE$.equals(var3)) {
               var1 = this.prod(this.elements());
            } else if (algebra.ring.Signed.Zero..MODULE$.equals(var3)) {
               var1 = SafeLong$.MODULE$.zero();
            } else {
               if (!algebra.ring.Signed.Negative..MODULE$.equals(var3)) {
                  throw new MatchError(var3);
               }

               var1 = this.prod(this.elements()).unary_$minus();
            }

            this.value = var1;
            this.bitmap$0 = true;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.value;
   }

   public SafeLong value() {
      return !this.bitmap$0 ? this.value$lzycompute() : this.value;
   }

   public String toString() {
      Signed.Sign var2 = this.sign();
      String var1;
      if (algebra.ring.Signed.Positive..MODULE$.equals(var2)) {
         var1 = (new java.lang.StringBuilder(2)).append("(").append(this.terms$1()).append(")").toString();
      } else if (algebra.ring.Signed.Zero..MODULE$.equals(var2)) {
         var1 = "(0)";
      } else {
         if (!algebra.ring.Signed.Negative..MODULE$.equals(var2)) {
            throw new MatchError(var2);
         }

         var1 = (new java.lang.StringBuilder(3)).append("-(").append(this.terms$1()).append(")").toString();
      }

      return var1;
   }

   public int signum() {
      return this.sign().toInt();
   }

   public Iterator iterator() {
      return this.elements().iterator();
   }

   public Map toMap() {
      return this.elements();
   }

   public Set uniqueFactors() {
      return this.elements().keySet();
   }

   public boolean contains(final SafeLong p) {
      return this.elements().contains(p);
   }

   public int get(final SafeLong p) {
      return BoxesRunTime.unboxToInt(this.elements().getOrElse(p, (JFunction0.mcI.sp)() -> 0));
   }

   public int compare(final Factors rhs) {
      int n = this.signum() - rhs.signum();
      return n == 0 ? this.value().compare(rhs.value()) : Integer.signum(n);
   }

   public int compare(final int rhs) {
      Signed.Sign var5 = this.sign();
      int var2;
      if (algebra.ring.Signed.Positive..MODULE$.equals(var5)) {
         SafeLong t = SafeLong$.MODULE$.one();

         SafeLong p;
         int e;
         for(Iterator it = this.iterator(); it.hasNext() && t.$less$eq(SafeLong$.MODULE$.apply(rhs)); t = t.$times(p.$times$times(e))) {
            Tuple2 var9 = (Tuple2)it.next();
            if (var9 == null) {
               throw new MatchError(var9);
            }

            SafeLong p = (SafeLong)var9._1();
            int e = var9._2$mcI$sp();
            Tuple2 var4 = new Tuple2(p, BoxesRunTime.boxToInteger(e));
            p = (SafeLong)var4._1();
            e = var4._2$mcI$sp();
         }

         var2 = t.compare(SafeLong$.MODULE$.apply(rhs));
      } else if (algebra.ring.Signed.Zero..MODULE$.equals(var5)) {
         var2 = BoxesRunTime.unboxToInt((new RichInt(scala.Predef..MODULE$.intWrapper(rhs))).sign());
      } else {
         if (!algebra.ring.Signed.Negative..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         SafeLong t = SafeLong$.MODULE$.one().unary_$minus();

         SafeLong p;
         int e;
         for(Iterator it = this.iterator(); it.hasNext() && t.$greater$eq(SafeLong$.MODULE$.apply(rhs)); t = t.$times(p.$times$times(e))) {
            Tuple2 var17 = (Tuple2)it.next();
            if (var17 == null) {
               throw new MatchError(var17);
            }

            SafeLong p = (SafeLong)var17._1();
            int e = var17._2$mcI$sp();
            Tuple2 var3 = new Tuple2(p, BoxesRunTime.boxToInteger(e));
            p = (SafeLong)var3._1();
            e = var3._2$mcI$sp();
         }

         var2 = t.compare(SafeLong$.MODULE$.apply(rhs));
      }

      return var2;
   }

   public Factors gcd(final Factors rhs) {
      return new Factors((Map)this.elements().flatMap((x0$1) -> {
         if (x0$1 != null) {
            SafeLong p = (SafeLong)x0$1._1();
            int le = x0$1._2$mcI$sp();
            Option var2 = rhs.elements().get(p).map((re) -> $anonfun$gcd$2(p, le, BoxesRunTime.unboxToInt(re)));
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }), algebra.ring.Signed.Positive..MODULE$);
   }

   public Factors lcm(final Factors rhs) {
      return new Factors((Map)this.elements().foldLeft(rhs.elements(), (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            Map fs = (Map)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var5 != null) {
               SafeLong p = (SafeLong)var5._1();
               int e = var5._2$mcI$sp();
               Map var2 = (Map)fs.updated(p, BoxesRunTime.boxToInteger(scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(BoxesRunTime.unboxToInt(fs.getOrElse(p, (JFunction0.mcI.sp)() -> 0))), e)));
               return var2;
            }
         }

         throw new MatchError(var3);
      }), algebra.ring.Signed.Positive..MODULE$);
   }

   public Factors unary_$minus() {
      return new Factors(this.elements(), this.sign().unary_$minus());
   }

   public Factors $plus(final Factors rhs) {
      return Factors$.MODULE$.apply(this.value().$plus(rhs.value()));
   }

   public Factors $plus(final SafeLong rhs) {
      return Factors$.MODULE$.apply(this.value().$plus(rhs));
   }

   public Factors $minus(final Factors rhs) {
      return Factors$.MODULE$.apply(this.value().$minus(rhs.value()));
   }

   public Factors $minus(final SafeLong rhs) {
      return Factors$.MODULE$.apply(this.value().$minus(rhs));
   }

   public Factors $times(final Factors rhs) {
      return new Factors(spire.std.package.map$.MODULE$.MapCRng(spire.std.package.int$.MODULE$.IntAlgebra()).plus(this.elements(), rhs.elements()), this.sign().$times(rhs.sign()));
   }

   public Factors $times(final SafeLong rhs) {
      return this.$times(Factors$.MODULE$.apply(rhs));
   }

   public Tuple4 qm(final Factors rhs) {
      int sign = this.sign().$times(rhs.sign()).toInt();
      Tuple2 var5 = ((IterableOps)((Map)spire.std.package.map$.MODULE$.MapCRng(spire.std.package.int$.MODULE$.IntAlgebra()).minus(this.elements(), rhs.elements())).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$qm$1(x$3)))).partition((x$4) -> BoxesRunTime.boxToBoolean($anonfun$qm$2(x$4)));
      if (var5 != null) {
         Map nn = (Map)var5._1();
         Map dd = (Map)var5._2();
         Tuple2 var2 = new Tuple2(nn, dd);
         Map nn = (Map)var2._1();
         Map dd = (Map)var2._2();
         Map cc = (Map)this.elements().flatMap((x0$1) -> {
            if (x0$1 != null) {
               SafeLong p = (SafeLong)x0$1._1();
               int le = x0$1._2$mcI$sp();
               Iterator var2 = rhs.elements().get(p).iterator().map((re) -> $anonfun$qm$4(p, le, BoxesRunTime.unboxToInt(re)));
               return var2;
            } else {
               throw new MatchError(x0$1);
            }
         });
         return new Tuple4(BoxesRunTime.boxToInteger(sign), nn, dd.map((x0$2) -> {
            if (x0$2 != null) {
               SafeLong p = (SafeLong)x0$2._1();
               int e = x0$2._2$mcI$sp();
               Tuple2 var1 = new Tuple2(p, BoxesRunTime.boxToInteger(-e));
               return var1;
            } else {
               throw new MatchError(x0$2);
            }
         }), cc);
      } else {
         throw new MatchError(var5);
      }
   }

   public Factors $div(final Factors rhs) {
      Tuple4 var4 = this.qm(rhs);
      if (var4 != null) {
         int sign = BoxesRunTime.unboxToInt(var4._1());
         Map nn = (Map)var4._2();
         Map dd = (Map)var4._3();
         Map cc = (Map)var4._4();
         Tuple4 var2 = new Tuple4(BoxesRunTime.boxToInteger(sign), nn, dd, cc);
         int sign = BoxesRunTime.unboxToInt(var2._1());
         Map nn = (Map)var2._2();
         Map dd = (Map)var2._3();
         Map var12 = (Map)var2._4();
         return dd.isEmpty() ? new Factors(nn, spire.algebra.package$.MODULE$.Sign().apply(sign)) : Factors$.MODULE$.apply(this.prod(nn).$times((long)sign).$div(this.prod(dd)));
      } else {
         throw new MatchError(var4);
      }
   }

   public Factors $div(final SafeLong rhs) {
      boolean var3 = false;
      Some var4 = null;
      Option var5 = this.elements().get(rhs);
      Factors var2;
      if (var5 instanceof Some) {
         var3 = true;
         var4 = (Some)var5;
         int var6 = BoxesRunTime.unboxToInt(var4.value());
         if (1 == var6) {
            var2 = new Factors((Map)this.elements().$minus(rhs), this.sign());
            return var2;
         }
      }

      if (var3) {
         int n = BoxesRunTime.unboxToInt(var4.value());
         var2 = new Factors((Map)this.elements().updated(rhs, BoxesRunTime.boxToInteger(n - 1)), this.sign());
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         SafeLong n = this.value().$div(rhs);
         var2 = n.$less(rhs) ? Factors$.MODULE$.apply(n) : this.$div(Factors$.MODULE$.apply(rhs));
      }

      return var2;
   }

   public Factors $percent(final Factors rhs) {
      Tuple4 var4 = this.qm(rhs);
      if (var4 != null) {
         Map nn = (Map)var4._2();
         Map dd = (Map)var4._3();
         Map cc = (Map)var4._4();
         Tuple3 var2 = new Tuple3(nn, dd, cc);
         Map nn = (Map)var2._1();
         Map dd = (Map)var2._2();
         Map cc = (Map)var2._3();
         return dd.isEmpty() ? Factors$.MODULE$.zero() : Factors$.MODULE$.apply(this.prod(nn).$times((long)this.signum()).$percent(this.prod(dd)).$times(this.prod(cc)));
      } else {
         throw new MatchError(var4);
      }
   }

   public Factors $percent(final SafeLong rhs) {
      return this.$percent(Factors$.MODULE$.apply(rhs));
   }

   public Tuple2 $div$percent(final Factors rhs) {
      Tuple4 var5 = this.qm(rhs);
      if (var5 != null) {
         int sign = BoxesRunTime.unboxToInt(var5._1());
         Map nn = (Map)var5._2();
         Map dd = (Map)var5._3();
         Map cc = (Map)var5._4();
         Tuple4 var3 = new Tuple4(BoxesRunTime.boxToInteger(sign), nn, dd, cc);
         int sign = BoxesRunTime.unboxToInt(var3._1());
         Map nn = (Map)var3._2();
         Map dd = (Map)var3._3();
         Map cc = (Map)var3._4();
         Tuple2 var10000;
         if (dd.isEmpty()) {
            var10000 = new Tuple2(new Factors(nn, spire.algebra.package$.MODULE$.Sign().apply(sign)), Factors$.MODULE$.zero());
         } else {
            Tuple2 var15 = this.prod(nn).$div$percent(this.prod(dd));
            if (var15 == null) {
               throw new MatchError(var15);
            }

            SafeLong q = (SafeLong)var15._1();
            SafeLong m = (SafeLong)var15._2();
            Tuple2 var2 = new Tuple2(q, m);
            SafeLong q = (SafeLong)var2._1();
            SafeLong m = (SafeLong)var2._2();
            var10000 = new Tuple2(Factors$.MODULE$.apply(q).$times(SafeLong$.MODULE$.apply(sign)), Factors$.MODULE$.apply(m.$times(this.prod(cc))).$times(SafeLong$.MODULE$.apply(this.signum())));
         }

         return var10000;
      } else {
         throw new MatchError(var5);
      }
   }

   public Tuple2 $div$percent(final SafeLong rhs) {
      boolean var4 = false;
      Some var5 = null;
      Option var6 = this.elements().get(rhs);
      Tuple2 var2;
      if (var6 instanceof Some) {
         var4 = true;
         var5 = (Some)var6;
         int var7 = BoxesRunTime.unboxToInt(var5.value());
         if (1 == var7) {
            var2 = new Tuple2(new Factors((Map)this.elements().$minus(rhs), this.sign()), Factors$.MODULE$.zero());
            return var2;
         }
      }

      if (var4) {
         int n = BoxesRunTime.unboxToInt(var5.value());
         var2 = new Tuple2(new Factors((Map)this.elements().updated(rhs, BoxesRunTime.boxToInteger(n - 1)), this.sign()), Factors$.MODULE$.zero());
      } else {
         if (!scala.None..MODULE$.equals(var6)) {
            throw new MatchError(var6);
         }

         Tuple2 var10 = this.value().$div$percent(rhs);
         if (var10 == null) {
            throw new MatchError(var10);
         }

         SafeLong q = (SafeLong)var10._1();
         SafeLong m = (SafeLong)var10._2();
         Tuple2 var3 = new Tuple2(q, m);
         SafeLong q = (SafeLong)var3._1();
         SafeLong m = (SafeLong)var3._2();
         var2 = new Tuple2(Factors$.MODULE$.apply(q), Factors$.MODULE$.apply(m));
      }

      return var2;
   }

   public Factors pow(final int rhs) {
      if (rhs < 0) {
         throw new IllegalArgumentException("negative exponent");
      } else {
         Factors var10000;
         if (rhs == 0) {
            var10000 = Factors$.MODULE$.one();
         } else {
            Signed.Sign var4 = this.sign();
            Object sign;
            if (algebra.ring.Signed.Negative..MODULE$.equals(var4) && (rhs & 1) == 0) {
               sign = algebra.ring.Signed.Positive..MODULE$;
            } else {
               sign = var4;
            }

            var10000 = new Factors((Map)this.elements().map((x0$1) -> {
               if (x0$1 != null) {
                  SafeLong p = (SafeLong)x0$1._1();
                  int e = x0$1._2$mcI$sp();
                  Tuple2 var2 = new Tuple2(p, BoxesRunTime.boxToInteger(e * rhs));
                  return var2;
               } else {
                  throw new MatchError(x0$1);
               }
            }), (Signed.Sign)sign);
         }

         return var10000;
      }
   }

   public Factors copy(final Map elements, final Signed.Sign sign) {
      return new Factors(elements, sign);
   }

   public Map copy$default$1() {
      return this.elements();
   }

   public Signed.Sign copy$default$2() {
      return this.sign();
   }

   public String productPrefix() {
      return "Factors";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.elements();
            break;
         case 1:
            var10000 = this.sign();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Factors;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "elements";
            break;
         case 1:
            var10000 = "sign";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof Factors) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     Factors var4 = (Factors)x$1;
                     Map var10000 = this.elements();
                     Map var5 = var4.elements();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Signed.Sign var7 = this.sign();
                     Signed.Sign var6 = var4.sign();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   private final String terms$1() {
      return this.elements().isEmpty() ? "1" : ((IterableOnceOps)((IterableOps)this.elements().toSeq().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()), scala.math.Ordering.Int..MODULE$))).map((x0$1) -> {
         if (x0$1 != null) {
            SafeLong p = (SafeLong)x0$1._1();
            int e = x0$1._2$mcI$sp();
            String var1 = (new java.lang.StringBuilder(1)).append(p).append("^").append(e).toString();
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString(" * ");
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$gcd$2(final SafeLong p$1, final int le$1, final int re) {
      return new Tuple2(p$1, BoxesRunTime.boxToInteger(scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(le$1), re)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qm$1(final Tuple2 x$3) {
      return x$3._2$mcI$sp() != 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$qm$2(final Tuple2 x$4) {
      return x$4._2$mcI$sp() > 0;
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$qm$4(final SafeLong p$2, final int le$2, final int re) {
      return new Tuple2(p$2, BoxesRunTime.boxToInteger(scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(le$2), re)));
   }

   public Factors(final Map elements, final Signed.Sign sign) {
      this.elements = elements;
      this.sign = sign;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      Ordered.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
