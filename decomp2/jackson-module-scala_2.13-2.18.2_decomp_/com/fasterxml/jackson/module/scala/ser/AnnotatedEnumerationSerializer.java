package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2Aa\u0001\u0003\u0005#!)A\u0005\u0001C\u0001K!)q\u0005\u0001C!Q\tq\u0012I\u001c8pi\u0006$X\rZ#ok6,'/\u0019;j_:\u001cVM]5bY&TXM\u001d\u0006\u0003\u000b\u0019\t1a]3s\u0015\t9\u0001\"A\u0003tG\u0006d\u0017M\u0003\u0002\n\u0015\u00051Qn\u001c3vY\u0016T!a\u0003\u0007\u0002\u000f)\f7m[:p]*\u0011QBD\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011aD\u0001\u0004G>l7\u0001A\n\u0004\u0001I\u0001\u0003cA\n\u001715\tAC\u0003\u0002\u0016\u0015\u0005AA-\u0019;bE&tG-\u0003\u0002\u0018)\tq!j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bCA\r\u001f!\tQB$D\u0001\u001c\u0015\u00059\u0011BA\u000f\u001c\u0005-)e.^7fe\u0006$\u0018n\u001c8\n\u0005}a\"!\u0002,bYV,\u0007CA\u0011#\u001b\u0005!\u0011BA\u0012\u0005\u0005}\u0019uN\u001c;fqR,\u0018\r\\#ok6,'/\u0019;j_:\u001cVM]5bY&TXM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0019\u0002\"!\t\u0001\u0002\u0013M,'/[1mSj,G\u0003B\u0015-]Y\u0002\"A\u0007\u0016\n\u0005-Z\"\u0001B+oSRDQ!\f\u0002A\u0002a\tQA^1mk\u0016DQa\f\u0002A\u0002A\nAA[4f]B\u0011\u0011\u0007N\u0007\u0002e)\u00111GC\u0001\u0005G>\u0014X-\u0003\u00026e\ti!j]8o\u000f\u0016tWM]1u_JDQa\u000e\u0002A\u0002a\n\u0001\u0002\u001d:pm&$WM\u001d\t\u0003'eJ!A\u000f\u000b\u0003%M+'/[1mSj,'\u000f\u0015:pm&$WM\u001d"
)
public class AnnotatedEnumerationSerializer extends JsonSerializer implements ContextualEnumerationSerializer {
   public JsonSerializer createContextual(final SerializerProvider serializerProvider, final BeanProperty beanProperty) {
      return ContextualEnumerationSerializer.createContextual$(this, serializerProvider, beanProperty);
   }

   public void serialize(final Enumeration.Value value, final JsonGenerator jgen, final SerializerProvider provider) {
      provider.defaultSerializeValue(value.toString(), jgen);
   }

   public AnnotatedEnumerationSerializer() {
      ContextualEnumerationSerializer.$init$(this);
   }
}
