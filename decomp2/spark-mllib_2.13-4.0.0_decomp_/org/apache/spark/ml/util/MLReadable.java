package org.apache.spark.ml.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3q\u0001B\u0003\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0019\u0005a\u0004C\u00038\u0001\u0011\u0005\u0001H\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016T!AB\u0004\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0011%\t!!\u001c7\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u0012KM\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005Q\u0002CA\n\u001c\u0013\taBC\u0001\u0003V]&$\u0018\u0001\u0002:fC\u0012,\u0012a\b\t\u0004A\u0005\u001aS\"A\u0003\n\u0005\t*!\u0001C'M%\u0016\fG-\u001a:\u0011\u0005\u0011*C\u0002\u0001\u0003\u0006M\u0001\u0011\ra\n\u0002\u0002)F\u0011\u0001f\u000b\t\u0003'%J!A\u000b\u000b\u0003\u000f9{G\u000f[5oOB\u00111\u0003L\u0005\u0003[Q\u00111!\u00118zQ\r\u0011q&\u000e\t\u0003aMj\u0011!\r\u0006\u0003e%\t!\"\u00198o_R\fG/[8o\u0013\t!\u0014GA\u0003TS:\u001cW-I\u00017\u0003\u0015\tdF\u000e\u00181\u0003\u0011aw.\u00193\u0015\u0005\rJ\u0004\"\u0002\u001e\u0004\u0001\u0004Y\u0014\u0001\u00029bi\"\u0004\"\u0001P\"\u000f\u0005u\n\u0005C\u0001 \u0015\u001b\u0005y$B\u0001!\u0010\u0003\u0019a$o\\8u}%\u0011!\tF\u0001\u0007!J,G-\u001a4\n\u0005\u0011+%AB*ue&twM\u0003\u0002C)!\u001a1aL\u001b)\u0007\u0001yS\u0007"
)
public interface MLReadable {
   MLReader read();

   // $FF: synthetic method
   static Object load$(final MLReadable $this, final String path) {
      return $this.load(path);
   }

   default Object load(final String path) {
      return this.read().load(path);
   }

   static void $init$(final MLReadable $this) {
   }
}
