package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import scala.collection.convert.AsJavaConverters;
import scala.collection.convert.AsScalaConverters;
import scala.collection.mutable.Buffer;

/** @deprecated */
public final class JavaConverters$ implements AsJavaConverters, AsScalaConverters {
   public static final JavaConverters$ MODULE$ = new JavaConverters$();

   static {
      JavaConverters$ var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public Iterator asScala(final java.util.Iterator i) {
      return AsScalaConverters.asScala$(this, (java.util.Iterator)i);
   }

   public Iterator asScala(final Enumeration e) {
      return AsScalaConverters.asScala$(this, (Enumeration)e);
   }

   public Iterable asScala(final java.lang.Iterable i) {
      return AsScalaConverters.asScala$(this, (java.lang.Iterable)i);
   }

   public Iterable asScala(final Collection c) {
      return AsScalaConverters.asScala$(this, (Collection)c);
   }

   public Buffer asScala(final List l) {
      return AsScalaConverters.asScala$(this, (List)l);
   }

   public scala.collection.mutable.Set asScala(final java.util.Set s) {
      return AsScalaConverters.asScala$(this, (java.util.Set)s);
   }

   public scala.collection.mutable.Map asScala(final java.util.Map m) {
      return AsScalaConverters.asScala$(this, (java.util.Map)m);
   }

   public scala.collection.concurrent.Map asScala(final ConcurrentMap m) {
      return AsScalaConverters.asScala$(this, (ConcurrentMap)m);
   }

   public scala.collection.mutable.Map asScala(final Dictionary d) {
      return AsScalaConverters.asScala$(this, (Dictionary)d);
   }

   public scala.collection.mutable.Map asScala(final Properties p) {
      return AsScalaConverters.asScala$(this, (Properties)p);
   }

   public java.util.Iterator asJava(final Iterator i) {
      return AsJavaConverters.asJava$(this, (Iterator)i);
   }

   public Enumeration asJavaEnumeration(final Iterator i) {
      return AsJavaConverters.asJavaEnumeration$(this, i);
   }

   public java.lang.Iterable asJava(final Iterable i) {
      return AsJavaConverters.asJava$(this, (Iterable)i);
   }

   public Collection asJavaCollection(final Iterable i) {
      return AsJavaConverters.asJavaCollection$(this, i);
   }

   public List asJava(final Buffer b) {
      return AsJavaConverters.asJava$(this, (Buffer)b);
   }

   public List asJava(final scala.collection.mutable.Seq s) {
      return AsJavaConverters.asJava$(this, (scala.collection.mutable.Seq)s);
   }

   public List asJava(final Seq s) {
      return AsJavaConverters.asJava$(this, (Seq)s);
   }

   public java.util.Set asJava(final scala.collection.mutable.Set s) {
      return AsJavaConverters.asJava$(this, (scala.collection.mutable.Set)s);
   }

   public java.util.Set asJava(final Set s) {
      return AsJavaConverters.asJava$(this, (Set)s);
   }

   public java.util.Map asJava(final scala.collection.mutable.Map m) {
      return AsJavaConverters.asJava$(this, (scala.collection.mutable.Map)m);
   }

   public Dictionary asJavaDictionary(final scala.collection.mutable.Map m) {
      return AsJavaConverters.asJavaDictionary$(this, m);
   }

   public java.util.Map asJava(final Map m) {
      return AsJavaConverters.asJava$(this, (Map)m);
   }

   public ConcurrentMap asJava(final scala.collection.concurrent.Map m) {
      return AsJavaConverters.asJava$(this, (scala.collection.concurrent.Map)m);
   }

   /** @deprecated */
   public java.util.Iterator asJavaIterator(final Iterator i) {
      return AsJavaConverters.asJava$(this, (Iterator)i);
   }

   /** @deprecated */
   public java.lang.Iterable asJavaIterable(final Iterable i) {
      return AsJavaConverters.asJava$(this, (Iterable)i);
   }

   /** @deprecated */
   public List bufferAsJavaList(final Buffer b) {
      return AsJavaConverters.asJava$(this, (Buffer)b);
   }

   /** @deprecated */
   public List mutableSeqAsJavaList(final scala.collection.mutable.Seq s) {
      return AsJavaConverters.asJava$(this, (scala.collection.mutable.Seq)s);
   }

   /** @deprecated */
   public List seqAsJavaList(final Seq s) {
      return AsJavaConverters.asJava$(this, (Seq)s);
   }

   /** @deprecated */
   public java.util.Set mutableSetAsJavaSet(final scala.collection.mutable.Set s) {
      return AsJavaConverters.asJava$(this, (scala.collection.mutable.Set)s);
   }

   /** @deprecated */
   public java.util.Set setAsJavaSet(final Set s) {
      return AsJavaConverters.asJava$(this, (Set)s);
   }

   /** @deprecated */
   public java.util.Map mutableMapAsJavaMap(final scala.collection.mutable.Map m) {
      return AsJavaConverters.asJava$(this, (scala.collection.mutable.Map)m);
   }

   /** @deprecated */
   public java.util.Map mapAsJavaMap(final Map m) {
      return AsJavaConverters.asJava$(this, (Map)m);
   }

   /** @deprecated */
   public ConcurrentMap mapAsJavaConcurrentMap(final scala.collection.concurrent.Map m) {
      return AsJavaConverters.asJava$(this, (scala.collection.concurrent.Map)m);
   }

   /** @deprecated */
   public Iterator asScalaIterator(final java.util.Iterator i) {
      return AsScalaConverters.asScala$(this, (java.util.Iterator)i);
   }

   /** @deprecated */
   public Iterator enumerationAsScalaIterator(final Enumeration i) {
      return AsScalaConverters.asScala$(this, (Enumeration)i);
   }

   /** @deprecated */
   public Iterable iterableAsScalaIterable(final java.lang.Iterable i) {
      return AsScalaConverters.asScala$(this, (java.lang.Iterable)i);
   }

   /** @deprecated */
   public Iterable collectionAsScalaIterable(final Collection i) {
      return AsScalaConverters.asScala$(this, (Collection)i);
   }

   /** @deprecated */
   public Buffer asScalaBuffer(final List l) {
      return AsScalaConverters.asScala$(this, (List)l);
   }

   /** @deprecated */
   public scala.collection.mutable.Set asScalaSet(final java.util.Set s) {
      return AsScalaConverters.asScala$(this, (java.util.Set)s);
   }

   /** @deprecated */
   public scala.collection.mutable.Map mapAsScalaMap(final java.util.Map m) {
      return AsScalaConverters.asScala$(this, (java.util.Map)m);
   }

   /** @deprecated */
   public scala.collection.concurrent.Map mapAsScalaConcurrentMap(final ConcurrentMap m) {
      return AsScalaConverters.asScala$(this, (ConcurrentMap)m);
   }

   /** @deprecated */
   public scala.collection.mutable.Map dictionaryAsScalaMap(final Dictionary p) {
      return AsScalaConverters.asScala$(this, (Dictionary)p);
   }

   /** @deprecated */
   public scala.collection.mutable.Map propertiesAsScalaMap(final Properties p) {
      return AsScalaConverters.asScala$(this, (Properties)p);
   }

   public JavaConverters.AsJava asJavaIteratorConverter(final Iterator i) {
      return new JavaConverters.AsJava(() -> MODULE$.asJavaIterator(i));
   }

   public JavaConverters.AsJavaEnumeration asJavaEnumerationConverter(final Iterator i) {
      return new JavaConverters.AsJavaEnumeration(i);
   }

   public JavaConverters.AsJava asJavaIterableConverter(final Iterable i) {
      return new JavaConverters.AsJava(() -> MODULE$.asJavaIterable(i));
   }

   public JavaConverters.AsJavaCollection asJavaCollectionConverter(final Iterable i) {
      return new JavaConverters.AsJavaCollection(i);
   }

   public JavaConverters.AsJava bufferAsJavaListConverter(final Buffer b) {
      return new JavaConverters.AsJava(() -> MODULE$.bufferAsJavaList(b));
   }

   public JavaConverters.AsJava mutableSeqAsJavaListConverter(final scala.collection.mutable.Seq b) {
      return new JavaConverters.AsJava(() -> MODULE$.mutableSeqAsJavaList(b));
   }

   public JavaConverters.AsJava seqAsJavaListConverter(final Seq b) {
      return new JavaConverters.AsJava(() -> MODULE$.seqAsJavaList(b));
   }

   public JavaConverters.AsJava mutableSetAsJavaSetConverter(final scala.collection.mutable.Set s) {
      return new JavaConverters.AsJava(() -> MODULE$.mutableSetAsJavaSet(s));
   }

   public JavaConverters.AsJava setAsJavaSetConverter(final Set s) {
      return new JavaConverters.AsJava(() -> MODULE$.setAsJavaSet(s));
   }

   public JavaConverters.AsJava mutableMapAsJavaMapConverter(final scala.collection.mutable.Map m) {
      return new JavaConverters.AsJava(() -> MODULE$.mutableMapAsJavaMap(m));
   }

   public JavaConverters.AsJavaDictionary asJavaDictionaryConverter(final scala.collection.mutable.Map m) {
      return new JavaConverters.AsJavaDictionary(m);
   }

   public JavaConverters.AsJava mapAsJavaMapConverter(final Map m) {
      return new JavaConverters.AsJava(() -> MODULE$.mapAsJavaMap(m));
   }

   public JavaConverters.AsJava mapAsJavaConcurrentMapConverter(final scala.collection.concurrent.Map m) {
      return new JavaConverters.AsJava(() -> MODULE$.mapAsJavaConcurrentMap(m));
   }

   public JavaConverters.AsScala asScalaIteratorConverter(final java.util.Iterator i) {
      return new JavaConverters.AsScala(() -> MODULE$.asScalaIterator(i));
   }

   public JavaConverters.AsScala enumerationAsScalaIteratorConverter(final Enumeration i) {
      return new JavaConverters.AsScala(() -> MODULE$.enumerationAsScalaIterator(i));
   }

   public JavaConverters.AsScala iterableAsScalaIterableConverter(final java.lang.Iterable i) {
      return new JavaConverters.AsScala(() -> MODULE$.iterableAsScalaIterable(i));
   }

   public JavaConverters.AsScala collectionAsScalaIterableConverter(final Collection i) {
      return new JavaConverters.AsScala(() -> MODULE$.collectionAsScalaIterable(i));
   }

   public JavaConverters.AsScala asScalaBufferConverter(final List l) {
      return new JavaConverters.AsScala(() -> MODULE$.asScalaBuffer(l));
   }

   public JavaConverters.AsScala asScalaSetConverter(final java.util.Set s) {
      return new JavaConverters.AsScala(() -> MODULE$.asScalaSet(s));
   }

   public JavaConverters.AsScala mapAsScalaMapConverter(final java.util.Map m) {
      return new JavaConverters.AsScala(() -> MODULE$.mapAsScalaMap(m));
   }

   public JavaConverters.AsScala mapAsScalaConcurrentMapConverter(final ConcurrentMap m) {
      return new JavaConverters.AsScala(() -> MODULE$.mapAsScalaConcurrentMap(m));
   }

   public JavaConverters.AsScala dictionaryAsScalaMapConverter(final Dictionary p) {
      return new JavaConverters.AsScala(() -> MODULE$.dictionaryAsScalaMap(p));
   }

   public JavaConverters.AsScala propertiesAsScalaMapConverter(final Properties p) {
      return new JavaConverters.AsScala(() -> MODULE$.propertiesAsScalaMap(p));
   }

   private JavaConverters$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
