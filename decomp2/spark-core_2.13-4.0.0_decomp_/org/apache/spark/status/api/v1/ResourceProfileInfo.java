package org.apache.spark.status.api.v1;

import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3A\u0001C\u0005\u0001-!AQ\u0004\u0001BC\u0002\u0013\u0005a\u0004\u0003\u0005#\u0001\t\u0005\t\u0015!\u0003 \u0011!\u0019\u0003A!b\u0001\n\u0003!\u0003\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u0011i\u0002!Q1A\u0005\u0002mB\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\u0010\u0005\u0007\u0003\u0002!\ta\u0004\"\u0003'I+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LeNZ8\u000b\u0005)Y\u0011A\u0001<2\u0015\taQ\"A\u0002ba&T!AD\b\u0002\rM$\u0018\r^;t\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7\u0001A\n\u0003\u0001]\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0017AA5e+\u0005y\u0002C\u0001\r!\u0013\t\t\u0013DA\u0002J]R\f1!\u001b3!\u0003E)\u00070Z2vi>\u0014(+Z:pkJ\u001cWm]\u000b\u0002KA!a%\f\u00194\u001d\t93\u0006\u0005\u0002)35\t\u0011F\u0003\u0002++\u00051AH]8pizJ!\u0001L\r\u0002\rA\u0013X\rZ3g\u0013\tqsFA\u0002NCBT!\u0001L\r\u0011\u0005\u0019\n\u0014B\u0001\u001a0\u0005\u0019\u0019FO]5oOB\u0011AgN\u0007\u0002k)\u0011agD\u0001\te\u0016\u001cx.\u001e:dK&\u0011\u0001(\u000e\u0002\u0018\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u0014V-];fgR\f!#\u001a=fGV$xN\u001d*fg>,(oY3tA\u0005iA/Y:l%\u0016\u001cx.\u001e:dKN,\u0012\u0001\u0010\t\u0005M5\u0002T\b\u0005\u00025}%\u0011q(\u000e\u0002\u0014)\u0006\u001c8NU3t_V\u00148-\u001a*fcV,7\u000f^\u0001\u000fi\u0006\u001c8NU3t_V\u00148-Z:!\u0003\u0019a\u0014N\\5u}Q!1)\u0012$H!\t!\u0005!D\u0001\n\u0011\u0015ir\u00011\u0001 \u0011\u0015\u0019s\u00011\u0001&\u0011\u0015Qt\u00011\u0001=\u0001"
)
public class ResourceProfileInfo {
   private final int id;
   private final Map executorResources;
   private final Map taskResources;

   public int id() {
      return this.id;
   }

   public Map executorResources() {
      return this.executorResources;
   }

   public Map taskResources() {
      return this.taskResources;
   }

   public ResourceProfileInfo(final int id, final Map executorResources, final Map taskResources) {
      this.id = id;
      this.executorResources = executorResources;
      this.taskResources = taskResources;
   }
}
