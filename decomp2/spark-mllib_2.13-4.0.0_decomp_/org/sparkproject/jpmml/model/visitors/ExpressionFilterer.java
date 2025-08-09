package org.sparkproject.jpmml.model.visitors;

import java.util.List;
import java.util.ListIterator;
import org.sparkproject.dmg.pmml.Apply;
import org.sparkproject.dmg.pmml.Expression;
import org.sparkproject.dmg.pmml.HasExpression;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;

public abstract class ExpressionFilterer extends AbstractVisitor {
   public abstract Expression filter(Expression var1);

   public void filterAll(List expressions) {
      ListIterator<Expression> it = expressions.listIterator();

      while(it.hasNext()) {
         it.set(this.filter((Expression)it.next()));
      }

   }

   public VisitorAction visit(PMMLObject object) {
      if (object instanceof HasExpression) {
         HasExpression<?> hasExpression = (HasExpression)object;
         hasExpression.setExpression(this.filter(hasExpression.getExpression()));
      }

      return super.visit(object);
   }

   public VisitorAction visit(Apply apply) {
      if (apply.hasExpressions()) {
         this.filterAll(apply.getExpressions());
      }

      return super.visit(apply);
   }
}
