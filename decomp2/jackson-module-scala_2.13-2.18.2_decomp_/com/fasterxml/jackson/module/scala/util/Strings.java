package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\ra\u0004C\u00034\u0001\u0011\rAGA\u0004TiJLgnZ:\u000b\u0005\u00199\u0011\u0001B;uS2T!\u0001C\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005)Y\u0011AB7pIVdWM\u0003\u0002\r\u001b\u00059!.Y2lg>t'B\u0001\b\u0010\u0003%1\u0017m\u001d;feblGNC\u0001\u0011\u0003\r\u0019w.\\\u0002\u0001'\t\u00011\u0003\u0005\u0002\u0015-5\tQCC\u0001\t\u0013\t9RC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0001\"\u0001F\u000e\n\u0005q)\"\u0001B+oSR\f\u0011\"\\6TiJLgnZ,\u0015\u0005}\u0019\u0003C\u0001\u0011\"\u001b\u0005)\u0011B\u0001\u0012\u0006\u0005\u001d\u0019FO]5oO^Ca\u0001\n\u0002\u0005\u0002\u0004)\u0013!\u0001=\u0011\u0007Q1\u0003&\u0003\u0002(+\tAAHY=oC6,g\b\u0005\u0002*a9\u0011!F\f\t\u0003WUi\u0011\u0001\f\u0006\u0003[E\ta\u0001\u0010:p_Rt\u0014BA\u0018\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011G\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005=*\u0012aC;o\u001b.\u001cFO]5oO^#\"\u0001K\u001b\t\u000b\u0011\u001a\u0001\u0019A\u0010"
)
public interface Strings {
   // $FF: synthetic method
   static StringW mkStringW$(final Strings $this, final Function0 x) {
      return $this.mkStringW(x);
   }

   default StringW mkStringW(final Function0 x) {
      return StringW$.MODULE$.apply(x);
   }

   // $FF: synthetic method
   static String unMkStringW$(final Strings $this, final StringW x) {
      return $this.unMkStringW(x);
   }

   default String unMkStringW(final StringW x) {
      return (String)x.value();
   }

   static void $init$(final Strings $this) {
   }
}
