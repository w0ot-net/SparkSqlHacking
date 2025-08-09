package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.module.scala.deser.EnumerationDeserializerModule;
import com.fasterxml.jackson.module.scala.ser.EnumerationSerializerModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003*\u0001\u0011\u0005#FA\tF]VlWM]1uS>tWj\u001c3vY\u0016T!!\u0002\u0004\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005\u001dA\u0011AB7pIVdWM\u0003\u0002\n\u0015\u00059!.Y2lg>t'BA\u0006\r\u0003%1\u0017m\u001d;feblGNC\u0001\u000e\u0003\r\u0019w.\\\u0002\u0001'\u0011\u0001\u0001C\u0006\u000f\u0011\u0005E!R\"\u0001\n\u000b\u0005MA\u0011\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005U\u0011\"AB'pIVdW\r\u0005\u0002\u001855\t\u0001D\u0003\u0002\u001a\t\u0005\u00191/\u001a:\n\u0005mA\"aG#ok6,'/\u0019;j_:\u001cVM]5bY&TXM]'pIVdW\r\u0005\u0002\u001eA5\taD\u0003\u0002 \t\u0005)A-Z:fe&\u0011\u0011E\b\u0002\u001e\u000b:,X.\u001a:bi&|g\u000eR3tKJL\u0017\r\\5{KJlu\u000eZ;mK\u00061A%\u001b8ji\u0012\"\u0012\u0001\n\t\u0003K\u001dj\u0011A\n\u0006\u0002\u000b%\u0011\u0001F\n\u0002\u0005+:LG/A\u0007hKRlu\u000eZ;mK:\u000bW.\u001a\u000b\u0002WA\u0011Af\r\b\u0003[E\u0002\"A\f\u0014\u000e\u0003=R!\u0001\r\b\u0002\rq\u0012xn\u001c;?\u0013\t\u0011d%\u0001\u0004Qe\u0016$WMZ\u0005\u0003iU\u0012aa\u0015;sS:<'B\u0001\u001a'\u0001"
)
public interface EnumerationModule extends EnumerationSerializerModule, EnumerationDeserializerModule {
   // $FF: synthetic method
   static String getModuleName$(final EnumerationModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "EnumerationModule";
   }

   static void $init$(final EnumerationModule $this) {
   }
}
