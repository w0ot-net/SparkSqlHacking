package org.apache.spark.streaming;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.SparkHadoopUtil.;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552Aa\u0001\u0003\u0005\u001b!)A\u0003\u0001C\u0001+!)\u0001\u0004\u0001C\u00013\ta2\u000b\u001e:fC6LgnZ\"p]R,\u0007\u0010\u001e)zi\"|g\u000eS3ma\u0016\u0014(BA\u0003\u0007\u0003%\u0019HO]3b[&twM\u0003\u0002\b\u0011\u0005)1\u000f]1sW*\u0011\u0011BC\u0001\u0007CB\f7\r[3\u000b\u0003-\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\ta\u0003\u0005\u0002\u0018\u00015\tA!\u0001\ruef\u0014VmY8wKJ4%o\\7DQ\u0016\u001c7\u000e]8j]R$\"A\u0007\u0011\u0011\u0007=YR$\u0003\u0002\u001d!\t1q\n\u001d;j_:\u0004\"a\u0006\u0010\n\u0005}!!\u0001E*ue\u0016\fW.\u001b8h\u0007>tG/\u001a=u\u0011\u0015\t#\u00011\u0001#\u00039\u0019\u0007.Z2la>Lg\u000e\u001e)bi\"\u0004\"a\t\u0016\u000f\u0005\u0011B\u0003CA\u0013\u0011\u001b\u00051#BA\u0014\r\u0003\u0019a$o\\8u}%\u0011\u0011\u0006E\u0001\u0007!J,G-\u001a4\n\u0005-b#AB*ue&twM\u0003\u0002*!\u0001"
)
public class StreamingContextPythonHelper {
   public Option tryRecoverFromCheckpoint(final String checkpointPath) {
      Option checkpointOption = CheckpointReader$.MODULE$.read(checkpointPath, new SparkConf(), .MODULE$.get().conf(), false);
      return checkpointOption.map((x$8) -> new StreamingContext((SparkContext)null, x$8, (Duration)null));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
