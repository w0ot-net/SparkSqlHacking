package py4j.reflection;

public interface ClassLoadingStrategy {
   Class classForName(String var1) throws ClassNotFoundException;

   ClassLoader getClassLoader();
}
