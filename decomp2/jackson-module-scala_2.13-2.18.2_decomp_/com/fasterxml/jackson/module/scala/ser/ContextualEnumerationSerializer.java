package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration;
import com.fasterxml.jackson.module.scala.util.Implicits$;
import java.lang.invoke.SerializedLambda;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003\u0012\u0005\u0006C\u0001!\tA\t\u0005\u0006Q\u0001!\t%\u000b\u0002 \u0007>tG/\u001a=uk\u0006dWI\\;nKJ\fG/[8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0003\u0007\u0003\r\u0019XM\u001d\u0006\u0003\u000f!\tQa]2bY\u0006T!!\u0003\u0006\u0002\r5|G-\u001e7f\u0015\tYA\"A\u0004kC\u000e\\7o\u001c8\u000b\u00055q\u0011!\u00034bgR,'\u000f_7m\u0015\u0005y\u0011aA2p[\u000e\u00011c\u0001\u0001\u00135A\u00111\u0003G\u0007\u0002))\u0011QCF\u0001\u0005Y\u0006twMC\u0001\u0018\u0003\u0011Q\u0017M^1\n\u0005e!\"AB(cU\u0016\u001cG\u000f\u0005\u0002\u001c?5\tAD\u0003\u0002\u0006;)\u0011aDC\u0001\tI\u0006$\u0018MY5oI&\u0011\u0001\u0005\b\u0002\u0015\u0007>tG/\u001a=uk\u0006d7+\u001a:jC2L'0\u001a:\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0003C\u0001\u0013'\u001b\u0005)#\"A\u0004\n\u0005\u001d*#\u0001B+oSR\f\u0001c\u0019:fCR,7i\u001c8uKb$X/\u00197\u0015\u0007)Rt\b\r\u0002,cA\u0019A&L\u0018\u000e\u0003uI!AL\u000f\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>feB\u0011\u0001'\r\u0007\u0001\t%\u0011$!!A\u0001\u0002\u000b\u00051GA\u0002`II\n\"\u0001N\u001c\u0011\u0005\u0011*\u0014B\u0001\u001c&\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\n\u001d\n\u0005e*#aA!os\")1H\u0001a\u0001y\u0005\u00112/\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s!\taS(\u0003\u0002?;\t\u00112+\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s\u0011\u0015\u0001%\u00011\u0001B\u00031\u0011W-\u00198Qe>\u0004XM\u001d;z!\ta#)\u0003\u0002D;\ta!)Z1o!J|\u0007/\u001a:usJ\u0019Q)S&\u0007\t\u0019\u0003\u0001\u0001\u0012\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\u0006\u0003\u0011B\ta\u0001\u0010:p_Rt\u0004C\u0001&\u0001\u001b\u0005!\u0001G\u0001'O!\raS&\u0014\t\u0003a9#\u0011b\u0014\u0001\u0002\u0002\u0003\u0005)\u0011A\u001a\u0003\u0007}#\u0013\u0007"
)
public interface ContextualEnumerationSerializer extends ContextualSerializer {
   // $FF: synthetic method
   static JsonSerializer createContextual$(final ContextualEnumerationSerializer $this, final SerializerProvider serializerProvider, final BeanProperty beanProperty) {
      return $this.createContextual(serializerProvider, beanProperty);
   }

   default JsonSerializer createContextual(final SerializerProvider serializerProvider, final BeanProperty beanProperty) {
      return (JsonSerializer)Implicits$.MODULE$.mkOptionW(.MODULE$.apply(beanProperty)).optMap((x$1) -> (JsonScalaEnumeration)x$1.getAnnotation(JsonScalaEnumeration.class)).map((x$2) -> new AnnotatedEnumerationSerializer()).getOrElse(() -> (JsonSerializer)this);
   }

   static void $init$(final ContextualEnumerationSerializer $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
