package com.fasterxml.jackson.module.scala.introspect;

import scala.collection.immutable.LazyList;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);aAB\u0004\t\u0002\u001d\u0019bAB\u000b\b\u0011\u00039a\u0003C\u0003\u001d\u0003\u0011\u0005a$\u0002\u0003 \u0003\u0001\u0001\u0003\"\u0002\u001d\u0002\t\u0003I\u0004\"B \u0002\t\u0003\u0001\u0015a\u0004'bufd\u0015n\u001d;TkB\u0004xN\u001d;\u000b\u0005!I\u0011AC5oiJ|7\u000f]3di*\u0011!bC\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u00195\ta!\\8ek2,'B\u0001\b\u0010\u0003\u001dQ\u0017mY6t_:T!\u0001E\t\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\n\u0002\u0007\r|W\u000e\u0005\u0002\u0015\u00035\tqAA\bMCjLH*[:u'V\u0004\bo\u001c:u'\t\tq\u0003\u0005\u0002\u001955\t\u0011DC\u0001\u000b\u0013\tY\u0012D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1C\u0001\u0007MCjLH*[:u)f\u0004X-\u0006\u0002\"_A\u0019!EK\u0017\u000f\u0005\rBcB\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u001e\u0003\u0019a$o\\8u}%\t!\"\u0003\u0002*3\u00059\u0001/Y2lC\u001e,\u0017BA\u0016-\u0005!a\u0015M_=MSN$(BA\u0015\u001a!\tqs\u0006\u0004\u0001\u0005\u000bA\u001a!\u0019A\u0019\u0003\u0003Q\u000b\"AM\u001b\u0011\u0005a\u0019\u0014B\u0001\u001b\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u001c\n\u0005]J\"aA!os\u0006)Q-\u001c9usV\u0011!HP\u000b\u0002wA\u0019AhA\u001f\u000e\u0003\u0005\u0001\"A\f \u0005\u000bA\"!\u0019A\u0019\u0002\u0013\u0019\u0014x.\\!se\u0006LXCA!E)\t\u0011U\tE\u0002=\u0007\r\u0003\"A\f#\u0005\u000bA*!\u0019A\u0019\t\u000b\u0019+\u0001\u0019A$\u0002\u000b\u0005\u0014(/Y=\u0011\u0007aA5)\u0003\u0002J3\t)\u0011I\u001d:bs\u0002"
)
public final class LazyListSupport {
   public static LazyList fromArray(final Object array) {
      return LazyListSupport$.MODULE$.fromArray(array);
   }

   public static LazyList empty() {
      return LazyListSupport$.MODULE$.empty();
   }
}
