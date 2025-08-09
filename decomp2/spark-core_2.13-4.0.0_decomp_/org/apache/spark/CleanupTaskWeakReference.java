package org.apache.spark;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2AAB\u0004\u0005\u001d!Aq\u0004\u0001BC\u0002\u0013\u0005\u0001\u0005\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003\"\u0011!1\u0003A!A!\u0002\u0013I\u0002\u0002C\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u000b-\u0002A\u0011\u0001\u0017\u00031\rcW-\u00198vaR\u000b7o[,fC.\u0014VMZ3sK:\u001cWM\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\b\u0011\u0007A9\u0012$D\u0001\u0012\u0015\t\u00112#A\u0002sK\u001aT!\u0001F\u000b\u0002\t1\fgn\u001a\u0006\u0002-\u0005!!.\u0019<b\u0013\tA\u0012CA\u0007XK\u0006\\'+\u001a4fe\u0016t7-\u001a\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\tQ\f7o[\u000b\u0002CA\u0011!eI\u0007\u0002\u000f%\u0011Ae\u0002\u0002\f\u00072,\u0017M\\;q)\u0006\u001c8.A\u0003uCN\\\u0007%\u0001\u0005sK\u001a,'/\u001a8u\u00039\u0011XMZ3sK:\u001cW-U;fk\u0016\u00042\u0001E\u0015\u001a\u0013\tQ\u0013C\u0001\bSK\u001a,'/\u001a8dKF+X-^3\u0002\rqJg.\u001b;?)\u0011icf\f\u0019\u0011\u0005\t\u0002\u0001\"B\u0010\u0006\u0001\u0004\t\u0003\"\u0002\u0014\u0006\u0001\u0004I\u0002\"B\u0014\u0006\u0001\u0004A\u0003"
)
public class CleanupTaskWeakReference extends WeakReference {
   private final CleanupTask task;

   public CleanupTask task() {
      return this.task;
   }

   public CleanupTaskWeakReference(final CleanupTask task, final Object referent, final ReferenceQueue referenceQueue) {
      super(referent, referenceQueue);
      this.task = task;
   }
}
