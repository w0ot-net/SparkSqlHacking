package org.apache.spark.metrics.source;

import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-:a!\u0002\u0004\t\u0002)\u0001bA\u0002\n\u0007\u0011\u0003Q1\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0004\u001e\u0003\t\u0007I\u0011\u0001\u0010\t\r)\n\u0001\u0015!\u0003 \u00035\u0019F/\u0019;jGN{WO]2fg*\u0011q\u0001C\u0001\u0007g>,(oY3\u000b\u0005%Q\u0011aB7fiJL7m\u001d\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sOB\u0011\u0011#A\u0007\u0002\r\ti1\u000b^1uS\u000e\u001cv.\u001e:dKN\u001c\"!\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\t\u0002\u0015\u0005dGnU8ve\u000e,7/F\u0001 !\r\u0001SeJ\u0007\u0002C)\u0011!eI\u0001\nS6lW\u000f^1cY\u0016T!\u0001\n\f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002'C\t\u00191+Z9\u0011\u0005EA\u0013BA\u0015\u0007\u0005\u0019\u0019v.\u001e:dK\u0006Y\u0011\r\u001c7T_V\u00148-Z:!\u0001"
)
public final class StaticSources {
   public static Seq allSources() {
      return StaticSources$.MODULE$.allSources();
   }
}
