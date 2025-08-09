package javax.jdo.query;

public interface CharacterExpression extends ComparableExpression {
   CharacterExpression toLowerCase();

   CharacterExpression toUpperCase();

   CharacterExpression neg();

   CharacterExpression com();
}
