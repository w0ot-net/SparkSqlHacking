package org.json4s.jackson;

import com.fasterxml.jackson.core.Version;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.matching.Regex;

public final class Json4sModule$ {
   public static final Json4sModule$ MODULE$ = new Json4sModule$();
   private static final Regex VersionRegex;
   private static final Version version;

   static {
      VersionRegex = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(\\d+)\\.(\\d+)(?:\\.(\\d+)(?:\\-(.*))?)?"));
      version = MODULE$.liftedTree1$1();
   }

   public Version version() {
      return version;
   }

   // $FF: synthetic method
   public static final int $anonfun$version$1(final String x$1) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   private final Version liftedTree1$1() {
      Version var10000;
      try {
         Version var1;
         label23: {
            String groupId = org.json4s.BuildInfo..MODULE$.organization();
            String artifactId = org.json4s.BuildInfo..MODULE$.name();
            String var4 = org.json4s.BuildInfo..MODULE$.version();
            if (var4 != null) {
               Option var5 = VersionRegex.unapplySeq(var4);
               if (!var5.isEmpty() && var5.get() != null && ((List)var5.get()).lengthCompare(4) == 0) {
                  String major = (String)((LinearSeqOps)var5.get()).apply(0);
                  String minor = (String)((LinearSeqOps)var5.get()).apply(1);
                  String patchOpt = (String)((LinearSeqOps)var5.get()).apply(2);
                  String snapOpt = (String)((LinearSeqOps)var5.get()).apply(3);
                  int patch = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(patchOpt).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$version$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 0));
                  var1 = new Version(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(major)), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(minor)), patch, snapOpt, groupId, artifactId);
                  break label23;
               }
            }

            var1 = Version.unknownVersion();
         }

         var10000 = var1;
      } catch (Throwable var11) {
         var10000 = Version.unknownVersion();
      }

      return var10000;
   }

   private Json4sModule$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
