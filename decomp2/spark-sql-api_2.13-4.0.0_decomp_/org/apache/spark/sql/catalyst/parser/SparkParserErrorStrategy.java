package org.apache.spark.sql.catalyst.parser;

import java.lang.invoke.SerializedLambda;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3A!\u0003\u0006\u0001/!)!\u0005\u0001C\u0001G!9a\u0005\u0001b\u0001\n\u00139\u0003B\u0002\u001d\u0001A\u0003%\u0001\u0006C\u0003:\u0001\u0011\u0005#\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003Q\u0001\u0011\u0005\u0013\u000bC\u0003X\u0001\u0011\u0005\u0003\fC\u0003[\u0001\u0011\u00053L\u0001\rTa\u0006\u00148\u000eU1sg\u0016\u0014XI\u001d:peN#(/\u0019;fOfT!a\u0003\u0007\u0002\rA\f'o]3s\u0015\tia\"\u0001\u0005dCR\fG._:u\u0015\ty\u0001#A\u0002tc2T!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\u0002\u0001'\t\u0001\u0001\u0004\u0005\u0002\u001aA5\t!D\u0003\u0002\u001c9\u00059!/\u001e8uS6,'BA\u000f\u001f\u0003\t1HG\u0003\u0002 )\u0005)\u0011M\u001c;me&\u0011\u0011E\u0007\u0002\u0015\t\u00164\u0017-\u001e7u\u000bJ\u0014xN]*ue\u0006$XmZ=\u0002\rqJg.\u001b;?)\u0005!\u0003CA\u0013\u0001\u001b\u0005Q\u0011\u0001D;tKJ<vN\u001d3ES\u000e$X#\u0001\u0015\u0011\t%\u0012T'\u000e\b\u0003UA\u0002\"a\u000b\u0018\u000e\u00031R!!\f\f\u0002\rq\u0012xn\u001c;?\u0015\u0005y\u0013!B:dC2\f\u0017BA\u0019/\u0003\u0019\u0001&/\u001a3fM&\u00111\u0007\u000e\u0002\u0004\u001b\u0006\u0004(BA\u0019/!\tIc'\u0003\u00028i\t11\u000b\u001e:j]\u001e\fQ\"^:fe^{'\u000f\u001a#jGR\u0004\u0013\u0001F4fiR{7.\u001a8FeJ|'\u000fR5ta2\f\u0017\u0010\u0006\u00026w!)A\b\u0002a\u0001{\u0005\tA\u000f\u0005\u0002\u001a}%\u0011qH\u0007\u0002\u0006)>\\WM\\\u0001\u0014e\u0016\u0004xN\u001d;J]B,H/T5t[\u0006$8\r\u001b\u000b\u0004\u0005\u001a[\u0005CA\"E\u001b\u0005q\u0013BA#/\u0005\u0011)f.\u001b;\t\u000b\u001d+\u0001\u0019\u0001%\u0002\u0015I,7m\\4oSj,'\u000f\u0005\u0002\u001a\u0013&\u0011!J\u0007\u0002\u0007!\u0006\u00148/\u001a:\t\u000b1+\u0001\u0019A'\u0002\u0003\u0015\u0004\"!\u0007(\n\u0005=S\"AF%oaV$X*[:nCR\u001c\u0007.\u0012=dKB$\u0018n\u001c8\u00023I,\u0007o\u001c:u\u001d>4\u0016.\u00192mK\u0006cG/\u001a:oCRLg/\u001a\u000b\u0004\u0005J\u001b\u0006\"B$\u0007\u0001\u0004A\u0005\"\u0002'\u0007\u0001\u0004!\u0006CA\rV\u0013\t1&D\u0001\u000bO_ZK\u0017M\u00197f\u00032$X\t_2faRLwN\\\u0001\u0014e\u0016\u0004xN\u001d;V]^\fg\u000e^3e)>\\WM\u001c\u000b\u0003\u0005fCQaR\u0004A\u0002!\u000b!C]3q_J$X*[:tS:<Gk\\6f]R\u0011!\t\u0018\u0005\u0006\u000f\"\u0001\r\u0001\u0013"
)
public class SparkParserErrorStrategy extends DefaultErrorStrategy {
   private final Map userWordDict;

   private Map userWordDict() {
      return this.userWordDict;
   }

   public String getTokenErrorDisplay(final Token t) {
      String tokenName = super.getTokenErrorDisplay(t);
      return (String)this.userWordDict().getOrElse(tokenName, () -> tokenName);
   }

   public void reportInputMismatch(final Parser recognizer, final InputMismatchException e) {
      SparkRecognitionException exceptionWithErrorClass = new SparkRecognitionException(e, "PARSE_SYNTAX_ERROR", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("error"), this.getTokenErrorDisplay(e.getOffendingToken())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("hint"), "")}))));
      recognizer.notifyErrorListeners(e.getOffendingToken(), "", exceptionWithErrorClass);
   }

   public void reportNoViableAlternative(final Parser recognizer, final NoViableAltException e) {
      SparkRecognitionException exceptionWithErrorClass = new SparkRecognitionException(e, "PARSE_SYNTAX_ERROR", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("error"), this.getTokenErrorDisplay(e.getOffendingToken())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("hint"), "")}))));
      recognizer.notifyErrorListeners(e.getOffendingToken(), "", exceptionWithErrorClass);
   }

   public void reportUnwantedToken(final Parser recognizer) {
      if (!this.inErrorRecoveryMode(recognizer)) {
         this.beginErrorCondition(recognizer);
         String errorTokenDisplay = this.getTokenErrorDisplay(recognizer.getCurrentToken());
         String hint = ": extra input " + errorTokenDisplay;
         SparkRecognitionException exceptionWithErrorClass = new SparkRecognitionException("PARSE_SYNTAX_ERROR", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("error"), errorTokenDisplay), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("hint"), hint)}))));
         recognizer.notifyErrorListeners(recognizer.getCurrentToken(), "", exceptionWithErrorClass);
      }
   }

   public void reportMissingToken(final Parser recognizer) {
      if (!this.inErrorRecoveryMode(recognizer)) {
         this.beginErrorCondition(recognizer);
         String hint = ": missing " + this.getExpectedTokens(recognizer).toString(recognizer.getVocabulary());
         SparkRecognitionException exceptionWithErrorClass = new SparkRecognitionException("PARSE_SYNTAX_ERROR", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("error"), this.getTokenErrorDisplay(recognizer.getCurrentToken())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("hint"), hint)}))));
         recognizer.notifyErrorListeners(recognizer.getCurrentToken(), "", exceptionWithErrorClass);
      }
   }

   public SparkParserErrorStrategy() {
      this.userWordDict = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("'<EOF>'"), "end of input")})));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
