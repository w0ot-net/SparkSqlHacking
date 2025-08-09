package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.ApplicationInfoWrapper;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo$;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.status.api.v1.ApplicationInfo$;
import scala.Option;
import scala.collection.Seq;
import scala.collection.mutable.Buffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005Q3Q\u0001C\u0005\u0001\u0013MAQA\t\u0001\u0005\u0002\u0011BQA\n\u0001\u0005B\u001dBQ\u0001\r\u0001\u0005\u0002EBQ\u0001\u000e\u0001\u0005\nUBQA\u0012\u0001\u0005\n\u001dCQ!\u0013\u0001\u0005\n)CQ!\u0015\u0001\u0005\nI\u0013\u0001%\u00119qY&\u001c\u0017\r^5p]&sgm\\,sCB\u0004XM]*fe&\fG.\u001b>fe*\u0011!bC\u0001\taJ|Go\u001c2vM*\u0011A\"D\u0001\u0007gR\fG/^:\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c2\u0001\u0001\u000b\u001b!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00191\u0004\b\u0010\u000e\u0003%I!!H\u0005\u0003\u001bA\u0013x\u000e^8ck\u001a\u001cVM\u001d#f!\ty\u0002%D\u0001\f\u0013\t\t3B\u0001\fBaBd\u0017nY1uS>t\u0017J\u001c4p/J\f\u0007\u000f]3s\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0013\u0011\u0005m\u0001\u0011!C:fe&\fG.\u001b>f)\tAc\u0006E\u0002\u0016S-J!A\u000b\f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005Ua\u0013BA\u0017\u0017\u0005\u0011\u0011\u0015\u0010^3\t\u000b=\u0012\u0001\u0019\u0001\u0010\u0002\u0003)\f1\u0002Z3tKJL\u0017\r\\5{KR\u0011aD\r\u0005\u0006g\r\u0001\r\u0001K\u0001\u0006Ef$Xm]\u0001\u0019g\u0016\u0014\u0018.\u00197ju\u0016\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8J]\u001a|GC\u0001\u001c>!\t9$H\u0004\u0002\u001cq%\u0011\u0011(C\u0001\u000b'R|'/\u001a+za\u0016\u001c\u0018BA\u001e=\u0005=\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8J]\u001a|'BA\u001d\n\u0011\u0015qD\u00011\u0001@\u0003\u0011IgNZ8\u0011\u0005\u0001+U\"A!\u000b\u0005\t\u001b\u0015A\u0001<2\u0015\t!5\"A\u0002ba&L!aO!\u00025\u0011,7/\u001a:jC2L'0Z!qa2L7-\u0019;j_:LeNZ8\u0015\u0005}B\u0005\"\u0002 \u0006\u0001\u00041\u0014aH:fe&\fG.\u001b>f\u0003B\u0004H.[2bi&|g.\u0011;uK6\u0004H/\u00138g_R\u00111J\u0014\t\u0003o1K!!\u0014\u001f\u0003-\u0005\u0003\b\u000f\\5dCRLwN\\!ui\u0016l\u0007\u000f^%oM>DQA\u0010\u0004A\u0002=\u0003\"\u0001\u0011)\n\u00055\u000b\u0015!\t3fg\u0016\u0014\u0018.\u00197ju\u0016\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8BiR,W\u000e\u001d;J]\u001a|GCA(T\u0011\u0015qt\u00011\u0001L\u0001"
)
public class ApplicationInfoWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final ApplicationInfoWrapper j) {
      StoreTypes.ApplicationInfo jobData = this.serializeApplicationInfo(j.info());
      StoreTypes.ApplicationInfoWrapper.Builder builder = StoreTypes.ApplicationInfoWrapper.newBuilder();
      builder.setInfo(jobData);
      return builder.build().toByteArray();
   }

   public ApplicationInfoWrapper deserialize(final byte[] bytes) {
      StoreTypes.ApplicationInfoWrapper wrapper = StoreTypes.ApplicationInfoWrapper.parseFrom(bytes);
      return new ApplicationInfoWrapper(this.deserializeApplicationInfo(wrapper.getInfo()));
   }

   private StoreTypes.ApplicationInfo serializeApplicationInfo(final ApplicationInfo info) {
      StoreTypes.ApplicationInfo.Builder builder = StoreTypes.ApplicationInfo.newBuilder();
      Utils$.MODULE$.setStringField(info.id(), (value) -> builder.setId(value));
      Utils$.MODULE$.setStringField(info.name(), (value) -> builder.setName(value));
      info.coresGranted().foreach((c) -> $anonfun$serializeApplicationInfo$3(builder, BoxesRunTime.unboxToInt(c)));
      info.maxCores().foreach((c) -> $anonfun$serializeApplicationInfo$4(builder, BoxesRunTime.unboxToInt(c)));
      info.coresPerExecutor().foreach((c) -> $anonfun$serializeApplicationInfo$5(builder, BoxesRunTime.unboxToInt(c)));
      info.memoryPerExecutorMB().foreach((m) -> $anonfun$serializeApplicationInfo$6(builder, BoxesRunTime.unboxToInt(m)));
      info.attempts().foreach((attempt) -> builder.addAttempts(this.serializeApplicationAttemptInfo(attempt)));
      return builder.build();
   }

   private ApplicationInfo deserializeApplicationInfo(final StoreTypes.ApplicationInfo info) {
      Option coresGranted = Utils$.MODULE$.getOptional(info.hasCoresGranted(), (JFunction0.mcI.sp)() -> info.getCoresGranted());
      Option maxCores = Utils$.MODULE$.getOptional(info.hasMaxCores(), (JFunction0.mcI.sp)() -> info.getMaxCores());
      Option coresPerExecutor = Utils$.MODULE$.getOptional(info.hasCoresPerExecutor(), (JFunction0.mcI.sp)() -> info.getCoresPerExecutor());
      Option memoryPerExecutorMB = Utils$.MODULE$.getOptional(info.hasMemoryPerExecutorMb(), (JFunction0.mcI.sp)() -> info.getMemoryPerExecutorMb());
      Buffer attempts = (Buffer).MODULE$.ListHasAsScala(info.getAttemptsList()).asScala().map((infox) -> this.deserializeApplicationAttemptInfo(infox));
      return ApplicationInfo$.MODULE$.apply((String)Utils$.MODULE$.getStringField(info.hasId(), () -> info.getId()), (String)Utils$.MODULE$.getStringField(info.hasName(), () -> info.getName()), (Option)coresGranted, (Option)maxCores, (Option)coresPerExecutor, (Option)memoryPerExecutorMB, (Seq)attempts);
   }

   private StoreTypes.ApplicationAttemptInfo serializeApplicationAttemptInfo(final ApplicationAttemptInfo info) {
      StoreTypes.ApplicationAttemptInfo.Builder builder = StoreTypes.ApplicationAttemptInfo.newBuilder();
      builder.setStartTime(info.startTime().getTime()).setEndTime(info.endTime().getTime()).setLastUpdated(info.lastUpdated().getTime()).setDuration(info.duration()).setCompleted(info.completed());
      Utils$.MODULE$.setStringField(info.sparkUser(), (value) -> builder.setSparkUser(value));
      Utils$.MODULE$.setStringField(info.appSparkVersion(), (value) -> builder.setAppSparkVersion(value));
      info.attemptId().foreach((id) -> builder.setAttemptId(id));
      return builder.build();
   }

   private ApplicationAttemptInfo deserializeApplicationAttemptInfo(final StoreTypes.ApplicationAttemptInfo info) {
      Option attemptId = Utils$.MODULE$.getOptional(info.hasAttemptId(), () -> info.getAttemptId());
      return ApplicationAttemptInfo$.MODULE$.apply(attemptId, new Date(info.getStartTime()), new Date(info.getEndTime()), new Date(info.getLastUpdated()), info.getDuration(), Utils$.MODULE$.getStringField(info.hasSparkUser(), () -> info.getSparkUser()), info.getCompleted(), Utils$.MODULE$.getStringField(info.hasAppSparkVersion(), () -> info.getAppSparkVersion()));
   }

   // $FF: synthetic method
   public static final StoreTypes.ApplicationInfo.Builder $anonfun$serializeApplicationInfo$3(final StoreTypes.ApplicationInfo.Builder builder$1, final int c) {
      return builder$1.setCoresGranted(c);
   }

   // $FF: synthetic method
   public static final StoreTypes.ApplicationInfo.Builder $anonfun$serializeApplicationInfo$4(final StoreTypes.ApplicationInfo.Builder builder$1, final int c) {
      return builder$1.setMaxCores(c);
   }

   // $FF: synthetic method
   public static final StoreTypes.ApplicationInfo.Builder $anonfun$serializeApplicationInfo$5(final StoreTypes.ApplicationInfo.Builder builder$1, final int c) {
      return builder$1.setCoresPerExecutor(c);
   }

   // $FF: synthetic method
   public static final StoreTypes.ApplicationInfo.Builder $anonfun$serializeApplicationInfo$6(final StoreTypes.ApplicationInfo.Builder builder$1, final int m) {
      return builder$1.setMemoryPerExecutorMb(m);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
