package org.codehaus.janino.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.codehaus.commons.nullanalysis.Nullable;

public final class HprofScrubber {
   private HprofScrubber() {
   }

   public static void main(String[] args) throws Exception {
      String fileName = args.length == 0 ? "java.hprof.txt" : args[0];
      BufferedReader br = new BufferedReader(new FileReader(fileName));

      try {
         Map<Integer, String[]> traces = new HashMap();
         List<Site> sites = new ArrayList();
         List<Sample> samples = new ArrayList();
         String s = br.readLine();

         while(s != null) {
            if (s.startsWith("SITES BEGIN")) {
               br.readLine();
               br.readLine();

               while(true) {
                  s = br.readLine();
                  if (s.startsWith("SITES END")) {
                     break;
                  }

                  StringTokenizer st = new StringTokenizer(s);
                  st.nextToken();
                  st.nextToken();
                  st.nextToken();
                  st.nextToken();
                  st.nextToken();
                  sites.add(new Site(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken()));
               }
            } else if (s.startsWith("TRACE ") && s.endsWith(":")) {
               int traceNumber = Integer.parseInt(s.substring(6, s.length() - 1));
               List<String> l = new ArrayList();

               while(true) {
                  s = br.readLine();
                  if (!s.startsWith("\t")) {
                     traces.put(new Integer(traceNumber), (String[])l.toArray(new String[l.size()]));
                     break;
                  }

                  l.add(s.substring(1));
               }
            } else if (s.startsWith("CPU SAMPLES BEGIN")) {
               br.readLine();

               while(true) {
                  s = br.readLine();
                  if (s.startsWith("CPU SAMPLES END")) {
                     break;
                  }

                  StringTokenizer st = new StringTokenizer(s);
                  st.nextToken();
                  st.nextToken();
                  st.nextToken();
                  int count = Integer.parseInt(st.nextToken());
                  if (count != 0) {
                     int trace = Integer.parseInt(st.nextToken());
                     samples.add(new Sample(count, trace));
                  }
               }
            } else {
               s = br.readLine();
            }
         }

         dumpSites((Site[])sites.toArray(new Site[sites.size()]), traces);
         dumpSamples((Sample[])samples.toArray(new Sample[samples.size()]), traces);
      } finally {
         try {
            br.close();
         } catch (IOException var15) {
         }

      }

   }

   private static void dumpSites(Site[] ss, Map traces) {
      Arrays.sort(ss, new Comparator() {
         public int compare(@Nullable Object o1, @Nullable Object o2) {
            assert o1 != null;

            assert o2 != null;

            return ((Site)o2).allocatedBytes - ((Site)o1).allocatedBytes;
         }
      });
      int totalAllocatedBytes = 0;
      int totalAllocatedObjects = 0;

      for(Site site : ss) {
         totalAllocatedBytes += site.allocatedBytes;
         totalAllocatedObjects += site.allocatedObjects;
      }

      System.out.println("          percent          alloc'ed");
      System.out.println("rank   self  accum      bytes  objects  class name");
      System.out.println("Total:              " + totalAllocatedBytes + "  " + totalAllocatedObjects);
      double accumulatedPercentage = (double)0.0F;
      MessageFormat mf = new MessageFormat("{0,number,00000} {1,number,00.00}% {2,number,00.00}% {3,number,000000000} {4,number,000000000} {5}");

      for(int i = 0; i < ss.length; ++i) {
         Site site = ss[i];
         double selfPercentage = (double)100.0F * ((double)site.allocatedBytes / (double)totalAllocatedBytes);
         accumulatedPercentage += selfPercentage;
         System.out.println(mf.format(new Object[]{new Integer(i + 1), new Double(selfPercentage), new Double(accumulatedPercentage), new Integer(site.allocatedBytes), new Integer(site.allocatedObjects), site.className}, new StringBuffer(), new FieldPosition(0)));
         String[] stackFrames = (String[])traces.get(new Integer(site.traceNumber));
         if (stackFrames != null) {
            for(String stackFrame : stackFrames) {
               System.out.println("                           " + stackFrame);
            }
         }
      }

   }

   private static void dumpSamples(Sample[] ss, Map traces) {
      int totalCount = 0;

      for(Sample s : ss) {
         totalCount += s.count;
      }

      System.out.println("          percent");
      System.out.println("rank   self  accum      count");
      System.out.println("Total:              " + totalCount);
      double accumulatedPercentage = (double)0.0F;
      MessageFormat mf = new MessageFormat("{0,number,00000} {1,number,00.00}% {2,number,00.00}% {3,number,000000000}");

      for(int i = 0; i < ss.length; ++i) {
         Sample sample = ss[i];
         double selfPercentage = (double)100.0F * ((double)sample.count / (double)totalCount);
         accumulatedPercentage += selfPercentage;
         System.out.println(mf.format(new Object[]{new Integer(i + 1), new Double(selfPercentage), new Double(accumulatedPercentage), new Integer(sample.count)}, new StringBuffer(), new FieldPosition(0)));
         String[] stackFrames = (String[])traces.get(new Integer(sample.traceNumber));
         if (stackFrames != null) {
            for(String stackFrame : stackFrames) {
               System.out.println("                           " + stackFrame);
            }
         }
      }

   }

   private static class Site {
      public final int allocatedBytes;
      public final int allocatedObjects;
      public final int traceNumber;
      public final String className;

      Site(int allocatedBytes, int allocatedObjects, int traceNumber, String className) {
         this.allocatedBytes = allocatedBytes;
         this.allocatedObjects = allocatedObjects;
         this.traceNumber = traceNumber;
         this.className = className;
      }
   }

   private static class Sample {
      public final int count;
      public final int traceNumber;

      Sample(int count, int traceNumber) {
         this.count = count;
         this.traceNumber = traceNumber;
      }
   }
}
