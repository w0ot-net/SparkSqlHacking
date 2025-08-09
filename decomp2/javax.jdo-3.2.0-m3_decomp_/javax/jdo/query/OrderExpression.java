package javax.jdo.query;

public interface OrderExpression {
   OrderDirection getDirection();

   Expression getExpression();

   public static enum OrderDirection {
      ASC,
      DESC;
   }
}
