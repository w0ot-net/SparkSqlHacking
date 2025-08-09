package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]<Q\u0001C\u0005\t\nY1Q\u0001G\u0005\t\neAQ!J\u0001\u0005\u0002\u0019BqaJ\u0001C\u0002\u0013%\u0001\u0006\u0003\u00046\u0003\u0001\u0006I!\u000b\u0005\bm\u0005\u0011\r\u0011\"\u00038\u0011\u0019)\u0015\u0001)A\u0005q!)a*\u0001C!\u001f\u0006y2kY1mC&#XM]1u_J\u001cVM]5bY&TXM\u001d*fg>dg/\u001a:\u000b\u0005)Y\u0011aA:fe*\u0011A\"D\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u001d=\ta!\\8ek2,'B\u0001\t\u0012\u0003\u001dQ\u0017mY6t_:T!AE\n\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\u000b\u0002\u0007\r|Wn\u0001\u0001\u0011\u0005]\tQ\"A\u0005\u0003?M\u001b\u0017\r\\1Ji\u0016\u0014\u0018\r^8s'\u0016\u0014\u0018.\u00197ju\u0016\u0014(+Z:pYZ,'o\u0005\u0002\u00025A\u00111D\t\b\u00039\u0001j\u0011!\b\u0006\u0003\u0015yQ!aH\b\u0002\u0011\u0011\fG/\u00192j]\u0012L!!I\u000f\u0002\u0017M+'/[1mSj,'o]\u0005\u0003G\u0011\u0012AAQ1tK*\u0011\u0011%H\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Y\taCS*P\u001dN+%+S!M\u0013j\u000b%\tT#`\u00072\u000b5kU\u000b\u0002SA\u0019!fL\u0019\u000e\u0003-R!\u0001L\u0017\u0002\t1\fgn\u001a\u0006\u0002]\u0005!!.\u0019<b\u0013\t\u00014FA\u0003DY\u0006\u001c8\u000f\u0005\u00023g5\ta$\u0003\u00025=\t\u0001\"j]8o'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0018\u0015N{ejU#S\u0013\u0006c\u0015JW!C\u0019\u0016{6\tT!T'\u0002\n1cU\"B\u0019\u0006KE+\u0012*B)>\u0013vl\u0011'B'N+\u0012\u0001\u000f\t\u0004U=J\u0004G\u0001\u001eD!\rYt(Q\u0007\u0002y)\u0011QHP\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0007\n\u0005\u0001c$\u0001C%uKJ\fGo\u001c:\u0011\u0005\t\u001bE\u0002\u0001\u0003\n\t\u001a\t\t\u0011!A\u0003\u0002\u0019\u00131a\u0018\u00135\u0003Q\u00196)\u0011'B\u0013R+%+\u0011+P%~\u001bE*Q*TAE\u0011qi\u0013\t\u0003\u0011&k\u0011AP\u0005\u0003\u0015z\u0012qAT8uQ&tw\r\u0005\u0002I\u0019&\u0011QJ\u0010\u0002\u0004\u0003:L\u0018\u0001\b4j]\u0012\u001cu\u000e\u001c7fGRLwN\u001c'jW\u0016\u001cVM]5bY&TXM\u001d\u000b\u0007!^cF-[91\u0005E+\u0006c\u0001\u001aS)&\u00111K\b\u0002\u000f\u0015N|gnU3sS\u0006d\u0017N_3s!\t\u0011U\u000bB\u0005W\u000f\u0005\u0005\t\u0011!B\u0001\r\n\u0019q\fJ\u001b\t\u000ba;\u0001\u0019A-\u0002\r\r|gNZ5h!\t\u0011$,\u0003\u0002\\=\t\u00192+\u001a:jC2L'0\u0019;j_:\u001cuN\u001c4jO\")Ql\u0002a\u0001=\u0006q1m\u001c7mK\u000e$\u0018n\u001c8UsB,\u0007CA0c\u001b\u0005\u0001'BA1\u001f\u0003\u0011!\u0018\u0010]3\n\u0005\r\u0004'AE\"pY2,7\r^5p]2K7.\u001a+za\u0016DQ!Z\u0004A\u0002\u0019\fqBY3b]\u0012+7o\u0019:jaRLwN\u001c\t\u0003e\u001dL!\u0001\u001b\u0010\u0003\u001f\t+\u0017M\u001c#fg\u000e\u0014\u0018\u000e\u001d;j_:DQA[\u0004A\u0002-\fQ#\u001a7f[\u0016tG\u000fV=qKN+'/[1mSj,'\u000f\u0005\u0002m_6\tQN\u0003\u0002o=\u0005A!n]8oif\u0004X-\u0003\u0002q[\nqA+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\"\u0002:\b\u0001\u0004\u0019\u0018!E3mK6,g\u000e^*fe&\fG.\u001b>feB\u0019!G\u0015;\u0011\u0005)*\u0018B\u0001<,\u0005\u0019y%M[3di\u0002"
)
public final class ScalaIteratorSerializerResolver {
   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig config, final CollectionLikeType collectionType, final BeanDescription beanDescription, final TypeSerializer elementTypeSerializer, final JsonSerializer elementSerializer) {
      return ScalaIteratorSerializerResolver$.MODULE$.findCollectionLikeSerializer(config, collectionType, beanDescription, elementTypeSerializer, elementSerializer);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return ScalaIteratorSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return ScalaIteratorSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return ScalaIteratorSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return ScalaIteratorSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findReferenceSerializer(final SerializationConfig x$1, final ReferenceType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return ScalaIteratorSerializerResolver$.MODULE$.findReferenceSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findSerializer(final SerializationConfig x$1, final JavaType x$2, final BeanDescription x$3) {
      return ScalaIteratorSerializerResolver$.MODULE$.findSerializer(x$1, x$2, x$3);
   }
}
