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
   bytes = "\u0006\u0005\u0005\u0015q!\u0002\u0006\f\u0011\u0013Ab!\u0002\u000e\f\u0011\u0013Y\u0002\"B\u0014\u0002\t\u0003A\u0003bB\u0015\u0002\u0005\u0004%IA\u000b\u0005\u0007\t\u0006\u0001\u000b\u0011B\u0016\t\u000f\u0015\u000b!\u0019!C\u0005\r\"11*\u0001Q\u0001\n\u001dCq\u0001T\u0001C\u0002\u0013%Q\n\u0003\u0004S\u0003\u0001\u0006IA\u0014\u0005\u0006'\u0006!\t\u0005V\u0001\u0019\u000b&$\b.\u001a:TKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(B\u0001\u0007\u000e\u0003\r\u0019XM\u001d\u0006\u0003\u001d=\tQa]2bY\u0006T!\u0001E\t\u0002\r5|G-\u001e7f\u0015\t\u00112#A\u0004kC\u000e\\7o\u001c8\u000b\u0005Q)\u0012!\u00034bgR,'\u000f_7m\u0015\u00051\u0012aA2p[\u000e\u0001\u0001CA\r\u0002\u001b\u0005Y!\u0001G#ji\",'oU3sS\u0006d\u0017N_3s%\u0016\u001cx\u000e\u001c<feN\u0011\u0011\u0001\b\t\u0003;\u0011r!A\b\u0012\u000e\u0003}Q!\u0001\u0004\u0011\u000b\u0005\u0005\n\u0012\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005\rz\u0012aC*fe&\fG.\u001b>feNL!!\n\u0014\u0003\t\t\u000b7/\u001a\u0006\u0003G}\ta\u0001P5oSRtD#\u0001\r\u0002\r\u0015KE\u000bS#S+\u0005Y\u0003c\u0001\u00172g5\tQF\u0003\u0002/_\u0005!A.\u00198h\u0015\u0005\u0001\u0014\u0001\u00026bm\u0006L!AM\u0017\u0003\u000b\rc\u0017m]:\u0011\tQj\u0004\t\u0011\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001O\f\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011BA\u001e=\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AD\u0005\u0003}}\u0012a!R5uQ\u0016\u0014(BA\u001e=!\t\t%)D\u0001=\u0013\t\u0019EH\u0001\u0004B]f\u0014VMZ\u0001\b\u000b&#\u0006*\u0012*!\u0003\u0011aUI\u0012+\u0016\u0003\u001d\u00032\u0001L\u0019I!\u0011!\u0014\n\u0011!\n\u0005){$\u0001\u0002'fMR\fQ\u0001T#G)\u0002\nQAU%H\u0011R+\u0012A\u0014\t\u0004YEz\u0005\u0003\u0002\u001bQ\u0001\u0002K!!U \u0003\u000bIKw\r\u001b;\u0002\rIKu\t\u0013+!\u0003]1\u0017N\u001c3SK\u001a,'/\u001a8dKN+'/[1mSj,'\u000f\u0006\u0004VK*\u0014xo \u0019\u0003-r\u00032a\u0016-[\u001b\u0005\u0001\u0013BA-!\u00059Q5o\u001c8TKJL\u0017\r\\5{KJ\u0004\"a\u0017/\r\u0001\u0011IQ,CA\u0001\u0002\u0003\u0015\tA\u0018\u0002\u0004?\u0012\u001a\u0014CA0c!\t\t\u0005-\u0003\u0002by\t9aj\u001c;iS:<\u0007CA!d\u0013\t!GHA\u0002B]fDQAZ\u0005A\u0002\u001d\faaY8oM&<\u0007CA,i\u0013\tI\u0007EA\nTKJL\u0017\r\\5{CRLwN\\\"p]\u001aLw\rC\u0003l\u0013\u0001\u0007A.A\u0004sK\u001a$\u0016\u0010]3\u0011\u00055\u0004X\"\u00018\u000b\u0005=\u0004\u0013\u0001\u0002;za\u0016L!!\u001d8\u0003\u001bI+g-\u001a:f]\u000e,G+\u001f9f\u0011\u0015\u0019\u0018\u00021\u0001u\u0003!\u0011W-\u00198EKN\u001c\u0007CA,v\u0013\t1\bEA\bCK\u0006tG)Z:de&\u0004H/[8o\u0011\u0015A\u0018\u00021\u0001z\u0003U\u0019wN\u001c;f]R$\u0016\u0010]3TKJL\u0017\r\\5{KJ\u0004\"A_?\u000e\u0003mT!\u0001 \u0011\u0002\u0011)\u001cxN\u001c;za\u0016L!A`>\u0003\u001dQK\b/Z*fe&\fG.\u001b>fe\"9\u0011\u0011A\u0005A\u0002\u0005\r\u0011AF2p]R,g\u000e\u001e,bYV,7+\u001a:jC2L'0\u001a:\u0011\u0007]C\u0006\t"
)
public final class EitherSerializerResolver {
   public static JsonSerializer findReferenceSerializer(final SerializationConfig config, final ReferenceType refType, final BeanDescription beanDesc, final TypeSerializer contentTypeSerializer, final JsonSerializer contentValueSerializer) {
      return EitherSerializerResolver$.MODULE$.findReferenceSerializer(config, refType, beanDesc, contentTypeSerializer, contentValueSerializer);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return EitherSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return EitherSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig x$1, final CollectionLikeType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return EitherSerializerResolver$.MODULE$.findCollectionLikeSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return EitherSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return EitherSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findSerializer(final SerializationConfig x$1, final JavaType x$2, final BeanDescription x$3) {
      return EitherSerializerResolver$.MODULE$.findSerializer(x$1, x$2, x$3);
   }
}
