package org.apache.spark.deploy;

import java.io.Serializable;
import org.apache.spark.resource.ResourceProfile;
import scala.Option;
import scala.Some;
import scala.Tuple9;
import scala.None.;
import scala.runtime.AbstractFunction9;
import scala.runtime.ModuleSerializationProxy;

public final class ApplicationDescription$ extends AbstractFunction9 implements Serializable {
   public static final ApplicationDescription$ MODULE$ = new ApplicationDescription$();

   public Option $lessinit$greater$default$6() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$7() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$8() {
      return .MODULE$;
   }

   public String $lessinit$greater$default$9() {
      return System.getProperty("user.name", "<unknown>");
   }

   public final String toString() {
      return "ApplicationDescription";
   }

   public ApplicationDescription apply(final String name, final Option maxCores, final Command command, final String appUiUrl, final ResourceProfile defaultProfile, final Option eventLogDir, final Option eventLogCodec, final Option initialExecutorLimit, final String user) {
      return new ApplicationDescription(name, maxCores, command, appUiUrl, defaultProfile, eventLogDir, eventLogCodec, initialExecutorLimit, user);
   }

   public Option apply$default$6() {
      return .MODULE$;
   }

   public Option apply$default$7() {
      return .MODULE$;
   }

   public Option apply$default$8() {
      return .MODULE$;
   }

   public String apply$default$9() {
      return System.getProperty("user.name", "<unknown>");
   }

   public Option unapply(final ApplicationDescription x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple9(x$0.name(), x$0.maxCores(), x$0.command(), x$0.appUiUrl(), x$0.defaultProfile(), x$0.eventLogDir(), x$0.eventLogCodec(), x$0.initialExecutorLimit(), x$0.user())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ApplicationDescription$.class);
   }

   private ApplicationDescription$() {
   }
}
