package org.apache.spark.deploy.history;

import org.apache.spark.ui.SparkUI;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3\u0001\u0002B\u0003\u0011\u0002G\u0005Qa\u0004\u0005\u0006-\u00011\t\u0001\u0007\u0005\u0006a\u00011\t!\r\u0005\u0006\u0007\u00021\t\u0001\u0012\u0002\u001b\u0003B\u0004H.[2bi&|gnQ1dQ\u0016|\u0005/\u001a:bi&|gn\u001d\u0006\u0003\r\u001d\tq\u0001[5ti>\u0014\u0018P\u0003\u0002\t\u0013\u00051A-\u001a9m_fT!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0003\u0001A\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0017\u0001C4fi\u0006\u0003\b/V%\u0004\u0001Q\u0019\u0011\u0004I\u0017\u0011\u0007EQB$\u0003\u0002\u001c%\t1q\n\u001d;j_:\u0004\"!\b\u0010\u000e\u0003\u0015I!aH\u0003\u0003\u00171{\u0017\rZ3e\u0003B\u0004X+\u0013\u0005\u0006C\u0005\u0001\rAI\u0001\u0006CB\u0004\u0018\n\u001a\t\u0003G)r!\u0001\n\u0015\u0011\u0005\u0015\u0012R\"\u0001\u0014\u000b\u0005\u001d:\u0012A\u0002\u001fs_>$h(\u0003\u0002*%\u00051\u0001K]3eK\u001aL!a\u000b\u0017\u0003\rM#(/\u001b8h\u0015\tI#\u0003C\u0003/\u0003\u0001\u0007q&A\u0005biR,W\u000e\u001d;JIB\u0019\u0011C\u0007\u0012\u0002\u001b\u0005$H/Y2i'B\f'o[+J)\u0015\u0011TGN\u001c?!\t\t2'\u0003\u00025%\t!QK\\5u\u0011\u0015\t#\u00011\u0001#\u0011\u0015q#\u00011\u00010\u0011\u0015A$\u00011\u0001:\u0003\t)\u0018\u000e\u0005\u0002;y5\t1H\u0003\u00029\u0013%\u0011Qh\u000f\u0002\b'B\f'o[+J\u0011\u0015y$\u00011\u0001A\u0003%\u0019w.\u001c9mKR,G\r\u0005\u0002\u0012\u0003&\u0011!I\u0005\u0002\b\u0005>|G.Z1o\u00035!W\r^1dQN\u0003\u0018M]6V\u0013R!!'\u0012$H\u0011\u0015\t3\u00011\u0001#\u0011\u0015q3\u00011\u00010\u0011\u0015A4\u00011\u0001:\u0001"
)
public interface ApplicationCacheOperations {
   Option getAppUI(final String appId, final Option attemptId);

   void attachSparkUI(final String appId, final Option attemptId, final SparkUI ui, final boolean completed);

   void detachSparkUI(final String appId, final Option attemptId, final SparkUI ui);
}
