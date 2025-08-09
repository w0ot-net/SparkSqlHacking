package scala.jdk.javaapi;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.convert.AsJavaConverters;
import scala.collection.convert.AsScalaConverters;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.Seq;
import scala.collection.mutable.Set;

public final class CollectionConverters$ implements AsJavaConverters, AsScalaConverters {
   public static final CollectionConverters$ MODULE$ = new CollectionConverters$();

   static {
      CollectionConverters$ var10000 = MODULE$;
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

   public Set asScala(final java.util.Set s) {
      return AsScalaConverters.asScala$(this, (java.util.Set)s);
   }

   public Map asScala(final java.util.Map m) {
      return AsScalaConverters.asScala$(this, (java.util.Map)m);
   }

   public scala.collection.concurrent.Map asScala(final ConcurrentMap m) {
      return AsScalaConverters.asScala$(this, (ConcurrentMap)m);
   }

   public Map asScala(final Dictionary d) {
      return AsScalaConverters.asScala$(this, (Dictionary)d);
   }

   public Map asScala(final Properties p) {
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

   public List asJava(final Seq s) {
      return AsJavaConverters.asJava$(this, (Seq)s);
   }

   public List asJava(final scala.collection.Seq s) {
      return AsJavaConverters.asJava$(this, (scala.collection.Seq)s);
   }

   public java.util.Set asJava(final Set s) {
      return AsJavaConverters.asJava$(this, (Set)s);
   }

   public java.util.Set asJava(final scala.collection.Set s) {
      return AsJavaConverters.asJava$(this, (scala.collection.Set)s);
   }

   public java.util.Map asJava(final Map m) {
      return AsJavaConverters.asJava$(this, (Map)m);
   }

   public Dictionary asJavaDictionary(final Map m) {
      return AsJavaConverters.asJavaDictionary$(this, m);
   }

   public java.util.Map asJava(final scala.collection.Map m) {
      return AsJavaConverters.asJava$(this, (scala.collection.Map)m);
   }

   public ConcurrentMap asJava(final scala.collection.concurrent.Map m) {
      return AsJavaConverters.asJava$(this, (scala.collection.concurrent.Map)m);
   }

   private CollectionConverters$() {
   }
}
