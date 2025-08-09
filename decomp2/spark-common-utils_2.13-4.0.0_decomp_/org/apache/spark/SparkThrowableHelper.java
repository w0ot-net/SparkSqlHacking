package org.apache.spark;

import scala.Enumeration;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a<a!\u0004\b\t\u00029!bA\u0002\f\u000f\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0004\"\u0003\t\u0007I\u0011\u0001\u0012\t\r\u0019\n\u0001\u0015!\u0003$\u0011\u00159\u0013\u0001\"\u0001)\u0011\u00159\u0013\u0001\"\u0001<\u0011\u00159\u0013\u0001\"\u0001F\u0011\u0015Q\u0015\u0001\"\u0001L\u0011\u0015i\u0015\u0001\"\u0001O\u0011\u0015\u0019\u0016\u0001\"\u0001U\u0011\u0015y\u0016\u0001\"\u0001a\u0011\u00159\u0013\u0001\"\u0001c\u0003Q\u0019\u0006/\u0019:l)\"\u0014xn^1cY\u0016DU\r\u001c9fe*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014x\r\u0005\u0002\u0016\u00035\taB\u0001\u000bTa\u0006\u00148\u000e\u00165s_^\f'\r\\3IK2\u0004XM]\n\u0003\u0003a\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Q\t1\"\u001a:s_J\u0014V-\u00193feV\t1\u0005\u0005\u0002\u0016I%\u0011QE\u0004\u0002\u0017\u000bJ\u0014xN]\"mCN\u001cXm\u001d&t_:\u0014V-\u00193fe\u0006aQM\u001d:peJ+\u0017\rZ3sA\u0005Qq-\u001a;NKN\u001c\u0018mZ3\u0015\u0007%\"d\u0007\u0005\u0002+c9\u00111f\f\t\u0003Yii\u0011!\f\u0006\u0003]}\ta\u0001\u0010:p_Rt\u0014B\u0001\u0019\u001b\u0003\u0019\u0001&/\u001a3fM&\u0011!g\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005AR\u0002\"B\u001b\u0006\u0001\u0004I\u0013AC3se>\u00148\t\\1tg\")q'\u0002a\u0001q\u0005\tR.Z:tC\u001e,\u0007+\u0019:b[\u0016$XM]:\u0011\t)J\u0014&K\u0005\u0003uM\u00121!T1q)\rIC(\u0010\u0005\u0006k\u0019\u0001\r!\u000b\u0005\u0006o\u0019\u0001\rA\u0010\t\u0005\u007f\u0011K\u0013&D\u0001A\u0015\t\t%)\u0001\u0003vi&d'\"A\"\u0002\t)\fg/Y\u0005\u0003u\u0001#B!\u000b$H\u0011\")Qg\u0002a\u0001S!)qg\u0002a\u0001q!)\u0011j\u0002a\u0001S\u000591m\u001c8uKb$\u0018aC4fiN\u000bHn\u0015;bi\u0016$\"!\u000b'\t\u000bUB\u0001\u0019A\u0015\u0002#%\u001ch+\u00197jI\u0016\u0013(o\u001c:DY\u0006\u001c8\u000f\u0006\u0002P%B\u0011\u0011\u0004U\u0005\u0003#j\u0011qAQ8pY\u0016\fg\u000eC\u00036\u0013\u0001\u0007\u0011&\u0001\u000bhKRlUm]:bO\u0016\u0004\u0016M]1nKR,'o\u001d\u000b\u0003+z\u00032AV.*\u001d\t9\u0016L\u0004\u0002-1&\t1$\u0003\u0002[5\u00059\u0001/Y2lC\u001e,\u0017B\u0001/^\u0005\r\u0019V-\u001d\u0006\u00035jAQ!\u000e\u0006A\u0002%\nq\"[:J]R,'O\\1m\u000bJ\u0014xN\u001d\u000b\u0003\u001f\u0006DQ!N\u0006A\u0002%\"2!K2o\u0011\u0015!G\u00021\u0001f\u0003\u0005)'c\u00014iW\u001a!q-\u0001\u0001f\u00051a$/\u001a4j]\u0016lWM\u001c;?!\t)\u0012.\u0003\u0002k\u001d\tq1\u000b]1sWRC'o\\<bE2,\u0007C\u0001,m\u0013\tiWLA\u0005UQJ|w/\u00192mK\")q\u000e\u0004a\u0001a\u00061am\u001c:nCR\u0004\"!\u001d;\u000f\u0005U\u0011\u0018BA:\u000f\u0003I)%O]8s\u001b\u0016\u001c8/Y4f\r>\u0014X.\u0019;\n\u0005U4(!\u0002,bYV,\u0017BA<\u001b\u0005-)e.^7fe\u0006$\u0018n\u001c8"
)
public final class SparkThrowableHelper {
   public static String getMessage(final Throwable e, final Enumeration.Value format) {
      return SparkThrowableHelper$.MODULE$.getMessage(e, format);
   }

   public static boolean isInternalError(final String errorClass) {
      return SparkThrowableHelper$.MODULE$.isInternalError(errorClass);
   }

   public static Seq getMessageParameters(final String errorClass) {
      return SparkThrowableHelper$.MODULE$.getMessageParameters(errorClass);
   }

   public static boolean isValidErrorClass(final String errorClass) {
      return SparkThrowableHelper$.MODULE$.isValidErrorClass(errorClass);
   }

   public static String getSqlState(final String errorClass) {
      return SparkThrowableHelper$.MODULE$.getSqlState(errorClass);
   }

   public static String getMessage(final String errorClass, final Map messageParameters, final String context) {
      return SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters, context);
   }

   public static String getMessage(final String errorClass, final java.util.Map messageParameters) {
      return SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters);
   }

   public static String getMessage(final String errorClass, final Map messageParameters) {
      return SparkThrowableHelper$.MODULE$.getMessage(errorClass, messageParameters);
   }

   public static ErrorClassesJsonReader errorReader() {
      return SparkThrowableHelper$.MODULE$.errorReader();
   }
}
