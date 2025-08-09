package org.json4s.reflect;

import java.lang.invoke.SerializedLambda;
import org.json4s.DefaultFormats$;
import org.json4s.Formats;
import org.json4s.MappingException;
import scala.Some;
import scala.collection.immutable.List;
import scala.collection.immutable.Vector;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.java8.JFunction0;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final String ConstructorDefaultValuePattern = "$lessinit$greater$default$%d";
   private static final String ModuleFieldName = "MODULE$";
   private static final Vector ClassLoaders;

   static {
      ClassLoaders = (Vector).MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new ClassLoader[]{MODULE$.getClass().getClassLoader(), Thread.currentThread().getContextClassLoader()})));
   }

   public String safeSimpleName(final Class clazz) {
      String var10000;
      try {
         var10000 = clazz.getSimpleName();
      } catch (Throwable var3) {
         int packageNameLen = BoxesRunTime.unboxToInt((new Some(clazz.getPackage())).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$safeSimpleName$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 0));
         var10000 = this.stripDollar(clazz.getName().substring(packageNameLen));
      }

      return var10000;
   }

   public String stripDollar(final String name) {
      while(true) {
         int index = name.lastIndexOf(36);
         String var10000;
         if (index == -1) {
            var10000 = name;
         } else {
            if (index == name.length() - 1) {
               name = name.substring(0, index);
               continue;
            }

            var10000 = name.substring(index + 1);
         }

         return var10000;
      }
   }

   public String ConstructorDefaultValuePattern() {
      return ConstructorDefaultValuePattern;
   }

   public String ModuleFieldName() {
      return ModuleFieldName;
   }

   public Vector ClassLoaders() {
      return ClassLoaders;
   }

   public ReflectorDescribable scalaTypeDescribable(final ScalaType t, final Formats formats) {
      return new ReflectorDescribable(formats, t) {
         private final List companionClasses;
         private final ParameterNameReader paranamer;
         private final ScalaType scalaType;

         public List companionClasses() {
            return this.companionClasses;
         }

         public ParameterNameReader paranamer() {
            return this.paranamer;
         }

         public ScalaType scalaType() {
            return this.scalaType;
         }

         public {
            this.companionClasses = formats$1.companions();
            this.paranamer = formats$1.parameterNameReader();
            this.scalaType = t$1;
         }
      };
   }

   public Formats scalaTypeDescribable$default$2(final ScalaType t) {
      return DefaultFormats$.MODULE$;
   }

   public ReflectorDescribable classDescribable(final Class t, final Formats formats) {
      return new ReflectorDescribable(formats, t) {
         private final List companionClasses;
         private final ParameterNameReader paranamer;
         private final ScalaType scalaType;

         public List companionClasses() {
            return this.companionClasses;
         }

         public ParameterNameReader paranamer() {
            return this.paranamer;
         }

         public ScalaType scalaType() {
            return this.scalaType;
         }

         public {
            this.companionClasses = formats$2.companions();
            this.paranamer = formats$2.parameterNameReader();
            this.scalaType = Reflector$.MODULE$.scalaTypeOf(t$2);
         }
      };
   }

   public Formats classDescribable$default$2(final Class t) {
      return DefaultFormats$.MODULE$;
   }

   public ReflectorDescribable stringDescribable(final String t, final Formats formats) {
      return new ReflectorDescribable(formats, t) {
         private final List companionClasses;
         private final ParameterNameReader paranamer;
         private final ScalaType scalaType;
         private final String t$3;

         public List companionClasses() {
            return this.companionClasses;
         }

         public ParameterNameReader paranamer() {
            return this.paranamer;
         }

         public ScalaType scalaType() {
            return this.scalaType;
         }

         public {
            this.t$3 = t$3;
            this.companionClasses = formats$3.companions();
            this.paranamer = formats$3.parameterNameReader();
            this.scalaType = (ScalaType)Reflector$.MODULE$.scalaTypeOf(t$3).getOrElse(() -> {
               throw new MappingException((new StringBuilder(24)).append("Couldn't find class for ").append(this.t$3).toString());
            });
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Formats stringDescribable$default$2(final String t) {
      return DefaultFormats$.MODULE$;
   }

   public Nothing fail(final String msg, final Exception cause) {
      throw new MappingException(msg, cause);
   }

   public Exception fail$default$2() {
      return null;
   }

   // $FF: synthetic method
   public static final int $anonfun$safeSimpleName$1(final Package x$1) {
      return x$1.getName().length() + 1;
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
