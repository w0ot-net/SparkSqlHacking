package com.twitter.chill.config;

import scala.Predef.;
import scala.collection.immutable.Map;

public final class ScalaMapConfig$ {
   public static final ScalaMapConfig$ MODULE$ = new ScalaMapConfig$();

   public ScalaMapConfig apply(final Map m) {
      return new ScalaMapConfig(m);
   }

   public ScalaMapConfig empty() {
      return new ScalaMapConfig(.MODULE$.Map().empty());
   }

   private ScalaMapConfig$() {
   }
}
