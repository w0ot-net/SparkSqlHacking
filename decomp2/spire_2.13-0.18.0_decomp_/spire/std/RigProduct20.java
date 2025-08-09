package spire.std;

import algebra.ring.Rig;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001C\f\u0019!\u0003\r\tA\u0007\u000f\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u00111\u0002\u0001\u0007\u0004\u00055\u0001bBA\t\u0001\u0019\r\u00111\u0003\u0005\b\u0003/\u0001a1AA\r\u0011\u001d\ti\u0002\u0001D\u0002\u0003?Aq!a\t\u0001\r\u0007\t)\u0003C\u0004\u0002*\u00011\u0019!a\u000b\t\u000f\u0005=\u0002Ab\u0001\u00022!9\u0011Q\u0007\u0001\u0007\u0004\u0005]\u0002bBA\u001e\u0001\u0019\r\u0011Q\b\u0005\b\u0003\u0003\u0002a1AA\"\u0011\u001d\t9\u0005\u0001D\u0002\u0003\u0013Bq!!\u0014\u0001\r\u0007\ty\u0005C\u0004\u0002T\u00011\u0019!!\u0016\t\u000f\u0005e\u0003Ab\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0004\u0005\u0005\u0004bBA3\u0001\u0019\r\u0011q\r\u0005\b\u0003W\u0002a1AA7\u0011\u001d\t\t\b\u0001D\u0002\u0003gBq!a\u001e\u0001\r\u0007\tI\bC\u0004\u0002~\u00011\u0019!a \t\u000f\u0005\r\u0005\u0001\"\u0001\u0002\u0006\na!+[4Qe>$Wo\u0019;3a)\u0011\u0011DG\u0001\u0004gR$'\"A\u000e\u0002\u000bM\u0004\u0018N]3\u0016+uQDi\u0012&N!N3\u0016\fX0cK\"\\g.\u001d;xuN!\u0001A\b\u0013}!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0019QEM\u001b\u000f\u0005\u0019zcBA\u0014.\u001d\tAC&D\u0001*\u0015\tQ3&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0012B\u0001\u0018\u001b\u0003\u001d\tGnZ3ce\u0006L!\u0001M\u0019\u0002\u000fA\f7m[1hK*\u0011aFG\u0005\u0003gQ\u00121AU5h\u0015\t\u0001\u0014\u0007\u0005\f ma\u001ae)\u0013'P%VC6LX1eO*l\u0007o\u001d<z\u0013\t9\u0004EA\u0004UkBdWM\r\u0019\u0011\u0005eRD\u0002\u0001\u0003\u0006w\u0001\u0011\r\u0001\u0010\u0002\u0002\u0003F\u0011Q\b\u0011\t\u0003?yJ!a\u0010\u0011\u0003\u000f9{G\u000f[5oOB\u0011q$Q\u0005\u0003\u0005\u0002\u00121!\u00118z!\tID\tB\u0003F\u0001\t\u0007AHA\u0001C!\tIt\tB\u0003I\u0001\t\u0007AHA\u0001D!\tI$\nB\u0003L\u0001\t\u0007AHA\u0001E!\tIT\nB\u0003O\u0001\t\u0007AHA\u0001F!\tI\u0004\u000bB\u0003R\u0001\t\u0007AHA\u0001G!\tI4\u000bB\u0003U\u0001\t\u0007AHA\u0001H!\tId\u000bB\u0003X\u0001\t\u0007AHA\u0001I!\tI\u0014\fB\u0003[\u0001\t\u0007AHA\u0001J!\tID\fB\u0003^\u0001\t\u0007AHA\u0001K!\tIt\fB\u0003a\u0001\t\u0007AHA\u0001L!\tI$\rB\u0003d\u0001\t\u0007AHA\u0001M!\tIT\rB\u0003g\u0001\t\u0007AHA\u0001N!\tI\u0004\u000eB\u0003j\u0001\t\u0007AHA\u0001O!\tI4\u000eB\u0003m\u0001\t\u0007AHA\u0001P!\tId\u000eB\u0003p\u0001\t\u0007AHA\u0001Q!\tI\u0014\u000fB\u0003s\u0001\t\u0007AHA\u0001R!\tID\u000fB\u0003v\u0001\t\u0007AHA\u0001S!\tIt\u000fB\u0003y\u0001\t\u0007AHA\u0001T!\tI$\u0010B\u0003|\u0001\t\u0007AHA\u0001U!Yih\u0010O\"G\u00132{%+\u0016-\\=\u0006$wM[7qgZLX\"\u0001\r\n\u0005}D\"!E*f[&\u0014\u0018N\\4Qe>$Wo\u0019;3a\u00051A%\u001b8ji\u0012\"\"!!\u0002\u0011\u0007}\t9!C\u0002\u0002\n\u0001\u0012A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0005\u0005=\u0001cA\u00133q\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0005\u0005U\u0001cA\u00133\u0007\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0005\u0005m\u0001cA\u00133\r\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005\u0005\u0002cA\u00133\u0013\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005\u001d\u0002cA\u00133\u0019\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u00055\u0002cA\u00133\u001f\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005M\u0002cA\u00133%\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005e\u0002cA\u00133+\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005}\u0002cA\u001331\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t)\u0005E\u0002&em\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\n\t\u0004KIr\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0015\u0011\u0007\u0015\u0012\u0014-A\u0006tiJ,8\r^;sKF\u001aTCAA,!\r)#\u0007Z\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002^A\u0019QEM4\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u0003G\u00022!\n\u001ak\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005%\u0004cA\u00133[\u0006Y1\u000f\u001e:vGR,(/Z\u00198+\t\ty\u0007E\u0002&eA\f1b\u001d;sk\u000e$XO]32qU\u0011\u0011Q\u000f\t\u0004KI\u001a\u0018aC:ueV\u001cG/\u001e:fce*\"!a\u001f\u0011\u0007\u0015\u0012d/A\u0006tiJ,8\r^;sKJ\u0002TCAAA!\r)#'_\u0001\u0004_:,W#A\u001b"
)
public interface RigProduct20 extends Rig, SemiringProduct20 {
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

   // $FF: synthetic method
   static Tuple20 one$(final RigProduct20 $this) {
      return $this.one();
   }

   default Tuple20 one() {
      return new Tuple20(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one(), this.structure19().one(), this.structure20().one());
   }

   static void $init$(final RigProduct20 $this) {
   }
}
