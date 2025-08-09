package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.ModuleSerializationProxy;

public final class IvyProperties$ extends AbstractFunction5 implements Serializable {
   public static final IvyProperties$ MODULE$ = new IvyProperties$();

   public final String toString() {
      return "IvyProperties";
   }

   public IvyProperties apply(final String packagesExclusions, final String packages, final String repositories, final String ivyRepoPath, final String ivySettingsPath) {
      return new IvyProperties(packagesExclusions, packages, repositories, ivyRepoPath, ivySettingsPath);
   }

   public Option unapply(final IvyProperties x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.packagesExclusions(), x$0.packages(), x$0.repositories(), x$0.ivyRepoPath(), x$0.ivySettingsPath())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IvyProperties$.class);
   }

   private IvyProperties$() {
   }
}
