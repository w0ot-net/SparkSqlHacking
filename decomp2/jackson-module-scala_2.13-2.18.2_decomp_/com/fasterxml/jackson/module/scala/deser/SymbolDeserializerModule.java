package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.JacksonModule;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003$\u0001\u0011\u0005CE\u0001\rTs6\u0014w\u000e\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014Xj\u001c3vY\u0016T!!\u0002\u0004\u0002\u000b\u0011,7/\u001a:\u000b\u0005\u001dA\u0011!B:dC2\f'BA\u0005\u000b\u0003\u0019iw\u000eZ;mK*\u00111\u0002D\u0001\bU\u0006\u001c7n]8o\u0015\tia\"A\u0005gCN$XM\u001d=nY*\tq\"A\u0002d_6\u001c\u0001aE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ!!\u0006\u0006\u0002\u0011\u0011\fG/\u00192j]\u0012L!a\u0006\u000b\u0003\r5{G-\u001e7f!\tI\"$D\u0001\u0007\u0013\tYbAA\u0007KC\u000e\\7o\u001c8N_\u0012,H.Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003y\u0001\"aH\u0011\u000e\u0003\u0001R\u0011aB\u0005\u0003E\u0001\u0012A!\u00168ji\u0006iq-\u001a;N_\u0012,H.\u001a(b[\u0016$\u0012!\n\t\u0003M5r!aJ\u0016\u0011\u0005!\u0002S\"A\u0015\u000b\u0005)\u0002\u0012A\u0002\u001fs_>$h(\u0003\u0002-A\u00051\u0001K]3eK\u001aL!AL\u0018\u0003\rM#(/\u001b8h\u0015\ta\u0003\u0005"
)
public interface SymbolDeserializerModule extends JacksonModule {
   // $FF: synthetic method
   static String getModuleName$(final SymbolDeserializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "SymbolDeserializerModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$1) {
      x$1.addDeserializers(SymbolDeserializerResolver$.MODULE$);
   }

   static void $init$(final SymbolDeserializerModule $this) {
      $this.$plus$eq((x$1) -> {
         $anonfun$$init$$1(x$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
