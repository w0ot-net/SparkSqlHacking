package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractListChromosome extends Chromosome {
   private final List representation;

   public AbstractListChromosome(List representation) throws InvalidRepresentationException {
      this(representation, true);
   }

   public AbstractListChromosome(Object[] representation) throws InvalidRepresentationException {
      this(Arrays.asList(representation));
   }

   public AbstractListChromosome(List representation, boolean copyList) {
      this.checkValidity(representation);
      this.representation = Collections.unmodifiableList((List)(copyList ? new ArrayList(representation) : representation));
   }

   protected abstract void checkValidity(List var1) throws InvalidRepresentationException;

   protected List getRepresentation() {
      return this.representation;
   }

   public int getLength() {
      return this.getRepresentation().size();
   }

   public abstract AbstractListChromosome newFixedLengthChromosome(List var1);

   public String toString() {
      return String.format("(f=%s %s)", this.getFitness(), this.getRepresentation());
   }
}
