package javax.jdo;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.jdo.query.BooleanExpression;
import javax.jdo.query.CharacterExpression;
import javax.jdo.query.CollectionExpression;
import javax.jdo.query.DateExpression;
import javax.jdo.query.DateTimeExpression;
import javax.jdo.query.Expression;
import javax.jdo.query.ListExpression;
import javax.jdo.query.MapExpression;
import javax.jdo.query.NumericExpression;
import javax.jdo.query.OrderExpression;
import javax.jdo.query.PersistableExpression;
import javax.jdo.query.StringExpression;
import javax.jdo.query.TimeExpression;

public interface JDOQLTypedQuery extends Serializable, Closeable {
   String QUERY_CLASS_PREFIX = "Q";

   PersistableExpression candidate();

   Expression parameter(String var1, Class var2);

   StringExpression stringParameter(String var1);

   CharacterExpression characterParameter(String var1);

   NumericExpression numericParameter(String var1);

   DateExpression dateParameter(String var1);

   TimeExpression timeParameter(String var1);

   DateTimeExpression datetimeParameter(String var1);

   CollectionExpression collectionParameter(String var1);

   MapExpression mapParameter(String var1);

   ListExpression listParameter(String var1);

   Expression variable(String var1, Class var2);

   JDOQLTypedQuery setCandidates(Collection var1);

   JDOQLTypedQuery excludeSubclasses();

   JDOQLTypedQuery includeSubclasses();

   JDOQLTypedQuery filter(BooleanExpression var1);

   JDOQLTypedQuery groupBy(Expression... var1);

   JDOQLTypedQuery having(Expression var1);

   JDOQLTypedQuery orderBy(OrderExpression... var1);

   JDOQLTypedQuery result(boolean var1, Expression... var2);

   JDOQLTypedQuery range(NumericExpression var1, NumericExpression var2);

   JDOQLTypedQuery range(long var1, long var3);

   JDOQLTypedQuery range(Expression var1, Expression var2);

   JDOQLTypedSubquery subquery(String var1);

   JDOQLTypedSubquery subquery(Class var1, String var2);

   JDOQLTypedQuery setParameters(Map var1);

   JDOQLTypedQuery setParameter(Expression var1, Object var2);

   JDOQLTypedQuery setParameter(String var1, Object var2);

   List executeList();

   Object executeUnique();

   List executeResultList(Class var1);

   Object executeResultUnique(Class var1);

   List executeResultList();

   Object executeResultUnique();

   long deletePersistentAll();

   Integer getDatastoreReadTimeoutMillis();

   JDOQLTypedQuery datastoreReadTimeoutMillis(Integer var1);

   Integer getDatastoreWriteTimeoutMillis();

   JDOQLTypedQuery datastoreWriteTimeoutMillis(Integer var1);

   Boolean getSerializeRead();

   JDOQLTypedQuery serializeRead(Boolean var1);

   boolean isUnmodifiable();

   JDOQLTypedQuery unmodifiable();

   boolean getIgnoreCache();

   JDOQLTypedQuery ignoreCache(boolean var1);

   JDOQLTypedQuery extension(String var1, Object var2);

   JDOQLTypedQuery extensions(Map var1);

   JDOQLTypedQuery saveAsNamedQuery(String var1);

   PersistenceManager getPersistenceManager();

   FetchPlan getFetchPlan();

   void cancelAll();

   void cancel(Thread var1);

   void close(Object var1);

   void closeAll();

   String toString();
}
