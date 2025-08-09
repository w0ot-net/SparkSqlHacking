package org.apache.spark.serializer;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Private;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005=3Q\u0001C\u0005\u0002\u0002IAQ!\u0007\u0001\u0005\u0002iAq!\b\u0001A\u0002\u0013Ea\u0004C\u0004+\u0001\u0001\u0007I\u0011C\u0016\t\rE\u0002\u0001\u0015)\u0003 \u0011\u00151\u0004\u0001\"\u00018\u0011\u0015Q\u0004A\"\u0001<\u0011\u0019y\u0004\u0001\"\u0001\f\u0001\nQ1+\u001a:jC2L'0\u001a:\u000b\u0005)Y\u0011AC:fe&\fG.\u001b>fe*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u00027A\u0011A\u0004A\u0007\u0002\u0013\u0005\u0011B-\u001a4bk2$8\t\\1tg2{\u0017\rZ3s+\u0005y\u0002c\u0001\u000b!E%\u0011\u0011%\u0006\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\rBS\"\u0001\u0013\u000b\u0005\u00152\u0013\u0001\u00027b]\u001eT\u0011aJ\u0001\u0005U\u00064\u0018-\u0003\u0002*I\tY1\t\\1tg2{\u0017\rZ3s\u0003Y!WMZ1vYR\u001cE.Y:t\u0019>\fG-\u001a:`I\u0015\fHC\u0001\u00170!\t!R&\u0003\u0002/+\t!QK\\5u\u0011\u001d\u00014!!AA\u0002}\t1\u0001\u001f\u00132\u0003M!WMZ1vYR\u001cE.Y:t\u0019>\fG-\u001a:!Q\t!1\u0007\u0005\u0002\u0015i%\u0011Q'\u0006\u0002\tm>d\u0017\r^5mK\u0006)2/\u001a;EK\u001a\fW\u000f\u001c;DY\u0006\u001c8\u000fT8bI\u0016\u0014HCA\u000e9\u0011\u0015IT\u00011\u0001#\u0003-\u0019G.Y:t\u0019>\fG-\u001a:\u0002\u00179,w/\u00138ti\u0006t7-\u001a\u000b\u0002yA\u0011A$P\u0005\u0003}%\u0011!cU3sS\u0006d\u0017N_3s\u0013:\u001cH/\u00198dK\u0006)3/\u001e9q_J$8OU3m_\u000e\fG/[8o\u001f\u001a\u001cVM]5bY&TX\rZ(cU\u0016\u001cGo]\u000b\u0002\u0003B\u0011ACQ\u0005\u0003\u0007V\u0011qAQ8pY\u0016\fg\u000e\u000b\u0002\b\u000bB\u0011a)S\u0007\u0002\u000f*\u0011\u0001jC\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001&H\u0005\u001d\u0001&/\u001b<bi\u0016D#\u0001\u0001'\u0011\u0005\u0019k\u0015B\u0001(H\u00051!UM^3m_B,'/\u00119j\u0001"
)
public abstract class Serializer {
   private volatile Option defaultClassLoader;

   public Option defaultClassLoader() {
      return this.defaultClassLoader;
   }

   public void defaultClassLoader_$eq(final Option x$1) {
      this.defaultClassLoader = x$1;
   }

   public Serializer setDefaultClassLoader(final ClassLoader classLoader) {
      this.defaultClassLoader_$eq(new Some(classLoader));
      return this;
   }

   public abstract SerializerInstance newInstance();

   @Private
   public boolean supportsRelocationOfSerializedObjects() {
      return false;
   }

   public Serializer() {
      this.defaultClassLoader = .MODULE$;
   }
}
