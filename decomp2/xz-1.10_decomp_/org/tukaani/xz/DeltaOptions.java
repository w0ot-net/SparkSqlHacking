package org.tukaani.xz;

import java.io.InputStream;

public final class DeltaOptions extends FilterOptions {
   public static final int DISTANCE_MIN = 1;
   public static final int DISTANCE_MAX = 256;
   private int distance = 1;

   public DeltaOptions() {
   }

   public DeltaOptions(int distance) throws UnsupportedOptionsException {
      this.setDistance(distance);
   }

   public void setDistance(int distance) throws UnsupportedOptionsException {
      if (distance >= 1 && distance <= 256) {
         this.distance = distance;
      } else {
         throw new UnsupportedOptionsException("Delta distance must be in the range [1, 256]: " + distance);
      }
   }

   public int getDistance() {
      return this.distance;
   }

   public int getEncoderMemoryUsage() {
      return DeltaOutputStream.getMemoryUsage();
   }

   public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
      return new DeltaOutputStream(out, this);
   }

   public int getDecoderMemoryUsage() {
      return 1;
   }

   public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
      return new DeltaInputStream(in, this.distance);
   }

   FilterEncoder getFilterEncoder() {
      return new DeltaEncoder(this);
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         assert false;

         throw new RuntimeException();
      }
   }
}
