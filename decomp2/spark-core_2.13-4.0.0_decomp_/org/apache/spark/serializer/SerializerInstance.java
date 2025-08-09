package org.apache.spark.serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.NotThreadSafe;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a!B\u0004\t\u0003\u0003\t\u0002\"\u0002\r\u0001\t\u0003I\u0002\"\u0002\u000f\u0001\r\u0003i\u0002\"B\u001f\u0001\r\u0003q\u0004\"B\u001f\u0001\r\u0003A\u0005\"B-\u0001\r\u0003Q\u0006\"\u00024\u0001\r\u00039'AE*fe&\fG.\u001b>fe&s7\u000f^1oG\u0016T!!\u0003\u0006\u0002\u0015M,'/[1mSj,'O\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\n\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t!\u0004\u0005\u0002\u001c\u00015\t\u0001\"A\u0005tKJL\u0017\r\\5{KV\u0011aD\r\u000b\u0003?m\"\"\u0001\t\u0015\u0011\u0005\u00052S\"\u0001\u0012\u000b\u0005\r\"\u0013a\u00018j_*\tQ%\u0001\u0003kCZ\f\u0017BA\u0014#\u0005)\u0011\u0015\u0010^3Ck\u001a4WM\u001d\u0005\bS\t\t\t\u0011q\u0001+\u0003))g/\u001b3f]\u000e,G%\r\t\u0004W9\u0002T\"\u0001\u0017\u000b\u00055\"\u0012a\u0002:fM2,7\r^\u0005\u0003_1\u0012\u0001b\u00117bgN$\u0016m\u001a\t\u0003cIb\u0001\u0001B\u00034\u0005\t\u0007AGA\u0001U#\t)\u0004\b\u0005\u0002\u0014m%\u0011q\u0007\u0006\u0002\b\u001d>$\b.\u001b8h!\t\u0019\u0012(\u0003\u0002;)\t\u0019\u0011I\\=\t\u000bq\u0012\u0001\u0019\u0001\u0019\u0002\u0003Q\f1\u0002Z3tKJL\u0017\r\\5{KV\u0011qH\u0011\u000b\u0003\u0001\u001a#\"!Q\"\u0011\u0005E\u0012E!B\u001a\u0004\u0005\u0004!\u0004b\u0002#\u0004\u0003\u0003\u0005\u001d!R\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004cA\u0016/\u0003\")qi\u0001a\u0001A\u0005)!-\u001f;fgV\u0011\u0011\n\u0014\u000b\u0004\u0015B\u000bFCA&N!\t\tD\nB\u00034\t\t\u0007A\u0007C\u0004O\t\u0005\u0005\t9A(\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002,]-CQa\u0012\u0003A\u0002\u0001BQA\u0015\u0003A\u0002M\u000ba\u0001\\8bI\u0016\u0014\bC\u0001+X\u001b\u0005)&B\u0001,%\u0003\u0011a\u0017M\\4\n\u0005a+&aC\"mCN\u001cHj\\1eKJ\fqb]3sS\u0006d\u0017N_3TiJ,\u0017-\u001c\u000b\u00037z\u0003\"a\u0007/\n\u0005uC!aE*fe&\fG.\u001b>bi&|gn\u0015;sK\u0006l\u0007\"B0\u0006\u0001\u0004\u0001\u0017!A:\u0011\u0005\u0005$W\"\u00012\u000b\u0005\r$\u0013AA5p\u0013\t)'M\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW.A\teKN,'/[1mSj,7\u000b\u001e:fC6$\"\u0001[6\u0011\u0005mI\u0017B\u00016\t\u0005U!Um]3sS\u0006d\u0017N_1uS>t7\u000b\u001e:fC6DQa\u0018\u0004A\u00021\u0004\"!Y7\n\u00059\u0014'aC%oaV$8\u000b\u001e:fC6D#\u0001\u00019\u0011\u0005E$X\"\u0001:\u000b\u0005MT\u0011AC1o]>$\u0018\r^5p]&\u0011QO\u001d\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b\u0015\u0003\u0001]\u0004\"\u0001\u001f@\u000e\u0003eT!A_>\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002ty*\tQ0A\u0003kCZ\f\u00070\u0003\u0002\u0000s\niaj\u001c;UQJ,\u0017\rZ*bM\u0016\u0004"
)
@NotThreadSafe
public abstract class SerializerInstance {
   public abstract ByteBuffer serialize(final Object t, final ClassTag evidence$1);

   public abstract Object deserialize(final ByteBuffer bytes, final ClassTag evidence$2);

   public abstract Object deserialize(final ByteBuffer bytes, final ClassLoader loader, final ClassTag evidence$3);

   public abstract SerializationStream serializeStream(final OutputStream s);

   public abstract DeserializationStream deserializeStream(final InputStream s);
}
