package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.resource.ExecutorResourceRequest;
import org.apache.spark.resource.TaskResourceRequest;
import org.apache.spark.status.ApplicationEnvironmentInfoWrapper;
import org.apache.spark.status.api.v1.ApplicationEnvironmentInfo;
import org.apache.spark.status.api.v1.ResourceProfileInfo;
import org.apache.spark.status.api.v1.RuntimeInfo;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4Qa\u0003\u0007\u0001\u0019YAQ!\n\u0001\u0005\u0002\u001dBQ!\u000b\u0001\u0005B)BQa\r\u0001\u0005\u0002QBQa\u000e\u0001\u0005\naBQ!\u0013\u0001\u0005\n)CQ\u0001\u0014\u0001\u0005\n5Ca!\u0019\u0001\u0005\u00029\u0011\u0007BB5\u0001\t\u0003q!\u000eC\u0003m\u0001\u0011%Q\u000eC\u0003x\u0001\u0011%\u0001PA\u0016BaBd\u0017nY1uS>tWI\u001c<je>tW.\u001a8u\u0013:4wn\u0016:baB,'oU3sS\u0006d\u0017N_3s\u0015\tia\"\u0001\u0005qe>$xNY;g\u0015\ty\u0001#\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sON\u0019\u0001aF\u000f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\rqr$I\u0007\u0002\u0019%\u0011\u0001\u0005\u0004\u0002\u000e!J|Go\u001c2vMN+'\u000fR3\u0011\u0005\t\u001aS\"\u0001\b\n\u0005\u0011r!!I!qa2L7-\u0019;j_:,eN^5s_:lWM\u001c;J]\u001a|wK]1qa\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003!\u0002\"A\b\u0001\u0002\u0013M,'/[1mSj,GCA\u00162!\rABFL\u0005\u0003[e\u0011Q!\u0011:sCf\u0004\"\u0001G\u0018\n\u0005AJ\"\u0001\u0002\"zi\u0016DQA\r\u0002A\u0002\u0005\nQ!\u001b8qkR\f1\u0002Z3tKJL\u0017\r\\5{KR\u0011\u0011%\u000e\u0005\u0006m\r\u0001\raK\u0001\u0006Ef$Xm]\u0001$g\u0016\u0014\u0018.\u00197ju\u0016\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8F]ZL'o\u001c8nK:$\u0018J\u001c4p)\tI\u0004\t\u0005\u0002;{9\u0011adO\u0005\u0003y1\t!b\u0015;pe\u0016$\u0016\u0010]3t\u0013\tqtH\u0001\u000eBaBd\u0017nY1uS>tWI\u001c<je>tW.\u001a8u\u0013:4wN\u0003\u0002=\u0019!)\u0011\t\u0002a\u0001\u0005\u0006!\u0011N\u001c4p!\t\u0019\u0005*D\u0001E\u0015\t)e)\u0001\u0002wc)\u0011qID\u0001\u0004CBL\u0017B\u0001 E\u0003\u0015\"Wm]3sS\u0006d\u0017N_3BaBd\u0017nY1uS>tWI\u001c<je>tW.\u001a8u\u0013:4w\u000e\u0006\u0002C\u0017\")\u0011)\u0002a\u0001s\u0005!2/\u001a:jC2L'0\u001a)bSJ\u001cFO]5oON$\"AT)\u0011\u0005iz\u0015B\u0001)@\u0005-\u0001\u0016-\u001b:TiJLgnZ:\t\u000bI3\u0001\u0019A*\u0002\tA\f\u0017N\u001d\t\u00051Q3f+\u0003\u0002V3\t1A+\u001e9mKJ\u0002\"a\u00160\u000f\u0005ac\u0006CA-\u001a\u001b\u0005Q&BA.'\u0003\u0019a$o\\8u}%\u0011Q,G\u0001\u0007!J,G-\u001a4\n\u0005}\u0003'AB*ue&twM\u0003\u0002^3\u0005a2/\u001a:jC2L'0\u001a*fg>,(oY3Qe>4\u0017\u000e\\3J]\u001a|GCA2g!\tQD-\u0003\u0002f\u007f\t\u0019\"+Z:pkJ\u001cW\r\u0015:pM&dW-\u00138g_\")\u0011i\u0002a\u0001OB\u00111\t[\u0005\u0003K\u0012\u000ba\u0004Z3tKJL\u0017\r\\5{KJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LeNZ8\u0015\u0005\u001d\\\u0007\"B!\t\u0001\u0004\u0019\u0017A\t3fg\u0016\u0014\u0018.\u00197ju\u0016,\u00050Z2vi>\u0014(+Z:pkJ\u001cWMU3rk\u0016\u001cH\u000f\u0006\u0002oiB\u0011qN]\u0007\u0002a*\u0011\u0011\u000fE\u0001\te\u0016\u001cx.\u001e:dK&\u00111\u000f\u001d\u0002\u0018\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u0014V-];fgRDQ!Q\u0005A\u0002U\u0004\"A\u000f<\n\u0005M|\u0014A\b3fg\u0016\u0014\u0018.\u00197ju\u0016$\u0016m]6SKN|WO]2f%\u0016\fX/Z:u)\tIH\u0010\u0005\u0002pu&\u00111\u0010\u001d\u0002\u0014)\u0006\u001c8NU3t_V\u00148-\u001a*fcV,7\u000f\u001e\u0005\u0006\u0003*\u0001\r! \t\u0003uyL!a_ "
)
public class ApplicationEnvironmentInfoWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final ApplicationEnvironmentInfoWrapper input) {
      StoreTypes.ApplicationEnvironmentInfoWrapper.Builder builder = StoreTypes.ApplicationEnvironmentInfoWrapper.newBuilder();
      builder.setInfo(this.serializeApplicationEnvironmentInfo(input.info()));
      return builder.build().toByteArray();
   }

   public ApplicationEnvironmentInfoWrapper deserialize(final byte[] bytes) {
      StoreTypes.ApplicationEnvironmentInfoWrapper wrapper = StoreTypes.ApplicationEnvironmentInfoWrapper.parseFrom(bytes);
      return new ApplicationEnvironmentInfoWrapper(this.deserializeApplicationEnvironmentInfo(wrapper.getInfo()));
   }

   private StoreTypes.ApplicationEnvironmentInfo serializeApplicationEnvironmentInfo(final ApplicationEnvironmentInfo info) {
      StoreTypes.RuntimeInfo.Builder runtimeBuilder = StoreTypes.RuntimeInfo.newBuilder();
      RuntimeInfo runtime = info.runtime();
      Utils$.MODULE$.setStringField(runtime.javaHome(), (value) -> runtimeBuilder.setJavaHome(value));
      Utils$.MODULE$.setStringField(runtime.javaVersion(), (value) -> runtimeBuilder.setJavaVersion(value));
      Utils$.MODULE$.setStringField(runtime.scalaVersion(), (value) -> runtimeBuilder.setScalaVersion(value));
      StoreTypes.ApplicationEnvironmentInfo.Builder builder = StoreTypes.ApplicationEnvironmentInfo.newBuilder();
      builder.setRuntime(runtimeBuilder.build());
      info.sparkProperties().foreach((pair) -> builder.addSparkProperties(this.serializePairStrings(pair)));
      info.hadoopProperties().foreach((pair) -> builder.addHadoopProperties(this.serializePairStrings(pair)));
      info.systemProperties().foreach((pair) -> builder.addSystemProperties(this.serializePairStrings(pair)));
      info.metricsProperties().foreach((pair) -> builder.addMetricsProperties(this.serializePairStrings(pair)));
      info.classpathEntries().foreach((pair) -> builder.addClasspathEntries(this.serializePairStrings(pair)));
      info.resourceProfiles().foreach((profile) -> builder.addResourceProfiles(this.serializeResourceProfileInfo(profile)));
      return builder.build();
   }

   private ApplicationEnvironmentInfo deserializeApplicationEnvironmentInfo(final StoreTypes.ApplicationEnvironmentInfo info) {
      StoreTypes.RuntimeInfo rt = info.getRuntime();
      RuntimeInfo runtime = new RuntimeInfo(Utils$.MODULE$.getStringField(rt.hasJavaVersion(), () -> rt.getJavaVersion()), Utils$.MODULE$.getStringField(rt.hasJavaHome(), () -> rt.getJavaHome()), Utils$.MODULE$.getStringField(rt.hasScalaVersion(), () -> rt.getScalaVersion()));
      Function1 pairSSToTuple = (pair) -> new Tuple2(Utils$.MODULE$.getStringField(pair.hasValue1(), () -> pair.getValue1()), Utils$.MODULE$.getStringField(pair.hasValue2(), () -> pair.getValue2()));
      return new ApplicationEnvironmentInfo(runtime, (Seq).MODULE$.ListHasAsScala(info.getSparkPropertiesList()).asScala().map(pairSSToTuple), (Seq).MODULE$.ListHasAsScala(info.getHadoopPropertiesList()).asScala().map(pairSSToTuple), (Seq).MODULE$.ListHasAsScala(info.getSystemPropertiesList()).asScala().map(pairSSToTuple), (Seq).MODULE$.ListHasAsScala(info.getMetricsPropertiesList()).asScala().map(pairSSToTuple), (Seq).MODULE$.ListHasAsScala(info.getClasspathEntriesList()).asScala().map(pairSSToTuple), (Seq).MODULE$.ListHasAsScala(info.getResourceProfilesList()).asScala().map((infox) -> this.deserializeResourceProfileInfo(infox)));
   }

   private StoreTypes.PairStrings serializePairStrings(final Tuple2 pair) {
      StoreTypes.PairStrings.Builder builder = StoreTypes.PairStrings.newBuilder();
      Utils$.MODULE$.setStringField((String)pair._1(), (value) -> builder.setValue1(value));
      Utils$.MODULE$.setStringField((String)pair._2(), (value) -> builder.setValue2(value));
      return builder.build();
   }

   public StoreTypes.ResourceProfileInfo serializeResourceProfileInfo(final ResourceProfileInfo info) {
      StoreTypes.ResourceProfileInfo.Builder builder = StoreTypes.ResourceProfileInfo.newBuilder();
      builder.setId(info.id());
      info.executorResources().foreach((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            ExecutorResourceRequest resource = (ExecutorResourceRequest)x0$1._2();
            StoreTypes.ExecutorResourceRequest.Builder requestBuilder = StoreTypes.ExecutorResourceRequest.newBuilder();
            Utils$.MODULE$.setStringField(resource.resourceName(), (value) -> requestBuilder.setResourceName(value));
            requestBuilder.setAmount(resource.amount());
            Utils$.MODULE$.setStringField(resource.discoveryScript(), (value) -> requestBuilder.setDiscoveryScript(value));
            Utils$.MODULE$.setStringField(resource.vendor(), (value) -> requestBuilder.setVendor(value));
            return builder.putExecutorResources(k, requestBuilder.build());
         } else {
            throw new MatchError(x0$1);
         }
      });
      info.taskResources().foreach((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            TaskResourceRequest resource = (TaskResourceRequest)x0$2._2();
            StoreTypes.TaskResourceRequest.Builder requestBuilder = StoreTypes.TaskResourceRequest.newBuilder();
            Utils$.MODULE$.setStringField(resource.resourceName(), (value) -> requestBuilder.setResourceName(value));
            requestBuilder.setAmount(resource.amount());
            return builder.putTaskResources(k, requestBuilder.build());
         } else {
            throw new MatchError(x0$2);
         }
      });
      return builder.build();
   }

   public ResourceProfileInfo deserializeResourceProfileInfo(final StoreTypes.ResourceProfileInfo info) {
      return new ResourceProfileInfo(info.getId(), (Map).MODULE$.MapHasAsScala(info.getExecutorResourcesMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$1, v) -> this.deserializeExecutorResourceRequest(v)), (Map).MODULE$.MapHasAsScala(info.getTaskResourcesMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$2, v) -> this.deserializeTaskResourceRequest(v)));
   }

   private ExecutorResourceRequest deserializeExecutorResourceRequest(final StoreTypes.ExecutorResourceRequest info) {
      return new ExecutorResourceRequest(Utils$.MODULE$.getStringField(info.hasResourceName(), () -> info.getResourceName()), info.getAmount(), Utils$.MODULE$.getStringField(info.hasDiscoveryScript(), () -> info.getDiscoveryScript()), Utils$.MODULE$.getStringField(info.hasVendor(), () -> info.getVendor()));
   }

   private TaskResourceRequest deserializeTaskResourceRequest(final StoreTypes.TaskResourceRequest info) {
      return new TaskResourceRequest(Utils$.MODULE$.getStringField(info.hasResourceName(), () -> info.getResourceName()), info.getAmount());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
