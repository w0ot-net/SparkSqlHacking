package org.sparkproject.jpmml.model.visitors;

import java.util.ArrayDeque;
import java.util.Deque;
import org.sparkproject.dmg.pmml.Aggregate;
import org.sparkproject.dmg.pmml.Annotation;
import org.sparkproject.dmg.pmml.Anova;
import org.sparkproject.dmg.pmml.AnovaRow;
import org.sparkproject.dmg.pmml.AnyDistribution;
import org.sparkproject.dmg.pmml.Application;
import org.sparkproject.dmg.pmml.Apply;
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.BinarySimilarity;
import org.sparkproject.dmg.pmml.BlockIndicator;
import org.sparkproject.dmg.pmml.BoundaryValueMeans;
import org.sparkproject.dmg.pmml.BoundaryValues;
import org.sparkproject.dmg.pmml.Cell;
import org.sparkproject.dmg.pmml.Chebychev;
import org.sparkproject.dmg.pmml.ChildParent;
import org.sparkproject.dmg.pmml.CityBlock;
import org.sparkproject.dmg.pmml.ClassLabels;
import org.sparkproject.dmg.pmml.ClusteringModelQuality;
import org.sparkproject.dmg.pmml.ComparisonField;
import org.sparkproject.dmg.pmml.ComparisonMeasure;
import org.sparkproject.dmg.pmml.ComplexScoreDistribution;
import org.sparkproject.dmg.pmml.CompoundPredicate;
import org.sparkproject.dmg.pmml.ConfusionMatrix;
import org.sparkproject.dmg.pmml.Constant;
import org.sparkproject.dmg.pmml.ContStats;
import org.sparkproject.dmg.pmml.ContinuousDistribution;
import org.sparkproject.dmg.pmml.CorrelationFields;
import org.sparkproject.dmg.pmml.CorrelationMethods;
import org.sparkproject.dmg.pmml.CorrelationValues;
import org.sparkproject.dmg.pmml.Correlations;
import org.sparkproject.dmg.pmml.Counts;
import org.sparkproject.dmg.pmml.DataDictionary;
import org.sparkproject.dmg.pmml.DataField;
import org.sparkproject.dmg.pmml.Decision;
import org.sparkproject.dmg.pmml.Decisions;
import org.sparkproject.dmg.pmml.DefineFunction;
import org.sparkproject.dmg.pmml.DerivedField;
import org.sparkproject.dmg.pmml.DiscrStats;
import org.sparkproject.dmg.pmml.DiscreteDistribution;
import org.sparkproject.dmg.pmml.Discretize;
import org.sparkproject.dmg.pmml.DiscretizeBin;
import org.sparkproject.dmg.pmml.Distance;
import org.sparkproject.dmg.pmml.Distribution;
import org.sparkproject.dmg.pmml.EmbeddedModel;
import org.sparkproject.dmg.pmml.Entity;
import org.sparkproject.dmg.pmml.Euclidean;
import org.sparkproject.dmg.pmml.Expression;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.False;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.FieldColumnPair;
import org.sparkproject.dmg.pmml.FieldRef;
import org.sparkproject.dmg.pmml.GaussianDistribution;
import org.sparkproject.dmg.pmml.Header;
import org.sparkproject.dmg.pmml.InlineTable;
import org.sparkproject.dmg.pmml.IntSparseArray;
import org.sparkproject.dmg.pmml.Interval;
import org.sparkproject.dmg.pmml.Jaccard;
import org.sparkproject.dmg.pmml.Lag;
import org.sparkproject.dmg.pmml.LiftData;
import org.sparkproject.dmg.pmml.LiftGraph;
import org.sparkproject.dmg.pmml.LinearNorm;
import org.sparkproject.dmg.pmml.LocalTransformations;
import org.sparkproject.dmg.pmml.MapValues;
import org.sparkproject.dmg.pmml.MatCell;
import org.sparkproject.dmg.pmml.Matrix;
import org.sparkproject.dmg.pmml.Measure;
import org.sparkproject.dmg.pmml.MiningBuildTask;
import org.sparkproject.dmg.pmml.MiningField;
import org.sparkproject.dmg.pmml.MiningSchema;
import org.sparkproject.dmg.pmml.Minkowski;
import org.sparkproject.dmg.pmml.Model;
import org.sparkproject.dmg.pmml.ModelExplanation;
import org.sparkproject.dmg.pmml.ModelLiftGraph;
import org.sparkproject.dmg.pmml.ModelStats;
import org.sparkproject.dmg.pmml.ModelVerification;
import org.sparkproject.dmg.pmml.MultivariateStat;
import org.sparkproject.dmg.pmml.MultivariateStats;
import org.sparkproject.dmg.pmml.NormContinuous;
import org.sparkproject.dmg.pmml.NormDiscrete;
import org.sparkproject.dmg.pmml.NumericInfo;
import org.sparkproject.dmg.pmml.OptimumLiftGraph;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.OutputField;
import org.sparkproject.dmg.pmml.PMML;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.ParameterField;
import org.sparkproject.dmg.pmml.Partition;
import org.sparkproject.dmg.pmml.PartitionFieldStats;
import org.sparkproject.dmg.pmml.PoissonDistribution;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.PredictiveModelQuality;
import org.sparkproject.dmg.pmml.Quantile;
import org.sparkproject.dmg.pmml.ROC;
import org.sparkproject.dmg.pmml.ROCGraph;
import org.sparkproject.dmg.pmml.RandomLiftGraph;
import org.sparkproject.dmg.pmml.RealSparseArray;
import org.sparkproject.dmg.pmml.ResultField;
import org.sparkproject.dmg.pmml.Row;
import org.sparkproject.dmg.pmml.ScoreDistribution;
import org.sparkproject.dmg.pmml.Similarity;
import org.sparkproject.dmg.pmml.SimpleMatching;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.SimpleSetPredicate;
import org.sparkproject.dmg.pmml.SparseArray;
import org.sparkproject.dmg.pmml.SquaredEuclidean;
import org.sparkproject.dmg.pmml.TableLocator;
import org.sparkproject.dmg.pmml.Tanimoto;
import org.sparkproject.dmg.pmml.Target;
import org.sparkproject.dmg.pmml.TargetValue;
import org.sparkproject.dmg.pmml.Targets;
import org.sparkproject.dmg.pmml.Taxonomy;
import org.sparkproject.dmg.pmml.TextIndex;
import org.sparkproject.dmg.pmml.TextIndexNormalization;
import org.sparkproject.dmg.pmml.Timestamp;
import org.sparkproject.dmg.pmml.TransformationDictionary;
import org.sparkproject.dmg.pmml.True;
import org.sparkproject.dmg.pmml.UnivariateStats;
import org.sparkproject.dmg.pmml.Value;
import org.sparkproject.dmg.pmml.VerificationField;
import org.sparkproject.dmg.pmml.VerificationFields;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.XCoordinates;
import org.sparkproject.dmg.pmml.YCoordinates;
import org.sparkproject.dmg.pmml.anomaly_detection.AnomalyDetectionModel;
import org.sparkproject.dmg.pmml.anomaly_detection.MeanClusterDistances;
import org.sparkproject.dmg.pmml.association.AssociationModel;
import org.sparkproject.dmg.pmml.association.AssociationRule;
import org.sparkproject.dmg.pmml.association.Item;
import org.sparkproject.dmg.pmml.association.ItemRef;
import org.sparkproject.dmg.pmml.association.Itemset;
import org.sparkproject.dmg.pmml.baseline.Alternate;
import org.sparkproject.dmg.pmml.baseline.Baseline;
import org.sparkproject.dmg.pmml.baseline.BaselineModel;
import org.sparkproject.dmg.pmml.baseline.CountTable;
import org.sparkproject.dmg.pmml.baseline.FieldValue;
import org.sparkproject.dmg.pmml.baseline.FieldValueCount;
import org.sparkproject.dmg.pmml.baseline.TestDistributions;
import org.sparkproject.dmg.pmml.bayesian_network.BayesianNetworkModel;
import org.sparkproject.dmg.pmml.bayesian_network.BayesianNetworkNodes;
import org.sparkproject.dmg.pmml.bayesian_network.ContinuousConditionalProbability;
import org.sparkproject.dmg.pmml.bayesian_network.ContinuousNode;
import org.sparkproject.dmg.pmml.bayesian_network.DiscreteConditionalProbability;
import org.sparkproject.dmg.pmml.bayesian_network.DiscreteNode;
import org.sparkproject.dmg.pmml.bayesian_network.LognormalDistribution;
import org.sparkproject.dmg.pmml.bayesian_network.Lower;
import org.sparkproject.dmg.pmml.bayesian_network.Mean;
import org.sparkproject.dmg.pmml.bayesian_network.NormalDistribution;
import org.sparkproject.dmg.pmml.bayesian_network.ParentValue;
import org.sparkproject.dmg.pmml.bayesian_network.TriangularDistribution;
import org.sparkproject.dmg.pmml.bayesian_network.UniformDistribution;
import org.sparkproject.dmg.pmml.bayesian_network.Upper;
import org.sparkproject.dmg.pmml.bayesian_network.ValueProbability;
import org.sparkproject.dmg.pmml.bayesian_network.Variance;
import org.sparkproject.dmg.pmml.clustering.CenterFields;
import org.sparkproject.dmg.pmml.clustering.Cluster;
import org.sparkproject.dmg.pmml.clustering.ClusteringField;
import org.sparkproject.dmg.pmml.clustering.ClusteringModel;
import org.sparkproject.dmg.pmml.clustering.Comparisons;
import org.sparkproject.dmg.pmml.clustering.Covariances;
import org.sparkproject.dmg.pmml.clustering.KohonenMap;
import org.sparkproject.dmg.pmml.clustering.MissingValueWeights;
import org.sparkproject.dmg.pmml.gaussian_process.ARDSquaredExponentialKernel;
import org.sparkproject.dmg.pmml.gaussian_process.AbsoluteExponentialKernel;
import org.sparkproject.dmg.pmml.gaussian_process.GaussianProcessModel;
import org.sparkproject.dmg.pmml.gaussian_process.GeneralizedExponentialKernel;
import org.sparkproject.dmg.pmml.gaussian_process.Lambda;
import org.sparkproject.dmg.pmml.gaussian_process.RadialBasisKernel;
import org.sparkproject.dmg.pmml.general_regression.BaseCumHazardTables;
import org.sparkproject.dmg.pmml.general_regression.BaselineCell;
import org.sparkproject.dmg.pmml.general_regression.BaselineStratum;
import org.sparkproject.dmg.pmml.general_regression.Categories;
import org.sparkproject.dmg.pmml.general_regression.Category;
import org.sparkproject.dmg.pmml.general_regression.CovariateList;
import org.sparkproject.dmg.pmml.general_regression.EventValues;
import org.sparkproject.dmg.pmml.general_regression.FactorList;
import org.sparkproject.dmg.pmml.general_regression.GeneralRegressionModel;
import org.sparkproject.dmg.pmml.general_regression.PCell;
import org.sparkproject.dmg.pmml.general_regression.PCovCell;
import org.sparkproject.dmg.pmml.general_regression.PCovMatrix;
import org.sparkproject.dmg.pmml.general_regression.PPCell;
import org.sparkproject.dmg.pmml.general_regression.PPMatrix;
import org.sparkproject.dmg.pmml.general_regression.ParamMatrix;
import org.sparkproject.dmg.pmml.general_regression.Parameter;
import org.sparkproject.dmg.pmml.general_regression.ParameterCell;
import org.sparkproject.dmg.pmml.general_regression.ParameterList;
import org.sparkproject.dmg.pmml.general_regression.Predictor;
import org.sparkproject.dmg.pmml.general_regression.PredictorList;
import org.sparkproject.dmg.pmml.mining.MiningModel;
import org.sparkproject.dmg.pmml.mining.Segment;
import org.sparkproject.dmg.pmml.mining.Segmentation;
import org.sparkproject.dmg.pmml.mining.VariableWeight;
import org.sparkproject.dmg.pmml.naive_bayes.BayesInput;
import org.sparkproject.dmg.pmml.naive_bayes.BayesInputs;
import org.sparkproject.dmg.pmml.naive_bayes.BayesOutput;
import org.sparkproject.dmg.pmml.naive_bayes.NaiveBayesModel;
import org.sparkproject.dmg.pmml.naive_bayes.PairCounts;
import org.sparkproject.dmg.pmml.naive_bayes.TargetValueCount;
import org.sparkproject.dmg.pmml.naive_bayes.TargetValueCounts;
import org.sparkproject.dmg.pmml.naive_bayes.TargetValueStat;
import org.sparkproject.dmg.pmml.naive_bayes.TargetValueStats;
import org.sparkproject.dmg.pmml.nearest_neighbor.InstanceField;
import org.sparkproject.dmg.pmml.nearest_neighbor.InstanceFields;
import org.sparkproject.dmg.pmml.nearest_neighbor.KNNInput;
import org.sparkproject.dmg.pmml.nearest_neighbor.KNNInputs;
import org.sparkproject.dmg.pmml.nearest_neighbor.NearestNeighborModel;
import org.sparkproject.dmg.pmml.nearest_neighbor.TrainingInstances;
import org.sparkproject.dmg.pmml.neural_network.Connection;
import org.sparkproject.dmg.pmml.neural_network.NeuralEntity;
import org.sparkproject.dmg.pmml.neural_network.NeuralInput;
import org.sparkproject.dmg.pmml.neural_network.NeuralInputs;
import org.sparkproject.dmg.pmml.neural_network.NeuralLayer;
import org.sparkproject.dmg.pmml.neural_network.NeuralNetwork;
import org.sparkproject.dmg.pmml.neural_network.NeuralOutput;
import org.sparkproject.dmg.pmml.neural_network.NeuralOutputs;
import org.sparkproject.dmg.pmml.neural_network.Neuron;
import org.sparkproject.dmg.pmml.regression.CategoricalPredictor;
import org.sparkproject.dmg.pmml.regression.NumericPredictor;
import org.sparkproject.dmg.pmml.regression.PredictorTerm;
import org.sparkproject.dmg.pmml.regression.Regression;
import org.sparkproject.dmg.pmml.regression.RegressionModel;
import org.sparkproject.dmg.pmml.regression.RegressionTable;
import org.sparkproject.dmg.pmml.regression.Term;
import org.sparkproject.dmg.pmml.rule_set.CompoundRule;
import org.sparkproject.dmg.pmml.rule_set.Rule;
import org.sparkproject.dmg.pmml.rule_set.RuleSelectionMethod;
import org.sparkproject.dmg.pmml.rule_set.RuleSet;
import org.sparkproject.dmg.pmml.rule_set.RuleSetModel;
import org.sparkproject.dmg.pmml.rule_set.SimpleRule;
import org.sparkproject.dmg.pmml.scorecard.Attribute;
import org.sparkproject.dmg.pmml.scorecard.Characteristic;
import org.sparkproject.dmg.pmml.scorecard.Characteristics;
import org.sparkproject.dmg.pmml.scorecard.ComplexPartialScore;
import org.sparkproject.dmg.pmml.scorecard.Scorecard;
import org.sparkproject.dmg.pmml.sequence.AntecedentSequence;
import org.sparkproject.dmg.pmml.sequence.ConsequentSequence;
import org.sparkproject.dmg.pmml.sequence.Constraints;
import org.sparkproject.dmg.pmml.sequence.Delimiter;
import org.sparkproject.dmg.pmml.sequence.Sequence;
import org.sparkproject.dmg.pmml.sequence.SequenceModel;
import org.sparkproject.dmg.pmml.sequence.SequenceReference;
import org.sparkproject.dmg.pmml.sequence.SequenceRule;
import org.sparkproject.dmg.pmml.sequence.SetPredicate;
import org.sparkproject.dmg.pmml.sequence.SetReference;
import org.sparkproject.dmg.pmml.sequence.Time;
import org.sparkproject.dmg.pmml.support_vector_machine.Coefficient;
import org.sparkproject.dmg.pmml.support_vector_machine.Coefficients;
import org.sparkproject.dmg.pmml.support_vector_machine.Kernel;
import org.sparkproject.dmg.pmml.support_vector_machine.LinearKernel;
import org.sparkproject.dmg.pmml.support_vector_machine.PolynomialKernel;
import org.sparkproject.dmg.pmml.support_vector_machine.SigmoidKernel;
import org.sparkproject.dmg.pmml.support_vector_machine.SupportVector;
import org.sparkproject.dmg.pmml.support_vector_machine.SupportVectorMachine;
import org.sparkproject.dmg.pmml.support_vector_machine.SupportVectorMachineModel;
import org.sparkproject.dmg.pmml.support_vector_machine.SupportVectors;
import org.sparkproject.dmg.pmml.support_vector_machine.VectorDictionary;
import org.sparkproject.dmg.pmml.support_vector_machine.VectorFields;
import org.sparkproject.dmg.pmml.support_vector_machine.VectorInstance;
import org.sparkproject.dmg.pmml.text.DocumentTermMatrix;
import org.sparkproject.dmg.pmml.text.TextCorpus;
import org.sparkproject.dmg.pmml.text.TextDictionary;
import org.sparkproject.dmg.pmml.text.TextDocument;
import org.sparkproject.dmg.pmml.text.TextModel;
import org.sparkproject.dmg.pmml.text.TextModelNormalization;
import org.sparkproject.dmg.pmml.text.TextModelSimiliarity;
import org.sparkproject.dmg.pmml.time_series.AR;
import org.sparkproject.dmg.pmml.time_series.ARIMA;
import org.sparkproject.dmg.pmml.time_series.ARMAPart;
import org.sparkproject.dmg.pmml.time_series.Algorithm;
import org.sparkproject.dmg.pmml.time_series.Denominator;
import org.sparkproject.dmg.pmml.time_series.DynamicRegressor;
import org.sparkproject.dmg.pmml.time_series.ExponentialSmoothing;
import org.sparkproject.dmg.pmml.time_series.FinalNoise;
import org.sparkproject.dmg.pmml.time_series.FinalNu;
import org.sparkproject.dmg.pmml.time_series.FinalOmega;
import org.sparkproject.dmg.pmml.time_series.FinalPredictedNoise;
import org.sparkproject.dmg.pmml.time_series.FinalStateVector;
import org.sparkproject.dmg.pmml.time_series.FinalTheta;
import org.sparkproject.dmg.pmml.time_series.GARCH;
import org.sparkproject.dmg.pmml.time_series.GARCHPart;
import org.sparkproject.dmg.pmml.time_series.HVector;
import org.sparkproject.dmg.pmml.time_series.InterceptVector;
import org.sparkproject.dmg.pmml.time_series.KalmanState;
import org.sparkproject.dmg.pmml.time_series.Level;
import org.sparkproject.dmg.pmml.time_series.MA;
import org.sparkproject.dmg.pmml.time_series.MACoefficients;
import org.sparkproject.dmg.pmml.time_series.MaximumLikelihoodStat;
import org.sparkproject.dmg.pmml.time_series.MeasurementMatrix;
import org.sparkproject.dmg.pmml.time_series.NonseasonalComponent;
import org.sparkproject.dmg.pmml.time_series.NonseasonalFactor;
import org.sparkproject.dmg.pmml.time_series.Numerator;
import org.sparkproject.dmg.pmml.time_series.ObservationVarianceMatrix;
import org.sparkproject.dmg.pmml.time_series.OutlierEffect;
import org.sparkproject.dmg.pmml.time_series.PastVariances;
import org.sparkproject.dmg.pmml.time_series.PredictedStateCovarianceMatrix;
import org.sparkproject.dmg.pmml.time_series.PsiVector;
import org.sparkproject.dmg.pmml.time_series.RegressorValues;
import org.sparkproject.dmg.pmml.time_series.ResidualSquareCoefficients;
import org.sparkproject.dmg.pmml.time_series.Residuals;
import org.sparkproject.dmg.pmml.time_series.SeasonalComponent;
import org.sparkproject.dmg.pmml.time_series.SeasonalFactor;
import org.sparkproject.dmg.pmml.time_series.SeasonalTrendDecomposition;
import org.sparkproject.dmg.pmml.time_series.SeasonalityExpoSmooth;
import org.sparkproject.dmg.pmml.time_series.SelectedStateCovarianceMatrix;
import org.sparkproject.dmg.pmml.time_series.SpectralAnalysis;
import org.sparkproject.dmg.pmml.time_series.StateSpaceModel;
import org.sparkproject.dmg.pmml.time_series.StateVector;
import org.sparkproject.dmg.pmml.time_series.Theta;
import org.sparkproject.dmg.pmml.time_series.ThetaRecursionState;
import org.sparkproject.dmg.pmml.time_series.TimeAnchor;
import org.sparkproject.dmg.pmml.time_series.TimeCycle;
import org.sparkproject.dmg.pmml.time_series.TimeException;
import org.sparkproject.dmg.pmml.time_series.TimeSeries;
import org.sparkproject.dmg.pmml.time_series.TimeSeriesModel;
import org.sparkproject.dmg.pmml.time_series.TimeValue;
import org.sparkproject.dmg.pmml.time_series.TransferFunctionValues;
import org.sparkproject.dmg.pmml.time_series.TransitionMatrix;
import org.sparkproject.dmg.pmml.time_series.TrendCoefficients;
import org.sparkproject.dmg.pmml.time_series.TrendExpoSmooth;
import org.sparkproject.dmg.pmml.time_series.VarianceCoefficients;
import org.sparkproject.dmg.pmml.tree.ComplexNode;
import org.sparkproject.dmg.pmml.tree.DecisionTree;
import org.sparkproject.dmg.pmml.tree.Node;
import org.sparkproject.dmg.pmml.tree.TreeModel;

public abstract class AbstractVisitor implements Visitor {
   private Deque parents = new ArrayDeque();

   public Deque getParents() {
      return this.parents;
   }

   public void applyTo(Visitable visitable) {
      visitable.accept(this);
   }

   public VisitorAction visit(PMMLObject object) {
      return VisitorAction.CONTINUE;
   }

   public VisitorAction visit(Cell cell) {
      return this.visit((PMMLObject)cell);
   }

   public VisitorAction visit(ComparisonField comparisonField) {
      return this.visit((PMMLObject)comparisonField);
   }

   public VisitorAction visit(ContinuousDistribution continuousDistribution) {
      return this.visit((Distribution)continuousDistribution);
   }

   public VisitorAction visit(DiscreteDistribution discreteDistribution) {
      return this.visit((Distribution)discreteDistribution);
   }

   public VisitorAction visit(Distance distance) {
      return this.visit((Measure)distance);
   }

   public VisitorAction visit(Distribution distribution) {
      return this.visit((PMMLObject)distribution);
   }

   public VisitorAction visit(EmbeddedModel embeddedModel) {
      return this.visit((PMMLObject)embeddedModel);
   }

   public VisitorAction visit(Entity entity) {
      return this.visit((PMMLObject)entity);
   }

   public VisitorAction visit(Expression expression) {
      return this.visit((PMMLObject)expression);
   }

   public VisitorAction visit(Field field) {
      return this.visit((PMMLObject)field);
   }

   public VisitorAction visit(Kernel kernel) {
      return this.visit((PMMLObject)kernel);
   }

   public VisitorAction visit(Measure measure) {
      return this.visit((PMMLObject)measure);
   }

   public VisitorAction visit(Model model) {
      return this.visit((PMMLObject)model);
   }

   public VisitorAction visit(NeuralEntity neuralEntity) {
      return this.visit((Entity)neuralEntity);
   }

   public VisitorAction visit(Node node) {
      return this.visit((Entity)node);
   }

   public VisitorAction visit(ParameterCell parameterCell) {
      return this.visit((PMMLObject)parameterCell);
   }

   public VisitorAction visit(Predicate predicate) {
      return this.visit((PMMLObject)predicate);
   }

   public VisitorAction visit(PredictorList predictorList) {
      return this.visit((PMMLObject)predictorList);
   }

   public VisitorAction visit(Rule rule) {
      return this.visit((Entity)rule);
   }

   public VisitorAction visit(ScoreDistribution scoreDistribution) {
      return this.visit((PMMLObject)scoreDistribution);
   }

   public VisitorAction visit(Similarity similarity) {
      return this.visit((Measure)similarity);
   }

   public VisitorAction visit(SparseArray sparseArray) {
      return this.visit((PMMLObject)sparseArray);
   }

   public VisitorAction visit(Term term) {
      return this.visit((PMMLObject)term);
   }

   public VisitorAction visit(Algorithm algorithm) {
      return this.visit((PMMLObject)algorithm);
   }

   public VisitorAction visit(AbsoluteExponentialKernel absoluteExponentialKernel) {
      return this.visit((PMMLObject)absoluteExponentialKernel);
   }

   public VisitorAction visit(Aggregate aggregate) {
      return this.visit((Expression)aggregate);
   }

   public VisitorAction visit(Alternate alternate) {
      return this.visit((PMMLObject)alternate);
   }

   public VisitorAction visit(Annotation annotation) {
      return this.visit((PMMLObject)annotation);
   }

   public VisitorAction visit(AnomalyDetectionModel anomalyDetectionModel) {
      return this.visit((Model)anomalyDetectionModel);
   }

   public VisitorAction visit(Anova anova) {
      return this.visit((PMMLObject)anova);
   }

   public VisitorAction visit(AnovaRow anovaRow) {
      return this.visit((PMMLObject)anovaRow);
   }

   public VisitorAction visit(AntecedentSequence antecedentSequence) {
      return this.visit((PMMLObject)antecedentSequence);
   }

   public VisitorAction visit(AnyDistribution anyDistribution) {
      return this.visit((ContinuousDistribution)anyDistribution);
   }

   public VisitorAction visit(Application application) {
      return this.visit((PMMLObject)application);
   }

   public VisitorAction visit(Apply apply) {
      return this.visit((Expression)apply);
   }

   public VisitorAction visit(AR ar) {
      return this.visit((PMMLObject)ar);
   }

   public VisitorAction visit(ARDSquaredExponentialKernel ardSquaredExponentialKernel) {
      return this.visit((PMMLObject)ardSquaredExponentialKernel);
   }

   public VisitorAction visit(ARIMA arima) {
      return this.visit((Algorithm)arima);
   }

   public VisitorAction visit(ARMAPart armaPart) {
      return this.visit((PMMLObject)armaPart);
   }

   public VisitorAction visit(Array array) {
      return this.visit((PMMLObject)array);
   }

   public VisitorAction visit(AssociationModel associationModel) {
      return this.visit((Model)associationModel);
   }

   public VisitorAction visit(AssociationRule associationRule) {
      return this.visit((Entity)associationRule);
   }

   public VisitorAction visit(Attribute attribute) {
      return this.visit((PMMLObject)attribute);
   }

   public VisitorAction visit(BaseCumHazardTables baseCumHazardTables) {
      return this.visit((PMMLObject)baseCumHazardTables);
   }

   public VisitorAction visit(Baseline baseline) {
      return this.visit((PMMLObject)baseline);
   }

   public VisitorAction visit(BaselineCell baselineCell) {
      return this.visit((PMMLObject)baselineCell);
   }

   public VisitorAction visit(BaselineModel baselineModel) {
      return this.visit((Model)baselineModel);
   }

   public VisitorAction visit(BaselineStratum baselineStratum) {
      return this.visit((PMMLObject)baselineStratum);
   }

   public VisitorAction visit(BayesianNetworkModel bayesianNetworkModel) {
      return this.visit((Model)bayesianNetworkModel);
   }

   public VisitorAction visit(BayesianNetworkNodes bayesianNetworkNodes) {
      return this.visit((PMMLObject)bayesianNetworkNodes);
   }

   public VisitorAction visit(BayesInput bayesInput) {
      return this.visit((PMMLObject)bayesInput);
   }

   public VisitorAction visit(BayesInputs bayesInputs) {
      return this.visit((PMMLObject)bayesInputs);
   }

   public VisitorAction visit(BayesOutput bayesOutput) {
      return this.visit((PMMLObject)bayesOutput);
   }

   public VisitorAction visit(BinarySimilarity binarySimilarity) {
      return this.visit((Similarity)binarySimilarity);
   }

   public VisitorAction visit(BlockIndicator blockIndicator) {
      return this.visit((PMMLObject)blockIndicator);
   }

   public VisitorAction visit(BoundaryValueMeans boundaryValueMeans) {
      return this.visit((PMMLObject)boundaryValueMeans);
   }

   public VisitorAction visit(BoundaryValues boundaryValues) {
      return this.visit((PMMLObject)boundaryValues);
   }

   public VisitorAction visit(CategoricalPredictor categoricalPredictor) {
      return this.visit((Term)categoricalPredictor);
   }

   public VisitorAction visit(Categories categories) {
      return this.visit((PMMLObject)categories);
   }

   public VisitorAction visit(Category category) {
      return this.visit((PMMLObject)category);
   }

   public VisitorAction visit(CenterFields centerFields) {
      return this.visit((PMMLObject)centerFields);
   }

   public VisitorAction visit(Characteristic characteristic) {
      return this.visit((PMMLObject)characteristic);
   }

   public VisitorAction visit(Characteristics characteristics) {
      return this.visit((PMMLObject)characteristics);
   }

   public VisitorAction visit(Chebychev chebychev) {
      return this.visit((Distance)chebychev);
   }

   public VisitorAction visit(ChildParent childParent) {
      return this.visit((PMMLObject)childParent);
   }

   public VisitorAction visit(CityBlock cityBlock) {
      return this.visit((Distance)cityBlock);
   }

   public VisitorAction visit(ClassLabels classLabels) {
      return this.visit((PMMLObject)classLabels);
   }

   public VisitorAction visit(Cluster cluster) {
      return this.visit((Entity)cluster);
   }

   public VisitorAction visit(ClusteringField clusteringField) {
      return this.visit((ComparisonField)clusteringField);
   }

   public VisitorAction visit(ClusteringModel clusteringModel) {
      return this.visit((Model)clusteringModel);
   }

   public VisitorAction visit(ClusteringModelQuality clusteringModelQuality) {
      return this.visit((PMMLObject)clusteringModelQuality);
   }

   public VisitorAction visit(Coefficient coefficient) {
      return this.visit((PMMLObject)coefficient);
   }

   public VisitorAction visit(Coefficients coefficients) {
      return this.visit((PMMLObject)coefficients);
   }

   public VisitorAction visit(ComparisonMeasure comparisonMeasure) {
      return this.visit((PMMLObject)comparisonMeasure);
   }

   public VisitorAction visit(Comparisons comparisons) {
      return this.visit((PMMLObject)comparisons);
   }

   public VisitorAction visit(ComplexNode complexNode) {
      return this.visit((Node)complexNode);
   }

   public VisitorAction visit(ComplexPartialScore complexPartialScore) {
      return this.visit((PMMLObject)complexPartialScore);
   }

   public VisitorAction visit(ComplexScoreDistribution complexScoreDistribution) {
      return this.visit((ScoreDistribution)complexScoreDistribution);
   }

   public VisitorAction visit(CompoundPredicate compoundPredicate) {
      return this.visit((Predicate)compoundPredicate);
   }

   public VisitorAction visit(CompoundRule compoundRule) {
      return this.visit((Rule)compoundRule);
   }

   public VisitorAction visit(ConfusionMatrix confusionMatrix) {
      return this.visit((PMMLObject)confusionMatrix);
   }

   public VisitorAction visit(Connection connection) {
      return this.visit((PMMLObject)connection);
   }

   public VisitorAction visit(ConsequentSequence consequentSequence) {
      return this.visit((PMMLObject)consequentSequence);
   }

   public VisitorAction visit(Constant constant) {
      return this.visit((Expression)constant);
   }

   public VisitorAction visit(Constraints constraints) {
      return this.visit((PMMLObject)constraints);
   }

   public VisitorAction visit(ContinuousConditionalProbability continuousConditionalProbability) {
      return this.visit((PMMLObject)continuousConditionalProbability);
   }

   public VisitorAction visit(org.sparkproject.dmg.pmml.bayesian_network.ContinuousDistribution continuousDistribution) {
      return this.visit((PMMLObject)continuousDistribution);
   }

   public VisitorAction visit(ContinuousNode continuousNode) {
      return this.visit((PMMLObject)continuousNode);
   }

   public VisitorAction visit(ContStats contStats) {
      return this.visit((PMMLObject)contStats);
   }

   public VisitorAction visit(CorrelationFields correlationFields) {
      return this.visit((PMMLObject)correlationFields);
   }

   public VisitorAction visit(CorrelationMethods correlationMethods) {
      return this.visit((PMMLObject)correlationMethods);
   }

   public VisitorAction visit(Correlations correlations) {
      return this.visit((PMMLObject)correlations);
   }

   public VisitorAction visit(CorrelationValues correlationValues) {
      return this.visit((PMMLObject)correlationValues);
   }

   public VisitorAction visit(Counts counts) {
      return this.visit((PMMLObject)counts);
   }

   public VisitorAction visit(CountTable countTable) {
      return this.visit((DiscreteDistribution)countTable);
   }

   public VisitorAction visit(Covariances covariances) {
      return this.visit((PMMLObject)covariances);
   }

   public VisitorAction visit(CovariateList covariateList) {
      return this.visit((PredictorList)covariateList);
   }

   public VisitorAction visit(DataDictionary dataDictionary) {
      return this.visit((PMMLObject)dataDictionary);
   }

   public VisitorAction visit(DataField dataField) {
      return this.visit((Field)dataField);
   }

   public VisitorAction visit(Decision decision) {
      return this.visit((PMMLObject)decision);
   }

   public VisitorAction visit(Decisions decisions) {
      return this.visit((PMMLObject)decisions);
   }

   public VisitorAction visit(DecisionTree decisionTree) {
      return this.visit((EmbeddedModel)decisionTree);
   }

   public VisitorAction visit(DefineFunction defineFunction) {
      return this.visit((PMMLObject)defineFunction);
   }

   public VisitorAction visit(Delimiter delimiter) {
      return this.visit((PMMLObject)delimiter);
   }

   public VisitorAction visit(Denominator denominator) {
      return this.visit((PMMLObject)denominator);
   }

   public VisitorAction visit(DerivedField derivedField) {
      return this.visit((Field)derivedField);
   }

   public VisitorAction visit(DiscreteConditionalProbability discreteConditionalProbability) {
      return this.visit((PMMLObject)discreteConditionalProbability);
   }

   public VisitorAction visit(DiscreteNode discreteNode) {
      return this.visit((PMMLObject)discreteNode);
   }

   public VisitorAction visit(Discretize discretize) {
      return this.visit((Expression)discretize);
   }

   public VisitorAction visit(DiscretizeBin discretizeBin) {
      return this.visit((PMMLObject)discretizeBin);
   }

   public VisitorAction visit(DiscrStats discrStats) {
      return this.visit((PMMLObject)discrStats);
   }

   public VisitorAction visit(DocumentTermMatrix documentTermMatrix) {
      return this.visit((PMMLObject)documentTermMatrix);
   }

   public VisitorAction visit(DynamicRegressor dynamicRegressor) {
      return this.visit((PMMLObject)dynamicRegressor);
   }

   public VisitorAction visit(Euclidean euclidean) {
      return this.visit((Distance)euclidean);
   }

   public VisitorAction visit(EventValues eventValues) {
      return this.visit((PMMLObject)eventValues);
   }

   public VisitorAction visit(ExponentialSmoothing exponentialSmoothing) {
      return this.visit((Algorithm)exponentialSmoothing);
   }

   public VisitorAction visit(Extension extension) {
      return this.visit((PMMLObject)extension);
   }

   public VisitorAction visit(FactorList factorList) {
      return this.visit((PredictorList)factorList);
   }

   public VisitorAction visit(False _false) {
      return this.visit((Predicate)_false);
   }

   public VisitorAction visit(FieldColumnPair fieldColumnPair) {
      return this.visit((PMMLObject)fieldColumnPair);
   }

   public VisitorAction visit(FieldRef fieldRef) {
      return this.visit((Expression)fieldRef);
   }

   public VisitorAction visit(FieldValue fieldValue) {
      return this.visit((PMMLObject)fieldValue);
   }

   public VisitorAction visit(FieldValueCount fieldValueCount) {
      return this.visit((PMMLObject)fieldValueCount);
   }

   public VisitorAction visit(FinalNoise finalNoise) {
      return this.visit((PMMLObject)finalNoise);
   }

   public VisitorAction visit(FinalNu finalNu) {
      return this.visit((PMMLObject)finalNu);
   }

   public VisitorAction visit(FinalOmega finalOmega) {
      return this.visit((PMMLObject)finalOmega);
   }

   public VisitorAction visit(FinalPredictedNoise finalPredictedNoise) {
      return this.visit((PMMLObject)finalPredictedNoise);
   }

   public VisitorAction visit(FinalStateVector finalStateVector) {
      return this.visit((PMMLObject)finalStateVector);
   }

   public VisitorAction visit(FinalTheta finalTheta) {
      return this.visit((PMMLObject)finalTheta);
   }

   public VisitorAction visit(GARCH garch) {
      return this.visit((Algorithm)garch);
   }

   public VisitorAction visit(GARCHPart garchPart) {
      return this.visit((PMMLObject)garchPart);
   }

   public VisitorAction visit(GaussianDistribution gaussianDistribution) {
      return this.visit((ContinuousDistribution)gaussianDistribution);
   }

   public VisitorAction visit(GaussianProcessModel gaussianProcessModel) {
      return this.visit((Model)gaussianProcessModel);
   }

   public VisitorAction visit(GeneralizedExponentialKernel generalizedExponentialKernel) {
      return this.visit((PMMLObject)generalizedExponentialKernel);
   }

   public VisitorAction visit(GeneralRegressionModel generalRegressionModel) {
      return this.visit((Model)generalRegressionModel);
   }

   public VisitorAction visit(Header header) {
      return this.visit((PMMLObject)header);
   }

   public VisitorAction visit(HVector hVector) {
      return this.visit((PMMLObject)hVector);
   }

   public VisitorAction visit(InlineTable inlineTable) {
      return this.visit((PMMLObject)inlineTable);
   }

   public VisitorAction visit(InstanceField instanceField) {
      return this.visit((PMMLObject)instanceField);
   }

   public VisitorAction visit(InstanceFields instanceFields) {
      return this.visit((PMMLObject)instanceFields);
   }

   public VisitorAction visit(InterceptVector interceptVector) {
      return this.visit((PMMLObject)interceptVector);
   }

   public VisitorAction visit(Interval interval) {
      return this.visit((PMMLObject)interval);
   }

   public VisitorAction visit(IntSparseArray intSparseArray) {
      return this.visit((SparseArray)intSparseArray);
   }

   public VisitorAction visit(Item item) {
      return this.visit((PMMLObject)item);
   }

   public VisitorAction visit(ItemRef itemRef) {
      return this.visit((PMMLObject)itemRef);
   }

   public VisitorAction visit(Itemset itemset) {
      return this.visit((PMMLObject)itemset);
   }

   public VisitorAction visit(Jaccard jaccard) {
      return this.visit((Similarity)jaccard);
   }

   public VisitorAction visit(KalmanState kalmanState) {
      return this.visit((PMMLObject)kalmanState);
   }

   public VisitorAction visit(KNNInput knnInput) {
      return this.visit((ComparisonField)knnInput);
   }

   public VisitorAction visit(KNNInputs knnInputs) {
      return this.visit((PMMLObject)knnInputs);
   }

   public VisitorAction visit(KohonenMap kohonenMap) {
      return this.visit((PMMLObject)kohonenMap);
   }

   public VisitorAction visit(Lag lag) {
      return this.visit((Expression)lag);
   }

   public VisitorAction visit(Lambda lambda) {
      return this.visit((PMMLObject)lambda);
   }

   public VisitorAction visit(Level level) {
      return this.visit((PMMLObject)level);
   }

   public VisitorAction visit(LiftData liftData) {
      return this.visit((PMMLObject)liftData);
   }

   public VisitorAction visit(LiftGraph liftGraph) {
      return this.visit((PMMLObject)liftGraph);
   }

   public VisitorAction visit(LinearKernel linearKernel) {
      return this.visit((Kernel)linearKernel);
   }

   public VisitorAction visit(LinearNorm linearNorm) {
      return this.visit((PMMLObject)linearNorm);
   }

   public VisitorAction visit(LocalTransformations localTransformations) {
      return this.visit((PMMLObject)localTransformations);
   }

   public VisitorAction visit(LognormalDistribution lognormalDistribution) {
      return this.visit((PMMLObject)lognormalDistribution);
   }

   public VisitorAction visit(Lower lower) {
      return this.visit((PMMLObject)lower);
   }

   public VisitorAction visit(MA ma) {
      return this.visit((PMMLObject)ma);
   }

   public VisitorAction visit(MACoefficients maCoefficients) {
      return this.visit((PMMLObject)maCoefficients);
   }

   public VisitorAction visit(MapValues mapValues) {
      return this.visit((Expression)mapValues);
   }

   public VisitorAction visit(MatCell matCell) {
      return this.visit((PMMLObject)matCell);
   }

   public VisitorAction visit(Matrix matrix) {
      return this.visit((PMMLObject)matrix);
   }

   public VisitorAction visit(MaximumLikelihoodStat maximumLikelihoodStat) {
      return this.visit((PMMLObject)maximumLikelihoodStat);
   }

   public VisitorAction visit(Mean mean) {
      return this.visit((PMMLObject)mean);
   }

   public VisitorAction visit(MeanClusterDistances meanClusterDistances) {
      return this.visit((PMMLObject)meanClusterDistances);
   }

   public VisitorAction visit(MeasurementMatrix measurementMatrix) {
      return this.visit((PMMLObject)measurementMatrix);
   }

   public VisitorAction visit(MiningBuildTask miningBuildTask) {
      return this.visit((PMMLObject)miningBuildTask);
   }

   public VisitorAction visit(MiningField miningField) {
      return this.visit((PMMLObject)miningField);
   }

   public VisitorAction visit(MiningModel miningModel) {
      return this.visit((Model)miningModel);
   }

   public VisitorAction visit(MiningSchema miningSchema) {
      return this.visit((PMMLObject)miningSchema);
   }

   public VisitorAction visit(Minkowski minkowski) {
      return this.visit((Distance)minkowski);
   }

   public VisitorAction visit(MissingValueWeights missingValueWeights) {
      return this.visit((PMMLObject)missingValueWeights);
   }

   public VisitorAction visit(ModelExplanation modelExplanation) {
      return this.visit((PMMLObject)modelExplanation);
   }

   public VisitorAction visit(ModelLiftGraph modelLiftGraph) {
      return this.visit((PMMLObject)modelLiftGraph);
   }

   public VisitorAction visit(ModelStats modelStats) {
      return this.visit((PMMLObject)modelStats);
   }

   public VisitorAction visit(ModelVerification modelVerification) {
      return this.visit((PMMLObject)modelVerification);
   }

   public VisitorAction visit(MultivariateStat multivariateStat) {
      return this.visit((PMMLObject)multivariateStat);
   }

   public VisitorAction visit(MultivariateStats multivariateStats) {
      return this.visit((PMMLObject)multivariateStats);
   }

   public VisitorAction visit(NaiveBayesModel naiveBayesModel) {
      return this.visit((Model)naiveBayesModel);
   }

   public VisitorAction visit(NearestNeighborModel nearestNeighborModel) {
      return this.visit((Model)nearestNeighborModel);
   }

   public VisitorAction visit(NeuralInput neuralInput) {
      return this.visit((NeuralEntity)neuralInput);
   }

   public VisitorAction visit(NeuralInputs neuralInputs) {
      return this.visit((PMMLObject)neuralInputs);
   }

   public VisitorAction visit(NeuralLayer neuralLayer) {
      return this.visit((PMMLObject)neuralLayer);
   }

   public VisitorAction visit(NeuralNetwork neuralNetwork) {
      return this.visit((Model)neuralNetwork);
   }

   public VisitorAction visit(NeuralOutput neuralOutput) {
      return this.visit((PMMLObject)neuralOutput);
   }

   public VisitorAction visit(NeuralOutputs neuralOutputs) {
      return this.visit((PMMLObject)neuralOutputs);
   }

   public VisitorAction visit(Neuron neuron) {
      return this.visit((NeuralEntity)neuron);
   }

   public VisitorAction visit(NonseasonalComponent nonseasonalComponent) {
      return this.visit((PMMLObject)nonseasonalComponent);
   }

   public VisitorAction visit(NonseasonalFactor nonseasonalFactor) {
      return this.visit((PMMLObject)nonseasonalFactor);
   }

   public VisitorAction visit(NormalDistribution normalDistribution) {
      return this.visit((PMMLObject)normalDistribution);
   }

   public VisitorAction visit(NormContinuous normContinuous) {
      return this.visit((Expression)normContinuous);
   }

   public VisitorAction visit(NormDiscrete normDiscrete) {
      return this.visit((Expression)normDiscrete);
   }

   public VisitorAction visit(Numerator numerator) {
      return this.visit((PMMLObject)numerator);
   }

   public VisitorAction visit(NumericInfo numericInfo) {
      return this.visit((PMMLObject)numericInfo);
   }

   public VisitorAction visit(NumericPredictor numericPredictor) {
      return this.visit((Term)numericPredictor);
   }

   public VisitorAction visit(ObservationVarianceMatrix observationVarianceMatrix) {
      return this.visit((PMMLObject)observationVarianceMatrix);
   }

   public VisitorAction visit(OptimumLiftGraph optimumLiftGraph) {
      return this.visit((PMMLObject)optimumLiftGraph);
   }

   public VisitorAction visit(OutlierEffect outlierEffect) {
      return this.visit((PMMLObject)outlierEffect);
   }

   public VisitorAction visit(Output output) {
      return this.visit((PMMLObject)output);
   }

   public VisitorAction visit(OutputField outputField) {
      return this.visit((Field)outputField);
   }

   public VisitorAction visit(PairCounts pairCounts) {
      return this.visit((PMMLObject)pairCounts);
   }

   public VisitorAction visit(Parameter parameter) {
      return this.visit((PMMLObject)parameter);
   }

   public VisitorAction visit(ParameterField parameterField) {
      return this.visit((Field)parameterField);
   }

   public VisitorAction visit(ParameterList parameterList) {
      return this.visit((PMMLObject)parameterList);
   }

   public VisitorAction visit(ParamMatrix paramMatrix) {
      return this.visit((PMMLObject)paramMatrix);
   }

   public VisitorAction visit(ParentValue parentValue) {
      return this.visit((PMMLObject)parentValue);
   }

   public VisitorAction visit(Partition partition) {
      return this.visit((PMMLObject)partition);
   }

   public VisitorAction visit(PartitionFieldStats partitionFieldStats) {
      return this.visit((PMMLObject)partitionFieldStats);
   }

   public VisitorAction visit(PastVariances pastVariances) {
      return this.visit((PMMLObject)pastVariances);
   }

   public VisitorAction visit(PCell pCell) {
      return this.visit((ParameterCell)pCell);
   }

   public VisitorAction visit(PCovCell pCovCell) {
      return this.visit((PMMLObject)pCovCell);
   }

   public VisitorAction visit(PCovMatrix pCovMatrix) {
      return this.visit((PMMLObject)pCovMatrix);
   }

   public VisitorAction visit(PMML pmml) {
      return this.visit((PMMLObject)pmml);
   }

   public VisitorAction visit(PoissonDistribution poissonDistribution) {
      return this.visit((ContinuousDistribution)poissonDistribution);
   }

   public VisitorAction visit(PolynomialKernel polynomialKernel) {
      return this.visit((Kernel)polynomialKernel);
   }

   public VisitorAction visit(PPCell ppCell) {
      return this.visit((ParameterCell)ppCell);
   }

   public VisitorAction visit(PPMatrix ppMatrix) {
      return this.visit((PMMLObject)ppMatrix);
   }

   public VisitorAction visit(PredictedStateCovarianceMatrix predictedStateCovarianceMatrix) {
      return this.visit((PMMLObject)predictedStateCovarianceMatrix);
   }

   public VisitorAction visit(PredictiveModelQuality predictiveModelQuality) {
      return this.visit((PMMLObject)predictiveModelQuality);
   }

   public VisitorAction visit(Predictor predictor) {
      return this.visit((PMMLObject)predictor);
   }

   public VisitorAction visit(PredictorTerm predictorTerm) {
      return this.visit((Term)predictorTerm);
   }

   public VisitorAction visit(PsiVector psiVector) {
      return this.visit((PMMLObject)psiVector);
   }

   public VisitorAction visit(Quantile quantile) {
      return this.visit((PMMLObject)quantile);
   }

   public VisitorAction visit(RadialBasisKernel radialBasisKernel) {
      return this.visit((PMMLObject)radialBasisKernel);
   }

   public VisitorAction visit(org.sparkproject.dmg.pmml.support_vector_machine.RadialBasisKernel radialBasisKernel) {
      return this.visit((Kernel)radialBasisKernel);
   }

   public VisitorAction visit(RandomLiftGraph randomLiftGraph) {
      return this.visit((PMMLObject)randomLiftGraph);
   }

   public VisitorAction visit(RealSparseArray realSparseArray) {
      return this.visit((SparseArray)realSparseArray);
   }

   public VisitorAction visit(Regression regression) {
      return this.visit((EmbeddedModel)regression);
   }

   public VisitorAction visit(RegressionModel regressionModel) {
      return this.visit((Model)regressionModel);
   }

   public VisitorAction visit(RegressionTable regressionTable) {
      return this.visit((PMMLObject)regressionTable);
   }

   public VisitorAction visit(RegressorValues regressorValues) {
      return this.visit((PMMLObject)regressorValues);
   }

   public VisitorAction visit(Residuals residuals) {
      return this.visit((PMMLObject)residuals);
   }

   public VisitorAction visit(ResidualSquareCoefficients residualSquareCoefficients) {
      return this.visit((PMMLObject)residualSquareCoefficients);
   }

   public VisitorAction visit(ResultField resultField) {
      return this.visit((Field)resultField);
   }

   public VisitorAction visit(ROC roc) {
      return this.visit((PMMLObject)roc);
   }

   public VisitorAction visit(ROCGraph rocGraph) {
      return this.visit((PMMLObject)rocGraph);
   }

   public VisitorAction visit(Row row) {
      return this.visit((PMMLObject)row);
   }

   public VisitorAction visit(RuleSelectionMethod ruleSelectionMethod) {
      return this.visit((PMMLObject)ruleSelectionMethod);
   }

   public VisitorAction visit(RuleSet ruleSet) {
      return this.visit((PMMLObject)ruleSet);
   }

   public VisitorAction visit(RuleSetModel ruleSetModel) {
      return this.visit((Model)ruleSetModel);
   }

   public VisitorAction visit(Scorecard scorecard) {
      return this.visit((Model)scorecard);
   }

   public VisitorAction visit(SeasonalComponent seasonalComponent) {
      return this.visit((PMMLObject)seasonalComponent);
   }

   public VisitorAction visit(SeasonalFactor seasonalFactor) {
      return this.visit((PMMLObject)seasonalFactor);
   }

   public VisitorAction visit(SeasonalityExpoSmooth seasonalityExpoSmooth) {
      return this.visit((PMMLObject)seasonalityExpoSmooth);
   }

   public VisitorAction visit(SeasonalTrendDecomposition seasonalTrendDecomposition) {
      return this.visit((Algorithm)seasonalTrendDecomposition);
   }

   public VisitorAction visit(Segment segment) {
      return this.visit((Entity)segment);
   }

   public VisitorAction visit(Segmentation segmentation) {
      return this.visit((PMMLObject)segmentation);
   }

   public VisitorAction visit(SelectedStateCovarianceMatrix selectedStateCovarianceMatrix) {
      return this.visit((PMMLObject)selectedStateCovarianceMatrix);
   }

   public VisitorAction visit(Sequence sequence) {
      return this.visit((PMMLObject)sequence);
   }

   public VisitorAction visit(SequenceModel sequenceModel) {
      return this.visit((Model)sequenceModel);
   }

   public VisitorAction visit(SequenceReference sequenceReference) {
      return this.visit((PMMLObject)sequenceReference);
   }

   public VisitorAction visit(SequenceRule sequenceRule) {
      return this.visit((Entity)sequenceRule);
   }

   public VisitorAction visit(SetPredicate setPredicate) {
      return this.visit((PMMLObject)setPredicate);
   }

   public VisitorAction visit(SetReference setReference) {
      return this.visit((PMMLObject)setReference);
   }

   public VisitorAction visit(SigmoidKernel sigmoidKernel) {
      return this.visit((Kernel)sigmoidKernel);
   }

   public VisitorAction visit(SimpleMatching simpleMatching) {
      return this.visit((Similarity)simpleMatching);
   }

   public VisitorAction visit(SimplePredicate simplePredicate) {
      return this.visit((Predicate)simplePredicate);
   }

   public VisitorAction visit(SimpleRule simpleRule) {
      return this.visit((Rule)simpleRule);
   }

   public VisitorAction visit(SimpleSetPredicate simpleSetPredicate) {
      return this.visit((Predicate)simpleSetPredicate);
   }

   public VisitorAction visit(SpectralAnalysis spectralAnalysis) {
      return this.visit((Algorithm)spectralAnalysis);
   }

   public VisitorAction visit(SquaredEuclidean squaredEuclidean) {
      return this.visit((Distance)squaredEuclidean);
   }

   public VisitorAction visit(StateSpaceModel stateSpaceModel) {
      return this.visit((Algorithm)stateSpaceModel);
   }

   public VisitorAction visit(StateVector stateVector) {
      return this.visit((PMMLObject)stateVector);
   }

   public VisitorAction visit(SupportVector supportVector) {
      return this.visit((PMMLObject)supportVector);
   }

   public VisitorAction visit(SupportVectorMachine supportVectorMachine) {
      return this.visit((PMMLObject)supportVectorMachine);
   }

   public VisitorAction visit(SupportVectorMachineModel supportVectorMachineModel) {
      return this.visit((Model)supportVectorMachineModel);
   }

   public VisitorAction visit(SupportVectors supportVectors) {
      return this.visit((PMMLObject)supportVectors);
   }

   public VisitorAction visit(TableLocator tableLocator) {
      return this.visit((PMMLObject)tableLocator);
   }

   public VisitorAction visit(Tanimoto tanimoto) {
      return this.visit((Similarity)tanimoto);
   }

   public VisitorAction visit(Target target) {
      return this.visit((PMMLObject)target);
   }

   public VisitorAction visit(Targets targets) {
      return this.visit((PMMLObject)targets);
   }

   public VisitorAction visit(TargetValue targetValue) {
      return this.visit((PMMLObject)targetValue);
   }

   public VisitorAction visit(TargetValueCount targetValueCount) {
      return this.visit((PMMLObject)targetValueCount);
   }

   public VisitorAction visit(TargetValueCounts targetValueCounts) {
      return this.visit((PMMLObject)targetValueCounts);
   }

   public VisitorAction visit(TargetValueStat targetValueStat) {
      return this.visit((PMMLObject)targetValueStat);
   }

   public VisitorAction visit(TargetValueStats targetValueStats) {
      return this.visit((PMMLObject)targetValueStats);
   }

   public VisitorAction visit(Taxonomy taxonomy) {
      return this.visit((PMMLObject)taxonomy);
   }

   public VisitorAction visit(TestDistributions testDistributions) {
      return this.visit((PMMLObject)testDistributions);
   }

   public VisitorAction visit(TextCorpus textCorpus) {
      return this.visit((PMMLObject)textCorpus);
   }

   public VisitorAction visit(TextDictionary textDictionary) {
      return this.visit((PMMLObject)textDictionary);
   }

   public VisitorAction visit(TextDocument textDocument) {
      return this.visit((PMMLObject)textDocument);
   }

   public VisitorAction visit(TextIndex textIndex) {
      return this.visit((Expression)textIndex);
   }

   public VisitorAction visit(TextIndexNormalization textIndexNormalization) {
      return this.visit((PMMLObject)textIndexNormalization);
   }

   public VisitorAction visit(TextModel textModel) {
      return this.visit((Model)textModel);
   }

   public VisitorAction visit(TextModelNormalization textModelNormalization) {
      return this.visit((PMMLObject)textModelNormalization);
   }

   public VisitorAction visit(TextModelSimiliarity textModelSimiliarity) {
      return this.visit((PMMLObject)textModelSimiliarity);
   }

   public VisitorAction visit(Theta theta) {
      return this.visit((PMMLObject)theta);
   }

   public VisitorAction visit(ThetaRecursionState thetaRecursionState) {
      return this.visit((PMMLObject)thetaRecursionState);
   }

   public VisitorAction visit(Time time) {
      return this.visit((PMMLObject)time);
   }

   public VisitorAction visit(TimeAnchor timeAnchor) {
      return this.visit((PMMLObject)timeAnchor);
   }

   public VisitorAction visit(TimeCycle timeCycle) {
      return this.visit((PMMLObject)timeCycle);
   }

   public VisitorAction visit(TimeException timeException) {
      return this.visit((PMMLObject)timeException);
   }

   public VisitorAction visit(TimeSeries timeSeries) {
      return this.visit((PMMLObject)timeSeries);
   }

   public VisitorAction visit(TimeSeriesModel timeSeriesModel) {
      return this.visit((Model)timeSeriesModel);
   }

   public VisitorAction visit(Timestamp timestamp) {
      return this.visit((PMMLObject)timestamp);
   }

   public VisitorAction visit(TimeValue timeValue) {
      return this.visit((PMMLObject)timeValue);
   }

   public VisitorAction visit(TrainingInstances trainingInstances) {
      return this.visit((PMMLObject)trainingInstances);
   }

   public VisitorAction visit(TransferFunctionValues transferFunctionValues) {
      return this.visit((PMMLObject)transferFunctionValues);
   }

   public VisitorAction visit(TransformationDictionary transformationDictionary) {
      return this.visit((PMMLObject)transformationDictionary);
   }

   public VisitorAction visit(TransitionMatrix transitionMatrix) {
      return this.visit((PMMLObject)transitionMatrix);
   }

   public VisitorAction visit(TreeModel treeModel) {
      return this.visit((Model)treeModel);
   }

   public VisitorAction visit(TrendCoefficients trendCoefficients) {
      return this.visit((PMMLObject)trendCoefficients);
   }

   public VisitorAction visit(TrendExpoSmooth trendExpoSmooth) {
      return this.visit((PMMLObject)trendExpoSmooth);
   }

   public VisitorAction visit(TriangularDistribution triangularDistribution) {
      return this.visit((PMMLObject)triangularDistribution);
   }

   public VisitorAction visit(True _true) {
      return this.visit((Predicate)_true);
   }

   public VisitorAction visit(UniformDistribution uniformDistribution) {
      return this.visit((PMMLObject)uniformDistribution);
   }

   public VisitorAction visit(org.sparkproject.dmg.pmml.UniformDistribution uniformDistribution) {
      return this.visit((ContinuousDistribution)uniformDistribution);
   }

   public VisitorAction visit(UnivariateStats univariateStats) {
      return this.visit((PMMLObject)univariateStats);
   }

   public VisitorAction visit(Upper upper) {
      return this.visit((PMMLObject)upper);
   }

   public VisitorAction visit(Value value) {
      return this.visit((PMMLObject)value);
   }

   public VisitorAction visit(ValueProbability valueProbability) {
      return this.visit((PMMLObject)valueProbability);
   }

   public VisitorAction visit(VariableWeight variableWeight) {
      return this.visit((PMMLObject)variableWeight);
   }

   public VisitorAction visit(Variance variance) {
      return this.visit((PMMLObject)variance);
   }

   public VisitorAction visit(VarianceCoefficients varianceCoefficients) {
      return this.visit((PMMLObject)varianceCoefficients);
   }

   public VisitorAction visit(VectorDictionary vectorDictionary) {
      return this.visit((PMMLObject)vectorDictionary);
   }

   public VisitorAction visit(VectorFields vectorFields) {
      return this.visit((PMMLObject)vectorFields);
   }

   public VisitorAction visit(VectorInstance vectorInstance) {
      return this.visit((PMMLObject)vectorInstance);
   }

   public VisitorAction visit(VerificationField verificationField) {
      return this.visit((PMMLObject)verificationField);
   }

   public VisitorAction visit(VerificationFields verificationFields) {
      return this.visit((PMMLObject)verificationFields);
   }

   public VisitorAction visit(XCoordinates xCoordinates) {
      return this.visit((PMMLObject)xCoordinates);
   }

   public VisitorAction visit(YCoordinates yCoordinates) {
      return this.visit((PMMLObject)yCoordinates);
   }
}
