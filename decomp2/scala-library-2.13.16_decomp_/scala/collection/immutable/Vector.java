package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IndexedSeqView;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.generic.CommonErrors$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Integral;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\r}r!B\u00193\u0011\u0003Id!B\u001e3\u0011\u0003a\u0004B\u0002?\u0002\t\u0003\u0011Y\rC\u0004\u0003N\u0006!\tAa4\t\u000f\te\u0017\u0001\"\u0001\u0003\\\"9!Q^\u0001\u0005\u0002\t=\b\u0002CB\u0003\u0003\u0011\u0005Aga\u0002\t\u0013\r]\u0011A1A\u0005\n\u0005-\u0001\u0002CB\r\u0003\u0001\u0006I!!\u0004\t\u0013\rm\u0011A1A\u0005\n\ru\u0001\u0002CB\u0013\u0003\u0001\u0006Iaa\b\t\u0013\r\u001d\u0012!!A\u0005\n\r%b!B\u001e3\u0003C1\u0005\"C5\r\u0005\u000b\u0007IQ\u0001\u001ak\u0011!YHB!A!\u0002\u001bY\u0007B\u0002?\r\t\u0003\u0011T\u0010\u0003\u0004\u0000\u0019\u0011\u0005\u0013\u0011\u0001\u0005\b\u0003\u0013aAQIA\u0006\u0011\u001d\t\u0019\u0002\u0004C#\u0003+A\u0001\"!\b\r\t+\"\u0014q\u0004\u0005\b\u0003kaA\u0011IA\u001c\u0011\u001d\ti\u0005\u0004C!\u0003\u001fBq!a\u0017\r\t\u0003\ni\u0006C\u0004\u0002j1!\t%a\u001b\t\u000f\u0005}D\u0002\"\u0012\u0002\u0002\"A\u0011\u0011\u0013\u0007!\n#\t\u0019\n\u0003\u0005\u0002&2\u0001K\u0011CAT\u0011\u001d\t9\f\u0004C!\u0003sCq!a3\r\t\u000b\ni\rC\u0004\u0002\\2!)%!8\t\u000f\u0005\rH\u0002\"\u0012\u0002f\"9\u00111\u001e\u0007\u0005F\u00055\bbBAz\u0019\u0011\u0005\u0013Q\u001f\u0005\b\u0003odA\u0011IA{\u0011!\tI\u0010\u0004Q\u0007\u0012\u0005m\b\u0002\u0003B\u0003\u0019\u0019E!'a\u0003\t\u0011\t\u001dAB\"\u00053\u0005\u0013A\u0001Ba\b\r\r#\u0011$\u0011\u0005\u0005\b\u0005KaA\u0011\tB\u0014\u0011\u001d\u0011i\u0004\u0004C!\u0003kDqAa\u0010\r\t#\nY\u0001C\u0004\u0003B1!\tEa\u0011\t\u0011\tmD\u0002)C\t\u0005{BqAa$\r\t\u000b\u0012\t\nC\u0004\u0003\u00142!)E!%\t\u000f\tUE\u0002\"\u0012\u0003\u0018\"A!Q\u0016\u0007\u0005\u0002Q\nY\u0001\u0003\u0005\u000302!\t\u0001NA\u0006\u0011!\u0011\t\f\u0004C\u0001i\tM\u0016A\u0002,fGR|'O\u0003\u00024i\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003kY\n!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0014!B:dC2\f7\u0001\u0001\t\u0003u\u0005i\u0011A\r\u0002\u0007-\u0016\u001cGo\u001c:\u0014\u0007\u0005i\u0014\t\u0005\u0002?\u007f5\ta'\u0003\u0002Am\t1\u0011I\\=SK\u001a\u00042AQ\"F\u001b\u0005!\u0014B\u0001#5\u0005e\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9GC\u000e$xN]=\u0011\u0005ibQCA$N'\u001da\u0001JV-^A\u000e\u00042AO%L\u0013\tQ%GA\u0006BEN$(/Y2u'\u0016\f\bC\u0001'N\u0019\u0001!aA\u0014\u0007\u0005\u0006\u0004y%!A!\u0012\u0005A\u001b\u0006C\u0001 R\u0013\t\u0011fGA\u0004O_RD\u0017N\\4\u0011\u0005y\"\u0016BA+7\u0005\r\te.\u001f\t\u0004u][\u0015B\u0001-3\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0006ui[U\tX\u0005\u00037J\u0012Q\"\u00138eKb,GmU3r\u001fB\u001c\bc\u0001\u001e\r\u0017B)!HX&F9&\u0011qL\r\u0002\u0016'R\u0014\u0018n\u0019;PaRLW.\u001b>fIN+\u0017o\u00149t!\u0011\u0011\u0015mS#\n\u0005\t$$aF%uKJ\f'\r\\3GC\u000e$xN]=EK\u001a\fW\u000f\u001c;t!\t!w-D\u0001f\u0015\t1G'A\u0004hK:,'/[2\n\u0005!,'a\u0005#fM\u0006,H\u000e^*fe&\fG.\u001b>bE2,\u0017a\u00029sK\u001aL\u00070M\u000b\u0002WB\u0011A\u000e\u001f\b\u0003[Zt!A\\;\u000f\u0005=$hB\u00019t\u001b\u0005\t(B\u0001:9\u0003\u0019a$o\\8u}%\tq'\u0003\u00026m%\u00111\u0007N\u0005\u0003oJ\nABV3di>\u0014\u0018J\u001c7j]\u0016L!!\u001f>\u0003\t\u0005\u0013(/\r\u0006\u0003oJ\n\u0001\u0002\u001d:fM&D\u0018\u0007I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005qs\b\"B5\u0010\u0001\u0004Y\u0017aD5uKJ\f'\r\\3GC\u000e$xN]=\u0016\u0005\u0005\r\u0001\u0003\u0002\"\u0002\u0006\u0015K1!a\u00025\u0005)\u0019V-\u001d$bGR|'/_\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0005\u00055\u0001c\u0001 \u0002\u0010%\u0019\u0011\u0011\u0003\u001c\u0003\u0007%sG/\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\t9\u0002\u0005\u0003C\u00033Y\u0015bAA\u000ei\tA\u0011\n^3sCR|'/\u0001\u0006gS2$XM]%na2$R\u0001XA\u0011\u0003cAq!a\t\u0014\u0001\u0004\t)#\u0001\u0003qe\u0016$\u0007C\u0002 \u0002(-\u000bY#C\u0002\u0002*Y\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0007y\ni#C\u0002\u00020Y\u0012qAQ8pY\u0016\fg\u000eC\u0004\u00024M\u0001\r!a\u000b\u0002\u0013%\u001ch\t\\5qa\u0016$\u0017aB;qI\u0006$X\rZ\u000b\u0005\u0003s\ty\u0004\u0006\u0004\u0002<\u0005\u0015\u0013\u0011\n\t\u0005u1\ti\u0004E\u0002M\u0003\u007f!q!!\u0011\u0015\u0005\u0004\t\u0019EA\u0001C#\tY5\u000bC\u0004\u0002HQ\u0001\r!!\u0004\u0002\u000b%tG-\u001a=\t\u000f\u0005-C\u00031\u0001\u0002>\u0005!Q\r\\3n\u0003!\t\u0007\u000f]3oI\u0016$W\u0003BA)\u0003/\"B!a\u0015\u0002ZA!!\bDA+!\ra\u0015q\u000b\u0003\b\u0003\u0003*\"\u0019AA\"\u0011\u001d\tY%\u0006a\u0001\u0003+\n\u0011\u0002\u001d:fa\u0016tG-\u001a3\u0016\t\u0005}\u0013Q\r\u000b\u0005\u0003C\n9\u0007\u0005\u0003;\u0019\u0005\r\u0004c\u0001'\u0002f\u00119\u0011\u0011\t\fC\u0002\u0005\r\u0003bBA&-\u0001\u0007\u00111M\u0001\raJ,\u0007/\u001a8eK\u0012\fE\u000e\\\u000b\u0005\u0003[\n\u0019\b\u0006\u0003\u0002p\u0005U\u0004\u0003\u0002\u001e\r\u0003c\u00022\u0001TA:\t\u001d\t\te\u0006b\u0001\u0003\u0007Bq!a\u001e\u0018\u0001\u0004\tI(\u0001\u0004qe\u00164\u0017\u000e\u001f\t\u0006\u0005\u0006m\u0014\u0011O\u0005\u0004\u0003{\"$\u0001D%uKJ\f'\r\\3P]\u000e,\u0017aC1qa\u0016tG-\u001a3BY2,B!a!\u0002\nR!\u0011QQAF!\u0011QD\"a\"\u0011\u00071\u000bI\tB\u0004\u0002Ba\u0011\r!a\u0011\t\u000f\u00055\u0005\u00041\u0001\u0002\u0010\u000611/\u001e4gSb\u0004RAQA>\u0003\u000f\u000bQ\u0002\u001d:fa\u0016tG-\u001a3BY2\u0004T\u0003BAK\u00037#b!a&\u0002\u001e\u0006\u0005\u0006\u0003\u0002\u001e\r\u00033\u00032\u0001TAN\t\u001d\t\t%\u0007b\u0001\u0003\u0007Bq!a\u001e\u001a\u0001\u0004\ty\nE\u0003C\u0003w\nI\nC\u0004\u0002$f\u0001\r!!\u0004\u0002\u0003-\fA\"\u00199qK:$W\rZ!mYB*B!!+\u00020R1\u00111VAY\u0003k\u0003BA\u000f\u0007\u0002.B\u0019A*a,\u0005\u000f\u0005\u0005#D1\u0001\u0002D!9\u0011Q\u0012\u000eA\u0002\u0005M\u0006#\u0002\"\u0002|\u00055\u0006bBAR5\u0001\u0007\u0011QB\u0001\nG2\f7o\u001d(b[\u0016,\"!a/\u0011\t\u0005u\u0016qY\u0007\u0003\u0003\u007fSA!!1\u0002D\u0006!A.\u00198h\u0015\t\t)-\u0001\u0003kCZ\f\u0017\u0002BAe\u0003\u007f\u0013aa\u0015;sS:<\u0017\u0001\u0002;bW\u0016$2\u0001XAh\u0011\u001d\t\t\u000e\ba\u0001\u0003\u001b\t\u0011A\u001c\u0015\u00049\u0005U\u0007c\u0001 \u0002X&\u0019\u0011\u0011\u001c\u001c\u0003\r%tG.\u001b8f\u0003\u0011!'o\u001c9\u0015\u0007q\u000by\u000eC\u0004\u0002Rv\u0001\r!!\u0004)\u0007u\t).A\u0005uC.,'+[4iiR\u0019A,a:\t\u000f\u0005Eg\u00041\u0001\u0002\u000e!\u001aa$!6\u0002\u0013\u0011\u0014x\u000e\u001d*jO\"$Hc\u0001/\u0002p\"9\u0011\u0011[\u0010A\u0002\u00055\u0001fA\u0010\u0002V\u0006!A/Y5m+\u0005a\u0016\u0001B5oSR\faa\u001d7jG\u0016\u0004D#\u0002/\u0002~\n\u0005\u0001bBA\u0000E\u0001\u0007\u0011QB\u0001\u0003Y>DqAa\u0001#\u0001\u0004\ti!\u0001\u0002iS\u0006\u0001b/Z2u_J\u001cF.[2f\u0007>,h\u000e^\u0001\fm\u0016\u001cGo\u001c:TY&\u001cW\r\u0006\u0003\u0003\f\tm\u0001\u0007\u0002B\u0007\u0005+\u0001RA\u0010B\b\u0005'I1A!\u00057\u0005\u0015\t%O]1z!\ra%Q\u0003\u0003\f\u0005/!\u0013\u0011!A\u0001\u0006\u0003\u0011IBA\u0002`Ia\n\"\u0001U\u001f\t\u000f\tuA\u00051\u0001\u0002\u000e\u0005\u0019\u0011\u000e\u001a=\u0002/Y,7\r^8s'2L7-\u001a)sK\u001aL\u0007\u0010T3oORDG\u0003BA\u0007\u0005GAqA!\b&\u0001\u0004\ti!A\u0006d_BLHk\\!se\u0006LX\u0003\u0002B\u0015\u0005g!\u0002\"!\u0004\u0003,\tU\"\u0011\b\u0005\b\u0005[1\u0003\u0019\u0001B\u0018\u0003\tA8\u000fE\u0003?\u0005\u001f\u0011\t\u0004E\u0002M\u0005g!q!!\u0011'\u0005\u0004\t\u0019\u0005C\u0004\u00038\u0019\u0002\r!!\u0004\u0002\u000bM$\u0018M\u001d;\t\u000f\tmb\u00051\u0001\u0002\u000e\u0005\u0019A.\u001a8\u0002\u0011Q|g+Z2u_J\fq#\u00199qYf\u0004&/\u001a4feJ,G-T1y\u0019\u0016tw\r\u001e5\u0002\u000fM$X\r\u001d9feV!!Q\tB()\u0011\u00119E!\u001d\u0013\r\t%#Q\nB2\r\u0019\u0011Y\u0005\u0004\u0001\u0003H\taAH]3gS:,W.\u001a8u}A\u0019AJa\u0014\u0005\u000f\tE\u0013F1\u0001\u0003T\t\t1+E\u0002Q\u0005+\u0002DAa\u0016\u0003`A)!I!\u0017\u0003^%\u0019!1\f\u001b\u0003\u000fM#X\r\u001d9feB\u0019AJa\u0018\u0005\u0017\t\u0005$qJA\u0001\u0002\u0003\u0015\ta\u0014\u0002\u0004?\u0012J\u0004\u0003\u0002B3\u0005Wr1A\u001cB4\u0013\r\u0011I\u0007N\u0001\b'R,\u0007\u000f]3s\u0013\u0011\u0011iGa\u001c\u0003\u001d\u00153g-[2jK:$8\u000b\u001d7ji*\u0019!\u0011\u000e\u001b\t\u000f\tM\u0014\u0006q\u0001\u0003v\u0005)1\u000f[1qKB1!Ia\u001eL\u0005\u001bJ1A!\u001f5\u00051\u0019F/\u001a9qKJ\u001c\u0006.\u00199f\u0003\u0011Iwn\u001c2\u0015\t\t}$Q\u0012\t\u0005\u0005\u0003\u00139ID\u0002p\u0005\u0007K1A!\"7\u0003\u001d\u0001\u0018mY6bO\u0016LAA!#\u0003\f\nI\u0012J\u001c3fq>+Ho\u00144C_VtGm]#yG\u0016\u0004H/[8o\u0015\r\u0011)I\u000e\u0005\b\u0003\u000fR\u0003\u0019AA\u0007\u0003\u0011AW-\u00193\u0016\u0003-\u000bA\u0001\\1ti\u00069am\u001c:fC\u000eDW\u0003\u0002BM\u0005S#BAa'\u0003\"B\u0019aH!(\n\u0007\t}eG\u0001\u0003V]&$\bb\u0002BR[\u0001\u0007!QU\u0001\u0002MB1a(a\nL\u0005O\u00032\u0001\u0014BU\t\u0019\u0011Y+\fb\u0001\u001f\n\tQ+\u0001\u0006ti\u0006\u0014H/\u00138eKb\f\u0001\"\u001a8e\u0013:$W\r_\u0001\rS:LG/\u0013;fe\u0006$xN]\u000b\u0005\u0005k\u0013\u0019\r\u0006\u0003\u0003\u001c\n]\u0006b\u0002B]a\u0001\u0007!1X\u0001\u0002gB)!H!0\u0003B&\u0019!q\u0018\u001a\u0003\u001dY+7\r^8s\u0013R,'/\u0019;peB\u0019AJa1\u0005\u000f\u0005\u0005\u0003G1\u0001\u0002D%\u001aABa2\n\u0007\t%'G\u0001\u0006WK\u000e$xN]%na2$\u0012!O\u0001\u0006K6\u0004H/_\u000b\u0005\u0005#\u00149.\u0006\u0002\u0003TB!!\b\u0004Bk!\ra%q\u001b\u0003\u0006\u001d\u000e\u0011\raT\u0001\u0005MJ|W.\u0006\u0003\u0003^\n\rH\u0003\u0002Bp\u0005O\u0004BA\u000f\u0007\u0003bB\u0019AJa9\u0005\r\t\u0015HA1\u0001P\u0005\u0005)\u0005b\u0002Bu\t\u0001\u0007!1^\u0001\u0003SR\u0004RAQA>\u0005C\f!B\\3x\u0005VLG\u000eZ3s+\u0011\u0011\tp!\u0001\u0016\u0005\tM\b\u0003\u0003B{\u0005w\u0014ypa\u0001\u000e\u0005\t](b\u0001B}i\u00059Q.\u001e;bE2,\u0017\u0002\u0002B\u007f\u0005o\u0014qBU3vg\u0006\u0014G.\u001a\"vS2$WM\u001d\t\u0004\u0019\u000e\u0005A!\u0002(\u0006\u0005\u0004y\u0005\u0003\u0002\u001e\r\u0005\u007f\f!BZ5mYN\u0003\u0018M]:f+\u0011\u0019Ia!\u0005\u0015\t\r-1Q\u0003\u000b\u0005\u0007\u001b\u0019\u0019\u0002\u0005\u0003;\u0019\r=\u0001c\u0001'\u0004\u0012\u0011)aJ\u0002b\u0001\u001f\"9\u00111\n\u0004A\u0002\r=\u0001bBAi\r\u0001\u0007\u0011QB\u0001\u001fI\u00164\u0017-\u001e7u\u0003B\u0004H.\u001f)sK\u001a,'O]3e\u001b\u0006DH*\u001a8hi\"\fq\u0004Z3gCVdG/\u00119qYf\u0004&/\u001a4feJ,G-T1y\u0019\u0016tw\r\u001e5!\u00035)W\u000e\u001d;z\u0013R,'/\u0019;peV\u00111q\u0004\t\u0005u\r\u0005\u0002+C\u0002\u0004$I\u0012\u0011CT3x-\u0016\u001cGo\u001c:Ji\u0016\u0014\u0018\r^8s\u00039)W\u000e\u001d;z\u0013R,'/\u0019;pe\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"aa\u000b\u0011\t\u0005u6QF\u0005\u0005\u0007_\tyL\u0001\u0004PE*,7\r\u001e\u0015\b\u0003\rM2\u0011HB\u001e!\rq4QG\u0005\u0004\u0007o1$\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0001f\u0002\u0001\u00044\re21\b"
)
public abstract class Vector extends AbstractSeq implements IndexedSeq, StrictOptimizedSeqOps, DefaultSerializable {
   private final Object[] prefix1;

   public static ReusableBuilder newBuilder() {
      Vector$ var10000 = Vector$.MODULE$;
      return new VectorBuilder();
   }

   public static Vector from(final IterableOnce it) {
      return Vector$.MODULE$.from(it);
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder tabulate_b = new VectorBuilder();

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return ((VectorBuilder)tabulate_b).result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder fill_b = new VectorBuilder();

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return ((VectorBuilder)fill_b).result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      Vector$ var10000 = Vector$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder tabulate_b = new VectorBuilder();

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new VectorBuilder();

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new VectorBuilder();

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new VectorBuilder();
               Builder.sizeHint$(tabulate_b, n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Builder tabulate_b = new VectorBuilder();
                  Builder.sizeHint$(tabulate_b, n5);

                  for(int tabulate_i = 0; tabulate_i < n5; ++tabulate_i) {
                     Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                     ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
                     tabulate_$plus$eq_elem = null;
                  }

                  Vector var34 = ((VectorBuilder)tabulate_b).result();
                  tabulate_b = null;
                  Object var33 = null;
                  Object tabulate_$plus$eq_elem = var34;
                  ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               Vector var35 = ((VectorBuilder)tabulate_b).result();
               tabulate_b = null;
               Object var30 = null;
               Object tabulate_$plus$eq_elem = var35;
               ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            Vector var36 = ((VectorBuilder)tabulate_b).result();
            tabulate_b = null;
            Object var27 = null;
            Object tabulate_$plus$eq_elem = var36;
            ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         Vector var37 = ((VectorBuilder)tabulate_b).result();
         tabulate_b = null;
         Object var24 = null;
         Object tabulate_$plus$eq_elem = var37;
         ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return ((VectorBuilder)tabulate_b).result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder tabulate_b = new VectorBuilder();

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new VectorBuilder();

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new VectorBuilder();

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new VectorBuilder();
               Builder.sizeHint$(tabulate_b, n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                  ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               Vector var27 = ((VectorBuilder)tabulate_b).result();
               tabulate_b = null;
               Object var26 = null;
               Object tabulate_$plus$eq_elem = var27;
               ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            Vector var28 = ((VectorBuilder)tabulate_b).result();
            tabulate_b = null;
            Object var23 = null;
            Object tabulate_$plus$eq_elem = var28;
            ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         Vector var29 = ((VectorBuilder)tabulate_b).result();
         tabulate_b = null;
         Object var20 = null;
         Object tabulate_$plus$eq_elem = var29;
         ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return ((VectorBuilder)tabulate_b).result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder tabulate_b = new VectorBuilder();

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new VectorBuilder();

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new VectorBuilder();

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i);
               ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            Vector var20 = ((VectorBuilder)tabulate_b).result();
            tabulate_b = null;
            Object var19 = null;
            Object tabulate_$plus$eq_elem = var20;
            ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         Vector var21 = ((VectorBuilder)tabulate_b).result();
         tabulate_b = null;
         Object var16 = null;
         Object tabulate_$plus$eq_elem = var21;
         ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return ((VectorBuilder)tabulate_b).result();
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder tabulate_b = new VectorBuilder();

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new VectorBuilder();

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i);
            ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         Vector var13 = ((VectorBuilder)tabulate_b).result();
         tabulate_b = null;
         Object var12 = null;
         Object tabulate_$plus$eq_elem = var13;
         ((VectorBuilder)tabulate_b).addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return ((VectorBuilder)tabulate_b).result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder fill_b = new VectorBuilder();

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new VectorBuilder();

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new VectorBuilder();

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new VectorBuilder();
               Builder.sizeHint$(fill_b, n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Builder fill_b = new VectorBuilder();
                  Builder.sizeHint$(fill_b, n5);

                  for(int fill_i = 0; fill_i < n5; ++fill_i) {
                     Object fill_$plus$eq_elem = elem.apply();
                     ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
                     fill_$plus$eq_elem = null;
                  }

                  Vector var34 = ((VectorBuilder)fill_b).result();
                  fill_b = null;
                  Object var33 = null;
                  Object fill_$plus$eq_elem = var34;
                  ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               Vector var35 = ((VectorBuilder)fill_b).result();
               fill_b = null;
               Object var30 = null;
               Object fill_$plus$eq_elem = var35;
               ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            Vector var36 = ((VectorBuilder)fill_b).result();
            fill_b = null;
            Object var27 = null;
            Object fill_$plus$eq_elem = var36;
            ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         Vector var37 = ((VectorBuilder)fill_b).result();
         fill_b = null;
         Object var24 = null;
         Object fill_$plus$eq_elem = var37;
         ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return ((VectorBuilder)fill_b).result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder fill_b = new VectorBuilder();

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new VectorBuilder();

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new VectorBuilder();

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new VectorBuilder();
               Builder.sizeHint$(fill_b, n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Object fill_$plus$eq_elem = elem.apply();
                  ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               Vector var27 = ((VectorBuilder)fill_b).result();
               fill_b = null;
               Object var26 = null;
               Object fill_$plus$eq_elem = var27;
               ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            Vector var28 = ((VectorBuilder)fill_b).result();
            fill_b = null;
            Object var23 = null;
            Object fill_$plus$eq_elem = var28;
            ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         Vector var29 = ((VectorBuilder)fill_b).result();
         fill_b = null;
         Object var20 = null;
         Object fill_$plus$eq_elem = var29;
         ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return ((VectorBuilder)fill_b).result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder fill_b = new VectorBuilder();

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new VectorBuilder();

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new VectorBuilder();

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_$plus$eq_elem = elem.apply();
               ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            Vector var20 = ((VectorBuilder)fill_b).result();
            fill_b = null;
            Object var19 = null;
            Object fill_$plus$eq_elem = var20;
            ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         Vector var21 = ((VectorBuilder)fill_b).result();
         fill_b = null;
         Object var16 = null;
         Object fill_$plus$eq_elem = var21;
         ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return ((VectorBuilder)fill_b).result();
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      Vector$ var10000 = Vector$.MODULE$;
      Builder fill_b = new VectorBuilder();

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new VectorBuilder();

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_$plus$eq_elem = elem.apply();
            ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         Vector var13 = ((VectorBuilder)fill_b).result();
         fill_b = null;
         Object var12 = null;
         Object fill_$plus$eq_elem = var13;
         ((VectorBuilder)fill_b).addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return ((VectorBuilder)fill_b).result();
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Vector$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Vector$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      Vector$ unfold_this = Vector$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      Vector$ iterate_this = Vector$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
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

   public Object padTo(final int len, final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.intersect$(this, that);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
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

   // $FF: synthetic method
   public boolean scala$collection$immutable$IndexedSeq$$super$canEqual(final Object that) {
      return scala.collection.Seq.canEqual$(this, that);
   }

   // $FF: synthetic method
   public boolean scala$collection$immutable$IndexedSeq$$super$sameElements(final IterableOnce that) {
      return scala.collection.SeqOps.sameElements$(this, that);
   }

   public final IndexedSeq toIndexedSeq() {
      return IndexedSeq.toIndexedSeq$(this);
   }

   public boolean canEqual(final Object that) {
      return IndexedSeq.canEqual$(this, that);
   }

   public boolean sameElements(final IterableOnce o) {
      return IndexedSeq.sameElements$(this, o);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$IndexedSeqOps$$super$slice(final int from, final int until) {
      return scala.collection.IndexedSeqOps.slice$(this, from, until);
   }

   public Object slice(final int from, final int until) {
      return IndexedSeqOps.slice$(this, from, until);
   }

   public String stringPrefix() {
      return scala.collection.IndexedSeq.stringPrefix$(this);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return scala.collection.IndexedSeqOps.foldRight$(this, z, op);
   }

   public IndexedSeqView view() {
      return scala.collection.IndexedSeqOps.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public scala.collection.Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Object reverse() {
      return scala.collection.IndexedSeqOps.reverse$(this);
   }

   public Option headOption() {
      return scala.collection.IndexedSeqOps.headOption$(this);
   }

   public final int lengthCompare(final int len) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, len);
   }

   public int knownSize() {
      return scala.collection.IndexedSeqOps.knownSize$(this);
   }

   public final int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   public final Object[] prefix1() {
      return this.prefix1;
   }

   public SeqFactory iterableFactory() {
      return Vector$.MODULE$;
   }

   public final int length() {
      return this instanceof BigVector ? ((BigVector)this).length0() : this.prefix1().length;
   }

   public final Iterator iterator() {
      return Vector0$.MODULE$ == this ? Vector$.MODULE$.scala$collection$immutable$Vector$$emptyIterator() : new NewVectorIterator(this, this.length(), this.vectorSliceCount());
   }

   public final Vector filterImpl(final Function1 pred, final boolean isFlipped) {
      int i = 0;

      for(int len = this.prefix1().length; i != len; ++i) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.prefix1()[i])) == isFlipped) {
            int bitmap = 0;

            for(int j = i + 1; j < len; ++j) {
               if (BoxesRunTime.unboxToBoolean(pred.apply(this.prefix1()[j])) != isFlipped) {
                  bitmap |= 1 << j;
               }
            }

            int newLen = i + Integer.bitCount(bitmap);
            if (!(this instanceof BigVector)) {
               if (newLen == 0) {
                  return Vector0$.MODULE$;
               }

               Object[] newData = new Object[newLen];
               System.arraycopy(this.prefix1(), 0, newData, 0, i);

               for(int k = i + 1; i != newLen; ++k) {
                  if ((1 << k & bitmap) != 0) {
                     newData[i] = this.prefix1()[k];
                     ++i;
                  }
               }

               return new Vector1(newData);
            }

            VectorBuilder b = new VectorBuilder();

            for(int k = 0; k < i; ++k) {
               b.addOne(this.prefix1()[k]);
            }

            for(int var45 = i + 1; i != newLen; ++var45) {
               if ((1 << var45 & bitmap) != 0) {
                  b.addOne(this.prefix1()[var45]);
                  ++i;
               }
            }

            BigVector var10000 = (BigVector)this;
            Function1 foreachRest_f = (v) -> BoxesRunTime.unboxToBoolean(pred.apply(v)) != isFlipped ? b.addOne(v) : BoxedUnit.UNIT;
            BigVector foreachRest_this = var10000;
            int foreachRest_c = foreachRest_this.vectorSliceCount();

            for(int foreachRest_i = 1; foreachRest_i < foreachRest_c; ++foreachRest_i) {
               VectorStatics$ var60 = VectorStatics$.MODULE$;
               VectorInline$ var10001 = VectorInline$.MODULE$;
               int foreachRest_vectorSliceDim_c = foreachRest_c / 2;
               int var63 = foreachRest_vectorSliceDim_c + 1 - Math.abs(foreachRest_i - foreachRest_vectorSliceDim_c) - 1;
               Object[] foreachRest_foreachRec_a = foreachRest_this.vectorSlice(foreachRest_i);
               int foreachRest_foreachRec_level = var63;
               VectorStatics$ foreachRest_foreachRec_this = var60;
               int foreachRest_foreachRec_i = 0;
               int foreachRest_foreachRec_len = foreachRest_foreachRec_a.length;
               if (foreachRest_foreachRec_level == 0) {
                  for(; foreachRest_foreachRec_i < foreachRest_foreachRec_len; ++foreachRest_foreachRec_i) {
                     Object var58 = foreachRest_foreachRec_a[foreachRest_foreachRec_i];
                     if (BoxesRunTime.unboxToBoolean(pred.apply(var58)) != isFlipped) {
                        b.addOne(var58);
                     }
                  }
               } else {
                  for(int foreachRest_foreachRec_l = foreachRest_foreachRec_level - 1; foreachRest_foreachRec_i < foreachRest_foreachRec_len; ++foreachRest_foreachRec_i) {
                     Object[] foreachRec_a = foreachRest_foreachRec_a[foreachRest_foreachRec_i];
                     int foreachRec_i = 0;
                     int foreachRec_len = foreachRec_a.length;
                     if (foreachRest_foreachRec_l == 0) {
                        for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                           Object var43 = foreachRec_a[foreachRec_i];
                           if (BoxesRunTime.unboxToBoolean(pred.apply(var43)) != isFlipped) {
                              b.addOne(var43);
                           }
                        }
                     } else {
                        for(int foreachRec_l = foreachRest_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                           foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], foreachRest_f);
                        }
                     }

                     foreachRec_a = null;
                  }
               }

               Object var47 = null;
               foreachRest_foreachRec_a = null;
            }

            foreachRest_f = null;
            Object var48 = null;
            Object var50 = null;
            return b.result();
         }
      }

      if (!(this instanceof BigVector)) {
         return this;
      } else {
         VectorBuilder b = new VectorBuilder();
         b.initFrom(this.prefix1());
         BigVector var61 = (BigVector)this;
         Function1 foreachRest_f = (v) -> BoxesRunTime.unboxToBoolean(pred.apply(v)) != isFlipped ? b.addOne(v) : BoxedUnit.UNIT;
         BigVector foreachRest_this = var61;
         int foreachRest_c = foreachRest_this.vectorSliceCount();

         for(int foreachRest_i = 1; foreachRest_i < foreachRest_c; ++foreachRest_i) {
            VectorStatics$ var62 = VectorStatics$.MODULE$;
            VectorInline$ var64 = VectorInline$.MODULE$;
            int foreachRest_vectorSliceDim_c = foreachRest_c / 2;
            int var65 = foreachRest_vectorSliceDim_c + 1 - Math.abs(foreachRest_i - foreachRest_vectorSliceDim_c) - 1;
            Object[] foreachRest_foreachRec_a = foreachRest_this.vectorSlice(foreachRest_i);
            int foreachRest_foreachRec_level = var65;
            VectorStatics$ foreachRest_foreachRec_this = var62;
            int foreachRest_foreachRec_i = 0;
            int foreachRest_foreachRec_len = foreachRest_foreachRec_a.length;
            if (foreachRest_foreachRec_level == 0) {
               for(; foreachRest_foreachRec_i < foreachRest_foreachRec_len; ++foreachRest_foreachRec_i) {
                  Object var59 = foreachRest_foreachRec_a[foreachRest_foreachRec_i];
                  if (BoxesRunTime.unboxToBoolean(pred.apply(var59)) != isFlipped) {
                     b.addOne(var59);
                  }
               }
            } else {
               for(int foreachRest_foreachRec_l = foreachRest_foreachRec_level - 1; foreachRest_foreachRec_i < foreachRest_foreachRec_len; ++foreachRest_foreachRec_i) {
                  Object[] foreachRec_a = foreachRest_foreachRec_a[foreachRest_foreachRec_i];
                  int foreachRec_i = 0;
                  int foreachRec_len = foreachRec_a.length;
                  if (foreachRest_foreachRec_l == 0) {
                     for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                        Object var44 = foreachRec_a[foreachRec_i];
                        if (BoxesRunTime.unboxToBoolean(pred.apply(var44)) != isFlipped) {
                           b.addOne(var44);
                        }
                     }
                  } else {
                     for(int foreachRec_l = foreachRest_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                        foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], foreachRest_f);
                     }
                  }

                  foreachRec_a = null;
               }
            }

            Object var52 = null;
            foreachRest_foreachRec_a = null;
         }

         foreachRest_f = null;
         Object var53 = null;
         Object var55 = null;
         return b.result();
      }
   }

   public Vector updated(final int index, final Object elem) {
      return (Vector)StrictOptimizedSeqOps.updated$(this, index, elem);
   }

   public Vector appended(final Object elem) {
      return (Vector)scala.collection.StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Vector prepended(final Object elem) {
      return (Vector)scala.collection.StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Vector prependedAll(final IterableOnce prefix) {
      int k = prefix.knownSize();
      if (k == 0) {
         return this;
      } else {
         return k < 0 ? (Vector)scala.collection.StrictOptimizedSeqOps.prependedAll$(this, prefix) : this.prependedAll0(prefix, k);
      }
   }

   public final Vector appendedAll(final IterableOnce suffix) {
      int k = suffix.knownSize();
      if (k == 0) {
         return this;
      } else {
         return k < 0 ? (Vector)scala.collection.StrictOptimizedSeqOps.appendedAll$(this, suffix) : this.appendedAll0(suffix, k);
      }
   }

   public Vector prependedAll0(final IterableOnce prefix, final int k) {
      int tinyAppendLimit = 4 + this.vectorSliceCount();
      if (k < tinyAppendLimit) {
         Vector v = this;

         Object var6;
         for(Iterator it = IndexedSeq$.MODULE$.from(prefix).reverseIterator(); it.hasNext(); v = (Vector)v.prepended(var6)) {
            var6 = it.next();
            if (v == null) {
               throw null;
            }
         }

         return v;
      } else if (this.length() < k >>> 5 && prefix instanceof Vector) {
         Vector v = (Vector)prefix;

         Object var10000;
         for(Iterator it = this.iterator(); it.hasNext(); v = (Vector)var10000) {
            Object $colon$plus_elem = it.next();
            if (v == null) {
               throw null;
            }

            var10000 = v.appended($colon$plus_elem);
            $colon$plus_elem = null;
         }

         return v;
      } else {
         return k < this.length() - 64 ? (new VectorBuilder()).alignTo(k, this).addAll(prefix).addAll(this).result() : (Vector)scala.collection.StrictOptimizedSeqOps.prependedAll$(this, prefix);
      }
   }

   public Vector appendedAll0(final IterableOnce suffix, final int k) {
      int tinyAppendLimit = 4 + this.vectorSliceCount();
      if (k < tinyAppendLimit) {
         ObjectRef v = new ObjectRef(this);
         if (suffix instanceof Iterable) {
            ((Iterable)suffix).foreach((x) -> {
               $anonfun$appendedAll0$1(v, x);
               return BoxedUnit.UNIT;
            });
         } else {
            suffix.iterator().foreach((x) -> {
               $anonfun$appendedAll0$2(v, x);
               return BoxedUnit.UNIT;
            });
         }

         return (Vector)v.elem;
      } else if (this.length() < k >>> 5 && suffix instanceof Vector) {
         Vector v = (Vector)suffix;

         for(Iterator ri = scala.collection.IndexedSeqOps.reverseIterator$(this); ri.hasNext(); v = v.prepended(ri.next())) {
         }

         return v;
      } else if (this.length() < k - 64 && suffix instanceof Vector) {
         Vector v = (Vector)suffix;
         return (new VectorBuilder()).alignTo(this.length(), v).addAll(this).addAll(v).result();
      } else {
         return (new VectorBuilder()).initFrom(this).addAll(suffix).result();
      }
   }

   public String className() {
      return "Vector";
   }

   public final Vector take(final int n) {
      return (Vector)this.slice(0, n);
   }

   public final Vector drop(final int n) {
      return (Vector)this.slice(n, this.length());
   }

   public final Vector takeRight(final int n) {
      return (Vector)this.slice(this.length() - Math.max(n, 0), this.length());
   }

   public final Vector dropRight(final int n) {
      return (Vector)this.slice(0, this.length() - Math.max(n, 0));
   }

   public Vector tail() {
      return (Vector)this.slice(1, this.length());
   }

   public Vector init() {
      return (Vector)this.slice(0, this.length() - 1);
   }

   public abstract Vector slice0(final int lo, final int hi);

   public abstract int vectorSliceCount();

   public abstract Object[] vectorSlice(final int idx);

   public abstract int vectorSlicePrefixLength(final int idx);

   public int copyToArray(final Object xs, final int start, final int len) {
      return this.iterator().copyToArray(xs, start, len);
   }

   public Vector toVector() {
      return this;
   }

   public int applyPreferredMaxLength() {
      return Vector$.MODULE$.scala$collection$immutable$Vector$$defaultApplyPreferredMaxLength();
   }

   public Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntVectorStepper((NewVectorIterator)this.iterator());
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongVectorStepper((NewVectorIterator)this.iterator());
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleVectorStepper((NewVectorIterator)this.iterator()) : shape.parUnbox(new AnyVectorStepper((NewVectorIterator)this.iterator())));
      }
   }

   public IndexOutOfBoundsException ioob(final int index) {
      return CommonErrors$.MODULE$.indexOutOfBounds(index, this.length() - 1);
   }

   public final Object head() {
      if (this.prefix1().length == 0) {
         throw new NoSuchElementException("empty.head");
      } else {
         return this.prefix1()[0];
      }
   }

   public final Object last() {
      if (this instanceof BigVector) {
         Object[] suffix = ((BigVector)this).suffix1();
         if (suffix.length == 0) {
            throw new NoSuchElementException("empty.tail");
         } else {
            return suffix[suffix.length - 1];
         }
      } else {
         return this.prefix1()[this.prefix1().length - 1];
      }
   }

   public final void foreach(final Function1 f) {
      int c = this.vectorSliceCount();

      for(int i = 0; i < c; ++i) {
         VectorStatics$ var10000 = VectorStatics$.MODULE$;
         VectorInline$ var10001 = VectorInline$.MODULE$;
         int vectorSliceDim_c = c / 2;
         int var13 = vectorSliceDim_c + 1 - Math.abs(i - vectorSliceDim_c) - 1;
         Object[] foreachRec_a = this.vectorSlice(i);
         int foreachRec_level = var13;
         VectorStatics$ foreachRec_this = var10000;
         int foreachRec_i = 0;
         int foreachRec_len = foreachRec_a.length;
         if (foreachRec_level == 0) {
            while(foreachRec_i < foreachRec_len) {
               f.apply(foreachRec_a[foreachRec_i]);
               ++foreachRec_i;
            }
         } else {
            for(int foreachRec_l = foreachRec_level - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
               foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], f);
            }
         }

         Object var11 = null;
         foreachRec_a = null;
      }

   }

   public int startIndex() {
      return 0;
   }

   public int endIndex() {
      return this.length();
   }

   public void initIterator(final VectorIterator s) {
      s.it_$eq((NewVectorIterator)this.iterator());
   }

   // $FF: synthetic method
   public static final void $anonfun$appendedAll0$1(final ObjectRef v$1, final Object x) {
      v$1.elem = ((Vector)v$1.elem).appended(x);
   }

   // $FF: synthetic method
   public static final void $anonfun$appendedAll0$2(final ObjectRef v$1, final Object x) {
      v$1.elem = ((Vector)v$1.elem).appended(x);
   }

   public Vector(final Object[] prefix1) {
      this.prefix1 = prefix1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
