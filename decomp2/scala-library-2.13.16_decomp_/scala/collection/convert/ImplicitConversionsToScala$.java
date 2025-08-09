package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;

/** @deprecated */
public final class ImplicitConversionsToScala$ implements ToScalaImplicits {
   public static final ImplicitConversionsToScala$ MODULE$ = new ImplicitConversionsToScala$();

   static {
      ImplicitConversionsToScala$ var10000 = MODULE$;
   }

   public Iterator iterator$u0020asScala(final java.util.Iterator it) {
      return ToScalaImplicits.iterator$u0020asScala$(this, it);
   }

   public Iterator enumeration$u0020AsScalaIterator(final Enumeration i) {
      return ToScalaImplicits.enumeration$u0020AsScalaIterator$(this, i);
   }

   public Iterable iterable$u0020AsScalaIterable(final java.lang.Iterable i) {
      return ToScalaImplicits.iterable$u0020AsScalaIterable$(this, i);
   }

   public Iterable collection$u0020AsScalaIterable(final Collection i) {
      return ToScalaImplicits.collection$u0020AsScalaIterable$(this, i);
   }

   public Buffer list$u0020asScalaBuffer(final List l) {
      return ToScalaImplicits.list$u0020asScalaBuffer$(this, l);
   }

   public Set set$u0020asScala(final java.util.Set s) {
      return ToScalaImplicits.set$u0020asScala$(this, s);
   }

   public Map map$u0020AsScala(final java.util.Map m) {
      return ToScalaImplicits.map$u0020AsScala$(this, m);
   }

   public scala.collection.concurrent.Map map$u0020AsScalaConcurrentMap(final ConcurrentMap m) {
      return ToScalaImplicits.map$u0020AsScalaConcurrentMap$(this, m);
   }

   public Map dictionary$u0020AsScalaMap(final Dictionary p) {
      return ToScalaImplicits.dictionary$u0020AsScalaMap$(this, p);
   }

   public Map properties$u0020AsScalaMap(final Properties p) {
      return ToScalaImplicits.properties$u0020AsScalaMap$(this, p);
   }

   private ImplicitConversionsToScala$() {
   }
}
