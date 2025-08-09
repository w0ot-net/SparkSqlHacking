package org.apache.spark.util.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.SeqView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]d!\u0002\u000f\u001e\u0001\u0005:\u0003\u0002\u0003&\u0001\u0005\u0007\u0005\u000b1B&\t\u000bE\u0003A\u0011\u0001*\t\u0013]\u0003\u0001\u0019!a\u0001\n\u0013A\u0006\"C-\u0001\u0001\u0004\u0005\r\u0011\"\u0003[\u0011%\u0001\u0007\u00011A\u0001B\u0003&A\bC\u0005b\u0001\u0001\u0007\t\u0019!C\u00051\"I!\r\u0001a\u0001\u0002\u0004%Ia\u0019\u0005\nK\u0002\u0001\r\u0011!Q!\nqBqA\u001a\u0001A\u0002\u0013%q\rC\u0004l\u0001\u0001\u0007I\u0011\u00027\t\r9\u0004\u0001\u0015)\u0003i\u0011\u001dy\u0007\u00011A\u0005\nADq\u0001\u001e\u0001A\u0002\u0013%Q\u000f\u0003\u0004x\u0001\u0001\u0006K!\u001d\u0005\u0006q\u0002!\t!\u001f\u0005\u0006y\u0002!I! \u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\tI\u0001\u0001C\u0001\u0003\u0017Aa!a\u0006\u0001\t\u0003:\u0007bBA\r\u0001\u0011\u0005\u00131\u0004\u0005\b\u0003G\u0001A\u0011BA\u0013\u000f!\tY#\bE\u0001C\u00055ba\u0002\u000f\u001e\u0011\u0003\t\u0013q\u0006\u0005\u0007#^!\t!a\u0010\t\ra<B\u0011AA!\u0011\u0019Ax\u0003\"\u0001\u0002T!I\u0011qM\f\u0002\u0002\u0013%\u0011\u0011\u000e\u0002\u000e\u0007>l\u0007/Y2u\u0005V4g-\u001a:\u000b\u0005yy\u0012AC2pY2,7\r^5p]*\u0011\u0001%I\u0001\u0005kRLGN\u0003\u0002#G\u0005)1\u000f]1sW*\u0011A%J\u0001\u0007CB\f7\r[3\u000b\u0003\u0019\n1a\u001c:h+\tAch\u0005\u0003\u0001S=:\u0005C\u0001\u0016.\u001b\u0005Y#\"\u0001\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u00059Z#AB!osJ+g\rE\u00021sqr!!M\u001c\u000f\u0005I2T\"A\u001a\u000b\u0005Q*\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u00031J!\u0001O\u0016\u0002\u000fA\f7m[1hK&\u0011!h\u000f\u0002\u0004'\u0016\f(B\u0001\u001d,!\tid\b\u0004\u0001\u0005\u000b}\u0002!\u0019\u0001!\u0003\u0003Q\u000b\"!\u0011#\u0011\u0005)\u0012\u0015BA\",\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AK#\n\u0005\u0019[#aA!osB\u0011\u0001\u0007S\u0005\u0003\u0013n\u0012AbU3sS\u0006d\u0017N_1cY\u0016\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rau\nP\u0007\u0002\u001b*\u0011ajK\u0001\be\u00164G.Z2u\u0013\t\u0001VJ\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q\t1\u000b\u0006\u0002U-B\u0019Q\u000b\u0001\u001f\u000e\u0003uAQA\u0013\u0002A\u0004-\u000b\u0001\"\u001a7f[\u0016tG\u000fM\u000b\u0002y\u0005aQ\r\\3nK:$\bg\u0018\u0013fcR\u00111L\u0018\t\u0003UqK!!X\u0016\u0003\tUs\u0017\u000e\u001e\u0005\b?\u0012\t\t\u00111\u0001=\u0003\rAH%M\u0001\nK2,W.\u001a8ua\u0001\n\u0001\"\u001a7f[\u0016tG/M\u0001\rK2,W.\u001a8uc}#S-\u001d\u000b\u00037\u0012DqaX\u0004\u0002\u0002\u0003\u0007A(A\u0005fY\u0016lWM\u001c;2A\u000591-\u001e:TSj,W#\u00015\u0011\u0005)J\u0017B\u00016,\u0005\rIe\u000e^\u0001\fGV\u00148+\u001b>f?\u0012*\u0017\u000f\u0006\u0002\\[\"9qLCA\u0001\u0002\u0004A\u0017\u0001C2veNK'0\u001a\u0011\u0002\u001b=$\b.\u001a:FY\u0016lWM\u001c;t+\u0005\t\bc\u0001\u0016sy%\u00111o\u000b\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0012_RDWM]#mK6,g\u000e^:`I\u0015\fHCA.w\u0011\u001dyV\"!AA\u0002E\fab\u001c;iKJ,E.Z7f]R\u001c\b%A\u0003baBd\u0017\u0010\u0006\u0002=u\")1p\u0004a\u0001Q\u0006A\u0001o\\:ji&|g.\u0001\u0004va\u0012\fG/\u001a\u000b\u00047z|\b\"B>\u0011\u0001\u0004A\u0007BBA\u0001!\u0001\u0007A(A\u0003wC2,X-\u0001\u0005%a2,8\u000fJ3r)\r!\u0016q\u0001\u0005\u0007\u0003\u0003\t\u0002\u0019\u0001\u001f\u0002\u001b\u0011\u0002H.^:%a2,8\u000fJ3r)\r!\u0016Q\u0002\u0005\b\u0003\u001f\u0011\u0002\u0019AA\t\u0003\u00191\u0018\r\\;fgB!\u0001'a\u0005=\u0013\r\t)b\u000f\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\u0007Y\u0016tw\r\u001e5\u0002\u0011%$XM]1u_J,\"!!\b\u0011\tA\ny\u0002P\u0005\u0004\u0003CY$\u0001C%uKJ\fGo\u001c:\u0002\u0015\u001d\u0014xn\u001e+p'&TX\rF\u0002\\\u0003OAa!!\u000b\u0016\u0001\u0004A\u0017a\u00028foNK'0Z\u0001\u000e\u0007>l\u0007/Y2u\u0005V4g-\u001a:\u0011\u0005U;2\u0003B\f*\u0003c\u0001B!a\r\u0002>5\u0011\u0011Q\u0007\u0006\u0005\u0003o\tI$\u0001\u0002j_*\u0011\u00111H\u0001\u0005U\u00064\u0018-C\u0002J\u0003k!\"!!\f\u0016\t\u0005\r\u00131\n\u000b\u0003\u0003\u000b\"B!a\u0012\u0002NA!Q\u000bAA%!\ri\u00141\n\u0003\u0006\u007fe\u0011\r\u0001\u0011\u0005\n\u0003\u001fJ\u0012\u0011!a\u0002\u0003#\n!\"\u001a<jI\u0016t7-\u001a\u00133!\u0011au*!\u0013\u0016\t\u0005U\u0013Q\f\u000b\u0005\u0003/\n)\u0007\u0006\u0003\u0002Z\u0005}\u0003\u0003B+\u0001\u00037\u00022!PA/\t\u0015y$D1\u0001A\u0011%\t\tGGA\u0001\u0002\b\t\u0019'\u0001\u0006fm&$WM\\2fIM\u0002B\u0001T(\u0002\\!9\u0011\u0011\u0001\u000eA\u0002\u0005m\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAA6!\u0011\ti'a\u001d\u000e\u0005\u0005=$\u0002BA9\u0003s\tA\u0001\\1oO&!\u0011QOA8\u0005\u0019y%M[3di\u0002"
)
public class CompactBuffer implements Seq, Serializable {
   private final ClassTag evidence$1;
   private Object element0;
   private Object element1;
   private int org$apache$spark$util$collection$CompactBuffer$$curSize;
   private Object otherElements;

   public final Seq toSeq() {
      return Seq.toSeq$(this);
   }

   public SeqFactory iterableFactory() {
      return Seq.iterableFactory$(this);
   }

   public boolean canEqual(final Object that) {
      return scala.collection.Seq.canEqual$(this, that);
   }

   public boolean equals(final Object o) {
      return scala.collection.Seq.equals$(this, o);
   }

   public int hashCode() {
      return scala.collection.Seq.hashCode$(this);
   }

   public String toString() {
      return scala.collection.Seq.toString$(this);
   }

   public String stringPrefix() {
      return scala.collection.Seq.stringPrefix$(this);
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

   public SeqView view() {
      return SeqOps.view$(this);
   }

   public Object prepended(final Object elem) {
      return SeqOps.prepended$(this, elem);
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
   public final Object union(final scala.collection.Seq that) {
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

   public Object reverse() {
      return SeqOps.reverse$(this);
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

   public int indexOfSlice(final scala.collection.Seq that, final int from) {
      return SeqOps.indexOfSlice$(this, that, from);
   }

   public int indexOfSlice(final scala.collection.Seq that) {
      return SeqOps.indexOfSlice$(this, that);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that, final int end) {
      return SeqOps.lastIndexOfSlice$(this, that, end);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that) {
      return SeqOps.lastIndexOfSlice$(this, that);
   }

   public Option findLast(final Function1 p) {
      return SeqOps.findLast$(this, p);
   }

   public boolean containsSlice(final scala.collection.Seq that) {
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

   public boolean corresponds(final scala.collection.Seq that, final Function2 p) {
      return SeqOps.corresponds$(this, that, p);
   }

   public Object diff(final scala.collection.Seq that) {
      return SeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return SeqOps.intersect$(this, that);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return SeqOps.patch$(this, from, other, replaced);
   }

   public Object updated(final int index, final Object elem) {
      return SeqOps.updated$(this, index, elem);
   }

   public Map occCounts(final scala.collection.Seq sq) {
      return SeqOps.occCounts$(this, sq);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return SeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return SeqOps.search$(this, elem, from, to, ord);
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

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
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

   public scala.collection.immutable.Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

   public List toList() {
      return IterableOnceOps.toList$(this);
   }

   public Vector toVector() {
      return IterableOnceOps.toVector$(this);
   }

   public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public Set toSet() {
      return IterableOnceOps.toSet$(this);
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

   private Object element0() {
      return this.element0;
   }

   private void element0_$eq(final Object x$1) {
      this.element0 = x$1;
   }

   private Object element1() {
      return this.element1;
   }

   private void element1_$eq(final Object x$1) {
      this.element1 = x$1;
   }

   public int org$apache$spark$util$collection$CompactBuffer$$curSize() {
      return this.org$apache$spark$util$collection$CompactBuffer$$curSize;
   }

   private void curSize_$eq(final int x$1) {
      this.org$apache$spark$util$collection$CompactBuffer$$curSize = x$1;
   }

   private Object otherElements() {
      return this.otherElements;
   }

   private void otherElements_$eq(final Object x$1) {
      this.otherElements = x$1;
   }

   public Object apply(final int position) {
      if (position >= 0 && position < this.org$apache$spark$util$collection$CompactBuffer$$curSize()) {
         if (position == 0) {
            return this.element0();
         } else {
            return position == 1 ? this.element1() : scala.runtime.ScalaRunTime..MODULE$.array_apply(this.otherElements(), position - 2);
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   private void update(final int position, final Object value) {
      if (position >= 0 && position < this.org$apache$spark$util$collection$CompactBuffer$$curSize()) {
         if (position == 0) {
            this.element0_$eq(value);
         } else if (position == 1) {
            this.element1_$eq(value);
         } else {
            scala.runtime.ScalaRunTime..MODULE$.array_update(this.otherElements(), position - 2, value);
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public CompactBuffer $plus$eq(final Object value) {
      int newIndex = this.org$apache$spark$util$collection$CompactBuffer$$curSize();
      if (newIndex == 0) {
         this.element0_$eq(value);
         this.curSize_$eq(1);
      } else if (newIndex == 1) {
         this.element1_$eq(value);
         this.curSize_$eq(2);
      } else {
         this.growToSize(this.org$apache$spark$util$collection$CompactBuffer$$curSize() + 1);
         scala.runtime.ScalaRunTime..MODULE$.array_update(this.otherElements(), newIndex - 2, value);
      }

      return this;
   }

   public CompactBuffer $plus$plus$eq(final IterableOnce values) {
      if (values instanceof CompactBuffer var4) {
         int oldSize = this.org$apache$spark$util$collection$CompactBuffer$$curSize();
         int itsSize = var4.org$apache$spark$util$collection$CompactBuffer$$curSize();
         Object itsElements = var4.otherElements();
         this.growToSize(this.org$apache$spark$util$collection$CompactBuffer$$curSize() + itsSize);
         if (itsSize == 1) {
            this.update(oldSize, var4.element0());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else if (itsSize == 2) {
            this.update(oldSize, var4.element0());
            this.update(oldSize + 1, var4.element1());
            BoxedUnit var8 = BoxedUnit.UNIT;
         } else if (itsSize > 2) {
            this.update(oldSize, var4.element0());
            this.update(oldSize + 1, var4.element1());
            System.arraycopy(itsElements, 0, this.otherElements(), oldSize, itsSize - 2);
            BoxedUnit var9 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10 = BoxedUnit.UNIT;
         }
      } else {
         values.iterator().foreach((e) -> this.$plus$eq(e));
         BoxedUnit var11 = BoxedUnit.UNIT;
      }

      return this;
   }

   public int length() {
      return this.org$apache$spark$util$collection$CompactBuffer$$curSize();
   }

   public Iterator iterator() {
      return new Iterator() {
         private int pos;
         // $FF: synthetic field
         private final CompactBuffer $outer;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
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

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
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

         private int pos() {
            return this.pos;
         }

         private void pos_$eq(final int x$1) {
            this.pos = x$1;
         }

         public boolean hasNext() {
            return this.pos() < this.$outer.org$apache$spark$util$collection$CompactBuffer$$curSize();
         }

         public Object next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               this.pos_$eq(this.pos() + 1);
               return this.$outer.apply(this.pos() - 1);
            }
         }

         public {
            if (CompactBuffer.this == null) {
               throw null;
            } else {
               this.$outer = CompactBuffer.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = 0;
            }
         }
      };
   }

   private void growToSize(final int newSize) {
      int newArraySize = newSize - 2;
      int arrayMax = 2147483632;
      if (newSize >= 0 && newArraySize <= arrayMax) {
         int capacity = this.otherElements() != null ? scala.runtime.ScalaRunTime..MODULE$.array_length(this.otherElements()) : 0;
         if (newArraySize > capacity) {
            long newArrayLen;
            for(newArrayLen = 8L; (long)newArraySize > newArrayLen; newArrayLen *= 2L) {
            }

            if (newArrayLen > (long)arrayMax) {
               newArrayLen = (long)arrayMax;
            }

            Object newArray = this.evidence$1.newArray((int)newArrayLen);
            if (this.otherElements() != null) {
               System.arraycopy(this.otherElements(), 0, newArray, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.otherElements()));
            }

            this.otherElements_$eq(newArray);
         }

         this.curSize_$eq(newSize);
      } else {
         throw new UnsupportedOperationException("Can't grow buffer past " + arrayMax + " elements");
      }
   }

   public CompactBuffer(final ClassTag evidence$1) {
      this.evidence$1 = evidence$1;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      scala.collection.immutable.Iterable.$init$(this);
      Function1.$init$(this);
      PartialFunction.$init$(this);
      SeqOps.$init$(this);
      scala.collection.Seq.$init$(this);
      Seq.$init$(this);
      this.org$apache$spark$util$collection$CompactBuffer$$curSize = 0;
      this.otherElements = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
