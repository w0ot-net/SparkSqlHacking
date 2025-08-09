package org.apache.spark.status.api.v1.streaming;

import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a\u0001\u0002\f\u0018\u0001\u0019B\u0001\"\f\u0001\u0003\u0006\u0004%\tA\f\u0005\te\u0001\u0011\t\u0011)A\u0005_!A1\u0007\u0001BC\u0002\u0013\u0005A\u0007\u0003\u0005A\u0001\t\u0005\t\u0015!\u00036\u0011!\t\u0005A!b\u0001\n\u0003\u0011\u0005\u0002C%\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0011)\u0003!Q1A\u0005\u0002-C\u0001\"\u0014\u0001\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\t\u001d\u0002\u0011)\u0019!C\u0001\u0017\"Aq\n\u0001B\u0001B\u0003%A\n\u0003\u0005Q\u0001\t\u0015\r\u0011\"\u0001R\u0011!Y\u0006A!A!\u0002\u0013\u0011\u0006\u0002\u0003/\u0001\u0005\u000b\u0007I\u0011A&\t\u0011u\u0003!\u0011!Q\u0001\n1C\u0001B\u0018\u0001\u0003\u0006\u0004%\ta\u0013\u0005\t?\u0002\u0011\t\u0011)A\u0005\u0019\"A\u0001\r\u0001BC\u0002\u0013\u0005\u0011\r\u0003\u0005g\u0001\t\u0005\t\u0015!\u0003c\u0011!9\u0007A!b\u0001\n\u0003A\u0007\u0002\u0003=\u0001\u0005\u0003\u0005\u000b\u0011B5\t\re\u0004A\u0011A\u0010{\u00051\u0011VmY3jm\u0016\u0014\u0018J\u001c4p\u0015\tA\u0012$A\u0005tiJ,\u0017-\\5oO*\u0011!dG\u0001\u0003mFR!\u0001H\u000f\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u001f?\u000511\u000f^1ukNT!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\u0002\u0001'\t\u0001q\u0005\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VMZ\u0001\tgR\u0014X-Y7JIV\tq\u0006\u0005\u0002)a%\u0011\u0011'\u000b\u0002\u0004\u0013:$\u0018!C:ue\u0016\fW.\u00133!\u0003)\u0019HO]3b[:\u000bW.Z\u000b\u0002kA\u0011a'\u0010\b\u0003om\u0002\"\u0001O\u0015\u000e\u0003eR!AO\u0013\u0002\rq\u0012xn\u001c;?\u0013\ta\u0014&\u0001\u0004Qe\u0016$WMZ\u0005\u0003}}\u0012aa\u0015;sS:<'B\u0001\u001f*\u0003-\u0019HO]3b[:\u000bW.\u001a\u0011\u0002\u0011%\u001c\u0018i\u0019;jm\u0016,\u0012a\u0011\t\u0004Q\u00113\u0015BA#*\u0005\u0019y\u0005\u000f^5p]B\u0011\u0001fR\u0005\u0003\u0011&\u0012qAQ8pY\u0016\fg.A\u0005jg\u0006\u001bG/\u001b<fA\u0005QQ\r_3dkR|'/\u00133\u0016\u00031\u00032\u0001\u000b#6\u0003-)\u00070Z2vi>\u0014\u0018\n\u001a\u0011\u0002\u0019\u0015DXmY;u_JDun\u001d;\u0002\u001b\u0015DXmY;u_JDun\u001d;!\u00035a\u0017m\u001d;FeJ|'\u000fV5nKV\t!\u000bE\u0002)\tN\u0003\"\u0001V-\u000e\u0003US!AV,\u0002\tU$\u0018\u000e\u001c\u0006\u00021\u0006!!.\u0019<b\u0013\tQVK\u0001\u0003ECR,\u0017A\u00047bgR,%O]8s)&lW\rI\u0001\u0011Y\u0006\u001cH/\u0012:s_JlUm]:bO\u0016\f\u0011\u0003\\1ti\u0016\u0013(o\u001c:NKN\u001c\u0018mZ3!\u0003%a\u0017m\u001d;FeJ|'/\u0001\u0006mCN$XI\u001d:pe\u0002\nA\"\u0019<h\u000bZ,g\u000e\u001e*bi\u0016,\u0012A\u0019\t\u0004Q\u0011\u001b\u0007C\u0001\u0015e\u0013\t)\u0017F\u0001\u0004E_V\u0014G.Z\u0001\u000eCZ<WI^3oiJ\u000bG/\u001a\u0011\u0002\u0015\u00154XM\u001c;SCR,7/F\u0001j!\rQwN\u001d\b\u0003W6t!\u0001\u000f7\n\u0003)J!A\\\u0015\u0002\u000fA\f7m[1hK&\u0011\u0001/\u001d\u0002\u0004'\u0016\f(B\u00018*!\u0011A3/^2\n\u0005QL#A\u0002+va2,'\u0007\u0005\u0002)m&\u0011q/\u000b\u0002\u0005\u0019>tw-A\u0006fm\u0016tGOU1uKN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\n|{z|\u0018\u0011AA\u0002\u0003\u000b\t9!!\u0003\u0002\f\u00055\u0001C\u0001?\u0001\u001b\u00059\u0002\"B\u0017\u0016\u0001\u0004y\u0003\"B\u001a\u0016\u0001\u0004)\u0004\"B!\u0016\u0001\u0004\u0019\u0005\"\u0002&\u0016\u0001\u0004a\u0005\"\u0002(\u0016\u0001\u0004a\u0005\"\u0002)\u0016\u0001\u0004\u0011\u0006\"\u0002/\u0016\u0001\u0004a\u0005\"\u00020\u0016\u0001\u0004a\u0005\"\u00021\u0016\u0001\u0004\u0011\u0007\"B4\u0016\u0001\u0004I\u0007"
)
public class ReceiverInfo {
   private final int streamId;
   private final String streamName;
   private final Option isActive;
   private final Option executorId;
   private final Option executorHost;
   private final Option lastErrorTime;
   private final Option lastErrorMessage;
   private final Option lastError;
   private final Option avgEventRate;
   private final Seq eventRates;

   public int streamId() {
      return this.streamId;
   }

   public String streamName() {
      return this.streamName;
   }

   public Option isActive() {
      return this.isActive;
   }

   public Option executorId() {
      return this.executorId;
   }

   public Option executorHost() {
      return this.executorHost;
   }

   public Option lastErrorTime() {
      return this.lastErrorTime;
   }

   public Option lastErrorMessage() {
      return this.lastErrorMessage;
   }

   public Option lastError() {
      return this.lastError;
   }

   public Option avgEventRate() {
      return this.avgEventRate;
   }

   public Seq eventRates() {
      return this.eventRates;
   }

   public ReceiverInfo(final int streamId, final String streamName, final Option isActive, final Option executorId, final Option executorHost, final Option lastErrorTime, final Option lastErrorMessage, final Option lastError, final Option avgEventRate, final Seq eventRates) {
      this.streamId = streamId;
      this.streamName = streamName;
      this.isActive = isActive;
      this.executorId = executorId;
      this.executorHost = executorHost;
      this.lastErrorTime = lastErrorTime;
      this.lastErrorMessage = lastErrorMessage;
      this.lastError = lastError;
      this.avgEventRate = avgEventRate;
      this.eventRates = eventRates;
   }
}
