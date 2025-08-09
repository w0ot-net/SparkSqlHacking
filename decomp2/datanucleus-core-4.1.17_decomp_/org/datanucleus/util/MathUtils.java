package org.datanucleus.util;

import java.util.LinkedList;

public class MathUtils {
   public static class SMA {
      private LinkedList values = new LinkedList();
      private int length;
      private double sum = (double)0.0F;
      private double average = (double)0.0F;

      public SMA(int length) {
         if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than zero");
         } else {
            this.length = length;
         }
      }

      public double currentAverage() {
         return this.average;
      }

      public synchronized double compute(double value) {
         if (this.values.size() == this.length && this.length > 0) {
            this.sum -= (Double)this.values.getFirst();
            this.values.removeFirst();
         }

         this.sum += value;
         this.values.addLast(new Double(value));
         this.average = this.sum / (double)this.values.size();
         return this.average;
      }
   }
}
