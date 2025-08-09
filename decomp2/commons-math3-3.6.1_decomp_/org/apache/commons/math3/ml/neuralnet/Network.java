package org.apache.commons.math3.ml.neuralnet;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;

public class Network implements Iterable, Serializable {
   private static final long serialVersionUID = 20130207L;
   private final ConcurrentHashMap neuronMap = new ConcurrentHashMap();
   private final AtomicLong nextId;
   private final int featureSize;
   private final ConcurrentHashMap linkMap = new ConcurrentHashMap();

   Network(long nextId, int featureSize, Neuron[] neuronList, long[][] neighbourIdList) {
      int numNeurons = neuronList.length;
      if (numNeurons != neighbourIdList.length) {
         throw new MathIllegalStateException();
      } else {
         for(int i = 0; i < numNeurons; ++i) {
            Neuron n = neuronList[i];
            long id = n.getIdentifier();
            if (id >= nextId) {
               throw new MathIllegalStateException();
            }

            this.neuronMap.put(id, n);
            this.linkMap.put(id, new HashSet());
         }

         for(int i = 0; i < numNeurons; ++i) {
            long aId = neuronList[i].getIdentifier();
            Set<Long> aLinks = (Set)this.linkMap.get(aId);
            long[] arr$ = neighbourIdList[i];
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               Long bId = arr$[i$];
               if (this.neuronMap.get(bId) == null) {
                  throw new MathIllegalStateException();
               }

               this.addLinkToLinkSet(aLinks, bId);
            }
         }

         this.nextId = new AtomicLong(nextId);
         this.featureSize = featureSize;
      }
   }

   public Network(long initialIdentifier, int featureSize) {
      this.nextId = new AtomicLong(initialIdentifier);
      this.featureSize = featureSize;
   }

   public synchronized Network copy() {
      Network copy = new Network(this.nextId.get(), this.featureSize);

      for(Map.Entry e : this.neuronMap.entrySet()) {
         copy.neuronMap.put(e.getKey(), ((Neuron)e.getValue()).copy());
      }

      for(Map.Entry e : this.linkMap.entrySet()) {
         copy.linkMap.put(e.getKey(), new HashSet((Collection)e.getValue()));
      }

      return copy;
   }

   public Iterator iterator() {
      return this.neuronMap.values().iterator();
   }

   public Collection getNeurons(Comparator comparator) {
      List<Neuron> neurons = new ArrayList();
      neurons.addAll(this.neuronMap.values());
      Collections.sort(neurons, comparator);
      return neurons;
   }

   public long createNeuron(double[] features) {
      if (features.length != this.featureSize) {
         throw new DimensionMismatchException(features.length, this.featureSize);
      } else {
         long id = this.createNextId();
         this.neuronMap.put(id, new Neuron(id, features));
         this.linkMap.put(id, new HashSet());
         return id;
      }
   }

   public void deleteNeuron(Neuron neuron) {
      for(Neuron n : this.getNeighbours(neuron)) {
         this.deleteLink(n, neuron);
      }

      this.neuronMap.remove(neuron.getIdentifier());
   }

   public int getFeaturesSize() {
      return this.featureSize;
   }

   public void addLink(Neuron a, Neuron b) {
      long aId = a.getIdentifier();
      long bId = b.getIdentifier();
      if (a != this.getNeuron(aId)) {
         throw new NoSuchElementException(Long.toString(aId));
      } else if (b != this.getNeuron(bId)) {
         throw new NoSuchElementException(Long.toString(bId));
      } else {
         this.addLinkToLinkSet((Set)this.linkMap.get(aId), bId);
      }
   }

   private void addLinkToLinkSet(Set linkSet, long id) {
      linkSet.add(id);
   }

   public void deleteLink(Neuron a, Neuron b) {
      long aId = a.getIdentifier();
      long bId = b.getIdentifier();
      if (a != this.getNeuron(aId)) {
         throw new NoSuchElementException(Long.toString(aId));
      } else if (b != this.getNeuron(bId)) {
         throw new NoSuchElementException(Long.toString(bId));
      } else {
         this.deleteLinkFromLinkSet((Set)this.linkMap.get(aId), bId);
      }
   }

   private void deleteLinkFromLinkSet(Set linkSet, long id) {
      linkSet.remove(id);
   }

   public Neuron getNeuron(long id) {
      Neuron n = (Neuron)this.neuronMap.get(id);
      if (n == null) {
         throw new NoSuchElementException(Long.toString(id));
      } else {
         return n;
      }
   }

   public Collection getNeighbours(Iterable neurons) {
      return this.getNeighbours((Iterable)neurons, (Iterable)null);
   }

   public Collection getNeighbours(Iterable neurons, Iterable exclude) {
      Set<Long> idList = new HashSet();

      for(Neuron n : neurons) {
         idList.addAll((Collection)this.linkMap.get(n.getIdentifier()));
      }

      if (exclude != null) {
         for(Neuron n : exclude) {
            idList.remove(n.getIdentifier());
         }
      }

      List<Neuron> neuronList = new ArrayList();

      for(Long id : idList) {
         neuronList.add(this.getNeuron(id));
      }

      return neuronList;
   }

   public Collection getNeighbours(Neuron neuron) {
      return this.getNeighbours((Neuron)neuron, (Iterable)null);
   }

   public Collection getNeighbours(Neuron neuron, Iterable exclude) {
      Set<Long> idList = (Set)this.linkMap.get(neuron.getIdentifier());
      if (exclude != null) {
         for(Neuron n : exclude) {
            idList.remove(n.getIdentifier());
         }
      }

      List<Neuron> neuronList = new ArrayList();

      for(Long id : idList) {
         neuronList.add(this.getNeuron(id));
      }

      return neuronList;
   }

   private Long createNextId() {
      return this.nextId.getAndIncrement();
   }

   private void readObject(ObjectInputStream in) {
      throw new IllegalStateException();
   }

   private Object writeReplace() {
      Neuron[] neuronList = (Neuron[])this.neuronMap.values().toArray(new Neuron[0]);
      long[][] neighbourIdList = new long[neuronList.length][];

      for(int i = 0; i < neuronList.length; ++i) {
         Collection<Neuron> neighbours = this.getNeighbours(neuronList[i]);
         long[] neighboursId = new long[neighbours.size()];
         int count = 0;

         for(Neuron n : neighbours) {
            neighboursId[count] = n.getIdentifier();
            ++count;
         }

         neighbourIdList[i] = neighboursId;
      }

      return new SerializationProxy(this.nextId.get(), this.featureSize, neuronList, neighbourIdList);
   }

   public static class NeuronIdentifierComparator implements Comparator, Serializable {
      private static final long serialVersionUID = 20130207L;

      public int compare(Neuron a, Neuron b) {
         long aId = a.getIdentifier();
         long bId = b.getIdentifier();
         return aId < bId ? -1 : (aId > bId ? 1 : 0);
      }
   }

   private static class SerializationProxy implements Serializable {
      private static final long serialVersionUID = 20130207L;
      private final long nextId;
      private final int featureSize;
      private final Neuron[] neuronList;
      private final long[][] neighbourIdList;

      SerializationProxy(long nextId, int featureSize, Neuron[] neuronList, long[][] neighbourIdList) {
         this.nextId = nextId;
         this.featureSize = featureSize;
         this.neuronList = neuronList;
         this.neighbourIdList = neighbourIdList;
      }

      private Object readResolve() {
         return new Network(this.nextId, this.featureSize, this.neuronList, this.neighbourIdList);
      }
   }
}
