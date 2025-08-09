package org.apache.spark.sql.streaming;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2\u0001\"\u0002\u0004\u0011\u0002G\u0005!\u0002\u0005\u0005\u0006/\u00011\t!\u0007\u0005\u0006O\u00011\t\u0001\u000b\u0005\u0006[\u00011\tA\f\u0005\u0006g\u00011\t\u0001\u000e\u0002\u001d!f$\bn\u001c8TiJ,\u0017-\\5oOF+XM]=MSN$XM\\3s\u0015\t9\u0001\"A\u0005tiJ,\u0017-\\5oO*\u0011\u0011BC\u0001\u0004gFd'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\bp]F+XM]=Ti\u0006\u0014H/\u001a3\u0004\u0001Q\u0011!$\b\t\u0003%mI!\u0001H\n\u0003\tUs\u0017\u000e\u001e\u0005\u0006=\u0005\u0001\raH\u0001\u0006KZ,g\u000e\u001e\t\u0003A\u0011r!!\t\u0012\u000e\u0003\u0019I!a\t\u0004\u0002-M#(/Z1nS:<\u0017+^3ss2K7\u000f^3oKJL!!\n\u0014\u0003#E+XM]=Ti\u0006\u0014H/\u001a3Fm\u0016tGO\u0003\u0002$\r\u0005yqN\\)vKJL\bK]8he\u0016\u001c8\u000f\u0006\u0002\u001bS!)aD\u0001a\u0001UA\u0011\u0001eK\u0005\u0003Y\u0019\u0012!#U;fef\u0004&o\\4sKN\u001cXI^3oi\u0006YqN\\)vKJL\u0018\n\u001a7f)\tQr\u0006C\u0003\u001f\u0007\u0001\u0007\u0001\u0007\u0005\u0002!c%\u0011!G\n\u0002\u000f#V,'/_%eY\u0016,e/\u001a8u\u0003Eyg.U;fef$VM]7j]\u0006$X\r\u001a\u000b\u00035UBQA\b\u0003A\u0002Y\u0002\"\u0001I\u001c\n\u0005a2#\u0001F)vKJLH+\u001a:nS:\fG/\u001a3Fm\u0016tG\u000f"
)
public interface PythonStreamingQueryListener {
   void onQueryStarted(final StreamingQueryListener.QueryStartedEvent event);

   void onQueryProgress(final StreamingQueryListener.QueryProgressEvent event);

   void onQueryIdle(final StreamingQueryListener.QueryIdleEvent event);

   void onQueryTerminated(final StreamingQueryListener.QueryTerminatedEvent event);
}
