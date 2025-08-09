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
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.CustomParallelizable;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
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
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.Shrinkable;
import scala.collection.mutable.HashTable.HashUtils;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.CombinerFactory;
import scala.collection.parallel.IterableSplitter;
import scala.collection.parallel.ParIterableLike;
import scala.collection.parallel.Splitter;
import scala.collection.parallel.TaskSupport;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rh\u0001B\u001a5\u0001uB!\"a7\u0001\u0005\u0003\u0005\u000b\u0011BAo\u0011!\tI\u0001\u0001C\u0001q\u0005-X!BAx\u0001\u0001A\u0007bBA\u0005\u0001\u0011\u0005\u0011\u0011\u001f\u0005\b\u0003g\u0004A\u0011IA{\u0011\u001d\t9\u0003\u0001C!\u0003{D\u0001\"a\u000e\u0001A\u0013E\u0013q \u0005\b\u0005\u000f\u0001A\u0011\u0001B\u0005\u0011\u001d\u0011Y\u0001\u0001C\u0001\u0005\u001bAqA!\u0014\u0001\t\u0003\ny\u0001C\u0004\u0003P\u0001!\tA!\u0015\t\u000f\tM\u0003\u0001\"\u0001\u0003V!9!q\f\u0001\u0005\u0002\t\u0005\u0004b\u0002B4\u0001\u0011\u0005!\u0011\u000e\u0005\b\u0005_\u0002A\u0011\u0001B9\u0011\u001d\u0011)\b\u0001C\u0001\u0005oBqA! \u0001\t\u0003\u0011y\bC\u0004\u0003\u0004\u0002!\tE!\"\u0007\r\tM\u0001\u0001\u0001B\u000b\u0011)\u0011yb\u0005B\u0001B\u0003%\u0011\u0011\u0003\u0005\u000b\u0005C\u0019\"\u0011!Q\u0001\n\u0005E\u0001B\u0003B\u0012'\t\u0005\t\u0015!\u0003\u0002\u0012!I!QE\n\u0003\u0002\u0003\u0006I\u0001\u001b\u0005\b\u0003\u0013\u0019B\u0011\u0001B\u0014\u0011\u001d\u0011\td\u0005C\u0001\u0005gAqA!\u000f\u0014\t\u0003\u0011Y\u0004C\u0004\u0003\f\u0002!\tA!$\t\u000f\tU\u0005\u0001\"\u0003\u0003\u0018\"9!1\u0015\u0001\u0005\n\t\u0015\u0006\u0002\u0003BY\u0001\u0011\u0005cGa-\t\u000f\t\u0005\u0007\u0001\"\u0003\u0003D\"9!q\u001a\u0001\u0005\n\tEw!B;5\u0011\u00031h!B\u001a5\u0011\u00039\bbBA\u0005E\u0011\u0005\u00111\u0002\u0005\n\u0003\u001b\u0011\u0003\u0019!C\u0001\u0003\u001fA\u0011\"a\u0006#\u0001\u0004%\t!!\u0007\t\u0011\u0005\u0015\"\u0005)Q\u0005\u0003#Aq!a\n#\t\u0003\tI\u0003C\u0004\u00028\t\"\t!!\u000f\t\u000f\u0005U#\u0005b\u0001\u0002X\u00191\u00111\u0010\u0012\u0003\u0003{B!\"a(+\u0005\u000b\u0007I\u0011AAQ\u0011)\t\u0019K\u000bB\u0001B\u0003%\u0011q\u0011\u0005\u000b\u0003KS#\u00111A\u0005\u0002\u0005\u001d\u0006BCAUU\t\u0005\r\u0011\"\u0001\u0002,\"Q\u0011q\u0016\u0016\u0003\u0002\u0003\u0006K!a$\t\u000f\u0005%!\u0006\"\u0001\u00022\"9\u0011q\u0017\u0016\u0005B\u0005e\u0006\"CAfE\u0005\u0005I\u0011BAg\u0005)\u0001\u0016M\u001d%bg\"l\u0015\r\u001d\u0006\u0003kY\nq!\\;uC\ndWM\u0003\u00028q\u0005A\u0001/\u0019:bY2,GN\u0003\u0002:u\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003m\nQa]2bY\u0006\u001c\u0001!F\u0002?\u0013N\u001b\u0002\u0002A D+r+\u00171\u0013\t\u0003\u0001\u0006k\u0011AO\u0005\u0003\u0005j\u0012a!\u00118z%\u00164\u0007\u0003\u0002#F\u000fJk\u0011\u0001N\u0005\u0003\rR\u0012a\u0001U1s\u001b\u0006\u0004\bC\u0001%J\u0019\u0001!QA\u0013\u0001C\u0002-\u0013\u0011aS\t\u0003\u0019>\u0003\"\u0001Q'\n\u00059S$a\u0002(pi\"Lgn\u001a\t\u0003\u0001BK!!\u0015\u001e\u0003\u0007\u0005s\u0017\u0010\u0005\u0002I'\u0012)A\u000b\u0001b\u0001\u0017\n\ta\u000bE\u0003W3\u001e\u00136,D\u0001X\u0015\tA\u0006(A\u0004hK:,'/[2\n\u0005i;&!F$f]\u0016\u0014\u0018n\u0019)be6\u000b\u0007\u000fV3na2\fG/\u001a\t\u0003\t\u0002\u0001r\u0001R/H%n{\u0006-\u0003\u0002_i\tQ\u0001+\u0019:NCBd\u0015n[3\u0011\t\u0011\u0003qI\u0015\t\u0005C\u000e<%+D\u0001c\u0015\t)\u0004(\u0003\u0002eE\n9\u0001*Y:i\u001b\u0006\u0004\b#\u0002#g\u000fJC\u0017BA45\u00051\u0001\u0016M\u001d%bg\"$\u0016M\u00197f!\u0011I'f\u0012*\u000f\u0005)\fcBA6u\u001d\ta7O\u0004\u0002ne:\u0011a.]\u0007\u0002_*\u0011\u0001\u000fP\u0001\u0007yI|w\u000e\u001e \n\u0003mJ!!\u000f\u001e\n\u0005]B\u0014BA\u001b7\u0003)\u0001\u0016M\u001d%bg\"l\u0015\r\u001d\t\u0003\t\n\u001a2A\t=}!\u00111\u0016pW>\n\u0005i<&!\u0004)be6\u000b\u0007OR1di>\u0014\u0018\u0010\u0005\u0002bGB\u0019Q0!\u0002\u000e\u0003yT1a`A\u0001\u0003\tIwN\u0003\u0002\u0002\u0004\u0005!!.\u0019<b\u0013\r\t9A \u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Y\fQ!\u001b;feN,\"!!\u0005\u0011\u0007\u0001\u000b\u0019\"C\u0002\u0002\u0016i\u00121!\u00138u\u0003%IG/\u001a:t?\u0012*\u0017\u000f\u0006\u0003\u0002\u001c\u0005\u0005\u0002c\u0001!\u0002\u001e%\u0019\u0011q\u0004\u001e\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003G)\u0013\u0011!a\u0001\u0003#\t1\u0001\u001f\u00132\u0003\u0019IG/\u001a:tA\u0005)Q-\u001c9usV1\u00111FA\u0019\u0003k)\"!!\f\u0011\r\u0011\u0003\u0011qFA\u001a!\rA\u0015\u0011\u0007\u0003\u0006\u0015\u001e\u0012\ra\u0013\t\u0004\u0011\u0006UB!\u0002+(\u0005\u0004Y\u0015a\u00038fo\u000e{WNY5oKJ,b!a\u000f\u0002N\u0005ESCAA\u001f!!\ty$!\u0011\u0002F\u0005MS\"\u0001\u001c\n\u0007\u0005\rcG\u0001\u0005D_6\u0014\u0017N\\3s!\u001d\u0001\u0015qIA&\u0003\u001fJ1!!\u0013;\u0005\u0019!V\u000f\u001d7feA\u0019\u0001*!\u0014\u0005\u000b)C#\u0019A&\u0011\u0007!\u000b\t\u0006B\u0003UQ\t\u00071\n\u0005\u0004E\u0001\u0005-\u0013qJ\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u000b\u00033\n)'a\u001b\u0002t\u0005]TCAA.!%1\u0016QLA1\u0003_\nI(C\u0002\u0002`]\u0013abQ1o\u0007>l'-\u001b8f\rJ|W\u000e\u0005\u0004E\u0001\u0005\r\u0014\u0011\u000e\t\u0004\u0011\u0006\u0015DABA4S\t\u00071JA\u0003Ge>l7\nE\u0002I\u0003W\"a!!\u001c*\u0005\u0004Y%!\u0002$s_64\u0006c\u0002!\u0002H\u0005E\u0014Q\u000f\t\u0004\u0011\u0006MD!\u0002&*\u0005\u0004Y\u0005c\u0001%\u0002x\u0011)A+\u000bb\u0001\u0017B1A\tAA9\u0003k\u0012A\u0002R3gCVdG/\u00128uef,b!a \u0002\n\u0006E5C\u0002\u0016@\u0003\u0003\u000b\u0019\nE\u0004b\u0003\u0007\u000b9)a#\n\u0007\u0005\u0015%MA\u0005ICNDWI\u001c;ssB\u0019\u0001*!#\u0005\u000b)S#\u0019A&\u0011\u000f\u00055%&a\"\u0002\u00106\t!\u0005E\u0002I\u0003##Q\u0001\u0016\u0016C\u0002-\u0003B!!&\u0002\u001c:\u0019\u0001)a&\n\u0007\u0005e%(A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u001d\u0011Q\u0014\u0006\u0004\u00033S\u0014aA6fsV\u0011\u0011qQ\u0001\u0005W\u0016L\b%A\u0003wC2,X-\u0006\u0002\u0002\u0010\u0006Ia/\u00197vK~#S-\u001d\u000b\u0005\u00037\ti\u000bC\u0005\u0002$9\n\t\u00111\u0001\u0002\u0010\u00061a/\u00197vK\u0002\"b!a#\u00024\u0006U\u0006bBAPa\u0001\u0007\u0011q\u0011\u0005\b\u0003K\u0003\u0004\u0019AAH\u0003!!xn\u0015;sS:<GCAA^!\u0011\ti,!2\u000f\t\u0005}\u0016\u0011\u0019\t\u0003]jJ1!a1;\u0003\u0019\u0001&/\u001a3fM&!\u0011qYAe\u0005\u0019\u0019FO]5oO*\u0019\u00111\u0019\u001e\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005=\u0007\u0003BAi\u0003/l!!a5\u000b\t\u0005U\u0017\u0011A\u0001\u0005Y\u0006tw-\u0003\u0003\u0002Z\u0006M'AB(cU\u0016\u001cG/\u0001\u0005d_:$XM\u001c;t!\u0019\ty.!:HQ:\u0019A)!9\n\u0007\u0005\rH'\u0001\u0007QCJD\u0015m\u001d5UC\ndW-\u0003\u0003\u0002h\u0006%(\u0001C\"p]R,g\u000e^:\u000b\u0007\u0005\rH\u0007F\u0002`\u0003[Dq!a7\u0003\u0001\u0004\tiNA\u0003F]R\u0014\u0018\u0010F\u0001`\u00031i\u0017\r]\"p[B\fg.[8o+\t\t9\u0010\u0005\u0003W\u0003s\\\u0016bAA~/\n1r)\u001a8fe&\u001c\u0007+\u0019:NCB\u001cu.\u001c9b]&|g.F\u0001`+\t\u0011\t\u0001E\u0003E\u0005\u00079%+C\u0002\u0003\u0006Q\u0012!\u0003U1s\u0011\u0006\u001c\b.T1q\u0007>l'-\u001b8fe\u0006\u00191/Z9\u0016\u0003\u0001\f\u0001b\u001d9mSR$XM]\u000b\u0003\u0005\u001f\u00012A!\u0005\u0014\u001b\u0005\u0001!A\u0005)be\"\u000b7\u000f['ba&#XM]1u_J\u001c2a\u0005B\f!!\u0011\tB!\u0007\u0003\u001e\t=\u0011b\u0001B\u000eM\niQI\u001c;ss&#XM]1u_J\u0004R\u0001QA$\u000fJ\u000bQa\u001d;beR\f\u0001\"\u001e8uS2LE\r_\u0001\ni>$\u0018\r\\*ju\u0016\f\u0011!\u001a\u000b\u000b\u0005\u001f\u0011ICa\u000b\u0003.\t=\u0002b\u0002B\u00101\u0001\u0007\u0011\u0011\u0003\u0005\b\u0005CA\u0002\u0019AA\t\u0011\u001d\u0011\u0019\u0003\u0007a\u0001\u0003#AaA!\n\u0019\u0001\u0004A\u0017AC3oiJL('\u001b;f[R!!Q\u0004B\u001b\u0011\u0019\u00119$\u0007a\u0001Q\u0006)QM\u001c;ss\u0006Ya.Z<Ji\u0016\u0014\u0018\r^8s))\u0011yA!\u0010\u0003B\t\u0015#\u0011\n\u0005\b\u0005\u007fQ\u0002\u0019AA\t\u0003\u001dIG\r\u001f$s_6DqAa\u0011\u001b\u0001\u0004\t\t\"\u0001\u0005jIb,f\u000e^5m\u0011\u001d\u00119E\u0007a\u0001\u0003#\tq\u0001^8uC2\u001c&\u0010\u0003\u0004\u0003Li\u0001\r\u0001[\u0001\u0003KN\f\u0011b\u001b8po:\u001c\u0016N_3\u0002\u000b\rdW-\u0019:\u0015\u0005\u0005m\u0011aA4fiR!!q\u000bB/!\u0011\u0001%\u0011\f*\n\u0007\tm#H\u0001\u0004PaRLwN\u001c\u0005\u0007\u0003?c\u0001\u0019A$\u0002\u0007A,H\u000f\u0006\u0004\u0003X\t\r$Q\r\u0005\u0007\u0003?k\u0001\u0019A$\t\r\u0005\u0015V\u00021\u0001S\u0003\u0019)\b\u000fZ1uKR1\u00111\u0004B6\u0005[Ba!a(\u000f\u0001\u00049\u0005BBAS\u001d\u0001\u0007!+\u0001\u0004sK6|g/\u001a\u000b\u0005\u0005/\u0012\u0019\b\u0003\u0004\u0002 >\u0001\raR\u0001\u0007C\u0012$wJ\\3\u0015\t\tE!\u0011\u0010\u0005\b\u0005w\u0002\u0002\u0019\u0001B\u000f\u0003\tYg/A\u0006tk\n$(/Y2u\u001f:,G\u0003\u0002B\t\u0005\u0003Ca!a(\u0012\u0001\u00049\u0015\u0001D:ue&tw\r\u0015:fM&DXC\u0001BD!\u0011\t\tN!#\n\t\u0005\u001d\u00171[\u0001\u000fGJ,\u0017\r^3OK^,e\u000e\u001e:z)\u0019\u0011yI!%\u0003\u0014B\u0019!\u0011C\u0002\t\r\u0005}5\u00041\u0001H\u0011\u0019\t)k\u0007a\u0001%\u0006YqO]5uK>\u0013'.Z2u)\u0011\tYB!'\t\u000f\tmE\u00041\u0001\u0003\u001e\u0006\u0019q.\u001e;\u0011\u0007u\u0014y*C\u0002\u0003\"z\u0014!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\u0006Q!/Z1e\u001f\nTWm\u0019;\u0015\t\u0005m!q\u0015\u0005\b\u0005Sk\u0002\u0019\u0001BV\u0003\tIg\u000eE\u0002~\u0005[K1Aa,\u007f\u0005Ey%M[3di&s\u0007/\u001e;TiJ,\u0017-\\\u0001\u0011EJ|7.\u001a8J]Z\f'/[1oiN,\"A!.\u0011\r\t]&Q\u0018BD\u001b\t\u0011ILC\u0002\u0003<b\n\u0011\"[7nkR\f'\r\\3\n\t\t}&\u0011\u0018\u0002\u000b\u0013:$W\r_3e'\u0016\f\u0018aC2iK\u000e\\')^2lKR$BA!2\u0003LB1!q\u0017Bd\u0005\u000fKAA!3\u0003:\n!A*[:u\u0011\u001d\u0011im\ba\u0001\u0003#\t\u0011![\u0001\u000bG\",7m[#oiJLH\u0003\u0002Bj\u0005/\u0004b!!&\u0003V\u0006m\u0016\u0002\u0002Be\u0003;CqA!4!\u0001\u0004\t\t\u0002K\u0004\u0001\u00057\f)K!9\u0011\u0007\u0001\u0013i.C\u0002\u0003`j\u0012\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u001f\u0003\r\u0001"
)
public class ParHashMap implements ParMap, ParHashTable, Serializable {
   private static final long serialVersionUID = 3L;
   private int _loadFactor;
   private HashEntry[] table;
   private int tableSize;
   private int threshold;
   private int[] sizemap;
   private int seedvalue;
   private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
   private volatile ParIterableLike.ScanNode$ ScanNode$module;
   private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;

   public static CanCombineFrom canBuildFrom() {
      return ParHashMap$.MODULE$.canBuildFrom();
   }

   public static void iters_$eq(final int x$1) {
      ParHashMap$.MODULE$.iters_$eq(x$1);
   }

   public static int iters() {
      return ParHashMap$.MODULE$.iters();
   }

   public static Factory toFactory() {
      return ParHashMap$.MODULE$.toFactory();
   }

   public boolean alwaysInitSizeMap() {
      return ParHashTable.alwaysInitSizeMap$(this);
   }

   public void initWithContents(final ParHashTable.Contents c) {
      WithContents.initWithContents$(this, c);
   }

   public ParHashTable.Contents hashTableContents() {
      return WithContents.hashTableContents$(this);
   }

   public final int size() {
      return HashTable.size$(this);
   }

   public int tableSizeSeed() {
      return HashTable.tableSizeSeed$(this);
   }

   public int initialSize() {
      return HashTable.initialSize$(this);
   }

   public void init(final ObjectInputStream in, final Function0 readEntry) {
      HashTable.init$(this, in, readEntry);
   }

   public void serializeTo(final ObjectOutputStream out, final Function1 writeEntry) {
      HashTable.serializeTo$(this, out, writeEntry);
   }

   public final HashEntry findEntry(final Object key) {
      return HashTable.findEntry$(this, key);
   }

   public final HashEntry findEntry0(final Object key, final int h) {
      return HashTable.findEntry0$(this, key, h);
   }

   public final void addEntry(final HashEntry e) {
      HashTable.addEntry$(this, e);
   }

   public final void addEntry0(final HashEntry e, final int h) {
      HashTable.addEntry0$(this, e, h);
   }

   public HashEntry findOrAddEntry(final Object key, final Object value) {
      return HashTable.findOrAddEntry$(this, key, value);
   }

   public final HashEntry removeEntry(final Object key) {
      return HashTable.removeEntry$(this, key);
   }

   public final HashEntry removeEntry0(final Object key, final int h) {
      return HashTable.removeEntry0$(this, key, h);
   }

   public Iterator entriesIterator() {
      return HashTable.entriesIterator$(this);
   }

   public void foreachEntry(final Function1 f) {
      HashTable.foreachEntry$(this, f);
   }

   public void clearTable() {
      HashTable.clearTable$(this);
   }

   public final void nnSizeMapAdd(final int h) {
      HashTable.nnSizeMapAdd$(this, h);
   }

   public final void nnSizeMapRemove(final int h) {
      HashTable.nnSizeMapRemove$(this, h);
   }

   public final void nnSizeMapReset(final int tableLength) {
      HashTable.nnSizeMapReset$(this, tableLength);
   }

   public final int totalSizeMapBuckets() {
      return HashTable.totalSizeMapBuckets$(this);
   }

   public final int calcSizeMapSize(final int tableLength) {
      return HashTable.calcSizeMapSize$(this, tableLength);
   }

   public void sizeMapInit(final int tableLength) {
      HashTable.sizeMapInit$(this, tableLength);
   }

   public final void sizeMapInitAndRebuild() {
      HashTable.sizeMapInitAndRebuild$(this);
   }

   public void printSizeMap() {
      HashTable.printSizeMap$(this);
   }

   public final void sizeMapDisable() {
      HashTable.sizeMapDisable$(this);
   }

   public final boolean isSizeMapDefined() {
      return HashTable.isSizeMapDefined$(this);
   }

   public boolean elemEquals(final Object key1, final Object key2) {
      return HashTable.elemEquals$(this, key1, key2);
   }

   public final int index(final int hcode) {
      return HashTable.index$(this, hcode);
   }

   public final int sizeMapBucketBitSize() {
      return HashUtils.sizeMapBucketBitSize$(this);
   }

   public final int sizeMapBucketSize() {
      return HashUtils.sizeMapBucketSize$(this);
   }

   public int elemHashCode(final Object key) {
      return HashUtils.elemHashCode$(this, key);
   }

   public final int improve(final int hcode, final int seed) {
      return HashUtils.improve$(this, hcode, seed);
   }

   public ParMap withDefault(final Function1 d) {
      return ParMap.withDefault$(this, d);
   }

   public ParMap withDefaultValue(final Object d) {
      return ParMap.withDefaultValue$(this, d);
   }

   public ParMap $plus(final Tuple2 kv) {
      return ParMapLike.$plus$(this, kv);
   }

   public ParMap $minus(final Object key) {
      return ParMapLike.$minus$(this, key);
   }

   public ParMap clone() {
      return ParMapLike.clone$(this);
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

   public scala.collection.parallel.ParMap updated(final Object key, final Object value) {
      return scala.collection.parallel.ParMapLike.updated$(this, key, value);
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

   public String debugInformation() {
      return ParIterableLike.debugInformation$(this);
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

   public int _loadFactor() {
      return this._loadFactor;
   }

   public void _loadFactor_$eq(final int x$1) {
      this._loadFactor = x$1;
   }

   public HashEntry[] table() {
      return this.table;
   }

   public void table_$eq(final HashEntry[] x$1) {
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

   public GenericParMapCompanion mapCompanion() {
      return ParHashMap$.MODULE$;
   }

   public ParHashMap empty() {
      return new ParHashMap();
   }

   public ParHashMapCombiner newCombiner() {
      return ParHashMapCombiner$.MODULE$.apply();
   }

   public HashMap seq() {
      return scala.collection.mutable.HashMap..MODULE$.from(this);
   }

   public ParHashMapIterator splitter() {
      return new ParHashMapIterator(1, this.table().length, this.size(), (DefaultEntry)this.table()[0]);
   }

   public int knownSize() {
      return this.tableSize();
   }

   public void clear() {
      this.clearTable();
   }

   public Option get(final Object key) {
      DefaultEntry e = (DefaultEntry)this.findEntry(key);
      return (Option)(e == null ? scala.None..MODULE$ : new Some(e.value()));
   }

   public Option put(final Object key, final Object value) {
      DefaultEntry e = (DefaultEntry)this.findOrAddEntry(key, value);
      if (e == null) {
         return scala.None..MODULE$;
      } else {
         Object v = e.value();
         e.value_$eq(value);
         return new Some(v);
      }
   }

   public void update(final Object key, final Object value) {
      this.put(key, value);
   }

   public Option remove(final Object key) {
      DefaultEntry e = (DefaultEntry)this.removeEntry(key);
      return (Option)(e != null ? new Some(e.value()) : scala.None..MODULE$);
   }

   public ParHashMap addOne(final Tuple2 kv) {
      DefaultEntry e = (DefaultEntry)this.findOrAddEntry(kv._1(), kv._2());
      if (e != null) {
         e.value_$eq(kv._2());
      }

      return this;
   }

   public ParHashMap subtractOne(final Object key) {
      this.removeEntry(key);
      return this;
   }

   public String stringPrefix() {
      return "ParHashMap";
   }

   public DefaultEntry createNewEntry(final Object key, final Object value) {
      return new DefaultEntry(key, value);
   }

   private void writeObject(final ObjectOutputStream out) {
      this.serializeTo(out, (entry) -> {
         $anonfun$writeObject$1(out, entry);
         return BoxedUnit.UNIT;
      });
   }

   private void readObject(final ObjectInputStream in) {
      this.init(in, () -> this.createNewEntry(in.readObject(), in.readObject()));
   }

   public IndexedSeq brokenInvariants() {
      IndexedSeq buckets = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.table().length / this.sizeMapBucketSize()).map((i) -> $anonfun$brokenInvariants$1(this, BoxesRunTime.unboxToInt(i)));
      IndexedSeq elems = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.table().length).map((i) -> $anonfun$brokenInvariants$2(this, BoxesRunTime.unboxToInt(i)));
      return (IndexedSeq)((IterableOps)buckets.flatMap((x) -> x)).$plus$plus((IterableOnce)elems.flatMap((x) -> x));
   }

   private List checkBucket(final int i) {
      int expected = this.sizemap()[i];
      int found = BoxesRunTime.unboxToInt(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(i * this.sizeMapBucketSize()), (i + 1) * this.sizeMapBucketSize()).foldLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(acc, c) -> acc + count$1(this.table()[c])));
      return (List)(found != expected ? new scala.collection.immutable..colon.colon((new StringBuilder(38)).append("Found ").append(found).append(" elements, while sizemap showed ").append(expected).toString(), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$);
   }

   private List checkEntry(final int i) {
      return this.check$1(this.table()[i], i);
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
   public static final void $anonfun$writeObject$1(final ObjectOutputStream out$1, final DefaultEntry entry) {
      out$1.writeObject(entry.key());
      out$1.writeObject(entry.value());
   }

   // $FF: synthetic method
   public static final List $anonfun$brokenInvariants$1(final ParHashMap $this, final int i) {
      return $this.checkBucket(i);
   }

   // $FF: synthetic method
   public static final List $anonfun$brokenInvariants$2(final ParHashMap $this, final int i) {
      return $this.checkEntry(i);
   }

   private static final int count$1(final HashEntry e) {
      return e == null ? 0 : 1 + count$1(e.next());
   }

   private final List check$1(final HashEntry e, final int i$1) {
      while(e != null) {
         if (this.index(this.elemHashCode(e.key())) != i$1) {
            String var4 = (new StringBuilder(27)).append("Element ").append(e.key()).append(" at ").append(i$1).append(" with ").append(this.elemHashCode(e.key())).append(" maps to ").append(this.index(this.elemHashCode(e.key()))).toString();
            return this.check$1(e.next(), i$1).$colon$colon(var4);
         }

         e = e.next();
      }

      return scala.collection.immutable.Nil..MODULE$;
   }

   public ParHashMap(final ParHashTable.Contents contents) {
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
      Growable.$init$(this);
      Shrinkable.$init$(this);
      Cloneable.$init$(this);
      ParMapLike.$init$(this);
      ParMap.$init$(this);
      HashUtils.$init$(this);
      HashTable.$init$(this);
      WithContents.$init$(this);
      ParHashTable.$init$(this);
      this.initWithContents(contents);
   }

   public ParHashMap() {
      this((ParHashTable.Contents)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ParHashMapIterator extends ParHashTable.EntryIterator {
      public Tuple2 entry2item(final DefaultEntry entry) {
         return new Tuple2(entry.key(), entry.value());
      }

      public ParHashMapIterator newIterator(final int idxFrom, final int idxUntil, final int totalSz, final DefaultEntry es) {
         return this.scala$collection$parallel$mutable$ParHashMap$ParHashMapIterator$$$outer().new ParHashMapIterator(idxFrom, idxUntil, totalSz, es);
      }

      // $FF: synthetic method
      public ParHashMap scala$collection$parallel$mutable$ParHashMap$ParHashMapIterator$$$outer() {
         return (ParHashMap)this.$outer;
      }

      public ParHashMapIterator(final int start, final int untilIdx, final int totalSize, final DefaultEntry e) {
         super(start, untilIdx, totalSize, e);
      }
   }

   public static final class DefaultEntry implements HashEntry, Serializable {
      private final Object key;
      private Object value;
      private DefaultEntry next;

      public DefaultEntry next() {
         return this.next;
      }

      public void next_$eq(final DefaultEntry x$1) {
         this.next = x$1;
      }

      public Object key() {
         return this.key;
      }

      public Object value() {
         return this.value;
      }

      public void value_$eq(final Object x$1) {
         this.value = x$1;
      }

      public String toString() {
         return (new StringBuilder(18)).append("DefaultEntry(").append(this.key()).append(" -> ").append(this.value()).append(")").toString();
      }

      public DefaultEntry(final Object key, final Object value) {
         this.key = key;
         this.value = value;
         super();
         HashEntry.$init$(this);
      }
   }
}
