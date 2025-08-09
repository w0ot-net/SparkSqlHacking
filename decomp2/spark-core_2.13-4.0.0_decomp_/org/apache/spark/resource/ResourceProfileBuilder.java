package org.apache.spark.resource;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.annotation.Evolving;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005m4A\u0001E\t\u00015!)\u0011\u0005\u0001C\u0001E!9Q\u0005\u0001b\u0001\n\u00131\u0003BB \u0001A\u0003%q\u0005C\u0004A\u0001\t\u0007I\u0011B!\t\r\u0019\u0003\u0001\u0015!\u0003C\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u0015!\u0006\u0001\"\u0001V\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u00159\u0006\u0001\"\u0001`\u0011\u0015!\u0007\u0001\"\u0001f\u0011\u00151\u0007\u0001\"\u0001f\u0011\u00159\u0007\u0001\"\u0011i\u0011\u0015I\u0007\u0001\"\u0001k\u0005Y\u0011Vm]8ve\u000e,\u0007K]8gS2,')^5mI\u0016\u0014(B\u0001\n\u0014\u0003!\u0011Xm]8ve\u000e,'B\u0001\u000b\u0016\u0003\u0015\u0019\b/\u0019:l\u0015\t1r#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00021\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\u0007\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0019\u0003C\u0001\u0013\u0001\u001b\u0005\t\u0012AD0uCN\\'+Z:pkJ\u001cWm]\u000b\u0002OA!\u0001fL\u0019=\u001b\u0005I#B\u0001\u0016,\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003Y5\nA!\u001e;jY*\ta&\u0001\u0003kCZ\f\u0017B\u0001\u0019*\u0005E\u0019uN\\2veJ,g\u000e\u001e%bg\"l\u0015\r\u001d\t\u0003eer!aM\u001c\u0011\u0005QjR\"A\u001b\u000b\u0005YJ\u0012A\u0002\u001fs_>$h(\u0003\u00029;\u00051\u0001K]3eK\u001aL!AO\u001e\u0003\rM#(/\u001b8h\u0015\tAT\u0004\u0005\u0002%{%\u0011a(\u0005\u0002\u0014)\u0006\u001c8NU3t_V\u00148-\u001a*fcV,7\u000f^\u0001\u0010?R\f7o\u001b*fg>,(oY3tA\u0005\u0011r,\u001a=fGV$xN\u001d*fg>,(oY3t+\u0005\u0011\u0005\u0003\u0002\u00150c\r\u0003\"\u0001\n#\n\u0005\u0015\u000b\"aF#yK\u000e,Ho\u001c:SKN|WO]2f%\u0016\fX/Z:u\u0003MyV\r_3dkR|'OU3t_V\u00148-Z:!\u00035!\u0018m]6SKN|WO]2fgV\t\u0011\n\u0005\u00033\u0015Fb\u0014BA&<\u0005\ri\u0015\r]\u0001\u0012Kb,7-\u001e;peJ+7o\\;sG\u0016\u001cX#\u0001(\u0011\tIR\u0015gQ\u0001\u0012i\u0006\u001c8NU3t_V\u00148-Z:K\u001b\u0006\u0004X#A)\u0011\tI\u001b\u0016\u0007P\u0007\u0002W%\u00111jK\u0001\u0016Kb,7-\u001e;peJ+7o\\;sG\u0016\u001c(*T1q+\u00051\u0006\u0003\u0002*Tc\r\u000bqA]3rk&\u0014X\r\u0006\u0002Z56\t\u0001\u0001C\u0003\\\u0015\u0001\u0007A,\u0001\u0005sKF,Xm\u001d;t!\t!S,\u0003\u0002_#\tAR\t_3dkR|'OU3t_V\u00148-\u001a*fcV,7\u000f^:\u0015\u0005e\u0003\u0007\"B.\f\u0001\u0004\t\u0007C\u0001\u0013c\u0013\t\u0019\u0017C\u0001\u000bUCN\\'+Z:pkJ\u001cWMU3rk\u0016\u001cHo]\u0001\u001eG2,\u0017M]#yK\u000e,Ho\u001c:SKN|WO]2f%\u0016\fX/Z:ugR\t\u0011,A\rdY\u0016\f'\u000fV1tWJ+7o\\;sG\u0016\u0014V-];fgR\u001c\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003E\nQAY;jY\u0012$\u0012a\u001b\t\u0003I1L!!\\\t\u0003\u001fI+7o\\;sG\u0016\u0004&o\u001c4jY\u0016D#\u0001A8\u0011\u0005A\u001cX\"A9\u000b\u0005I\u001c\u0012AC1o]>$\u0018\r^5p]&\u0011A/\u001d\u0002\t\u000bZ|GN^5oO\"\u001a\u0001A^=\u0011\u0005A<\u0018B\u0001=r\u0005\u0015\u0019\u0016N\\2fC\u0005Q\u0018!B\u001a/c9\u0002\u0004"
)
public class ResourceProfileBuilder {
   private final ConcurrentHashMap _taskResources = new ConcurrentHashMap();
   private final ConcurrentHashMap _executorResources = new ConcurrentHashMap();

   private ConcurrentHashMap _taskResources() {
      return this._taskResources;
   }

   private ConcurrentHashMap _executorResources() {
      return this._executorResources;
   }

   public Map taskResources() {
      return .MODULE$.ConcurrentMapHasAsScala(this._taskResources()).asScala().toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Map executorResources() {
      return .MODULE$.ConcurrentMapHasAsScala(this._executorResources()).asScala().toMap(scala..less.colon.less..MODULE$.refl());
   }

   public java.util.Map taskResourcesJMap() {
      return .MODULE$.ConcurrentMapHasAsJava(.MODULE$.ConcurrentMapHasAsScala(this._taskResources()).asScala()).asJava();
   }

   public java.util.Map executorResourcesJMap() {
      return .MODULE$.ConcurrentMapHasAsJava(.MODULE$.ConcurrentMapHasAsScala(this._executorResources()).asScala()).asJava();
   }

   public ResourceProfileBuilder require(final ExecutorResourceRequests requests) {
      this._executorResources().putAll(.MODULE$.MapHasAsJava(requests.requests()).asJava());
      return this;
   }

   public ResourceProfileBuilder require(final TaskResourceRequests requests) {
      this._taskResources().putAll(.MODULE$.MapHasAsJava(requests.requests()).asJava());
      return this;
   }

   public ResourceProfileBuilder clearExecutorResourceRequests() {
      this._executorResources().clear();
      return this;
   }

   public ResourceProfileBuilder clearTaskResourceRequests() {
      this._taskResources().clear();
      return this;
   }

   public String toString() {
      Object var10000 = .MODULE$.ConcurrentMapHasAsScala(this._executorResources()).asScala().map((pair) -> {
         Object var10000 = pair._1();
         return var10000 + "=" + ((ExecutorResourceRequest)pair._2()).toString();
      });
      return "Profile executor resources: " + var10000 + ", task resources: " + .MODULE$.ConcurrentMapHasAsScala(this._taskResources()).asScala().map((pair) -> {
         Object var10000 = pair._1();
         return var10000 + "=" + ((TaskResourceRequest)pair._2()).toString();
      });
   }

   public ResourceProfile build() {
      return (ResourceProfile)(this._executorResources().isEmpty() ? new TaskResourceProfile(this.taskResources()) : new ResourceProfile(this.executorResources(), this.taskResources()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
