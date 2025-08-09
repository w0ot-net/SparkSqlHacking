package org.apache.spark.graphx.util;

import org.apache.spark.SparkContext;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Graph;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uv!B\u000e\u001d\u0011\u00039c!B\u0015\u001d\u0011\u0003Q\u0003\"B\u001c\u0002\t\u0003A\u0004bB\u001d\u0002\u0005\u0004%\tA\u000f\u0005\u0007}\u0005\u0001\u000b\u0011B\u001e\t\u000f}\n!\u0019!C\u0001u!1\u0001)\u0001Q\u0001\nmBq!Q\u0001C\u0002\u0013\u0005!\b\u0003\u0004C\u0003\u0001\u0006Ia\u000f\u0005\u0006\u0007\u0006!\t\u0001\u0012\u0005\b?\u0006\t\n\u0011\"\u0001a\u0011\u001dY\u0017!%A\u0005\u00021DqA\\\u0001\u0012\u0002\u0013\u0005A\u000eC\u0004p\u0003E\u0005I\u0011\u00019\t\u000fI\f!\u0019!C\u0001u!11/\u0001Q\u0001\nmBQ\u0001^\u0001\u0005\u0002UD\u0001\"a\u0002\u0002#\u0003%\t\u0001\u001d\u0005\t\u0003\u0013\tA\u0011\u0001\u0011\u0002\f!I\u0011qC\u0001\u0012\u0002\u0013\u0005\u0001\u0005\u001d\u0005\b\u00033\tA\u0011AA\u000e\u0011\u001d\t9#\u0001C\u0005\u0003SAq!!\u001b\u0002\t\u0013\tY\u0007C\u0004\u0002p\u0005!I!!\u001d\t\u000f\u0005=\u0015\u0001\"\u0003\u0002\u0012\"9\u00111U\u0001\u0005\u0002\u0005\u0015\u0006bBAZ\u0003\u0011\u0005\u0011QW\u0001\u0010\u000fJ\f\u0007\u000f[$f]\u0016\u0014\u0018\r^8sg*\u0011QDH\u0001\u0005kRLGN\u0003\u0002 A\u00051qM]1qQbT!!\t\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\r\"\u0013AB1qC\u000eDWMC\u0001&\u0003\ry'oZ\u0002\u0001!\tA\u0013!D\u0001\u001d\u0005=9%/\u00199i\u000f\u0016tWM]1u_J\u001c8cA\u0001,cA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t1\u0011I\\=SK\u001a\u0004\"AM\u001b\u000e\u0003MR!\u0001\u000e\u0011\u0002\u0011%tG/\u001a:oC2L!AN\u001a\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\"\u0012aJ\u0001\u0006%6\u000bE+Y\u000b\u0002wA\u0011A\u0006P\u0005\u0003{5\u0012a\u0001R8vE2,\u0017A\u0002*N\u0003R\u000b\u0007%A\u0003S\u001b\u0006#&-\u0001\u0004S\u001b\u0006#&\rI\u0001\u0006%6\u000bE\u000bZ\u0001\u0007%6\u000bE\u000b\u001a\u0011\u0002\u001d1|wMT8s[\u0006dwI]1qQR9QiT+X3nk\u0006\u0003\u0002$H\u00132k\u0011AH\u0005\u0003\u0011z\u0011Qa\u0012:ba\"\u0004\"\u0001\f&\n\u0005-k#\u0001\u0002'p]\u001e\u0004\"\u0001L'\n\u00059k#aA%oi\")\u0001+\u0003a\u0001#\u0006\u00111o\u0019\t\u0003%Nk\u0011\u0001I\u0005\u0003)\u0002\u0012Ab\u00159be.\u001cuN\u001c;fqRDQAV\u0005A\u00021\u000b1B\\;n-\u0016\u0014H/[2fg\"9\u0001,\u0003I\u0001\u0002\u0004a\u0015!\u00038v[\u0016\u0003\u0016M\u001d;t\u0011\u001dQ\u0016\u0002%AA\u0002m\n!!\\;\t\u000fqK\u0001\u0013!a\u0001w\u0005)1/[4nC\"9a,\u0003I\u0001\u0002\u0004I\u0015\u0001B:fK\u0012\f\u0001\u0004\\8h\u001d>\u0014X.\u00197He\u0006\u0004\b\u000e\n3fM\u0006,H\u000e\u001e\u00134+\u0005\t'F\u0001'cW\u0005\u0019\u0007C\u00013j\u001b\u0005)'B\u00014h\u0003%)hn\u00195fG.,GM\u0003\u0002i[\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005),'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006ABn\\4O_Jl\u0017\r\\$sCBDG\u0005Z3gCVdG\u000f\n\u001b\u0016\u00035T#a\u000f2\u000211|wMT8s[\u0006dwI]1qQ\u0012\"WMZ1vYR$S'\u0001\rm_\u001etuN]7bY\u001e\u0013\u0018\r\u001d5%I\u00164\u0017-\u001e7uIY*\u0012!\u001d\u0016\u0003\u0013\n\fQAU'B)\u000e\faAU'B)\u000e\u0004\u0013aE4f]\u0016\u0014\u0018\r^3SC:$w.\\#eO\u0016\u001cHc\u0002<}}\u0006\u0005\u0011Q\u0001\t\u0004Y]L\u0018B\u0001=.\u0005\u0015\t%O]1z!\r1%\u0010T\u0005\u0003wz\u0011A!\u00123hK\")Q\u0010\u0005a\u0001\u0019\u0006\u00191O]2\t\u000b}\u0004\u0002\u0019\u0001'\u0002\u00119,X.\u00123hKNDa!a\u0001\u0011\u0001\u0004a\u0015aC7bqZ+'\u000f^3y\u0013\u0012DqA\u0018\t\u0011\u0002\u0003\u0007\u0011*A\u000fhK:,'/\u0019;f%\u0006tGm\\7FI\u001e,7\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0003=\u0019\u0018-\u001c9mK2{wMT8s[\u0006dG#\u0003'\u0002\u000e\u0005=\u0011\u0011CA\u000b\u0011\u0015Q&\u00031\u0001<\u0011\u0015a&\u00031\u0001<\u0011\u0019\t\u0019B\u0005a\u0001\u0019\u00061Q.\u0019=WC2DqA\u0018\n\u0011\u0002\u0003\u0007\u0011*A\rtC6\u0004H.\u001a'pO:{'/\\1mI\u0011,g-Y;mi\u0012\"\u0014!\u0003:nCR<%/\u00199i)!\ti\"a\b\u0002\"\u0005\u0015\u0002\u0003\u0002$H\u00192CQ\u0001\u0015\u000bA\u0002ECa!a\t\u0015\u0001\u0004a\u0015\u0001\u0006:fcV,7\u000f^3e\u001dVlg+\u001a:uS\u000e,7\u000fC\u0003\u0000)\u0001\u0007A*\u0001\npkR$Um\u001a:fK\u001a\u0013x.\\#eO\u0016\u001cX\u0003BA\u0016\u0003k!B!!\f\u0002XQ!\u0011qFA$!\u00151u\tTA\u0019!\u0011\t\u0019$!\u000e\r\u0001\u00119\u0011qG\u000bC\u0002\u0005e\"AA#E#\u0011\tY$!\u0011\u0011\u00071\ni$C\u0002\u0002@5\u0012qAT8uQ&tw\rE\u0002-\u0003\u0007J1!!\u0012.\u0005\r\te.\u001f\u0005\n\u0003\u0013*\u0012\u0011!a\u0002\u0003\u0017\n!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\ti%a\u0015\u000225\u0011\u0011q\n\u0006\u0004\u0003#j\u0013a\u0002:fM2,7\r^\u0005\u0005\u0003+\nyE\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u001d\tI&\u0006a\u0001\u00037\nQ!\u001a3hKN\u0004b!!\u0018\u0002d\u0005\u001dTBAA0\u0015\r\t\t\u0007I\u0001\u0004e\u0012$\u0017\u0002BA3\u0003?\u00121A\u0015#E!\u00111%0!\r\u0002\u000f\u0005$G-\u00123hKR\u0019\u00110!\u001c\t\u000bY3\u0002\u0019\u0001'\u0002\u0015\rDwn\\:f\u0007\u0016dG\u000e\u0006\u0005\u0002t\u0005e\u0014QPAA!\u0015a\u0013Q\u000f'M\u0013\r\t9(\f\u0002\u0007)V\u0004H.\u001a\u001a\t\r\u0005mt\u00031\u0001M\u0003\u0005A\bBBA@/\u0001\u0007A*A\u0001z\u0011\u0019\t\u0019i\u0006a\u0001\u0019\u0006\tA\u000fK\u0002\u0018\u0003\u000f\u0003B!!#\u0002\f6\tq-C\u0002\u0002\u000e\u001e\u0014q\u0001^1jYJ,7-\u0001\u0007qS\u000e\\\u0017+^1ee\u0006tG\u000fF\u0005M\u0003'\u000b9*a'\u0002 \"1\u0011Q\u0013\rA\u0002m\n\u0011!\u0019\u0005\u0007\u00033C\u0002\u0019A\u001e\u0002\u0003\tDa!!(\u0019\u0001\u0004Y\u0014!A2\t\r\u0005\u0005\u0006\u00041\u0001<\u0003\u0005!\u0017!C4sS\u0012<%/\u00199i)!\t9+!+\u0002,\u0006=\u0006#\u0002$H\u0003gZ\u0004\"\u0002)\u001a\u0001\u0004\t\u0006BBAW3\u0001\u0007A*\u0001\u0003s_^\u001c\bBBAY3\u0001\u0007A*\u0001\u0003d_2\u001c\u0018!C:uCJ<%/\u00199i)\u0019\ti\"a.\u0002:\")\u0001K\u0007a\u0001#\"1\u00111\u0018\u000eA\u00021\u000baA\u001c<feR\u001c\b"
)
public final class GraphGenerators {
   public static Graph starGraph(final SparkContext sc, final int nverts) {
      return GraphGenerators$.MODULE$.starGraph(sc, nverts);
   }

   public static Graph gridGraph(final SparkContext sc, final int rows, final int cols) {
      return GraphGenerators$.MODULE$.gridGraph(sc, rows, cols);
   }

   public static Graph rmatGraph(final SparkContext sc, final int requestedNumVertices, final int numEdges) {
      return GraphGenerators$.MODULE$.rmatGraph(sc, requestedNumVertices, numEdges);
   }

   public static long generateRandomEdges$default$4() {
      return GraphGenerators$.MODULE$.generateRandomEdges$default$4();
   }

   public static Edge[] generateRandomEdges(final int src, final int numEdges, final int maxVertexId, final long seed) {
      return GraphGenerators$.MODULE$.generateRandomEdges(src, numEdges, maxVertexId, seed);
   }

   public static double RMATc() {
      return GraphGenerators$.MODULE$.RMATc();
   }

   public static long logNormalGraph$default$6() {
      return GraphGenerators$.MODULE$.logNormalGraph$default$6();
   }

   public static double logNormalGraph$default$5() {
      return GraphGenerators$.MODULE$.logNormalGraph$default$5();
   }

   public static double logNormalGraph$default$4() {
      return GraphGenerators$.MODULE$.logNormalGraph$default$4();
   }

   public static int logNormalGraph$default$3() {
      return GraphGenerators$.MODULE$.logNormalGraph$default$3();
   }

   public static Graph logNormalGraph(final SparkContext sc, final int numVertices, final int numEParts, final double mu, final double sigma, final long seed) {
      return GraphGenerators$.MODULE$.logNormalGraph(sc, numVertices, numEParts, mu, sigma, seed);
   }

   public static double RMATd() {
      return GraphGenerators$.MODULE$.RMATd();
   }

   public static double RMATb() {
      return GraphGenerators$.MODULE$.RMATb();
   }

   public static double RMATa() {
      return GraphGenerators$.MODULE$.RMATa();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return GraphGenerators$.MODULE$.LogStringContext(sc);
   }
}
