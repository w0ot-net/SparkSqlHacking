package javolution.testing;

import java.util.List;
import javolution.util.FastTable;

public abstract class TestSuite {
   FastTable _tests = new FastTable();

   protected TestSuite() {
   }

   public String getName() {
      return this.getClass().getName();
   }

   protected TestCase addTest(TestCase testCase) {
      this._tests.add(testCase);
      return testCase;
   }

   public void setUp() {
   }

   public void tearDown() {
   }

   public List tests() {
      return this._tests;
   }

   public boolean isParallelizable() {
      return true;
   }

   public String toString() {
      return this.getName();
   }
}
