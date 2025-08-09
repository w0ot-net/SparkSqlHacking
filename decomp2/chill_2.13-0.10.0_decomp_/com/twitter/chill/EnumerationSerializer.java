package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import scala.Enumeration;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E4Aa\u0003\u0007\u0001'!)Q\u0005\u0001C\u0001M!9\u0001\u0006\u0001b\u0001\n\u0013I\u0003B\u0002\u001a\u0001A\u0003%!\u0006C\u00044\u0001\t\u0007I\u0011\u0002\u001b\t\rm\u0002\u0001\u0015!\u00036\u0011\u001da\u0004A1A\u0005\nuBaA\u0012\u0001!\u0002\u0013q\u0004\"B$\u0001\t\u0013A\u0005\"B&\u0001\t\u0003a\u0005\"\u0002/\u0001\t\u0003i&!F#ok6,'/\u0019;j_:\u001cVM]5bY&TXM\u001d\u0006\u0003\u001b9\tQa\u00195jY2T!a\u0004\t\u0002\u000fQ<\u0018\u000e\u001e;fe*\t\u0011#A\u0002d_6\u001c\u0001a\u0005\u0002\u0001)A\u0019Q#\u0007\u000f\u000f\u0005Y9R\"\u0001\u0007\n\u0005aa\u0011a\u00029bG.\fw-Z\u0005\u00035m\u00111bS*fe&\fG.\u001b>fe*\u0011\u0001\u0004\u0004\t\u0003;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u00111\"\u00128v[\u0016\u0014\u0018\r^5p]&\u0011A%\t\u0002\u0006-\u0006dW/Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u001d\u0002\"A\u0006\u0001\u0002\u0015\u0015tW/\\'fi\"|G-F\u0001+!\tY\u0003'D\u0001-\u0015\tic&\u0001\u0003mC:<'\"A\u0018\u0002\t)\fg/Y\u0005\u0003c1\u0012aa\u0015;sS:<\u0017aC3ok6lU\r\u001e5pI\u0002\n1b\\;uKJlU\r\u001e5pIV\tQ\u0007\u0005\u00027s5\tqG\u0003\u00029Y\u00059!/\u001a4mK\u000e$\u0018B\u0001\u001e8\u0005\u0019iU\r\u001e5pI\u0006aq.\u001e;fe6+G\u000f[8eA\u00059QM\\;n\u001b\u0006\u0004X#\u0001 \u0011\t}\"E$H\u0007\u0002\u0001*\u0011\u0011IQ\u0001\b[V$\u0018M\u00197f\u0015\t\u0019u$\u0001\u0006d_2dWm\u0019;j_:L!!\u0012!\u0003\u00075\u000b\u0007/\u0001\u0005f]VlW*\u00199!\u0003\u0019)g.^7PMR\u0011Q$\u0013\u0005\u0006\u0015\"\u0001\r\u0001H\u0001\u0002m\u0006)qO]5uKR!Q\nU+[!\tqb*\u0003\u0002P?\t!QK\\5u\u0011\u0015\t\u0016\u00021\u0001S\u0003\u0011Y7/\u001a:\u0011\u0005U\u0019\u0016B\u0001+\u001c\u0005\u0011Y%/_8\t\u000bYK\u0001\u0019A,\u0002\u0007=,H\u000f\u0005\u0002\u00161&\u0011\u0011l\u0007\u0002\u0007\u001fV$\b/\u001e;\t\u000bmK\u0001\u0019\u0001\u000f\u0002\u0007=\u0014'.\u0001\u0003sK\u0006$G\u0003\u0002\u000f_?\u0012DQ!\u0015\u0006A\u0002ICQ\u0001\u0019\u0006A\u0002\u0005\f!!\u001b8\u0011\u0005U\u0011\u0017BA2\u001c\u0005\u0015Ie\u000e];u\u0011\u0015)'\u00021\u0001g\u0003\r\u0019Gn\u001d\t\u0004O:dbB\u00015m!\tIw$D\u0001k\u0015\tY'#\u0001\u0004=e>|GOP\u0005\u0003[~\ta\u0001\u0015:fI\u00164\u0017BA8q\u0005\u0015\u0019E.Y:t\u0015\tiw\u0004"
)
public class EnumerationSerializer extends Serializer {
   private final String enumMethod = "scala$Enumeration$$outerEnum";
   private final Method outerMethod = Enumeration.Value.class.getMethod(this.enumMethod());
   private final Map enumMap;

   private String enumMethod() {
      return this.enumMethod;
   }

   private Method outerMethod() {
      return this.outerMethod;
   }

   private Map enumMap() {
      return this.enumMap;
   }

   private Enumeration enumOf(final Enumeration.Value v) {
      synchronized(this.enumMap()){}

      Enumeration var3;
      try {
         var3 = (Enumeration)this.enumMap().getOrElseUpdate(v, () -> (Enumeration)this.outerMethod().invoke(v));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   public void write(final Kryo kser, final Output out, final Enumeration.Value obj) {
      Enumeration var4 = this.enumOf(obj);
      kser.writeClassAndObject(out, var4);
      out.writeInt(obj.id());
   }

   public Enumeration.Value read(final Kryo kser, final Input in, final Class cls) {
      Enumeration var4 = (Enumeration)kser.readClassAndObject(in);
      return var4.apply(in.readInt());
   }

   public EnumerationSerializer() {
      this.enumMap = (Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
