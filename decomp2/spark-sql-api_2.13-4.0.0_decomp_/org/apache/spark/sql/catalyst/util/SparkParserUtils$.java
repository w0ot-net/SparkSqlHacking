package org.apache.spark.sql.catalyst.util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Function0;
import scala.Option;

public final class SparkParserUtils$ implements SparkParserUtils {
   public static final SparkParserUtils$ MODULE$ = new SparkParserUtils$();

   static {
      SparkParserUtils.$init$(MODULE$);
   }

   public String unescapeSQLString(final String b) {
      return SparkParserUtils.unescapeSQLString$(this, b);
   }

   public String source(final ParserRuleContext ctx) {
      return SparkParserUtils.source$(this, ctx);
   }

   public String string(final Token token) {
      return SparkParserUtils.string$(this, (Token)token);
   }

   public String string(final TerminalNode node) {
      return SparkParserUtils.string$(this, (TerminalNode)node);
   }

   public Origin position(final Token token) {
      return SparkParserUtils.position$(this, token);
   }

   public Object withOrigin(final ParserRuleContext ctx, final Option sqlText, final Function0 f) {
      return SparkParserUtils.withOrigin$(this, ctx, sqlText, f);
   }

   public Option withOrigin$default$2() {
      return SparkParserUtils.withOrigin$default$2$(this);
   }

   public Origin positionAndText(final Token startToken, final Token stopToken, final String sqlText, final Option objectType, final Option objectName) {
      return SparkParserUtils.positionAndText$(this, startToken, stopToken, sqlText, objectType, objectName);
   }

   public String command(final ParserRuleContext ctx) {
      return SparkParserUtils.command$(this, ctx);
   }

   private SparkParserUtils$() {
   }
}
