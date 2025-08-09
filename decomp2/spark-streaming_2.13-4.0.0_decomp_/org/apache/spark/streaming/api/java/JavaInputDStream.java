package org.apache.spark.streaming.api.java;

import org.apache.spark.streaming.dstream.InputDStream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%4Aa\u0003\u0007\u00013!AQ\u0006\u0001BC\u0002\u0013\u0005a\u0006C\u00056\u0001\t\u0005\t\u0015!\u00030m!Aq\u0007\u0001BC\u0002\u0013\r\u0003\bC\u0005@\u0001\t\u0005\t\u0015!\u0003:\u0001\")\u0011\t\u0001C\u0001\u0005\u001e)q\t\u0004E\u0001\u0011\u001a)1\u0002\u0004E\u0001\u0013\")\u0011i\u0002C\u0001)\")Qk\u0002C\u0002-\"9\u0011mBA\u0001\n\u0013\u0011'\u0001\u0005&bm\u0006Le\u000e];u\tN#(/Z1n\u0015\tia\"\u0001\u0003kCZ\f'BA\b\u0011\u0003\r\t\u0007/\u001b\u0006\u0003#I\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u001bCM\u0011\u0001a\u0007\t\u00049uyR\"\u0001\u0007\n\u0005ya!a\u0003&bm\u0006$5\u000b\u001e:fC6\u0004\"\u0001I\u0011\r\u0001\u0011)!\u0005\u0001b\u0001G\t\tA+\u0005\u0002%UA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t9aj\u001c;iS:<\u0007CA\u0013,\u0013\tacEA\u0002B]f\fA\"\u001b8qkR$5\u000b\u001e:fC6,\u0012a\f\t\u0004aMzR\"A\u0019\u000b\u0005I\u0002\u0012a\u00023tiJ,\u0017-\\\u0005\u0003iE\u0012A\"\u00138qkR$5\u000b\u001e:fC6\fQ\"\u001b8qkR$5\u000b\u001e:fC6\u0004\u0013B\u0001\u001a\u001e\u0003!\u0019G.Y:t)\u0006<W#A\u001d\u0011\u0007ijt$D\u0001<\u0015\tad%A\u0004sK\u001adWm\u0019;\n\u0005yZ$\u0001C\"mCN\u001cH+Y4\u0002\u0013\rd\u0017m]:UC\u001e\u0004\u0013BA\u001c\u001e\u0003\u0019a\u0014N\\5u}Q\u00111I\u0012\u000b\u0003\t\u0016\u00032\u0001\b\u0001 \u0011\u00159T\u0001q\u0001:\u0011\u0015iS\u00011\u00010\u0003AQ\u0015M^1J]B,H\u000fR*ue\u0016\fW\u000e\u0005\u0002\u001d\u000fM\u0019qAS'\u0011\u0005\u0015Z\u0015B\u0001''\u0005\u0019\te.\u001f*fMB\u0011aJU\u0007\u0002\u001f*\u0011\u0001+U\u0001\u0003S>T\u0011!D\u0005\u0003'>\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\u0012\u0001S\u0001\u0011MJ|W.\u00138qkR$5\u000b\u001e:fC6,\"aV.\u0015\u0005a{FCA-]!\ra\u0002A\u0017\t\u0003Am#QAI\u0005C\u0002\rBq!X\u0005\u0002\u0002\u0003\u000fa,\u0001\u0006fm&$WM\\2fIE\u00022AO\u001f[\u0011\u0015i\u0013\u00021\u0001a!\r\u00014GW\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002GB\u0011AmZ\u0007\u0002K*\u0011a-U\u0001\u0005Y\u0006tw-\u0003\u0002iK\n1qJ\u00196fGR\u0004"
)
public class JavaInputDStream extends JavaDStream {
   public static JavaInputDStream fromInputDStream(final InputDStream inputDStream, final ClassTag evidence$1) {
      return JavaInputDStream$.MODULE$.fromInputDStream(inputDStream, evidence$1);
   }

   public InputDStream inputDStream() {
      return (InputDStream)super.dstream();
   }

   public ClassTag classTag() {
      return super.classTag();
   }

   public JavaInputDStream(final InputDStream inputDStream, final ClassTag classTag) {
      super(inputDStream, classTag);
   }
}
