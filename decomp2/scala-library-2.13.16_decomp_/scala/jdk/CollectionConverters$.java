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
import scala.collection.convert.AsJavaExtensions;
import scala.collection.convert.AsScalaExtensions;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Seq;

public final class CollectionConverters$ implements AsJavaExtensions, AsScalaExtensions {
   public static final CollectionConverters$ MODULE$ = new CollectionConverters$();

   static {
      CollectionConverters$ var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public AsScalaExtensions.IteratorHasAsScala IteratorHasAsScala(final Iterator i) {
      return AsScalaExtensions.IteratorHasAsScala$(this, i);
   }

   public AsScalaExtensions.EnumerationHasAsScala EnumerationHasAsScala(final Enumeration e) {
      return AsScalaExtensions.EnumerationHasAsScala$(this, e);
   }

   public AsScalaExtensions.IterableHasAsScala IterableHasAsScala(final Iterable i) {
      return AsScalaExtensions.IterableHasAsScala$(this, i);
   }

   public AsScalaExtensions.CollectionHasAsScala CollectionHasAsScala(final Collection c) {
      return AsScalaExtensions.CollectionHasAsScala$(this, c);
   }

   public AsScalaExtensions.ListHasAsScala ListHasAsScala(final List l) {
      return AsScalaExtensions.ListHasAsScala$(this, l);
   }

   public AsScalaExtensions.SetHasAsScala SetHasAsScala(final Set s) {
      return AsScalaExtensions.SetHasAsScala$(this, s);
   }

   public AsScalaExtensions.MapHasAsScala MapHasAsScala(final Map m) {
      return AsScalaExtensions.MapHasAsScala$(this, m);
   }

   public AsScalaExtensions.ConcurrentMapHasAsScala ConcurrentMapHasAsScala(final ConcurrentMap m) {
      return AsScalaExtensions.ConcurrentMapHasAsScala$(this, m);
   }

   public AsScalaExtensions.DictionaryHasAsScala DictionaryHasAsScala(final Dictionary d) {
      return AsScalaExtensions.DictionaryHasAsScala$(this, d);
   }

   public AsScalaExtensions.PropertiesHasAsScala PropertiesHasAsScala(final Properties i) {
      return AsScalaExtensions.PropertiesHasAsScala$(this, i);
   }

   public AsJavaExtensions.IteratorHasAsJava IteratorHasAsJava(final scala.collection.Iterator i) {
      return AsJavaExtensions.IteratorHasAsJava$(this, i);
   }

   public AsJavaExtensions.IterableHasAsJava IterableHasAsJava(final scala.collection.Iterable i) {
      return AsJavaExtensions.IterableHasAsJava$(this, i);
   }

   public AsJavaExtensions.BufferHasAsJava BufferHasAsJava(final Buffer b) {
      return AsJavaExtensions.BufferHasAsJava$(this, b);
   }

   public AsJavaExtensions.MutableSeqHasAsJava MutableSeqHasAsJava(final Seq s) {
      return AsJavaExtensions.MutableSeqHasAsJava$(this, s);
   }

   public AsJavaExtensions.SeqHasAsJava SeqHasAsJava(final scala.collection.Seq s) {
      return AsJavaExtensions.SeqHasAsJava$(this, s);
   }

   public AsJavaExtensions.MutableSetHasAsJava MutableSetHasAsJava(final scala.collection.mutable.Set s) {
      return AsJavaExtensions.MutableSetHasAsJava$(this, s);
   }

   public AsJavaExtensions.SetHasAsJava SetHasAsJava(final scala.collection.Set s) {
      return AsJavaExtensions.SetHasAsJava$(this, s);
   }

   public AsJavaExtensions.MutableMapHasAsJava MutableMapHasAsJava(final scala.collection.mutable.Map m) {
      return AsJavaExtensions.MutableMapHasAsJava$(this, m);
   }

   public AsJavaExtensions.MapHasAsJava MapHasAsJava(final scala.collection.Map m) {
      return AsJavaExtensions.MapHasAsJava$(this, m);
   }

   public AsJavaExtensions.ConcurrentMapHasAsJava ConcurrentMapHasAsJava(final scala.collection.concurrent.Map m) {
      return AsJavaExtensions.ConcurrentMapHasAsJava$(this, m);
   }

   private CollectionConverters$() {
   }
}
