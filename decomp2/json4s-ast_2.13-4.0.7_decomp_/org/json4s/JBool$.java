package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JBool$ implements Serializable {
   public static final JBool$ MODULE$ = new JBool$();
   private static final JBool True = new JBool(true);
   private static final JBool False = new JBool(false);

   public JBool apply(final boolean value) {
      return value ? this.True() : this.False();
   }

   public JBool True() {
      return True;
   }

   public JBool False() {
      return False;
   }

   public Option unapply(final JBool x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToBoolean(x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JBool$.class);
   }

   private JBool$() {
   }
}
