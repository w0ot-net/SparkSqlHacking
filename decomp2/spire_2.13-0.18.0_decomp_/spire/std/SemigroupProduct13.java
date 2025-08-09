package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b\u0001\u0003\t\u0012!\u0003\r\taE\u000b\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0015\u0004a1\u00014\t\u000b!\u0004a1A5\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001C\u0001\u00037\u0011!cU3nS\u001e\u0014x.\u001e9Qe>$Wo\u0019;2g)\u0011!cE\u0001\u0004gR$'\"\u0001\u000b\u0002\u000bM\u0004\u0018N]3\u0016\u001dY\u0019T\bQ\"G\u00132{%+\u0016-\\=N\u0019\u0001aF\u000f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\rq2F\f\b\u0003?!r!\u0001\t\u0014\u000f\u0005\u0005*S\"\u0001\u0012\u000b\u0005\r\"\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003QI!aJ\n\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011FK\u0001\ba\u0006\u001c7.Y4f\u0015\t93#\u0003\u0002-[\tI1+Z7jOJ|W\u000f\u001d\u0006\u0003S)\u0002r\u0002G\u00182y}\u0012U\tS&O#R;&,X\u0005\u0003ae\u0011q\u0001V;qY\u0016\f4\u0007\u0005\u00023g1\u0001A!\u0002\u001b\u0001\u0005\u0004)$!A!\u0012\u0005YJ\u0004C\u0001\r8\u0013\tA\u0014DA\u0004O_RD\u0017N\\4\u0011\u0005aQ\u0014BA\u001e\u001a\u0005\r\te.\u001f\t\u0003eu\"QA\u0010\u0001C\u0002U\u0012\u0011A\u0011\t\u0003e\u0001#Q!\u0011\u0001C\u0002U\u0012\u0011a\u0011\t\u0003e\r#Q\u0001\u0012\u0001C\u0002U\u0012\u0011\u0001\u0012\t\u0003e\u0019#Qa\u0012\u0001C\u0002U\u0012\u0011!\u0012\t\u0003e%#QA\u0013\u0001C\u0002U\u0012\u0011A\u0012\t\u0003e1#Q!\u0014\u0001C\u0002U\u0012\u0011a\u0012\t\u0003e=#Q\u0001\u0015\u0001C\u0002U\u0012\u0011\u0001\u0013\t\u0003eI#Qa\u0015\u0001C\u0002U\u0012\u0011!\u0013\t\u0003eU#QA\u0016\u0001C\u0002U\u0012\u0011A\u0013\t\u0003ea#Q!\u0017\u0001C\u0002U\u0012\u0011a\u0013\t\u0003em#Q\u0001\u0018\u0001C\u0002U\u0012\u0011\u0001\u0014\t\u0003ey#Qa\u0018\u0001C\u0002U\u0012\u0011!T\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\t\u0004\"\u0001G2\n\u0005\u0011L\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u00059\u0007c\u0001\u0010,c\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003)\u00042AH\u0016=\u0003)\u0019HO];diV\u0014XmM\u000b\u0002[B\u0019adK \u0002\u0015M$(/^2ukJ,G'F\u0001q!\rq2FQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#A:\u0011\u0007yYS)\u0001\u0006tiJ,8\r^;sKZ*\u0012A\u001e\t\u0004=-B\u0015AC:ueV\u001cG/\u001e:foU\t\u0011\u0010E\u0002\u001fW-\u000b!b\u001d;sk\u000e$XO]39+\u0005a\bc\u0001\u0010,\u001d\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0003}\u00042AH\u0016R\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005\u0015\u0001c\u0001\u0010,)\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\tY\u0001E\u0002\u001fW]\u000b1b\u001d;sk\u000e$XO]32eU\u0011\u0011\u0011\u0003\t\u0004=-R\u0016aC:ueV\u001cG/\u001e:fcM*\"!a\u0006\u0011\u0007yYS,A\u0004d_6\u0014\u0017N\\3\u0015\u000b9\ni\"!\t\t\r\u0005}q\u00021\u0001/\u0003\tA\b\u0007\u0003\u0004\u0002$=\u0001\rAL\u0001\u0003qF\u0002"
)
public interface SemigroupProduct13 extends Semigroup {
   Semigroup structure1();

   Semigroup structure2();

   Semigroup structure3();

   Semigroup structure4();

   Semigroup structure5();

   Semigroup structure6();

   Semigroup structure7();

   Semigroup structure8();

   Semigroup structure9();

   Semigroup structure10();

   Semigroup structure11();

   Semigroup structure12();

   Semigroup structure13();

   // $FF: synthetic method
   static Tuple13 combine$(final SemigroupProduct13 $this, final Tuple13 x0, final Tuple13 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple13 combine(final Tuple13 x0, final Tuple13 x1) {
      return new Tuple13(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()));
   }

   static void $init$(final SemigroupProduct13 $this) {
   }
}
