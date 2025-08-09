package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Seq;

/** @deprecated */
public final class ImplicitConversions$ implements ToScalaImplicits, ToJavaImplicits {
   public static final ImplicitConversions$ MODULE$ = new ImplicitConversions$();

   static {
      ImplicitConversions$ var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public Iterator iterator$u0020asJava(final scala.collection.Iterator it) {
      return ToJavaImplicits.iterator$u0020asJava$(this, it);
   }

   public Enumeration enumeration$u0020asJava(final scala.collection.Iterator it) {
      return ToJavaImplicits.enumeration$u0020asJava$(this, it);
   }

   public Iterable iterable$u0020asJava(final scala.collection.Iterable i) {
      return ToJavaImplicits.iterable$u0020asJava$(this, i);
   }

   public Collection collection$u0020asJava(final scala.collection.Iterable it) {
      return ToJavaImplicits.collection$u0020asJava$(this, it);
   }

   public List buffer$u0020AsJavaList(final Buffer b) {
      return ToJavaImplicits.buffer$u0020AsJavaList$(this, b);
   }

   public List mutableSeq$u0020AsJavaList(final Seq seq) {
      return ToJavaImplicits.mutableSeq$u0020AsJavaList$(this, seq);
   }

   public List seq$u0020AsJavaList(final scala.collection.Seq seq) {
      return ToJavaImplicits.seq$u0020AsJavaList$(this, seq);
   }

   public Set mutableSet$u0020AsJavaSet(final scala.collection.mutable.Set s) {
      return ToJavaImplicits.mutableSet$u0020AsJavaSet$(this, s);
   }

   public Set set$u0020AsJavaSet(final scala.collection.Set s) {
      return ToJavaImplicits.set$u0020AsJavaSet$(this, s);
   }

   public Map mutableMap$u0020AsJavaMap(final scala.collection.mutable.Map m) {
      return ToJavaImplicits.mutableMap$u0020AsJavaMap$(this, m);
   }

   public Dictionary dictionary$u0020asJava(final scala.collection.mutable.Map m) {
      return ToJavaImplicits.dictionary$u0020asJava$(this, m);
   }

   public Map map$u0020AsJavaMap(final scala.collection.Map m) {
      return ToJavaImplicits.map$u0020AsJavaMap$(this, m);
   }

   public ConcurrentMap map$u0020AsJavaConcurrentMap(final scala.collection.concurrent.Map m) {
      return ToJavaImplicits.map$u0020AsJavaConcurrentMap$(this, m);
   }

   public scala.collection.Iterator iterator$u0020asScala(final Iterator it) {
      return ToScalaImplicits.iterator$u0020asScala$(this, it);
   }

   public scala.collection.Iterator enumeration$u0020AsScalaIterator(final Enumeration i) {
      return ToScalaImplicits.enumeration$u0020AsScalaIterator$(this, i);
   }

   public scala.collection.Iterable iterable$u0020AsScalaIterable(final Iterable i) {
      return ToScalaImplicits.iterable$u0020AsScalaIterable$(this, i);
   }

   public scala.collection.Iterable collection$u0020AsScalaIterable(final Collection i) {
      return ToScalaImplicits.collection$u0020AsScalaIterable$(this, i);
   }

   public Buffer list$u0020asScalaBuffer(final List l) {
      return ToScalaImplicits.list$u0020asScalaBuffer$(this, l);
   }

   public scala.collection.mutable.Set set$u0020asScala(final Set s) {
      return ToScalaImplicits.set$u0020asScala$(this, s);
   }

   public scala.collection.mutable.Map map$u0020AsScala(final Map m) {
      return ToScalaImplicits.map$u0020AsScala$(this, m);
   }

   public scala.collection.concurrent.Map map$u0020AsScalaConcurrentMap(final ConcurrentMap m) {
      return ToScalaImplicits.map$u0020AsScalaConcurrentMap$(this, m);
   }

   public scala.collection.mutable.Map dictionary$u0020AsScalaMap(final Dictionary p) {
      return ToScalaImplicits.dictionary$u0020AsScalaMap$(this, p);
   }

   public scala.collection.mutable.Map properties$u0020AsScalaMap(final Properties p) {
      return ToScalaImplicits.properties$u0020AsScalaMap$(this, p);
   }

   private ImplicitConversions$() {
   }
}
