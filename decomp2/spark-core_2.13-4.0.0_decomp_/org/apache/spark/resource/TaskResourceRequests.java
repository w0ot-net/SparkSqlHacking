package org.apache.spark.resource;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.annotation.Evolving;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005=4AAC\u0006\u0001)!)q\u0005\u0001C\u0001Q!91\u0006\u0001b\u0001\n\u0013a\u0003B\u0002\"\u0001A\u0003%Q\u0006C\u0003D\u0001\u0011\u0005A\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003N\u0001\u0011\u0005a\nC\u0003\r\u0001\u0011\u0005Q\u000bC\u0003]\u0001\u0011\u0005Q\fC\u0003a\u0001\u0011\u0005\u0013M\u0001\u000bUCN\\'+Z:pkJ\u001cWMU3rk\u0016\u001cHo\u001d\u0006\u0003\u00195\t\u0001B]3t_V\u00148-\u001a\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sO\u000e\u00011c\u0001\u0001\u00167A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u0004\"\u0001\b\u0013\u000f\u0005u\u0011cB\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u0014\u0003\u0019a$o\\8u}%\t\u0001$\u0003\u0002$/\u00059\u0001/Y2lC\u001e,\u0017BA\u0013'\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019s#\u0001\u0004=S:LGO\u0010\u000b\u0002SA\u0011!\u0006A\u0007\u0002\u0017\u0005qq\f^1tWJ+7o\\;sG\u0016\u001cX#A\u0017\u0011\t9*tgP\u0007\u0002_)\u0011\u0001'M\u0001\u000bG>t7-\u001e:sK:$(B\u0001\u001a4\u0003\u0011)H/\u001b7\u000b\u0003Q\nAA[1wC&\u0011ag\f\u0002\u0012\u0007>t7-\u001e:sK:$\b*Y:i\u001b\u0006\u0004\bC\u0001\u001d=\u001d\tI$\b\u0005\u0002\u001f/%\u00111hF\u0001\u0007!J,G-\u001a4\n\u0005ur$AB*ue&twM\u0003\u0002</A\u0011!\u0006Q\u0005\u0003\u0003.\u00111\u0003V1tWJ+7o\\;sG\u0016\u0014V-];fgR\fqb\u0018;bg.\u0014Vm]8ve\u000e,7\u000fI\u0001\te\u0016\fX/Z:ugV\tQ\t\u0005\u00039\r^z\u0014BA$?\u0005\ri\u0015\r]\u0001\re\u0016\fX/Z:ug*k\u0015\r]\u000b\u0002\u0015B!1\nT\u001c@\u001b\u0005\t\u0014BA$2\u0003\u0011\u0019\u0007/^:\u0015\u0005=\u0003V\"\u0001\u0001\t\u000bE3\u0001\u0019\u0001*\u0002\r\u0005lw.\u001e8u!\t12+\u0003\u0002U/\t\u0019\u0011J\u001c;\u0015\u0007=3\u0006\fC\u0003X\u000f\u0001\u0007q'\u0001\u0007sKN|WO]2f\u001d\u0006lW\rC\u0003R\u000f\u0001\u0007\u0011\f\u0005\u0002\u00175&\u00111l\u0006\u0002\u0007\t>,(\r\\3\u0002\u0015\u0005$GMU3rk\u0016\u001cH\u000f\u0006\u0002P=\")q\f\u0003a\u0001\u007f\u0005!AO]3r\u0003!!xn\u0015;sS:<G#A\u001c)\u0005\u0001\u0019\u0007C\u00013h\u001b\u0005)'B\u00014\u000e\u0003)\tgN\\8uCRLwN\\\u0005\u0003Q\u0016\u0014\u0001\"\u0012<pYZLgn\u001a\u0015\u0004\u0001)l\u0007C\u00013l\u0013\taWMA\u0003TS:\u001cW-I\u0001o\u0003\u0015\u0019d&\r\u00181\u0001"
)
public class TaskResourceRequests implements Serializable {
   private final ConcurrentHashMap _taskResources = new ConcurrentHashMap();

   private ConcurrentHashMap _taskResources() {
      return this._taskResources;
   }

   public Map requests() {
      return .MODULE$.ConcurrentMapHasAsScala(this._taskResources()).asScala().toMap(scala..less.colon.less..MODULE$.refl());
   }

   public java.util.Map requestsJMap() {
      return .MODULE$.MapHasAsJava(this.requests()).asJava();
   }

   public TaskResourceRequests cpus(final int amount) {
      TaskResourceRequest treq = new TaskResourceRequest(ResourceProfile$.MODULE$.CPUS(), (double)amount);
      this._taskResources().put(ResourceProfile$.MODULE$.CPUS(), treq);
      return this;
   }

   public TaskResourceRequests resource(final String resourceName, final double amount) {
      TaskResourceRequest treq = new TaskResourceRequest(resourceName, amount);
      this._taskResources().put(resourceName, treq);
      return this;
   }

   public TaskResourceRequests addRequest(final TaskResourceRequest treq) {
      this._taskResources().put(treq.resourceName(), treq);
      return this;
   }

   public String toString() {
      return "Task resource requests: " + this._taskResources();
   }
}
