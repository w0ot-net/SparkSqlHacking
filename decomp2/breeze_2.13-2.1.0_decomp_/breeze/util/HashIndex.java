package breeze.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
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
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005e\u0001B\u00181\u0001UBQ\u0001\u0017\u0001\u0005\u0002eCqa\u0017\u0001A\u0002\u0013%A\fC\u0004f\u0001\u0001\u0007I\u0011\u00024\t\r1\u0004\u0001\u0015)\u0003^\u0011\u001di\u0007\u00011A\u0005\n9Dq!\u001f\u0001A\u0002\u0013%!\u0010\u0003\u0004}\u0001\u0001\u0006Ka\u001c\u0005\u0006{\u0002!\tE \u0005\u0007\u007f\u0002!\t%!\u0001\t\u000f\u0005\u001d\u0001\u0001\"\u0011\u0002\n!9\u0011Q\u0003\u0001\u0005B\u0005]\u0001bBA\u0011\u0001\u0011\u0005\u00131\u0005\u0005\b\u0003S\u0001A\u0011IA\u0016\u0011\u001d\ty\u0003\u0001C!\u0003cAq!a\u000f\u0001\t\u0003\ni\u0004C\u0004\u0002B\u0001!\t!a\u0011\t\u000f\u00055\u0003\u0001\"\u0003\u0002P!9\u00111\u000f\u0001\u0005\n\u0005UtaBASa!\u0005\u0011q\u0015\u0004\u0007_AB\t!!+\t\ra#B\u0011AAY\r\u0019\t\u0019\f\u0006#\u00026\"I1L\u0006BK\u0002\u0013\u0005\u0011q\u0018\u0005\nYZ\u0011\t\u0012)A\u0005\u0003\u0003Da\u0001\u0017\f\u0005\u0002\u0005-\u0007bBAj-\u0011%\u0011q\n\u0005\n\u0003/4\u0012\u0011!C\u0001\u00033D\u0011\"a:\u0017#\u0003%\t!!;\t\u0013\t\ra#!A\u0005B\t\u0015\u0001\u0002\u0003B\u0007-\u0005\u0005I\u0011\u0001@\t\u0013\t=a#!A\u0005\u0002\tE\u0001\"\u0003B\u000b-\u0005\u0005I\u0011\tB\f\u0011%\u0011YBFA\u0001\n\u0003\u0011i\u0002C\u0005\u0003\"Y\t\t\u0011\"\u0011\u0003$!I!q\u0005\f\u0002\u0002\u0013\u0005#\u0011\u0006\u0005\n\u0005W1\u0012\u0011!C!\u0005[A\u0011Ba\f\u0017\u0003\u0003%\tE!\r\b\u0013\teB#!A\t\n\tmb!CAZ)\u0005\u0005\t\u0012\u0002B\u001f\u0011\u0019Av\u0005\"\u0001\u0003D!I!1F\u0014\u0002\u0002\u0013\u0015#Q\u0006\u0005\t\u007f\u001e\n\t\u0011\"!\u0003F!I\u0011qA\u0014\u0002\u0002\u0013\u0005%1\u000b\u0005\n\u0003\u001b:\u0013\u0011!C\u0005\u0003\u001fBqA!\u001a\u0015\t\u0013\u00119\u0007C\u0005\u0002NQ\t\t\u0011\"\u0003\u0002P\tI\u0001*Y:i\u0013:$W\r\u001f\u0006\u0003cI\nA!\u001e;jY*\t1'\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t14i\u0005\u0003\u0001oub\u0005C\u0001\u001d<\u001b\u0005I$\"\u0001\u001e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qJ$AB!osJ+g\rE\u0002?\u007f\u0005k\u0011\u0001M\u0005\u0003\u0001B\u0012A\"T;uC\ndW-\u00138eKb\u0004\"AQ\"\r\u0001\u0011)A\t\u0001b\u0001\u000b\n\tA+\u0005\u0002G\u0013B\u0011\u0001hR\u0005\u0003\u0011f\u0012qAT8uQ&tw\r\u0005\u00029\u0015&\u00111*\u000f\u0002\u0004\u0003:L\bCA'V\u001d\tq5K\u0004\u0002P%6\t\u0001K\u0003\u0002Ri\u00051AH]8pizJ\u0011AO\u0005\u0003)f\nq\u0001]1dW\u0006<W-\u0003\u0002W/\na1+\u001a:jC2L'0\u00192mK*\u0011A+O\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\u00032A\u0010\u0001B\u0003\u001dy'M[3diN,\u0012!\u0018\t\u0004=\u000e\fU\"A0\u000b\u0005\u0001\f\u0017aB7vi\u0006\u0014G.\u001a\u0006\u0003Ef\n!bY8mY\u0016\u001cG/[8o\u0013\t!wLA\u0006BeJ\f\u0017PQ;gM\u0016\u0014\u0018aC8cU\u0016\u001cGo]0%KF$\"a\u001a6\u0011\u0005aB\u0017BA5:\u0005\u0011)f.\u001b;\t\u000f-\u001c\u0011\u0011!a\u0001;\u0006\u0019\u0001\u0010J\u0019\u0002\u0011=\u0014'.Z2ug\u0002\nq!\u001b8eS\u000e,7/F\u0001p!\u0011\u0001H/\u0011<\u000e\u0003ET!!\r:\u000b\u0003M\fAA[1wC&\u0011Q/\u001d\u0002\b\u0011\u0006\u001c\b.T1q!\tAt/\u0003\u0002ys\t\u0019\u0011J\u001c;\u0002\u0017%tG-[2fg~#S-\u001d\u000b\u0003OnDqa\u001b\u0004\u0002\u0002\u0003\u0007q.\u0001\u0005j]\u0012L7-Z:!\u0003\u0011\u0019\u0018N_3\u0016\u0003Y\fQ!\u00199qYf$2A^A\u0002\u0011\u0019\t)!\u0003a\u0001\u0003\u0006\tA/A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0011\u0011\u0003\t\u0005q\u00055\u0011)C\u0002\u0002\u0010e\u0012aa\u00149uS>t\u0007BBA\n\u0015\u0001\u0007a/A\u0002q_N\f\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u00033\ty\u0002E\u00029\u00037I1!!\b:\u0005\u001d\u0011un\u001c7fC:Da!!\u0002\f\u0001\u0004\t\u0015\u0001C5oI\u0016Dx\n\u001d;\u0015\t\u0005\u0015\u0012q\u0005\t\u0005q\u00055a\u000f\u0003\u0004\u0002\u00061\u0001\r!Q\u0001\u0004O\u0016$HcA!\u0002.!1\u00111C\u0007A\u0002Y\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003g\u0001R!!\u000e\u00028\u0005k\u0011!Y\u0005\u0004\u0003s\t'\u0001C%uKJ\fGo\u001c:\u0002\u000b%tG-\u001a=\u0015\u0007Y\fy\u0004\u0003\u0004\u0002\u0006=\u0001\r!Q\u0001\u0006a\u0006L'o]\u000b\u0003\u0003\u000b\u0002b!!\u000e\u00028\u0005\u001d\u0003#\u0002\u001d\u0002J\u00053\u0018bAA&s\t1A+\u001e9mKJ\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0015\u0011\t\u0005M\u0013\u0011L\u0007\u0003\u0003+R1!a\u0016s\u0003\u0011a\u0017M\\4\n\t\u0005m\u0013Q\u000b\u0002\u0007\u001f\nTWm\u0019;)\u000bE\ty&!\u001d\u0011\u000ba\n\t'!\u001a\n\u0007\u0005\r\u0014H\u0001\u0004uQJ|wo\u001d\t\u0005\u0003O\ni'\u0004\u0002\u0002j)\u0019\u00111\u000e:\u0002\u0005%|\u0017\u0002BA8\u0003S\u0012Qc\u00142kK\u000e$8\u000b\u001e:fC6,\u0005pY3qi&|gn\t\u0002\u0002f\u0005Q!/Z1e\u001f\nTWm\u0019;\u0015\u0007\u001d\f9\bC\u0004\u0002zI\u0001\r!a\u001f\u0002\rM$(/Z1n!\u0011\t9'! \n\t\u0005}\u0014\u0011\u000e\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0007&\u0002\n\u0002\u0004\u0006-\u0005#\u0002\u001d\u0002b\u0005\u0015\u0005\u0003BA*\u0003\u000fKA!!#\u0002V\t12\t\\1tg:{GOR8v]\u0012,\u0005pY3qi&|gn\t\u0002\u0002\u0006\"*!#a$\u0002\u0018B)\u0001(!\u0019\u0002\u0012B!\u0011qMAJ\u0013\u0011\t)*!\u001b\u0003\u0017%{U\t_2faRLwN\\\u0012\u0003\u0003#Cs\u0001AAN\u0003C\u000b\u0019\u000bE\u00029\u0003;K1!a(:\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XM\b\u0005\u0016\b|\r\\HQ\f0\u0004%A\u0015m\u001d5J]\u0012,\u0007\u0010\u0005\u0002?)M!AcNAV!\rq\u0014QV\u0005\u0004\u0003_\u0003$aE*fe&\fG.\u001b>bE2,Gj\\4hS:<GCAAT\u00059\u0019VM]5bY&TX\r\u001a$pe6,B!a.\u0002JN)acNA]\u0019B\u0019\u0001(a/\n\u0007\u0005u\u0016HA\u0004Qe>$Wo\u0019;\u0016\u0005\u0005\u0005\u0007CBA\u001b\u0003\u0007\f9-C\u0002\u0002F\u0006\u0014!\"\u00138eKb,GmU3r!\r\u0011\u0015\u0011\u001a\u0003\u0006\tZ\u0011\r!\u0012\u000b\u0005\u0003\u001b\f\t\u000eE\u0003\u0002PZ\t9-D\u0001\u0015\u0011\u0019Y\u0016\u00041\u0001\u0002B\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<fQ\u0015Q\u0012qLA9\u0003\u0011\u0019w\u000e]=\u0016\t\u0005m\u0017\u0011\u001d\u000b\u0005\u0003;\f\u0019\u000fE\u0003\u0002PZ\ty\u000eE\u0002C\u0003C$Q\u0001R\u000eC\u0002\u0015C\u0001bW\u000e\u0011\u0002\u0003\u0007\u0011Q\u001d\t\u0007\u0003k\t\u0019-a8\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU!\u00111\u001eB\u0001+\t\tiO\u000b\u0003\u0002B\u0006=8FAAy!\u0011\t\u00190!@\u000e\u0005\u0005U(\u0002BA|\u0003s\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005m\u0018(\u0001\u0006b]:|G/\u0019;j_:LA!a@\u0002v\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b\u0011c\"\u0019A#\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u00119\u0001\u0005\u0003\u0002T\t%\u0011\u0002\u0002B\u0006\u0003+\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004\u0013\nM\u0001bB6 \u0003\u0003\u0005\rA^\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!\u0011\u0004\t\u0006\u0003k\t9$S\u0001\tG\u0006tW)];bYR!\u0011\u0011\u0004B\u0010\u0011\u001dY\u0017%!AA\u0002%\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!q\u0001B\u0013\u0011\u001dY'%!AA\u0002Y\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002m\u0006AAo\\*ue&tw\r\u0006\u0002\u0003\b\u00051Q-];bYN$B!!\u0007\u00034!91.JA\u0001\u0002\u0004I\u0005f\u0002\f\u0002\u001c\u0006\u0005&q\u0007\u0010\u0002\u0003\u0005q1+\u001a:jC2L'0\u001a3G_Jl\u0007cAAhOM!qe\u000eB !\u0011\t9G!\u0011\n\u0007Y\u000bI\u0007\u0006\u0002\u0003<U!!q\tB')\u0011\u0011IEa\u0014\u0011\u000b\u0005=gCa\u0013\u0011\u0007\t\u0013i\u0005B\u0003EU\t\u0007Q\t\u0003\u0004\\U\u0001\u0007!\u0011\u000b\t\u0007\u0003k\t\u0019Ma\u0013\u0016\t\tU#Q\f\u000b\u0005\u0005/\u0012y\u0006E\u00039\u0003\u001b\u0011I\u0006\u0005\u0004\u00026\u0005\r'1\f\t\u0004\u0005\nuC!\u0002#,\u0005\u0004)\u0005\"\u0003B1W\u0005\u0005\t\u0019\u0001B2\u0003\rAH\u0005\r\t\u0006\u0003\u001f4\"1L\u0001\tY><WI\u001d:peR\u0019qM!\u001b\t\u0011\t-T\u0006\"a\u0001\u0005[\n1a\u001d;s!\u0015A$q\u000eB:\u0013\r\u0011\t(\u000f\u0002\ty\tLh.Y7f}A!!Q\u000fB?\u001d\u0011\u00119H!\u001f\u0011\u0005=K\u0014b\u0001B>s\u00051\u0001K]3eK\u001aLAAa\u0003\u0003\u0000)\u0019!1P\u001d"
)
public class HashIndex implements MutableIndex {
   private static final long serialVersionUID = -7655100457525569617L;
   private ArrayBuffer objects;
   private HashMap indices;
   private int defaultHashCode;
   private volatile boolean bitmap$0;

   public int indexOf(final Object t) {
      return Index.indexOf$(this, t);
   }

   public boolean equals(final Object other) {
      return Index.equals$(this, other);
   }

   public int hashCode() {
      return Index.hashCode$(this);
   }

   public String toString() {
      return Index.toString$(this);
   }

   public EitherIndex $bar(final Index right) {
      return Index.$bar$(this, right);
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

   private int defaultHashCode$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.defaultHashCode = Index.defaultHashCode$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.defaultHashCode;
   }

   public int defaultHashCode() {
      return !this.bitmap$0 ? this.defaultHashCode$lzycompute() : this.defaultHashCode;
   }

   private ArrayBuffer objects() {
      return this.objects;
   }

   private void objects_$eq(final ArrayBuffer x$1) {
      this.objects = x$1;
   }

   private HashMap indices() {
      return this.indices;
   }

   private void indices_$eq(final HashMap x$1) {
      this.indices = x$1;
   }

   public int size() {
      return this.indices().size();
   }

   public int apply(final Object t) {
      return BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(this.indices().get(t)).getOrElse((JFunction0.mcI.sp)() -> -1));
   }

   public Option unapply(final int pos) {
      return (Option)(pos >= 0 && pos < this.objects().length() ? new Some(this.objects().apply(pos)) : scala.None..MODULE$);
   }

   public boolean contains(final Object t) {
      return this.indices().containsKey(t);
   }

   public Option indexOpt(final Object t) {
      return scala.Option..MODULE$.apply(this.indices().get(t));
   }

   public Object get(final int pos) {
      return this.objects().apply(pos);
   }

   public Iterator iterator() {
      return this.objects().iterator();
   }

   public int index(final Object t) {
      int var10000;
      if (!this.indices().containsKey(t)) {
         int ind = this.objects().size();
         this.objects().$plus$eq(t);
         this.indices().put(t, BoxesRunTime.boxToInteger(ind));
         var10000 = ind;
      } else {
         var10000 = BoxesRunTime.unboxToInt(this.indices().get(t));
      }

      return var10000;
   }

   public Iterator pairs() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.indices()).asScala().iterator();
   }

   private Object writeReplace() throws ObjectStreamException {
      return new SerializedForm(this.objects());
   }

   private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
      HashIndex$.MODULE$.breeze$util$HashIndex$$logError(() -> "Deserializing an old-style HashIndex. Taking counter measures");
      ObjectInputStream.GetField fields = stream.readFields();
      Object objects = fields.get("objects", (Object)null);
      this.objects_$eq((ArrayBuffer)objects);
      this.indices_$eq(new HashMap());
      ((IterableOps)this.objects().zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$readObject$2(check$ifrefutable$1))).foreach((x$3) -> BoxesRunTime.boxToInteger($anonfun$readObject$3(this, x$3)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$readObject$2(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final int $anonfun$readObject$3(final HashIndex $this, final Tuple2 x$3) {
      if (x$3 != null) {
         Object x = x$3._1();
         int i = x$3._2$mcI$sp();
         int var2 = BoxesRunTime.unboxToInt($this.indices().put(x, BoxesRunTime.boxToInteger(i)));
         return var2;
      } else {
         throw new MatchError(x$3);
      }
   }

   public HashIndex() {
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      Function1.$init$(this);
      Index.$init$(this);
      this.objects = new ArrayBuffer();
      this.indices = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SerializedForm implements Product, Serializable {
      private static final long serialVersionUID = 1L;
      private final scala.collection.IndexedSeq objects;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public scala.collection.IndexedSeq objects() {
         return this.objects;
      }

      private Object readResolve() throws ObjectStreamException {
         HashIndex ind = new HashIndex();
         this.objects().foreach((t) -> BoxesRunTime.boxToInteger($anonfun$readResolve$1(ind, t)));
         return ind;
      }

      public SerializedForm copy(final scala.collection.IndexedSeq objects) {
         return new SerializedForm(objects);
      }

      public scala.collection.IndexedSeq copy$default$1() {
         return this.objects();
      }

      public String productPrefix() {
         return "SerializedForm";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.objects();
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
         return x$1 instanceof SerializedForm;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "objects";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof SerializedForm) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        SerializedForm var4 = (SerializedForm)x$1;
                        scala.collection.IndexedSeq var10000 = this.objects();
                        scala.collection.IndexedSeq var5 = var4.objects();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public static final int $anonfun$readResolve$1(final HashIndex ind$1, final Object t) {
         return ind$1.index(t);
      }

      public SerializedForm(final scala.collection.IndexedSeq objects) {
         this.objects = objects;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class SerializedForm$ implements Serializable {
      public static final SerializedForm$ MODULE$ = new SerializedForm$();

      public final String toString() {
         return "SerializedForm";
      }

      public SerializedForm apply(final scala.collection.IndexedSeq objects) {
         return new SerializedForm(objects);
      }

      public Option unapply(final SerializedForm x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.objects()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SerializedForm$.class);
      }

      public SerializedForm$() {
      }
   }
}
