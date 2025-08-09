package scala.reflect.api;

import java.io.ObjectStreamException;
import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593Q!\u0003\u0006\u0001\u001dAA\u0001\u0002\b\u0001\u0003\u0002\u0004%\tA\b\u0005\tG\u0001\u0011\t\u0019!C\u0001I!A!\u0006\u0001B\u0001B\u0003&q\u0004\u0003\u0005,\u0001\t\u0005\r\u0011\"\u0001-\u0011!\u0001\u0004A!a\u0001\n\u0003\t\u0004\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0015B\u0017\t\u000bQ\u0002A\u0011A\u001b\t\u000be\u0002A\u0011\u0002\u001e\u0003#M+'/[1mSj,G\rV=qKR\u000bwM\u0003\u0002\f\u0019\u0005\u0019\u0011\r]5\u000b\u00055q\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u001f\u0005)1oY1mCN\u0019\u0001!E\u000b\u0011\u0005I\u0019R\"\u0001\b\n\u0005Qq!AB!osJ+g\r\u0005\u0002\u001739\u0011!cF\u0005\u000319\tq\u0001]1dW\u0006<W-\u0003\u0002\u001b7\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001DD\u0001\u0005iB,7m\u0001\u0001\u0016\u0003}\u0001\"\u0001I\u0011\u000e\u0003)I!A\t\u0006\u0003\u0017QK\b/Z\"sK\u0006$xN]\u0001\tiB,7m\u0018\u0013fcR\u0011Q\u0005\u000b\t\u0003%\u0019J!a\n\b\u0003\tUs\u0017\u000e\u001e\u0005\bS\t\t\t\u00111\u0001 \u0003\rAH%M\u0001\u0006iB,7\rI\u0001\tG>t7M]3uKV\tQ\u0006\u0005\u0002\u0013]%\u0011qF\u0004\u0002\b\u0005>|G.Z1o\u00031\u0019wN\\2sKR,w\fJ3r)\t)#\u0007C\u0004*\u000b\u0005\u0005\t\u0019A\u0017\u0002\u0013\r|gn\u0019:fi\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u00027oa\u0002\"\u0001\t\u0001\t\u000bq9\u0001\u0019A\u0010\t\u000b-:\u0001\u0019A\u0017\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0002#!\u001a\u0001\u0002P$\u0011\u0007Iit(\u0003\u0002?\u001d\t1A\u000f\u001b:poN\u0004\"\u0001Q#\u000e\u0003\u0005S!AQ\"\u0002\u0005%|'\"\u0001#\u0002\t)\fg/Y\u0005\u0003\r\u0006\u0013Qc\u00142kK\u000e$8\u000b\u001e:fC6,\u0005pY3qi&|gnI\u0001@Q\u0011\u0001\u0011\nT'\u0011\u0005IQ\u0015BA&\u000f\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0001"
)
public class SerializedTypeTag implements Serializable {
   private static final long serialVersionUID = 1L;
   private TypeCreator tpec;
   private boolean concrete;

   public TypeCreator tpec() {
      return this.tpec;
   }

   public void tpec_$eq(final TypeCreator x$1) {
      this.tpec = x$1;
   }

   public boolean concrete() {
      return this.concrete;
   }

   public void concrete_$eq(final boolean x$1) {
      this.concrete = x$1;
   }

   private Object readResolve() throws ObjectStreamException {
      ClassLoader var10000;
      try {
         var10000 = Thread.currentThread().getContextClassLoader();
      } catch (SecurityException var3) {
         var10000 = null;
      }

      ClassLoader loader = var10000;
      JavaUniverse.JavaMirror m = scala.reflect.runtime.package$.MODULE$.universe().runtimeMirror(loader);
      return this.concrete() ? ((TypeTags)scala.reflect.runtime.package$.MODULE$.universe()).TypeTag().apply((Mirror)m, this.tpec()) : ((TypeTags)scala.reflect.runtime.package$.MODULE$.universe()).WeakTypeTag().apply((Mirror)m, this.tpec());
   }

   public SerializedTypeTag(final TypeCreator tpec, final boolean concrete) {
      this.tpec = tpec;
      this.concrete = concrete;
      super();
   }
}
