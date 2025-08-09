package org.apache.spark.graphx;

import org.apache.spark.SparkContext;
import org.apache.spark.internal.Logging;
import org.apache.spark.storage.StorageLevel;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r<Q\u0001C\u0005\t\u0002I1Q\u0001F\u0005\t\u0002UAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005\u0002\u0015Bq\u0001U\u0001\u0012\u0002\u0013\u0005\u0011\u000bC\u0004]\u0003E\u0005I\u0011A/\t\u000f}\u000b\u0011\u0013!C\u0001A\"9!-AI\u0001\n\u0003\u0001\u0017aC$sCBDGj\\1eKJT!AC\u0006\u0002\r\u001d\u0014\u0018\r\u001d5y\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7\u0001\u0001\t\u0003'\u0005i\u0011!\u0003\u0002\f\u000fJ\f\u0007\u000f\u001b'pC\u0012,'oE\u0002\u0002-q\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\u000f!\u001b\u0005q\"BA\u0010\f\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0011\u001f\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRtD#\u0001\n\u0002\u0019\u0015$w-\u001a'jgR4\u0015\u000e\\3\u0015\u000f\u0019b#g\u0010#G\u001dB!1cJ\u0015*\u0013\tA\u0013BA\u0003He\u0006\u0004\b\u000e\u0005\u0002\u0018U%\u00111\u0006\u0007\u0002\u0004\u0013:$\b\"B\u0017\u0004\u0001\u0004q\u0013AA:d!\ty\u0003'D\u0001\f\u0013\t\t4B\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u00034\u0007\u0001\u0007A'\u0001\u0003qCRD\u0007CA\u001b=\u001d\t1$\b\u0005\u0002815\t\u0001H\u0003\u0002:#\u00051AH]8pizJ!a\u000f\r\u0002\rA\u0013X\rZ3g\u0013\tidH\u0001\u0004TiJLgn\u001a\u0006\u0003waAq\u0001Q\u0002\u0011\u0002\u0003\u0007\u0011)\u0001\u000bdC:|g.[2bY>\u0013\u0018.\u001a8uCRLwN\u001c\t\u0003/\tK!a\u0011\r\u0003\u000f\t{w\u000e\\3b]\"9Qi\u0001I\u0001\u0002\u0004I\u0013!\u00058v[\u0016#w-\u001a)beRLG/[8og\"9qi\u0001I\u0001\u0002\u0004A\u0015\u0001E3eO\u0016\u001cFo\u001c:bO\u0016dUM^3m!\tIE*D\u0001K\u0015\tY5\"A\u0004ti>\u0014\u0018mZ3\n\u00055S%\u0001D*u_J\fw-\u001a'fm\u0016d\u0007bB(\u0004!\u0003\u0005\r\u0001S\u0001\u0013m\u0016\u0014H/\u001a=Ti>\u0014\u0018mZ3MKZ,G.\u0001\ffI\u001e,G*[:u\r&dW\r\n3fM\u0006,H\u000e\u001e\u00134+\u0005\u0011&FA!TW\u0005!\u0006CA+[\u001b\u00051&BA,Y\u0003%)hn\u00195fG.,GM\u0003\u0002Z1\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005m3&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00061R\rZ4f\u0019&\u001cHOR5mK\u0012\"WMZ1vYR$C'F\u0001_U\tI3+\u0001\ffI\u001e,G*[:u\r&dW\r\n3fM\u0006,H\u000e\u001e\u00136+\u0005\t'F\u0001%T\u0003Y)GmZ3MSN$h)\u001b7fI\u0011,g-Y;mi\u00122\u0004"
)
public final class GraphLoader {
   public static StorageLevel edgeListFile$default$6() {
      return GraphLoader$.MODULE$.edgeListFile$default$6();
   }

   public static StorageLevel edgeListFile$default$5() {
      return GraphLoader$.MODULE$.edgeListFile$default$5();
   }

   public static int edgeListFile$default$4() {
      return GraphLoader$.MODULE$.edgeListFile$default$4();
   }

   public static boolean edgeListFile$default$3() {
      return GraphLoader$.MODULE$.edgeListFile$default$3();
   }

   public static Graph edgeListFile(final SparkContext sc, final String path, final boolean canonicalOrientation, final int numEdgePartitions, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel) {
      return GraphLoader$.MODULE$.edgeListFile(sc, path, canonicalOrientation, numEdgePartitions, edgeStorageLevel, vertexStorageLevel);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return GraphLoader$.MODULE$.LogStringContext(sc);
   }
}
