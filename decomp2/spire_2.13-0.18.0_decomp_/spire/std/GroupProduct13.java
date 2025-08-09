package spire.std;

import cats.kernel.Group;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b\u0001\u0003\t\u0012!\u0003\r\taE\u000b\t\u000b\u0011\u0004A\u0011A3\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\t\u0003\t\u0019C\u0001\bHe>,\b\u000f\u0015:pIV\u001cG/M\u001a\u000b\u0005I\u0019\u0012aA:uI*\tA#A\u0003ta&\u0014X-\u0006\b\u0017gu\u00025IR%M\u001fJ+\u0006l\u00170\u0014\t\u00019R\u0004\u0019\t\u00031mi\u0011!\u0007\u0006\u00025\u0005)1oY1mC&\u0011A$\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007yYcF\u0004\u0002 Q9\u0011\u0001E\n\b\u0003C\u0015j\u0011A\t\u0006\u0003G\u0011\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002)%\u0011qeE\u0001\bC2<WM\u0019:b\u0013\tI#&A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u001d\u001a\u0012B\u0001\u0017.\u0005\u00159%o\\;q\u0015\tI#\u0006E\b\u0019_EbtHQ#I\u0017:\u000bFk\u0016.^\u0013\t\u0001\u0014DA\u0004UkBdW-M\u001a\u0011\u0005I\u001aD\u0002\u0001\u0003\u0006i\u0001\u0011\r!\u000e\u0002\u0002\u0003F\u0011a'\u000f\t\u00031]J!\u0001O\r\u0003\u000f9{G\u000f[5oOB\u0011\u0001DO\u0005\u0003we\u00111!\u00118z!\t\u0011T\bB\u0003?\u0001\t\u0007QGA\u0001C!\t\u0011\u0004\tB\u0003B\u0001\t\u0007QGA\u0001D!\t\u00114\tB\u0003E\u0001\t\u0007QGA\u0001E!\t\u0011d\tB\u0003H\u0001\t\u0007QGA\u0001F!\t\u0011\u0014\nB\u0003K\u0001\t\u0007QGA\u0001G!\t\u0011D\nB\u0003N\u0001\t\u0007QGA\u0001H!\t\u0011t\nB\u0003Q\u0001\t\u0007QGA\u0001I!\t\u0011$\u000bB\u0003T\u0001\t\u0007QGA\u0001J!\t\u0011T\u000bB\u0003W\u0001\t\u0007QGA\u0001K!\t\u0011\u0004\fB\u0003Z\u0001\t\u0007QGA\u0001L!\t\u00114\fB\u0003]\u0001\t\u0007QGA\u0001M!\t\u0011d\fB\u0003`\u0001\t\u0007QGA\u0001N!=\t'-\r\u001f@\u0005\u0016C5JT)U/jkV\"A\t\n\u0005\r\f\"aD'p]>LG\r\u0015:pIV\u001cG/M\u001a\u0002\r\u0011Jg.\u001b;%)\u00051\u0007C\u0001\rh\u0013\tA\u0017D\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\u000eE\u0002\u001fWE\n!b\u001d;sk\u000e$XO]33+\u0005q\u0007c\u0001\u0010,y\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003E\u00042AH\u0016@\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002iB\u0019ad\u000b\"\u0002\u0015M$(/^2ukJ,W'F\u0001x!\rq2&R\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#\u0001>\u0011\u0007yY\u0003*\u0001\u0006tiJ,8\r^;sK^*\u0012! \t\u0004=-Z\u0015AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\u0001\t\u0004=-r\u0015AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\u0001\t\u0004=-\n\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\u0004\u0011\u0007yYC+A\u0006tiJ,8\r^;sKF\nTCAA\n!\rq2fV\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002\u001aA\u0019ad\u000b.\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003?\u00012AH\u0016^\u0003\u001dIgN^3sg\u0016$2ALA\u0013\u0011\u0019\t9c\u0004a\u0001]\u0005\u0011\u0001\u0010\r"
)
public interface GroupProduct13 extends Group, MonoidProduct13 {
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

   // $FF: synthetic method
   static Tuple13 inverse$(final GroupProduct13 $this, final Tuple13 x0) {
      return $this.inverse(x0);
   }

   default Tuple13 inverse(final Tuple13 x0) {
      return new Tuple13(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()));
   }

   static void $init$(final GroupProduct13 $this) {
   }
}
