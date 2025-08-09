package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qAB\u0004\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0019\u0005\u0011\u0005C\u0003.\u0001\u0019\u0005a\u0006C\u00034\u0001\u0019\u0005A\u0007C\u0003?\u0001\u0011\u0005\u0011E\u0001\u0004Ts6\u0014w\u000e\u001c\u0006\u0003\u0011%\t\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u0003\u0015-\taa]2bY\u0006\u0004(B\u0001\u0007\u000e\u0003\u0019Q7o\u001c85g*\ta\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\u001a\u001b\u00059\u0011B\u0001\u000e\b\u0005\u00151E.Y4t\u0003\u0019!\u0013N\\5uIQ\tQ\u0004\u0005\u0002\u0013=%\u0011qd\u0005\u0002\u0005+:LG/\u0001\u0003oC6,W#\u0001\u0012\u0011\u0005\rRcB\u0001\u0013)!\t)3#D\u0001'\u0015\t9s\"\u0001\u0004=e>|GOP\u0005\u0003SM\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\u0019\u0019FO]5oO*\u0011\u0011fE\u0001\u0007a\u0006\u0014XM\u001c;\u0016\u0003=\u00022A\u0005\u00193\u0013\t\t4C\u0001\u0004PaRLwN\u001c\t\u00031\u0001\t\u0001b\u00195jY\u0012\u0014XM\\\u000b\u0002kA\u0019ag\u000f\u001a\u000f\u0005]JdBA\u00139\u0013\u0005!\u0012B\u0001\u001e\u0014\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0007M+\u0017O\u0003\u0002;'\u0005!\u0001/\u0019;i\u0001"
)
public interface Symbol extends Flags {
   String name();

   Option parent();

   Seq children();

   // $FF: synthetic method
   static String path$(final Symbol $this) {
      return $this.path();
   }

   default String path() {
      return (new StringBuilder(0)).append((String)this.parent().map((x$1) -> (new StringBuilder(1)).append(x$1.path()).append(".").toString()).getOrElse(() -> "")).append(this.name()).toString();
   }

   static void $init$(final Symbol $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
