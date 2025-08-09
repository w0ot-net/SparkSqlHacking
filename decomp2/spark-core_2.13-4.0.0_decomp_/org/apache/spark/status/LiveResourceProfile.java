package org.apache.spark.status;

import org.apache.spark.status.api.v1.ResourceProfileInfo;
import scala.Option;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3A\u0001D\u0007\u0005-!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003\u001e\u0011!!\u0003A!b\u0001\n\u0003)\u0003\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u0011m\u0002!Q1A\u0005\u0002qB\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\t\u0005\u0002\u0011)\u0019!C\u0001\u0007\"Aq\t\u0001B\u0001B\u0003%A\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003P\u0001\u0011\u0005\u0001\u000bC\u0003Z\u0001\u0011E#LA\nMSZ,'+Z:pkJ\u001cW\r\u0015:pM&dWM\u0003\u0002\u000f\u001f\u000511\u000f^1ukNT!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u0002\u0001'\t\u0001q\u0003\u0005\u0002\u001935\tQ\"\u0003\u0002\u001b\u001b\tQA*\u001b<f\u000b:$\u0018\u000e^=\u0002#I,7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE-F\u0001\u001e!\tq\u0012%D\u0001 \u0015\u0005\u0001\u0013!B:dC2\f\u0017B\u0001\u0012 \u0005\rIe\u000e^\u0001\u0013e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0007%A\tfq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKN,\u0012A\n\t\u0005O9\nDG\u0004\u0002)YA\u0011\u0011fH\u0007\u0002U)\u00111&F\u0001\u0007yI|w\u000e\u001e \n\u00055z\u0012A\u0002)sK\u0012,g-\u0003\u00020a\t\u0019Q*\u00199\u000b\u00055z\u0002CA\u00143\u0013\t\u0019\u0004G\u0001\u0004TiJLgn\u001a\t\u0003kaj\u0011A\u000e\u0006\u0003o=\t\u0001B]3t_V\u00148-Z\u0005\u0003sY\u0012q#\u0012=fGV$xN\u001d*fg>,(oY3SKF,Xm\u001d;\u0002%\u0015DXmY;u_J\u0014Vm]8ve\u000e,7\u000fI\u0001\u000ei\u0006\u001c8NU3t_V\u00148-Z:\u0016\u0003u\u0002Ba\n\u00182}A\u0011QgP\u0005\u0003\u0001Z\u00121\u0003V1tWJ+7o\\;sG\u0016\u0014V-];fgR\fa\u0002^1tWJ+7o\\;sG\u0016\u001c\b%A\nnCb$\u0016m]6t!\u0016\u0014X\t_3dkR|'/F\u0001E!\rqR)H\u0005\u0003\r~\u0011aa\u00149uS>t\u0017\u0001F7bqR\u000b7o[:QKJ,\u00050Z2vi>\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0006\u0015.cUJ\u0014\t\u00031\u0001AQaG\u0005A\u0002uAQ\u0001J\u0005A\u0002\u0019BQaO\u0005A\u0002uBQAQ\u0005A\u0002\u0011\u000bQ\u0001^8Ba&$\u0012!\u0015\t\u0003%^k\u0011a\u0015\u0006\u0003)V\u000b!A^\u0019\u000b\u0005Yk\u0011aA1qS&\u0011\u0001l\u0015\u0002\u0014%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&sgm\\\u0001\tI>,\u0006\u000fZ1uKR\t1\f\u0005\u0002\u001f9&\u0011Ql\b\u0002\u0004\u0003:L\b"
)
public class LiveResourceProfile extends LiveEntity {
   private final int resourceProfileId;
   private final Map executorResources;
   private final Map taskResources;
   private final Option maxTasksPerExecutor;

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public Map executorResources() {
      return this.executorResources;
   }

   public Map taskResources() {
      return this.taskResources;
   }

   public Option maxTasksPerExecutor() {
      return this.maxTasksPerExecutor;
   }

   public ResourceProfileInfo toApi() {
      return new ResourceProfileInfo(this.resourceProfileId(), this.executorResources(), this.taskResources());
   }

   public Object doUpdate() {
      return new ResourceProfileWrapper(this.toApi());
   }

   public LiveResourceProfile(final int resourceProfileId, final Map executorResources, final Map taskResources, final Option maxTasksPerExecutor) {
      this.resourceProfileId = resourceProfileId;
      this.executorResources = executorResources;
      this.taskResources = taskResources;
      this.maxTasksPerExecutor = maxTasksPerExecutor;
   }
}
