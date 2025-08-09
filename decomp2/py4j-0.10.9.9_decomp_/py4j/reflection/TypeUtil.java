package py4j.reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import py4j.JVMView;
import py4j.Py4JException;

public class TypeUtil {
   private static Set primitiveTypes = new HashSet();
   private static Map primitiveClasses;
   public static final int DISTANCE_FACTOR = 100;

   public static int computeCharacterConversion(Class parent, Class child, List converters) {
      int cost = -1;
      if (isCharacter(child)) {
         cost = 0;
         converters.add(TypeConverter.NO_CONVERTER);
      } else if (CharSequence.class.isAssignableFrom(child)) {
         cost = 1;
         converters.add(TypeConverter.CHAR_CONVERTER);
      }

      return cost;
   }

   public static int computeDistance(Class parent, Class child) {
      int distance = -1;
      if (parent.equals(child)) {
         distance = 0;
      }

      if (distance == -1) {
         distance = computeSuperDistance(parent, child);
      }

      if (distance == -1) {
         distance = computeInterfaceDistance(parent, child, new HashSet(), Arrays.asList(child.getInterfaces()));
      }

      if (distance != -1) {
         distance *= 100;
      }

      return distance;
   }

   private static int computeInterfaceDistance(Class parent, Class child, Set visitedInterfaces, List interfacesToVisit) {
      int distance = -1;
      List<Class<?>> nextInterfaces = new ArrayList();

      for(Class clazz : interfacesToVisit) {
         if (parent.equals(clazz)) {
            distance = 1;
            break;
         }

         visitedInterfaces.add(clazz.getName());
         getNextInterfaces(clazz, nextInterfaces, visitedInterfaces);
      }

      if (distance == -1) {
         Class<?> grandChild = null;
         if (child != null) {
            grandChild = child.getSuperclass();
            getNextInterfaces(grandChild, nextInterfaces, visitedInterfaces);
         }

         if (nextInterfaces.size() > 0 || grandChild != null) {
            int newDistance = computeInterfaceDistance(parent, grandChild, visitedInterfaces, nextInterfaces);
            if (newDistance != -1) {
               distance = newDistance + 1;
            }
         }
      }

      return distance;
   }

   public static int computeNumericConversion(Class parent, Class child, List converters) {
      int cost = -1;
      if (isLong(parent) && !isFloat(child) && !isDouble(child)) {
         cost = getCost(parent, child);
         if (isLong(child)) {
            converters.add(TypeConverter.NO_CONVERTER);
         } else {
            converters.add(TypeConverter.LONG_CONVERTER);
         }
      } else if (!isInteger(parent) || !isInteger(child) && !isShort(child) && !isByte(child)) {
         if (isShort(parent)) {
            if (!isShort(child) && !isByte(child)) {
               if (isInteger(child)) {
                  cost = 1;
                  converters.add(TypeConverter.SHORT_CONVERTER);
               }
            } else {
               cost = getCost(parent, child);
               converters.add(TypeConverter.NO_CONVERTER);
            }
         } else if (isByte(parent)) {
            if (isByte(child)) {
               cost = 0;
               converters.add(TypeConverter.NO_CONVERTER);
            } else if (isInteger(child)) {
               cost = 2;
               converters.add(TypeConverter.BYTE_CONVERTER);
            }
         } else if (isDouble(parent)) {
            if (isDouble(child)) {
               cost = 0;
               converters.add(TypeConverter.NO_CONVERTER);
            } else if (isFloat(child)) {
               cost = 1;
               converters.add(TypeConverter.NO_CONVERTER);
            }
         } else if (isFloat(parent)) {
            if (isFloat(child)) {
               cost = 0;
               converters.add(TypeConverter.NO_CONVERTER);
            } else if (isDouble(child)) {
               cost = 1;
               converters.add(TypeConverter.FLOAT_CONVERTER);
            }
         }
      } else {
         cost = getCost(parent, child);
         converters.add(TypeConverter.NO_CONVERTER);
      }

      return cost;
   }

   private static int computeSuperDistance(Class parent, Class child) {
      Class<?> superChild = child.getSuperclass();
      if (superChild == null) {
         return -1;
      } else if (superChild.equals(parent)) {
         return 1;
      } else {
         int distance = computeSuperDistance(parent, superChild);
         return distance != -1 ? distance + 1 : distance;
      }
   }

   public static Class forName(String fqn) throws ClassNotFoundException {
      Class<?> clazz = (Class)primitiveClasses.get(fqn);
      if (clazz == null) {
         clazz = ReflectionUtil.classForName(fqn);
      }

      return clazz;
   }

   public static Class forName(String fqn, JVMView view) throws ClassNotFoundException {
      Class<?> clazz = (Class)primitiveClasses.get(fqn);
      if (clazz == null) {
         if (fqn.indexOf(46) < 0) {
            clazz = getClass(fqn, view);
         } else {
            clazz = ReflectionUtil.classForName(fqn);
         }
      }

      return clazz;
   }

   public static Class getClass(String simpleName, JVMView view) throws ClassNotFoundException {
      Class<?> clazz = null;

      try {
         clazz = ReflectionUtil.classForName(simpleName);
      } catch (Exception var10) {
         Map<String, String> singleImportsMap = view.getSingleImportsMap();
         String newFQN = (String)singleImportsMap.get(simpleName);
         if (newFQN != null) {
            clazz = ReflectionUtil.classForName(newFQN);
         } else {
            for(String starImport : view.getStarImports()) {
               try {
                  clazz = ReflectionUtil.classForName(starImport + "." + simpleName);
                  break;
               } catch (Exception var9) {
               }
            }
         }
      }

      if (clazz == null) {
         throw new ClassNotFoundException(simpleName + " not found.");
      } else {
         return clazz;
      }
   }

   public static int getCost(Class parent, Class child) {
      return getPoint(parent) - getPoint(child);
   }

   public static String getName(String name, boolean shortName) {
      if (!shortName) {
         return name;
      } else {
         int index = name.lastIndexOf(".");
         return index >= 0 && index < name.length() + 1 ? name.substring(index + 1) : name;
      }
   }

   public static List getNames(Class[] classes) {
      List<String> names = new ArrayList();

      for(int i = 0; i < classes.length; ++i) {
         names.add(classes[i].getCanonicalName());
      }

      return Collections.unmodifiableList(names);
   }

   private static void getNextInterfaces(Class clazz, List nextInterfaces, Set visitedInterfaces) {
      if (clazz != null) {
         for(Class nextClazz : clazz.getInterfaces()) {
            if (!visitedInterfaces.contains(nextClazz.getName())) {
               nextInterfaces.add(nextClazz);
            }
         }
      }

   }

   public static String getPackage(String name) {
      int index = name.lastIndexOf(".");
      return index < 0 ? name : name.substring(0, index);
   }

   public static int getPoint(Class clazz) {
      int point = -1;
      if (isByte(clazz)) {
         point = 0;
      } else if (isShort(clazz)) {
         point = 1;
      } else if (isInteger(clazz)) {
         point = 2;
      } else if (isLong(clazz)) {
         point = 3;
      }

      return point;
   }

   public static boolean isBoolean(Class clazz) {
      return clazz.equals(Boolean.class) || clazz.equals(Boolean.TYPE);
   }

   public static boolean isByte(Class clazz) {
      return clazz.equals(Byte.class) || clazz.equals(Byte.TYPE);
   }

   public static boolean isCharacter(Class clazz) {
      return clazz.equals(Character.class) || clazz.equals(Character.TYPE);
   }

   public static boolean isDouble(Class clazz) {
      return clazz.equals(Double.class) || clazz.equals(Double.TYPE);
   }

   public static boolean isFloat(Class clazz) {
      return clazz.equals(Float.class) || clazz.equals(Float.TYPE);
   }

   public static boolean isInteger(Class clazz) {
      return clazz.equals(Integer.class) || clazz.equals(Integer.TYPE);
   }

   public static boolean isLong(Class clazz) {
      return clazz.equals(Long.class) || clazz.equals(Long.TYPE);
   }

   public static boolean isNumeric(Class clazz) {
      return primitiveTypes.contains(clazz.getName());
   }

   public static boolean isShort(Class clazz) {
      return clazz.equals(Short.class) || clazz.equals(Short.TYPE);
   }

   public static boolean isInstanceOf(Class clazz, Object object) {
      return clazz.isInstance(object);
   }

   public static boolean isInstanceOf(String classFQN, Object object) {
      Class<?> clazz = null;

      try {
         clazz = ReflectionUtil.classForName(classFQN);
      } catch (Exception e) {
         throw new Py4JException(e);
      }

      return isInstanceOf(clazz, object);
   }

   static {
      primitiveTypes.add(Long.TYPE.getName());
      primitiveTypes.add(Integer.TYPE.getName());
      primitiveTypes.add(Short.TYPE.getName());
      primitiveTypes.add(Byte.TYPE.getName());
      primitiveTypes.add(Double.TYPE.getName());
      primitiveTypes.add(Float.TYPE.getName());
      primitiveTypes.add(Long.class.getName());
      primitiveTypes.add(Integer.class.getName());
      primitiveTypes.add(Short.class.getName());
      primitiveTypes.add(Byte.class.getName());
      primitiveTypes.add(Double.class.getName());
      primitiveTypes.add(Float.class.getName());
      primitiveClasses = new HashMap();
      primitiveClasses.put("long", Long.TYPE);
      primitiveClasses.put("int", Integer.TYPE);
      primitiveClasses.put("short", Short.TYPE);
      primitiveClasses.put("byte", Byte.TYPE);
      primitiveClasses.put("double", Double.TYPE);
      primitiveClasses.put("float", Float.TYPE);
      primitiveClasses.put("boolean", Boolean.TYPE);
      primitiveClasses.put("char", Character.TYPE);
   }
}
