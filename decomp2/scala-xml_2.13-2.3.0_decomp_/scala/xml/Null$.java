package scala.xml;

import scala.Function1;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Iterator.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class Null$ extends MetaData implements Product {
   public static final Null$ MODULE$ = new Null$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Iterator iterator() {
      return .MODULE$.empty();
   }

   public int size() {
      return 0;
   }

   public MetaData append(final MetaData m, final NamespaceBinding scope) {
      return m;
   }

   public NamespaceBinding append$default$2() {
      return TopScope$.MODULE$;
   }

   public MetaData filter(final Function1 f) {
      return this;
   }

   public MetaData copy(final MetaData next) {
      return next;
   }

   public scala.runtime.Null getNamespace(final Node owner) {
      return null;
   }

   public boolean hasNext() {
      return false;
   }

   public scala.runtime.Null next() {
      return null;
   }

   public scala.runtime.Null key() {
      return null;
   }

   public scala.runtime.Null value() {
      return null;
   }

   public boolean isPrefixed() {
      return false;
   }

   public int length() {
      return 0;
   }

   public int length(final int i) {
      return i;
   }

   public boolean strict_$eq$eq(final Equality other) {
      if (other instanceof MetaData) {
         MetaData var4 = (MetaData)other;
         return var4.length() == 0;
      } else {
         return false;
      }
   }

   public Seq basisForHashCode() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public scala.runtime.Null apply(final String namespace, final NamespaceBinding scope, final String key) {
      return null;
   }

   public Seq apply(final String key) {
      if (Utility$.MODULE$.isNameStart(scala.collection.StringOps..MODULE$.head$extension(scala.Predef..MODULE$.augmentString(key)))) {
         return null;
      } else {
         throw new IllegalArgumentException((new StringBuilder(51)).append("not a valid attribute name '").append(key).append("', so can never match !").toString());
      }
   }

   public void toString1(final scala.collection.mutable.StringBuilder sb) {
   }

   public String toString1() {
      return "";
   }

   public String toString() {
      return "";
   }

   public scala.collection.mutable.StringBuilder buildString(final scala.collection.mutable.StringBuilder sb) {
      return sb;
   }

   public boolean wellformed(final NamespaceBinding scope) {
      return true;
   }

   public Null$ remove(final String key) {
      return this;
   }

   public Null$ remove(final String namespace, final NamespaceBinding scope, final String key) {
      return this;
   }

   public String productPrefix() {
      return "Null";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Null$.class);
   }

   private Null$() {
   }
}
