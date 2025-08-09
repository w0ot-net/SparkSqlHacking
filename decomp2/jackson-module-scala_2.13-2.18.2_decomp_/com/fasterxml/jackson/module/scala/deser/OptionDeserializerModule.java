package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.module.scala.modifiers.OptionTypeModifierModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003&\u0001\u0011\u0005cE\u0001\rPaRLwN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014Xj\u001c3vY\u0016T!!\u0002\u0004\u0002\u000b\u0011,7/\u001a:\u000b\u0005\u001dA\u0011!B:dC2\f'BA\u0005\u000b\u0003\u0019iw\u000eZ;mK*\u00111\u0002D\u0001\bU\u0006\u001c7n]8o\u0015\tia\"A\u0005gCN$XM\u001d=nY*\tq\"A\u0002d_6\u001c\u0001aE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ!!\u0006\u0006\u0002\u0011\u0011\fG/\u00192j]\u0012L!a\u0006\u000b\u0003\r5{G-\u001e7f!\tIB$D\u0001\u001b\u0015\tYb!A\u0005n_\u0012Lg-[3sg&\u0011QD\u0007\u0002\u0019\u001fB$\u0018n\u001c8UsB,Wj\u001c3jM&,'/T8ek2,\u0017A\u0002\u0013j]&$H\u0005F\u0001!!\t\t3%D\u0001#\u0015\u00059\u0011B\u0001\u0013#\u0005\u0011)f.\u001b;\u0002\u001b\u001d,G/T8ek2,g*Y7f)\u00059\u0003C\u0001\u00150\u001d\tIS\u0006\u0005\u0002+E5\t1F\u0003\u0002-!\u00051AH]8pizJ!A\f\u0012\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]\t\u0002"
)
public interface OptionDeserializerModule extends OptionTypeModifierModule {
   // $FF: synthetic method
   static String getModuleName$(final OptionDeserializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "OptionDeserializerModule";
   }

   static void $init$(final OptionDeserializerModule $this) {
      $this.$plus$eq(OptionDeserializerResolver$.MODULE$);
   }
}
