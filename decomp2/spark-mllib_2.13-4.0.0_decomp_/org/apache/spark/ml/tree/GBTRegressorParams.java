package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.mllib.tree.loss.AbsoluteError$;
import org.apache.spark.mllib.tree.loss.Loss;
import org.apache.spark.mllib.tree.loss.SquaredError$;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u00051<a\u0001D\u0007\t\u0002=9bAB\r\u000e\u0011\u0003y!\u0004C\u0003*\u0003\u0011\u00051\u0006C\u0004-\u0003\t\u0007IQA\u0017\t\rq\n\u0001\u0015!\u0004/\u0011\u001di\u0014!!A\u0005\ny2\u0001\"G\u0007\u0011\u0002\u0007\u0005q\"\u0012\u0005\u0006\u001f\u001a!\t\u0001\u0015\u0005\b)\u001a\u0011\r\u0011\"\u0001V\u0011\u0015af\u0001\"\u0001^\u0011\u0019qf\u0001\"\u0011\u0010?\"1\u0011N\u0002C\u0001\u001f)\f!c\u0012\"U%\u0016<'/Z:t_J\u0004\u0016M]1ng*\u0011abD\u0001\u0005iJ,WM\u0003\u0002\u0011#\u0005\u0011Q\u000e\u001c\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sOB\u0011\u0001$A\u0007\u0002\u001b\t\u0011rI\u0011+SK\u001e\u0014Xm]:peB\u000b'/Y7t'\r\t1$\t\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\t:S\"A\u0012\u000b\u0005\u0011*\u0013AA5p\u0015\u00051\u0013\u0001\u00026bm\u0006L!\u0001K\u0012\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?\u0007\u0001!\u0012aF\u0001\u0013gV\u0004\bo\u001c:uK\u0012dun]:UsB,7/F\u0001/!\rar&M\u0005\u0003au\u0011Q!\u0011:sCf\u0004\"AM\u001d\u000f\u0005M:\u0004C\u0001\u001b\u001e\u001b\u0005)$B\u0001\u001c+\u0003\u0019a$o\\8u}%\u0011\u0001(H\u0001\u0007!J,G-\u001a4\n\u0005iZ$AB*ue&twM\u0003\u00029;\u0005\u00192/\u001e9q_J$X\r\u001a'pgN$\u0016\u0010]3tA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\tq\b\u0005\u0002A\u00076\t\u0011I\u0003\u0002CK\u0005!A.\u00198h\u0013\t!\u0015I\u0001\u0004PE*,7\r^\n\u0006\rm1\u0015\n\u0014\t\u00031\u001dK!\u0001S\u0007\u0003\u0013\u001d\u0013E\u000bU1sC6\u001c\bC\u0001\rK\u0013\tYUBA\u000eUe\u0016,WI\\:f[\ndWMU3he\u0016\u001c8o\u001c:QCJ\fWn\u001d\t\u000315K!AT\u0007\u0003'Q\u0013X-\u001a*fOJ,7o]8s!\u0006\u0014\u0018-\\:\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0006C\u0001\u000fS\u0013\t\u0019VD\u0001\u0003V]&$\u0018\u0001\u00037pgN$\u0016\u0010]3\u0016\u0003Y\u00032a\u0016.2\u001b\u0005A&BA-\u0010\u0003\u0015\u0001\u0018M]1n\u0013\tY\u0006LA\u0003QCJ\fW.A\u0006hKRdun]:UsB,W#A\u0019\u0002\u001d\u001d,Go\u00147e\u0019>\u001c8\u000fV=qKV\t\u0001\r\u0005\u0002bO6\t!M\u0003\u0002dI\u0006!An\\:t\u0015\tqQM\u0003\u0002g#\u0005)Q\u000e\u001c7jE&\u0011\u0001N\u0019\u0002\u0005\u0019>\u001c8/\u0001\u000bd_:4XM\u001d;U_>cG\rT8tgRK\b/\u001a\u000b\u0003A.DQaY\u0006A\u0002E\u0002"
)
public interface GBTRegressorParams extends GBTParams, TreeEnsembleRegressorParams, TreeRegressorParams {
   static String[] supportedLossTypes() {
      return GBTRegressorParams$.MODULE$.supportedLossTypes();
   }

   void org$apache$spark$ml$tree$GBTRegressorParams$_setter_$lossType_$eq(final Param x$1);

   Param lossType();

   // $FF: synthetic method
   static String getLossType$(final GBTRegressorParams $this) {
      return $this.getLossType();
   }

   default String getLossType() {
      return ((String)this.$(this.lossType())).toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static Loss getOldLossType$(final GBTRegressorParams $this) {
      return $this.getOldLossType();
   }

   default Loss getOldLossType() {
      return this.convertToOldLossType(this.getLossType());
   }

   // $FF: synthetic method
   static Loss convertToOldLossType$(final GBTRegressorParams $this, final String loss) {
      return $this.convertToOldLossType(loss);
   }

   default Loss convertToOldLossType(final String loss) {
      switch (loss == null ? 0 : loss.hashCode()) {
         case -1965110553:
            if ("squared".equals(loss)) {
               return SquaredError$.MODULE$;
            }
            break;
         case 1728122231:
            if ("absolute".equals(loss)) {
               return AbsoluteError$.MODULE$;
            }
      }

      throw new RuntimeException("GBTRegressorParams was given bad loss type: " + this.getLossType());
   }

   // $FF: synthetic method
   static boolean $anonfun$lossType$2(final String value) {
      return .MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])GBTRegressorParams$.MODULE$.supportedLossTypes()), value.toLowerCase(Locale.ROOT));
   }

   static void $init$(final GBTRegressorParams $this) {
      $this.org$apache$spark$ml$tree$GBTRegressorParams$_setter_$lossType_$eq(new Param($this, "lossType", "Loss function which GBT tries to minimize (case-insensitive). Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])GBTRegressorParams$.MODULE$.supportedLossTypes()).mkString(", "), (value) -> BoxesRunTime.boxToBoolean($anonfun$lossType$2(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.lossType().$minus$greater("squared")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
