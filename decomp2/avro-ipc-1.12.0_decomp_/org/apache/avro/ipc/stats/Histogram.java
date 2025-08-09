package org.apache.avro.ipc.stats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;

class Histogram {
   public static final int MAX_HISTORY_SIZE = 20;
   private Segmenter segmenter;
   private int[] counts;
   protected int totalCount;
   private LinkedList recentAdditions;

   public Histogram(Segmenter segmenter) {
      this.segmenter = segmenter;
      this.counts = new int[segmenter.size()];
      this.recentAdditions = new LinkedList();
   }

   public void add(Object value) {
      int i = this.segmenter.segment(value);
      int var10002 = this.counts[i]++;
      ++this.totalCount;
      if (this.recentAdditions.size() > 20) {
         this.recentAdditions.pollLast();
      }

      this.recentAdditions.push(value);
   }

   public int[] getHistogram() {
      return this.counts;
   }

   public Segmenter getSegmenter() {
      return this.segmenter;
   }

   public List getRecentAdditions() {
      return this.recentAdditions;
   }

   public int getCount() {
      return this.totalCount;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      boolean first = true;

      for(Entry e : this.entries()) {
         if (!first) {
            sb.append(";");
         } else {
            first = false;
         }

         sb.append(e.bucket).append("=").append(e.count);
      }

      return sb.toString();
   }

   public Iterable entries() {
      return new EntryIterator();
   }

   public static class SegmenterException extends RuntimeException {
      public SegmenterException(String s) {
         super(s);
      }
   }

   public static class TreeMapSegmenter implements Segmenter {
      private TreeMap index = new TreeMap();

      public TreeMapSegmenter(SortedSet leftEndpoints) {
         if (leftEndpoints.isEmpty()) {
            throw new IllegalArgumentException("Endpoints must not be empty: " + String.valueOf(leftEndpoints));
         } else {
            int i = 0;

            for(Comparable t : leftEndpoints) {
               this.index.put(t, i++);
            }

         }
      }

      public int segment(Comparable value) {
         Map.Entry<T, Integer> e = this.index.floorEntry(value);
         if (e == null) {
            throw new SegmenterException("Could not find bucket for: " + String.valueOf(value));
         } else {
            return (Integer)e.getValue();
         }
      }

      public int size() {
         return this.index.size();
      }

      private String rangeAsString(Comparable a, Comparable b) {
         return String.format("[%s,%s)", a, b == null ? "infinity" : b);
      }

      public ArrayList getBoundaryLabels() {
         ArrayList<String> outArray = new ArrayList(this.index.keySet().size());

         for(Comparable obj : this.index.keySet()) {
            outArray.add(obj.toString());
         }

         return outArray;
      }

      public ArrayList getBucketLabels() {
         ArrayList<String> outArray = new ArrayList(this.index.keySet().size());
         Iterator<String> bucketsIt = this.getBuckets();

         while(bucketsIt.hasNext()) {
            outArray.add((String)bucketsIt.next());
         }

         return outArray;
      }

      public Iterator getBuckets() {
         return new Iterator() {
            Iterator it;
            Comparable cur;
            int pos;

            {
               this.it = TreeMapSegmenter.this.index.keySet().iterator();
               this.cur = (Comparable)this.it.next();
               this.pos = 0;
            }

            public boolean hasNext() {
               return this.pos < TreeMapSegmenter.this.index.keySet().size();
            }

            public String next() {
               ++this.pos;
               T left = (T)this.cur;
               this.cur = this.it.hasNext() ? (Comparable)this.it.next() : null;
               return TreeMapSegmenter.this.rangeAsString(left, this.cur);
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      }
   }

   static class Entry {
      Object bucket;
      int count;

      public Entry(Object bucket, int count) {
         this.bucket = bucket;
         this.count = count;
      }
   }

   private class EntryIterator implements Iterable, Iterator {
      int i = 0;
      Iterator bucketNameIterator;

      private EntryIterator() {
         this.bucketNameIterator = Histogram.this.segmenter.getBuckets();
      }

      public Iterator iterator() {
         return this;
      }

      public boolean hasNext() {
         return this.i < Histogram.this.segmenter.size();
      }

      public Entry next() {
         return new Entry(this.bucketNameIterator.next(), Histogram.this.counts[this.i++]);
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   interface Segmenter {
      int size();

      int segment(Object value);

      Iterator getBuckets();

      List getBoundaryLabels();

      List getBucketLabels();
   }
}
