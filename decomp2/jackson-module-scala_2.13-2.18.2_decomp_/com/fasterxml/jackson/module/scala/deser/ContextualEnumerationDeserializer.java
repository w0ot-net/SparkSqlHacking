package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.module.scala.util.EnumResolver$;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3\u0001b\u0001\u0003\u0011\u0002\u0007%\u0011c\u0011\u0005\u0006C\u0001!\tA\t\u0005\u0006Q\u0001!\t%\u000b\u0002\"\u0007>tG/\u001a=uk\u0006dWI\\;nKJ\fG/[8o\t\u0016\u001cXM]5bY&TXM\u001d\u0006\u0003\u000b\u0019\tQ\u0001Z3tKJT!a\u0002\u0005\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005%Q\u0011AB7pIVdWM\u0003\u0002\f\u0019\u00059!.Y2lg>t'BA\u0007\u000f\u0003%1\u0017m\u001d;feblGNC\u0001\u0010\u0003\r\u0019w.\\\u0002\u0001'\r\u0001!C\u0007\t\u0003'ai\u0011\u0001\u0006\u0006\u0003+Y\tA\u0001\\1oO*\tq#\u0001\u0003kCZ\f\u0017BA\r\u0015\u0005\u0019y%M[3diB\u00111dH\u0007\u00029)\u0011Q!\b\u0006\u0003=)\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003Aq\u0011acQ8oi\u0016DH/^1m\t\u0016\u001cXM]5bY&TXM]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\r\u0002\"\u0001\n\u0014\u000e\u0003\u0015R\u0011aB\u0005\u0003O\u0015\u0012A!\u00168ji\u0006\u00012M]3bi\u0016\u001cuN\u001c;fqR,\u0018\r\u001c\u000b\u0004Uer$cA\u0016.o\u0019!A\u0006\u0001\u0001+\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rqs&M\u0007\u0002;%\u0011\u0001'\b\u0002\u0011\u0015N|g\u000eR3tKJL\u0017\r\\5{KJ\u0004\"AM\u001b\u0011\u0005\u0011\u001a\u0014B\u0001\u001b&\u0005-)e.^7fe\u0006$\u0018n\u001c8\n\u0005Y\u001a$!\u0002,bYV,\u0007C\u0001\u001d\u0001\u001b\u0005!\u0001\"\u0002\u001e\u0003\u0001\u0004Y\u0014\u0001B2uqR\u0004\"A\f\u001f\n\u0005uj\"A\u0006#fg\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:$X\r\u001f;\t\u000b}\u0012\u0001\u0019\u0001!\u0002\u0011A\u0014x\u000e]3sif\u0004\"AL!\n\u0005\tk\"\u0001\u0004\"fC:\u0004&o\u001c9feRL(c\u0001#8[\u0019!A\u0006\u0001\u0001D\u0015\t1\u0005#\u0001\u0004=e>|GO\u0010"
)
public interface ContextualEnumerationDeserializer extends ContextualDeserializer {
   // $FF: synthetic method
   static JsonDeserializer createContextual$(final ContextualEnumerationDeserializer $this, final DeserializationContext ctxt, final BeanProperty property) {
      return $this.createContextual(ctxt, property);
   }

   default JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
      return (JsonDeserializer)EnumResolver$.MODULE$.apply(property).map((r) -> new AnnotatedEnumerationDeserializer(r)).getOrElse(() -> (JsonDeserializer)this);
   }

   static void $init$(final ContextualEnumerationDeserializer $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
