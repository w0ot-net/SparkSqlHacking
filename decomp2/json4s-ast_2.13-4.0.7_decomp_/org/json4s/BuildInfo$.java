package org.json4s;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class BuildInfo$ implements Product, Serializable {
   public static final BuildInfo$ MODULE$ = new BuildInfo$();
   private static final String name;
   private static final String organization;
   private static final String version;
   private static final String scalaVersion;
   private static final String sbtVersion;
   private static final String toString;

   static {
      Product.$init$(MODULE$);
      name = "json4s-ast";
      organization = "org.json4s";
      version = "4.0.7";
      scalaVersion = "2.13.8";
      sbtVersion = "1.9.7";
      toString = .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("name: %s, organization: %s, version: %s, scalaVersion: %s, sbtVersion: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{MODULE$.name(), MODULE$.organization(), MODULE$.version(), MODULE$.scalaVersion(), MODULE$.sbtVersion()}));
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return name;
   }

   public String organization() {
      return organization;
   }

   public String version() {
      return version;
   }

   public String scalaVersion() {
      return scalaVersion;
   }

   public String sbtVersion() {
      return sbtVersion;
   }

   public String toString() {
      return toString;
   }

   public String productPrefix() {
      return "BuildInfo";
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
      return x$1 instanceof BuildInfo$;
   }

   public int hashCode() {
      return 602658844;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BuildInfo$.class);
   }

   private BuildInfo$() {
   }
}
