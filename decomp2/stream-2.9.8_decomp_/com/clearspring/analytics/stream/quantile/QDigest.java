package com.clearspring.analytics.stream.quantile;

import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongIterator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QDigest implements IQuantileEstimator {
   private static final Comparator RANGES_COMPARATOR = new Comparator() {
      public int compare(long[] ra, long[] rb) {
         long rightA = ra[1];
         long rightB = rb[1];
         long sizeA = ra[1] - ra[0];
         long sizeB = rb[1] - rb[0];
         if (rightA < rightB) {
            return -1;
         } else if (rightA > rightB) {
            return 1;
         } else if (sizeA < sizeB) {
            return -1;
         } else {
            return sizeA > sizeB ? 1 : 0;
         }
      }
   };
   private static final int MAP_INITIAL_SIZE = 16;
   private static final float MAP_LOAD_FACTOR = 0.25F;
   private long size;
   private long capacity = 1L;
   private double compressionFactor;
   private Long2LongOpenHashMap node2count = new Long2LongOpenHashMap(16, 0.25F);

   public QDigest(double compressionFactor) {
      this.compressionFactor = compressionFactor;
   }

   private long value2leaf(long x) {
      return this.capacity + x;
   }

   private long leaf2value(long id) {
      return id - this.capacity;
   }

   private boolean isRoot(long id) {
      return id == 1L;
   }

   private boolean isLeaf(long id) {
      return id >= this.capacity;
   }

   private long sibling(long id) {
      return id % 2L == 0L ? id + 1L : id - 1L;
   }

   private long parent(long id) {
      return id / 2L;
   }

   private long leftChild(long id) {
      return 2L * id;
   }

   private long rightChild(long id) {
      return 2L * id + 1L;
   }

   private long rangeLeft(long id) {
      while(!this.isLeaf(id)) {
         id = this.leftChild(id);
      }

      return this.leaf2value(id);
   }

   private long rangeRight(long id) {
      while(!this.isLeaf(id)) {
         id = this.rightChild(id);
      }

      return this.leaf2value(id);
   }

   public void offer(long value) {
      if (value >= 0L && value <= 4611686018427387903L) {
         if (value >= this.capacity) {
            this.rebuildToCapacity(Long.highestOneBit(value) << 1);
         }

         long leaf = this.value2leaf(value);
         this.node2count.addTo(leaf, 1L);
         ++this.size;
         this.compressUpward(leaf);
         if ((double)this.node2count.size() > (double)3.0F * this.compressionFactor) {
            this.compressFully();
         }

      } else {
         throw new IllegalArgumentException("Can only accept values in the range 0..4611686018427387903, got " + value);
      }
   }

   public static QDigest unionOf(QDigest a, QDigest b) {
      if (a.compressionFactor != b.compressionFactor) {
         throw new IllegalArgumentException("Compression factors must be the same: left is " + a.compressionFactor + ", right is " + b.compressionFactor);
      } else if (a.capacity > b.capacity) {
         return unionOf(b, a);
      } else {
         QDigest res = new QDigest(a.compressionFactor);
         res.capacity = a.capacity;
         res.size = a.size + b.size;
         LongIterator var3 = a.node2count.keySet().iterator();

         while(var3.hasNext()) {
            long k = (Long)var3.next();
            res.node2count.put(k, a.node2count.get(k));
         }

         if (b.capacity > res.capacity) {
            res.rebuildToCapacity(b.capacity);
         }

         var3 = b.node2count.keySet().iterator();

         while(var3.hasNext()) {
            long k = (Long)var3.next();
            res.node2count.put(k, b.get(k) + res.get(k));
         }

         res.compressFully();
         return res;
      }
   }

   private void rebuildToCapacity(long newCapacity) {
      Long2LongOpenHashMap newNode2count = new Long2LongOpenHashMap(16, 0.25F);
      long scaleR = newCapacity / this.capacity - 1L;
      Long[] keys = (Long[])this.node2count.keySet().toArray(new Long[this.node2count.size()]);
      Arrays.sort(keys);
      long scaleL = 1L;
      Long[] var9 = keys;
      int var10 = keys.length;

      for(int var11 = 0; var11 < var10; ++var11) {
         long k;
         for(k = var9[var11]; scaleL <= k / 2L; scaleL <<= 1) {
         }

         newNode2count.put(k + scaleL * scaleR, this.node2count.get(k));
      }

      this.node2count = newNode2count;
      this.capacity = newCapacity;
      this.compressFully();
   }

   private void compressFully() {
      Long[] allNodes = (Long[])this.node2count.keySet().toArray(new Long[this.node2count.size()]);
      Long[] var2 = allNodes;
      int var3 = allNodes.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         long node = var2[var4];
         if (!this.isRoot(node)) {
            this.compressDownward(node);
         }
      }

   }

   private void compressUpward(long node) {
      double threshold = Math.floor((double)this.size / this.compressionFactor);

      long atSibling;
      long atParent;
      for(long atNode = this.get(node); !this.isRoot(node) && !((double)atNode > threshold); atNode = atParent + atNode + atSibling) {
         atSibling = this.get(this.sibling(node));
         if ((double)(atNode + atSibling) > threshold) {
            break;
         }

         atParent = this.get(this.parent(node));
         if ((double)(atNode + atSibling + atParent) > threshold) {
            break;
         }

         this.node2count.addTo(this.parent(node), atNode + atSibling);
         this.node2count.remove(node);
         if (atSibling > 0L) {
            this.node2count.remove(this.sibling(node));
         }

         node = this.parent(node);
      }

   }

   private void compressDownward(long seedNode) {
      double threshold = Math.floor((double)this.size / this.compressionFactor);
      LongArrayFIFOQueue q = new LongArrayFIFOQueue();
      q.enqueue(seedNode);

      while(!q.isEmpty()) {
         long node = q.dequeueLong();
         long atNode = this.get(node);
         long atSibling = this.get(this.sibling(node));
         if (atNode != 0L || atSibling != 0L) {
            long atParent = this.get(this.parent(node));
            if (!((double)(atParent + atNode + atSibling) > threshold)) {
               this.node2count.addTo(this.parent(node), atNode + atSibling);
               this.node2count.remove(node);
               this.node2count.remove(this.sibling(node));
               if (!this.isLeaf(node)) {
                  q.enqueue(this.leftChild(node));
                  q.enqueue(this.leftChild(this.sibling(node)));
               }
            }
         }
      }

   }

   private long get(long node) {
      return this.node2count.get(node);
   }

   public long getQuantile(double q) {
      List<long[]> ranges = this.toAscRanges();
      long s = 0L;

      for(long[] r : ranges) {
         s += r[2];
         if ((double)s > q * (double)this.size) {
            return r[1];
         }
      }

      return ((long[])ranges.get(ranges.size() - 1))[1];
   }

   public List toAscRanges() {
      List<long[]> ranges = new ArrayList();
      LongIterator var2 = this.node2count.keySet().iterator();

      while(var2.hasNext()) {
         long key = (Long)var2.next();
         ranges.add(new long[]{this.rangeLeft(key), this.rangeRight(key), this.node2count.get(key)});
      }

      Collections.sort(ranges, RANGES_COMPARATOR);
      return ranges;
   }

   public String toString() {
      List<long[]> ranges = this.toAscRanges();
      StringBuilder res = new StringBuilder();

      for(long[] range : ranges) {
         if (res.length() > 0) {
            res.append(", ");
         }

         res.append(range[0]).append(" .. ").append(range[1]).append(": ").append(range[2]);
      }

      return res.toString();
   }

   public static byte[] serialize(QDigest d) {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream s = new DataOutputStream(bos);

      try {
         s.writeLong(d.size);
         s.writeDouble(d.compressionFactor);
         s.writeLong(d.capacity);
         s.writeInt(d.node2count.size());
         LongIterator var3 = d.node2count.keySet().iterator();

         while(var3.hasNext()) {
            long k = (Long)var3.next();
            s.writeLong(k);
            s.writeLong(d.node2count.get(k));
         }

         s.close();
         return bos.toByteArray();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static QDigest deserialize(byte[] b) {
      ByteArrayInputStream bis = new ByteArrayInputStream(b);
      DataInputStream s = new DataInputStream(bis);

      try {
         long size = s.readLong();
         double compressionFactor = s.readDouble();
         long capacity = s.readLong();
         int count = s.readInt();
         QDigest d = new QDigest(compressionFactor);
         d.size = size;
         d.capacity = capacity;

         for(int i = 0; i < count; ++i) {
            long k = s.readLong();
            long n = s.readLong();
            d.node2count.put(k, n);
         }

         return d;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public long computeActualSize() {
      long res = 0L;

      long x;
      for(LongIterator var3 = this.node2count.values().iterator(); var3.hasNext(); res += x) {
         x = (Long)var3.next();
      }

      return res;
   }
}
