package org.sparkproject.dmg.pmml.time_series;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public TimeCycle createTimeCycle() {
      return new TimeCycle();
   }

   public TimeException createTimeException() {
      return new TimeException();
   }

   public TimeAnchor createTimeAnchor() {
      return new TimeAnchor();
   }

   public TimeSeries createTimeSeries() {
      return new TimeSeries();
   }

   public DynamicRegressor createDynamicRegressor() {
      return new DynamicRegressor();
   }

   public MaximumLikelihoodStat createMaximumLikelihoodStat() {
      return new MaximumLikelihoodStat();
   }

   public OutlierEffect createOutlierEffect() {
      return new OutlierEffect();
   }

   public ARIMA createARIMA() {
      return new ARIMA();
   }

   public TrendExpoSmooth createTrendExpoSmooth() {
      return new TrendExpoSmooth();
   }

   public SeasonalityExpoSmooth createSeasonalityExpoSmooth() {
      return new SeasonalityExpoSmooth();
   }

   public ExponentialSmoothing createExponentialSmoothing() {
      return new ExponentialSmoothing();
   }

   public InterceptVector createInterceptVector() {
      return new InterceptVector();
   }

   public TimeSeriesModel createTimeSeriesModel() {
      return new TimeSeriesModel();
   }

   public TimeValue createTimeValue() {
      return new TimeValue();
   }

   public SpectralAnalysis createSpectralAnalysis() {
      return new SpectralAnalysis();
   }

   public NonseasonalComponent createNonseasonalComponent() {
      return new NonseasonalComponent();
   }

   public AR createAR() {
      return new AR();
   }

   public MA createMA() {
      return new MA();
   }

   public MACoefficients createMACoefficients() {
      return new MACoefficients();
   }

   public Residuals createResiduals() {
      return new Residuals();
   }

   public SeasonalComponent createSeasonalComponent() {
      return new SeasonalComponent();
   }

   public Numerator createNumerator() {
      return new Numerator();
   }

   public NonseasonalFactor createNonseasonalFactor() {
      return new NonseasonalFactor();
   }

   public SeasonalFactor createSeasonalFactor() {
      return new SeasonalFactor();
   }

   public Denominator createDenominator() {
      return new Denominator();
   }

   public RegressorValues createRegressorValues() {
      return new RegressorValues();
   }

   public TrendCoefficients createTrendCoefficients() {
      return new TrendCoefficients();
   }

   public TransferFunctionValues createTransferFunctionValues() {
      return new TransferFunctionValues();
   }

   public KalmanState createKalmanState() {
      return new KalmanState();
   }

   public FinalOmega createFinalOmega() {
      return new FinalOmega();
   }

   public FinalStateVector createFinalStateVector() {
      return new FinalStateVector();
   }

   public HVector createHVector() {
      return new HVector();
   }

   public ThetaRecursionState createThetaRecursionState() {
      return new ThetaRecursionState();
   }

   public FinalNoise createFinalNoise() {
      return new FinalNoise();
   }

   public FinalPredictedNoise createFinalPredictedNoise() {
      return new FinalPredictedNoise();
   }

   public FinalTheta createFinalTheta() {
      return new FinalTheta();
   }

   public Theta createTheta() {
      return new Theta();
   }

   public FinalNu createFinalNu() {
      return new FinalNu();
   }

   public Level createLevel() {
      return new Level();
   }

   public SeasonalTrendDecomposition createSeasonalTrendDecomposition() {
      return new SeasonalTrendDecomposition();
   }

   public StateSpaceModel createStateSpaceModel() {
      return new StateSpaceModel();
   }

   public StateVector createStateVector() {
      return new StateVector();
   }

   public TransitionMatrix createTransitionMatrix() {
      return new TransitionMatrix();
   }

   public MeasurementMatrix createMeasurementMatrix() {
      return new MeasurementMatrix();
   }

   public PredictedStateCovarianceMatrix createPredictedStateCovarianceMatrix() {
      return new PredictedStateCovarianceMatrix();
   }

   public SelectedStateCovarianceMatrix createSelectedStateCovarianceMatrix() {
      return new SelectedStateCovarianceMatrix();
   }

   public ObservationVarianceMatrix createObservationVarianceMatrix() {
      return new ObservationVarianceMatrix();
   }

   public PsiVector createPsiVector() {
      return new PsiVector();
   }

   public GARCH createGARCH() {
      return new GARCH();
   }

   public ARMAPart createARMAPart() {
      return new ARMAPart();
   }

   public GARCHPart createGARCHPart() {
      return new GARCHPart();
   }

   public ResidualSquareCoefficients createResidualSquareCoefficients() {
      return new ResidualSquareCoefficients();
   }

   public VarianceCoefficients createVarianceCoefficients() {
      return new VarianceCoefficients();
   }

   public PastVariances createPastVariances() {
      return new PastVariances();
   }
}
