package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0003C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0011gB\u00038\u0013!\u0005\u0001HB\u0003\t\u0013!\u0005\u0011\bC\u0003;\t\u0011\u00051\bC\u0003=\t\u0011\u0005Q\bC\u0003B\t\u0011\u0005!IA\u0004TiJLgnZ,\u000b\u0005)Y\u0011\u0001B;uS2T!\u0001D\u0007\u0002\u000bM\u001c\u0017\r\\1\u000b\u00059y\u0011AB7pIVdWM\u0003\u0002\u0011#\u00059!.Y2lg>t'B\u0001\n\u0014\u0003%1\u0017m\u001d;feblGNC\u0001\u0015\u0003\r\u0019w.\\\u0002\u0001'\r\u0001q\u0003\b\t\u00031ii\u0011!\u0007\u0006\u0002\u0019%\u00111$\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007uq\u0002%D\u0001\n\u0013\ty\u0012B\u0001\u0006QS6\u0004X\r\u001a+za\u0016\u0004\"!\t\u0015\u000f\u0005\t2\u0003CA\u0012\u001a\u001b\u0005!#BA\u0013\u0016\u0003\u0019a$o\\8u}%\u0011q%G\u0001\u0007!J,G-\u001a4\n\u0005%R#AB*ue&twM\u0003\u0002(3\u00051A%\u001b8ji\u0012\"\u0012!\f\t\u000319J!aL\r\u0003\tUs\u0017\u000e^\u0001\n_JLe-R7qif$\"\u0001\t\u001a\t\rM\u0012A\u00111\u00015\u0003\t\u0019(\u0007E\u0002\u0019k\u0001J!AN\r\u0003\u0011q\u0012\u0017P\\1nKz\nqa\u0015;sS:<w\u000b\u0005\u0002\u001e\tM\u0011AaF\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\nQ!\u00199qYf$\"AP \u0011\u0005u\u0001\u0001B\u0002!\u0007\t\u0003\u0007A'A\u0001t\u0003\u001d)h.\u00199qYf$\"a\u0011$\u0011\u0007a!\u0005%\u0003\u0002F3\t1q\n\u001d;j_:DQ\u0001Q\u0004A\u0002y\u0002"
)
public interface StringW extends PimpedType {
   static Option unapply(final StringW s) {
      return StringW$.MODULE$.unapply(s);
   }

   static StringW apply(final Function0 s) {
      return StringW$.MODULE$.apply(s);
   }

   // $FF: synthetic method
   static String orIfEmpty$(final StringW $this, final Function0 s2) {
      return $this.orIfEmpty(s2);
   }

   default String orIfEmpty(final Function0 s2) {
      return ((String)this.value()).isEmpty() ? (String)s2.apply() : (String)this.value();
   }

   static void $init$(final StringW $this) {
   }
}
