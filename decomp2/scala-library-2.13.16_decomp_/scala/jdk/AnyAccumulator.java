package scala.jdk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.AnyStepper;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.View;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\r%c\u0001\u0002\u001f>\u0005\tCQA\u001c\u0001\u0005\u0002=D\u0001\u0002\u001d\u0001A\u0002\u0013\u0005Q(\u001d\u0005\tq\u0002\u0001\r\u0011\"\u0001>s\"1q\u0010\u0001Q!\nID!\"!\u0001\u0001\u0001\u0004%\t!PA\u0002\u0011)\t9\u0001\u0001a\u0001\n\u0003i\u0014\u0011\u0002\u0005\t\u0003\u001b\u0001\u0001\u0015)\u0003\u0002\u0006!Q\u0011q\u0002\u0001A\u0002\u0013\u0005Q(!\u0005\t\u0015\u0005m\u0001\u00011A\u0005\u0002u\ni\u0002\u0003\u0005\u0002\"\u0001\u0001\u000b\u0015BA\n\u0011!\t\u0019\u0003\u0001C\u0001{\u0005\u0015\u0002\u0002CA\u0019\u0001\u0001&\t&a\r\t\u000f\u0005\u0015\u0003\u0001\"\u0001\u0002H!9\u00111\u0011\u0001\u0005\n\u0005\u0015\u0005bBAD\u0001\u0011%\u0011Q\u0011\u0005\b\u0003\u0013\u0003A\u0011AAF\u0011\u0019\t\u0019\n\u0001C!_\"9\u0011Q\u0013\u0001\u0005\u0002\u0005]\u0005bBAU\u0001\u0011\u0005\u0013Q\u0011\u0005\b\u0003W\u0003A\u0011AAW\u0011\u001d\tY\u000b\u0001C\u0001\u0003gCq!a.\u0001\t\u0003\tI\fC\u0004\u00028\u0002!\t!a1\t\u000f\u0005%\u0007\u0001\"\u0001\u0002L\"9\u00111\u001b\u0001\u0005\u0002\u0005U\u0007bBAt\u0001\u0011\u0005\u0013\u0011\u001e\u0005\b\u0005\u000f\u0001A\u0011\tB\u0005\u0011\u001d\u0011\t\u0002\u0001C!\u0005'AqAa\n\u0001\t\u0003\u0012I\u0003C\u0004\u00032\u0001!IAa\r\b\u000f\tUR\b#\u0001\u00038\u00191A(\u0010E\u0001\u0005sAaA\u001c\u0011\u0005\u0002\tm\u0002\u0002\u0003B\u001fA\t\u0007I\u0011B9\t\u000f\t}\u0002\u0005)A\u0005e\"I!\u0011\t\u0011C\u0002\u0013%\u00111\u0001\u0005\t\u0005\u0007\u0002\u0003\u0015!\u0003\u0002\u0006!I!Q\t\u0011C\u0002\u0013%\u0011\u0011\u0003\u0005\t\u0005\u000f\u0002\u0003\u0015!\u0003\u0002\u0014!9!\u0011\n\u0011\u0005\u0002\t-\u0003b\u0002B5A\u0011\u0005!1\u000e\u0005\b\u0005w\u0002C\u0011\u0001B?\u0011\u001d\u00119\t\tC\u0001\u0005\u0013CqAa%!\t\u0003\u0011)\nC\u0004\u0003&\u0002\"\tAa*\t\u000f\tM\u0006\u0005\"\u0001\u00036\"9!\u0011\u001a\u0011\u0005\u0002\t-\u0007b\u0002BkA\u0011\u0005!q\u001b\u0004\u0007\u0005O\u0004\u0003A!;\t\u0015\t5\u0018G!b\u0001\n\u0013\u0011y\u000f\u0003\u0006\u0003xF\u0012\t\u0011)A\u0005\u0005cDaA\\\u0019\u0005\u0002\r\u0005\u0001bCAJc\u0001\u0007\t\u0019!C\u0005\u0007\u0013A1b!\u00042\u0001\u0004\u0005\r\u0011\"\u0003\u0004\u0010!Y11C\u0019A\u0002\u0003\u0005\u000b\u0015BB\u0006\u0011\u001d\u00199\"\rC\u0005\u00073Aqaa\u000b2\t\u0013\u0019i\u0003C\u0004\u0004:E\"IAa\r\t\u0013\tE\u0002%!A\u0005\n\rm\"AD!os\u0006\u001b7-^7vY\u0006$xN\u001d\u0006\u0003}}\n1A\u001b3l\u0015\u0005\u0001\u0015!B:dC2\f7\u0001A\u000b\u0003\u0007*\u001bR\u0001\u0001#W=\n\u0004R!\u0012$I)Vk\u0011!P\u0005\u0003\u000fv\u00121\"Q2dk6,H.\u0019;peB\u0011\u0011J\u0013\u0007\u0001\t\u0015Y\u0005A1\u0001M\u0005\u0005\t\u0015CA'R!\tqu*D\u0001@\u0013\t\u0001vHA\u0004O_RD\u0017N\\4\u0011\u00059\u0013\u0016BA*@\u0005\r\te.\u001f\t\u0003\u000b\u0002\u00012!\u0012\u0001I!\u00159F\f\u0013+V\u001b\u0005A&BA-[\u0003\u001diW\u000f^1cY\u0016T!aW \u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002^1\n11+Z9PaN\u0004Ba\u00181I)6\t!,\u0003\u0002b5\n9\u0012\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0003G.t!\u0001Z5\u000f\u0005\u0015DW\"\u00014\u000b\u0005\u001d\f\u0015A\u0002\u001fs_>$h(C\u0001A\u0013\tQw(A\u0004qC\u000e\\\u0017mZ3\n\u00051l'\u0001D*fe&\fG.\u001b>bE2,'B\u00016@\u0003\u0019a\u0014N\\5u}Q\tQ+A\u0004dkJ\u0014XM\u001c;\u0016\u0003I\u00042AT:v\u0013\t!xHA\u0003BeJ\f\u0017\u0010\u0005\u0002Om&\u0011qo\u0010\u0002\u0007\u0003:L(+\u001a4\u0002\u0017\r,(O]3oi~#S-\u001d\u000b\u0003uv\u0004\"AT>\n\u0005q|$\u0001B+oSRDqA`\u0002\u0002\u0002\u0003\u0007!/A\u0002yIE\n\u0001bY;se\u0016tG\u000fI\u0001\bQ&\u001cHo\u001c:z+\t\t)\u0001E\u0002OgJ\f1\u0002[5ti>\u0014\u0018p\u0018\u0013fcR\u0019!0a\u0003\t\u0011y4\u0011\u0011!a\u0001\u0003\u000b\t\u0001\u0002[5ti>\u0014\u0018\u0010I\u0001\u0006GVlW\u000f\\\u000b\u0003\u0003'\u0001BAT:\u0002\u0016A\u0019a*a\u0006\n\u0007\u0005eqH\u0001\u0003M_:<\u0017!C2v[Vdw\fJ3r)\rQ\u0018q\u0004\u0005\t}&\t\t\u00111\u0001\u0002\u0014\u000511-^7vY\u0002\n!bY;nk2\fG/\u001b<f)\u0011\t)\"a\n\t\u000f\u0005%2\u00021\u0001\u0002,\u0005\t\u0011\u000eE\u0002O\u0003[I1!a\f@\u0005\rIe\u000e^\u0001\nG2\f7o\u001d(b[\u0016,\"!!\u000e\u0011\t\u0005]\u0012q\b\b\u0005\u0003s\tY\u0004\u0005\u0002f\u007f%\u0019\u0011QH \u0002\rA\u0013X\rZ3g\u0013\u0011\t\t%a\u0011\u0003\rM#(/\u001b8h\u0015\r\tidP\u0001\u0011K\u001a4\u0017nY5f]R\u001cF/\u001a9qKJ,B!!\u0013\u0002TQ!\u00111JA=%\u0019\ti%!\u0015\u0002h\u00191\u0011q\n\u0001\u0001\u0003\u0017\u0012A\u0002\u0010:fM&tW-\\3oiz\u00022!SA*\t\u001d\t)&\u0004b\u0001\u0003/\u0012\u0011aU\t\u0004\u001b\u0006e\u0003\u0007BA.\u0003G\u0002RaXA/\u0003CJ1!a\u0018[\u0005\u001d\u0019F/\u001a9qKJ\u00042!SA2\t-\t)'a\u0015\u0002\u0002\u0003\u0005)\u0011\u0001'\u0003\u0007}#\u0013\u0007\u0005\u0003\u0002j\u0005Md\u0002BA6\u0003_r1\u0001ZA7\u0013\tYv(C\u0002\u0002ri\u000bqa\u0015;faB,'/\u0003\u0003\u0002v\u0005]$AD#gM&\u001c\u0017.\u001a8u'Bd\u0017\u000e\u001e\u0006\u0004\u0003cR\u0006bBA>\u001b\u0001\u000f\u0011QP\u0001\u0006g\"\f\u0007/\u001a\t\u0007?\u0006}\u0004*!\u0015\n\u0007\u0005\u0005%L\u0001\u0007Ti\u0016\u0004\b/\u001a:TQ\u0006\u0004X-\u0001\u0004fqB\fg\u000e\u001a\u000b\u0002u\u00069\u0001.\u0012=qC:$\u0017AB1eI>sW\r\u0006\u0003\u0002\u000e\u0006=U\"\u0001\u0001\t\r\u0005E\u0005\u00031\u0001I\u0003\u0005\t\u0017A\u0002:fgVdG/A\u0003ee\u0006Lg.\u0006\u0003\u0002\u001a\u0006\rFc\u0001>\u0002\u001c\"9\u0011Q\u0014\nA\u0002\u0005}\u0015\u0001\u0002;iCR\u0004B!\u0012\u0001\u0002\"B\u0019\u0011*a)\u0005\u000f\u0005\u0015&C1\u0001\u0002(\n\u0011\u0011)M\t\u0003\u001b\"\u000bQa\u00197fCJ\fQ!\u00199qYf$2\u0001SAX\u0011\u001d\t\t\f\u0006a\u0001\u0003+\t!!\u001b=\u0015\u0007!\u000b)\fC\u0004\u0002*U\u0001\r!a\u000b\u0002\rU\u0004H-\u0019;f)\u0015Q\u00181XA`\u0011\u001d\tiL\u0006a\u0001\u0003+\t1!\u001b3y\u0011\u0019\t\tM\u0006a\u0001\u0011\u0006!Q\r\\3n)\u0015Q\u0018QYAd\u0011\u001d\til\u0006a\u0001\u0003WAa!!1\u0018\u0001\u0004A\u0015\u0001C5uKJ\fGo\u001c:\u0016\u0005\u00055\u0007\u0003B2\u0002P\"K1!!5n\u0005!IE/\u001a:bi>\u0014\u0018!C2pk:$Hj\u001c8h)\u0011\t)\"a6\t\u000f\u0005e\u0017\u00041\u0001\u0002\\\u0006\t\u0001\u000f\u0005\u0004O\u0003;D\u0015\u0011]\u0005\u0004\u0003?|$!\u0003$v]\u000e$\u0018n\u001c82!\rq\u00151]\u0005\u0004\u0003K|$a\u0002\"p_2,\u0017M\\\u0001\bi>\f%O]1z+\u0011\tY/!=\u0015\t\u00055\u0018q\u001f\t\u0005\u001dN\fy\u000fE\u0002J\u0003c$q!a=\u001b\u0005\u0004\t)PA\u0001C#\tA\u0015\u000bC\u0005\u0002zj\t\t\u0011q\u0001\u0002|\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005u(1AAx\u001b\t\tyPC\u0002\u0003\u0002}\nqA]3gY\u0016\u001cG/\u0003\u0003\u0003\u0006\u0005}(\u0001C\"mCN\u001cH+Y4\u0002\rQ|G*[:u+\t\u0011Y\u0001\u0005\u0003d\u0005\u001bA\u0015b\u0001B\b[\n!A*[:u\u0003\t!x.\u0006\u0003\u0003\u0016\teA\u0003\u0002B\f\u0005;\u00012!\u0013B\r\t\u0019\u0011Y\u0002\bb\u0001\u0019\n\u00111)\r\u0005\b\u0005?a\u0002\u0019\u0001B\u0011\u0003\u001d1\u0017m\u0019;pef\u0004ba\u0018B\u0012\u0011\n]\u0011b\u0001B\u00135\n9a)Y2u_JL\u0018aD5uKJ\f'\r\\3GC\u000e$xN]=\u0016\u0005\t-\u0002\u0003B0\u0003.QK1Aa\f[\u0005)\u0019V-\u001d$bGR|'/_\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002k\u0006q\u0011I\\=BG\u000e,X.\u001e7bi>\u0014\bCA#!'\u0011\u0001SOa\u000b\u0015\u0005\t]\u0012\u0001E3naRL\u0018I\\=SK\u001a\f%O]1z\u0003E)W\u000e\u001d;z\u0003:L(+\u001a4BeJ\f\u0017\u0010I\u0001\u0016K6\u0004H/_!osJ+g-\u0011:sCf\f%O]1z\u0003Y)W\u000e\u001d;z\u0003:L(+\u001a4BeJ\f\u00170\u0011:sCf\u0004\u0013AD3naRLHj\u001c8h\u0003J\u0014\u0018-_\u0001\u0010K6\u0004H/\u001f'p]\u001e\f%O]1zA\u0005A1/\u001e9qY&,'/\u0006\u0003\u0003N\t\u001dTC\u0001B(!\u0019\u0011\tFa\u0018\u0003d5\u0011!1\u000b\u0006\u0005\u0005+\u00129&\u0001\u0005gk:\u001cG/[8o\u0015\u0011\u0011IFa\u0017\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0005;\nAA[1wC&!!\u0011\rB*\u0005!\u0019V\u000f\u001d9mS\u0016\u0014\b\u0003B#\u0001\u0005K\u00022!\u0013B4\t\u0015Y\u0005F1\u0001M\u0003\u0015\tG\rZ3s+\u0011\u0011iG!\u001f\u0016\u0005\t=\u0004\u0003\u0003B)\u0005c\u0012)Ha\u001e\n\t\tM$1\u000b\u0002\u000b\u0005&\u001cuN\\:v[\u0016\u0014\b\u0003B#\u0001\u0005o\u00022!\u0013B=\t\u0015Y\u0015F1\u0001M\u0003=)hNY8yK\u0012Le\u000e^!eI\u0016\u0014XC\u0001B@!\u0019\u0011\tF!!\u0003\u0006&!!1\u0011B*\u00059y%M[%oi\u000e{gn];nKJ\u0004B!\u0012\u0001\u0002,\u0005\u0001RO\u001c2pq\u0016$Gj\u001c8h\u0003\u0012$WM]\u000b\u0003\u0005\u0017\u0003bA!\u0015\u0003\u000e\nE\u0015\u0002\u0002BH\u0005'\u0012qb\u00142k\u0019>twmQ8ogVlWM\u001d\t\u0005\u000b\u0002\t)\"\u0001\nv]\n|\u00070\u001a3E_V\u0014G.Z!eI\u0016\u0014XC\u0001BL!\u0019\u0011\tF!'\u0003\u001e&!!1\u0014B*\u0005Ey%M\u001b#pk\ndWmQ8ogVlWM\u001d\t\u0005\u000b\u0002\u0011y\nE\u0002O\u0005CK1Aa)@\u0005\u0019!u.\u001e2mK\u00061Q.\u001a:hKJ,BA!+\u00032V\u0011!1\u0016\t\t\u0005#\u0012\tH!,\u0003.B!Q\t\u0001BX!\rI%\u0011\u0017\u0003\u0006\u00176\u0012\r\u0001T\u0001\u0005MJ|W.\u0006\u0003\u00038\nuF\u0003\u0002B]\u0005\u007f\u0003B!\u0012\u0001\u0003<B\u0019\u0011J!0\u0005\u000b-s#\u0019\u0001'\t\u000f\t\u0005g\u00061\u0001\u0003D\u000611o\\;sG\u0016\u0004Ra\u0019Bc\u0005wK1Aa2n\u00051IE/\u001a:bE2,wJ\\2f\u0003\u0015)W\u000e\u001d;z+\u0011\u0011iMa5\u0016\u0005\t=\u0007\u0003B#\u0001\u0005#\u00042!\u0013Bj\t\u0015YuF1\u0001M\u0003)qWm\u001e\"vS2$WM]\u000b\u0005\u00053\u0014\u0019/\u0006\u0002\u0003\\B9qK!8\u0003b\n\u0015\u0018b\u0001Bp1\n9!)^5mI\u0016\u0014\bcA%\u0003d\u0012)1\n\rb\u0001\u0019B!Q\t\u0001Bq\u0005I\u0019VM]5bY&T\u0018\r^5p]B\u0013x\u000e_=\u0016\t\t-(Q_\n\u0004cU\u0014\u0017aA1dGV\u0011!\u0011\u001f\t\u0005\u000b\u0002\u0011\u0019\u0010E\u0002J\u0005k$QaS\u0019C\u00021\u000bA!Y2dA!\u001a1Ga?\u0011\u00079\u0013i0C\u0002\u0003\u0000~\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0015\t\r\r1q\u0001\t\u0006\u0007\u000b\t$1_\u0007\u0002A!9!Q\u001e\u001bA\u0002\tEXCAB\u0006!\r)\u0005!^\u0001\u000be\u0016\u001cX\u000f\u001c;`I\u0015\fHc\u0001>\u0004\u0012!AaPNA\u0001\u0002\u0004\u0019Y!A\u0004sKN,H\u000e\u001e\u0011)\u0007]\u0012Y0A\u0006xe&$Xm\u00142kK\u000e$Hc\u0001>\u0004\u001c!91Q\u0004\u001dA\u0002\r}\u0011aA8viB!1\u0011EB\u0014\u001b\t\u0019\u0019C\u0003\u0003\u0004&\tm\u0013AA5p\u0013\u0011\u0019Ica\t\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u000be\u0016\fGm\u00142kK\u000e$Hc\u0001>\u00040!91\u0011G\u001dA\u0002\rM\u0012AA5o!\u0011\u0019\tc!\u000e\n\t\r]21\u0005\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0017a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"a!\u0010\u0011\t\r}2QI\u0007\u0003\u0007\u0003RAaa\u0011\u0003\\\u0005!A.\u00198h\u0013\u0011\u00199e!\u0011\u0003\r=\u0013'.Z2u\u0001"
)
public final class AnyAccumulator extends Accumulator implements Serializable {
   private Object[] current;
   private Object[][] history;
   private long[] cumul;

   public static Builder newBuilder() {
      AnyAccumulator$ var10000 = AnyAccumulator$.MODULE$;
      return new AnyAccumulator();
   }

   public static AnyAccumulator from(final IterableOnce source) {
      return AnyAccumulator$.MODULE$.from(source);
   }

   public static BiConsumer merger() {
      return AnyAccumulator$.MODULE$.merger();
   }

   public static ObjDoubleConsumer unboxedDoubleAdder() {
      return AnyAccumulator$.MODULE$.unboxedDoubleAdder();
   }

   public static ObjLongConsumer unboxedLongAdder() {
      return AnyAccumulator$.MODULE$.unboxedLongAdder();
   }

   public static ObjIntConsumer unboxedIntAdder() {
      return AnyAccumulator$.MODULE$.unboxedIntAdder();
   }

   public static BiConsumer adder() {
      return AnyAccumulator$.MODULE$.adder();
   }

   public static Supplier supplier() {
      return AnyAccumulator$.MODULE$.supplier();
   }

   public static SeqOps unapplySeq(final SeqOps x) {
      AnyAccumulator$ var10000 = AnyAccumulator$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      AnyAccumulator$ tabulate_this = AnyAccumulator$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$7$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      AnyAccumulator$ tabulate_this = AnyAccumulator$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$5$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      AnyAccumulator$ tabulate_this = AnyAccumulator$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$3$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      AnyAccumulator$ tabulate_this = AnyAccumulator$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$1$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n, final Function1 f) {
      AnyAccumulator$ tabulate_this = AnyAccumulator$.MODULE$;
      IterableOnce from_source = new View.Tabulate(n, f);
      return tabulate_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      AnyAccumulator$ fill_this = AnyAccumulator$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$4;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      AnyAccumulator$ fill_this = AnyAccumulator$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$3;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      AnyAccumulator$ fill_this = AnyAccumulator$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$2;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      AnyAccumulator$ fill_this = AnyAccumulator$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$1;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n, final Function0 elem) {
      AnyAccumulator$ fill_this = AnyAccumulator$.MODULE$;
      IterableOnce from_source = new View.Fill(n, elem);
      return fill_this.from(from_source);
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(AnyAccumulator$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(AnyAccumulator$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      AnyAccumulator$ unfold_this = AnyAccumulator$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      AnyAccumulator$ iterate_this = AnyAccumulator$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public Object[] current() {
      return this.current;
   }

   public void current_$eq(final Object[] x$1) {
      this.current = x$1;
   }

   public Object[][] history() {
      return this.history;
   }

   public void history_$eq(final Object[][] x$1) {
      this.history = x$1;
   }

   public long[] cumul() {
      return this.cumul;
   }

   public void cumul_$eq(final long[] x$1) {
      this.cumul = x$1;
   }

   public long cumulative(final int i) {
      return this.cumul()[i];
   }

   public String className() {
      return "AnyAccumulator";
   }

   public Stepper efficientStepper(final StepperShape shape) {
      return shape.parUnbox(new AnyAccumulatorStepper(this));
   }

   private void expand() {
      if (this.index() > 0) {
         if (this.hIndex() >= this.history().length) {
            this.hExpand();
         }

         this.history()[this.hIndex()] = this.current();
         long[] var10000 = this.cumul();
         int var10001 = this.hIndex();
         long var10002;
         if (this.hIndex() > 0) {
            int cumulative_i = this.hIndex() - 1;
            var10002 = this.cumul()[cumulative_i];
         } else {
            var10002 = 0L;
         }

         var10000[var10001] = var10002 + (long)this.index();
         this.hIndex_$eq(this.hIndex() + 1);
      }

      this.current_$eq(new Object[this.nextBlockSize()]);
      this.index_$eq(0);
   }

   private void hExpand() {
      if (this.hIndex() == 0) {
         this.history_$eq(new Object[4][]);
         this.cumul_$eq(new long[4]);
      } else {
         this.history_$eq(Arrays.copyOf(this.history(), this.history().length << 1));
         this.cumul_$eq(Arrays.copyOf(this.cumul(), this.cumul().length << 1));
      }
   }

   public AnyAccumulator addOne(final Object a) {
      this.totalSize_$eq(this.totalSize() + 1L);
      if (this.index() >= this.current().length) {
         this.expand();
      }

      this.current()[this.index()] = a;
      this.index_$eq(this.index() + 1);
      return this;
   }

   public AnyAccumulator result() {
      return this;
   }

   public void drain(final AnyAccumulator that) {
      int h = 0;
      long prev = 0L;
      boolean more = true;

      while(more && h < that.hIndex()) {
         int n = (int)(that.cumul()[h] - prev);
         if (this.current().length - this.index() >= n) {
            System.arraycopy(that.history()[h], 0, this.current(), this.index(), n);
            prev = that.cumul()[h];
            this.index_$eq(this.index() + n);
            ++h;
         } else {
            more = false;
         }
      }

      if (h >= that.hIndex() && this.current().length - this.index() >= that.index()) {
         if (that.index() > 0) {
            System.arraycopy(that.current(), 0, this.current(), this.index(), that.index());
         }

         this.index_$eq(this.index() + that.index());
      } else {
         int slots = (this.index() > 0 ? 1 : 0) + that.hIndex() - h;
         if (this.hIndex() + slots > this.history().length) {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            int n = Math.max(4, 1 << 32 - Integer.numberOfLeadingZeros(1 + this.hIndex() + slots));
            this.history_$eq(Arrays.copyOf(this.history(), n));
            this.cumul_$eq(Arrays.copyOf(this.cumul(), n));
         }

         long var12;
         if (this.hIndex() > 0) {
            int cumulative_i = this.hIndex() - 1;
            var12 = this.cumul()[cumulative_i];
         } else {
            var12 = 0L;
         }

         long pv = var12;
         if (this.index() > 0) {
            pv += (long)this.index();
            this.cumul()[this.hIndex()] = pv;
            this.history()[this.hIndex()] = this.index() < this.current().length >>> 3 && this.current().length > 32 ? Arrays.copyOf(this.current(), this.index()) : this.current();
            this.hIndex_$eq(this.hIndex() + 1);
         }

         while(h < that.hIndex()) {
            pv += that.cumul()[h] - prev;
            prev = that.cumul()[h];
            this.cumul()[this.hIndex()] = pv;
            this.history()[this.hIndex()] = that.history()[h];
            ++h;
            this.hIndex_$eq(this.hIndex() + 1);
         }

         this.index_$eq(that.index());
         this.current_$eq(that.current());
      }

      this.totalSize_$eq(this.totalSize() + that.totalSize());
      that.clear();
   }

   public void clear() {
      super.clear();
      this.current_$eq(AnyAccumulator$.MODULE$.scala$jdk$AnyAccumulator$$emptyAnyRefArray());
      this.history_$eq(AnyAccumulator$.MODULE$.scala$jdk$AnyAccumulator$$emptyAnyRefArrayArray());
      this.cumul_$eq(AnyAccumulator$.MODULE$.scala$jdk$AnyAccumulator$$emptyLongArray());
   }

   public Object apply(final long ix) {
      if (this.totalSize() - ix > (long)this.index() && this.hIndex() != 0) {
         long w = this.seekSlot(ix);
         return this.history()[(int)(w >>> 32)][(int)(w & 4294967295L)];
      } else {
         return this.current()[(int)(ix - (this.totalSize() - (long)this.index()))];
      }
   }

   public Object apply(final int i) {
      return this.apply((long)i);
   }

   public void update(final long idx, final Object elem) {
      if (this.totalSize() - idx > (long)this.index() && this.hIndex() != 0) {
         long w = this.seekSlot(idx);
         this.history()[(int)(w >>> 32)][(int)(w & 4294967295L)] = elem;
      } else {
         this.current()[(int)(idx - (this.totalSize() - (long)this.index()))] = elem;
      }
   }

   public void update(final int idx, final Object elem) {
      this.update((long)idx, elem);
   }

   public Iterator iterator() {
      StepperShape$ anyStepperShape_this = StepperShape$.MODULE$;
      StepperShape var10000 = anyStepperShape_this.anyStepperShapePrototype();
      Object var3 = null;
      StepperShape stepper_shape = var10000;
      Stepper var5 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      return var5.iterator();
   }

   public long countLong(final Function1 p) {
      long r = 0L;
      StepperShape$ anyStepperShape_this = StepperShape$.MODULE$;
      StepperShape var10000 = anyStepperShape_this.anyStepperShapePrototype();
      Object var7 = null;
      StepperShape stepper_shape = var10000;
      Stepper var9 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      AnyStepper s = (AnyStepper)var9;

      while(s.hasStep()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(s.nextStep()))) {
            ++r;
         }
      }

      return r;
   }

   public Object toArray(final ClassTag evidence$1) {
      if (this.totalSize() > 2147483647L) {
         throw new IllegalArgumentException((new StringBuilder(44)).append("Too many elements accumulated for an array: ").append(Long.toString(this.totalSize())).toString());
      } else {
         Object a = evidence$1.newArray((int)this.totalSize());
         int j = 0;
         int h = 0;

         for(long pv = 0L; h < this.hIndex(); ++h) {
            Object[] x = this.history()[h];
            long n = this.cumul()[h] - pv;
            pv = this.cumul()[h];

            for(int i = 0; (long)i < n; ++j) {
               ScalaRunTime$.MODULE$.array_update(a, j, x[i]);
               ++i;
            }
         }

         for(int i = 0; i < this.index(); ++j) {
            ScalaRunTime$.MODULE$.array_update(a, j, this.current()[i]);
            ++i;
         }

         return a;
      }
   }

   public List toList() {
      List ans = Nil$.MODULE$;

      for(int i = this.index() - 1; i >= 0; --i) {
         Object var3 = this.current()[i];
         ans = ans.$colon$colon(var3);
      }

      for(int h = this.hIndex() - 1; h >= 0; --h) {
         Object[] a = this.history()[h];
         long var10000 = this.cumul()[h];
         long var10001;
         if (h == 0) {
            var10001 = 0L;
         } else {
            int cumulative_i = h - 1;
            var10001 = this.cumul()[cumulative_i];
         }

         for(int var8 = (int)(var10000 - var10001) - 1; var8 >= 0; --var8) {
            Object var6 = a[var8];
            ans = ans.$colon$colon(var6);
         }
      }

      return ans;
   }

   public Object to(final Factory factory) {
      if (this.totalSize() > 2147483647L) {
         throw new IllegalArgumentException((new StringBuilder(54)).append("Too many elements accumulated for a Scala collection: ").append(Long.toString(this.totalSize())).toString());
      } else {
         return factory.fromSpecific(this.iterator());
      }
   }

   public SeqFactory iterableFactory() {
      return AnyAccumulator$.MODULE$;
   }

   private Object writeReplace() {
      return new SerializationProxy(this);
   }

   public AnyAccumulator() {
      this.current = AnyAccumulator$.MODULE$.scala$jdk$AnyAccumulator$$emptyAnyRefArray();
      this.history = AnyAccumulator$.MODULE$.scala$jdk$AnyAccumulator$$emptyAnyRefArrayArray();
      this.cumul = AnyAccumulator$.MODULE$.scala$jdk$AnyAccumulator$$emptyLongArray();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SerializationProxy implements Serializable {
      private final transient AnyAccumulator acc;
      private transient AnyAccumulator result;

      private AnyAccumulator acc() {
         return this.acc;
      }

      private AnyAccumulator result() {
         return this.result;
      }

      private void result_$eq(final AnyAccumulator x$1) {
         this.result = x$1;
      }

      private void writeObject(final ObjectOutputStream out) {
         out.defaultWriteObject();
         AnyAccumulator var10000 = this.acc();
         if (var10000 == null) {
            throw null;
         } else {
            Accumulator sizeLong_this = var10000;
            long var11 = sizeLong_this.totalSize();
            sizeLong_this = null;
            long size = var11;
            out.writeLong(size);
            AnyAccumulator var12 = this.acc();
            StepperShape$ anyStepperShape_this = StepperShape$.MODULE$;
            StepperShape var10001 = anyStepperShape_this.anyStepperShapePrototype();
            Object var8 = null;
            StepperShape stepper_shape = var10001;
            if (var12 == null) {
               throw null;
            } else {
               Stepper var13 = ((Accumulator)var12).efficientStepper(stepper_shape);
               stepper_shape = null;
               AnyStepper st = (AnyStepper)var13;

               while(st.hasStep()) {
                  out.writeObject(st.nextStep());
               }

            }
         }
      }

      private void readObject(final ObjectInputStream in) {
         in.defaultReadObject();
         AnyAccumulator res = new AnyAccumulator();

         for(long elems = in.readLong(); elems > 0L; --elems) {
            Object $plus$eq_elem = in.readObject();
            res.addOne($plus$eq_elem);
            $plus$eq_elem = null;
         }

         this.result_$eq(res);
      }

      private Object readResolve() {
         return this.result();
      }

      public SerializationProxy(final AnyAccumulator acc) {
         this.acc = acc;
      }
   }
}
