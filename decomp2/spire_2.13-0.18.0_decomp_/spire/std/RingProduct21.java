package spire.std;

import algebra.ring.Ring;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f\u0001C\r\u001b!\u0003\r\t\u0001\b\u0010\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!9\u0011Q\u0003\u0001\u0007\u0004\u0005]\u0001bBA\u000e\u0001\u0019\r\u0011Q\u0004\u0005\b\u0003C\u0001a1AA\u0012\u0011\u001d\t9\u0003\u0001D\u0002\u0003SAq!!\f\u0001\r\u0007\ty\u0003C\u0004\u00024\u00011\u0019!!\u000e\t\u000f\u0005e\u0002Ab\u0001\u0002<!9\u0011q\b\u0001\u0007\u0004\u0005\u0005\u0003bBA#\u0001\u0019\r\u0011q\t\u0005\b\u0003\u0017\u0002a1AA'\u0011\u001d\t\t\u0006\u0001D\u0002\u0003'Bq!a\u0016\u0001\r\u0007\tI\u0006C\u0004\u0002^\u00011\u0019!a\u0018\t\u000f\u0005\r\u0004Ab\u0001\u0002f!9\u0011\u0011\u000e\u0001\u0007\u0004\u0005-\u0004bBA8\u0001\u0019\r\u0011\u0011\u000f\u0005\b\u0003k\u0002a1AA<\u0011\u001d\tY\b\u0001D\u0002\u0003{Bq!!!\u0001\r\u0007\t\u0019\tC\u0004\u0002\b\u00021\u0019!!#\t\u000f\u00055\u0005Ab\u0001\u0002\u0010\"9\u00111\u0013\u0001\u0005B\u0005U\u0005bBAQ\u0001\u0011\u0005\u00111\u0015\u0002\u000e%&tw\r\u0015:pIV\u001cGOM\u0019\u000b\u0005ma\u0012aA:uI*\tQ$A\u0003ta&\u0014X-\u0006\f y\u0019KEj\u0014*V1ns\u0016\rZ4k[B\u001ch/\u001f?\u0000'\u0015\u0001\u0001EJA\u0002!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019\te.\u001f*fMB\u0019q\u0005N\u001c\u000f\u0005!\ndBA\u00150\u001d\tQc&D\u0001,\u0015\taS&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005i\u0012B\u0001\u0019\u001d\u0003\u001d\tGnZ3ce\u0006L!AM\u001a\u0002\u000fA\f7m[1hK*\u0011\u0001\u0007H\u0005\u0003kY\u0012AAU5oO*\u0011!g\r\t\u0018CaRT\tS&O#R;&,\u00181dM&dwN];ywzL!!\u000f\u0012\u0003\u000fQ+\b\u000f\\33cA\u00111\b\u0010\u0007\u0001\t\u0015i\u0004A1\u0001?\u0005\u0005\t\u0015CA C!\t\t\u0003)\u0003\u0002BE\t9aj\u001c;iS:<\u0007CA\u0011D\u0013\t!%EA\u0002B]f\u0004\"a\u000f$\u0005\u000b\u001d\u0003!\u0019\u0001 \u0003\u0003\t\u0003\"aO%\u0005\u000b)\u0003!\u0019\u0001 \u0003\u0003\r\u0003\"a\u000f'\u0005\u000b5\u0003!\u0019\u0001 \u0003\u0003\u0011\u0003\"aO(\u0005\u000bA\u0003!\u0019\u0001 \u0003\u0003\u0015\u0003\"a\u000f*\u0005\u000bM\u0003!\u0019\u0001 \u0003\u0003\u0019\u0003\"aO+\u0005\u000bY\u0003!\u0019\u0001 \u0003\u0003\u001d\u0003\"a\u000f-\u0005\u000be\u0003!\u0019\u0001 \u0003\u0003!\u0003\"aO.\u0005\u000bq\u0003!\u0019\u0001 \u0003\u0003%\u0003\"a\u000f0\u0005\u000b}\u0003!\u0019\u0001 \u0003\u0003)\u0003\"aO1\u0005\u000b\t\u0004!\u0019\u0001 \u0003\u0003-\u0003\"a\u000f3\u0005\u000b\u0015\u0004!\u0019\u0001 \u0003\u00031\u0003\"aO4\u0005\u000b!\u0004!\u0019\u0001 \u0003\u00035\u0003\"a\u000f6\u0005\u000b-\u0004!\u0019\u0001 \u0003\u00039\u0003\"aO7\u0005\u000b9\u0004!\u0019\u0001 \u0003\u0003=\u0003\"a\u000f9\u0005\u000bE\u0004!\u0019\u0001 \u0003\u0003A\u0003\"aO:\u0005\u000bQ\u0004!\u0019\u0001 \u0003\u0003E\u0003\"a\u000f<\u0005\u000b]\u0004!\u0019\u0001 \u0003\u0003I\u0003\"aO=\u0005\u000bi\u0004!\u0019\u0001 \u0003\u0003M\u0003\"a\u000f?\u0005\u000bu\u0004!\u0019\u0001 \u0003\u0003Q\u0003\"aO@\u0005\r\u0005\u0005\u0001A1\u0001?\u0005\u0005)\u0006#GA\u0003\u0003\u000fQT\tS&O#R;&,\u00181dM&dwN];ywzl\u0011AG\u0005\u0004\u0003\u0013Q\"\u0001\u0004*oOB\u0013x\u000eZ;diJ\n\u0014A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002\u0010A\u0019\u0011%!\u0005\n\u0007\u0005M!E\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\u0011\u0011\u0011\u0004\t\u0004OQR\u0014AC:ueV\u001cG/\u001e:feU\u0011\u0011q\u0004\t\u0004OQ*\u0015AC:ueV\u001cG/\u001e:fgU\u0011\u0011Q\u0005\t\u0004OQB\u0015AC:ueV\u001cG/\u001e:fiU\u0011\u00111\u0006\t\u0004OQZ\u0015AC:ueV\u001cG/\u001e:fkU\u0011\u0011\u0011\u0007\t\u0004OQr\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u0011q\u0007\t\u0004OQ\n\u0016AC:ueV\u001cG/\u001e:foU\u0011\u0011Q\b\t\u0004OQ\"\u0016AC:ueV\u001cG/\u001e:fqU\u0011\u00111\t\t\u0004OQ:\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011\u0011\n\t\u0004OQR\u0016aC:ueV\u001cG/\u001e:fcA*\"!a\u0014\u0011\u0007\u001d\"T,A\u0006tiJ,8\r^;sKF\nTCAA+!\r9C\u0007Y\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002\\A\u0019q\u0005N2\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003C\u00022a\n\u001bg\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005\u001d\u0004cA\u00145S\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\ti\u0007E\u0002(i1\f1b\u001d;sk\u000e$XO]32mU\u0011\u00111\u000f\t\u0004OQz\u0017aC:ueV\u001cG/\u001e:fc]*\"!!\u001f\u0011\u0007\u001d\"$/A\u0006tiJ,8\r^;sKFBTCAA@!\r9C'^\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014(\u0006\u0002\u0002\u0006B\u0019q\u0005\u000e=\u0002\u0017M$(/^2ukJ,'\u0007M\u000b\u0003\u0003\u0017\u00032a\n\u001b|\u0003-\u0019HO];diV\u0014XMM\u0019\u0016\u0005\u0005E\u0005cA\u00145}\u00069aM]8n\u0013:$HcA\u001c\u0002\u0018\"9\u0011\u0011T\fA\u0002\u0005m\u0015A\u0001=1!\r\t\u0013QT\u0005\u0004\u0003?\u0013#aA%oi\u0006\u0019qN\\3\u0016\u0003]\u0002"
)
public interface RingProduct21 extends Ring, RngProduct21 {
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

   Ring structure17();

   Ring structure18();

   Ring structure19();

   Ring structure20();

   Ring structure21();

   // $FF: synthetic method
   static Tuple21 fromInt$(final RingProduct21 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple21 fromInt(final int x0) {
      return new Tuple21(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0), this.structure13().fromInt(x0), this.structure14().fromInt(x0), this.structure15().fromInt(x0), this.structure16().fromInt(x0), this.structure17().fromInt(x0), this.structure18().fromInt(x0), this.structure19().fromInt(x0), this.structure20().fromInt(x0), this.structure21().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple21 one$(final RingProduct21 $this) {
      return $this.one();
   }

   default Tuple21 one() {
      return new Tuple21(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one(), this.structure13().one(), this.structure14().one(), this.structure15().one(), this.structure16().one(), this.structure17().one(), this.structure18().one(), this.structure19().one(), this.structure20().one(), this.structure21().one());
   }

   static void $init$(final RingProduct21 $this) {
   }
}
