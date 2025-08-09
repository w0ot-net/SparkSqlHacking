package org.apache.spark.api.python;

import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%vAB\f\u0019\u0011\u0003a\"E\u0002\u0004%1!\u0005A$\n\u0005\u0006e\u0005!\t\u0001\u000e\u0005\bk\u0005\u0011\r\u0011\"\u00017\u0011\u0019y\u0014\u0001)A\u0005o!)\u0001)\u0001C\u0001\u0003\")A*\u0001C\u0001\u001b\")1+\u0001C\u0001)\")q,\u0001C\u0001A\")a0\u0001C\u0001\u007f\"9\u0011qB\u0001\u0005\u0002\u0005E\u0001bBA\u0012\u0003\u0011\u0005\u0011Q\u0005\u0005\b\u0003\u0007\nA\u0011AA#\u0011\u001d\ty%\u0001C\u0001\u0003#Bq!a\u0017\u0002\t\u0003\ti\u0006C\u0004\u0002b\u0005!\t!a\u0019\t\u000f\u00055\u0014\u0001\"\u0001\u0002p!Q\u00111P\u0001A\u0002\u0013\u0005A$! \t\u0015\u0005\u0015\u0015\u00011A\u0005\u0002q\t9\t\u0003\u0005\u0002\u000e\u0006\u0001\u000b\u0015BA@\u0011%\ty)\u0001b\u0001\n\u0003a\u0012\tC\u0004\u0002\u0012\u0006\u0001\u000b\u0011\u0002\"\t\u0011\u0005M\u0015\u0001\"\u0001\u001d\u0003+\u000b1\u0002U=uQ>tW\u000b^5mg*\u0011\u0011DG\u0001\u0007af$\bn\u001c8\u000b\u0005ma\u0012aA1qS*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014x\r\u0005\u0002$\u00035\t\u0001DA\u0006QsRDwN\\+uS2\u001c8cA\u0001'YA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u0004\"!\f\u0019\u000e\u00039R!a\f\u000f\u0002\u0011%tG/\u001a:oC2L!!\r\u0018\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001#\u00035\u0001\u0016\f\u000e&`5&\u0003vLT!N\u000bV\tq\u0007\u0005\u00029{5\t\u0011H\u0003\u0002;w\u0005!A.\u00198h\u0015\u0005a\u0014\u0001\u00026bm\u0006L!AP\u001d\u0003\rM#(/\u001b8h\u00039\u0001\u0016\f\u000e&`5&\u0003vLT!N\u000b\u0002\nqb\u001d9be.\u0004\u0016\u0010\u001e5p]B\u000bG\u000f[\u000b\u0002\u0005B\u00111I\u0013\b\u0003\t\"\u0003\"!\u0012\u0015\u000e\u0003\u0019S!aR\u001a\u0002\rq\u0012xn\u001c;?\u0013\tI\u0005&\u0001\u0004Qe\u0016$WMZ\u0005\u0003}-S!!\u0013\u0015\u0002!5,'oZ3QsRDwN\u001c)bi\"\u001cHC\u0001\"O\u0011\u0015ye\u00011\u0001Q\u0003\u0015\u0001\u0018\r\u001e5t!\r9\u0013KQ\u0005\u0003%\"\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?\u0003M9WM\\3sCR,'\u000b\u0012#XSRDg*\u001e7m)\t)&\fE\u0002W1\nk\u0011a\u0016\u0006\u0003yiI!!W,\u0003\u000f)\u000bg/\u0019*E\t\")1l\u0002a\u00019\u0006\u00111o\u0019\t\u0003-vK!AX,\u0003!)\u000bg/Y*qCJ\\7i\u001c8uKb$\u0018!\u0002;p'\u0016\fXCA1n)\t\u0011g\u000fE\u0002dQ.t!\u0001\u001a4\u000f\u0005\u0015+\u0017\"A\u0015\n\u0005\u001dD\u0013a\u00029bG.\fw-Z\u0005\u0003S*\u00141aU3r\u0015\t9\u0007\u0006\u0005\u0002m[2\u0001A!\u00028\t\u0005\u0004y'!\u0001+\u0012\u0005A\u001c\bCA\u0014r\u0013\t\u0011\bFA\u0004O_RD\u0017N\\4\u0011\u0005\u001d\"\u0018BA;)\u0005\r\te.\u001f\u0005\u0006o\"\u0001\r\u0001_\u0001\u0003mN\u00042!\u001f?l\u001b\u0005Q(BA><\u0003\u0011)H/\u001b7\n\u0005uT(\u0001\u0002'jgR\fa\u0001^8MSN$X\u0003BA\u0001\u0003\u0013!B!a\u0001\u0002\fA)1-!\u0002\u0002\b%\u0011QP\u001b\t\u0004Y\u0006%A!\u00028\n\u0005\u0004y\u0007BB<\n\u0001\u0004\ti\u0001\u0005\u0003zy\u0006\u001d\u0011a\u0002;p\u0003J\u0014\u0018-_\u000b\u0005\u0003'\ti\u0002\u0006\u0003\u0002\u0016\u0005}\u0001#B\u0014\u0002\u0018\u0005m\u0011bAA\rQ\t)\u0011I\u001d:bsB\u0019A.!\b\u0005\u000b9T!\u0019A8\t\r]T\u0001\u0019AA\u0011!\u0011IH0a\u0007\u0002\u0015Q|7kY1mC6\u000b\u0007/\u0006\u0004\u0002(\u0005E\u0012q\u0007\u000b\u0005\u0003S\tY\u0004E\u0004D\u0003W\ty#!\u000e\n\u0007\u000552JA\u0002NCB\u00042\u0001\\A\u0019\t\u0019\t\u0019d\u0003b\u0001_\n\t1\nE\u0002m\u0003o!a!!\u000f\f\u0005\u0004y'!\u0001,\t\u000f\u0005u2\u00021\u0001\u0002@\u0005\u0011!.\u001c\t\bs\u0006\u0005\u0013qFA\u001b\u0013\r\tiC_\u0001\u0014SN,en\u0019:zaRLwN\\#oC\ndW\r\u001a\u000b\u0005\u0003\u000f\ni\u0005E\u0002(\u0003\u0013J1!a\u0013)\u0005\u001d\u0011un\u001c7fC:DQa\u0017\u0007A\u0002q\u000bQcZ3u\u0005J|\u0017\rZ2bgR$\u0006N]3tQ>dG\r\u0006\u0003\u0002T\u0005e\u0003cA\u0014\u0002V%\u0019\u0011q\u000b\u0015\u0003\t1{gn\u001a\u0005\u000676\u0001\r\u0001X\u0001\u001bO\u0016$\b+\u001f;i_:\fU\u000f\u001e5T_\u000e\\W\r\u001e+j[\u0016|W\u000f\u001e\u000b\u0005\u0003'\ny\u0006C\u0003\\\u001d\u0001\u0007A,\u0001\nhKR\u001c\u0006/\u0019:l\u0005V4g-\u001a:TSj,G\u0003BA3\u0003W\u00022aJA4\u0013\r\tI\u0007\u000b\u0002\u0004\u0013:$\b\"B.\u0010\u0001\u0004a\u0016!\u00047pOBKH\u000f[8o\u0013:4w\u000e\u0006\u0003\u0002r\u0005]\u0004cA\u0014\u0002t%\u0019\u0011Q\u000f\u0015\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003s\u0002\u0002\u0019\u0001\"\u0002\u0015ALH\u000f[8o\u000bb,7-A\u000bbI\u0012LG/[8oC2$Vm\u001d;j]\u001e\u0004\u0016\r\u001e5\u0016\u0005\u0005}\u0004\u0003B\u0014\u0002\u0002\nK1!a!)\u0005\u0019y\u0005\u000f^5p]\u0006I\u0012\r\u001a3ji&|g.\u00197UKN$\u0018N\\4QCRDw\fJ3r)\u0011\t\t(!#\t\u0013\u0005-%#!AA\u0002\u0005}\u0014a\u0001=%c\u00051\u0012\r\u001a3ji&|g.\u00197UKN$\u0018N\\4QCRD\u0007%A\teK\u001a\fW\u000f\u001c;QsRDwN\\#yK\u000e\f!\u0003Z3gCVdG\u000fU=uQ>tW\t_3dA\u0005!2M]3bi\u0016\u0004\u0016\u0010\u001e5p]\u001a+hn\u0019;j_:$B!a&\u0002\u001eB\u00191%!'\n\u0007\u0005m\u0005D\u0001\u000bTS6\u0004H.\u001a)zi\"|gNR;oGRLwN\u001c\u0005\b\u0003?3\u0002\u0019AAQ\u0003\u001d\u0019w.\\7b]\u0012\u0004RaJA\f\u0003G\u00032aJAS\u0013\r\t9\u000b\u000b\u0002\u0005\u0005f$X\r"
)
public final class PythonUtils {
   public static void logPythonInfo(final String pythonExec) {
      PythonUtils$.MODULE$.logPythonInfo(pythonExec);
   }

   public static int getSparkBufferSize(final JavaSparkContext sc) {
      return PythonUtils$.MODULE$.getSparkBufferSize(sc);
   }

   public static long getPythonAuthSocketTimeout(final JavaSparkContext sc) {
      return PythonUtils$.MODULE$.getPythonAuthSocketTimeout(sc);
   }

   public static long getBroadcastThreshold(final JavaSparkContext sc) {
      return PythonUtils$.MODULE$.getBroadcastThreshold(sc);
   }

   public static boolean isEncryptionEnabled(final JavaSparkContext sc) {
      return PythonUtils$.MODULE$.isEncryptionEnabled(sc);
   }

   public static Map toScalaMap(final java.util.Map jm) {
      return PythonUtils$.MODULE$.toScalaMap(jm);
   }

   public static Object toArray(final List vs) {
      return PythonUtils$.MODULE$.toArray(vs);
   }

   public static scala.collection.immutable.List toList(final List vs) {
      return PythonUtils$.MODULE$.toList(vs);
   }

   public static Seq toSeq(final List vs) {
      return PythonUtils$.MODULE$.toSeq(vs);
   }

   public static JavaRDD generateRDDWithNull(final JavaSparkContext sc) {
      return PythonUtils$.MODULE$.generateRDDWithNull(sc);
   }

   public static String mergePythonPaths(final Seq paths) {
      return PythonUtils$.MODULE$.mergePythonPaths(paths);
   }

   public static String sparkPythonPath() {
      return PythonUtils$.MODULE$.sparkPythonPath();
   }

   public static String PY4J_ZIP_NAME() {
      return PythonUtils$.MODULE$.PY4J_ZIP_NAME();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return PythonUtils$.MODULE$.LogStringContext(sc);
   }
}
