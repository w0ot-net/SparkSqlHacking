package javax.jdo;

import java.io.Serializable;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.CharacterExpression;
import javax.jdo.query.CollectionExpression;
import javax.jdo.query.DateExpression;
import javax.jdo.query.DateTimeExpression;
import javax.jdo.query.Expression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;
import javax.jdo.query.StringExpression;
import javax.jdo.query.TimeExpression;

public interface JDOQLTypedSubquery extends Serializable {
   PersistableExpression candidate();

   JDOQLTypedSubquery filter(BooleanExpression var1);

   JDOQLTypedSubquery groupBy(Expression... var1);

   JDOQLTypedSubquery having(Expression var1);

   NumericExpression selectUnique(NumericExpression var1);

   StringExpression selectUnique(StringExpression var1);

   DateExpression selectUnique(DateExpression var1);

   DateTimeExpression selectUnique(DateTimeExpression var1);

   TimeExpression selectUnique(TimeExpression var1);

   CharacterExpression selectUnique(CharacterExpression var1);

   CollectionExpression select(CollectionExpression var1);
}
