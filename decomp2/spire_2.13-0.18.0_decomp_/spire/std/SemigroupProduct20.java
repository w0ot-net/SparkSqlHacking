package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001C\f\u0019!\u0003\r\tA\u0007\u000f\t\u000bq\u0004A\u0011A?\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u00011\u0019!a\u0015\t\u000f\u0005]\u0003Ab\u0001\u0002Z!9\u0011Q\f\u0001\u0007\u0004\u0005}\u0003bBA2\u0001\u0019\r\u0011Q\r\u0005\b\u0003S\u0002a1AA6\u0011\u001d\ty\u0007\u0001D\u0002\u0003cBq!!\u001e\u0001\r\u0007\t9\bC\u0004\u0002|\u0001!\t!! \u0003%M+W.[4s_V\u0004\bK]8ek\u000e$(\u0007\r\u0006\u00033i\t1a\u001d;e\u0015\u0005Y\u0012!B:qSJ,W#F\u000f;\t\u001eSU\nU*W3r{&-\u001a5l]F$xO_\n\u0004\u0001y!\u0003CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g\rE\u0002&eUr!AJ\u0018\u000f\u0005\u001djcB\u0001\u0015-\u001b\u0005I#B\u0001\u0016,\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u000e\n\u00059R\u0012aB1mO\u0016\u0014'/Y\u0005\u0003aE\nq\u0001]1dW\u0006<WM\u0003\u0002/5%\u00111\u0007\u000e\u0002\n'\u0016l\u0017n\u001a:pkBT!\u0001M\u0019\u0011-}1\u0004h\u0011$J\u0019>\u0013V\u000bW._C\u0012<'.\u001c9tmfL!a\u000e\u0011\u0003\u000fQ+\b\u000f\\33aA\u0011\u0011H\u000f\u0007\u0001\t\u0015Y\u0004A1\u0001=\u0005\u0005\t\u0015CA\u001fA!\tyb(\u0003\u0002@A\t9aj\u001c;iS:<\u0007CA\u0010B\u0013\t\u0011\u0005EA\u0002B]f\u0004\"!\u000f#\u0005\u000b\u0015\u0003!\u0019\u0001\u001f\u0003\u0003\t\u0003\"!O$\u0005\u000b!\u0003!\u0019\u0001\u001f\u0003\u0003\r\u0003\"!\u000f&\u0005\u000b-\u0003!\u0019\u0001\u001f\u0003\u0003\u0011\u0003\"!O'\u0005\u000b9\u0003!\u0019\u0001\u001f\u0003\u0003\u0015\u0003\"!\u000f)\u0005\u000bE\u0003!\u0019\u0001\u001f\u0003\u0003\u0019\u0003\"!O*\u0005\u000bQ\u0003!\u0019\u0001\u001f\u0003\u0003\u001d\u0003\"!\u000f,\u0005\u000b]\u0003!\u0019\u0001\u001f\u0003\u0003!\u0003\"!O-\u0005\u000bi\u0003!\u0019\u0001\u001f\u0003\u0003%\u0003\"!\u000f/\u0005\u000bu\u0003!\u0019\u0001\u001f\u0003\u0003)\u0003\"!O0\u0005\u000b\u0001\u0004!\u0019\u0001\u001f\u0003\u0003-\u0003\"!\u000f2\u0005\u000b\r\u0004!\u0019\u0001\u001f\u0003\u00031\u0003\"!O3\u0005\u000b\u0019\u0004!\u0019\u0001\u001f\u0003\u00035\u0003\"!\u000f5\u0005\u000b%\u0004!\u0019\u0001\u001f\u0003\u00039\u0003\"!O6\u0005\u000b1\u0004!\u0019\u0001\u001f\u0003\u0003=\u0003\"!\u000f8\u0005\u000b=\u0004!\u0019\u0001\u001f\u0003\u0003A\u0003\"!O9\u0005\u000bI\u0004!\u0019\u0001\u001f\u0003\u0003E\u0003\"!\u000f;\u0005\u000bU\u0004!\u0019\u0001\u001f\u0003\u0003I\u0003\"!O<\u0005\u000ba\u0004!\u0019\u0001\u001f\u0003\u0003M\u0003\"!\u000f>\u0005\u000bm\u0004!\u0019\u0001\u001f\u0003\u0003Q\u000ba\u0001J5oSR$C#\u0001@\u0011\u0005}y\u0018bAA\u0001A\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0003\u0003\u000f\u00012!\n\u001a9\u0003)\u0019HO];diV\u0014XMM\u000b\u0003\u0003\u001b\u00012!\n\u001aD\u0003)\u0019HO];diV\u0014XmM\u000b\u0003\u0003'\u00012!\n\u001aG\u0003)\u0019HO];diV\u0014X\rN\u000b\u0003\u00033\u00012!\n\u001aJ\u0003)\u0019HO];diV\u0014X-N\u000b\u0003\u0003?\u00012!\n\u001aM\u0003)\u0019HO];diV\u0014XMN\u000b\u0003\u0003K\u00012!\n\u001aP\u0003)\u0019HO];diV\u0014XmN\u000b\u0003\u0003W\u00012!\n\u001aS\u0003)\u0019HO];diV\u0014X\rO\u000b\u0003\u0003c\u00012!\n\u001aV\u0003)\u0019HO];diV\u0014X-O\u000b\u0003\u0003o\u00012!\n\u001aY\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005u\u0002cA\u001337\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\t\u0019\u0005E\u0002&ey\u000b1b\u001d;sk\u000e$XO]32eU\u0011\u0011\u0011\n\t\u0004KI\n\u0017aC:ueV\u001cG/\u001e:fcM*\"!a\u0014\u0011\u0007\u0015\u0012D-A\u0006tiJ,8\r^;sKF\"TCAA+!\r)#gZ\u0001\fgR\u0014Xo\u0019;ve\u0016\fT'\u0006\u0002\u0002\\A\u0019QE\r6\u0002\u0017M$(/^2ukJ,\u0017GN\u000b\u0003\u0003C\u00022!\n\u001an\u0003-\u0019HO];diV\u0014X-M\u001c\u0016\u0005\u0005\u001d\u0004cA\u00133a\u0006Y1\u000f\u001e:vGR,(/Z\u00199+\t\ti\u0007E\u0002&eM\f1b\u001d;sk\u000e$XO]32sU\u0011\u00111\u000f\t\u0004KI2\u0018aC:ueV\u001cG/\u001e:feA*\"!!\u001f\u0011\u0007\u0015\u0012\u00140A\u0004d_6\u0014\u0017N\\3\u0015\u000bU\ny(a!\t\r\u0005\u0005e\u00031\u00016\u0003\tA\b\u0007\u0003\u0004\u0002\u0006Z\u0001\r!N\u0001\u0003qF\u0002"
)
public interface SemigroupProduct20 extends Semigroup {
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

   Semigroup structure14();

   Semigroup structure15();

   Semigroup structure16();

   Semigroup structure17();

   Semigroup structure18();

   Semigroup structure19();

   Semigroup structure20();

   // $FF: synthetic method
   static Tuple20 combine$(final SemigroupProduct20 $this, final Tuple20 x0, final Tuple20 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple20 combine(final Tuple20 x0, final Tuple20 x1) {
      return new Tuple20(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()), this.structure15().combine(x0._15(), x1._15()), this.structure16().combine(x0._16(), x1._16()), this.structure17().combine(x0._17(), x1._17()), this.structure18().combine(x0._18(), x1._18()), this.structure19().combine(x0._19(), x1._19()), this.structure20().combine(x0._20(), x1._20()));
   }

   static void $init$(final SemigroupProduct20 $this) {
   }
}
