package org.datanucleus.store.rdbms.sql.method;

import java.util.Collection;
import java.util.List;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.BooleanLiteral;
import org.datanucleus.store.rdbms.sql.expression.CollectionExpression;
import org.datanucleus.store.rdbms.sql.expression.CollectionLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class CollectionIsEmptyMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060015", new Object[]{"isEmpty", "CollectionExpression"}));
      } else if (!(expr instanceof CollectionLiteral)) {
         AbstractMemberMetaData mmd = ((CollectionExpression)expr).getJavaTypeMapping().getMemberMetaData();
         if (mmd.isSerialized()) {
            throw new NucleusUserException("Cannot perform Collection.isEmpty when the collection is being serialised");
         } else {
            ApiAdapter api = this.stmt.getRDBMSManager().getApiAdapter();
            Class elementType = this.clr.classForName(mmd.getCollection().getElementType());
            if (!api.isPersistable(elementType) && mmd.getJoinMetaData() == null) {
               throw new NucleusUserException("Cannot perform Collection.isEmpty when the collection<Non-Persistable> is not in a join table");
            } else {
               SQLExpression sizeExpr = this.exprFactory.invokeMethod(this.stmt, Collection.class.getName(), "size", expr, args);
               JavaTypeMapping mapping = this.exprFactory.getMappingForType(Integer.class, true);
               SQLExpression zeroExpr = this.exprFactory.newLiteral(this.stmt, mapping, 0);
               return sizeExpr.eq(zeroExpr);
            }
         }
      } else {
         Collection coll = (Collection)((CollectionLiteral)expr).getValue();
         boolean isEmpty = coll == null || coll.size() == 0;
         JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, false);
         return new BooleanLiteral(this.stmt, m, isEmpty ? Boolean.TRUE : Boolean.FALSE);
      }
   }
}
