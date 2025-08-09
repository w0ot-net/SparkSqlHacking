package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.rdd.RDD;
import scala.Enumeration;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tmb!B\u001a5\u0001i\u0002\u0005\u0002\u0003+\u0001\u0005\u000b\u0007I\u0011A+\t\u0011e\u0003!\u0011!Q\u0001\nYC\u0001B\u0017\u0001\u0003\u0006\u0004%\ta\u0017\u0005\t?\u0002\u0011\t\u0011)A\u00059\"A\u0001\r\u0001BC\u0002\u0013\u0005\u0011\r\u0003\u0005f\u0001\t\u0005\t\u0015!\u0003c\u0011!1\u0007A!b\u0001\n\u0003)\u0006\u0002C4\u0001\u0005\u0003\u0005\u000b\u0011\u0002,\t\u0011!\u0004!Q1A\u0005\u0002UC\u0001\"\u001b\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\tU\u0002\u0011)\u0019!C\u0001W\"AA\u000f\u0001B\u0001B\u0003%A\u000e\u0003\u0005v\u0001\t\u0015\r\u0011\"\u0001w\u0011!Q\bA!A!\u0002\u00139\b\u0002C>\u0001\u0005\u000b\u0007I\u0011\u0001?\t\u0013\u0005\u0005\u0001A!A!\u0002\u0013i\bBCA\u0002\u0001\t\u0015\r\u0011\"\u0001\u0002\u0006!Q\u0011q\u0003\u0001\u0003\u0002\u0003\u0006I!a\u0002\t\u0015\u0005e\u0001A!b\u0001\n\u0003\tY\u0002\u0003\u0006\u0002H\u0001\u0011\t\u0011)A\u0005\u0003;A\u0011\"!\u0013\u0001\u0005\u000b\u0007I\u0011A+\t\u0013\u0005-\u0003A!A!\u0002\u00131\u0006\"CA'\u0001\t\u0015\r\u0011\"\u0001V\u0011%\ty\u0005\u0001B\u0001B\u0003%a\u000bC\u0005\u0002R\u0001\u0011)\u0019!C\u0001C\"I\u00111\u000b\u0001\u0003\u0002\u0003\u0006IA\u0019\u0005\n\u0003+\u0002!Q1A\u0005\u0002\u0005D\u0011\"a\u0016\u0001\u0005\u0003\u0005\u000b\u0011\u00022\t\u0013\u0005e\u0003A!b\u0001\n\u0003)\u0006\"CA.\u0001\t\u0005\t\u0015!\u0003W\u0011%\ti\u0006\u0001BC\u0002\u0013\u0005Q\u000bC\u0005\u0002`\u0001\u0011\t\u0011)A\u0005-\"9\u0011\u0011\r\u0001\u0005\u0002\u0005\r\u0004bBAE\u0001\u0011\u0005\u00111\u0012\u0005\b\u0003/\u0003A\u0011AAM\u0011\u001d\tY\n\u0001C\u0001\u00033Cq!!(\u0001\t\u0003\tI\nC\u0004\u0002 \u0002!\t!!)\t\u000f\u0005\u0015\u0006\u0001\"\u0001\u0002(\"1\u00111\u0016\u0001\u0005\u0002\u0005Dq!!,\u0001\t\u0003\ty\u000bC\u0004\u00024\u0002!\t!!.\t\u000f\u0005\u0005\u0007\u0001\"\u0001\u0002\u001a\u001eA\u00111\u0019\u001b\t\u0002i\n)MB\u00044i!\u0005!(a2\t\u000f\u0005\u0005T\u0006\"\u0001\u0002d\"9\u0011Q]\u0017\u0005\u0002\u0005\u001d\bbBAs[\u0011\u0005!Q\u0004\u0005\b\u0005GiC\u0011\u0001B\u0013\u0011%\u0011Y#LA\u0001\n\u0013\u0011iC\u0001\u000bEK\u000eL7/[8o)J,W-T3uC\u0012\fG/\u0019\u0006\u0003kY\nA![7qY*\u0011q\u0007O\u0001\u0005iJ,WM\u0003\u0002:u\u0005\u0011Q\u000e\u001c\u0006\u0003wq\nQa\u001d9be.T!!\u0010 \u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0014aA8sON\u0019\u0001!Q$\u0011\u0005\t+U\"A\"\u000b\u0003\u0011\u000bQa]2bY\u0006L!AR\"\u0003\r\u0005s\u0017PU3g!\tA\u0015K\u0004\u0002J\u001f:\u0011!JT\u0007\u0002\u0017*\u0011A*T\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tA)\u0003\u0002Q\u0007\u00069\u0001/Y2lC\u001e,\u0017B\u0001*T\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u00016)A\u0006ok64U-\u0019;ve\u0016\u001cX#\u0001,\u0011\u0005\t;\u0016B\u0001-D\u0005\rIe\u000e^\u0001\r]Vlg)Z1ukJ,7\u000fI\u0001\f]VlW\t_1na2,7/F\u0001]!\t\u0011U,\u0003\u0002_\u0007\n!Aj\u001c8h\u00031qW/\\#yC6\u0004H.Z:!\u0003M9X-[4ii\u0016$g*^7Fq\u0006l\u0007\u000f\\3t+\u0005\u0011\u0007C\u0001\"d\u0013\t!7I\u0001\u0004E_V\u0014G.Z\u0001\u0015o\u0016Lw\r\u001b;fI:+X.\u0012=b[BdWm\u001d\u0011\u0002\u00159,Xn\u00117bgN,7/A\u0006ok6\u001cE.Y:tKN\u0004\u0013aB7bq\nKgn]\u0001\t[\u0006D()\u001b8tA\u0005aa-Z1ukJ,\u0017I]5usV\tA\u000e\u0005\u0003ncZ3fB\u00018p!\tQ5)\u0003\u0002q\u0007\u00061\u0001K]3eK\u001aL!A]:\u0003\u00075\u000b\u0007O\u0003\u0002q\u0007\u0006ia-Z1ukJ,\u0017I]5us\u0002\n\u0011#\u001e8pe\u0012,'/\u001a3GK\u0006$XO]3t+\u00059\bcA7y-&\u0011\u0011p\u001d\u0002\u0004'\u0016$\u0018AE;o_J$WM]3e\r\u0016\fG/\u001e:fg\u0002\nqA\\;n\u0005&t7/F\u0001~!\r\u0011ePV\u0005\u0003\u007f\u000e\u0013Q!\u0011:sCf\f\u0001B\\;n\u0005&t7\u000fI\u0001\tS6\u0004XO]5usV\u0011\u0011q\u0001\t\u0005\u0003\u0013\t\u0019\"\u0004\u0002\u0002\f)!\u00111AA\u0007\u0015\r9\u0014q\u0002\u0006\u0004\u0003#Q\u0014!B7mY&\u0014\u0017\u0002BA\u000b\u0003\u0017\u0011\u0001\"S7qkJLG/_\u0001\nS6\u0004XO]5us\u0002\n\u0001#];b]RLG.Z*ue\u0006$XmZ=\u0016\u0005\u0005u\u0001\u0003BA\u0010\u0003\u0003rA!!\t\u0002<9!\u00111EA\u001c\u001d\u0011\t)#!\u000e\u000f\t\u0005\u001d\u00121\u0007\b\u0005\u0003S\t\tD\u0004\u0003\u0002,\u0005=bb\u0001&\u0002.%\tq(\u0003\u0002>}%\u00111\bP\u0005\u0004\u0003#Q\u0014bA\u001c\u0002\u0010%!\u0011\u0011HA\u0007\u00035\u0019wN\u001c4jOV\u0014\u0018\r^5p]&!\u0011QHA \u0003A\tV/\u00198uS2,7\u000b\u001e:bi\u0016<\u0017P\u0003\u0003\u0002:\u00055\u0011\u0002BA\"\u0003\u000b\u0012\u0001#U;b]RLG.Z*ue\u0006$XmZ=\u000b\t\u0005u\u0012qH\u0001\u0012cV\fg\u000e^5mKN#(/\u0019;fOf\u0004\u0013\u0001C7bq\u0012+\u0007\u000f\u001e5\u0002\u00135\f\u0007\u0010R3qi\"\u0004\u0013aE7j]&s7\u000f^1oG\u0016\u001c\b+\u001a:O_\u0012,\u0017\u0001F7j]&s7\u000f^1oG\u0016\u001c\b+\u001a:O_\u0012,\u0007%\u0001\rnS:<V-[4ii\u001a\u0013\u0018m\u0019;j_:\u0004VM\u001d(pI\u0016\f\u0011$\\5o/\u0016Lw\r\u001b;Ge\u0006\u001cG/[8o!\u0016\u0014hj\u001c3fA\u0005YQ.\u001b8J]\u001a|w)Y5o\u00031i\u0017N\\%oM><\u0015-\u001b8!\u0003!qW/\u001c+sK\u0016\u001c\u0018!\u00038v[R\u0013X-Z:!\u0003IqW/\u001c$fCR,(/Z:QKJtu\u000eZ3\u0002'9,XNR3biV\u0014Xm\u001d)fe:{G-\u001a\u0011\u0002\rqJg.\u001b;?)\t\n)'!\u001b\u0002l\u00055\u0014qNA9\u0003g\n)(a\u001e\u0002z\u0005m\u0014QPA@\u0003\u0003\u000b\u0019)!\"\u0002\bB\u0019\u0011q\r\u0001\u000e\u0003QBQ\u0001V\u0011A\u0002YCQAW\u0011A\u0002qCQ\u0001Y\u0011A\u0002\tDQAZ\u0011A\u0002YCQ\u0001[\u0011A\u0002YCQA[\u0011A\u00021DQ!^\u0011A\u0002]DQa_\u0011A\u0002uDq!a\u0001\"\u0001\u0004\t9\u0001C\u0004\u0002\u001a\u0005\u0002\r!!\b\t\r\u0005%\u0013\u00051\u0001W\u0011\u0019\ti%\ta\u0001-\"1\u0011\u0011K\u0011A\u0002\tDa!!\u0016\"\u0001\u0004\u0011\u0007BBA-C\u0001\u0007a\u000b\u0003\u0004\u0002^\u0005\u0002\rAV\u0001\fSN,fn\u001c:eKJ,G\r\u0006\u0003\u0002\u000e\u0006M\u0005c\u0001\"\u0002\u0010&\u0019\u0011\u0011S\"\u0003\u000f\t{w\u000e\\3b]\"1\u0011Q\u0013\u0012A\u0002Y\u000bABZ3biV\u0014X-\u00138eKb\f\u0001#[:DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u0016\u0005\u00055\u0015\u0001D5t\u001bVdG/[2mCN\u001c\u0018aI5t\u001bVdG/[2mCN\u001cx+\u001b;i\u0007\u0006$XmZ8sS\u000e\fGNR3biV\u0014Xm]\u0001\u000eSN\u001c\u0015\r^3h_JL7-\u00197\u0015\t\u00055\u00151\u0015\u0005\u0007\u0003+3\u0003\u0019\u0001,\u0002\u0019%\u001c8i\u001c8uS:,x.^:\u0015\t\u00055\u0015\u0011\u0016\u0005\u0007\u0003+;\u0003\u0019\u0001,\u0002!5LgnV3jO\"$\b+\u001a:O_\u0012,\u0017!\u00038v[N\u0003H.\u001b;t)\r1\u0016\u0011\u0017\u0005\u0007\u0003+K\u0003\u0019\u0001,\u0002\u0019M,GOT;n'Bd\u0017\u000e^:\u0015\r\u0005]\u0016QXA`!\r\u0011\u0015\u0011X\u0005\u0004\u0003w\u001b%\u0001B+oSRDa!!&+\u0001\u00041\u0006BBAWU\u0001\u0007a+A\ntk\n\u001c\u0018-\u001c9mS:<g)Z1ukJ,7/\u0001\u000bEK\u000eL7/[8o)J,W-T3uC\u0012\fG/\u0019\t\u0004\u0003Oj3CB\u0017B\u0003\u0013\f)\u000e\u0005\u0003\u0002L\u0006EWBAAg\u0015\r\tyMO\u0001\tS:$XM\u001d8bY&!\u00111[Ag\u0005\u001daunZ4j]\u001e\u0004B!a6\u0002b6\u0011\u0011\u0011\u001c\u0006\u0005\u00037\fi.\u0001\u0002j_*\u0011\u0011q\\\u0001\u0005U\u00064\u0018-C\u0002S\u00033$\"!!2\u0002\u001b\t,\u0018\u000e\u001c3NKR\fG-\u0019;b))\t)'!;\u0003\u0006\tE!1\u0003\u0005\b\u0003W|\u0003\u0019AAw\u0003\u0015Ig\u000e];u!\u0019\ty/!>\u0002z6\u0011\u0011\u0011\u001f\u0006\u0004\u0003gT\u0014a\u0001:eI&!\u0011q_Ay\u0005\r\u0011F\t\u0012\t\u0005\u0003w\u0014\t!\u0004\u0002\u0002~*\u0019\u0011q \u001d\u0002\u000f\u0019,\u0017\r^;sK&!!1AA\u007f\u0005!Ien\u001d;b]\u000e,\u0007b\u0002B\u0004_\u0001\u0007!\u0011B\u0001\tgR\u0014\u0018\r^3hsB!!1\u0002B\u0007\u001b\t\ty$\u0003\u0003\u0003\u0010\u0005}\"\u0001C*ue\u0006$XmZ=\t\r\u0005es\u00061\u0001W\u0011\u001d\u0011)b\fa\u0001\u0005/\tQCZ3biV\u0014XmU;cg\u0016$8\u000b\u001e:bi\u0016<\u0017\u0010E\u0002n\u00053I1Aa\u0007t\u0005\u0019\u0019FO]5oOR1\u0011Q\rB\u0010\u0005CAq!a;1\u0001\u0004\ti\u000fC\u0004\u0003\bA\u0002\rA!\u0003\u0002!9,X.\u00168pe\u0012,'/\u001a3CS:\u001cHc\u0001,\u0003(!1!\u0011F\u0019A\u0002Y\u000bQ!\u0019:jif\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\f\u0011\t\tE\"qG\u0007\u0003\u0005gQAA!\u000e\u0002^\u0006!A.\u00198h\u0013\u0011\u0011IDa\r\u0003\r=\u0013'.Z2u\u0001"
)
public class DecisionTreeMetadata implements Serializable {
   private final int numFeatures;
   private final long numExamples;
   private final double weightedNumExamples;
   private final int numClasses;
   private final int maxBins;
   private final Map featureArity;
   private final Set unorderedFeatures;
   private final int[] numBins;
   private final Impurity impurity;
   private final Enumeration.Value quantileStrategy;
   private final int maxDepth;
   private final int minInstancesPerNode;
   private final double minWeightFractionPerNode;
   private final double minInfoGain;
   private final int numTrees;
   private final int numFeaturesPerNode;

   public static int numUnorderedBins(final int arity) {
      return DecisionTreeMetadata$.MODULE$.numUnorderedBins(arity);
   }

   public static DecisionTreeMetadata buildMetadata(final RDD input, final Strategy strategy) {
      return DecisionTreeMetadata$.MODULE$.buildMetadata(input, strategy);
   }

   public static DecisionTreeMetadata buildMetadata(final RDD input, final Strategy strategy, final int numTrees, final String featureSubsetStrategy) {
      return DecisionTreeMetadata$.MODULE$.buildMetadata(input, strategy, numTrees, featureSubsetStrategy);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return DecisionTreeMetadata$.MODULE$.LogStringContext(sc);
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public long numExamples() {
      return this.numExamples;
   }

   public double weightedNumExamples() {
      return this.weightedNumExamples;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public int maxBins() {
      return this.maxBins;
   }

   public Map featureArity() {
      return this.featureArity;
   }

   public Set unorderedFeatures() {
      return this.unorderedFeatures;
   }

   public int[] numBins() {
      return this.numBins;
   }

   public Impurity impurity() {
      return this.impurity;
   }

   public Enumeration.Value quantileStrategy() {
      return this.quantileStrategy;
   }

   public int maxDepth() {
      return this.maxDepth;
   }

   public int minInstancesPerNode() {
      return this.minInstancesPerNode;
   }

   public double minWeightFractionPerNode() {
      return this.minWeightFractionPerNode;
   }

   public double minInfoGain() {
      return this.minInfoGain;
   }

   public int numTrees() {
      return this.numTrees;
   }

   public int numFeaturesPerNode() {
      return this.numFeaturesPerNode;
   }

   public boolean isUnordered(final int featureIndex) {
      return this.unorderedFeatures().contains(BoxesRunTime.boxToInteger(featureIndex));
   }

   public boolean isClassification() {
      return this.numClasses() >= 2;
   }

   public boolean isMulticlass() {
      return this.numClasses() > 2;
   }

   public boolean isMulticlassWithCategoricalFeatures() {
      return this.isMulticlass() && this.featureArity().size() > 0;
   }

   public boolean isCategorical(final int featureIndex) {
      return this.featureArity().contains(BoxesRunTime.boxToInteger(featureIndex));
   }

   public boolean isContinuous(final int featureIndex) {
      return !this.featureArity().contains(BoxesRunTime.boxToInteger(featureIndex));
   }

   public double minWeightPerNode() {
      return this.minWeightFractionPerNode() * this.weightedNumExamples();
   }

   public int numSplits(final int featureIndex) {
      return this.isUnordered(featureIndex) ? this.numBins()[featureIndex] : this.numBins()[featureIndex] - 1;
   }

   public void setNumSplits(final int featureIndex, final int numSplits) {
      .MODULE$.require(this.isContinuous(featureIndex), () -> "Only number of bin for a continuous feature can be set.");
      this.numBins()[featureIndex] = numSplits + 1;
   }

   public boolean subsamplingFeatures() {
      return this.numFeatures() != this.numFeaturesPerNode();
   }

   public DecisionTreeMetadata(final int numFeatures, final long numExamples, final double weightedNumExamples, final int numClasses, final int maxBins, final Map featureArity, final Set unorderedFeatures, final int[] numBins, final Impurity impurity, final Enumeration.Value quantileStrategy, final int maxDepth, final int minInstancesPerNode, final double minWeightFractionPerNode, final double minInfoGain, final int numTrees, final int numFeaturesPerNode) {
      this.numFeatures = numFeatures;
      this.numExamples = numExamples;
      this.weightedNumExamples = weightedNumExamples;
      this.numClasses = numClasses;
      this.maxBins = maxBins;
      this.featureArity = featureArity;
      this.unorderedFeatures = unorderedFeatures;
      this.numBins = numBins;
      this.impurity = impurity;
      this.quantileStrategy = quantileStrategy;
      this.maxDepth = maxDepth;
      this.minInstancesPerNode = minInstancesPerNode;
      this.minWeightFractionPerNode = minWeightFractionPerNode;
      this.minInfoGain = minInfoGain;
      this.numTrees = numTrees;
      this.numFeaturesPerNode = numFeaturesPerNode;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
