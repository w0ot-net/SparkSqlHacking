package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.module.scala.JacksonModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003$\u0001\u0011\u0005CEA\fUkBdW\rR3tKJL\u0017\r\\5{KJlu\u000eZ;mK*\u0011QAB\u0001\u0006I\u0016\u001cXM\u001d\u0006\u0003\u000f!\tQa]2bY\u0006T!!\u0003\u0006\u0002\r5|G-\u001e7f\u0015\tYA\"A\u0004kC\u000e\\7o\u001c8\u000b\u00055q\u0011!\u00034bgR,'\u000f_7m\u0015\u0005y\u0011aA2p[\u000e\u00011c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\u0011QCC\u0001\tI\u0006$\u0018MY5oI&\u0011q\u0003\u0006\u0002\u0007\u001b>$W\u000f\\3\u0011\u0005eQR\"\u0001\u0004\n\u0005m1!!\u0004&bG.\u001cxN\\'pIVdW-\u0001\u0004%S:LG\u000f\n\u000b\u0002=A\u0011q$I\u0007\u0002A)\tq!\u0003\u0002#A\t!QK\\5u\u000359W\r^'pIVdWMT1nKR\tQ\u0005\u0005\u0002'[9\u0011qe\u000b\t\u0003Q\u0001j\u0011!\u000b\u0006\u0003UA\ta\u0001\u0010:p_Rt\u0014B\u0001\u0017!\u0003\u0019\u0001&/\u001a3fM&\u0011af\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051\u0002\u0003"
)
public interface TupleDeserializerModule extends JacksonModule {
   // $FF: synthetic method
   static String getModuleName$(final TupleDeserializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "TupleDeserializerModule";
   }

   static void $init$(final TupleDeserializerModule $this) {
      $this.$plus$eq(TupleDeserializerResolver$.MODULE$);
   }
}
