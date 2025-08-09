package org.datanucleus.api.jdo.query;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.jdo.JDOException;
import javax.jdo.JDOQLTypedSubquery;
import javax.jdo.PersistenceManager;
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
import org.datanucleus.query.expression.VariableExpression;

public class JDOQLTypedSubqueryImpl extends AbstractJDOQLTypedQuery implements JDOQLTypedSubquery {
   private static final long serialVersionUID = 8872729615681952405L;

   public JDOQLTypedSubqueryImpl(PersistenceManager pm, Class candidateClass, String candidateAlias, JDOQLTypedQueryImpl parentQuery) {
      super(pm, candidateClass, candidateAlias);
   }

   public String getAlias() {
      return "VAR_" + this.candidateAlias.toUpperCase();
   }

   public PersistableExpression candidate() {
      String candName = this.candidateCls.getName();
      int pos = candName.lastIndexOf(46);
      String qName = candName.substring(0, pos + 1) + JDOQLTypedQueryImpl.getQueryClassNameForClassName(candName.substring(pos + 1));

      try {
         Class qClass = this.ec.getClassLoaderResolver().classForName(qName);
         Constructor ctr = qClass.getConstructor(PersistableExpression.class, String.class);
         Object candObj = ctr.newInstance(null, this.candidateAlias);
         if (candObj != null && candObj instanceof PersistableExpression) {
            return (PersistableExpression)candObj;
         } else {
            throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
         }
      } catch (NoSuchMethodException var7) {
         throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
      } catch (InvocationTargetException var8) {
         throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
      } catch (InstantiationException var9) {
         throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
      } catch (IllegalAccessException var10) {
         throw new JDOException("Class " + this.candidateCls.getName() + " has a Query class but the candidate is invalid");
      }
   }

   public JDOQLTypedSubquery filter(BooleanExpression expr) {
      this.discardCompiled();
      this.filter = (BooleanExpressionImpl)expr;
      return this;
   }

   public JDOQLTypedSubquery groupBy(Expression... exprs) {
      this.discardCompiled();
      if (exprs != null && exprs.length > 0) {
         this.grouping = new ArrayList();

         for(int i = 0; i < exprs.length; ++i) {
            this.grouping.add((ExpressionImpl)exprs[i]);
         }
      }

      return this;
   }

   public JDOQLTypedSubquery having(Expression expr) {
      this.discardCompiled();
      this.having = (ExpressionImpl)expr;
      return this;
   }

   public NumericExpression selectUnique(NumericExpression expr) {
      return (NumericExpression)this.internalSelect(expr, NumericExpressionImpl.class);
   }

   public StringExpression selectUnique(StringExpression expr) {
      return (StringExpression)this.internalSelect(expr, StringExpressionImpl.class);
   }

   public DateExpression selectUnique(DateExpression expr) {
      return (DateExpression)this.internalSelect(expr, DateExpressionImpl.class);
   }

   public DateTimeExpression selectUnique(DateTimeExpression expr) {
      return (DateTimeExpression)this.internalSelect(expr, DateTimeExpressionImpl.class);
   }

   public TimeExpression selectUnique(TimeExpression expr) {
      return (TimeExpression)this.internalSelect(expr, TimeExpressionImpl.class);
   }

   public CharacterExpression selectUnique(CharacterExpression expr) {
      return (CharacterExpression)this.internalSelect(expr, CharacterExpressionImpl.class);
   }

   public CollectionExpression select(CollectionExpression expr) {
      return (CollectionExpression)this.internalSelect(expr, CollectionExpressionImpl.class);
   }

   protected Expression internalSelect(Expression expr, Class implClass) {
      this.discardCompiled();
      this.result = new ArrayList();
      this.result.add((ExpressionImpl)expr);
      VariableExpression varExpr = new VariableExpression(this.getAlias());

      try {
         Constructor ctr = implClass.getConstructor(org.datanucleus.query.expression.Expression.class);
         return (Expression)ctr.newInstance(varExpr);
      } catch (NoSuchMethodException var5) {
         throw new JDOException("Unable to create expression of type " + expr.getClass().getName() + " since required constructor doesnt exist");
      } catch (InvocationTargetException var6) {
         throw new JDOException("Unable to create expression of type " + expr.getClass().getName() + " due to error in constructor");
      } catch (IllegalAccessException var7) {
         throw new JDOException("Unable to create expression of type " + expr.getClass().getName() + " due to error in constructor");
      } catch (InstantiationException var8) {
         throw new JDOException("Unable to create expression of type " + expr.getClass().getName() + " due to error in constructor");
      }
   }
}
