package org.apache.spark.sql.catalyst.parser;

import java.lang.invoke.SerializedLambda;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.util.SparkParserUtils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001\u0002\u000f\u001e\u0001)B\u0001b\f\u0001\u0003\u0006\u0004%\t\u0001\r\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005c!I1\t\u0001B\u0001B\u0003%q\u0007\u0012\u0005\t\u000b\u0002\u0011)\u0019!C\u0001\r\"AQ\n\u0001B\u0001B\u0003%q\t\u0003\u0005O\u0001\t\u0015\r\u0011\"\u0001G\u0011!y\u0005A!A!\u0002\u00139\u0005\"\u0003)\u0001\u0005\u0003\u0005\u000b\u0011B\u0019R\u0011%\u0011\u0006A!A!\u0002\u0013\u0019f\u000bC\u0005X\u0001\t\u0005\t\u0015!\u0003Y?\")\u0011\r\u0001C\u0005E\")\u0011\r\u0001C\u0001Y\")\u0011\r\u0001C\u0001w\")\u0011\r\u0001C\u0001}\"1\u0011\r\u0001C\u0001\u0003\u0013Aq!a\u0006\u0001\t\u0003\nI\u0002C\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005\r\u0002\u0001\"\u0011\u0002&!9\u0011q\u0005\u0001\u0005B\u0005eqaBA\u0015;!\u0005\u00111\u0006\u0004\u00079uA\t!!\f\t\r\u0005,B\u0011AA#\u0011\u001d\t\u0019#\u0006C\u0001\u0003KA\u0011\"a\u0012\u0016#\u0003%I!!\u0013\t\u0013\u0005}S#%A\u0005\n\u0005\u0005\u0004\"CA3+E\u0005I\u0011BA4\u0011%\tY'FA\u0001\n\u0013\tiG\u0001\bQCJ\u001cX-\u0012=dKB$\u0018n\u001c8\u000b\u0005yy\u0012A\u00029beN,'O\u0003\u0002!C\u0005A1-\u0019;bYf\u001cHO\u0003\u0002#G\u0005\u00191/\u001d7\u000b\u0005\u0011*\u0013!B:qCJ\\'B\u0001\u0014(\u0003\u0019\t\u0007/Y2iK*\t\u0001&A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001WA\u0011A&L\u0007\u0002C%\u0011a&\t\u0002\u0012\u0003:\fG._:jg\u0016C8-\u001a9uS>t\u0017aB2p[6\fg\u000eZ\u000b\u0002cA\u0019!'N\u001c\u000e\u0003MR\u0011\u0001N\u0001\u0006g\u000e\fG.Y\u0005\u0003mM\u0012aa\u00149uS>t\u0007C\u0001\u001d@\u001d\tIT\b\u0005\u0002;g5\t1H\u0003\u0002=S\u00051AH]8pizJ!AP\u001a\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0015I\u0001\u0004TiJLgn\u001a\u0006\u0003}M\n\u0001bY8n[\u0006tG\rI\u0001\b[\u0016\u001c8/Y4f\u0013\t\u0019U&A\u0003ti\u0006\u0014H/F\u0001H!\tA5*D\u0001J\u0015\tQu$A\u0003ue\u0016,7/\u0003\u0002M\u0013\n1qJ]5hS:\faa\u001d;beR\u0004\u0013\u0001B:u_B\fQa\u001d;pa\u0002\n!\"\u001a:s_J\u001cE.Y:t\u0013\t\u0001V&A\tnKN\u001c\u0018mZ3QCJ\fW.\u001a;feN\u0004B\u0001\u000f+8o%\u0011Q+\u0011\u0002\u0004\u001b\u0006\u0004\u0018B\u0001*.\u00031\tX/\u001a:z\u0007>tG/\u001a=u!\r\u0011\u0014lW\u0005\u00035N\u0012Q!\u0011:sCf\u0004\"\u0001X/\u000e\u0003\rJ!AX\u0012\u0003\u0019E+XM]=D_:$X\r\u001f;\n\u0005\u0001l\u0013aB2p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011\r,gm\u001a5jU.\u0004\"\u0001\u001a\u0001\u000e\u0003uAQaL\u0006A\u0002EBQaQ\u0006A\u0002]BQ!R\u0006A\u0002\u001dCQAT\u0006A\u0002\u001dCq\u0001U\u0006\u0011\u0002\u0003\u0007\u0011\u0007C\u0004S\u0017A\u0005\t\u0019A*\t\u000f][\u0001\u0013!a\u00011R!1-\u001c8p\u0011\u0015\u0001F\u00021\u00018\u0011\u0015\u0011F\u00021\u0001T\u0011\u0015\u0001H\u00021\u0001r\u0003\r\u0019G\u000f\u001f\t\u0003efl\u0011a\u001d\u0006\u0003iV\fqA];oi&lWM\u0003\u0002wo\u0006\u0011a\u000f\u000e\u0006\u0003q\u001e\nQ!\u00198uYJL!A_:\u0003#A\u000b'o]3s%VdWmQ8oi\u0016DH\u000fF\u0002dyvDQ\u0001U\u0007A\u0002]BQ\u0001]\u0007A\u0002E$\"bY@\u0002\u0002\u0005\r\u0011QAA\u0004\u0011\u0015yc\u00021\u00012\u0011\u0015)e\u00021\u0001H\u0011\u0015qe\u00021\u0001H\u0011\u0015\u0001f\u00021\u00018\u0011\u0015\u0011f\u00021\u0001T)5\u0019\u00171BA\u0007\u0003\u001f\t\t\"a\u0005\u0002\u0016!)qf\u0004a\u0001c!)Qi\u0004a\u0001\u000f\")aj\u0004a\u0001\u000f\")\u0001k\u0004a\u0001o!)!k\u0004a\u0001'\")qk\u0004a\u00011\u0006Qq-\u001a;NKN\u001c\u0018mZ3\u0015\u0003]\n1b^5uQ\u000e{W.\\1oIR\u00191-a\b\t\r\u0005\u0005\u0012\u00031\u00018\u0003\r\u0019W\u000eZ\u0001\u0010O\u0016$\u0018+^3ss\u000e{g\u000e^3yiR\t\u0001,\u0001\u0007hKR\u001cuN\u001c3ji&|g.\u0001\bQCJ\u001cX-\u0012=dKB$\u0018n\u001c8\u0011\u0005\u0011,2#B\u000b\u00020\u0005U\u0002c\u0001\u001a\u00022%\u0019\u00111G\u001a\u0003\r\u0005s\u0017PU3g!\u0011\t9$!\u0011\u000e\u0005\u0005e\"\u0002BA\u001e\u0003{\t!![8\u000b\u0005\u0005}\u0012\u0001\u00026bm\u0006LA!a\u0011\u0002:\ta1+\u001a:jC2L'0\u00192mKR\u0011\u00111F\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005-#fA\u0019\u0002N-\u0012\u0011q\n\t\u0005\u0003#\nY&\u0004\u0002\u0002T)!\u0011QKA,\u0003%)hn\u00195fG.,GMC\u0002\u0002ZM\n!\"\u00198o_R\fG/[8o\u0013\u0011\ti&a\u0015\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u000b\u0003\u0003GR3aUA'\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%oU\u0011\u0011\u0011\u000e\u0016\u00041\u00065\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAA8!\u0011\t\t(a\u001e\u000e\u0005\u0005M$\u0002BA;\u0003{\tA\u0001\\1oO&!\u0011\u0011PA:\u0005\u0019y%M[3di\u0002"
)
public class ParseException extends AnalysisException {
   private final Option command;
   private final Origin start;
   private final Origin stop;

   public Option command() {
      return this.command;
   }

   public Origin start() {
      return this.start;
   }

   public Origin stop() {
      return this.stop;
   }

   public String getMessage() {
      StringBuilder builder = new StringBuilder();
      builder.$plus$plus$eq("\n").$plus$plus$eq(super.message());
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])super.context()))) {
         builder.$plus$plus$eq("\n");
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])super.context()), (ctx) -> builder.$plus$plus$eq(ctx.summary()));
      } else {
         Origin var3 = this.start();
         if (var3 != null) {
            Option var4 = var3.line();
            Option var5 = var3.startPosition();
            if (var4 instanceof Some) {
               Some var6 = (Some)var4;
               int l = BoxesRunTime.unboxToInt(var6.value());
               if (var5 instanceof Some) {
                  Some var8 = (Some)var5;
                  int p = BoxesRunTime.unboxToInt(var8.value());
                  builder.$plus$plus$eq(" (line " + l + ", pos " + p + ")\n");
                  this.command().foreach((cmd) -> {
                     $anonfun$getMessage$2(l, builder, p, cmd);
                     return BoxedUnit.UNIT;
                  });
                  BoxedUnit var10 = BoxedUnit.UNIT;
                  return builder.toString();
               }
            }
         }

         this.command().foreach((cmd) -> builder.$plus$plus$eq("\n== SQL ==\n").$plus$plus$eq(cmd));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return builder.toString();
   }

   public ParseException withCommand(final String cmd) {
      Tuple2 var10000;
      label24: {
         String cl;
         label23: {
            cl = this.getCondition();
            String var6 = "PARSE_SYNTAX_ERROR";
            if (cl == null) {
               if (var6 != null) {
                  break label23;
               }
            } else if (!cl.equals(var6)) {
               break label23;
            }

            if (cmd.trim().isEmpty()) {
               var10000 = new Tuple2("PARSE_EMPTY_STATEMENT", scala.Predef..MODULE$.Map().empty());
               break label24;
            }
         }

         var10000 = new Tuple2(cl, super.messageParameters());
      }

      Tuple2 var5 = var10000;
      if (var5 != null) {
         String newCl = (String)var5._1();
         Map params = (Map)var5._2();
         Tuple2 var4 = new Tuple2(newCl, params);
         String newCl = (String)var4._1();
         Map params = (Map)var4._2();
         return new ParseException(scala.Option..MODULE$.apply(cmd), this.start(), this.stop(), newCl, params, super.context());
      } else {
         throw new MatchError(var5);
      }
   }

   public QueryContext[] getQueryContext() {
      return super.context();
   }

   public String getCondition() {
      return (String)super.errorClass().getOrElse(() -> {
         throw org.apache.spark.SparkException..MODULE$.internalError("ParseException shall have an error class.");
      });
   }

   // $FF: synthetic method
   public static final String $anonfun$getMessage$4(final int x$4) {
      return "-";
   }

   // $FF: synthetic method
   public static final void $anonfun$getMessage$2(final int l$1, final StringBuilder builder$1, final int p$1, final String cmd) {
      Tuple2 var6 = .MODULE$.splitAt$extension(scala.Predef..MODULE$.refArrayOps((Object[])cmd.split("\n")), l$1);
      if (var6 != null) {
         String[] above = (String[])var6._1();
         String[] below = (String[])var6._2();
         Tuple2 var5 = new Tuple2(above, below);
         String[] above = (String[])var5._1();
         String[] below = (String[])var5._2();
         builder$1.$plus$plus$eq("\n== SQL ==\n");
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])above), (x$3) -> (StringBuilder)builder$1.$plus$plus$eq(x$3).$plus$eq(BoxesRunTime.boxToCharacter('\n')));
         builder$1.$plus$plus$eq(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), p$1).map((x$4) -> $anonfun$getMessage$4(BoxesRunTime.unboxToInt(x$4))).mkString("")).$plus$plus$eq("^^^\n");
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])below), (x$5) -> (StringBuilder)builder$1.$plus$plus$eq(x$5).$plus$eq(BoxesRunTime.boxToCharacter('\n')));
      } else {
         throw new MatchError(var6);
      }
   }

   private ParseException(final Option command, final String message, final Origin start, final Origin stop, final Option errorClass, final Map messageParameters, final QueryContext[] queryContext) {
      super(message, start.line(), start.startPosition(), scala.None..MODULE$, errorClass, messageParameters, queryContext);
      this.command = command;
      this.start = start;
      this.stop = stop;
   }

   public ParseException(final String errorClass, final Map messageParameters, final ParserRuleContext ctx) {
      this(scala.Option..MODULE$.apply(SparkParserUtils$.MODULE$.command(ctx)), org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters), SparkParserUtils$.MODULE$.position(ctx.getStart()), SparkParserUtils$.MODULE$.position(ctx.getStop()), new Some(errorClass), messageParameters, ParseException$.MODULE$.org$apache$spark$sql$catalyst$parser$ParseException$$$lessinit$greater$default$7());
   }

   public ParseException(final String errorClass, final ParserRuleContext ctx) {
      this(errorClass, scala.Predef..MODULE$.Map().empty(), ctx);
   }

   public ParseException(final Option command, final Origin start, final Origin stop, final String errorClass, final Map messageParameters) {
      this(command, org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters), start, stop, new Some(errorClass), messageParameters, ParseException$.MODULE$.getQueryContext());
   }

   public ParseException(final Option command, final Origin start, final Origin stop, final String errorClass, final Map messageParameters, final QueryContext[] queryContext) {
      this(command, org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(errorClass, messageParameters), start, stop, new Some(errorClass), messageParameters, queryContext);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
