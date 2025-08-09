package org.apache.spark.streaming.api.java;

import org.apache.spark.streaming.dstream.ReceiverInputDStream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra\u0001B\u0007\u000f\u0001mA\u0001B\r\u0001\u0003\u0006\u0004%\ta\r\u0005\n{\u0001\u0011\t\u0011)A\u0005iyB\u0001\"\u0011\u0001\u0003\u0006\u0004%\u0019E\u0011\u0005\n\u0013\u0002\u0011\t\u0011)A\u0005\u0007*C\u0001\u0002\u0014\u0001\u0003\u0006\u0004%\u0019%\u0014\u0005\n\u001f\u0002\u0011\t\u0011)A\u0005\u001dBCQA\u0015\u0001\u0005\u0002M;Q!\u0017\b\t\u0002i3Q!\u0004\b\t\u0002mCQAU\u0005\u0005\u0002\u0019DQaZ\u0005\u0005\u0004!Dq!_\u0005\u0002\u0002\u0013%!P\u0001\u000fKCZ\f\u0007+Y5s%\u0016\u001cW-\u001b<fe&s\u0007/\u001e;E'R\u0014X-Y7\u000b\u0005=\u0001\u0012\u0001\u00026bm\u0006T!!\u0005\n\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0014)\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sO\u000e\u0001Qc\u0001\u000f$aM\u0011\u0001!\b\t\u0005=}\ts&D\u0001\u000f\u0013\t\u0001cB\u0001\u000bKCZ\f\u0007+Y5s\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c\t\u0003E\rb\u0001\u0001B\u0003%\u0001\t\u0007QEA\u0001L#\t1C\u0006\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003FA\u0004O_RD\u0017N\\4\u0011\u0005\u001dj\u0013B\u0001\u0018)\u0005\r\te.\u001f\t\u0003EA\"Q!\r\u0001C\u0002\u0015\u0012\u0011AV\u0001\u0015e\u0016\u001cW-\u001b<fe&s\u0007/\u001e;E'R\u0014X-Y7\u0016\u0003Q\u00022!\u000e\u001d;\u001b\u00051$BA\u001c\u0013\u0003\u001d!7\u000f\u001e:fC6L!!\u000f\u001c\u0003)I+7-Z5wKJLe\u000e];u\tN#(/Z1n!\u001193(I\u0018\n\u0005qB#A\u0002+va2,''A\u000bsK\u000e,\u0017N^3s\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c\u0011\n\u0005]z\u0014B\u0001!\u000f\u0005=Q\u0015M^1QC&\u0014Hi\u0015;sK\u0006l\u0017!C6DY\u0006\u001c8\u000fV1h+\u0005\u0019\u0005c\u0001#HC5\tQI\u0003\u0002GQ\u00059!/\u001a4mK\u000e$\u0018B\u0001%F\u0005!\u0019E.Y:t)\u0006<\u0017AC6DY\u0006\u001c8\u000fV1hA%\u00111jP\u0001\nW6\u000bg.\u001b4fgR\f\u0011B^\"mCN\u001cH+Y4\u0016\u00039\u00032\u0001R$0\u0003)18\t\\1tgR\u000bw\rI\u0005\u0003#~\n\u0011B^'b]&4Wm\u001d;\u0002\rqJg.\u001b;?)\t!\u0006\fF\u0002V-^\u0003BA\b\u0001\"_!)\u0011i\u0002a\u0002\u0007\")Aj\u0002a\u0002\u001d\")!g\u0002a\u0001i\u0005a\"*\u0019<b!\u0006L'OU3dK&4XM]%oaV$Hi\u0015;sK\u0006l\u0007C\u0001\u0010\n'\rIAl\u0018\t\u0003OuK!A\u0018\u0015\u0003\r\u0005s\u0017PU3g!\t\u0001G-D\u0001b\u0015\t\u00117-\u0001\u0002j_*\tq\"\u0003\u0002fC\na1+\u001a:jC2L'0\u00192mKR\t!,\u0001\rge>l'+Z2fSZ,'/\u00138qkR$5\u000b\u001e:fC6,2![7p)\tQg\u000fF\u0002laN\u0004BA\b\u0001m]B\u0011!%\u001c\u0003\u0006I-\u0011\r!\n\t\u0003E=$Q!M\u0006C\u0002\u0015Bq!]\u0006\u0002\u0002\u0003\u000f!/\u0001\u0006fm&$WM\\2fIE\u00022\u0001R$m\u0011\u001d!8\"!AA\u0004U\f!\"\u001a<jI\u0016t7-\u001a\u00133!\r!uI\u001c\u0005\u0006e-\u0001\ra\u001e\t\u0004kaB\b\u0003B\u0014<Y:\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u001f\t\u0003y~l\u0011! \u0006\u0003}\u000e\fA\u0001\\1oO&\u0019\u0011\u0011A?\u0003\r=\u0013'.Z2u\u0001"
)
public class JavaPairReceiverInputDStream extends JavaPairInputDStream {
   public static JavaPairReceiverInputDStream fromReceiverInputDStream(final ReceiverInputDStream receiverInputDStream, final ClassTag evidence$1, final ClassTag evidence$2) {
      return JavaPairReceiverInputDStream$.MODULE$.fromReceiverInputDStream(receiverInputDStream, evidence$1, evidence$2);
   }

   public ReceiverInputDStream receiverInputDStream() {
      return (ReceiverInputDStream)super.dstream();
   }

   public ClassTag kClassTag() {
      return super.kManifest();
   }

   public ClassTag vClassTag() {
      return super.vManifest();
   }

   public JavaPairReceiverInputDStream(final ReceiverInputDStream receiverInputDStream, final ClassTag kClassTag, final ClassTag vClassTag) {
      super(receiverInputDStream, kClassTag, vClassTag);
   }
}
