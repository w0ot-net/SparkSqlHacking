package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedLinearSeqOps;
import scala.collection.View;
import scala.collection.generic.CommonErrors$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.ListBuffer$;
import scala.math.Integral;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r}b!\u0002\u001a4\u0003CQ\u0004\"\u00023\u0001\t\u0003)\u0007\"\u00024\u0001\t\u0003:\u0007\"B6\u0001\t\u0003a\u0007\"B;\u0001\t\u00031\b\"B?\u0001\t\u0003q\bbBA\u0005\u0001\u0011\u0015\u00131\u0002\u0005\b\u0003'\u0001A\u0011IA\u000b\u0011\u001d\t\t\u0003\u0001C!\u0003GAq!!\u000e\u0001\t\u0003\n9\u0004C\u0004\u0002H\u0001!\t%!\u0013\t\u000f\u0005U\u0003\u0001\"\u0011\u0002X!9\u0011\u0011\r\u0001\u0005B\u0005\r\u0004bBA4\u0001\u0011\u0005\u0013\u0011\u000e\u0005\b\u0003g\u0002A\u0011IA;\u0011\u001d\t)\t\u0001C#\u0003\u000fCq!a'\u0001\t\u000b\ni\nC\u0004\u00022\u0002!)%a-\t\u000f\u0005\r\u0007\u0001\"\u0012\u0002F\"9\u0011Q\u001b\u0001\u0005F\u0005]\u0007bBAo\u0001\u0011\u0015\u0013q\u001c\u0005\b\u0003k\u0004AQIA|\u0011\u001d\tI\u0010\u0001C#\u0003wDqAa\u0005\u0001\t\u000b\u0012)\u0002C\u0004\u0003\u0018\u0001!)E!\u0007\t\u000f\t}\u0001\u0001\"\u0012\u0003\"!9!Q\u0005\u0001\u0005F\t\u001d\u0002b\u0002B\u0016\u0001\u0011\u0015#Q\u0006\u0005\b\u0005s\u0001AQ\tB\u001e\u0011\u001d\u0011)\u0005\u0001C!\u0005\u000fBqA!\u0013\u0001\t\u0003\u0012Y\u0005\u0003\u0005\u0003d\u0001\u0001K\u0011\u000bB3\u0011\u001d\u00119\b\u0001C\u0003\u0005sBqA!%\u0001\t\u0003\u0012\u0019\nC\u0004\u0003\u0018\u0002!\tE!'\t\u0011\tu\u0005\u0001)C\u0005\u0005?CqAa*\u0001\t\u0003\u0012I\u000bC\u0004\u0003.\u0002!)%a>\t\u000f\t=\u0006\u0001\"\u0011\u00032\u001e9!QZ\u001a\t\u0002\t=gA\u0002\u001a4\u0011\u0003\u0011\t\u000e\u0003\u0004eQ\u0011\u0005!\u0011\u001c\u0005\n\u00057D#\u0019!C\u0005\u0005;D\u0001B!:)A\u0003%!q\u001c\u0005\b\u00037BC\u0011\u0001Bt\u0011\u001d\u00119\u0010\u000bC\u0001\u0005sDqaa\u0004)\t\u0003\u0019\t\u0002\u0003\u0006\u0004\u001c!\u0012\r\u0011\"\u00016\u0007;A\u0001b!\n)A\u0003%1q\u0004\u0005\n\u0007cA\u0013\u0011!C\u0005\u0007g\u0011A\u0001T5ti*\u0011A'N\u0001\nS6lW\u000f^1cY\u0016T!AN\u001c\u0002\u0015\r|G\u000e\\3di&|gNC\u00019\u0003\u0015\u00198-\u00197b\u0007\u0001)\"a\u000f\"\u0014\u0011\u0001aDj\u0014+Y7z\u00032!\u0010 A\u001b\u0005\u0019\u0014BA 4\u0005-\t%m\u001d;sC\u000e$8+Z9\u0011\u0005\u0005\u0013E\u0002\u0001\u0003\u0007\u0007\u0002!)\u0019\u0001#\u0003\u0003\u0005\u000b\"!R%\u0011\u0005\u0019;U\"A\u001c\n\u0005!;$a\u0002(pi\"Lgn\u001a\t\u0003\r*K!aS\u001c\u0003\u0007\u0005s\u0017\u0010E\u0002>\u001b\u0002K!AT\u001a\u0003\u00131Kg.Z1s'\u0016\f\b#B\u001fQ\u0001J\u001b\u0016BA)4\u00051a\u0015N\\3beN+\u0017o\u00149t!\ti\u0004\u0001E\u0002>\u0001\u0001\u0003R!\u0016,A%Nk\u0011!N\u0005\u0003/V\u00121d\u0015;sS\u000e$x\n\u001d;j[&TX\r\u001a'j]\u0016\f'oU3r\u001fB\u001c\b#B\u001fZ\u0001J\u001b\u0016B\u0001.4\u0005U\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9PaN\u0004B!\u0016/A%&\u0011Q,\u000e\u0002\u0018\u0013R,'/\u00192mK\u001a\u000b7\r^8ss\u0012+g-Y;miN\u0004\"a\u00182\u000e\u0003\u0001T!!Y\u001b\u0002\u000f\u001d,g.\u001a:jG&\u00111\r\u0019\u0002\u0014\t\u00164\u0017-\u001e7u'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\u000bq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0002QB\u0019Q+\u001b*\n\u0005),$AC*fc\u001a\u000b7\r^8ss\u0006aAeY8m_:$3m\u001c7p]V\u0011Q\u000e\u001d\u000b\u0003]N\u00042!\u0010\u0001p!\t\t\u0005\u000fB\u0003r\u0007\t\u0007!OA\u0001C#\t\u0001\u0015\nC\u0003u\u0007\u0001\u0007q.\u0001\u0003fY\u0016l\u0017A\u0005\u0013d_2|g\u000eJ2pY>tGeY8m_:,\"a\u001e>\u0015\u0005a\\\bcA\u001f\u0001sB\u0011\u0011I\u001f\u0003\u0006c\u0012\u0011\rA\u001d\u0005\u0006y\u0012\u0001\r\u0001_\u0001\u0007aJ,g-\u001b=\u00025I,g/\u001a:tK~#3m\u001c7p]\u0012\u001aw\u000e\\8oI\r|Gn\u001c8\u0016\u0007}\f)\u0001\u0006\u0003\u0002\u0002\u0005\u001d\u0001\u0003B\u001f\u0001\u0003\u0007\u00012!QA\u0003\t\u0015\tXA1\u0001s\u0011\u0019aX\u00011\u0001\u0002\u0002\u00059\u0011n]#naRLXCAA\u0007!\r1\u0015qB\u0005\u0004\u0003#9$a\u0002\"p_2,\u0017M\\\u0001\naJ,\u0007/\u001a8eK\u0012,B!a\u0006\u0002\u001eQ!\u0011\u0011DA\u0010!\u0011i\u0004!a\u0007\u0011\u0007\u0005\u000bi\u0002B\u0003r\u000f\t\u0007!\u000f\u0003\u0004u\u000f\u0001\u0007\u00111D\u0001\raJ,\u0007/\u001a8eK\u0012\fE\u000e\\\u000b\u0005\u0003K\tY\u0003\u0006\u0003\u0002(\u00055\u0002\u0003B\u001f\u0001\u0003S\u00012!QA\u0016\t\u0015\t\bB1\u0001s\u0011\u0019a\b\u00021\u0001\u00020A)Q+!\r\u0002*%\u0019\u00111G\u001b\u0003\u0019%#XM]1cY\u0016|enY3\u0002\u0017\u0005\u0004\b/\u001a8eK\u0012\fE\u000e\\\u000b\u0005\u0003s\ty\u0004\u0006\u0003\u0002<\u0005\u0005\u0003\u0003B\u001f\u0001\u0003{\u00012!QA \t\u0015\t\u0018B1\u0001s\u0011\u001d\t\u0019%\u0003a\u0001\u0003\u000b\naa];gM&D\b#B+\u00022\u0005u\u0012\u0001\u0002;bW\u0016$2aUA&\u0011\u001d\tiE\u0003a\u0001\u0003\u001f\n\u0011A\u001c\t\u0004\r\u0006E\u0013bAA*o\t\u0019\u0011J\u001c;\u0002\u000bMd\u0017nY3\u0015\u000bM\u000bI&!\u0018\t\u000f\u0005m3\u00021\u0001\u0002P\u0005!aM]8n\u0011\u001d\tyf\u0003a\u0001\u0003\u001f\nQ!\u001e8uS2\f\u0011\u0002^1lKJKw\r\u001b;\u0015\u0007M\u000b)\u0007C\u0004\u0002N1\u0001\r!a\u0014\u0002\u000fM\u0004H.\u001b;BiR!\u00111NA9!\u00151\u0015QN*T\u0013\r\tyg\u000e\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\u00055S\u00021\u0001\u0002P\u00059Q\u000f\u001d3bi\u0016$W\u0003BA<\u0003{\"b!!\u001f\u0002\u0000\u0005\r\u0005\u0003B\u001f\u0001\u0003w\u00022!QA?\t\u0015\thB1\u0001s\u0011\u001d\t\tI\u0004a\u0001\u0003\u001f\nQ!\u001b8eKbDa\u0001\u001e\bA\u0002\u0005m\u0014aA7baV!\u0011\u0011RAH)\u0011\tY)!%\u0011\tu\u0002\u0011Q\u0012\t\u0004\u0003\u0006=E!B9\u0010\u0005\u0004!\u0005bBAJ\u001f\u0001\u0007\u0011QS\u0001\u0002MB1a)a&A\u0003\u001bK1!!'8\u0005%1UO\\2uS>t\u0017'A\u0004d_2dWm\u0019;\u0016\t\u0005}\u0015Q\u0015\u000b\u0005\u0003C\u000b9\u000b\u0005\u0003>\u0001\u0005\r\u0006cA!\u0002&\u0012)\u0011\u000f\u0005b\u0001\t\"9\u0011\u0011\u0016\tA\u0002\u0005-\u0016A\u00019g!\u00191\u0015Q\u0016!\u0002$&\u0019\u0011qV\u001c\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\fqA\u001a7bi6\u000b\u0007/\u0006\u0003\u00026\u0006mF\u0003BA\\\u0003{\u0003B!\u0010\u0001\u0002:B\u0019\u0011)a/\u0005\u000bE\f\"\u0019\u0001#\t\u000f\u0005M\u0015\u00031\u0001\u0002@B1a)a&A\u0003\u0003\u0004R!VA\u0019\u0003s\u000b\u0011\u0002^1lK^C\u0017\u000e\\3\u0015\u0007M\u000b9\rC\u0004\u0002JJ\u0001\r!a3\u0002\u0003A\u0004bARAL\u0001\u00065\u0001f\u0001\n\u0002PB\u0019a)!5\n\u0007\u0005MwG\u0001\u0004j]2Lg.Z\u0001\u0005gB\fg\u000e\u0006\u0003\u0002l\u0005e\u0007bBAe'\u0001\u0007\u00111\u001a\u0015\u0004'\u0005=\u0017a\u00024pe\u0016\f7\r[\u000b\u0005\u0003C\fy\u000f\u0006\u0003\u0002d\u0006%\bc\u0001$\u0002f&\u0019\u0011q]\u001c\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003'#\u0002\u0019AAv!\u00191\u0015q\u0013!\u0002nB\u0019\u0011)a<\u0005\r\u0005EHC1\u0001E\u0005\u0005)\u0006f\u0001\u000b\u0002P\u00069!/\u001a<feN,W#A*\u0002\u0013\u0019|G\u000e\u001a*jO\"$X\u0003BA\u007f\u0005\u0007!B!a@\u0003\u0010Q!!\u0011\u0001B\u0003!\r\t%1\u0001\u0003\u0006cZ\u0011\r\u0001\u0012\u0005\b\u0005\u000f1\u0002\u0019\u0001B\u0005\u0003\ty\u0007\u000f\u0005\u0005G\u0005\u0017\u0001%\u0011\u0001B\u0001\u0013\r\u0011ia\u000e\u0002\n\rVt7\r^5p]JBqA!\u0005\u0017\u0001\u0004\u0011\t!A\u0001{\u0003\u0019aWM\\4uQV\u0011\u0011qJ\u0001\u000eY\u0016tw\r\u001e5D_6\u0004\u0018M]3\u0015\t\u0005=#1\u0004\u0005\b\u0005;A\u0002\u0019AA(\u0003\raWM\\\u0001\u0007M>\u0014\u0018\r\u001c7\u0015\t\u00055!1\u0005\u0005\b\u0003\u0013L\u0002\u0019AAf\u0003\u0019)\u00070[:ugR!\u0011Q\u0002B\u0015\u0011\u001d\tIM\u0007a\u0001\u0003\u0017\f\u0001bY8oi\u0006Lgn]\u000b\u0005\u0005_\u0011)\u0004\u0006\u0003\u0002\u000e\tE\u0002B\u0002;\u001c\u0001\u0004\u0011\u0019\u0004E\u0002B\u0005k!aAa\u000e\u001c\u0005\u0004\u0011(AA!2\u0003\u00111\u0017N\u001c3\u0015\t\tu\"1\t\t\u0005\r\n}\u0002)C\u0002\u0003B]\u0012aa\u00149uS>t\u0007bBAe9\u0001\u0007\u00111Z\u0001\u0005Y\u0006\u001cH/F\u0001A\u0003-\u0019wN\u001d:fgB|g\u000eZ:\u0016\t\t5#q\u000b\u000b\u0005\u0005\u001f\u0012I\u0006\u0006\u0003\u0002\u000e\tE\u0003bBAe=\u0001\u0007!1\u000b\t\t\r\n-\u0001I!\u0016\u0002\u000eA\u0019\u0011Ia\u0016\u0005\u000bEt\"\u0019\u0001#\t\u000f\tmc\u00041\u0001\u0003^\u0005!A\u000f[1u!\u0015)&q\fB+\u0013\r\u0011\t'\u000e\u0002\u0004'\u0016\f\u0018!C2mCN\u001ch*Y7f+\t\u00119\u0007\u0005\u0003\u0003j\tMTB\u0001B6\u0015\u0011\u0011iGa\u001c\u0002\t1\fgn\u001a\u0006\u0003\u0005c\nAA[1wC&!!Q\u000fB6\u0005\u0019\u0019FO]5oO\u0006YQ.\u00199D_:\u001cXM\u001d<f+\u0011\u0011YH!!\u0015\t\tu$1\u0012\t\u0005{\u0001\u0011y\bE\u0002B\u0005\u0003#a!\u001d\u0011C\u0002\t\r\u0015c\u0001!\u0003\u0006B\u0019aIa\"\n\u0007\t%uG\u0001\u0004B]f\u0014VM\u001a\u0005\b\u0003'\u0003\u0003\u0019\u0001BG!\u00191\u0015q\u0013!\u0003\u0000!\u001a\u0001%a4\u0002\r\u0019LG\u000e^3s)\r\u0019&Q\u0013\u0005\b\u0003\u0013\f\u0003\u0019AAf\u0003%1\u0017\u000e\u001c;fe:{G\u000fF\u0002T\u00057Cq!!3#\u0001\u0004\tY-\u0001\u0007gS2$XM]\"p[6|g\u000eF\u0003T\u0005C\u0013\u0019\u000bC\u0004\u0002J\u000e\u0002\r!a3\t\u000f\t\u00156\u00051\u0001\u0002\u000e\u0005I\u0011n\u001d$mSB\u0004X\rZ\u0001\na\u0006\u0014H/\u001b;j_:$B!a\u001b\u0003,\"9\u0011\u0011\u001a\u0013A\u0002\u0005-\u0017A\u0002;p\u0019&\u001cH/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u001b\u0011\u0019\f\u0003\u0004\u00036\u001a\u0002\r!S\u0001\u0002_&*\u0001A!/\u0003>&\u0019!1X\u001a\u0003\u0019\u0011\u001aw\u000e\\8oI\r|Gn\u001c8\u000b\u0007\t}6'A\u0002OS2Ds\u0001\u0001Bb\u0005\u0013\u0014Y\rE\u0002G\u0005\u000bL1Aa28\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004\u0003\u0011a\u0015n\u001d;\u0011\u0005uB3#\u0002\u0015\u0003\u0006\nM\u0007\u0003B+\u0003VJK1Aa66\u0005e\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9GC\u000e$xN]=\u0015\u0005\t=\u0017A\u0003+va2,wJ\u001a(jYV\u0011!q\u001c\t\b\r\u00065$\u0011\u001dBq\u001d\ri$1]\u0005\u0004\u0005\u007f\u001b\u0014a\u0003+va2,wJ\u001a(jY\u0002*BA!;\u0003pR!!1\u001eBy!\u0011i\u0004A!<\u0011\u0007\u0005\u0013y\u000fB\u0003rY\t\u0007A\tC\u0004\u0003t2\u0002\rA!>\u0002\t\r|G\u000e\u001c\t\u0006+\u0006E\"Q^\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003\u0002B~\u0007\u0017)\"A!@\u0011\u0011\t}8QAB\u0005\u0007\u001bi!a!\u0001\u000b\u0007\r\rQ'A\u0004nkR\f'\r\\3\n\t\r\u001d1\u0011\u0001\u0002\b\u0005VLG\u000eZ3s!\r\t51\u0002\u0003\u0006\u00076\u0012\r\u0001\u0012\t\u0005{\u0001\u0019I!A\u0003f[B$\u00180\u0006\u0003\u0004\u0014\reQCAB\u000b!\u0011i\u0004aa\u0006\u0011\u0007\u0005\u001bI\u0002B\u0003D]\t\u0007A)A\tqCJ$\u0018.\u00197O_R\f\u0005\u000f\u001d7jK\u0012,\"aa\b\u0013\r\r\u0005\"QQB\u0018\r\u0019\u0019\u0019\u0003\r\u0001\u0004 \taAH]3gS:,W.\u001a8u}\u0005\u0011\u0002/\u0019:uS\u0006dgj\u001c;BaBd\u0017.\u001a3!Q\r\u00014\u0011\u0006\t\u0004\r\u000e-\u0012bAB\u0017o\tIAO]1og&,g\u000e\u001e\t\u0006\r\u0006]\u0015*S\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0007k\u0001BA!\u001b\u00048%!1\u0011\bB6\u0005\u0019y%M[3di\":\u0001Fa1\u0003J\n-\u0007fB\u0014\u0003D\n%'1\u001a"
)
public abstract class List extends AbstractSeq implements LinearSeq, StrictOptimizedLinearSeqOps, StrictOptimizedSeqOps, DefaultSerializable {
   private static final long serialVersionUID = 3L;

   public static Builder newBuilder() {
      List$ var10000 = List$.MODULE$;
      return new ListBuffer();
   }

   public static List from(final IterableOnce coll) {
      List$ var10000 = List$.MODULE$;
      return Nil$.MODULE$.prependedAll(coll);
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      List$ var10000 = List$.MODULE$;
      Builder tabulate_b = new ListBuffer();
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem) {
      List$ var10000 = List$.MODULE$;
      Builder fill_b = new ListBuffer();
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      List$ var10000 = List$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      List$ var10000 = List$.MODULE$;
      Builder tabulate_b = new ListBuffer();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new ListBuffer();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new ListBuffer();
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new ListBuffer();
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Builder tabulate_b = new ListBuffer();
                  tabulate_b.sizeHint(n5);

                  for(int tabulate_i = 0; tabulate_i < n5; ++tabulate_i) {
                     Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                     tabulate_b.addOne(tabulate_$plus$eq_elem);
                     tabulate_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var34 = (scala.collection.SeqOps)tabulate_b.result();
                  tabulate_b = null;
                  Object var33 = null;
                  Object tabulate_$plus$eq_elem = var34;
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var30 = null;
               Object tabulate_$plus$eq_elem = var35;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var27 = null;
            Object tabulate_$plus$eq_elem = var36;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var24 = null;
         Object tabulate_$plus$eq_elem = var37;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      List$ var10000 = List$.MODULE$;
      Builder tabulate_b = new ListBuffer();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new ListBuffer();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new ListBuffer();
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new ListBuffer();
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var27 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var26 = null;
               Object tabulate_$plus$eq_elem = var27;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var23 = null;
            Object tabulate_$plus$eq_elem = var28;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var20 = null;
         Object tabulate_$plus$eq_elem = var29;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      List$ var10000 = List$.MODULE$;
      Builder tabulate_b = new ListBuffer();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new ListBuffer();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new ListBuffer();
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var20 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var19 = null;
            Object tabulate_$plus$eq_elem = var20;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var16 = null;
         Object tabulate_$plus$eq_elem = var21;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      List$ var10000 = List$.MODULE$;
      Builder tabulate_b = new ListBuffer();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new ListBuffer();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i);
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var13 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var12 = null;
         Object tabulate_$plus$eq_elem = var13;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      List$ var10000 = List$.MODULE$;
      Builder fill_b = new ListBuffer();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new ListBuffer();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new ListBuffer();
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new ListBuffer();
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Builder fill_b = new ListBuffer();
                  fill_b.sizeHint(n5);

                  for(int fill_i = 0; fill_i < n5; ++fill_i) {
                     Object fill_$plus$eq_elem = elem.apply();
                     fill_b.addOne(fill_$plus$eq_elem);
                     fill_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var34 = (scala.collection.SeqOps)fill_b.result();
                  fill_b = null;
                  Object var33 = null;
                  Object fill_$plus$eq_elem = var34;
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var30 = null;
               Object fill_$plus$eq_elem = var35;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var27 = null;
            Object fill_$plus$eq_elem = var36;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var24 = null;
         Object fill_$plus$eq_elem = var37;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      List$ var10000 = List$.MODULE$;
      Builder fill_b = new ListBuffer();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new ListBuffer();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new ListBuffer();
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new ListBuffer();
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Object fill_$plus$eq_elem = elem.apply();
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var27 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var26 = null;
               Object fill_$plus$eq_elem = var27;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var23 = null;
            Object fill_$plus$eq_elem = var28;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var20 = null;
         Object fill_$plus$eq_elem = var29;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      List$ var10000 = List$.MODULE$;
      Builder fill_b = new ListBuffer();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new ListBuffer();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new ListBuffer();
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_$plus$eq_elem = elem.apply();
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var20 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var19 = null;
            Object fill_$plus$eq_elem = var20;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var16 = null;
         Object fill_$plus$eq_elem = var21;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      List$ var10000 = List$.MODULE$;
      Builder fill_b = new ListBuffer();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new ListBuffer();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_$plus$eq_elem = elem.apply();
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var13 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var12 = null;
         Object fill_$plus$eq_elem = var13;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(List$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(List$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      List$ var10000 = List$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return Nil$.MODULE$.prependedAll(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      List$ var10000 = List$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return Nil$.MODULE$.prependedAll(from_source);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(final Ordering ord) {
      return scala.collection.SeqOps.sorted$(this, ord);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return StrictOptimizedSeqOps.patch$(this, from, other, replaced);
   }

   public Object sorted(final Ordering ord) {
      return StrictOptimizedSeqOps.sorted$(this, ord);
   }

   public Iterator iterator() {
      return StrictOptimizedLinearSeqOps.iterator$(this);
   }

   public scala.collection.LinearSeq drop(final int n) {
      return StrictOptimizedLinearSeqOps.drop$(this, n);
   }

   public scala.collection.LinearSeq dropWhile(final Function1 p) {
      return StrictOptimizedLinearSeqOps.dropWhile$(this, p);
   }

   public Object appended(final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object padTo(final int len, final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.intersect$(this, that);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
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

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public String stringPrefix() {
      return scala.collection.LinearSeq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$LinearSeqOps$$super$sameElements(final IterableOnce that) {
      return scala.collection.SeqOps.sameElements$(this, that);
   }

   public Option headOption() {
      return scala.collection.LinearSeqOps.headOption$(this);
   }

   public int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.LinearSeqOps.lengthCompare$(this, that);
   }

   public boolean isDefinedAt(final int x) {
      return scala.collection.LinearSeqOps.isDefinedAt$(this, x);
   }

   public Object apply(final int n) throws IndexOutOfBoundsException {
      return scala.collection.LinearSeqOps.apply$(this, n);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return scala.collection.LinearSeqOps.foldLeft$(this, z, op);
   }

   public boolean sameElements(final IterableOnce that) {
      return scala.collection.LinearSeqOps.sameElements$(this, that);
   }

   public int segmentLength(final Function1 p, final int from) {
      return scala.collection.LinearSeqOps.segmentLength$(this, p, from);
   }

   public int indexWhere(final Function1 p, final int from) {
      return scala.collection.LinearSeqOps.indexWhere$(this, p, from);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return scala.collection.LinearSeqOps.lastIndexWhere$(this, p, end);
   }

   public Option findLast(final Function1 p) {
      return scala.collection.LinearSeqOps.findLast$(this, p);
   }

   public Iterator tails() {
      return scala.collection.LinearSeqOps.tails$(this);
   }

   public SeqFactory iterableFactory() {
      return List$.MODULE$;
   }

   public List $colon$colon(final Object elem) {
      return new $colon$colon(elem, this);
   }

   public List $colon$colon$colon(final List prefix) {
      if (this.isEmpty()) {
         return prefix;
      } else if (prefix.isEmpty()) {
         return this;
      } else {
         $colon$colon result = new $colon$colon(prefix.head(), this);
         $colon$colon curr = result;

         for(List that = (List)prefix.tail(); !that.isEmpty(); that = (List)that.tail()) {
            $colon$colon temp = new $colon$colon(that.head(), this);
            curr.next_$eq(temp);
            curr = temp;
         }

         Statics.releaseFence();
         return result;
      }
   }

   public List reverse_$colon$colon$colon(final List prefix) {
      List these = this;

      for(List pres = prefix; !pres.isEmpty(); pres = (List)pres.tail()) {
         Object var4 = pres.head();
         these = new $colon$colon(var4, these);
      }

      return these;
   }

   public final boolean isEmpty() {
      return this == Nil$.MODULE$;
   }

   public List prepended(final Object elem) {
      return new $colon$colon(elem, this);
   }

   public List prependedAll(final IterableOnce prefix) {
      if (prefix instanceof List) {
         List var2 = (List)prefix;
         return this.$colon$colon$colon(var2);
      } else if (prefix.knownSize() == 0) {
         return this;
      } else {
         if (prefix instanceof ListBuffer) {
            ListBuffer var3 = (ListBuffer)prefix;
            if (this.isEmpty()) {
               return var3.toList();
            }
         }

         Iterator iter = prefix.iterator();
         if (!iter.hasNext()) {
            return this;
         } else {
            $colon$colon result = new $colon$colon(iter.next(), this);

            $colon$colon temp;
            for($colon$colon curr = result; iter.hasNext(); curr = temp) {
               temp = new $colon$colon(iter.next(), this);
               curr.next_$eq(temp);
            }

            Statics.releaseFence();
            return result;
         }
      }
   }

   public List appendedAll(final IterableOnce suffix) {
      return suffix instanceof List ? ((List)suffix).$colon$colon$colon(this) : (List)scala.collection.StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public List take(final int n) {
      if (!this.isEmpty() && n > 0) {
         $colon$colon h = new $colon$colon(this.head(), Nil$.MODULE$);
         $colon$colon t = h;
         List rest = (List)this.tail();

         for(int i = 1; !rest.isEmpty(); rest = (List)rest.tail()) {
            if (i >= n) {
               Statics.releaseFence();
               return h;
            }

            ++i;
            $colon$colon nx = new $colon$colon(rest.head(), Nil$.MODULE$);
            t.next_$eq(nx);
            t = nx;
         }

         return this;
      } else {
         return Nil$.MODULE$;
      }
   }

   public List slice(final int from, final int until) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int lo = Math.max(from, max_y);
      return (List)(until > lo && !this.isEmpty() ? ((List)StrictOptimizedLinearSeqOps.drop$(this, lo)).take(until - lo) : Nil$.MODULE$);
   }

   public List takeRight(final int n) {
      return this.loop$1((List)StrictOptimizedLinearSeqOps.drop$(this, n), this);
   }

   public Tuple2 splitAt(final int n) {
      ListBuffer b = new ListBuffer();
      int i = 0;

      List these;
      for(these = this; !these.isEmpty() && i < n; these = (List)these.tail()) {
         ++i;
         Object $plus$eq_elem = these.head();
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return new Tuple2(b.toList(), these);
   }

   public List updated(final int index, final Object elem) {
      int i = 0;
      List current = this;
      ListBuffer$ var10000 = ListBuffer$.MODULE$;

      ListBuffer prefix;
      for(prefix = new ListBuffer(); i < index && current.nonEmpty(); current = (List)current.tail()) {
         ++i;
         Object $plus$eq_elem = current.head();
         prefix.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      if (i == index && current.nonEmpty()) {
         List var10001 = (List)current.tail();
         if (var10001 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10001;
            $colon$colon var10 = new $colon$colon(elem, $colon$colon_this);
            $colon$colon_this = null;
            return prefix.prependToList(var10);
         }
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index, this.length() - 1);
      }
   }

   public final List map(final Function1 f) {
      if (this == Nil$.MODULE$) {
         return Nil$.MODULE$;
      } else {
         $colon$colon h = new $colon$colon(f.apply(this.head()), Nil$.MODULE$);
         $colon$colon t = h;

         for(List rest = (List)this.tail(); rest != Nil$.MODULE$; rest = (List)rest.tail()) {
            $colon$colon nx = new $colon$colon(f.apply(rest.head()), Nil$.MODULE$);
            t.next_$eq(nx);
            t = nx;
         }

         Statics.releaseFence();
         return h;
      }
   }

   public final List collect(final PartialFunction pf) {
      if (this == Nil$.MODULE$) {
         return Nil$.MODULE$;
      } else {
         List rest = this;
         $colon$colon h = null;

         while(h == null) {
            Object x = pf.applyOrElse(rest.head(), List$.MODULE$.partialNotApplied());
            if (x != List$.MODULE$.partialNotApplied()) {
               h = new $colon$colon(x, Nil$.MODULE$);
            }

            rest = (List)rest.tail();
            if (rest == Nil$.MODULE$) {
               if (h == null) {
                  return Nil$.MODULE$;
               }

               return h;
            }
         }

         for($colon$colon t = h; rest != Nil$.MODULE$; rest = (List)rest.tail()) {
            Object var7 = pf.applyOrElse(rest.head(), List$.MODULE$.partialNotApplied());
            if (var7 != List$.MODULE$.partialNotApplied()) {
               $colon$colon nx = new $colon$colon(var7, Nil$.MODULE$);
               t.next_$eq(nx);
               t = nx;
            }
         }

         Statics.releaseFence();
         return h;
      }
   }

   public final List flatMap(final Function1 f) {
      List rest = this;
      $colon$colon h = null;

      $colon$colon nx;
      for($colon$colon t = null; rest != Nil$.MODULE$; rest = (List)rest.tail()) {
         for(Iterator it = ((IterableOnce)f.apply(rest.head())).iterator(); it.hasNext(); t = nx) {
            nx = new $colon$colon(it.next(), Nil$.MODULE$);
            if (t == null) {
               h = nx;
            } else {
               t.next_$eq(nx);
            }
         }
      }

      if (h == null) {
         return Nil$.MODULE$;
      } else {
         Statics.releaseFence();
         return h;
      }
   }

   public final List takeWhile(final Function1 p) {
      ListBuffer b = new ListBuffer();

      for(List these = this; !these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head())); these = (List)these.tail()) {
         Object $plus$eq_elem = these.head();
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return b.toList();
   }

   public final Tuple2 span(final Function1 p) {
      ListBuffer b = new ListBuffer();

      List these;
      for(these = this; !these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head())); these = (List)these.tail()) {
         Object $plus$eq_elem = these.head();
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return new Tuple2(b.toList(), these);
   }

   public final void foreach(final Function1 f) {
      for(List these = this; !these.isEmpty(); these = (List)these.tail()) {
         f.apply(these.head());
      }

   }

   public final List reverse() {
      List result = Nil$.MODULE$;

      for(List these = this; !these.isEmpty(); these = (List)these.tail()) {
         Object var3 = these.head();
         result = result.$colon$colon(var3);
      }

      return result;
   }

   public final Object foldRight(final Object z, final Function2 op) {
      Object acc = z;

      for(List these = this.reverse(); !these.isEmpty(); these = (List)these.tail()) {
         acc = op.apply(these.head(), acc);
      }

      return acc;
   }

   public final int length() {
      List these = this;

      int len;
      for(len = 0; !these.isEmpty(); these = (List)these.tail()) {
         ++len;
      }

      return len;
   }

   public final int lengthCompare(final int len) {
      return len < 0 ? 1 : this.loop$2(0, this, len);
   }

   public final boolean forall(final Function1 p) {
      for(List these = this; !these.isEmpty(); these = (List)these.tail()) {
         if (!BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            return false;
         }
      }

      return true;
   }

   public final boolean exists(final Function1 p) {
      for(List these = this; !these.isEmpty(); these = (List)these.tail()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            return true;
         }
      }

      return false;
   }

   public final boolean contains(final Object elem) {
      for(List these = this; !these.isEmpty(); these = (List)these.tail()) {
         if (BoxesRunTime.equals(these.head(), elem)) {
            return true;
         }
      }

      return false;
   }

   public final Option find(final Function1 p) {
      for(List these = this; !these.isEmpty(); these = (List)these.tail()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            return new Some(these.head());
         }
      }

      return None$.MODULE$;
   }

   public Object last() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("List.last");
      } else {
         List these = this;

         for(List scout = (List)this.tail(); !scout.isEmpty(); scout = (List)scout.tail()) {
            these = scout;
         }

         return these.head();
      }
   }

   public boolean corresponds(final scala.collection.Seq that, final Function2 p) {
      if (that instanceof LinearSeq) {
         LinearSeq var3 = (LinearSeq)that;
         List i = this;

         LinearSeq j;
         for(j = var3; !i.isEmpty() && !j.isEmpty(); j = (LinearSeq)j.tail()) {
            if (!BoxesRunTime.unboxToBoolean(p.apply(i.head(), j.head()))) {
               return false;
            }

            i = (List)i.tail();
         }

         return i.isEmpty() && j.isEmpty();
      } else {
         Iterator corresponds_i = this.iterator();
         Iterator corresponds_j = that.iterator();

         while(corresponds_i.hasNext() && corresponds_j.hasNext()) {
            if (!BoxesRunTime.unboxToBoolean(p.apply(corresponds_i.next(), corresponds_j.next()))) {
               return false;
            }
         }

         return !corresponds_i.hasNext() && !corresponds_j.hasNext();
      }
   }

   public String className() {
      return "List";
   }

   public final List mapConserve(final Function1 f) {
      List loop$3_pending = this;
      List loop$3_unchanged = this;
      $colon$colon loop$3_mappedLast = null;
      List loop$3_mappedHead = null;

      while(!loop$3_pending.isEmpty()) {
         Object loop$3_head0 = loop$3_pending.head();
         Object loop$3_head1 = f.apply(loop$3_head0);
         if (loop$3_head1 == loop$3_head0) {
            loop$3_pending = (List)loop$3_pending.tail();
            loop$3_unchanged = loop$3_unchanged;
            loop$3_mappedLast = loop$3_mappedLast;
            loop$3_mappedHead = loop$3_mappedHead;
         } else {
            List loop$3_xc = loop$3_unchanged;
            List loop$3_mappedHead1 = loop$3_mappedHead;

            $colon$colon loop$3_mappedLast1;
            for(loop$3_mappedLast1 = loop$3_mappedLast; loop$3_xc != loop$3_pending; loop$3_xc = (List)loop$3_xc.tail()) {
               $colon$colon loop$3_next = new $colon$colon(loop$3_xc.head(), Nil$.MODULE$);
               if (loop$3_mappedHead1 == null) {
                  loop$3_mappedHead1 = loop$3_next;
               }

               if (loop$3_mappedLast1 != null) {
                  loop$3_mappedLast1.next_$eq(loop$3_next);
               }

               loop$3_mappedLast1 = loop$3_next;
            }

            $colon$colon loop$3_next = new $colon$colon(loop$3_head1, Nil$.MODULE$);
            if (loop$3_mappedHead1 == null) {
               loop$3_mappedHead1 = loop$3_next;
            }

            if (loop$3_mappedLast1 != null) {
               loop$3_mappedLast1.next_$eq(loop$3_next);
            }

            List loop$3_tail0 = (List)loop$3_pending.tail();
            loop$3_pending = loop$3_tail0;
            loop$3_unchanged = loop$3_tail0;
            loop$3_mappedLast = loop$3_next;
            loop$3_mappedHead = loop$3_mappedHead1;
         }
      }

      Object var10000;
      if (loop$3_mappedHead == null) {
         var10000 = loop$3_unchanged;
      } else {
         loop$3_mappedLast.next_$eq(loop$3_unchanged);
         var10000 = loop$3_mappedHead;
      }

      loop$3_mappedHead = null;
      Object var16 = null;
      loop$3_unchanged = null;
      Object var18 = null;
      Object var19 = null;
      Object var20 = null;
      Object var21 = null;
      Object var22 = null;
      Object var23 = null;
      Object var24 = null;
      Object var25 = null;
      Object var26 = null;
      List result = (List)var10000;
      Statics.releaseFence();
      return result;
   }

   public List filter(final Function1 p) {
      boolean filterCommon_isFlipped = false;
      List filterCommon_noneIn$1_l = this;

      Object var10000;
      while(true) {
         if (filterCommon_noneIn$1_l.isEmpty()) {
            var10000 = Nil$.MODULE$;
            break;
         }

         Object filterCommon_noneIn$1_h = filterCommon_noneIn$1_l.head();
         List filterCommon_noneIn$1_t = (List)filterCommon_noneIn$1_l.tail();
         if (BoxesRunTime.unboxToBoolean(p.apply(filterCommon_noneIn$1_h)) != filterCommon_isFlipped) {
            List filterCommon_noneIn$1_allIn$1_remaining = filterCommon_noneIn$1_t;

            while(true) {
               if (filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                  var10000 = filterCommon_noneIn$1_l;
                  break;
               }

               Object filterCommon_noneIn$1_allIn$1_x = filterCommon_noneIn$1_allIn$1_remaining.head();
               if (BoxesRunTime.unboxToBoolean(p.apply(filterCommon_noneIn$1_allIn$1_x)) == filterCommon_isFlipped) {
                  $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new $colon$colon(filterCommon_noneIn$1_l.head(), Nil$.MODULE$);
                  List filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterCommon_noneIn$1_l.tail();

                  $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                  for(filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterCommon_noneIn$1_allIn$1_remaining; filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                     $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), Nil$.MODULE$);
                     filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                     filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                  }

                  List filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterCommon_noneIn$1_allIn$1_remaining.tail();
                  List filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                  while(!filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                     Object filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                     if (BoxesRunTime.unboxToBoolean(p.apply(filterCommon_noneIn$1_allIn$1_partialFill$1_head)) != filterCommon_isFlipped) {
                        filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                     } else {
                        while(filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                           $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), Nil$.MODULE$);
                           filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                        }

                        filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                     }
                  }

                  if (!filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                     filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                  }

                  var10000 = filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                  filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = null;
                  Object var27 = null;
                  Object var30 = null;
                  Object var33 = null;
                  Object var36 = null;
                  Object var39 = null;
                  Object var42 = null;
                  Object var45 = null;
                  break;
               }

               filterCommon_noneIn$1_allIn$1_remaining = (List)filterCommon_noneIn$1_allIn$1_remaining.tail();
            }

            Object var20 = null;
            Object var22 = null;
            Object var25 = null;
            Object var28 = null;
            Object var31 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            Object var46 = null;
            break;
         }

         filterCommon_noneIn$1_l = filterCommon_noneIn$1_t;
      }

      Object var17 = null;
      Object var18 = null;
      Object var19 = null;
      Object var21 = null;
      Object var23 = null;
      Object var26 = null;
      Object var29 = null;
      Object var32 = null;
      Object var35 = null;
      Object var38 = null;
      Object var41 = null;
      Object var44 = null;
      Object var47 = null;
      List filterCommon_result = (List)var10000;
      Statics.releaseFence();
      return filterCommon_result;
   }

   public List filterNot(final Function1 p) {
      boolean filterCommon_isFlipped = true;
      List filterCommon_noneIn$1_l = this;

      Object var10000;
      while(true) {
         if (filterCommon_noneIn$1_l.isEmpty()) {
            var10000 = Nil$.MODULE$;
            break;
         }

         Object filterCommon_noneIn$1_h = filterCommon_noneIn$1_l.head();
         List filterCommon_noneIn$1_t = (List)filterCommon_noneIn$1_l.tail();
         if (BoxesRunTime.unboxToBoolean(p.apply(filterCommon_noneIn$1_h)) != filterCommon_isFlipped) {
            List filterCommon_noneIn$1_allIn$1_remaining = filterCommon_noneIn$1_t;

            while(true) {
               if (filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                  var10000 = filterCommon_noneIn$1_l;
                  break;
               }

               Object filterCommon_noneIn$1_allIn$1_x = filterCommon_noneIn$1_allIn$1_remaining.head();
               if (BoxesRunTime.unboxToBoolean(p.apply(filterCommon_noneIn$1_allIn$1_x)) == filterCommon_isFlipped) {
                  $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new $colon$colon(filterCommon_noneIn$1_l.head(), Nil$.MODULE$);
                  List filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterCommon_noneIn$1_l.tail();

                  $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                  for(filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterCommon_noneIn$1_allIn$1_remaining; filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                     $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), Nil$.MODULE$);
                     filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                     filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                  }

                  List filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterCommon_noneIn$1_allIn$1_remaining.tail();
                  List filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                  while(!filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                     Object filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                     if (BoxesRunTime.unboxToBoolean(p.apply(filterCommon_noneIn$1_allIn$1_partialFill$1_head)) != filterCommon_isFlipped) {
                        filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                     } else {
                        while(filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                           $colon$colon filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), Nil$.MODULE$);
                           filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                        }

                        filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                     }
                  }

                  if (!filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                     filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                  }

                  var10000 = filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                  filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = null;
                  Object var27 = null;
                  Object var30 = null;
                  Object var33 = null;
                  Object var36 = null;
                  Object var39 = null;
                  Object var42 = null;
                  Object var45 = null;
                  break;
               }

               filterCommon_noneIn$1_allIn$1_remaining = (List)filterCommon_noneIn$1_allIn$1_remaining.tail();
            }

            Object var20 = null;
            Object var22 = null;
            Object var25 = null;
            Object var28 = null;
            Object var31 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            Object var46 = null;
            break;
         }

         filterCommon_noneIn$1_l = filterCommon_noneIn$1_t;
      }

      Object var17 = null;
      Object var18 = null;
      Object var19 = null;
      Object var21 = null;
      Object var23 = null;
      Object var26 = null;
      Object var29 = null;
      Object var32 = null;
      Object var35 = null;
      Object var38 = null;
      Object var41 = null;
      Object var44 = null;
      Object var47 = null;
      List filterCommon_result = (List)var10000;
      Statics.releaseFence();
      return filterCommon_result;
   }

   private List filterCommon(final Function1 p, final boolean isFlipped) {
      List noneIn$1_l = this;

      Object var10000;
      while(true) {
         if (noneIn$1_l.isEmpty()) {
            var10000 = Nil$.MODULE$;
            break;
         }

         Object noneIn$1_h = noneIn$1_l.head();
         List noneIn$1_t = (List)noneIn$1_l.tail();
         if (BoxesRunTime.unboxToBoolean(p.apply(noneIn$1_h)) != isFlipped) {
            List noneIn$1_allIn$1_remaining = noneIn$1_t;

            while(true) {
               if (noneIn$1_allIn$1_remaining.isEmpty()) {
                  var10000 = noneIn$1_l;
                  break;
               }

               Object noneIn$1_allIn$1_x = noneIn$1_allIn$1_remaining.head();
               if (BoxesRunTime.unboxToBoolean(p.apply(noneIn$1_allIn$1_x)) == isFlipped) {
                  $colon$colon noneIn$1_allIn$1_partialFill$1_newHead = new $colon$colon(noneIn$1_l.head(), Nil$.MODULE$);
                  List noneIn$1_allIn$1_partialFill$1_toProcess = (List)noneIn$1_l.tail();

                  $colon$colon noneIn$1_allIn$1_partialFill$1_currentLast;
                  for(noneIn$1_allIn$1_partialFill$1_currentLast = noneIn$1_allIn$1_partialFill$1_newHead; noneIn$1_allIn$1_partialFill$1_toProcess != noneIn$1_allIn$1_remaining; noneIn$1_allIn$1_partialFill$1_toProcess = (List)noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                     $colon$colon noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(noneIn$1_allIn$1_partialFill$1_toProcess.head(), Nil$.MODULE$);
                     noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(noneIn$1_allIn$1_partialFill$1_newElem);
                     noneIn$1_allIn$1_partialFill$1_currentLast = noneIn$1_allIn$1_partialFill$1_newElem;
                  }

                  List noneIn$1_allIn$1_partialFill$1_next = (List)noneIn$1_allIn$1_remaining.tail();
                  List noneIn$1_allIn$1_partialFill$1_nextToCopy = noneIn$1_allIn$1_partialFill$1_next;

                  while(!noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                     Object noneIn$1_allIn$1_partialFill$1_head = noneIn$1_allIn$1_partialFill$1_next.head();
                     if (BoxesRunTime.unboxToBoolean(p.apply(noneIn$1_allIn$1_partialFill$1_head)) != isFlipped) {
                        noneIn$1_allIn$1_partialFill$1_next = (List)noneIn$1_allIn$1_partialFill$1_next.tail();
                     } else {
                        while(noneIn$1_allIn$1_partialFill$1_nextToCopy != noneIn$1_allIn$1_partialFill$1_next) {
                           $colon$colon noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), Nil$.MODULE$);
                           noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(noneIn$1_allIn$1_partialFill$1_newElem);
                           noneIn$1_allIn$1_partialFill$1_currentLast = noneIn$1_allIn$1_partialFill$1_newElem;
                           noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                        }

                        noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)noneIn$1_allIn$1_partialFill$1_next.tail();
                        noneIn$1_allIn$1_partialFill$1_next = (List)noneIn$1_allIn$1_partialFill$1_next.tail();
                     }
                  }

                  if (!noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                     noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(noneIn$1_allIn$1_partialFill$1_nextToCopy);
                  }

                  var10000 = noneIn$1_allIn$1_partialFill$1_newHead;
                  noneIn$1_allIn$1_partialFill$1_newHead = null;
                  Object var27 = null;
                  Object var30 = null;
                  Object var33 = null;
                  Object var36 = null;
                  Object var39 = null;
                  Object var42 = null;
                  Object var45 = null;
                  break;
               }

               noneIn$1_allIn$1_remaining = (List)noneIn$1_allIn$1_remaining.tail();
            }

            Object var20 = null;
            Object var22 = null;
            Object var25 = null;
            Object var28 = null;
            Object var31 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            Object var46 = null;
            break;
         }

         noneIn$1_l = noneIn$1_t;
      }

      Object var17 = null;
      Object var18 = null;
      Object var19 = null;
      Object var21 = null;
      Object var23 = null;
      Object var26 = null;
      Object var29 = null;
      Object var32 = null;
      Object var35 = null;
      Object var38 = null;
      Object var41 = null;
      Object var44 = null;
      Object var47 = null;
      List result = (List)var10000;
      Statics.releaseFence();
      return result;
   }

   public Tuple2 partition(final Function1 p) {
      if (this.isEmpty()) {
         return List$.MODULE$.scala$collection$immutable$List$$TupleOfNil();
      } else {
         Builder partition_l = this.newSpecificBuilder();
         Builder partition_r = this.newSpecificBuilder();
         this.iterator().foreach(StrictOptimizedIterableOps::$anonfun$partition$1);
         Object var10002 = partition_l.result();
         Object var8 = partition_r.result();
         Object var7 = var10002;
         Tuple2 var10000 = new Tuple2(var7, var8);
         Object var9 = null;
         Object var10 = null;
         Tuple2 var2 = var10000;
         List var3 = (List)var7;
         if (Nil$.MODULE$.equals(var3)) {
            return new Tuple2(Nil$.MODULE$, this);
         } else {
            List var4 = (List)var8;
            return Nil$.MODULE$.equals(var4) ? new Tuple2(this, Nil$.MODULE$) : var2;
         }
      }
   }

   public final List toList() {
      return this;
   }

   public boolean equals(final Object o) {
      if (o instanceof List) {
         List var2 = (List)o;
         return this.listEq$1(this, var2);
      } else {
         return scala.collection.Seq.equals$(this, o);
      }
   }

   private final List loop$1(final List lead, final List lag) {
      while(!Nil$.MODULE$.equals(lead)) {
         if (!(lead instanceof $colon$colon)) {
            throw new MatchError(lead);
         }

         List var10000 = (($colon$colon)lead).next$access$1();
         lag = (List)lag.tail();
         lead = var10000;
      }

      return lag;
   }

   private final int loop$2(final int i, final List xs, final int len$1) {
      while(i != len$1) {
         if (xs.isEmpty()) {
            return -1;
         }

         int var10000 = i + 1;
         xs = (List)xs.tail();
         i = var10000;
      }

      if (xs.isEmpty()) {
         return 0;
      } else {
         return 1;
      }
   }

   private final List loop$3(final List mappedHead, final $colon$colon mappedLast, final List unchanged, final List pending, final Function1 f$1) {
      while(!pending.isEmpty()) {
         Object head0 = pending.head();
         Object head1 = f$1.apply(head0);
         if (head1 == head0) {
            pending = (List)pending.tail();
            unchanged = unchanged;
            mappedLast = mappedLast;
            mappedHead = mappedHead;
         } else {
            List xc = unchanged;
            List mappedHead1 = mappedHead;

            $colon$colon mappedLast1;
            for(mappedLast1 = mappedLast; xc != pending; xc = (List)xc.tail()) {
               $colon$colon next = new $colon$colon(xc.head(), Nil$.MODULE$);
               if (mappedHead1 == null) {
                  mappedHead1 = next;
               }

               if (mappedLast1 != null) {
                  mappedLast1.next_$eq(next);
               }

               mappedLast1 = next;
            }

            $colon$colon next = new $colon$colon(head1, Nil$.MODULE$);
            if (mappedHead1 == null) {
               mappedHead1 = next;
            }

            if (mappedLast1 != null) {
               mappedLast1.next_$eq(next);
            }

            List tail0 = (List)pending.tail();
            pending = tail0;
            unchanged = tail0;
            mappedLast = next;
            mappedHead = mappedHead1;
         }
      }

      if (mappedHead == null) {
         return unchanged;
      } else {
         mappedLast.next_$eq(unchanged);
         return mappedHead;
      }
   }

   private final List noneIn$1(final List l, final Function1 p$1, final boolean isFlipped$1) {
      while(!l.isEmpty()) {
         Object h = l.head();
         List t = (List)l.tail();
         if (BoxesRunTime.unboxToBoolean(p$1.apply(h)) != isFlipped$1) {
            for(List allIn$1_remaining = t; !allIn$1_remaining.isEmpty(); allIn$1_remaining = (List)allIn$1_remaining.tail()) {
               Object allIn$1_x = allIn$1_remaining.head();
               if (BoxesRunTime.unboxToBoolean(p$1.apply(allIn$1_x)) == isFlipped$1) {
                  $colon$colon allIn$1_partialFill$1_newHead = new $colon$colon(l.head(), Nil$.MODULE$);
                  List allIn$1_partialFill$1_toProcess = (List)l.tail();

                  $colon$colon allIn$1_partialFill$1_currentLast;
                  for(allIn$1_partialFill$1_currentLast = allIn$1_partialFill$1_newHead; allIn$1_partialFill$1_toProcess != allIn$1_remaining; allIn$1_partialFill$1_toProcess = (List)allIn$1_partialFill$1_toProcess.tail()) {
                     $colon$colon allIn$1_partialFill$1_newElem = new $colon$colon(allIn$1_partialFill$1_toProcess.head(), Nil$.MODULE$);
                     allIn$1_partialFill$1_currentLast.next_$eq(allIn$1_partialFill$1_newElem);
                     allIn$1_partialFill$1_currentLast = allIn$1_partialFill$1_newElem;
                  }

                  List allIn$1_partialFill$1_next = (List)allIn$1_remaining.tail();
                  List allIn$1_partialFill$1_nextToCopy = allIn$1_partialFill$1_next;

                  while(!allIn$1_partialFill$1_next.isEmpty()) {
                     Object allIn$1_partialFill$1_head = allIn$1_partialFill$1_next.head();
                     if (BoxesRunTime.unboxToBoolean(p$1.apply(allIn$1_partialFill$1_head)) != isFlipped$1) {
                        allIn$1_partialFill$1_next = (List)allIn$1_partialFill$1_next.tail();
                     } else {
                        while(allIn$1_partialFill$1_nextToCopy != allIn$1_partialFill$1_next) {
                           $colon$colon allIn$1_partialFill$1_newElem = new $colon$colon(allIn$1_partialFill$1_nextToCopy.head(), Nil$.MODULE$);
                           allIn$1_partialFill$1_currentLast.next_$eq(allIn$1_partialFill$1_newElem);
                           allIn$1_partialFill$1_currentLast = allIn$1_partialFill$1_newElem;
                           allIn$1_partialFill$1_nextToCopy = (List)allIn$1_partialFill$1_nextToCopy.tail();
                        }

                        allIn$1_partialFill$1_nextToCopy = (List)allIn$1_partialFill$1_next.tail();
                        allIn$1_partialFill$1_next = (List)allIn$1_partialFill$1_next.tail();
                     }
                  }

                  if (!allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                     allIn$1_partialFill$1_currentLast.next_$eq(allIn$1_partialFill$1_nextToCopy);
                  }

                  return allIn$1_partialFill$1_newHead;
               }
            }

            return l;
         }

         l = t;
      }

      return Nil$.MODULE$;
   }

   private final List allIn$1(final List start, final List remaining, final Function1 p$1, final boolean isFlipped$1) {
      while(!remaining.isEmpty()) {
         Object x = remaining.head();
         if (BoxesRunTime.unboxToBoolean(p$1.apply(x)) == isFlipped$1) {
            $colon$colon partialFill$1_newHead = new $colon$colon(start.head(), Nil$.MODULE$);
            List partialFill$1_toProcess = (List)start.tail();

            $colon$colon partialFill$1_currentLast;
            for(partialFill$1_currentLast = partialFill$1_newHead; partialFill$1_toProcess != remaining; partialFill$1_toProcess = (List)partialFill$1_toProcess.tail()) {
               $colon$colon partialFill$1_newElem = new $colon$colon(partialFill$1_toProcess.head(), Nil$.MODULE$);
               partialFill$1_currentLast.next_$eq(partialFill$1_newElem);
               partialFill$1_currentLast = partialFill$1_newElem;
            }

            List partialFill$1_next = (List)remaining.tail();
            List partialFill$1_nextToCopy = partialFill$1_next;

            while(!partialFill$1_next.isEmpty()) {
               Object partialFill$1_head = partialFill$1_next.head();
               if (BoxesRunTime.unboxToBoolean(p$1.apply(partialFill$1_head)) != isFlipped$1) {
                  partialFill$1_next = (List)partialFill$1_next.tail();
               } else {
                  while(partialFill$1_nextToCopy != partialFill$1_next) {
                     $colon$colon partialFill$1_newElem = new $colon$colon(partialFill$1_nextToCopy.head(), Nil$.MODULE$);
                     partialFill$1_currentLast.next_$eq(partialFill$1_newElem);
                     partialFill$1_currentLast = partialFill$1_newElem;
                     partialFill$1_nextToCopy = (List)partialFill$1_nextToCopy.tail();
                  }

                  partialFill$1_nextToCopy = (List)partialFill$1_next.tail();
                  partialFill$1_next = (List)partialFill$1_next.tail();
               }
            }

            if (!partialFill$1_nextToCopy.isEmpty()) {
               partialFill$1_currentLast.next_$eq(partialFill$1_nextToCopy);
            }

            return partialFill$1_newHead;
         }

         remaining = (List)remaining.tail();
         start = start;
      }

      return start;
   }

   private static final List partialFill$1(final List origStart, final List firstMiss, final Function1 p$1, final boolean isFlipped$1) {
      $colon$colon newHead = new $colon$colon(origStart.head(), Nil$.MODULE$);
      List toProcess = (List)origStart.tail();

      $colon$colon currentLast;
      for(currentLast = newHead; toProcess != firstMiss; toProcess = (List)toProcess.tail()) {
         $colon$colon newElem = new $colon$colon(toProcess.head(), Nil$.MODULE$);
         currentLast.next_$eq(newElem);
         currentLast = newElem;
      }

      List next = (List)firstMiss.tail();
      List nextToCopy = next;

      while(!next.isEmpty()) {
         Object head = next.head();
         if (BoxesRunTime.unboxToBoolean(p$1.apply(head)) != isFlipped$1) {
            next = (List)next.tail();
         } else {
            while(nextToCopy != next) {
               $colon$colon newElem = new $colon$colon(nextToCopy.head(), Nil$.MODULE$);
               currentLast.next_$eq(newElem);
               currentLast = newElem;
               nextToCopy = (List)nextToCopy.tail();
            }

            nextToCopy = (List)next.tail();
            next = (List)next.tail();
         }
      }

      if (!nextToCopy.isEmpty()) {
         currentLast.next_$eq(nextToCopy);
      }

      return newHead;
   }

   private final boolean listEq$1(final List a, final List b) {
      while(a != b) {
         boolean aEmpty = a.isEmpty();
         boolean bEmpty = b.isEmpty();
         if (aEmpty || bEmpty || !BoxesRunTime.equals(a.head(), b.head())) {
            if (aEmpty && bEmpty) {
               break;
            }

            return false;
         }

         List var10000 = (List)a.tail();
         b = (List)b.tail();
         a = var10000;
      }

      return true;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
