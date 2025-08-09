package org.apache.commons.math3.genetics;

import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class ElitisticListPopulation extends ListPopulation {
   private double elitismRate = 0.9;

   public ElitisticListPopulation(List chromosomes, int populationLimit, double elitismRate) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException, OutOfRangeException {
      super(chromosomes, populationLimit);
      this.setElitismRate(elitismRate);
   }

   public ElitisticListPopulation(int populationLimit, double elitismRate) throws NotPositiveException, OutOfRangeException {
      super(populationLimit);
      this.setElitismRate(elitismRate);
   }

   public Population nextGeneration() {
      ElitisticListPopulation nextGeneration = new ElitisticListPopulation(this.getPopulationLimit(), this.getElitismRate());
      List<Chromosome> oldChromosomes = this.getChromosomeList();
      Collections.sort(oldChromosomes);
      int boundIndex = (int)FastMath.ceil(((double)1.0F - this.getElitismRate()) * (double)oldChromosomes.size());

      for(int i = boundIndex; i < oldChromosomes.size(); ++i) {
         nextGeneration.addChromosome((Chromosome)oldChromosomes.get(i));
      }

      return nextGeneration;
   }

   public void setElitismRate(double elitismRate) throws OutOfRangeException {
      if (!(elitismRate < (double)0.0F) && !(elitismRate > (double)1.0F)) {
         this.elitismRate = elitismRate;
      } else {
         throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, elitismRate, 0, 1);
      }
   }

   public double getElitismRate() {
      return this.elitismRate;
   }
}
