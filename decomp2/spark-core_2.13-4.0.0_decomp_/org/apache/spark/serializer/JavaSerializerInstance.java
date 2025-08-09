package org.apache.spark.serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.spark.util.ByteBufferInputStream;
import org.apache.spark.util.ByteBufferOutputStream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a!B\u0006\r\u00019!\u0002\u0002C\r\u0001\u0005\u0003\u0005\u000b\u0011B\u000e\t\u0011\u0005\u0002!\u0011!Q\u0001\n\tB\u0001\"\n\u0001\u0003\u0002\u0003\u0006IA\n\u0005\u0006]\u0001!\ta\f\u0005\u0006i\u0001!\t%\u000e\u0005\u0006'\u0002!\t\u0005\u0016\u0005\u0006'\u0002!\tE\u0018\u0005\u0006S\u0002!\tE\u001b\u0005\u0006m\u0002!\te\u001e\u0005\u0006m\u0002!\ta \u0002\u0017\u0015\u00064\u0018mU3sS\u0006d\u0017N_3s\u0013:\u001cH/\u00198dK*\u0011QBD\u0001\u000bg\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0014\u0005\u0001)\u0002C\u0001\f\u0018\u001b\u0005a\u0011B\u0001\r\r\u0005I\u0019VM]5bY&TXM]%ogR\fgnY3\u0002\u0019\r|WO\u001c;feJ+7/\u001a;\u0004\u0001A\u0011AdH\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\t\u0019\u0011J\u001c;\u0002\u001d\u0015DHO]1EK\n,x-\u00138g_B\u0011AdI\u0005\u0003Iu\u0011qAQ8pY\u0016\fg.\u0001\neK\u001a\fW\u000f\u001c;DY\u0006\u001c8\u000fT8bI\u0016\u0014\bCA\u0014-\u001b\u0005A#BA\u0015+\u0003\u0011a\u0017M\\4\u000b\u0003-\nAA[1wC&\u0011Q\u0006\u000b\u0002\f\u00072\f7o\u001d'pC\u0012,'/\u0001\u0004=S:LGO\u0010\u000b\u0005aE\u00124\u0007\u0005\u0002\u0017\u0001!)\u0011\u0004\u0002a\u00017!)\u0011\u0005\u0002a\u0001E!)Q\u0005\u0002a\u0001M\u0005I1/\u001a:jC2L'0Z\u000b\u0003m!#\"aN)\u0015\u0005ar\u0004CA\u001d=\u001b\u0005Q$BA\u001e+\u0003\rq\u0017n\\\u0005\u0003{i\u0012!BQ=uK\n+hMZ3s\u0011\u001dyT!!AA\u0004\u0001\u000b!\"\u001a<jI\u0016t7-\u001a\u00134!\r\tEIR\u0007\u0002\u0005*\u00111)H\u0001\be\u00164G.Z2u\u0013\t)%I\u0001\u0005DY\u0006\u001c8\u000fV1h!\t9\u0005\n\u0004\u0001\u0005\u000b%+!\u0019\u0001&\u0003\u0003Q\u000b\"a\u0013(\u0011\u0005qa\u0015BA'\u001e\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001H(\n\u0005Ak\"aA!os\")!+\u0002a\u0001\r\u0006\tA/A\u0006eKN,'/[1mSj,WCA+Y)\t1F\f\u0006\u0002X3B\u0011q\t\u0017\u0003\u0006\u0013\u001a\u0011\rA\u0013\u0005\b5\u001a\t\t\u0011q\u0001\\\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0004\u0003\u0012;\u0006\"B/\u0007\u0001\u0004A\u0014!\u00022zi\u0016\u001cXCA0c)\r\u0001gm\u001a\u000b\u0003C\u000e\u0004\"a\u00122\u0005\u000b%;!\u0019\u0001&\t\u000f\u0011<\u0011\u0011!a\u0002K\u0006QQM^5eK:\u001cW\rJ\u001b\u0011\u0007\u0005#\u0015\rC\u0003^\u000f\u0001\u0007\u0001\bC\u0003i\u000f\u0001\u0007a%\u0001\u0004m_\u0006$WM]\u0001\u0010g\u0016\u0014\u0018.\u00197ju\u0016\u001cFO]3b[R\u00111N\u001c\t\u0003-1L!!\u001c\u0007\u0003'M+'/[1mSj\fG/[8o'R\u0014X-Y7\t\u000b=D\u0001\u0019\u00019\u0002\u0003M\u0004\"!\u001d;\u000e\u0003IT!a\u001d\u0016\u0002\u0005%|\u0017BA;s\u00051yU\u000f\u001e9viN#(/Z1n\u0003E!Wm]3sS\u0006d\u0017N_3TiJ,\u0017-\u001c\u000b\u0003qn\u0004\"AF=\n\u0005id!!\u0006#fg\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8TiJ,\u0017-\u001c\u0005\u0006_&\u0001\r\u0001 \t\u0003cvL!A :\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u0006q\u0006\u0005\u00111\u0001\u0005\u0006_*\u0001\r\u0001 \u0005\u0006Q*\u0001\rA\n"
)
public class JavaSerializerInstance extends SerializerInstance {
   private final int counterReset;
   private final boolean extraDebugInfo;
   private final ClassLoader defaultClassLoader;

   public ByteBuffer serialize(final Object t, final ClassTag evidence$3) {
      ByteBufferOutputStream bos = new ByteBufferOutputStream();
      SerializationStream out = this.serializeStream(bos);
      out.writeObject(t, evidence$3);
      out.close();
      return bos.toByteBuffer();
   }

   public Object deserialize(final ByteBuffer bytes, final ClassTag evidence$4) {
      ByteBufferInputStream bis = new ByteBufferInputStream(bytes);
      DeserializationStream in = this.deserializeStream(bis);
      return in.readObject(evidence$4);
   }

   public Object deserialize(final ByteBuffer bytes, final ClassLoader loader, final ClassTag evidence$5) {
      ByteBufferInputStream bis = new ByteBufferInputStream(bytes);
      DeserializationStream in = this.deserializeStream(bis, loader);
      return in.readObject(evidence$5);
   }

   public SerializationStream serializeStream(final OutputStream s) {
      return new JavaSerializationStream(s, this.counterReset, this.extraDebugInfo);
   }

   public DeserializationStream deserializeStream(final InputStream s) {
      return new JavaDeserializationStream(s, this.defaultClassLoader);
   }

   public DeserializationStream deserializeStream(final InputStream s, final ClassLoader loader) {
      return new JavaDeserializationStream(s, loader);
   }

   public JavaSerializerInstance(final int counterReset, final boolean extraDebugInfo, final ClassLoader defaultClassLoader) {
      this.counterReset = counterReset;
      this.extraDebugInfo = extraDebugInfo;
      this.defaultClassLoader = defaultClassLoader;
   }
}
