package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.concurrent.Map;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019:Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\t1#S7qY&\u001c\u0017\u000e^\"p]Z,'o]5p]NT!!\u0002\u0004\u0002\u000f\r|gN^3si*\u0011q\u0001C\u0001\u000bG>dG.Z2uS>t'\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011A\"A\u0007\u0002\t\t\u0019\u0012*\u001c9mS\u000eLGoQ8om\u0016\u00148/[8ogN!\u0011aD\n\u0017!\t\u0001\u0012#D\u0001\t\u0013\t\u0011\u0002B\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0019QI!!\u0006\u0003\u0003!Q{7kY1mC&k\u0007\u000f\\5dSR\u001c\bC\u0001\u0007\u0018\u0013\tABAA\bU_*\u000bg/Y%na2L7-\u001b;t\u0003\u0019a\u0014N\\5u}Q\t1\u0002\u000b\u0004\u00029}\u0001#e\t\t\u0003!uI!A\b\u0005\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0003\u0005\nA&V:fA\u0001\u001c8-\u00197b])$7NL\"pY2,7\r^5p]\u000e{gN^3si\u0016\u00148\u000f\u0019\u0011j]N$X-\u00193\u0002\u000bMLgnY3\"\u0003\u0011\naA\r\u00182g9\u0002\u0004F\u0002\u0001\u001d?\u0001\u00123\u0005"
)
public final class ImplicitConversions {
   public static ConcurrentMap map$u0020AsJavaConcurrentMap(final Map m) {
      return ImplicitConversions$.MODULE$.map$u0020AsJavaConcurrentMap(m);
   }

   public static java.util.Map map$u0020AsJavaMap(final scala.collection.Map m) {
      return ImplicitConversions$.MODULE$.map$u0020AsJavaMap(m);
   }

   public static Dictionary dictionary$u0020asJava(final scala.collection.mutable.Map m) {
      return ImplicitConversions$.MODULE$.dictionary$u0020asJava(m);
   }

   public static java.util.Map mutableMap$u0020AsJavaMap(final scala.collection.mutable.Map m) {
      return ImplicitConversions$.MODULE$.mutableMap$u0020AsJavaMap(m);
   }

   public static Set set$u0020AsJavaSet(final scala.collection.Set s) {
      return ImplicitConversions$.MODULE$.set$u0020AsJavaSet(s);
   }

   public static Set mutableSet$u0020AsJavaSet(final scala.collection.mutable.Set s) {
      return ImplicitConversions$.MODULE$.mutableSet$u0020AsJavaSet(s);
   }

   public static List seq$u0020AsJavaList(final Seq seq) {
      return ImplicitConversions$.MODULE$.seq$u0020AsJavaList(seq);
   }

   public static List mutableSeq$u0020AsJavaList(final scala.collection.mutable.Seq seq) {
      return ImplicitConversions$.MODULE$.mutableSeq$u0020AsJavaList(seq);
   }

   public static List buffer$u0020AsJavaList(final Buffer b) {
      return ImplicitConversions$.MODULE$.buffer$u0020AsJavaList(b);
   }

   public static Collection collection$u0020asJava(final Iterable it) {
      return ImplicitConversions$.MODULE$.collection$u0020asJava(it);
   }

   public static java.lang.Iterable iterable$u0020asJava(final Iterable i) {
      return ImplicitConversions$.MODULE$.iterable$u0020asJava(i);
   }

   public static Enumeration enumeration$u0020asJava(final Iterator it) {
      return ImplicitConversions$.MODULE$.enumeration$u0020asJava(it);
   }

   public static java.util.Iterator iterator$u0020asJava(final Iterator it) {
      return ImplicitConversions$.MODULE$.iterator$u0020asJava(it);
   }

   public static scala.collection.mutable.Map properties$u0020AsScalaMap(final Properties p) {
      return ImplicitConversions$.MODULE$.properties$u0020AsScalaMap(p);
   }

   public static scala.collection.mutable.Map dictionary$u0020AsScalaMap(final Dictionary p) {
      return ImplicitConversions$.MODULE$.dictionary$u0020AsScalaMap(p);
   }

   public static Map map$u0020AsScalaConcurrentMap(final ConcurrentMap m) {
      return ImplicitConversions$.MODULE$.map$u0020AsScalaConcurrentMap(m);
   }

   public static scala.collection.mutable.Map map$u0020AsScala(final java.util.Map m) {
      return ImplicitConversions$.MODULE$.map$u0020AsScala(m);
   }

   public static scala.collection.mutable.Set set$u0020asScala(final Set s) {
      return ImplicitConversions$.MODULE$.set$u0020asScala(s);
   }

   public static Buffer list$u0020asScalaBuffer(final List l) {
      return ImplicitConversions$.MODULE$.list$u0020asScalaBuffer(l);
   }

   public static Iterable collection$u0020AsScalaIterable(final Collection i) {
      return ImplicitConversions$.MODULE$.collection$u0020AsScalaIterable(i);
   }

   public static Iterable iterable$u0020AsScalaIterable(final java.lang.Iterable i) {
      return ImplicitConversions$.MODULE$.iterable$u0020AsScalaIterable(i);
   }

   public static Iterator enumeration$u0020AsScalaIterator(final Enumeration i) {
      return ImplicitConversions$.MODULE$.enumeration$u0020AsScalaIterator(i);
   }

   public static Iterator iterator$u0020asScala(final java.util.Iterator it) {
      return ImplicitConversions$.MODULE$.iterator$u0020asScala(it);
   }
}
