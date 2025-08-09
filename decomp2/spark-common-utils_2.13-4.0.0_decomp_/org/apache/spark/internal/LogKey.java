package org.apache.spark.internal;

import java.util.Locale;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2q\u0001B\u0003\u0011\u0002\u0007\u0005a\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003\u0003\u0005\u001b\u0001!\u0015\r\u0011\"\u0003\u001c\u0011\u00159\u0003\u0001\"\u0001\u001c\u0005\u0019aunZ&fs*\u0011aaB\u0001\tS:$XM\u001d8bY*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001y\u0001C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002/A\u0011\u0001\u0003G\u0005\u00033E\u0011A!\u00168ji\u0006)qL\\1nKV\tA\u0004\u0005\u0002\u001eI9\u0011aD\t\t\u0003?Ei\u0011\u0001\t\u0006\u0003C5\ta\u0001\u0010:p_Rt\u0014BA\u0012\u0012\u0003\u0019\u0001&/\u001a3fM&\u0011QE\n\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\r\n\u0012\u0001\u00028b[\u0016\u0004"
)
public interface LogKey {
   // $FF: synthetic method
   static String org$apache$spark$internal$LogKey$$_name$(final LogKey $this) {
      return $this.org$apache$spark$internal$LogKey$$_name();
   }

   default String org$apache$spark$internal$LogKey$$_name() {
      return .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getSimpleName()), "$").toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static String name$(final LogKey $this) {
      return $this.name();
   }

   default String name() {
      return this.org$apache$spark$internal$LogKey$$_name();
   }

   static void $init$(final LogKey $this) {
   }
}
