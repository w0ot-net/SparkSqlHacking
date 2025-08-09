package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.modifiers.OptionTypeModifierModule;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003&\u0001\u0011\u0005cE\u0001\fPaRLwN\\*fe&\fG.\u001b>fe6{G-\u001e7f\u0015\t)a!A\u0002tKJT!a\u0002\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005%Q\u0011AB7pIVdWM\u0003\u0002\f\u0019\u00059!.Y2lg>t'BA\u0007\u000f\u0003%1\u0017m\u001d;feblGNC\u0001\u0010\u0003\r\u0019w.\\\u0002\u0001'\r\u0001!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0003+)\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003/Q\u0011a!T8ek2,\u0007CA\r\u001d\u001b\u0005Q\"BA\u000e\u0007\u0003%iw\u000eZ5gS\u0016\u00148/\u0003\u0002\u001e5\tAr\n\u001d;j_:$\u0016\u0010]3N_\u0012Lg-[3s\u001b>$W\u000f\\3\u0002\r\u0011Jg.\u001b;%)\u0005\u0001\u0003CA\u0011$\u001b\u0005\u0011#\"A\u0004\n\u0005\u0011\u0012#\u0001B+oSR\fQbZ3u\u001b>$W\u000f\\3OC6,G#A\u0014\u0011\u0005!zcBA\u0015.!\tQ#%D\u0001,\u0015\ta\u0003#\u0001\u0004=e>|GOP\u0005\u0003]\t\na\u0001\u0015:fI\u00164\u0017B\u0001\u00192\u0005\u0019\u0019FO]5oO*\u0011aF\t"
)
public interface OptionSerializerModule extends OptionTypeModifierModule {
   // $FF: synthetic method
   static String getModuleName$(final OptionSerializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "OptionSerializerModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext ctx) {
      ctx.addSerializers(OptionSerializerResolver$.MODULE$);
   }

   static void $init$(final OptionSerializerModule $this) {
      $this.$plus$eq((ctx) -> {
         $anonfun$$init$$1(ctx);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
