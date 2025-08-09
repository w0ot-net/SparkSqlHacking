package javolution.testing;

import javolution.context.Context;
import javolution.context.LogContext;
import javolution.context.ObjectFactory;
import javolution.lang.Configurable;
import javolution.lang.MathLib;
import javolution.text.Text;
import javolution.text.TextBuilder;

public abstract class TimeContext extends TestContext {
   public static final Class REGRESSION = Regression.class;
   public static final Configurable TEST_DURATION_MS = new Configurable(new Integer(1000)) {
   };
   public static final Configurable DEFAULT = new Configurable(Default.class) {
   };
   private long _minimumPs;
   private long _averagePs;
   private long _maximumPs;

   public static void enter() {
      Context.enter((Class)DEFAULT.get());
   }

   public static void exit() {
      Context.exit(TimeContext.class);
   }

   public static long getMinimumTime(String unit) {
      LogContext ctx = LogContext.getCurrentLogContext();
      return ctx instanceof TimeContext ? picosecondTo(unit, ((TimeContext)ctx).getMinimumTimeInPicoSeconds()) : -1L;
   }

   public static long getAverageTime(String unit) {
      LogContext ctx = LogContext.getCurrentLogContext();
      return ctx instanceof TimeContext ? picosecondTo(unit, ((TimeContext)ctx).getAverageTimeInPicoSeconds()) : -1L;
   }

   public static long getMaximumTime(String unit) {
      LogContext ctx = LogContext.getCurrentLogContext();
      return ctx instanceof TimeContext ? picosecondTo(unit, ((TimeContext)ctx).getMaximumTimeInPicoSeconds()) : -1L;
   }

   private static long picosecondTo(String unit, long picoseconds) {
      if (unit.equals("ps")) {
         return picoseconds;
      } else if (unit.equals("ns")) {
         return picoseconds / 1000L;
      } else if (unit.equals("us")) {
         return picoseconds / 1000000L;
      } else if (unit.equals("ms")) {
         return picoseconds / 1000000000L;
      } else if (unit.equals("s")) {
         return picoseconds / 1000000000000L;
      } else {
         throw new IllegalArgumentException("Unit " + unit + " not recognized");
      }
   }

   public long getMinimumTimeInPicoSeconds() {
      return this._minimumPs;
   }

   public long getAverageTimeInPicoSeconds() {
      return this._averagePs;
   }

   public long getMaximumTimeInPicoSeconds() {
      return this._maximumPs;
   }

   protected void doRun(TestCase testCase) throws Exception {
      if (!testCase.isIgnored()) {
         System.gc();

         try {
            Thread.sleep(200L);
         } catch (InterruptedException var18) {
         }

         this._minimumPs = Long.MAX_VALUE;
         this._maximumPs = 0L;
         this._averagePs = 0L;
         long totalCount = 0L;
         long totalDuration = 0L;
         long maximumDurationPs = (long)(Integer)TEST_DURATION_MS.get() * 1000000000L;

         while(true) {
            testCase.setUp();

            try {
               long start = nanoTime();
               testCase.execute();
               long duration = (nanoTime() - start) * 1000L;
               int count = testCase.count();
               totalCount += (long)count;
               totalDuration += duration;
               long singleExecutionDuration = duration / (long)count;
               if (singleExecutionDuration < this._minimumPs) {
                  this._minimumPs = singleExecutionDuration;
               }

               if (singleExecutionDuration > this._maximumPs) {
                  this._maximumPs = singleExecutionDuration;
               }

               if (totalDuration >= maximumDurationPs) {
                  this._averagePs = totalDuration / totalCount;
                  testCase.validate();
                  return;
               }
            } finally {
               testCase.tearDown();
            }
         }
      }
   }

   private static long nanoTime() {
      return System.nanoTime();
   }

   static {
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Default();
         }
      }, Default.class);
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Regression();
         }
      }, Regression.class);
   }

   private static final class Default extends TimeContext {
      private int _passedCount;
      private int _failedCount;
      private int _ignoredCount;
      private boolean _isPassed;

      private Default() {
      }

      protected void enterAction() {
         this._passedCount = this._failedCount = this._ignoredCount = 0;
      }

      protected void exitAction() {
         this.logMessage("test", Text.valueOf((Object)"---------------------------------------------------"));
         this.logMessage("test", Text.valueOf((Object)("SUMMARY - PASSED: " + this._passedCount + ", FAILED: " + this._failedCount + ", IGNORED: " + this._ignoredCount)));
      }

      protected void doRun(TestSuite testSuite) throws Exception {
         this.logMessage("test", Text.valueOf((Object)"---------------------------------------------------"));
         this.logMessage("test", Text.valueOf((Object)"Executes Test Suite: ").plus(testSuite.getName()));
         this.logMessage("test", Text.valueOf((Object)""));
         super.doRun(testSuite);
      }

      protected boolean doAssert(boolean value, CharSequence message) {
         if (!value) {
            this._isPassed = false;
            return super.doAssert(value, message);
         } else {
            return value;
         }
      }

      protected void logMessage(String category, CharSequence message) {
         if (category.equals("error")) {
            System.err.print("[");
            System.err.print(category);
            System.err.print("] ");
            System.err.println(message);
            System.err.flush();
         } else {
            System.out.print("[");
            System.out.print(category);
            System.out.print("] ");
            System.out.println(message);
            System.out.flush();
         }

      }

      protected void doRun(TestCase testCase) {
         if (testCase.isIgnored()) {
            this.logWarning(Text.valueOf((Object)"Ignore ").plus(testCase.getName()));
            ++this._ignoredCount;
         } else {
            this._isPassed = true;

            try {
               super.doRun(testCase);
            } catch (Throwable error) {
               this._isPassed = false;
               this.logError(error, (CharSequence)null);
            } finally {
               if (this._isPassed) {
                  ++this._passedCount;
               } else {
                  ++this._failedCount;
               }

            }

            TextBuilder tmp = TextBuilder.newInstance();

            try {
               tmp.append(testCase.getName());
               tmp.setLength(40, ' ');
               tmp.append(" - Average: ");
               appendTime(this.getAverageTimeInPicoSeconds(), tmp);
               tmp.append(", Minimum: ");
               appendTime(this.getMinimumTimeInPicoSeconds(), tmp);
               tmp.append(", Maximum: ");
               appendTime(this.getMaximumTimeInPicoSeconds(), tmp);
               this.logMessage("time", tmp);
            } finally {
               TextBuilder.recycle(tmp);
            }

         }
      }

      private static TextBuilder appendTime(long picoseconds, TextBuilder tb) {
         long divisor;
         String unit;
         if (picoseconds > 1000000000000L) {
            unit = " s";
            divisor = 1000000000000L;
         } else if (picoseconds > 1000000000L) {
            unit = " ms";
            divisor = 1000000000L;
         } else if (picoseconds > 1000000L) {
            unit = " us";
            divisor = 1000000L;
         } else if (picoseconds > 1000L) {
            unit = " ns";
            divisor = 1000L;
         } else {
            unit = " ps";
            divisor = 1L;
         }

         long value = picoseconds / divisor;
         tb.append(value);
         int fracDigits = 3 - MathLib.digitLength(value);
         if (fracDigits > 0) {
            tb.append(".");
         }

         int i = 0;

         for(int j = 10; i < fracDigits; j *= 10) {
            tb.append(picoseconds * (long)j / divisor % 10L);
            ++i;
         }

         return tb.append(unit);
      }
   }

   private static final class Regression extends TimeContext {
      private Regression() {
      }

      protected boolean doAssert(boolean value, CharSequence message) {
         if (!value) {
            throw new AssertionException(message.toString());
         } else {
            return value;
         }
      }

      protected void logMessage(String category, CharSequence message) {
      }

      public boolean isLogged(String category) {
         return false;
      }
   }
}
