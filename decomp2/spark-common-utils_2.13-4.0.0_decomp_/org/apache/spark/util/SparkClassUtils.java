package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001C\t\u0013!\u0003\r\t\u0001\u0006\u000e\t\u000b\u0005\u0002A\u0011A\u0012\t\u000f\u001d\u0002!\u0019!C\u0001Q!)\u0001\u0007\u0001C\u0001c!)\u0001\b\u0001C\u0001c!)\u0011\b\u0001C\u0001u!9a\fAI\u0001\n\u0003y\u0006b\u00027\u0001#\u0003%\t!\u001c\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006{\u0002!\tA \u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\t)\u0002\u0001C\u0005\u0003/Aq!!\b\u0001\t\u000b\tyb\u0002\u0005\u00020IA\t\u0001FA\u0019\r\u001d\t\"\u0003#\u0001\u0015\u0003kAq!!\u000f\u0010\t\u0003\tYDA\bTa\u0006\u00148n\u00117bgN,F/\u001b7t\u0015\t\u0019B#\u0001\u0003vi&d'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0014\u0005\u0001Y\u0002C\u0001\u000f \u001b\u0005i\"\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\"AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005!\u0003C\u0001\u000f&\u0013\t1SD\u0001\u0003V]&$\u0018A\u0002:b]\u0012|W.F\u0001*!\tQc&D\u0001,\u0015\t\u0019BFC\u0001.\u0003\u0011Q\u0017M^1\n\u0005=Z#A\u0002*b]\u0012|W.A\nhKR\u001c\u0006/\u0019:l\u00072\f7o\u001d'pC\u0012,'/F\u00013!\t\u0019d'D\u00015\u0015\t)D&\u0001\u0003mC:<\u0017BA\u001c5\u0005-\u0019E.Y:t\u0019>\fG-\u001a:\u00029\u001d,GoQ8oi\u0016DHo\u0014:Ta\u0006\u00148n\u00117bgNdu.\u00193fe\u0006a1\r\\1tg\u001a{'OT1nKV\u00111(\u0013\u000b\u0005yI;F\fE\u0002>\t\u001es!A\u0010\"\u0011\u0005}jR\"\u0001!\u000b\u0005\u0005\u0013\u0013A\u0002\u001fs_>$h(\u0003\u0002D;\u00051\u0001K]3eK\u001aL!!\u0012$\u0003\u000b\rc\u0017m]:\u000b\u0005\rk\u0002C\u0001%J\u0019\u0001!QAS\u0003C\u0002-\u0013\u0011aQ\t\u0003\u0019>\u0003\"\u0001H'\n\u00059k\"a\u0002(pi\"Lgn\u001a\t\u00039AK!!U\u000f\u0003\u0007\u0005s\u0017\u0010C\u0003T\u000b\u0001\u0007A+A\u0005dY\u0006\u001c8OT1nKB\u0011Q(V\u0005\u0003-\u001a\u0013aa\u0015;sS:<\u0007b\u0002-\u0006!\u0003\u0005\r!W\u0001\u000bS:LG/[1mSj,\u0007C\u0001\u000f[\u0013\tYVDA\u0004C_>dW-\u00198\t\u000fu+\u0001\u0013!a\u00013\u0006\u0011bn\\*qCJ\\7\t\\1tg2{\u0017\rZ3s\u0003Y\u0019G.Y:t\r>\u0014h*Y7fI\u0011,g-Y;mi\u0012\u0012TC\u00011l+\u0005\t'FA-cW\u0005\u0019\u0007C\u00013j\u001b\u0005)'B\u00014h\u0003%)hn\u00195fG.,GM\u0003\u0002i;\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005),'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)!J\u0002b\u0001\u0017\u000612\r\\1tg\u001a{'OT1nK\u0012\"WMZ1vYR$3'\u0006\u0002a]\u0012)!j\u0002b\u0001\u0017\u0006y1\r\\1tg&\u001bHj\\1eC\ndW\r\u0006\u0002Zc\")!\u000f\u0003a\u0001)\u0006)1\r\\1{u\u0006\u00013\r\\1tg&\u001bHj\\1eC\ndW-\u00118e\u0003N\u001c\u0018n\u001a8bE2,gI]8n)\rIVO\u001e\u0005\u0006e&\u0001\r\u0001\u0016\u0005\u0006o&\u0001\r\u0001_\u0001\fi\u0006\u0014x-\u001a;DY\u0006\u001c8\u000f\r\u0002zwB\u0019Q\b\u0012>\u0011\u0005![H!\u0003?w\u0003\u0003\u0005\tQ!\u0001L\u0005\ryF%M\u0001\u0016O\u0016$hi\u001c:nCR$X\rZ\"mCN\u001ch*Y7f)\t!v\u0010\u0003\u0004\u0002\u0002)\u0001\raG\u0001\u0004_\nT\u0017!D4fiNKW\u000e\u001d7f\u001d\u0006lW\rF\u0002U\u0003\u000fAq!!\u0003\f\u0001\u0004\tY!A\u0002dYN\u0004D!!\u0004\u0002\u0012A!Q\bRA\b!\rA\u0015\u0011\u0003\u0003\f\u0003'\t9!!A\u0001\u0002\u000b\u00051JA\u0002`II\nQb\u001d;sSB\u0004\u0016mY6bO\u0016\u001cHc\u0001+\u0002\u001a!1\u00111\u0004\u0007A\u0002Q\u000b!CZ;mYf\fV/\u00197jM&,GMT1nK\u0006a1\u000f\u001e:ja\u0012{G\u000e\\1sgR\u0019A+!\t\t\r\u0005\rR\u00021\u0001U\u0003\u0005\u0019\bfA\u0007\u0002(A!\u0011\u0011FA\u0016\u001b\u00059\u0017bAA\u0017O\n9A/Y5me\u0016\u001c\u0017aD*qCJ\\7\t\\1tgV#\u0018\u000e\\:\u0011\u0007\u0005Mr\"D\u0001\u0013'\u0011y1$a\u000e\u0011\u0007\u0005M\u0002!\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003c\u0001"
)
public interface SparkClassUtils {
   void org$apache$spark$util$SparkClassUtils$_setter_$random_$eq(final Random x$1);

   Random random();

   // $FF: synthetic method
   static ClassLoader getSparkClassLoader$(final SparkClassUtils $this) {
      return $this.getSparkClassLoader();
   }

   default ClassLoader getSparkClassLoader() {
      return this.getClass().getClassLoader();
   }

   // $FF: synthetic method
   static ClassLoader getContextOrSparkClassLoader$(final SparkClassUtils $this) {
      return $this.getContextOrSparkClassLoader();
   }

   default ClassLoader getContextOrSparkClassLoader() {
      return (ClassLoader).MODULE$.apply(Thread.currentThread().getContextClassLoader()).getOrElse(() -> this.getSparkClassLoader());
   }

   // $FF: synthetic method
   static Class classForName$(final SparkClassUtils $this, final String className, final boolean initialize, final boolean noSparkClassLoader) {
      return $this.classForName(className, initialize, noSparkClassLoader);
   }

   default Class classForName(final String className, final boolean initialize, final boolean noSparkClassLoader) {
      return !noSparkClassLoader ? Class.forName(className, initialize, this.getContextOrSparkClassLoader()) : Class.forName(className, initialize, Thread.currentThread().getContextClassLoader());
   }

   // $FF: synthetic method
   static boolean classForName$default$2$(final SparkClassUtils $this) {
      return $this.classForName$default$2();
   }

   default boolean classForName$default$2() {
      return true;
   }

   // $FF: synthetic method
   static boolean classForName$default$3$(final SparkClassUtils $this) {
      return $this.classForName$default$3();
   }

   default boolean classForName$default$3() {
      return false;
   }

   // $FF: synthetic method
   static boolean classIsLoadable$(final SparkClassUtils $this, final String clazz) {
      return $this.classIsLoadable(clazz);
   }

   default boolean classIsLoadable(final String clazz) {
      return scala.util.Try..MODULE$.apply(() -> this.classForName(clazz, false, this.classForName$default$3())).isSuccess();
   }

   // $FF: synthetic method
   static boolean classIsLoadableAndAssignableFrom$(final SparkClassUtils $this, final String clazz, final Class targetClass) {
      return $this.classIsLoadableAndAssignableFrom(clazz, targetClass);
   }

   default boolean classIsLoadableAndAssignableFrom(final String clazz, final Class targetClass) {
      return BoxesRunTime.unboxToBoolean(scala.util.Try..MODULE$.apply((JFunction0.mcZ.sp)() -> {
         Class cls = this.classForName(clazz, false, this.classForName$default$3());
         return targetClass == null || targetClass.isAssignableFrom(cls);
      }).getOrElse((JFunction0.mcZ.sp)() -> false));
   }

   // $FF: synthetic method
   static String getFormattedClassName$(final SparkClassUtils $this, final Object obj) {
      return $this.getFormattedClassName(obj);
   }

   default String getFormattedClassName(final Object obj) {
      return this.getSimpleName(obj.getClass()).replace("$", "");
   }

   // $FF: synthetic method
   static String getSimpleName$(final SparkClassUtils $this, final Class cls) {
      return $this.getSimpleName(cls);
   }

   default String getSimpleName(final Class cls) {
      String var10000;
      try {
         var10000 = cls.getSimpleName();
      } catch (InternalError var2) {
         var10000 = this.stripDollars(this.stripPackages(cls.getName()));
      }

      return var10000;
   }

   private String stripPackages(final String fullyQualifiedName) {
      return ((String[])scala.collection.ArrayOps..MODULE$.takeRight$extension(scala.Predef..MODULE$.refArrayOps((Object[])fullyQualifiedName.split("\\.")), 1))[0];
   }

   // $FF: synthetic method
   static String stripDollars$(final SparkClassUtils $this, final String s) {
      return $this.stripDollars(s);
   }

   default String stripDollars(final String s) {
      while(true) {
         int lastDollarIndex = s.lastIndexOf(36);
         if (lastDollarIndex < s.length() - 1) {
            if (lastDollarIndex != -1 && s.contains("$iw")) {
               return s.substring(lastDollarIndex + 1);
            }

            return s;
         }

         Option lastNonDollarChar = scala.Predef..MODULE$.wrapString(s).findLast((x$1) -> BoxesRunTime.boxToBoolean($anonfun$stripDollars$1(BoxesRunTime.unboxToChar(x$1))));
         if (scala.None..MODULE$.equals(lastNonDollarChar)) {
            return s;
         }

         if (!(lastNonDollarChar instanceof Some)) {
            throw new MatchError(lastNonDollarChar);
         }

         Some var7 = (Some)lastNonDollarChar;
         char c = BoxesRunTime.unboxToChar(var7.value());
         int lastNonDollarIndex = s.lastIndexOf(c);
         if (lastNonDollarIndex == -1) {
            return s;
         }

         s = s.substring(0, lastNonDollarIndex + 1);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$stripDollars$1(final char x$1) {
      return x$1 != '$';
   }

   static void $init$(final SparkClassUtils $this) {
      $this.org$apache$spark$util$SparkClassUtils$_setter_$random_$eq(new Random());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
