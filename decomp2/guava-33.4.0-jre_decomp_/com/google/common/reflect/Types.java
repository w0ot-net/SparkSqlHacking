package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
final class Types {
   private static final Joiner COMMA_JOINER = Joiner.on(", ").useForNull("null");

   static Type newArrayType(Type componentType) {
      if (componentType instanceof WildcardType) {
         WildcardType wildcard = (WildcardType)componentType;
         Type[] lowerBounds = wildcard.getLowerBounds();
         Preconditions.checkArgument(lowerBounds.length <= 1, "Wildcard cannot have more than one lower bounds.");
         if (lowerBounds.length == 1) {
            return supertypeOf(newArrayType(lowerBounds[0]));
         } else {
            Type[] upperBounds = wildcard.getUpperBounds();
            Preconditions.checkArgument(upperBounds.length == 1, "Wildcard should have only one upper bound.");
            return subtypeOf(newArrayType(upperBounds[0]));
         }
      } else {
         return Types.JavaVersion.CURRENT.newArrayType(componentType);
      }
   }

   static ParameterizedType newParameterizedTypeWithOwner(@CheckForNull Type ownerType, Class rawType, Type... arguments) {
      if (ownerType == null) {
         return newParameterizedType(rawType, arguments);
      } else {
         Preconditions.checkNotNull(arguments);
         Preconditions.checkArgument(rawType.getEnclosingClass() != null, "Owner type for unenclosed %s", (Object)rawType);
         return new ParameterizedTypeImpl(ownerType, rawType, arguments);
      }
   }

   static ParameterizedType newParameterizedType(Class rawType, Type... arguments) {
      return new ParameterizedTypeImpl(Types.ClassOwnership.JVM_BEHAVIOR.getOwnerType(rawType), rawType, arguments);
   }

   static TypeVariable newArtificialTypeVariable(GenericDeclaration declaration, String name, Type... bounds) {
      return newTypeVariableImpl(declaration, name, bounds.length == 0 ? new Type[]{Object.class} : bounds);
   }

   @VisibleForTesting
   static WildcardType subtypeOf(Type upperBound) {
      return new WildcardTypeImpl(new Type[0], new Type[]{upperBound});
   }

   @VisibleForTesting
   static WildcardType supertypeOf(Type lowerBound) {
      return new WildcardTypeImpl(new Type[]{lowerBound}, new Type[]{Object.class});
   }

   static String toString(Type type) {
      return type instanceof Class ? ((Class)type).getName() : type.toString();
   }

   @CheckForNull
   static Type getComponentType(Type type) {
      Preconditions.checkNotNull(type);
      final AtomicReference<Type> result = new AtomicReference();
      (new TypeVisitor() {
         void visitTypeVariable(TypeVariable t) {
            result.set(Types.subtypeOfComponentType(t.getBounds()));
         }

         void visitWildcardType(WildcardType t) {
            result.set(Types.subtypeOfComponentType(t.getUpperBounds()));
         }

         void visitGenericArrayType(GenericArrayType t) {
            result.set(t.getGenericComponentType());
         }

         void visitClass(Class t) {
            result.set(t.getComponentType());
         }
      }).visit(new Type[]{type});
      return (Type)result.get();
   }

   @CheckForNull
   private static Type subtypeOfComponentType(Type[] bounds) {
      for(Type bound : bounds) {
         Type componentType = getComponentType(bound);
         if (componentType != null) {
            if (componentType instanceof Class) {
               Class<?> componentClass = (Class)componentType;
               if (componentClass.isPrimitive()) {
                  return componentClass;
               }
            }

            return subtypeOf(componentType);
         }
      }

      return null;
   }

   private static TypeVariable newTypeVariableImpl(GenericDeclaration genericDeclaration, String name, Type[] bounds) {
      TypeVariableImpl<D> typeVariableImpl = new TypeVariableImpl(genericDeclaration, name, bounds);
      TypeVariable<D> typeVariable = (TypeVariable)Reflection.newProxy(TypeVariable.class, new TypeVariableInvocationHandler(typeVariableImpl));
      return typeVariable;
   }

   private static Type[] toArray(Collection types) {
      return (Type[])types.toArray(new Type[0]);
   }

   private static Iterable filterUpperBounds(Iterable bounds) {
      return Iterables.filter(bounds, Predicates.not(Predicates.equalTo(Object.class)));
   }

   private static void disallowPrimitiveType(Type[] types, String usedAs) {
      for(Type type : types) {
         if (type instanceof Class) {
            Class<?> cls = (Class)type;
            Preconditions.checkArgument(!cls.isPrimitive(), "Primitive type '%s' used as %s", cls, usedAs);
         }
      }

   }

   static Class getArrayClass(Class componentType) {
      return Array.newInstance(componentType, 0).getClass();
   }

   private Types() {
   }

   private static enum ClassOwnership {
      OWNED_BY_ENCLOSING_CLASS {
         @CheckForNull
         Class getOwnerType(Class rawType) {
            return rawType.getEnclosingClass();
         }
      },
      LOCAL_CLASS_HAS_NO_OWNER {
         @CheckForNull
         Class getOwnerType(Class rawType) {
            return rawType.isLocalClass() ? null : rawType.getEnclosingClass();
         }
      };

      static final ClassOwnership JVM_BEHAVIOR = detectJvmBehavior();

      private ClassOwnership() {
      }

      @CheckForNull
      abstract Class getOwnerType(Class rawType);

      private static ClassOwnership detectJvmBehavior() {
         Class<?> subclass = (new LocalClass() {
         }).getClass();
         ParameterizedType parameterizedType = (ParameterizedType)Objects.requireNonNull((ParameterizedType)subclass.getGenericSuperclass());

         for(ClassOwnership behavior : values()) {
            class LocalClass {
            }

            if (behavior.getOwnerType(LocalClass.class) == parameterizedType.getOwnerType()) {
               return behavior;
            }
         }

         throw new AssertionError();
      }

      // $FF: synthetic method
      private static ClassOwnership[] $values() {
         return new ClassOwnership[]{OWNED_BY_ENCLOSING_CLASS, LOCAL_CLASS_HAS_NO_OWNER};
      }
   }

   private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
      private final Type componentType;
      private static final long serialVersionUID = 0L;

      GenericArrayTypeImpl(Type componentType) {
         this.componentType = Types.JavaVersion.CURRENT.usedInGenericType(componentType);
      }

      public Type getGenericComponentType() {
         return this.componentType;
      }

      public String toString() {
         return Types.toString(this.componentType) + "[]";
      }

      public int hashCode() {
         return this.componentType.hashCode();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof GenericArrayType) {
            GenericArrayType that = (GenericArrayType)obj;
            return com.google.common.base.Objects.equal(this.getGenericComponentType(), that.getGenericComponentType());
         } else {
            return false;
         }
      }
   }

   private static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
      @CheckForNull
      private final Type ownerType;
      private final ImmutableList argumentsList;
      private final Class rawType;
      private static final long serialVersionUID = 0L;

      ParameterizedTypeImpl(@CheckForNull Type ownerType, Class rawType, Type[] typeArguments) {
         Preconditions.checkNotNull(rawType);
         Preconditions.checkArgument(typeArguments.length == rawType.getTypeParameters().length);
         Types.disallowPrimitiveType(typeArguments, "type parameter");
         this.ownerType = ownerType;
         this.rawType = rawType;
         this.argumentsList = Types.JavaVersion.CURRENT.usedInGenericType(typeArguments);
      }

      public Type[] getActualTypeArguments() {
         return Types.toArray(this.argumentsList);
      }

      public Type getRawType() {
         return this.rawType;
      }

      @CheckForNull
      public Type getOwnerType() {
         return this.ownerType;
      }

      public String toString() {
         StringBuilder builder = new StringBuilder();
         if (this.ownerType != null && Types.JavaVersion.CURRENT.jdkTypeDuplicatesOwnerName()) {
            builder.append(Types.JavaVersion.CURRENT.typeName(this.ownerType)).append('.');
         }

         StringBuilder var10000 = builder.append(this.rawType.getName()).append('<');
         Joiner var10001 = Types.COMMA_JOINER;
         ImmutableList var10002 = this.argumentsList;
         JavaVersion var10003 = Types.JavaVersion.CURRENT;
         Objects.requireNonNull(var10003);
         return var10000.append(var10001.join(Iterables.transform(var10002, var10003::typeName))).append('>').toString();
      }

      public int hashCode() {
         return (this.ownerType == null ? 0 : this.ownerType.hashCode()) ^ this.argumentsList.hashCode() ^ this.rawType.hashCode();
      }

      public boolean equals(@CheckForNull Object other) {
         if (!(other instanceof ParameterizedType)) {
            return false;
         } else {
            ParameterizedType that = (ParameterizedType)other;
            return this.getRawType().equals(that.getRawType()) && com.google.common.base.Objects.equal(this.getOwnerType(), that.getOwnerType()) && Arrays.equals(this.getActualTypeArguments(), that.getActualTypeArguments());
         }
      }
   }

   private static final class TypeVariableInvocationHandler implements InvocationHandler {
      private static final ImmutableMap typeVariableMethods;
      private final TypeVariableImpl typeVariableImpl;

      TypeVariableInvocationHandler(TypeVariableImpl typeVariableImpl) {
         this.typeVariableImpl = typeVariableImpl;
      }

      @CheckForNull
      public Object invoke(Object proxy, Method method, @CheckForNull @Nullable Object[] args) throws Throwable {
         String methodName = method.getName();
         Method typeVariableMethod = (Method)typeVariableMethods.get(methodName);
         if (typeVariableMethod == null) {
            throw new UnsupportedOperationException(methodName);
         } else {
            try {
               return typeVariableMethod.invoke(this.typeVariableImpl, args);
            } catch (InvocationTargetException e) {
               throw e.getCause();
            }
         }
      }

      static {
         ImmutableMap.Builder<String, Method> builder = ImmutableMap.builder();

         for(Method method : TypeVariableImpl.class.getMethods()) {
            if (method.getDeclaringClass().equals(TypeVariableImpl.class)) {
               try {
                  method.setAccessible(true);
               } catch (AccessControlException var6) {
               }

               builder.put(method.getName(), method);
            }
         }

         typeVariableMethods = builder.buildKeepingLast();
      }
   }

   private static final class TypeVariableImpl {
      private final GenericDeclaration genericDeclaration;
      private final String name;
      private final ImmutableList bounds;

      TypeVariableImpl(GenericDeclaration genericDeclaration, String name, Type[] bounds) {
         Types.disallowPrimitiveType(bounds, "bound for type variable");
         this.genericDeclaration = (GenericDeclaration)Preconditions.checkNotNull(genericDeclaration);
         this.name = (String)Preconditions.checkNotNull(name);
         this.bounds = ImmutableList.copyOf((Object[])bounds);
      }

      public Type[] getBounds() {
         return Types.toArray(this.bounds);
      }

      public GenericDeclaration getGenericDeclaration() {
         return this.genericDeclaration;
      }

      public String getName() {
         return this.name;
      }

      public String getTypeName() {
         return this.name;
      }

      public String toString() {
         return this.name;
      }

      public int hashCode() {
         return this.genericDeclaration.hashCode() ^ this.name.hashCode();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY) {
            if (obj != null && Proxy.isProxyClass(obj.getClass()) && Proxy.getInvocationHandler(obj) instanceof TypeVariableInvocationHandler) {
               TypeVariableInvocationHandler typeVariableInvocationHandler = (TypeVariableInvocationHandler)Proxy.getInvocationHandler(obj);
               TypeVariableImpl<?> that = typeVariableInvocationHandler.typeVariableImpl;
               return this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration()) && this.bounds.equals(that.bounds);
            } else {
               return false;
            }
         } else if (!(obj instanceof TypeVariable)) {
            return false;
         } else {
            TypeVariable<?> that = (TypeVariable)obj;
            return this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration());
         }
      }
   }

   static final class WildcardTypeImpl implements WildcardType, Serializable {
      private final ImmutableList lowerBounds;
      private final ImmutableList upperBounds;
      private static final long serialVersionUID = 0L;

      WildcardTypeImpl(Type[] lowerBounds, Type[] upperBounds) {
         Types.disallowPrimitiveType(lowerBounds, "lower bound for wildcard");
         Types.disallowPrimitiveType(upperBounds, "upper bound for wildcard");
         this.lowerBounds = Types.JavaVersion.CURRENT.usedInGenericType(lowerBounds);
         this.upperBounds = Types.JavaVersion.CURRENT.usedInGenericType(upperBounds);
      }

      public Type[] getLowerBounds() {
         return Types.toArray(this.lowerBounds);
      }

      public Type[] getUpperBounds() {
         return Types.toArray(this.upperBounds);
      }

      public boolean equals(@CheckForNull Object obj) {
         if (!(obj instanceof WildcardType)) {
            return false;
         } else {
            WildcardType that = (WildcardType)obj;
            return this.lowerBounds.equals(Arrays.asList(that.getLowerBounds())) && this.upperBounds.equals(Arrays.asList(that.getUpperBounds()));
         }
      }

      public int hashCode() {
         return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
      }

      public String toString() {
         StringBuilder builder = new StringBuilder("?");

         for(Type lowerBound : this.lowerBounds) {
            builder.append(" super ").append(Types.JavaVersion.CURRENT.typeName(lowerBound));
         }

         for(Type upperBound : Types.filterUpperBounds(this.upperBounds)) {
            builder.append(" extends ").append(Types.JavaVersion.CURRENT.typeName(upperBound));
         }

         return builder.toString();
      }
   }

   static enum JavaVersion {
      JAVA6 {
         GenericArrayType newArrayType(Type componentType) {
            return new GenericArrayTypeImpl(componentType);
         }

         Type usedInGenericType(Type type) {
            Preconditions.checkNotNull(type);
            if (type instanceof Class) {
               Class<?> cls = (Class)type;
               if (cls.isArray()) {
                  return new GenericArrayTypeImpl(cls.getComponentType());
               }
            }

            return type;
         }
      },
      JAVA7 {
         Type newArrayType(Type componentType) {
            return (Type)(componentType instanceof Class ? Types.getArrayClass((Class)componentType) : new GenericArrayTypeImpl(componentType));
         }

         Type usedInGenericType(Type type) {
            return (Type)Preconditions.checkNotNull(type);
         }
      },
      JAVA8 {
         Type newArrayType(Type componentType) {
            return JAVA7.newArrayType(componentType);
         }

         Type usedInGenericType(Type type) {
            return JAVA7.usedInGenericType(type);
         }

         String typeName(Type type) {
            try {
               Method getTypeName = Type.class.getMethod("getTypeName");
               return (String)getTypeName.invoke(type);
            } catch (NoSuchMethodException var3) {
               throw new AssertionError("Type.getTypeName should be available in Java 8");
            } catch (IllegalAccessException | InvocationTargetException e) {
               throw new RuntimeException(e);
            }
         }
      },
      JAVA9 {
         Type newArrayType(Type componentType) {
            return JAVA8.newArrayType(componentType);
         }

         Type usedInGenericType(Type type) {
            return JAVA8.usedInGenericType(type);
         }

         String typeName(Type type) {
            return JAVA8.typeName(type);
         }

         boolean jdkTypeDuplicatesOwnerName() {
            return false;
         }
      };

      static final JavaVersion CURRENT;

      private JavaVersion() {
      }

      abstract Type newArrayType(Type componentType);

      abstract Type usedInGenericType(Type type);

      final ImmutableList usedInGenericType(Type[] types) {
         ImmutableList.Builder<Type> builder = ImmutableList.builder();

         for(Type type : types) {
            builder.add((Object)this.usedInGenericType(type));
         }

         return builder.build();
      }

      String typeName(Type type) {
         return Types.toString(type);
      }

      boolean jdkTypeDuplicatesOwnerName() {
         return true;
      }

      // $FF: synthetic method
      private static JavaVersion[] $values() {
         return new JavaVersion[]{JAVA6, JAVA7, JAVA8, JAVA9};
      }

      static {
         if (AnnotatedElement.class.isAssignableFrom(TypeVariable.class)) {
            if ((new TypeCapture() {
            }).capture().toString().contains("java.util.Map.java.util.Map")) {
               CURRENT = JAVA8;
            } else {
               CURRENT = JAVA9;
            }
         } else if ((new TypeCapture() {
         }).capture() instanceof Class) {
            CURRENT = JAVA7;
         } else {
            CURRENT = JAVA6;
         }

      }
   }

   static final class NativeTypeVariableEquals {
      static final boolean NATIVE_TYPE_VARIABLE_ONLY = !NativeTypeVariableEquals.class.getTypeParameters()[0].equals(Types.newArtificialTypeVariable(NativeTypeVariableEquals.class, "X"));
   }
}
