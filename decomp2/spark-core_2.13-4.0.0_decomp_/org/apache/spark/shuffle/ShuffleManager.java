package org.apache.spark.shuffle;

import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c\u0001\u0003\b\u0010!\u0003\r\t!E\f\t\u000by\u0001A\u0011\u0001\u0011\t\u000b\u0011\u0002a\u0011A\u0013\t\u000b\u001d\u0003a\u0011\u0001%\t\u000b\t\u0004AQA2\t\u000b\t\u0004a\u0011\u0001<\t\u000f\u00055\u0001A\"\u0001\u0002\u0010!9\u0011\u0011\u0004\u0001\u0007\u0002\u0005m\u0001BBA\u0012\u0001\u0019\u0005\u0001e\u0002\u0005\u0002&=A\t!EA\u0014\r\u001dqq\u0002#\u0001\u0012\u0003SAq!a\u000b\u000b\t\u0003\ti\u0003C\u0004\u00020)!\t!!\r\t\u000f\u0005\r#\u0002\"\u0001\u0002F\tq1\u000b[;gM2,W*\u00198bO\u0016\u0014(B\u0001\t\u0012\u0003\u001d\u0019\b.\u001e4gY\u0016T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\n\u0003\u0001a\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003\u0005\u0002\"!\u0007\u0012\n\u0005\rR\"\u0001B+oSR\fqB]3hSN$XM]*ik\u001a4G.Z\u000b\u0005Ma\u0012U\tF\u0002(WA\u0002\"\u0001K\u0015\u000e\u0003=I!AK\b\u0003\u001bMCWO\u001a4mK\"\u000bg\u000e\u001a7f\u0011\u0015a#\u00011\u0001.\u0003%\u0019\b.\u001e4gY\u0016LE\r\u0005\u0002\u001a]%\u0011qF\u0007\u0002\u0004\u0013:$\b\"B\u0019\u0003\u0001\u0004\u0011\u0014A\u00033fa\u0016tG-\u001a8dsB)1\u0007\u000e\u001cB\t6\t\u0011#\u0003\u00026#\t\t2\u000b[;gM2,G)\u001a9f]\u0012,gnY=\u0011\u0005]BD\u0002\u0001\u0003\u0006s\t\u0011\rA\u000f\u0002\u0002\u0017F\u00111H\u0010\t\u00033qJ!!\u0010\u000e\u0003\u000f9{G\u000f[5oOB\u0011\u0011dP\u0005\u0003\u0001j\u00111!\u00118z!\t9$\tB\u0003D\u0005\t\u0007!HA\u0001W!\t9T\tB\u0003G\u0005\t\u0007!HA\u0001D\u0003%9W\r^,sSR,'/F\u0002J\u001dB#RAS)T1v\u0003B\u0001K&N\u001f&\u0011Aj\u0004\u0002\u000e'\",hM\u001a7f/JLG/\u001a:\u0011\u0005]rE!B\u001d\u0004\u0005\u0004Q\u0004CA\u001cQ\t\u0015\u00195A1\u0001;\u0011\u0015\u00116\u00011\u0001(\u0003\u0019A\u0017M\u001c3mK\")Ak\u0001a\u0001+\u0006)Q.\u00199JIB\u0011\u0011DV\u0005\u0003/j\u0011A\u0001T8oO\")\u0011l\u0001a\u00015\u000691m\u001c8uKb$\bCA\u001a\\\u0013\ta\u0016CA\u0006UCN\\7i\u001c8uKb$\b\"\u00020\u0004\u0001\u0004y\u0016aB7fiJL7m\u001d\t\u0003Q\u0001L!!Y\b\u00037MCWO\u001a4mK^\u0013\u0018\u000e^3NKR\u0014\u0018nY:SKB|'\u000f^3s\u0003%9W\r\u001e*fC\u0012,'/F\u0002eS.$b!\u001a7n_F\u0014\b\u0003\u0002\u0015gQ*L!aZ\b\u0003\u001bMCWO\u001a4mKJ+\u0017\rZ3s!\t9\u0014\u000eB\u0003:\t\t\u0007!\b\u0005\u00028W\u0012)a\t\u0002b\u0001u!)!\u000b\u0002a\u0001O!)a\u000e\u0002a\u0001[\u0005q1\u000f^1siB\u000b'\u000f^5uS>t\u0007\"\u00029\u0005\u0001\u0004i\u0013\u0001D3oIB\u000b'\u000f^5uS>t\u0007\"B-\u0005\u0001\u0004Q\u0006\"\u00020\u0005\u0001\u0004\u0019\bC\u0001\u0015u\u0013\t)xB\u0001\u000eTQV4g\r\\3SK\u0006$W*\u001a;sS\u000e\u001c(+\u001a9peR,'/F\u0002xur$R\u0002_?\u007f\u0003\u0003\t)!a\u0002\u0002\n\u0005-\u0001\u0003\u0002\u0015gsn\u0004\"a\u000e>\u0005\u000be*!\u0019\u0001\u001e\u0011\u0005]bH!\u0002$\u0006\u0005\u0004Q\u0004\"\u0002*\u0006\u0001\u00049\u0003\"B@\u0006\u0001\u0004i\u0013!D:uCJ$X*\u00199J]\u0012,\u0007\u0010\u0003\u0004\u0002\u0004\u0015\u0001\r!L\u0001\fK:$W*\u00199J]\u0012,\u0007\u0010C\u0003o\u000b\u0001\u0007Q\u0006C\u0003q\u000b\u0001\u0007Q\u0006C\u0003Z\u000b\u0001\u0007!\fC\u0003_\u000b\u0001\u00071/A\tv]J,w-[:uKJ\u001c\u0006.\u001e4gY\u0016$B!!\u0005\u0002\u0018A\u0019\u0011$a\u0005\n\u0007\u0005U!DA\u0004C_>dW-\u00198\t\u000b12\u0001\u0019A\u0017\u0002)MDWO\u001a4mK\ncwnY6SKN|GN^3s+\t\ti\u0002E\u0002)\u0003?I1!!\t\u0010\u0005Q\u0019\u0006.\u001e4gY\u0016\u0014En\\2l%\u0016\u001cx\u000e\u001c<fe\u0006!1\u000f^8q\u00039\u0019\u0006.\u001e4gY\u0016l\u0015M\\1hKJ\u0004\"\u0001\u000b\u0006\u0014\u0005)A\u0012A\u0002\u001fj]&$h\b\u0006\u0002\u0002(\u000511M]3bi\u0016$b!a\r\u00026\u0005}\u0002C\u0001\u0015\u0001\u0011\u001d\t9\u0004\u0004a\u0001\u0003s\tAaY8oMB\u00191'a\u000f\n\u0007\u0005u\u0012CA\u0005Ta\u0006\u00148nQ8oM\"9\u0011\u0011\t\u0007A\u0002\u0005E\u0011\u0001C5t\tJLg/\u001a:\u00025\u001d,Go\u00155vM\u001adW-T1oC\u001e,'o\u00117bgNt\u0015-\\3\u0015\t\u0005\u001d\u0013Q\f\t\u0005\u0003\u0013\n9F\u0004\u0003\u0002L\u0005M\u0003cAA'55\u0011\u0011q\n\u0006\u0004\u0003#z\u0012A\u0002\u001fs_>$h(C\u0002\u0002Vi\ta\u0001\u0015:fI\u00164\u0017\u0002BA-\u00037\u0012aa\u0015;sS:<'bAA+5!9\u0011qG\u0007A\u0002\u0005e\u0002"
)
public interface ShuffleManager {
   static String getShuffleManagerClassName(final SparkConf conf) {
      return ShuffleManager$.MODULE$.getShuffleManagerClassName(conf);
   }

   static ShuffleManager create(final SparkConf conf, final boolean isDriver) {
      return ShuffleManager$.MODULE$.create(conf, isDriver);
   }

   ShuffleHandle registerShuffle(final int shuffleId, final ShuffleDependency dependency);

   ShuffleWriter getWriter(final ShuffleHandle handle, final long mapId, final TaskContext context, final ShuffleWriteMetricsReporter metrics);

   // $FF: synthetic method
   static ShuffleReader getReader$(final ShuffleManager $this, final ShuffleHandle handle, final int startPartition, final int endPartition, final TaskContext context, final ShuffleReadMetricsReporter metrics) {
      return $this.getReader(handle, startPartition, endPartition, context, metrics);
   }

   default ShuffleReader getReader(final ShuffleHandle handle, final int startPartition, final int endPartition, final TaskContext context, final ShuffleReadMetricsReporter metrics) {
      return this.getReader(handle, 0, Integer.MAX_VALUE, startPartition, endPartition, context, metrics);
   }

   ShuffleReader getReader(final ShuffleHandle handle, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition, final TaskContext context, final ShuffleReadMetricsReporter metrics);

   boolean unregisterShuffle(final int shuffleId);

   ShuffleBlockResolver shuffleBlockResolver();

   void stop();

   static void $init$(final ShuffleManager $this) {
   }
}
