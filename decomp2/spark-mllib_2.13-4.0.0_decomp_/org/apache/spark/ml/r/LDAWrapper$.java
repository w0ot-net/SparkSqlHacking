package org.apache.spark.ml.r;

import org.apache.spark.SparkException;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.clustering.LDA;
import org.apache.spark.ml.clustering.LDAModel;
import org.apache.spark.ml.feature.CountVectorizer;
import org.apache.spark.ml.feature.CountVectorizerModel;
import org.apache.spark.ml.feature.RegexTokenizer;
import org.apache.spark.ml.feature.StopWordsRemover;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class LDAWrapper$ implements MLReadable {
   public static final LDAWrapper$ MODULE$ = new LDAWrapper$();
   private static final String TOKENIZER_COL;
   private static final String STOPWORDS_REMOVER_COL;
   private static final String COUNT_VECTOR_COL;

   static {
      MLReadable.$init$(MODULE$);
      TOKENIZER_COL = String.valueOf(Identifiable$.MODULE$.randomUID("rawTokens"));
      STOPWORDS_REMOVER_COL = String.valueOf(Identifiable$.MODULE$.randomUID("tokens"));
      COUNT_VECTOR_COL = String.valueOf(Identifiable$.MODULE$.randomUID("features"));
   }

   public String TOKENIZER_COL() {
      return TOKENIZER_COL;
   }

   public String STOPWORDS_REMOVER_COL() {
      return STOPWORDS_REMOVER_COL;
   }

   public String COUNT_VECTOR_COL() {
      return COUNT_VECTOR_COL;
   }

   private PipelineStage[] getPreStages(final String features, final String[] customizedStopWords, final int maxVocabSize) {
      RegexTokenizer tokenizer = (RegexTokenizer)(new RegexTokenizer()).setInputCol(features).setOutputCol(this.TOKENIZER_COL());
      StopWordsRemover stopWordsRemover = (new StopWordsRemover()).setInputCol(this.TOKENIZER_COL()).setOutputCol(this.STOPWORDS_REMOVER_COL());
      stopWordsRemover.setStopWords((String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])stopWordsRemover.getStopWords()), customizedStopWords, scala.reflect.ClassTag..MODULE$.apply(String.class)));
      CountVectorizer countVectorizer = (new CountVectorizer()).setVocabSize(maxVocabSize).setInputCol(this.STOPWORDS_REMOVER_COL()).setOutputCol(this.COUNT_VECTOR_COL());
      return (PipelineStage[])(new PipelineStage[]{tokenizer, stopWordsRemover, countVectorizer});
   }

   public LDAWrapper fit(final Dataset data, final String features, final int k, final int maxIter, final String optimizer, final double subsamplingRate, final double topicConcentration, final double[] docConcentration, final String[] customizedStopWords, final int maxVocabSize) {
      LDA lda = (new LDA()).setK(k).setMaxIter(maxIter).setSubsamplingRate(subsamplingRate).setOptimizer(optimizer);
      StructField featureSchema = data.schema().apply(features);
      DataType var18 = featureSchema.dataType();
      PipelineStage[] var10000;
      if (var18 instanceof StringType) {
         var10000 = (PipelineStage[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(this.getPreStages(features, customizedStopWords, maxVocabSize)), new LDA[]{lda.setFeaturesCol(this.COUNT_VECTOR_COL())}, scala.reflect.ClassTag..MODULE$.apply(PipelineStage.class));
      } else {
         if (!(var18 instanceof VectorUDT)) {
            throw new SparkException("Unsupported input features type of " + featureSchema.dataType().typeName() + ", only String type and Vector type are supported now.");
         }

         var10000 = (PipelineStage[])(new LDA[]{lda.setFeaturesCol(features)});
      }

      PipelineStage[] stages = var10000;
      if (topicConcentration != (double)-1) {
         lda.setTopicConcentration(topicConcentration);
      } else {
         BoxedUnit var27 = BoxedUnit.UNIT;
      }

      if (docConcentration.length == 1) {
         if (BoxesRunTime.unboxToDouble(.MODULE$.head$extension(scala.Predef..MODULE$.doubleArrayOps(docConcentration))) != (double)-1) {
            lda.setDocConcentration(BoxesRunTime.unboxToDouble(.MODULE$.head$extension(scala.Predef..MODULE$.doubleArrayOps(docConcentration))));
         } else {
            BoxedUnit var28 = BoxedUnit.UNIT;
         }
      } else {
         lda.setDocConcentration(docConcentration);
      }

      Pipeline pipeline = (new Pipeline()).setStages(stages);
      PipelineModel model = pipeline.fit(data);
      DataType var22 = featureSchema.dataType();
      String[] var29;
      if (var22 instanceof StringType) {
         CountVectorizerModel countVectorModel = (CountVectorizerModel)model.stages()[2];
         var29 = countVectorModel.vocabulary();
      } else {
         var29 = (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
      }

      String[] vocabulary = var29;
      LDAModel ldaModel = (LDAModel).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(model.stages()));
      PipelineModel preprocessor = new PipelineModel(String.valueOf(Identifiable$.MODULE$.randomUID(pipeline.uid())), (Transformer[]).MODULE$.dropRight$extension(scala.Predef..MODULE$.refArrayOps(model.stages()), 1));
      Dataset preprocessedData = preprocessor.transform(data);
      return new LDAWrapper(model, ldaModel.logLikelihood(preprocessedData), ldaModel.logPerplexity(preprocessedData), vocabulary);
   }

   public MLReader read() {
      return new LDAWrapper.LDAWrapperReader();
   }

   public LDAWrapper load(final String path) {
      return (LDAWrapper)MLReadable.load$(this, path);
   }

   private LDAWrapper$() {
   }
}
