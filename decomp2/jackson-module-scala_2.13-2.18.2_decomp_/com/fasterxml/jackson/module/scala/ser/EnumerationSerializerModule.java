package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.module.scala.JacksonModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003$\u0001\u0011\u0005CEA\u000eF]VlWM]1uS>t7+\u001a:jC2L'0\u001a:N_\u0012,H.\u001a\u0006\u0003\u000b\u0019\t1a]3s\u0015\t9\u0001\"A\u0003tG\u0006d\u0017M\u0003\u0002\n\u0015\u00051Qn\u001c3vY\u0016T!a\u0003\u0007\u0002\u000f)\f7m[:p]*\u0011QBD\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011aD\u0001\u0004G>l7\u0001A\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"BA\u000b\u000b\u0003!!\u0017\r^1cS:$\u0017BA\f\u0015\u0005\u0019iu\u000eZ;mKB\u0011\u0011DG\u0007\u0002\r%\u00111D\u0002\u0002\u000e\u0015\u0006\u001c7n]8o\u001b>$W\u000f\\3\u0002\r\u0011Jg.\u001b;%)\u0005q\u0002CA\u0010\"\u001b\u0005\u0001#\"A\u0004\n\u0005\t\u0002#\u0001B+oSR\fQbZ3u\u001b>$W\u000f\\3OC6,G#A\u0013\u0011\u0005\u0019jcBA\u0014,!\tA\u0003%D\u0001*\u0015\tQ\u0003#\u0001\u0004=e>|GOP\u0005\u0003Y\u0001\na\u0001\u0015:fI\u00164\u0017B\u0001\u00180\u0005\u0019\u0019FO]5oO*\u0011A\u0006\t"
)
public interface EnumerationSerializerModule extends JacksonModule {
   // $FF: synthetic method
   static String getModuleName$(final EnumerationSerializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "EnumerationSerializerModule";
   }

   static void $init$(final EnumerationSerializerModule $this) {
      $this.$plus$eq(EnumerationSerializerResolver$.MODULE$);
   }
}
