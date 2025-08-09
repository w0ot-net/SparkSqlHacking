package com.clearspring.analytics.stream.quantile;

import com.clearspring.analytics.util.Lists;
import com.clearspring.analytics.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TDigest {
   private Random gen;
   private double compression;
   private GroupTree summary;
   private int count;
   private boolean recordAllData;
   public static final int VERBOSE_ENCODING = 1;
   public static final int SMALL_ENCODING = 2;

   public TDigest(double compression) {
      this(compression, new Random());
   }

   public TDigest(double compression, Random random) {
      this.compression = (double)100.0F;
      this.summary = new GroupTree();
      this.count = 0;
      this.recordAllData = false;
      this.compression = compression;
      this.gen = random;
   }

   public void add(double x) {
      this.add(x, 1);
   }

   public void add(double x, int w) {
      Group base = this.createGroup(x, 0);
      this.add(x, w, base);
   }

   private void add(double x, int w, Group base) {
      Group start = this.summary.floor(base);
      if (start == null) {
         start = this.summary.ceiling(base);
      }

      if (start == null) {
         this.summary.add(TDigest.Group.createWeighted(x, w, base.data()));
         this.count = w;
      } else {
         Iterable<Group> neighbors = this.summary.tailSet(start);
         double minDistance = Double.MAX_VALUE;
         int lastNeighbor = 0;
         int i = this.summary.headCount(start);

         for(Group neighbor : neighbors) {
            double z = Math.abs(neighbor.mean() - x);
            if (!(z <= minDistance)) {
               break;
            }

            minDistance = z;
            lastNeighbor = i++;
         }

         Group closest = null;
         int sum = this.summary.headSum(start);
         i = this.summary.headCount(start);
         double n = (double)1.0F;

         for(Group neighbor : neighbors) {
            if (i > lastNeighbor) {
               break;
            }

            double z = Math.abs(neighbor.mean() - x);
            double q = ((double)sum + (double)neighbor.count() / (double)2.0F) / (double)this.count;
            double k = (double)(4 * this.count) * q * ((double)1.0F - q) / this.compression;
            if (z == minDistance && (double)(neighbor.count() + w) <= k) {
               if (this.gen.nextDouble() < (double)1.0F / n) {
                  closest = neighbor;
               }

               ++n;
            }

            sum += neighbor.count();
            ++i;
         }

         if (closest == null) {
            this.summary.add(TDigest.Group.createWeighted(x, w, base.data()));
         } else {
            this.summary.remove(closest);
            closest.add(x, w, base.data());
            this.summary.add(closest);
         }

         this.count += w;
         if ((double)this.summary.size() > (double)100.0F * this.compression) {
            this.compress();
         }
      }

   }

   public void add(TDigest other) {
      List<Group> tmp = Lists.newArrayList(other.summary);
      Collections.shuffle(tmp, this.gen);

      for(Group group : tmp) {
         this.add(group.mean(), group.count(), group);
      }

   }

   public static TDigest merge(double compression, Iterable subData) {
      Preconditions.checkArgument(subData.iterator().hasNext(), "Can't merge 0 digests");
      List<TDigest> elements = Lists.newArrayList(subData);
      int n = Math.max(1, elements.size() / 4);
      TDigest r = new TDigest(compression, ((TDigest)elements.get(0)).gen);
      if (((TDigest)elements.get(0)).recordAllData) {
         r.recordAllData();
      }

      for(int i = 0; i < elements.size(); i += n) {
         if (n > 1) {
            r.add(merge(compression, elements.subList(i, Math.min(i + n, elements.size()))));
         } else {
            r.add((TDigest)elements.get(i));
         }
      }

      return r;
   }

   public void compress() {
      this.compress(this.summary);
   }

   private void compress(GroupTree other) {
      TDigest reduced = new TDigest(this.compression, this.gen);
      if (this.recordAllData) {
         reduced.recordAllData();
      }

      List<Group> tmp = Lists.newArrayList(other);
      Collections.shuffle(tmp, this.gen);

      for(Group group : tmp) {
         reduced.add(group.mean(), group.count(), group);
      }

      this.summary = reduced.summary;
   }

   public int size() {
      return this.count;
   }

   public double cdf(double x) {
      GroupTree values = this.summary;
      if (values.size() == 0) {
         return Double.NaN;
      } else if (values.size() == 1) {
         return x < values.first().mean() ? (double)0.0F : (double)1.0F;
      } else {
         double r = (double)0.0F;
         Iterator<Group> it = values.iterator();
         Group a = (Group)it.next();
         Group b = (Group)it.next();
         double left = (b.mean() - a.mean()) / (double)2.0F;

         double right;
         for(right = left; it.hasNext(); right = (b.mean() - a.mean()) / (double)2.0F) {
            if (x < a.mean() + right) {
               return (r + (double)a.count() * this.interpolate(x, a.mean() - left, a.mean() + right)) / (double)this.count;
            }

            r += (double)a.count();
            a = b;
            b = (Group)it.next();
            left = right;
         }

         if (x < b.mean() + right) {
            return (r + (double)b.count() * this.interpolate(x, b.mean() - right, b.mean() + right)) / (double)this.count;
         } else {
            return (double)1.0F;
         }
      }
   }

   public double quantile(double q) {
      GroupTree values = this.summary;
      Preconditions.checkArgument(values.size() > 1);
      Iterator<Group> it = values.iterator();
      Group center = (Group)it.next();
      Group leading = (Group)it.next();
      if (!it.hasNext()) {
         double diff = (leading.mean() - center.mean()) / (double)2.0F;
         return q > (double)0.75F ? leading.mean() + diff * ((double)4.0F * q - (double)3.0F) : center.mean() + diff * ((double)4.0F * q - (double)1.0F);
      } else {
         q *= (double)this.count;
         double right = (leading.mean() - center.mean()) / (double)2.0F;
         double left = right;

         double t;
         for(t = (double)center.count(); it.hasNext(); right = (leading.mean() - center.mean()) / (double)2.0F) {
            if (t + (double)(center.count() / 2) >= q) {
               return center.mean() - left * (double)2.0F * (q - t) / (double)center.count();
            }

            if (t + (double)leading.count() >= q) {
               return center.mean() + right * (double)2.0F * ((double)center.count() - (q - t)) / (double)center.count();
            }

            t += (double)center.count();
            center = leading;
            leading = (Group)it.next();
            left = right;
         }

         if (t + (double)(leading.count() / 2) >= q) {
            return leading.mean() - right * (double)2.0F * (q - t) / (double)leading.count();
         } else if (t + (double)leading.count() >= q) {
            return leading.mean() + right * (double)2.0F * ((double)leading.count() - (q - t)) / (double)leading.count();
         } else {
            return (double)1.0F;
         }
      }
   }

   public int centroidCount() {
      return this.summary.size();
   }

   public Iterable centroids() {
      return this.summary;
   }

   public double compression() {
      return this.compression;
   }

   public TDigest recordAllData() {
      this.recordAllData = true;
      return this;
   }

   public int byteSize() {
      return 16 + this.summary.size() * 12;
   }

   public int smallByteSize() {
      int bound = this.byteSize();
      ByteBuffer buf = ByteBuffer.allocate(bound);
      this.asSmallBytes(buf);
      return buf.position();
   }

   public void asBytes(ByteBuffer buf) {
      buf.putInt(1);
      buf.putDouble(this.compression());
      buf.putInt(this.summary.size());

      for(Group group : this.summary) {
         buf.putDouble(group.mean());
      }

      for(Group group : this.summary) {
         buf.putInt(group.count());
      }

   }

   public void asSmallBytes(ByteBuffer buf) {
      buf.putInt(2);
      buf.putDouble(this.compression());
      buf.putInt(this.summary.size());
      double x = (double)0.0F;

      for(Group group : this.summary) {
         double delta = group.mean() - x;
         x = group.mean();
         buf.putFloat((float)delta);
      }

      for(Group group : this.summary) {
         int n = group.count();
         encode(buf, n);
      }

   }

   public static void encode(ByteBuffer buf, int n) {
      int k = 0;

      while(n < 0 || n > 127) {
         byte b = (byte)(128 | 127 & n);
         buf.put(b);
         n >>>= 7;
         ++k;
         Preconditions.checkState(k < 6);
      }

      buf.put((byte)n);
   }

   public static int decode(ByteBuffer buf) {
      int v = buf.get();
      int z = 127 & v;

      for(int shift = 7; (v & 128) != 0; shift += 7) {
         Preconditions.checkState(shift <= 28);
         v = buf.get();
         z += (v & 127) << shift;
      }

      return z;
   }

   public static TDigest fromBytes(ByteBuffer buf) {
      int encoding = buf.getInt();
      if (encoding == 1) {
         double compression = buf.getDouble();
         TDigest r = new TDigest(compression);
         int n = buf.getInt();
         double[] means = new double[n];

         for(int i = 0; i < n; ++i) {
            means[i] = buf.getDouble();
         }

         for(int i = 0; i < n; ++i) {
            r.add(means[i], buf.getInt());
         }

         return r;
      } else if (encoding != 2) {
         throw new IllegalStateException("Invalid format for serialized histogram");
      } else {
         double compression = buf.getDouble();
         TDigest r = new TDigest(compression);
         int n = buf.getInt();
         double[] means = new double[n];
         double x = (double)0.0F;

         for(int i = 0; i < n; ++i) {
            double delta = (double)buf.getFloat();
            x += delta;
            means[i] = x;
         }

         for(int i = 0; i < n; ++i) {
            int z = decode(buf);
            r.add(means[i], z);
         }

         return r;
      }
   }

   private Group createGroup(double mean, int id) {
      return new Group(mean, id, this.recordAllData);
   }

   private double interpolate(double x, double x0, double x1) {
      return (x - x0) / (x1 - x0);
   }

   public static class Group implements Comparable {
      private static final AtomicInteger uniqueCount = new AtomicInteger(1);
      private double centroid;
      private int count;
      private int id;
      private List actualData;

      private Group(boolean record) {
         this.centroid = (double)0.0F;
         this.count = 0;
         this.actualData = null;
         this.id = uniqueCount.incrementAndGet();
         if (record) {
            this.actualData = Lists.newArrayList();
         }

      }

      public Group(double x) {
         this(false);
         this.start(x, uniqueCount.getAndIncrement());
      }

      public Group(double x, int id) {
         this(false);
         this.start(x, id);
      }

      public Group(double x, int id, boolean record) {
         this(record);
         this.start(x, id);
      }

      private void start(double x, int id) {
         this.id = id;
         this.add(x, 1);
      }

      public void add(double x, int w) {
         if (this.actualData != null) {
            this.actualData.add(x);
         }

         this.count += w;
         this.centroid += (double)w * (x - this.centroid) / (double)this.count;
      }

      public double mean() {
         return this.centroid;
      }

      public int count() {
         return this.count;
      }

      public int id() {
         return this.id;
      }

      public String toString() {
         return "Group{centroid=" + this.centroid + ", count=" + this.count + '}';
      }

      public int hashCode() {
         return this.id;
      }

      public int compareTo(Group o) {
         int r = Double.compare(this.centroid, o.centroid);
         if (r == 0) {
            r = this.id - o.id;
         }

         return r;
      }

      public Iterable data() {
         return this.actualData;
      }

      public static Group createWeighted(double x, int w, Iterable data) {
         Group r = new Group(data != null);
         r.add(x, w, data);
         return r;
      }

      private void add(double x, int w, Iterable data) {
         if (this.actualData != null) {
            if (data != null) {
               for(Double old : data) {
                  this.actualData.add(old);
               }
            } else {
               this.actualData.add(x);
            }
         }

         this.count += w;
         this.centroid += (double)w * (x - this.centroid) / (double)this.count;
      }
   }
}
