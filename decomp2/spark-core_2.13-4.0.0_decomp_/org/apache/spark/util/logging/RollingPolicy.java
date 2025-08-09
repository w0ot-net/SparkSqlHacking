package org.apache.spark.util.logging;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2\u0001\"\u0002\u0004\u0011\u0002G\u0005!\u0002\u0005\u0005\u0006/\u00011\t!\u0007\u0005\u0006E\u00011\ta\t\u0005\u0006O\u00011\t\u0001\u000b\u0005\u0006W\u00011\t\u0001\f\u0002\u000e%>dG.\u001b8h!>d\u0017nY=\u000b\u0005\u001dA\u0011a\u00027pO\u001eLgn\u001a\u0006\u0003\u0013)\tA!\u001e;jY*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xm\u0005\u0002\u0001#A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\fab\u001d5pk2$'k\u001c7m_Z,'o\u0001\u0001\u0015\u0005ii\u0002C\u0001\n\u001c\u0013\ta2CA\u0004C_>dW-\u00198\t\u000by\t\u0001\u0019A\u0010\u0002!\tLH/Z:U_\n+wK]5ui\u0016t\u0007C\u0001\n!\u0013\t\t3C\u0001\u0003M_:<\u0017A\u0003:pY2,Gm\u0014<feR\tA\u0005\u0005\u0002\u0013K%\u0011ae\u0005\u0002\u0005+:LG/\u0001\u0007csR,7o\u0016:jiR,g\u000e\u0006\u0002%S!)!f\u0001a\u0001?\u0005)!-\u001f;fg\u0006ar-\u001a8fe\u0006$XMU8mY\u0016$wJ^3s\r&dWmU;gM&DH#A\u0017\u0011\u00059*dBA\u00184!\t\u00014#D\u00012\u0015\t\u0011\u0004$\u0001\u0004=e>|GOP\u0005\u0003iM\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001c8\u0005\u0019\u0019FO]5oO*\u0011Ag\u0005"
)
public interface RollingPolicy {
   boolean shouldRollover(final long bytesToBeWritten);

   void rolledOver();

   void bytesWritten(final long bytes);

   String generateRolledOverFileSuffix();
}
