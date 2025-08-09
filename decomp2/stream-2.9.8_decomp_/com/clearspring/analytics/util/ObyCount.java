package com.clearspring.analytics.util;

import com.clearspring.analytics.stream.cardinality.HyperLogLogPlus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ObyCount {
   public static void usage() {
      System.err.println("obycount [update-rate]\n\nupdate-rate: output results after every update-rate elements/lines\nExample:> cat elements.txt | obycount\n");
      System.exit(-1);
   }

   public static void main(String[] args) throws IOException {
      long updateRate = -1L;
      long count = 0L;
      if (args.length > 0) {
         try {
            updateRate = Long.parseLong(args[0]);
         } catch (NumberFormatException var8) {
            System.err.print("Bad update rate: '" + args[0] + "'  Update rate must be an integer.");
            usage();
         }
      }

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      HyperLogLogPlus card = new HyperLogLogPlus(14, 25);
      String line = null;

      while((line = in.readLine()) != null) {
         card.offer(line);
         ++count;
         if (updateRate > 0L && count % updateRate == 0L) {
            System.out.println(formatSummary(count, card.cardinality()));
         }
      }

      System.out.println(formatSummary(count, card.cardinality()));
   }

   protected static String formatSummary(long count, long cardinality) {
      String cntStr = Long.toString(count);
      int len = cntStr.length();
      int l1 = Math.max(len, 10);
      int l2 = Math.max(len, 20);
      String fmt = "%" + l1 + "s %" + l2 + "s";
      StringBuilder sb = new StringBuilder();
      sb.append(String.format(fmt, "Item Count", "Cardinality Estimate")).append('\n');
      sb.append(String.format(fmt, TopK.string('-', l1), TopK.string('-', l2))).append('\n');
      sb.append(String.format(fmt, count, cardinality)).append('\n');
      return sb.toString();
   }
}
