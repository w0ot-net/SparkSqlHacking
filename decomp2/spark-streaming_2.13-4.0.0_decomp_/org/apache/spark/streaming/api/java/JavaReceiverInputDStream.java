package org.apache.spark.streaming.api.java;

import org.apache.spark.streaming.dstream.ReceiverInputDStream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4Aa\u0003\u0007\u00013!AQ\u0006\u0001BC\u0002\u0013\u0005a\u0006C\u00056\u0001\t\u0005\t\u0015!\u00030m!A\u0011\b\u0001BC\u0002\u0013\r#\b\u0003\u0005B\u0001\t\u0005\t\u0015!\u0003<\u0011\u0015\u0011\u0005\u0001\"\u0001D\u000f\u0015AE\u0002#\u0001J\r\u0015YA\u0002#\u0001K\u0011\u0015\u0011u\u0001\"\u0001V\u0011\u00151v\u0001b\u0001X\u0011\u001d\u0011w!!A\u0005\n\r\u0014\u0001DS1wCJ+7-Z5wKJLe\u000e];u\tN#(/Z1n\u0015\tia\"\u0001\u0003kCZ\f'BA\b\u0011\u0003\r\t\u0007/\u001b\u0006\u0003#I\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u001bCM\u0011\u0001a\u0007\t\u00049uyR\"\u0001\u0007\n\u0005ya!\u0001\u0005&bm\u0006Le\u000e];u\tN#(/Z1n!\t\u0001\u0013\u0005\u0004\u0001\u0005\u000b\t\u0002!\u0019A\u0012\u0003\u0003Q\u000b\"\u0001\n\u0016\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\u000f9{G\u000f[5oOB\u0011QeK\u0005\u0003Y\u0019\u00121!\u00118z\u0003Q\u0011XmY3jm\u0016\u0014\u0018J\u001c9vi\u0012\u001bFO]3b[V\tq\u0006E\u00021g}i\u0011!\r\u0006\u0003eA\tq\u0001Z:ue\u0016\fW.\u0003\u00025c\t!\"+Z2fSZ,'/\u00138qkR$5\u000b\u001e:fC6\fQC]3dK&4XM]%oaV$Hi\u0015;sK\u0006l\u0007%\u0003\u00023o%\u0011\u0001\b\u0004\u0002\f\u0015\u00064\u0018\rR*ue\u0016\fW.\u0001\u0005dY\u0006\u001c8\u000fV1h+\u0005Y\u0004c\u0001\u001f@?5\tQH\u0003\u0002?M\u00059!/\u001a4mK\u000e$\u0018B\u0001!>\u0005!\u0019E.Y:t)\u0006<\u0017!C2mCN\u001cH+Y4!\u0003\u0019a\u0014N\\5u}Q\u0011Ai\u0012\u000b\u0003\u000b\u001a\u00032\u0001\b\u0001 \u0011\u0015IT\u0001q\u0001<\u0011\u0015iS\u00011\u00010\u0003aQ\u0015M^1SK\u000e,\u0017N^3s\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c\t\u00039\u001d\u00192aB&O!\t)C*\u0003\u0002NM\t1\u0011I\\=SK\u001a\u0004\"aT*\u000e\u0003AS!!\u0015*\u0002\u0005%|'\"A\u0007\n\u0005Q\u0003&\u0001D*fe&\fG.\u001b>bE2,G#A%\u00021\u0019\u0014x.\u001c*fG\u0016Lg/\u001a:J]B,H\u000fR*ue\u0016\fW.\u0006\u0002Y9R\u0011\u0011\f\u0019\u000b\u00035v\u00032\u0001\b\u0001\\!\t\u0001C\fB\u0003#\u0013\t\u00071\u0005C\u0004_\u0013\u0005\u0005\t9A0\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002=\u007fmCQ!L\u0005A\u0002\u0005\u00042\u0001M\u001a\\\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005!\u0007CA3i\u001b\u00051'BA4S\u0003\u0011a\u0017M\\4\n\u0005%4'AB(cU\u0016\u001cG\u000f"
)
public class JavaReceiverInputDStream extends JavaInputDStream {
   private final ClassTag classTag;

   public static JavaReceiverInputDStream fromReceiverInputDStream(final ReceiverInputDStream receiverInputDStream, final ClassTag evidence$1) {
      return JavaReceiverInputDStream$.MODULE$.fromReceiverInputDStream(receiverInputDStream, evidence$1);
   }

   public ReceiverInputDStream receiverInputDStream() {
      return (ReceiverInputDStream)super.dstream();
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   public JavaReceiverInputDStream(final ReceiverInputDStream receiverInputDStream, final ClassTag classTag) {
      super(receiverInputDStream, classTag);
      this.classTag = classTag;
   }
}
