package scala.util;

import java.util.jar.Attributes;
import scala.Function0;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M;Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ\u0001G\u0001\u0005\u0002eAQAG\u0001\u0005\u0012mAQ\u0001J\u0001\u0005\u0012\u0015Bq\u0001Q\u0001C\u0002\u0013\u0005\u0011\t\u0003\u0004S\u0003\u0001\u0006IAQ\u0001\u000b!J|\u0007/\u001a:uS\u0016\u001c(BA\u0005\u000b\u0003\u0011)H/\u001b7\u000b\u0003-\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\u000f\u00035\t\u0001B\u0001\u0006Qe>\u0004XM\u001d;jKN\u001c2!A\t\u0016!\t\u00112#D\u0001\u000b\u0013\t!\"B\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001dYI!a\u0006\u0005\u0003\u001fA\u0013x\u000e]3si&,7\u000f\u0016:bSR\fa\u0001P5oSRtD#A\u0007\u0002\u0019A\u0014x\u000e]\"bi\u0016<wN]=\u0016\u0003q\u0001\"!\b\u0012\u000e\u0003yQ!a\b\u0011\u0002\t1\fgn\u001a\u0006\u0002C\u0005!!.\u0019<b\u0013\t\u0019cD\u0001\u0004TiJLgnZ\u0001\u000fa&\u001c7NS1s\u0005\u0006\u001cX\rZ(o+\u00051\u0003cA\u0014/c9\u0011\u0001\u0006\f\t\u0003S)i\u0011A\u000b\u0006\u0003W1\ta\u0001\u0010:p_Rt\u0014BA\u0017\u000b\u0003\u0019\u0001&/\u001a3fM&\u0011q\u0006\r\u0002\u0006\u00072\f7o\u001d\u0006\u0003[)\u0001$AM\u001c\u0011\u0007I\u0019T'\u0003\u00025\u0015\t1q\n\u001d;j_:\u0004\"AN\u001c\r\u0001\u0011I\u0001\bBA\u0001\u0002\u0003\u0015\t!\u000f\u0002\u0004?\u0012\n\u0014C\u0001\u001e>!\t\u00112(\u0003\u0002=\u0015\t9aj\u001c;iS:<\u0007C\u0001\n?\u0013\ty$BA\u0002B]f\fAcU2bY\u0006\u001cu.\u001c9jY\u0016\u0014h+\u001a:tS>tW#\u0001\"\u0011\u0005\r{eB\u0001#M\u001d\t)\u0015J\u0004\u0002G\u0011:\u0011\u0011fR\u0005\u0002C%\u0011\u0011\u0002I\u0005\u0003\u0015.\u000b1A[1s\u0015\tI\u0001%\u0003\u0002N\u001d\u0006Q\u0011\t\u001e;sS\n,H/Z:\u000b\u0005)[\u0015B\u0001)R\u0005\u0011q\u0015-\\3\u000b\u00055s\u0015!F*dC2\f7i\\7qS2,'OV3sg&|g\u000e\t"
)
public final class Properties {
   public static Attributes.Name ScalaCompilerVersion() {
      return Properties$.MODULE$.ScalaCompilerVersion();
   }

   public static void main(final String[] args) {
      Properties$.MODULE$.main(args);
   }

   public static boolean isJavaAtLeast(final int version) {
      return Properties$.MODULE$.isJavaAtLeast(version);
   }

   public static boolean isJavaAtLeast(final String version) {
      return Properties$.MODULE$.isJavaAtLeast(version);
   }

   public static String scalacCmd() {
      return Properties$.MODULE$.scalacCmd();
   }

   public static String scalaCmd() {
      return Properties$.MODULE$.scalaCmd();
   }

   public static String versionMsg() {
      return Properties$.MODULE$.versionMsg();
   }

   public static String jdkHome() {
      return Properties$.MODULE$.jdkHome();
   }

   public static boolean isLinux() {
      return Properties$.MODULE$.isLinux();
   }

   public static boolean isMac() {
      return Properties$.MODULE$.isMac();
   }

   public static boolean isWin() {
      return Properties$.MODULE$.isWin();
   }

   public static String userName() {
      return Properties$.MODULE$.userName();
   }

   public static String userHome() {
      return Properties$.MODULE$.userHome();
   }

   public static String userDir() {
      return Properties$.MODULE$.userDir();
   }

   public static String tmpDir() {
      return Properties$.MODULE$.tmpDir();
   }

   public static String scalaHome() {
      return Properties$.MODULE$.scalaHome();
   }

   public static String osName() {
      return Properties$.MODULE$.osName();
   }

   public static String javaSpecName() {
      return Properties$.MODULE$.javaSpecName();
   }

   public static String javaSpecVendor() {
      return Properties$.MODULE$.javaSpecVendor();
   }

   public static String javaSpecVersion() {
      return Properties$.MODULE$.javaSpecVersion();
   }

   public static String javaVmVersion() {
      return Properties$.MODULE$.javaVmVersion();
   }

   public static String javaVmVendor() {
      return Properties$.MODULE$.javaVmVendor();
   }

   public static String javaVmName() {
      return Properties$.MODULE$.javaVmName();
   }

   public static String javaVmInfo() {
      return Properties$.MODULE$.javaVmInfo();
   }

   public static String javaVersion() {
      return Properties$.MODULE$.javaVersion();
   }

   public static String javaVendor() {
      return Properties$.MODULE$.javaVendor();
   }

   public static String javaHome() {
      return Properties$.MODULE$.javaHome();
   }

   public static String javaClassPath() {
      return Properties$.MODULE$.javaClassPath();
   }

   public static String lineSeparator() {
      return Properties$.MODULE$.lineSeparator();
   }

   public static String encodingString() {
      return Properties$.MODULE$.encodingString();
   }

   public static String sourceReader() {
      return Properties$.MODULE$.sourceReader();
   }

   public static String sourceEncoding() {
      return Properties$.MODULE$.sourceEncoding();
   }

   public static String copyrightString() {
      return Properties$.MODULE$.copyrightString();
   }

   public static String versionString() {
      return Properties$.MODULE$.versionString();
   }

   public static String versionNumberString() {
      return Properties$.MODULE$.versionNumberString();
   }

   public static Option developmentVersion() {
      return Properties$.MODULE$.developmentVersion();
   }

   public static Option releaseVersion() {
      return Properties$.MODULE$.releaseVersion();
   }

   public static Option scalaPropOrNone(final String name) {
      return Properties$.MODULE$.scalaPropOrNone(name);
   }

   public static String scalaPropOrEmpty(final String name) {
      return Properties$.MODULE$.scalaPropOrEmpty(name);
   }

   public static String scalaPropOrElse(final String name, final Function0 alt) {
      return Properties$.MODULE$.scalaPropOrElse(name, alt);
   }

   public static Option envOrSome(final String name, final Function0 alt) {
      return Properties$.MODULE$.envOrSome(name, alt);
   }

   public static Option envOrNone(final String name) {
      return Properties$.MODULE$.envOrNone(name);
   }

   public static String envOrElse(final String name, final Function0 alt) {
      return Properties$.MODULE$.envOrElse(name, alt);
   }

   public static String clearProp(final String name) {
      return Properties$.MODULE$.clearProp(name);
   }

   public static String setProp(final String name, final String value) {
      return Properties$.MODULE$.setProp(name, value);
   }

   public static boolean propOrFalse(final String name) {
      return Properties$.MODULE$.propOrFalse(name);
   }

   public static Option propOrNone(final String name) {
      return Properties$.MODULE$.propOrNone(name);
   }

   public static String propOrNull(final String name) {
      return Properties$.MODULE$.propOrNull(name);
   }

   public static String propOrEmpty(final String name) {
      return Properties$.MODULE$.propOrEmpty(name);
   }

   public static String propOrElse(final String name, final Function0 alt) {
      return Properties$.MODULE$.propOrElse(name, alt);
   }

   public static boolean propIsSetTo(final String name, final String value) {
      return Properties$.MODULE$.propIsSetTo(name, value);
   }

   public static boolean propIsSet(final String name) {
      return Properties$.MODULE$.propIsSet(name);
   }
}
