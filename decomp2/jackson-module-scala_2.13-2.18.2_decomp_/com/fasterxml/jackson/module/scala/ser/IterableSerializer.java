package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m4qa\u0002\u0005\u0011\u0002\u0007%Q\u0003C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0019\u0005\u0001\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003=\u0001\u0011\u0005S\bC\u0003N\u0001\u0011\u0005c\nC\u0003w\u0001\u0011\u0005sO\u0001\nJi\u0016\u0014\u0018M\u00197f'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0005\u000b\u0003\r\u0019XM\u001d\u0006\u0003\u00171\tQa]2bY\u0006T!!\u0004\b\u0002\r5|G-\u001e7f\u0015\ty\u0001#A\u0004kC\u000e\\7o\u001c8\u000b\u0005E\u0011\u0012!\u00034bgR,'\u000f_7m\u0015\u0005\u0019\u0012aA2p[\u000e\u00011C\u0001\u0001\u0017!\r9RdH\u0007\u00021)\u0011\u0011DG\u0001\u0004gR$'BA\u0005\u001c\u0015\tab\"\u0001\u0005eCR\f'-\u001b8e\u0013\tq\u0002DA\u000bBg\u0006\u0013(/Y=TKJL\u0017\r\\5{KJ\u0014\u0015m]3\u0011\u0007\u0001\"c%D\u0001\"\u0015\t\u00113%\u0001\u0006d_2dWm\u0019;j_:T\u0011aC\u0005\u0003K\u0005\u0012\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u0003O!j\u0011aI\u0005\u0003S\r\u00121!\u00118z\u0003\u0019!\u0013N\\5uIQ\tA\u0006\u0005\u0002([%\u0011af\t\u0002\u0005+:LG/\u0001\u000bd_2dWm\u0019;j_:\u001cVM]5bY&TXM]\u000b\u0002cA\u0011!gM\u0007\u0002\u0011%\u0011A\u0007\u0003\u0002\u0018'\u000e\fG.Y%uKJ\f'\r\\3TKJL\u0017\r\\5{KJ\f\u0001\u0003[1t'&tw\r\\3FY\u0016lWM\u001c;\u0015\u0005]R\u0004CA\u00149\u0013\tI4EA\u0004C_>dW-\u00198\t\u000bm\u001a\u0001\u0019A\u0010\u0002\u000bY\fG.^3\u0002#M,'/[1mSj,7i\u001c8uK:$8\u000f\u0006\u0003-}}:\u0005\"B\u001e\u0005\u0001\u0004y\u0002\"\u0002!\u0005\u0001\u0004\t\u0015aA4f]B\u0011!)R\u0007\u0002\u0007*\u0011AID\u0001\u0005G>\u0014X-\u0003\u0002G\u0007\ni!j]8o\u000f\u0016tWM]1u_JDQ\u0001\u0013\u0003A\u0002%\u000b\u0001\u0002\u001d:pm&$WM\u001d\t\u0003\u0015.k\u0011aG\u0005\u0003\u0019n\u0011!cU3sS\u0006d\u0017N_3s!J|g/\u001b3fe\u0006aq/\u001b;i%\u0016\u001cx\u000e\u001c<fIR)qJU,`[B\u0011!\u0007U\u0005\u0003#\"\u0011!DU3t_24X\rZ%uKJ\f'\r\\3TKJL\u0017\r\\5{KJDQaU\u0003A\u0002Q\u000b\u0001\u0002\u001d:pa\u0016\u0014H/\u001f\t\u0003\u0015VK!AV\u000e\u0003\u0019\t+\u0017M\u001c)s_B,'\u000f^=\t\u000ba+\u0001\u0019A-\u0002\u0007Y$8\u000f\u0005\u0002[;6\t1L\u0003\u0002]7\u0005A!n]8oif\u0004X-\u0003\u0002_7\nqA+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\"\u00021\u0006\u0001\u0004\t\u0017!E3mK6,g\u000e^*fe&\fG.\u001b>feB\u0012!m\u001a\t\u0004\u0015\u000e,\u0017B\u00013\u001c\u00059Q5o\u001c8TKJL\u0017\r\\5{KJ\u0004\"AZ4\r\u0001\u0011I\u0001nXA\u0001\u0002\u0003\u0015\t!\u001b\u0002\u0004?\u0012\n\u0014C\u00016'!\t93.\u0003\u0002mG\t9aj\u001c;iS:<\u0007\"\u00028\u0006\u0001\u0004y\u0017\u0001D;ooJ\f\u0007oU5oO2,\u0007C\u00019v\u001b\u0005\t(B\u0001:t\u0003\u0011a\u0017M\\4\u000b\u0003Q\fAA[1wC&\u0011\u0011(]\u0001\bSN,U\u000e\u001d;z)\r9\u0004P\u001f\u0005\u0006s\u001a\u0001\r!S\u0001\u0005aJ|g\u000fC\u0003<\r\u0001\u0007q\u0004"
)
public interface IterableSerializer {
   ScalaIterableSerializer collectionSerializer();

   // $FF: synthetic method
   static boolean hasSingleElement$(final IterableSerializer $this, final Iterable value) {
      return $this.hasSingleElement(value);
   }

   default boolean hasSingleElement(final Iterable value) {
      return value.size() == 1;
   }

   // $FF: synthetic method
   static void serializeContents$(final IterableSerializer $this, final Iterable value, final JsonGenerator gen, final SerializerProvider provider) {
      $this.serializeContents(value, gen, provider);
   }

   default void serializeContents(final Iterable value, final JsonGenerator gen, final SerializerProvider provider) {
      this.collectionSerializer().serializeContents(value, gen, provider);
   }

   // $FF: synthetic method
   static ResolvedIterableSerializer withResolved$(final IterableSerializer $this, final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return $this.withResolved(property, vts, elementSerializer, unwrapSingle);
   }

   default ResolvedIterableSerializer withResolved(final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return new ResolvedIterableSerializer(this, property, vts, elementSerializer, unwrapSingle);
   }

   // $FF: synthetic method
   static boolean isEmpty$(final IterableSerializer $this, final SerializerProvider prov, final Iterable value) {
      return $this.isEmpty(prov, value);
   }

   default boolean isEmpty(final SerializerProvider prov, final Iterable value) {
      return value.isEmpty();
   }

   static void $init$(final IterableSerializer $this) {
   }
}
