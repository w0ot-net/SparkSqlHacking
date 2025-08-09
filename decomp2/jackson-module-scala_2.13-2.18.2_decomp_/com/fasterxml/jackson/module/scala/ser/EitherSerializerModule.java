package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.modifiers.EitherTypeModifierModule;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112qAA\u0002\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001e\u0001\u0011\u0005aD\u0001\fFSRDWM]*fe&\fG.\u001b>fe6{G-\u001e7f\u0015\t!Q!A\u0002tKJT!AB\u0004\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005!I\u0011AB7pIVdWM\u0003\u0002\u000b\u0017\u00059!.Y2lg>t'B\u0001\u0007\u000e\u0003%1\u0017m\u001d;feblGNC\u0001\u000f\u0003\r\u0019w.\\\u0002\u0001'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0003)%\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003-M\u0011a!T8ek2,\u0007C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\u0006\u0003%iw\u000eZ5gS\u0016\u00148/\u0003\u0002\u001d3\tAR)\u001b;iKJ$\u0016\u0010]3N_\u0012Lg-[3s\u001b>$W\u000f\\3\u0002\r\u0011Jg.\u001b;%)\u0005y\u0002C\u0001\u0011#\u001b\u0005\t#\"\u0001\u0004\n\u0005\r\n#\u0001B+oSR\u0004"
)
public interface EitherSerializerModule extends EitherTypeModifierModule {
   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$7) {
      x$7.addSerializers(EitherSerializerResolver$.MODULE$);
   }

   static void $init$(final EitherSerializerModule $this) {
      $this.$plus$eq((x$7) -> {
         $anonfun$$init$$1(x$7);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
