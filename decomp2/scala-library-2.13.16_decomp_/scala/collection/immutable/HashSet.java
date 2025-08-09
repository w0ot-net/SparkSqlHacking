package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Hashing$;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.convert.impl.AnyChampStepper;
import scala.collection.convert.impl.AnyChampStepper$;
import scala.collection.convert.impl.DoubleChampStepper;
import scala.collection.convert.impl.DoubleChampStepper$;
import scala.collection.convert.impl.IntChampStepper;
import scala.collection.convert.impl.IntChampStepper$;
import scala.collection.convert.impl.LongChampStepper;
import scala.collection.convert.impl.LongChampStepper$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedHashSet;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\t=f\u0001B\u001a5\u0005mB\u0011\u0002\u0018\u0001\u0003\u0006\u0004%\t\u0001N/\t\u0011\u0005\u0004!\u0011!Q\u0001\nyCaA\u0019\u0001\u0005\u0002Q\u001a\u0007\"\u00022\u0001\t\u0003)\u0007B\u00024\u0001A\u0013%q\rC\u0003k\u0001\u0011\u00053\u000eC\u0003p\u0001\u0011\u0005\u0003\u000fC\u0003u\u0001\u0011\u0005\u0003\u000fC\u0003v\u0001\u0011\u0005c\u000fC\u0003{\u0001\u0011\u00051\u0010\u0003\u0004\u0000\u0001\u0011EAg\u001f\u0005\b\u0003\u0003\u0001A\u0011IA\u0002\u0011\u001d\tI\u0005\u0001C\u0001\u0003\u0017Bq!!\u0015\u0001\t\u0003\t\u0019\u0006C\u0004\u0002X\u0001!\t!!\u0017\t\u000f\u0005u\u0003\u0001\"\u0011\u0002`!9\u00111\u000e\u0001\u0005B\u00055\u0004bBA8\u0001\u0011\u0005\u0013Q\u000e\u0005\b\u0003c\u0002A\u0011IA:\u0011\u001d\t)\b\u0001C!\u0003gBq!a\u001e\u0001\t\u0003\nI\b\u0003\u0005\u0002\u0014\u0002!\tANAK\u0011!\t9\u000b\u0001C\u0001m\u0005%\u0006bBAY\u0001\u0011E\u00111\u0017\u0005\b\u0003c\u0003A\u0011IA_\u0011\u001d\t)\r\u0001C!\u0003\u000fD\u0001\"a3\u0001A\u0013E\u0013Q\u001a\u0005\b\u0003?\u0004A\u0011IAq\u0011\u001d\t\u0019\u000f\u0001C!\u0003KD\u0001\"!;\u0001A\u0013%\u00111\u001e\u0005\b\u0003_\u0004A\u0011IAy\u0011\u001d\t)\u0010\u0001C!\u0003oDqA!\u0002\u0001\t\u0003\u00129\u0001\u0003\u0005\u0003\f\u0001!\tF\u000eB\u0007\u0011\u001d\u00119\u0002\u0001C!\u00053AqA!\b\u0001\t\u0003\u0012y\u0002C\u0004\u0003&\u0001!\tEa\n\t\u000f\t-\u0002\u0001\"\u0011\u0003.!9!\u0011\u0007\u0001\u0005B\tM\u0002b\u0002B\u001c\u0001\u0011\u0005#\u0011\b\u0005\b\u0005{\u0001A\u0011\tB \u000f\u001d\u0011\u0019\u0005\u000eE\u0001\u0005\u000b2aa\r\u001b\t\u0002\t\u001d\u0003B\u00022,\t\u0003\u0011y\u0005C\u0005\u0003R-\u0012\r\u0011\"\u0004\u0003T!A!qK\u0016!\u0002\u001b\u0011)\u0006C\u0004\u0003b-\"\tAa\u0019\t\u000f\t54\u0006\"\u0001\u0003p!9!qP\u0016\u0005\u0002\t\u0005\u0005\"\u0003BLW\u0005\u0005I\u0011\u0002BM\u0005\u001dA\u0015m\u001d5TKRT!!\u000e\u001c\u0002\u0013%lW.\u001e;bE2,'BA\u001c9\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002s\u0005)1oY1mC\u000e\u0001QC\u0001\u001fD'\u0015\u0001Q(\u0014*W!\rqt(Q\u0007\u0002i%\u0011\u0001\t\u000e\u0002\f\u0003\n\u001cHO]1diN+G\u000f\u0005\u0002C\u00072\u0001A!\u0002#\u0001\u0005\u0004)%!A!\u0012\u0005\u0019S\u0005CA$I\u001b\u0005A\u0014BA%9\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aR&\n\u00051C$aA!osB)aHT!Q#&\u0011q\n\u000e\u0002\u0016'R\u0014\u0018n\u0019;PaRLW.\u001b>fIN+Go\u00149t!\tq\u0004\u0001E\u0002?\u0001\u0005\u0003Ba\u0015+B!6\ta'\u0003\u0002Vm\t9\u0012\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0003/jk\u0011\u0001\u0017\u0006\u00033Z\nqaZ3oKJL7-\u0003\u0002\\1\n\u0019B)\u001a4bk2$8+\u001a:jC2L'0\u00192mK\u0006A!o\\8u\u001d>$W-F\u0001_!\rqt,Q\u0005\u0003AR\u0012ACQ5u[\u0006\u0004\u0018J\u001c3fq\u0016$7+\u001a;O_\u0012,\u0017!\u0003:p_Rtu\u000eZ3!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011\u000b\u001a\u0005\u00069\u000e\u0001\rA\u0018\u000b\u0002#\u0006\u0001b.Z<ICND7+\u001a;PeRC\u0017n\u001d\u000b\u0003#\"DQ![\u0003A\u0002y\u000b1B\\3x%>|GOT8eK\u0006y\u0011\u000e^3sC\ndWMR1di>\u0014\u00180F\u0001m!\r\u0019V\u000eU\u0005\u0003]Z\u0012q\"\u0013;fe\u0006\u0014G.\u001a$bGR|'/_\u0001\nW:|wO\\*ju\u0016,\u0012!\u001d\t\u0003\u000fJL!a\u001d\u001d\u0003\u0007%sG/\u0001\u0003tSj,\u0017aB5t\u000b6\u0004H/_\u000b\u0002oB\u0011q\t_\u0005\u0003sb\u0012qAQ8pY\u0016\fg.\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u0005a\bcA*~\u0003&\u0011aP\u000e\u0002\t\u0013R,'/\u0019;pe\u0006y!/\u001a<feN,\u0017\n^3sCR|'/A\u0004ti\u0016\u0004\b/\u001a:\u0016\t\u0005\u0015\u0011q\u0002\u000b\u0005\u0003\u000f\tyD\u0005\u0004\u0002\n\u00055\u00111\u0005\u0004\u0007\u0003\u0017\u0001\u0001!a\u0002\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0007\t\u000by\u0001B\u0004\u0002\u00121\u0011\r!a\u0005\u0003\u0003M\u000b2ARA\u000ba\u0011\t9\"a\b\u0011\u000bM\u000bI\"!\b\n\u0007\u0005maGA\u0004Ti\u0016\u0004\b/\u001a:\u0011\u0007\t\u000by\u0002B\u0006\u0002\"\u0005=\u0011\u0011!A\u0001\u0006\u0003)%aA0%cA!\u0011QEA\u001d\u001d\u0011\t9#!\u000e\u000f\t\u0005%\u00121\u0007\b\u0005\u0003W\t\t$\u0004\u0002\u0002.)\u0019\u0011q\u0006\u001e\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0014BA\u001c9\u0013\r\t9DN\u0001\b'R,\u0007\u000f]3s\u0013\u0011\tY$!\u0010\u0003\u001d\u00153g-[2jK:$8\u000b\u001d7ji*\u0019\u0011q\u0007\u001c\t\u000f\u0005\u0005C\u0002q\u0001\u0002D\u0005)1\u000f[1qKB11+!\u0012B\u0003\u001bI1!a\u00127\u00051\u0019F/\u001a9qKJ\u001c\u0006.\u00199f\u0003!\u0019wN\u001c;bS:\u001cHcA<\u0002N!1\u0011qJ\u0007A\u0002\u0005\u000bq!\u001a7f[\u0016tG/\u0001\u0003j]\u000edGcA)\u0002V!1\u0011q\n\bA\u0002\u0005\u000bA!\u001a=dYR\u0019\u0011+a\u0017\t\r\u0005=s\u00021\u0001B\u0003\u0019\u0019wN\\2biR\u0019\u0011+!\u0019\t\u000f\u0005\r\u0004\u00031\u0001\u0002f\u0005!A\u000f[1u!\u0011\u0019\u0016qM!\n\u0007\u0005%dG\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-\u0001\u0003uC&dW#A)\u0002\t%t\u0017\u000e^\u0001\u0005Q\u0016\fG-F\u0001B\u0003\u0011a\u0017m\u001d;\u0002\u000f\u0019|'/Z1dQV!\u00111PAH)\u0011\ti(a!\u0011\u0007\u001d\u000by(C\u0002\u0002\u0002b\u0012A!\u00168ji\"9\u0011QQ\u000bA\u0002\u0005\u001d\u0015!\u00014\u0011\r\u001d\u000bI)QAG\u0013\r\tY\t\u000f\u0002\n\rVt7\r^5p]F\u00022AQAH\t\u0019\t\t*\u0006b\u0001\u000b\n\tQ+A\bg_J,\u0017m\u00195XSRD\u0007*Y:i)\u0011\ti(a&\t\u000f\u0005\u0015e\u00031\u0001\u0002\u001aB9q)a'Bc\u0006u\u0014bAAOq\tIa)\u001e8di&|gN\r\u0015\u0004-\u0005\u0005\u0006cA$\u0002$&\u0019\u0011Q\u0015\u001d\u0003\r%tG.\u001b8f\u0003Q1wN]3bG\"<\u0016\u000e\u001e5ICNDw\u000b[5mKR!\u0011QPAV\u0011\u001d\t)i\u0006a\u0001\u0003[\u0003baRAN\u0003F<\bfA\f\u0002\"\u0006A1/\u001e2tKR|e\rF\u0002x\u0003kCq!a\u0019\u0019\u0001\u0004\t9\f\u0005\u0003?\u0003s\u000b\u0015bAA^i\t\u00191+\u001a;\u0015\u0007]\fy\fC\u0004\u0002de\u0001\r!!1\u0011\tM\u000b\u0019-Q\u0005\u0004\u0003w3\u0014AB3rk\u0006d7\u000fF\u0002x\u0003\u0013Da!a\u0019\u001b\u0001\u0004Q\u0015!C2mCN\u001ch*Y7f+\t\ty\r\u0005\u0003\u0002R\u0006mWBAAj\u0015\u0011\t).a6\u0002\t1\fgn\u001a\u0006\u0003\u00033\fAA[1wC&!\u0011Q\\Aj\u0005\u0019\u0019FO]5oO\u0006A\u0001.Y:i\u0007>$W\rF\u0001r\u0003\u0011!\u0017N\u001a4\u0015\u0007E\u000b9\u000fC\u0004\u0002du\u0001\r!!1\u0002=I,Wn\u001c<fI\u0006cGnV5uQNC\u0017\r\u001c7po6+H/\u0019;j_:\u001cHcA)\u0002n\"9\u00111\r\u0010A\u0002\u0005\u0015\u0014A\u0003:f[>4X\rZ!mYR\u0019\u0011+a=\t\u000f\u0005\rt\u00041\u0001\u0002f\u0005I\u0001/\u0019:uSRLwN\u001c\u000b\u0005\u0003s\fy\u0010E\u0003H\u0003w\f\u0016+C\u0002\u0002~b\u0012a\u0001V;qY\u0016\u0014\u0004b\u0002B\u0001A\u0001\u0007!1A\u0001\u0002aB)q)!#Bo\u0006!1\u000f]1o)\u0011\tIP!\u0003\t\u000f\t\u0005\u0011\u00051\u0001\u0003\u0004\u0005Qa-\u001b7uKJLU\u000e\u001d7\u0015\u000bE\u0013yAa\u0005\t\u000f\tE!\u00051\u0001\u0003\u0004\u0005!\u0001O]3e\u0011\u0019\u0011)B\ta\u0001o\u0006I\u0011n\u001d$mSB\u0004X\rZ\u0001\nS:$XM]:fGR$2!\u0015B\u000e\u0011\u001d\t\u0019g\ta\u0001\u0003\u0003\fA\u0001^1lKR\u0019\u0011K!\t\t\r\t\rB\u00051\u0001r\u0003\u0005q\u0017!\u0003;bW\u0016\u0014\u0016n\u001a5u)\r\t&\u0011\u0006\u0005\u0007\u0005G)\u0003\u0019A9\u0002\u0013Q\f7.Z,iS2,GcA)\u00030!9!\u0011\u0001\u0014A\u0002\t\r\u0011\u0001\u00023s_B$2!\u0015B\u001b\u0011\u0019\u0011\u0019c\na\u0001c\u0006IAM]8q%&<\u0007\u000e\u001e\u000b\u0004#\nm\u0002B\u0002B\u0012Q\u0001\u0007\u0011/A\u0005ee>\u0004x\u000b[5mKR\u0019\u0011K!\u0011\t\u000f\t\u0005\u0011\u00061\u0001\u0003\u0004\u00059\u0001*Y:i'\u0016$\bC\u0001 ,'\u0011Y#\u0011\n7\u0011\u0007\u001d\u0013Y%C\u0002\u0003Na\u0012a!\u00118z%\u00164GC\u0001B#\u0003!)U\u000e\u001d;z'\u0016$XC\u0001B+!\rq\u0004AR\u0001\n\u000b6\u0004H/_*fi\u0002B3A\fB.!\r9%QL\u0005\u0004\u0005?B$!\u0003;sC:\u001c\u0018.\u001a8u\u0003\u0015)W\u000e\u001d;z+\u0011\u0011)Ga\u001b\u0016\u0005\t\u001d\u0004\u0003\u0002 \u0001\u0005S\u00022A\u0011B6\t\u0015!uF1\u0001F\u0003\u00111'o\\7\u0016\t\tE$q\u000f\u000b\u0005\u0005g\u0012I\b\u0005\u0003?\u0001\tU\u0004c\u0001\"\u0003x\u0011)A\t\rb\u0001\u000b\"9!1\u0010\u0019A\u0002\tu\u0014AB:pkJ\u001cW\rE\u0003T\u0003O\u0012)(\u0001\u0006oK^\u0014U/\u001b7eKJ,BAa!\u0003\u0014V\u0011!Q\u0011\t\t\u0005\u000f\u0013iI!%\u0003\u00166\u0011!\u0011\u0012\u0006\u0004\u0005\u00173\u0014aB7vi\u0006\u0014G.Z\u0005\u0005\u0005\u001f\u0013IIA\bSKV\u001c\u0018M\u00197f\u0005VLG\u000eZ3s!\r\u0011%1\u0013\u0003\u0006\tF\u0012\r!\u0012\t\u0005}\u0001\u0011\t*\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u001cB!\u0011\u0011\u001bBO\u0013\u0011\u0011y*a5\u0003\r=\u0013'.Z2uQ\u001dY#1\u0015BU\u0005W\u00032a\u0012BS\u0013\r\u00119\u000b\u000f\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012a\u0001\u0015\bU\t\r&\u0011\u0016BV\u0001"
)
public final class HashSet extends AbstractSet implements StrictOptimizedSetOps, DefaultSerializable {
   private final BitmapIndexedSetNode rootNode;

   public static ReusableBuilder newBuilder() {
      HashSet$ var10000 = HashSet$.MODULE$;
      return new HashSetBuilder();
   }

   public static HashSet from(final IterableOnce source) {
      return HashSet$.MODULE$.from(source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$7$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$5$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$3$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$1$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n, final Function1 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Tabulate(n, f);
      return tabulate_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$4;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$3;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$2;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$1;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Fill(n, elem);
      return fill_this.from(from_source);
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(HashSet$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(HashSet$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      HashSet$ unfold_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      HashSet$ iterate_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public BitmapIndexedSetNode rootNode() {
      return this.rootNode;
   }

   private HashSet newHashSetOrThis(final BitmapIndexedSetNode newRootNode) {
      return this.rootNode() == newRootNode ? this : new HashSet(newRootNode);
   }

   public IterableFactory iterableFactory() {
      return HashSet$.MODULE$;
   }

   public int knownSize() {
      return this.rootNode().size();
   }

   public int size() {
      return this.rootNode().size();
   }

   public boolean isEmpty() {
      return this.rootNode().size() == 0;
   }

   public Iterator iterator() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new SetIterator(this.rootNode());
      }
   }

   public Iterator reverseIterator() {
      return new SetReverseIterator(this.rootNode());
   }

   public Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntChampStepper$ var25 = IntChampStepper$.MODULE$;
         int var26 = this.size();
         BitmapIndexedSetNode var31 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToInteger($anonfun$stepper$1(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedSetNode from_root = var31;
         int from_maxSize = var26;
         IntChampStepper from_ans = new IntChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongChampStepper$ var23 = LongChampStepper$.MODULE$;
         int var24 = this.size();
         BitmapIndexedSetNode var30 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToLong($anonfun$stepper$2(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedSetNode from_root = var30;
         int from_maxSize = var24;
         LongChampStepper from_ans = new LongChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleChampStepper$ var10000 = DoubleChampStepper$.MODULE$;
         int var22 = this.size();
         BitmapIndexedSetNode var29 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToDouble($anonfun$stepper$3(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedSetNode from_root = var29;
         int from_maxSize = var22;
         DoubleChampStepper from_ans = new DoubleChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else {
         AnyChampStepper$ var10001 = AnyChampStepper$.MODULE$;
         int var27 = this.size();
         BitmapIndexedSetNode var10002 = this.rootNode();
         Function2 from_extract = (node, i) -> $anonfun$stepper$4(node, BoxesRunTime.unboxToInt(i));
         BitmapIndexedSetNode from_root = var10002;
         int from_maxSize = var27;
         AnyChampStepper from_ans = new AnyChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         AnyChampStepper var28 = from_ans;
         from_root = null;
         from_extract = null;
         Object var21 = null;
         return shape.parUnbox(var28);
      }
   }

   public boolean contains(final Object element) {
      int elementUnimprovedHash = Statics.anyHash(element);
      int elementHash = Hashing$.MODULE$.improve(elementUnimprovedHash);
      return this.rootNode().contains(element, elementUnimprovedHash, elementHash, 0);
   }

   public HashSet incl(final Object element) {
      int elementUnimprovedHash = Statics.anyHash(element);
      int elementHash = Hashing$.MODULE$.improve(elementUnimprovedHash);
      BitmapIndexedSetNode newRootNode = this.rootNode().updated(element, elementUnimprovedHash, elementHash, 0);
      return this.newHashSetOrThis(newRootNode);
   }

   public HashSet excl(final Object element) {
      int elementUnimprovedHash = Statics.anyHash(element);
      int elementHash = Hashing$.MODULE$.improve(elementUnimprovedHash);
      BitmapIndexedSetNode newRootNode = this.rootNode().removed(element, elementUnimprovedHash, elementHash, 0);
      return this.newHashSetOrThis(newRootNode);
   }

   public HashSet concat(final IterableOnce that) {
      if (that instanceof HashSet) {
         HashSet var2 = (HashSet)that;
         if (this.isEmpty()) {
            return var2;
         } else {
            BitmapIndexedSetNode newNode = this.rootNode().concat(var2.rootNode(), 0);
            return newNode == var2.rootNode() ? var2 : this.newHashSetOrThis(newNode);
         }
      } else if (that instanceof scala.collection.mutable.HashSet) {
         scala.collection.mutable.HashSet var4 = (scala.collection.mutable.HashSet)that;
         if (var4 == null) {
            throw null;
         } else {
            Iterator iter = var4.new HashSetIterator() {
               public scala.collection.mutable.HashSet.Node extract(final scala.collection.mutable.HashSet.Node nd) {
                  return nd;
               }
            };
            BitmapIndexedSetNode current = this.rootNode();

            while(iter.hasNext()) {
               scala.collection.mutable.HashSet.Node next = (scala.collection.mutable.HashSet.Node)iter.next();
               int unimproveHash_improvedHash = next.hash();
               int originalHash = unimproveHash_improvedHash ^ unimproveHash_improvedHash >>> 16;
               int improved = Hashing$.MODULE$.improve(originalHash);
               current = current.updated(next.key(), originalHash, improved, 0);
               if (current != this.rootNode()) {
                  Node$ var44 = Node$.MODULE$;
                  var44 = Node$.MODULE$;
                  int maskFrom_shift = 0;
                  int bitposFrom_mask = improved >>> maskFrom_shift & 31;

                  scala.collection.mutable.HashSet.Node next;
                  int originalHash;
                  int improved;
                  for(int shallowlyMutableNodeMap = 1 << bitposFrom_mask; iter.hasNext(); shallowlyMutableNodeMap = current.updateWithShallowMutations(next.key(), originalHash, improved, 0, shallowlyMutableNodeMap)) {
                     next = (scala.collection.mutable.HashSet.Node)iter.next();
                     int unimproveHash_improvedHash = next.hash();
                     originalHash = unimproveHash_improvedHash ^ unimproveHash_improvedHash >>> 16;
                     improved = Hashing$.MODULE$.improve(originalHash);
                  }

                  return new HashSet(current);
               }
            }

            return this;
         }
      } else if (that instanceof LinkedHashSet) {
         LinkedHashSet var14 = (LinkedHashSet)that;
         Iterator iter = var14.entryIterator();
         BitmapIndexedSetNode current = this.rootNode();

         while(iter.hasNext()) {
            LinkedHashSet.Entry next = (LinkedHashSet.Entry)iter.next();
            int originalHash = var14.unimproveHash(next.hash());
            int improved = Hashing$.MODULE$.improve(originalHash);
            current = current.updated(next.key(), originalHash, improved, 0);
            if (current != this.rootNode()) {
               Node$ var42 = Node$.MODULE$;
               var42 = Node$.MODULE$;
               int maskFrom_shift = 0;
               int bitposFrom_mask = improved >>> maskFrom_shift & 31;

               LinkedHashSet.Entry next;
               int originalHash;
               int improved;
               for(int shallowlyMutableNodeMap = 1 << bitposFrom_mask; iter.hasNext(); shallowlyMutableNodeMap = current.updateWithShallowMutations(next.key(), originalHash, improved, 0, shallowlyMutableNodeMap)) {
                  next = (LinkedHashSet.Entry)iter.next();
                  originalHash = var14.unimproveHash(next.hash());
                  improved = Hashing$.MODULE$.improve(originalHash);
               }

               return new HashSet(current);
            }
         }

         return this;
      } else {
         Iterator iter = that.iterator();
         BitmapIndexedSetNode current = this.rootNode();

         while(iter.hasNext()) {
            Object element = iter.next();
            int originalHash = Statics.anyHash(element);
            int improved = Hashing$.MODULE$.improve(originalHash);
            current = current.updated(element, originalHash, improved, 0);
            if (current != this.rootNode()) {
               Node$ var10000 = Node$.MODULE$;
               var10000 = Node$.MODULE$;
               int maskFrom_shift = 0;
               int bitposFrom_mask = improved >>> maskFrom_shift & 31;

               Object element;
               int originalHash;
               int improved;
               for(int shallowlyMutableNodeMap = 1 << bitposFrom_mask; iter.hasNext(); shallowlyMutableNodeMap = current.updateWithShallowMutations(element, originalHash, improved, 0, shallowlyMutableNodeMap)) {
                  element = iter.next();
                  originalHash = Statics.anyHash(element);
                  improved = Hashing$.MODULE$.improve(originalHash);
               }

               return new HashSet(current);
            }
         }

         return this;
      }
   }

   public HashSet tail() {
      Object $minus_elem = this.head();
      return this.excl($minus_elem);
   }

   public HashSet init() {
      Object $minus_elem = this.last();
      return this.excl($minus_elem);
   }

   public Object head() {
      return this.iterator().next();
   }

   public Object last() {
      return this.reverseIterator().next();
   }

   public void foreach(final Function1 f) {
      BitmapIndexedSetNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedSetNode foreach_this = var10000;
         int foreach_thisPayloadArity = foreach_this.payloadArity();

         for(int foreach_i = 0; foreach_i < foreach_thisPayloadArity; ++foreach_i) {
            f.apply(foreach_this.content()[foreach_i]);
         }

         int foreach_thisNodeArity = foreach_this.nodeArity();

         for(int foreach_j = 0; foreach_j < foreach_thisNodeArity; ++foreach_j) {
            foreach_this.getNode(foreach_j).foreach(f);
         }

      }
   }

   public void foreachWithHash(final Function2 f) {
      BitmapIndexedSetNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedSetNode foreachWithHash_this = var10000;
         int foreachWithHash_iN = foreachWithHash_this.payloadArity();

         for(int foreachWithHash_i = 0; foreachWithHash_i < foreachWithHash_iN; ++foreachWithHash_i) {
            f.apply(foreachWithHash_this.content()[foreachWithHash_i], foreachWithHash_this.originalHashes()[foreachWithHash_i]);
         }

         int foreachWithHash_jN = foreachWithHash_this.nodeArity();

         for(int foreachWithHash_j = 0; foreachWithHash_j < foreachWithHash_jN; ++foreachWithHash_j) {
            foreachWithHash_this.getNode(foreachWithHash_j).foreachWithHash(f);
         }

      }
   }

   public void foreachWithHashWhile(final Function2 f) {
      BitmapIndexedSetNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedSetNode foreachWithHashWhile_this = var10000;
         int foreachWithHashWhile_thisPayloadArity = foreachWithHashWhile_this.payloadArity();
         boolean foreachWithHashWhile_pass = true;

         for(int foreachWithHashWhile_i = 0; foreachWithHashWhile_i < foreachWithHashWhile_thisPayloadArity && foreachWithHashWhile_pass; ++foreachWithHashWhile_i) {
            foreachWithHashWhile_pass = foreachWithHashWhile_pass && BoxesRunTime.unboxToBoolean(f.apply(foreachWithHashWhile_this.content()[foreachWithHashWhile_i], foreachWithHashWhile_this.originalHashes()[foreachWithHashWhile_i]));
         }

         int foreachWithHashWhile_thisNodeArity = foreachWithHashWhile_this.nodeArity();

         for(int foreachWithHashWhile_j = 0; foreachWithHashWhile_j < foreachWithHashWhile_thisNodeArity && foreachWithHashWhile_pass; ++foreachWithHashWhile_j) {
            foreachWithHashWhile_pass = foreachWithHashWhile_pass && foreachWithHashWhile_this.getNode(foreachWithHashWhile_j).foreachWithHashWhile(f);
         }

      }
   }

   public boolean subsetOf(final Set that) {
      return this.subsetOf((scala.collection.Set)that);
   }

   public boolean subsetOf(final scala.collection.Set that) {
      if (!this.isEmpty()) {
         if (!that.isEmpty()) {
            boolean var10000;
            if (that instanceof HashSet) {
               HashSet var2 = (HashSet)that;
               var10000 = this.rootNode().subsetOf(var2.rootNode(), 0);
            } else {
               var10000 = this.forall(that);
            }

            if (var10000) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public boolean equals(final Object that) {
      if (!(that instanceof HashSet)) {
         return scala.collection.Set.equals$(this, that);
      } else {
         HashSet var2 = (HashSet)that;
         if (this != var2) {
            BitmapIndexedSetNode var10000 = this.rootNode();
            BitmapIndexedSetNode var3 = var2.rootNode();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         } else {
            return true;
         }
      }
   }

   public String className() {
      return "HashSet";
   }

   public int hashCode() {
      SetHashIterator it = new SetHashIterator(this.rootNode());
      return MurmurHash3$.MODULE$.unorderedHash(it, MurmurHash3$.MODULE$.setSeed());
   }

   public HashSet diff(final scala.collection.Set that) {
      if (this.isEmpty()) {
         return this;
      } else if (that instanceof HashSet) {
         HashSet var2 = (HashSet)that;
         if (var2.isEmpty()) {
            return this;
         } else {
            return this.rootNode().diff(var2.rootNode(), 0).size() == 0 ? HashSet$.MODULE$.empty() : this.newHashSetOrThis(this.rootNode().diff(var2.rootNode(), 0));
         }
      } else if (that instanceof scala.collection.mutable.HashSet) {
         scala.collection.mutable.HashSet var3 = (scala.collection.mutable.HashSet)that;
         if (var3 == null) {
            throw null;
         } else {
            Iterator iter = var3.new HashSetIterator() {
               public scala.collection.mutable.HashSet.Node extract(final scala.collection.mutable.HashSet.Node nd) {
                  return nd;
               }
            };
            BitmapIndexedSetNode curr = this.rootNode();

            while(iter.hasNext()) {
               scala.collection.mutable.HashSet.Node next = (scala.collection.mutable.HashSet.Node)iter.next();
               int unimproveHash_improvedHash = next.hash();
               int originalHash = unimproveHash_improvedHash ^ unimproveHash_improvedHash >>> 16;
               int improved = Hashing$.MODULE$.improve(originalHash);
               curr = curr.removed(next.key(), originalHash, improved, 0);
               if (curr != this.rootNode()) {
                  if (curr.size() == 0) {
                     return HashSet$.MODULE$.empty();
                  }

                  while(iter.hasNext()) {
                     scala.collection.mutable.HashSet.Node next = (scala.collection.mutable.HashSet.Node)iter.next();
                     int unimproveHash_improvedHash = next.hash();
                     int originalHash = unimproveHash_improvedHash ^ unimproveHash_improvedHash >>> 16;
                     int improved = Hashing$.MODULE$.improve(originalHash);
                     curr.removeWithShallowMutations(next.key(), originalHash, improved);
                     if (curr.size() == 0) {
                        return HashSet$.MODULE$.empty();
                     }
                  }

                  return new HashSet(curr);
               }
            }

            return this;
         }
      } else {
         int thatKnownSize = that.knownSize();
         if (thatKnownSize == 0) {
            return this;
         } else if (thatKnownSize <= this.size()) {
            return this.removedAllWithShallowMutations(that);
         } else {
            Function1 filterNot_pred = (elem) -> BoxesRunTime.boxToBoolean($anonfun$diff$1(that, elem));
            boolean filterImpl_isFlipped = true;
            BitmapIndexedSetNode filterImpl_newRootNode = this.rootNode().filterImpl(filterNot_pred, filterImpl_isFlipped);
            if (filterImpl_newRootNode == this.rootNode()) {
               return this;
            } else {
               return filterImpl_newRootNode.size() == 0 ? HashSet$.MODULE$.empty() : new HashSet(filterImpl_newRootNode);
            }
         }
      }
   }

   private HashSet removedAllWithShallowMutations(final IterableOnce that) {
      Iterator iter = that.iterator();
      BitmapIndexedSetNode curr = this.rootNode();

      while(iter.hasNext()) {
         Object next = iter.next();
         int originalHash = Statics.anyHash(next);
         int improved = Hashing$.MODULE$.improve(originalHash);
         curr = curr.removed(next, originalHash, improved, 0);
         if (curr != this.rootNode()) {
            if (curr.size() == 0) {
               return HashSet$.MODULE$.empty();
            }

            while(iter.hasNext()) {
               Object next = iter.next();
               int originalHash = Statics.anyHash(next);
               int improved = Hashing$.MODULE$.improve(originalHash);
               curr.removeWithShallowMutations(next, originalHash, improved);
               if (curr.size() == 0) {
                  return HashSet$.MODULE$.empty();
               }
            }

            return new HashSet(curr);
         }
      }

      return this;
   }

   public HashSet removedAll(final IterableOnce that) {
      if (that instanceof scala.collection.Set) {
         scala.collection.Set var2 = (scala.collection.Set)that;
         return this.diff(var2);
      } else {
         if (that instanceof Range) {
            Range var3 = (Range)that;
            if (var3.length() > this.size()) {
               Function1 filter_pred = (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$removedAll$1(var3, x0$1));
               boolean filterImpl_isFlipped = false;
               BitmapIndexedSetNode filterImpl_newRootNode = this.rootNode().filterImpl(filter_pred, filterImpl_isFlipped);
               if (filterImpl_newRootNode == this.rootNode()) {
                  return this;
               }

               if (filterImpl_newRootNode.size() == 0) {
                  return HashSet$.MODULE$.empty();
               }

               return new HashSet(filterImpl_newRootNode);
            }
         }

         return this.removedAllWithShallowMutations(that);
      }
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public HashSet filterImpl(final Function1 pred, final boolean isFlipped) {
      BitmapIndexedSetNode newRootNode = this.rootNode().filterImpl(pred, isFlipped);
      if (newRootNode == this.rootNode()) {
         return this;
      } else {
         return newRootNode.size() == 0 ? HashSet$.MODULE$.empty() : new HashSet(newRootNode);
      }
   }

   public HashSet intersect(final scala.collection.Set that) {
      return (HashSet)scala.collection.SetOps.intersect$(this, that);
   }

   public HashSet take(final int n) {
      return (HashSet)IterableOps.take$(this, n);
   }

   public HashSet takeRight(final int n) {
      return (HashSet)StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public HashSet takeWhile(final Function1 p) {
      return (HashSet)IterableOps.takeWhile$(this, p);
   }

   public HashSet drop(final int n) {
      return (HashSet)IterableOps.drop$(this, n);
   }

   public HashSet dropRight(final int n) {
      return (HashSet)StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public HashSet dropWhile(final Function1 p) {
      return (HashSet)IterableOps.dropWhile$(this, p);
   }

   // $FF: synthetic method
   public static final int $anonfun$stepper$1(final SetNode node, final int i) {
      return BoxesRunTime.unboxToInt(node.getPayload(i));
   }

   // $FF: synthetic method
   public static final long $anonfun$stepper$2(final SetNode node, final int i) {
      return BoxesRunTime.unboxToLong(node.getPayload(i));
   }

   // $FF: synthetic method
   public static final double $anonfun$stepper$3(final SetNode node, final int i) {
      return BoxesRunTime.unboxToDouble(node.getPayload(i));
   }

   // $FF: synthetic method
   public static final Object $anonfun$stepper$4(final SetNode node, final int i) {
      return node.getPayload(i);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$diff$1(final scala.collection.Set x1$1, final Object elem) {
      return x1$1.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removedAll$1(final Range x3$1, final Object x0$1) {
      if (x0$1 instanceof Integer) {
         int var2 = BoxesRunTime.unboxToInt(x0$1);
         return !x3$1.contains(var2);
      } else {
         return true;
      }
   }

   public HashSet(final BitmapIndexedSetNode rootNode) {
      this.rootNode = rootNode;
      Statics.releaseFence();
   }

   public HashSet() {
      this(SetNode$.MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
