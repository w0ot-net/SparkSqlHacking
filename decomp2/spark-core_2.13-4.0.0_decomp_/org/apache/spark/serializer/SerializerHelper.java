package org.apache.spark.serializer;

import org.apache.spark.internal.Logging;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d<aAB\u0004\t\u0002%yaAB\t\b\u0011\u0003I!\u0003C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0003#\u0003\u0011\u00051\u0005C\u0004N\u0003E\u0005I\u0011\u0001(\t\u000bm\u000bA\u0011\u0001/\u0002!M+'/[1mSj,'\u000fS3ma\u0016\u0014(B\u0001\u0005\n\u0003)\u0019XM]5bY&TXM\u001d\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sOB\u0011\u0001#A\u0007\u0002\u000f\t\u00012+\u001a:jC2L'0\u001a:IK2\u0004XM]\n\u0004\u0003MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001b;5\t1D\u0003\u0002\u001d\u0013\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\u001f7\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003=\t\u0001d]3sS\u0006d\u0017N_3U_\u000eCWO\\6fI\n+hMZ3s+\t!\u0003\b\u0006\u0003&\u0003\u001aCEC\u0001\u0014/!\t9C&D\u0001)\u0015\tI#&\u0001\u0002j_*\u00111&C\u0001\u0005kRLG.\u0003\u0002.Q\t\t2\t[;oW\u0016$')\u001f;f\u0005V4g-\u001a:\t\u000f=\u001a\u0011\u0011!a\u0002a\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007E\"d'D\u00013\u0015\t\u0019T#A\u0004sK\u001adWm\u0019;\n\u0005U\u0012$\u0001C\"mCN\u001cH+Y4\u0011\u0005]BD\u0002\u0001\u0003\u0006s\r\u0011\rA\u000f\u0002\u0002)F\u00111H\u0010\t\u0003)qJ!!P\u000b\u0003\u000f9{G\u000f[5oOB\u0011AcP\u0005\u0003\u0001V\u00111!\u00118z\u0011\u0015\u00115\u00011\u0001D\u0003I\u0019XM]5bY&TXM]%ogR\fgnY3\u0011\u0005A!\u0015BA#\b\u0005I\u0019VM]5bY&TXM]%ogR\fgnY3\t\u000b\u001d\u001b\u0001\u0019\u0001\u001c\u0002#=\u0014'.Z2u)>\u001cVM]5bY&TX\rC\u0004J\u0007A\u0005\t\u0019\u0001&\u0002\u001b\u0015\u001cH/[7bi\u0016$7+\u001b>f!\t!2*\u0003\u0002M+\t!Aj\u001c8h\u0003\t\u001aXM]5bY&TX\rV8DQVt7.\u001a3Ck\u001a4WM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011qJW\u000b\u0002!*\u0012!*U\u0016\u0002%B\u00111\u000bW\u0007\u0002)*\u0011QKV\u0001\nk:\u001c\u0007.Z2lK\u0012T!aV\u000b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Z)\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000be\"!\u0019\u0001\u001e\u00029\u0011,7/\u001a:jC2L'0\u001a$s_6\u001c\u0005.\u001e8lK\u0012\u0014UO\u001a4feV\u0011Q\f\u0019\u000b\u0004=\u0012,GCA0b!\t9\u0004\rB\u0003:\u000b\t\u0007!\bC\u0004c\u000b\u0005\u0005\t9A2\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u00022i}CQAQ\u0003A\u0002\rCQAZ\u0003A\u0002\u0019\nQAY=uKN\u0004"
)
public final class SerializerHelper {
   public static Object deserializeFromChunkedBuffer(final SerializerInstance serializerInstance, final ChunkedByteBuffer bytes, final ClassTag evidence$2) {
      return SerializerHelper$.MODULE$.deserializeFromChunkedBuffer(serializerInstance, bytes, evidence$2);
   }

   public static long serializeToChunkedBuffer$default$3() {
      return SerializerHelper$.MODULE$.serializeToChunkedBuffer$default$3();
   }

   public static ChunkedByteBuffer serializeToChunkedBuffer(final SerializerInstance serializerInstance, final Object objectToSerialize, final long estimatedSize, final ClassTag evidence$1) {
      return SerializerHelper$.MODULE$.serializeToChunkedBuffer(serializerInstance, objectToSerialize, estimatedSize, evidence$1);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SerializerHelper$.MODULE$.LogStringContext(sc);
   }
}
