package javolution.testing;

import javolution.context.Context;
import javolution.context.LogContext;
import javolution.context.ObjectFactory;
import javolution.lang.Configurable;
import javolution.lang.MathLib;
import javolution.text.Text;
import javolution.text.TextBuilder;
import javolution.util.FastTable;

public abstract class TestContext extends LogContext {
   public static final Configurable DEFAULT = new Configurable(Default.class) {
   };
   public static final Class CONSOLE = Console.class;
   public static final Class REGRESSION = Regression.class;

   public static void enter() {
      Context.enter((Class)DEFAULT.get());
   }

   public static void exit() {
      Context.exit(TestContext.class);
   }

   public static void run(TestSuite testSuite) throws Exception {
      TestContext testContext = (TestContext)LogContext.getCurrentLogContext();
      testContext.doRun(testSuite);
   }

   public static void run(TestCase testCase) throws Exception {
      TestContext testContext = (TestContext)LogContext.getCurrentLogContext();
      testContext.doRun(testCase);
   }

   public static boolean assertEquals(Object expected, Object actual, CharSequence message) {
      boolean ok = expected == null && actual == null || expected != null && expected.equals(actual);
      CharSequence var5 = !ok && message == null ? Text.valueOf(expected).plus(" expected but found ").plus(actual) : message;
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      return ctx.doAssert(ok, (CharSequence)var5);
   }

   public static boolean assertEquals(Object expected, Object actual) {
      return assertEquals(expected, actual, (CharSequence)null);
   }

   public static boolean assertSame(Object expected, Object actual, CharSequence message) {
      boolean ok = expected == actual;
      CharSequence var5 = !ok && message == null ? Text.valueOf(expected).plus(" expected but found a different instance ").plus(actual) : message;
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      return ctx.doAssert(ok, (CharSequence)var5);
   }

   public static boolean assertSame(Object expected, Object actual) {
      return assertSame(expected, actual, (CharSequence)null);
   }

   public static boolean assertTrue(boolean actual, CharSequence message) {
      return assertEquals(Boolean.TRUE, actual ? Boolean.TRUE : Boolean.FALSE, message);
   }

   public static boolean assertTrue(boolean actual) {
      return assertTrue(actual, (CharSequence)null);
   }

   public static boolean assertFalse(boolean actual, CharSequence message) {
      return assertEquals(Boolean.FALSE, actual ? Boolean.TRUE : Boolean.FALSE, message);
   }

   public static boolean assertFalse(boolean actual) {
      return assertFalse(actual, (CharSequence)null);
   }

   public static boolean assertNull(Object actual, CharSequence message) {
      return assertEquals((Object)null, actual, message);
   }

   public static boolean assertNull(Object actual) {
      return assertNull(actual, (CharSequence)null);
   }

   public static boolean assertNotNull(Object actual, CharSequence message) {
      boolean ok = actual != null;
      CharSequence var4 = !ok && message == null ? Text.valueOf((Object)"Not null expected but found null") : message;
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      return ctx.doAssert(ok, (CharSequence)var4);
   }

   public static boolean assertNotNull(Object actual) {
      return assertNotNull(actual, (CharSequence)null);
   }

   public static boolean assertEquals(int expected, int actual, CharSequence message) {
      boolean ok = expected == actual;
      CharSequence var5 = !ok && message == null ? Text.valueOf((Object)(expected + " expected but found " + actual)) : message;
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      return ctx.doAssert(ok, (CharSequence)var5);
   }

   public static boolean assertEquals(int expected, int actual) {
      return assertEquals(expected, actual, (CharSequence)null);
   }

   public static boolean assertEquals(long expected, long actual, CharSequence message) {
      boolean ok = expected == actual;
      CharSequence var7 = !ok && message == null ? Text.valueOf((Object)(expected + " expected but found " + actual)) : message;
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      return ctx.doAssert(ok, (CharSequence)var7);
   }

   public static boolean assertEquals(long expected, long actual) {
      return assertEquals(expected, actual, (CharSequence)null);
   }

   /** @deprecated */
   public static boolean assertEquals(double expected, double actual, CharSequence message) {
      boolean ok = expected == actual || Double.isNaN(expected) && Double.isNaN(actual);
      CharSequence var7 = !ok && message == null ? Text.valueOf((Object)(expected + " expected but found " + actual)) : message;
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      return ctx.doAssert(ok, (CharSequence)var7);
   }

   /** @deprecated */
   public static boolean assertEquals(double expected, double actual) {
      return assertEquals(expected, actual, (CharSequence)null);
   }

   public static boolean assertEquals(double expected, double actual, double delta, CharSequence message) {
      boolean ok = expected == actual || Double.isNaN(expected) && Double.isNaN(actual);
      CharSequence var9 = !ok && message == null ? Text.valueOf((Object)(expected + " expected but found " + actual)) : message;
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      return ctx.doAssert(ok, (CharSequence)var9);
   }

   public static boolean assertEquals(double expected, double actual, double delta) {
      return assertEquals(expected, actual, delta, (CharSequence)null);
   }

   public static boolean assertArrayEquals(Object[] expected, Object[] actual, CharSequence message) {
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      if (expected == actual) {
         return ctx.doAssert(true, message);
      } else if (expected == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Null array expected but found actual array not null")));
      } else if (actual == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Not null array expected but found actual array null")));
      } else if (expected.length != actual.length) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array of size " + expected.length + " expected but found array of actual size " + actual.length))));
      } else {
         for(int i = 0; i < expected.length; ++i) {
            Object e = expected[i];
            Object a = actual[i];
            if (e != null && !e.equals(a) || e != a) {
               return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array element at " + i + ", expected " + e + " but found " + a))));
            }
         }

         return ctx.doAssert(true, message);
      }
   }

   public static boolean assertArrayEquals(Object[] expected, Object[] actual) {
      return assertArrayEquals((Object[])expected, (Object[])actual, (CharSequence)null);
   }

   public static boolean assertArrayEquals(boolean[] expected, boolean[] actual, CharSequence message) {
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      if (expected == actual) {
         return ctx.doAssert(true, message);
      } else if (expected == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Null array expected but found actual array not null")));
      } else if (actual == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Not null array expected but found actual array null")));
      } else if (expected.length != actual.length) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array of size " + expected.length + " expected but found array of actual size " + actual.length))));
      } else {
         for(int i = 0; i < expected.length; ++i) {
            boolean e = expected[i];
            boolean a = actual[i];
            if (e != a) {
               return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array element at " + i + ", expected " + e + " but found " + a))));
            }
         }

         return ctx.doAssert(true, message);
      }
   }

   public static boolean assertArrayEquals(boolean[] expected, boolean[] actual) {
      return assertArrayEquals((boolean[])expected, (boolean[])actual, (CharSequence)null);
   }

   public static boolean assertArrayEquals(int[] expected, int[] actual, CharSequence message) {
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      if (expected == actual) {
         return ctx.doAssert(true, message);
      } else if (expected == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Null array expected but found actual array not null")));
      } else if (actual == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Not null array expected but found actual array null")));
      } else if (expected.length != actual.length) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array of size " + expected.length + " expected but found array of actual size " + actual.length))));
      } else {
         for(int i = 0; i < expected.length; ++i) {
            int e = expected[i];
            int a = actual[i];
            if (e != a) {
               return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array element at " + i + ", expected " + e + " but found " + a))));
            }
         }

         return ctx.doAssert(true, message);
      }
   }

   public static boolean assertArrayEquals(int[] expected, int[] actual) {
      return assertArrayEquals((int[])expected, (int[])actual, (CharSequence)null);
   }

   public static boolean assertArrayEquals(long[] expected, long[] actual, CharSequence message) {
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      if (expected == actual) {
         return ctx.doAssert(true, message);
      } else if (expected == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Null array expected but found actual array not null")));
      } else if (actual == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Not null array expected but found actual array null")));
      } else if (expected.length != actual.length) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array of size " + expected.length + " expected but found array of actual size " + actual.length))));
      } else {
         for(int i = 0; i < expected.length; ++i) {
            long e = expected[i];
            long a = actual[i];
            if (e != a) {
               return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array element at " + i + ", expected " + e + " but found " + a))));
            }
         }

         return ctx.doAssert(true, message);
      }
   }

   public static boolean assertArrayEquals(long[] expected, long[] actual) {
      return assertArrayEquals((long[])expected, (long[])actual, (CharSequence)null);
   }

   public static boolean assertArrayEquals(double[] expected, double[] actual, double delta, CharSequence message) {
      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      if (expected == actual) {
         return ctx.doAssert(true, message);
      } else if (expected == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Null array expected but found actual array not null")));
      } else if (actual == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Not null array expected but found actual array null")));
      } else if (expected.length != actual.length) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array of size " + expected.length + " expected but found array of actual size " + actual.length))));
      } else {
         for(int i = 0; i < expected.length; ++i) {
            double e = expected[i];
            double a = actual[i];
            if (MathLib.abs(e - a) > delta) {
               return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)("Array element at " + i + ", expected " + e + " but found " + a))));
            }
         }

         return ctx.doAssert(true, message);
      }
   }

   public static boolean assertArrayEquals(double[] expected, double[] actual, double delta) {
      return assertArrayEquals(expected, actual, delta, (CharSequence)null);
   }

   public static boolean assertException(Class exceptionClass, Runnable logic, CharSequence message) {
      Throwable exception = null;

      try {
         logic.run();
      } catch (Throwable e) {
         exception = e;
      }

      TestContext ctx = (TestContext)LogContext.getCurrentLogContext();
      if (exception == null) {
         return ctx.doAssert(false, (CharSequence)(message != null ? message : Text.valueOf((Object)"Expected exception instance of ").plus(exceptionClass.getName()).plus(" but no exception has been raised")));
      } else {
         boolean ok = exceptionClass.isInstance(exception);
         CharSequence var7 = !ok && message == null ? Text.valueOf((Object)"Expected instance of ").plus(exceptionClass.getName()).plus(" but actual exception is instance of ").plus(exception.getClass().getName()) : message;
         return ctx.doAssert(ok, (CharSequence)var7);
      }
   }

   public static boolean assertException(Class exceptionClass, Runnable logic) {
      return assertException(exceptionClass, logic, (CharSequence)null);
   }

   public static boolean fail(CharSequence message) {
      return assertTrue(false, message);
   }

   public static boolean fail() {
      return fail((CharSequence)null);
   }

   protected void doRun(TestSuite testSuite) throws Exception {
      testSuite.setUp();

      try {
         FastTable tests = testSuite._tests;

         for(int i = 0; i < tests.size(); ++i) {
            this.doRun((TestCase)tests.get(i));
         }
      } finally {
         testSuite.tearDown();
      }

   }

   protected void doRun(TestCase testCase) throws Exception {
      if (!testCase.isIgnored()) {
         testCase.setUp();

         try {
            testCase.execute();
            testCase.validate();
         } finally {
            testCase.tearDown();
         }

      }
   }

   protected boolean doAssert(boolean value, CharSequence message) {
      if (value) {
         return true;
      } else {
         Throwable error = new Error();
         StackTraceElement[] trace = error.getStackTrace();
         TextBuilder tmp = TextBuilder.newInstance();

         try {
            tmp.append(message);

            for(int i = 1; i < trace.length; ++i) {
               if (trace[i].getMethodName().equals("validate")) {
                  tmp.append("\n\tat ");
                  tmp.append((Object)trace[i]);
                  break;
               }
            }

            this.logError((Throwable)null, tmp);
         } finally {
            TextBuilder.recycle(tmp);
         }

         return false;
      }
   }

   static {
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Default();
         }
      }, Default.class);
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Console();
         }
      }, CONSOLE);
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Regression();
         }
      }, Regression.class);
   }

   private static class Default extends TestContext {
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

      protected void doRun(TestCase testCase) {
         if (testCase.isIgnored()) {
            this.logWarning(Text.valueOf((Object)"Ignore ").plus(testCase.getName()));
            ++this._ignoredCount;
         } else {
            this.logMessage("test", Text.valueOf((Object)testCase.getName()));
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

         }
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
   }

   private static class Console extends Default {
      private Console() {
      }
   }

   private static class Regression extends TestContext {
      private Regression() {
      }

      protected boolean doAssert(boolean value, CharSequence message) {
         if (!value) {
            throw new AssertionException(message.toString());
         } else {
            return value;
         }
      }

      protected boolean isLogged(String category) {
         return false;
      }

      protected void logMessage(String category, CharSequence message) {
      }
   }
}
