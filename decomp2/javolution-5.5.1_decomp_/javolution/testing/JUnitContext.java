package javolution.testing;

import javolution.context.Context;
import javolution.lang.Reflection;
import javolution.text.Text;

public class JUnitContext extends TestContext {
   private static Reflection.Constructor JUNIT_ERROR_CONSTRUCTOR = Reflection.getInstance().getConstructor("junit.framework.AssertionFailedError(String)");

   public static void enter() {
      Context.enter(JUnitContext.class);
   }

   public static void exit() {
      Context.exit(JUnitContext.class);
   }

   protected void doRun(TestSuite testSuite) throws Exception {
      this.logMessage("test", Text.valueOf((Object)"---------------------------------------------------"));
      this.logMessage("test", Text.valueOf((Object)"Executes Test Suite: ").plus(testSuite.getName()));
      this.logMessage("test", Text.valueOf((Object)""));
      super.doRun(testSuite);
   }

   protected void doRun(TestCase testCase) throws Exception {
      if (testCase.isIgnored()) {
         this.logWarning(Text.valueOf((Object)"Ignore ").plus(testCase.getName()));
      } else {
         this.logMessage("test", Text.valueOf((Object)testCase.getName()));
         super.doRun(testCase);
      }
   }

   protected boolean doAssert(boolean value, CharSequence message) {
      if (!value) {
         super.doAssert(value, message);
         if (JUNIT_ERROR_CONSTRUCTOR != null) {
            RuntimeException junitError = (RuntimeException)JUNIT_ERROR_CONSTRUCTOR.newInstance((Object)message.toString());
            throw junitError;
         } else {
            throw new AssertionException(message.toString());
         }
      } else {
         return true;
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
