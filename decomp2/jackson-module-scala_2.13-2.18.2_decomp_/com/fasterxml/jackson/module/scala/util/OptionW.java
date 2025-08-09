package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0003C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005QgB\u0003A\u0013!\u0005\u0011IB\u0003\t\u0013!\u0005!\tC\u0003D\t\u0011\u0005A\tC\u0003F\t\u0011\u0005a\tC\u0003R\t\u0011\u0005!KA\u0004PaRLwN\\,\u000b\u0005)Y\u0011\u0001B;uS2T!\u0001D\u0007\u0002\u000bM\u001c\u0017\r\\1\u000b\u00059y\u0011AB7pIVdWM\u0003\u0002\u0011#\u00059!.Y2lg>t'B\u0001\n\u0014\u0003%1\u0017m\u001d;feblGNC\u0001\u0015\u0003\r\u0019w.\\\u0002\u0001+\t9beE\u0002\u00011u\u0001\"!G\u000e\u000e\u0003iQ\u0011\u0001D\u0005\u00039i\u0011a!\u00118z%\u00164\u0007c\u0001\u0010 C5\t\u0011\"\u0003\u0002!\u0013\tQ\u0001+[7qK\u0012$\u0016\u0010]3\u0011\u0007e\u0011C%\u0003\u0002$5\t1q\n\u001d;j_:\u0004\"!\n\u0014\r\u0001\u0011)q\u0005\u0001b\u0001Q\t\t\u0011)\u0005\u0002*YA\u0011\u0011DK\u0005\u0003Wi\u0011qAT8uQ&tw\r\u0005\u0002\u001a[%\u0011aF\u0007\u0002\u0004\u0003:L\u0018A\u0002\u0013j]&$H\u0005F\u00012!\tI\"'\u0003\u000245\t!QK\\5u\u0003\u0019y\u0007\u000f^'baV\u0011a'\u000f\u000b\u0003om\u00022!\u0007\u00129!\t)\u0013\bB\u0003;\u0005\t\u0007\u0001FA\u0001C\u0011\u0015a$\u00011\u0001>\u0003\u00051\u0007\u0003B\r?IaJ!a\u0010\u000e\u0003\u0013\u0019+hn\u0019;j_:\f\u0014aB(qi&|gn\u0016\t\u0003=\u0011\u0019\"\u0001\u0002\r\u0002\rqJg.\u001b;?)\u0005\t\u0015!B1qa2LXCA$K)\tA5\nE\u0002\u001f\u0001%\u0003\"!\n&\u0005\u000b\u001d2!\u0019\u0001\u0015\t\r13A\u00111\u0001N\u0003\u0005\t\u0007cA\rO!&\u0011qJ\u0007\u0002\ty\tLh.Y7f}A\u0019\u0011DI%\u0002\u000fUt\u0017\r\u001d9msV\u00111k\u0016\u000b\u0003)b\u00032!\u0007\u0012V!\rI\"E\u0016\t\u0003K]#QaJ\u0004C\u0002!BQ!W\u0004A\u0002i\u000b\u0011A\u001e\t\u0004=\u00011\u0006"
)
public interface OptionW extends PimpedType {
   static Option unapply(final OptionW v) {
      return OptionW$.MODULE$.unapply(v);
   }

   static OptionW apply(final Function0 a) {
      return OptionW$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Option optMap$(final OptionW $this, final Function1 f) {
      return $this.optMap(f);
   }

   default Option optMap(final Function1 f) {
      return (Option)(((Option)this.value()).isEmpty() ? .MODULE$ : scala.Option..MODULE$.apply(f.apply(((Option)this.value()).get())));
   }

   static void $init$(final OptionW $this) {
   }
}
