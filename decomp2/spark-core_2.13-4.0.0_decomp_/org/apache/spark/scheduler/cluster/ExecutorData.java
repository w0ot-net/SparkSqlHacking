package org.apache.spark.scheduler.cluster;

import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma!B\r\u001b\u0001i!\u0003\u0002C\u0015\u0001\u0005\u000b\u0007I\u0011A\u0016\t\u0011I\u0002!\u0011!Q\u0001\n1B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0001\u000e\u0005\tq\u0001\u0011\t\u0011)A\u0005k!A\u0011\b\u0001BC\u0002\u0013\u0005#\bC\u0005I\u0001\t\u0005\t\u0015!\u0003<\u0013\"A!\n\u0001BA\u0002\u0013\u00051\n\u0003\u0005Q\u0001\t\u0005\r\u0011\"\u0001R\u0011!9\u0006A!A!B\u0013a\u0005\u0002\u0003-\u0001\u0005\u000b\u0007I\u0011I&\t\u0013e\u0003!\u0011!Q\u0001\n1S\u0006\u0002C.\u0001\u0005\u000b\u0007I\u0011\t/\t\u0013\u0001\u0004!\u0011!Q\u0001\nu\u000b\u0007\u0002\u00032\u0001\u0005\u000b\u0007I\u0011\t/\t\u0013\r\u0004!\u0011!Q\u0001\nu#\u0007\u0002C3\u0001\u0005\u000b\u0007I\u0011\t4\t\u00131\u0004!\u0011!Q\u0001\n\u001dl\u0007\u0002\u00038\u0001\u0005\u000b\u0007I\u0011I&\t\u0013=\u0004!\u0011!Q\u0001\n1\u0003\b\u0002C9\u0001\u0005\u000b\u0007I\u0011\u0001:\t\u0011Y\u0004!\u0011!Q\u0001\nMD\u0001b\u001e\u0001\u0003\u0006\u0004%\t\u0001\u001f\u0005\ny\u0002\u0011\t\u0011)A\u0005svDaa \u0001\u0005\u0002\u0005\u0005!\u0001D#yK\u000e,Ho\u001c:ECR\f'BA\u000e\u001d\u0003\u001d\u0019G.^:uKJT!!\b\u0010\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0014\u0005\u0001)\u0003C\u0001\u0014(\u001b\u0005Q\u0012B\u0001\u0015\u001b\u00051)\u00050Z2vi>\u0014\u0018J\u001c4p\u0003A)\u00070Z2vi>\u0014XI\u001c3q_&tGo\u0001\u0001\u0016\u00031\u0002\"!\f\u0019\u000e\u00039R!a\f\u0010\u0002\u0007I\u00048-\u0003\u00022]\tq!\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0017!E3yK\u000e,Ho\u001c:F]\u0012\u0004x.\u001b8uA\u0005yQ\r_3dkR|'/\u00113ee\u0016\u001c8/F\u00016!\tic'\u0003\u00028]\tQ!\u000b]2BI\u0012\u0014Xm]:\u0002!\u0015DXmY;u_J\fE\r\u001a:fgN\u0004\u0013\u0001D3yK\u000e,Ho\u001c:I_N$X#A\u001e\u0011\u0005q*eBA\u001fD!\tq\u0014)D\u0001@\u0015\t\u0001%&\u0001\u0004=e>|GO\u0010\u0006\u0002\u0005\u0006)1oY1mC&\u0011A)Q\u0001\u0007!J,G-\u001a4\n\u0005\u0019;%AB*ue&twM\u0003\u0002E\u0003\u0006iQ\r_3dkR|'\u000fS8ti\u0002J!!O\u0014\u0002\u0013\u0019\u0014X-Z\"pe\u0016\u001cX#\u0001'\u0011\u00055sU\"A!\n\u0005=\u000b%aA%oi\u0006iaM]3f\u0007>\u0014Xm]0%KF$\"AU+\u0011\u00055\u001b\u0016B\u0001+B\u0005\u0011)f.\u001b;\t\u000fYC\u0011\u0011!a\u0001\u0019\u0006\u0019\u0001\u0010J\u0019\u0002\u0015\u0019\u0014X-Z\"pe\u0016\u001c\b%\u0001\u0006u_R\fGnQ8sKN\f1\u0002^8uC2\u001cuN]3tA%\u0011\u0001lJ\u0001\nY><WK\u001d7NCB,\u0012!\u0018\t\u0005yy[4(\u0003\u0002`\u000f\n\u0019Q*\u00199\u0002\u00151|w-\u0016:m\u001b\u0006\u0004\b%\u0003\u0002\\O\u0005Q\u0011\r\u001e;sS\n,H/Z:\u0002\u0017\u0005$HO]5ckR,7\u000fI\u0005\u0003E\u001e\nQB]3t_V\u00148-Z:J]\u001a|W#A4\u0011\tqr6\b\u001b\t\u0003S*l\u0011\u0001H\u0005\u0003Wr\u0011A#\u0012=fGV$xN\u001d*fg>,(oY3J]\u001a|\u0017A\u0004:fg>,(oY3t\u0013:4w\u000eI\u0005\u0003K\u001e\n\u0011C]3t_V\u00148-\u001a)s_\u001aLG.Z%e\u0003I\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a\u0011\n\u00059<\u0013A\u0004:fO&\u001cHO]1uS>tGk]\u000b\u0002gB\u0011Q\n^\u0005\u0003k\u0006\u0013A\u0001T8oO\u0006y!/Z4jgR\u0014\u0018\r^5p]R\u001b\b%A\u0005sKF,Xm\u001d;UgV\t\u0011\u0010E\u0002NuNL!a_!\u0003\r=\u0003H/[8o\u0003)\u0011X-];fgR$6\u000fI\u0005\u0003}\u001e\n1B]3rk\u0016\u001cH\u000fV5nK\u00061A(\u001b8jiz\"\u0002$a\u0001\u0002\u0006\u0005\u001d\u0011\u0011BA\u0006\u0003\u001b\ty!!\u0005\u0002\u0014\u0005U\u0011qCA\r!\t1\u0003\u0001C\u0003*1\u0001\u0007A\u0006C\u000341\u0001\u0007Q\u0007C\u0003:1\u0001\u00071\bC\u0003K1\u0001\u0007A\nC\u0003Y1\u0001\u0007A\nC\u0003\\1\u0001\u0007Q\fC\u0003c1\u0001\u0007Q\fC\u0003f1\u0001\u0007q\rC\u0003o1\u0001\u0007A\nC\u0003r1\u0001\u00071\u000fC\u0003x1\u0001\u0007\u0011\u0010"
)
public class ExecutorData extends ExecutorInfo {
   private final RpcEndpointRef executorEndpoint;
   private final RpcAddress executorAddress;
   private int freeCores;
   private final long registrationTs;

   public RpcEndpointRef executorEndpoint() {
      return this.executorEndpoint;
   }

   public RpcAddress executorAddress() {
      return this.executorAddress;
   }

   public String executorHost() {
      return super.executorHost();
   }

   public int freeCores() {
      return this.freeCores;
   }

   public void freeCores_$eq(final int x$1) {
      this.freeCores = x$1;
   }

   public int totalCores() {
      return super.totalCores();
   }

   public Map logUrlMap() {
      return super.logUrlMap();
   }

   public Map attributes() {
      return super.attributes();
   }

   public Map resourcesInfo() {
      return super.resourcesInfo();
   }

   public int resourceProfileId() {
      return super.resourceProfileId();
   }

   public long registrationTs() {
      return this.registrationTs;
   }

   public Option requestTs() {
      return super.requestTime();
   }

   public ExecutorData(final RpcEndpointRef executorEndpoint, final RpcAddress executorAddress, final String executorHost, final int freeCores, final int totalCores, final Map logUrlMap, final Map attributes, final Map resourcesInfo, final int resourceProfileId, final long registrationTs, final Option requestTs) {
      this.executorEndpoint = executorEndpoint;
      this.executorAddress = executorAddress;
      this.freeCores = freeCores;
      this.registrationTs = registrationTs;
      super(executorHost, totalCores, logUrlMap, attributes, resourcesInfo, resourceProfileId, new Some(BoxesRunTime.boxToLong(registrationTs)), requestTs);
   }
}
