package py4j.reflection;

public class CurrentThreadClassLoadingStrategy implements ClassLoadingStrategy {
   public Class classForName(String className) throws ClassNotFoundException {
      return Class.forName(className, true, this.getClassLoader());
   }

   public ClassLoader getClassLoader() {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      return classLoader;
   }
}
