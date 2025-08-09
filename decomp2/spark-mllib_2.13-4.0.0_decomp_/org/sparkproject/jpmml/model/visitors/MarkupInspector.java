package org.sparkproject.jpmml.model.visitors;

import java.util.ArrayList;
import java.util.List;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.jpmml.model.MarkupException;

public abstract class MarkupInspector extends AbstractVisitor {
   private List exceptions = new ArrayList();

   public void applyTo(Visitable visitable) {
      super.applyTo(visitable);
      List<E> exceptions = this.getExceptions();
      if (!exceptions.isEmpty()) {
         throw (MarkupException)exceptions.get(0);
      }
   }

   protected void report(MarkupException exception) {
      this.exceptions.add(exception);
   }

   public List getExceptions() {
      return this.exceptions;
   }
}
