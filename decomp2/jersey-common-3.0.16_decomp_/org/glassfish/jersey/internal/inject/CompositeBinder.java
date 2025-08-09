package org.glassfish.jersey.internal.inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CompositeBinder extends AbstractBinder {
   private Collection installed = new ArrayList();

   private CompositeBinder(Collection installed) {
      this.installed = installed;
   }

   public static AbstractBinder wrap(Collection binders) {
      return new CompositeBinder(binders);
   }

   public static AbstractBinder wrap(Binder... binders) {
      return new CompositeBinder(Arrays.asList(binders));
   }

   public void configure() {
      this.install((AbstractBinder[])this.installed.toArray(new AbstractBinder[0]));
   }
}
