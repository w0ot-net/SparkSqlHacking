package org.apache.spark.deploy.rest;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.util.Try.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a!B\f\u0019\u0001a\u0011\u0003\"B\u0014\u0001\t\u0003I\u0003bB\u0016\u0001\u0001\u0004%\t\u0001\f\u0005\bu\u0001\u0001\r\u0011\"\u0001<\u0011\u0019\u0011\u0005\u0001)Q\u0005[!91\t\u0001a\u0001\n\u0003a\u0003b\u0002#\u0001\u0001\u0004%\t!\u0012\u0005\u0007\u000f\u0002\u0001\u000b\u0015B\u0017\t\u000f!\u0003\u0001\u0019!C\u0001\u0013\"9Q\n\u0001a\u0001\n\u0003q\u0005B\u0002)\u0001A\u0003&!\nC\u0004R\u0001\u0001\u0007I\u0011\u0001*\t\u000fY\u0003\u0001\u0019!C\u0001/\"1\u0011\f\u0001Q!\nMCqA\u0017\u0001A\u0002\u0013\u0005!\u000bC\u0004\\\u0001\u0001\u0007I\u0011\u0001/\t\ry\u0003\u0001\u0015)\u0003T\u0011\u0015y\u0006\u0001\"\u0015a\u0011\u0015\t\u0007\u0001\"\u0003c\u0011\u0015)\u0007\u0001\"\u0003g\u0011\u0015A\u0007\u0001\"\u0003j\u0011\u0015Y\u0007\u0001\"\u0003m\u0011\u0015q\u0007\u0001\"\u0003p\u0005]\u0019%/Z1uKN+(-\\5tg&|gNU3rk\u0016\u001cHO\u0003\u0002\u001a5\u0005!!/Z:u\u0015\tYB$\u0001\u0004eKBdw.\u001f\u0006\u0003;y\tQa\u001d9be.T!a\b\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0013aA8sON\u0011\u0001a\t\t\u0003I\u0015j\u0011\u0001G\u0005\u0003Ma\u0011\u0011dU;c[&$(+Z:u!J|Go\\2pYJ+\u0017/^3ti\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001+!\t!\u0003!A\u0006baB\u0014Vm]8ve\u000e,W#A\u0017\u0011\u00059:dBA\u00186!\t\u00014'D\u00012\u0015\t\u0011\u0004&\u0001\u0004=e>|GO\u0010\u0006\u0002i\u0005)1oY1mC&\u0011agM\u0001\u0007!J,G-\u001a4\n\u0005aJ$AB*ue&twM\u0003\u00027g\u0005y\u0011\r\u001d9SKN|WO]2f?\u0012*\u0017\u000f\u0006\u0002=\u0001B\u0011QHP\u0007\u0002g%\u0011qh\r\u0002\u0005+:LG\u000fC\u0004B\u0007\u0005\u0005\t\u0019A\u0017\u0002\u0007a$\u0013'\u0001\u0007baB\u0014Vm]8ve\u000e,\u0007%A\u0005nC&t7\t\\1tg\u0006iQ.Y5o\u00072\f7o]0%KF$\"\u0001\u0010$\t\u000f\u00053\u0011\u0011!a\u0001[\u0005QQ.Y5o\u00072\f7o\u001d\u0011\u0002\u000f\u0005\u0004\b/\u0011:hgV\t!\nE\u0002>\u00176J!\u0001T\u001a\u0003\u000b\u0005\u0013(/Y=\u0002\u0017\u0005\u0004\b/\u0011:hg~#S-\u001d\u000b\u0003y=Cq!Q\u0005\u0002\u0002\u0003\u0007!*\u0001\u0005baB\f%oZ:!\u0003=\u0019\b/\u0019:l!J|\u0007/\u001a:uS\u0016\u001cX#A*\u0011\t9\"V&L\u0005\u0003+f\u00121!T1q\u0003M\u0019\b/\u0019:l!J|\u0007/\u001a:uS\u0016\u001cx\fJ3r)\ta\u0004\fC\u0004B\u0019\u0005\u0005\t\u0019A*\u0002!M\u0004\u0018M]6Qe>\u0004XM\u001d;jKN\u0004\u0013\u0001F3om&\u0014xN\\7f]R4\u0016M]5bE2,7/\u0001\rf]ZL'o\u001c8nK:$h+\u0019:jC\ndWm]0%KF$\"\u0001P/\t\u000f\u0005{\u0011\u0011!a\u0001'\u0006)RM\u001c<je>tW.\u001a8u-\u0006\u0014\u0018.\u00192mKN\u0004\u0013A\u00033p-\u0006d\u0017\u000eZ1uKR\tA(A\nbgN,'\u000f\u001e)s_B,'\u000f^=JgN+G\u000f\u0006\u0002=G\")AM\u0005a\u0001[\u0005\u00191.Z=\u0002/\u0005\u001c8/\u001a:u!J|\u0007/\u001a:us&\u001b(i\\8mK\u0006tGC\u0001\u001fh\u0011\u0015!7\u00031\u0001.\u0003]\t7o]3siB\u0013x\u000e]3sifL5OT;nKJL7\r\u0006\u0002=U\")A\r\u0006a\u0001[\u00051\u0012m]:feR\u0004&o\u001c9feRL\u0018j]'f[>\u0014\u0018\u0010\u0006\u0002=[\")A-\u0006a\u0001[\u0005q\u0011m]:feR\u0004&o\u001c9feRLXC\u00019|)\u0011a\u0014O\u001d;\t\u000b\u00114\u0002\u0019A\u0017\t\u000bM4\u0002\u0019A\u0017\u0002\u0013Y\fG.^3UsB,\u0007\"B;\u0017\u0001\u00041\u0018aB2p]Z,'\u000f\u001e\t\u0005{]l\u00130\u0003\u0002yg\tIa)\u001e8di&|g.\r\t\u0003und\u0001\u0001B\u0003}-\t\u0007QPA\u0001U#\rq\u00181\u0001\t\u0003{}L1!!\u00014\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!PA\u0003\u0013\r\t9a\r\u0002\u0004\u0003:L\b"
)
public class CreateSubmissionRequest extends SubmitRestProtocolRequest {
   private String appResource = null;
   private String mainClass = null;
   private String[] appArgs = null;
   private Map sparkProperties = null;
   private Map environmentVariables = null;

   public String appResource() {
      return this.appResource;
   }

   public void appResource_$eq(final String x$1) {
      this.appResource = x$1;
   }

   public String mainClass() {
      return this.mainClass;
   }

   public void mainClass_$eq(final String x$1) {
      this.mainClass = x$1;
   }

   public String[] appArgs() {
      return this.appArgs;
   }

   public void appArgs_$eq(final String[] x$1) {
      this.appArgs = x$1;
   }

   public Map sparkProperties() {
      return this.sparkProperties;
   }

   public void sparkProperties_$eq(final Map x$1) {
      this.sparkProperties = x$1;
   }

   public Map environmentVariables() {
      return this.environmentVariables;
   }

   public void environmentVariables_$eq(final Map x$1) {
      this.environmentVariables = x$1;
   }

   public void doValidate() {
      super.doValidate();
      this.assert(this.sparkProperties() != null, "No Spark properties set!");
      this.assertFieldIsSet(this.appResource(), "appResource");
      this.assertPropertyIsBoolean(package$.MODULE$.DRIVER_SUPERVISE().key());
      this.assertPropertyIsNumeric(package$.MODULE$.DRIVER_CORES().key());
      this.assertPropertyIsNumeric(package$.MODULE$.CORES_MAX().key());
      this.assertPropertyIsMemory(package$.MODULE$.DRIVER_MEMORY().key());
      this.assertPropertyIsMemory(package$.MODULE$.EXECUTOR_MEMORY().key());
   }

   private void assertPropertyIsSet(final String key) {
      this.assertFieldIsSet(this.sparkProperties().getOrElse(key, () -> null), key);
   }

   private void assertPropertyIsBoolean(final String key) {
      this.assertProperty(key, "boolean", (x$1) -> BoxesRunTime.boxToBoolean($anonfun$assertPropertyIsBoolean$1(x$1)));
   }

   private void assertPropertyIsNumeric(final String key) {
      this.assertProperty(key, "numeric", (x$2) -> BoxesRunTime.boxToDouble($anonfun$assertPropertyIsNumeric$1(x$2)));
   }

   private void assertPropertyIsMemory(final String key) {
      this.assertProperty(key, "memory", (str) -> BoxesRunTime.boxToInteger($anonfun$assertPropertyIsMemory$1(str)));
   }

   private void assertProperty(final String key, final String valueType, final Function1 convert) {
      this.sparkProperties().get(key).foreach((value) -> .MODULE$.apply(() -> convert.apply(value)).getOrElse(() -> {
            throw new SubmitRestProtocolException("Property '" + key + "' expected " + valueType + " value: actual was '" + value + "'.", SubmitRestProtocolException$.MODULE$.$lessinit$greater$default$2());
         }));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertPropertyIsBoolean$1(final String x$1) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final double $anonfun$assertPropertyIsNumeric$1(final String x$2) {
      return scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final int $anonfun$assertPropertyIsMemory$1(final String str) {
      return Utils$.MODULE$.memoryStringToMb(str);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
