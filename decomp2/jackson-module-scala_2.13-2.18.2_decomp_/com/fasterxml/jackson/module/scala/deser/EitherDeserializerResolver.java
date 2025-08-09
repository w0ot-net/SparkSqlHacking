package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055q!B\u0004\t\u0011\u0013)b!B\f\t\u0011\u0013A\u0002\"\u0002\u0013\u0002\t\u0003)\u0003b\u0002\u0014\u0002\u0005\u0004%Ia\n\u0005\u0007\u0005\u0006\u0001\u000b\u0011\u0002\u0015\t\u000b9\u000bA\u0011I(\t\u000b\u001d\fA\u0011\t5\u00025\u0015KG\u000f[3s\t\u0016\u001cXM]5bY&TXM\u001d*fg>dg/\u001a:\u000b\u0005%Q\u0011!\u00023fg\u0016\u0014(BA\u0006\r\u0003\u0015\u00198-\u00197b\u0015\tia\"\u0001\u0004n_\u0012,H.\u001a\u0006\u0003\u001fA\tqA[1dWN|gN\u0003\u0002\u0012%\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002'\u0005\u00191m\\7\u0004\u0001A\u0011a#A\u0007\u0002\u0011\tQR)\u001b;iKJ$Um]3sS\u0006d\u0017N_3s%\u0016\u001cx\u000e\u001c<feN\u0011\u0011!\u0007\t\u00035\u0005r!aG\u0010\u000e\u0003qQ!!C\u000f\u000b\u0005yq\u0011\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005\u0001b\u0012!\u0004#fg\u0016\u0014\u0018.\u00197ju\u0016\u00148/\u0003\u0002#G\t!!)Y:f\u0015\t\u0001C$\u0001\u0004=S:LGO\u0010\u000b\u0002+\u00051Q)\u0013+I\u000bJ+\u0012\u0001\u000b\t\u0004S9\u0002T\"\u0001\u0016\u000b\u0005-b\u0013\u0001\u00027b]\u001eT\u0011!L\u0001\u0005U\u00064\u0018-\u0003\u00020U\t)1\t\\1tgB\u001a\u0011\u0007\u0011'\u0011\tIZdh\u0013\b\u0003gar!\u0001N\u001c\u000e\u0003UR!A\u000e\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0011BA\u001d;\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011aC\u0005\u0003yu\u0012a!R5uQ\u0016\u0014(BA\u001d;!\ty\u0004\t\u0004\u0001\u0005\u0013\u0005#\u0011\u0011!A\u0001\u0006\u0003\u0019%aA0%c\u00059Q)\u0013+I\u000bJ\u0003\u0013C\u0001#I!\t)e)D\u0001;\u0013\t9%HA\u0004O_RD\u0017N\\4\u0011\u0005\u0015K\u0015B\u0001&;\u0005\r\te.\u001f\t\u0003\u007f1#\u0011\"\u0014\u0003\u0002\u0002\u0003\u0005)\u0011A\"\u0003\u0007}##'\u0001\u000bgS:$')Z1o\t\u0016\u001cXM]5bY&TXM\u001d\u000b\u0005!bk&\r\r\u0002R-B\u0019!kU+\u000e\u0003uI!\u0001V\u000f\u0003!)\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bCA W\t%9V!!A\u0001\u0002\u000b\u00051IA\u0002`IMBQ!W\u0003A\u0002i\u000bA\u0001^=qKB\u0011!kW\u0005\u00039v\u0011\u0001BS1wCRK\b/\u001a\u0005\u0006=\u0016\u0001\raX\u0001\u0007G>tg-[4\u0011\u0005I\u0003\u0017BA1\u001e\u0005U!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8gS\u001eDQaY\u0003A\u0002\u0011\f\u0001BY3b]\u0012+7o\u0019\t\u0003%\u0016L!AZ\u000f\u0003\u001f\t+\u0017M\u001c#fg\u000e\u0014\u0018\u000e\u001d;j_:\f\u0011DZ5oIJ+g-\u001a:f]\u000e,G)Z:fe&\fG.\u001b>feR1\u0011N\\;wo~\u0004$A\u001b7\u0011\u0007I\u001b6\u000e\u0005\u0002@Y\u0012IQNBA\u0001\u0002\u0003\u0015\ta\u0011\u0002\u0004?\u0012*\u0004\"B8\u0007\u0001\u0004\u0001\u0018a\u0002:fMRK\b/\u001a\t\u0003cNl\u0011A\u001d\u0006\u00033vI!\u0001\u001e:\u0003\u001bI+g-\u001a:f]\u000e,G+\u001f9f\u0011\u0015qf\u00011\u0001`\u0011\u0015\u0019g\u00011\u0001e\u0011\u0015Ah\u00011\u0001z\u0003]\u0019wN\u001c;f]R$\u0016\u0010]3EKN,'/[1mSj,'\u000f\u0005\u0002{{6\t1P\u0003\u0002};\u0005A!n]8oif\u0004X-\u0003\u0002\u007fw\n\u0001B+\u001f9f\t\u0016\u001cXM]5bY&TXM\u001d\u0005\b\u0003\u00031\u0001\u0019AA\u0002\u0003M\u0019wN\u001c;f]R$Um]3sS\u0006d\u0017N_3sa\u0011\t)!!\u0003\u0011\tI\u001b\u0016q\u0001\t\u0004\u007f\u0005%AACA\u0006\u007f\u0006\u0005\t\u0011!B\u0001\u0007\n\u0019q\f\n\u001b"
)
public final class EitherDeserializerResolver {
   public static JsonDeserializer findReferenceDeserializer(final ReferenceType refType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer contentTypeDeserializer, final JsonDeserializer contentDeserializer) {
      return EitherDeserializerResolver$.MODULE$.findReferenceDeserializer(refType, config, beanDesc, contentTypeDeserializer, contentDeserializer);
   }

   public static JsonDeserializer findBeanDeserializer(final JavaType type, final DeserializationConfig config, final BeanDescription beanDesc) {
      return EitherDeserializerResolver$.MODULE$.findBeanDeserializer(type, config, beanDesc);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return EitherDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return EitherDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return EitherDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return EitherDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return EitherDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return EitherDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return EitherDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return EitherDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
