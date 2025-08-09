package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.module.scala.util.EnumResolver;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2A\u0001B\u0003\u0005%!AQ\u0005\u0001B\u0001B\u0003%a\u0005C\u0003-\u0001\u0011\u0005Q\u0006C\u00031\u0001\u0011\u0005\u0013G\u0001\u0011B]:|G/\u0019;fI\u0016sW/\\3sCRLwN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0004\b\u0003\u0015!Wm]3s\u0015\tA\u0011\"A\u0003tG\u0006d\u0017M\u0003\u0002\u000b\u0017\u00051Qn\u001c3vY\u0016T!\u0001D\u0007\u0002\u000f)\f7m[:p]*\u0011abD\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001E\u0001\u0004G>l7\u0001A\n\u0004\u0001M\t\u0003c\u0001\u000b\u001835\tQC\u0003\u0002\u0017\u0017\u0005AA-\u0019;bE&tG-\u0003\u0002\u0019+\t\u0001\"j]8o\t\u0016\u001cXM]5bY&TXM\u001d\t\u00035}\u0001\"aG\u000f\u000e\u0003qQ\u0011\u0001C\u0005\u0003=q\u00111\"\u00128v[\u0016\u0014\u0018\r^5p]&\u0011\u0001%\b\u0002\u0006-\u0006dW/\u001a\t\u0003E\rj\u0011!B\u0005\u0003I\u0015\u0011\u0011eQ8oi\u0016DH/^1m\u000b:,X.\u001a:bi&|g\u000eR3tKJL\u0017\r\\5{KJ\f\u0011A\u001d\t\u0003O)j\u0011\u0001\u000b\u0006\u0003S\u001d\tA!\u001e;jY&\u00111\u0006\u000b\u0002\r\u000b:,XNU3t_24XM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00059z\u0003C\u0001\u0012\u0001\u0011\u0015)#\u00011\u0001'\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u0007e\u0011$\bC\u00034\u0007\u0001\u0007A'\u0001\u0002kaB\u0011Q\u0007O\u0007\u0002m)\u0011qgC\u0001\u0005G>\u0014X-\u0003\u0002:m\tQ!j]8o!\u0006\u00148/\u001a:\t\u000bm\u001a\u0001\u0019\u0001\u001f\u0002\t\r$\b\u0010\u001e\t\u0003)uJ!AP\u000b\u0003-\u0011+7/\u001a:jC2L'0\u0019;j_:\u001cuN\u001c;fqR\u0004"
)
public class AnnotatedEnumerationDeserializer extends JsonDeserializer implements ContextualEnumerationDeserializer {
   private final EnumResolver r;

   public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
      return ContextualEnumerationDeserializer.createContextual$(this, ctxt, property);
   }

   public Enumeration.Value deserialize(final JsonParser jp, final DeserializationContext ctxt) {
      JsonToken var4 = jp.getCurrentToken();
      return JsonToken.VALUE_STRING.equals(var4) ? this.r.getEnum(jp.getValueAsString()) : (Enumeration.Value)ctxt.handleUnexpectedToken(this.r.getEnumClass(), jp);
   }

   public AnnotatedEnumerationDeserializer(final EnumResolver r) {
      this.r = r;
      ContextualEnumerationDeserializer.$init$(this);
   }
}
