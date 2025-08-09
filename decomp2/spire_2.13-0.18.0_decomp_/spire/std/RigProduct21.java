package spire.std;

import algebra.ring.Rig;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ue\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u00111\u0003\u0001\u0007\u0004\u0005U\u0001bBA\r\u0001\u0019\r\u00111\u0004\u0005\b\u0003?\u0001a1AA\u0011\u0011\u001d\t)\u0003\u0001D\u0002\u0003OAq!a\u000b\u0001\r\u0007\ti\u0003C\u0004\u00022\u00011\u0019!a\r\t\u000f\u0005]\u0002Ab\u0001\u0002:!9\u0011Q\b\u0001\u0007\u0004\u0005}\u0002bBA\"\u0001\u0019\r\u0011Q\t\u0005\b\u0003\u0013\u0002a1AA&\u0011\u001d\ty\u0005\u0001D\u0002\u0003#Bq!!\u0016\u0001\r\u0007\t9\u0006C\u0004\u0002\\\u00011\u0019!!\u0018\t\u000f\u0005\u0005\u0004Ab\u0001\u0002d!9\u0011q\r\u0001\u0007\u0004\u0005%\u0004bBA7\u0001\u0019\r\u0011q\u000e\u0005\b\u0003g\u0002a1AA;\u0011\u001d\tI\b\u0001D\u0002\u0003wBq!a \u0001\r\u0007\t\t\tC\u0004\u0002\u0006\u00021\u0019!a\"\t\u000f\u0005-\u0005Ab\u0001\u0002\u000e\"9\u0011\u0011\u0013\u0001\u0005\u0002\u0005M%\u0001\u0004*jOB\u0013x\u000eZ;diJ\n$B\u0001\u000e\u001c\u0003\r\u0019H\u000f\u001a\u0006\u00029\u0005)1\u000f]5sKV1bdO#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8skb\\hpE\u0003\u0001?\u0015\n\t\u0001\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VM\u001a\t\u0004MM2dBA\u00141\u001d\tAcF\u0004\u0002*[5\t!F\u0003\u0002,Y\u00051AH]8piz\u001a\u0001!C\u0001\u001d\u0013\ty3$A\u0004bY\u001e,'M]1\n\u0005E\u0012\u0014a\u00029bG.\fw-\u001a\u0006\u0003_mI!\u0001N\u001b\u0003\u0007IKwM\u0003\u00022eA9\u0002eN\u001dE\u000f*k\u0005k\u0015,Z9~\u0013W\r[6ocR<(0`\u0005\u0003q\u0005\u0012q\u0001V;qY\u0016\u0014\u0014\u0007\u0005\u0002;w1\u0001A!\u0002\u001f\u0001\u0005\u0004i$!A!\u0012\u0005y\n\u0005C\u0001\u0011@\u0013\t\u0001\u0015EA\u0004O_RD\u0017N\\4\u0011\u0005\u0001\u0012\u0015BA\"\"\u0005\r\te.\u001f\t\u0003u\u0015#QA\u0012\u0001C\u0002u\u0012\u0011A\u0011\t\u0003u!#Q!\u0013\u0001C\u0002u\u0012\u0011a\u0011\t\u0003u-#Q\u0001\u0014\u0001C\u0002u\u0012\u0011\u0001\u0012\t\u0003u9#Qa\u0014\u0001C\u0002u\u0012\u0011!\u0012\t\u0003uE#QA\u0015\u0001C\u0002u\u0012\u0011A\u0012\t\u0003uQ#Q!\u0016\u0001C\u0002u\u0012\u0011a\u0012\t\u0003u]#Q\u0001\u0017\u0001C\u0002u\u0012\u0011\u0001\u0013\t\u0003ui#Qa\u0017\u0001C\u0002u\u0012\u0011!\u0013\t\u0003uu#QA\u0018\u0001C\u0002u\u0012\u0011A\u0013\t\u0003u\u0001$Q!\u0019\u0001C\u0002u\u0012\u0011a\u0013\t\u0003u\r$Q\u0001\u001a\u0001C\u0002u\u0012\u0011\u0001\u0014\t\u0003u\u0019$Qa\u001a\u0001C\u0002u\u0012\u0011!\u0014\t\u0003u%$QA\u001b\u0001C\u0002u\u0012\u0011A\u0014\t\u0003u1$Q!\u001c\u0001C\u0002u\u0012\u0011a\u0014\t\u0003u=$Q\u0001\u001d\u0001C\u0002u\u0012\u0011\u0001\u0015\t\u0003uI$Qa\u001d\u0001C\u0002u\u0012\u0011!\u0015\t\u0003uU$QA\u001e\u0001C\u0002u\u0012\u0011A\u0015\t\u0003ua$Q!\u001f\u0001C\u0002u\u0012\u0011a\u0015\t\u0003um$Q\u0001 \u0001C\u0002u\u0012\u0011\u0001\u0016\t\u0003uy$Qa \u0001C\u0002u\u0012\u0011!\u0016\t\u001a\u0003\u0007\t)!\u000f#H\u00156\u00036KV-]?\n,\u0007n\u001b8ri^TX0D\u0001\u001a\u0013\r\t9!\u0007\u0002\u0012'\u0016l\u0017N]5oOB\u0013x\u000eZ;diJ\n\u0014A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002\u000eA\u0019\u0001%a\u0004\n\u0007\u0005E\u0011E\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\u0011\u0011q\u0003\t\u0004MMJ\u0014AC:ueV\u001cG/\u001e:feU\u0011\u0011Q\u0004\t\u0004MM\"\u0015AC:ueV\u001cG/\u001e:fgU\u0011\u00111\u0005\t\u0004MM:\u0015AC:ueV\u001cG/\u001e:fiU\u0011\u0011\u0011\u0006\t\u0004MMR\u0015AC:ueV\u001cG/\u001e:fkU\u0011\u0011q\u0006\t\u0004MMj\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u0011Q\u0007\t\u0004MM\u0002\u0016AC:ueV\u001cG/\u001e:foU\u0011\u00111\b\t\u0004MM\u001a\u0016AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\t\t\u0004MM2\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\t\t\u0004MMJ\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\u0014\u0011\u0007\u0019\u001aD,A\u0006tiJ,8\r^;sKF\nTCAA*!\r13gX\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002ZA\u0019ae\r2\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003?\u00022AJ\u001af\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005\u0015\u0004c\u0001\u00144Q\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\tY\u0007E\u0002'g-\f1b\u001d;sk\u000e$XO]32mU\u0011\u0011\u0011\u000f\t\u0004MMr\u0017aC:ueV\u001cG/\u001e:fc]*\"!a\u001e\u0011\u0007\u0019\u001a\u0014/A\u0006tiJ,8\r^;sKFBTCAA?!\r13\u0007^\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014(\u0006\u0002\u0002\u0004B\u0019aeM<\u0002\u0017M$(/^2ukJ,'\u0007M\u000b\u0003\u0003\u0013\u00032AJ\u001a{\u0003-\u0019HO];diV\u0014XMM\u0019\u0016\u0005\u0005=\u0005c\u0001\u00144{\u0006\u0019qN\\3\u0016\u0003Y\u0002"
)
public interface RigProduct21 extends Rig, SemiringProduct21 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   Rig structure6();

   Rig structure7();

   Rig structure8();

   Rig structure9();

   Rig structure10();

   Rig structure11();

   Rig structure12();

   Rig structure13();

   Rig structure14();

   Rig structure15();

   Rig structure16();

   Rig structure17();

   Rig structure18();

   Rig structure19();

   Rig structure20();

   Rig structure21();

   // $FF: synthetic method
   static Tuple21 one$(final RigProduct21 $this) {
      return $this.one();
   }

   default Tuple21 one() {
      return new Tuple21(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one(), this.structure19().one(), this.structure20().one(), this.structure21().one());
   }

   static void $init$(final RigProduct21 $this) {
   }
}
