package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M<QAB\u0004\t\u000211QAD\u0004\t\u0002=AQAF\u0001\u0005\u0002]AQ\u0001G\u0001\u0005\u0006eAQaU\u0001\u0005\u0006QCQaY\u0001\u0005\u0006\u0011\f\u0011bU3mK\u000e$\u0018n\u001c8\u000b\u0005!I\u0011\u0001B7bi\"T\u0011AC\u0001\u0006gBL'/Z\u0002\u0001!\ti\u0011!D\u0001\b\u0005%\u0019V\r\\3di&|gn\u0005\u0002\u0002!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u0007\u0002\rM,G.Z2u+\tQ2\u0007F\u0002\u001c\u0013:#2\u0001H\u0010A!\t\tR$\u0003\u0002\u001f%\t!QK\\5u\u0011\u001d\u00013!!AA\u0004\u0005\n!\"\u001a<jI\u0016t7-\u001a\u0013:!\r\u0011c&\r\b\u0003G-r!\u0001J\u0015\u000f\u0005\u0015BS\"\u0001\u0014\u000b\u0005\u001dZ\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\tQ\u0013\"A\u0004bY\u001e,'M]1\n\u00051j\u0013a\u00029bG.\fw-\u001a\u0006\u0003U%I!a\f\u0019\u0003\u000b=\u0013H-\u001a:\u000b\u00051j\u0003C\u0001\u001a4\u0019\u0001!\u0011\u0002N\u0002!\u0002\u0003\u0005)\u0019A\u001b\u0003\u0003\u0005\u000b\"AN\u001d\u0011\u0005E9\u0014B\u0001\u001d\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0005\u001e\n\u0005m\u0012\"aA!os\"\u00121'\u0010\t\u0003#yJ!a\u0010\n\u0003\u0017M\u0004XmY5bY&TX\r\u001a\u0005\b\u0003\u000e\t\t\u0011q\u0001C\u0003-)g/\u001b3f]\u000e,G%\r\u0019\u0011\u0007\r3\u0015G\u0004\u0002E\u000b6\t\u0011\"\u0003\u0002-\u0013%\u0011q\t\u0013\u0002\t\u00072\f7o\u001d+bO*\u0011A&\u0003\u0005\u0006\u0015\u000e\u0001\raS\u0001\u0005I\u0006$\u0018\rE\u0002\u0012\u0019FJ!!\u0014\n\u0003\u000b\u0005\u0013(/Y=\t\u000b=\u001b\u0001\u0019\u0001)\u0002\u0003-\u0004\"!E)\n\u0005I\u0013\"aA%oi\u0006aA.\u001b8fCJ\u001cV\r\\3diV\u0011Qk\u0017\u000b\u0004-\u0002\u0014Gc\u0001\u000fX;\"9\u0001\fBA\u0001\u0002\bI\u0016aC3wS\u0012,gnY3%cE\u00022A\t\u0018[!\t\u00114\fB\u00055\t\u0001\u0006\t\u0011!b\u0001k!\u00121,\u0010\u0005\b=\u0012\t\t\u0011q\u0001`\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\u0007\r3%\fC\u0003K\t\u0001\u0007\u0011\rE\u0002\u0012\u0019jCQa\u0014\u0003A\u0002A\u000b1\"];jG.\u001cV\r\\3diV\u0011Qm\u001b\u000b\u0004MB\u0014Hc\u0001\u000fh[\"9\u0001.BA\u0001\u0002\bI\u0017aC3wS\u0012,gnY3%cM\u00022A\t\u0018k!\t\u00114\u000eB\u00055\u000b\u0001\u0006\t\u0011!b\u0001k!\u00121.\u0010\u0005\b]\u0016\t\t\u0011q\u0001p\u0003-)g/\u001b3f]\u000e,G%\r\u001b\u0011\u0007\r3%\u000eC\u0003K\u000b\u0001\u0007\u0011\u000fE\u0002\u0012\u0019*DQaT\u0003A\u0002A\u0003"
)
public final class Selection {
   public static void quickSelect(final Object data, final int k, final Order evidence$13, final ClassTag evidence$14) {
      Selection$.MODULE$.quickSelect(data, k, evidence$13, evidence$14);
   }

   public static void linearSelect(final Object data, final int k, final Order evidence$11, final ClassTag evidence$12) {
      Selection$.MODULE$.linearSelect(data, k, evidence$11, evidence$12);
   }

   public static void select(final Object data, final int k, final Order evidence$9, final ClassTag evidence$10) {
      Selection$.MODULE$.select(data, k, evidence$9, evidence$10);
   }
}
