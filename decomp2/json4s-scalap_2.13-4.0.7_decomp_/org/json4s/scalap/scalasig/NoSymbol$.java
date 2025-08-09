package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.None;
import scala.Product;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.Nil;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class NoSymbol$ implements Symbol, Product, Serializable {
   public static final NoSymbol$ MODULE$ = new NoSymbol$();

   static {
      Flags.$init$(MODULE$);
      Symbol.$init$(MODULE$);
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String path() {
      return Symbol.path$(this);
   }

   public boolean isImplicit() {
      return Flags.isImplicit$(this);
   }

   public boolean isFinal() {
      return Flags.isFinal$(this);
   }

   public boolean isPrivate() {
      return Flags.isPrivate$(this);
   }

   public boolean isProtected() {
      return Flags.isProtected$(this);
   }

   public boolean isSealed() {
      return Flags.isSealed$(this);
   }

   public boolean isOverride() {
      return Flags.isOverride$(this);
   }

   public boolean isCase() {
      return Flags.isCase$(this);
   }

   public boolean isAbstract() {
      return Flags.isAbstract$(this);
   }

   public boolean isDeferred() {
      return Flags.isDeferred$(this);
   }

   public boolean isMethod() {
      return Flags.isMethod$(this);
   }

   public boolean isModule() {
      return Flags.isModule$(this);
   }

   public boolean isInterface() {
      return Flags.isInterface$(this);
   }

   public boolean isMutable() {
      return Flags.isMutable$(this);
   }

   public boolean isParam() {
      return Flags.isParam$(this);
   }

   public boolean isPackage() {
      return Flags.isPackage$(this);
   }

   public boolean isDeprecated() {
      return Flags.isDeprecated$(this);
   }

   public boolean isCovariant() {
      return Flags.isCovariant$(this);
   }

   public boolean isCaptured() {
      return Flags.isCaptured$(this);
   }

   public boolean isByNameParam() {
      return Flags.isByNameParam$(this);
   }

   public boolean isContravariant() {
      return Flags.isContravariant$(this);
   }

   public boolean isLabel() {
      return Flags.isLabel$(this);
   }

   public boolean isInConstructor() {
      return Flags.isInConstructor$(this);
   }

   public boolean isAbstractOverride() {
      return Flags.isAbstractOverride$(this);
   }

   public boolean isLocal() {
      return Flags.isLocal$(this);
   }

   public boolean isJava() {
      return Flags.isJava$(this);
   }

   public boolean isSynthetic() {
      return Flags.isSynthetic$(this);
   }

   public boolean isStable() {
      return Flags.isStable$(this);
   }

   public boolean isStatic() {
      return Flags.isStatic$(this);
   }

   public boolean isCaseAccessor() {
      return Flags.isCaseAccessor$(this);
   }

   public boolean isTrait() {
      return Flags.isTrait$(this);
   }

   public boolean isBridge() {
      return Flags.isBridge$(this);
   }

   public boolean isAccessor() {
      return Flags.isAccessor$(this);
   }

   public boolean isSuperAccessor() {
      return Flags.isSuperAccessor$(this);
   }

   public boolean isParamAccessor() {
      return Flags.isParamAccessor$(this);
   }

   public boolean isModuleVar() {
      return Flags.isModuleVar$(this);
   }

   public boolean isMonomorphic() {
      return Flags.isMonomorphic$(this);
   }

   public boolean isLazy() {
      return Flags.isLazy$(this);
   }

   public boolean isError() {
      return Flags.isError$(this);
   }

   public boolean isOverloaded() {
      return Flags.isOverloaded$(this);
   }

   public boolean isLifted() {
      return Flags.isLifted$(this);
   }

   public boolean isMixedIn() {
      return Flags.isMixedIn$(this);
   }

   public boolean isExistential() {
      return Flags.isExistential$(this);
   }

   public boolean isExpandedName() {
      return Flags.isExpandedName$(this);
   }

   public boolean isImplementationClass() {
      return Flags.isImplementationClass$(this);
   }

   public boolean isPreSuper() {
      return Flags.isPreSuper$(this);
   }

   public String name() {
      return "<no symbol>";
   }

   public None parent() {
      return .MODULE$;
   }

   public boolean hasFlag(final long flag) {
      return false;
   }

   public Nil children() {
      return scala.package..MODULE$.Nil();
   }

   public String productPrefix() {
      return "NoSymbol";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof NoSymbol$;
   }

   public int hashCode() {
      return 720223961;
   }

   public String toString() {
      return "NoSymbol";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NoSymbol$.class);
   }

   private NoSymbol$() {
   }
}
