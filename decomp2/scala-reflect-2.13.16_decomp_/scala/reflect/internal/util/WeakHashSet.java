package scala.reflect.internal.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIndexedSeqView;
import scala.collection.AbstractIterator;
import scala.collection.Factory;
import scala.collection.IndexedSeqView;
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
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.SetOps;
import scala.collection.mutable.Shrinkable;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.RichDouble;

@ScalaSignature(
   bytes = "\u0006\u0005\tMc\u0001\u0002\u001f>\u0005\u0019C\u0001\u0002\u0018\u0001\u0003\u0006\u0004%\t!\u0018\u0005\tC\u0002\u0011\t\u0011)A\u0005=\"A!\r\u0001BC\u0002\u0013\u00051\r\u0003\u0005h\u0001\t\u0005\t\u0015!\u0003e\u0011\u0015A\u0007\u0001\"\u0001j\u0011\u0015A\u0007\u0001\"\u0001o\u000b\u0011y\u0007\u0001\u00016\t\rA\u0004\u0001\u0015!\u0003r\u0011\u0019Y\b\u0001)Q\u0005=\")A\u0010\u0001C\u0005;\"1Q\u0010\u0001Q!\nyDq!a'\u0001A\u0003&a\fC\u0004\u0002\u001e\u0002\u0001K\u0011B/\t\u000f\u0005}\u0005\u0001\"\u0001\u0002\"\"A\u0011Q\u0016\u0001!\n\u0013\ty\u000b\u0003\u0005\u00024\u0002\u0001K\u0011BA[\u0011!\t\u0019\r\u0001Q\u0005\n\u0005\u0015\u0007\u0002CAd\u0001\u0001&I!!2\t\u000f\u0005%\u0007\u0001\"\u0001\u0002L\"9\u0011Q\u001b\u0001\u0005\u0002\u0005]\u0007bBAn\u0001\u0011\u0005\u0011Q\u001c\u0005\b\u0003C\u0004A\u0011IAr\u0011\u001d\tI\u000f\u0001C!\u0003WDq!a<\u0001\t\u0003\n\t\u0010C\u0004\u0002v\u0002!\t%!2\t\u000f\u0005=\u0005\u0001\"\u0011\u0002x\"1\u00111 \u0001\u0005BuCq!!@\u0001\t\u0003\ny\u0010C\u0004\u0003\u0002\u0001!\tEa\u0001\t\u000f\t]\u0001\u0001\"\u0011\u0003\u001a!9!\u0011\u0006\u0001\u0005B\t-ba\u0002B\u001a\u0001\u0001i$Q\u0007\u0005\u0007Q\u0002\"\tAa\u000e\t\u000f\tm\u0002\u0005\"\u0001\u0002F\"9!Q\b\u0011\u0005\u0002\t}\u0002B\u0002B%A\u0011\u0005Q\f\u0003\u0004\u0003L\u0001\"\t!\u0018\u0005\u0007\u0005\u001b\u0002C\u0011A/\t\u0011\t=\u0003\u0001\"\u0001>\u0005#:q!a\u0002>\u0011\u0003\tIA\u0002\u0004={!\u0005\u00111\u0002\u0005\u0007Q&\"\t!!\u0004\u0007\r\u0005=\u0011\u0006BA\t\u0011)\t9c\u000bB\u0001B\u0003%\u00111\u0004\u0005\n\u0003SY#Q1A\u0005\u0002uC\u0011\"a\u000b,\u0005\u0003\u0005\u000b\u0011\u00020\t\u0015\u000552F!a\u0001\n\u0003\ty\u0003\u0003\u0006\u00026-\u0012\t\u0019!C\u0001\u0003oA!\"a\u0011,\u0005\u0003\u0005\u000b\u0015BA\u0019\u0011%\u00018F!A!\u0002\u0013\t)\u0005\u0003\u0004iW\u0011\u0005\u0011q\t\u0005\t\u0003#J#\u0019!C\u0001;\"9\u00111K\u0015!\u0002\u0013q\u0006\u0002CA+S\t\u0007I\u0011A2\t\u000f\u0005]\u0013\u0006)A\u0005I\"9\u0011\u0011L\u0015\u0005\u0002\u0005m\u0003\"CA5SE\u0005I\u0011AA6\u0011%\t))KI\u0001\n\u0003\t9\tC\u0004\u0002\u0010&\"\t!!%\u0003\u0017]+\u0017m\u001b%bg\"\u001cV\r\u001e\u0006\u0003}}\nA!\u001e;jY*\u0011\u0001)Q\u0001\tS:$XM\u001d8bY*\u0011!iQ\u0001\be\u00164G.Z2u\u0015\u0005!\u0015!B:dC2\f7\u0001A\u000b\u0003\u000fZ\u001b2\u0001\u0001%M!\tI%*D\u0001D\u0013\tY5I\u0001\u0004B]f\u0014VM\u001a\t\u0004\u001bJ#V\"\u0001(\u000b\u0005=\u0003\u0016aB7vi\u0006\u0014G.\u001a\u0006\u0003#\u000e\u000b!bY8mY\u0016\u001cG/[8o\u0013\t\u0019fJA\u0002TKR\u0004\"!\u0016,\r\u0001\u0011)q\u000b\u0001b\u00011\n\t\u0011)\u0005\u0002Z\u0011B\u0011\u0011JW\u0005\u00037\u000e\u0013qAT8uQ&tw-A\bj]&$\u0018.\u00197DCB\f7-\u001b;z+\u0005q\u0006CA%`\u0013\t\u00017IA\u0002J]R\f\u0001#\u001b8ji&\fGnQ1qC\u000eLG/\u001f\u0011\u0002\u00151|\u0017\r\u001a$bGR|'/F\u0001e!\tIU-\u0003\u0002g\u0007\n1Ai\\;cY\u0016\f1\u0002\\8bI\u001a\u000b7\r^8sA\u00051A(\u001b8jiz\"2A\u001b7n!\rY\u0007\u0001V\u0007\u0002{!)A,\u0002a\u0001=\")!-\u0002a\u0001IR\t!N\u0001\u0003UQ&\u001c\u0018!B9vKV,\u0007c\u0001:z)6\t1O\u0003\u0002uk\u0006\u0019!/\u001a4\u000b\u0005Y<\u0018\u0001\u00027b]\u001eT\u0011\u0001_\u0001\u0005U\u00064\u0018-\u0003\u0002{g\nq!+\u001a4fe\u0016t7-Z)vKV,\u0017!B2pk:$\u0018aD2p[B,H/Z\"ba\u0006\u001c\u0017\u000e^=\u0002\u000bQ\f'\r\\3\u0011\t%{\u00181A\u0005\u0004\u0003\u0003\u0019%!B!se\u0006L\b\u0003BA\u0003WQs!a\u001b\u0015\u0002\u0017]+\u0017m\u001b%bg\"\u001cV\r\u001e\t\u0003W&\u001a\"!\u000b%\u0015\u0005\u0005%!!B#oiJLX\u0003BA\n\u0003;\u00192aKA\u000b!\u0015\u0011\u0018qCA\u000e\u0013\r\tIb\u001d\u0002\u000e/\u0016\f7NU3gKJ,gnY3\u0011\u0007U\u000bi\u0002\u0002\u0004XW\t\u0007\u0011qD\t\u00043\u0006\u0005\u0002cA%\u0002$%\u0019\u0011QE\"\u0003\u0007\u0005s\u00170A\u0004fY\u0016lWM\u001c;\u0002\t!\f7\u000f[\u0001\u0006Q\u0006\u001c\b\u000eI\u0001\u0005i\u0006LG.\u0006\u0002\u00022A)\u00111G\u0016\u0002\u001c5\t\u0011&\u0001\u0005uC&dw\fJ3r)\u0011\tI$a\u0010\u0011\u0007%\u000bY$C\u0002\u0002>\r\u0013A!\u00168ji\"I\u0011\u0011\t\u0019\u0002\u0002\u0003\u0007\u0011\u0011G\u0001\u0004q\u0012\n\u0014!\u0002;bS2\u0004\u0003\u0003\u0002:z\u00037!\"\"!\r\u0002J\u0005-\u0013QJA(\u0011\u001d\t9c\ra\u0001\u00037Aa!!\u000b4\u0001\u0004q\u0006bBA\u0017g\u0001\u0007\u0011\u0011\u0007\u0005\u0007aN\u0002\r!!\u0012\u0002-\u0011,g-Y;mi&s\u0017\u000e^5bY\u000e\u000b\u0007/Y2jif\fq\u0003Z3gCVdG/\u00138ji&\fGnQ1qC\u000eLG/\u001f\u0011\u0002#\u0011,g-Y;mi2{\u0017\r\u001a$bGR|'/\u0001\neK\u001a\fW\u000f\u001c;M_\u0006$g)Y2u_J\u0004\u0013!B1qa2LX\u0003BA/\u0003G\"b!a\u0018\u0002f\u0005\u001d\u0004\u0003B6\u0001\u0003C\u00022!VA2\t\u00159\u0006H1\u0001Y\u0011\u001da\u0006\b%AA\u0002yCqA\u0019\u001d\u0011\u0002\u0003\u0007A-A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\ti'a!\u0016\u0005\u0005=$f\u00010\u0002r-\u0012\u00111\u000f\t\u0005\u0003k\ny(\u0004\u0002\u0002x)!\u0011\u0011PA>\u0003%)hn\u00195fG.,GMC\u0002\u0002~\r\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t\t)a\u001e\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003Xs\t\u0007\u0001,A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\tI)!$\u0016\u0005\u0005-%f\u00013\u0002r\u0011)qK\u000fb\u00011\u0006)Q-\u001c9usV!\u00111SAM+\t\t)\n\u0005\u0003l\u0001\u0005]\u0005cA+\u0002\u001a\u0012)qk\u000fb\u00011\u0006IA\u000f\u001b:fg\"|G\u000eZ\u0001\u0011G>l\u0007/\u001e;f)\"\u0014Xm\u001d5pY\u0012\f1aZ3u)\u0011\t\u0019+!+\u0011\t%\u000b)\u000bV\u0005\u0004\u0003O\u001b%AB(qi&|g\u000e\u0003\u0004\u0002,:\u0001\r\u0001V\u0001\u0005K2,W.A\u0005ck\u000e\\W\r\u001e$peR\u0019a,!-\t\r\u0005%r\u00021\u0001_\u0003\u0019\u0011X-\\8wKRA\u0011\u0011HA\\\u0003w\u000by\f\u0003\u0004\u0002:B\u0001\rAX\u0001\u0007EV\u001c7.\u001a;\t\u000f\u0005u\u0006\u00031\u0001\u0002\u0004\u0005I\u0001O]3w\u000b:$(/\u001f\u0005\b\u0003\u0003\u0004\u0002\u0019AA\u0002\u0003\u0015)g\u000e\u001e:z\u0003I\u0011X-\\8wKN#\u0018\r\\3F]R\u0014\u0018.Z:\u0015\u0005\u0005e\u0012A\u0002:fg&TX-\u0001\u0005d_:$\u0018-\u001b8t)\u0011\ti-a5\u0011\u0007%\u000by-C\u0002\u0002R\u000e\u0013qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002,N\u0001\r\u0001V\u0001\nM&tG-\u00128uef$2\u0001VAm\u0011\u0019\tY\u000b\u0006a\u0001)\u0006\tb-\u001b8e\u000b:$(/_(s+B$\u0017\r^3\u0015\u0007Q\u000by\u000e\u0003\u0004\u0002,V\u0001\r\u0001V\u0001\u0007C\u0012$wJ\\3\u0015\t\u0005\u0015\u0018q]\u0007\u0002\u0001!1\u00111\u0016\fA\u0002Q\u000b1b];ciJ\f7\r^(oKR!\u0011Q]Aw\u0011\u0019\tYk\u0006a\u0001)\u00061A%\\5okN$2A[Az\u0011\u0019\tY\u000b\u0007a\u0001)\u0006)1\r\\3beV\u0011\u0011\u0011 \t\u0004\u0003K<\u0011\u0001B:ju\u0016\fq![:F[B$\u00180\u0006\u0002\u0002N\u00069am\u001c:fC\u000eDW\u0003\u0002B\u0003\u0005'!B!!\u000f\u0003\b!9!\u0011B\u000fA\u0002\t-\u0011!\u00014\u0011\r%\u0013i\u0001\u0016B\t\u0013\r\u0011ya\u0011\u0002\n\rVt7\r^5p]F\u00022!\u0016B\n\t\u001d\u0011)\"\bb\u0001\u0003?\u0011\u0011!V\u0001\u0007i>d\u0015n\u001d;\u0016\u0005\tm\u0001#\u0002B\u000f\u0005G!fbA%\u0003 %\u0019!\u0011E\"\u0002\u000fA\f7m[1hK&!!Q\u0005B\u0014\u0005\u0011a\u0015n\u001d;\u000b\u0007\t\u00052)\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\u0011i\u0003E\u0003\u0003\u001e\t=B+\u0003\u0003\u00032\t\u001d\"\u0001C%uKJ\fGo\u001c:\u0003\u0017\u0011K\u0017m\u001a8pgRL7m]\n\u0003A!#\"A!\u000f\u0011\u0007\u0005\u0015\b%A\u0007gk2d\u0017PV1mS\u0012\fG/Z\u0001\u0005IVl\u0007/\u0006\u0002\u0003BA1!1\tB#\u0003Ci\u0011\u0001U\u0005\u0004\u0005\u000f\u0002&AD%oI\u0016DX\rZ*fcZKWm^\u0001\u0016G>dG.[:j_:\u0014UoY6fiN\u001cu.\u001e8u\u0003A1W\u000f\u001c7Ck\u000e\\W\r^:D_VtG/\u0001\u0007ck\u000e\\W\r^:D_VtG/A\u0006eS\u0006<gn\\:uS\u000e\u001cXC\u0001B\u001d\u0001"
)
public final class WeakHashSet implements scala.collection.mutable.Set {
   private final int initialCapacity;
   private final double loadFactor;
   private final ReferenceQueue queue;
   public int scala$reflect$internal$util$WeakHashSet$$count;
   public Entry[] scala$reflect$internal$util$WeakHashSet$$table;
   private int threshold;

   public static double apply$default$2() {
      return WeakHashSet$.MODULE$.defaultLoadFactor();
   }

   public static int apply$default$1() {
      return WeakHashSet$.MODULE$.defaultInitialCapacity();
   }

   public static double defaultLoadFactor() {
      return WeakHashSet$.MODULE$.defaultLoadFactor();
   }

   public static int defaultInitialCapacity() {
      return WeakHashSet$.MODULE$.defaultInitialCapacity();
   }

   public IterableFactory iterableFactory() {
      return scala.collection.mutable.Set.iterableFactory$(this);
   }

   public SetOps result() {
      return SetOps.result$(this);
   }

   public boolean add(final Object elem) {
      return SetOps.add$(this, elem);
   }

   public void update(final Object elem, final boolean included) {
      SetOps.update$(this, elem, included);
   }

   public boolean remove(final Object elem) {
      return SetOps.remove$(this, elem);
   }

   public SetOps diff(final scala.collection.Set that) {
      return SetOps.diff$(this, that);
   }

   /** @deprecated */
   public final void retain(final Function1 p) {
      SetOps.retain$(this, p);
   }

   public SetOps filterInPlace(final Function1 p) {
      return SetOps.filterInPlace$(this, p);
   }

   public SetOps clone() {
      return SetOps.clone$(this);
   }

   public int knownSize() {
      return SetOps.knownSize$(this);
   }

   public final Shrinkable $minus$eq(final Object elem) {
      return Shrinkable.$minus$eq$(this, elem);
   }

   /** @deprecated */
   public Shrinkable $minus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Shrinkable.$minus$eq$(this, elem1, elem2, elems);
   }

   public Shrinkable subtractAll(final IterableOnce xs) {
      return Shrinkable.subtractAll$(this, xs);
   }

   public final Shrinkable $minus$minus$eq(final IterableOnce xs) {
      return Shrinkable.$minus$minus$eq$(this, xs);
   }

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
   }

   public boolean canEqual(final Object that) {
      return scala.collection.Set.canEqual$(this, that);
   }

   public boolean equals(final Object that) {
      return scala.collection.Set.equals$(this, that);
   }

   public int hashCode() {
      return scala.collection.Set.hashCode$(this);
   }

   public String stringPrefix() {
      return scala.collection.Set.stringPrefix$(this);
   }

   public String toString() {
      return scala.collection.Set.toString$(this);
   }

   public final boolean apply(final Object elem) {
      return scala.collection.SetOps.apply$(this, elem);
   }

   public boolean subsetOf(final scala.collection.Set that) {
      return scala.collection.SetOps.subsetOf$(this, that);
   }

   public Iterator subsets(final int len) {
      return scala.collection.SetOps.subsets$(this, len);
   }

   public Iterator subsets() {
      return scala.collection.SetOps.subsets$(this);
   }

   public scala.collection.SetOps intersect(final scala.collection.Set that) {
      return scala.collection.SetOps.intersect$(this, that);
   }

   public final scala.collection.SetOps $amp(final scala.collection.Set that) {
      return scala.collection.SetOps.$amp$(this, that);
   }

   public final scala.collection.SetOps $amp$tilde(final scala.collection.Set that) {
      return scala.collection.SetOps.$amp$tilde$(this, that);
   }

   /** @deprecated */
   public scala.collection.SetOps $minus$minus(final IterableOnce that) {
      return scala.collection.SetOps.$minus$minus$(this, that);
   }

   /** @deprecated */
   public scala.collection.SetOps $minus(final Object elem1, final Object elem2, final Seq elems) {
      return scala.collection.SetOps.$minus$(this, elem1, elem2, elems);
   }

   public scala.collection.SetOps concat(final IterableOnce that) {
      return scala.collection.SetOps.concat$(this, that);
   }

   /** @deprecated */
   public scala.collection.SetOps $plus(final Object elem) {
      return scala.collection.SetOps.$plus$(this, elem);
   }

   /** @deprecated */
   public scala.collection.SetOps $plus(final Object elem1, final Object elem2, final Seq elems) {
      return scala.collection.SetOps.$plus$(this, elem1, elem2, elems);
   }

   public final scala.collection.SetOps $plus$plus(final IterableOnce that) {
      return scala.collection.SetOps.$plus$plus$(this, that);
   }

   public final scala.collection.SetOps union(final scala.collection.Set that) {
      return scala.collection.SetOps.union$(this, that);
   }

   public final scala.collection.SetOps $bar(final scala.collection.Set that) {
      return scala.collection.SetOps.$bar$(this, that);
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

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
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

   public Object maxBy(final Function1 f, final Ordering ord) {
      return IterableOnceOps.maxBy$(this, f, ord);
   }

   public Option maxByOption(final Function1 f, final Ordering ord) {
      return IterableOnceOps.maxByOption$(this, f, ord);
   }

   public Object minBy(final Function1 f, final Ordering ord) {
      return IterableOnceOps.minBy$(this, f, ord);
   }

   public Option minByOption(final Function1 f, final Ordering ord) {
      return IterableOnceOps.minByOption$(this, f, ord);
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

   public Vector toVector() {
      return IterableOnceOps.toVector$(this);
   }

   public Map toMap(final .less.colon.less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public scala.collection.immutable.Set toSet() {
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

   public int initialCapacity() {
      return this.initialCapacity;
   }

   public double loadFactor() {
      return this.loadFactor;
   }

   private int computeCapacity() {
      if (this.initialCapacity() < 0) {
         throw new IllegalArgumentException("initial capacity cannot be less than 0");
      } else {
         int candidate;
         for(candidate = 1; candidate < this.initialCapacity(); candidate *= 2) {
         }

         return candidate;
      }
   }

   private int computeThreshold() {
      RichDouble var10000 = scala.runtime.RichDouble..MODULE$;
      double var1 = (double)this.scala$reflect$internal$util$WeakHashSet$$table.length * this.loadFactor();
      scala.math.package var3 = scala.math.package..MODULE$;
      return (int)Math.ceil(var1);
   }

   public Option get(final Object elem) {
      return scala.Option..MODULE$.apply(this.findEntry(elem));
   }

   public int scala$reflect$internal$util$WeakHashSet$$bucketFor(final int hash) {
      int h = hash ^ hash >>> 20 ^ hash >>> 12;
      h ^= h >>> 7 ^ h >>> 4;
      return h & this.scala$reflect$internal$util$WeakHashSet$$table.length - 1;
   }

   private void remove(final int bucket, final Entry prevEntry, final Entry entry) {
      if (prevEntry == null) {
         this.scala$reflect$internal$util$WeakHashSet$$table[bucket] = entry.tail();
      } else {
         prevEntry.tail_$eq(entry.tail());
      }

      --this.scala$reflect$internal$util$WeakHashSet$$count;
   }

   private void removeStaleEntries() {
      this.queueLoop$1();
   }

   private void resize() {
      Entry[] oldTable = this.scala$reflect$internal$util$WeakHashSet$$table;
      this.scala$reflect$internal$util$WeakHashSet$$table = new Entry[oldTable.length * 2];
      this.threshold = this.computeThreshold();
      this.tableLoop$1(0, oldTable);
   }

   public boolean contains(final Object elem) {
      return this.findEntry(elem) != null;
   }

   public Object findEntry(final Object elem) {
      if (elem == null) {
         throw new NullPointerException("WeakHashSet cannot hold nulls");
      } else {
         this.removeStaleEntries();
         int hash = elem.hashCode();
         int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(hash);
         return this.linkedListLoop$3(this.scala$reflect$internal$util$WeakHashSet$$table[bucket], elem);
      }
   }

   public Object findEntryOrUpdate(final Object elem) {
      if (elem == null) {
         throw new NullPointerException("WeakHashSet cannot hold nulls");
      } else {
         this.removeStaleEntries();
         int hash = elem.hashCode();
         int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(hash);
         Entry oldHead = this.scala$reflect$internal$util$WeakHashSet$$table[bucket];
         return this.linkedListLoop$4(oldHead, elem, bucket, hash, oldHead);
      }
   }

   public WeakHashSet addOne(final Object elem) {
      if (elem == null) {
         throw new NullPointerException("WeakHashSet cannot hold nulls");
      } else {
         this.removeStaleEntries();
         int hash = elem.hashCode();
         int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(hash);
         Entry oldHead = this.scala$reflect$internal$util$WeakHashSet$$table[bucket];
         this.linkedListLoop$5(oldHead, elem, bucket, hash, oldHead);
         return this;
      }
   }

   public WeakHashSet subtractOne(final Object elem) {
      if (elem == null) {
         return this;
      } else {
         this.removeStaleEntries();
         int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(elem.hashCode());
         this.linkedListLoop$6((Entry)null, this.scala$reflect$internal$util$WeakHashSet$$table[bucket], elem, bucket);
         return this;
      }
   }

   public WeakHashSet $minus(final Object elem) {
      return this.subtractOne(elem);
   }

   public void clear() {
      this.scala$reflect$internal$util$WeakHashSet$$table = new Entry[this.scala$reflect$internal$util$WeakHashSet$$table.length];
      this.threshold = this.computeThreshold();
      this.scala$reflect$internal$util$WeakHashSet$$count = 0;
      this.queueLoop$2();
   }

   public WeakHashSet empty() {
      return new WeakHashSet(this.initialCapacity(), this.loadFactor());
   }

   public int size() {
      this.removeStaleEntries();
      return this.scala$reflect$internal$util$WeakHashSet$$count;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void foreach(final Function1 f) {
      this.iterator().foreach(f);
   }

   public List toList() {
      return this.iterator().toList();
   }

   public Iterator iterator() {
      this.removeStaleEntries();
      return new AbstractIterator() {
         private int currentBucket;
         private Entry entry;
         private Object lookaheadelement;
         // $FF: synthetic field
         private final WeakHashSet $outer;

         public boolean hasNext() {
            while(true) {
               if (this.entry == null && this.currentBucket > 0) {
                  --this.currentBucket;
                  this.entry = this.$outer.scala$reflect$internal$util$WeakHashSet$$table[this.currentBucket];
               } else {
                  if (this.entry == null) {
                     return false;
                  }

                  this.lookaheadelement = this.entry.get();
                  if (this.lookaheadelement != null) {
                     return true;
                  }

                  this.entry = this.entry.tail();
               }
            }
         }

         public Object next() {
            if (this.lookaheadelement == null) {
               throw new IndexOutOfBoundsException("next on an empty iterator");
            } else {
               Object result = this.lookaheadelement;
               this.lookaheadelement = null;
               this.entry = this.entry.tail();
               return result;
            }
         }

         public {
            if (WeakHashSet.this == null) {
               throw null;
            } else {
               this.$outer = WeakHashSet.this;
               this.currentBucket = WeakHashSet.this.scala$reflect$internal$util$WeakHashSet$$table.length;
               this.entry = null;
               this.lookaheadelement = null;
            }
         }
      };
   }

   public Diagnostics diagnostics() {
      return new Diagnostics();
   }

   private final Entry poll$1() {
      return (Entry)this.queue.poll();
   }

   private final void linkedListLoop$1(final Entry prevEntry, final Entry entry, final Entry stale$1, final int bucket$1) {
      while(stale$1 != entry) {
         if (entry == null) {
            return;
         }

         Entry var10000 = entry;
         entry = entry.tail();
         prevEntry = var10000;
      }

      this.remove(bucket$1, prevEntry, entry);
   }

   private final void queueLoop$1() {
      while(true) {
         Entry stale = this.poll$1();
         if (stale == null) {
            return;
         }

         int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(stale.hash());
         this.linkedListLoop$1((Entry)null, this.scala$reflect$internal$util$WeakHashSet$$table[bucket], stale, bucket);
      }
   }

   private final void linkedListLoop$2(final Entry entry) {
      while(entry != null) {
         int bucket = this.scala$reflect$internal$util$WeakHashSet$$bucketFor(entry.hash());
         Entry oldNext = entry.tail();
         entry.tail_$eq(this.scala$reflect$internal$util$WeakHashSet$$table[bucket]);
         this.scala$reflect$internal$util$WeakHashSet$$table[bucket] = entry;
         entry = oldNext;
      }

   }

   private final void tableLoop$1(final int oldBucket, final Entry[] oldTable$1) {
      while(oldBucket < oldTable$1.length) {
         this.linkedListLoop$2(oldTable$1[oldBucket]);
         ++oldBucket;
      }

   }

   private final Object linkedListLoop$3(final Entry entry, final Object elem$1) {
      while(entry != null) {
         Object entryElem = entry.get();
         if (elem$1.equals(entryElem)) {
            return entryElem;
         }

         entry = entry.tail();
      }

      return null;
   }

   private final Object add$1(final int bucket$2, final Object elem$2, final int hash$1, final Entry oldHead$1) {
      this.scala$reflect$internal$util$WeakHashSet$$table[bucket$2] = new Entry(elem$2, hash$1, oldHead$1, this.queue);
      ++this.scala$reflect$internal$util$WeakHashSet$$count;
      if (this.scala$reflect$internal$util$WeakHashSet$$count > this.threshold) {
         this.resize();
      }

      return elem$2;
   }

   private final Object linkedListLoop$4(final Entry entry, final Object elem$2, final int bucket$2, final int hash$1, final Entry oldHead$1) {
      while(entry != null) {
         Object entryElem = entry.get();
         if (elem$2.equals(entryElem)) {
            return entryElem;
         }

         entry = entry.tail();
      }

      return this.add$1(bucket$2, elem$2, hash$1, oldHead$1);
   }

   private final void add$2(final int bucket$3, final Object elem$3, final int hash$2, final Entry oldHead$2) {
      this.scala$reflect$internal$util$WeakHashSet$$table[bucket$3] = new Entry(elem$3, hash$2, oldHead$2, this.queue);
      ++this.scala$reflect$internal$util$WeakHashSet$$count;
      if (this.scala$reflect$internal$util$WeakHashSet$$count > this.threshold) {
         this.resize();
      }
   }

   private final void linkedListLoop$5(final Entry entry, final Object elem$3, final int bucket$3, final int hash$2, final Entry oldHead$2) {
      while(entry != null) {
         if (elem$3.equals(entry.get())) {
            return;
         }

         entry = entry.tail();
      }

      this.add$2(bucket$3, elem$3, hash$2, oldHead$2);
   }

   private final void linkedListLoop$6(final Entry prevEntry, final Entry entry, final Object elem$4, final int bucket$4) {
      while(entry != null) {
         if (elem$4.equals(entry.get())) {
            this.remove(bucket$4, prevEntry, entry);
            return;
         }

         Entry var10000 = entry;
         entry = entry.tail();
         prevEntry = var10000;
      }

   }

   private final void queueLoop$2() {
      while(this.queue.poll() != null) {
      }

   }

   public WeakHashSet(final int initialCapacity, final double loadFactor) {
      this.initialCapacity = initialCapacity;
      this.loadFactor = loadFactor;
      this.queue = new ReferenceQueue();
      this.scala$reflect$internal$util$WeakHashSet$$count = 0;
      this.scala$reflect$internal$util$WeakHashSet$$table = new Entry[this.computeCapacity()];
      this.threshold = this.computeThreshold();
   }

   public WeakHashSet() {
      this(WeakHashSet$.MODULE$.defaultInitialCapacity(), WeakHashSet$.MODULE$.defaultLoadFactor());
   }

   public class Diagnostics {
      // $FF: synthetic field
      public final WeakHashSet $outer;

      public void fullyValidate() {
         int var4 = 0;

         for(int var5 = 0; var5 < this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table.length; ++var5) {
            for(Entry var6 = this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table[var5]; (Entry)var6 != null; var6 = ((Entry)var6).tail()) {
               if (((Entry)var6).get() == null) {
                  throw new AssertionError((new java.lang.StringBuilder(18)).append("assertion failed: ").append((new java.lang.StringBuilder(121)).append(var6).append(" had a null value indicated that gc activity was happening during diagnostic validation or that a null value was inserted").toString()).toString());
               }

               ++var4;
               int cachedHash = ((Entry)var6).hash();
               int realHash = ((Entry)var6).get().hashCode();
               if (cachedHash != realHash) {
                  throw new AssertionError((new java.lang.StringBuilder(18)).append("assertion failed: ").append((new java.lang.StringBuilder(43)).append("for ").append(var6).append(" cached hash was ").append(cachedHash).append(" but should have been ").append(realHash).toString()).toString());
               }

               int computedBucket = this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$bucketFor(realHash);
               if (computedBucket != var5) {
                  throw new AssertionError((new java.lang.StringBuilder(18)).append("assertion failed: ").append((new java.lang.StringBuilder(51)).append("for ").append(var6).append(" the computed bucket was ").append(computedBucket).append(" but should have been ").append(var5).toString()).toString());
               }
            }
         }

         if (var4 != this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$count) {
            throw new AssertionError((new java.lang.StringBuilder(18)).append("assertion failed: ").append((new java.lang.StringBuilder(45)).append("The computed count was ").append(var4).append(" but should have been ").append(this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$count).toString()).toString());
         }
      }

      public IndexedSeqView dump() {
         Object scala$reflect$internal$util$WeakHashSet$Diagnostics$$deep$1_a = this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table;
         return new AbstractIndexedSeqView(scala$reflect$internal$util$WeakHashSet$Diagnostics$$deep$1_a) {
            // $FF: synthetic field
            private final Diagnostics $outer;
            private final Object a$1;

            public int length() {
               return Array.getLength(this.a$1);
            }

            public Object apply(final int idx) {
               Object var2 = scala.runtime.ScalaRunTime..MODULE$.array_apply(this.a$1, idx);
               if (var2 instanceof Object && var2.getClass().isArray()) {
                  Diagnostics var10000 = this.$outer;
                  if (var10000 == null) {
                     throw null;
                  } else {
                     Diagnostics scala$reflect$internal$util$WeakHashSet$Diagnostics$$deep$1_this = var10000;
                     return new <anonymous constructor>(var2);
                  }
               } else {
                  return var2;
               }
            }

            public String className() {
               return "Array";
            }

            public {
               if (Diagnostics.this == null) {
                  throw null;
               } else {
                  this.$outer = Diagnostics.this;
                  this.a$1 = a$1;
               }
            }
         };
      }

      public int collisionBucketsCount() {
         Object[] refArrayOps_xs = this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table;
         Object var6 = null;
         Object count$extension_$this = refArrayOps_xs;
         int count$extension_i = 0;
         int count$extension_res = 0;

         for(int count$extension_len = refArrayOps_xs.length; count$extension_i < count$extension_len; ++count$extension_i) {
            if ($anonfun$collisionBucketsCount$1((Entry)((Object[])count$extension_$this)[count$extension_i])) {
               ++count$extension_res;
            }
         }

         return count$extension_res;
      }

      public int fullBucketsCount() {
         Object[] refArrayOps_xs = this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table;
         Object var6 = null;
         Object count$extension_$this = refArrayOps_xs;
         int count$extension_i = 0;
         int count$extension_res = 0;

         for(int count$extension_len = refArrayOps_xs.length; count$extension_i < count$extension_len; ++count$extension_i) {
            if ($anonfun$fullBucketsCount$1((Entry)((Object[])count$extension_$this)[count$extension_i])) {
               ++count$extension_res;
            }
         }

         return count$extension_res;
      }

      public int bucketsCount() {
         return this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$table.length;
      }

      // $FF: synthetic method
      public WeakHashSet scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$fullyValidate$1(final ObjectRef entry$1) {
         return (new java.lang.StringBuilder(121)).append((Entry)entry$1.elem).append(" had a null value indicated that gc activity was happening during diagnostic validation or that a null value was inserted").toString();
      }

      // $FF: synthetic method
      public static final String $anonfun$fullyValidate$2(final ObjectRef entry$1, final int cachedHash$1, final int realHash$1) {
         return (new java.lang.StringBuilder(43)).append("for ").append((Entry)entry$1.elem).append(" cached hash was ").append(cachedHash$1).append(" but should have been ").append(realHash$1).toString();
      }

      // $FF: synthetic method
      public static final String $anonfun$fullyValidate$3(final ObjectRef entry$1, final int computedBucket$1, final IntRef bucket$5) {
         return (new java.lang.StringBuilder(51)).append("for ").append((Entry)entry$1.elem).append(" the computed bucket was ").append(computedBucket$1).append(" but should have been ").append(bucket$5.elem).toString();
      }

      // $FF: synthetic method
      public static final String $anonfun$fullyValidate$4(final Diagnostics $this, final IntRef computedCount$1) {
         return (new java.lang.StringBuilder(45)).append("The computed count was ").append(computedCount$1.elem).append(" but should have been ").append($this.scala$reflect$internal$util$WeakHashSet$Diagnostics$$$outer().scala$reflect$internal$util$WeakHashSet$$count).toString();
      }

      public final IndexedSeqView scala$reflect$internal$util$WeakHashSet$Diagnostics$$deep$1(final Object a) {
         return new AbstractIndexedSeqView(a) {
            // $FF: synthetic field
            private final Diagnostics $outer;
            private final Object a$1;

            public int length() {
               return Array.getLength(this.a$1);
            }

            public Object apply(final int idx) {
               Object var2 = scala.runtime.ScalaRunTime..MODULE$.array_apply(this.a$1, idx);
               if (var2 instanceof Object && var2.getClass().isArray()) {
                  Diagnostics var10000 = this.$outer;
                  if (var10000 == null) {
                     throw null;
                  } else {
                     Diagnostics scala$reflect$internal$util$WeakHashSet$Diagnostics$$deep$1_this = var10000;
                     return new <anonymous constructor>(var2);
                  }
               } else {
                  return var2;
               }
            }

            public String className() {
               return "Array";
            }

            public {
               if (Diagnostics.this == null) {
                  throw null;
               } else {
                  this.$outer = Diagnostics.this;
                  this.a$1 = a$1;
               }
            }
         };
      }

      // $FF: synthetic method
      public static final boolean $anonfun$collisionBucketsCount$1(final Entry entry) {
         return entry != null && entry.tail() != null;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$fullBucketsCount$1(final Entry entry) {
         return entry != null;
      }

      public Diagnostics() {
         if (WeakHashSet.this == null) {
            throw null;
         } else {
            this.$outer = WeakHashSet.this;
            super();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$collisionBucketsCount$1$adapted(final Entry entry) {
         return BoxesRunTime.boxToBoolean($anonfun$collisionBucketsCount$1(entry));
      }

      // $FF: synthetic method
      public static final Object $anonfun$fullBucketsCount$1$adapted(final Entry entry) {
         return BoxesRunTime.boxToBoolean($anonfun$fullBucketsCount$1(entry));
      }
   }

   private static class Entry extends WeakReference {
      private final int hash;
      private Entry tail;

      public int hash() {
         return this.hash;
      }

      public Entry tail() {
         return this.tail;
      }

      public void tail_$eq(final Entry x$1) {
         this.tail = x$1;
      }

      public Entry(final Object element, final int hash, final Entry tail, final ReferenceQueue queue) {
         this.hash = hash;
         this.tail = tail;
         super(element, queue);
      }
   }
}
