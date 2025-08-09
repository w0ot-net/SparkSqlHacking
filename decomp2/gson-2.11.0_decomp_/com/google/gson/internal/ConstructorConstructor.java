package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor {
   private final Map instanceCreators;
   private final boolean useJdkUnsafe;
   private final List reflectionFilters;

   public ConstructorConstructor(Map instanceCreators, boolean useJdkUnsafe, List reflectionFilters) {
      this.instanceCreators = instanceCreators;
      this.useJdkUnsafe = useJdkUnsafe;
      this.reflectionFilters = reflectionFilters;
   }

   static String checkInstantiable(Class c) {
      int modifiers = c.getModifiers();
      if (Modifier.isInterface(modifiers)) {
         return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: " + c.getName();
      } else {
         return Modifier.isAbstract(modifiers) ? "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + c.getName() + "\nSee " + TroubleshootingGuide.createUrl("r8-abstract-class") : null;
      }
   }

   public ObjectConstructor get(TypeToken typeToken) {
      final Type type = typeToken.getType();
      Class<? super T> rawType = typeToken.getRawType();
      final InstanceCreator<T> typeCreator = (InstanceCreator)this.instanceCreators.get(type);
      if (typeCreator != null) {
         return new ObjectConstructor() {
            public Object construct() {
               return typeCreator.createInstance(type);
            }
         };
      } else {
         final InstanceCreator<T> rawTypeCreator = (InstanceCreator)this.instanceCreators.get(rawType);
         if (rawTypeCreator != null) {
            return new ObjectConstructor() {
               public Object construct() {
                  return rawTypeCreator.createInstance(type);
               }
            };
         } else {
            ObjectConstructor<T> specialConstructor = newSpecialCollectionConstructor(type, rawType);
            if (specialConstructor != null) {
               return specialConstructor;
            } else {
               ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, rawType);
               ObjectConstructor<T> defaultConstructor = newDefaultConstructor(rawType, filterResult);
               if (defaultConstructor != null) {
                  return defaultConstructor;
               } else {
                  ObjectConstructor<T> defaultImplementation = newDefaultImplementationConstructor(type, rawType);
                  if (defaultImplementation != null) {
                     return defaultImplementation;
                  } else {
                     final String exceptionMessage = checkInstantiable(rawType);
                     if (exceptionMessage != null) {
                        return new ObjectConstructor() {
                           public Object construct() {
                              throw new JsonIOException(exceptionMessage);
                           }
                        };
                     } else if (filterResult == ReflectionAccessFilter.FilterResult.ALLOW) {
                        return this.newUnsafeAllocator(rawType);
                     } else {
                        final String message = "Unable to create instance of " + rawType + "; ReflectionAccessFilter does not permit using reflection or Unsafe. Register an InstanceCreator or a TypeAdapter for this type or adjust the access filter to allow using reflection.";
                        return new ObjectConstructor() {
                           public Object construct() {
                              throw new JsonIOException(message);
                           }
                        };
                     }
                  }
               }
            }
         }
      }
   }

   private static ObjectConstructor newSpecialCollectionConstructor(final Type type, Class rawType) {
      if (EnumSet.class.isAssignableFrom(rawType)) {
         return new ObjectConstructor() {
            public Object construct() {
               if (type instanceof ParameterizedType) {
                  Type elementType = ((ParameterizedType)type).getActualTypeArguments()[0];
                  if (elementType instanceof Class) {
                     T set = (T)EnumSet.noneOf((Class)elementType);
                     return set;
                  } else {
                     throw new JsonIOException("Invalid EnumSet type: " + type.toString());
                  }
               } else {
                  throw new JsonIOException("Invalid EnumSet type: " + type.toString());
               }
            }
         };
      } else {
         return rawType == EnumMap.class ? new ObjectConstructor() {
            public Object construct() {
               if (type instanceof ParameterizedType) {
                  Type elementType = ((ParameterizedType)type).getActualTypeArguments()[0];
                  if (elementType instanceof Class) {
                     T map = (T)(new EnumMap((Class)elementType));
                     return map;
                  } else {
                     throw new JsonIOException("Invalid EnumMap type: " + type.toString());
                  }
               } else {
                  throw new JsonIOException("Invalid EnumMap type: " + type.toString());
               }
            }
         } : null;
      }
   }

   private static ObjectConstructor newDefaultConstructor(Class rawType, ReflectionAccessFilter.FilterResult filterResult) {
      if (Modifier.isAbstract(rawType.getModifiers())) {
         return null;
      } else {
         final Constructor<? super T> constructor;
         try {
            constructor = rawType.getDeclaredConstructor();
         } catch (NoSuchMethodException var5) {
            return null;
         }

         boolean canAccess = filterResult == ReflectionAccessFilter.FilterResult.ALLOW || ReflectionAccessFilterHelper.canAccess(constructor, (Object)null) && (filterResult != ReflectionAccessFilter.FilterResult.BLOCK_ALL || Modifier.isPublic(constructor.getModifiers()));
         if (!canAccess) {
            final String message = "Unable to invoke no-args constructor of " + rawType + "; constructor is not accessible and ReflectionAccessFilter does not permit making it accessible. Register an InstanceCreator or a TypeAdapter for this type, change the visibility of the constructor or adjust the access filter.";
            return new ObjectConstructor() {
               public Object construct() {
                  throw new JsonIOException(message);
               }
            };
         } else {
            if (filterResult == ReflectionAccessFilter.FilterResult.ALLOW) {
               final String exceptionMessage = ReflectionHelper.tryMakeAccessible(constructor);
               if (exceptionMessage != null) {
                  return new ObjectConstructor() {
                     public Object construct() {
                        throw new JsonIOException(exceptionMessage);
                     }
                  };
               }
            }

            return new ObjectConstructor() {
               public Object construct() {
                  try {
                     T newInstance = (T)constructor.newInstance();
                     return newInstance;
                  } catch (InstantiationException e) {
                     throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(constructor) + "' with no args", e);
                  } catch (InvocationTargetException e) {
                     throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(constructor) + "' with no args", e.getCause());
                  } catch (IllegalAccessException e) {
                     throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
                  }
               }
            };
         }
      }
   }

   private static ObjectConstructor newDefaultImplementationConstructor(Type type, Class rawType) {
      if (Collection.class.isAssignableFrom(rawType)) {
         if (SortedSet.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor() {
               public Object construct() {
                  return new TreeSet();
               }
            };
         } else if (Set.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor() {
               public Object construct() {
                  return new LinkedHashSet();
               }
            };
         } else {
            return Queue.class.isAssignableFrom(rawType) ? new ObjectConstructor() {
               public Object construct() {
                  return new ArrayDeque();
               }
            } : new ObjectConstructor() {
               public Object construct() {
                  return new ArrayList();
               }
            };
         }
      } else if (Map.class.isAssignableFrom(rawType)) {
         if (ConcurrentNavigableMap.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor() {
               public Object construct() {
                  return new ConcurrentSkipListMap();
               }
            };
         } else if (ConcurrentMap.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor() {
               public Object construct() {
                  return new ConcurrentHashMap();
               }
            };
         } else if (SortedMap.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor() {
               public Object construct() {
                  return new TreeMap();
               }
            };
         } else {
            return type instanceof ParameterizedType && !String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)type).getActualTypeArguments()[0]).getRawType()) ? new ObjectConstructor() {
               public Object construct() {
                  return new LinkedHashMap();
               }
            } : new ObjectConstructor() {
               public Object construct() {
                  return new LinkedTreeMap();
               }
            };
         }
      } else {
         return null;
      }
   }

   private ObjectConstructor newUnsafeAllocator(final Class rawType) {
      if (this.useJdkUnsafe) {
         return new ObjectConstructor() {
            public Object construct() {
               try {
                  T newInstance = (T)UnsafeAllocator.INSTANCE.newInstance(rawType);
                  return newInstance;
               } catch (Exception e) {
                  throw new RuntimeException("Unable to create instance of " + rawType + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
               }
            }
         };
      } else {
         final String exceptionMessage = "Unable to create instance of " + rawType + "; usage of JDK Unsafe is disabled. Registering an InstanceCreator or a TypeAdapter for this type, adding a no-args constructor, or enabling usage of JDK Unsafe may fix this problem.";
         if (rawType.getDeclaredConstructors().length == 0) {
            exceptionMessage = exceptionMessage + " Or adjust your R8 configuration to keep the no-args constructor of the class.";
         }

         return new ObjectConstructor() {
            public Object construct() {
               throw new JsonIOException(exceptionMessage);
            }
         };
      }
   }

   public String toString() {
      return this.instanceCreators.toString();
   }
}
