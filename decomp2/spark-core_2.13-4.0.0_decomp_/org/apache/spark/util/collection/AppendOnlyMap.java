package org.apache.spark.util.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import java.util.NoSuchElementException;
import org.apache.spark.annotation.DeveloperApi;
import org.sparkproject.guava.hash.Hashing;
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
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t5a\u0001B\u00193\u0001uB\u0001\"\u001a\u0001\u0003\u0002\u0003\u0006IA\u001a\u0005\u0006S\u0002!\tA\u001b\u0005\b]\u0002\u0011\r\u0011\"\u0003p\u0011\u0019\u0019\b\u0001)A\u0005a\"9A\u000f\u0001a\u0001\n\u0013)\bb\u0002<\u0001\u0001\u0004%Ia\u001e\u0005\u0007{\u0002\u0001\u000b\u0015\u00024\t\u000fy\u0004\u0001\u0019!C\u0005k\"Aq\u0010\u0001a\u0001\n\u0013\t\t\u0001C\u0004\u0002\u0006\u0001\u0001\u000b\u0015\u00024\t\u0011\u0005\u001d\u0001\u00011A\u0005\nUD\u0011\"!\u0003\u0001\u0001\u0004%I!a\u0003\t\u000f\u0005=\u0001\u0001)Q\u0005M\"A\u0011\u0011\u0003\u0001A\u0002\u0013%Q\u000fC\u0005\u0002\u0014\u0001\u0001\r\u0011\"\u0003\u0002\u0016!9\u0011\u0011\u0004\u0001!B\u00131\u0007\"CA\u000e\u0001\u0001\u0007I\u0011BA\u000f\u0011%\t)\u0003\u0001a\u0001\n\u0013\t9\u0003\u0003\u0005\u0002,\u0001\u0001\u000b\u0015BA\u0010\u0011%\ti\u0003\u0001a\u0001\n\u0013\ty\u0003C\u0005\u00028\u0001\u0001\r\u0011\"\u0003\u0002:!A\u0011Q\b\u0001!B\u0013\t\t\u0004C\u0005\u0002@\u0001\u0001\r\u0011\"\u0003\u0002B!I\u00111\t\u0001A\u0002\u0013%\u0011Q\t\u0005\b\u0003\u0013\u0002\u0001\u0015)\u0003`\u0011%\tY\u0005\u0001a\u0001\n\u0013\ty\u0003C\u0005\u0002N\u0001\u0001\r\u0011\"\u0003\u0002P!A\u00111\u000b\u0001!B\u0013\t\t\u0004C\u0005\u0002V\u0001\u0011\r\u0011\"\u0003\u0002X!A\u0011\u0011\u000e\u0001!\u0002\u0013\tI\u0006C\u0004\u0002l\u0001!\t!!\u001c\t\u000f\u0005M\u0004\u0001\"\u0001\u0002v!9\u0011Q\u0010\u0001\u0005\u0002\u0005}\u0004bBAG\u0001\u0011\u0005\u0013q\u0012\u0005\u0007\u0003/\u0003A\u0011I;\t\u000f\u0005e\u0005\u0001\"\u0003\u0002\u001c\"9\u0011Q\u0014\u0001\u0005\n\u0005}\u0005bBAS\u0001\u0011E\u00111\u0014\u0005\b\u0003O\u0003A\u0011BAU\u0011\u001d\ty\u000b\u0001C\u0001\u0003cCq!!1\u0001\t\u0003\tycB\u0004\u0002RJBI!a5\u0007\rE\u0012\u0004\u0012BAk\u0011\u0019I7\u0006\"\u0001\u0002b\"A\u00111]\u0016C\u0002\u0013\u0005Q\u000fC\u0004\u0002f.\u0002\u000b\u0011\u00024\t\u0013\u0005\u001d8&%A\u0005\u0002\u0005%\b\"\u0003B\u0002W\u0005\u0005I\u0011\u0002B\u0003\u00055\t\u0005\u000f]3oI>sG._'ba*\u00111\u0007N\u0001\u000bG>dG.Z2uS>t'BA\u001b7\u0003\u0011)H/\u001b7\u000b\u0005]B\u0014!B:qCJ\\'BA\u001d;\u0003\u0019\t\u0007/Y2iK*\t1(A\u0002pe\u001e\u001c\u0001!F\u0002?-\u0002\u001cB\u0001A FEB\u0011\u0001iQ\u0007\u0002\u0003*\t!)A\u0003tG\u0006d\u0017-\u0003\u0002E\u0003\n1\u0011I\\=SK\u001a\u00042A\u0012(R\u001d\t9EJ\u0004\u0002I\u00176\t\u0011J\u0003\u0002Ky\u00051AH]8pizJ\u0011AQ\u0005\u0003\u001b\u0006\u000bq\u0001]1dW\u0006<W-\u0003\u0002P!\nA\u0011\n^3sC\ndWM\u0003\u0002N\u0003B!\u0001I\u0015+`\u0013\t\u0019\u0016I\u0001\u0004UkBdWM\r\t\u0003+Zc\u0001\u0001B\u0003X\u0001\t\u0007\u0001LA\u0001L#\tIF\f\u0005\u0002A5&\u00111,\u0011\u0002\b\u001d>$\b.\u001b8h!\t\u0001U,\u0003\u0002_\u0003\n\u0019\u0011I\\=\u0011\u0005U\u0003G!B1\u0001\u0005\u0004A&!\u0001,\u0011\u0005\u0019\u001b\u0017B\u00013Q\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003=Ig.\u001b;jC2\u001c\u0015\r]1dSRL\bC\u0001!h\u0013\tA\u0017IA\u0002J]R\fa\u0001P5oSRtDCA6n!\u0011a\u0007\u0001V0\u000e\u0003IBq!\u001a\u0002\u0011\u0002\u0003\u0007a-A\u0006M\u001f\u0006#uLR!D)>\u0013V#\u00019\u0011\u0005\u0001\u000b\u0018B\u0001:B\u0005\u0019!u.\u001e2mK\u0006aAjT!E?\u001a\u000b5\tV(SA\u0005A1-\u00199bG&$\u00180F\u0001g\u00031\u0019\u0017\r]1dSRLx\fJ3r)\tA8\u0010\u0005\u0002As&\u0011!0\u0011\u0002\u0005+:LG\u000fC\u0004}\r\u0005\u0005\t\u0019\u00014\u0002\u0007a$\u0013'A\u0005dCB\f7-\u001b;zA\u0005!Q.Y:l\u0003!i\u0017m]6`I\u0015\fHc\u0001=\u0002\u0004!9A0CA\u0001\u0002\u00041\u0017!B7bg.\u0004\u0013aB2veNK'0Z\u0001\fGV\u00148+\u001b>f?\u0012*\u0017\u000fF\u0002y\u0003\u001bAq\u0001 \u0007\u0002\u0002\u0003\u0007a-\u0001\u0005dkJ\u001c\u0016N_3!\u000359'o\\<UQJ,7\u000f[8mI\u0006\trM]8x)\"\u0014Xm\u001d5pY\u0012|F%Z9\u0015\u0007a\f9\u0002C\u0004}\u001f\u0005\u0005\t\u0019\u00014\u0002\u001d\u001d\u0014xn\u001e+ie\u0016\u001c\bn\u001c7eA\u0005!A-\u0019;b+\t\ty\u0002\u0005\u0003A\u0003Cy\u0014bAA\u0012\u0003\n)\u0011I\u001d:bs\u0006AA-\u0019;b?\u0012*\u0017\u000fF\u0002y\u0003SA\u0001\u0002 \n\u0002\u0002\u0003\u0007\u0011qD\u0001\u0006I\u0006$\u0018\rI\u0001\u000eQ\u00064XMT;mYZ\u000bG.^3\u0016\u0005\u0005E\u0002c\u0001!\u00024%\u0019\u0011QG!\u0003\u000f\t{w\u000e\\3b]\u0006\t\u0002.\u0019<f\u001dVdGNV1mk\u0016|F%Z9\u0015\u0007a\fY\u0004\u0003\u0005}+\u0005\u0005\t\u0019AA\u0019\u00039A\u0017M^3Ok2dg+\u00197vK\u0002\n\u0011B\\;mYZ\u000bG.^3\u0016\u0003}\u000bQB\\;mYZ\u000bG.^3`I\u0015\fHc\u0001=\u0002H!9A\u0010GA\u0001\u0002\u0004y\u0016A\u00038vY24\u0016\r\\;fA\u0005IA-Z:ue>LX\rZ\u0001\u000eI\u0016\u001cHO]8zK\u0012|F%Z9\u0015\u0007a\f\t\u0006\u0003\u0005}7\u0005\u0005\t\u0019AA\u0019\u0003)!Wm\u001d;s_f,G\rI\u0001\u0013I\u0016\u001cHO];di&|g.T3tg\u0006<W-\u0006\u0002\u0002ZA!\u00111LA3\u001b\t\tiF\u0003\u0003\u0002`\u0005\u0005\u0014\u0001\u00027b]\u001eT!!a\u0019\u0002\t)\fg/Y\u0005\u0005\u0003O\niF\u0001\u0004TiJLgnZ\u0001\u0014I\u0016\u001cHO];di&|g.T3tg\u0006<W\rI\u0001\u0006CB\u0004H.\u001f\u000b\u0004?\u0006=\u0004BBA9?\u0001\u0007A+A\u0002lKf\fa!\u001e9eCR,G#\u0002=\u0002x\u0005e\u0004BBA9A\u0001\u0007A\u000b\u0003\u0004\u0002|\u0001\u0002\raX\u0001\u0006m\u0006dW/Z\u0001\fG\"\fgnZ3WC2,X\rF\u0003`\u0003\u0003\u000b\u0019\t\u0003\u0004\u0002r\u0005\u0002\r\u0001\u0016\u0005\b\u0003\u000b\u000b\u0003\u0019AAD\u0003))\b\u000fZ1uK\u001a+hn\u0019\t\b\u0001\u0006%\u0015\u0011G0`\u0013\r\tY)\u0011\u0002\n\rVt7\r^5p]J\n\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003#\u0003BARAJ#&\u0019\u0011Q\u0013)\u0003\u0011%#XM]1u_J\fAa]5{K\u0006i\u0011N\\2sK6,g\u000e^*ju\u0016$\u0012\u0001_\u0001\u0007e\u0016D\u0017m\u001d5\u0015\u0007\u0019\f\t\u000b\u0003\u0004\u0002$\u0016\u0002\rAZ\u0001\u0002Q\u0006IqM]8x)\u0006\u0014G.Z\u0001\r]\u0016DH\u000fU8xKJ|eM\r\u000b\u0004M\u0006-\u0006BBAWO\u0001\u0007a-A\u0001o\u0003e!Wm\u001d;sk\u000e$\u0018N^3T_J$X\rZ%uKJ\fGo\u001c:\u0015\t\u0005E\u00151\u0017\u0005\b\u0003kC\u0003\u0019AA\\\u00035YW-_\"p[B\f'/\u0019;peB)\u0011\u0011XA_)6\u0011\u00111\u0018\u0006\u0004k\u0005\u0005\u0014\u0002BA`\u0003w\u0013!bQ8na\u0006\u0014\u0018\r^8s\u0003=\tGo\u0012:poRC'/Z:i_2$\u0007f\u0001\u0001\u0002FB!\u0011qYAg\u001b\t\tIMC\u0002\u0002LZ\n!\"\u00198o_R\fG/[8o\u0013\u0011\ty-!3\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u001b\u0005\u0003\b/\u001a8e\u001f:d\u00170T1q!\ta7f\u0005\u0003,\u007f\u0005]\u0007\u0003BAm\u0003?l!!a7\u000b\t\u0005u\u0017\u0011M\u0001\u0003S>L1\u0001ZAn)\t\t\u0019.\u0001\tN\u0003bKU*V'`\u0007\u0006\u0003\u0016iQ%U3\u0006\tR*\u0011-J\u001bVkulQ!Q\u0003\u000eKE+\u0017\u0011\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\u0019\tY/a@\u0003\u0002U\u0011\u0011Q\u001e\u0016\u0004M\u0006=8FAAy!\u0011\t\u00190a?\u000e\u0005\u0005U(\u0002BA|\u0003s\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005-\u0017)\u0003\u0003\u0002~\u0006U(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)qk\fb\u00011\u0012)\u0011m\fb\u00011\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u0001\t\u0005\u00037\u0012I!\u0003\u0003\u0003\f\u0005u#AB(cU\u0016\u001cG\u000f"
)
public class AppendOnlyMap implements Iterable, Serializable {
   private final double LOAD_FACTOR;
   private int org$apache$spark$util$collection$AppendOnlyMap$$capacity;
   private int mask;
   private int curSize;
   private int growThreshold;
   private Object[] org$apache$spark$util$collection$AppendOnlyMap$$data;
   private boolean org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue;
   private Object org$apache$spark$util$collection$AppendOnlyMap$$nullValue;
   private boolean destroyed;
   private final String destructionMessage;

   public static int $lessinit$greater$default$1() {
      return AppendOnlyMap$.MODULE$.$lessinit$greater$default$1();
   }

   public static int MAXIMUM_CAPACITY() {
      return AppendOnlyMap$.MODULE$.MAXIMUM_CAPACITY();
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

   public String toString() {
      return Iterable.toString$(this);
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

   private double LOAD_FACTOR() {
      return this.LOAD_FACTOR;
   }

   public int org$apache$spark$util$collection$AppendOnlyMap$$capacity() {
      return this.org$apache$spark$util$collection$AppendOnlyMap$$capacity;
   }

   private void capacity_$eq(final int x$1) {
      this.org$apache$spark$util$collection$AppendOnlyMap$$capacity = x$1;
   }

   private int mask() {
      return this.mask;
   }

   private void mask_$eq(final int x$1) {
      this.mask = x$1;
   }

   private int curSize() {
      return this.curSize;
   }

   private void curSize_$eq(final int x$1) {
      this.curSize = x$1;
   }

   private int growThreshold() {
      return this.growThreshold;
   }

   private void growThreshold_$eq(final int x$1) {
      this.growThreshold = x$1;
   }

   public Object[] org$apache$spark$util$collection$AppendOnlyMap$$data() {
      return this.org$apache$spark$util$collection$AppendOnlyMap$$data;
   }

   private void data_$eq(final Object[] x$1) {
      this.org$apache$spark$util$collection$AppendOnlyMap$$data = x$1;
   }

   public boolean org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue() {
      return this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue;
   }

   private void haveNullValue_$eq(final boolean x$1) {
      this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue = x$1;
   }

   public Object org$apache$spark$util$collection$AppendOnlyMap$$nullValue() {
      return this.org$apache$spark$util$collection$AppendOnlyMap$$nullValue;
   }

   private void nullValue_$eq(final Object x$1) {
      this.org$apache$spark$util$collection$AppendOnlyMap$$nullValue = x$1;
   }

   private boolean destroyed() {
      return this.destroyed;
   }

   private void destroyed_$eq(final boolean x$1) {
      this.destroyed = x$1;
   }

   private String destructionMessage() {
      return this.destructionMessage;
   }

   public Object apply(final Object key) {
      scala.Predef..MODULE$.assert(!this.destroyed(), () -> this.destructionMessage());
      Object k = key;
      if (key == null) {
         return this.org$apache$spark$util$collection$AppendOnlyMap$$nullValue();
      } else {
         int pos = this.rehash(key.hashCode()) & this.mask();
         int i = 1;

         while(true) {
            Object curKey = this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos];
            if (k == curKey || k.equals(curKey)) {
               return this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos + 1];
            }

            if (curKey == null) {
               return null;
            }

            pos = pos + i & this.mask();
            ++i;
         }
      }
   }

   public void update(final Object key, final Object value) {
      scala.Predef..MODULE$.assert(!this.destroyed(), () -> this.destructionMessage());
      Object k = key;
      if (key == null) {
         if (!this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue()) {
            this.incrementSize();
         }

         this.nullValue_$eq(value);
         this.haveNullValue_$eq(true);
      } else {
         int pos = this.rehash(key.hashCode()) & this.mask();
         int i = 1;

         while(true) {
            Object curKey = this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos];
            if (curKey == null) {
               this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos] = k;
               this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos + 1] = value;
               this.incrementSize();
               return;
            }

            if (k == curKey || k.equals(curKey)) {
               this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos + 1] = value;
               return;
            }

            pos = pos + i & this.mask();
            ++i;
         }
      }
   }

   public Object changeValue(final Object key, final Function2 updateFunc) {
      scala.Predef..MODULE$.assert(!this.destroyed(), () -> this.destructionMessage());
      Object k = key;
      if (key == null) {
         if (!this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue()) {
            this.incrementSize();
         }

         this.nullValue_$eq(updateFunc.apply(BoxesRunTime.boxToBoolean(this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue()), this.org$apache$spark$util$collection$AppendOnlyMap$$nullValue()));
         this.haveNullValue_$eq(true);
         return this.org$apache$spark$util$collection$AppendOnlyMap$$nullValue();
      } else {
         int pos = this.rehash(key.hashCode()) & this.mask();
         int i = 1;

         while(true) {
            Object curKey = this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos];
            if (curKey == null) {
               Object newValue = updateFunc.apply(BoxesRunTime.boxToBoolean(false), (Object)null);
               this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos] = k;
               this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos + 1] = newValue;
               this.incrementSize();
               return newValue;
            }

            if (k == curKey || k.equals(curKey)) {
               Object newValue = updateFunc.apply(BoxesRunTime.boxToBoolean(true), this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos + 1]);
               this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * pos + 1] = newValue;
               return newValue;
            }

            pos = pos + i & this.mask();
            ++i;
         }
      }
   }

   public Iterator iterator() {
      scala.Predef..MODULE$.assert(!this.destroyed(), () -> this.destructionMessage());
      return new Iterator() {
         private int pos;
         // $FF: synthetic field
         private final AppendOnlyMap $outer;

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

         private int pos() {
            return this.pos;
         }

         private void pos_$eq(final int x$1) {
            this.pos = x$1;
         }

         private Tuple2 nextValue() {
            if (this.pos() == -1) {
               if (this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue()) {
                  return new Tuple2((Object)null, this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$nullValue());
               }

               this.pos_$eq(this.pos() + 1);
            }

            while(this.pos() < this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$capacity()) {
               if (this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * this.pos()] != null) {
                  return new Tuple2(this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * this.pos()], this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * this.pos() + 1]);
               }

               this.pos_$eq(this.pos() + 1);
            }

            return null;
         }

         public boolean hasNext() {
            return this.nextValue() != null;
         }

         public Tuple2 next() {
            Tuple2 value = this.nextValue();
            if (value == null) {
               throw new NoSuchElementException("End of iterator");
            } else {
               this.pos_$eq(this.pos() + 1);
               return value;
            }
         }

         public {
            if (AppendOnlyMap.this == null) {
               throw null;
            } else {
               this.$outer = AppendOnlyMap.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = -1;
            }
         }
      };
   }

   public int size() {
      return this.curSize();
   }

   private void incrementSize() {
      this.curSize_$eq(this.curSize() + 1);
      if (this.curSize() > this.growThreshold()) {
         this.growTable();
      }
   }

   private int rehash(final int h) {
      return Hashing.murmur3_32_fixed().hashInt(h).asInt();
   }

   public void growTable() {
      int newCapacity = this.org$apache$spark$util$collection$AppendOnlyMap$$capacity() * 2;
      scala.Predef..MODULE$.require(newCapacity <= AppendOnlyMap$.MODULE$.MAXIMUM_CAPACITY(), () -> "Can't contain more than " + this.growThreshold() + " elements");
      Object[] newData = new Object[2 * newCapacity];
      int newMask = newCapacity - 1;

      for(int oldPos = 0; oldPos < this.org$apache$spark$util$collection$AppendOnlyMap$$capacity(); ++oldPos) {
         if (this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * oldPos] != null) {
            Object key = this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * oldPos];
            Object value = this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * oldPos + 1];
            int newPos = this.rehash(key.hashCode()) & newMask;
            int i = 1;
            boolean keepGoing = true;

            while(keepGoing) {
               Object curKey = newData[2 * newPos];
               if (curKey == null) {
                  newData[2 * newPos] = key;
                  newData[2 * newPos + 1] = value;
                  keepGoing = false;
               } else {
                  newPos = newPos + i & newMask;
                  ++i;
               }
            }
         }
      }

      this.data_$eq(newData);
      this.capacity_$eq(newCapacity);
      this.mask_$eq(newMask);
      this.growThreshold_$eq((int)(this.LOAD_FACTOR() * (double)newCapacity));
   }

   private int nextPowerOf2(final int n) {
      int highBit = Integer.highestOneBit(n);
      return highBit == n ? n : highBit << 1;
   }

   public Iterator destructiveSortedIterator(final Comparator keyComparator) {
      this.destroyed_$eq(true);
      int keyIndex = 0;

      IntRef newIndex;
      for(newIndex = IntRef.create(0); keyIndex < this.org$apache$spark$util$collection$AppendOnlyMap$$capacity(); ++keyIndex) {
         if (this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * keyIndex] != null) {
            this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * newIndex.elem] = this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * keyIndex];
            this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * newIndex.elem + 1] = this.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * keyIndex + 1];
            ++newIndex.elem;
         }
      }

      scala.Predef..MODULE$.assert(this.curSize() == newIndex.elem + (this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue() ? 1 : 0));
      (new Sorter(new KVArraySortDataFormat(scala.reflect.ClassTag..MODULE$.AnyRef()))).sort(this.org$apache$spark$util$collection$AppendOnlyMap$$data(), 0, newIndex.elem, keyComparator);
      return new Iterator(newIndex) {
         private int i;
         private boolean nullValueReady;
         // $FF: synthetic field
         private final AppendOnlyMap $outer;
         private final IntRef newIndex$1;

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

         private int i() {
            return this.i;
         }

         private void i_$eq(final int x$1) {
            this.i = x$1;
         }

         private boolean nullValueReady() {
            return this.nullValueReady;
         }

         private void nullValueReady_$eq(final boolean x$1) {
            this.nullValueReady = x$1;
         }

         public boolean hasNext() {
            return this.i() < this.newIndex$1.elem || this.nullValueReady();
         }

         public Tuple2 next() {
            if (this.nullValueReady()) {
               this.nullValueReady_$eq(false);
               return new Tuple2((Object)null, this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$nullValue());
            } else {
               Tuple2 item = new Tuple2(this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * this.i()], this.$outer.org$apache$spark$util$collection$AppendOnlyMap$$data()[2 * this.i() + 1]);
               this.i_$eq(this.i() + 1);
               return item;
            }
         }

         public {
            if (AppendOnlyMap.this == null) {
               throw null;
            } else {
               this.$outer = AppendOnlyMap.this;
               this.newIndex$1 = newIndex$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.i = 0;
               this.nullValueReady = AppendOnlyMap.this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue();
            }
         }
      };
   }

   public boolean atGrowThreshold() {
      return this.curSize() == this.growThreshold();
   }

   public AppendOnlyMap(final int initialCapacity) {
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      scala.Predef..MODULE$.require(initialCapacity <= AppendOnlyMap$.MODULE$.MAXIMUM_CAPACITY(), () -> "Can't make capacity bigger than " + AppendOnlyMap$.MODULE$.MAXIMUM_CAPACITY() + " elements");
      scala.Predef..MODULE$.require(initialCapacity >= 1, () -> "Invalid initial capacity");
      this.LOAD_FACTOR = 0.7;
      this.org$apache$spark$util$collection$AppendOnlyMap$$capacity = this.nextPowerOf2(initialCapacity);
      this.mask = this.org$apache$spark$util$collection$AppendOnlyMap$$capacity() - 1;
      this.curSize = 0;
      this.growThreshold = (int)(this.LOAD_FACTOR() * (double)this.org$apache$spark$util$collection$AppendOnlyMap$$capacity());
      this.org$apache$spark$util$collection$AppendOnlyMap$$data = new Object[2 * this.org$apache$spark$util$collection$AppendOnlyMap$$capacity()];
      this.org$apache$spark$util$collection$AppendOnlyMap$$haveNullValue = false;
      this.org$apache$spark$util$collection$AppendOnlyMap$$nullValue = null;
      this.destroyed = false;
      this.destructionMessage = "Map state is invalid from destructive sorting!";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
