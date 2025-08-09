package org.apache.spark.api.r;

import org.apache.spark.api.java.JavaSparkContext;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001<a\u0001D\u0007\t\u0002E9bAB\r\u000e\u0011\u0003\t\"\u0004C\u0003\"\u0003\u0011\u00051\u0005C\u0004%\u0003\u0001\u0007I\u0011A\u0013\t\u000fQ\n\u0001\u0019!C\u0001k!11(\u0001Q!\n\u0019BQ\u0001P\u0001\u0005\u0002\u0015BQ!P\u0001\u0005\u0002yBQAQ\u0001\u0005\u0002\rCQaT\u0001\u0005\u0002yBQ\u0001U\u0001\u0005\u0002ECQAW\u0001\u0005\u0002m\u000baAU+uS2\u001c(B\u0001\b\u0010\u0003\u0005\u0011(B\u0001\t\u0012\u0003\r\t\u0007/\u001b\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sOB\u0011\u0001$A\u0007\u0002\u001b\t1!+\u0016;jYN\u001c\"!A\u000e\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#A\f\u0002\u0013I\u0004\u0016mY6bO\u0016\u001cX#\u0001\u0014\u0011\u0007q9\u0013&\u0003\u0002);\t1q\n\u001d;j_:\u0004\"AK\u0019\u000f\u0005-z\u0003C\u0001\u0017\u001e\u001b\u0005i#B\u0001\u0018#\u0003\u0019a$o\\8u}%\u0011\u0001'H\u0001\u0007!J,G-\u001a4\n\u0005I\u001a$AB*ue&twM\u0003\u00021;\u0005i!\u000fU1dW\u0006<Wm]0%KF$\"AN\u001d\u0011\u0005q9\u0014B\u0001\u001d\u001e\u0005\u0011)f.\u001b;\t\u000fi\"\u0011\u0011!a\u0001M\u0005\u0019\u0001\u0010J\u0019\u0002\u0015I\u0004\u0016mY6bO\u0016\u001c\b%\u0001\fm_\u000e\fGn\u00159be.\u0014\u0006+Y2lC\u001e,\u0007+\u0019;i\u0003EI7o\u00159be.\u0014\u0016J\\:uC2dW\rZ\u000b\u0002\u007fA\u0011A\u0004Q\u0005\u0003\u0003v\u0011qAQ8pY\u0016\fg.A\tta\u0006\u00148N\u0015)bG.\fw-\u001a)bi\"$\"\u0001R'\u0011\u0007\u0015S\u0015F\u0004\u0002G\u0011:\u0011AfR\u0005\u0002=%\u0011\u0011*H\u0001\ba\u0006\u001c7.Y4f\u0013\tYEJA\u0002TKFT!!S\u000f\t\u000b9C\u0001\u0019A \u0002\u0011%\u001cHI]5wKJ\fA\"[:S\u0013:\u001cH/\u00197mK\u0012\f1#[:F]\u000e\u0014\u0018\u0010\u001d;j_:,e.\u00192mK\u0012$\"a\u0010*\t\u000bMS\u0001\u0019\u0001+\u0002\u0005M\u001c\u0007CA+Y\u001b\u00051&BA,\u0010\u0003\u0011Q\u0017M^1\n\u0005e3&\u0001\u0005&bm\u0006\u001c\u0006/\u0019:l\u0007>tG/\u001a=u\u0003)9W\r\u001e&pER\u000bwm\u001d\u000b\u00039~\u00032\u0001H/*\u0013\tqVDA\u0003BeJ\f\u0017\u0010C\u0003T\u0017\u0001\u0007A\u000b"
)
public final class RUtils {
   public static String[] getJobTags(final JavaSparkContext sc) {
      return RUtils$.MODULE$.getJobTags(sc);
   }

   public static boolean isEncryptionEnabled(final JavaSparkContext sc) {
      return RUtils$.MODULE$.isEncryptionEnabled(sc);
   }

   public static boolean isRInstalled() {
      return RUtils$.MODULE$.isRInstalled();
   }

   public static Seq sparkRPackagePath(final boolean isDriver) {
      return RUtils$.MODULE$.sparkRPackagePath(isDriver);
   }

   public static boolean isSparkRInstalled() {
      return RUtils$.MODULE$.isSparkRInstalled();
   }

   public static Option localSparkRPackagePath() {
      return RUtils$.MODULE$.localSparkRPackagePath();
   }

   public static void rPackages_$eq(final Option x$1) {
      RUtils$.MODULE$.rPackages_$eq(x$1);
   }

   public static Option rPackages() {
      return RUtils$.MODULE$.rPackages();
   }
}
