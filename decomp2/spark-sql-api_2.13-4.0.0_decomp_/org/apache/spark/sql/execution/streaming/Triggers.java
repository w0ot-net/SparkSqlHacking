package org.apache.spark.sql.execution.streaming;

import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E;Qa\u0002\u0005\t\nU1Qa\u0006\u0005\t\naAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0002\tBQaK\u0001\u0005\u00021BQaK\u0001\u0005\u0002iBQaK\u0001\u0005\u0002\u0011\u000b\u0001\u0002\u0016:jO\u001e,'o\u001d\u0006\u0003\u0013)\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005-a\u0011!C3yK\u000e,H/[8o\u0015\tia\"A\u0002tc2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\u0002\u0001!\t1\u0012!D\u0001\t\u0005!!&/[4hKJ\u001c8CA\u0001\u001a!\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!F\u0001\tm\u0006d\u0017\u000eZ1uKR\u00111E\n\t\u00035\u0011J!!J\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006O\r\u0001\r\u0001K\u0001\u000bS:$XM\u001d<bY6\u001b\bC\u0001\u000e*\u0013\tQ3D\u0001\u0003M_:<\u0017aB2p]Z,'\u000f\u001e\u000b\u0003Q5BQA\f\u0003A\u0002=\n\u0001\"\u001b8uKJ4\u0018\r\u001c\t\u0003a]r!!M\u001b\u0011\u0005IZR\"A\u001a\u000b\u0005Q\"\u0012A\u0002\u001fs_>$h(\u0003\u000277\u00051\u0001K]3eK\u001aL!\u0001O\u001d\u0003\rM#(/\u001b8h\u0015\t14\u0004\u0006\u0002)w!)a&\u0002a\u0001yA\u0011QHQ\u0007\u0002})\u0011q\bQ\u0001\tIV\u0014\u0018\r^5p]*\u0011\u0011iG\u0001\u000bG>t7-\u001e:sK:$\u0018BA\"?\u0005!!UO]1uS>tGc\u0001\u0015F\r\")aF\u0002a\u0001Q!)qI\u0002a\u0001\u0011\u0006!QO\\5u!\tIu*D\u0001K\u0015\t\t5J\u0003\u0002M\u001b\u0006!Q\u000f^5m\u0015\u0005q\u0015\u0001\u00026bm\u0006L!\u0001\u0015&\u0003\u0011QKW.Z+oSR\u0004"
)
public final class Triggers {
   public static long convert(final long interval, final TimeUnit unit) {
      return Triggers$.MODULE$.convert(interval, unit);
   }

   public static long convert(final Duration interval) {
      return Triggers$.MODULE$.convert(interval);
   }

   public static long convert(final String interval) {
      return Triggers$.MODULE$.convert(interval);
   }

   public static void validate(final long intervalMs) {
      Triggers$.MODULE$.validate(intervalMs);
   }
}
