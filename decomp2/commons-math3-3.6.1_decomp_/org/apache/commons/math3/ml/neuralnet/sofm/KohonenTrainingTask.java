package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Iterator;
import org.apache.commons.math3.ml.neuralnet.Network;

public class KohonenTrainingTask implements Runnable {
   private final Network net;
   private final Iterator featuresIterator;
   private final KohonenUpdateAction updateAction;

   public KohonenTrainingTask(Network net, Iterator featuresIterator, KohonenUpdateAction updateAction) {
      this.net = net;
      this.featuresIterator = featuresIterator;
      this.updateAction = updateAction;
   }

   public void run() {
      while(this.featuresIterator.hasNext()) {
         this.updateAction.update(this.net, (double[])this.featuresIterator.next());
      }

   }
}
