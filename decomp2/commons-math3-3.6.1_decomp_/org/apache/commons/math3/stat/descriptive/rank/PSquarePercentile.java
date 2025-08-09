package org.apache.commons.math3.stat.descriptive.rank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class PSquarePercentile extends AbstractStorelessUnivariateStatistic implements StorelessUnivariateStatistic, Serializable {
   private static final int PSQUARE_CONSTANT = 5;
   private static final double DEFAULT_QUANTILE_DESIRED = (double)50.0F;
   private static final long serialVersionUID = 2283912083175715479L;
   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00.00");
   private final List initialFive;
   private final double quantile;
   private transient double lastObservation;
   private PSquareMarkers markers;
   private double pValue;
   private long countOfObservations;

   public PSquarePercentile(double p) {
      this.initialFive = new FixedCapacityList(5);
      this.markers = null;
      this.pValue = Double.NaN;
      if (!(p > (double)100.0F) && !(p < (double)0.0F)) {
         this.quantile = p / (double)100.0F;
      } else {
         throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE, p, 0, 100);
      }
   }

   PSquarePercentile() {
      this((double)50.0F);
   }

   public int hashCode() {
      double result = this.getResult();
      result = Double.isNaN(result) ? (double)37.0F : result;
      double markersHash = this.markers == null ? (double)0.0F : (double)this.markers.hashCode();
      double[] toHash = new double[]{result, this.quantile, markersHash, (double)this.countOfObservations};
      return Arrays.hashCode(toHash);
   }

   public boolean equals(Object o) {
      boolean result = false;
      if (this == o) {
         result = true;
      } else if (o != null && o instanceof PSquarePercentile) {
         PSquarePercentile that = (PSquarePercentile)o;
         boolean isNotNull = this.markers != null && that.markers != null;
         boolean isNull = this.markers == null && that.markers == null;
         result = isNotNull ? this.markers.equals(that.markers) : isNull;
         result = result && this.getN() == that.getN();
      }

      return result;
   }

   public void increment(double observation) {
      ++this.countOfObservations;
      this.lastObservation = observation;
      if (this.markers == null) {
         if (this.initialFive.add(observation)) {
            Collections.sort(this.initialFive);
            this.pValue = (Double)this.initialFive.get((int)(this.quantile * (double)(this.initialFive.size() - 1)));
            return;
         }

         this.markers = newMarkers(this.initialFive, this.quantile);
      }

      this.pValue = this.markers.processDataPoint(observation);
   }

   public String toString() {
      return this.markers == null ? String.format("obs=%s pValue=%s", DECIMAL_FORMAT.format(this.lastObservation), DECIMAL_FORMAT.format(this.pValue)) : String.format("obs=%s markers=%s", DECIMAL_FORMAT.format(this.lastObservation), this.markers.toString());
   }

   public long getN() {
      return this.countOfObservations;
   }

   public StorelessUnivariateStatistic copy() {
      PSquarePercentile copy = new PSquarePercentile((double)100.0F * this.quantile);
      if (this.markers != null) {
         copy.markers = (PSquareMarkers)this.markers.clone();
      }

      copy.countOfObservations = this.countOfObservations;
      copy.pValue = this.pValue;
      copy.initialFive.clear();
      copy.initialFive.addAll(this.initialFive);
      return copy;
   }

   public double quantile() {
      return this.quantile;
   }

   public void clear() {
      this.markers = null;
      this.initialFive.clear();
      this.countOfObservations = 0L;
      this.pValue = Double.NaN;
   }

   public double getResult() {
      if (Double.compare(this.quantile, (double)1.0F) == 0) {
         this.pValue = this.maximum();
      } else if (Double.compare(this.quantile, (double)0.0F) == 0) {
         this.pValue = this.minimum();
      }

      return this.pValue;
   }

   private double maximum() {
      double val = Double.NaN;
      if (this.markers != null) {
         val = this.markers.height(5);
      } else if (!this.initialFive.isEmpty()) {
         val = (Double)this.initialFive.get(this.initialFive.size() - 1);
      }

      return val;
   }

   private double minimum() {
      double val = Double.NaN;
      if (this.markers != null) {
         val = this.markers.height(1);
      } else if (!this.initialFive.isEmpty()) {
         val = (Double)this.initialFive.get(0);
      }

      return val;
   }

   public static PSquareMarkers newMarkers(List initialFive, double p) {
      return new Markers(initialFive, p);
   }

   private static class Markers implements PSquareMarkers, Serializable {
      private static final long serialVersionUID = 1L;
      private static final int LOW = 2;
      private static final int HIGH = 4;
      private final Marker[] markerArray;
      private transient int k;

      private Markers(Marker[] theMarkerArray) {
         this.k = -1;
         MathUtils.checkNotNull(theMarkerArray);
         this.markerArray = theMarkerArray;

         for(int i = 1; i < 5; ++i) {
            this.markerArray[i].previous(this.markerArray[i - 1]).next(this.markerArray[i + 1]).index(i);
         }

         this.markerArray[0].previous(this.markerArray[0]).next(this.markerArray[1]).index(0);
         this.markerArray[5].previous(this.markerArray[4]).next(this.markerArray[5]).index(5);
      }

      private Markers(List initialFive, double p) {
         this(createMarkerArray(initialFive, p));
      }

      private static Marker[] createMarkerArray(List initialFive, double p) {
         int countObserved = initialFive == null ? -1 : initialFive.size();
         if (countObserved < 5) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, new Object[]{countObserved, 5});
         } else {
            Collections.sort(initialFive);
            return new Marker[]{new Marker(), new Marker((Double)initialFive.get(0), (double)1.0F, (double)0.0F, (double)1.0F), new Marker((Double)initialFive.get(1), (double)1.0F + (double)2.0F * p, p / (double)2.0F, (double)2.0F), new Marker((Double)initialFive.get(2), (double)1.0F + (double)4.0F * p, p, (double)3.0F), new Marker((Double)initialFive.get(3), (double)3.0F + (double)2.0F * p, ((double)1.0F + p) / (double)2.0F, (double)4.0F), new Marker((Double)initialFive.get(4), (double)5.0F, (double)1.0F, (double)5.0F)};
         }
      }

      public int hashCode() {
         return Arrays.deepHashCode(this.markerArray);
      }

      public boolean equals(Object o) {
         boolean result = false;
         if (this == o) {
            result = true;
         } else if (o != null && o instanceof Markers) {
            Markers that = (Markers)o;
            result = Arrays.deepEquals(this.markerArray, that.markerArray);
         }

         return result;
      }

      public double processDataPoint(double inputDataPoint) {
         int kthCell = this.findCellAndUpdateMinMax(inputDataPoint);
         this.incrementPositions(1, kthCell + 1, 5);
         this.updateDesiredPositions();
         this.adjustHeightsOfMarkers();
         return this.getPercentileValue();
      }

      public double getPercentileValue() {
         return this.height(3);
      }

      private int findCellAndUpdateMinMax(double observation) {
         this.k = -1;
         if (observation < this.height(1)) {
            this.markerArray[1].markerHeight = observation;
            this.k = 1;
         } else if (observation < this.height(2)) {
            this.k = 1;
         } else if (observation < this.height(3)) {
            this.k = 2;
         } else if (observation < this.height(4)) {
            this.k = 3;
         } else if (observation <= this.height(5)) {
            this.k = 4;
         } else {
            this.markerArray[5].markerHeight = observation;
            this.k = 4;
         }

         return this.k;
      }

      private void adjustHeightsOfMarkers() {
         for(int i = 2; i <= 4; ++i) {
            this.estimate(i);
         }

      }

      public double estimate(int index) {
         if (index >= 2 && index <= 4) {
            return this.markerArray[index].estimate();
         } else {
            throw new OutOfRangeException(index, 2, 4);
         }
      }

      private void incrementPositions(int d, int startIndex, int endIndex) {
         for(int i = startIndex; i <= endIndex; ++i) {
            this.markerArray[i].incrementPosition(d);
         }

      }

      private void updateDesiredPositions() {
         for(int i = 1; i < this.markerArray.length; ++i) {
            this.markerArray[i].updateDesiredPosition();
         }

      }

      private void readObject(ObjectInputStream anInputStream) throws ClassNotFoundException, IOException {
         anInputStream.defaultReadObject();

         for(int i = 1; i < 5; ++i) {
            this.markerArray[i].previous(this.markerArray[i - 1]).next(this.markerArray[i + 1]).index(i);
         }

         this.markerArray[0].previous(this.markerArray[0]).next(this.markerArray[1]).index(0);
         this.markerArray[5].previous(this.markerArray[4]).next(this.markerArray[5]).index(5);
      }

      public double height(int markerIndex) {
         if (markerIndex < this.markerArray.length && markerIndex > 0) {
            return this.markerArray[markerIndex].markerHeight;
         } else {
            throw new OutOfRangeException(markerIndex, 1, this.markerArray.length);
         }
      }

      public Object clone() {
         return new Markers(new Marker[]{new Marker(), (Marker)this.markerArray[1].clone(), (Marker)this.markerArray[2].clone(), (Marker)this.markerArray[3].clone(), (Marker)this.markerArray[4].clone(), (Marker)this.markerArray[5].clone()});
      }

      public String toString() {
         return String.format("m1=[%s],m2=[%s],m3=[%s],m4=[%s],m5=[%s]", this.markerArray[1].toString(), this.markerArray[2].toString(), this.markerArray[3].toString(), this.markerArray[4].toString(), this.markerArray[5].toString());
      }
   }

   private static class Marker implements Serializable, Cloneable {
      private static final long serialVersionUID = -3575879478288538431L;
      private int index;
      private double intMarkerPosition;
      private double desiredMarkerPosition;
      private double markerHeight;
      private double desiredMarkerIncrement;
      private transient Marker next;
      private transient Marker previous;
      private final UnivariateInterpolator nonLinear;
      private transient UnivariateInterpolator linear;

      private Marker() {
         this.nonLinear = new NevilleInterpolator();
         this.linear = new LinearInterpolator();
         this.next = this.previous = this;
      }

      private Marker(double heightOfMarker, double makerPositionDesired, double markerPositionIncrement, double markerPositionNumber) {
         this();
         this.markerHeight = heightOfMarker;
         this.desiredMarkerPosition = makerPositionDesired;
         this.desiredMarkerIncrement = markerPositionIncrement;
         this.intMarkerPosition = markerPositionNumber;
      }

      private Marker previous(Marker previousMarker) {
         MathUtils.checkNotNull(previousMarker);
         this.previous = previousMarker;
         return this;
      }

      private Marker next(Marker nextMarker) {
         MathUtils.checkNotNull(nextMarker);
         this.next = nextMarker;
         return this;
      }

      private Marker index(int indexOfMarker) {
         this.index = indexOfMarker;
         return this;
      }

      private void updateDesiredPosition() {
         this.desiredMarkerPosition += this.desiredMarkerIncrement;
      }

      private void incrementPosition(int d) {
         this.intMarkerPosition += (double)d;
      }

      private double difference() {
         return this.desiredMarkerPosition - this.intMarkerPosition;
      }

      private double estimate() {
         double di = this.difference();
         boolean isNextHigher = this.next.intMarkerPosition - this.intMarkerPosition > (double)1.0F;
         boolean isPreviousLower = this.previous.intMarkerPosition - this.intMarkerPosition < (double)-1.0F;
         if (di >= (double)1.0F && isNextHigher || di <= (double)-1.0F && isPreviousLower) {
            int d = di >= (double)0.0F ? 1 : -1;
            double[] xval = new double[]{this.previous.intMarkerPosition, this.intMarkerPosition, this.next.intMarkerPosition};
            double[] yval = new double[]{this.previous.markerHeight, this.markerHeight, this.next.markerHeight};
            double xD = this.intMarkerPosition + (double)d;
            UnivariateFunction univariateFunction = this.nonLinear.interpolate(xval, yval);
            this.markerHeight = univariateFunction.value(xD);
            if (this.isEstimateBad(yval, this.markerHeight)) {
               int delta = xD - xval[1] > (double)0.0F ? 1 : -1;
               double[] xBad = new double[]{xval[1], xval[1 + delta]};
               double[] yBad = new double[]{yval[1], yval[1 + delta]};
               MathArrays.sortInPlace(xBad, yBad);
               univariateFunction = this.linear.interpolate(xBad, yBad);
               this.markerHeight = univariateFunction.value(xD);
            }

            this.incrementPosition(d);
         }

         return this.markerHeight;
      }

      private boolean isEstimateBad(double[] y, double yD) {
         return yD <= y[0] || yD >= y[2];
      }

      public boolean equals(Object o) {
         boolean result = false;
         if (this == o) {
            result = true;
         } else if (o != null && o instanceof Marker) {
            Marker that = (Marker)o;
            result = Double.compare(this.markerHeight, that.markerHeight) == 0;
            result = result && Double.compare(this.intMarkerPosition, that.intMarkerPosition) == 0;
            result = result && Double.compare(this.desiredMarkerPosition, that.desiredMarkerPosition) == 0;
            result = result && Double.compare(this.desiredMarkerIncrement, that.desiredMarkerIncrement) == 0;
            result = result && this.next.index == that.next.index;
            result = result && this.previous.index == that.previous.index;
         }

         return result;
      }

      public int hashCode() {
         return Arrays.hashCode(new double[]{this.markerHeight, this.intMarkerPosition, this.desiredMarkerIncrement, this.desiredMarkerPosition, (double)this.previous.index, (double)this.next.index});
      }

      private void readObject(ObjectInputStream anInstream) throws ClassNotFoundException, IOException {
         anInstream.defaultReadObject();
         this.previous = this.next = this;
         this.linear = new LinearInterpolator();
      }

      public Object clone() {
         return new Marker(this.markerHeight, this.desiredMarkerPosition, this.desiredMarkerIncrement, this.intMarkerPosition);
      }

      public String toString() {
         return String.format("index=%.0f,n=%.0f,np=%.2f,q=%.2f,dn=%.2f,prev=%d,next=%d", (double)this.index, Precision.round(this.intMarkerPosition, 0), Precision.round(this.desiredMarkerPosition, 2), Precision.round(this.markerHeight, 2), Precision.round(this.desiredMarkerIncrement, 2), this.previous.index, this.next.index);
      }
   }

   private static class FixedCapacityList extends ArrayList implements Serializable {
      private static final long serialVersionUID = 2283952083075725479L;
      private final int capacity;

      FixedCapacityList(int fixedCapacity) {
         super(fixedCapacity);
         this.capacity = fixedCapacity;
      }

      public boolean add(Object e) {
         return this.size() < this.capacity ? super.add(e) : false;
      }

      public boolean addAll(Collection collection) {
         boolean isCollectionLess = collection != null && collection.size() + this.size() <= this.capacity;
         return isCollectionLess ? super.addAll(collection) : false;
      }
   }

   protected interface PSquareMarkers extends Cloneable {
      double getPercentileValue();

      Object clone();

      double height(int var1);

      double processDataPoint(double var1);

      double estimate(int var1);
   }
}
