package com.fasterxml.jackson.module.scala.modifiers;

import com.fasterxml.jackson.module.scala.JacksonModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003$\u0001\u0011\u0005CEA\fTG\u0006d\u0017\rV=qK6{G-\u001b4jKJlu\u000eZ;mK*\u0011QAB\u0001\n[>$\u0017NZ5feNT!a\u0002\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005%Q\u0011AB7pIVdWM\u0003\u0002\f\u0019\u00059!.Y2lg>t'BA\u0007\u000f\u0003%1\u0017m\u001d;feblGNC\u0001\u0010\u0003\r\u0019w.\\\u0002\u0001'\r\u0001!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0003+)\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003/Q\u0011a!T8ek2,\u0007CA\r\u001b\u001b\u00051\u0011BA\u000e\u0007\u00055Q\u0015mY6t_:lu\u000eZ;mK\u00061A%\u001b8ji\u0012\"\u0012A\b\t\u0003?\u0005j\u0011\u0001\t\u0006\u0002\u000f%\u0011!\u0005\t\u0002\u0005+:LG/A\u0007hKRlu\u000eZ;mK:\u000bW.\u001a\u000b\u0002KA\u0011a%\f\b\u0003O-\u0002\"\u0001\u000b\u0011\u000e\u0003%R!A\u000b\t\u0002\rq\u0012xn\u001c;?\u0013\ta\u0003%\u0001\u0004Qe\u0016$WMZ\u0005\u0003]=\u0012aa\u0015;sS:<'B\u0001\u0017!\u0001"
)
public interface ScalaTypeModifierModule extends JacksonModule {
   // $FF: synthetic method
   static String getModuleName$(final ScalaTypeModifierModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "ScalaTypeModifierModule";
   }

   static void $init$(final ScalaTypeModifierModule $this) {
      $this.$plus$eq(new ScalaTypeModifier());
   }
}
