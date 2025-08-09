package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.module.scala.JacksonModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2qAA\u0002\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001c\u0001\u0011\u0005AD\u0001\rFSRDWM\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014Xj\u001c3vY\u0016T!\u0001B\u0003\u0002\u000b\u0011,7/\u001a:\u000b\u0005\u00199\u0011!B:dC2\f'B\u0001\u0005\n\u0003\u0019iw\u000eZ;mK*\u0011!bC\u0001\bU\u0006\u001c7n]8o\u0015\taQ\"A\u0005gCN$XM\u001d=nY*\ta\"A\u0002d_6\u001c\u0001aE\u0002\u0001#]\u0001\"AE\u000b\u000e\u0003MQ!\u0001F\u0005\u0002\u0011\u0011\fG/\u00192j]\u0012L!AF\n\u0003\r5{G-\u001e7f!\tA\u0012$D\u0001\u0006\u0013\tQRAA\u0007KC\u000e\\7o\u001c8N_\u0012,H.Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003u\u0001\"A\b\u0011\u000e\u0003}Q\u0011AB\u0005\u0003C}\u0011A!\u00168ji\u0002"
)
public interface EitherDeserializerModule extends JacksonModule {
   static void $init$(final EitherDeserializerModule $this) {
      $this.$plus$eq(EitherDeserializerResolver$.MODULE$);
   }
}
