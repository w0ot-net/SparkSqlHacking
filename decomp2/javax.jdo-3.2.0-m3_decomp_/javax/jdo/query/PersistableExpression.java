package javax.jdo.query;

public interface PersistableExpression extends Expression {
   Expression jdoObjectId();

   Expression jdoVersion();
}
