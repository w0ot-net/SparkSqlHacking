package com.clearspring.analytics.util;

import com.clearspring.analytics.stream.Counter;
import com.clearspring.analytics.stream.StreamSummary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TopK {
   public static void usage() {
      System.err.println("topk [capacity] [update-rate]\n\ncapacity   : size of top / k (defaults to 1000)update-rate: output results after every update-rate elements/lines\nExample:> cat elements.txt | topk 10\n");
      System.exit(-1);
   }

   public static void main(String[] args) throws IOException {
      long updateRate = -1L;
      long count = 0L;
      int capacity = 1000;
      if (args.length > 0) {
         try {
            capacity = Integer.parseInt(args[0]);
         } catch (NumberFormatException var10) {
            System.err.print("Bad capacity: '" + args[0] + "'  Capacity must be an integer.");
            usage();
         }
      }

      if (args.length > 1) {
         try {
            updateRate = Long.parseLong(args[1]);
         } catch (NumberFormatException var9) {
            System.err.print("Bade update rate: '" + args[1] + "'  Update rate must be an integer.");
            usage();
         }
      }

      StreamSummary<String> topk = new StreamSummary(capacity);
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String line = null;

      while((line = in.readLine()) != null) {
         topk.offer(line);
         ++count;
         if (updateRate > 0L && count % updateRate == 0L) {
            System.out.println(formatSummary(topk));
            System.out.println("Item count: " + count);
            System.out.println();
         }
      }

      System.out.println(formatSummary(topk));
      System.out.println("Item count: " + count);
   }

   public static String formatSummary(StreamSummary topk) {
      StringBuilder sb = new StringBuilder();
      List<Counter<String>> counters = topk.topK(topk.getCapacity());
      String itemHeader = "item";
      String countHeader = "count";
      String errorHeader = "error";
      int maxItemLen = itemHeader.length();
      int maxCountLen = countHeader.length();
      int maxErrorLen = errorHeader.length();

      for(Counter counter : counters) {
         maxItemLen = Math.max(((String)counter.getItem()).length(), maxItemLen);
         maxCountLen = Math.max(Long.toString(counter.getCount()).length(), maxCountLen);
         maxErrorLen = Math.max(Long.toString(counter.getError()).length(), maxErrorLen);
      }

      sb.append(String.format("%" + maxItemLen + "s %" + maxCountLen + "s %" + maxErrorLen + "s", itemHeader, countHeader, errorHeader));
      sb.append('\n');
      sb.append(String.format("%" + maxItemLen + "s %" + maxCountLen + "s %" + maxErrorLen + "s", string('-', maxItemLen), string('-', maxCountLen), string('-', maxErrorLen)));
      sb.append('\n');

      for(Counter counter : counters) {
         sb.append(String.format("%" + maxItemLen + "s %" + maxCountLen + "d %" + maxErrorLen + "d", counter.getItem(), counter.getCount(), counter.getError()));
         sb.append('\n');
      }

      return sb.toString();
   }

   public static String string(char c, int len) {
      StringBuilder sb = new StringBuilder(len);

      for(int i = 0; i < len; ++i) {
         sb.append(c);
      }

      return sb.toString();
   }
}
