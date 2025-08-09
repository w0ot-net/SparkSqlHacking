package javax.realtime;

public class MemoryArea {
   static final MemoryArea DEFAULT = new MemoryArea();

   private MemoryArea() {
   }

   public static MemoryArea getMemoryArea(Object object) {
      return DEFAULT;
   }

   public void executeInArea(Runnable logic) {
      logic.run();
   }

   public Object newInstance(Class type) throws InstantiationException, IllegalAccessException {
      return type.newInstance();
   }
}
