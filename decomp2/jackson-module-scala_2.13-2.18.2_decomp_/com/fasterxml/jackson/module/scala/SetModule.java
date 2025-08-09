package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.module.scala.deser.SortedSetDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.UnsortedSetDeserializerModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003 \u0001\u0011\u0005\u0001\u0005C\u0003'\u0001\u0011\u0005sEA\u0005TKRlu\u000eZ;mK*\u0011QAB\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u000f!\ta!\\8ek2,'BA\u0005\u000b\u0003\u001dQ\u0017mY6t_:T!a\u0003\u0007\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\u0007\u0002\u0007\r|Wn\u0001\u0001\u0014\t\u0001\u0001b\u0003\b\t\u0003#Qi\u0011A\u0005\u0006\u0003'!\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003+I\u0011a!T8ek2,\u0007CA\f\u001b\u001b\u0005A\"BA\r\u0005\u0003\u0015!Wm]3s\u0013\tY\u0002DA\u000fV]N|'\u000f^3e'\u0016$H)Z:fe&\fG.\u001b>fe6{G-\u001e7f!\t9R$\u0003\u0002\u001f1\tY2k\u001c:uK\u0012\u001cV\r\u001e#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014Xj\u001c3vY\u0016\fa\u0001J5oSR$C#A\u0011\u0011\u0005\t\"S\"A\u0012\u000b\u0003\u0015I!!J\u0012\u0003\tUs\u0017\u000e^\u0001\u000eO\u0016$Xj\u001c3vY\u0016t\u0015-\\3\u0015\u0003!\u0002\"!\u000b\u0019\u000f\u0005)r\u0003CA\u0016$\u001b\u0005a#BA\u0017\u000f\u0003\u0019a$o\\8u}%\u0011qfI\u0001\u0007!J,G-\u001a4\n\u0005E\u0012$AB*ue&twM\u0003\u00020G\u0001"
)
public interface SetModule extends UnsortedSetDeserializerModule, SortedSetDeserializerModule {
   // $FF: synthetic method
   static String getModuleName$(final SetModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "SetModule";
   }

   static void $init$(final SetModule $this) {
   }
}
