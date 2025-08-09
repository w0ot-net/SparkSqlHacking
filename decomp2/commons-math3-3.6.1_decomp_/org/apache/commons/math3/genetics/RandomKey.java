package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class RandomKey extends AbstractListChromosome implements PermutationChromosome {
   private final List sortedRepresentation;
   private final List baseSeqPermutation;

   public RandomKey(List representation) throws InvalidRepresentationException {
      super(representation);
      List<Double> sortedRepr = new ArrayList(this.getRepresentation());
      Collections.sort(sortedRepr);
      this.sortedRepresentation = Collections.unmodifiableList(sortedRepr);
      this.baseSeqPermutation = Collections.unmodifiableList(decodeGeneric(baseSequence(this.getLength()), this.getRepresentation(), this.sortedRepresentation));
   }

   public RandomKey(Double[] representation) throws InvalidRepresentationException {
      this(Arrays.asList(representation));
   }

   public List decode(List sequence) {
      return decodeGeneric(sequence, this.getRepresentation(), this.sortedRepresentation);
   }

   private static List decodeGeneric(List sequence, List representation, List sortedRepr) throws DimensionMismatchException {
      int l = sequence.size();
      if (representation.size() != l) {
         throw new DimensionMismatchException(representation.size(), l);
      } else if (sortedRepr.size() != l) {
         throw new DimensionMismatchException(sortedRepr.size(), l);
      } else {
         List<Double> reprCopy = new ArrayList(representation);
         List<S> res = new ArrayList(l);

         for(int i = 0; i < l; ++i) {
            int index = reprCopy.indexOf(sortedRepr.get(i));
            res.add(sequence.get(index));
            reprCopy.set(index, (Object)null);
         }

         return res;
      }
   }

   protected boolean isSame(Chromosome another) {
      if (!(another instanceof RandomKey)) {
         return false;
      } else {
         RandomKey<?> anotherRk = (RandomKey)another;
         if (this.getLength() != anotherRk.getLength()) {
            return false;
         } else {
            List<Integer> thisPerm = this.baseSeqPermutation;
            List<Integer> anotherPerm = anotherRk.baseSeqPermutation;

            for(int i = 0; i < this.getLength(); ++i) {
               if (thisPerm.get(i) != anotherPerm.get(i)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   protected void checkValidity(List chromosomeRepresentation) throws InvalidRepresentationException {
      for(double val : chromosomeRepresentation) {
         if (val < (double)0.0F || val > (double)1.0F) {
            throw new InvalidRepresentationException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, new Object[]{val, 0, 1});
         }
      }

   }

   public static final List randomPermutation(int l) {
      List<Double> repr = new ArrayList(l);

      for(int i = 0; i < l; ++i) {
         repr.add(GeneticAlgorithm.getRandomGenerator().nextDouble());
      }

      return repr;
   }

   public static final List identityPermutation(int l) {
      List<Double> repr = new ArrayList(l);

      for(int i = 0; i < l; ++i) {
         repr.add((double)i / (double)l);
      }

      return repr;
   }

   public static List comparatorPermutation(List data, Comparator comparator) {
      List<S> sortedData = new ArrayList(data);
      Collections.sort(sortedData, comparator);
      return inducedPermutation(data, sortedData);
   }

   public static List inducedPermutation(List originalData, List permutedData) throws DimensionMismatchException, MathIllegalArgumentException {
      if (originalData.size() != permutedData.size()) {
         throw new DimensionMismatchException(permutedData.size(), originalData.size());
      } else {
         int l = originalData.size();
         List<S> origDataCopy = new ArrayList(originalData);
         Double[] res = new Double[l];

         for(int i = 0; i < l; ++i) {
            int index = origDataCopy.indexOf(permutedData.get(i));
            if (index == -1) {
               throw new MathIllegalArgumentException(LocalizedFormats.DIFFERENT_ORIG_AND_PERMUTED_DATA, new Object[0]);
            }

            res[index] = (double)i / (double)l;
            origDataCopy.set(index, (Object)null);
         }

         return Arrays.asList(res);
      }
   }

   public String toString() {
      return String.format("(f=%s pi=(%s))", this.getFitness(), this.baseSeqPermutation);
   }

   private static List baseSequence(int l) {
      List<Integer> baseSequence = new ArrayList(l);

      for(int i = 0; i < l; ++i) {
         baseSequence.add(i);
      }

      return baseSequence;
   }
}
