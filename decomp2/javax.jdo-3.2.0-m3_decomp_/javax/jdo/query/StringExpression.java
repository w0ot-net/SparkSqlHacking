package javax.jdo.query;

public interface StringExpression extends ComparableExpression {
   StringExpression add(Expression var1);

   CharacterExpression charAt(int var1);

   CharacterExpression charAt(NumericExpression var1);

   BooleanExpression endsWith(StringExpression var1);

   BooleanExpression endsWith(String var1);

   BooleanExpression equalsIgnoreCase(StringExpression var1);

   BooleanExpression equalsIgnoreCase(String var1);

   NumericExpression indexOf(StringExpression var1);

   NumericExpression indexOf(String var1);

   NumericExpression indexOf(StringExpression var1, NumericExpression var2);

   NumericExpression indexOf(String var1, NumericExpression var2);

   NumericExpression indexOf(String var1, int var2);

   NumericExpression indexOf(StringExpression var1, int var2);

   NumericExpression length();

   BooleanExpression matches(StringExpression var1);

   BooleanExpression matches(String var1);

   BooleanExpression startsWith(StringExpression var1);

   BooleanExpression startsWith(String var1);

   StringExpression substring(NumericExpression var1);

   StringExpression substring(int var1);

   StringExpression substring(NumericExpression var1, NumericExpression var2);

   StringExpression substring(int var1, int var2);

   StringExpression toLowerCase();

   StringExpression toUpperCase();

   StringExpression trim();
}
