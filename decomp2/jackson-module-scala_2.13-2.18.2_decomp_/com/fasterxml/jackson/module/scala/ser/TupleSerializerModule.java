package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.JacksonModule;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003$\u0001\u0011\u0005CEA\u000bUkBdWmU3sS\u0006d\u0017N_3s\u001b>$W\u000f\\3\u000b\u0005\u00151\u0011aA:fe*\u0011q\u0001C\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u0013)\ta!\\8ek2,'BA\u0006\r\u0003\u001dQ\u0017mY6t_:T!!\u0004\b\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\b\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u0001\u0011\u0002\u0004\u0005\u0002\u0014-5\tAC\u0003\u0002\u0016\u0015\u0005AA-\u0019;bE&tG-\u0003\u0002\u0018)\t1Qj\u001c3vY\u0016\u0004\"!\u0007\u000e\u000e\u0003\u0019I!a\u0007\u0004\u0003\u001b)\u000b7m[:p]6{G-\u001e7f\u0003\u0019!\u0013N\\5uIQ\ta\u0004\u0005\u0002 C5\t\u0001EC\u0001\b\u0013\t\u0011\u0003E\u0001\u0003V]&$\u0018!D4fi6{G-\u001e7f\u001d\u0006lW\rF\u0001&!\t1SF\u0004\u0002(WA\u0011\u0001\u0006I\u0007\u0002S)\u0011!\u0006E\u0001\u0007yI|w\u000e\u001e \n\u00051\u0002\u0013A\u0002)sK\u0012,g-\u0003\u0002/_\t11\u000b\u001e:j]\u001eT!\u0001\f\u0011"
)
public interface TupleSerializerModule extends JacksonModule {
   // $FF: synthetic method
   static String getModuleName$(final TupleSerializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "TupleSerializerModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$2) {
      x$2.addSerializers(TupleSerializerResolver$.MODULE$);
   }

   static void $init$(final TupleSerializerModule $this) {
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
