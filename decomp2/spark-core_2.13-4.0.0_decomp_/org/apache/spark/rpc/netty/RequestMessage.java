package org.apache.spark.rpc.netty;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.util.ByteBufferOutputStream;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra!\u0002\t\u0012\u0001EY\u0002\u0002\u0003\u0012\u0001\u0005\u000b\u0007I\u0011\u0001\u0013\t\u0011%\u0002!\u0011!Q\u0001\n\u0015B\u0001B\u000b\u0001\u0003\u0006\u0004%\ta\u000b\u0005\ta\u0001\u0011\t\u0011)A\u0005Y!A\u0011\u0007\u0001BC\u0002\u0013\u0005!\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00034\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u0015a\u0005\u0001\"\u0003N\u0011\u0015Y\u0006\u0001\"\u0011]\u000f\u0019A\u0017\u0003#\u0001\u0012S\u001a1\u0001#\u0005E\u0001#)DQa\u000e\u0007\u0005\u0002-DQ\u0001\u001c\u0007\u0005\n5DQa\u001d\u0007\u0005\u0002Q\u0014aBU3rk\u0016\u001cH/T3tg\u0006<WM\u0003\u0002\u0013'\u0005)a.\u001a;us*\u0011A#F\u0001\u0004eB\u001c'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0014\u0005\u0001a\u0002CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g-A\u0007tK:$WM]!eIJ,7o]\u0002\u0001+\u0005)\u0003C\u0001\u0014(\u001b\u0005\u0019\u0012B\u0001\u0015\u0014\u0005)\u0011\u0006oY!eIJ,7o]\u0001\u000fg\u0016tG-\u001a:BI\u0012\u0014Xm]:!\u0003!\u0011XmY3jm\u0016\u0014X#\u0001\u0017\u0011\u00055rS\"A\t\n\u0005=\n\"a\u0005(fiRL(\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0017!\u0003:fG\u0016Lg/\u001a:!\u0003\u001d\u0019wN\u001c;f]R,\u0012a\r\t\u0003;QJ!!\u000e\u0010\u0003\u0007\u0005s\u00170\u0001\u0005d_:$XM\u001c;!\u0003\u0019a\u0014N\\5u}Q!\u0011HO\u001e=!\ti\u0003\u0001C\u0003#\u000f\u0001\u0007Q\u0005C\u0003+\u000f\u0001\u0007A\u0006C\u00032\u000f\u0001\u00071'A\u0005tKJL\u0017\r\\5{KR\u0011qh\u0012\t\u0003\u0001\u0016k\u0011!\u0011\u0006\u0003\u0005\u000e\u000b1A\\5p\u0015\u0005!\u0015\u0001\u00026bm\u0006L!AR!\u0003\u0015\tKH/\u001a\"vM\u001a,'\u000fC\u0003I\u0011\u0001\u0007\u0011*\u0001\u0005oKR$\u00180\u00128w!\ti#*\u0003\u0002L#\tYa*\u001a;usJ\u00038-\u00128w\u0003=9(/\u001b;f%B\u001c\u0017\t\u001a3sKN\u001cHc\u0001(R3B\u0011QdT\u0005\u0003!z\u0011A!\u00168ji\")!+\u0003a\u0001'\u0006\u0019q.\u001e;\u0011\u0005Q;V\"A+\u000b\u0005Y\u001b\u0015AA5p\u0013\tAVK\u0001\tECR\fw*\u001e;qkR\u001cFO]3b[\")!,\u0003a\u0001K\u0005Q!\u000f]2BI\u0012\u0014Xm]:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u0018\t\u0003=\u0016t!aX2\u0011\u0005\u0001tR\"A1\u000b\u0005\t\u001c\u0013A\u0002\u001fs_>$h(\u0003\u0002e=\u00051\u0001K]3eK\u001aL!AZ4\u0003\rM#(/\u001b8h\u0015\t!g$\u0001\bSKF,Xm\u001d;NKN\u001c\u0018mZ3\u0011\u00055b1C\u0001\u0007\u001d)\u0005I\u0017A\u0004:fC\u0012\u0014\u0006oY!eIJ,7o\u001d\u000b\u0003K9DQa\u001c\bA\u0002A\f!!\u001b8\u0011\u0005Q\u000b\u0018B\u0001:V\u0005=!\u0015\r^1J]B,Ho\u0015;sK\u0006l\u0017!B1qa2LH\u0003B\u001dvm~DQ\u0001S\bA\u0002%CQa^\bA\u0002a\faa\u00197jK:$\bCA=~\u001b\u0005Q(BA<|\u0015\taX#A\u0004oKR<xN]6\n\u0005yT(a\u0004+sC:\u001c\bo\u001c:u\u00072LWM\u001c;\t\r\u0005\u0005q\u00021\u0001@\u0003\u0015\u0011\u0017\u0010^3t\u0001"
)
public class RequestMessage {
   private final RpcAddress senderAddress;
   private final NettyRpcEndpointRef receiver;
   private final Object content;

   public static RequestMessage apply(final NettyRpcEnv nettyEnv, final TransportClient client, final ByteBuffer bytes) {
      return RequestMessage$.MODULE$.apply(nettyEnv, client, bytes);
   }

   public RpcAddress senderAddress() {
      return this.senderAddress;
   }

   public NettyRpcEndpointRef receiver() {
      return this.receiver;
   }

   public Object content() {
      return this.content;
   }

   public ByteBuffer serialize(final NettyRpcEnv nettyEnv) {
      ByteBufferOutputStream bos = new ByteBufferOutputStream();
      DataOutputStream out = new DataOutputStream(bos);

      try {
         this.writeRpcAddress(out, this.senderAddress());
         this.writeRpcAddress(out, this.receiver().address());
         out.writeUTF(this.receiver().name());
         SerializationStream s = nettyEnv.serializeStream(out);

         try {
            s.writeObject(this.content(), .MODULE$.Any());
         } finally {
            s.close();
         }
      } finally {
         out.close();
      }

      return bos.toByteBuffer();
   }

   private void writeRpcAddress(final DataOutputStream out, final RpcAddress rpcAddress) {
      if (rpcAddress == null) {
         out.writeBoolean(false);
      } else {
         out.writeBoolean(true);
         out.writeUTF(rpcAddress.host());
         out.writeInt(rpcAddress.port());
      }
   }

   public String toString() {
      RpcAddress var10000 = this.senderAddress();
      return "RequestMessage(" + var10000 + ", " + this.receiver() + ", " + this.content() + ")";
   }

   public RequestMessage(final RpcAddress senderAddress, final NettyRpcEndpointRef receiver, final Object content) {
      this.senderAddress = senderAddress;
      this.receiver = receiver;
      this.content = content;
   }
}
