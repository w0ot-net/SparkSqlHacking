package org.apache.spark.sql.streaming;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r3Qa\u0002\u0005\u0001\u0019IA\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\u00069\u0001!\t!\b\u0005\u0006A\u0001!\t!\t\u0005\u0006c\u0001!\tA\r\u0005\u0006o\u0001!\t\u0005\u000f\u0005\u0006{\u0001!\tA\u0010\u0002$!f$\bn\u001c8TiJ,\u0017-\\5oOF+XM]=MSN$XM\\3s/J\f\u0007\u000f]3s\u0015\tI!\"A\u0005tiJ,\u0017-\\5oO*\u00111\u0002D\u0001\u0004gFd'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0016\u001b\u0005A\u0011B\u0001\f\t\u0005Y\u0019FO]3b[&tw-U;fefd\u0015n\u001d;f]\u0016\u0014\u0018\u0001\u00037jgR,g.\u001a:\u0004\u0001A\u0011ACG\u0005\u00037!\u0011A\u0004U=uQ>t7\u000b\u001e:fC6LgnZ)vKJLH*[:uK:,'/\u0001\u0004=S:LGO\u0010\u000b\u0003=}\u0001\"\u0001\u0006\u0001\t\u000b]\u0011\u0001\u0019A\r\u0002\u001d=t\u0017+^3ssN#\u0018M\u001d;fIR\u0011!\u0005\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0005+:LG\u000fC\u0003*\u0007\u0001\u0007!&A\u0003fm\u0016tG\u000f\u0005\u0002,]9\u0011A\u0003L\u0005\u0003[!\tac\u0015;sK\u0006l\u0017N\\4Rk\u0016\u0014\u0018\u0010T5ti\u0016tWM]\u0005\u0003_A\u0012\u0011#U;fef\u001cF/\u0019:uK\u0012,e/\u001a8u\u0015\ti\u0003\"A\bp]F+XM]=Qe><'/Z:t)\t\u00113\u0007C\u0003*\t\u0001\u0007A\u0007\u0005\u0002,k%\u0011a\u0007\r\u0002\u0013#V,'/\u001f)s_\u001e\u0014Xm]:Fm\u0016tG/A\u0006p]F+XM]=JI2,GC\u0001\u0012:\u0011\u0015IS\u00011\u0001;!\tY3(\u0003\u0002=a\tq\u0011+^3ss&#G.Z#wK:$\u0018!E8o#V,'/\u001f+fe6Lg.\u0019;fIR\u0011!e\u0010\u0005\u0006S\u0019\u0001\r\u0001\u0011\t\u0003W\u0005K!A\u0011\u0019\u0003)E+XM]=UKJl\u0017N\\1uK\u0012,e/\u001a8u\u0001"
)
public class PythonStreamingQueryListenerWrapper extends StreamingQueryListener {
   private final PythonStreamingQueryListener listener;

   public void onQueryStarted(final StreamingQueryListener.QueryStartedEvent event) {
      this.listener.onQueryStarted(event);
   }

   public void onQueryProgress(final StreamingQueryListener.QueryProgressEvent event) {
      this.listener.onQueryProgress(event);
   }

   public void onQueryIdle(final StreamingQueryListener.QueryIdleEvent event) {
      this.listener.onQueryIdle(event);
   }

   public void onQueryTerminated(final StreamingQueryListener.QueryTerminatedEvent event) {
      this.listener.onQueryTerminated(event);
   }

   public PythonStreamingQueryListenerWrapper(final PythonStreamingQueryListener listener) {
      this.listener = listener;
   }
}
