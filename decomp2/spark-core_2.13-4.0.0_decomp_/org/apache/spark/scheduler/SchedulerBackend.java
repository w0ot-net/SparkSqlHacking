package org.apache.spark.scheduler;

import org.apache.spark.resource.ResourceProfile;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001\u0003\n\u0014!\u0003\r\t!F\u000e\t\u000b\t\u0002A\u0011\u0001\u0013\t\u000f!\u0002!\u0019!C\u0005S!)!\u0007\u0001D\u0001I!)1\u0007\u0001D\u0001I!)1\u0007\u0001C\u0001i!)!\b\u0001C\u0001w!)\u0001\n\u0001D\u0001I!)\u0011\n\u0001D\u0001\u0015\")1\n\u0001C\u0001\u0019\")1\f\u0001D\u00019\")A\u000e\u0001C\u0001[\")a\u000e\u0001C\u0001_\")\u0001\u000f\u0001C\u0001c\")1\u000f\u0001C\u0001i\")\u0011\u0010\u0001C\u0001i\")!\u0010\u0001D\u0001w\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-!\u0001E*dQ\u0016$W\u000f\\3s\u0005\u0006\u001c7.\u001a8e\u0015\t!R#A\u0005tG\",G-\u001e7fe*\u0011acF\u0001\u0006gB\f'o\u001b\u0006\u00031e\ta!\u00199bG\",'\"\u0001\u000e\u0002\u0007=\u0014xm\u0005\u0002\u00019A\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002KA\u0011QDJ\u0005\u0003Oy\u0011A!\u00168ji\u0006)\u0011\r\u001d9JIV\t!\u0006\u0005\u0002,a5\tAF\u0003\u0002.]\u0005!A.\u00198h\u0015\u0005y\u0013\u0001\u00026bm\u0006L!!\r\u0017\u0003\rM#(/\u001b8h\u0003\u0015\u0019H/\u0019:u\u0003\u0011\u0019Ho\u001c9\u0015\u0005\u0015*\u0004\"\u0002\u001c\u0006\u0001\u00049\u0014\u0001C3ySR\u001cu\u000eZ3\u0011\u0005uA\u0014BA\u001d\u001f\u0005\rIe\u000e^\u0001\u0018kB$\u0017\r^3Fq\u0016\u001cW\u000f^8sg2{w\rT3wK2$\"!\n\u001f\t\u000bu2\u0001\u0019\u0001 \u0002\u00111|w\rT3wK2\u0004\"a\u0010$\u000f\u0005\u0001#\u0005CA!\u001f\u001b\u0005\u0011%BA\"$\u0003\u0019a$o\\8u}%\u0011QIH\u0001\u0007!J,G-\u001a4\n\u0005E:%BA#\u001f\u00031\u0011XM^5wK>3g-\u001a:t\u0003I!WMZ1vYR\u0004\u0016M]1mY\u0016d\u0017n]7\u0015\u0003]\n\u0001b[5mYR\u000b7o\u001b\u000b\u0006K5\u0013F+\u0017\u0005\u0006\u001d&\u0001\raT\u0001\u0007i\u0006\u001c8.\u00133\u0011\u0005u\u0001\u0016BA)\u001f\u0005\u0011auN\\4\t\u000bMK\u0001\u0019\u0001 \u0002\u0015\u0015DXmY;u_JLE\rC\u0003V\u0013\u0001\u0007a+A\bj]R,'O];qiRC'/Z1e!\tir+\u0003\u0002Y=\t9!i\\8mK\u0006t\u0007\"\u0002.\n\u0001\u0004q\u0014A\u0002:fCN|g.A\thKR$\u0016m]6UQJ,\u0017\r\u001a#v[B$2!\u00186l!\rib\fY\u0005\u0003?z\u0011aa\u00149uS>t\u0007CA1i\u001b\u0005\u0011'BA2e\u0003\t1\u0018G\u0003\u0002fM\u0006\u0019\u0011\r]5\u000b\u0005\u001d,\u0012AB:uCR,8/\u0003\u0002jE\n\u0001B\u000b\u001b:fC\u0012\u001cF/Y2l)J\f7-\u001a\u0005\u0006\u001d*\u0001\ra\u0014\u0005\u0006'*\u0001\rAP\u0001\bSN\u0014V-\u00193z)\u00051\u0016!D1qa2L7-\u0019;j_:LE\rF\u0001?\u0003Q\t\u0007\u000f\u001d7jG\u0006$\u0018n\u001c8BiR,W\u000e\u001d;JIR\t!\u000fE\u0002\u001e=z\n\u0001cZ3u\tJLg/\u001a:M_\u001e,&\u000f\\:\u0016\u0003U\u00042!\b0w!\u0011ytO\u0010 \n\u0005a<%aA'ba\u0006\u0019r-\u001a;Ee&4XM]!uiJL'-\u001e;fg\u0006)R.\u0019=Ok6\u001cuN\\2veJ,g\u000e\u001e+bg.\u001cHCA\u001c}\u0011\u0015i\b\u00031\u0001\u007f\u0003\t\u0011\b\u000fE\u0002\u0000\u0003\u000bi!!!\u0001\u000b\u0007\u0005\rQ#\u0001\u0005sKN|WO]2f\u0013\u0011\t9!!\u0001\u0003\u001fI+7o\\;sG\u0016\u0004&o\u001c4jY\u0016\fQdZ3u'\",hM\u001a7f!V\u001c\b.T3sO\u0016\u0014Hj\\2bi&|gn\u001d\u000b\u0007\u0003\u001b\tY#a\f\u0011\r\u0005=\u0011\u0011DA\u0010\u001d\u0011\t\t\"!\u0006\u000f\u0007\u0005\u000b\u0019\"C\u0001 \u0013\r\t9BH\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tY\"!\b\u0003\u0007M+\u0017OC\u0002\u0002\u0018y\u0001B!!\t\u0002(5\u0011\u00111\u0005\u0006\u0004\u0003K)\u0012aB:u_J\fw-Z\u0005\u0005\u0003S\t\u0019C\u0001\bCY>\u001c7.T1oC\u001e,'/\u00133\t\r\u00055\u0012\u00031\u00018\u00035qW/\u001c)beRLG/[8og\"1\u0011\u0011G\tA\u0002]\n\u0011C]3t_V\u00148-\u001a)s_\u001aLG.Z%e\u0001"
)
public interface SchedulerBackend {
   void org$apache$spark$scheduler$SchedulerBackend$_setter_$org$apache$spark$scheduler$SchedulerBackend$$appId_$eq(final String x$1);

   String org$apache$spark$scheduler$SchedulerBackend$$appId();

   void start();

   void stop();

   // $FF: synthetic method
   static void stop$(final SchedulerBackend $this, final int exitCode) {
      $this.stop(exitCode);
   }

   default void stop(final int exitCode) {
      this.stop();
   }

   // $FF: synthetic method
   static void updateExecutorsLogLevel$(final SchedulerBackend $this, final String logLevel) {
      $this.updateExecutorsLogLevel(logLevel);
   }

   default void updateExecutorsLogLevel(final String logLevel) {
   }

   void reviveOffers();

   int defaultParallelism();

   // $FF: synthetic method
   static void killTask$(final SchedulerBackend $this, final long taskId, final String executorId, final boolean interruptThread, final String reason) {
      $this.killTask(taskId, executorId, interruptThread, reason);
   }

   default void killTask(final long taskId, final String executorId, final boolean interruptThread, final String reason) {
      throw new UnsupportedOperationException();
   }

   Option getTaskThreadDump(final long taskId, final String executorId);

   // $FF: synthetic method
   static boolean isReady$(final SchedulerBackend $this) {
      return $this.isReady();
   }

   default boolean isReady() {
      return true;
   }

   // $FF: synthetic method
   static String applicationId$(final SchedulerBackend $this) {
      return $this.applicationId();
   }

   default String applicationId() {
      return this.org$apache$spark$scheduler$SchedulerBackend$$appId();
   }

   // $FF: synthetic method
   static Option applicationAttemptId$(final SchedulerBackend $this) {
      return $this.applicationAttemptId();
   }

   default Option applicationAttemptId() {
      return .MODULE$;
   }

   // $FF: synthetic method
   static Option getDriverLogUrls$(final SchedulerBackend $this) {
      return $this.getDriverLogUrls();
   }

   default Option getDriverLogUrls() {
      return .MODULE$;
   }

   // $FF: synthetic method
   static Option getDriverAttributes$(final SchedulerBackend $this) {
      return $this.getDriverAttributes();
   }

   default Option getDriverAttributes() {
      return .MODULE$;
   }

   int maxNumConcurrentTasks(final ResourceProfile rp);

   // $FF: synthetic method
   static Seq getShufflePushMergerLocations$(final SchedulerBackend $this, final int numPartitions, final int resourceProfileId) {
      return $this.getShufflePushMergerLocations(numPartitions, resourceProfileId);
   }

   default Seq getShufflePushMergerLocations(final int numPartitions, final int resourceProfileId) {
      return scala.collection.immutable.Nil..MODULE$;
   }

   static void $init$(final SchedulerBackend $this) {
      $this.org$apache$spark$scheduler$SchedulerBackend$_setter_$org$apache$spark$scheduler$SchedulerBackend$$appId_$eq("spark-application-" + System.currentTimeMillis());
   }
}
