package com.fasterxml.jackson.module.scala.util;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\ra\u0004C\u00035\u0001\u0011\rQGA\u0004PaRLwN\\:\u000b\u0005\u00199\u0011\u0001B;uS2T!\u0001C\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005)Y\u0011AB7pIVdWM\u0003\u0002\r\u001b\u00059!.Y2lg>t'B\u0001\b\u0010\u0003%1\u0017m\u001d;feblGNC\u0001\u0011\u0003\r\u0019w.\\\u0002\u0001'\t\u00011\u0003\u0005\u0002\u0015-5\tQCC\u0001\t\u0013\t9RC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0001\"\u0001F\u000e\n\u0005q)\"\u0001B+oSR\f\u0011\"\\6PaRLwN\\,\u0016\u0005}1CC\u0001\u00110!\r\t#\u0005J\u0007\u0002\u000b%\u00111%\u0002\u0002\b\u001fB$\u0018n\u001c8X!\t)c\u0005\u0004\u0001\u0005\u000b\u001d\u0012!\u0019\u0001\u0015\u0003\u0003\u0005\u000b\"!\u000b\u0017\u0011\u0005QQ\u0013BA\u0016\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001F\u0017\n\u00059*\"aA!os\")\u0001G\u0001a\u0001c\u0005\t\u0001\u0010E\u0002\u0015e\u0011J!aM\u000b\u0003\r=\u0003H/[8o\u0003-)h.T6PaRLwN\\,\u0016\u0005YJDCA\u001c;!\r!\"\u0007\u000f\t\u0003Ke\"QaJ\u0002C\u0002!BQ\u0001M\u0002A\u0002m\u00022!\t\u00129\u0001"
)
public interface Options {
   // $FF: synthetic method
   static OptionW mkOptionW$(final Options $this, final Option x) {
      return $this.mkOptionW(x);
   }

   default OptionW mkOptionW(final Option x) {
      return OptionW$.MODULE$.apply(() -> x);
   }

   // $FF: synthetic method
   static Option unMkOptionW$(final Options $this, final OptionW x) {
      return $this.unMkOptionW(x);
   }

   default Option unMkOptionW(final OptionW x) {
      return (Option)x.value();
   }

   static void $init$(final Options $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
