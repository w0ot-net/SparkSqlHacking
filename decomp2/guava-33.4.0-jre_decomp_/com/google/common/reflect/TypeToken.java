package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Primitives;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class TypeToken extends TypeCapture implements Serializable {
   private final Type runtimeType;
   @LazyInit
   @CheckForNull
   private transient TypeResolver invariantTypeResolver;
   @LazyInit
   @CheckForNull
   private transient TypeResolver covariantTypeResolver;
   private static final long serialVersionUID = 3637540370352322684L;

   protected TypeToken() {
      this.runtimeType = this.capture();
      Preconditions.checkState(!(this.runtimeType instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", (Object)this.runtimeType);
   }

   protected TypeToken(Class declaringClass) {
      Type captured = super.capture();
      if (captured instanceof Class) {
         this.runtimeType = captured;
      } else {
         this.runtimeType = TypeResolver.covariantly(declaringClass).resolveType(captured);
      }

   }

   private TypeToken(Type type) {
      this.runtimeType = (Type)Preconditions.checkNotNull(type);
   }

   public static TypeToken of(Class type) {
      return new SimpleTypeToken(type);
   }

   public static TypeToken of(Type type) {
      return new SimpleTypeToken(type);
   }

   public final Class getRawType() {
      Class<?> rawType = (Class)this.getRawTypes().iterator().next();
      return rawType;
   }

   public final Type getType() {
      return this.runtimeType;
   }

   public final TypeToken where(TypeParameter typeParam, TypeToken typeArg) {
      TypeResolver resolver = (new TypeResolver()).where(ImmutableMap.of(new TypeResolver.TypeVariableKey(typeParam.typeVariable), typeArg.runtimeType));
      return new SimpleTypeToken(resolver.resolveType(this.runtimeType));
   }

   public final TypeToken where(TypeParameter typeParam, Class typeArg) {
      return this.where(typeParam, of(typeArg));
   }

   public final TypeToken resolveType(Type type) {
      Preconditions.checkNotNull(type);
      return of(this.getInvariantTypeResolver().resolveType(type));
   }

   private TypeToken resolveSupertype(Type type) {
      TypeToken<?> supertype = of(this.getCovariantTypeResolver().resolveType(type));
      supertype.covariantTypeResolver = this.covariantTypeResolver;
      supertype.invariantTypeResolver = this.invariantTypeResolver;
      return supertype;
   }

   @CheckForNull
   final TypeToken getGenericSuperclass() {
      if (this.runtimeType instanceof TypeVariable) {
         return this.boundAsSuperclass(((TypeVariable)this.runtimeType).getBounds()[0]);
      } else if (this.runtimeType instanceof WildcardType) {
         return this.boundAsSuperclass(((WildcardType)this.runtimeType).getUpperBounds()[0]);
      } else {
         Type superclass = this.getRawType().getGenericSuperclass();
         if (superclass == null) {
            return null;
         } else {
            TypeToken<? super T> superToken = this.resolveSupertype(superclass);
            return superToken;
         }
      }
   }

   @CheckForNull
   private TypeToken boundAsSuperclass(Type bound) {
      TypeToken<?> token = of(bound);
      return token.getRawType().isInterface() ? null : token;
   }

   final ImmutableList getGenericInterfaces() {
      if (this.runtimeType instanceof TypeVariable) {
         return this.boundsAsInterfaces(((TypeVariable)this.runtimeType).getBounds());
      } else if (this.runtimeType instanceof WildcardType) {
         return this.boundsAsInterfaces(((WildcardType)this.runtimeType).getUpperBounds());
      } else {
         ImmutableList.Builder<TypeToken<? super T>> builder = ImmutableList.builder();

         for(Type interfaceType : this.getRawType().getGenericInterfaces()) {
            TypeToken<? super T> resolvedInterface = this.resolveSupertype(interfaceType);
            builder.add((Object)resolvedInterface);
         }

         return builder.build();
      }
   }

   private ImmutableList boundsAsInterfaces(Type[] bounds) {
      ImmutableList.Builder<TypeToken<? super T>> builder = ImmutableList.builder();

      for(Type bound : bounds) {
         TypeToken<? super T> boundType = of(bound);
         if (boundType.getRawType().isInterface()) {
            builder.add((Object)boundType);
         }
      }

      return builder.build();
   }

   public final TypeSet getTypes() {
      return new TypeSet();
   }

   public final TypeToken getSupertype(Class superclass) {
      Preconditions.checkArgument(this.someRawTypeIsSubclassOf(superclass), "%s is not a super class of %s", superclass, this);
      if (this.runtimeType instanceof TypeVariable) {
         return this.getSupertypeFromUpperBounds(superclass, ((TypeVariable)this.runtimeType).getBounds());
      } else if (this.runtimeType instanceof WildcardType) {
         return this.getSupertypeFromUpperBounds(superclass, ((WildcardType)this.runtimeType).getUpperBounds());
      } else if (superclass.isArray()) {
         return this.getArraySupertype(superclass);
      } else {
         TypeToken<? super T> supertype = this.resolveSupertype(toGenericType(superclass).runtimeType);
         return supertype;
      }
   }

   public final TypeToken getSubtype(Class subclass) {
      Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", (Object)this);
      if (this.runtimeType instanceof WildcardType) {
         return this.getSubtypeFromLowerBounds(subclass, ((WildcardType)this.runtimeType).getLowerBounds());
      } else if (this.isArray()) {
         return this.getArraySubtype(subclass);
      } else {
         Preconditions.checkArgument(this.getRawType().isAssignableFrom(subclass), "%s isn't a subclass of %s", subclass, this);
         Type resolvedTypeArgs = this.resolveTypeArgsForSubclass(subclass);
         TypeToken<? extends T> subtype = of(resolvedTypeArgs);
         Preconditions.checkArgument(subtype.isSubtypeOf(this), "%s does not appear to be a subtype of %s", subtype, this);
         return subtype;
      }
   }

   public final boolean isSupertypeOf(TypeToken type) {
      return type.isSubtypeOf(this.getType());
   }

   public final boolean isSupertypeOf(Type type) {
      return of(type).isSubtypeOf(this.getType());
   }

   public final boolean isSubtypeOf(TypeToken type) {
      return this.isSubtypeOf(type.getType());
   }

   public final boolean isSubtypeOf(Type supertype) {
      Preconditions.checkNotNull(supertype);
      if (supertype instanceof WildcardType) {
         return any(((WildcardType)supertype).getLowerBounds()).isSupertypeOf(this.runtimeType);
      } else if (this.runtimeType instanceof WildcardType) {
         return any(((WildcardType)this.runtimeType).getUpperBounds()).isSubtypeOf(supertype);
      } else if (!(this.runtimeType instanceof TypeVariable)) {
         if (this.runtimeType instanceof GenericArrayType) {
            return of(supertype).isSupertypeOfArray((GenericArrayType)this.runtimeType);
         } else if (supertype instanceof Class) {
            return this.someRawTypeIsSubclassOf((Class)supertype);
         } else if (supertype instanceof ParameterizedType) {
            return this.isSubtypeOfParameterizedType((ParameterizedType)supertype);
         } else {
            return supertype instanceof GenericArrayType ? this.isSubtypeOfArrayType((GenericArrayType)supertype) : false;
         }
      } else {
         return this.runtimeType.equals(supertype) || any(((TypeVariable)this.runtimeType).getBounds()).isSubtypeOf(supertype);
      }
   }

   public final boolean isArray() {
      return this.getComponentType() != null;
   }

   public final boolean isPrimitive() {
      return this.runtimeType instanceof Class && ((Class)this.runtimeType).isPrimitive();
   }

   public final TypeToken wrap() {
      if (this.isPrimitive()) {
         Class<T> type = (Class)this.runtimeType;
         return of(Primitives.wrap(type));
      } else {
         return this;
      }
   }

   private boolean isWrapper() {
      return Primitives.allWrapperTypes().contains(this.runtimeType);
   }

   public final TypeToken unwrap() {
      if (this.isWrapper()) {
         Class<T> type = (Class)this.runtimeType;
         return of(Primitives.unwrap(type));
      } else {
         return this;
      }
   }

   @CheckForNull
   public final TypeToken getComponentType() {
      Type componentType = Types.getComponentType(this.runtimeType);
      return componentType == null ? null : of(componentType);
   }

   public final Invokable method(Method method) {
      Preconditions.checkArgument(this.someRawTypeIsSubclassOf(method.getDeclaringClass()), "%s not declared by %s", method, this);
      return new Invokable.MethodInvokable(method) {
         Type getGenericReturnType() {
            return TypeToken.this.getCovariantTypeResolver().resolveType(super.getGenericReturnType());
         }

         Type[] getGenericParameterTypes() {
            return TypeToken.this.getInvariantTypeResolver().resolveTypesInPlace(super.getGenericParameterTypes());
         }

         Type[] getGenericExceptionTypes() {
            return TypeToken.this.getCovariantTypeResolver().resolveTypesInPlace(super.getGenericExceptionTypes());
         }

         public TypeToken getOwnerType() {
            return TypeToken.this;
         }

         public String toString() {
            return this.getOwnerType() + "." + super.toString();
         }
      };
   }

   public final Invokable constructor(Constructor constructor) {
      Preconditions.checkArgument(constructor.getDeclaringClass() == this.getRawType(), "%s not declared by %s", constructor, this.getRawType());
      return new Invokable.ConstructorInvokable(constructor) {
         Type getGenericReturnType() {
            return TypeToken.this.getCovariantTypeResolver().resolveType(super.getGenericReturnType());
         }

         Type[] getGenericParameterTypes() {
            return TypeToken.this.getInvariantTypeResolver().resolveTypesInPlace(super.getGenericParameterTypes());
         }

         Type[] getGenericExceptionTypes() {
            return TypeToken.this.getCovariantTypeResolver().resolveTypesInPlace(super.getGenericExceptionTypes());
         }

         public TypeToken getOwnerType() {
            return TypeToken.this;
         }

         public String toString() {
            return this.getOwnerType() + "(" + Joiner.on(", ").join((Object[])this.getGenericParameterTypes()) + ")";
         }
      };
   }

   public boolean equals(@CheckForNull Object o) {
      if (o instanceof TypeToken) {
         TypeToken<?> that = (TypeToken)o;
         return this.runtimeType.equals(that.runtimeType);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.runtimeType.hashCode();
   }

   public String toString() {
      return Types.toString(this.runtimeType);
   }

   protected Object writeReplace() {
      return of((new TypeResolver()).resolveType(this.runtimeType));
   }

   @CanIgnoreReturnValue
   final TypeToken rejectTypeVariables() {
      (new TypeVisitor() {
         void visitTypeVariable(TypeVariable type) {
            throw new IllegalArgumentException(TypeToken.this.runtimeType + "contains a type variable and is not safe for the operation");
         }

         void visitWildcardType(WildcardType type) {
            this.visit(type.getLowerBounds());
            this.visit(type.getUpperBounds());
         }

         void visitParameterizedType(ParameterizedType type) {
            this.visit(type.getActualTypeArguments());
            this.visit(new Type[]{type.getOwnerType()});
         }

         void visitGenericArrayType(GenericArrayType type) {
            this.visit(new Type[]{type.getGenericComponentType()});
         }
      }).visit(new Type[]{this.runtimeType});
      return this;
   }

   private boolean someRawTypeIsSubclassOf(Class superclass) {
      for(Class rawType : this.getRawTypes()) {
         if (superclass.isAssignableFrom(rawType)) {
            return true;
         }
      }

      return false;
   }

   private boolean isSubtypeOfParameterizedType(ParameterizedType supertype) {
      Class<?> matchedClass = of((Type)supertype).getRawType();
      if (!this.someRawTypeIsSubclassOf(matchedClass)) {
         return false;
      } else {
         TypeVariable<?>[] typeVars = matchedClass.getTypeParameters();
         Type[] supertypeArgs = supertype.getActualTypeArguments();

         for(int i = 0; i < typeVars.length; ++i) {
            Type subtypeParam = this.getCovariantTypeResolver().resolveType(typeVars[i]);
            if (!of(subtypeParam).is(supertypeArgs[i], typeVars[i])) {
               return false;
            }
         }

         return Modifier.isStatic(((Class)supertype.getRawType()).getModifiers()) || supertype.getOwnerType() == null || this.isOwnedBySubtypeOf(supertype.getOwnerType());
      }
   }

   private boolean isSubtypeOfArrayType(GenericArrayType supertype) {
      if (this.runtimeType instanceof Class) {
         Class<?> fromClass = (Class)this.runtimeType;
         return !fromClass.isArray() ? false : of(fromClass.getComponentType()).isSubtypeOf(supertype.getGenericComponentType());
      } else if (this.runtimeType instanceof GenericArrayType) {
         GenericArrayType fromArrayType = (GenericArrayType)this.runtimeType;
         return of(fromArrayType.getGenericComponentType()).isSubtypeOf(supertype.getGenericComponentType());
      } else {
         return false;
      }
   }

   private boolean isSupertypeOfArray(GenericArrayType subtype) {
      if (this.runtimeType instanceof Class) {
         Class<?> thisClass = (Class)this.runtimeType;
         return !thisClass.isArray() ? thisClass.isAssignableFrom(Object[].class) : of(subtype.getGenericComponentType()).isSubtypeOf((Type)thisClass.getComponentType());
      } else {
         return this.runtimeType instanceof GenericArrayType ? of(subtype.getGenericComponentType()).isSubtypeOf(((GenericArrayType)this.runtimeType).getGenericComponentType()) : false;
      }
   }

   private boolean is(Type formalType, TypeVariable declaration) {
      if (this.runtimeType.equals(formalType)) {
         return true;
      } else if (!(formalType instanceof WildcardType)) {
         return canonicalizeWildcardsInType(this.runtimeType).equals(canonicalizeWildcardsInType(formalType));
      } else {
         WildcardType your = canonicalizeWildcardType(declaration, (WildcardType)formalType);
         return every(your.getUpperBounds()).isSupertypeOf(this.runtimeType) && every(your.getLowerBounds()).isSubtypeOf(this.runtimeType);
      }
   }

   private static Type canonicalizeTypeArg(TypeVariable declaration, Type typeArg) {
      return (Type)(typeArg instanceof WildcardType ? canonicalizeWildcardType(declaration, (WildcardType)typeArg) : canonicalizeWildcardsInType(typeArg));
   }

   private static Type canonicalizeWildcardsInType(Type type) {
      if (type instanceof ParameterizedType) {
         return canonicalizeWildcardsInParameterizedType((ParameterizedType)type);
      } else {
         return type instanceof GenericArrayType ? Types.newArrayType(canonicalizeWildcardsInType(((GenericArrayType)type).getGenericComponentType())) : type;
      }
   }

   private static WildcardType canonicalizeWildcardType(TypeVariable declaration, WildcardType type) {
      Type[] declared = declaration.getBounds();
      List<Type> upperBounds = new ArrayList();

      for(Type bound : type.getUpperBounds()) {
         if (!any(declared).isSubtypeOf(bound)) {
            upperBounds.add(canonicalizeWildcardsInType(bound));
         }
      }

      return new Types.WildcardTypeImpl(type.getLowerBounds(), (Type[])upperBounds.toArray(new Type[0]));
   }

   private static ParameterizedType canonicalizeWildcardsInParameterizedType(ParameterizedType type) {
      Class<?> rawType = (Class)type.getRawType();
      TypeVariable<?>[] typeVars = rawType.getTypeParameters();
      Type[] typeArgs = type.getActualTypeArguments();

      for(int i = 0; i < typeArgs.length; ++i) {
         typeArgs[i] = canonicalizeTypeArg(typeVars[i], typeArgs[i]);
      }

      return Types.newParameterizedTypeWithOwner(type.getOwnerType(), rawType, typeArgs);
   }

   private static Bounds every(Type[] bounds) {
      return new Bounds(bounds, false);
   }

   private static Bounds any(Type[] bounds) {
      return new Bounds(bounds, true);
   }

   private ImmutableSet getRawTypes() {
      final ImmutableSet.Builder<Class<?>> builder = ImmutableSet.builder();
      (new TypeVisitor() {
         void visitTypeVariable(TypeVariable t) {
            this.visit(t.getBounds());
         }

         void visitWildcardType(WildcardType t) {
            this.visit(t.getUpperBounds());
         }

         void visitParameterizedType(ParameterizedType t) {
            builder.add((Object)((Class)t.getRawType()));
         }

         void visitClass(Class t) {
            builder.add((Object)t);
         }

         void visitGenericArrayType(GenericArrayType t) {
            builder.add((Object)Types.getArrayClass(TypeToken.of(t.getGenericComponentType()).getRawType()));
         }
      }).visit(new Type[]{this.runtimeType});
      ImmutableSet<Class<? super T>> result = builder.build();
      return result;
   }

   private boolean isOwnedBySubtypeOf(Type supertype) {
      for(TypeToken type : this.getTypes()) {
         Type ownerType = type.getOwnerTypeIfPresent();
         if (ownerType != null && of(ownerType).isSubtypeOf(supertype)) {
            return true;
         }
      }

      return false;
   }

   @CheckForNull
   private Type getOwnerTypeIfPresent() {
      if (this.runtimeType instanceof ParameterizedType) {
         return ((ParameterizedType)this.runtimeType).getOwnerType();
      } else {
         return this.runtimeType instanceof Class ? ((Class)this.runtimeType).getEnclosingClass() : null;
      }
   }

   @VisibleForTesting
   static TypeToken toGenericType(Class cls) {
      if (cls.isArray()) {
         Type arrayOfGenericType = Types.newArrayType(toGenericType(cls.getComponentType()).runtimeType);
         TypeToken<? extends T> result = of(arrayOfGenericType);
         return result;
      } else {
         TypeVariable<Class<T>>[] typeParams = cls.getTypeParameters();
         Type ownerType = cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers()) ? toGenericType(cls.getEnclosingClass()).runtimeType : null;
         if (typeParams.length <= 0 && (ownerType == null || ownerType == cls.getEnclosingClass())) {
            return of(cls);
         } else {
            TypeToken<? extends T> type = of((Type)Types.newParameterizedTypeWithOwner(ownerType, cls, typeParams));
            return type;
         }
      }
   }

   private TypeResolver getCovariantTypeResolver() {
      TypeResolver resolver = this.covariantTypeResolver;
      if (resolver == null) {
         resolver = this.covariantTypeResolver = TypeResolver.covariantly(this.runtimeType);
      }

      return resolver;
   }

   private TypeResolver getInvariantTypeResolver() {
      TypeResolver resolver = this.invariantTypeResolver;
      if (resolver == null) {
         resolver = this.invariantTypeResolver = TypeResolver.invariantly(this.runtimeType);
      }

      return resolver;
   }

   private TypeToken getSupertypeFromUpperBounds(Class supertype, Type[] upperBounds) {
      for(Type upperBound : upperBounds) {
         TypeToken<? super T> bound = of(upperBound);
         if (bound.isSubtypeOf((Type)supertype)) {
            TypeToken<? super T> result = bound.getSupertype(supertype);
            return result;
         }
      }

      throw new IllegalArgumentException(supertype + " isn't a super type of " + this);
   }

   private TypeToken getSubtypeFromLowerBounds(Class subclass, Type[] lowerBounds) {
      if (lowerBounds.length > 0) {
         TypeToken<? extends T> bound = of(lowerBounds[0]);
         return bound.getSubtype(subclass);
      } else {
         throw new IllegalArgumentException(subclass + " isn't a subclass of " + this);
      }
   }

   private TypeToken getArraySupertype(Class supertype) {
      TypeToken componentType = this.getComponentType();
      if (componentType == null) {
         throw new IllegalArgumentException(supertype + " isn't a super type of " + this);
      } else {
         TypeToken<?> componentSupertype = componentType.getSupertype((Class)Objects.requireNonNull(supertype.getComponentType()));
         TypeToken<? super T> result = of(newArrayClassOrGenericArrayType(componentSupertype.runtimeType));
         return result;
      }
   }

   private TypeToken getArraySubtype(Class subclass) {
      Class<?> subclassComponentType = subclass.getComponentType();
      if (subclassComponentType == null) {
         throw new IllegalArgumentException(subclass + " does not appear to be a subtype of " + this);
      } else {
         TypeToken<?> componentSubtype = ((TypeToken)Objects.requireNonNull(this.getComponentType())).getSubtype(subclassComponentType);
         TypeToken<? extends T> result = of(newArrayClassOrGenericArrayType(componentSubtype.runtimeType));
         return result;
      }
   }

   private Type resolveTypeArgsForSubclass(Class subclass) {
      if (!(this.runtimeType instanceof Class) || subclass.getTypeParameters().length != 0 && this.getRawType().getTypeParameters().length == 0) {
         TypeToken<?> genericSubtype = toGenericType(subclass);
         Type supertypeWithArgsFromSubtype = genericSubtype.getSupertype(this.getRawType()).runtimeType;
         return (new TypeResolver()).where(supertypeWithArgsFromSubtype, this.runtimeType).resolveType(genericSubtype.runtimeType);
      } else {
         return subclass;
      }
   }

   private static Type newArrayClassOrGenericArrayType(Type componentType) {
      return Types.JavaVersion.JAVA7.newArrayType(componentType);
   }

   public class TypeSet extends ForwardingSet implements Serializable {
      @CheckForNull
      private transient ImmutableSet types;
      private static final long serialVersionUID = 0L;

      TypeSet() {
      }

      public TypeSet interfaces() {
         return TypeToken.this.new InterfaceSet(this);
      }

      public TypeSet classes() {
         return TypeToken.this.new ClassSet();
      }

      protected Set delegate() {
         ImmutableSet<TypeToken<? super T>> filteredTypes = this.types;
         if (filteredTypes == null) {
            ImmutableList<TypeToken<? super T>> collectedTypes = TypeToken.TypeCollector.FOR_GENERIC_TYPE.collectTypes((Object)TypeToken.this);
            return this.types = FluentIterable.from((Iterable)collectedTypes).filter((Predicate)TypeToken.TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
         } else {
            return filteredTypes;
         }
      }

      public Set rawTypes() {
         ImmutableList<Class<? super T>> collectedTypes = TypeToken.TypeCollector.FOR_RAW_TYPE.collectTypes((Iterable)TypeToken.this.getRawTypes());
         return ImmutableSet.copyOf((Collection)collectedTypes);
      }
   }

   private final class InterfaceSet extends TypeSet {
      private final transient TypeSet allTypes;
      @CheckForNull
      private transient ImmutableSet interfaces;
      private static final long serialVersionUID = 0L;

      InterfaceSet(TypeSet allTypes) {
         this.allTypes = allTypes;
      }

      protected Set delegate() {
         ImmutableSet<TypeToken<? super T>> result = this.interfaces;
         return result == null ? (this.interfaces = FluentIterable.from((Iterable)this.allTypes).filter((Predicate)TypeToken.TypeFilter.INTERFACE_ONLY).toSet()) : result;
      }

      public TypeSet interfaces() {
         return this;
      }

      public Set rawTypes() {
         ImmutableList<Class<? super T>> collectedTypes = TypeToken.TypeCollector.FOR_RAW_TYPE.collectTypes((Iterable)TypeToken.this.getRawTypes());
         return FluentIterable.from((Iterable)collectedTypes).filter(Class::isInterface).toSet();
      }

      public TypeSet classes() {
         throw new UnsupportedOperationException("interfaces().classes() not supported.");
      }

      private Object readResolve() {
         return TypeToken.this.getTypes().interfaces();
      }
   }

   private final class ClassSet extends TypeSet {
      @CheckForNull
      private transient ImmutableSet classes;
      private static final long serialVersionUID = 0L;

      private ClassSet() {
      }

      protected Set delegate() {
         ImmutableSet<TypeToken<? super T>> result = this.classes;
         if (result == null) {
            ImmutableList<TypeToken<? super T>> collectedTypes = TypeToken.TypeCollector.FOR_GENERIC_TYPE.classesOnly().collectTypes((Object)TypeToken.this);
            return this.classes = FluentIterable.from((Iterable)collectedTypes).filter((Predicate)TypeToken.TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
         } else {
            return result;
         }
      }

      public TypeSet classes() {
         return this;
      }

      public Set rawTypes() {
         ImmutableList<Class<? super T>> collectedTypes = TypeToken.TypeCollector.FOR_RAW_TYPE.classesOnly().collectTypes((Iterable)TypeToken.this.getRawTypes());
         return ImmutableSet.copyOf((Collection)collectedTypes);
      }

      public TypeSet interfaces() {
         throw new UnsupportedOperationException("classes().interfaces() not supported.");
      }

      private Object readResolve() {
         return TypeToken.this.getTypes().classes();
      }
   }

   private static enum TypeFilter implements Predicate {
      IGNORE_TYPE_VARIABLE_OR_WILDCARD {
         public boolean apply(TypeToken type) {
            return !(type.runtimeType instanceof TypeVariable) && !(type.runtimeType instanceof WildcardType);
         }
      },
      INTERFACE_ONLY {
         public boolean apply(TypeToken type) {
            return type.getRawType().isInterface();
         }
      };

      private TypeFilter() {
      }

      // $FF: synthetic method
      private static TypeFilter[] $values() {
         return new TypeFilter[]{IGNORE_TYPE_VARIABLE_OR_WILDCARD, INTERFACE_ONLY};
      }
   }

   private static class Bounds {
      private final Type[] bounds;
      private final boolean target;

      Bounds(Type[] bounds, boolean target) {
         this.bounds = bounds;
         this.target = target;
      }

      boolean isSubtypeOf(Type supertype) {
         for(Type bound : this.bounds) {
            if (TypeToken.of(bound).isSubtypeOf(supertype) == this.target) {
               return this.target;
            }
         }

         return !this.target;
      }

      boolean isSupertypeOf(Type subtype) {
         TypeToken<?> type = TypeToken.of(subtype);

         for(Type bound : this.bounds) {
            if (type.isSubtypeOf(bound) == this.target) {
               return this.target;
            }
         }

         return !this.target;
      }
   }

   private static final class SimpleTypeToken extends TypeToken {
      private static final long serialVersionUID = 0L;

      SimpleTypeToken(Type type) {
         super(type, null);
      }
   }

   private abstract static class TypeCollector {
      static final TypeCollector FOR_GENERIC_TYPE = new TypeCollector() {
         Class getRawType(TypeToken type) {
            return type.getRawType();
         }

         Iterable getInterfaces(TypeToken type) {
            return type.getGenericInterfaces();
         }

         @CheckForNull
         TypeToken getSuperclass(TypeToken type) {
            return type.getGenericSuperclass();
         }
      };
      static final TypeCollector FOR_RAW_TYPE = new TypeCollector() {
         Class getRawType(Class type) {
            return type;
         }

         Iterable getInterfaces(Class type) {
            return Arrays.asList(type.getInterfaces());
         }

         @CheckForNull
         Class getSuperclass(Class type) {
            return type.getSuperclass();
         }
      };

      private TypeCollector() {
      }

      final TypeCollector classesOnly() {
         return new ForwardingTypeCollector(this) {
            Iterable getInterfaces(Object type) {
               return ImmutableSet.of();
            }

            ImmutableList collectTypes(Iterable types) {
               ImmutableList.Builder<K> builder = ImmutableList.builder();

               for(Object type : types) {
                  if (!this.getRawType(type).isInterface()) {
                     builder.add(type);
                  }
               }

               return super.collectTypes(builder.build());
            }
         };
      }

      final ImmutableList collectTypes(Object type) {
         return this.collectTypes((Iterable)ImmutableList.of(type));
      }

      ImmutableList collectTypes(Iterable types) {
         Map<K, Integer> map = Maps.newHashMap();

         for(Object type : types) {
            this.collectTypes(type, map);
         }

         return sortKeysByValue(map, Ordering.natural().reverse());
      }

      @CanIgnoreReturnValue
      private int collectTypes(Object type, Map map) {
         Integer existing = (Integer)map.get(type);
         if (existing != null) {
            return existing;
         } else {
            int aboveMe = this.getRawType(type).isInterface() ? 1 : 0;

            for(Object interfaceType : this.getInterfaces(type)) {
               aboveMe = Math.max(aboveMe, this.collectTypes(interfaceType, map));
            }

            K superclass = (K)this.getSuperclass(type);
            if (superclass != null) {
               aboveMe = Math.max(aboveMe, this.collectTypes(superclass, map));
            }

            map.put(type, aboveMe + 1);
            return aboveMe + 1;
         }
      }

      private static ImmutableList sortKeysByValue(final Map map, final Comparator valueComparator) {
         Ordering<K> keyOrdering = new Ordering() {
            public int compare(Object left, Object right) {
               return valueComparator.compare(Objects.requireNonNull(map.get(left)), Objects.requireNonNull(map.get(right)));
            }
         };
         return keyOrdering.immutableSortedCopy(map.keySet());
      }

      abstract Class getRawType(Object type);

      abstract Iterable getInterfaces(Object type);

      @CheckForNull
      abstract Object getSuperclass(Object type);

      private static class ForwardingTypeCollector extends TypeCollector {
         private final TypeCollector delegate;

         ForwardingTypeCollector(TypeCollector delegate) {
            this.delegate = delegate;
         }

         Class getRawType(Object type) {
            return this.delegate.getRawType(type);
         }

         Iterable getInterfaces(Object type) {
            return this.delegate.getInterfaces(type);
         }

         @CheckForNull
         Object getSuperclass(Object type) {
            return this.delegate.getSuperclass(type);
         }
      }
   }
}
