package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.JacksonModule;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005i2qAB\u0004\u0011\u0002\u0007\u0005A\u0003C\u0003 \u0001\u0011\u0005\u0001\u0005C\u0003'\u0001\u0011\u0005seB\u00034\u000f!\u0005AGB\u0003\u0007\u000f!\u0005a\u0007C\u00039\t\u0011\u0005\u0011HA\u000fTG\u0006d\u0017m\u00142kK\u000e$H)Z:fe&\fG.\u001b>fe6{G-\u001e7f\u0015\tA\u0011\"A\u0003eKN,'O\u0003\u0002\u000b\u0017\u0005)1oY1mC*\u0011A\"D\u0001\u0007[>$W\u000f\\3\u000b\u00059y\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003!E\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003I\t1aY8n\u0007\u0001\u00192\u0001A\u000b\u001c!\t1\u0012$D\u0001\u0018\u0015\tAR\"\u0001\u0005eCR\f'-\u001b8e\u0013\tQrC\u0001\u0004N_\u0012,H.\u001a\t\u00039ui\u0011!C\u0005\u0003=%\u0011QBS1dWN|g.T8ek2,\u0017A\u0002\u0013j]&$H\u0005F\u0001\"!\t\u0011C%D\u0001$\u0015\u0005Q\u0011BA\u0013$\u0005\u0011)f.\u001b;\u0002\u001b\u001d,G/T8ek2,g*Y7f)\u0005A\u0003CA\u00151\u001d\tQc\u0006\u0005\u0002,G5\tAF\u0003\u0002.'\u00051AH]8pizJ!aL\u0012\u0002\rA\u0013X\rZ3g\u0013\t\t$G\u0001\u0004TiJLgn\u001a\u0006\u0003_\r\nQdU2bY\u0006|%M[3di\u0012+7/\u001a:jC2L'0\u001a:N_\u0012,H.\u001a\t\u0003k\u0011i\u0011aB\n\u0004\tU9\u0004CA\u001b\u0001\u0003\u0019a\u0014N\\5u}Q\tA\u0007"
)
public interface ScalaObjectDeserializerModule extends JacksonModule {
   static Iterable getDependencies() {
      return ScalaObjectDeserializerModule$.MODULE$.getDependencies();
   }

   static Object getTypeId() {
      return ScalaObjectDeserializerModule$.MODULE$.getTypeId();
   }

   // $FF: synthetic method
   static String getModuleName$(final ScalaObjectDeserializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "ScalaObjectDeserializerModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$2) {
      x$2.addDeserializers(ScalaObjectDeserializerResolver$.MODULE$);
   }

   static void $init$(final ScalaObjectDeserializerModule $this) {
      $this.$plus$eq((x$2) -> {
         $anonfun$$init$$1(x$2);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
