package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class BinaryChromosome extends AbstractListChromosome {
   public BinaryChromosome(List representation) throws InvalidRepresentationException {
      super(representation);
   }

   public BinaryChromosome(Integer[] representation) throws InvalidRepresentationException {
      super((Object[])representation);
   }

   protected void checkValidity(List chromosomeRepresentation) throws InvalidRepresentationException {
      for(int i : chromosomeRepresentation) {
         if (i < 0 || i > 1) {
            throw new InvalidRepresentationException(LocalizedFormats.INVALID_BINARY_DIGIT, new Object[]{i});
         }
      }

   }

   public static List randomBinaryRepresentation(int length) {
      List<Integer> rList = new ArrayList(length);

      for(int j = 0; j < length; ++j) {
         rList.add(GeneticAlgorithm.getRandomGenerator().nextInt(2));
      }

      return rList;
   }

   protected boolean isSame(Chromosome another) {
      if (!(another instanceof BinaryChromosome)) {
         return false;
      } else {
         BinaryChromosome anotherBc = (BinaryChromosome)another;
         if (this.getLength() != anotherBc.getLength()) {
            return false;
         } else {
            for(int i = 0; i < this.getRepresentation().size(); ++i) {
               if (!((Integer)this.getRepresentation().get(i)).equals(anotherBc.getRepresentation().get(i))) {
                  return false;
               }
            }

            return true;
         }
      }
   }
}
