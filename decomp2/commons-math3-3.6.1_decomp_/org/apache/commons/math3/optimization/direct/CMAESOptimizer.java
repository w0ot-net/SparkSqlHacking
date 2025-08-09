package org.apache.commons.math3.optimization.direct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/** @deprecated */
@Deprecated
public class CMAESOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer implements MultivariateOptimizer {
   public static final int DEFAULT_CHECKFEASABLECOUNT = 0;
   public static final double DEFAULT_STOPFITNESS = (double)0.0F;
   public static final boolean DEFAULT_ISACTIVECMA = true;
   public static final int DEFAULT_MAXITERATIONS = 30000;
   public static final int DEFAULT_DIAGONALONLY = 0;
   public static final RandomGenerator DEFAULT_RANDOMGENERATOR = new MersenneTwister();
   private int lambda;
   private boolean isActiveCMA;
   private int checkFeasableCount;
   private double[] inputSigma;
   private int dimension;
   private int diagonalOnly;
   private boolean isMinimize;
   private boolean generateStatistics;
   private int maxIterations;
   private double stopFitness;
   private double stopTolUpX;
   private double stopTolX;
   private double stopTolFun;
   private double stopTolHistFun;
   private int mu;
   private double logMu2;
   private RealMatrix weights;
   private double mueff;
   private double sigma;
   private double cc;
   private double cs;
   private double damps;
   private double ccov1;
   private double ccovmu;
   private double chiN;
   private double ccov1Sep;
   private double ccovmuSep;
   private RealMatrix xmean;
   private RealMatrix pc;
   private RealMatrix ps;
   private double normps;
   private RealMatrix B;
   private RealMatrix D;
   private RealMatrix BD;
   private RealMatrix diagD;
   private RealMatrix C;
   private RealMatrix diagC;
   private int iterations;
   private double[] fitnessHistory;
   private int historySize;
   private RandomGenerator random;
   private List statisticsSigmaHistory;
   private List statisticsMeanHistory;
   private List statisticsFitnessHistory;
   private List statisticsDHistory;

   /** @deprecated */
   @Deprecated
   public CMAESOptimizer() {
      this(0);
   }

   /** @deprecated */
   @Deprecated
   public CMAESOptimizer(int lambda) {
      this(lambda, (double[])null, 30000, (double)0.0F, true, 0, 0, DEFAULT_RANDOMGENERATOR, false, (ConvergenceChecker)null);
   }

   /** @deprecated */
   @Deprecated
   public CMAESOptimizer(int lambda, double[] inputSigma) {
      this(lambda, inputSigma, 30000, (double)0.0F, true, 0, 0, DEFAULT_RANDOMGENERATOR, false);
   }

   /** @deprecated */
   @Deprecated
   public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics) {
      this(lambda, inputSigma, maxIterations, stopFitness, isActiveCMA, diagonalOnly, checkFeasableCount, random, generateStatistics, new SimpleValueChecker());
   }

   /** @deprecated */
   @Deprecated
   public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker checker) {
      super(checker);
      this.diagonalOnly = 0;
      this.isMinimize = true;
      this.generateStatistics = false;
      this.statisticsSigmaHistory = new ArrayList();
      this.statisticsMeanHistory = new ArrayList();
      this.statisticsFitnessHistory = new ArrayList();
      this.statisticsDHistory = new ArrayList();
      this.lambda = lambda;
      this.inputSigma = inputSigma == null ? null : (double[])((double[])(([D)inputSigma).clone());
      this.maxIterations = maxIterations;
      this.stopFitness = stopFitness;
      this.isActiveCMA = isActiveCMA;
      this.diagonalOnly = diagonalOnly;
      this.checkFeasableCount = checkFeasableCount;
      this.random = random;
      this.generateStatistics = generateStatistics;
   }

   public CMAESOptimizer(int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker checker) {
      super(checker);
      this.diagonalOnly = 0;
      this.isMinimize = true;
      this.generateStatistics = false;
      this.statisticsSigmaHistory = new ArrayList();
      this.statisticsMeanHistory = new ArrayList();
      this.statisticsFitnessHistory = new ArrayList();
      this.statisticsDHistory = new ArrayList();
      this.maxIterations = maxIterations;
      this.stopFitness = stopFitness;
      this.isActiveCMA = isActiveCMA;
      this.diagonalOnly = diagonalOnly;
      this.checkFeasableCount = checkFeasableCount;
      this.random = random;
      this.generateStatistics = generateStatistics;
   }

   public List getStatisticsSigmaHistory() {
      return this.statisticsSigmaHistory;
   }

   public List getStatisticsMeanHistory() {
      return this.statisticsMeanHistory;
   }

   public List getStatisticsFitnessHistory() {
      return this.statisticsFitnessHistory;
   }

   public List getStatisticsDHistory() {
      return this.statisticsDHistory;
   }

   protected PointValuePair optimizeInternal(int maxEval, MultivariateFunction f, GoalType goalType, OptimizationData... optData) {
      this.parseOptimizationData(optData);
      return super.optimizeInternal(maxEval, f, goalType, optData);
   }

   protected PointValuePair doOptimize() {
      this.checkParameters();
      this.isMinimize = this.getGoalType().equals(GoalType.MINIMIZE);
      FitnessFunction fitfun = new FitnessFunction();
      double[] guess = this.getStartPoint();
      this.dimension = guess.length;
      this.initializeCMA(guess);
      this.iterations = 0;
      double bestValue = fitfun.value(guess);
      push(this.fitnessHistory, bestValue);
      PointValuePair optimum = new PointValuePair(this.getStartPoint(), this.isMinimize ? bestValue : -bestValue);
      PointValuePair lastResult = null;

      for(this.iterations = 1; this.iterations <= this.maxIterations; ++this.iterations) {
         RealMatrix arz = this.randn1(this.dimension, this.lambda);
         RealMatrix arx = zeros(this.dimension, this.lambda);
         double[] fitness = new double[this.lambda];

         for(int k = 0; k < this.lambda; ++k) {
            RealMatrix arxk = null;

            for(int i = 0; i < this.checkFeasableCount + 1; ++i) {
               if (this.diagonalOnly <= 0) {
                  arxk = this.xmean.add(this.BD.multiply(arz.getColumnMatrix(k)).scalarMultiply(this.sigma));
               } else {
                  arxk = this.xmean.add(times(this.diagD, arz.getColumnMatrix(k)).scalarMultiply(this.sigma));
               }

               if (i >= this.checkFeasableCount || fitfun.isFeasible(arxk.getColumn(0))) {
                  break;
               }

               arz.setColumn(k, this.randn(this.dimension));
            }

            copyColumn(arxk, 0, arx, k);

            try {
               fitness[k] = fitfun.value(arx.getColumn(k));
            } catch (TooManyEvaluationsException var27) {
               return optimum;
            }
         }

         int[] arindex = this.sortedIndices(fitness);
         RealMatrix xold = this.xmean;
         RealMatrix bestArx = selectColumns(arx, MathArrays.copyOf(arindex, this.mu));
         this.xmean = bestArx.multiply(this.weights);
         RealMatrix bestArz = selectColumns(arz, MathArrays.copyOf(arindex, this.mu));
         RealMatrix zmean = bestArz.multiply(this.weights);
         boolean hsig = this.updateEvolutionPaths(zmean, xold);
         if (this.diagonalOnly <= 0) {
            this.updateCovariance(hsig, bestArx, arz, arindex, xold);
         } else {
            this.updateCovarianceDiagonalOnly(hsig, bestArz);
         }

         this.sigma *= FastMath.exp(FastMath.min((double)1.0F, (this.normps / this.chiN - (double)1.0F) * this.cs / this.damps));
         double bestFitness = fitness[arindex[0]];
         double worstFitness = fitness[arindex[arindex.length - 1]];
         if (bestValue > bestFitness) {
            bestValue = bestFitness;
            lastResult = optimum;
            optimum = new PointValuePair(fitfun.repair(bestArx.getColumn(0)), this.isMinimize ? bestFitness : -bestFitness);
            if (this.getConvergenceChecker() != null && lastResult != null && this.getConvergenceChecker().converged(this.iterations, optimum, lastResult)) {
               break;
            }
         }

         if (this.stopFitness != (double)0.0F && bestFitness < (this.isMinimize ? this.stopFitness : -this.stopFitness)) {
            break;
         }

         double[] sqrtDiagC = sqrt(this.diagC).getColumn(0);
         double[] pcCol = this.pc.getColumn(0);

         for(int i = 0; i < this.dimension && !(this.sigma * FastMath.max(FastMath.abs(pcCol[i]), sqrtDiagC[i]) > this.stopTolX); ++i) {
            if (i >= this.dimension - 1) {
               return optimum;
            }
         }

         for(int i = 0; i < this.dimension; ++i) {
            if (this.sigma * sqrtDiagC[i] > this.stopTolUpX) {
               return optimum;
            }
         }

         double historyBest = min(this.fitnessHistory);
         double historyWorst = max(this.fitnessHistory);
         if (this.iterations > 2 && FastMath.max(historyWorst, worstFitness) - FastMath.min(historyBest, bestFitness) < this.stopTolFun || this.iterations > this.fitnessHistory.length && historyWorst - historyBest < this.stopTolHistFun || max(this.diagD) / min(this.diagD) > (double)1.0E7F) {
            break;
         }

         if (this.getConvergenceChecker() != null) {
            PointValuePair current = new PointValuePair(bestArx.getColumn(0), this.isMinimize ? bestFitness : -bestFitness);
            if (lastResult != null && this.getConvergenceChecker().converged(this.iterations, current, lastResult)) {
               break;
            }

            lastResult = current;
         }

         if (bestValue == fitness[arindex[(int)(0.1 + (double)this.lambda / (double)4.0F)]]) {
            this.sigma *= FastMath.exp(0.2 + this.cs / this.damps);
         }

         if (this.iterations > 2 && FastMath.max(historyWorst, bestFitness) - FastMath.min(historyBest, bestFitness) == (double)0.0F) {
            this.sigma *= FastMath.exp(0.2 + this.cs / this.damps);
         }

         push(this.fitnessHistory, bestFitness);
         fitfun.setValueRange(worstFitness - bestFitness);
         if (this.generateStatistics) {
            this.statisticsSigmaHistory.add(this.sigma);
            this.statisticsFitnessHistory.add(bestFitness);
            this.statisticsMeanHistory.add(this.xmean.transpose());
            this.statisticsDHistory.add(this.diagD.transpose().scalarMultiply((double)100000.0F));
         }
      }

      return optimum;
   }

   private void parseOptimizationData(OptimizationData... optData) {
      for(OptimizationData data : optData) {
         if (data instanceof Sigma) {
            this.inputSigma = ((Sigma)data).getSigma();
         } else if (data instanceof PopulationSize) {
            this.lambda = ((PopulationSize)data).getPopulationSize();
         }
      }

   }

   private void checkParameters() {
      double[] init = this.getStartPoint();
      double[] lB = this.getLowerBound();
      double[] uB = this.getUpperBound();
      if (this.inputSigma != null) {
         if (this.inputSigma.length != init.length) {
            throw new DimensionMismatchException(this.inputSigma.length, init.length);
         }

         for(int i = 0; i < init.length; ++i) {
            if (this.inputSigma[i] < (double)0.0F) {
               throw new NotPositiveException(this.inputSigma[i]);
            }

            if (this.inputSigma[i] > uB[i] - lB[i]) {
               throw new OutOfRangeException(this.inputSigma[i], 0, uB[i] - lB[i]);
            }
         }
      }

   }

   private void initializeCMA(double[] guess) {
      if (this.lambda <= 0) {
         this.lambda = 4 + (int)((double)3.0F * FastMath.log((double)this.dimension));
      }

      double[][] sigmaArray = new double[guess.length][1];

      for(int i = 0; i < guess.length; ++i) {
         sigmaArray[i][0] = this.inputSigma == null ? 0.3 : this.inputSigma[i];
      }

      RealMatrix insigma = new Array2DRowRealMatrix(sigmaArray, false);
      this.sigma = max(insigma);
      this.stopTolUpX = (double)1000.0F * max(insigma);
      this.stopTolX = 1.0E-11 * max(insigma);
      this.stopTolFun = 1.0E-12;
      this.stopTolHistFun = 1.0E-13;
      this.mu = this.lambda / 2;
      this.logMu2 = FastMath.log((double)this.mu + (double)0.5F);
      this.weights = log(sequence((double)1.0F, (double)this.mu, (double)1.0F)).scalarMultiply((double)-1.0F).scalarAdd(this.logMu2);
      double sumw = (double)0.0F;
      double sumwq = (double)0.0F;

      for(int i = 0; i < this.mu; ++i) {
         double w = this.weights.getEntry(i, 0);
         sumw += w;
         sumwq += w * w;
      }

      this.weights = this.weights.scalarMultiply((double)1.0F / sumw);
      this.mueff = sumw * sumw / sumwq;
      this.cc = ((double)4.0F + this.mueff / (double)this.dimension) / ((double)(this.dimension + 4) + (double)2.0F * this.mueff / (double)this.dimension);
      this.cs = (this.mueff + (double)2.0F) / ((double)this.dimension + this.mueff + (double)3.0F);
      this.damps = ((double)1.0F + (double)2.0F * FastMath.max((double)0.0F, FastMath.sqrt((this.mueff - (double)1.0F) / (double)(this.dimension + 1)) - (double)1.0F)) * FastMath.max(0.3, (double)1.0F - (double)this.dimension / (1.0E-6 + (double)this.maxIterations)) + this.cs;
      this.ccov1 = (double)2.0F / (((double)this.dimension + 1.3) * ((double)this.dimension + 1.3) + this.mueff);
      this.ccovmu = FastMath.min((double)1.0F - this.ccov1, (double)2.0F * (this.mueff - (double)2.0F + (double)1.0F / this.mueff) / ((double)((this.dimension + 2) * (this.dimension + 2)) + this.mueff));
      this.ccov1Sep = FastMath.min((double)1.0F, this.ccov1 * ((double)this.dimension + (double)1.5F) / (double)3.0F);
      this.ccovmuSep = FastMath.min((double)1.0F - this.ccov1, this.ccovmu * ((double)this.dimension + (double)1.5F) / (double)3.0F);
      this.chiN = FastMath.sqrt((double)this.dimension) * ((double)1.0F - (double)1.0F / ((double)4.0F * (double)this.dimension) + (double)1.0F / ((double)21.0F * (double)this.dimension * (double)this.dimension));
      this.xmean = MatrixUtils.createColumnRealMatrix(guess);
      this.diagD = insigma.scalarMultiply((double)1.0F / this.sigma);
      this.diagC = square(this.diagD);
      this.pc = zeros(this.dimension, 1);
      this.ps = zeros(this.dimension, 1);
      this.normps = this.ps.getFrobeniusNorm();
      this.B = eye(this.dimension, this.dimension);
      this.D = ones(this.dimension, 1);
      this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
      this.C = this.B.multiply(diag(square(this.D)).multiply(this.B.transpose()));
      this.historySize = 10 + (int)((double)(30 * this.dimension) / (double)this.lambda);
      this.fitnessHistory = new double[this.historySize];

      for(int i = 0; i < this.historySize; ++i) {
         this.fitnessHistory[i] = Double.MAX_VALUE;
      }

   }

   private boolean updateEvolutionPaths(RealMatrix zmean, RealMatrix xold) {
      this.ps = this.ps.scalarMultiply((double)1.0F - this.cs).add(this.B.multiply(zmean).scalarMultiply(FastMath.sqrt(this.cs * ((double)2.0F - this.cs) * this.mueff)));
      this.normps = this.ps.getFrobeniusNorm();
      boolean hsig = this.normps / FastMath.sqrt((double)1.0F - FastMath.pow((double)1.0F - this.cs, 2 * this.iterations)) / this.chiN < 1.4 + (double)2.0F / ((double)this.dimension + (double)1.0F);
      this.pc = this.pc.scalarMultiply((double)1.0F - this.cc);
      if (hsig) {
         this.pc = this.pc.add(this.xmean.subtract(xold).scalarMultiply(FastMath.sqrt(this.cc * ((double)2.0F - this.cc) * this.mueff) / this.sigma));
      }

      return hsig;
   }

   private void updateCovarianceDiagonalOnly(boolean hsig, RealMatrix bestArz) {
      double oldFac = hsig ? (double)0.0F : this.ccov1Sep * this.cc * ((double)2.0F - this.cc);
      oldFac += (double)1.0F - this.ccov1Sep - this.ccovmuSep;
      this.diagC = this.diagC.scalarMultiply(oldFac).add(square(this.pc).scalarMultiply(this.ccov1Sep)).add(times(this.diagC, square(bestArz).multiply(this.weights)).scalarMultiply(this.ccovmuSep));
      this.diagD = sqrt(this.diagC);
      if (this.diagonalOnly > 1 && this.iterations > this.diagonalOnly) {
         this.diagonalOnly = 0;
         this.B = eye(this.dimension, this.dimension);
         this.BD = diag(this.diagD);
         this.C = diag(this.diagC);
      }

   }

   private void updateCovariance(boolean hsig, RealMatrix bestArx, RealMatrix arz, int[] arindex, RealMatrix xold) {
      double negccov = (double)0.0F;
      if (this.ccov1 + this.ccovmu > (double)0.0F) {
         RealMatrix arpos = bestArx.subtract(repmat(xold, 1, this.mu)).scalarMultiply((double)1.0F / this.sigma);
         RealMatrix roneu = this.pc.multiply(this.pc.transpose()).scalarMultiply(this.ccov1);
         double oldFac = hsig ? (double)0.0F : this.ccov1 * this.cc * ((double)2.0F - this.cc);
         oldFac += (double)1.0F - this.ccov1 - this.ccovmu;
         if (this.isActiveCMA) {
            negccov = ((double)1.0F - this.ccovmu) * (double)0.25F * this.mueff / (FastMath.pow((double)(this.dimension + 2), (double)1.5F) + (double)2.0F * this.mueff);
            double negminresidualvariance = 0.66;
            double negalphaold = (double)0.5F;
            int[] arReverseIndex = reverse(arindex);
            RealMatrix arzneg = selectColumns(arz, MathArrays.copyOf(arReverseIndex, this.mu));
            RealMatrix arnorms = sqrt(sumRows(square(arzneg)));
            int[] idxnorms = this.sortedIndices(arnorms.getRow(0));
            RealMatrix arnormsSorted = selectColumns(arnorms, idxnorms);
            int[] idxReverse = reverse(idxnorms);
            RealMatrix arnormsReverse = selectColumns(arnorms, idxReverse);
            arnorms = divide(arnormsReverse, arnormsSorted);
            int[] idxInv = inverse(idxnorms);
            RealMatrix arnormsInv = selectColumns(arnorms, idxInv);
            double negcovMax = 0.33999999999999997 / square(arnormsInv).multiply(this.weights).getEntry(0, 0);
            if (negccov > negcovMax) {
               negccov = negcovMax;
            }

            arzneg = times(arzneg, repmat(arnormsInv, this.dimension, 1));
            RealMatrix artmp = this.BD.multiply(arzneg);
            RealMatrix Cneg = artmp.multiply(diag(this.weights)).multiply(artmp.transpose());
            oldFac += (double)0.5F * negccov;
            this.C = this.C.scalarMultiply(oldFac).add(roneu).add(arpos.scalarMultiply(this.ccovmu + (double)0.5F * negccov).multiply(times(repmat(this.weights, 1, this.dimension), arpos.transpose()))).subtract(Cneg.scalarMultiply(negccov));
         } else {
            this.C = this.C.scalarMultiply(oldFac).add(roneu).add(arpos.scalarMultiply(this.ccovmu).multiply(times(repmat(this.weights, 1, this.dimension), arpos.transpose())));
         }
      }

      this.updateBD(negccov);
   }

   private void updateBD(double negccov) {
      if (this.ccov1 + this.ccovmu + negccov > (double)0.0F && (double)this.iterations % (double)1.0F / (this.ccov1 + this.ccovmu + negccov) / (double)this.dimension / (double)10.0F < (double)1.0F) {
         this.C = triu(this.C, 0).add(triu(this.C, 1).transpose());
         EigenDecomposition eig = new EigenDecomposition(this.C);
         this.B = eig.getV();
         this.D = eig.getD();
         this.diagD = diag(this.D);
         if (min(this.diagD) <= (double)0.0F) {
            for(int i = 0; i < this.dimension; ++i) {
               if (this.diagD.getEntry(i, 0) < (double)0.0F) {
                  this.diagD.setEntry(i, 0, (double)0.0F);
               }
            }

            double tfac = max(this.diagD) / 1.0E14;
            this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(tfac));
            this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(tfac));
         }

         if (max(this.diagD) > 1.0E14 * min(this.diagD)) {
            double tfac = max(this.diagD) / 1.0E14 - min(this.diagD);
            this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(tfac));
            this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(tfac));
         }

         this.diagC = diag(this.C);
         this.diagD = sqrt(this.diagD);
         this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
      }

   }

   private static void push(double[] vals, double val) {
      for(int i = vals.length - 1; i > 0; --i) {
         vals[i] = vals[i - 1];
      }

      vals[0] = val;
   }

   private int[] sortedIndices(double[] doubles) {
      DoubleIndex[] dis = new DoubleIndex[doubles.length];

      for(int i = 0; i < doubles.length; ++i) {
         dis[i] = new DoubleIndex(doubles[i], i);
      }

      Arrays.sort(dis);
      int[] indices = new int[doubles.length];

      for(int i = 0; i < doubles.length; ++i) {
         indices[i] = dis[i].index;
      }

      return indices;
   }

   private static RealMatrix log(RealMatrix m) {
      double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            d[r][c] = FastMath.log(m.getEntry(r, c));
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix sqrt(RealMatrix m) {
      double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            d[r][c] = FastMath.sqrt(m.getEntry(r, c));
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix square(RealMatrix m) {
      double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            double e = m.getEntry(r, c);
            d[r][c] = e * e;
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix times(RealMatrix m, RealMatrix n) {
      double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            d[r][c] = m.getEntry(r, c) * n.getEntry(r, c);
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix divide(RealMatrix m, RealMatrix n) {
      double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            d[r][c] = m.getEntry(r, c) / n.getEntry(r, c);
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix selectColumns(RealMatrix m, int[] cols) {
      double[][] d = new double[m.getRowDimension()][cols.length];

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < cols.length; ++c) {
            d[r][c] = m.getEntry(r, cols[c]);
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix triu(RealMatrix m, int k) {
      double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            d[r][c] = r <= c - k ? m.getEntry(r, c) : (double)0.0F;
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix sumRows(RealMatrix m) {
      double[][] d = new double[1][m.getColumnDimension()];

      for(int c = 0; c < m.getColumnDimension(); ++c) {
         double sum = (double)0.0F;

         for(int r = 0; r < m.getRowDimension(); ++r) {
            sum += m.getEntry(r, c);
         }

         d[0][c] = sum;
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix diag(RealMatrix m) {
      if (m.getColumnDimension() == 1) {
         double[][] d = new double[m.getRowDimension()][m.getRowDimension()];

         for(int i = 0; i < m.getRowDimension(); ++i) {
            d[i][i] = m.getEntry(i, 0);
         }

         return new Array2DRowRealMatrix(d, false);
      } else {
         double[][] d = new double[m.getRowDimension()][1];

         for(int i = 0; i < m.getColumnDimension(); ++i) {
            d[i][0] = m.getEntry(i, i);
         }

         return new Array2DRowRealMatrix(d, false);
      }
   }

   private static void copyColumn(RealMatrix m1, int col1, RealMatrix m2, int col2) {
      for(int i = 0; i < m1.getRowDimension(); ++i) {
         m2.setEntry(i, col2, m1.getEntry(i, col1));
      }

   }

   private static RealMatrix ones(int n, int m) {
      double[][] d = new double[n][m];

      for(int r = 0; r < n; ++r) {
         Arrays.fill(d[r], (double)1.0F);
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix eye(int n, int m) {
      double[][] d = new double[n][m];

      for(int r = 0; r < n; ++r) {
         if (r < m) {
            d[r][r] = (double)1.0F;
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix zeros(int n, int m) {
      return new Array2DRowRealMatrix(n, m);
   }

   private static RealMatrix repmat(RealMatrix mat, int n, int m) {
      int rd = mat.getRowDimension();
      int cd = mat.getColumnDimension();
      double[][] d = new double[n * rd][m * cd];

      for(int r = 0; r < n * rd; ++r) {
         for(int c = 0; c < m * cd; ++c) {
            d[r][c] = mat.getEntry(r % rd, c % cd);
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static RealMatrix sequence(double start, double end, double step) {
      int size = (int)((end - start) / step + (double)1.0F);
      double[][] d = new double[size][1];
      double value = start;

      for(int r = 0; r < size; ++r) {
         d[r][0] = value;
         value += step;
      }

      return new Array2DRowRealMatrix(d, false);
   }

   private static double max(RealMatrix m) {
      double max = -Double.MAX_VALUE;

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            double e = m.getEntry(r, c);
            if (max < e) {
               max = e;
            }
         }
      }

      return max;
   }

   private static double min(RealMatrix m) {
      double min = Double.MAX_VALUE;

      for(int r = 0; r < m.getRowDimension(); ++r) {
         for(int c = 0; c < m.getColumnDimension(); ++c) {
            double e = m.getEntry(r, c);
            if (min > e) {
               min = e;
            }
         }
      }

      return min;
   }

   private static double max(double[] m) {
      double max = -Double.MAX_VALUE;

      for(int r = 0; r < m.length; ++r) {
         if (max < m[r]) {
            max = m[r];
         }
      }

      return max;
   }

   private static double min(double[] m) {
      double min = Double.MAX_VALUE;

      for(int r = 0; r < m.length; ++r) {
         if (min > m[r]) {
            min = m[r];
         }
      }

      return min;
   }

   private static int[] inverse(int[] indices) {
      int[] inverse = new int[indices.length];

      for(int i = 0; i < indices.length; inverse[indices[i]] = i++) {
      }

      return inverse;
   }

   private static int[] reverse(int[] indices) {
      int[] reverse = new int[indices.length];

      for(int i = 0; i < indices.length; ++i) {
         reverse[i] = indices[indices.length - i - 1];
      }

      return reverse;
   }

   private double[] randn(int size) {
      double[] randn = new double[size];

      for(int i = 0; i < size; ++i) {
         randn[i] = this.random.nextGaussian();
      }

      return randn;
   }

   private RealMatrix randn1(int size, int popSize) {
      double[][] d = new double[size][popSize];

      for(int r = 0; r < size; ++r) {
         for(int c = 0; c < popSize; ++c) {
            d[r][c] = this.random.nextGaussian();
         }
      }

      return new Array2DRowRealMatrix(d, false);
   }

   public static class Sigma implements OptimizationData {
      private final double[] sigma;

      public Sigma(double[] s) throws NotPositiveException {
         for(int i = 0; i < s.length; ++i) {
            if (s[i] < (double)0.0F) {
               throw new NotPositiveException(s[i]);
            }
         }

         this.sigma = (double[])(([D)s).clone();
      }

      public double[] getSigma() {
         return (double[])this.sigma.clone();
      }
   }

   public static class PopulationSize implements OptimizationData {
      private final int lambda;

      public PopulationSize(int size) throws NotStrictlyPositiveException {
         if (size <= 0) {
            throw new NotStrictlyPositiveException(size);
         } else {
            this.lambda = size;
         }
      }

      public int getPopulationSize() {
         return this.lambda;
      }
   }

   private static class DoubleIndex implements Comparable {
      private final double value;
      private final int index;

      DoubleIndex(double value, int index) {
         this.value = value;
         this.index = index;
      }

      public int compareTo(DoubleIndex o) {
         return Double.compare(this.value, o.value);
      }

      public boolean equals(Object other) {
         if (this == other) {
            return true;
         } else if (other instanceof DoubleIndex) {
            return Double.compare(this.value, ((DoubleIndex)other).value) == 0;
         } else {
            return false;
         }
      }

      public int hashCode() {
         long bits = Double.doubleToLongBits(this.value);
         return (int)((1438542L ^ bits >>> 32 ^ bits) & -1L);
      }
   }

   private class FitnessFunction {
      private double valueRange = (double)1.0F;
      private final boolean isRepairMode = true;

      FitnessFunction() {
      }

      public double value(double[] point) {
         double value;
         if (this.isRepairMode) {
            double[] repaired = this.repair(point);
            value = CMAESOptimizer.this.computeObjectiveValue(repaired) + this.penalty(point, repaired);
         } else {
            value = CMAESOptimizer.this.computeObjectiveValue(point);
         }

         return CMAESOptimizer.this.isMinimize ? value : -value;
      }

      public boolean isFeasible(double[] x) {
         double[] lB = CMAESOptimizer.this.getLowerBound();
         double[] uB = CMAESOptimizer.this.getUpperBound();

         for(int i = 0; i < x.length; ++i) {
            if (x[i] < lB[i]) {
               return false;
            }

            if (x[i] > uB[i]) {
               return false;
            }
         }

         return true;
      }

      public void setValueRange(double valueRange) {
         this.valueRange = valueRange;
      }

      private double[] repair(double[] x) {
         double[] lB = CMAESOptimizer.this.getLowerBound();
         double[] uB = CMAESOptimizer.this.getUpperBound();
         double[] repaired = new double[x.length];

         for(int i = 0; i < x.length; ++i) {
            if (x[i] < lB[i]) {
               repaired[i] = lB[i];
            } else if (x[i] > uB[i]) {
               repaired[i] = uB[i];
            } else {
               repaired[i] = x[i];
            }
         }

         return repaired;
      }

      private double penalty(double[] x, double[] repaired) {
         double penalty = (double)0.0F;

         for(int i = 0; i < x.length; ++i) {
            double diff = FastMath.abs(x[i] - repaired[i]);
            penalty += diff * this.valueRange;
         }

         return CMAESOptimizer.this.isMinimize ? penalty : -penalty;
      }
   }
}
