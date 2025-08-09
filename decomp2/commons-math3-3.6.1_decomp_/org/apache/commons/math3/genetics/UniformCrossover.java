package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;

public class UniformCrossover implements CrossoverPolicy {
   private final double ratio;

   public UniformCrossover(double ratio) throws OutOfRangeException {
      if (!(ratio < (double)0.0F) && !(ratio > (double)1.0F)) {
         this.ratio = ratio;
      } else {
         throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE, ratio, (double)0.0F, (double)1.0F);
      }
   }

   public double getRatio() {
      return this.ratio;
   }

   public ChromosomePair crossover(Chromosome first, Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
      if (first instanceof AbstractListChromosome && second instanceof AbstractListChromosome) {
         return this.mate((AbstractListChromosome)first, (AbstractListChromosome)second);
      } else {
         throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
      }
   }

   private ChromosomePair mate(AbstractListChromosome first, AbstractListChromosome second) throws DimensionMismatchException {
      int length = first.getLength();
      if (length != second.getLength()) {
         throw new DimensionMismatchException(second.getLength(), length);
      } else {
         List<T> parent1Rep = first.getRepresentation();
         List<T> parent2Rep = second.getRepresentation();
         List<T> child1Rep = new ArrayList(length);
         List<T> child2Rep = new ArrayList(length);
         RandomGenerator random = GeneticAlgorithm.getRandomGenerator();

         for(int index = 0; index < length; ++index) {
            if (random.nextDouble() < this.ratio) {
               child1Rep.add(parent2Rep.get(index));
               child2Rep.add(parent1Rep.get(index));
            } else {
               child1Rep.add(parent1Rep.get(index));
               child2Rep.add(parent2Rep.get(index));
            }
         }

         return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
      }
   }
}
