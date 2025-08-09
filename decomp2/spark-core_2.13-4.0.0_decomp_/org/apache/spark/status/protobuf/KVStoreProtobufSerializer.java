package org.apache.spark.status.protobuf;

import org.apache.spark.status.KVUtils;
import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i4Q!\u0003\u0006\u0001\u001dQAQ\u0001\u000b\u0001\u0005\u0002%BQ\u0001\f\u0001\u0005B5BQ!\u0011\u0001\u0005B\t;aa\u0017\u0006\t\u00029afAB\u0005\u000b\u0011\u0003qQ\fC\u0003)\u000b\u0011\u0005\u0011\r\u0003\u0005c\u000b!\u0015\r\u0015\"\u0003d\u0011\u0015yW\u0001\"\u0001q\u0005eYek\u0015;pe\u0016\u0004&o\u001c;pEV47+\u001a:jC2L'0\u001a:\u000b\u0005-a\u0011\u0001\u00039s_R|'-\u001e4\u000b\u00055q\u0011AB:uCR,8O\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h'\t\u0001Q\u0003\u0005\u0002\u0017K9\u0011qc\t\b\u00031\tr!!G\u0011\u000f\u0005i\u0001cBA\u000e \u001b\u0005a\"BA\u000f\u001f\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\n\n\u0005E\u0011\u0012BA\b\u0011\u0013\tia\"\u0003\u0002%\u0019\u000591JV+uS2\u001c\u0018B\u0001\u0014(\u0005YYek\u0015;pe\u0016\u001c6-\u00197b'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0013\r\u0003\u0019a\u0014N\\5u}Q\t!\u0006\u0005\u0002,\u00015\t!\"A\u0005tKJL\u0017\r\\5{KR\u0011af\u000e\t\u0004_I\"T\"\u0001\u0019\u000b\u0003E\nQa]2bY\u0006L!a\r\u0019\u0003\u000b\u0005\u0013(/Y=\u0011\u0005=*\u0014B\u0001\u001c1\u0005\u0011\u0011\u0015\u0010^3\t\u000ba\u0012\u0001\u0019A\u001d\u0002\u0003=\u0004\"AO \u000e\u0003mR!\u0001P\u001f\u0002\t1\fgn\u001a\u0006\u0002}\u0005!!.\u0019<b\u0013\t\u00015H\u0001\u0004PE*,7\r^\u0001\fI\u0016\u001cXM]5bY&TX-\u0006\u0002D\rR\u0019AiT)\u0011\u0005\u00153E\u0002\u0001\u0003\u0006\u000f\u000e\u0011\r\u0001\u0013\u0002\u0002)F\u0011\u0011\n\u0014\t\u0003_)K!a\u0013\u0019\u0003\u000f9{G\u000f[5oOB\u0011q&T\u0005\u0003\u001dB\u00121!\u00118z\u0011\u0015\u00016\u00011\u0001/\u0003\u0011!\u0017\r^1\t\u000bI\u001b\u0001\u0019A*\u0002\u000b-d\u0017m]:\u0011\u0007QCFI\u0004\u0002V-B\u00111\u0004M\u0005\u0003/B\na\u0001\u0015:fI\u00164\u0017BA-[\u0005\u0015\u0019E.Y:t\u0015\t9\u0006'A\rL-N#xN]3Qe>$xNY;g'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bCA\u0016\u0006'\t)a\f\u0005\u00020?&\u0011\u0001\r\r\u0002\u0007\u0003:L(+\u001a4\u0015\u0003q\u000bQb]3sS\u0006d\u0017N_3s\u001b\u0006\u0004X#\u00013\u0011\tQ+w\r\\\u0005\u0003Mj\u00131!T1qa\tA'\u000eE\u0002U1&\u0004\"!\u00126\u0005\u0013-<\u0011\u0011!A\u0001\u0006\u0003A%aA0%cA\u00191&\u001c'\n\u00059T!!\u0004)s_R|'-\u001e4TKJ$U-A\u0007hKR\u001cVM]5bY&TXM\u001d\u000b\u0003cR\u00042a\f:m\u0013\t\u0019\bG\u0001\u0004PaRLwN\u001c\u0005\u0006%\"\u0001\r!\u001e\u0019\u0003mb\u00042\u0001\u0016-x!\t)\u0005\u0010B\u0005zi\u0006\u0005\t\u0011!B\u0001\u0011\n\u0019q\fJ\u001b"
)
public class KVStoreProtobufSerializer extends KVUtils.KVStoreScalaSerializer {
   public static Option getSerializer(final Class klass) {
      return KVStoreProtobufSerializer$.MODULE$.getSerializer(klass);
   }

   public byte[] serialize(final Object o) {
      Option var3 = KVStoreProtobufSerializer$.MODULE$.getSerializer(o.getClass());
      if (var3 instanceof Some var4) {
         ProtobufSerDe serializer = (ProtobufSerDe)var4.value();
         return serializer.serialize(o);
      } else {
         return super.serialize(o);
      }
   }

   public Object deserialize(final byte[] data, final Class klass) {
      Option var4 = KVStoreProtobufSerializer$.MODULE$.getSerializer(klass);
      if (var4 instanceof Some var5) {
         ProtobufSerDe serializer = (ProtobufSerDe)var5.value();
         return serializer.deserialize(data);
      } else {
         return super.deserialize(data, klass);
      }
   }
}
