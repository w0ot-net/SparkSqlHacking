package org.apache.spark.util;

import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i;aAD\b\t\u0002E9bAB\r\u0010\u0011\u0003\t\"\u0004C\u0003\"\u0003\u0011\u00051\u0005C\u0004%\u0003\t\u0007I\u0011B\u0013\t\r5\n\u0001\u0015!\u0003'\u0011\u001dq\u0013A1A\u0005\n\u0015BaaL\u0001!\u0002\u00131\u0003b\u0002\u0019\u0002\u0005\u0004%I!\n\u0005\u0007c\u0005\u0001\u000b\u0011\u0002\u0014\t\u000bI\nA\u0011A\u001a\t\u000b\u0011\u000bA\u0011A#\t\u000b\u001d\u000bA\u0011\u0001%\t\u000b)\u000bA\u0011A&\t\u000bA\u000bA\u0011A)\u0002\u0019Y+'o]5p]V#\u0018\u000e\\:\u000b\u0005A\t\u0012\u0001B;uS2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'o\u001a\t\u00031\u0005i\u0011a\u0004\u0002\r-\u0016\u00148/[8o+RLGn]\n\u0003\u0003m\u0001\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003]\tq\"\\1k_Jl\u0015N\\8s%\u0016<W\r_\u000b\u0002MA\u0011qeK\u0007\u0002Q)\u0011\u0011FK\u0001\t[\u0006$8\r[5oO*\u0011\u0001#H\u0005\u0003Y!\u0012QAU3hKb\f\u0001#\\1k_Jl\u0015N\\8s%\u0016<W\r\u001f\u0011\u0002#MDwN\u001d;WKJ\u001c\u0018n\u001c8SK\u001e,\u00070\u0001\ntQ>\u0014HOV3sg&|gNU3hKb\u0004\u0013\u0001F7bU>\u0014X*\u001b8peB\u000bGo\u00195SK\u001e,\u00070A\u000bnC*|'/T5o_J\u0004\u0016\r^2i%\u0016<W\r\u001f\u0011\u0002\u00195\f'n\u001c:WKJ\u001c\u0018n\u001c8\u0015\u0005Q:\u0004C\u0001\u000f6\u0013\t1TDA\u0002J]RDQ\u0001O\u0005A\u0002e\nAb\u001d9be.4VM]:j_:\u0004\"AO!\u000f\u0005mz\u0004C\u0001\u001f\u001e\u001b\u0005i$B\u0001 #\u0003\u0019a$o\\8u}%\u0011\u0001)H\u0001\u0007!J,G-\u001a4\n\u0005\t\u001b%AB*ue&twM\u0003\u0002A;\u0005aQ.\u001b8peZ+'o]5p]R\u0011AG\u0012\u0005\u0006q)\u0001\r!O\u0001\rg\"|'\u000f\u001e,feNLwN\u001c\u000b\u0003s%CQ\u0001O\u0006A\u0002e\n\u0011#\\1k_Jl\u0015N\\8s-\u0016\u00148/[8o)\tau\n\u0005\u0003\u001d\u001bR\"\u0014B\u0001(\u001e\u0005\u0019!V\u000f\u001d7fe!)\u0001\b\u0004a\u0001s\u00051R.\u00196pe6Kgn\u001c:QCR\u001c\u0007NV3sg&|g\u000e\u0006\u0002S1B\u0019AdU+\n\u0005Qk\"AB(qi&|g\u000eE\u0003\u001d-R\"D'\u0003\u0002X;\t1A+\u001e9mKNBQ!W\u0007A\u0002e\nqA^3sg&|g\u000e"
)
public final class VersionUtils {
   public static Option majorMinorPatchVersion(final String version) {
      return VersionUtils$.MODULE$.majorMinorPatchVersion(version);
   }

   public static Tuple2 majorMinorVersion(final String sparkVersion) {
      return VersionUtils$.MODULE$.majorMinorVersion(sparkVersion);
   }

   public static String shortVersion(final String sparkVersion) {
      return VersionUtils$.MODULE$.shortVersion(sparkVersion);
   }

   public static int minorVersion(final String sparkVersion) {
      return VersionUtils$.MODULE$.minorVersion(sparkVersion);
   }

   public static int majorVersion(final String sparkVersion) {
      return VersionUtils$.MODULE$.majorVersion(sparkVersion);
   }
}
