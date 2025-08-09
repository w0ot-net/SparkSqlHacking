package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.module.scala.deser.SeqDeserializerModule;
import com.fasterxml.jackson.module.scala.ser.IterableSerializerModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003*\u0001\u0011\u0005#FA\u0005TKFlu\u000eZ;mK*\u0011QAB\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u000f!\ta!\\8ek2,'BA\u0005\u000b\u0003\u001dQ\u0017mY6t_:T!a\u0003\u0007\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\u0007\u0002\u0007\r|Wn\u0001\u0001\u0014\t\u0001\u0001b\u0003\b\t\u0003#Qi\u0011A\u0005\u0006\u0003'!\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003+I\u0011a!T8ek2,\u0007CA\f\u001b\u001b\u0005A\"BA\r\u0005\u0003\r\u0019XM]\u0005\u00037a\u0011\u0001$\u0013;fe\u0006\u0014G.Z*fe&\fG.\u001b>fe6{G-\u001e7f!\ti\u0002%D\u0001\u001f\u0015\tyB!A\u0003eKN,'/\u0003\u0002\"=\t)2+Z9EKN,'/[1mSj,'/T8ek2,\u0017A\u0002\u0013j]&$H\u0005F\u0001%!\t)s%D\u0001'\u0015\u0005)\u0011B\u0001\u0015'\u0005\u0011)f.\u001b;\u0002\u001b\u001d,G/T8ek2,g*Y7f)\u0005Y\u0003C\u0001\u00174\u001d\ti\u0013\u0007\u0005\u0002/M5\tqF\u0003\u00021\u001d\u00051AH]8pizJ!A\r\u0014\u0002\rA\u0013X\rZ3g\u0013\t!TG\u0001\u0004TiJLgn\u001a\u0006\u0003e\u0019\u0002"
)
public interface SeqModule extends IterableSerializerModule, SeqDeserializerModule {
   // $FF: synthetic method
   static String getModuleName$(final SeqModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "SeqModule";
   }

   static void $init$(final SeqModule $this) {
   }
}
