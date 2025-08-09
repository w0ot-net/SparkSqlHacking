package javassist.bytecode.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Type {
   private final CtClass clazz;
   private final boolean special;
   private static final Map prims = new IdentityHashMap();
   public static final Type DOUBLE;
   public static final Type BOOLEAN;
   public static final Type LONG;
   public static final Type CHAR;
   public static final Type BYTE;
   public static final Type SHORT;
   public static final Type INTEGER;
   public static final Type FLOAT;
   public static final Type VOID;
   public static final Type UNINIT;
   public static final Type RETURN_ADDRESS;
   public static final Type TOP;
   public static final Type BOGUS;
   public static final Type OBJECT;
   public static final Type SERIALIZABLE;
   public static final Type CLONEABLE;
   public static final Type THROWABLE;

   public static Type get(CtClass clazz) {
      Type type = (Type)prims.get(clazz);
      return type != null ? type : new Type(clazz);
   }

   private static Type lookupType(String name) {
      try {
         return new Type(ClassPool.getDefault().get(name));
      } catch (NotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   Type(CtClass clazz) {
      this(clazz, false);
   }

   private Type(CtClass clazz, boolean special) {
      this.clazz = clazz;
      this.special = special;
   }

   boolean popChanged() {
      return false;
   }

   public int getSize() {
      return this.clazz != CtClass.doubleType && this.clazz != CtClass.longType && this != TOP ? 1 : 2;
   }

   public CtClass getCtClass() {
      return this.clazz;
   }

   public boolean isReference() {
      return !this.special && (this.clazz == null || !this.clazz.isPrimitive());
   }

   public boolean isSpecial() {
      return this.special;
   }

   public boolean isArray() {
      return this.clazz != null && this.clazz.isArray();
   }

   public int getDimensions() {
      if (!this.isArray()) {
         return 0;
      } else {
         String name = this.clazz.getName();
         int pos = name.length() - 1;

         int count;
         for(count = 0; name.charAt(pos) == ']'; ++count) {
            pos -= 2;
         }

         return count;
      }
   }

   public Type getComponent() {
      if (this.clazz != null && this.clazz.isArray()) {
         CtClass component;
         try {
            component = this.clazz.getComponentType();
         } catch (NotFoundException e) {
            throw new RuntimeException(e);
         }

         Type type = (Type)prims.get(component);
         return type != null ? type : new Type(component);
      } else {
         return null;
      }
   }

   public boolean isAssignableFrom(Type type) {
      if (this == type) {
         return true;
      } else if ((type != UNINIT || !this.isReference()) && (this != UNINIT || !type.isReference())) {
         if (type instanceof MultiType) {
            return ((MultiType)type).isAssignableTo(this);
         } else if (type instanceof MultiArrayType) {
            return ((MultiArrayType)type).isAssignableTo(this);
         } else if (this.clazz != null && !this.clazz.isPrimitive()) {
            try {
               return type.clazz.subtypeOf(this.clazz);
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public Type merge(Type type) {
      if (type == this) {
         return this;
      } else if (type == null) {
         return this;
      } else if (type == UNINIT) {
         return this;
      } else if (this == UNINIT) {
         return type;
      } else if (type.isReference() && this.isReference()) {
         if (type instanceof MultiType) {
            return type.merge(this);
         } else if (type.isArray() && this.isArray()) {
            return this.mergeArray(type);
         } else {
            try {
               return this.mergeClasses(type);
            } catch (NotFoundException e) {
               throw new RuntimeException(e);
            }
         }
      } else {
         return BOGUS;
      }
   }

   Type getRootComponent(Type type) {
      while(type.isArray()) {
         type = type.getComponent();
      }

      return type;
   }

   private Type createArray(Type rootComponent, int dims) {
      if (rootComponent instanceof MultiType) {
         return new MultiArrayType((MultiType)rootComponent, dims);
      } else {
         String name = this.arrayName(rootComponent.clazz.getName(), dims);

         try {
            Type type = get(this.getClassPool(rootComponent).get(name));
            return type;
         } catch (NotFoundException e) {
            throw new RuntimeException(e);
         }
      }
   }

   String arrayName(String component, int dims) {
      int i = component.length();
      int size = i + dims * 2;
      char[] string = new char[size];
      component.getChars(0, i, string, 0);

      while(i < size) {
         string[i++] = '[';
         string[i++] = ']';
      }

      component = new String(string);
      return component;
   }

   private ClassPool getClassPool(Type rootComponent) {
      ClassPool pool = rootComponent.clazz.getClassPool();
      return pool != null ? pool : ClassPool.getDefault();
   }

   private Type mergeArray(Type type) {
      Type typeRoot = this.getRootComponent(type);
      Type thisRoot = this.getRootComponent(this);
      int typeDims = type.getDimensions();
      int thisDims = this.getDimensions();
      if (typeDims == thisDims) {
         Type mergedComponent = thisRoot.merge(typeRoot);
         return mergedComponent == BOGUS ? OBJECT : this.createArray(mergedComponent, thisDims);
      } else {
         Type targetRoot;
         int targetDims;
         if (typeDims < thisDims) {
            targetRoot = typeRoot;
            targetDims = typeDims;
         } else {
            targetRoot = thisRoot;
            targetDims = thisDims;
         }

         return !eq(CLONEABLE.clazz, targetRoot.clazz) && !eq(SERIALIZABLE.clazz, targetRoot.clazz) ? this.createArray(OBJECT, targetDims) : this.createArray(targetRoot, targetDims);
      }
   }

   private static CtClass findCommonSuperClass(CtClass one, CtClass two) throws NotFoundException {
      CtClass deep = one;
      CtClass shallow = two;
      CtClass backupDeep = one;

      while(true) {
         if (eq(deep, shallow) && deep.getSuperclass() != null) {
            return deep;
         }

         CtClass deepSuper = deep.getSuperclass();
         CtClass shallowSuper = shallow.getSuperclass();
         if (shallowSuper == null) {
            shallow = two;
            break;
         }

         if (deepSuper == null) {
            backupDeep = two;
            deep = shallow;
            shallow = one;
            break;
         }

         deep = deepSuper;
         shallow = shallowSuper;
      }

      while(true) {
         deep = deep.getSuperclass();
         if (deep == null) {
            for(deep = backupDeep; !eq(deep, shallow); shallow = shallow.getSuperclass()) {
               deep = deep.getSuperclass();
            }

            return deep;
         }

         backupDeep = backupDeep.getSuperclass();
      }
   }

   private Type mergeClasses(Type type) throws NotFoundException {
      CtClass superClass = findCommonSuperClass(this.clazz, type.clazz);
      if (superClass.getSuperclass() == null) {
         Map<String, CtClass> interfaces = this.findCommonInterfaces(type);
         if (interfaces.size() == 1) {
            return new Type((CtClass)interfaces.values().iterator().next());
         } else {
            return (Type)(interfaces.size() > 1 ? new MultiType(interfaces) : new Type(superClass));
         }
      } else {
         Map<String, CtClass> commonDeclared = this.findExclusiveDeclaredInterfaces(type, superClass);
         return (Type)(commonDeclared.size() > 0 ? new MultiType(commonDeclared, new Type(superClass)) : new Type(superClass));
      }
   }

   private Map findCommonInterfaces(Type type) {
      Map<String, CtClass> typeMap = this.getAllInterfaces(type.clazz, (Map)null);
      Map<String, CtClass> thisMap = this.getAllInterfaces(this.clazz, (Map)null);
      return this.findCommonInterfaces(typeMap, thisMap);
   }

   private Map findExclusiveDeclaredInterfaces(Type type, CtClass exclude) {
      Map<String, CtClass> typeMap = this.getDeclaredInterfaces(type.clazz, (Map)null);
      Map<String, CtClass> thisMap = this.getDeclaredInterfaces(this.clazz, (Map)null);
      Map<String, CtClass> excludeMap = this.getAllInterfaces(exclude, (Map)null);

      for(String intf : excludeMap.keySet()) {
         typeMap.remove(intf);
         thisMap.remove(intf);
      }

      return this.findCommonInterfaces(typeMap, thisMap);
   }

   Map findCommonInterfaces(Map typeMap, Map alterMap) {
      if (alterMap == null) {
         alterMap = new HashMap();
      }

      if (typeMap == null || typeMap.isEmpty()) {
         alterMap.clear();
      }

      Iterator<String> it = alterMap.keySet().iterator();

      while(it.hasNext()) {
         String name = (String)it.next();
         if (!typeMap.containsKey(name)) {
            it.remove();
         }
      }

      Collection<CtClass> interfaces = new ArrayList();

      for(CtClass intf : alterMap.values()) {
         try {
            interfaces.addAll(Arrays.asList(intf.getInterfaces()));
         } catch (NotFoundException e) {
            throw new RuntimeException(e);
         }
      }

      for(CtClass c : interfaces) {
         alterMap.remove(c.getName());
      }

      return alterMap;
   }

   Map getAllInterfaces(CtClass clazz, Map map) {
      if (map == null) {
         map = new HashMap();
      }

      if (clazz.isInterface()) {
         map.put(clazz.getName(), clazz);
      }

      do {
         try {
            CtClass[] interfaces = clazz.getInterfaces();

            for(CtClass intf : interfaces) {
               map.put(intf.getName(), intf);
               this.getAllInterfaces(intf, map);
            }

            clazz = clazz.getSuperclass();
         } catch (NotFoundException e) {
            throw new RuntimeException(e);
         }
      } while(clazz != null);

      return map;
   }

   Map getDeclaredInterfaces(CtClass clazz, Map map) {
      if (map == null) {
         map = new HashMap();
      }

      if (clazz.isInterface()) {
         map.put(clazz.getName(), clazz);
      }

      CtClass[] interfaces;
      try {
         interfaces = clazz.getInterfaces();
      } catch (NotFoundException e) {
         throw new RuntimeException(e);
      }

      for(CtClass intf : interfaces) {
         map.put(intf.getName(), intf);
         this.getDeclaredInterfaces(intf, map);
      }

      return map;
   }

   public int hashCode() {
      return this.getClass().hashCode() + this.clazz.hashCode();
   }

   public boolean equals(Object o) {
      if (!(o instanceof Type)) {
         return false;
      } else {
         return o.getClass() == this.getClass() && eq(this.clazz, ((Type)o).clazz);
      }
   }

   static boolean eq(CtClass one, CtClass two) {
      return one == two || one != null && two != null && one.getName().equals(two.getName());
   }

   public String toString() {
      if (this == BOGUS) {
         return "BOGUS";
      } else if (this == UNINIT) {
         return "UNINIT";
      } else if (this == RETURN_ADDRESS) {
         return "RETURN ADDRESS";
      } else if (this == TOP) {
         return "TOP";
      } else {
         return this.clazz == null ? "null" : this.clazz.getName();
      }
   }

   static {
      DOUBLE = new Type(CtClass.doubleType);
      BOOLEAN = new Type(CtClass.booleanType);
      LONG = new Type(CtClass.longType);
      CHAR = new Type(CtClass.charType);
      BYTE = new Type(CtClass.byteType);
      SHORT = new Type(CtClass.shortType);
      INTEGER = new Type(CtClass.intType);
      FLOAT = new Type(CtClass.floatType);
      VOID = new Type(CtClass.voidType);
      UNINIT = new Type((CtClass)null);
      RETURN_ADDRESS = new Type((CtClass)null, true);
      TOP = new Type((CtClass)null, true);
      BOGUS = new Type((CtClass)null, true);
      OBJECT = lookupType("java.lang.Object");
      SERIALIZABLE = lookupType("java.io.Serializable");
      CLONEABLE = lookupType("java.lang.Cloneable");
      THROWABLE = lookupType("java.lang.Throwable");
      prims.put(CtClass.doubleType, DOUBLE);
      prims.put(CtClass.longType, LONG);
      prims.put(CtClass.charType, CHAR);
      prims.put(CtClass.shortType, SHORT);
      prims.put(CtClass.intType, INTEGER);
      prims.put(CtClass.floatType, FLOAT);
      prims.put(CtClass.byteType, BYTE);
      prims.put(CtClass.booleanType, BOOLEAN);
      prims.put(CtClass.voidType, VOID);
   }
}
