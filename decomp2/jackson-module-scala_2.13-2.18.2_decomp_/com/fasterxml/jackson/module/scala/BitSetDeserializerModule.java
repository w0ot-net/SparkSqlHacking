package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00055:Q\u0001B\u0003\t\u0002A1QAE\u0003\t\u0002MAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005B\u0001\n\u0001DQ5u'\u0016$H)Z:fe&\fG.\u001b>fe6{G-\u001e7f\u0015\t1q!A\u0003tG\u0006d\u0017M\u0003\u0002\t\u0013\u00051Qn\u001c3vY\u0016T!AC\u0006\u0002\u000f)\f7m[:p]*\u0011A\"D\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011AD\u0001\u0004G>l7\u0001\u0001\t\u0003#\u0005i\u0011!\u0002\u0002\u0019\u0005&$8+\u001a;EKN,'/[1mSj,'/T8ek2,7cA\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\u0011q#C\u0001\tI\u0006$\u0018MY5oI&\u0011\u0011D\u0006\u0002\u0007\u001b>$W\u000f\\3\u0011\u0005EY\u0012B\u0001\u000f\u0006\u00055Q\u0015mY6t_:lu\u000eZ;mK\u00061A(\u001b8jiz\"\u0012\u0001E\u0001\u000eO\u0016$Xj\u001c3vY\u0016t\u0015-\\3\u0015\u0003\u0005\u0002\"A\t\u0016\u000f\u0005\rB\u0003C\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u0010\u0003\u0019a$o\\8u})\ta!\u0003\u0002*O\u00051\u0001K]3eK\u001aL!a\u000b\u0017\u0003\rM#(/\u001b8h\u0015\tIs\u0005"
)
public final class BitSetDeserializerModule {
   public static String getModuleName() {
      return BitSetDeserializerModule$.MODULE$.getModuleName();
   }

   public static void setupModule(final Module.SetupContext context) {
      BitSetDeserializerModule$.MODULE$.setupModule(context);
   }

   public static Version version() {
      return BitSetDeserializerModule$.MODULE$.version();
   }

   public static Iterable getDependencies() {
      return BitSetDeserializerModule$.MODULE$.getDependencies();
   }

   public static Object getTypeId() {
      return BitSetDeserializerModule$.MODULE$.getTypeId();
   }
}
