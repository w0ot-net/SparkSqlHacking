package org.apache.spark.deploy;

import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.sys.process.ProcessBuilder;

@ScalaSignature(
   bytes = "\u0006\u0005a;Q\u0001C\u0005\t\nI1Q\u0001F\u0005\t\nUAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005\u0002\u0015BqaP\u0001\u0012\u0002\u0013\u0005\u0001\tC\u0004L\u0003E\u0005I\u0011\u0001!\t\u000b1\u000bA\u0011A'\t\u000bY\u000bA\u0011A,\u0002\r\u0011{7m[3s\u0015\tQ1\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sO\u000e\u0001\u0001CA\n\u0002\u001b\u0005I!A\u0002#pG.,'oE\u0002\u0002-q\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\u000f!\u001b\u0005q\"BA\u0010\f\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0011\u001f\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRtD#\u0001\n\u0002\u00155\f7.\u001a*v]\u000ekG\r\u0006\u0003']mj\u0004CA\u0014-\u001b\u0005A#BA\u0015+\u0003\u001d\u0001(o\\2fgNT!a\u000b\r\u0002\u0007ML8/\u0003\u0002.Q\tq\u0001K]8dKN\u001c()^5mI\u0016\u0014\b\"B\u0018\u0004\u0001\u0004\u0001\u0014\u0001C5nC\u001e,G+Y4\u0011\u0005EBdB\u0001\u001a7!\t\u0019\u0004$D\u00015\u0015\t)\u0014#\u0001\u0004=e>|GOP\u0005\u0003oa\ta\u0001\u0015:fI\u00164\u0017BA\u001d;\u0005\u0019\u0019FO]5oO*\u0011q\u0007\u0007\u0005\by\r\u0001\n\u00111\u00011\u0003\u0011\t'oZ:\t\u000fy\u001a\u0001\u0013!a\u0001a\u0005AQn\\;oi\u0012K'/\u0001\u000bnC.,'+\u001e8D[\u0012$C-\u001a4bk2$HEM\u000b\u0002\u0003*\u0012\u0001GQ\u0016\u0002\u0007B\u0011A)S\u0007\u0002\u000b*\u0011aiR\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0013\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002K\u000b\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002)5\f7.\u001a*v]\u000ekG\r\n3fM\u0006,H\u000e\u001e\u00134\u0003\u0011Y\u0017\u000e\u001c7\u0015\u00059\u000b\u0006CA\fP\u0013\t\u0001\u0006D\u0001\u0003V]&$\b\"\u0002*\u0007\u0001\u0004\u0019\u0016\u0001\u00033pG.,'/\u00133\u0011\u0005M!\u0016BA+\n\u0005!!unY6fe&#\u0017\u0001E4fi2\u000b7\u000f\u001e)s_\u000e,7o]%e+\u0005\u0019\u0006"
)
public final class Docker {
   public static DockerId getLastProcessId() {
      return Docker$.MODULE$.getLastProcessId();
   }

   public static void kill(final DockerId dockerId) {
      Docker$.MODULE$.kill(dockerId);
   }

   public static String makeRunCmd$default$3() {
      return Docker$.MODULE$.makeRunCmd$default$3();
   }

   public static String makeRunCmd$default$2() {
      return Docker$.MODULE$.makeRunCmd$default$2();
   }

   public static ProcessBuilder makeRunCmd(final String imageTag, final String args, final String mountDir) {
      return Docker$.MODULE$.makeRunCmd(imageTag, args, mountDir);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Docker$.MODULE$.LogStringContext(sc);
   }
}
