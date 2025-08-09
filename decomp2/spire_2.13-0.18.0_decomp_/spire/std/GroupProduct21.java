package spire.std;

import cats.kernel.Group;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u00111\u0003\u0001\u0007\u0004\u0005U\u0001bBA\r\u0001\u0019\r\u00111\u0004\u0005\b\u0003?\u0001a1AA\u0011\u0011\u001d\t)\u0003\u0001D\u0002\u0003OAq!a\u000b\u0001\r\u0007\ti\u0003C\u0004\u00022\u00011\u0019!a\r\t\u000f\u0005]\u0002Ab\u0001\u0002:!9\u0011Q\b\u0001\u0007\u0004\u0005}\u0002bBA\"\u0001\u0019\r\u0011Q\t\u0005\b\u0003\u0013\u0002a1AA&\u0011\u001d\ty\u0005\u0001D\u0002\u0003#Bq!!\u0016\u0001\r\u0007\t9\u0006C\u0004\u0002\\\u00011\u0019!!\u0018\t\u000f\u0005\u0005\u0004Ab\u0001\u0002d!9\u0011q\r\u0001\u0007\u0004\u0005%\u0004bBA7\u0001\u0019\r\u0011q\u000e\u0005\b\u0003g\u0002a1AA;\u0011\u001d\tI\b\u0001D\u0002\u0003wBq!a \u0001\r\u0007\t\t\tC\u0004\u0002\u0006\u00021\u0019!a\"\t\u000f\u0005-\u0005Ab\u0001\u0002\u000e\"9\u0011\u0011\u0013\u0001\u0005\u0002\u0005M%AD$s_V\u0004\bK]8ek\u000e$('\r\u0006\u00035m\t1a\u001d;e\u0015\u0005a\u0012!B:qSJ,WC\u0006\u0010<\u000b\"[e*\u0015+X5v\u00037MZ5m_J,\bp\u001f@\u0014\u000b\u0001yR%!\u0001\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\r13G\u000e\b\u0003OAr!\u0001\u000b\u0018\u000f\u0005%jS\"\u0001\u0016\u000b\u0005-b\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003qI!aL\u000e\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011GM\u0001\ba\u0006\u001c7.Y4f\u0015\ty3$\u0003\u00025k\t)qI]8va*\u0011\u0011G\r\t\u0018A]JDi\u0012&N!N3\u0016\fX0cK\"\\g.\u001d;xuvL!\u0001O\u0011\u0003\u000fQ+\b\u000f\\33cA\u0011!h\u000f\u0007\u0001\t\u0015a\u0004A1\u0001>\u0005\u0005\t\u0015C\u0001 B!\t\u0001s(\u0003\u0002AC\t9aj\u001c;iS:<\u0007C\u0001\u0011C\u0013\t\u0019\u0015EA\u0002B]f\u0004\"AO#\u0005\u000b\u0019\u0003!\u0019A\u001f\u0003\u0003\t\u0003\"A\u000f%\u0005\u000b%\u0003!\u0019A\u001f\u0003\u0003\r\u0003\"AO&\u0005\u000b1\u0003!\u0019A\u001f\u0003\u0003\u0011\u0003\"A\u000f(\u0005\u000b=\u0003!\u0019A\u001f\u0003\u0003\u0015\u0003\"AO)\u0005\u000bI\u0003!\u0019A\u001f\u0003\u0003\u0019\u0003\"A\u000f+\u0005\u000bU\u0003!\u0019A\u001f\u0003\u0003\u001d\u0003\"AO,\u0005\u000ba\u0003!\u0019A\u001f\u0003\u0003!\u0003\"A\u000f.\u0005\u000bm\u0003!\u0019A\u001f\u0003\u0003%\u0003\"AO/\u0005\u000by\u0003!\u0019A\u001f\u0003\u0003)\u0003\"A\u000f1\u0005\u000b\u0005\u0004!\u0019A\u001f\u0003\u0003-\u0003\"AO2\u0005\u000b\u0011\u0004!\u0019A\u001f\u0003\u00031\u0003\"A\u000f4\u0005\u000b\u001d\u0004!\u0019A\u001f\u0003\u00035\u0003\"AO5\u0005\u000b)\u0004!\u0019A\u001f\u0003\u00039\u0003\"A\u000f7\u0005\u000b5\u0004!\u0019A\u001f\u0003\u0003=\u0003\"AO8\u0005\u000bA\u0004!\u0019A\u001f\u0003\u0003A\u0003\"A\u000f:\u0005\u000bM\u0004!\u0019A\u001f\u0003\u0003E\u0003\"AO;\u0005\u000bY\u0004!\u0019A\u001f\u0003\u0003I\u0003\"A\u000f=\u0005\u000be\u0004!\u0019A\u001f\u0003\u0003M\u0003\"AO>\u0005\u000bq\u0004!\u0019A\u001f\u0003\u0003Q\u0003\"A\u000f@\u0005\u000b}\u0004!\u0019A\u001f\u0003\u0003U\u0003\u0012$a\u0001\u0002\u0006e\"uIS'Q'ZKFl\u00182fQ.t\u0017\u000f^<{{6\t\u0011$C\u0002\u0002\be\u0011q\"T8o_&$\u0007K]8ek\u000e$('M\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0005\u00055\u0001c\u0001\u0011\u0002\u0010%\u0019\u0011\u0011C\u0011\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fTCAA\f!\r13'O\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014TCAA\u000f!\r13\u0007R\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cTCAA\u0012!\r13gR\u0001\u000bgR\u0014Xo\u0019;ve\u0016$TCAA\u0015!\r13GS\u0001\u000bgR\u0014Xo\u0019;ve\u0016,TCAA\u0018!\r13'T\u0001\u000bgR\u0014Xo\u0019;ve\u00164TCAA\u001b!\r13\u0007U\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u001e!\r13gU\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA!!\r13GV\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA$!\r13'W\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u0002NA\u0019ae\r/\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003'\u00022AJ\u001a`\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005e\u0003c\u0001\u00144E\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\ty\u0006E\u0002'g\u0015\f1b\u001d;sk\u000e$XO]32iU\u0011\u0011Q\r\t\u0004MMB\u0017aC:ueV\u001cG/\u001e:fcU*\"!a\u001b\u0011\u0007\u0019\u001a4.A\u0006tiJ,8\r^;sKF2TCAA9!\r13G\\\u0001\fgR\u0014Xo\u0019;ve\u0016\ft'\u0006\u0002\u0002xA\u0019aeM9\u0002\u0017M$(/^2ukJ,\u0017\u0007O\u000b\u0003\u0003{\u00022AJ\u001au\u0003-\u0019HO];diV\u0014X-M\u001d\u0016\u0005\u0005\r\u0005c\u0001\u00144o\u0006Y1\u000f\u001e:vGR,(/\u001a\u001a1+\t\tI\tE\u0002'gi\f1b\u001d;sk\u000e$XO]33cU\u0011\u0011q\u0012\t\u0004MMj\u0018aB5om\u0016\u00148/\u001a\u000b\u0004m\u0005U\u0005BBAL/\u0001\u0007a'\u0001\u0002ya\u0001"
)
public interface GroupProduct21 extends Group, MonoidProduct21 {
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

   Group structure21();

   // $FF: synthetic method
   static Tuple21 inverse$(final GroupProduct21 $this, final Tuple21 x0) {
      return $this.inverse(x0);
   }

   default Tuple21 inverse(final Tuple21 x0) {
      return new Tuple21(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()), this.structure15().inverse(x0._15()), this.structure16().inverse(x0._16()), this.structure17().inverse(x0._17()), this.structure18().inverse(x0._18()), this.structure19().inverse(x0._19()), this.structure20().inverse(x0._20()), this.structure21().inverse(x0._21()));
   }

   static void $init$(final GroupProduct21 $this) {
   }
}
