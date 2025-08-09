package py4j.reflection;

public class RootClassLoadingStrategy implements ClassLoadingStrategy {
   public Class classForName(String className) throws ClassNotFoundException {
      return Class.forName(className);
   }

   public ClassLoader getClassLoader() {
      return RootClassLoadingStrategy.class.getClassLoader();
   }
}
