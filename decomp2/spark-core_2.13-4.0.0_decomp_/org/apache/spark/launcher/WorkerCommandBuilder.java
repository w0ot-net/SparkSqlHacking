package org.apache.spark.launcher;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.spark.deploy.Command;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193Qa\u0002\u0005\u0001\u0015AA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\tI\u0001\u0011\t\u0011)A\u0005K!A\u0011\u0006\u0001B\u0001B\u0003%!\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00037\u0001\u0011\u0005s\u0007C\u00037\u0001\u0011\u0005QI\u0001\u000bX_J\\WM]\"p[6\fg\u000e\u001a\"vS2$WM\u001d\u0006\u0003\u0013)\t\u0001\u0002\\1v]\u000eDWM\u001d\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sON\u0011\u0001!\u0005\t\u0003%Mi\u0011\u0001C\u0005\u0003)!\u0011a#\u00112tiJ\f7\r^\"p[6\fg\u000e\u001a\"vS2$WM]\u0001\ngB\f'o\u001b%p[\u0016\u001c\u0001\u0001\u0005\u0002\u0019C9\u0011\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00039Y\ta\u0001\u0010:p_Rt$\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\u0012A\u0002)sK\u0012,g-\u0003\u0002#G\t11\u000b\u001e:j]\u001eT!\u0001I\u000f\u0002\u00115,Wn\u001c:z\u001b\n\u0004\"AJ\u0014\u000e\u0003uI!\u0001K\u000f\u0003\u0007%sG/A\u0004d_6l\u0017M\u001c3\u0011\u0005-rS\"\u0001\u0017\u000b\u00055R\u0011A\u00023fa2|\u00170\u0003\u00020Y\t91i\\7nC:$\u0017A\u0002\u001fj]&$h\b\u0006\u00033gQ*\u0004C\u0001\n\u0001\u0011\u0015)B\u00011\u0001\u0018\u0011\u0015!C\u00011\u0001&\u0011\u0015IC\u00011\u0001+\u00031\u0011W/\u001b7e\u0007>lW.\u00198e)\tA\u0004\tE\u0002:}]i\u0011A\u000f\u0006\u0003wq\nA!\u001e;jY*\tQ(\u0001\u0003kCZ\f\u0017BA ;\u0005\u0011a\u0015n\u001d;\t\u000b\u0005+\u0001\u0019\u0001\"\u0002\u0007\u0015tg\u000f\u0005\u0003:\u0007^9\u0012B\u0001#;\u0005\ri\u0015\r\u001d\u000b\u0002q\u0001"
)
public class WorkerCommandBuilder extends AbstractCommandBuilder {
   private final int memoryMb;
   private final Command command;

   public List buildCommand(final Map env) {
      List cmd = this.buildJavaCommand(this.command.classPathEntries().mkString(File.pathSeparator));
      cmd.add("-Xmx" + this.memoryMb + "M");
      this.command.javaOpts().foreach((x$1) -> BoxesRunTime.boxToBoolean($anonfun$buildCommand$1(cmd, x$1)));
      return cmd;
   }

   public List buildCommand() {
      return this.buildCommand(new HashMap());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildCommand$1(final List cmd$1, final String x$1) {
      return cmd$1.add(x$1);
   }

   public WorkerCommandBuilder(final String sparkHome, final int memoryMb, final Command command) {
      this.memoryMb = memoryMb;
      this.command = command;
      this.childEnv.putAll(.MODULE$.MapHasAsJava(command.environment()).asJava());
      this.childEnv.put("SPARK_HOME", sparkHome);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
