package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class ListPopulation implements Population {
   private List chromosomes;
   private int populationLimit;

   public ListPopulation(int populationLimit) throws NotPositiveException {
      this(Collections.emptyList(), populationLimit);
   }

   public ListPopulation(List chromosomes, int populationLimit) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException {
      if (chromosomes == null) {
         throw new NullArgumentException();
      } else if (populationLimit <= 0) {
         throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, populationLimit);
      } else if (chromosomes.size() > populationLimit) {
         throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, chromosomes.size(), populationLimit, false);
      } else {
         this.populationLimit = populationLimit;
         this.chromosomes = new ArrayList(populationLimit);
         this.chromosomes.addAll(chromosomes);
      }
   }

   /** @deprecated */
   @Deprecated
   public void setChromosomes(List chromosomes) throws NullArgumentException, NumberIsTooLargeException {
      if (chromosomes == null) {
         throw new NullArgumentException();
      } else if (chromosomes.size() > this.populationLimit) {
         throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, chromosomes.size(), this.populationLimit, false);
      } else {
         this.chromosomes.clear();
         this.chromosomes.addAll(chromosomes);
      }
   }

   public void addChromosomes(Collection chromosomeColl) throws NumberIsTooLargeException {
      if (this.chromosomes.size() + chromosomeColl.size() > this.populationLimit) {
         throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, this.chromosomes.size(), this.populationLimit, false);
      } else {
         this.chromosomes.addAll(chromosomeColl);
      }
   }

   public List getChromosomes() {
      return Collections.unmodifiableList(this.chromosomes);
   }

   protected List getChromosomeList() {
      return this.chromosomes;
   }

   public void addChromosome(Chromosome chromosome) throws NumberIsTooLargeException {
      if (this.chromosomes.size() >= this.populationLimit) {
         throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, this.chromosomes.size(), this.populationLimit, false);
      } else {
         this.chromosomes.add(chromosome);
      }
   }

   public Chromosome getFittestChromosome() {
      Chromosome bestChromosome = (Chromosome)this.chromosomes.get(0);

      for(Chromosome chromosome : this.chromosomes) {
         if (chromosome.compareTo(bestChromosome) > 0) {
            bestChromosome = chromosome;
         }
      }

      return bestChromosome;
   }

   public int getPopulationLimit() {
      return this.populationLimit;
   }

   public void setPopulationLimit(int populationLimit) throws NotPositiveException, NumberIsTooSmallException {
      if (populationLimit <= 0) {
         throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, populationLimit);
      } else if (populationLimit < this.chromosomes.size()) {
         throw new NumberIsTooSmallException(populationLimit, this.chromosomes.size(), true);
      } else {
         this.populationLimit = populationLimit;
      }
   }

   public int getPopulationSize() {
      return this.chromosomes.size();
   }

   public String toString() {
      return this.chromosomes.toString();
   }

   public Iterator iterator() {
      return this.getChromosomes().iterator();
   }
}
