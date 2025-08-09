package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import java.util.List;
import javax.xml.namespace.QName;
import org.sparkproject.dmg.pmml.baseline.CountTable;

@XmlRegistry
public class ObjectFactory {
   private static final QName _CountTable_QNAME = new QName("http://www.dmg.org/PMML-4_4", "CountTable");
   private static final QName _NormalizedCountTable_QNAME = new QName("http://www.dmg.org/PMML-4_4", "NormalizedCountTable");
   private static final QName _Indices_QNAME = new QName("http://www.dmg.org/PMML-4_4", "Indices");
   private static final QName _INTEntries_QNAME = new QName("http://www.dmg.org/PMML-4_4", "INT-Entries");
   private static final QName _REALEntries_QNAME = new QName("http://www.dmg.org/PMML-4_4", "REAL-Entries");

   public NormDiscrete createNormDiscrete() {
      return new NormDiscrete();
   }

   public Interval createInterval() {
      return new Interval();
   }

   public Aggregate createAggregate() {
      return new Aggregate();
   }

   public Lag createLag() {
      return new Lag();
   }

   public TextIndex createTextIndex() {
      return new TextIndex();
   }

   public MiningField createMiningField() {
      return new MiningField();
   }

   public Value createValue() {
      return new Value();
   }

   public OutputField createOutputField() {
      return new OutputField();
   }

   public Array createArray() {
      return new Array();
   }

   public AnovaRow createAnovaRow() {
      return new AnovaRow();
   }

   public UnivariateStats createUnivariateStats() {
      return new UnivariateStats();
   }

   public Matrix createMatrix() {
      return new Matrix();
   }

   public PredictiveModelQuality createPredictiveModelQuality() {
      return new PredictiveModelQuality();
   }

   public PartitionFieldStats createPartitionFieldStats() {
      return new PartitionFieldStats();
   }

   public Target createTarget() {
      return new Target();
   }

   public SimplePredicate createSimplePredicate() {
      return new SimplePredicate();
   }

   public SimpleSetPredicate createSimpleSetPredicate() {
      return new SimpleSetPredicate();
   }

   public CompoundPredicate createCompoundPredicate() {
      return new CompoundPredicate();
   }

   public ComparisonMeasure createComparisonMeasure() {
      return new ComparisonMeasure();
   }

   public ChildParent createChildParent() {
      return new ChildParent();
   }

   public DataField createDataField() {
      return new DataField();
   }

   public DefineFunction createDefineFunction() {
      return new DefineFunction();
   }

   public Extension createExtension() {
      return new Extension();
   }

   public ParameterField createParameterField() {
      return new ParameterField();
   }

   public Constant createConstant() {
      return new Constant();
   }

   public FieldRef createFieldRef() {
      return new FieldRef();
   }

   public NormContinuous createNormContinuous() {
      return new NormContinuous();
   }

   public LinearNorm createLinearNorm() {
      return new LinearNorm();
   }

   public Discretize createDiscretize() {
      return new Discretize();
   }

   public DiscretizeBin createDiscretizeBin() {
      return new DiscretizeBin();
   }

   public MapValues createMapValues() {
      return new MapValues();
   }

   public FieldColumnPair createFieldColumnPair() {
      return new FieldColumnPair();
   }

   public TableLocator createTableLocator() {
      return new TableLocator();
   }

   public InlineTable createInlineTable() {
      return new InlineTable();
   }

   public Row createRow() {
      return new Row();
   }

   public TextIndexNormalization createTextIndexNormalization() {
      return new TextIndexNormalization();
   }

   public Apply createApply() {
      return new Apply();
   }

   public BlockIndicator createBlockIndicator() {
      return new BlockIndicator();
   }

   public MiningSchema createMiningSchema() {
      return new MiningSchema();
   }

   public Output createOutput() {
      return new Output();
   }

   public Decisions createDecisions() {
      return new Decisions();
   }

   public Decision createDecision() {
      return new Decision();
   }

   public ModelStats createModelStats() {
      return new ModelStats();
   }

   public Counts createCounts() {
      return new Counts();
   }

   public NumericInfo createNumericInfo() {
      return new NumericInfo();
   }

   public Quantile createQuantile() {
      return new Quantile();
   }

   public DiscrStats createDiscrStats() {
      return new DiscrStats();
   }

   public ContStats createContStats() {
      return new ContStats();
   }

   public Anova createAnova() {
      return new Anova();
   }

   public MultivariateStats createMultivariateStats() {
      return new MultivariateStats();
   }

   public MultivariateStat createMultivariateStat() {
      return new MultivariateStat();
   }

   public ModelExplanation createModelExplanation() {
      return new ModelExplanation();
   }

   public ConfusionMatrix createConfusionMatrix() {
      return new ConfusionMatrix();
   }

   public ClassLabels createClassLabels() {
      return new ClassLabels();
   }

   public MatCell createMatCell() {
      return new MatCell();
   }

   public LiftData createLiftData() {
      return new LiftData();
   }

   public ModelLiftGraph createModelLiftGraph() {
      return new ModelLiftGraph();
   }

   public LiftGraph createLiftGraph() {
      return new LiftGraph();
   }

   public XCoordinates createXCoordinates() {
      return new XCoordinates();
   }

   public YCoordinates createYCoordinates() {
      return new YCoordinates();
   }

   public BoundaryValues createBoundaryValues() {
      return new BoundaryValues();
   }

   public BoundaryValueMeans createBoundaryValueMeans() {
      return new BoundaryValueMeans();
   }

   public OptimumLiftGraph createOptimumLiftGraph() {
      return new OptimumLiftGraph();
   }

   public RandomLiftGraph createRandomLiftGraph() {
      return new RandomLiftGraph();
   }

   public ROC createROC() {
      return new ROC();
   }

   public ROCGraph createROCGraph() {
      return new ROCGraph();
   }

   public ClusteringModelQuality createClusteringModelQuality() {
      return new ClusteringModelQuality();
   }

   public Correlations createCorrelations() {
      return new Correlations();
   }

   public CorrelationFields createCorrelationFields() {
      return new CorrelationFields();
   }

   public CorrelationValues createCorrelationValues() {
      return new CorrelationValues();
   }

   public CorrelationMethods createCorrelationMethods() {
      return new CorrelationMethods();
   }

   public Targets createTargets() {
      return new Targets();
   }

   public TargetValue createTargetValue() {
      return new TargetValue();
   }

   public Partition createPartition() {
      return new Partition();
   }

   public LocalTransformations createLocalTransformations() {
      return new LocalTransformations();
   }

   public DerivedField createDerivedField() {
      return new DerivedField();
   }

   public ResultField createResultField() {
      return new ResultField();
   }

   public True createTrue() {
      return new True();
   }

   public False createFalse() {
      return new False();
   }

   public ComplexScoreDistribution createComplexScoreDistribution() {
      return new ComplexScoreDistribution();
   }

   public ModelVerification createModelVerification() {
      return new ModelVerification();
   }

   public VerificationFields createVerificationFields() {
      return new VerificationFields();
   }

   public VerificationField createVerificationField() {
      return new VerificationField();
   }

   public AnyDistribution createAnyDistribution() {
      return new AnyDistribution();
   }

   public GaussianDistribution createGaussianDistribution() {
      return new GaussianDistribution();
   }

   public PoissonDistribution createPoissonDistribution() {
      return new PoissonDistribution();
   }

   public UniformDistribution createUniformDistribution() {
      return new UniformDistribution();
   }

   public Euclidean createEuclidean() {
      return new Euclidean();
   }

   public SquaredEuclidean createSquaredEuclidean() {
      return new SquaredEuclidean();
   }

   public Chebychev createChebychev() {
      return new Chebychev();
   }

   public CityBlock createCityBlock() {
      return new CityBlock();
   }

   public Minkowski createMinkowski() {
      return new Minkowski();
   }

   public SimpleMatching createSimpleMatching() {
      return new SimpleMatching();
   }

   public Jaccard createJaccard() {
      return new Jaccard();
   }

   public Tanimoto createTanimoto() {
      return new Tanimoto();
   }

   public BinarySimilarity createBinarySimilarity() {
      return new BinarySimilarity();
   }

   public RealSparseArray createRealSparseArray() {
      return new RealSparseArray();
   }

   public Taxonomy createTaxonomy() {
      return new Taxonomy();
   }

   public Timestamp createTimestamp() {
      return new Timestamp();
   }

   public PMML createPMML() {
      return new PMML();
   }

   public Header createHeader() {
      return new Header();
   }

   public Application createApplication() {
      return new Application();
   }

   public Annotation createAnnotation() {
      return new Annotation();
   }

   public MiningBuildTask createMiningBuildTask() {
      return new MiningBuildTask();
   }

   public DataDictionary createDataDictionary() {
      return new DataDictionary();
   }

   public TransformationDictionary createTransformationDictionary() {
      return new TransformationDictionary();
   }

   public IntSparseArray createIntSparseArray() {
      return new IntSparseArray();
   }

   @XmlElementDecl(
      namespace = "http://www.dmg.org/PMML-4_4",
      name = "CountTable"
   )
   public JAXBElement createCountTable(CountTable value) {
      return new JAXBElement(_CountTable_QNAME, CountTable.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.dmg.org/PMML-4_4",
      name = "NormalizedCountTable"
   )
   public JAXBElement createNormalizedCountTable(CountTable value) {
      return new JAXBElement(_NormalizedCountTable_QNAME, CountTable.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.dmg.org/PMML-4_4",
      name = "Indices"
   )
   public JAXBElement createIndices(List value) {
      return new JAXBElement(_Indices_QNAME, List.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.dmg.org/PMML-4_4",
      name = "INT-Entries"
   )
   public JAXBElement createINTEntries(List value) {
      return new JAXBElement(_INTEntries_QNAME, List.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.dmg.org/PMML-4_4",
      name = "REAL-Entries"
   )
   public JAXBElement createREALEntries(List value) {
      return new JAXBElement(_REALEntries_QNAME, List.class, (Class)null, value);
   }
}
