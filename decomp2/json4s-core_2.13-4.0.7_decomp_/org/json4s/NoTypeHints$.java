package org.json4s;

import java.io.Serializable;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.package.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class NoTypeHints$ implements TypeHints, Product, Serializable {
   public static final NoTypeHints$ MODULE$ = new NoTypeHints$();
   private static final List hints;

   static {
      TypeHints.$init$(MODULE$);
      Product.$init$(MODULE$);
      hints = .MODULE$.Nil();
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String typeHintFieldName() {
      return TypeHints.typeHintFieldName$(this);
   }

   public boolean isTypeHintField(final Tuple2 f, final Class parent) {
      return TypeHints.isTypeHintField$(this, f, parent);
   }

   public Option typeHintFieldNameForHint(final String hint, final Class parent) {
      return TypeHints.typeHintFieldNameForHint$(this, hint, parent);
   }

   public Option typeHintFieldNameForClass(final Class clazz) {
      return TypeHints.typeHintFieldNameForClass$(this, clazz);
   }

   public boolean containsHint(final Class clazz) {
      return TypeHints.containsHint$(this, clazz);
   }

   public PartialFunction deserialize() {
      return TypeHints.deserialize$(this);
   }

   public PartialFunction serialize() {
      return TypeHints.serialize$(this);
   }

   public List components() {
      return TypeHints.components$(this);
   }

   public TypeHints $plus(final TypeHints hints) {
      return TypeHints.$plus$(this, hints);
   }

   public List hints() {
      return hints;
   }

   public None hintFor(final Class clazz) {
      return scala.None..MODULE$;
   }

   public None classFor(final String hint, final Class parent) {
      return scala.None..MODULE$;
   }

   public boolean shouldExtractHints(final Class clazz) {
      return false;
   }

   public String productPrefix() {
      return "NoTypeHints";
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
      return x$1 instanceof NoTypeHints$;
   }

   public int hashCode() {
      return -559641903;
   }

   public String toString() {
      return "NoTypeHints";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NoTypeHints$.class);
   }

   private NoTypeHints$() {
   }
}
