package org.apache.spark.sql.catalyst.parser;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.spark.SparkThrowable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.catalyst.trees.WithOrigin;
import org.apache.spark.sql.internal.SqlApiConf;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%4Qa\u0002\u0005\u0002\u0002UAQA\n\u0001\u0005\u0002\u001dBQ!\u000b\u0001\u0005B)BQA\u0010\u0001\u0005B}BQ\u0001\u0012\u0001\u0007\u0012\u0015CQ!\u0013\u0001\u0005\u0012)CQA\u0019\u0001\u0005\n\r\u0014a\"\u00112tiJ\f7\r\u001e)beN,'O\u0003\u0002\n\u0015\u00051\u0001/\u0019:tKJT!a\u0003\u0007\u0002\u0011\r\fG/\u00197zgRT!!\u0004\b\u0002\u0007M\fHN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\f\u001dAA\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\u0004\"!\b\u0010\u000e\u0003!I!a\b\u0005\u0003/\u0011\u000bG/\u0019+za\u0016\u0004\u0016M]:fe&sG/\u001a:gC\u000e,\u0007CA\u0011%\u001b\u0005\u0011#BA\u0012\u000f\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0013#\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRtD#\u0001\u0015\u0011\u0005u\u0001\u0011!\u00049beN,G)\u0019;b)f\u0004X\r\u0006\u0002,cA\u0011AfL\u0007\u0002[)\u0011a\u0006D\u0001\u0006if\u0004Xm]\u0005\u0003a5\u0012\u0001\u0002R1uCRK\b/\u001a\u0005\u0006e\t\u0001\raM\u0001\bgFdG+\u001a=u!\t!4H\u0004\u00026sA\u0011a\u0007G\u0007\u0002o)\u0011\u0001\bF\u0001\u0007yI|w\u000e\u001e \n\u0005iB\u0012A\u0002)sK\u0012,g-\u0003\u0002={\t11\u000b\u001e:j]\u001eT!A\u000f\r\u0002!A\f'o]3UC\ndWmU2iK6\fGC\u0001!D!\ta\u0013)\u0003\u0002C[\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000bI\u001a\u0001\u0019A\u001a\u0002\u0015\u0005\u001cHOQ;jY\u0012,'/F\u0001G!\tir)\u0003\u0002I\u0011\t\u0011B)\u0019;b)f\u0004X-Q:u\u0005VLG\u000eZ3s\u0003\u0015\u0001\u0018M]:f+\tYu\n\u0006\u0002MAR\u0011Q\n\u0017\t\u0003\u001d>c\u0001\u0001B\u0003Q\u000b\t\u0007\u0011KA\u0001U#\t\u0011V\u000b\u0005\u0002\u0018'&\u0011A\u000b\u0007\u0002\b\u001d>$\b.\u001b8h!\t9b+\u0003\u0002X1\t\u0019\u0011I\\=\t\u000be+\u0001\u0019\u0001.\u0002\u0011Q|'+Z:vYR\u0004BaF.^\u001b&\u0011A\f\u0007\u0002\n\rVt7\r^5p]F\u0002\"!\b0\n\u0005}C!!D*rY\n\u000b7/\u001a)beN,'\u000fC\u0003b\u000b\u0001\u00071'A\u0004d_6l\u0017M\u001c3\u0002\t\r|gNZ\u000b\u0002IB\u0011QmZ\u0007\u0002M*\u00111\u0005D\u0005\u0003Q\u001a\u0014!bU9m\u0003BL7i\u001c8g\u0001"
)
public abstract class AbstractParser implements DataTypeParserInterface, Logging {
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public DataType parseDataType(final String sqlText) {
      return (DataType)this.parse(sqlText, (parser) -> this.astBuilder().visitSingleDataType(parser.singleDataType()));
   }

   public StructType parseTableSchema(final String sqlText) {
      return (StructType)this.parse(sqlText, (parser) -> this.astBuilder().visitSingleTableSchema(parser.singleTableSchema()));
   }

   public abstract DataTypeAstBuilder astBuilder();

   public Object parse(final String command, final Function1 toResult) {
      this.logDebug((Function0)(() -> "Parsing command: " + command));
      SqlBaseLexer lexer = new SqlBaseLexer(new UpperCaseCharStream(CharStreams.fromString(command)));
      lexer.removeErrorListeners();
      lexer.addErrorListener(ParseErrorListener$.MODULE$);
      CommonTokenStream tokenStream = new CommonTokenStream(lexer);
      SqlBaseParser parser = new SqlBaseParser(tokenStream);
      parser.addParseListener(PostProcessor$.MODULE$);
      parser.addParseListener(new UnclosedCommentProcessor(command, tokenStream));
      parser.removeErrorListeners();
      parser.addErrorListener(ParseErrorListener$.MODULE$);
      parser.legacy_setops_precedence_enabled = this.conf().setOpsPrecedenceEnforced();
      parser.legacy_exponent_literal_as_decimal_enabled = this.conf().exponentLiteralAsDecimalEnabled();
      parser.SQL_standard_keyword_behavior = this.conf().enforceReservedKeywords();
      parser.double_quoted_identifiers = this.conf().doubleQuotedIdentifiers();

      try {
         Object var10000;
         try {
            parser.setErrorHandler(new SparkParserBailErrorStrategy());
            ((ParserATNSimulator)parser.getInterpreter()).setPredictionMode(PredictionMode.SLL);
            var10000 = toResult.apply(parser);
         } catch (ParseCancellationException var13) {
            tokenStream.seek(0);
            parser.reset();
            parser.setErrorHandler(new SparkParserErrorStrategy());
            ((ParserATNSimulator)parser.getInterpreter()).setPredictionMode(PredictionMode.LL);
            var10000 = toResult.apply(parser);
         }

         return var10000;
      } catch (Throwable var14) {
         boolean var9 = false;
         ParseException var10 = null;
         if (var14 instanceof ParseException) {
            var9 = true;
            var10 = (ParseException)var14;
            if (var10.command().isDefined()) {
               throw var10;
            }
         }

         if (var9) {
            throw var10.withCommand(command);
         } else if (var14 instanceof SparkThrowable && var14 instanceof WithOrigin) {
            throw new ParseException(.MODULE$.apply(command), ((WithOrigin)var14).origin(), ((WithOrigin)var14).origin(), ((SparkThrowable)var14).getCondition(), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(((SparkThrowable)var14).getMessageParameters()).asScala().toMap(scala..less.colon.less..MODULE$.refl()), ((SparkThrowable)var14).getQueryContext());
         } else {
            throw var14;
         }
      }
   }

   private SqlApiConf conf() {
      return SqlApiConf$.MODULE$.get();
   }

   public AbstractParser() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
