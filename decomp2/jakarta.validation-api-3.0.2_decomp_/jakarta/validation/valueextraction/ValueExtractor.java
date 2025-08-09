package jakarta.validation.valueextraction;

public interface ValueExtractor {
   void extractValues(Object var1, ValueReceiver var2);

   public interface ValueReceiver {
      void value(String var1, Object var2);

      void iterableValue(String var1, Object var2);

      void indexedValue(String var1, int var2, Object var3);

      void keyedValue(String var1, Object var2, Object var3);
   }
}
