package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import org.apache.spark.util.ArrayImplicits.;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001\u0003\b\u0010!\u0003\r\taD\u000b\t\u000bq\u0001A\u0011\u0001\u0010\t\r\t\u0002a\u0011A\b$\u0011\u0015A\u0004A\"\u0001:\u0011\u0019y\u0004A\"\u0001\u0010\u0001\")q\n\u0001D\u0001!\")1\u000b\u0001D\u0001)\"9Q\fAI\u0001\n\u0003q\u0006\"B5\u0001\t\u0003Q\u0007\"\u0002?\u0001\t\u000bi\b\u0002CA\u0005\u0001E\u0005IQ\u00010\t\u000f\u0005-\u0001A\"\u0001\u0002\u000e!9\u00111\u0003\u0001\u0007\u0002\u0005U\u0001bBA\r\u0001\u0011\u0005\u00111\u0004\u0002\u0019\u000bb,7-\u001e;pe\u0006cGn\\2bi&|gn\u00117jK:$(B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0014\u0005\u00011\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005y\u0002CA\f!\u0013\t\t\u0003D\u0001\u0003V]&$\u0018AD4fi\u0016CXmY;u_JLEm\u001d\u000b\u0002IA\u0019Q%\f\u0019\u000f\u0005\u0019ZcBA\u0014+\u001b\u0005A#BA\u0015\u001e\u0003\u0019a$o\\8u}%\t\u0011$\u0003\u0002-1\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u00180\u0005\r\u0019V-\u001d\u0006\u0003Ya\u0001\"!M\u001b\u000f\u0005I\u001a\u0004CA\u0014\u0019\u0013\t!\u0004$\u0001\u0004Qe\u0016$WMZ\u0005\u0003m]\u0012aa\u0015;sS:<'B\u0001\u001b\u0019\u0003AI7/\u0012=fGV$xN]!di&4X\r\u0006\u0002;{A\u0011qcO\u0005\u0003ya\u0011qAQ8pY\u0016\fg\u000eC\u0003?\u0007\u0001\u0007\u0001'\u0001\u0002jI\u0006)\"/Z9vKN$Hk\u001c;bY\u0016CXmY;u_J\u001cH\u0003\u0002\u001eB\u0013.CQA\u0011\u0003A\u0002\r\u000bqD]3t_V\u00148-\u001a)s_\u001aLG.Z%e)>tU/\\#yK\u000e,Ho\u001c:t!\u0011\tDI\u0012$\n\u0005\u0015;$aA'baB\u0011qcR\u0005\u0003\u0011b\u00111!\u00138u\u0011\u0015QE\u00011\u0001D\u0003%rW/\u001c'pG\u0006d\u0017\u000e^=Bo\u0006\u0014X\rV1tWN\u0004VM\u001d*fg>,(oY3Qe>4\u0017\u000e\\3JI\")A\n\u0002a\u0001\u001b\u0006!\u0002n\\:u)>dunY1m)\u0006\u001c8nQ8v]R\u0004B!\r#G\u001dB!\u0011\u0007\u0012\u0019G\u0003A\u0011X-];fgR,\u00050Z2vi>\u00148\u000f\u0006\u0002;#\")!+\u0002a\u0001\r\u00061b.^7BI\u0012LG/[8oC2,\u00050Z2vi>\u00148/A\u0007lS2dW\t_3dkR|'o\u001d\u000b\u0006IU;\u0016l\u0017\u0005\u0006-\u001a\u0001\r\u0001J\u0001\fKb,7-\u001e;pe&#7\u000fC\u0003Y\r\u0001\u0007!(\u0001\rbI*,8\u000f\u001e+be\u001e,GOT;n\u000bb,7-\u001e;peNDQA\u0017\u0004A\u0002i\nQbY8v]R4\u0015-\u001b7ve\u0016\u001c\bb\u0002/\u0007!\u0003\u0005\rAO\u0001\u0006M>\u00148-Z\u0001\u0018W&dG.\u0012=fGV$xN]:%I\u00164\u0017-\u001e7uIQ*\u0012a\u0018\u0016\u0003u\u0001\\\u0013!\u0019\t\u0003E\u001el\u0011a\u0019\u0006\u0003I\u0016\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0019D\u0012AC1o]>$\u0018\r^5p]&\u0011\u0001n\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00063fG>lW.[:tS>tW\t_3dkR|'o\u001d\u000b\u0005I-L(\u0010C\u0003m\u0011\u0001\u0007Q.A\u000bfq\u0016\u001cW\u000f^8sg\u0006sG\rR3d_6LeNZ8\u0011\u0007]q\u0007/\u0003\u0002p1\t)\u0011I\u001d:bsB!q#\u001d\u0019t\u0013\t\u0011\bD\u0001\u0004UkBdWM\r\t\u0003i^l\u0011!\u001e\u0006\u0003m>\t\u0011b]2iK\u0012,H.\u001a:\n\u0005a,(\u0001G#yK\u000e,Ho\u001c:EK\u000e|W.\\5tg&|g.\u00138g_\")\u0001\f\u0003a\u0001u!)1\u0010\u0003a\u0001u\u0005\u0019BO]5hO\u0016\u0014X\r\u001a\"z\u000bb,7-\u001e;pe\u0006!B-Z2p[6L7o]5p]\u0016CXmY;u_J$\u0002B\u000f@\u0002\u0002\u0005\u0015\u0011q\u0001\u0005\u0006\u007f&\u0001\r\u0001M\u0001\u000bKb,7-\u001e;pe&#\u0007BBA\u0002\u0013\u0001\u00071/\u0001\teK\u000e|W.\\5tg&|g.\u00138g_\")\u0001,\u0003a\u0001u!910\u0003I\u0001\u0002\u0004Q\u0014A\b3fG>lW.[:tS>tW\t_3dkR|'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0003m!WmY8n[&\u001c8/[8o\u000bb,7-\u001e;peN|e\u000eS8tiR\u0019!(a\u0004\t\r\u0005E1\u00021\u00011\u0003\u0011Awn\u001d;\u0002'-LG\u000e\\#yK\u000e,Ho\u001c:t\u001f:Dun\u001d;\u0015\u0007i\n9\u0002\u0003\u0004\u0002\u00121\u0001\r\u0001M\u0001\rW&dG.\u0012=fGV$xN\u001d\u000b\u0004u\u0005u\u0001\"B@\u000e\u0001\u0004\u0001\u0004"
)
public interface ExecutorAllocationClient {
   Seq getExecutorIds();

   boolean isExecutorActive(final String id);

   boolean requestTotalExecutors(final Map resourceProfileIdToNumExecutors, final Map numLocalityAwareTasksPerResourceProfileId, final Map hostToLocalTaskCount);

   boolean requestExecutors(final int numAdditionalExecutors);

   Seq killExecutors(final Seq executorIds, final boolean adjustTargetNumExecutors, final boolean countFailures, final boolean force);

   // $FF: synthetic method
   static Seq decommissionExecutors$(final ExecutorAllocationClient $this, final Tuple2[] executorsAndDecomInfo, final boolean adjustTargetNumExecutors, final boolean triggeredByExecutor) {
      return $this.decommissionExecutors(executorsAndDecomInfo, adjustTargetNumExecutors, triggeredByExecutor);
   }

   default Seq decommissionExecutors(final Tuple2[] executorsAndDecomInfo, final boolean adjustTargetNumExecutors, final boolean triggeredByExecutor) {
      return this.killExecutors(.MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])executorsAndDecomInfo), (x$1) -> (String)x$1._1(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq(), adjustTargetNumExecutors, false, this.killExecutors$default$4());
   }

   // $FF: synthetic method
   static boolean decommissionExecutor$(final ExecutorAllocationClient $this, final String executorId, final ExecutorDecommissionInfo decommissionInfo, final boolean adjustTargetNumExecutors, final boolean triggeredByExecutor) {
      return $this.decommissionExecutor(executorId, decommissionInfo, adjustTargetNumExecutors, triggeredByExecutor);
   }

   default boolean decommissionExecutor(final String executorId, final ExecutorDecommissionInfo decommissionInfo, final boolean adjustTargetNumExecutors, final boolean triggeredByExecutor) {
      Seq decommissionedExecutors = this.decommissionExecutors((Tuple2[])((Object[])(new Tuple2[]{new Tuple2(executorId, decommissionInfo)})), adjustTargetNumExecutors, triggeredByExecutor);
      return decommissionedExecutors.nonEmpty() && ((String)decommissionedExecutors.apply(0)).equals(executorId);
   }

   // $FF: synthetic method
   static boolean decommissionExecutor$default$4$(final ExecutorAllocationClient $this) {
      return $this.decommissionExecutor$default$4();
   }

   default boolean decommissionExecutor$default$4() {
      return false;
   }

   boolean decommissionExecutorsOnHost(final String host);

   boolean killExecutorsOnHost(final String host);

   // $FF: synthetic method
   static boolean killExecutor$(final ExecutorAllocationClient $this, final String executorId) {
      return $this.killExecutor(executorId);
   }

   default boolean killExecutor(final String executorId) {
      Seq killedExecutors = this.killExecutors(new scala.collection.immutable..colon.colon(executorId, scala.collection.immutable.Nil..MODULE$), true, false, this.killExecutors$default$4());
      return killedExecutors.nonEmpty() && ((String)killedExecutors.apply(0)).equals(executorId);
   }

   // $FF: synthetic method
   static boolean killExecutors$default$4$(final ExecutorAllocationClient $this) {
      return $this.killExecutors$default$4();
   }

   default boolean killExecutors$default$4() {
      return false;
   }

   static void $init$(final ExecutorAllocationClient $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
