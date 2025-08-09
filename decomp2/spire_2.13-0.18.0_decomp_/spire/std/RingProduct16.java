package spire.std;

import algebra.ring.Ring;
import scala.Tuple16;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c\u0001\u0003\u000b\u0016!\u0003\r\taF\r\t\u000bE\u0004A\u0011\u0001:\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0011\u0005\u0013q\n\u0005\b\u00037\u0002A\u0011AA/\u00055\u0011\u0016N\\4Qe>$Wo\u0019;2m)\u0011acF\u0001\u0004gR$'\"\u0001\r\u0002\u000bM\u0004\u0018N]3\u0016#i9\u0014\tR$K\u001bB\u001bf+\u0017/`E\u0016D7n\u0005\u0003\u00017\u0005j\u0007C\u0001\u000f \u001b\u0005i\"\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\"AB!osJ+g\rE\u0002#_Ir!a\t\u0017\u000f\u0005\u0011RcBA\u0013*\u001b\u00051#BA\u0014)\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\r\n\u0005-:\u0012aB1mO\u0016\u0014'/Y\u0005\u0003[9\nq\u0001]1dW\u0006<WM\u0003\u0002,/%\u0011\u0001'\r\u0002\u0005%&twM\u0003\u0002.]A\u0011BdM\u001bA\u0007\u001aKEj\u0014*V1ns\u0016\rZ4k\u0013\t!TDA\u0004UkBdW-\r\u001c\u0011\u0005Y:D\u0002\u0001\u0003\u0006q\u0001\u0011\r!\u000f\u0002\u0002\u0003F\u0011!(\u0010\t\u00039mJ!\u0001P\u000f\u0003\u000f9{G\u000f[5oOB\u0011ADP\u0005\u0003\u007fu\u00111!\u00118z!\t1\u0014\tB\u0003C\u0001\t\u0007\u0011HA\u0001C!\t1D\tB\u0003F\u0001\t\u0007\u0011HA\u0001D!\t1t\tB\u0003I\u0001\t\u0007\u0011HA\u0001E!\t1$\nB\u0003L\u0001\t\u0007\u0011HA\u0001F!\t1T\nB\u0003O\u0001\t\u0007\u0011HA\u0001G!\t1\u0004\u000bB\u0003R\u0001\t\u0007\u0011HA\u0001H!\t14\u000bB\u0003U\u0001\t\u0007\u0011HA\u0001I!\t1d\u000bB\u0003X\u0001\t\u0007\u0011HA\u0001J!\t1\u0014\fB\u0003[\u0001\t\u0007\u0011HA\u0001K!\t1D\fB\u0003^\u0001\t\u0007\u0011HA\u0001L!\t1t\fB\u0003a\u0001\t\u0007\u0011HA\u0001M!\t1$\rB\u0003d\u0001\t\u0007\u0011HA\u0001N!\t1T\rB\u0003g\u0001\t\u0007\u0011HA\u0001O!\t1\u0004\u000eB\u0003j\u0001\t\u0007\u0011HA\u0001P!\t14\u000eB\u0003m\u0001\t\u0007\u0011HA\u0001Q!Iqw.\u000e!D\r&cuJU+Y7z\u000bGm\u001a6\u000e\u0003UI!\u0001]\u000b\u0003\u0019Isw\r\u0015:pIV\u001cG/\r\u001c\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\bC\u0001\u000fu\u0013\t)XD\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t\u0001\u0010E\u0002#_U\n!b\u001d;sk\u000e$XO]33+\u0005Y\bc\u0001\u00120\u0001\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003y\u00042AI\u0018D\u0003)\u0019HO];diV\u0014X\rN\u000b\u0003\u0003\u0007\u00012AI\u0018G\u0003)\u0019HO];diV\u0014X-N\u000b\u0003\u0003\u0013\u00012AI\u0018J\u0003)\u0019HO];diV\u0014XMN\u000b\u0003\u0003\u001f\u00012AI\u0018M\u0003)\u0019HO];diV\u0014XmN\u000b\u0003\u0003+\u00012AI\u0018P\u0003)\u0019HO];diV\u0014X\rO\u000b\u0003\u00037\u00012AI\u0018S\u0003)\u0019HO];diV\u0014X-O\u000b\u0003\u0003C\u00012AI\u0018V\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005\u001d\u0002c\u0001\u001201\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\ti\u0003E\u0002#_m\u000b1b\u001d;sk\u000e$XO]32eU\u0011\u00111\u0007\t\u0004E=r\u0016aC:ueV\u001cG/\u001e:fcM*\"!!\u000f\u0011\u0007\tz\u0013-A\u0006tiJ,8\r^;sKF\"TCAA !\r\u0011s\u0006Z\u0001\fgR\u0014Xo\u0019;ve\u0016\fT'\u0006\u0002\u0002FA\u0019!eL4\u0002\u0017M$(/^2ukJ,\u0017GN\u000b\u0003\u0003\u0017\u00022AI\u0018k\u0003\u001d1'o\\7J]R$2AMA)\u0011\u001d\t\u0019F\u0005a\u0001\u0003+\n!\u0001\u001f\u0019\u0011\u0007q\t9&C\u0002\u0002Zu\u00111!\u00138u\u0003\ryg.Z\u000b\u0002e\u0001"
)
public interface RingProduct16 extends Ring, RngProduct16 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   Ring structure7();

   Ring structure8();

   Ring structure9();

   Ring structure10();

   Ring structure11();

   Ring structure12();

   Ring structure13();

   Ring structure14();

   Ring structure15();

   Ring structure16();

   // $FF: synthetic method
   static Tuple16 fromInt$(final RingProduct16 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple16 fromInt(final int x0) {
      return new Tuple16(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0), this.structure16().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple16 one$(final RingProduct16 $this) {
      return $this.one();
   }

   default Tuple16 one() {
      return new Tuple16(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one());
   }

   static void $init$(final RingProduct16 $this) {
   }
}
