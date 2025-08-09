package org.apache.spark.sql.catalyst.parser;

import java.io.Serializable;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.trees.Origin$;
import scala.MatchError;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class ParseErrorListener$ extends BaseErrorListener implements Product, Serializable {
   public static final ParseErrorListener$ MODULE$ = new ParseErrorListener$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public void syntaxError(final Recognizer recognizer, final Object offendingSymbol, final int line, final int charPositionInLine, final String msg, final RecognitionException e) {
      Tuple2 var10000;
      if (offendingSymbol instanceof CommonToken var13) {
         Origin start = new Origin(new Some(BoxesRunTime.boxToInteger(line)), new Some(BoxesRunTime.boxToInteger(var13.getCharPositionInLine())), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
         int length = var13.getStopIndex() - var13.getStartIndex() + 1;
         Origin stop = new Origin(new Some(BoxesRunTime.boxToInteger(line)), new Some(BoxesRunTime.boxToInteger(var13.getCharPositionInLine() + length)), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
         var10000 = new Tuple2(start, stop);
      } else {
         Origin start = new Origin(new Some(BoxesRunTime.boxToInteger(line)), new Some(BoxesRunTime.boxToInteger(charPositionInLine)), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
         var10000 = new Tuple2(start, start);
      }

      Tuple2 var11 = var10000;
      if (var11 != null) {
         Origin start = (Origin)var11._1();
         Origin stop = (Origin)var11._2();
         Tuple2 var10 = new Tuple2(start, stop);
         Origin start = (Origin)var10._1();
         Origin stop = (Origin)var10._2();
         if (e instanceof SparkRecognitionException) {
            SparkRecognitionException var23 = (SparkRecognitionException)e;
            if (var23.errorClass().isDefined()) {
               throw new ParseException(.MODULE$, start, stop, (String)var23.errorClass().get(), var23.messageParameters());
            }
         }

         throw new ParseException(.MODULE$, start, stop, "PARSE_SYNTAX_ERROR", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("error"), msg), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("hint"), "")}))));
      } else {
         throw new MatchError(var11);
      }
   }

   public String productPrefix() {
      return "ParseErrorListener";
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
      return x$1 instanceof ParseErrorListener$;
   }

   public int hashCode() {
      return 900103945;
   }

   public String toString() {
      return "ParseErrorListener";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParseErrorListener$.class);
   }

   private ParseErrorListener$() {
   }
}
