package org.apache.spark.graphx;

import java.io.Serializable;

public class TripletFields implements Serializable {
   public final boolean useSrc;
   public final boolean useDst;
   public final boolean useEdge;
   public static final TripletFields None = new TripletFields(false, false, false);
   public static final TripletFields EdgeOnly = new TripletFields(false, false, true);
   public static final TripletFields Src = new TripletFields(true, false, true);
   public static final TripletFields Dst = new TripletFields(false, true, true);
   public static final TripletFields All = new TripletFields(true, true, true);

   public TripletFields() {
      this(true, true, true);
   }

   public TripletFields(boolean useSrc, boolean useDst, boolean useEdge) {
      this.useSrc = useSrc;
      this.useDst = useDst;
      this.useEdge = useEdge;
   }
}
