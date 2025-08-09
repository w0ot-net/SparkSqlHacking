package py4j.reflection;

public class ReflectionUtil {
   private static ClassLoadingStrategy classLoadingStrategy = new CurrentThreadClassLoadingStrategy();

   public static ClassLoadingStrategy getClassLoadingStrategy() {
      return classLoadingStrategy;
   }

   public static void setClassLoadingStrategy(ClassLoadingStrategy classLoadingStrategy) {
      ReflectionUtil.classLoadingStrategy = classLoadingStrategy;
   }

   public static Class classForName(String className) throws ClassNotFoundException {
      return classLoadingStrategy.classForName(className);
   }

   public static ClassLoader getClassLoader() {
      return classLoadingStrategy.getClassLoader();
   }
}
