package com.fasterxml.jackson.module.scala.introspect;

import scala.collection.immutable.LazyList;
import scala.package.;

public final class LazyListSupport$ {
   public static final LazyListSupport$ MODULE$ = new LazyListSupport$();

   public LazyList empty() {
      return .MODULE$.LazyList().empty();
   }

   public LazyList fromArray(final Object array) {
      return (LazyList)scala.Predef..MODULE$.genericWrapArray(array).to(scala.collection.IterableFactory..MODULE$.toFactory(.MODULE$.LazyList()));
   }

   private LazyListSupport$() {
   }
}
