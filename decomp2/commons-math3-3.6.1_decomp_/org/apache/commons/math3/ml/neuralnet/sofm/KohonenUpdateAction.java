package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.UpdateAction;

public class KohonenUpdateAction implements UpdateAction {
   private final DistanceMeasure distance;
   private final LearningFactorFunction learningFactor;
   private final NeighbourhoodSizeFunction neighbourhoodSize;
   private final AtomicLong numberOfCalls = new AtomicLong(0L);

   public KohonenUpdateAction(DistanceMeasure distance, LearningFactorFunction learningFactor, NeighbourhoodSizeFunction neighbourhoodSize) {
      this.distance = distance;
      this.learningFactor = learningFactor;
      this.neighbourhoodSize = neighbourhoodSize;
   }

   public void update(Network net, double[] features) {
      long numCalls = this.numberOfCalls.incrementAndGet() - 1L;
      double currentLearning = this.learningFactor.value(numCalls);
      Neuron best = this.findAndUpdateBestNeuron(net, features, currentLearning);
      int currentNeighbourhood = this.neighbourhoodSize.value(numCalls);
      Gaussian neighbourhoodDecay = new Gaussian(currentLearning, (double)0.0F, (double)currentNeighbourhood);
      if (currentNeighbourhood > 0) {
         Collection<Neuron> neighbours = new HashSet();
         neighbours.add(best);
         HashSet<Neuron> exclude = new HashSet();
         exclude.add(best);
         int radius = 1;

         do {
            neighbours = net.getNeighbours((Iterable)neighbours, exclude);

            for(Neuron n : neighbours) {
               this.updateNeighbouringNeuron(n, features, neighbourhoodDecay.value((double)radius));
            }

            exclude.addAll(neighbours);
            ++radius;
         } while(radius <= currentNeighbourhood);
      }

   }

   public long getNumberOfCalls() {
      return this.numberOfCalls.get();
   }

   private boolean attemptNeuronUpdate(Neuron n, double[] features, double learningRate) {
      double[] expect = n.getFeatures();
      double[] update = this.computeFeatures(expect, features, learningRate);
      return n.compareAndSetFeatures(expect, update);
   }

   private void updateNeighbouringNeuron(Neuron n, double[] features, double learningRate) {
      while(!this.attemptNeuronUpdate(n, features, learningRate)) {
      }

   }

   private Neuron findAndUpdateBestNeuron(Network net, double[] features, double learningRate) {
      Neuron best;
      do {
         best = MapUtils.findBest(features, net, this.distance);
      } while(!this.attemptNeuronUpdate(best, features, learningRate));

      return best;
   }

   private double[] computeFeatures(double[] current, double[] sample, double learningRate) {
      ArrayRealVector c = new ArrayRealVector(current, false);
      ArrayRealVector s = new ArrayRealVector(sample, false);
      return s.subtract(c).mapMultiplyToSelf(learningRate).add(c).toArray();
   }
}
