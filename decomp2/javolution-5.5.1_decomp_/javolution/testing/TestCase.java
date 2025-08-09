package javolution.testing;

public abstract class TestCase {
   boolean _isIgnored;

   public String getName() {
      return this.getClass().getName();
   }

   protected TestCase() {
   }

   public TestCase ignore(boolean isIgnored) {
      this._isIgnored = isIgnored;
      return this;
   }

   public boolean isIgnored() {
      return this._isIgnored;
   }

   public void setUp() {
   }

   public abstract void execute() throws Exception;

   public int count() {
      return 1;
   }

   public abstract void validate() throws Exception;

   public void tearDown() {
   }

   public String toString() {
      return this.getName();
   }
}
