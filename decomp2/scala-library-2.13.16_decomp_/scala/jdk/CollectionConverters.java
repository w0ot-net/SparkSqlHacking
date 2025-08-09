package scala.jdk;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Seq;
import scala.collection.convert.AsJavaExtensions;
import scala.collection.convert.AsScalaExtensions;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y9Qa\u0001\u0003\t\u0002%1Qa\u0003\u0003\t\u00021AQ\u0001H\u0001\u0005\u0002u\tAcQ8mY\u0016\u001cG/[8o\u0007>tg/\u001a:uKJ\u001c(BA\u0003\u0007\u0003\rQGm\u001b\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001\u0001C\u0001\u0006\u0002\u001b\u0005!!\u0001F\"pY2,7\r^5p]\u000e{gN^3si\u0016\u00148o\u0005\u0003\u0002\u001bEI\u0002C\u0001\b\u0010\u001b\u00051\u0011B\u0001\t\u0007\u0005\u0019\te.\u001f*fMB\u0011!cF\u0007\u0002')\u0011A#F\u0001\bG>tg/\u001a:u\u0015\t1b!\u0001\u0006d_2dWm\u0019;j_:L!\u0001G\n\u0003!\u0005\u001b(*\u0019<b\u000bb$XM\\:j_:\u001c\bC\u0001\n\u001b\u0013\tY2CA\tBgN\u001b\u0017\r\\1FqR,gn]5p]N\fa\u0001P5oSRtD#A\u0005"
)
public final class CollectionConverters {
   public static AsScalaExtensions.PropertiesHasAsScala PropertiesHasAsScala(final Properties i) {
      return CollectionConverters$.MODULE$.PropertiesHasAsScala(i);
   }

   public static AsScalaExtensions.DictionaryHasAsScala DictionaryHasAsScala(final Dictionary d) {
      return CollectionConverters$.MODULE$.DictionaryHasAsScala(d);
   }

   public static AsScalaExtensions.ConcurrentMapHasAsScala ConcurrentMapHasAsScala(final ConcurrentMap m) {
      return CollectionConverters$.MODULE$.ConcurrentMapHasAsScala(m);
   }

   public static AsScalaExtensions.MapHasAsScala MapHasAsScala(final Map m) {
      return CollectionConverters$.MODULE$.MapHasAsScala(m);
   }

   public static AsScalaExtensions.SetHasAsScala SetHasAsScala(final Set s) {
      return CollectionConverters$.MODULE$.SetHasAsScala(s);
   }

   public static AsScalaExtensions.ListHasAsScala ListHasAsScala(final List l) {
      return CollectionConverters$.MODULE$.ListHasAsScala(l);
   }

   public static AsScalaExtensions.CollectionHasAsScala CollectionHasAsScala(final Collection c) {
      return CollectionConverters$.MODULE$.CollectionHasAsScala(c);
   }

   public static AsScalaExtensions.IterableHasAsScala IterableHasAsScala(final Iterable i) {
      return CollectionConverters$.MODULE$.IterableHasAsScala(i);
   }

   public static AsScalaExtensions.EnumerationHasAsScala EnumerationHasAsScala(final Enumeration e) {
      return CollectionConverters$.MODULE$.EnumerationHasAsScala(e);
   }

   public static AsScalaExtensions.IteratorHasAsScala IteratorHasAsScala(final Iterator i) {
      return CollectionConverters$.MODULE$.IteratorHasAsScala(i);
   }

   public static AsJavaExtensions.ConcurrentMapHasAsJava ConcurrentMapHasAsJava(final scala.collection.concurrent.Map m) {
      return CollectionConverters$.MODULE$.ConcurrentMapHasAsJava(m);
   }

   public static AsJavaExtensions.MapHasAsJava MapHasAsJava(final scala.collection.Map m) {
      return CollectionConverters$.MODULE$.MapHasAsJava(m);
   }

   public static AsJavaExtensions.MutableMapHasAsJava MutableMapHasAsJava(final scala.collection.mutable.Map m) {
      return CollectionConverters$.MODULE$.MutableMapHasAsJava(m);
   }

   public static AsJavaExtensions.SetHasAsJava SetHasAsJava(final scala.collection.Set s) {
      return CollectionConverters$.MODULE$.SetHasAsJava(s);
   }

   public static AsJavaExtensions.MutableSetHasAsJava MutableSetHasAsJava(final scala.collection.mutable.Set s) {
      return CollectionConverters$.MODULE$.MutableSetHasAsJava(s);
   }

   public static AsJavaExtensions.SeqHasAsJava SeqHasAsJava(final Seq s) {
      return CollectionConverters$.MODULE$.SeqHasAsJava(s);
   }

   public static AsJavaExtensions.MutableSeqHasAsJava MutableSeqHasAsJava(final scala.collection.mutable.Seq s) {
      return CollectionConverters$.MODULE$.MutableSeqHasAsJava(s);
   }

   public static AsJavaExtensions.BufferHasAsJava BufferHasAsJava(final Buffer b) {
      return CollectionConverters$.MODULE$.BufferHasAsJava(b);
   }

   public static AsJavaExtensions.IterableHasAsJava IterableHasAsJava(final scala.collection.Iterable i) {
      return CollectionConverters$.MODULE$.IterableHasAsJava(i);
   }

   public static AsJavaExtensions.IteratorHasAsJava IteratorHasAsJava(final scala.collection.Iterator i) {
      return CollectionConverters$.MODULE$.IteratorHasAsJava(i);
   }
}
