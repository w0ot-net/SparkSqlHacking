package scala.collection.parallel.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicInteger;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Parallelizable;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate;
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.OldHashMap;
import scala.collection.immutable.OldHashMap$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.TrieIterator;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.parallel.AugmentedIterableIterator;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.RemainsIterator;
import scala.collection.parallel.SeqSplitter;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\t=e\u0001\u0002\u0017.\u0001YB\u0001\"\u001a\u0001\u0003\u0002\u0003\u0006I!\u0017\u0005\u0007M\u0002!\t!L4\t\u000b\u0019\u0004A\u0011A5\t\u000b)\u0004A\u0011I6\t\u000b=\u0004A\u0011\t9\t\rE\u0004\u0001\u0015\"\u0015s\u0011\u00151\b\u0001\"\u0001x\u0011\u0019y\b\u0001\"\u0011\u0002\u0002!9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0003C\u0001A\u0011AA\u0012\u0011\u001d\ti\u0003\u0001C!\u0003_Aq!a\u000e\u0001\t\u0003\ny\u0003C\u0004\u0002:\u0001!\t&a\u000f\u0007\r\u0005m\u0003\u0001AA/\u0011)\tyf\u0004BA\u0002\u0013\u0005\u0011\u0011\r\u0005\u000b\u0003\u007fz!\u00111A\u0005\u0002\u0005\u0005\u0005BCAG\u001f\t\u0005\t\u0015)\u0003\u0002d!Q\u0011qR\b\u0003\u0006\u0004%\t!a\f\t\u0015\u0005EuB!A!\u0002\u0013\t\t\u0004\u0003\u0004g\u001f\u0011\u0005\u00111\u0013\u0005\n\u0003;{\u0001\u0019!C\u0001\u0003_A\u0011\"a(\u0010\u0001\u0004%\t!!)\t\u0011\u0005\u0015v\u0002)Q\u0005\u0003cAa!a*\u0010\t\u00039\bbBAU\u001f\u0011%\u00111\u0016\u0005\b\u0003c{A\u0011AAZ\u0011\u001d\tYl\u0004C\u0001\u0003{Cq!a0\u0010\t\u0003\t\t\rC\u0004\u0002J>!\t!a\f\t\u000f\u0005-w\u0002\"\u0011\u0002N\"A\u0011q\u001c\u0001\u0005\u0002=\n\toB\u0004\u0002p6B\t!!=\u0007\r1j\u0003\u0012AAz\u0011\u00191'\u0005\"\u0001\u0003\b!1qN\tC\u0001\u0005\u0013Aa!\u001d\u0012\u0005\u0002\t]\u0001b\u0002B\u0015E\u0011\r!1\u0006\u0005\b\u0005\u001f\u0012C\u0011\u0001B)\u0011%\u0011)G\ta\u0001\n\u0003\u00119\u0007C\u0005\u0003~\t\u0002\r\u0011\"\u0001\u0003\u0000!A!1\u0011\u0012!B\u0013\u0011I\u0007C\u0005\u0003\u0006\n\n\t\u0011\"\u0003\u0003\b\nQ\u0001+\u0019:ICNDW*\u00199\u000b\u00059z\u0013!C5n[V$\u0018M\u00197f\u0015\t\u0001\u0014'\u0001\u0005qCJ\fG\u000e\\3m\u0015\t\u00114'\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001N\u0001\u0006g\u000e\fG.Y\u0002\u0001+\r9$\tT\n\u0007\u0001abd*\u00160\u0011\u0005eRT\"A\u001a\n\u0005m\u001a$AB!osJ+g\r\u0005\u0003>}\u0001[U\"A\u0017\n\u0005}j#A\u0002)be6\u000b\u0007\u000f\u0005\u0002B\u00052\u0001A!B\"\u0001\u0005\u0004!%!A&\u0012\u0005\u0015C\u0005CA\u001dG\u0013\t95GA\u0004O_RD\u0017N\\4\u0011\u0005eJ\u0015B\u0001&4\u0005\r\te.\u001f\t\u0003\u00032#a!\u0014\u0001\u0005\u0006\u0004!%!\u0001,\u0011\u000b=\u0013\u0006i\u0013+\u000e\u0003AS!!U\u0019\u0002\u000f\u001d,g.\u001a:jG&\u00111\u000b\u0015\u0002\u0016\u000f\u0016tWM]5d!\u0006\u0014X*\u00199UK6\u0004H.\u0019;f!\ti\u0004\u0001E\u0004>-\u0002[E\u000bW-\n\u0005]k#A\u0003)be6\u000b\u0007\u000fT5lKB!Q\b\u0001!L!\u0011QF\fQ&\u000e\u0003mS!AL\u0019\n\u0005u[&AC(mI\"\u000b7\u000f['baB\u0011qL\u0019\b\u0003s\u0001L!!Y\u001a\u0002\u000fA\f7m[1hK&\u00111\r\u001a\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003CN\nA\u0001\u001e:jK\u00061A(\u001b8jiz\"\"\u0001\u00175\t\u000b\u0015\u0014\u0001\u0019A-\u0015\u0003a\u000bA\"\\1q\u0007>l\u0007/\u00198j_:,\u0012\u0001\u001c\t\u0004\u001f6$\u0016B\u00018Q\u0005Y9UM\\3sS\u000e\u0004\u0016M]'ba\u000e{W\u000e]1oS>t\u0017!B3naRLX#\u0001-\u0002\u00179,woQ8nE&tWM]\u000b\u0002gB!Q\b\u001e!L\u0013\t)XFA\bICNDW*\u00199D_6\u0014\u0017N\\3s\u0003!\u0019\b\u000f\\5ui\u0016\u0014X#\u0001=\u0011\u0007eTH0D\u00010\u0013\tYxF\u0001\tJi\u0016\u0014\u0018M\u00197f'Bd\u0017\u000e\u001e;feB!\u0011( !L\u0013\tq8G\u0001\u0004UkBdWMM\u0001\u0004g\u0016\fX#A-\u0002\r\u0011j\u0017N\\;t)\rA\u0016q\u0001\u0005\u0007\u0003\u0013I\u0001\u0019\u0001!\u0002\u0003-\fQ\u0001\n9mkN,B!a\u0004\u0002\u0016Q!\u0011\u0011CA\u000e!\u0015i\u0004\u0001QA\n!\r\t\u0015Q\u0003\u0003\b\u0003/Q!\u0019AA\r\u0005\u0005)\u0016CA&I\u0011\u001d\tiB\u0003a\u0001\u0003?\t!a\u001b<\u0011\u000bej\b)a\u0005\u0002\u0007\u001d,G\u000f\u0006\u0003\u0002&\u0005-\u0002\u0003B\u001d\u0002(-K1!!\u000b4\u0005\u0019y\u0005\u000f^5p]\"1\u0011\u0011B\u0006A\u0002\u0001\u000bAa]5{KV\u0011\u0011\u0011\u0007\t\u0004s\u0005M\u0012bAA\u001bg\t\u0019\u0011J\u001c;\u0002\u0013-twn\u001e8TSj,\u0017!\u0002:fkN,WCBA\u001f\u0003\u000f\ni\u0005\u0006\u0004\u0002@\u0005E\u0013q\u000b\t\bs\u0006\u0005\u0013QIA&\u0013\r\t\u0019e\f\u0002\t\u0007>l'-\u001b8feB\u0019\u0011)a\u0012\u0005\r\u0005%cB1\u0001E\u0005\u0005\u0019\u0006cA!\u0002N\u00111\u0011q\n\bC\u0002\u0011\u0013A\u0001\u00165bi\"9\u00111\u000b\bA\u0002\u0005U\u0013\u0001B8mI\u000e\u0004R!OA\u0014\u0003\u007fAq!!\u0017\u000f\u0001\u0004\ty$\u0001\u0003oK^\u001c'A\u0005)be\"\u000b7\u000f['ba&#XM]1u_J\u001c2a\u0004\u001dy\u0003\u0019!(/\u001b;feV\u0011\u00111\r\t\u0006?\u0006\u0015\u0014\u0011N\u0005\u0004\u0003O\"'\u0001C%uKJ\fGo\u001c:\u0011\u000bej\b)a\u001b+\u0007-\u000big\u000b\u0002\u0002pA!\u0011\u0011OA>\u001b\t\t\u0019H\u0003\u0003\u0002v\u0005]\u0014!C;oG\",7m[3e\u0015\r\tIhM\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA?\u0003g\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003)!(/\u001b;fe~#S-\u001d\u000b\u0005\u0003\u0007\u000bI\tE\u0002:\u0003\u000bK1!a\"4\u0005\u0011)f.\u001b;\t\u0013\u0005-\u0015#!AA\u0002\u0005\r\u0014a\u0001=%c\u00059AO]5uKJ\u0004\u0013AA:{\u0003\r\u0019(\u0010\t\u000b\u0007\u0003+\u000bI*a'\u0011\u0007\u0005]u\"D\u0001\u0001\u0011\u001d\ty&\u0006a\u0001\u0003GBq!a$\u0016\u0001\u0004\t\t$A\u0001j\u0003\u0015Iw\fJ3r)\u0011\t\u0019)a)\t\u0013\u0005-u#!AA\u0002\u0005E\u0012AA5!\u0003\r!W\u000f]\u0001\u0010IV\u0004hI]8n\u0013R,'/\u0019;peR!\u0011QSAW\u0011\u001d\tyK\u0007a\u0001\u0003G\n!!\u001b;\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u0005U\u0006\u0003B0\u00028bL1!!/e\u0005\r\u0019V-]\u0001\u0005]\u0016DH\u000fF\u0001}\u0003\u001dA\u0017m\u001d(fqR,\"!a1\u0011\u0007e\n)-C\u0002\u0002HN\u0012qAQ8pY\u0016\fg.A\u0005sK6\f\u0017N\\5oO\u0006AAo\\*ue&tw\r\u0006\u0002\u0002PB!\u0011\u0011[An\u001b\t\t\u0019N\u0003\u0003\u0002V\u0006]\u0017\u0001\u00027b]\u001eT!!!7\u0002\t)\fg/Y\u0005\u0005\u0003;\f\u0019N\u0001\u0004TiJLgnZ\u0001\u000faJLg\u000e\u001e#fEV<\u0017J\u001c4p)\t\t\u0019\tK\u0004\u0001\u0003K\fY/!<\u0011\u0007e\n9/C\u0002\u0002jN\u0012\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\r\t!\u0002U1s\u0011\u0006\u001c\b.T1q!\ti$eE\u0003#\u0003k\fi\u0010\u0005\u0004P\u0003o$\u00161`\u0005\u0004\u0003s\u0004&!\u0004)be6\u000b\u0007OR1di>\u0014\u0018\u0010\u0005\u0002[9B!\u0011q B\u0003\u001b\t\u0011\tA\u0003\u0003\u0003\u0004\u0005]\u0017AA5p\u0013\r\u0019'\u0011\u0001\u000b\u0003\u0003c,bAa\u0003\u0003\u0012\tUQC\u0001B\u0007!\u0019i\u0004Aa\u0004\u0003\u0014A\u0019\u0011I!\u0005\u0005\u000b\r##\u0019\u0001#\u0011\u0007\u0005\u0013)\u0002B\u0003NI\t\u0007A)\u0006\u0004\u0003\u001a\t\u0005\"QE\u000b\u0003\u00057\u0001r!_A!\u0005;\u00119\u0003\u0005\u0004:{\n}!1\u0005\t\u0004\u0003\n\u0005B!B\"&\u0005\u0004!\u0005cA!\u0003&\u0011)Q*\nb\u0001\tB1Q\b\u0001B\u0010\u0005G\tAbY1o\u0005VLG\u000e\u001a$s_6,\"B!\f\u0003:\t}\"q\tB&+\t\u0011y\u0003E\u0005P\u0005c\u0011)Da\u0011\u0003N%\u0019!1\u0007)\u0003\u001d\r\u000bgnQ8nE&tWM\u0012:p[B1Q\b\u0001B\u001c\u0005{\u00012!\u0011B\u001d\t\u0019\u0011YD\nb\u0001\t\n)aI]8n\u0017B\u0019\u0011Ia\u0010\u0005\r\t\u0005cE1\u0001E\u0005\u00151%o\\7W!\u0019ITP!\u0012\u0003JA\u0019\u0011Ia\u0012\u0005\u000b\r3#\u0019\u0001#\u0011\u0007\u0005\u0013Y\u0005B\u0003NM\t\u0007A\t\u0005\u0004>\u0001\t\u0015#\u0011J\u0001\tMJ|W\u000e\u0016:jKV1!1\u000bB-\u0005;\"BA!\u0016\u0003`A1Q\b\u0001B,\u00057\u00022!\u0011B-\t\u0015\u0019uE1\u0001E!\r\t%Q\f\u0003\u0006\u001b\u001e\u0012\r\u0001\u0012\u0005\b\u0005C:\u0003\u0019\u0001B2\u0003\u0005!\bC\u0002.]\u0005/\u0012Y&A\u0007u_R\fGnY8nE&tWm]\u000b\u0003\u0005S\u0002BAa\u001b\u0003z5\u0011!Q\u000e\u0006\u0005\u0005_\u0012\t(\u0001\u0004bi>l\u0017n\u0019\u0006\u0005\u0005g\u0012)(\u0001\u0006d_:\u001cWO\u001d:f]RTAAa\u001e\u0002X\u0006!Q\u000f^5m\u0013\u0011\u0011YH!\u001c\u0003\u001b\u0005#x.\\5d\u0013:$XmZ3s\u0003E!x\u000e^1mG>l'-\u001b8fg~#S-\u001d\u000b\u0005\u0003\u0007\u0013\t\tC\u0005\u0002\f&\n\t\u00111\u0001\u0003j\u0005qAo\u001c;bY\u000e|WNY5oKN\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001BE!\u0011\t\tNa#\n\t\t5\u00151\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class ParHashMap implements ParMap, Serializable {
   private static final long serialVersionUID = 3L;
   private final OldHashMap trie;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public static void totalcombines_$eq(final AtomicInteger x$1) {
      ParHashMap$.MODULE$.totalcombines_$eq(x$1);
   }

   public static AtomicInteger totalcombines() {
      return ParHashMap$.MODULE$.totalcombines();
   }

   public static ParHashMap fromTrie(final OldHashMap t) {
      return ParHashMap$.MODULE$.fromTrie(t);
   }

   public static CanCombineFrom canBuildFrom() {
      return ParHashMap$.MODULE$.canBuildFrom();
   }

   public static Factory toFactory() {
      return ParHashMap$.MODULE$.toFactory();
   }

   public String stringPrefix() {
      return ParMap.stringPrefix$(this);
   }

   public ParMap withDefault(final Function1 d) {
      return ParMap.withDefault$(this, d);
   }

   public ParMap withDefaultValue(final Object d) {
      return ParMap.withDefaultValue$(this, d);
   }

   public ParMap toMap(final .less.colon.less ev) {
      return ParMapLike.toMap$(this, ev);
   }

   public ParMap updated(final Object key, final Object value) {
      return ParMapLike.updated$(this, key, value);
   }

   public GenericParCompanion companion() {
      return ParIterable.companion$(this);
   }

   public ParIterable toIterable() {
      return ParIterable.toIterable$(this);
   }

   public ParSeq toSeq() {
      return ParIterable.toSeq$(this);
   }

   public boolean canEqual(final Object that) {
      return scala.collection.parallel.ParMapLike.canEqual$(this, that);
   }

   public boolean equals(final Object that) {
      return scala.collection.parallel.ParMapLike.equals$(this, that);
   }

   public int hashCode() {
      return scala.collection.parallel.ParMapLike.hashCode$(this);
   }

   public Object default(final Object key) {
      return scala.collection.parallel.ParMapLike.default$(this, key);
   }

   public Object apply(final Object key) {
      return scala.collection.parallel.ParMapLike.apply$(this, key);
   }

   public Object getOrElse(final Object key, final Function0 default) {
      return scala.collection.parallel.ParMapLike.getOrElse$(this, key, default);
   }

   public boolean contains(final Object key) {
      return scala.collection.parallel.ParMapLike.contains$(this, key);
   }

   public boolean isDefinedAt(final Object key) {
      return scala.collection.parallel.ParMapLike.isDefinedAt$(this, key);
   }

   public IterableSplitter keysIterator() {
      return scala.collection.parallel.ParMapLike.keysIterator$(this);
   }

   public IterableSplitter valuesIterator() {
      return scala.collection.parallel.ParMapLike.valuesIterator$(this);
   }

   public scala.collection.parallel.ParSet keySet() {
      return scala.collection.parallel.ParMapLike.keySet$(this);
   }

   public scala.collection.parallel.ParIterable keys() {
      return scala.collection.parallel.ParMapLike.keys$(this);
   }

   public scala.collection.parallel.ParIterable values() {
      return scala.collection.parallel.ParMapLike.values$(this);
   }

   public scala.collection.parallel.ParMap filterKeys(final Function1 p) {
      return scala.collection.parallel.ParMapLike.filterKeys$(this, p);
   }

   public scala.collection.parallel.ParMap mapValues(final Function1 f) {
      return scala.collection.parallel.ParMapLike.mapValues$(this, f);
   }

   public scala.collection.parallel.ParMap map(final Function1 f) {
      return scala.collection.parallel.ParMapLike.map$(this, f);
   }

   public scala.collection.parallel.ParMap collect(final PartialFunction pf) {
      return scala.collection.parallel.ParMapLike.collect$(this, pf);
   }

   public scala.collection.parallel.ParMap flatMap(final Function1 f) {
      return scala.collection.parallel.ParMapLike.flatMap$(this, f);
   }

   public scala.collection.parallel.ParMap concat(final IterableOnce that) {
      return scala.collection.parallel.ParMapLike.concat$(this, that);
   }

   public final scala.collection.parallel.ParMap $plus$plus(final IterableOnce xs) {
      return scala.collection.parallel.ParMapLike.$plus$plus$(this, xs);
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

   public Splitter iterator() {
      return ParIterableLike.iterator$(this);
   }

   public scala.collection.parallel.ParIterable par() {
      return ParIterableLike.par$(this);
   }

   public boolean isStrictSplitterCollection() {
      return ParIterableLike.isStrictSplitterCollection$(this);
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

   public String toString() {
      return ParIterableLike.toString$(this);
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

   public ParMap groupBy(final Function1 f) {
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

   public ParSet toSet() {
      return ParIterableLike.toSet$(this);
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

   public String debugInformation() {
      return ParIterableLike.debugInformation$(this);
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

   public Combiner genericMapCombiner() {
      return GenericParMapTemplate.genericMapCombiner$(this);
   }

   public Combiner newBuilder() {
      return GenericParTemplate.newBuilder$(this);
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

   public GenericParMapCompanion mapCompanion() {
      return ParHashMap$.MODULE$;
   }

   public ParHashMap empty() {
      return new ParHashMap();
   }

   public HashMapCombiner newCombiner() {
      return HashMapCombiner$.MODULE$.apply();
   }

   public IterableSplitter splitter() {
      return new ParHashMapIterator(this.trie.iterator(), this.trie.size());
   }

   public OldHashMap seq() {
      return this.trie;
   }

   public ParHashMap $minus(final Object k) {
      return new ParHashMap((OldHashMap)this.trie.$minus(k));
   }

   public ParHashMap $plus(final Tuple2 kv) {
      return new ParHashMap(this.trie.$plus(kv));
   }

   public Option get(final Object k) {
      return this.trie.get(k);
   }

   public int size() {
      return this.trie.size();
   }

   public int knownSize() {
      return this.trie.size();
   }

   public Combiner reuse(final Option oldc, final Combiner newc) {
      if (oldc instanceof Some) {
         Some var5 = (Some)oldc;
         Combiner old = (Combiner)var5.value();
         return old;
      } else if (scala.None..MODULE$.equals(oldc)) {
         return newc;
      } else {
         throw new MatchError(oldc);
      }
   }

   public void printDebugInfo() {
      scala.Predef..MODULE$.println("Parallel hash trie");
      scala.Predef..MODULE$.println((new StringBuilder(27)).append("Top level inner trie type: ").append(this.trie.getClass()).toString());
      OldHashMap var2 = this.trie;
      if (var2 instanceof OldHashMap.OldHashMap1) {
         OldHashMap.OldHashMap1 var3 = (OldHashMap.OldHashMap1)var2;
         scala.Predef..MODULE$.println("single node type");
         scala.Predef..MODULE$.println((new StringBuilder(12)).append("key stored: ").append(var3.getKey()).toString());
         scala.Predef..MODULE$.println((new StringBuilder(13)).append("hash of key: ").append(var3.getHash()).toString());
         scala.Predef..MODULE$.println((new StringBuilder(19)).append("computed hash of ").append(var3.getKey()).append(": ").append(var3.computeHashFor(var3.getKey())).toString());
         scala.Predef..MODULE$.println((new StringBuilder(15)).append("trie.get(key): ").append(var3.get(var3.getKey())).toString());
         BoxedUnit var4 = BoxedUnit.UNIT;
      } else {
         scala.Predef..MODULE$.println("other kind of node");
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
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

   public ParHashMap(final OldHashMap trie) {
      this.trie = trie;
      GenericTraversableTemplate.$init$(this);
      GenericParTemplate.$init$(this);
      GenericParMapTemplate.$init$(this);
      IterableOnce.$init$(this);
      Parallelizable.$init$(this);
      CustomParallelizable.$init$(this);
      ParIterableLike.$init$(this);
      scala.collection.parallel.ParIterable.$init$(this);
      scala.collection.parallel.ParMapLike.$init$(this);
      scala.collection.parallel.ParMap.$init$(this);
      ParIterable.$init$(this);
      ParMapLike.$init$(this);
      ParMap.$init$(this);
   }

   public ParHashMap() {
      this(OldHashMap$.MODULE$.empty());
   }

   public class ParHashMapIterator implements IterableSplitter {
      private Iterator triter;
      private final int sz;
      private int i;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final ParHashMap $outer;

      public Seq splitWithSignalling() {
         return IterableSplitter.splitWithSignalling$(this);
      }

      public boolean shouldSplitFurther(final scala.collection.parallel.ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.buildString$(this, closure);
      }

      public String debugInformation() {
         return IterableSplitter.debugInformation$(this);
      }

      public IterableSplitter.Taken newTaken(final int until) {
         return IterableSplitter.newTaken$(this, until);
      }

      public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
         return IterableSplitter.newSliceInternal$(this, it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.drop$(this, n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.take$(this, n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.slice$(this, from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.map$(this, f);
      }

      public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.appendParIterable$(this, that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.zipParSeq$(this, that);
      }

      public IterableSplitter.ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

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

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
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

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
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

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
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

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public Iterator triter() {
         return this.triter;
      }

      public void triter_$eq(final Iterator x$1) {
         this.triter = x$1;
      }

      public int sz() {
         return this.sz;
      }

      public int i() {
         return this.i;
      }

      public void i_$eq(final int x$1) {
         this.i = x$1;
      }

      public IterableSplitter dup() {
         Iterator var2 = this.triter();
         if (var2 instanceof TrieIterator) {
            TrieIterator var3 = (TrieIterator)var2;
            return this.dupFromIterator(var3.dupIterator());
         } else {
            Buffer buff = this.triter().toBuffer();
            this.triter_$eq(buff.iterator());
            return this.dupFromIterator(buff.iterator());
         }
      }

      private ParHashMapIterator dupFromIterator(final Iterator it) {
         ParHashMapIterator phit = this.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer().new ParHashMapIterator(it, this.sz());
         phit.i_$eq(this.i());
         return phit;
      }

      public Seq split() {
         if (this.remaining() < 2) {
            return new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$);
         } else {
            Iterator var4 = this.triter();
            if (var4 instanceof TrieIterator) {
               TrieIterator var5 = (TrieIterator)var4;
               int previousRemaining = this.remaining();
               Tuple2 var8 = var5.split();
               if (var8 != null) {
                  Tuple2 var9 = (Tuple2)var8._1();
                  Iterator snd = (Iterator)var8._2();
                  if (var9 != null) {
                     Iterator fst = (Iterator)var9._1();
                     int fstlength = var9._2$mcI$sp();
                     Tuple3 var7 = new Tuple3(fst, BoxesRunTime.boxToInteger(fstlength), snd);
                     Iterator fstx = (Iterator)var7._1();
                     int fstlengthx = BoxesRunTime.unboxToInt(var7._2());
                     Iterator snd = (Iterator)var7._3();
                     int sndlength = previousRemaining - fstlengthx;
                     return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer().new ParHashMapIterator(fstx, fstlengthx), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer().new ParHashMapIterator(snd, sndlength), scala.collection.immutable.Nil..MODULE$));
                  }
               }

               throw new MatchError(var8);
            } else {
               Buffer buff = this.triter().toBuffer();
               Tuple2 var19 = buff.splitAt(buff.length() / 2);
               if (var19 != null) {
                  Buffer fp = (Buffer)var19._1();
                  Buffer sp = (Buffer)var19._2();
                  Tuple2 var18 = new Tuple2(fp, sp);
                  Buffer fp = (Buffer)var18._1();
                  Buffer sp = (Buffer)var18._2();
                  return (Seq)(new scala.collection.immutable..colon.colon(fp, new scala.collection.immutable..colon.colon(sp, scala.collection.immutable.Nil..MODULE$))).map((b) -> this.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer().new ParHashMapIterator(b.iterator(), b.length()));
               } else {
                  throw new MatchError(var19);
               }
            }
         }
      }

      public Tuple2 next() {
         this.i_$eq(this.i() + 1);
         Tuple2 r = (Tuple2)this.triter().next();
         return r;
      }

      public boolean hasNext() {
         return this.i() < this.sz();
      }

      public int remaining() {
         return this.sz() - this.i();
      }

      public String toString() {
         return (new StringBuilder(18)).append("HashTrieIterator(").append(this.sz()).append(")").toString();
      }

      // $FF: synthetic method
      public ParHashMap scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer() {
         return this.$outer;
      }

      public ParHashMapIterator(final Iterator triter, final int sz) {
         this.triter = triter;
         this.sz = sz;
         if (ParHashMap.this == null) {
            throw null;
         } else {
            this.$outer = ParHashMap.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            this.i = 0;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
