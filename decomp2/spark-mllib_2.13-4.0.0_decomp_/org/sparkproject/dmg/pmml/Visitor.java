package org.sparkproject.dmg.pmml;

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

public interface Visitor extends VisitContext {
   void applyTo(Visitable var1);

   VisitorAction visit(Cell var1);

   VisitorAction visit(ComparisonField var1);

   VisitorAction visit(ContinuousDistribution var1);

   VisitorAction visit(DiscreteDistribution var1);

   VisitorAction visit(Distance var1);

   VisitorAction visit(Distribution var1);

   VisitorAction visit(EmbeddedModel var1);

   VisitorAction visit(Entity var1);

   VisitorAction visit(Expression var1);

   VisitorAction visit(Field var1);

   VisitorAction visit(Kernel var1);

   VisitorAction visit(Measure var1);

   VisitorAction visit(Model var1);

   VisitorAction visit(NeuralEntity var1);

   VisitorAction visit(Node var1);

   VisitorAction visit(ParameterCell var1);

   VisitorAction visit(Predicate var1);

   VisitorAction visit(PredictorList var1);

   VisitorAction visit(Rule var1);

   VisitorAction visit(ScoreDistribution var1);

   VisitorAction visit(Similarity var1);

   VisitorAction visit(SparseArray var1);

   VisitorAction visit(Term var1);

   VisitorAction visit(Algorithm var1);

   VisitorAction visit(AbsoluteExponentialKernel var1);

   VisitorAction visit(Aggregate var1);

   VisitorAction visit(Alternate var1);

   VisitorAction visit(Annotation var1);

   VisitorAction visit(AnomalyDetectionModel var1);

   VisitorAction visit(Anova var1);

   VisitorAction visit(AnovaRow var1);

   VisitorAction visit(AntecedentSequence var1);

   VisitorAction visit(AnyDistribution var1);

   VisitorAction visit(Application var1);

   VisitorAction visit(Apply var1);

   VisitorAction visit(AR var1);

   VisitorAction visit(ARDSquaredExponentialKernel var1);

   VisitorAction visit(ARIMA var1);

   VisitorAction visit(ARMAPart var1);

   VisitorAction visit(Array var1);

   VisitorAction visit(AssociationModel var1);

   VisitorAction visit(AssociationRule var1);

   VisitorAction visit(Attribute var1);

   VisitorAction visit(BaseCumHazardTables var1);

   VisitorAction visit(Baseline var1);

   VisitorAction visit(BaselineCell var1);

   VisitorAction visit(BaselineModel var1);

   VisitorAction visit(BaselineStratum var1);

   VisitorAction visit(BayesianNetworkModel var1);

   VisitorAction visit(BayesianNetworkNodes var1);

   VisitorAction visit(BayesInput var1);

   VisitorAction visit(BayesInputs var1);

   VisitorAction visit(BayesOutput var1);

   VisitorAction visit(BinarySimilarity var1);

   VisitorAction visit(BlockIndicator var1);

   VisitorAction visit(BoundaryValueMeans var1);

   VisitorAction visit(BoundaryValues var1);

   VisitorAction visit(CategoricalPredictor var1);

   VisitorAction visit(Categories var1);

   VisitorAction visit(Category var1);

   VisitorAction visit(CenterFields var1);

   VisitorAction visit(Characteristic var1);

   VisitorAction visit(Characteristics var1);

   VisitorAction visit(Chebychev var1);

   VisitorAction visit(ChildParent var1);

   VisitorAction visit(CityBlock var1);

   VisitorAction visit(ClassLabels var1);

   VisitorAction visit(Cluster var1);

   VisitorAction visit(ClusteringField var1);

   VisitorAction visit(ClusteringModel var1);

   VisitorAction visit(ClusteringModelQuality var1);

   VisitorAction visit(Coefficient var1);

   VisitorAction visit(Coefficients var1);

   VisitorAction visit(ComparisonMeasure var1);

   VisitorAction visit(Comparisons var1);

   VisitorAction visit(ComplexNode var1);

   VisitorAction visit(ComplexPartialScore var1);

   VisitorAction visit(ComplexScoreDistribution var1);

   VisitorAction visit(CompoundPredicate var1);

   VisitorAction visit(CompoundRule var1);

   VisitorAction visit(ConfusionMatrix var1);

   VisitorAction visit(Connection var1);

   VisitorAction visit(ConsequentSequence var1);

   VisitorAction visit(Constant var1);

   VisitorAction visit(Constraints var1);

   VisitorAction visit(ContinuousConditionalProbability var1);

   VisitorAction visit(org.sparkproject.dmg.pmml.bayesian_network.ContinuousDistribution var1);

   VisitorAction visit(ContinuousNode var1);

   VisitorAction visit(ContStats var1);

   VisitorAction visit(CorrelationFields var1);

   VisitorAction visit(CorrelationMethods var1);

   VisitorAction visit(Correlations var1);

   VisitorAction visit(CorrelationValues var1);

   VisitorAction visit(Counts var1);

   VisitorAction visit(CountTable var1);

   VisitorAction visit(Covariances var1);

   VisitorAction visit(CovariateList var1);

   VisitorAction visit(DataDictionary var1);

   VisitorAction visit(DataField var1);

   VisitorAction visit(Decision var1);

   VisitorAction visit(Decisions var1);

   VisitorAction visit(DecisionTree var1);

   VisitorAction visit(DefineFunction var1);

   VisitorAction visit(Delimiter var1);

   VisitorAction visit(Denominator var1);

   VisitorAction visit(DerivedField var1);

   VisitorAction visit(DiscreteConditionalProbability var1);

   VisitorAction visit(DiscreteNode var1);

   VisitorAction visit(Discretize var1);

   VisitorAction visit(DiscretizeBin var1);

   VisitorAction visit(DiscrStats var1);

   VisitorAction visit(DocumentTermMatrix var1);

   VisitorAction visit(DynamicRegressor var1);

   VisitorAction visit(Euclidean var1);

   VisitorAction visit(EventValues var1);

   VisitorAction visit(ExponentialSmoothing var1);

   VisitorAction visit(Extension var1);

   VisitorAction visit(FactorList var1);

   VisitorAction visit(False var1);

   VisitorAction visit(FieldColumnPair var1);

   VisitorAction visit(FieldRef var1);

   VisitorAction visit(FieldValue var1);

   VisitorAction visit(FieldValueCount var1);

   VisitorAction visit(FinalNoise var1);

   VisitorAction visit(FinalNu var1);

   VisitorAction visit(FinalOmega var1);

   VisitorAction visit(FinalPredictedNoise var1);

   VisitorAction visit(FinalStateVector var1);

   VisitorAction visit(FinalTheta var1);

   VisitorAction visit(GARCH var1);

   VisitorAction visit(GARCHPart var1);

   VisitorAction visit(GaussianDistribution var1);

   VisitorAction visit(GaussianProcessModel var1);

   VisitorAction visit(GeneralizedExponentialKernel var1);

   VisitorAction visit(GeneralRegressionModel var1);

   VisitorAction visit(Header var1);

   VisitorAction visit(HVector var1);

   VisitorAction visit(InlineTable var1);

   VisitorAction visit(InstanceField var1);

   VisitorAction visit(InstanceFields var1);

   VisitorAction visit(InterceptVector var1);

   VisitorAction visit(Interval var1);

   VisitorAction visit(IntSparseArray var1);

   VisitorAction visit(Item var1);

   VisitorAction visit(ItemRef var1);

   VisitorAction visit(Itemset var1);

   VisitorAction visit(Jaccard var1);

   VisitorAction visit(KalmanState var1);

   VisitorAction visit(KNNInput var1);

   VisitorAction visit(KNNInputs var1);

   VisitorAction visit(KohonenMap var1);

   VisitorAction visit(Lag var1);

   VisitorAction visit(Lambda var1);

   VisitorAction visit(Level var1);

   VisitorAction visit(LiftData var1);

   VisitorAction visit(LiftGraph var1);

   VisitorAction visit(LinearKernel var1);

   VisitorAction visit(LinearNorm var1);

   VisitorAction visit(LocalTransformations var1);

   VisitorAction visit(LognormalDistribution var1);

   VisitorAction visit(Lower var1);

   VisitorAction visit(MA var1);

   VisitorAction visit(MACoefficients var1);

   VisitorAction visit(MapValues var1);

   VisitorAction visit(MatCell var1);

   VisitorAction visit(Matrix var1);

   VisitorAction visit(MaximumLikelihoodStat var1);

   VisitorAction visit(Mean var1);

   VisitorAction visit(MeanClusterDistances var1);

   VisitorAction visit(MeasurementMatrix var1);

   VisitorAction visit(MiningBuildTask var1);

   VisitorAction visit(MiningField var1);

   VisitorAction visit(MiningModel var1);

   VisitorAction visit(MiningSchema var1);

   VisitorAction visit(Minkowski var1);

   VisitorAction visit(MissingValueWeights var1);

   VisitorAction visit(ModelExplanation var1);

   VisitorAction visit(ModelLiftGraph var1);

   VisitorAction visit(ModelStats var1);

   VisitorAction visit(ModelVerification var1);

   VisitorAction visit(MultivariateStat var1);

   VisitorAction visit(MultivariateStats var1);

   VisitorAction visit(NaiveBayesModel var1);

   VisitorAction visit(NearestNeighborModel var1);

   VisitorAction visit(NeuralInput var1);

   VisitorAction visit(NeuralInputs var1);

   VisitorAction visit(NeuralLayer var1);

   VisitorAction visit(NeuralNetwork var1);

   VisitorAction visit(NeuralOutput var1);

   VisitorAction visit(NeuralOutputs var1);

   VisitorAction visit(Neuron var1);

   VisitorAction visit(NonseasonalComponent var1);

   VisitorAction visit(NonseasonalFactor var1);

   VisitorAction visit(NormalDistribution var1);

   VisitorAction visit(NormContinuous var1);

   VisitorAction visit(NormDiscrete var1);

   VisitorAction visit(Numerator var1);

   VisitorAction visit(NumericInfo var1);

   VisitorAction visit(NumericPredictor var1);

   VisitorAction visit(ObservationVarianceMatrix var1);

   VisitorAction visit(OptimumLiftGraph var1);

   VisitorAction visit(OutlierEffect var1);

   VisitorAction visit(Output var1);

   VisitorAction visit(OutputField var1);

   VisitorAction visit(PairCounts var1);

   VisitorAction visit(Parameter var1);

   VisitorAction visit(ParameterField var1);

   VisitorAction visit(ParameterList var1);

   VisitorAction visit(ParamMatrix var1);

   VisitorAction visit(ParentValue var1);

   VisitorAction visit(Partition var1);

   VisitorAction visit(PartitionFieldStats var1);

   VisitorAction visit(PastVariances var1);

   VisitorAction visit(PCell var1);

   VisitorAction visit(PCovCell var1);

   VisitorAction visit(PCovMatrix var1);

   VisitorAction visit(PMML var1);

   VisitorAction visit(PoissonDistribution var1);

   VisitorAction visit(PolynomialKernel var1);

   VisitorAction visit(PPCell var1);

   VisitorAction visit(PPMatrix var1);

   VisitorAction visit(PredictedStateCovarianceMatrix var1);

   VisitorAction visit(PredictiveModelQuality var1);

   VisitorAction visit(Predictor var1);

   VisitorAction visit(PredictorTerm var1);

   VisitorAction visit(PsiVector var1);

   VisitorAction visit(Quantile var1);

   VisitorAction visit(RadialBasisKernel var1);

   VisitorAction visit(org.sparkproject.dmg.pmml.support_vector_machine.RadialBasisKernel var1);

   VisitorAction visit(RandomLiftGraph var1);

   VisitorAction visit(RealSparseArray var1);

   VisitorAction visit(Regression var1);

   VisitorAction visit(RegressionModel var1);

   VisitorAction visit(RegressionTable var1);

   VisitorAction visit(RegressorValues var1);

   VisitorAction visit(Residuals var1);

   VisitorAction visit(ResidualSquareCoefficients var1);

   VisitorAction visit(ResultField var1);

   VisitorAction visit(ROC var1);

   VisitorAction visit(ROCGraph var1);

   VisitorAction visit(Row var1);

   VisitorAction visit(RuleSelectionMethod var1);

   VisitorAction visit(RuleSet var1);

   VisitorAction visit(RuleSetModel var1);

   VisitorAction visit(Scorecard var1);

   VisitorAction visit(SeasonalComponent var1);

   VisitorAction visit(SeasonalFactor var1);

   VisitorAction visit(SeasonalityExpoSmooth var1);

   VisitorAction visit(SeasonalTrendDecomposition var1);

   VisitorAction visit(Segment var1);

   VisitorAction visit(Segmentation var1);

   VisitorAction visit(SelectedStateCovarianceMatrix var1);

   VisitorAction visit(Sequence var1);

   VisitorAction visit(SequenceModel var1);

   VisitorAction visit(SequenceReference var1);

   VisitorAction visit(SequenceRule var1);

   VisitorAction visit(SetPredicate var1);

   VisitorAction visit(SetReference var1);

   VisitorAction visit(SigmoidKernel var1);

   VisitorAction visit(SimpleMatching var1);

   VisitorAction visit(SimplePredicate var1);

   VisitorAction visit(SimpleRule var1);

   VisitorAction visit(SimpleSetPredicate var1);

   VisitorAction visit(SpectralAnalysis var1);

   VisitorAction visit(SquaredEuclidean var1);

   VisitorAction visit(StateSpaceModel var1);

   VisitorAction visit(StateVector var1);

   VisitorAction visit(SupportVector var1);

   VisitorAction visit(SupportVectorMachine var1);

   VisitorAction visit(SupportVectorMachineModel var1);

   VisitorAction visit(SupportVectors var1);

   VisitorAction visit(TableLocator var1);

   VisitorAction visit(Tanimoto var1);

   VisitorAction visit(Target var1);

   VisitorAction visit(Targets var1);

   VisitorAction visit(TargetValue var1);

   VisitorAction visit(TargetValueCount var1);

   VisitorAction visit(TargetValueCounts var1);

   VisitorAction visit(TargetValueStat var1);

   VisitorAction visit(TargetValueStats var1);

   VisitorAction visit(Taxonomy var1);

   VisitorAction visit(TestDistributions var1);

   VisitorAction visit(TextCorpus var1);

   VisitorAction visit(TextDictionary var1);

   VisitorAction visit(TextDocument var1);

   VisitorAction visit(TextIndex var1);

   VisitorAction visit(TextIndexNormalization var1);

   VisitorAction visit(TextModel var1);

   VisitorAction visit(TextModelNormalization var1);

   VisitorAction visit(TextModelSimiliarity var1);

   VisitorAction visit(Theta var1);

   VisitorAction visit(ThetaRecursionState var1);

   VisitorAction visit(Time var1);

   VisitorAction visit(TimeAnchor var1);

   VisitorAction visit(TimeCycle var1);

   VisitorAction visit(TimeException var1);

   VisitorAction visit(TimeSeries var1);

   VisitorAction visit(TimeSeriesModel var1);

   VisitorAction visit(Timestamp var1);

   VisitorAction visit(TimeValue var1);

   VisitorAction visit(TrainingInstances var1);

   VisitorAction visit(TransferFunctionValues var1);

   VisitorAction visit(TransformationDictionary var1);

   VisitorAction visit(TransitionMatrix var1);

   VisitorAction visit(TreeModel var1);

   VisitorAction visit(TrendCoefficients var1);

   VisitorAction visit(TrendExpoSmooth var1);

   VisitorAction visit(TriangularDistribution var1);

   VisitorAction visit(True var1);

   VisitorAction visit(org.sparkproject.dmg.pmml.bayesian_network.UniformDistribution var1);

   VisitorAction visit(UniformDistribution var1);

   VisitorAction visit(UnivariateStats var1);

   VisitorAction visit(Upper var1);

   VisitorAction visit(Value var1);

   VisitorAction visit(ValueProbability var1);

   VisitorAction visit(VariableWeight var1);

   VisitorAction visit(Variance var1);

   VisitorAction visit(VarianceCoefficients var1);

   VisitorAction visit(VectorDictionary var1);

   VisitorAction visit(VectorFields var1);

   VisitorAction visit(VectorInstance var1);

   VisitorAction visit(VerificationField var1);

   VisitorAction visit(VerificationFields var1);

   VisitorAction visit(XCoordinates var1);

   VisitorAction visit(YCoordinates var1);
}
