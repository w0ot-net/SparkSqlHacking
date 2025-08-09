package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.IntStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuleContext;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001\u0002\n\u0014\u0001\u0001B\u0001b\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\ts\u0001\u0011\t\u0011)A\u0005u!AQ\n\u0001B\u0001B\u0003%a\n\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003S\u0011!)\u0006A!b\u0001\n\u00031\u0006\u0002\u0003.\u0001\u0005\u0003\u0005\u000b\u0011B,\t\u0011m\u0003!Q1A\u0005\u0002qC\u0001\u0002\u0019\u0001\u0003\u0002\u0003\u0006I!\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\u0006C\u0002!\t!\u001d\u0005\u0006C\u0002!\tA^\u0004\bsN\t\t\u0011#\u0001{\r\u001d\u00112#!A\t\u0002mDa!Y\u0007\u0005\u0002\u0005=\u0001\"CA\t\u001bE\u0005I\u0011AA\n\u0011%\tI#DI\u0001\n\u0003\tY\u0003C\u0005\u000205\t\t\u0011\"\u0003\u00022\tI2\u000b]1sWJ+7m\\4oSRLwN\\#yG\u0016\u0004H/[8o\u0015\t!R#\u0001\u0004qCJ\u001cXM\u001d\u0006\u0003-]\t\u0001bY1uC2L8\u000f\u001e\u0006\u00031e\t1a]9m\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7\u0001A\n\u0003\u0001\u0005\u0002\"AI\u0015\u000e\u0003\rR!\u0001J\u0013\u0002\u000fI,h\u000e^5nK*\u0011aeJ\u0001\u0003mRR!\u0001K\u000f\u0002\u000b\u0005tG\u000f\u001c:\n\u0005)\u001a#\u0001\u0006*fG><g.\u001b;j_:,\u0005pY3qi&|g.A\u0004nKN\u001c\u0018mZ3\u0011\u000552dB\u0001\u00185!\ty#'D\u00011\u0015\t\tt$\u0001\u0004=e>|GO\u0010\u0006\u0002g\u0005)1oY1mC&\u0011QGM\u0001\u0007!J,G-\u001a4\n\u0005]B$AB*ue&twM\u0003\u00026e\u0005Q!/Z2pO:L'0\u001a:1\u0007m\u00025\n\u0005\u0003#yyR\u0015BA\u001f$\u0005)\u0011VmY8h]&TXM\u001d\t\u0003\u007f\u0001c\u0001\u0001B\u0005B\u0005\u0005\u0005\t\u0011!B\u0001\u0005\n\u0019q\fJ\u0019\u0012\u0005\r;\u0005C\u0001#F\u001b\u0005\u0011\u0014B\u0001$3\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0012%\n\u0005%\u0013$aA!osB\u0011qh\u0013\u0003\n\u0019\n\t\t\u0011!A\u0003\u0002\t\u00131a\u0018\u00133\u0003\u0015Ig\u000e];u!\t\u0011s*\u0003\u0002QG\tI\u0011J\u001c;TiJ,\u0017-\\\u0001\u0004GRD\bC\u0001\u0012T\u0013\t!6EA\tQCJ\u001cXM\u001d*vY\u0016\u001cuN\u001c;fqR\f!\"\u001a:s_J\u001cE.Y:t+\u00059\u0006c\u0001#YY%\u0011\u0011L\r\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u0017\u0015\u0014(o\u001c:DY\u0006\u001c8\u000fI\u0001\u0012[\u0016\u001c8/Y4f!\u0006\u0014\u0018-\\3uKJ\u001cX#A/\u0011\t5rF\u0006L\u0005\u0003?b\u00121!T1q\u0003IiWm]:bO\u0016\u0004\u0016M]1nKR,'o\u001d\u0011\u0002\rqJg.\u001b;?)\u001d\u0019WMZ7o_B\u0004\"\u0001\u001a\u0001\u000e\u0003MAQaK\u0005A\u00021BQ!O\u0005A\u0002\u001d\u00044\u0001\u001b6m!\u0011\u0011C([6\u0011\u0005}RG!C!g\u0003\u0003\u0005\tQ!\u0001C!\tyD\u000eB\u0005MM\u0006\u0005\t\u0011!B\u0001\u0005\")Q*\u0003a\u0001\u001d\")\u0011+\u0003a\u0001%\"9Q+\u0003I\u0001\u0002\u00049\u0006bB.\n!\u0003\u0005\r!\u0018\u000b\u0005GJ$X\u000fC\u0003t\u0015\u0001\u0007\u0011%\u0001\u000bsK\u000e|wM\\5uS>tW\t_2faRLwN\u001c\u0005\u0006+*\u0001\r\u0001\f\u0005\u00067*\u0001\r!\u0018\u000b\u0004G^D\b\"B+\f\u0001\u0004a\u0003\"B.\f\u0001\u0004i\u0016!G*qCJ\\'+Z2pO:LG/[8o\u000bb\u001cW\r\u001d;j_:\u0004\"\u0001Z\u0007\u0014\u00075ax\u0010\u0005\u0002E{&\u0011aP\r\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005\u0005\u00111B\u0007\u0003\u0003\u0007QA!!\u0002\u0002\b\u0005\u0011\u0011n\u001c\u0006\u0003\u0003\u0013\tAA[1wC&!\u0011QBA\u0002\u00051\u0019VM]5bY&T\u0018M\u00197f)\u0005Q\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0002\u0002\u0016)\u001aq+a\u0006,\u0005\u0005e\u0001\u0003BA\u000e\u0003Ki!!!\b\u000b\t\u0005}\u0011\u0011E\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\t3\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003O\tiBA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u00122TCAA\u0017U\ri\u0016qC\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003g\u0001B!!\u000e\u0002<5\u0011\u0011q\u0007\u0006\u0005\u0003s\t9!\u0001\u0003mC:<\u0017\u0002BA\u001f\u0003o\u0011aa\u00142kK\u000e$\b"
)
public class SparkRecognitionException extends RecognitionException {
   private final Option errorClass;
   private final Map messageParameters;

   public static Map $lessinit$greater$default$6() {
      return SparkRecognitionException$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option $lessinit$greater$default$5() {
      return SparkRecognitionException$.MODULE$.$lessinit$greater$default$5();
   }

   public Option errorClass() {
      return this.errorClass;
   }

   public Map messageParameters() {
      return this.messageParameters;
   }

   public SparkRecognitionException(final String message, final Recognizer recognizer, final IntStream input, final ParserRuleContext ctx, final Option errorClass, final Map messageParameters) {
      super(message, recognizer, input, ctx);
      this.errorClass = errorClass;
      this.messageParameters = messageParameters;
   }

   public SparkRecognitionException(final RecognitionException recognitionException, final String errorClass, final Map messageParameters) {
      String var10001 = recognitionException.getMessage();
      Recognizer var10002 = recognitionException.getRecognizer();
      IntStream var10003 = recognitionException.getInputStream();
      RuleContext var5 = recognitionException.getCtx();
      ParserRuleContext var10004;
      if (var5 instanceof ParserRuleContext var6) {
         var10004 = var6;
      } else {
         var10004 = null;
      }

      this(var10001, var10002, var10003, var10004, new Some(errorClass), messageParameters);
   }

   public SparkRecognitionException(final String errorClass, final Map messageParameters) {
      this("", (Recognizer)null, (IntStream)null, (ParserRuleContext)null, new Some(errorClass), messageParameters);
   }
}
