package org.datanucleus.query.evaluator;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class AbstractResultClassMapper {
   protected Class resultClass;

   public AbstractResultClassMapper(Class resultClass) {
      this.resultClass = resultClass;
   }

   public Collection map(final Collection inputResults, final Expression[] resultNames) {
      return (Collection)AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            String[] fieldNames = new String[resultNames.length];
            Field[] fields = new Field[fieldNames.length];

            for(int i = 0; i < fieldNames.length; ++i) {
               if (resultNames[i] instanceof PrimaryExpression) {
                  fieldNames[i] = ((PrimaryExpression)resultNames[i]).getId();
                  if (fieldNames[i].indexOf(46) > 0) {
                     int pos = fieldNames[i].lastIndexOf(46);
                     fieldNames[i] = fieldNames[i].substring(pos + 1);
                  }

                  fields[i] = AbstractResultClassMapper.this.getFieldForFieldNameInResultClass(AbstractResultClassMapper.this.resultClass, fieldNames[i]);
               } else if (resultNames[i] instanceof ParameterExpression) {
                  fieldNames[i] = ((ParameterExpression)resultNames[i]).getId();
                  fields[i] = AbstractResultClassMapper.this.getFieldForFieldNameInResultClass(AbstractResultClassMapper.this.resultClass, fieldNames[i]);
               } else {
                  fieldNames[i] = resultNames[i].getAlias();
                  fields[i] = null;
               }
            }

            List outputResults = new ArrayList();

            for(Object inputResult : inputResults) {
               Object row = AbstractResultClassMapper.this.getResultForResultSetRow(inputResult, fieldNames, fields);
               outputResults.add(row);
            }

            return outputResults;
         }
      });
   }

   Object getResultForResultSetRow(Object inputResult, String[] fieldNames, Field[] fields) {
      if (this.resultClass == Object[].class) {
         return inputResult;
      } else if (QueryUtils.resultClassIsSimple(this.resultClass.getName())) {
         if (fieldNames.length == 1) {
            if (inputResult != null && !this.resultClass.isAssignableFrom(inputResult.getClass())) {
               String msg = Localiser.msg("021202", this.resultClass.getName(), inputResult.getClass().getName());
               NucleusLogger.QUERY.error(msg);
               throw new NucleusUserException(msg);
            } else {
               return inputResult;
            }
         } else if (fieldNames.length > 1) {
            String msg = Localiser.msg("021201", this.resultClass.getName());
            NucleusLogger.QUERY.error(msg);
            throw new NucleusUserException(msg);
         } else {
            return null;
         }
      } else if (fieldNames.length == 1 && this.resultClass.isAssignableFrom(inputResult.getClass())) {
         return inputResult;
      } else {
         Object[] fieldValues = null;
         if (inputResult instanceof Object[]) {
            fieldValues = inputResult;
         } else {
            fieldValues = new Object[]{inputResult};
         }

         Object obj = QueryUtils.createResultObjectUsingArgumentedConstructor(this.resultClass, fieldValues, (Class[])null);
         if (obj != null) {
            return obj;
         } else {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               Class[] ctr_arg_types = new Class[fieldNames.length];

               for(int i = 0; i < fieldNames.length; ++i) {
                  if (fieldValues[i] != null) {
                     ctr_arg_types[i] = fieldValues[i].getClass();
                  } else {
                     ctr_arg_types[i] = null;
                  }
               }

               NucleusLogger.QUERY.debug(Localiser.msg("021206", this.resultClass.getName(), StringUtils.objectArrayToString(ctr_arg_types)));
            }

            return QueryUtils.createResultObjectUsingDefaultConstructorAndSetters(this.resultClass, fieldNames, fields, fieldValues);
         }
      }
   }

   Field getFieldForFieldNameInResultClass(Class cls, String fieldName) {
      try {
         return cls.getDeclaredField(fieldName);
      } catch (NoSuchFieldException var4) {
         return cls.getSuperclass() != null ? this.getFieldForFieldNameInResultClass(cls.getSuperclass(), fieldName) : null;
      }
   }
}
