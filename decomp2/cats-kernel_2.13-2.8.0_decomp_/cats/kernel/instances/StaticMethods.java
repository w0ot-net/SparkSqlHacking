package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.compat.WrappedMutableMapBase;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.IndexedSeqOps;
import scala.collection.IndexedSeqView;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.Seq;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t=q!\u0002\r\u001a\u0011\u0003\u0001c!\u0002\u0012\u001a\u0011\u0003\u0019\u0003\"\u0002\u0016\u0002\t\u0003Y\u0003\"\u0002\u0017\u0002\t\u0003ic!\u0002+\u0002\u0001m)\u0006\u0002\u0003'\u0005\u0005\u0003\u0005\u000b\u0011\u00020\t\u000b)\"A\u0011A0\t\u000b\r$A\u0011\t3\t\u000b!$A\u0011A5\t\u000b=$A\u0011\u00019\t\u000bu\fA\u0011\u0001@\u0007\u000f\u0005e\u0011\u0001A\u000e\u0002\u001c!IAj\u0003B\u0001B\u0003%\u00111\u0006\u0005\u0007U-!\t!!\f\t\r\u0005M2\u0002\"\u0011e\u0011\u001d\t)d\u0003C!\u0003oAaa\\\u0006\u0005B\u0005u\u0002bBA!\u0003\u0011\u0005\u00111\t\u0005\b\u0003G\nA\u0011AA3\u0011\u001d\t\u0019)\u0001C\u0001\u0003\u000bCq!a)\u0002\t\u0003\t)\u000bC\u0004\u0002L\u0006!\t!!4\t\u000f\u0005\u001d\u0018\u0001\"\u0001\u0002j\"9\u0011q^\u0001\u0005\u0002\u0005E\u0018!D*uCRL7-T3uQ>$7O\u0003\u0002\u001b7\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u00039u\taa[3s]\u0016d'\"\u0001\u0010\u0002\t\r\fGo]\u0002\u0001!\t\t\u0013!D\u0001\u001a\u00055\u0019F/\u0019;jG6+G\u000f[8egN\u0011\u0011\u0001\n\t\u0003K!j\u0011A\n\u0006\u0003Om\taaY8na\u0006$\u0018BA\u0015'\u0005)A\u0015m\u001d5D_6\u0004\u0018\r^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0001\nab\u001e:ba6+H/\u00192mK6\u000b\u0007/F\u0002/}%#\"aL&\u0011\tAJD\b\u0013\b\u0003c]\u0002\"AM\u001b\u000e\u0003MR!\u0001N\u0010\u0002\rq\u0012xn\u001c;?\u0015\u00051\u0014!B:dC2\f\u0017B\u0001\u001d6\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0004\u001b\u0006\u0004(B\u0001\u001d6!\tid\b\u0004\u0001\u0005\u000b}\u001a!\u0019\u0001!\u0003\u0003-\u000b\"!Q#\u0011\u0005\t\u001bU\"A\u001b\n\u0005\u0011+$a\u0002(pi\"Lgn\u001a\t\u0003\u0005\u001aK!aR\u001b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002>\u0013\u0012)!j\u0001b\u0001\u0001\n\ta\u000bC\u0003M\u0007\u0001\u0007Q*A\u0001n!\u0011q5\u000b\u0010%\u000e\u0003=S!\u0001U)\u0002\u000f5,H/\u00192mK*\u0011!+N\u0001\u000bG>dG.Z2uS>t\u0017B\u0001\u001eP\u0005E9&/\u00199qK\u0012lU\u000f^1cY\u0016l\u0015\r]\u000b\u0004-nk6C\u0001\u0003X!\u0011)\u0003L\u0017/\n\u0005e3#!F,sCB\u0004X\rZ'vi\u0006\u0014G.Z'ba\n\u000b7/\u001a\t\u0003{m#Qa\u0010\u0003C\u0002\u0001\u0003\"!P/\u0005\u000b)#!\u0019\u0001!\u0011\t9\u001b&\f\u0018\u000b\u0003A\n\u0004B!\u0019\u0003[96\t\u0011\u0001C\u0003M\r\u0001\u0007a,\u0001\u0003tSj,W#A3\u0011\u0005\t3\u0017BA46\u0005\rIe\u000e^\u0001\u0004O\u0016$HC\u00016n!\r\u00115\u000eX\u0005\u0003YV\u0012aa\u00149uS>t\u0007\"\u00028\t\u0001\u0004Q\u0016!A6\u0002\u0011%$XM]1u_J,\u0012!\u001d\t\u0004e^ThBA:v\u001d\t\u0011D/C\u00017\u0013\t1X'A\u0004qC\u000e\\\u0017mZ3\n\u0005aL(\u0001C%uKJ\fGo\u001c:\u000b\u0005Y,\u0004\u0003\u0002\"|5rK!\u0001`\u001b\u0003\rQ+\b\u000f\\33\u0003U9(/\u00199NkR\f'\r\\3J]\u0012,\u00070\u001a3TKF,2a`A\b)\u0011\t\t!a\u0005\u0011\r\u0005\r\u0011\u0011BA\u0007\u001b\t\t)AC\u0002\u0002\bE\u000b\u0011\"[7nkR\f'\r\\3\n\t\u0005-\u0011Q\u0001\u0002\u000b\u0013:$W\r_3e'\u0016\f\bcA\u001f\u0002\u0010\u00111\u0011\u0011\u0003\u0006C\u0002\u0001\u0013\u0011!\u0011\u0005\u0007\u0019*\u0001\r!!\u0006\u0011\u000b9\u000b9\"!\u0004\n\u0007\u0005-qJA\tXe\u0006\u0004\b/\u001a3J]\u0012,\u00070\u001a3TKF,B!!\b\u0002*M)1\"a\b\u0002&A\u0019!)!\t\n\u0007\u0005\rRG\u0001\u0004B]f\u0014VM\u001a\t\u0007\u0003\u0007\tI!a\n\u0011\u0007u\nI\u0003\u0002\u0004\u0002\u0012-\u0011\r\u0001\u0011\t\u0006\u001d\u0006]\u0011q\u0005\u000b\u0005\u0003_\t\t\u0004\u0005\u0003b\u0017\u0005\u001d\u0002B\u0002'\u000e\u0001\u0004\tY#\u0001\u0004mK:<G\u000f[\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003O\tI\u0004\u0003\u0004\u0002<=\u0001\r!Z\u0001\u0002SV\u0011\u0011q\b\t\u0005e^\f9#A\bji\u0016\u0014\u0018\r^8s\u0007>l\u0007/\u0019:f+\u0011\t)%a\u0016\u0015\r\u0005\u001d\u0013\u0011LA0)\r)\u0017\u0011\n\u0005\b\u0003\u0017\n\u00029AA'\u0003\t)g\u000f\u0005\u0004\u0002P\u0005E\u0013QK\u0007\u00027%\u0019\u00111K\u000e\u0003\u000b=\u0013H-\u001a:\u0011\u0007u\n9\u0006\u0002\u0004\u0002\u0012E\u0011\r\u0001\u0011\u0005\b\u00037\n\u0002\u0019AA/\u0003\tA8\u000f\u0005\u0003so\u0006U\u0003bBA1#\u0001\u0007\u0011QL\u0001\u0003sN\fa#\u001b;fe\u0006$xN\u001d)beRL\u0017\r\\\"p[B\f'/Z\u000b\u0005\u0003O\nY\b\u0006\u0004\u0002j\u0005u\u0014\u0011\u0011\u000b\u0005\u0003W\n\t\bE\u0002C\u0003[J1!a\u001c6\u0005\u0019!u.\u001e2mK\"9\u00111\n\nA\u0004\u0005M\u0004CBA(\u0003k\nI(C\u0002\u0002xm\u0011A\u0002U1si&\fGn\u0014:eKJ\u00042!PA>\t\u0019\t\tB\u0005b\u0001\u0001\"9\u00111\f\nA\u0002\u0005}\u0004\u0003\u0002:x\u0003sBq!!\u0019\u0013\u0001\u0004\ty(\u0001\u0006ji\u0016\u0014\u0018\r^8s\u000bF,B!a\"\u0002\u001cR1\u0011\u0011RAO\u0003C#B!a#\u0002\u0012B\u0019!)!$\n\u0007\u0005=UGA\u0004C_>dW-\u00198\t\u000f\u0005-3\u0003q\u0001\u0002\u0014B1\u0011qJAK\u00033K1!a&\u001c\u0005\t)\u0015\u000fE\u0002>\u00037#a!!\u0005\u0014\u0005\u0004\u0001\u0005bBA.'\u0001\u0007\u0011q\u0014\t\u0005e^\fI\nC\u0004\u0002bM\u0001\r!a(\u0002!\r|WNY5oK:KE/\u001a:bE2,WCBAT\u0003w\u000bY\u000b\u0006\u0005\u0002*\u0006=\u0016QXAd!\ri\u00141\u0016\u0003\u0007\u0003[#\"\u0019\u0001!\u0003\u0003ICq!!-\u0015\u0001\u0004\t\u0019,A\u0001c!\u001dq\u0015QWA]\u0003SK1!a.P\u0005\u001d\u0011U/\u001b7eKJ\u00042!PA^\t\u0019\t\t\u0002\u0006b\u0001\u0001\"9\u0011q\u0018\u000bA\u0002\u0005\u0005\u0017!\u0001=\u0011\u000bI\f\u0019-!/\n\u0007\u0005\u0015\u0017P\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0011\u0019\tI\r\u0006a\u0001K\u0006\ta.\u0001\nd_6\u0014\u0017N\\3BY2LE/\u001a:bE2,WCBAh\u00037\f\u0019\u000e\u0006\u0004\u0002R\u0006U\u0017Q\u001c\t\u0004{\u0005MGABAW+\t\u0007\u0001\tC\u0004\u00022V\u0001\r!a6\u0011\u000f9\u000b),!7\u0002RB\u0019Q(a7\u0005\r\u0005EQC1\u0001A\u0011\u001d\tY&\u0006a\u0001\u0003?\u0004RA]Aq\u0003KL1!a9z\u00051IE/\u001a:bE2,wJ\\2f!\u0015\u0011\u00181YAm\u00031\u0001(o\u001c3vGR\f\u0004*Y:i)\r)\u00171\u001e\u0005\u0007\u0003[4\u0002\u0019A3\u0002\r}\u000b\u0004*Y:i\u00031\u0001(o\u001c3vGR\u0014\u0004*Y:i)\u0015)\u00171_A{\u0011\u0019\tio\u0006a\u0001K\"1\u0011q_\fA\u0002\u0015\faa\u0018\u001aICND\u0007fA\u0001\u0002|B!\u0011Q B\u0004\u001d\u0011\tyPa\u0001\u000f\t\u0005=#\u0011A\u0005\u0003OmI1A!\u0002'\u0003Q\u00198-\u00197b-\u0016\u00148/[8o'B,7-\u001b4jG&!!\u0011\u0002B\u0006\u0005I\u001aX\u000f\u001d9sKN\u001cXK\\;tK\u0012LU\u000e]8si^\u000b'O\\5oO\u001a{'oU2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001c'b\u0001B\u0003M!\u001a\u0001!a?"
)
public final class StaticMethods {
   public static int product2Hash(final int _1Hash, final int _2Hash) {
      return StaticMethods$.MODULE$.product2Hash(_1Hash, _2Hash);
   }

   public static int product1Hash(final int _1Hash) {
      return StaticMethods$.MODULE$.product1Hash(_1Hash);
   }

   public static Object combineAllIterable(final Builder b, final IterableOnce xs) {
      return StaticMethods$.MODULE$.combineAllIterable(b, xs);
   }

   public static Object combineNIterable(final Builder b, final Iterable x, final int n) {
      return StaticMethods$.MODULE$.combineNIterable(b, x, n);
   }

   public static boolean iteratorEq(final Iterator xs, final Iterator ys, final Eq ev) {
      return StaticMethods$.MODULE$.iteratorEq(xs, ys, ev);
   }

   public static double iteratorPartialCompare(final Iterator xs, final Iterator ys, final PartialOrder ev) {
      return StaticMethods$.MODULE$.iteratorPartialCompare(xs, ys, ev);
   }

   public static int iteratorCompare(final Iterator xs, final Iterator ys, final Order ev) {
      return StaticMethods$.MODULE$.iteratorCompare(xs, ys, ev);
   }

   public static IndexedSeq wrapMutableIndexedSeq(final scala.collection.mutable.IndexedSeq m) {
      return StaticMethods$.MODULE$.wrapMutableIndexedSeq(m);
   }

   public static Map wrapMutableMap(final scala.collection.mutable.Map m) {
      return StaticMethods$.MODULE$.wrapMutableMap(m);
   }

   public static int orderedHash(final IterableOnce xs, final Hash A) {
      return StaticMethods$.MODULE$.orderedHash(xs, A);
   }

   public static int listHash(final List x, final Hash A) {
      return StaticMethods$.MODULE$.listHash(x, A);
   }

   public static class WrappedMutableMap extends WrappedMutableMapBase {
      private final scala.collection.mutable.Map m;

      public int size() {
         return this.m.size();
      }

      public Option get(final Object k) {
         return this.m.get(k);
      }

      public Iterator iterator() {
         return this.m.iterator();
      }

      public WrappedMutableMap(final scala.collection.mutable.Map m) {
         super(m);
         this.m = m;
      }
   }

   public static class WrappedIndexedSeq implements IndexedSeq {
      private final scala.collection.mutable.IndexedSeq m;

      // $FF: synthetic method
      public boolean scala$collection$immutable$IndexedSeq$$super$canEqual(final Object that) {
         return Seq.canEqual$(this, that);
      }

      // $FF: synthetic method
      public boolean scala$collection$immutable$IndexedSeq$$super$sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public final IndexedSeq toIndexedSeq() {
         return IndexedSeq.toIndexedSeq$(this);
      }

      public boolean canEqual(final Object that) {
         return IndexedSeq.canEqual$(this, that);
      }

      public boolean sameElements(final IterableOnce o) {
         return IndexedSeq.sameElements$(this, o);
      }

      public int applyPreferredMaxLength() {
         return IndexedSeq.applyPreferredMaxLength$(this);
      }

      public SeqFactory iterableFactory() {
         return IndexedSeq.iterableFactory$(this);
      }

      // $FF: synthetic method
      public Object scala$collection$immutable$IndexedSeqOps$$super$slice(final int from, final int until) {
         return IndexedSeqOps.slice$(this, from, until);
      }

      public Object slice(final int from, final int until) {
         return scala.collection.immutable.IndexedSeqOps.slice$(this, from, until);
      }

      public String stringPrefix() {
         return scala.collection.IndexedSeq.stringPrefix$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IndexedSeqOps.stepper$(this, shape);
      }

      public Iterator reverseIterator() {
         return IndexedSeqOps.reverseIterator$(this);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IndexedSeqOps.foldRight$(this, z, op);
      }

      public IndexedSeqView view() {
         return IndexedSeqOps.view$(this);
      }

      /** @deprecated */
      public IndexedSeqView view(final int from, final int until) {
         return IndexedSeqOps.view$(this, from, until);
      }

      public Iterable reversed() {
         return IndexedSeqOps.reversed$(this);
      }

      public Object prepended(final Object elem) {
         return IndexedSeqOps.prepended$(this, elem);
      }

      public Object take(final int n) {
         return IndexedSeqOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IndexedSeqOps.takeRight$(this, n);
      }

      public Object drop(final int n) {
         return IndexedSeqOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IndexedSeqOps.dropRight$(this, n);
      }

      public Object map(final Function1 f) {
         return IndexedSeqOps.map$(this, f);
      }

      public Object reverse() {
         return IndexedSeqOps.reverse$(this);
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

      public final scala.collection.immutable.Seq toSeq() {
         return scala.collection.immutable.Seq.toSeq$(this);
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

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
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
         return PartialFunction.andThen$(this, k);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, k);
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

      /** @deprecated */
      public final Iterable toIterable() {
         return Iterable.toIterable$(this);
      }

      public final Iterable coll() {
         return Iterable.coll$(this);
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

      public Option lastOption() {
         return IterableOps.lastOption$(this);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
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

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
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

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
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

      public int length() {
         return this.m.length();
      }

      public Object apply(final int i) {
         return this.m.apply(i);
      }

      public Iterator iterator() {
         return this.m.iterator();
      }

      public WrappedIndexedSeq(final scala.collection.mutable.IndexedSeq m) {
         this.m = m;
         IterableOnce.$init$(this);
         IterableOnceOps.$init$(this);
         IterableOps.$init$(this);
         IterableFactoryDefaults.$init$(this);
         Iterable.$init$(this);
         scala.collection.immutable.Iterable.$init$(this);
         Function1.$init$(this);
         PartialFunction.$init$(this);
         SeqOps.$init$(this);
         Seq.$init$(this);
         scala.collection.immutable.Seq.$init$(this);
         IndexedSeqOps.$init$(this);
         scala.collection.IndexedSeq.$init$(this);
         scala.collection.immutable.IndexedSeqOps.$init$(this);
         IndexedSeq.$init$(this);
      }
   }
}
