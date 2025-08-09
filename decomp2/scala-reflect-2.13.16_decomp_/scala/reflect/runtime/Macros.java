package scala.reflect.runtime;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.macros.blackbox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005]:a\u0001B\u0003\t\u0002%YaAB\u0007\u0006\u0011\u0003Ia\u0002C\u0003\u0014\u0003\u0011\u0005Q\u0003C\u0003\u0017\u0003\u0011\u0005q#\u0001\u0004NC\u000e\u0014xn\u001d\u0006\u0003\r\u001d\tqA];oi&lWM\u0003\u0002\t\u0013\u00059!/\u001a4mK\u000e$(\"\u0001\u0006\u0002\u000bM\u001c\u0017\r\\1\u0011\u00051\tQ\"A\u0003\u0003\r5\u000b7M]8t'\t\tq\u0002\u0005\u0002\u0011#5\t\u0011\"\u0003\u0002\u0013\u0013\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u0017\u0005i1-\u001e:sK:$X*\u001b:s_J$\"\u0001G\u000e\u0011\u0007e)\u0013F\u0004\u0002\u001b71\u0001\u0001\"\u0002\u000f\u0004\u0001\u0004i\u0012!A2\u0011\u0005y\u0019S\"A\u0010\u000b\u0005\u0001\n\u0013\u0001\u00032mC\u000e\\'m\u001c=\u000b\u0005\t:\u0011AB7bGJ|7/\u0003\u0002%?\t91i\u001c8uKb$\u0018B\u0001\u0014(\u0005\u0011)\u0005\u0010\u001d:\n\u0005!\n#aB!mS\u0006\u001cXm\u001d\t\u0003UEr!a\u000b\u0018\u000f\u00051a\u0013BA\u0017\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!a\f\u0019\u0002\u0011Ut\u0017N^3sg\u0016T!!L\u0003\n\u0005I\u001a$AB'jeJ|'/\u0003\u00025k\ta!*\u0019<b+:Lg/\u001a:tK*\u0011agB\u0001\u0004CBL\u0007"
)
public final class Macros {
   public static Exprs.Expr currentMirror(final Context c) {
      return Macros$.MODULE$.currentMirror(c);
   }
}
