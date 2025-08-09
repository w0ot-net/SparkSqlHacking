package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.matching.Regex;

public final class VersionUtils$ {
   public static final VersionUtils$ MODULE$ = new VersionUtils$();
   private static final Regex majorMinorRegex;
   private static final Regex shortVersionRegex;
   private static final Regex majorMinorPatchRegex;

   static {
      majorMinorRegex = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(\\d+)\\.(\\d+)(\\..*)?$"));
      shortVersionRegex = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(\\d+\\.\\d+\\.\\d+)(.*)?$"));
      majorMinorPatchRegex = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:[.-].*)?)?)?$"));
   }

   private Regex majorMinorRegex() {
      return majorMinorRegex;
   }

   private Regex shortVersionRegex() {
      return shortVersionRegex;
   }

   private Regex majorMinorPatchRegex() {
      return majorMinorPatchRegex;
   }

   public int majorVersion(final String sparkVersion) {
      return this.majorMinorVersion(sparkVersion)._1$mcI$sp();
   }

   public int minorVersion(final String sparkVersion) {
      return this.majorMinorVersion(sparkVersion)._2$mcI$sp();
   }

   public String shortVersion(final String sparkVersion) {
      Option var3 = this.shortVersionRegex().findFirstMatchIn(sparkVersion);
      if (var3 instanceof Some var4) {
         Regex.Match m = (Regex.Match)var4.value();
         return m.group(1);
      } else if (scala.None..MODULE$.equals(var3)) {
         throw new IllegalArgumentException("Spark tried to parse '" + sparkVersion + "' as a Spark version string, but it could not find the major/minor/maintenance version numbers.");
      } else {
         throw new MatchError(var3);
      }
   }

   public Tuple2 majorMinorVersion(final String sparkVersion) {
      Option var3 = this.majorMinorRegex().findFirstMatchIn(sparkVersion);
      if (var3 instanceof Some var4) {
         Regex.Match m = (Regex.Match)var4.value();
         return new Tuple2.mcII.sp(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(m.group(1))), .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(m.group(2))));
      } else if (scala.None..MODULE$.equals(var3)) {
         throw new IllegalArgumentException("Spark tried to parse '" + sparkVersion + "' as a Spark version string, but it could not find the major and minor version numbers.");
      } else {
         throw new MatchError(var3);
      }
   }

   public Option majorMinorPatchVersion(final String version) {
      return this.majorMinorPatchRegex().findFirstMatchIn(version).map((m) -> {
         int major = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(m.group(1)));
         int minor = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(m.group(2)).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$majorMinorPatchVersion$2(x$1))).getOrElse((JFunction0.mcI.sp)() -> 0));
         int patch = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(m.group(3)).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$majorMinorPatchVersion$4(x$2))).getOrElse((JFunction0.mcI.sp)() -> 0));
         return new Tuple3(BoxesRunTime.boxToInteger(major), BoxesRunTime.boxToInteger(minor), BoxesRunTime.boxToInteger(patch));
      });
   }

   // $FF: synthetic method
   public static final int $anonfun$majorMinorPatchVersion$2(final String x$1) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$majorMinorPatchVersion$4(final String x$2) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   private VersionUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
