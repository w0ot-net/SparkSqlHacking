package scala.jdk.javaapi;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001:Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQAH\u0001\u0005\u0002}\tAcQ8mY\u0016\u001cG/[8o\u0007>tg/\u001a:uKJ\u001c(BA\u0003\u0007\u0003\u001dQ\u0017M^1ba&T!a\u0002\u0005\u0002\u0007)$7NC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"\u0001D\u0001\u000e\u0003\u0011\u0011AcQ8mY\u0016\u001cG/[8o\u0007>tg/\u001a:uKJ\u001c8\u0003B\u0001\u0010'm\u0001\"\u0001E\t\u000e\u0003!I!A\u0005\u0005\u0003\r\u0005s\u0017PU3g!\t!\u0012$D\u0001\u0016\u0015\t1r#A\u0004d_:4XM\u001d;\u000b\u0005aA\u0011AC2pY2,7\r^5p]&\u0011!$\u0006\u0002\u0011\u0003NT\u0015M^1D_:4XM\u001d;feN\u0004\"\u0001\u0006\u000f\n\u0005u)\"!E!t'\u000e\fG.Y\"p]Z,'\u000f^3sg\u00061A(\u001b8jiz\"\u0012a\u0003"
)
public final class CollectionConverters {
   public static Map asScala(final Properties p) {
      return CollectionConverters$.MODULE$.asScala(p);
   }

   public static Map asScala(final Dictionary d) {
      return CollectionConverters$.MODULE$.asScala(d);
   }

   public static scala.collection.concurrent.Map asScala(final ConcurrentMap m) {
      return CollectionConverters$.MODULE$.asScala(m);
   }

   public static Map asScala(final java.util.Map m) {
      return CollectionConverters$.MODULE$.asScala(m);
   }

   public static Set asScala(final java.util.Set s) {
      return CollectionConverters$.MODULE$.asScala(s);
   }

   public static Buffer asScala(final List l) {
      return CollectionConverters$.MODULE$.asScala(l);
   }

   public static Iterable asScala(final Collection c) {
      return CollectionConverters$.MODULE$.asScala(c);
   }

   public static Iterable asScala(final java.lang.Iterable i) {
      return CollectionConverters$.MODULE$.asScala(i);
   }

   public static Iterator asScala(final Enumeration e) {
      return CollectionConverters$.MODULE$.asScala(e);
   }

   public static Iterator asScala(final java.util.Iterator i) {
      return CollectionConverters$.MODULE$.asScala(i);
   }

   public static ConcurrentMap asJava(final scala.collection.concurrent.Map m) {
      return CollectionConverters$.MODULE$.asJava(m);
   }

   public static java.util.Map asJava(final scala.collection.Map m) {
      return CollectionConverters$.MODULE$.asJava(m);
   }

   public static Dictionary asJavaDictionary(final Map m) {
      return CollectionConverters$.MODULE$.asJavaDictionary(m);
   }

   public static java.util.Map asJava(final Map m) {
      return CollectionConverters$.MODULE$.asJava(m);
   }

   public static java.util.Set asJava(final scala.collection.Set s) {
      return CollectionConverters$.MODULE$.asJava(s);
   }

   public static java.util.Set asJava(final Set s) {
      return CollectionConverters$.MODULE$.asJava(s);
   }

   public static List asJava(final Seq s) {
      return CollectionConverters$.MODULE$.asJava(s);
   }

   public static List asJava(final scala.collection.mutable.Seq s) {
      return CollectionConverters$.MODULE$.asJava(s);
   }

   public static List asJava(final Buffer b) {
      return CollectionConverters$.MODULE$.asJava(b);
   }

   public static Collection asJavaCollection(final Iterable i) {
      return CollectionConverters$.MODULE$.asJavaCollection(i);
   }

   public static java.lang.Iterable asJava(final Iterable i) {
      return CollectionConverters$.MODULE$.asJava(i);
   }

   public static Enumeration asJavaEnumeration(final Iterator i) {
      return CollectionConverters$.MODULE$.asJavaEnumeration(i);
   }

   public static java.util.Iterator asJava(final Iterator i) {
      return CollectionConverters$.MODULE$.asJava(i);
   }
}
