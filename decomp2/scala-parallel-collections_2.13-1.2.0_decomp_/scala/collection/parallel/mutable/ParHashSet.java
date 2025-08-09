package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable;
import scala.collection.DebugUtils$;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Parallelizable;
import scala.collection.Set;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Shrinkable;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-h\u0001B\u0010!\u0001%B\u0001\u0002\u0017\u0001\u0003\u0002\u0003\u0006I!\u0017\u0005\u0007A\u0002!\t\u0001J1\t\u000b\u0001\u0004A\u0011A2\t\u000b\u0011\u0004A\u0011I3\t\u000f\u0005M\u0002\u0001\"\u0011\u00026!9\u0011q\u0007\u0001\u0005B\u0005e\u0002bBA9\u0001\u0011\u0005\u00111\u000f\u0005\b\u0003w\u0002A\u0011AA?\u0011\u001d\ty\b\u0001C!\u0003\u0003Cq!a!\u0001\t\u0003\t)\tC\u0004\u0002\f\u0002!\t!!$\t\u000f\u0005E\u0005\u0001\"\u0011\u0002\u0014\"9\u00111\u0014\u0001\u0005\u0002\u0005u\u0005bBAT\u0001\u0011\u0005\u0011\u0011\b\u0004\u0007\u0003\u007f\u0001\u0001!!\u0011\t\u0015\u0005%sB!A!\u0002\u0013\tY\u0005\u0003\u0007\u0002R=\u0011\t\u0011)A\u0005\u0003\u0017\n\u0019\u0006\u0003\u0007\u0002X=\u0011\t\u0011)A\u0005\u0003\u0017\nI\u0006\u0003\u0004a\u001f\u0011\u0005\u0011Q\f\u0005\b\u0003KzA\u0011AA4\u0011\u001d\tI\u000b\u0001C\u0005\u0003WCq!a.\u0001\t\u0013\tI\fC\u0004\u0002F\u0002!\t%a2\b\r\u0005%\b\u0005#\u0001g\r\u0015y\u0002\u0005#\u0001h\u0011\u0015\u0001\u0017\u0004\"\u0001s\u0011\u0015\u0019\u0018\u0004b\u0001u\u0011\u001d\t\t!\u0007C!\u0003\u0007Aq!!\u0006\u001a\t\u0003\n9\u0002C\u0005\u0002$e\t\t\u0011\"\u0003\u0002&\tQ\u0001+\u0019:ICND7+\u001a;\u000b\u0005\u0005\u0012\u0013aB7vi\u0006\u0014G.\u001a\u0006\u0003G\u0011\n\u0001\u0002]1sC2dW\r\u001c\u0006\u0003K\u0019\n!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0013!B:dC2\f7\u0001A\u000b\u0003UU\u001ar\u0001A\u00160}\u0015s\u0015\u000b\u0005\u0002-[5\ta%\u0003\u0002/M\t1\u0011I\\=SK\u001a\u00042\u0001M\u00194\u001b\u0005\u0001\u0013B\u0001\u001a!\u0005\u0019\u0001\u0016M]*fiB\u0011A'\u000e\u0007\u0001\t\u00151\u0004A1\u00018\u0005\u0005!\u0016C\u0001\u001d<!\ta\u0013(\u0003\u0002;M\t9aj\u001c;iS:<\u0007C\u0001\u0017=\u0013\tidEA\u0002B]f\u0004Ba\u0010\"4\t6\t\u0001I\u0003\u0002BI\u00059q-\u001a8fe&\u001c\u0017BA\"A\u0005I9UM\\3sS\u000e\u0004\u0016M\u001d+f[Bd\u0017\r^3\u0011\u0005A\u0002\u0001C\u0002\u0019Gg\u0011C\u0015*\u0003\u0002HA\tQ\u0001+\u0019:TKRd\u0015n[3\u0011\u0007A\u00021\u0007E\u0002K\u0019Nj\u0011a\u0013\u0006\u0003C\u0011J!!T&\u0003\u000f!\u000b7\u000f[*fiB\u0019\u0001gT\u001a\n\u0005A\u0003#\u0001\u0005)be\u001ac\u0017\r\u001e%bg\"$\u0016M\u00197f!\t\u0011VK\u0004\u0002-'&\u0011AKJ\u0001\ba\u0006\u001c7.Y4f\u0013\t1vK\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002UM\u0005A1m\u001c8uK:$8\u000fE\u0002[;Nr!AS.\n\u0005q[\u0015!\u0004$mCRD\u0015m\u001d5UC\ndW-\u0003\u0002_?\nA1i\u001c8uK:$8O\u0003\u0002]\u0017\u00061A(\u001b8jiz\"\"\u0001\u00132\t\u000ba\u0013\u0001\u0019A-\u0015\u0003!\u000b\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003\u0019\u0004\"\u0001M\r\u0014\u0007eA7\u000eE\u0002@S\u0012K!A\u001b!\u0003\u001bA\u000b'oU3u\r\u0006\u001cGo\u001c:z!\ta\u0017/D\u0001n\u0015\tqw.\u0001\u0002j_*\t\u0001/\u0001\u0003kCZ\f\u0017B\u0001,n)\u00051\u0017\u0001D2b]\n+\u0018\u000e\u001c3Ge>lWcA;|}V\ta\u000fE\u0003@oflx0\u0003\u0002y\u0001\nq1)\u00198D_6\u0014\u0017N\\3Ge>l\u0007c\u0001\u0019\u0001uB\u0011Ag\u001f\u0003\u0006yn\u0011\ra\u000e\u0002\u0002'B\u0011AG \u0003\u0006mm\u0011\ra\u000e\t\u0004a\u0001i\u0018A\u00038fo\n+\u0018\u000e\u001c3feV!\u0011QAA\t+\t\t9\u0001\u0005\u0005\u0002\n\u0005-\u0011qBA\n\u001b\u0005\u0011\u0013bAA\u0007E\tA1i\\7cS:,'\u000fE\u00025\u0003#!QA\u000e\u000fC\u0002]\u0002B\u0001\r\u0001\u0002\u0010\u0005Ya.Z<D_6\u0014\u0017N\\3s+\u0011\tI\"a\b\u0016\u0005\u0005m\u0001\u0003CA\u0005\u0003\u0017\ti\"!\t\u0011\u0007Q\ny\u0002B\u00037;\t\u0007q\u0007\u0005\u00031\u0001\u0005u\u0011\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0014!\u0011\tI#a\f\u000e\u0005\u0005-\"bAA\u0017_\u0006!A.\u00198h\u0013\u0011\t\t$a\u000b\u0003\r=\u0013'.Z2u\u0003\u0015)W\u000e\u001d;z+\u0005A\u0015\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005m\u0002cAA\u001f\u001f5\t\u0001A\u0001\nQCJD\u0015m\u001d5TKRLE/\u001a:bi>\u00148cA\b\u0002DA!\u0011QHA#\u0013\r\t9e\u0014\u0002\u0019!\u0006\u0014h\t\\1u\u0011\u0006\u001c\b\u000eV1cY\u0016LE/\u001a:bi>\u0014\u0018!B:uCJ$\bc\u0001\u0017\u0002N%\u0019\u0011q\n\u0014\u0003\u0007%sG/A\u0007ji\u0016\u0014\u0018\r^3t+:$\u0018\u000e\\\u0005\u0005\u0003+\n)%A\u0003v]RLG.A\u0007u_R\fG.\u00127f[\u0016tGo]\u0005\u0005\u00037\n)%A\u0005u_R\fGn]5{KRA\u00111HA0\u0003C\n\u0019\u0007C\u0004\u0002JM\u0001\r!a\u0013\t\u000f\u0005E3\u00031\u0001\u0002L!9\u0011qK\nA\u0002\u0005-\u0013a\u00038fo&#XM]1u_J$\u0002\"a\u000f\u0002j\u0005-\u0014Q\u000e\u0005\b\u0003\u0013\"\u0002\u0019AA&\u0011\u001d\t)\u0006\u0006a\u0001\u0003\u0017Bq!a\u001c\u0015\u0001\u0004\tY%A\u0003u_R\fG.A\u0003dY\u0016\f'\u000f\u0006\u0002\u0002vA\u0019A&a\u001e\n\u0007\u0005edE\u0001\u0003V]&$\u0018aA:fcV\t\u0011*A\u0005l]><hnU5{KV\u0011\u00111J\u0001\u0007C\u0012$wJ\\3\u0015\t\u0005u\u0012q\u0011\u0005\u0007\u0003\u0013S\u0001\u0019A\u001a\u0002\t\u0015dW-\\\u0001\fgV\u0014GO]1di>sW\r\u0006\u0003\u0002>\u0005=\u0005BBAE\u0017\u0001\u00071'\u0001\u0007tiJLgn\u001a)sK\u001aL\u00070\u0006\u0002\u0002\u0016B!\u0011\u0011FAL\u0013\u0011\tI*a\u000b\u0003\rM#(/\u001b8h\u0003!\u0019wN\u001c;bS:\u001cH\u0003BAP\u0003K\u00032\u0001LAQ\u0013\r\t\u0019K\n\u0002\b\u0005>|G.Z1o\u0011\u0019\tI)\u0004a\u0001g\u0005A1\u000f\u001d7jiR,'/A\u0006xe&$Xm\u00142kK\u000e$H\u0003BA;\u0003[Cq!a,\u0016\u0001\u0004\t\t,A\u0001t!\ra\u00171W\u0005\u0004\u0003kk'AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6\f!B]3bI>\u0013'.Z2u)\u0011\t)(a/\t\u000f\u0005uf\u00031\u0001\u0002@\u0006\u0011\u0011N\u001c\t\u0004Y\u0006\u0005\u0017bAAb[\n\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7\u0002!\u0011,'-^4J]\u001a|'/\\1uS>tWCAAe!\u0011\tY-!7\u000f\t\u00055\u0017Q\u001b\t\u0004\u0003\u001f4SBAAi\u0015\r\t\u0019\u000eK\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005]g%\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u00033\u000bYNC\u0002\u0002X\u001aBs\u0001AAp\u0003K\f9\u000fE\u0002-\u0003CL1!a9'\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0003)\u0001\u0016M\u001d%bg\"\u001cV\r\u001e"
)
public class ParHashSet implements ParSet, ParFlatHashTable, Serializable {
   private static final long serialVersionUID = 1L;
   private int _loadFactor;
   private Object[] table;
   private int tableSize;
   private int threshold;
   private int[] sizemap;
   private int seedvalue;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public static CanCombineFrom canBuildFrom() {
      return ParHashSet$.MODULE$.canBuildFrom();
   }

   public static Factory toFactory() {
      return ParHashSet$.MODULE$.toFactory();
   }

   public boolean alwaysInitSizeMap() {
      return ParFlatHashTable.alwaysInitSizeMap$(this);
   }

   public int capacity(final int expectedSize) {
      return FlatHashTable.capacity$(this, expectedSize);
   }

   public int initialSize() {
      return FlatHashTable.initialSize$(this);
   }

   public int size() {
      return FlatHashTable.size$(this);
   }

   public int randomSeed() {
      return FlatHashTable.randomSeed$(this);
   }

   public int tableSizeSeed() {
      return FlatHashTable.tableSizeSeed$(this);
   }

   public void init(final ObjectInputStream in, final Function1 f) {
      FlatHashTable.init$(this, in, f);
   }

   public void serializeTo(final ObjectOutputStream out) {
      FlatHashTable.serializeTo$(this, out);
   }

   public Option findEntry(final Object elem) {
      return FlatHashTable.findEntry$(this, elem);
   }

   public boolean containsElem(final Object elem) {
      return FlatHashTable.containsElem$(this, elem);
   }

   public boolean addElem(final Object elem) {
      return FlatHashTable.addElem$(this, elem);
   }

   public boolean addEntry(final Object newEntry) {
      return FlatHashTable.addEntry$(this, newEntry);
   }

   public boolean removeElem(final Object elem) {
      return FlatHashTable.removeElem$(this, elem);
   }

   public final void nnSizeMapAdd(final int h) {
      FlatHashTable.nnSizeMapAdd$(this, h);
   }

   public final void nnSizeMapRemove(final int h) {
      FlatHashTable.nnSizeMapRemove$(this, h);
   }

   public final void nnSizeMapReset(final int tableLength) {
      FlatHashTable.nnSizeMapReset$(this, tableLength);
   }

   public final int totalSizeMapBuckets() {
      return FlatHashTable.totalSizeMapBuckets$(this);
   }

   public final int calcSizeMapSize(final int tableLength) {
      return FlatHashTable.calcSizeMapSize$(this, tableLength);
   }

   public final void sizeMapInit(final int tableLength) {
      FlatHashTable.sizeMapInit$(this, tableLength);
   }

   public final void sizeMapInitAndRebuild() {
      FlatHashTable.sizeMapInitAndRebuild$(this);
   }

   public void printSizeMap() {
      FlatHashTable.printSizeMap$(this);
   }

   public void printContents() {
      FlatHashTable.printContents$(this);
   }

   public void sizeMapDisable() {
      FlatHashTable.sizeMapDisable$(this);
   }

   public boolean isSizeMapDefined() {
      return FlatHashTable.isSizeMapDefined$(this);
   }

   public int index(final int hcode) {
      return FlatHashTable.index$(this, hcode);
   }

   public void clearTable() {
      FlatHashTable.clearTable$(this);
   }

   public FlatHashTable.Contents hashTableContents() {
      return FlatHashTable.hashTableContents$(this);
   }

   public void initWithContents(final FlatHashTable.Contents c) {
      FlatHashTable.initWithContents$(this, c);
   }

   public final int sizeMapBucketBitSize() {
      return FlatHashTable.HashUtils.sizeMapBucketBitSize$(this);
   }

   public final int sizeMapBucketSize() {
      return FlatHashTable.HashUtils.sizeMapBucketSize$(this);
   }

   public final int improve(final int hcode, final int seed) {
      return FlatHashTable.HashUtils.improve$(this, hcode, seed);
   }

   public final Object elemToEntry(final Object elem) {
      return FlatHashTable.HashUtils.elemToEntry$(this, elem);
   }

   public final Object entryToElem(final Object entry) {
      return FlatHashTable.HashUtils.entryToElem$(this, entry);
   }

   public ParSet $plus(final Object elem) {
      return ParSetLike.$plus$(this, elem);
   }

   public ParSet $minus(final Object elem) {
      return ParSetLike.$minus$(this, elem);
   }

   public ParSet clone() {
      return ParSetLike.clone$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
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

   public final boolean apply(final Object elem) {
      return scala.collection.parallel.ParSetLike.apply$(this, elem);
   }

   public scala.collection.parallel.ParSet intersect(final scala.collection.parallel.ParSet that) {
      return scala.collection.parallel.ParSetLike.intersect$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet intersect(final Set that) {
      return scala.collection.parallel.ParSetLike.intersect$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet $amp(final scala.collection.parallel.ParSet that) {
      return scala.collection.parallel.ParSetLike.$amp$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet $amp(final Set that) {
      return scala.collection.parallel.ParSetLike.$amp$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet $bar(final scala.collection.parallel.ParSet that) {
      return scala.collection.parallel.ParSetLike.$bar$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet $bar(final Set that) {
      return scala.collection.parallel.ParSetLike.$bar$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet $amp$tilde(final scala.collection.parallel.ParSet that) {
      return scala.collection.parallel.ParSetLike.$amp$tilde$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet $amp$tilde(final Set that) {
      return scala.collection.parallel.ParSetLike.$amp$tilde$(this, (Set)that);
   }

   public boolean subsetOf(final scala.collection.parallel.ParSet that) {
      return scala.collection.parallel.ParSetLike.subsetOf$(this, that);
   }

   public boolean equals(final Object that) {
      return scala.collection.parallel.ParSetLike.equals$(this, that);
   }

   public int hashCode() {
      return scala.collection.parallel.ParSetLike.hashCode$(this);
   }

   public boolean canEqual(final Object other) {
      return scala.collection.parallel.ParSetLike.canEqual$(this, other);
   }

   public scala.collection.parallel.ParSet union(final Set that) {
      return scala.collection.parallel.ParSetLike.union$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet union(final scala.collection.parallel.ParSet that) {
      return scala.collection.parallel.ParSetLike.union$(this, (scala.collection.parallel.ParSet)that);
   }

   public scala.collection.parallel.ParSet diff(final Set that) {
      return scala.collection.parallel.ParSetLike.diff$(this, (Set)that);
   }

   public scala.collection.parallel.ParSet diff(final scala.collection.parallel.ParSet that) {
      return scala.collection.parallel.ParSetLike.diff$(this, (scala.collection.parallel.ParSet)that);
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

   public String toString() {
      return Function1.toString$(this);
   }

   public ParIterable toIterable() {
      return ParIterable.toIterable$(this);
   }

   public ParSeq toSeq() {
      return ParIterable.toSeq$(this);
   }

   public void initTaskSupport() {
      ParIterableLike.initTaskSupport$(this);
   }

   public TaskSupport tasksupport() {
      return ParIterableLike.tasksupport$(this);
   }

   public void tasksupport_$eq(final TaskSupport ts) {
      ParIterableLike.tasksupport_$eq$(this, ts);
   }

   public scala.collection.parallel.ParIterable repr() {
      return ParIterableLike.repr$(this);
   }

   public final boolean isTraversableAgain() {
      return ParIterableLike.isTraversableAgain$(this);
   }

   public boolean hasDefiniteSize() {
      return ParIterableLike.hasDefiniteSize$(this);
   }

   public boolean isEmpty() {
      return ParIterableLike.isEmpty$(this);
   }

   public boolean nonEmpty() {
      return ParIterableLike.nonEmpty$(this);
   }

   public Object head() {
      return ParIterableLike.head$(this);
   }

   public Option headOption() {
      return ParIterableLike.headOption$(this);
   }

   public scala.collection.parallel.ParIterable tail() {
      return ParIterableLike.tail$(this);
   }

   public Object last() {
      return ParIterableLike.last$(this);
   }

   public Option lastOption() {
      return ParIterableLike.lastOption$(this);
   }

   public scala.collection.parallel.ParIterable init() {
      return ParIterableLike.init$(this);
   }

   public scala.collection.parallel.ParIterable par() {
      return ParIterableLike.par$(this);
   }

   public boolean isStrictSplitterCollection() {
      return ParIterableLike.isStrictSplitterCollection$(this);
   }

   public Combiner reuse(final Option oldc, final Combiner newc) {
      return ParIterableLike.reuse$(this, oldc, newc);
   }

   public ParIterableLike.TaskOps task2ops(final ParIterableLike.StrictSplitterCheckTask tsk) {
      return ParIterableLike.task2ops$(this, tsk);
   }

   public ParIterableLike.NonDivisible wrap(final Function0 body) {
      return ParIterableLike.wrap$(this, body);
   }

   public ParIterableLike.SignallingOps delegatedSignalling2ops(final DelegatedSignalling it) {
      return ParIterableLike.delegatedSignalling2ops$(this, it);
   }

   public ParIterableLike.BuilderOps builder2ops(final Builder cb) {
      return ParIterableLike.builder2ops$(this, cb);
   }

   public scala.collection.parallel.ParIterable sequentially(final Function1 b) {
      return ParIterableLike.sequentially$(this, b);
   }

   public String mkString(final String start, final String sep, final String end) {
      return ParIterableLike.mkString$(this, start, sep, end);
   }

   public String mkString(final String sep) {
      return ParIterableLike.mkString$(this, sep);
   }

   public String mkString() {
      return ParIterableLike.mkString$(this);
   }

   public Object reduce(final Function2 op) {
      return ParIterableLike.reduce$(this, op);
   }

   public Option reduceOption(final Function2 op) {
      return ParIterableLike.reduceOption$(this, op);
   }

   public Object fold(final Object z, final Function2 op) {
      return ParIterableLike.fold$(this, z, op);
   }

   public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return ParIterableLike.aggregate$(this, z, seqop, combop);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return ParIterableLike.foldLeft$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return ParIterableLike.foldRight$(this, z, op);
   }

   public Object reduceLeft(final Function2 op) {
      return ParIterableLike.reduceLeft$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return ParIterableLike.reduceRight$(this, op);
   }

   public Option reduceLeftOption(final Function2 op) {
      return ParIterableLike.reduceLeftOption$(this, op);
   }

   public Option reduceRightOption(final Function2 op) {
      return ParIterableLike.reduceRightOption$(this, op);
   }

   public void foreach(final Function1 f) {
      ParIterableLike.foreach$(this, f);
   }

   public int count(final Function1 p) {
      return ParIterableLike.count$(this, p);
   }

   public Object sum(final Numeric num) {
      return ParIterableLike.sum$(this, num);
   }

   public Object product(final Numeric num) {
      return ParIterableLike.product$(this, num);
   }

   public Object min(final Ordering ord) {
      return ParIterableLike.min$(this, ord);
   }

   public Object max(final Ordering ord) {
      return ParIterableLike.max$(this, ord);
   }

   public Object maxBy(final Function1 f, final Ordering cmp) {
      return ParIterableLike.maxBy$(this, f, cmp);
   }

   public Object minBy(final Function1 f, final Ordering cmp) {
      return ParIterableLike.minBy$(this, f, cmp);
   }

   public scala.collection.parallel.ParIterable map(final Function1 f) {
      return ParIterableLike.map$(this, f);
   }

   public scala.collection.parallel.ParIterable collect(final PartialFunction pf) {
      return ParIterableLike.collect$(this, pf);
   }

   public scala.collection.parallel.ParIterable flatMap(final Function1 f) {
      return ParIterableLike.flatMap$(this, f);
   }

   public boolean forall(final Function1 p) {
      return ParIterableLike.forall$(this, p);
   }

   public boolean exists(final Function1 p) {
      return ParIterableLike.exists$(this, p);
   }

   public Option find(final Function1 p) {
      return ParIterableLike.find$(this, p);
   }

   public CombinerFactory combinerFactory() {
      return ParIterableLike.combinerFactory$(this);
   }

   public CombinerFactory combinerFactory(final Function0 cbf) {
      return ParIterableLike.combinerFactory$(this, cbf);
   }

   public scala.collection.parallel.ParIterable withFilter(final Function1 pred) {
      return ParIterableLike.withFilter$(this, pred);
   }

   public scala.collection.parallel.ParIterable filter(final Function1 pred) {
      return ParIterableLike.filter$(this, pred);
   }

   public scala.collection.parallel.ParIterable filterNot(final Function1 pred) {
      return ParIterableLike.filterNot$(this, pred);
   }

   public scala.collection.parallel.ParIterable $plus$plus(final IterableOnce that) {
      return ParIterableLike.$plus$plus$(this, that);
   }

   public Tuple2 partition(final Function1 pred) {
      return ParIterableLike.partition$(this, pred);
   }

   public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
      return ParIterableLike.groupBy$(this, f);
   }

   public scala.collection.parallel.ParIterable take(final int n) {
      return ParIterableLike.take$(this, n);
   }

   public scala.collection.parallel.ParIterable drop(final int n) {
      return ParIterableLike.drop$(this, n);
   }

   public scala.collection.parallel.ParIterable slice(final int unc_from, final int unc_until) {
      return ParIterableLike.slice$(this, unc_from, unc_until);
   }

   public Tuple2 splitAt(final int n) {
      return ParIterableLike.splitAt$(this, n);
   }

   public scala.collection.parallel.ParIterable scan(final Object z, final Function2 op) {
      return ParIterableLike.scan$(this, z, op);
   }

   public Iterable scanLeft(final Object z, final Function2 op) {
      return ParIterableLike.scanLeft$(this, z, op);
   }

   public Iterable scanRight(final Object z, final Function2 op) {
      return ParIterableLike.scanRight$(this, z, op);
   }

   public scala.collection.parallel.ParIterable takeWhile(final Function1 pred) {
      return ParIterableLike.takeWhile$(this, pred);
   }

   public Tuple2 span(final Function1 pred) {
      return ParIterableLike.span$(this, pred);
   }

   public scala.collection.parallel.ParIterable dropWhile(final Function1 pred) {
      return ParIterableLike.dropWhile$(this, pred);
   }

   public void copyToArray(final Object xs) {
      ParIterableLike.copyToArray$(this, xs);
   }

   public void copyToArray(final Object xs, final int start) {
      ParIterableLike.copyToArray$(this, xs, start);
   }

   public void copyToArray(final Object xs, final int start, final int len) {
      ParIterableLike.copyToArray$(this, xs, start, len);
   }

   public boolean sameElements(final IterableOnce that) {
      return ParIterableLike.sameElements$(this, that);
   }

   public scala.collection.parallel.ParIterable zip(final scala.collection.parallel.ParIterable that) {
      return ParIterableLike.zip$(this, (scala.collection.parallel.ParIterable)that);
   }

   public scala.collection.parallel.ParIterable zip(final Iterable that) {
      return ParIterableLike.zip$(this, (Iterable)that);
   }

   public scala.collection.parallel.ParIterable zipWithIndex() {
      return ParIterableLike.zipWithIndex$(this);
   }

   public scala.collection.parallel.ParIterable zipAll(final scala.collection.parallel.ParIterable that, final Object thisElem, final Object thatElem) {
      return ParIterableLike.zipAll$(this, that, thisElem, thatElem);
   }

   public Object toParCollection(final Function0 cbf) {
      return ParIterableLike.toParCollection$(this, cbf);
   }

   public Object toParMap(final Function0 cbf, final .less.colon.less ev) {
      return ParIterableLike.toParMap$(this, cbf, ev);
   }

   public Object toArray(final ClassTag evidence$1) {
      return ParIterableLike.toArray$(this, evidence$1);
   }

   public List toList() {
      return ParIterableLike.toList$(this);
   }

   public IndexedSeq toIndexedSeq() {
      return ParIterableLike.toIndexedSeq$(this);
   }

   /** @deprecated */
   public Stream toStream() {
      return ParIterableLike.toStream$(this);
   }

   public Iterator toIterator() {
      return ParIterableLike.toIterator$(this);
   }

   public Buffer toBuffer() {
      return ParIterableLike.toBuffer$(this);
   }

   /** @deprecated */
   public scala.collection.parallel.ParIterable toTraversable() {
      return ParIterableLike.toTraversable$(this);
   }

   public scala.collection.parallel.immutable.ParSet toSet() {
      return ParIterableLike.toSet$(this);
   }

   public scala.collection.parallel.immutable.ParMap toMap(final .less.colon.less ev) {
      return ParIterableLike.toMap$(this, ev);
   }

   public Vector toVector() {
      return ParIterableLike.toVector$(this);
   }

   public Object to(final Factory factory) {
      return ParIterableLike.to$(this, factory);
   }

   public int scanBlockSize() {
      return ParIterableLike.scanBlockSize$(this);
   }

   public Object $div$colon(final Object z, final Function2 op) {
      return ParIterableLike.$div$colon$(this, z, op);
   }

   public Object $colon$bslash(final Object z, final Function2 op) {
      return ParIterableLike.$colon$bslash$(this, z, op);
   }

   public Seq brokenInvariants() {
      return ParIterableLike.brokenInvariants$(this);
   }

   public ArrayBuffer debugBuffer() {
      return ParIterableLike.debugBuffer$(this);
   }

   public void debugclear() {
      ParIterableLike.debugclear$(this);
   }

   public ArrayBuffer debuglog(final String s) {
      return ParIterableLike.debuglog$(this, s);
   }

   public void printDebugBuffer() {
      ParIterableLike.printDebugBuffer$(this);
   }

   public Nothing parCombiner() {
      return CustomParallelizable.parCombiner$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IterableOnce.stepper$(this, shape);
   }

   public Combiner newBuilder() {
      return GenericParTemplate.newBuilder$(this);
   }

   public Combiner newCombiner() {
      return GenericParTemplate.newCombiner$(this);
   }

   public Combiner genericBuilder() {
      return GenericParTemplate.genericBuilder$(this);
   }

   public Combiner genericCombiner() {
      return GenericParTemplate.genericCombiner$(this);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return GenericTraversableTemplate.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return GenericTraversableTemplate.unzip3$(this, asTriple);
   }

   public scala.collection.parallel.ParIterable flatten(final Function1 asTraversable) {
      return GenericTraversableTemplate.flatten$(this, asTraversable);
   }

   public scala.collection.parallel.ParIterable transpose(final Function1 asTraversable) {
      return GenericTraversableTemplate.transpose$(this, asTraversable);
   }

   public int _loadFactor() {
      return this._loadFactor;
   }

   public void _loadFactor_$eq(final int x$1) {
      this._loadFactor = x$1;
   }

   public Object[] table() {
      return this.table;
   }

   public void table_$eq(final Object[] x$1) {
      this.table = x$1;
   }

   public int tableSize() {
      return this.tableSize;
   }

   public void tableSize_$eq(final int x$1) {
      this.tableSize = x$1;
   }

   public int threshold() {
      return this.threshold;
   }

   public void threshold_$eq(final int x$1) {
      this.threshold = x$1;
   }

   public int[] sizemap() {
      return this.sizemap;
   }

   public void sizemap_$eq(final int[] x$1) {
      this.sizemap = x$1;
   }

   public int seedvalue() {
      return this.seedvalue;
   }

   public void seedvalue_$eq(final int x$1) {
      this.seedvalue = x$1;
   }

   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
      return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
   }

   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(final TaskSupport x$1) {
      this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
   }

   public ParIterableLike.ScanNode$ ScanNode() {
      if (this.ScanNode$module == null) {
         this.ScanNode$lzycompute$1();
      }

      return this.ScanNode$module;
   }

   public ParIterableLike.ScanLeaf$ ScanLeaf() {
      if (this.ScanLeaf$module == null) {
         this.ScanLeaf$lzycompute$1();
      }

      return this.ScanLeaf$module;
   }

   public ParHashSet$ companion() {
      return ParHashSet$.MODULE$;
   }

   public ParHashSet empty() {
      return new ParHashSet();
   }

   public ParHashSetIterator iterator() {
      return this.splitter();
   }

   public void clear() {
      this.clearTable();
   }

   public HashSet seq() {
      return scala.collection.mutable.HashSet..MODULE$.from(this);
   }

   public int knownSize() {
      return this.tableSize();
   }

   public ParHashSet addOne(final Object elem) {
      this.addElem(elem);
      return this;
   }

   public ParHashSet subtractOne(final Object elem) {
      this.removeElem(elem);
      return this;
   }

   public String stringPrefix() {
      return "ParHashSet";
   }

   public boolean contains(final Object elem) {
      return this.containsElem(elem);
   }

   public ParHashSetIterator splitter() {
      return new ParHashSetIterator(0, this.table().length, this.size());
   }

   private void writeObject(final ObjectOutputStream s) {
      this.serializeTo(s);
   }

   private void readObject(final ObjectInputStream in) {
      this.init(in, (x) -> {
         $anonfun$readObject$1(x);
         return BoxedUnit.UNIT;
      });
   }

   public String debugInformation() {
      return DebugUtils$.MODULE$.buildString((append) -> {
         $anonfun$debugInformation$1(this, append);
         return BoxedUnit.UNIT;
      });
   }

   private final void ScanNode$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ScanNode$module == null) {
            this.ScanNode$module = new ParIterableLike.ScanNode$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void ScanLeaf$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ScanLeaf$module == null) {
            this.ScanLeaf$module = new ParIterableLike.ScanLeaf$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$readObject$1(final Object x) {
   }

   // $FF: synthetic method
   public static final void $anonfun$debugInformation$1(final ParHashSet $this, final Function1 append) {
      append.apply("Parallel flat hash table set");
      append.apply((new StringBuilder(11)).append("No. elems: ").append($this.tableSize()).toString());
      append.apply((new StringBuilder(14)).append("Table length: ").append($this.table().length).toString());
      append.apply("Table: ");
      append.apply(DebugUtils$.MODULE$.arrayString($this.table(), 0, $this.table().length));
      append.apply("Sizemap: ");
      append.apply(DebugUtils$.MODULE$.arrayString($this.sizemap(), 0, $this.sizemap().length));
   }

   public ParHashSet(final FlatHashTable.Contents contents) {
      GenericTraversableTemplate.$init$(this);
      GenericParTemplate.$init$(this);
      IterableOnce.$init$(this);
      Parallelizable.$init$(this);
      CustomParallelizable.$init$(this);
      ParIterableLike.$init$(this);
      scala.collection.parallel.ParIterable.$init$(this);
      ParIterable.$init$(this);
      Function1.$init$(this);
      scala.collection.parallel.ParSetLike.$init$(this);
      scala.collection.parallel.ParSet.$init$(this);
      Growable.$init$(this);
      Shrinkable.$init$(this);
      Cloneable.$init$(this);
      ParSetLike.$init$(this);
      ParSet.$init$(this);
      FlatHashTable.HashUtils.$init$(this);
      FlatHashTable.$init$(this);
      ParFlatHashTable.$init$(this);
      this.initWithContents(contents);
   }

   public ParHashSet() {
      this((FlatHashTable.Contents)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ParHashSetIterator extends ParFlatHashTable.ParFlatHashTableIterator {
      public ParHashSetIterator newIterator(final int start, final int until, final int total) {
         return this.scala$collection$parallel$mutable$ParHashSet$ParHashSetIterator$$$outer().new ParHashSetIterator(start, until, total);
      }

      // $FF: synthetic method
      public ParHashSet scala$collection$parallel$mutable$ParHashSet$ParHashSetIterator$$$outer() {
         return (ParHashSet)this.$outer;
      }

      public ParHashSetIterator(final int start, final int iteratesUntil, final int totalElements) {
         super(start, iteratesUntil, totalElements);
      }
   }
}
