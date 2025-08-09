package io.fabric8.kubernetes.api.builder;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class Visitors {
   private Visitors() {
   }

   public static Visitor newVisitor(Class type, Visitor visitor) {
      return new DelegatingVisitor(type, visitor);
   }

   protected static List getTypeArguments(Class baseClass, Class childClass) {
      Map<Type, Type> resolvedTypes = new LinkedHashMap();
      Type type = childClass;

      for(Class cl = getClass(childClass); cl != null && cl != Object.class && !baseClass.getName().equals(cl.getName()); cl = getClass(type)) {
         if (type instanceof Class) {
            Class c = (Class)type;
            Optional<Type> nextInterface = baseClass.isInterface() ? getMatchingInterface(baseClass, c.getGenericInterfaces()) : Optional.empty();
            if (nextInterface.isPresent()) {
               type = (Type)nextInterface.get();
            } else {
               type = ((Class)type).getGenericSuperclass();
            }
         } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type t = parameterizedType.getRawType();
            if (!(t instanceof Class)) {
               break;
            }

            Class<?> rawType = (Class)parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            TypeVariable<?>[] typeParameters = rawType.getTypeParameters();

            for(int i = 0; i < actualTypeArguments.length; ++i) {
               resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);
            }

            if (!baseClass.equals(rawType)) {
               type = rawType.getGenericSuperclass();
            }
         }
      }

      Type[] actualTypeArguments;
      if (type instanceof Class) {
         actualTypeArguments = ((Class)type).getTypeParameters();
      } else {
         actualTypeArguments = ((ParameterizedType)type).getActualTypeArguments();
      }

      List<Class> typeArgumentsAsClasses = new ArrayList();
      Object var15 = actualTypeArguments;
      int var16 = actualTypeArguments.length;

      for(int var17 = 0; var17 < var16; ++var17) {
         Type baseType;
         for(baseType = (Type)((Object[])var15)[var17]; resolvedTypes.containsKey(baseType); baseType = (Type)resolvedTypes.get(baseType)) {
         }

         typeArgumentsAsClasses.add(getClass(baseType));
      }

      return typeArgumentsAsClasses;
   }

   private static String getRawName(Type type) {
      return type instanceof ParameterizedType ? ((ParameterizedType)type).getRawType().getTypeName() : type.getTypeName();
   }

   private static Class getClass(Type type) {
      if (type instanceof Class) {
         return (Class)type;
      } else if (type instanceof ParameterizedType) {
         return getClass(((ParameterizedType)type).getRawType());
      } else if (type instanceof GenericArrayType) {
         Type componentType = ((GenericArrayType)type).getGenericComponentType();
         Class<?> componentClass = getClass(componentType);
         return componentClass != null ? Array.newInstance(componentClass, 0).getClass() : null;
      } else {
         return null;
      }
   }

   private static Optional getMatchingInterface(Class targetInterface, Type... candidates) {
      if (candidates != null && candidates.length != 0) {
         Optional<Type> match = Arrays.stream(candidates).filter((cx) -> getRawName(cx).equals(targetInterface.getTypeName())).findFirst();
         if (match.isPresent()) {
            return match;
         } else {
            for(Type candidate : candidates) {
               if (candidate instanceof Class) {
                  Class c = (Class)candidate;
                  Optional<Type> next = getMatchingInterface(targetInterface, c.getGenericInterfaces());
                  if (next.isPresent()) {
                     return Optional.of(c);
                  }
               }
            }

            return Optional.empty();
         }
      } else {
         return Optional.empty();
      }
   }
}
