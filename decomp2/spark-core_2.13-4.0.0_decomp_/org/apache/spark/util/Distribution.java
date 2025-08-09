package org.apache.spark.util;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Option;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055b!B\u000e\u001d\u0001y!\u0003\u0002C\u0016\u0001\u0005\u000b\u0007I\u0011A\u0017\t\u0011Q\u0002!\u0011!Q\u0001\n9B\u0001\"\u000e\u0001\u0003\u0006\u0004%\tA\u000e\u0005\tu\u0001\u0011\t\u0011)A\u0005o!A1\b\u0001BC\u0002\u0013\u0005a\u0007\u0003\u0005=\u0001\t\u0005\t\u0015!\u00038\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u0015i\u0004\u0001\"\u0001E\u0011\u001d\u0011\u0006A1A\u0005\u0002YBaa\u0015\u0001!\u0002\u00139\u0004b\u0002+\u0001\u0005\u0004%\t!\f\u0005\u0007+\u0002\u0001\u000b\u0011\u0002\u0018\t\u000bY\u0003A\u0011A,\t\u000fu\u0003\u0011\u0013!C\u0001=\")\u0011\u000e\u0001C\u0005U\")Q\u000e\u0001C\u0001]\"9A\u0010AI\u0001\n\u0003i\bBB@\u0001\t\u0003\t\t\u0001C\u0004\u0002\n\u0001!\t!a\u0003\t\u0011\u0005=\u0001!%A\u0005\u0002u<\u0001\"!\u0005\u001d\u0011\u0003q\u00121\u0003\u0004\b7qA\tAHA\u000b\u0011\u0019id\u0003\"\u0001\u0002\u0018!9\u0011\u0011\u0004\f\u0005\u0002\u0005m\u0001BB7\u0017\t\u0003\t)\u0003C\u0004}-E\u0005I\u0011A?\u0003\u0019\u0011K7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005uq\u0012\u0001B;uS2T!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\n\u0003\u0001\u0015\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0017\u0001\u00023bi\u0006\u001c\u0001!F\u0001/!\r1s&M\u0005\u0003a\u001d\u0012Q!\u0011:sCf\u0004\"A\n\u001a\n\u0005M:#A\u0002#pk\ndW-A\u0003eCR\f\u0007%\u0001\u0005ti\u0006\u0014H/\u00133y+\u00059\u0004C\u0001\u00149\u0013\tItEA\u0002J]R\f\u0011b\u001d;beRLE\r\u001f\u0011\u0002\r\u0015tG-\u00133y\u0003\u001d)g\u000eZ%eq\u0002\na\u0001P5oSRtD\u0003B B\u0005\u000e\u0003\"\u0001\u0011\u0001\u000e\u0003qAQaK\u0004A\u00029BQ!N\u0004A\u0002]BQaO\u0004A\u0002]\"\"aP#\t\u000b-B\u0001\u0019\u0001$\u0011\u0007\u001d{\u0015G\u0004\u0002I\u001b:\u0011\u0011\nT\u0007\u0002\u0015*\u00111\nL\u0001\u0007yI|w\u000e\u001e \n\u0003!J!AT\u0014\u0002\u000fA\f7m[1hK&\u0011\u0001+\u0015\u0002\t\u0013R,'/\u00192mK*\u0011ajJ\u0001\u0007Y\u0016tw\r\u001e5\u0002\u000f1,gn\u001a;iA\u0005!B-\u001a4bk2$\bK]8cC\nLG.\u001b;jKN\fQ\u0003Z3gCVdG\u000f\u0015:pE\u0006\u0014\u0017\u000e\\5uS\u0016\u001c\b%\u0001\u0007hKR\fV/\u00198uS2,7\u000f\u0006\u0002Y7B\u0019q)W\u0019\n\u0005i\u000b&AC%oI\u0016DX\rZ*fc\"9A,\u0004I\u0001\u0002\u00041\u0015!\u00049s_\n\f'-\u001b7ji&,7/\u0001\fhKR\fV/\u00198uS2,7\u000f\n3fM\u0006,H\u000e\u001e\u00132+\u0005y&F\u0001$aW\u0005\t\u0007C\u00012h\u001b\u0005\u0019'B\u00013f\u0003%)hn\u00195fG.,GM\u0003\u0002gO\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005!\u001c'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006a1\r\\8tKN$\u0018J\u001c3fqR\u0011qg\u001b\u0005\u0006Y>\u0001\r!M\u0001\u0002a\u0006i1\u000f[8x#V\fg\u000e^5mKN$\"a\u001c:\u0011\u0005\u0019\u0002\u0018BA9(\u0005\u0011)f.\u001b;\t\u000fM\u0004\u0002\u0013!a\u0001i\u0006\u0019q.\u001e;\u0011\u0005UTX\"\u0001<\u000b\u0005]D\u0018AA5p\u0015\u0005I\u0018\u0001\u00026bm\u0006L!a\u001f<\u0003\u0017A\u0013\u0018N\u001c;TiJ,\u0017-\\\u0001\u0018g\"|w/U;b]RLG.Z:%I\u00164\u0017-\u001e7uIE*\u0012A \u0016\u0003i\u0002\f1b\u001d;bi\u000e{WO\u001c;feV\u0011\u00111\u0001\t\u0004\u0001\u0006\u0015\u0011bAA\u00049\tY1\u000b^1u\u0007>,h\u000e^3s\u0003\u001d\u0019X/\\7bef$2a\\A\u0007\u0011\u001d\u00198\u0003%AA\u0002Q\f\u0011c];n[\u0006\u0014\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132\u00031!\u0015n\u001d;sS\n,H/[8o!\t\u0001ec\u0005\u0002\u0017KQ\u0011\u00111C\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003;\t\u0019\u0003\u0005\u0003'\u0003?y\u0014bAA\u0011O\t1q\n\u001d;j_:DQa\u000b\rA\u0002\u0019#Ra\\A\u0014\u0003SAqa]\r\u0011\u0002\u0003\u0007A\u000f\u0003\u0004\u0002,e\u0001\rAR\u0001\ncV\fg\u000e^5mKN\u0004"
)
public class Distribution {
   private final double[] data;
   private final int startIdx;
   private final int endIdx;
   private final int length;
   private final double[] defaultProbabilities;

   public static Option apply(final Iterable data) {
      return Distribution$.MODULE$.apply(data);
   }

   public double[] data() {
      return this.data;
   }

   public int startIdx() {
      return this.startIdx;
   }

   public int endIdx() {
      return this.endIdx;
   }

   public int length() {
      return this.length;
   }

   public double[] defaultProbabilities() {
      return this.defaultProbabilities;
   }

   public IndexedSeq getQuantiles(final Iterable probabilities) {
      return (IndexedSeq)probabilities.toIndexedSeq().map((JFunction1.mcDD.sp)(p) -> this.data()[this.closestIndex(p)]);
   }

   public Iterable getQuantiles$default$1() {
      return .MODULE$.wrapDoubleArray(this.defaultProbabilities());
   }

   private int closestIndex(final double p) {
      return scala.math.package..MODULE$.min((int)(p * (double)this.length()) + this.startIdx(), this.endIdx() - 1);
   }

   public void showQuantiles(final PrintStream out) {
      out.println("min\t25%\t50%\t75%\tmax");
      this.getQuantiles(.MODULE$.wrapDoubleArray(this.defaultProbabilities())).foreach((JFunction1.mcVD.sp)(q) -> out.print(q + "\t"));
      out.println();
   }

   public PrintStream showQuantiles$default$1() {
      return System.out;
   }

   public StatCounter statCounter() {
      return StatCounter$.MODULE$.apply((IterableOnce).MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.doubleArrayOps(this.data()), this.startIdx(), this.endIdx())));
   }

   public void summary(final PrintStream out) {
      out.println(this.statCounter());
      this.showQuantiles(out);
   }

   public PrintStream summary$default$1() {
      return System.out;
   }

   public Distribution(final double[] data, final int startIdx, final int endIdx) {
      this.data = data;
      this.startIdx = startIdx;
      this.endIdx = endIdx;
      .MODULE$.require(startIdx < endIdx);
      Arrays.sort(data, startIdx, endIdx);
      this.length = endIdx - startIdx;
      this.defaultProbabilities = new double[]{(double)0.0F, (double)0.25F, (double)0.5F, (double)0.75F, (double)1.0F};
   }

   public Distribution(final Iterable data) {
      this((double[])data.toArray(scala.reflect.ClassTag..MODULE$.Double()), 0, data.size());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
