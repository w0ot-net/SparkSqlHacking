package org.apache.spark.sql.catalyst.parser;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.spark.sql.errors.QueryParsingErrors$;
import scala.Function1;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class PostProcessor$ extends SqlBaseParserBaseListener implements Product, Serializable {
   public static final PostProcessor$ MODULE$ = new PostProcessor$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public void exitErrorIdent(final SqlBaseParser.ErrorIdentContext ctx) {
      String ident = ctx.getParent().getText();
      throw QueryParsingErrors$.MODULE$.invalidIdentifierError(ident, ctx);
   }

   public void exitUnquotedIdentifier(final SqlBaseParser.UnquotedIdentifierContext ctx) {
      String ident = ctx.getText();
      if (.MODULE$.exists$extension(scala.Predef..MODULE$.augmentString(ident), (c) -> BoxesRunTime.boxToBoolean($anonfun$exitUnquotedIdentifier$1(BoxesRunTime.unboxToChar(c))))) {
         throw QueryParsingErrors$.MODULE$.invalidIdentifierError(ident, ctx);
      }
   }

   public void exitQuotedIdentifier(final SqlBaseParser.QuotedIdentifierContext ctx) {
      if (ctx.BACKQUOTED_IDENTIFIER() != null) {
         this.replaceTokenByIdentifier(ctx, 1, (token) -> {
            token.setText(token.getText().replace("``", "`"));
            return token;
         });
      } else if (ctx.DOUBLEQUOTED_STRING() != null) {
         this.replaceTokenByIdentifier(ctx, 1, (token) -> {
            token.setText(token.getText().replace("\"\"", "\""));
            return token;
         });
      }
   }

   public void exitBackQuotedIdentifier(final SqlBaseParser.BackQuotedIdentifierContext ctx) {
      this.replaceTokenByIdentifier(ctx, 1, (token) -> {
         token.setText(token.getText().replace("``", "`"));
         return token;
      });
   }

   public void exitNonReserved(final SqlBaseParser.NonReservedContext ctx) {
      this.replaceTokenByIdentifier(ctx, 0, (x) -> (CommonToken)scala.Predef..MODULE$.identity(x));
   }

   private void replaceTokenByIdentifier(final ParserRuleContext ctx, final int stripMargins, final Function1 f) {
      ParserRuleContext parent = ctx.getParent();
      parent.removeLastChild();
      Token token = (Token)ctx.getChild(0).getPayload();
      CommonToken newToken = new CommonToken(new Pair(token.getTokenSource(), token.getInputStream()), SqlBaseParser.IDENTIFIER, token.getChannel(), token.getStartIndex() + stripMargins, token.getStopIndex() - stripMargins);
      parent.addChild(new TerminalNodeImpl((Token)f.apply(newToken)));
   }

   private Function1 replaceTokenByIdentifier$default$3(final ParserRuleContext ctx, final int stripMargins) {
      return (x) -> (CommonToken)scala.Predef..MODULE$.identity(x);
   }

   public String productPrefix() {
      return "PostProcessor";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PostProcessor$;
   }

   public int hashCode() {
      return -1456621646;
   }

   public String toString() {
      return "PostProcessor";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PostProcessor$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$exitUnquotedIdentifier$1(final char c) {
      return (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && c != '_';
   }

   private PostProcessor$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
