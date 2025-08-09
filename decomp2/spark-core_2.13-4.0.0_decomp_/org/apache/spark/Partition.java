package org.apache.spark;

import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113qAB\u0004\u0011\u0002\u0007\u0005a\u0002C\u0003\"\u0001\u0011\u0005!\u0005C\u0003'\u0001\u0019\u0005q\u0005C\u0003,\u0001\u0011\u0005C\u0006C\u0003.\u0001\u0011\u0005c\u0006C\u00068\u0001A\u0005\u0019\u0011!A\u0005\na\u001a%!\u0003)beRLG/[8o\u0015\tA\u0011\"A\u0003ta\u0006\u00148N\u0003\u0002\u000b\u0017\u00051\u0011\r]1dQ\u0016T\u0011\u0001D\u0001\u0004_J<7\u0001A\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u0017=9\u0011q\u0003\b\b\u00031mi\u0011!\u0007\u0006\u000355\ta\u0001\u0010:p_Rt\u0014\"\u0001\n\n\u0005u\t\u0012a\u00029bG.\fw-Z\u0005\u0003?\u0001\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!H\t\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0003C\u0001\t%\u0013\t)\u0013C\u0001\u0003V]&$\u0018!B5oI\u0016DX#\u0001\u0015\u0011\u0005AI\u0013B\u0001\u0016\u0012\u0005\rIe\u000e^\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0001&\u0001\u0004fcV\fGn\u001d\u000b\u0003_I\u0002\"\u0001\u0005\u0019\n\u0005E\n\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006g\u0011\u0001\r\u0001N\u0001\u0006_RDWM\u001d\t\u0003!UJ!AN\t\u0003\u0007\u0005s\u00170\u0001\u0007tkB,'\u000fJ3rk\u0006d7\u000f\u0006\u00020s!9!(BA\u0001\u0002\u0004Y\u0014a\u0001=%cA\u0011A(Q\u0007\u0002{)\u0011ahP\u0001\u0005Y\u0006twMC\u0001A\u0003\u0011Q\u0017M^1\n\u0005\tk$AB(cU\u0016\u001cG/\u0003\u0002.\u0003\u0002"
)
public interface Partition extends Serializable {
   // $FF: synthetic method
   boolean org$apache$spark$Partition$$super$equals(final Object x$1);

   int index();

   // $FF: synthetic method
   static int hashCode$(final Partition $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return this.index();
   }

   // $FF: synthetic method
   static boolean equals$(final Partition $this, final Object other) {
      return $this.equals(other);
   }

   default boolean equals(final Object other) {
      return this.org$apache$spark$Partition$$super$equals(other);
   }

   static void $init$(final Partition $this) {
   }
}
