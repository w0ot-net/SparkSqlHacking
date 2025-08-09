package org.apache.spark.storage;

import java.nio.ByteBuffer;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;aa\u0002\u0005\t\u0002)\u0001bA\u0002\n\t\u0011\u0003Q1\u0003C\u0003!\u0003\u0011\u0005!\u0005C\u0004$\u0003\t\u0007I\u0011\u0002\u0013\t\rM\n\u0001\u0015!\u0003&\u0011\u0015!\u0014\u0001\"\u00016\u0011\u0015A\u0014\u0001\"\u0001:\u00031\u0019Fo\u001c:bO\u0016,F/\u001b7t\u0015\tI!\"A\u0004ti>\u0014\u0018mZ3\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u0004\"!E\u0001\u000e\u0003!\u0011Ab\u0015;pe\u0006<W-\u0016;jYN\u001c2!\u0001\u000b\u001b!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00111DH\u0007\u00029)\u0011QDC\u0001\tS:$XM\u001d8bY&\u0011q\u0004\b\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\t\u0002\u001b\t,hMZ3s\u00072,\u0017M\\3s+\u0005)\u0003\u0003B\u000b'QAJ!a\n\f\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CA\u0015/\u001b\u0005Q#BA\u0016-\u0003\rq\u0017n\u001c\u0006\u0002[\u0005!!.\u0019<b\u0013\ty#F\u0001\u0006CsR,')\u001e4gKJ\u0004\"!F\u0019\n\u0005I2\"\u0001B+oSR\faBY;gM\u0016\u00148\t\\3b]\u0016\u0014\b%A\u0004eSN\u0004xn]3\u0015\u0005A2\u0004\"B\u001c\u0006\u0001\u0004A\u0013A\u00022vM\u001a,'/\u0001\u000efqR,'O\\1m'\",hM\u001a7f'\u0016\u0014h/[2f!>\u0014H\u000f\u0006\u0002;{A\u0011QcO\u0005\u0003yY\u00111!\u00138u\u0011\u0015qd\u00011\u0001@\u0003\u0011\u0019wN\u001c4\u0011\u0005\u0001\u000bU\"\u0001\u0006\n\u0005\tS!!C*qCJ\\7i\u001c8g\u0001"
)
public final class StorageUtils {
   public static int externalShuffleServicePort(final SparkConf conf) {
      return StorageUtils$.MODULE$.externalShuffleServicePort(conf);
   }

   public static void dispose(final ByteBuffer buffer) {
      StorageUtils$.MODULE$.dispose(buffer);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return StorageUtils$.MODULE$.LogStringContext(sc);
   }
}
