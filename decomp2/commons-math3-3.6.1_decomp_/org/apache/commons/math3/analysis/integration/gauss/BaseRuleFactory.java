package org.apache.commons.math3.analysis.integration.gauss;

import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Pair;

public abstract class BaseRuleFactory {
   private final Map pointsAndWeights = new TreeMap();
   private final Map pointsAndWeightsDouble = new TreeMap();

   public Pair getRule(int numberOfPoints) throws NotStrictlyPositiveException, DimensionMismatchException {
      if (numberOfPoints <= 0) {
         throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, numberOfPoints);
      } else {
         Pair<double[], double[]> cached = (Pair)this.pointsAndWeightsDouble.get(numberOfPoints);
         if (cached == null) {
            Pair<T[], T[]> rule = this.getRuleInternal(numberOfPoints);
            cached = convertToDouble(rule);
            this.pointsAndWeightsDouble.put(numberOfPoints, cached);
         }

         return new Pair(((double[])cached.getFirst()).clone(), ((double[])cached.getSecond()).clone());
      }
   }

   protected synchronized Pair getRuleInternal(int numberOfPoints) throws DimensionMismatchException {
      Pair<T[], T[]> rule = (Pair)this.pointsAndWeights.get(numberOfPoints);
      if (rule == null) {
         this.addRule(this.computeRule(numberOfPoints));
         return this.getRuleInternal(numberOfPoints);
      } else {
         return rule;
      }
   }

   protected void addRule(Pair rule) throws DimensionMismatchException {
      if (((Number[])rule.getFirst()).length != ((Number[])rule.getSecond()).length) {
         throw new DimensionMismatchException(((Number[])rule.getFirst()).length, ((Number[])rule.getSecond()).length);
      } else {
         this.pointsAndWeights.put(((Number[])rule.getFirst()).length, rule);
      }
   }

   protected abstract Pair computeRule(int var1) throws DimensionMismatchException;

   private static Pair convertToDouble(Pair rule) {
      T[] pT = (T[])((Number[])rule.getFirst());
      T[] wT = (T[])((Number[])rule.getSecond());
      int len = pT.length;
      double[] pD = new double[len];
      double[] wD = new double[len];

      for(int i = 0; i < len; ++i) {
         pD[i] = pT[i].doubleValue();
         wD[i] = wT[i].doubleValue();
      }

      return new Pair(pD, wD);
   }
}
