package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.UnsafeOutput;
import java.io.IOException;
import java.io.OutputStream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4QAC\u0006\u0001\u001bMA\u0001\u0002\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\t;\u0001\u0011\t\u0011)A\u0005=!Aa\u0005\u0001B\u0001B\u0003%q\u0005C\u0003.\u0001\u0011\u0005a\u0006\u0003\u00044\u0001\u0001\u0006K\u0001\u000e\u0005\u0007q\u0001\u0001\u000b\u0015B \t\u000b\r\u0003A\u0011\t#\t\u000bq\u0003A\u0011I/\t\u000b\u0005\u0004A\u0011I/\u0003/-\u0013\u0018p\\*fe&\fG.\u001b>bi&|gn\u0015;sK\u0006l'B\u0001\u0007\u000e\u0003)\u0019XM]5bY&TXM\u001d\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sON\u0011\u0001\u0001\u0006\t\u0003+Yi\u0011aC\u0005\u0003/-\u00111cU3sS\u0006d\u0017N_1uS>t7\u000b\u001e:fC6\f1b]3s\u0013:\u001cH/\u00198dK\u000e\u0001\u0001CA\u000b\u001c\u0013\ta2B\u0001\fLef|7+\u001a:jC2L'0\u001a:J]N$\u0018M\\2f\u0003%yW\u000f^*ue\u0016\fW\u000e\u0005\u0002 I5\t\u0001E\u0003\u0002\"E\u0005\u0011\u0011n\u001c\u0006\u0002G\u0005!!.\u0019<b\u0013\t)\u0003E\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW.A\u0005vg\u0016,fn]1gKB\u0011\u0001fK\u0007\u0002S)\t!&A\u0003tG\u0006d\u0017-\u0003\u0002-S\t9!i\\8mK\u0006t\u0017A\u0002\u001fj]&$h\b\u0006\u00030aE\u0012\u0004CA\u000b\u0001\u0011\u0015AB\u00011\u0001\u001b\u0011\u0015iB\u00011\u0001\u001f\u0011\u00151C\u00011\u0001(\u0003\u0019yW\u000f\u001e9viB\u0011Q'P\u0007\u0002m)\u0011\u0011e\u000e\u0006\u0003qe\nAa\u001b:z_*\u0011!hO\u0001\u0011KN|G/\u001a:jGN|g\r^<be\u0016T\u0011\u0001P\u0001\u0004G>l\u0017B\u0001 7\u0005\u0019yU\u000f\u001e9viB\u0011\u0001)Q\u0007\u0002o%\u0011!i\u000e\u0002\u0005\u0017JLx.A\u0006xe&$Xm\u00142kK\u000e$XCA#R)\t1%\f\u0006\u0002\u0015\u000f\"9\u0001jBA\u0001\u0002\bI\u0015AC3wS\u0012,gnY3%cA\u0019!*T(\u000e\u0003-S!\u0001T\u0015\u0002\u000fI,g\r\\3di&\u0011aj\u0013\u0002\t\u00072\f7o\u001d+bOB\u0011\u0001+\u0015\u0007\u0001\t\u0015\u0011vA1\u0001T\u0005\u0005!\u0016C\u0001+X!\tAS+\u0003\u0002WS\t9aj\u001c;iS:<\u0007C\u0001\u0015Y\u0013\tI\u0016FA\u0002B]fDQaW\u0004A\u0002=\u000b\u0011\u0001^\u0001\u0006M2,8\u000f\u001b\u000b\u0002=B\u0011\u0001fX\u0005\u0003A&\u0012A!\u00168ji\u0006)1\r\\8tK\u0002"
)
public class KryoSerializationStream extends SerializationStream {
   private final KryoSerializerInstance serInstance;
   private Output output;
   private Kryo kryo;

   public SerializationStream writeObject(final Object t, final ClassTag evidence$1) {
      this.kryo.writeClassAndObject(this.output, t);
      return this;
   }

   public void flush() {
      if (this.output == null) {
         throw new IOException("Stream is closed");
      } else {
         this.output.flush();
      }
   }

   public void close() {
      if (this.output != null) {
         try {
            this.output.close();
         } finally {
            this.serInstance.releaseKryo(this.kryo);
            this.kryo = null;
            this.output = null;
         }

      }
   }

   public KryoSerializationStream(final KryoSerializerInstance serInstance, final OutputStream outStream, final boolean useUnsafe) {
      this.serInstance = serInstance;
      this.output = (Output)(useUnsafe ? new UnsafeOutput(outStream) : new Output(outStream));
      this.kryo = serInstance.borrowKryo();
   }
}
