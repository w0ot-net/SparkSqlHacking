package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.module.scala.modifiers.IteratorTypeModifierModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003&\u0001\u0011\u0005cE\u0001\rJi\u0016\u0014\u0018\r^8s'\u0016\u0014\u0018.\u00197ju\u0016\u0014Xj\u001c3vY\u0016T!!\u0002\u0004\u0002\u0007M,'O\u0003\u0002\b\u0011\u0005)1oY1mC*\u0011\u0011BC\u0001\u0007[>$W\u000f\\3\u000b\u0005-a\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003\u001b9\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003=\t1aY8n\u0007\u0001\u00192\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\t)\"\"\u0001\u0005eCR\f'-\u001b8e\u0013\t9BC\u0001\u0004N_\u0012,H.\u001a\t\u00033qi\u0011A\u0007\u0006\u00037\u0019\t\u0011\"\\8eS\u001aLWM]:\n\u0005uQ\"AG%uKJ\fGo\u001c:UsB,Wj\u001c3jM&,'/T8ek2,\u0017A\u0002\u0013j]&$H\u0005F\u0001!!\t\t3%D\u0001#\u0015\u00059\u0011B\u0001\u0013#\u0005\u0011)f.\u001b;\u0002\u001b\u001d,G/T8ek2,g*Y7f)\u00059\u0003C\u0001\u00150\u001d\tIS\u0006\u0005\u0002+E5\t1F\u0003\u0002-!\u00051AH]8pizJ!A\f\u0012\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]\t\u0002"
)
public interface IteratorSerializerModule extends IteratorTypeModifierModule {
   // $FF: synthetic method
   static String getModuleName$(final IteratorSerializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "IteratorSerializerModule";
   }

   static void $init$(final IteratorSerializerModule $this) {
      $this.$plus$eq(ScalaIteratorSerializerResolver$.MODULE$);
   }
}
