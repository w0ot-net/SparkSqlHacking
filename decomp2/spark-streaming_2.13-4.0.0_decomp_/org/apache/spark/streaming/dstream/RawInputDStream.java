package org.apache.spark.streaming.dstream;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.receiver.Receiver;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4Q\u0001C\u0005\u0001\u0017MA\u0001B\f\u0001\u0003\u0002\u0003\u0006Ia\f\u0005\tg\u0001\u0011\t\u0011)A\u0005i!Aq\b\u0001B\u0001B\u0003%\u0001\t\u0003\u0005D\u0001\t\u0005\t\u0015!\u0003E\u0011!Q\u0005AaA!\u0002\u0017Y\u0005\"B)\u0001\t\u0003\u0011\u0006\"\u0002.\u0001\t\u0003Y&a\u0004*bo&s\u0007/\u001e;E'R\u0014X-Y7\u000b\u0005)Y\u0011a\u00023tiJ,\u0017-\u001c\u0006\u0003\u00195\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e,\"\u0001F\u000e\u0014\u0007\u0001)\u0002\u0006E\u0002\u0017/ei\u0011!C\u0005\u00031%\u0011ACU3dK&4XM]%oaV$Hi\u0015;sK\u0006l\u0007C\u0001\u000e\u001c\u0019\u0001!Q\u0001\b\u0001C\u0002y\u0011\u0011\u0001V\u0002\u0001#\tyR\u0005\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013EA\u0004O_RD\u0017N\\4\u0011\u0005\u00012\u0013BA\u0014\"\u0005\r\te.\u001f\t\u0003S1j\u0011A\u000b\u0006\u0003W5\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003[)\u0012q\u0001T8hO&tw-\u0001\u0003`gN\u001c\u0007C\u0001\u00192\u001b\u0005Y\u0011B\u0001\u001a\f\u0005A\u0019FO]3b[&twmQ8oi\u0016DH/\u0001\u0003i_N$\bCA\u001b=\u001d\t1$\b\u0005\u00028C5\t\u0001H\u0003\u0002:;\u00051AH]8pizJ!aO\u0011\u0002\rA\u0013X\rZ3g\u0013\tidH\u0001\u0004TiJLgn\u001a\u0006\u0003w\u0005\nA\u0001]8siB\u0011\u0001%Q\u0005\u0003\u0005\u0006\u00121!\u00138u\u00031\u0019Ho\u001c:bO\u0016dUM^3m!\t)\u0005*D\u0001G\u0015\t9U\"A\u0004ti>\u0014\u0018mZ3\n\u0005%3%\u0001D*u_J\fw-\u001a'fm\u0016d\u0017AC3wS\u0012,gnY3%cA\u0019AjT\r\u000e\u00035S!AT\u0011\u0002\u000fI,g\r\\3di&\u0011\u0001+\u0014\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"Ra\u0015,X1f#\"\u0001V+\u0011\u0007Y\u0001\u0011\u0004C\u0003K\r\u0001\u000f1\nC\u0003/\r\u0001\u0007q\u0006C\u00034\r\u0001\u0007A\u0007C\u0003@\r\u0001\u0007\u0001\tC\u0003D\r\u0001\u0007A)A\u0006hKR\u0014VmY3jm\u0016\u0014H#\u0001/\u0011\u0007u\u0003\u0017$D\u0001_\u0015\ty6\"\u0001\u0005sK\u000e,\u0017N^3s\u0013\t\tgL\u0001\u0005SK\u000e,\u0017N^3s\u0001"
)
public class RawInputDStream extends ReceiverInputDStream {
   private final String host;
   private final int port;
   private final StorageLevel storageLevel;

   public Receiver getReceiver() {
      return new RawNetworkReceiver(this.host, this.port, this.storageLevel);
   }

   public RawInputDStream(final StreamingContext _ssc, final String host, final int port, final StorageLevel storageLevel, final ClassTag evidence$1) {
      super(_ssc, evidence$1);
      this.host = host;
      this.port = port;
      this.storageLevel = storageLevel;
   }
}
