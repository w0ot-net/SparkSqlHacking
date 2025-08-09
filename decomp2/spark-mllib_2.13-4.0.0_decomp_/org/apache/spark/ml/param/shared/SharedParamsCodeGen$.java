package org.apache.spark.ml.param.shared;

import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime;

public final class SharedParamsCodeGen$ {
   public static final SharedParamsCodeGen$ MODULE$ = new SharedParamsCodeGen$();

   public void main(final String[] args) {
      Seq var10000 = .MODULE$.Seq();
      ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
      SharedParamsCodeGen.ParamDesc[] var10002 = new SharedParamsCodeGen.ParamDesc[33];
      String x$1 = "regParam";
      String x$2 = "regularization parameter (>= 0)";
      String x$3 = "ParamValidators.gtEq(0)";
      Option x$4 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$5 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$6 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      boolean x$7 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[0] = new SharedParamsCodeGen.ParamDesc("regParam", "regularization parameter (>= 0)", x$4, "ParamValidators.gtEq(0)", x$5, x$6, x$7, scala.reflect.ClassTag..MODULE$.Double());
      String x$8 = "maxIter";
      String x$9 = "maximum number of iterations (>= 0)";
      String x$10 = "ParamValidators.gtEq(0)";
      Option x$11 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$12 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$13 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      boolean x$14 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[1] = new SharedParamsCodeGen.ParamDesc("maxIter", "maximum number of iterations (>= 0)", x$11, "ParamValidators.gtEq(0)", x$12, x$13, x$14, scala.reflect.ClassTag..MODULE$.Int());
      var10002[2] = new SharedParamsCodeGen.ParamDesc("featuresCol", "features column name", new Some("\"features\""), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[3] = new SharedParamsCodeGen.ParamDesc("labelCol", "label column name", new Some("\"label\""), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[4] = new SharedParamsCodeGen.ParamDesc("predictionCol", "prediction column name", new Some("\"prediction\""), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[5] = new SharedParamsCodeGen.ParamDesc("rawPredictionCol", "raw prediction (a.k.a. confidence) column name", new Some("\"rawPrediction\""), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[6] = new SharedParamsCodeGen.ParamDesc("probabilityCol", "Column name for predicted class conditional probabilities. Note: Not all models output well-calibrated probability estimates! These probabilities should be treated as confidences, not precise probabilities", new Some("\"probability\""), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[7] = new SharedParamsCodeGen.ParamDesc("varianceCol", "Column name for the biased sample variance of prediction", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      String x$15 = "threshold";
      String x$16 = "threshold in binary classification prediction, in range [0, 1]";
      String x$17 = "ParamValidators.inRange(0, 1)";
      boolean x$18 = false;
      boolean x$19 = false;
      Option x$20 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$21 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[8] = new SharedParamsCodeGen.ParamDesc("threshold", "threshold in binary classification prediction, in range [0, 1]", x$20, "ParamValidators.inRange(0, 1)", false, false, x$21, scala.reflect.ClassTag..MODULE$.Double());
      String x$22 = "thresholds";
      String x$23 = "Thresholds in multi-class classification to adjust the probability of predicting each class. Array must have length equal to the number of classes, with values > 0 excepting that at most one value may be 0. The class with largest value p/t is predicted, where p is the original probability of that class and t is the class's threshold";
      String x$24 = "(t: Array[Double]) => t.forall(_ >= 0) && t.count(_ == 0) <= 1";
      boolean x$25 = false;
      boolean x$26 = false;
      Option x$27 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$28 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[9] = new SharedParamsCodeGen.ParamDesc("thresholds", "Thresholds in multi-class classification to adjust the probability of predicting each class. Array must have length equal to the number of classes, with values > 0 excepting that at most one value may be 0. The class with largest value p/t is predicted, where p is the original probability of that class and t is the class's threshold", x$27, "(t: Array[Double]) => t.forall(_ >= 0) && t.count(_ == 0) <= 1", false, false, x$28, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      var10002[10] = new SharedParamsCodeGen.ParamDesc("inputCol", "input column name", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[11] = new SharedParamsCodeGen.ParamDesc("inputCols", "input column names", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
      var10002[12] = new SharedParamsCodeGen.ParamDesc("outputCol", "output column name", new Some("uid + \"__output\""), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[13] = new SharedParamsCodeGen.ParamDesc("outputCols", "output column names", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
      var10002[14] = new SharedParamsCodeGen.ParamDesc("numFeatures", "Number of features. Should be greater than 0", new Some("262144"), "ParamValidators.gt(0)", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.Int());
      String x$29 = "checkpointInterval";
      String x$30 = "set checkpoint interval (>= 1) or disable checkpoint (-1). E.g. 10 means that the cache will get checkpointed every 10 iterations. Note: this setting will be ignored if the checkpoint directory is not set in the SparkContext";
      String x$31 = "(interval: Int) => interval == -1 || interval >= 1";
      Option x$32 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$33 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$34 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      boolean x$35 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[15] = new SharedParamsCodeGen.ParamDesc("checkpointInterval", "set checkpoint interval (>= 1) or disable checkpoint (-1). E.g. 10 means that the cache will get checkpointed every 10 iterations. Note: this setting will be ignored if the checkpoint directory is not set in the SparkContext", x$32, "(interval: Int) => interval == -1 || interval >= 1", x$33, x$34, x$35, scala.reflect.ClassTag..MODULE$.Int());
      var10002[16] = new SharedParamsCodeGen.ParamDesc("fitIntercept", "whether to fit an intercept term", new Some("true"), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.Boolean());
      String x$36 = "handleInvalid";
      String x$37 = "how to handle invalid entries. Options are skip (which will filter out rows with bad values), or error (which will throw an error). More options may be added later";
      String x$38 = "ParamValidators.inArray(Array(\"skip\", \"error\"))";
      boolean x$39 = false;
      Option x$40 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$41 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$42 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[17] = new SharedParamsCodeGen.ParamDesc("handleInvalid", "how to handle invalid entries. Options are skip (which will filter out rows with bad values), or error (which will throw an error). More options may be added later", x$40, "ParamValidators.inArray(Array(\"skip\", \"error\"))", x$41, false, x$42, scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[18] = new SharedParamsCodeGen.ParamDesc("standardization", "whether to standardize the training features before fitting the model", new Some("true"), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.Boolean());
      var10002[19] = new SharedParamsCodeGen.ParamDesc("seed", "random seed", new Some("this.getClass.getName.hashCode.toLong"), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.Long());
      String x$43 = "elasticNetParam";
      String x$44 = "the ElasticNet mixing parameter, in range [0, 1]. For alpha = 0, the penalty is an L2 penalty. For alpha = 1, it is an L1 penalty";
      String x$45 = "ParamValidators.inRange(0, 1)";
      Option x$46 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$47 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$48 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      boolean x$49 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[20] = new SharedParamsCodeGen.ParamDesc("elasticNetParam", "the ElasticNet mixing parameter, in range [0, 1]. For alpha = 0, the penalty is an L2 penalty. For alpha = 1, it is an L1 penalty", x$46, "ParamValidators.inRange(0, 1)", x$47, x$48, x$49, scala.reflect.ClassTag..MODULE$.Double());
      String x$50 = "tol";
      String x$51 = "the convergence tolerance for iterative algorithms (>= 0)";
      String x$52 = "ParamValidators.gtEq(0)";
      Option x$53 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$54 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$55 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      boolean x$56 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[21] = new SharedParamsCodeGen.ParamDesc("tol", "the convergence tolerance for iterative algorithms (>= 0)", x$53, "ParamValidators.gtEq(0)", x$54, x$55, x$56, scala.reflect.ClassTag..MODULE$.Double());
      String x$57 = "relativeError";
      String x$58 = "the relative target precision for the approximate quantile algorithm. Must be in the range [0, 1]";
      Some x$59 = new Some("0.001");
      String x$60 = "ParamValidators.inRange(0, 1)";
      boolean x$61 = true;
      boolean x$62 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$63 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      var10002[22] = new SharedParamsCodeGen.ParamDesc("relativeError", "the relative target precision for the approximate quantile algorithm. Must be in the range [0, 1]", x$59, "ParamValidators.inRange(0, 1)", x$62, x$63, true, scala.reflect.ClassTag..MODULE$.Double());
      String x$64 = "stepSize";
      String x$65 = "Step size to be used for each iteration of optimization (> 0)";
      String x$66 = "ParamValidators.gt(0)";
      boolean x$67 = false;
      Option x$68 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$69 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$70 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[23] = new SharedParamsCodeGen.ParamDesc("stepSize", "Step size to be used for each iteration of optimization (> 0)", x$68, "ParamValidators.gt(0)", x$69, false, x$70, scala.reflect.ClassTag..MODULE$.Double());
      var10002[24] = new SharedParamsCodeGen.ParamDesc("weightCol", "weight column name. If this is not set or empty, we treat all instance weights as 1.0", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      String x$71 = "solver";
      String x$72 = "the solver algorithm for optimization";
      boolean x$73 = false;
      Option x$74 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      String x$75 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4();
      boolean x$76 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$77 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[25] = new SharedParamsCodeGen.ParamDesc("solver", "the solver algorithm for optimization", x$74, x$75, x$76, false, x$77, scala.reflect.ClassTag..MODULE$.apply(String.class));
      String x$78 = "aggregationDepth";
      String x$79 = "suggested depth for treeAggregate (>= 2)";
      Some x$80 = new Some("2");
      String x$81 = "ParamValidators.gtEq(2)";
      boolean x$82 = true;
      boolean x$83 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$84 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      var10002[26] = new SharedParamsCodeGen.ParamDesc("aggregationDepth", "suggested depth for treeAggregate (>= 2)", x$80, "ParamValidators.gtEq(2)", x$83, x$84, true, scala.reflect.ClassTag..MODULE$.Int());
      String x$85 = "collectSubModels";
      String x$86 = "whether to collect a list of sub-models trained during tuning. If set to false, then only the single best sub-model will be available after fitting. If set to true, then all sub-models will be available. Warning: For large models, collecting all sub-models can cause OOMs on the Spark driver";
      Some x$87 = new Some("false");
      boolean x$88 = true;
      String x$89 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4();
      boolean x$90 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$91 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      var10002[27] = new SharedParamsCodeGen.ParamDesc("collectSubModels", "whether to collect a list of sub-models trained during tuning. If set to false, then only the single best sub-model will be available after fitting. If set to true, then all sub-models will be available. Warning: For large models, collecting all sub-models can cause OOMs on the Spark driver", x$87, x$89, x$90, x$91, true, scala.reflect.ClassTag..MODULE$.Boolean());
      String x$92 = "loss";
      String x$93 = "the loss function to be optimized";
      boolean x$94 = false;
      Option x$95 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      String x$96 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4();
      boolean x$97 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$98 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7();
      var10002[28] = new SharedParamsCodeGen.ParamDesc("loss", "the loss function to be optimized", x$95, x$96, x$97, false, x$98, scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[29] = new SharedParamsCodeGen.ParamDesc("distanceMeasure", "The distance measure. Supported options: 'euclidean' and 'cosine'", new Some("\"euclidean\""), "ParamValidators.inArray(Array(\"euclidean\", \"cosine\"))", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      var10002[30] = new SharedParamsCodeGen.ParamDesc("validationIndicatorCol", "name of the column that indicates whether each row is for training or for validation. False indicates training; true indicates validation.", SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$4(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6(), SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$7(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      String x$99 = "blockSize";
      String x$100 = "block size for stacking input data in matrices. Data is stacked within partitions. If block size is more than remaining data in a partition then it is adjusted to the size of this data.";
      String x$101 = "ParamValidators.gt(0)";
      boolean x$102 = true;
      Option x$103 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$3();
      boolean x$104 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$105 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      var10002[31] = new SharedParamsCodeGen.ParamDesc("blockSize", "block size for stacking input data in matrices. Data is stacked within partitions. If block size is more than remaining data in a partition then it is adjusted to the size of this data.", x$103, "ParamValidators.gt(0)", x$104, x$105, true, scala.reflect.ClassTag..MODULE$.Int());
      String x$106 = "maxBlockSizeInMB";
      String x$107 = "Maximum memory in MB for stacking input data into blocks. Data is stacked within partitions. If more than remaining data size in a partition then it is adjusted to the data size. Default 0.0 represents choosing optimal value, depends on specific algorithm. Must be >= 0.";
      Some x$108 = new Some("0.0");
      String x$109 = "ParamValidators.gtEq(0.0)";
      boolean x$110 = true;
      boolean x$111 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$5();
      boolean x$112 = SharedParamsCodeGen.ParamDesc$.MODULE$.apply$default$6();
      var10002[32] = new SharedParamsCodeGen.ParamDesc("maxBlockSizeInMB", "Maximum memory in MB for stacking input data into blocks. Data is stacked within partitions. If more than remaining data size in a partition then it is adjusted to the data size. Default 0.0 represents choosing optimal value, depends on specific algorithm. Must be >= 0.", x$108, "ParamValidators.gtEq(0.0)", x$111, x$112, true, scala.reflect.ClassTag..MODULE$.Double());
      Seq params = (Seq)var10000.apply(var10001.wrapRefArray(var10002));
      String code = this.genSharedParams(params);
      String file = "src/main/scala/org/apache/spark/ml/param/shared/sharedParams.scala";
      org.apache.spark.util.Utils..MODULE$.tryWithResource(() -> new PrintWriter(file), (writer) -> {
         $anonfun$main$2(code, writer);
         return BoxedUnit.UNIT;
      });
   }

   private String genHasParamTrait(final SharedParamsCodeGen.ParamDesc param) {
      String name;
      String Name;
      String Param;
      String T;
      String doc;
      String defaultValueDoc;
      String setDefault;
      String var17;
      label32: {
         label31: {
            name = param.name();
            char var4 = scala.runtime.RichChar..MODULE$.toUpper$extension(scala.Predef..MODULE$.charWrapper(scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(name), 0)));
            Name = scala.collection.StringOps..MODULE$.$plus$colon$extension(scala.Predef..MODULE$.augmentString(name.substring(1)), var4);
            Param = param.paramTypeName();
            T = param.valueTypeName();
            doc = param.doc();
            Option defaultValue = param.defaultValueStr();
            defaultValueDoc = (String)defaultValue.map((v) -> " (default: " + v + ")").getOrElse(() -> "");
            setDefault = (String)defaultValue.map((v) -> scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n         |  setDefault(" + name + ", " + v + ")\n         |"))).getOrElse(() -> "");
            var17 = param.isValid();
            String var12 = "";
            if (var17 == null) {
               if (var12 != null) {
                  break label31;
               }
            } else if (!var17.equals(var12)) {
               break label31;
            }

            var17 = "";
            break label32;
         }

         var17 = ", " + param.isValid();
      }

      String isValid = var17;
      String[] groupStr = param.isExpertParam() ? (String[])((Object[])(new String[]{"expertParam", "expertGetParam"})) : (String[])((Object[])(new String[]{"param", "getParam"}));
      String methodStr = param.finalMethods() ? "final def" : "def";
      String fieldStr = param.finalFields() ? "final val" : "val";
      String htmlCompliantDoc = scala.xml.Utility..MODULE$.escape(doc);
      return scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n      |/**\n      | * Trait for shared param " + name + defaultValueDoc + ". This trait may be changed or\n      | * removed between minor versions.\n      | */\n      |trait Has" + Name + " extends Params {\n      |\n      |  /**\n      |   * Param for " + htmlCompliantDoc + ".\n      |   * @group " + groupStr[0] + "\n      |   */\n      |  " + fieldStr + " " + name + ": " + Param + " = new " + Param + "(this, \"" + name + "\", \"" + doc + "\"" + isValid + ")\n      |" + setDefault + "\n      |  /** @group " + groupStr[1] + " */\n      |  " + methodStr + " get" + Name + ": " + T + " = $(" + name + ")\n      |}\n      |"));
   }

   private String genSharedParams(final Seq params) {
      String header = scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("/*\n        | * Licensed to the Apache Software Foundation (ASF) under one or more\n        | * contributor license agreements.  See the NOTICE file distributed with\n        | * this work for additional information regarding copyright ownership.\n        | * The ASF licenses this file to You under the Apache License, Version 2.0\n        | * (the \"License\"); you may not use this file except in compliance with\n        | * the License.  You may obtain a copy of the License at\n        | *\n        | *    http://www.apache.org/licenses/LICENSE-2.0\n        | *\n        | * Unless required by applicable law or agreed to in writing, software\n        | * distributed under the License is distributed on an \"AS IS\" BASIS,\n        | * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n        | * See the License for the specific language governing permissions and\n        | * limitations under the License.\n        | */\n        |\n        |package org.apache.spark.ml.param.shared\n        |\n        |import org.apache.spark.ml.param._\n        |\n        |// DO NOT MODIFY THIS FILE! It was generated by SharedParamsCodeGen.\n        |\n        |// scalastyle:off\n        |"));
      String footer = "// scalastyle:on\n";
      String traits = ((IterableOnceOps)params.map((param) -> MODULE$.genHasParamTrait(param))).mkString();
      return header + traits + footer;
   }

   // $FF: synthetic method
   public static final void $anonfun$main$2(final String code$1, final PrintWriter writer) {
      writer.write(code$1);
   }

   private SharedParamsCodeGen$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
