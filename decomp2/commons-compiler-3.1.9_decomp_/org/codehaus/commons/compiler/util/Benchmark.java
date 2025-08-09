package org.codehaus.commons.compiler.util;

import java.util.Stack;
import org.codehaus.commons.nullanalysis.Nullable;

public class Benchmark {
   private final Stack beginTimes = new Stack();
   private final boolean reportingEnabled;
   private final Reporter reporter;
   private static final String PAD = "                       ";

   public void begin() {
      this.beginTimes.push(System.currentTimeMillis());
   }

   public long end() {
      return System.currentTimeMillis() - (Long)this.beginTimes.pop();
   }

   public Benchmark(boolean reportingEnabled) {
      this.reportingEnabled = reportingEnabled;
      this.reporter = new Reporter() {
         public void report(String message) {
            System.out.println(message);
         }
      };
   }

   public Benchmark(boolean reportingEnabled, Reporter reporter) {
      this.reportingEnabled = reportingEnabled;
      this.reporter = reporter;
   }

   public void beginReporting() {
      if (this.reportingEnabled) {
         this.reportIndented("Beginning...");
         this.begin();
      }
   }

   public void beginReporting(String message) {
      if (this.reportingEnabled) {
         this.reportIndented(message + "...");
         this.begin();
      }
   }

   public void endReporting() {
      if (this.reportingEnabled) {
         this.reportIndented("... took " + this.end() + " ms");
      }
   }

   public void endReporting(String message) {
      if (this.reportingEnabled) {
         this.reportIndented("... took " + this.end() + " ms: " + message);
      }
   }

   public void report(String message) {
      if (this.reportingEnabled) {
         this.reportIndented(message);
      }
   }

   public void report(@Nullable String title, @Nullable Object o) {
      if (this.reportingEnabled) {
         String prefix = title == null ? "" : title + ": " + (title.length() < "                       ".length() ? "                       ".substring(title.length()) : "");
         if (o == null) {
            this.reportIndented(prefix + "(undefined)");
         } else if (o.getClass().isArray()) {
            Object[] oa = o;
            if (oa.length == 0) {
               this.reportIndented(prefix + "(empty)");
            } else if (oa.length == 1) {
               this.reportIndented(prefix + oa[0].toString());
            } else {
               this.reportIndented(title == null ? "Array:" : title + ':');
               this.begin();

               try {
                  for(Object o2 : oa) {
                     this.report((String)null, o2);
                  }
               } finally {
                  this.end();
               }
            }
         } else {
            this.reportIndented(prefix + o.toString());
         }

      }
   }

   private void reportIndented(String message) {
      StringBuilder sb = new StringBuilder();

      for(int i = this.beginTimes.size(); i > 0; --i) {
         sb.append("  ");
      }

      sb.append(message);
      this.reporter.report(sb.toString());
   }

   public interface Reporter {
      void report(String var1);
   }
}
