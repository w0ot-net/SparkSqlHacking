package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2A\u0001B\u0003\u0005%!A\u0011\u0005\u0001B\u0001B\u0003%A\u0004C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0003FA\fTG\u0006d\u0017m\u00142kK\u000e$H)Z:fe&\fG.\u001b>fe*\u0011aaB\u0001\u0006I\u0016\u001cXM\u001d\u0006\u0003\u0011%\tQa]2bY\u0006T!AC\u0006\u0002\r5|G-\u001e7f\u0015\taQ\"A\u0004kC\u000e\\7o\u001c8\u000b\u00059y\u0011!\u00034bgR,'\u000f_7m\u0015\u0005\u0001\u0012aA2p[\u000e\u00011C\u0001\u0001\u0014!\r!\"\u0004H\u0007\u0002+)\u0011acF\u0001\u0004gR$'B\u0001\u0004\u0019\u0015\tI2\"\u0001\u0005eCR\f'-\u001b8e\u0013\tYRCA\bTi\u0012$Um]3sS\u0006d\u0017N_3s!\tir$D\u0001\u001f\u0015\u0005A\u0011B\u0001\u0011\u001f\u0005\r\te._\u0001\u0006m\u0006dW/Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u00112\u0003CA\u0013\u0001\u001b\u0005)\u0001\"B\u0011\u0003\u0001\u0004a\u0012a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016$2\u0001H\u00152\u0011\u0015Q3\u00011\u0001,\u0003\u0005\u0001\bC\u0001\u00170\u001b\u0005i#B\u0001\u0018\f\u0003\u0011\u0019wN]3\n\u0005Aj#A\u0003&t_:\u0004\u0016M]:fe\")!g\u0001a\u0001g\u0005!1\r\u001e=u!\t!T'D\u0001\u0019\u0013\t1\u0004D\u0001\fEKN,'/[1mSj\fG/[8o\u0007>tG/\u001a=u\u0001"
)
public class ScalaObjectDeserializer extends StdDeserializer {
   private final Object value;

   public Object deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return this.value;
   }

   public ScalaObjectDeserializer(final Object value) {
      super(Object.class);
      this.value = value;
   }
}
