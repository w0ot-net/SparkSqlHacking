package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\ra\u0004C\u0003@\u0001\u0011\r\u0001IA\u0004DY\u0006\u001c8/Z:\u000b\u0005\u00199\u0011\u0001B;uS2T!\u0001C\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005)Y\u0011AB7pIVdWM\u0003\u0002\r\u001b\u00059!.Y2lg>t'B\u0001\b\u0010\u0003%1\u0017m\u001d;feblGNC\u0001\u0011\u0003\r\u0019w.\\\u0002\u0001'\t\u00011\u0003\u0005\u0002\u0015-5\tQCC\u0001\t\u0013\t9RC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0001\"\u0001F\u000e\n\u0005q)\"\u0001B+oSR\f\u0001\"\\6DY\u0006\u001c8o\u0016\u000b\u0003?\r\u0002\"\u0001I\u0011\u000e\u0003\u0015I!AI\u0003\u0003\r\rc\u0017m]:X\u0011\u0019!#\u0001\"a\u0001K\u0005\t\u0001\u0010E\u0002\u0015M!J!aJ\u000b\u0003\u0011q\u0012\u0017P\\1nKz\u0002$!\u000b\u001c\u0011\u0007)\nDG\u0004\u0002,_A\u0011A&F\u0007\u0002[)\u0011a&E\u0001\u0007yI|w\u000e\u001e \n\u0005A*\u0012A\u0002)sK\u0012,g-\u0003\u00023g\t)1\t\\1tg*\u0011\u0001'\u0006\t\u0003kYb\u0001\u0001B\u00058G\u0005\u0005\t\u0011!B\u0001q\t\u0019q\fJ\u001b\u0012\u0005eb\u0004C\u0001\u000b;\u0013\tYTCA\u0004O_RD\u0017N\\4\u0011\u0005Qi\u0014B\u0001 \u0016\u0005\r\te._\u0001\u000bk:l5n\u00117bgN<VCA!I)\t\u0011u\t\r\u0002D\u000bB\u0019!&\r#\u0011\u0005U*E!\u0003$\u0004\u0003\u0003\u0005\tQ!\u00019\u0005\ryFE\u000e\u0005\u0006I\r\u0001\ra\b\u0003\u0006\u0013\u000e\u0011\r\u0001\u000f\u0002\u0002\u0003\u0002"
)
public interface Classes {
   // $FF: synthetic method
   static ClassW mkClassW$(final Classes $this, final Function0 x) {
      return $this.mkClassW(x);
   }

   default ClassW mkClassW(final Function0 x) {
      return ClassW$.MODULE$.apply(x);
   }

   // $FF: synthetic method
   static Class unMkClassW$(final Classes $this, final ClassW x) {
      return $this.unMkClassW(x);
   }

   default Class unMkClassW(final ClassW x) {
      return (Class)x.value();
   }

   static void $init$(final Classes $this) {
   }
}
