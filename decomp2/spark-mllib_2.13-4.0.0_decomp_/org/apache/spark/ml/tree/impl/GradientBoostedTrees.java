package org.apache.spark.ml.tree.impl;

import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.ml.tree.Split;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.loss.Loss;
import org.apache.spark.rdd.RDD;
import scala.Enumeration;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005exA\u0002\t\u0012\u0011\u00039RD\u0002\u0004 #!\u0005q\u0003\t\u0005\u0006[\u0005!\ta\f\u0005\u0006a\u0005!\t!\r\u0005\bq\u0006\t\n\u0011\"\u0001z\u0011\u001d\tI!\u0001C\u0001\u0003\u0017A\u0001\"a\u0007\u0002#\u0003%\t!\u001f\u0005\b\u0003;\tA\u0011AA\u0010\u0011\u001d\t\u0019'\u0001C\u0001\u0003KBq!a\u001e\u0002\t\u0003\tI\bC\u0004\u0002x\u0005!\t!!$\t\u000f\u0005\u0015\u0016\u0001\"\u0001\u0002(\"9\u0011QU\u0001\u0005\u0002\u0005U\u0006bBA_\u0003\u0011\u0005\u0011q\u0018\u0005\b\u0003;\fA\u0011AAp\u0011!\t90AI\u0001\n\u0003I\u0018\u0001F$sC\u0012LWM\u001c;C_>\u001cH/\u001a3Ue\u0016,7O\u0003\u0002\u0013'\u0005!\u0011.\u001c9m\u0015\t!R#\u0001\u0003ue\u0016,'B\u0001\f\u0018\u0003\tiGN\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h!\tq\u0012!D\u0001\u0012\u0005Q9%/\u00193jK:$(i\\8ti\u0016$GK]3fgN\u0019\u0011!I\u0014\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\tA3&D\u0001*\u0015\tQs#\u0001\u0005j]R,'O\\1m\u0013\ta\u0013FA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!H\u0001\u0004eVtGC\u0002\u001aC!n\u0003W\u000e\u0005\u0003#gUr\u0014B\u0001\u001b$\u0005\u0019!V\u000f\u001d7feA\u0019!E\u000e\u001d\n\u0005]\u001a#!B!se\u0006L\bCA\u001d=\u001b\u0005Q$BA\u001e\u0016\u0003)\u0011Xm\u001a:fgNLwN\\\u0005\u0003{i\u00121\u0004R3dSNLwN\u001c+sK\u0016\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007c\u0001\u00127\u007fA\u0011!\u0005Q\u0005\u0003\u0003\u000e\u0012a\u0001R8vE2,\u0007\"B\"\u0004\u0001\u0004!\u0015!B5oaV$\bcA#I\u00156\taI\u0003\u0002H/\u0005\u0019!\u000f\u001a3\n\u0005%3%a\u0001*E\tB\u00111JT\u0007\u0002\u0019*\u0011Q*F\u0001\bM\u0016\fG/\u001e:f\u0013\tyEJ\u0001\u0005J]N$\u0018M\\2f\u0011\u0015\t6\u00011\u0001S\u0003A\u0011wn\\:uS:<7\u000b\u001e:bi\u0016<\u0017\u0010\u0005\u0002T36\tAK\u0003\u0002V-\u0006i1m\u001c8gS\u001e,(/\u0019;j_:T!\u0001F,\u000b\u0005a;\u0012!B7mY&\u0014\u0017B\u0001.U\u0005A\u0011un\\:uS:<7\u000b\u001e:bi\u0016<\u0017\u0010C\u0003]\u0007\u0001\u0007Q,\u0001\u0003tK\u0016$\u0007C\u0001\u0012_\u0013\ty6E\u0001\u0003M_:<\u0007\"B1\u0004\u0001\u0004\u0011\u0017!\u00064fCR,(/Z*vEN,Go\u0015;sCR,w-\u001f\t\u0003G*t!\u0001\u001a5\u0011\u0005\u0015\u001cS\"\u00014\u000b\u0005\u001dt\u0013A\u0002\u001fs_>$h(\u0003\u0002jG\u00051\u0001K]3eK\u001aL!a\u001b7\u0003\rM#(/\u001b8h\u0015\tI7\u0005C\u0004o\u0007A\u0005\t\u0019A8\u0002\u000b%t7\u000f\u001e:\u0011\u0007\t\u0002(/\u0003\u0002rG\t1q\n\u001d;j_:\u0004\"a\u001d<\u000e\u0003QT!!^\u000b\u0002\tU$\u0018\u000e\\\u0005\u0003oR\u0014q\"\u00138tiJ,X.\u001a8uCRLwN\\\u0001\u000eeVtG\u0005Z3gCVdG\u000fJ\u001b\u0016\u0003iT#a\\>,\u0003q\u00042!`A\u0003\u001b\u0005q(bA@\u0002\u0002\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u0007\u0019\u0013AC1o]>$\u0018\r^5p]&\u0019\u0011q\u0001@\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\tsk:<\u0016\u000e\u001e5WC2LG-\u0019;j_:$RBMA\u0007\u0003\u001f\t\u0019\"!\u0006\u0002\u0018\u0005e\u0001\"B\"\u0006\u0001\u0004!\u0005BBA\t\u000b\u0001\u0007A)A\bwC2LG-\u0019;j_:Le\u000e];u\u0011\u0015\tV\u00011\u0001S\u0011\u0015aV\u00011\u0001^\u0011\u0015\tW\u00011\u0001c\u0011\u001dqW\u0001%AA\u0002=\f1D];o/&$\bNV1mS\u0012\fG/[8oI\u0011,g-Y;mi\u00122\u0014\u0001I2p[B,H/Z%oSRL\u0017\r\u001c)sK\u0012L7\r^5p]\u0006sG-\u0012:s_J$B\"!\t\u0002&\u0005E\u0012QGA\u001d\u0003\u000f\u0002B!\u0012%\u0002$A!!eM @\u0011\u001d\t9c\u0002a\u0001\u0003S\tA\u0001Z1uCB!Q\tSA\u0016!\rq\u0012QF\u0005\u0004\u0003_\t\"!\u0003+sK\u0016\u0004v.\u001b8u\u0011\u0019\t\u0019d\u0002a\u0001\u007f\u0005q\u0011N\\5u)J,WmV3jO\"$\bBBA\u001c\u000f\u0001\u0007\u0001(\u0001\u0005j]&$HK]3f\u0011\u001d\tYd\u0002a\u0001\u0003{\tA\u0001\\8tgB!\u0011qHA\"\u001b\t\t\tEC\u0002\u0002<YKA!!\u0012\u0002B\t!Aj\\:t\u0011\u001d\tIe\u0002a\u0001\u0003\u0017\n\u0001BY2Ta2LGo\u001d\t\u0007\u0003\u001b\n\u0019&a\u0016\u000e\u0005\u0005=#bAA)/\u0005I!M]8bI\u000e\f7\u000f^\u0005\u0005\u0003+\nyEA\u0005Ce>\fGmY1tiB!!ENA-!\u0011\u0011c'a\u0017\u0011\t\u0005u\u0013qL\u0007\u0002'%\u0019\u0011\u0011M\n\u0003\u000bM\u0003H.\u001b;\u0002+U\u0004H-\u0019;f!J,G-[2uS>tWI\u001d:peRq\u0011\u0011EA4\u0003S\ni'!\u001d\u0002t\u0005U\u0004bBA\u0014\u0011\u0001\u0007\u0011\u0011\u0006\u0005\b\u0003WB\u0001\u0019AA\u0011\u0003I\u0001(/\u001a3jGRLwN\\!oI\u0016\u0013(o\u001c:\t\r\u0005=\u0004\u00021\u0001@\u0003)!(/Z3XK&<\u0007\u000e\u001e\u0005\u0006)!\u0001\r\u0001\u000f\u0005\b\u0003wA\u0001\u0019AA\u001f\u0011\u001d\tI\u0005\u0003a\u0001\u0003\u0017\n\u0001#\u001e9eCR,\u0007K]3eS\u000e$\u0018n\u001c8\u0015\u0017}\nY(a \u0002\u0004\u0006\u0015\u0015\u0011\u0012\u0005\b\u0003{J\u0001\u0019AA\u0016\u0003%!(/Z3Q_&tG\u000f\u0003\u0004\u0002\u0002&\u0001\raP\u0001\u000baJ,G-[2uS>t\u0007\"\u0002\u000b\n\u0001\u0004A\u0004BBAD\u0013\u0001\u0007q(\u0001\u0004xK&<\u0007\u000e\u001e\u0005\b\u0003\u0017K\u0001\u0019AA,\u0003\u0019\u0019\b\u000f\\5ugRIq(a$\u0002 \u0006\u0005\u00161\u0015\u0005\b\u0003#S\u0001\u0019AAJ\u0003!1W-\u0019;ve\u0016\u001c\b\u0003BAK\u00037k!!a&\u000b\u0007\u0005eU#\u0001\u0004mS:\fGnZ\u0005\u0005\u0003;\u000b9J\u0001\u0004WK\u000e$xN\u001d\u0005\u0007\u0003\u0003S\u0001\u0019A \t\u000bQQ\u0001\u0019\u0001\u001d\t\r\u0005\u001d%\u00021\u0001@\u0003Q\u0019w.\u001c9vi\u0016<V-[4ii\u0016$WI\u001d:peRIq(!+\u0002,\u0006=\u00161\u0017\u0005\u0007\u0003OY\u0001\u0019\u0001#\t\r\u000556\u00021\u00016\u0003\u0015!(/Z3t\u0011\u0019\t\tl\u0003a\u0001}\u0005YAO]3f/\u0016Lw\r\u001b;t\u0011\u001d\tYd\u0003a\u0001\u0003{!RaPA\\\u0003sCq!a\n\r\u0001\u0004\tI\u0003C\u0004\u0002<2\u0001\r!!\t\u0002\u0013A\u0014X\rZ#se>\u0014\u0018!F3wC2,\u0018\r^3FC\u000eD\u0017\n^3sCRLwN\u001c\u000b\f}\u0005\u0005\u00171YAc\u0003\u000f\fI\r\u0003\u0004\u0002(5\u0001\r\u0001\u0012\u0005\u0007\u0003[k\u0001\u0019A\u001b\t\r\u0005EV\u00021\u0001?\u0011\u001d\tY$\u0004a\u0001\u0003{Aq!a3\u000e\u0001\u0004\ti-\u0001\u0003bY\u001e|\u0007\u0003BAh\u0003+t1aUAi\u0013\r\t\u0019\u000eV\u0001\u0005\u00032<w.\u0003\u0003\u0002X\u0006e'!\u0002,bYV,\u0017bAAnG\tYQI\\;nKJ\fG/[8o\u0003\u0015\u0011wn\\:u)=\u0011\u0014\u0011]Ar\u0003K\f9/!=\u0002t\u0006U\b\"B\"\u000f\u0001\u0004!\u0005BBA\t\u001d\u0001\u0007A\tC\u0003R\u001d\u0001\u0007!\u000bC\u0004\u0002j:\u0001\r!a;\u0002\u0011Y\fG.\u001b3bi\u0016\u00042AIAw\u0013\r\tyo\t\u0002\b\u0005>|G.Z1o\u0011\u0015af\u00021\u0001^\u0011\u0015\tg\u00021\u0001c\u0011\u001dqg\u0002%AA\u0002=\fqBY8pgR$C-\u001a4bk2$He\u000e"
)
public final class GradientBoostedTrees {
   public static Option boost$default$7() {
      return GradientBoostedTrees$.MODULE$.boost$default$7();
   }

   public static Tuple2 boost(final RDD input, final RDD validationInput, final BoostingStrategy boostingStrategy, final boolean validate, final long seed, final String featureSubsetStrategy, final Option instr) {
      return GradientBoostedTrees$.MODULE$.boost(input, validationInput, boostingStrategy, validate, seed, featureSubsetStrategy, instr);
   }

   public static double[] evaluateEachIteration(final RDD data, final DecisionTreeRegressionModel[] trees, final double[] treeWeights, final Loss loss, final Enumeration.Value algo) {
      return GradientBoostedTrees$.MODULE$.evaluateEachIteration(data, trees, treeWeights, loss, algo);
   }

   public static double computeWeightedError(final RDD data, final RDD predError) {
      return GradientBoostedTrees$.MODULE$.computeWeightedError(data, predError);
   }

   public static double computeWeightedError(final RDD data, final DecisionTreeRegressionModel[] trees, final double[] treeWeights, final Loss loss) {
      return GradientBoostedTrees$.MODULE$.computeWeightedError(data, trees, treeWeights, loss);
   }

   public static double updatePrediction(final Vector features, final double prediction, final DecisionTreeRegressionModel tree, final double weight) {
      return GradientBoostedTrees$.MODULE$.updatePrediction(features, prediction, tree, weight);
   }

   public static double updatePrediction(final TreePoint treePoint, final double prediction, final DecisionTreeRegressionModel tree, final double weight, final Split[][] splits) {
      return GradientBoostedTrees$.MODULE$.updatePrediction(treePoint, prediction, tree, weight, splits);
   }

   public static RDD updatePredictionError(final RDD data, final RDD predictionAndError, final double treeWeight, final DecisionTreeRegressionModel tree, final Loss loss, final Broadcast bcSplits) {
      return GradientBoostedTrees$.MODULE$.updatePredictionError(data, predictionAndError, treeWeight, tree, loss, bcSplits);
   }

   public static RDD computeInitialPredictionAndError(final RDD data, final double initTreeWeight, final DecisionTreeRegressionModel initTree, final Loss loss, final Broadcast bcSplits) {
      return GradientBoostedTrees$.MODULE$.computeInitialPredictionAndError(data, initTreeWeight, initTree, loss, bcSplits);
   }

   public static Option runWithValidation$default$6() {
      return GradientBoostedTrees$.MODULE$.runWithValidation$default$6();
   }

   public static Tuple2 runWithValidation(final RDD input, final RDD validationInput, final BoostingStrategy boostingStrategy, final long seed, final String featureSubsetStrategy, final Option instr) {
      return GradientBoostedTrees$.MODULE$.runWithValidation(input, validationInput, boostingStrategy, seed, featureSubsetStrategy, instr);
   }

   public static Option run$default$5() {
      return GradientBoostedTrees$.MODULE$.run$default$5();
   }

   public static Tuple2 run(final RDD input, final BoostingStrategy boostingStrategy, final long seed, final String featureSubsetStrategy, final Option instr) {
      return GradientBoostedTrees$.MODULE$.run(input, boostingStrategy, seed, featureSubsetStrategy, instr);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return GradientBoostedTrees$.MODULE$.LogStringContext(sc);
   }
}
