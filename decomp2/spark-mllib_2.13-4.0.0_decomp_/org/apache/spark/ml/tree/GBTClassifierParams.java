package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.mllib.tree.loss.ClassificationLoss;
import org.apache.spark.mllib.tree.loss.LogLoss$;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005!<aa\u0003\u0007\t\u000291bA\u0002\r\r\u0011\u0003q\u0011\u0004C\u0003)\u0003\u0011\u0005!\u0006C\u0004,\u0003\t\u0007IQ\u0001\u0017\t\rm\n\u0001\u0015!\u0004.\u0011\u001da\u0014!!A\u0005\nu2\u0001\u0002\u0007\u0007\u0011\u0002\u0007\u0005a\u0002\u0012\u0005\u0006\u001d\u001a!\ta\u0014\u0005\b'\u001a\u0011\r\u0011\"\u0001U\u0011\u0015Yf\u0001\"\u0001]\u0011\u0019if\u0001\"\u0011\u000f=\u0006\u0019rI\u0011+DY\u0006\u001c8/\u001b4jKJ\u0004\u0016M]1ng*\u0011QBD\u0001\u0005iJ,WM\u0003\u0002\u0010!\u0005\u0011Q\u000e\u001c\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sOB\u0011q#A\u0007\u0002\u0019\t\u0019rI\u0011+DY\u0006\u001c8/\u001b4jKJ\u0004\u0016M]1ngN\u0019\u0011A\u0007\u0011\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\r\u0005s\u0017PU3g!\t\tc%D\u0001#\u0015\t\u0019C%\u0001\u0002j_*\tQ%\u0001\u0003kCZ\f\u0017BA\u0014#\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\f\u0002%M,\b\u000f]8si\u0016$Gj\\:t)f\u0004Xm]\u000b\u0002[A\u00191D\f\u0019\n\u0005=b\"!B!se\u0006L\bCA\u00199\u001d\t\u0011d\u0007\u0005\u0002495\tAG\u0003\u00026S\u00051AH]8pizJ!a\u000e\u000f\u0002\rA\u0013X\rZ3g\u0013\tI$H\u0001\u0004TiJLgn\u001a\u0006\u0003oq\t1c];qa>\u0014H/\u001a3M_N\u001cH+\u001f9fg\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012A\u0010\t\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003\u0012\nA\u0001\\1oO&\u00111\t\u0011\u0002\u0007\u001f\nTWm\u0019;\u0014\u000b\u0019QR\tS&\u0011\u0005]1\u0015BA$\r\u0005%9%\t\u0016)be\u0006l7\u000f\u0005\u0002\u0018\u0013&\u0011!\n\u0004\u0002\u001d)J,W-\u00128tK6\u0014G.Z\"mCN\u001c\u0018NZ5feB\u000b'/Y7t!\t9B*\u0003\u0002N\u0019\t\u0019\u0002*Y:WCJL\u0017M\\2f\u00136\u0004XO]5us\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0015\t\u00037EK!A\u0015\u000f\u0003\tUs\u0017\u000e^\u0001\tY>\u001c8\u000fV=qKV\tQ\u000bE\u0002W3Bj\u0011a\u0016\u0006\u00031:\tQ\u0001]1sC6L!AW,\u0003\u000bA\u000b'/Y7\u0002\u0017\u001d,G\u000fT8tgRK\b/Z\u000b\u0002a\u0005qq-\u001a;PY\u0012dun]:UsB,W#A0\u0011\u0005\u00014W\"A1\u000b\u0005\t\u001c\u0017\u0001\u00027pgNT!!\u00043\u000b\u0005\u0015\u0004\u0012!B7mY&\u0014\u0017BA4b\u0005I\u0019E.Y:tS\u001aL7-\u0019;j_:dun]:"
)
public interface GBTClassifierParams extends GBTParams, TreeEnsembleClassifierParams, HasVarianceImpurity {
   static String[] supportedLossTypes() {
      return GBTClassifierParams$.MODULE$.supportedLossTypes();
   }

   void org$apache$spark$ml$tree$GBTClassifierParams$_setter_$lossType_$eq(final Param x$1);

   Param lossType();

   // $FF: synthetic method
   static String getLossType$(final GBTClassifierParams $this) {
      return $this.getLossType();
   }

   default String getLossType() {
      return ((String)this.$(this.lossType())).toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static ClassificationLoss getOldLossType$(final GBTClassifierParams $this) {
      return $this.getOldLossType();
   }

   default ClassificationLoss getOldLossType() {
      String var2 = this.getLossType();
      switch (var2 == null ? 0 : var2.hashCode()) {
         case 2022928992:
            if ("logistic".equals(var2)) {
               return LogLoss$.MODULE$;
            }
         default:
            throw new RuntimeException("GBTClassifier was given bad loss type: " + this.getLossType());
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$lossType$1(final String value) {
      return .MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])GBTClassifierParams$.MODULE$.supportedLossTypes()), value.toLowerCase(Locale.ROOT));
   }

   static void $init$(final GBTClassifierParams $this) {
      $this.org$apache$spark$ml$tree$GBTClassifierParams$_setter_$lossType_$eq(new Param($this, "lossType", "Loss function which GBT tries to minimize (case-insensitive). Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])GBTClassifierParams$.MODULE$.supportedLossTypes()).mkString(", "), (value) -> BoxesRunTime.boxToBoolean($anonfun$lossType$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.lossType().$minus$greater("logistic")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
