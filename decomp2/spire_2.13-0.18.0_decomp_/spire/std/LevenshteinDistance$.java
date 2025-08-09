package spire.std;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;
import spire.algebra.MetricSpace;
import spire.algebra.MetricSpace$mcI$sp;

public final class LevenshteinDistance$ implements MetricSpace$mcI$sp, Serializable {
   public static final LevenshteinDistance$ MODULE$ = new LevenshteinDistance$();
   private static final long serialVersionUID = 0L;

   public double distance$mcD$sp(final Object v, final Object w) {
      return MetricSpace.distance$mcD$sp$(this, v, w);
   }

   public float distance$mcF$sp(final Object v, final Object w) {
      return MetricSpace.distance$mcF$sp$(this, v, w);
   }

   public long distance$mcJ$sp(final Object v, final Object w) {
      return MetricSpace.distance$mcJ$sp$(this, v, w);
   }

   public int distance(final String a, final String b) {
      return this.distance$mcI$sp(a, b);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LevenshteinDistance$.class);
   }

   public int distance$mcI$sp(final String a, final String b) {
      int[] row0 = new int[b.length() + 1];
      int[] row1 = new int[b.length() + 1];

      for(int index$macro$1 = 0; index$macro$1 < row0.length; row0[index$macro$1] = index$macro$1++) {
      }

      for(int index$macro$3 = 0; index$macro$3 < a.length(); ++index$macro$3) {
         row1[0] = index$macro$3 + 1;
         char c = a.charAt(index$macro$3);

         for(int index$macro$2 = 1; index$macro$2 < row1.length; ++index$macro$2) {
            int d = row0[index$macro$2 - 1] + (c == b.charAt(index$macro$2 - 1) ? 0 : 1);
            int h = row1[index$macro$2 - 1] + 1;
            int v = row0[index$macro$2] + 1;
            row1[index$macro$2] = d < h ? (v < d ? v : d) : (v < h ? v : h);
         }

         int[] tmp = row0;
         row0 = row1;
         row1 = tmp;
      }

      return row0[b.length()];
   }

   private LevenshteinDistance$() {
   }
}
