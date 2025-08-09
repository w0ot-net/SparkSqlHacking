package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q4qa\u0002\u0005\u0011\u0002\u0007%Q\u0003C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0019\u0005\u0001\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003=\u0001\u0011\u0005Q\bC\u0003O\u0001\u0011\u0005s\nC\u0003x\u0001\u0011\u0005\u0003P\u0001\nJi\u0016\u0014\u0018\r^8s'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0005\u000b\u0003\r\u0019XM\u001d\u0006\u0003\u00171\tQa]2bY\u0006T!!\u0004\b\u0002\r5|G-\u001e7f\u0015\ty\u0001#A\u0004kC\u000e\\7o\u001c8\u000b\u0005E\u0011\u0012!\u00034bgR,'\u000f_7m\u0015\u0005\u0019\u0012aA2p[\u000e\u00011C\u0001\u0001\u0017!\r9RdH\u0007\u00021)\u0011\u0011DG\u0001\u0004gR$'BA\u0005\u001c\u0015\tab\"\u0001\u0005eCR\f'-\u001b8e\u0013\tq\u0002DA\u000bBg\u0006\u0013(/Y=TKJL\u0017\r\\5{KJ\u0014\u0015m]3\u0011\u0007\u0001\"c%D\u0001\"\u0015\t\u00113%\u0001\u0006d_2dWm\u0019;j_:T\u0011aC\u0005\u0003K\u0005\u0012\u0001\"\u0013;fe\u0006$xN\u001d\t\u0003O!j\u0011aI\u0005\u0003S\r\u00121!\u00118z\u0003\u0019!\u0013N\\5uIQ\tA\u0006\u0005\u0002([%\u0011af\t\u0002\u0005+:LG/\u0001\nji\u0016\u0014\u0018\r^8s'\u0016\u0014\u0018.\u00197ju\u0016\u0014X#A\u0019\u0011\u0005I\u001aT\"\u0001\u0005\n\u0005QB!aF*dC2\f\u0017\n^3sCR|'oU3sS\u0006d\u0017N_3s\u0003AA\u0017m]*j]\u001edW-\u00127f[\u0016tG\u000f\u0006\u00028uA\u0011q\u0005O\u0005\u0003s\r\u0012qAQ8pY\u0016\fg\u000eC\u0003<\u0007\u0001\u0007q$\u0001\u0002qc\u0005\t2/\u001a:jC2L'0Z\"p]R,g\u000e^:\u0015\t1r\u0004\t\u0013\u0005\u0006\u007f\u0011\u0001\raH\u0001\u0006m\u0006dW/\u001a\u0005\u0006\u0003\u0012\u0001\rAQ\u0001\u0005U\u001e,g\u000e\u0005\u0002D\r6\tAI\u0003\u0002F\u001d\u0005!1m\u001c:f\u0013\t9EIA\u0007Kg>tw)\u001a8fe\u0006$xN\u001d\u0005\u0006\u0013\u0012\u0001\rAS\u0001\taJ|g/\u001b3feB\u00111\nT\u0007\u00027%\u0011Qj\u0007\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bK]8wS\u0012,'/\u0001\u0007xSRD'+Z:pYZ,G\rF\u0003Q'b\u0003g\u000e\u0005\u00023#&\u0011!\u000b\u0003\u0002\u001b%\u0016\u001cx\u000e\u001c<fI&#XM]1u_J\u001cVM]5bY&TXM\u001d\u0005\u0006)\u0016\u0001\r!V\u0001\taJ|\u0007/\u001a:usB\u00111JV\u0005\u0003/n\u0011ABQ3b]B\u0013x\u000e]3sifDQ!W\u0003A\u0002i\u000b1A\u001e;t!\tYf,D\u0001]\u0015\ti6$\u0001\u0005kg>tG/\u001f9f\u0013\tyFL\u0001\bUsB,7+\u001a:jC2L'0\u001a:\t\u000b\u0005,\u0001\u0019\u00012\u0002#\u0015dW-\\3oiN+'/[1mSj,'\u000f\r\u0002dQB\u00191\n\u001a4\n\u0005\u0015\\\"A\u0004&t_:\u001cVM]5bY&TXM\u001d\t\u0003O\"d\u0001\u0001B\u0005jA\u0006\u0005\t\u0011!B\u0001U\n\u0019q\fJ\u0019\u0012\u0005-4\u0003CA\u0014m\u0013\ti7EA\u0004O_RD\u0017N\\4\t\u000b=,\u0001\u0019\u00019\u0002\u0019UtwO]1q'&tw\r\\3\u0011\u0005E4X\"\u0001:\u000b\u0005M$\u0018\u0001\u00027b]\u001eT\u0011!^\u0001\u0005U\u00064\u0018-\u0003\u0002:e\u00069\u0011n]#naRLHcA\u001czw\")!P\u0002a\u0001\u0015\u0006\u00112/\u001a:jC2L'0\u001a:Qe>4\u0018\u000eZ3s\u0011\u0015yd\u00011\u0001 \u0001"
)
public interface IteratorSerializer {
   ScalaIteratorSerializer iteratorSerializer();

   // $FF: synthetic method
   static boolean hasSingleElement$(final IteratorSerializer $this, final Iterator p1) {
      return $this.hasSingleElement(p1);
   }

   default boolean hasSingleElement(final Iterator p1) {
      return p1.size() == 1;
   }

   // $FF: synthetic method
   static void serializeContents$(final IteratorSerializer $this, final Iterator value, final JsonGenerator jgen, final SerializerProvider provider) {
      $this.serializeContents(value, jgen, provider);
   }

   default void serializeContents(final Iterator value, final JsonGenerator jgen, final SerializerProvider provider) {
      this.iteratorSerializer().serializeContents(value, jgen, provider);
   }

   // $FF: synthetic method
   static ResolvedIteratorSerializer withResolved$(final IteratorSerializer $this, final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return $this.withResolved(property, vts, elementSerializer, unwrapSingle);
   }

   default ResolvedIteratorSerializer withResolved(final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return new ResolvedIteratorSerializer(this, property, vts, elementSerializer, unwrapSingle);
   }

   // $FF: synthetic method
   static boolean isEmpty$(final IteratorSerializer $this, final SerializerProvider serializerProvider, final Iterator value) {
      return $this.isEmpty(serializerProvider, value);
   }

   default boolean isEmpty(final SerializerProvider serializerProvider, final Iterator value) {
      return value.hasNext();
   }

   static void $init$(final IteratorSerializer $this) {
   }
}
