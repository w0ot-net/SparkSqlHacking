package org.apache.spark.api.python;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.apache.spark.SparkEnv;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005UtA\u0002\b\u0010\u0011\u0003\u0019\u0012D\u0002\u0004\u001c\u001f!\u00051\u0003\b\u0005\u0006S\u0005!\ta\u000b\u0005\u0006Y\u0005!\t!\f\u0005\u0006\u0011\u0006!\t!\u0013\u0005\u0006'\u0006!\t\u0001\u0016\u0005\u00061\u0006!\t!\u0017\u0005\u0006K\u0006!\tA\u001a\u0005\b\u0003\u001f\tA\u0011AA\t\u0011\u001d\ty\"\u0001C\u0001\u0003CAq!a\b\u0002\t\u0003\ti\u0003C\u0004\u0002<\u0005!\t!!\u0010\t\u000f\u0005m\u0012\u0001\"\u0001\u0002B!9\u0011qI\u0001\u0005\u0002\u0005%\u0013!\u0005)zi\"|gnV8sW\u0016\u0014X\u000b^5mg*\u0011\u0001#E\u0001\u0007af$\bn\u001c8\u000b\u0005I\u0019\u0012aA1qS*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014x\r\u0005\u0002\u001b\u00035\tqBA\tQsRDwN\\,pe.,'/\u0016;jYN\u001c2!A\u000f$!\tq\u0012%D\u0001 \u0015\u0005\u0001\u0013!B:dC2\f\u0017B\u0001\u0012 \u0005\u0019\te.\u001f*fMB\u0011AeJ\u0007\u0002K)\u0011aeE\u0001\tS:$XM\u001d8bY&\u0011\u0001&\n\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001A#A\r\u0002\u0011]\u0014\u0018\u000e^3V)\u001a#2AL\u0019?!\tqr&\u0003\u00021?\t!QK\\5u\u0011\u0015\u00114\u00011\u00014\u0003\r\u0019HO\u001d\t\u0003imr!!N\u001d\u0011\u0005YzR\"A\u001c\u000b\u0005aR\u0013A\u0002\u001fs_>$h(\u0003\u0002;?\u00051\u0001K]3eK\u001aL!\u0001P\u001f\u0003\rM#(/\u001b8h\u0015\tQt\u0004C\u0003@\u0007\u0001\u0007\u0001)A\u0004eCR\fw*\u001e;\u0011\u0005\u00053U\"\u0001\"\u000b\u0005\r#\u0015AA5p\u0015\u0005)\u0015\u0001\u00026bm\u0006L!a\u0012\"\u0003!\u0011\u000bG/Y(viB,Ho\u0015;sK\u0006l\u0017AC<sSR,')\u001f;fgR\u0019aF\u0013*\t\u000b-#\u0001\u0019\u0001'\u0002\u000b\tLH/Z:\u0011\u0007yiu*\u0003\u0002O?\t)\u0011I\u001d:bsB\u0011a\u0004U\u0005\u0003#~\u0011AAQ=uK\")q\b\u0002a\u0001\u0001\u0006\u0011rO]5uKBKH\u000f[8o-\u0016\u00148/[8o)\rqSk\u0016\u0005\u0006-\u0016\u0001\raM\u0001\naf$\bn\u001c8WKJDQaP\u0003A\u0002\u0001\u000bqb\u001e:ji\u0016\u001c\u0006/\u0019:l\r&dWm\u001d\u000b\u0005]i{F\rC\u0003\\\r\u0001\u0007A,A\bk_\n\f%\u000f^5gC\u000e$X+V%E!\rqRlM\u0005\u0003=~\u0011aa\u00149uS>t\u0007\"\u00021\u0007\u0001\u0004\t\u0017A\u00049zi\"|g.\u00138dYV$Wm\u001d\t\u0004i\t\u001c\u0014BA2>\u0005\r\u0019V\r\u001e\u0005\u0006\u007f\u0019\u0001\r\u0001Q\u0001\u0010oJLG/\u001a\"s_\u0006$7-Y:ugR9afZ>\u0002\u0002\u00055\u0001\"\u00025\b\u0001\u0004I\u0017!\u00042s_\u0006$7-Y:u-\u0006\u00148\u000fE\u0002k_Jt!a[7\u000f\u0005Yb\u0017\"\u0001\u0011\n\u00059|\u0012a\u00029bG.\fw-Z\u0005\u0003aF\u00141aU3r\u0015\tqw\u0004E\u0002tmbl\u0011\u0001\u001e\u0006\u0003kN\t\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\u0005]$(!\u0003\"s_\u0006$7-Y:u!\tQ\u00120\u0003\u0002{\u001f\ty\u0001+\u001f;i_:\u0014%o\\1eG\u0006\u001cH\u000fC\u0003}\u000f\u0001\u0007Q0\u0001\u0004x_J\\WM\u001d\t\u00035yL!a`\b\u0003\u0019AKH\u000f[8o/>\u00148.\u001a:\t\u000f\u0005\rq\u00011\u0001\u0002\u0006\u0005\u0019QM\u001c<\u0011\t\u0005\u001d\u0011\u0011B\u0007\u0002'%\u0019\u00111B\n\u0003\u0011M\u0003\u0018M]6F]ZDQaP\u0004A\u0002\u0001\u000b1c\u001e:ji\u0016\u0004\u0016\u0010\u001e5p]\u001a+hn\u0019;j_:$RALA\n\u0003;Aq!!\u0006\t\u0001\u0004\t9\"\u0001\u0003gk:\u001c\u0007c\u0001\u000e\u0002\u001a%\u0019\u00111D\b\u0003\u001dAKH\u000f[8o\rVt7\r^5p]\")q\b\u0003a\u0001\u0001\u00069!/Z1e+R3EcA\u001a\u0002$!9\u0011QE\u0005A\u0002\u0005\u001d\u0012A\u00023bi\u0006Le\u000eE\u0002B\u0003SI1!a\u000bC\u0005=!\u0015\r^1J]B,Ho\u0015;sK\u0006lG#B\u001a\u00020\u0005e\u0002bBA\u0019\u0015\u0001\u0007\u00111G\u0001\u0007Y\u0016tw\r\u001e5\u0011\u0007y\t)$C\u0002\u00028}\u00111!\u00138u\u0011\u001d\t)C\u0003a\u0001\u0003O\t\u0011B]3bI\nKH/Z:\u0015\u00071\u000by\u0004C\u0004\u0002&-\u0001\r!a\n\u0015\u000b1\u000b\u0019%!\u0012\t\u000f\u0005EB\u00021\u0001\u00024!9\u0011Q\u0005\u0007A\u0002\u0005\u001d\u0012!\u0007:fG\u0016Lg/Z!dGVlW\u000f\\1u_J,\u0006\u000fZ1uKN$RALA&\u0003gBq!!\u0014\u000e\u0001\u0004\ty%\u0001\tnCf\u0014W-Q2dk6,H.\u0019;peB!a$XA)!\u0011\t\u0019&!\u001c\u000f\t\u0005U\u0013\u0011\u000e\b\u0005\u0003/\n9G\u0004\u0003\u0002Z\u0005\u0015d\u0002BA.\u0003GrA!!\u0018\u0002b9\u0019a'a\u0018\n\u0003aI!AF\f\n\u0005Q)\u0012B\u0001\n\u0014\u0013\t\u0001\u0012#C\u0002\u0002l=\ta\u0002U=uQ>tg)\u001e8di&|g.\u0003\u0003\u0002p\u0005E$!\u0005)zi\"|g.Q2dk6,H.\u0019;pe*\u0019\u00111N\b\t\u000f\u0005\u0015R\u00021\u0001\u0002(\u0001"
)
public final class PythonWorkerUtils {
   public static void receiveAccumulatorUpdates(final Option maybeAccumulator, final DataInputStream dataIn) {
      PythonWorkerUtils$.MODULE$.receiveAccumulatorUpdates(maybeAccumulator, dataIn);
   }

   public static byte[] readBytes(final int length, final DataInputStream dataIn) {
      return PythonWorkerUtils$.MODULE$.readBytes(length, dataIn);
   }

   public static byte[] readBytes(final DataInputStream dataIn) {
      return PythonWorkerUtils$.MODULE$.readBytes(dataIn);
   }

   public static String readUTF(final int length, final DataInputStream dataIn) {
      return PythonWorkerUtils$.MODULE$.readUTF(length, dataIn);
   }

   public static String readUTF(final DataInputStream dataIn) {
      return PythonWorkerUtils$.MODULE$.readUTF(dataIn);
   }

   public static void writePythonFunction(final PythonFunction func, final DataOutputStream dataOut) {
      PythonWorkerUtils$.MODULE$.writePythonFunction(func, dataOut);
   }

   public static void writeBroadcasts(final Seq broadcastVars, final PythonWorker worker, final SparkEnv env, final DataOutputStream dataOut) {
      PythonWorkerUtils$.MODULE$.writeBroadcasts(broadcastVars, worker, env, dataOut);
   }

   public static void writeSparkFiles(final Option jobArtifactUUID, final Set pythonIncludes, final DataOutputStream dataOut) {
      PythonWorkerUtils$.MODULE$.writeSparkFiles(jobArtifactUUID, pythonIncludes, dataOut);
   }

   public static void writePythonVersion(final String pythonVer, final DataOutputStream dataOut) {
      PythonWorkerUtils$.MODULE$.writePythonVersion(pythonVer, dataOut);
   }

   public static void writeBytes(final byte[] bytes, final DataOutputStream dataOut) {
      PythonWorkerUtils$.MODULE$.writeBytes(bytes, dataOut);
   }

   public static void writeUTF(final String str, final DataOutputStream dataOut) {
      PythonWorkerUtils$.MODULE$.writeUTF(str, dataOut);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return PythonWorkerUtils$.MODULE$.LogStringContext(sc);
   }
}
