package org.apache.spark.util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.scala.DefaultScalaModule.;
import java.io.ByteArrayOutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005)3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!\u0002\u0005\u0005\u0006/\u0001!\t!\u0007\u0005\b;\u0001\u0011\r\u0011\"\u0005\u001f\u0011\u0015Y\u0003\u0001\"\u0001-\u000f\u0019\u0019\u0005\u0002#\u0001\u000b\t\u001a1q\u0001\u0003E\u0001\u0015\u0019CQ\u0001S\u0003\u0005\u0002%\u0013\u0011BS:p]V#\u0018\u000e\\:\u000b\u0005%Q\u0011\u0001B;uS2T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0003\u0001E\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003i\u0001\"AE\u000e\n\u0005q\u0019\"\u0001B+oSR\fa!\\1qa\u0016\u0014X#A\u0010\u0011\u0005\u0001JS\"A\u0011\u000b\u0005\t\u001a\u0013\u0001\u00033bi\u0006\u0014\u0017N\u001c3\u000b\u0005\u0011*\u0013a\u00026bG.\u001cxN\u001c\u0006\u0003M\u001d\n\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003!\n1aY8n\u0013\tQ\u0013E\u0001\u0007PE*,7\r^'baB,'/\u0001\u0007u_*\u001bxN\\*ue&tw\r\u0006\u0002.qA\u0011a&\u000e\b\u0003_M\u0002\"\u0001M\n\u000e\u0003ER!A\r\r\u0002\rq\u0012xn\u001c;?\u0013\t!4#\u0001\u0004Qe\u0016$WMZ\u0005\u0003m]\u0012aa\u0015;sS:<'B\u0001\u001b\u0014\u0011\u0015I4\u00011\u0001;\u0003\u0015\u0011Gn\\2l!\u0011\u00112(\u0010\u000e\n\u0005q\u001a\"!\u0003$v]\u000e$\u0018n\u001c82!\tq\u0014)D\u0001@\u0015\t\u00015%\u0001\u0003d_J,\u0017B\u0001\"@\u00055Q5o\u001c8HK:,'/\u0019;pe\u0006I!j]8o+RLGn\u001d\t\u0003\u000b\u0016i\u0011\u0001C\n\u0004\u000bE9\u0005CA#\u0001\u0003\u0019a\u0014N\\5u}Q\tA\t"
)
public interface JsonUtils {
   void org$apache$spark$util$JsonUtils$_setter_$mapper_$eq(final ObjectMapper x$1);

   ObjectMapper mapper();

   // $FF: synthetic method
   static String toJsonString$(final JsonUtils $this, final Function1 block) {
      return $this.toJsonString(block);
   }

   default String toJsonString(final Function1 block) {
      return (String)SparkErrorUtils$.MODULE$.tryWithResource(() -> new ByteArrayOutputStream(), (baos) -> {
         SparkErrorUtils$.MODULE$.tryWithResource(() -> this.mapper().createGenerator(baos, JsonEncoding.UTF8), (generator) -> {
            $anonfun$toJsonString$4(block, generator);
            return BoxedUnit.UNIT;
         });
         return new String(baos.toByteArray(), StandardCharsets.UTF_8);
      });
   }

   // $FF: synthetic method
   static void $anonfun$toJsonString$4(final Function1 block$1, final JsonGenerator generator) {
      block$1.apply(generator);
   }

   static void $init$(final JsonUtils $this) {
      $this.org$apache$spark$util$JsonUtils$_setter_$mapper_$eq((new ObjectMapper()).registerModule(.MODULE$).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
