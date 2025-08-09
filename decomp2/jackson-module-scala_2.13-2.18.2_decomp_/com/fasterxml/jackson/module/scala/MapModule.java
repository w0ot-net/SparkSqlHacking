package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.module.scala.deser.SortedMapDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.UnsortedMapDeserializerModule;
import com.fasterxml.jackson.module.scala.ser.MapSerializerModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003&\u0001\u0011\u0005a\u0005C\u0003-\u0001\u0011\u0005SFA\u0005NCBlu\u000eZ;mK*\u0011QAB\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u000f!\ta!\\8ek2,'BA\u0005\u000b\u0003\u001dQ\u0017mY6t_:T!a\u0003\u0007\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\u0007\u0002\u0007\r|Wn\u0001\u0001\u0014\u000b\u0001\u0001b\u0003\b\u0012\u0011\u0005E!R\"\u0001\n\u000b\u0005MA\u0011\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005U\u0011\"AB'pIVdW\r\u0005\u0002\u001855\t\u0001D\u0003\u0002\u001a\t\u0005\u00191/\u001a:\n\u0005mA\"aE'baN+'/[1mSj,'/T8ek2,\u0007CA\u000f!\u001b\u0005q\"BA\u0010\u0005\u0003\u0015!Wm]3s\u0013\t\tcDA\u000fV]N|'\u000f^3e\u001b\u0006\u0004H)Z:fe&\fG.\u001b>fe6{G-\u001e7f!\ti2%\u0003\u0002%=\tY2k\u001c:uK\u0012l\u0015\r\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014Xj\u001c3vY\u0016\fa\u0001J5oSR$C#A\u0014\u0011\u0005!RS\"A\u0015\u000b\u0003\u0015I!aK\u0015\u0003\tUs\u0017\u000e^\u0001\u000eO\u0016$Xj\u001c3vY\u0016t\u0015-\\3\u0015\u00039\u0002\"a\f\u001c\u000f\u0005A\"\u0004CA\u0019*\u001b\u0005\u0011$BA\u001a\u000f\u0003\u0019a$o\\8u}%\u0011Q'K\u0001\u0007!J,G-\u001a4\n\u0005]B$AB*ue&twM\u0003\u00026S\u0001"
)
public interface MapModule extends MapSerializerModule, UnsortedMapDeserializerModule, SortedMapDeserializerModule {
   // $FF: synthetic method
   static String getModuleName$(final MapModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "MapModule";
   }

   static void $init$(final MapModule $this) {
   }
}
