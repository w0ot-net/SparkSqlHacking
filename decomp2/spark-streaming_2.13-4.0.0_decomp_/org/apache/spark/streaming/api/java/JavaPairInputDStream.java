package org.apache.spark.streaming.api.java;

import org.apache.spark.streaming.dstream.InputDStream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4A!\u0004\b\u00017!A!\u0007\u0001BC\u0002\u0013\u00051\u0007C\u0005>\u0001\t\u0005\t\u0015!\u00035}!Aq\b\u0001BC\u0002\u0013\r\u0001\tC\u0005H\u0001\t\u0005\t\u0015!\u0003B\u0011\"A!\n\u0001BC\u0002\u0013\r1\nC\u0005N\u0001\t\u0005\t\u0015!\u0003M\u001d\")\u0001\u000b\u0001C\u0001#\u001e)qK\u0004E\u00011\u001a)QB\u0004E\u00013\")\u0001+\u0003C\u0001I\")Q-\u0003C\u0002M\"9q/CA\u0001\n\u0013A(\u0001\u0006&bm\u0006\u0004\u0016-\u001b:J]B,H\u000fR*ue\u0016\fWN\u0003\u0002\u0010!\u0005!!.\u0019<b\u0015\t\t\"#A\u0002ba&T!a\u0005\u000b\u0002\u0013M$(/Z1nS:<'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0004\u0001U\u0019Ad\t\u0019\u0014\u0005\u0001i\u0002\u0003\u0002\u0010 C=j\u0011AD\u0005\u0003A9\u0011qBS1wCB\u000b\u0017N\u001d#TiJ,\u0017-\u001c\t\u0003E\rb\u0001\u0001B\u0003%\u0001\t\u0007QEA\u0001L#\t1C\u0006\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003FA\u0004O_RD\u0017N\\4\u0011\u0005\u001dj\u0013B\u0001\u0018)\u0005\r\te.\u001f\t\u0003EA\"Q!\r\u0001C\u0002\u0015\u0012\u0011AV\u0001\rS:\u0004X\u000f\u001e#TiJ,\u0017-\\\u000b\u0002iA\u0019Q\u0007\u000f\u001e\u000e\u0003YR!a\u000e\n\u0002\u000f\u0011\u001cHO]3b[&\u0011\u0011H\u000e\u0002\r\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c\t\u0005Om\ns&\u0003\u0002=Q\t1A+\u001e9mKJ\nQ\"\u001b8qkR$5\u000b\u001e:fC6\u0004\u0013BA\u001c \u0003%Y7\t\\1tgR\u000bw-F\u0001B!\r\u0011U)I\u0007\u0002\u0007*\u0011A\tK\u0001\be\u00164G.Z2u\u0013\t15I\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003)Y7\t\\1tgR\u000bw\rI\u0005\u0003\u0013~\t\u0011b['b]&4Wm\u001d;\u0002\u0013Y\u001cE.Y:t)\u0006<W#\u0001'\u0011\u0007\t+u&\u0001\u0006w\u00072\f7o\u001d+bO\u0002J!aT\u0010\u0002\u0013Yl\u0015M\\5gKN$\u0018A\u0002\u001fj]&$h\b\u0006\u0002S-R\u00191\u000bV+\u0011\ty\u0001\u0011e\f\u0005\u0006\u007f\u001d\u0001\u001d!\u0011\u0005\u0006\u0015\u001e\u0001\u001d\u0001\u0014\u0005\u0006e\u001d\u0001\r\u0001N\u0001\u0015\u0015\u00064\u0018\rU1je&s\u0007/\u001e;E'R\u0014X-Y7\u0011\u0005yI1cA\u0005[;B\u0011qeW\u0005\u00039\"\u0012a!\u00118z%\u00164\u0007C\u00010c\u001b\u0005y&B\u00011b\u0003\tIwNC\u0001\u0010\u0013\t\u0019wL\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001Y\u0003A1'o\\7J]B,H\u000fR*ue\u0016\fW.F\u0002hW6$\"\u0001\u001b;\u0015\u0007%t\u0017\u000f\u0005\u0003\u001f\u0001)d\u0007C\u0001\u0012l\t\u0015!3B1\u0001&!\t\u0011S\u000eB\u00032\u0017\t\u0007Q\u0005C\u0004p\u0017\u0005\u0005\t9\u00019\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002C\u000b*DqA]\u0006\u0002\u0002\u0003\u000f1/\u0001\u0006fm&$WM\\2fII\u00022AQ#m\u0011\u0015\u00114\u00021\u0001v!\r)\u0004H\u001e\t\u0005OmRG.\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001z!\tQX0D\u0001|\u0015\ta\u0018-\u0001\u0003mC:<\u0017B\u0001@|\u0005\u0019y%M[3di\u0002"
)
public class JavaPairInputDStream extends JavaPairDStream {
   public static JavaPairInputDStream fromInputDStream(final InputDStream inputDStream, final ClassTag evidence$1, final ClassTag evidence$2) {
      return JavaPairInputDStream$.MODULE$.fromInputDStream(inputDStream, evidence$1, evidence$2);
   }

   public InputDStream inputDStream() {
      return (InputDStream)super.dstream();
   }

   public ClassTag kClassTag() {
      return super.kManifest();
   }

   public ClassTag vClassTag() {
      return super.vManifest();
   }

   public JavaPairInputDStream(final InputDStream inputDStream, final ClassTag kClassTag, final ClassTag vClassTag) {
      super(inputDStream, kClassTag, vClassTag);
   }
}
