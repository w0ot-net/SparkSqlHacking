package org.apache.spark.storage;

import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3\u0001\u0002C\u0005\u0011\u0002G\u00051\"\u0005\u0005\u00061\u00011\tA\u0007\u0005\u0006G\u00011\t\u0001\n\u0005\u0006W\u00011\t\u0001\n\u0005\u0006Y\u00011\t!\f\u0005\u0006\u0007\u00021\t\u0001\u0012\u0005\u0006\u000b\u00021\tA\u0012\u0005\u0006\u0015\u00021\ta\u0013\u0002\n\u00052|7m\u001b#bi\u0006T!AC\u0006\u0002\u000fM$xN]1hK*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0002\u0001%A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\fQ\u0002^8J]B,Ho\u0015;sK\u0006l7\u0001\u0001\u000b\u00027A\u0011A$I\u0007\u0002;)\u0011adH\u0001\u0003S>T\u0011\u0001I\u0001\u0005U\u00064\u0018-\u0003\u0002#;\tY\u0011J\u001c9viN#(/Z1n\u0003\u001d!xNT3uif$\u0012!\n\t\u0003M%j\u0011a\n\u0006\u0003Q}\tA\u0001\\1oO&\u0011!f\n\u0002\u0007\u001f\nTWm\u0019;\u0002\u001bQ|g*\u001a;us\u001a{'oU:m\u0003M!xn\u00115v].,GMQ=uK\n+hMZ3s)\tqS\u0007\u0005\u00020g5\t\u0001G\u0003\u0002\u001fc)\u0011!gC\u0001\u0005kRLG.\u0003\u00025a\t\t2\t[;oW\u0016$')\u001f;f\u0005V4g-\u001a:\t\u000bY\"\u0001\u0019A\u001c\u0002\u0013\u0005dGn\\2bi>\u0014\b\u0003B\n9uuJ!!\u000f\u000b\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CA\n<\u0013\taDCA\u0002J]R\u0004\"AP!\u000e\u0003}R!\u0001Q\u0010\u0002\u00079Lw.\u0003\u0002C\u007f\tQ!)\u001f;f\u0005V4g-\u001a:\u0002\u0019Q|')\u001f;f\u0005V4g-\u001a:\u0015\u0003u\nAa]5{KV\tq\t\u0005\u0002\u0014\u0011&\u0011\u0011\n\u0006\u0002\u0005\u0019>tw-A\u0004eSN\u0004xn]3\u0015\u00031\u0003\"aE'\n\u00059#\"\u0001B+oSR\u0004"
)
public interface BlockData {
   InputStream toInputStream();

   Object toNetty();

   Object toNettyForSsl();

   ChunkedByteBuffer toChunkedByteBuffer(final Function1 allocator);

   ByteBuffer toByteBuffer();

   long size();

   void dispose();
}
