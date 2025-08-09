package spire.std;

import cats.kernel.Group;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001C\f\u0019!\u0003\r\tA\u0007\u000f\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u00111\u0002\u0001\u0007\u0004\u00055\u0001bBA\t\u0001\u0019\r\u00111\u0003\u0005\b\u0003/\u0001a1AA\r\u0011\u001d\ti\u0002\u0001D\u0002\u0003?Aq!a\t\u0001\r\u0007\t)\u0003C\u0004\u0002*\u00011\u0019!a\u000b\t\u000f\u0005=\u0002Ab\u0001\u00022!9\u0011Q\u0007\u0001\u0007\u0004\u0005]\u0002bBA\u001e\u0001\u0019\r\u0011Q\b\u0005\b\u0003\u0003\u0002a1AA\"\u0011\u001d\t9\u0005\u0001D\u0002\u0003\u0013Bq!!\u0014\u0001\r\u0007\ty\u0005C\u0004\u0002T\u00011\u0019!!\u0016\t\u000f\u0005e\u0003Ab\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0004\u0005\u0005\u0004bBA3\u0001\u0019\r\u0011q\r\u0005\b\u0003W\u0002a1AA7\u0011\u001d\t\t\b\u0001D\u0002\u0003gBq!a\u001e\u0001\r\u0007\tI\bC\u0004\u0002~\u00011\u0019!a \t\u000f\u0005\r\u0005\u0001\"\u0001\u0002\u0006\nqqI]8vaB\u0013x\u000eZ;diJ\u0002$BA\r\u001b\u0003\r\u0019H\u000f\u001a\u0006\u00027\u0005)1\u000f]5sKV)RD\u000f#H\u00156\u00036KV-]?\n,\u0007n\u001b8ri^T8\u0003\u0002\u0001\u001fIq\u0004\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0007cA\u00133k9\u0011ae\f\b\u0003O5r!\u0001\u000b\u0017\u000e\u0003%R!AK\u0016\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aG\u0005\u0003]i\tq!\u00197hK\n\u0014\u0018-\u0003\u00021c\u00059\u0001/Y2lC\u001e,'B\u0001\u0018\u001b\u0013\t\u0019DGA\u0003He>,\bO\u0003\u00021cA1rD\u000e\u001dD\r&cuJU+Y7z\u000bGm\u001a6naN4\u00180\u0003\u00028A\t9A+\u001e9mKJ\u0002\u0004CA\u001d;\u0019\u0001!Qa\u000f\u0001C\u0002q\u0012\u0011!Q\t\u0003{\u0001\u0003\"a\b \n\u0005}\u0002#a\u0002(pi\"Lgn\u001a\t\u0003?\u0005K!A\u0011\u0011\u0003\u0007\u0005s\u0017\u0010\u0005\u0002:\t\u0012)Q\t\u0001b\u0001y\t\t!\t\u0005\u0002:\u000f\u0012)\u0001\n\u0001b\u0001y\t\t1\t\u0005\u0002:\u0015\u0012)1\n\u0001b\u0001y\t\tA\t\u0005\u0002:\u001b\u0012)a\n\u0001b\u0001y\t\tQ\t\u0005\u0002:!\u0012)\u0011\u000b\u0001b\u0001y\t\ta\t\u0005\u0002:'\u0012)A\u000b\u0001b\u0001y\t\tq\t\u0005\u0002:-\u0012)q\u000b\u0001b\u0001y\t\t\u0001\n\u0005\u0002:3\u0012)!\f\u0001b\u0001y\t\t\u0011\n\u0005\u0002:9\u0012)Q\f\u0001b\u0001y\t\t!\n\u0005\u0002:?\u0012)\u0001\r\u0001b\u0001y\t\t1\n\u0005\u0002:E\u0012)1\r\u0001b\u0001y\t\tA\n\u0005\u0002:K\u0012)a\r\u0001b\u0001y\t\tQ\n\u0005\u0002:Q\u0012)\u0011\u000e\u0001b\u0001y\t\ta\n\u0005\u0002:W\u0012)A\u000e\u0001b\u0001y\t\tq\n\u0005\u0002:]\u0012)q\u000e\u0001b\u0001y\t\t\u0001\u000b\u0005\u0002:c\u0012)!\u000f\u0001b\u0001y\t\t\u0011\u000b\u0005\u0002:i\u0012)Q\u000f\u0001b\u0001y\t\t!\u000b\u0005\u0002:o\u0012)\u0001\u0010\u0001b\u0001y\t\t1\u000b\u0005\u0002:u\u0012)1\u0010\u0001b\u0001y\t\tA\u000b\u0005\f~}b\u001ae)\u0013'P%VC6LX1eO*l\u0007o\u001d<z\u001b\u0005A\u0012BA@\u0019\u0005=iuN\\8jIB\u0013x\u000eZ;diJ\u0002\u0014A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002\u0006A\u0019q$a\u0002\n\u0007\u0005%\u0001E\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\u0011\u0011q\u0002\t\u0004KIB\u0014AC:ueV\u001cG/\u001e:feU\u0011\u0011Q\u0003\t\u0004KI\u001a\u0015AC:ueV\u001cG/\u001e:fgU\u0011\u00111\u0004\t\u0004KI2\u0015AC:ueV\u001cG/\u001e:fiU\u0011\u0011\u0011\u0005\t\u0004KIJ\u0015AC:ueV\u001cG/\u001e:fkU\u0011\u0011q\u0005\t\u0004KIb\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u0011Q\u0006\t\u0004KIz\u0015AC:ueV\u001cG/\u001e:foU\u0011\u00111\u0007\t\u0004KI\u0012\u0016AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\b\t\u0004KI*\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\b\t\u0004KIB\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\u0012\u0011\u0007\u0015\u00124,A\u0006tiJ,8\r^;sKF\nTCAA&!\r)#GX\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002RA\u0019QEM1\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003/\u00022!\n\u001ae\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005u\u0003cA\u00133O\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\t\u0019\u0007E\u0002&e)\f1b\u001d;sk\u000e$XO]32mU\u0011\u0011\u0011\u000e\t\u0004KIj\u0017aC:ueV\u001cG/\u001e:fc]*\"!a\u001c\u0011\u0007\u0015\u0012\u0004/A\u0006tiJ,8\r^;sKFBTCAA;!\r)#g]\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014(\u0006\u0002\u0002|A\u0019QE\r<\u0002\u0017M$(/^2ukJ,'\u0007M\u000b\u0003\u0003\u0003\u00032!\n\u001az\u0003\u001dIgN^3sg\u0016$2!NAD\u0011\u0019\tII\u0006a\u0001k\u0005\u0011\u0001\u0010\r"
)
public interface GroupProduct20 extends Group, MonoidProduct20 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   Group structure9();

   Group structure10();

   Group structure11();

   Group structure12();

   Group structure13();

   Group structure14();

   Group structure15();

   Group structure16();

   Group structure17();

   Group structure18();

   Group structure19();

   Group structure20();

   // $FF: synthetic method
   static Tuple20 inverse$(final GroupProduct20 $this, final Tuple20 x0) {
      return $this.inverse(x0);
   }

   default Tuple20 inverse(final Tuple20 x0) {
      return new Tuple20(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()), this.structure15().inverse(x0._15()), this.structure16().inverse(x0._16()), this.structure17().inverse(x0._17()), this.structure18().inverse(x0._18()), this.structure19().inverse(x0._19()), this.structure20().inverse(x0._20()));
   }

   static void $init$(final GroupProduct20 $this) {
   }
}
