package org.apache.spark.sql.hive.thriftserver;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\"\u0002\u0006\u0005\u0006i\u0001!\t!\u000e\u0005\u0006s\u00011\t!\u000e\u0005\u0006u\u0001!\ta\u000f\u0005\u0006\u0003\u00021\tA\u0011\u0005\u0006\u000b\u00021\tA\u0012\u0005\u0006\u000f\u00021\tA\u0012\u0002\u000e\r\u0016$8\r[%uKJ\fGo\u001c:\u000b\u0005%Q\u0011\u0001\u0004;ie&4Go]3sm\u0016\u0014(BA\u0006\r\u0003\u0011A\u0017N^3\u000b\u00055q\u0011aA:rY*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014x-\u0006\u0002\u0016WM\u0019\u0001A\u0006\u000f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g!\rib%\u000b\b\u0003=\u0011r!aH\u0012\u000e\u0003\u0001R!!\t\u0012\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!G\u0005\u0003Ka\tq\u0001]1dW\u0006<W-\u0003\u0002(Q\tA\u0011\n^3sCR|'O\u0003\u0002&1A\u0011!f\u000b\u0007\u0001\t\u0015a\u0003A1\u0001.\u0005\u0005\t\u0015C\u0001\u00182!\t9r&\u0003\u000211\t9aj\u001c;iS:<\u0007CA\f3\u0013\t\u0019\u0004DA\u0002B]f\fa\u0001J5oSR$C#\u0001\u001c\u0011\u0005]9\u0014B\u0001\u001d\u0019\u0005\u0011)f.\u001b;\u0002\u0013\u0019,Go\u00195OKb$\u0018A\u00034fi\u000eD\u0007K]5peR\u0011a\u0007\u0010\u0005\u0006{\r\u0001\rAP\u0001\u0007_\u001a47/\u001a;\u0011\u0005]y\u0014B\u0001!\u0019\u0005\u0011auN\\4\u0002\u001b\u0019,Go\u00195BEN|G.\u001e;f)\t14\tC\u0003E\t\u0001\u0007a(A\u0002q_N\fQbZ3u\r\u0016$8\r[*uCJ$X#\u0001 \u0002\u0017\u001d,G\u000fU8tSRLwN\\\u0015\u0004\u0001%[\u0015B\u0001&\t\u0005I\t%O]1z\r\u0016$8\r[%uKJ\fGo\u001c:\n\u00051C!!F%uKJ\f'\r\\3GKR\u001c\u0007.\u0013;fe\u0006$xN\u001d"
)
public interface FetchIterator extends Iterator {
   void fetchNext();

   // $FF: synthetic method
   static void fetchPrior$(final FetchIterator $this, final long offset) {
      $this.fetchPrior(offset);
   }

   default void fetchPrior(final long offset) {
      this.fetchAbsolute(this.getFetchStart() - offset);
   }

   void fetchAbsolute(final long pos);

   long getFetchStart();

   long getPosition();

   static void $init$(final FetchIterator $this) {
   }
}
