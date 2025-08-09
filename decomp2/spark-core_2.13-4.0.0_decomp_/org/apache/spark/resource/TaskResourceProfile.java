package org.apache.spark.resource;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.util.Utils$;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005%3QAB\u0004\u0001\u0013=A\u0001\u0002\u0006\u0001\u0003\u0006\u0004%\tE\u0006\u0005\nU\u0001\u0011\t\u0011)A\u0005/-BQ\u0001\f\u0001\u0005\u00025BQ\u0001\r\u0001\u0005REBaA\u000e\u0001\u0005R%9$a\u0005+bg.\u0014Vm]8ve\u000e,\u0007K]8gS2,'B\u0001\u0005\n\u0003!\u0011Xm]8ve\u000e,'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0014\u0005\u0001\u0001\u0002CA\t\u0013\u001b\u00059\u0011BA\n\b\u0005=\u0011Vm]8ve\u000e,\u0007K]8gS2,\u0017!\u0004;bg.\u0014Vm]8ve\u000e,7o\u0001\u0001\u0016\u0003]\u0001B\u0001G\u0011%O9\u0011\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00039U\ta\u0001\u0010:p_Rt$\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\u0012A\u0002)sK\u0012,g-\u0003\u0002#G\t\u0019Q*\u00199\u000b\u0005\u0001j\u0002C\u0001\r&\u0013\t13E\u0001\u0004TiJLgn\u001a\t\u0003#!J!!K\u0004\u0003'Q\u000b7o\u001b*fg>,(oY3SKF,Xm\u001d;\u0002\u001dQ\f7o\u001b*fg>,(oY3tA%\u0011ACE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00059z\u0003CA\t\u0001\u0011\u0015!2\u00011\u0001\u0018\u0003!1\u0018\r\\5eCR,G#\u0001\u001a\u0011\u0005M\"T\"A\u000f\n\u0005Uj\"\u0001B+oSR\f!dZ3u\u0007V\u001cHo\\7Fq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKN$\u0012\u0001\u000f\t\u00051\u0005\"\u0013\b\u0005\u0002\u0012u%\u00111h\u0002\u0002\u0018\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u0014V-];fgRD#\u0001A\u001f\u0011\u0005y\nU\"A \u000b\u0005\u0001K\u0011AC1o]>$\u0018\r^5p]&\u0011!i\u0010\u0002\t\u000bZ|GN^5oO\"\u001a\u0001\u0001R$\u0011\u0005y*\u0015B\u0001$@\u0005\u0015\u0019\u0016N\\2fC\u0005A\u0015!B\u001a/i9\u0002\u0004"
)
public class TaskResourceProfile extends ResourceProfile {
   public Map taskResources() {
      return super.taskResources();
   }

   public void validate() {
   }

   public Map getCustomExecutorResources() {
      if (SparkEnv$.MODULE$.get() == null) {
         return super.getCustomExecutorResources();
      } else {
         SparkConf sparkConf = SparkEnv$.MODULE$.get().conf();
         return !Utils$.MODULE$.isDynamicAllocationEnabled(sparkConf) ? ResourceProfile$.MODULE$.getOrCreateDefaultProfile(sparkConf).getCustomExecutorResources() : super.getCustomExecutorResources();
      }
   }

   public TaskResourceProfile(final Map taskResources) {
      super(.MODULE$.Map().empty(), taskResources);
   }
}
