package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.Version;
import scala.Some;
import scala.Tuple2;

public final class VersionExtractor$ {
   public static final VersionExtractor$ MODULE$ = new VersionExtractor$();

   public Some unapply(final Version v) {
      return new Some(new Tuple2.mcII.sp(v.getMajorVersion(), v.getMinorVersion()));
   }

   private VersionExtractor$() {
   }
}
