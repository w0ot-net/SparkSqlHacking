package spire.std;

import cats.kernel.Monoid;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ue\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u00111\u0003\u0001\u0007\u0004\u0005U\u0001bBA\r\u0001\u0019\r\u00111\u0004\u0005\b\u0003?\u0001a1AA\u0011\u0011\u001d\t)\u0003\u0001D\u0002\u0003OAq!a\u000b\u0001\r\u0007\ti\u0003C\u0004\u00022\u00011\u0019!a\r\t\u000f\u0005]\u0002Ab\u0001\u0002:!9\u0011Q\b\u0001\u0007\u0004\u0005}\u0002bBA\"\u0001\u0019\r\u0011Q\t\u0005\b\u0003\u0013\u0002a1AA&\u0011\u001d\ty\u0005\u0001D\u0002\u0003#Bq!!\u0016\u0001\r\u0007\t9\u0006C\u0004\u0002\\\u00011\u0019!!\u0018\t\u000f\u0005\u0005\u0004Ab\u0001\u0002d!9\u0011q\r\u0001\u0007\u0004\u0005%\u0004bBA7\u0001\u0019\r\u0011q\u000e\u0005\b\u0003g\u0002a1AA;\u0011\u001d\tI\b\u0001D\u0002\u0003wBq!a \u0001\r\u0007\t\t\tC\u0004\u0002\u0006\u00021\u0019!a\"\t\u000f\u0005-\u0005Ab\u0001\u0002\u000e\"9\u0011\u0011\u0013\u0001\u0005\u0002\u0005M%aD'p]>LG\r\u0015:pIV\u001cGOM\u0019\u000b\u0005iY\u0012aA:uI*\tA$A\u0003ta&\u0014X-\u0006\f\u001fw\u0015C5JT)U/jk\u0006m\u00194jY>\u0014X\u000f_>\u007f'\u0015\u0001q$JA\u0001!\t\u00013%D\u0001\"\u0015\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013\"\u0005\u0019\te.\u001f*fMB\u0019ae\r\u001c\u000f\u0005\u001d\u0002dB\u0001\u0015/\u001d\tIS&D\u0001+\u0015\tYC&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005a\u0012BA\u0018\u001c\u0003\u001d\tGnZ3ce\u0006L!!\r\u001a\u0002\u000fA\f7m[1hK*\u0011qfG\u0005\u0003iU\u0012a!T8o_&$'BA\u00193!]\u0001s'\u000f#H\u00156\u00036KV-]?\n,\u0007n\u001b8ri^TX0\u0003\u00029C\t9A+\u001e9mKJ\n\u0004C\u0001\u001e<\u0019\u0001!Q\u0001\u0010\u0001C\u0002u\u0012\u0011!Q\t\u0003}\u0005\u0003\"\u0001I \n\u0005\u0001\u000b#a\u0002(pi\"Lgn\u001a\t\u0003A\tK!aQ\u0011\u0003\u0007\u0005s\u0017\u0010\u0005\u0002;\u000b\u0012)a\t\u0001b\u0001{\t\t!\t\u0005\u0002;\u0011\u0012)\u0011\n\u0001b\u0001{\t\t1\t\u0005\u0002;\u0017\u0012)A\n\u0001b\u0001{\t\tA\t\u0005\u0002;\u001d\u0012)q\n\u0001b\u0001{\t\tQ\t\u0005\u0002;#\u0012)!\u000b\u0001b\u0001{\t\ta\t\u0005\u0002;)\u0012)Q\u000b\u0001b\u0001{\t\tq\t\u0005\u0002;/\u0012)\u0001\f\u0001b\u0001{\t\t\u0001\n\u0005\u0002;5\u0012)1\f\u0001b\u0001{\t\t\u0011\n\u0005\u0002;;\u0012)a\f\u0001b\u0001{\t\t!\n\u0005\u0002;A\u0012)\u0011\r\u0001b\u0001{\t\t1\n\u0005\u0002;G\u0012)A\r\u0001b\u0001{\t\tA\n\u0005\u0002;M\u0012)q\r\u0001b\u0001{\t\tQ\n\u0005\u0002;S\u0012)!\u000e\u0001b\u0001{\t\ta\n\u0005\u0002;Y\u0012)Q\u000e\u0001b\u0001{\t\tq\n\u0005\u0002;_\u0012)\u0001\u000f\u0001b\u0001{\t\t\u0001\u000b\u0005\u0002;e\u0012)1\u000f\u0001b\u0001{\t\t\u0011\u000b\u0005\u0002;k\u0012)a\u000f\u0001b\u0001{\t\t!\u000b\u0005\u0002;q\u0012)\u0011\u0010\u0001b\u0001{\t\t1\u000b\u0005\u0002;w\u0012)A\u0010\u0001b\u0001{\t\tA\u000b\u0005\u0002;}\u0012)q\u0010\u0001b\u0001{\t\tQ\u000bE\r\u0002\u0004\u0005\u0015\u0011\bR$K\u001bB\u001bf+\u0017/`E\u0016D7N\\9uojlX\"A\r\n\u0007\u0005\u001d\u0011D\u0001\nTK6LwM]8vaB\u0013x\u000eZ;diJ\n\u0014A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002\u000eA\u0019\u0001%a\u0004\n\u0007\u0005E\u0011E\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\u0011\u0011q\u0003\t\u0004MMJ\u0014AC:ueV\u001cG/\u001e:feU\u0011\u0011Q\u0004\t\u0004MM\"\u0015AC:ueV\u001cG/\u001e:fgU\u0011\u00111\u0005\t\u0004MM:\u0015AC:ueV\u001cG/\u001e:fiU\u0011\u0011\u0011\u0006\t\u0004MMR\u0015AC:ueV\u001cG/\u001e:fkU\u0011\u0011q\u0006\t\u0004MMj\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u0011Q\u0007\t\u0004MM\u0002\u0016AC:ueV\u001cG/\u001e:foU\u0011\u00111\b\t\u0004MM\u001a\u0016AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\t\t\u0004MM2\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\t\t\u0004MMJ\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\u0014\u0011\u0007\u0019\u001aD,A\u0006tiJ,8\r^;sKF\nTCAA*!\r13gX\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002ZA\u0019ae\r2\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003?\u00022AJ\u001af\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005\u0015\u0004c\u0001\u00144Q\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\tY\u0007E\u0002'g-\f1b\u001d;sk\u000e$XO]32mU\u0011\u0011\u0011\u000f\t\u0004MMr\u0017aC:ueV\u001cG/\u001e:fc]*\"!a\u001e\u0011\u0007\u0019\u001a\u0014/A\u0006tiJ,8\r^;sKFBTCAA?!\r13\u0007^\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014(\u0006\u0002\u0002\u0004B\u0019aeM<\u0002\u0017M$(/^2ukJ,'\u0007M\u000b\u0003\u0003\u0013\u00032AJ\u001a{\u0003-\u0019HO];diV\u0014XMM\u0019\u0016\u0005\u0005=\u0005c\u0001\u00144{\u0006)Q-\u001c9usV\ta\u0007"
)
public interface MonoidProduct21 extends Monoid, SemigroupProduct21 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   Monoid structure8();

   Monoid structure9();

   Monoid structure10();

   Monoid structure11();

   Monoid structure12();

   Monoid structure13();

   Monoid structure14();

   Monoid structure15();

   Monoid structure16();

   Monoid structure17();

   Monoid structure18();

   Monoid structure19();

   Monoid structure20();

   Monoid structure21();

   // $FF: synthetic method
   static Tuple21 empty$(final MonoidProduct21 $this) {
      return $this.empty();
   }

   default Tuple21 empty() {
      return new Tuple21(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty(), this.structure15().empty(), this.structure16().empty(), this.structure17().empty(), this.structure18().empty(), this.structure19().empty(), this.structure20().empty(), this.structure21().empty());
   }

   static void $init$(final MonoidProduct21 $this) {
   }
}
