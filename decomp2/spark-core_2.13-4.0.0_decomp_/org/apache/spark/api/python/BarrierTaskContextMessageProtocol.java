package org.apache.spark.api.python;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a:aa\u0003\u0007\t\u0002A1bA\u0002\r\r\u0011\u0003\u0001\u0012\u0004C\u0003!\u0003\u0011\u0005!\u0005C\u0004$\u0003\t\u0007I\u0011\u0001\u0013\t\r!\n\u0001\u0015!\u0003&\u0011\u001dI\u0013A1A\u0005\u0002\u0011BaAK\u0001!\u0002\u0013)\u0003bB\u0016\u0002\u0005\u0004%\t\u0001\f\u0005\u0007k\u0005\u0001\u000b\u0011B\u0017\t\u000fY\n!\u0019!C\u0001Y!1q'\u0001Q\u0001\n5\n\u0011EQ1se&,'\u000fV1tW\u000e{g\u000e^3yi6+7o]1hKB\u0013x\u000e^8d_2T!!\u0004\b\u0002\rALH\u000f[8o\u0015\ty\u0001#A\u0002ba&T!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'o\u001a\t\u0003/\u0005i\u0011\u0001\u0004\u0002\"\u0005\u0006\u0014(/[3s)\u0006\u001c8nQ8oi\u0016DH/T3tg\u0006<W\r\u0015:pi>\u001cw\u000e\\\n\u0003\u0003i\u0001\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Y\t\u0001CQ!S%&+%k\u0018$V\u001d\u000e#\u0016j\u0014(\u0016\u0003\u0015\u0002\"a\u0007\u0014\n\u0005\u001db\"aA%oi\u0006\t\")\u0011*S\u0013\u0016\u0013vLR+O\u0007RKuJ\u0014\u0011\u0002'\u0005cEjX$B)\"+%k\u0018$V\u001d\u000e#\u0016j\u0014(\u0002)\u0005cEjX$B)\"+%k\u0018$V\u001d\u000e#\u0016j\u0014(!\u0003Y\u0011\u0015I\u0015*J\u000bJ{&+R*V\u0019R{6+V\"D\u000bN\u001bV#A\u0017\u0011\u00059\u001aT\"A\u0018\u000b\u0005A\n\u0014\u0001\u00027b]\u001eT\u0011AM\u0001\u0005U\u00064\u0018-\u0003\u00025_\t11\u000b\u001e:j]\u001e\fqCQ!S%&+%k\u0018*F'VcEkX*V\u0007\u000e+5k\u0015\u0011\u00027\u0015\u0013&k\u0014*`+:\u0013ViQ(H\u001d&SV\tR0G+:\u001bE+S(O\u0003q)%KU(S?Vs%+R\"P\u000f:K%,\u0012#`\rVs5\tV%P\u001d\u0002\u0002"
)
public final class BarrierTaskContextMessageProtocol {
   public static String ERROR_UNRECOGNIZED_FUNCTION() {
      return BarrierTaskContextMessageProtocol$.MODULE$.ERROR_UNRECOGNIZED_FUNCTION();
   }

   public static String BARRIER_RESULT_SUCCESS() {
      return BarrierTaskContextMessageProtocol$.MODULE$.BARRIER_RESULT_SUCCESS();
   }

   public static int ALL_GATHER_FUNCTION() {
      return BarrierTaskContextMessageProtocol$.MODULE$.ALL_GATHER_FUNCTION();
   }

   public static int BARRIER_FUNCTION() {
      return BarrierTaskContextMessageProtocol$.MODULE$.BARRIER_FUNCTION();
   }
}
