package org.sparkproject.dmg.pmml;

public interface HasExpression {
   Expression requireExpression();

   Expression getExpression();

   PMMLObject setExpression(Expression var1);
}
