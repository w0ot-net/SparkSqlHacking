package spire.std;

import cats.kernel.Group;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006)\u0002!\t!\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u00021\u0019A\u001a\u0005\u0006Q\u00021\u0019!\u001b\u0005\u0006W\u00021\u0019\u0001\u001c\u0005\u0006]\u00021\u0019a\u001c\u0005\u0006c\u00021\u0019A\u001d\u0005\u0006i\u0002!\t!\u001e\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r^\u001d\u000b\u00059y\u0011aA:uI*\t\u0001#A\u0003ta&\u0014X-\u0006\u0006\u0013_ebtHQ#I\u0017:\u001bB\u0001A\n\u001a!B\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\u00042AG\u0014+\u001d\tYBE\u0004\u0002\u001dE9\u0011Q$I\u0007\u0002=)\u0011q\u0004I\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001#\u0003\u0002$\u001f\u00059\u0011\r\\4fEJ\f\u0017BA\u0013'\u0003\u001d\u0001\u0018mY6bO\u0016T!aI\b\n\u0005!J#!B$s_V\u0004(BA\u0013'!-!2&\f\u001d<}\u0005#uIS'\n\u00051*\"A\u0002+va2,\u0017\b\u0005\u0002/_1\u0001A!\u0002\u0019\u0001\u0005\u0004\t$!A!\u0012\u0005I*\u0004C\u0001\u000b4\u0013\t!TCA\u0004O_RD\u0017N\\4\u0011\u0005Q1\u0014BA\u001c\u0016\u0005\r\te.\u001f\t\u0003]e\"QA\u000f\u0001C\u0002E\u0012\u0011A\u0011\t\u0003]q\"Q!\u0010\u0001C\u0002E\u0012\u0011a\u0011\t\u0003]}\"Q\u0001\u0011\u0001C\u0002E\u0012\u0011\u0001\u0012\t\u0003]\t#Qa\u0011\u0001C\u0002E\u0012\u0011!\u0012\t\u0003]\u0015#QA\u0012\u0001C\u0002E\u0012\u0011A\u0012\t\u0003]!#Q!\u0013\u0001C\u0002E\u0012\u0011a\u0012\t\u0003]-#Q\u0001\u0014\u0001C\u0002E\u0012\u0011\u0001\u0013\t\u0003]9#Qa\u0014\u0001C\u0002E\u0012\u0011!\u0013\t\f#Jk\u0003h\u000f B\t\u001eSU*D\u0001\u000e\u0013\t\u0019VB\u0001\bN_:|\u0017\u000e\u001a)s_\u0012,8\r^\u001d\u0002\r\u0011Jg.\u001b;%)\u00051\u0006C\u0001\u000bX\u0013\tAVC\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\fE\u0002\u001bO5\n!b\u001d;sk\u000e$XO]33+\u0005q\u0006c\u0001\u000e(q\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003\u0005\u00042AG\u0014<\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002IB\u0019!d\n \u0002\u0015M$(/^2ukJ,W'F\u0001h!\rQr%Q\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#\u00016\u0011\u0007i9C)\u0001\u0006tiJ,8\r^;sK^*\u0012!\u001c\t\u00045\u001d:\u0015AC:ueV\u001cG/\u001e:fqU\t\u0001\u000fE\u0002\u001bO)\u000b!b\u001d;sk\u000e$XO]3:+\u0005\u0019\bc\u0001\u000e(\u001b\u00069\u0011N\u001c<feN,GC\u0001\u0016w\u0011\u001598\u00021\u0001+\u0003\tA\b\u0007"
)
public interface GroupProduct9 extends Group, MonoidProduct9 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   Group structure9();

   // $FF: synthetic method
   static Tuple9 inverse$(final GroupProduct9 $this, final Tuple9 x0) {
      return $this.inverse(x0);
   }

   default Tuple9 inverse(final Tuple9 x0) {
      return new Tuple9(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()));
   }

   static void $init$(final GroupProduct9 $this) {
   }
}
