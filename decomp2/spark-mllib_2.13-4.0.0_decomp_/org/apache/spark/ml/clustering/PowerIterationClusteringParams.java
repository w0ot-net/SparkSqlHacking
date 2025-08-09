package org.apache.spark.ml.clustering;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasWeightCol;
import scala.Function1;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001BC\u0006\u0011\u0002\u0007\u00051\"\u0006\u0005\u0006W\u0001!\t!\f\u0005\bc\u0001\u0011\r\u0011\"\u00023\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u001d)\u0005A1A\u0005\u0006\u0019CQA\u0016\u0001\u0005\u0002]Cq!\u0017\u0001C\u0002\u0013\u0005a\tC\u0003\\\u0001\u0011\u0005q\u000bC\u0004^\u0001\t\u0007I\u0011\u0001$\t\u000b}\u0003A\u0011A,\u0003=A{w/\u001a:Ji\u0016\u0014\u0018\r^5p]\u000ecWo\u001d;fe&tw\rU1sC6\u001c(B\u0001\u0007\u000e\u0003)\u0019G.^:uKJLgn\u001a\u0006\u0003\u001d=\t!!\u001c7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001cR\u0001\u0001\f\u001dE!\u0002\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\u000f!\u001b\u0005q\"BA\u0010\u000e\u0003\u0015\u0001\u0018M]1n\u0013\t\tcD\u0001\u0004QCJ\fWn\u001d\t\u0003G\u0019j\u0011\u0001\n\u0006\u0003Ky\taa\u001d5be\u0016$\u0017BA\u0014%\u0005)A\u0015m]'bq&#XM\u001d\t\u0003G%J!A\u000b\u0013\u0003\u0019!\u000b7oV3jO\"$8i\u001c7\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012A\f\t\u0003/=J!\u0001\r\r\u0003\tUs\u0017\u000e^\u0001\u0002WV\t1\u0007\u0005\u0002\u001ei%\u0011QG\b\u0002\t\u0013:$\b+\u0019:b[\"\u001a!aN\u001f\u0011\u0005aZT\"A\u001d\u000b\u0005iz\u0011AC1o]>$\u0018\r^5p]&\u0011A(\u000f\u0002\u0006'&t7-Z\u0011\u0002}\u0005)!G\f\u001b/a\u0005!q-\u001a;L+\u0005\t\u0005CA\fC\u0013\t\u0019\u0005DA\u0002J]RD3aA\u001c>\u0003!Ig.\u001b;N_\u0012,W#A$\u0011\u0007uA%*\u0003\u0002J=\t)\u0001+\u0019:b[B\u00111J\u0015\b\u0003\u0019B\u0003\"!\u0014\r\u000e\u00039S!a\u0014\u0017\u0002\rq\u0012xn\u001c;?\u0013\t\t\u0006$\u0001\u0004Qe\u0016$WMZ\u0005\u0003'R\u0013aa\u0015;sS:<'BA)\u0019Q\r!q'P\u0001\fO\u0016$\u0018J\\5u\u001b>$W-F\u0001KQ\r)q'P\u0001\u0007gJ\u001c7i\u001c7)\u0007\u00199T(A\u0005hKR\u001c&oY\"pY\"\u001aqaN\u001f\u0002\r\u0011\u001cHoQ8mQ\rAq'P\u0001\nO\u0016$Hi\u001d;D_2D3!C\u001c>\u0001"
)
public interface PowerIterationClusteringParams extends HasMaxIter, HasWeightCol {
   void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$k_$eq(final IntParam x$1);

   void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$initMode_$eq(final Param x$1);

   void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$srcCol_$eq(final Param x$1);

   void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$dstCol_$eq(final Param x$1);

   IntParam k();

   // $FF: synthetic method
   static int getK$(final PowerIterationClusteringParams $this) {
      return $this.getK();
   }

   default int getK() {
      return BoxesRunTime.unboxToInt(this.$(this.k()));
   }

   Param initMode();

   // $FF: synthetic method
   static String getInitMode$(final PowerIterationClusteringParams $this) {
      return $this.getInitMode();
   }

   default String getInitMode() {
      return (String)this.$(this.initMode());
   }

   Param srcCol();

   // $FF: synthetic method
   static String getSrcCol$(final PowerIterationClusteringParams $this) {
      return $this.getSrcCol();
   }

   default String getSrcCol() {
      return (String)this.getOrDefault(this.srcCol());
   }

   Param dstCol();

   // $FF: synthetic method
   static String getDstCol$(final PowerIterationClusteringParams $this) {
      return $this.getDstCol();
   }

   default String getDstCol() {
      return (String)this.$(this.dstCol());
   }

   // $FF: synthetic method
   static boolean $anonfun$srcCol$1(final String value) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(value));
   }

   // $FF: synthetic method
   static boolean $anonfun$dstCol$1(final String value) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(value));
   }

   static void $init$(final PowerIterationClusteringParams $this) {
      $this.org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$k_$eq(new IntParam($this, "k", "The number of clusters to create. Must be > 1.", ParamValidators$.MODULE$.gt((double)1.0F)));
      Function1 allowedParams = ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"random", "degree"})));
      $this.org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$initMode_$eq(new Param($this, "initMode", "The initialization algorithm. This can be either 'random' to use a random vector as vertex properties, or 'degree' to use a normalized sum of similarities with other vertices.  Supported options: 'random' and 'degree'.", allowedParams, scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$srcCol_$eq(new Param($this, "srcCol", "Name of the input column for source vertex IDs.", (value) -> BoxesRunTime.boxToBoolean($anonfun$srcCol$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$dstCol_$eq(new Param($this, "dstCol", "Name of the input column for destination vertex IDs.", (value) -> BoxesRunTime.boxToBoolean($anonfun$dstCol$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.srcCol().$minus$greater("src"), $this.dstCol().$minus$greater("dst"), $this.k().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(20)), $this.initMode().$minus$greater("random")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
