package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.JacksonModule;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003$\u0001\u0011\u0005CEA\u000fF]VlWM]1uS>tG)Z:fe&\fG.\u001b>fe6{G-\u001e7f\u0015\t)a!A\u0003eKN,'O\u0003\u0002\b\u0011\u0005)1oY1mC*\u0011\u0011BC\u0001\u0007[>$W\u000f\\3\u000b\u0005-a\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003\u001b9\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003=\t1aY8n\u0007\u0001\u00192\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\t)\"\"\u0001\u0005eCR\f'-\u001b8e\u0013\t9BC\u0001\u0004N_\u0012,H.\u001a\t\u00033ii\u0011AB\u0005\u00037\u0019\u0011QBS1dWN|g.T8ek2,\u0017A\u0002\u0013j]&$H\u0005F\u0001\u001f!\ty\u0012%D\u0001!\u0015\u00059\u0011B\u0001\u0012!\u0005\u0011)f.\u001b;\u0002\u001b\u001d,G/T8ek2,g*Y7f)\u0005)\u0003C\u0001\u0014.\u001d\t93\u0006\u0005\u0002)A5\t\u0011F\u0003\u0002+!\u00051AH]8pizJ!\u0001\f\u0011\u0002\rA\u0013X\rZ3g\u0013\tqsF\u0001\u0004TiJLgn\u001a\u0006\u0003Y\u0001\u0002"
)
public interface EnumerationDeserializerModule extends JacksonModule {
   // $FF: synthetic method
   static String getModuleName$(final EnumerationDeserializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "EnumerationDeserializerModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext ctxt) {
      ctxt.addDeserializers(EnumerationDeserializerResolver$.MODULE$);
      ctxt.addKeyDeserializers(EnumerationKeyDeserializers$.MODULE$);
   }

   static void $init$(final EnumerationDeserializerModule $this) {
      $this.$plus$eq((ctxt) -> {
         $anonfun$$init$$1(ctxt);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
