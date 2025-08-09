package spire.std;

import cats.kernel.Group;
import scala.Tuple19;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001\u0003\f\u0018!\u0003\r\t!G\u000e\t\u000bq\u0004A\u0011A?\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u00011\u0019!a\u0015\t\u000f\u0005]\u0003Ab\u0001\u0002Z!9\u0011Q\f\u0001\u0007\u0004\u0005}\u0003bBA2\u0001\u0019\r\u0011Q\r\u0005\b\u0003S\u0002a1AA6\u0011\u001d\ty\u0007\u0001D\u0002\u0003cBq!!\u001e\u0001\t\u0003\t9H\u0001\bHe>,\b\u000f\u0015:pIV\u001cG/M\u001d\u000b\u0005aI\u0012aA:uI*\t!$A\u0003ta&\u0014X-\u0006\u000b\u001ds\r3\u0015\nT(S+b[f,\u00193hU6\u00048O^\n\u0005\u0001u\u0019\u0003\u0010\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0004B]f\u0014VM\u001a\t\u0004IE\"dBA\u0013/\u001d\t1CF\u0004\u0002(W5\t\u0001F\u0003\u0002*U\u00051AH]8piz\u001a\u0001!C\u0001\u001b\u0013\ti\u0013$A\u0004bY\u001e,'M]1\n\u0005=\u0002\u0014a\u00029bG.\fw-\u001a\u0006\u0003[eI!AM\u001a\u0003\u000b\u001d\u0013x.\u001e9\u000b\u0005=\u0002\u0004#\u0006\u00106o\t+\u0005j\u0013(R)^SV\fY2gS2|'/^\u0005\u0003m}\u0011q\u0001V;qY\u0016\f\u0014\b\u0005\u00029s1\u0001A!\u0002\u001e\u0001\u0005\u0004Y$!A!\u0012\u0005qz\u0004C\u0001\u0010>\u0013\tqtDA\u0004O_RD\u0017N\\4\u0011\u0005y\u0001\u0015BA! \u0005\r\te.\u001f\t\u0003q\r#Q\u0001\u0012\u0001C\u0002m\u0012\u0011A\u0011\t\u0003q\u0019#Qa\u0012\u0001C\u0002m\u0012\u0011a\u0011\t\u0003q%#QA\u0013\u0001C\u0002m\u0012\u0011\u0001\u0012\t\u0003q1#Q!\u0014\u0001C\u0002m\u0012\u0011!\u0012\t\u0003q=#Q\u0001\u0015\u0001C\u0002m\u0012\u0011A\u0012\t\u0003qI#Qa\u0015\u0001C\u0002m\u0012\u0011a\u0012\t\u0003qU#QA\u0016\u0001C\u0002m\u0012\u0011\u0001\u0013\t\u0003qa#Q!\u0017\u0001C\u0002m\u0012\u0011!\u0013\t\u0003qm#Q\u0001\u0018\u0001C\u0002m\u0012\u0011A\u0013\t\u0003qy#Qa\u0018\u0001C\u0002m\u0012\u0011a\u0013\t\u0003q\u0005$QA\u0019\u0001C\u0002m\u0012\u0011\u0001\u0014\t\u0003q\u0011$Q!\u001a\u0001C\u0002m\u0012\u0011!\u0014\t\u0003q\u001d$Q\u0001\u001b\u0001C\u0002m\u0012\u0011A\u0014\t\u0003q)$Qa\u001b\u0001C\u0002m\u0012\u0011a\u0014\t\u0003q5$QA\u001c\u0001C\u0002m\u0012\u0011\u0001\u0015\t\u0003qA$Q!\u001d\u0001C\u0002m\u0012\u0011!\u0015\t\u0003qM$Q\u0001\u001e\u0001C\u0002m\u0012\u0011A\u0015\t\u0003qY$Qa\u001e\u0001C\u0002m\u0012\u0011a\u0015\t\u0016sj<$)\u0012%L\u001dF#vKW/aG\u001aLGn\u001c:v\u001b\u00059\u0012BA>\u0018\u0005=iuN\\8jIB\u0013x\u000eZ;diFJ\u0014A\u0002\u0013j]&$H\u0005F\u0001\u007f!\tqr0C\u0002\u0002\u0002}\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0005\u0005\u001d\u0001c\u0001\u00132o\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0005\u00055\u0001c\u0001\u00132\u0005\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0005\u0005M\u0001c\u0001\u00132\u000b\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005e\u0001c\u0001\u00132\u0011\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005}\u0001c\u0001\u00132\u0017\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u0005\u0015\u0002c\u0001\u00132\u001d\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005-\u0002c\u0001\u00132#\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005E\u0002c\u0001\u00132)\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005]\u0002c\u0001\u00132/\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\ti\u0004E\u0002%ci\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\t\t\u0004IEj\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0013\u0011\u0007\u0011\n\u0004-A\u0006tiJ,8\r^;sKF\u001aTCAA(!\r!\u0013gY\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002VA\u0019A%\r4\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u00037\u00022\u0001J\u0019j\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005\u0005\u0004c\u0001\u00132Y\u0006Y1\u000f\u001e:vGR,(/Z\u00198+\t\t9\u0007E\u0002%c=\f1b\u001d;sk\u000e$XO]32qU\u0011\u0011Q\u000e\t\u0004IE\u0012\u0018aC:ueV\u001cG/\u001e:fce*\"!a\u001d\u0011\u0007\u0011\nT/A\u0004j]Z,'o]3\u0015\u0007Q\nI\b\u0003\u0004\u0002|U\u0001\r\u0001N\u0001\u0003qB\u0002"
)
public interface GroupProduct19 extends Group, MonoidProduct19 {
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

   // $FF: synthetic method
   static Tuple19 inverse$(final GroupProduct19 $this, final Tuple19 x0) {
      return $this.inverse(x0);
   }

   default Tuple19 inverse(final Tuple19 x0) {
      return new Tuple19(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()), this.structure15().inverse(x0._15()), this.structure16().inverse(x0._16()), this.structure17().inverse(x0._17()), this.structure18().inverse(x0._18()), this.structure19().inverse(x0._19()));
   }

   static void $init$(final GroupProduct19 $this) {
   }
}
