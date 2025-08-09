package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.Option;

public final class Implicits$ implements Classes, Options, Strings {
   public static final Implicits$ MODULE$ = new Implicits$();

   static {
      Classes.$init$(MODULE$);
      Options.$init$(MODULE$);
      Strings.$init$(MODULE$);
   }

   public StringW mkStringW(final Function0 x) {
      return Strings.mkStringW$(this, x);
   }

   public String unMkStringW(final StringW x) {
      return Strings.unMkStringW$(this, x);
   }

   public OptionW mkOptionW(final Option x) {
      return Options.mkOptionW$(this, x);
   }

   public Option unMkOptionW(final OptionW x) {
      return Options.unMkOptionW$(this, x);
   }

   public ClassW mkClassW(final Function0 x) {
      return Classes.mkClassW$(this, x);
   }

   public Class unMkClassW(final ClassW x) {
      return Classes.unMkClassW$(this, x);
   }

   private Implicits$() {
   }
}
