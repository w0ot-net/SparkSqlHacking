package org.json4s.reflect;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.json4s.MappingException;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001\u0002\n\u0014\u0001iA\u0001\"\t\u0001\u0003\u0006\u0004%\tA\t\u0005\tY\u0001\u0011\t\u0011)A\u0005G!AQ\u0006\u0001BC\u0002\u0013\u0005a\u0006\u0003\u00058\u0001\t\u0005\t\u0015!\u00030\u0011!y\u0004A!A!\u0002\u0013\u0001\u0005\"B\"\u0001\t\u0013!\u0005\"B\"\u0001\t\u0003q\u0005\"B\"\u0001\t\u0003\u0001\u0006\"\u0002-\u0001\t\u0003I\u0006\"\u00025\u0001\t\u0003I\u0007\"B7\u0001\t\u0003q\u0007\"B;\u0001\t\u00031\bBB@\u0001\t\u0003\t\t\u0001C\u0004\u0002\u000e\u0001!\t!a\u0004\t\u000f\u0005M\u0002\u0001\"\u0001\u00026!9\u0011Q\b\u0001\u0005\u0002\u0005}\u0002bBA!\u0001\u0011\u0005\u00131\t\u0002\u000b\u000bb,7-\u001e;bE2,'B\u0001\u000b\u0016\u0003\u001d\u0011XM\u001a7fGRT!AF\f\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005A\u0012aA8sO\u000e\u00011C\u0001\u0001\u001c!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fM\u00061Q.\u001a;i_\u0012,\u0012a\t\t\u0003I)j\u0011!\n\u0006\u0003)\u0019R!a\n\u0015\u0002\t1\fgn\u001a\u0006\u0002S\u0005!!.\u0019<b\u0013\tYSE\u0001\u0004NKRDw\u000eZ\u0001\b[\u0016$\bn\u001c3!\u0003-\u0019wN\\:ueV\u001cGo\u001c:\u0016\u0003=\u0002$\u0001M\u001b\u0011\u0007\u0011\n4'\u0003\u00023K\tY1i\u001c8tiJ,8\r^8s!\t!T\u0007\u0004\u0001\u0005\u0013Y\"\u0011\u0011!A\u0001\u0006\u0003A$aA0%c\u0005a1m\u001c8tiJ,8\r^8sAE\u0011\u0011\b\u0010\t\u00039iJ!aO\u000f\u0003\u000f9{G\u000f[5oOB\u0011A$P\u0005\u0003}u\u00111!\u00118z\u00035I7\u000f\u0015:j[\u0006\u0014\u0018p\u0011;peB\u0011A$Q\u0005\u0003\u0005v\u0011qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0005\u000b\u001eCU\n\u0005\u0002G\u00015\t1\u0003C\u0003\"\r\u0001\u00071\u0005C\u0003.\r\u0001\u0007\u0011\n\r\u0002K\u0019B\u0019A%M&\u0011\u0005QbE!\u0003\u001cI\u0003\u0003\u0005\tQ!\u00019\u0011\u0015yd\u00011\u0001A)\t)u\nC\u0003\"\u000f\u0001\u00071\u0005F\u0002F#^CQ!\f\u0005A\u0002I\u0003$aU+\u0011\u0007\u0011\nD\u000b\u0005\u00025+\u0012Ia+UA\u0001\u0002\u0003\u0015\t\u0001\u000f\u0002\u0004?\u0012\u0012\u0004\"B \t\u0001\u0004\u0001\u0015a\u00053fM\u0006,H\u000e\u001e,bYV,\u0007+\u0019;uKJtW#\u0001.\u0011\u0007qYV,\u0003\u0002];\t1q\n\u001d;j_:\u0004\"AX3\u000f\u0005}\u001b\u0007C\u00011\u001e\u001b\u0005\t'B\u00012\u001a\u0003\u0019a$o\\8u}%\u0011A-H\u0001\u0007!J,G-\u001a4\n\u0005\u0019<'AB*ue&twM\u0003\u0002e;\u0005aq-\u001a;N_\u0012Lg-[3sgR\t!\u000e\u0005\u0002\u001dW&\u0011A.\b\u0002\u0004\u0013:$\u0018\u0001G4fi\u001e+g.\u001a:jGB\u000b'/Y7fi\u0016\u0014H+\u001f9fgR\tq\u000eE\u0002\u001daJL!!]\u000f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0011\u001a\u0018B\u0001;&\u0005\u0011!\u0016\u0010]3\u0002#\u001d,G\u000fU1sC6,G/\u001a:UsB,7\u000fF\u0001x!\ra\u0002\u000f\u001f\u0019\u0003sv\u00042A\u0018>}\u0013\tYxMA\u0003DY\u0006\u001c8\u000f\u0005\u00025{\u0012Ia\u0010DA\u0001\u0002\u0003\u0015\t\u0001\u000f\u0002\u0004?\u0012\u001a\u0014!E4fi\u0012+7\r\\1sS:<7\t\\1tgR\u0011\u00111\u0001\u0019\u0005\u0003\u000b\tI\u0001\u0005\u0003_u\u0006\u001d\u0001c\u0001\u001b\u0002\n\u0011Q\u00111B\u0007\u0002\u0002\u0003\u0005)\u0011\u0001\u001d\u0003\u0007}#C'\u0001\u0004j]Z|7.\u001a\u000b\u0006y\u0005E\u0011Q\u0004\u0005\b\u0003'q\u0001\u0019AA\u000b\u0003%\u0019w.\u001c9b]&|g\u000e\u0005\u0003\u001d7\u0006]\u0001c\u0001$\u0002\u001a%\u0019\u00111D\n\u0003'MKgn\u001a7fi>tG)Z:de&\u0004Ho\u001c:\t\u000f\u0005}a\u00021\u0001\u0002\"\u0005!\u0011M]4t!\u0015\t\u0019#!\f=\u001d\u0011\t)#!\u000b\u000f\u0007\u0001\f9#C\u0001\u001f\u0013\r\tY#H\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ty#!\r\u0003\u0007M+\u0017OC\u0002\u0002,u\tQcZ3u\u0003N\f5mY3tg&\u0014G.Z(cU\u0016\u001cG/\u0006\u0002\u00028A\u0019A%!\u000f\n\u0007\u0005mRE\u0001\tBG\u000e,7o]5cY\u0016|%M[3di\u0006\u0011r-\u001a;NCJ\\W\rZ!t!JLW.\u0019:z)\u0005\u0001\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u0015\u0003\u0003BA$\u0003\u0013j\u0011AJ\u0005\u0003M\u001a\u0002"
)
public class Executable {
   private final Method method;
   private final Constructor constructor;
   private final boolean isPrimaryCtor;

   public Method method() {
      return this.method;
   }

   public Constructor constructor() {
      return this.constructor;
   }

   public Option defaultValuePattern() {
      return (Option)(this.isPrimaryCtor ? new Some(package$.MODULE$.ConstructorDefaultValuePattern()) : .MODULE$);
   }

   public int getModifiers() {
      return this.method() != null ? this.method().getModifiers() : this.constructor().getModifiers();
   }

   public Type[] getGenericParameterTypes() {
      return this.method() != null ? this.method().getGenericParameterTypes() : this.constructor().getGenericParameterTypes();
   }

   public Class[] getParameterTypes() {
      return this.method() != null ? this.method().getParameterTypes() : this.constructor().getParameterTypes();
   }

   public Class getDeclaringClass() {
      return this.method() != null ? this.method().getDeclaringClass() : this.constructor().getDeclaringClass();
   }

   public Object invoke(final Option companion, final Seq args) {
      Object var10000;
      if (this.method() != null) {
         if (!(companion instanceof Some)) {
            if (.MODULE$.equals(companion)) {
               throw new MappingException("Trying to call apply method, but the companion object was not found.");
            }

            throw new MatchError(companion);
         }

         Some var5 = (Some)companion;
         SingletonDescriptor cmp = (SingletonDescriptor)var5.value();
         Object var3 = this.method().invoke(cmp.instance(), ((IterableOnceOps)args.map((x$1) -> x$1)).toArray(scala.reflect.ClassTag..MODULE$.AnyRef()));
         var10000 = var3;
      } else {
         var10000 = this.constructor().newInstance(((IterableOnceOps)args.map((x$2) -> x$2)).toArray(scala.reflect.ClassTag..MODULE$.AnyRef()));
      }

      return var10000;
   }

   public AccessibleObject getAsAccessibleObject() {
      return (AccessibleObject)(this.method() != null ? this.method() : this.constructor());
   }

   public boolean getMarkedAsPrimary() {
      boolean markedByAnnotation = this.method() == null ? this.constructor().isAnnotationPresent(PrimaryConstructor.class) : false;
      return markedByAnnotation || this.isPrimaryCtor;
   }

   public String toString() {
      return this.method() != null ? (new StringBuilder(20)).append("Executable(Method(").append(this.method()).append("))").toString() : (new StringBuilder(25)).append("Executable(Constructor(").append(this.constructor()).append("))").toString();
   }

   private Executable(final Method method, final Constructor constructor, final boolean isPrimaryCtor) {
      this.method = method;
      this.constructor = constructor;
      this.isPrimaryCtor = isPrimaryCtor;
   }

   public Executable(final Method method) {
      this(method, (Constructor)null, false);
   }

   public Executable(final Constructor constructor, final boolean isPrimaryCtor) {
      this((Method)null, constructor, isPrimaryCtor);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
