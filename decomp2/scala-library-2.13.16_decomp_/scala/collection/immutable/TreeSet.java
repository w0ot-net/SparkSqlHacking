package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SortedOps;
import scala.collection.SortedSetFactoryDefaults;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.convert.impl.AnyBinaryTreeStepper;
import scala.collection.convert.impl.AnyBinaryTreeStepper$;
import scala.collection.convert.impl.BinaryTreeStepper$;
import scala.collection.convert.impl.DoubleBinaryTreeStepper;
import scala.collection.convert.impl.DoubleBinaryTreeStepper$;
import scala.collection.convert.impl.IntBinaryTreeStepper;
import scala.collection.convert.impl.IntBinaryTreeStepper$;
import scala.collection.convert.impl.LongBinaryTreeStepper;
import scala.collection.convert.impl.LongBinaryTreeStepper$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\ruc\u0001B\u001f?\u0005\u0015C\u0011b\u001c\u0001\u0003\u0006\u0004%\tA\u00109\t\u0011a\u0004!\u0011!Q\u0001\nED\u0001\"\u001f\u0001\u0003\u0006\u0004%\u0019A\u001f\u0005\n\u0003\u000b\u0001!\u0011!Q\u0001\nmD\u0001\"a\u0002\u0001\t\u0003q\u0014\u0011\u0002\u0005\b\u0003\u000f\u0001A\u0011AA\t\u0011\u001d\t9\u0002\u0001C!\u00033A\u0001\"!;\u0001A\u0013%\u00111\u001e\u0005\b\u0003c\u0004A\u0011IAz\u0011\u001d\tY\u0010\u0001C!\u0003{DqA!\u0002\u0001\t\u0003\u00129\u0001C\u0004\u0003\n\u0001!\tEa\u0002\t\u000f\t-\u0001\u0001\"\u0011\u0003\u000e!9!q\u0002\u0001\u0005B\t5\u0001b\u0002B\t\u0001\u0011\u0005#1\u0003\u0005\b\u0005K\u0001A\u0011\tB\u0014\u0011\u001d\u0011\u0019\u0004\u0001C!\u0005kAqAa\u000f\u0001\t\u0003\u0012i\u0004C\u0004\u0003B\u0001!\tEa\u0011\t\u000f\t-\u0003\u0001\"\u0011\u0003N!9!\u0011\u000b\u0001\u0005B\tM\u0003\u0002\u0003B,\u0001\u0001&IA!\u0017\t\u000f\t\u0015\u0004\u0001\"\u0011\u0003h!9!1\u000e\u0001\u0005B\t5\u0004b\u0002B9\u0001\u0011\u0005#1\u000f\u0005\b\u0005{\u0002A\u0011\tB@\u0011\u001d\u0011y\t\u0001C!\u0005#CqA!(\u0001\t\u0003\u0012y\nC\u0004\u0003$\u0002!\tA!*\t\u000f\t5\u0006\u0001\"\u0001\u00030\"9!Q\u0017\u0001\u0005B\t]\u0006b\u0002B\u007f\u0001\u0011\u0005!q \u0005\b\u0007\u0007\u0001A\u0011IB\u0003\u0011\u001d\u0019Y\u0001\u0001C\u0001\u0007\u001bAqaa\u0005\u0001\t\u0003\u0019)\u0002C\u0004\u0004\u001a\u0001!\taa\u0007\t\u000f\r}\u0001\u0001\"\u0011\u0004\"!91\u0011\u0006\u0001\u0005B\r-\u0002bBB\u0018\u0001\u0011\u00053\u0011\u0007\u0005\b\u0007s\u0001A\u0011IB\u001e\u0011\u001d\u0019y\u0004\u0001C!\u0007\u0003Bqa!\u0012\u0001\t\u0003\u001a9\u0005C\u0004\u0004L\u0001!\te!\u0014\t\u0011\rM\u0003\u0001)C)\u0007+:q!!\b?\u0011\u0003\tyB\u0002\u0004>}!\u0005\u0011\u0011\u0005\u0005\b\u0003\u000fqC\u0011AA\u0018\u0011\u001d\t\tD\fC\u0001\u0003gAq!a\u0011/\t\u0003\t)\u0005C\u0004\u0002b9\"\t!a\u0019\u0007\r\u0005ud\u0006BA@\u0011-I8G!A!\u0002\u0017\t\t*a%\t\u000f\u0005\u001d1\u0007\"\u0001\u0002\u001a\u0016)ao\r\u0001\u0002$\"9qn\rQ!\n\u0005\r\u0006bBASg\u0011\u0005\u0013q\u0015\u0005\b\u0003_\u001bD\u0011IAY\u0011\u001d\tIl\rC!\u0003wCq!a14\t\u0003\n)\rC\u0005\u0002H:\n\t\u0011\"\u0003\u0002J\n9AK]3f'\u0016$(BA A\u0003%IW.\\;uC\ndWM\u0003\u0002B\u0005\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\r\u000bQa]2bY\u0006\u001c\u0001!\u0006\u0002G\u001bN9\u0001aR,[?\nL\u0007c\u0001%J\u00176\ta(\u0003\u0002K}\tY\u0011IY:ue\u0006\u001cGoU3u!\taU\n\u0004\u0001\u0005\u000b9\u0003!\u0019A(\u0003\u0003\u0005\u000b\"\u0001\u0015+\u0011\u0005E\u0013V\"\u0001\"\n\u0005M\u0013%a\u0002(pi\"Lgn\u001a\t\u0003#VK!A\u0016\"\u0003\u0007\u0005s\u0017\u0010E\u0002I1.K!!\u0017 \u0003\u0013M{'\u000f^3e'\u0016$\b#\u0002%\\\u0017vs\u0016B\u0001/?\u00051\u0019vN\u001d;fIN+Go\u00149t!\tA\u0005\u0001E\u0002I\u0001-\u0003R\u0001\u00131L;zK!!\u0019 \u00037M#(/[2u\u001fB$\u0018.\\5{K\u0012\u001cvN\u001d;fIN+Go\u00149t!\u0015\u0019GmS/g\u001b\u0005\u0001\u0015BA3A\u0005a\u0019vN\u001d;fIN+GOR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0003\u0011\u001eL!\u0001\u001b \u0003\u0007M+G\u000f\u0005\u0002k[6\t1N\u0003\u0002m\u0001\u00069q-\u001a8fe&\u001c\u0017B\u00018l\u0005M!UMZ1vYR\u001cVM]5bY&T\u0018M\u00197f\u0003\u0011!(/Z3\u0016\u0003E\u0004BA];L):\u0011\u0001j]\u0005\u0003iz\nABU3e\u00052\f7m\u001b+sK\u0016L!A^<\u0003\tQ\u0013X-\u001a\u0006\u0003iz\nQ\u0001\u001e:fK\u0002\n\u0001b\u001c:eKJLgnZ\u000b\u0002wB\u0019Ap`&\u000f\u0005Ek\u0018B\u0001@C\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0001\u0002\u0004\tAqJ\u001d3fe&twM\u0003\u0002\u007f\u0005\u0006IqN\u001d3fe&tw\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0005-\u0011q\u0002\u000b\u0004=\u00065\u0001\"B=\u0006\u0001\bY\b\"B8\u0006\u0001\u0004\tHCAA\n)\rq\u0016Q\u0003\u0005\u0006s\u001a\u0001\u001da_\u0001\u0016g>\u0014H/\u001a3Ji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z+\t\tYB\u0004\u0002I[\u00059AK]3f'\u0016$\bC\u0001%/'\u0015q\u00131EA\u0015!\r\t\u0016QE\u0005\u0004\u0003O\u0011%AB!osJ+g\r\u0005\u0003d\u0003Wi\u0016bAA\u0017\u0001\n)2k\u001c:uK\u0012LE/\u001a:bE2,g)Y2u_JLHCAA\u0010\u0003\u0015)W\u000e\u001d;z+\u0011\t)$a\u000f\u0015\t\u0005]\u0012Q\b\t\u0005\u0011\u0002\tI\u0004E\u0002M\u0003w!QA\u0014\u0019C\u0002=C\u0011\"a\u00101\u0003\u0003\u0005\u001d!!\u0011\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0003}\u007f\u0006e\u0012\u0001\u00024s_6,B!a\u0012\u0002PQ!\u0011\u0011JA,)\u0011\tY%a\u0015\u0011\t!\u0003\u0011Q\n\t\u0004\u0019\u0006=CABA)c\t\u0007qJA\u0001F\u0011\u0019I\u0018\u0007q\u0001\u0002VA!Ap`A'\u0011\u001d\tI&\ra\u0001\u00037\n!!\u001b;\u0011\u000b\r\fi&!\u0014\n\u0007\u0005}\u0003I\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-\u0001\u0006oK^\u0014U/\u001b7eKJ,B!!\u001a\u0002vQ!\u0011qMA=!!\tI'a\u001c\u0002t\u0005]TBAA6\u0015\r\ti\u0007Q\u0001\b[V$\u0018M\u00197f\u0013\u0011\t\t(a\u001b\u0003\u001fI+Wo]1cY\u0016\u0014U/\u001b7eKJ\u00042\u0001TA;\t\u0015q%G1\u0001P!\u0011A\u0005!a\u001d\t\re\u0014\u00049AA>!\u0011ax0a\u001d\u0003\u001dQ\u0013X-Z*fi\n+\u0018\u000e\u001c3feV!\u0011\u0011QAF'\u0015\u0019\u00141QAG!\u0015\u0011\u0018QQAE\u0013\r\t9i\u001e\u0002\n'\u0016$\b*\u001a7qKJ\u00042\u0001TAF\t\u0015q5G1\u0001P!!\tI'a\u001c\u0002\n\u0006=\u0005\u0003\u0002%\u0001\u0003\u0013\u0003B\u0001`@\u0002\n&\u0019\u00110!&\n\u0007\u0005]uO\u0001\u0004IK2\u0004XM\u001d\u000b\u0003\u00037#B!!(\u0002\"B)\u0011qT\u001a\u0002\n6\ta\u0006\u0003\u0004zk\u0001\u000f\u0011\u0011\u0013\t\u0006eV\fI\tV\u0001\u0007C\u0012$wJ\\3\u0015\t\u0005%\u00161V\u0007\u0002g!9\u0011Q\u0016\u001dA\u0002\u0005%\u0015\u0001B3mK6\fa!\u00193e\u00032dG\u0003BAU\u0003gCq!!.:\u0001\u0004\t9,\u0001\u0002ygB)1-!\u0018\u0002\n\u0006)1\r\\3beR\u0011\u0011Q\u0018\t\u0004#\u0006}\u0016bAAa\u0005\n!QK\\5u\u0003\u0019\u0011Xm];miR\u0011\u0011qR\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0017\u0004B!!4\u0002X6\u0011\u0011q\u001a\u0006\u0005\u0003#\f\u0019.\u0001\u0003mC:<'BAAk\u0003\u0011Q\u0017M^1\n\t\u0005e\u0017q\u001a\u0002\u0007\u001f\nTWm\u0019;)\u000f9\ni.a9\u0002fB\u0019\u0011+a8\n\u0007\u0005\u0005(I\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t1\u0001K\u0004.\u0003;\f\u0019/!:\u0002\u00199,woU3u\u001fJ\u001cV\r\u001c4\u0015\u0007y\u000bi\u000f\u0003\u0004\u0002p\"\u0001\r!]\u0001\u0002i\u0006!1/\u001b>f+\t\t)\u0010E\u0002R\u0003oL1!!?C\u0005\rIe\u000e^\u0001\bSN,U\u000e\u001d;z+\t\ty\u0010E\u0002R\u0005\u0003I1Aa\u0001C\u0005\u001d\u0011un\u001c7fC:\fA\u0001[3bIV\t1*\u0001\u0003mCN$\u0018\u0001\u0002;bS2,\u0012AX\u0001\u0005S:LG/A\u0002nS:,BA!\u0006\u0003 Q\u00191Ja\u0006\t\u000f\teq\u0002q\u0001\u0003\u001c\u0005\u0019qN\u001d3\u0011\tq|(Q\u0004\t\u0004\u0019\n}Aa\u0002B\u0011\u001f\t\u0007!1\u0005\u0002\u0003\u0003F\n\"a\u0013+\u0002\u00075\f\u00070\u0006\u0003\u0003*\tEBcA&\u0003,!9!\u0011\u0004\tA\u0004\t5\u0002\u0003\u0002?\u0000\u0005_\u00012\u0001\u0014B\u0019\t\u001d\u0011\t\u0003\u0005b\u0001\u0005G\tA\u0001\u001a:paR\u0019aLa\u000e\t\u000f\te\u0012\u00031\u0001\u0002v\u0006\ta.\u0001\u0003uC.,Gc\u00010\u0003@!9!\u0011\b\nA\u0002\u0005U\u0018!B:mS\u000e,G#\u00020\u0003F\t\u001d\u0003bBA\"'\u0001\u0007\u0011Q\u001f\u0005\b\u0005\u0013\u001a\u0002\u0019AA{\u0003\u0015)h\u000e^5m\u0003%!'o\u001c9SS\u001eDG\u000fF\u0002_\u0005\u001fBqA!\u000f\u0015\u0001\u0004\t)0A\u0005uC.,'+[4iiR\u0019aL!\u0016\t\u000f\teR\u00031\u0001\u0002v\u0006Q1m\\;oi^C\u0017\u000e\\3\u0015\t\u0005U(1\f\u0005\b\u0005;2\u0002\u0019\u0001B0\u0003\u0005\u0001\bCB)\u0003b-\u000by0C\u0002\u0003d\t\u0013\u0011BR;oGRLwN\\\u0019\u0002\u0013\u0011\u0014x\u000e],iS2,Gc\u00010\u0003j!9!QL\fA\u0002\t}\u0013!\u0003;bW\u0016<\u0006.\u001b7f)\rq&q\u000e\u0005\b\u0005;B\u0002\u0019\u0001B0\u0003\u0011\u0019\b/\u00198\u0015\t\tU$1\u0010\t\u0006#\n]dLX\u0005\u0004\u0005s\u0012%A\u0002+va2,'\u0007C\u0004\u0003^e\u0001\rAa\u0018\u0002\u000f\u0019|'/Z1dQV!!\u0011\u0011BF)\u0011\tiLa!\t\u000f\t\u0015%\u00041\u0001\u0003\b\u0006\ta\r\u0005\u0004R\u0005CZ%\u0011\u0012\t\u0004\u0019\n-EA\u0002BG5\t\u0007qJA\u0001V\u0003!i\u0017N\\!gi\u0016\u0014H\u0003\u0002BJ\u00053\u0003B!\u0015BK\u0017&\u0019!q\u0013\"\u0003\r=\u0003H/[8o\u0011\u0019\u0011Yj\u0007a\u0001\u0017\u0006\u00191.Z=\u0002\u00135\f\u0007PQ3g_J,G\u0003\u0002BJ\u0005CCaAa'\u001d\u0001\u0004Y\u0015\u0001C5uKJ\fGo\u001c:\u0016\u0005\t\u001d\u0006\u0003B2\u0003*.K1Aa+A\u0005!IE/\u001a:bi>\u0014\u0018\u0001D5uKJ\fGo\u001c:Ge>lG\u0003\u0002BT\u0005cCaAa-\u001f\u0001\u0004Y\u0015!B:uCJ$\u0018aB:uKB\u0004XM]\u000b\u0005\u0005s\u0013\u0019\r\u0006\u0003\u0003<\nM(C\u0002B_\u0005\u0003\u00149N\u0002\u0004\u0003@\u0002\u0001!1\u0018\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004\u0019\n\rGa\u0002Bc?\t\u0007!q\u0019\u0002\u0002'F\u0019\u0001K!31\t\t-'1\u001b\t\u0006G\n5'\u0011[\u0005\u0004\u0005\u001f\u0004%aB*uKB\u0004XM\u001d\t\u0004\u0019\nMGa\u0003Bk\u0005\u0007\f\t\u0011!A\u0003\u0002=\u00131a\u0018\u00132!\u0011\u0011IN!<\u000f\t\tm'\u0011\u001e\b\u0005\u0005;\u00149O\u0004\u0003\u0003`\n\u0015XB\u0001Bq\u0015\r\u0011\u0019\u000fR\u0001\u0007yI|w\u000e\u001e \n\u0003\rK!!\u0011\"\n\u0007\t-\b)A\u0004Ti\u0016\u0004\b/\u001a:\n\t\t=(\u0011\u001f\u0002\u000f\u000b\u001a4\u0017nY5f]R\u001c\u0006\u000f\\5u\u0015\r\u0011Y\u000f\u0011\u0005\b\u0005k|\u00029\u0001B|\u0003\u0015\u0019\b.\u00199f!\u0019\u0019'\u0011`&\u0003B&\u0019!1 !\u0003\u0019M#X\r\u001d9feNC\u0017\r]3\u0002\u0011\r|g\u000e^1j]N$B!a@\u0004\u0002!1\u0011Q\u0016\u0011A\u0002-\u000bQA]1oO\u0016$RAXB\u0004\u0007\u0013Aa!a\u0011\"\u0001\u0004Y\u0005B\u0002B%C\u0001\u00071*A\u0005sC:<W-S7qYR)ala\u0004\u0004\u0012!9\u00111\t\u0012A\u0002\tM\u0005b\u0002B%E\u0001\u0007!1S\u0001\u0005S:\u001cG\u000eF\u0002_\u0007/Aa!!,$\u0001\u0004Y\u0015\u0001B3yG2$2AXB\u000f\u0011\u0019\ti\u000b\na\u0001\u0017\u000611m\u001c8dCR$2AXB\u0012\u0011\u001d\u0019)#\na\u0001\u0007O\tA\u0001\u001e5biB!1-!\u0018L\u0003)\u0011X-\\8wK\u0012\fE\u000e\u001c\u000b\u0004=\u000e5\u0002bBB\u0013M\u0001\u00071qE\u0001\nS:$XM]:fGR$2AXB\u001a\u0011\u001d\u0019)c\na\u0001\u0007k\u0001BaYB\u001c\u0017&\u0011\u0001\u000eQ\u0001\u0005I&4g\rF\u0002_\u0007{Aqa!\n)\u0001\u0004\u0019)$\u0001\u0004gS2$XM\u001d\u000b\u0004=\u000e\r\u0003b\u0002BCS\u0001\u0007!qL\u0001\na\u0006\u0014H/\u001b;j_:$BA!\u001e\u0004J!9!Q\f\u0016A\u0002\t}\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0000\u000e=\u0003BBB)W\u0001\u0007A+A\u0002pE*\f\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\r]\u0003\u0003BAg\u00073JAaa\u0017\u0002P\n11\u000b\u001e:j]\u001e\u0004"
)
public final class TreeSet extends AbstractSet implements SortedSet, StrictOptimizedSortedSetOps, DefaultSerializable {
   private final RedBlackTree.Tree tree;
   private final Ordering ordering;

   public static ReusableBuilder newBuilder(final Ordering ordering) {
      TreeSet$ var10000 = TreeSet$.MODULE$;
      return new TreeSetBuilder(ordering);
   }

   public static Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(TreeSet$.MODULE$, evidence$13);
   }

   public static Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      TreeSet$ unfold_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Unfold(init, f);
      return unfold_this.from(from_it, (Ordering)evidence$11);
   }

   public static Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      TreeSet$ iterate_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Iterate(start, len, f);
      return iterate_this.from(from_it, (Ordering)evidence$10);
   }

   public static Object tabulate(final int n, final Function1 f, final Object evidence$9) {
      TreeSet$ tabulate_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Tabulate(n, f);
      return tabulate_this.from(from_it, (Ordering)evidence$9);
   }

   public static Object fill(final int n, final Function0 elem, final Object evidence$8) {
      TreeSet$ fill_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Fill(n, elem);
      return fill_this.from(from_it, (Ordering)evidence$8);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public scala.collection.SortedSet map(final Function1 f, final Ordering ev) {
      return scala.collection.StrictOptimizedSortedSetOps.map$(this, f, ev);
   }

   public scala.collection.SortedSet flatMap(final Function1 f, final Ordering ev) {
      return scala.collection.StrictOptimizedSortedSetOps.flatMap$(this, f, ev);
   }

   public scala.collection.SortedSet zip(final IterableOnce that, final Ordering ev) {
      return scala.collection.StrictOptimizedSortedSetOps.zip$(this, that, ev);
   }

   public scala.collection.SortedSet collect(final PartialFunction pf, final Ordering ev) {
      return scala.collection.StrictOptimizedSortedSetOps.collect$(this, pf, ev);
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

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Set unsorted() {
      return SortedSet.unsorted$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$SortedSet$$super$equals(final Object that) {
      return scala.collection.Set.equals$(this, that);
   }

   public String stringPrefix() {
      return scala.collection.SortedSet.stringPrefix$(this);
   }

   public scala.collection.SortedSet fromSpecific(final IterableOnce coll) {
      return SortedSetFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return SortedSetFactoryDefaults.newSpecificBuilder$(this);
   }

   public scala.collection.SortedSet empty() {
      return SortedSetFactoryDefaults.empty$(this);
   }

   public scala.collection.SortedSetOps.WithFilter withFilter(final Function1 p) {
      return SortedSetFactoryDefaults.withFilter$(this, p);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   /** @deprecated */
   public Iterator keysIteratorFrom(final Object start) {
      return scala.collection.SortedSetOps.keysIteratorFrom$(this, start);
   }

   public Object firstKey() {
      return scala.collection.SortedSetOps.firstKey$(this);
   }

   public Object lastKey() {
      return scala.collection.SortedSetOps.lastKey$(this);
   }

   public scala.collection.SortedSetOps rangeTo(final Object to) {
      return scala.collection.SortedSetOps.rangeTo$(this, to);
   }

   /** @deprecated */
   public int compare(final Object k0, final Object k1) {
      return SortedOps.compare$(this, k0, k1);
   }

   /** @deprecated */
   public final Object from(final Object from) {
      return SortedOps.from$(this, from);
   }

   public Object rangeFrom(final Object from) {
      return SortedOps.rangeFrom$(this, from);
   }

   /** @deprecated */
   public final Object until(final Object until) {
      return SortedOps.until$(this, until);
   }

   public Object rangeUntil(final Object until) {
      return SortedOps.rangeUntil$(this, until);
   }

   /** @deprecated */
   public final Object to(final Object to) {
      return SortedOps.to$(this, to);
   }

   public RedBlackTree.Tree tree() {
      return this.tree;
   }

   public Ordering ordering() {
      return this.ordering;
   }

   public TreeSet$ sortedIterableFactory() {
      return TreeSet$.MODULE$;
   }

   private TreeSet newSetOrSelf(final RedBlackTree.Tree t) {
      return t == this.tree() ? this : new TreeSet(t, this.ordering());
   }

   public int size() {
      return RedBlackTree$.MODULE$.count(this.tree());
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Object head() {
      RedBlackTree.Tree var10000 = RedBlackTree$.MODULE$.smallest(this.tree());
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.scala$collection$immutable$RedBlackTree$Tree$$_key;
      }
   }

   public Object last() {
      RedBlackTree.Tree var10000 = RedBlackTree$.MODULE$.greatest(this.tree());
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.scala$collection$immutable$RedBlackTree$Tree$$_key;
      }
   }

   public TreeSet tail() {
      return new TreeSet(RedBlackTree$.MODULE$.tail(this.tree()), this.ordering());
   }

   public TreeSet init() {
      return new TreeSet(RedBlackTree$.MODULE$.init(this.tree()), this.ordering());
   }

   public Object min(final Ordering ord) {
      return ord == this.ordering() && IterableOnceOps.nonEmpty$(this) ? this.head() : scala.collection.SortedSetOps.min$(this, ord);
   }

   public Object max(final Ordering ord) {
      return ord == this.ordering() && IterableOnceOps.nonEmpty$(this) ? this.last() : scala.collection.SortedSetOps.max$(this, ord);
   }

   public TreeSet drop(final int n) {
      if (n <= 0) {
         return this;
      } else {
         return n >= this.size() ? (TreeSet)SortedSetFactoryDefaults.empty$(this) : new TreeSet(RedBlackTree$.MODULE$.drop(this.tree(), n, this.ordering()), this.ordering());
      }
   }

   public TreeSet take(final int n) {
      if (n <= 0) {
         return (TreeSet)SortedSetFactoryDefaults.empty$(this);
      } else {
         return n >= this.size() ? this : new TreeSet(RedBlackTree$.MODULE$.take(this.tree(), n, this.ordering()), this.ordering());
      }
   }

   public TreeSet slice(final int from, final int until) {
      if (until <= from) {
         return (TreeSet)SortedSetFactoryDefaults.empty$(this);
      } else if (from <= 0) {
         return this.take(until);
      } else {
         return until >= this.size() ? this.drop(from) : new TreeSet(RedBlackTree$.MODULE$.slice(this.tree(), from, until, this.ordering()), this.ordering());
      }
   }

   public TreeSet dropRight(final int n) {
      int var10001 = this.size();
      scala.math.package$ var10002 = scala.math.package$.MODULE$;
      int max_y = 0;
      return this.take(var10001 - Math.max(n, max_y));
   }

   public TreeSet takeRight(final int n) {
      int var10001 = this.size();
      scala.math.package$ var10002 = scala.math.package$.MODULE$;
      int max_y = 0;
      return this.drop(var10001 - Math.max(n, max_y));
   }

   private int countWhile(final Function1 p) {
      int result = 0;

      for(Iterator it = this.iterator(); it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next())); ++result) {
      }

      return result;
   }

   public TreeSet dropWhile(final Function1 p) {
      int countWhile_result = 0;

      for(Iterator countWhile_it = this.iterator(); countWhile_it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(countWhile_it.next())); ++countWhile_result) {
      }

      Object var4 = null;
      return this.drop(countWhile_result);
   }

   public TreeSet takeWhile(final Function1 p) {
      int countWhile_result = 0;

      for(Iterator countWhile_it = this.iterator(); countWhile_it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(countWhile_it.next())); ++countWhile_result) {
      }

      Object var4 = null;
      return this.take(countWhile_result);
   }

   public Tuple2 span(final Function1 p) {
      int countWhile_result = 0;

      for(Iterator countWhile_it = this.iterator(); countWhile_it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(countWhile_it.next())); ++countWhile_result) {
      }

      Object var4 = null;
      return IterableOps.splitAt$(this, countWhile_result);
   }

   public void foreach(final Function1 f) {
      RedBlackTree$.MODULE$.foreachKey(this.tree(), f);
   }

   public Option minAfter(final Object key) {
      RedBlackTree.Tree v = RedBlackTree$.MODULE$.minAfter(this.tree(), key, this.ordering());
      if (v == null) {
         Option$ var10000 = Option$.MODULE$;
         return None$.MODULE$;
      } else {
         return new Some(v.scala$collection$immutable$RedBlackTree$Tree$$_key);
      }
   }

   public Option maxBefore(final Object key) {
      RedBlackTree.Tree v = RedBlackTree$.MODULE$.maxBefore(this.tree(), key, this.ordering());
      if (v == null) {
         Option$ var10000 = Option$.MODULE$;
         return None$.MODULE$;
      } else {
         return new Some(v.scala$collection$immutable$RedBlackTree$Tree$$_key);
      }
   }

   public Iterator iterator() {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var4 = this.tree();
      RedBlackTree$ var10001 = RedBlackTree$.MODULE$;
      None$ var5 = None$.MODULE$;
      Ordering keysIterator_evidence$17 = this.ordering();
      None$ keysIterator_start = var5;
      RedBlackTree.Tree keysIterator_tree = var4;
      return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_evidence$17);
   }

   public Iterator iteratorFrom(final Object start) {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var5 = this.tree();
      Some var10001 = new Some(start);
      Ordering keysIterator_evidence$17 = this.ordering();
      Some keysIterator_start = var10001;
      RedBlackTree.Tree keysIterator_tree = var5;
      return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_evidence$17);
   }

   public Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntBinaryTreeStepper$ var35 = IntBinaryTreeStepper$.MODULE$;
         int var36 = this.size();
         RedBlackTree.Tree var41 = this.tree();
         Function1 var44 = (x$1) -> x$1.left();
         Function1 var47 = (x$2) -> x$2.right();
         Function1 from_extract = (x$3) -> BoxesRunTime.boxToInteger($anonfun$stepper$3(x$3));
         Function1 from_right = var47;
         Function1 from_left = var44;
         RedBlackTree.Tree from_root = var41;
         int from_maxLength = var36;
         IntBinaryTreeStepper from_ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongBinaryTreeStepper$ var33 = LongBinaryTreeStepper$.MODULE$;
         int var34 = this.size();
         RedBlackTree.Tree var40 = this.tree();
         Function1 var43 = (x$4) -> x$4.left();
         Function1 var46 = (x$5) -> x$5.right();
         Function1 from_extract = (x$6) -> BoxesRunTime.boxToLong($anonfun$stepper$6(x$6));
         Function1 from_right = var46;
         Function1 from_left = var43;
         RedBlackTree.Tree from_root = var40;
         int from_maxLength = var34;
         LongBinaryTreeStepper from_ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleBinaryTreeStepper$ var10000 = DoubleBinaryTreeStepper$.MODULE$;
         int var32 = this.size();
         RedBlackTree.Tree var39 = this.tree();
         Function1 var42 = (x$7) -> x$7.left();
         Function1 var45 = (x$8) -> x$8.right();
         Function1 from_extract = (x$9) -> BoxesRunTime.boxToDouble($anonfun$stepper$9(x$9));
         Function1 from_right = var45;
         Function1 from_left = var42;
         RedBlackTree.Tree from_root = var39;
         int from_maxLength = var32;
         DoubleBinaryTreeStepper from_ans = new DoubleBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else {
         AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
         int var37 = this.size();
         RedBlackTree.Tree var10002 = this.tree();
         Function1 var10003 = (x$10) -> x$10.left();
         Function1 var10004 = (x$11) -> x$11.right();
         Function1 from_extract = (x$12) -> x$12.key();
         Function1 from_right = var10004;
         Function1 from_left = var10003;
         RedBlackTree.Tree from_root = var10002;
         int from_maxLength = var37;
         AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         AnyBinaryTreeStepper var38 = from_ans;
         from_root = null;
         from_left = null;
         from_right = null;
         from_extract = null;
         Object var31 = null;
         return shape.parUnbox(var38);
      }
   }

   public boolean contains(final Object elem) {
      return RedBlackTree$.MODULE$.contains(this.tree(), elem, this.ordering());
   }

   public TreeSet range(final Object from, final Object until) {
      return this.newSetOrSelf(RedBlackTree$.MODULE$.range(this.tree(), from, until, this.ordering()));
   }

   public TreeSet rangeImpl(final Option from, final Option until) {
      return this.newSetOrSelf(RedBlackTree$.MODULE$.rangeImpl(this.tree(), from, until, this.ordering()));
   }

   public TreeSet incl(final Object elem) {
      return this.newSetOrSelf(RedBlackTree$.MODULE$.update(this.tree(), elem, (Object)null, false, this.ordering()));
   }

   public TreeSet excl(final Object elem) {
      return this.newSetOrSelf(RedBlackTree$.MODULE$.delete(this.tree(), elem, this.ordering()));
   }

   public TreeSet concat(final IterableOnce that) {
      RedBlackTree.Tree var7;
      label28: {
         TreeSet var3;
         label30: {
            if (that instanceof TreeSet) {
               var3 = (TreeSet)that;
               Ordering var10000 = this.ordering();
               Ordering var4 = var3.ordering();
               if (var10000 == null) {
                  if (var4 == null) {
                     break label30;
                  }
               } else if (var10000.equals(var4)) {
                  break label30;
               }
            }

            Iterator it = that.iterator();

            RedBlackTree.Tree t;
            for(t = this.tree(); it.hasNext(); t = RedBlackTree$.MODULE$.update(t, it.next(), (Object)null, false, this.ordering())) {
            }

            var7 = t;
            break label28;
         }

         var7 = RedBlackTree$.MODULE$.union(this.tree(), var3.tree(), this.ordering());
      }

      RedBlackTree.Tree t = var7;
      return this.newSetOrSelf(t);
   }

   public TreeSet removedAll(final IterableOnce that) {
      if (that instanceof TreeSet) {
         TreeSet var2 = (TreeSet)that;
         Ordering var10000 = this.ordering();
         Ordering var3 = var2.ordering();
         if (var10000 == null) {
            if (var3 == null) {
               return this.newSetOrSelf(RedBlackTree$.MODULE$.difference(this.tree(), var2.tree(), this.ordering()));
            }
         } else if (var10000.equals(var3)) {
            return this.newSetOrSelf(RedBlackTree$.MODULE$.difference(this.tree(), var2.tree(), this.ordering()));
         }
      }

      LazyRef sub$module = new LazyRef();
      that.iterator().foreach(this.sub$2(sub$module));
      return this.newSetOrSelf(this.sub$2(sub$module).currentTree());
   }

   public TreeSet intersect(final scala.collection.Set that) {
      if (that instanceof TreeSet) {
         TreeSet var2 = (TreeSet)that;
         Ordering var10000 = this.ordering();
         Ordering var3 = var2.ordering();
         if (var10000 == null) {
            if (var3 == null) {
               return this.newSetOrSelf(RedBlackTree$.MODULE$.intersect(this.tree(), var2.tree(), this.ordering()));
            }
         } else if (var10000.equals(var3)) {
            return this.newSetOrSelf(RedBlackTree$.MODULE$.intersect(this.tree(), var2.tree(), this.ordering()));
         }
      }

      return this.newSetOrSelf(RedBlackTree$.MODULE$.filterEntries(this.tree(), (k, x$13) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(f, k, x$13))));
   }

   public TreeSet diff(final scala.collection.Set that) {
      if (that instanceof TreeSet) {
         TreeSet var2 = (TreeSet)that;
         Ordering var10000 = this.ordering();
         Ordering var3 = var2.ordering();
         if (var10000 == null) {
            if (var3 == null) {
               return this.newSetOrSelf(RedBlackTree$.MODULE$.difference(this.tree(), var2.tree(), this.ordering()));
            }
         } else if (var10000.equals(var3)) {
            return this.newSetOrSelf(RedBlackTree$.MODULE$.difference(this.tree(), var2.tree(), this.ordering()));
         }
      }

      return (TreeSet)SetOps.diff$(this, that);
   }

   public TreeSet filter(final Function1 f) {
      return this.newSetOrSelf(RedBlackTree$.MODULE$.filterEntries(this.tree(), (k, x$13) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(f, k, x$13))));
   }

   public Tuple2 partition(final Function1 p) {
      Tuple2 var2 = RedBlackTree$.MODULE$.partitionEntries(this.tree(), (a, x$14) -> BoxesRunTime.boxToBoolean($anonfun$partition$1(p, a, x$14)));
      if (var2 != null) {
         RedBlackTree.Tree l = (RedBlackTree.Tree)var2._1();
         RedBlackTree.Tree r = (RedBlackTree.Tree)var2._2();
         return new Tuple2(this.newSetOrSelf(l), this.newSetOrSelf(r));
      } else {
         throw new MatchError((Object)null);
      }
   }

   public boolean equals(final Object obj) {
      if (obj instanceof TreeSet) {
         TreeSet var2 = (TreeSet)obj;
         Ordering var10000 = this.ordering();
         Ordering var3 = var2.ordering();
         if (var10000 == null) {
            if (var3 == null) {
               return RedBlackTree$.MODULE$.keysEqual(this.tree(), var2.tree(), this.ordering());
            }
         } else if (var10000.equals(var3)) {
            return RedBlackTree$.MODULE$.keysEqual(this.tree(), var2.tree(), this.ordering());
         }
      }

      return scala.collection.SortedSet.equals$(this, obj);
   }

   public String className() {
      return "TreeSet";
   }

   // $FF: synthetic method
   public static final int $anonfun$stepper$3(final RedBlackTree.Tree x$3) {
      return BoxesRunTime.unboxToInt(x$3.key());
   }

   // $FF: synthetic method
   public static final long $anonfun$stepper$6(final RedBlackTree.Tree x$6) {
      return BoxesRunTime.unboxToLong(x$6.key());
   }

   // $FF: synthetic method
   public static final double $anonfun$stepper$9(final RedBlackTree.Tree x$9) {
      return BoxesRunTime.unboxToDouble(x$9.key());
   }

   // $FF: synthetic method
   private final sub$1$ sub$lzycompute$1(final LazyRef sub$module$1) {
      synchronized(sub$module$1){}

      sub$1$ var2;
      try {
         class sub$1$ extends AbstractFunction1 {
            private RedBlackTree.Tree currentTree;
            // $FF: synthetic field
            private final TreeSet $outer;

            public RedBlackTree.Tree currentTree() {
               return this.currentTree;
            }

            public void currentTree_$eq(final RedBlackTree.Tree x$1) {
               this.currentTree = x$1;
            }

            public void apply(final Object k) {
               this.currentTree_$eq(RedBlackTree$.MODULE$.delete(this.currentTree(), k, this.$outer.ordering()));
            }

            public sub$1$() {
               if (TreeSet.this == null) {
                  throw null;
               } else {
                  this.$outer = TreeSet.this;
                  super();
                  this.currentTree = TreeSet.this.tree();
               }
            }
         }

         var2 = sub$module$1.initialized() ? (sub$1$)sub$module$1.value() : (sub$1$)sub$module$1.initialize(new sub$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final sub$1$ sub$2(final LazyRef sub$module$1) {
      return sub$module$1.initialized() ? (sub$1$)sub$module$1.value() : this.sub$lzycompute$1(sub$module$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$1(final Function1 f$1, final Object k, final Object x$13) {
      return BoxesRunTime.unboxToBoolean(f$1.apply(k));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$partition$1(final Function1 p$1, final Object a, final Object x$14) {
      return BoxesRunTime.unboxToBoolean(p$1.apply(a));
   }

   public TreeSet(final RedBlackTree.Tree tree, final Ordering ordering) {
      this.tree = tree;
      this.ordering = ordering;
      if (ordering == null) {
         throw new NullPointerException("ordering must not be null");
      }
   }

   public TreeSet(final Ordering ordering) {
      this((RedBlackTree.Tree)null, ordering);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class TreeSetBuilder extends RedBlackTree.SetHelper implements ReusableBuilder {
      private RedBlackTree.Tree tree = null;

      public void sizeHint(final int size) {
         Builder.sizeHint$(this, size);
      }

      public final void sizeHint(final IterableOnce coll, final int delta) {
         Builder.sizeHint$(this, coll, delta);
      }

      public final int sizeHint$default$2() {
         return Builder.sizeHint$default$2$(this);
      }

      public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
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

      public final Growable $plus$plus$eq(final IterableOnce elems) {
         return Growable.$plus$plus$eq$(this, elems);
      }

      public int knownSize() {
         return Growable.knownSize$(this);
      }

      public TreeSetBuilder addOne(final Object elem) {
         this.tree = this.mutableUpd(this.tree, elem);
         return this;
      }

      public TreeSetBuilder addAll(final IterableOnce xs) {
         TreeSet var2;
         label53: {
            if (xs instanceof TreeSet) {
               var2 = (TreeSet)xs;
               Ordering var10000 = var2.ordering();
               Ordering var3 = super.ordering();
               if (var10000 == null) {
                  if (var3 == null) {
                     break label53;
                  }
               } else if (var10000.equals(var3)) {
                  break label53;
               }
            }

            if (xs instanceof TreeMap) {
               label54: {
                  TreeMap var4 = (TreeMap)xs;
                  Ordering var6 = var4.ordering();
                  Ordering var5 = super.ordering();
                  if (var6 == null) {
                     if (var5 != null) {
                        break label54;
                     }
                  } else if (!var6.equals(var5)) {
                     break label54;
                  }

                  if (this.tree == null) {
                     this.tree = var4.tree0();
                  } else {
                     this.tree = RedBlackTree$.MODULE$.union(this.beforePublish(this.tree), var4.tree0(), super.ordering());
                  }

                  return this;
               }
            }

            Growable.addAll$(this, xs);
            return this;
         }

         if (this.tree == null) {
            this.tree = var2.tree();
         } else {
            this.tree = RedBlackTree$.MODULE$.union(this.beforePublish(this.tree), var2.tree(), super.ordering());
         }

         return this;
      }

      public void clear() {
         this.tree = null;
      }

      public TreeSet result() {
         return new TreeSet(this.beforePublish(this.tree), super.ordering());
      }

      public TreeSetBuilder(final Ordering ordering) {
         super(ordering);
      }
   }
}
