package org.apache.spark.sql.catalyst.parser;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.trees.Origin$;
import org.apache.spark.sql.errors.QueryParsingErrors$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Option.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t}a\u0001\u0002\u0013&\u0001JB\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t'\u0002\u0011\t\u0012)A\u0005\u0017\"AA\u000b\u0001BK\u0002\u0013\u0005Q\u000b\u0003\u0005a\u0001\tE\t\u0015!\u0003W\u0011\u0015\t\u0007\u0001\"\u0001c\u0011\u00151\u0007\u0001\"\u0011h\u0011\u0015!\b\u0001\"\u0011v\u0011\u0015Q\b\u0001\"\u0011|\u0011\u001d\t\t\u0001\u0001C!\u0003\u0007Aq!!\u0004\u0001\t\u0003\ny\u0001C\u0004\u0002\u001a\u0001!\t%a\u0007\t\u000f\u0005\u0015\u0002\u0001\"\u0011\u0002(!9\u0011\u0011\u0007\u0001\u0005B\u0005M\u0002bBA\u001f\u0001\u0011\u0005\u0013q\b\u0005\b\u0003\u0013\u0002A\u0011IA&\u0011\u001d\t)\u0006\u0001C\u0005\u0003/B\u0011\"!\u0018\u0001\u0003\u0003%\t!a\u0018\t\u0013\u0005\u0015\u0004!%A\u0005\u0002\u0005\u001d\u0004\"CA?\u0001E\u0005I\u0011AA@\u0011%\t\u0019\tAA\u0001\n\u0003\n)\tC\u0005\u0002\u0016\u0002\t\t\u0011\"\u0001\u0002\u0018\"I\u0011q\u0014\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0015\u0005\n\u0003[\u0003\u0011\u0011!C!\u0003_C\u0011\"!0\u0001\u0003\u0003%\t!a0\t\u0013\u0005%\u0007!!A\u0005B\u0005-\u0007\"CAh\u0001\u0005\u0005I\u0011IAi\u0011%\t\u0019\u000eAA\u0001\n\u0003\n)\u000eC\u0005\u0002X\u0002\t\t\u0011\"\u0011\u0002Z\u001eI\u0011Q\\\u0013\u0002\u0002#\u0005\u0011q\u001c\u0004\tI\u0015\n\t\u0011#\u0001\u0002b\"1\u0011M\bC\u0001\u0003oD\u0011\"a5\u001f\u0003\u0003%)%!6\t\u0013\u0005eh$!A\u0005\u0002\u0006m\b\"\u0003B\u0001=\u0005\u0005I\u0011\u0011B\u0002\u0011%\u0011)BHA\u0001\n\u0013\u00119B\u0001\rV]\u000edwn]3e\u0007>lW.\u001a8u!J|7-Z:t_JT!AJ\u0014\u0002\rA\f'o]3s\u0015\tA\u0013&\u0001\u0005dCR\fG._:u\u0015\tQ3&A\u0002tc2T!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\u0002\u0001'\u0011\u00011gN\u001f\u0011\u0005Q*T\"A\u0013\n\u0005Y*#!G*rY\n\u000b7/\u001a)beN,'OQ1tK2K7\u000f^3oKJ\u0004\"\u0001O\u001e\u000e\u0003eR\u0011AO\u0001\u0006g\u000e\fG.Y\u0005\u0003ye\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002?\r:\u0011q\b\u0012\b\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005F\na\u0001\u0010:p_Rt\u0014\"\u0001\u001e\n\u0005\u0015K\u0014a\u00029bG.\fw-Z\u0005\u0003\u000f\"\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!R\u001d\u0002\u000f\r|W.\\1oIV\t1\n\u0005\u0002M!:\u0011QJ\u0014\t\u0003\u0001fJ!aT\u001d\u0002\rA\u0013X\rZ3g\u0013\t\t&K\u0001\u0004TiJLgn\u001a\u0006\u0003\u001ff\n\u0001bY8n[\u0006tG\rI\u0001\fi>\\WM\\*ue\u0016\fW.F\u0001W!\t9f,D\u0001Y\u0015\tI&,A\u0004sk:$\u0018.\\3\u000b\u0005mc\u0016A\u0001<5\u0015\tiv&A\u0003b]Rd'/\u0003\u0002`1\n\t2i\\7n_:$vn[3o'R\u0014X-Y7\u0002\u0019Q|7.\u001a8TiJ,\u0017-\u001c\u0011\u0002\rqJg.\u001b;?)\r\u0019G-\u001a\t\u0003i\u0001AQ!S\u0003A\u0002-CQ\u0001V\u0003A\u0002Y\u000b!#\u001a=jiNKgn\u001a7f\t\u0006$\u0018\rV=qKR\u0011\u0001n\u001b\t\u0003q%L!A[\u001d\u0003\tUs\u0017\u000e\u001e\u0005\u0006Y\u001a\u0001\r!\\\u0001\u0004GRD\bC\u00018r\u001d\t!t.\u0003\u0002qK\u0005i1+\u001d7CCN,\u0007+\u0019:tKJL!A]:\u0003+MKgn\u001a7f\t\u0006$\u0018\rV=qK\u000e{g\u000e^3yi*\u0011\u0001/J\u0001\u0015KbLGoU5oO2,W\t\u001f9sKN\u001c\u0018n\u001c8\u0015\u0005!4\b\"\u00027\b\u0001\u00049\bC\u00018y\u0013\tI8OA\fTS:<G.Z#yaJ,7o]5p]\u000e{g\u000e^3yi\u0006IR\r_5u'&tw\r\\3UC\ndW-\u00133f]RLg-[3s)\tAG\u0010C\u0003m\u0011\u0001\u0007Q\u0010\u0005\u0002o}&\u0011qp\u001d\u0002\u001d'&tw\r\\3UC\ndW-\u00133f]RLg-[3s\u0007>tG/\u001a=u\u0003q)\u00070\u001b;TS:<G.\u001a$v]\u000e$\u0018n\u001c8JI\u0016tG/\u001b4jKJ$2\u0001[A\u0003\u0011\u0019a\u0017\u00021\u0001\u0002\bA\u0019a.!\u0003\n\u0007\u0005-1OA\u0010TS:<G.\u001a$v]\u000e$\u0018n\u001c8JI\u0016tG/\u001b4jKJ\u001cuN\u001c;fqR\fQ$\u001a=jiNKgn\u001a7f\u001bVdG/\u001b9beRLE-\u001a8uS\u001aLWM\u001d\u000b\u0004Q\u0006E\u0001B\u00027\u000b\u0001\u0004\t\u0019\u0002E\u0002o\u0003+I1!a\u0006t\u0005\u0001\u001a\u0016N\\4mK6+H\u000e^5qCJ$\u0018\nZ3oi&4\u0017.\u001a:D_:$X\r\u001f;\u0002+\u0015D\u0018\u000e^*j]\u001edW\rV1cY\u0016\u001c6\r[3nCR\u0019\u0001.!\b\t\r1\\\u0001\u0019AA\u0010!\rq\u0017\u0011E\u0005\u0004\u0003G\u0019(\u0001G*j]\u001edW\rV1cY\u0016\u001c6\r[3nC\u000e{g\u000e^3yi\u0006IQ\r_5u#V,'/\u001f\u000b\u0004Q\u0006%\u0002B\u00027\r\u0001\u0004\tY\u0003E\u0002o\u0003[I1!a\ft\u00051\tV/\u001a:z\u0007>tG/\u001a=u\u0003M)\u00070\u001b;TS:<G.Z*uCR,W.\u001a8u)\rA\u0017Q\u0007\u0005\u0007Y6\u0001\r!a\u000e\u0011\u00079\fI$C\u0002\u0002<M\u0014acU5oO2,7\u000b^1uK6,g\u000e^\"p]R,\u0007\u0010^\u0001\u001eKbLGoQ8na>,h\u000eZ(s'&tw\r\\3Ti\u0006$X-\\3oiR\u0019\u0001.!\u0011\t\r1t\u0001\u0019AA\"!\rq\u0017QI\u0005\u0004\u0003\u000f\u001a(\u0001I\"p[B|WO\u001c3PeNKgn\u001a7f'R\fG/Z7f]R\u001cuN\u001c;fqR\f1$\u001a=jiNKgn\u001a7f\u0007>l\u0007o\\;oIN#\u0018\r^3nK:$Hc\u00015\u0002N!1An\u0004a\u0001\u0003\u001f\u00022A\\A)\u0013\r\t\u0019f\u001d\u0002\u001f'&tw\r\\3D_6\u0004x.\u001e8e'R\fG/Z7f]R\u001cuN\u001c;fqR\fAc\u00195fG.,fn\u00197pg\u0016$7i\\7nK:$H#\u00025\u0002Z\u0005m\u0003\"\u0002+\u0011\u0001\u00041\u0006\"B%\u0011\u0001\u0004Y\u0015\u0001B2paf$RaYA1\u0003GBq!S\t\u0011\u0002\u0003\u00071\nC\u0004U#A\u0005\t\u0019\u0001,\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\u000e\u0016\u0004\u0017\u0006-4FAA7!\u0011\ty'!\u001f\u000e\u0005\u0005E$\u0002BA:\u0003k\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005]\u0014(\u0001\u0006b]:|G/\u0019;j_:LA!a\u001f\u0002r\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\u0011\u0016\u0004-\u0006-\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\bB!\u0011\u0011RAJ\u001b\t\tYI\u0003\u0003\u0002\u000e\u0006=\u0015\u0001\u00027b]\u001eT!!!%\u0002\t)\fg/Y\u0005\u0004#\u0006-\u0015\u0001\u00049s_\u0012,8\r^!sSRLXCAAM!\rA\u00141T\u0005\u0004\u0003;K$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAR\u0003S\u00032\u0001OAS\u0013\r\t9+\u000f\u0002\u0004\u0003:L\b\"CAV-\u0005\u0005\t\u0019AAM\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0017\t\u0007\u0003g\u000bI,a)\u000e\u0005\u0005U&bAA\\s\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005m\u0016Q\u0017\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002B\u0006\u001d\u0007c\u0001\u001d\u0002D&\u0019\u0011QY\u001d\u0003\u000f\t{w\u000e\\3b]\"I\u00111\u0016\r\u0002\u0002\u0003\u0007\u00111U\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\b\u00065\u0007\"CAV3\u0005\u0005\t\u0019AAM\u0003!A\u0017m\u001d5D_\u0012,GCAAM\u0003!!xn\u0015;sS:<GCAAD\u0003\u0019)\u0017/^1mgR!\u0011\u0011YAn\u0011%\tY\u000bHA\u0001\u0002\u0004\t\u0019+\u0001\rV]\u000edwn]3e\u0007>lW.\u001a8u!J|7-Z:t_J\u0004\"\u0001\u000e\u0010\u0014\u000by\t\u0019/!<\u0011\u000f\u0005\u0015\u0018\u0011^&WG6\u0011\u0011q\u001d\u0006\u00033fJA!a;\u0002h\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005=\u0018Q_\u0007\u0003\u0003cTA!a=\u0002\u0010\u0006\u0011\u0011n\\\u0005\u0004\u000f\u0006EHCAAp\u0003\u0015\t\u0007\u000f\u001d7z)\u0015\u0019\u0017Q`A\u0000\u0011\u0015I\u0015\u00051\u0001L\u0011\u0015!\u0016\u00051\u0001W\u0003\u001d)h.\u00199qYf$BA!\u0002\u0003\u0012A)\u0001Ha\u0002\u0003\f%\u0019!\u0011B\u001d\u0003\r=\u0003H/[8o!\u0015A$QB&W\u0013\r\u0011y!\u000f\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\tM!%!AA\u0002\r\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011I\u0002\u0005\u0003\u0002\n\nm\u0011\u0002\u0002B\u000f\u0003\u0017\u0013aa\u00142kK\u000e$\b"
)
public class UnclosedCommentProcessor extends SqlBaseParserBaseListener implements Product, Serializable {
   private final String command;
   private final CommonTokenStream tokenStream;

   public static Option unapply(final UnclosedCommentProcessor x$0) {
      return UnclosedCommentProcessor$.MODULE$.unapply(x$0);
   }

   public static UnclosedCommentProcessor apply(final String command, final CommonTokenStream tokenStream) {
      return UnclosedCommentProcessor$.MODULE$.apply(command, tokenStream);
   }

   public static Function1 tupled() {
      return UnclosedCommentProcessor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnclosedCommentProcessor$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String command() {
      return this.command;
   }

   public CommonTokenStream tokenStream() {
      return this.tokenStream;
   }

   public void exitSingleDataType(final SqlBaseParser.SingleDataTypeContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   public void exitSingleExpression(final SqlBaseParser.SingleExpressionContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   public void exitSingleTableIdentifier(final SqlBaseParser.SingleTableIdentifierContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   public void exitSingleFunctionIdentifier(final SqlBaseParser.SingleFunctionIdentifierContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   public void exitSingleMultipartIdentifier(final SqlBaseParser.SingleMultipartIdentifierContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   public void exitSingleTableSchema(final SqlBaseParser.SingleTableSchemaContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   public void exitQuery(final SqlBaseParser.QueryContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   public void exitSingleStatement(final SqlBaseParser.SingleStatementContext ctx) {
      if (!(ctx.setResetStatement() instanceof SqlBaseParser.SetConfigurationContext)) {
         this.checkUnclosedComment(this.tokenStream(), this.command());
      }
   }

   public void exitCompoundOrSingleStatement(final SqlBaseParser.CompoundOrSingleStatementContext ctx) {
      if (.MODULE$.apply(ctx.singleStatement()).forall((x$7) -> BoxesRunTime.boxToBoolean($anonfun$exitCompoundOrSingleStatement$1(x$7)))) {
         this.checkUnclosedComment(this.tokenStream(), this.command());
      }
   }

   public void exitSingleCompoundStatement(final SqlBaseParser.SingleCompoundStatementContext ctx) {
      this.checkUnclosedComment(this.tokenStream(), this.command());
   }

   private void checkUnclosedComment(final CommonTokenStream tokenStream, final String command) {
      scala.Predef..MODULE$.assert(tokenStream.getTokenSource() instanceof SqlBaseLexer);
      SqlBaseLexer lexer = (SqlBaseLexer)tokenStream.getTokenSource();
      if (lexer.has_unclosed_bracketed_comment) {
         Token failedToken = tokenStream.get(tokenStream.size() - 2);
         scala.Predef..MODULE$.assert(failedToken.getType() == SqlBaseParser.BRACKETED_COMMENT);
         new Origin(.MODULE$.apply(BoxesRunTime.boxToInteger(failedToken.getLine())), .MODULE$.apply(BoxesRunTime.boxToInteger(failedToken.getCharPositionInLine())), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
         throw QueryParsingErrors$.MODULE$.unclosedBracketedCommentError(command, new Origin(.MODULE$.apply(BoxesRunTime.boxToInteger(failedToken.getStartIndex())), Origin$.MODULE$.apply$default$2(), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9()), new Origin(.MODULE$.apply(BoxesRunTime.boxToInteger(failedToken.getStopIndex())), Origin$.MODULE$.apply$default$2(), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9()));
      }
   }

   public UnclosedCommentProcessor copy(final String command, final CommonTokenStream tokenStream) {
      return new UnclosedCommentProcessor(command, tokenStream);
   }

   public String copy$default$1() {
      return this.command();
   }

   public CommonTokenStream copy$default$2() {
      return this.tokenStream();
   }

   public String productPrefix() {
      return "UnclosedCommentProcessor";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.command();
         }
         case 1 -> {
            return this.tokenStream();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof UnclosedCommentProcessor;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "command";
         }
         case 1 -> {
            return "tokenStream";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof UnclosedCommentProcessor) {
               label48: {
                  UnclosedCommentProcessor var4 = (UnclosedCommentProcessor)x$1;
                  String var10000 = this.command();
                  String var5 = var4.command();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  CommonTokenStream var7 = this.tokenStream();
                  CommonTokenStream var6 = var4.tokenStream();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$exitCompoundOrSingleStatement$1(final SqlBaseParser.SingleStatementContext x$7) {
      return !(x$7.setResetStatement() instanceof SqlBaseParser.SetConfigurationContext);
   }

   public UnclosedCommentProcessor(final String command, final CommonTokenStream tokenStream) {
      this.command = command;
      this.tokenStream = tokenStream;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
