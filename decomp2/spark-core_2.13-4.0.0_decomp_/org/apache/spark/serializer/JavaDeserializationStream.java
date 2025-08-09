package org.apache.spark.serializer;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Proxy;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4Q!\u0004\b\u0001!YA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\tK\u0001\u0011\t\u0011)A\u0005M!)A\u0006\u0001C\u0001[!9\u0011\u0007\u0001b\u0001\n\u0013\u0011\u0004B\u0002\u001c\u0001A\u0003%1\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003R\u0001\u0011\u0005!kB\u0003W\u001d!%qKB\u0003\u000e\u001d!%\u0001\fC\u0003-\u0013\u0011\u0005A\fC\u0004^\u0013\t\u0007I\u0011\u00010\t\rEL\u0001\u0015!\u0003`\u0005eQ\u0015M^1EKN,'/[1mSj\fG/[8o'R\u0014X-Y7\u000b\u0005=\u0001\u0012AC:fe&\fG.\u001b>fe*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0005\u0002\u0001/A\u0011\u0001$G\u0007\u0002\u001d%\u0011!D\u0004\u0002\u0016\t\u0016\u001cXM]5bY&T\u0018\r^5p]N#(/Z1n\u0003\tIgn\u0001\u0001\u0011\u0005y\u0019S\"A\u0010\u000b\u0005\u0001\n\u0013AA5p\u0015\u0005\u0011\u0013\u0001\u00026bm\u0006L!\u0001J\u0010\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\\\u0001\u0007Y>\fG-\u001a:\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0005%\n\u0013\u0001\u00027b]\u001eL!a\u000b\u0015\u0003\u0017\rc\u0017m]:M_\u0006$WM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00079z\u0003\u0007\u0005\u0002\u0019\u0001!)1d\u0001a\u0001;!)Qe\u0001a\u0001M\u0005)qN\u00196J]V\t1\u0007\u0005\u0002\u001fi%\u0011Qg\b\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0017AB8cU&s\u0007%\u0001\u0006sK\u0006$wJ\u00196fGR,\"!O\u001f\u0015\u0003i\"\"aO%\u0011\u0005qjD\u0002\u0001\u0003\u0006}\u0019\u0011\ra\u0010\u0002\u0002)F\u0011\u0001I\u0012\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0002\u0007\u0006)1oY1mC&\u0011QI\u0011\u0002\b\u001d>$\b.\u001b8h!\t\tu)\u0003\u0002I\u0005\n\u0019\u0011I\\=\t\u000f)3\u0011\u0011!a\u0002\u0017\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u00071{5(D\u0001N\u0015\tq%)A\u0004sK\u001adWm\u0019;\n\u0005Ak%\u0001C\"mCN\u001cH+Y4\u0002\u000b\rdwn]3\u0015\u0003M\u0003\"!\u0011+\n\u0005U\u0013%\u0001B+oSR\f\u0011DS1wC\u0012+7/\u001a:jC2L'0\u0019;j_:\u001cFO]3b[B\u0011\u0001$C\n\u0003\u0013e\u0003\"!\u0011.\n\u0005m\u0013%AB!osJ+g\rF\u0001X\u0003E\u0001(/[7ji&4X-T1qa&twm]\u000b\u0002?B!\u0001-Z4k\u001b\u0005\t'B\u00012d\u0003%IW.\\;uC\ndWM\u0003\u0002e\u0005\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0019\f'aA'baB\u0011q\u0005[\u0005\u0003S\"\u0012aa\u0015;sS:<\u0007GA6p!\r9CN\\\u0005\u0003[\"\u0012Qa\u00117bgN\u0004\"\u0001P8\u0005\u0013Ad\u0011\u0011!A\u0001\u0006\u0003y$aA0%g\u0005\u0011\u0002O]5nSRLg/Z'baBLgnZ:!\u0001"
)
public class JavaDeserializationStream extends DeserializationStream {
   public final InputStream org$apache$spark$serializer$JavaDeserializationStream$$in;
   public final ClassLoader org$apache$spark$serializer$JavaDeserializationStream$$loader;
   private final ObjectInputStream objIn;

   public static Map primitiveMappings() {
      return JavaDeserializationStream$.MODULE$.primitiveMappings();
   }

   private ObjectInputStream objIn() {
      return this.objIn;
   }

   public Object readObject(final ClassTag evidence$2) {
      return this.objIn().readObject();
   }

   public void close() {
      this.objIn().close();
   }

   public JavaDeserializationStream(final InputStream in, final ClassLoader loader) {
      this.org$apache$spark$serializer$JavaDeserializationStream$$in = in;
      this.org$apache$spark$serializer$JavaDeserializationStream$$loader = loader;
      this.objIn = new ObjectInputStream() {
         // $FF: synthetic field
         private final JavaDeserializationStream $outer;

         public Class resolveClass(final ObjectStreamClass desc) {
            Class var10000;
            try {
               var10000 = Class.forName(desc.getName(), false, this.$outer.org$apache$spark$serializer$JavaDeserializationStream$$loader);
            } catch (ClassNotFoundException var3) {
               var10000 = (Class)JavaDeserializationStream$.MODULE$.primitiveMappings().getOrElse(desc.getName(), () -> {
                  throw var3;
               });
            }

            return var10000;
         }

         public Class resolveProxyClass(final String[] ifaces) {
            Class[] resolved = (Class[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])ifaces), (iface) -> Class.forName(iface, false, this.$outer.org$apache$spark$serializer$JavaDeserializationStream$$loader), scala.reflect.ClassTag..MODULE$.apply(Class.class));
            return Proxy.newProxyInstance(this.$outer.org$apache$spark$serializer$JavaDeserializationStream$$loader, resolved, DummyInvocationHandler$.MODULE$).getClass();
         }

         public {
            if (JavaDeserializationStream.this == null) {
               throw null;
            } else {
               this.$outer = JavaDeserializationStream.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }
}
