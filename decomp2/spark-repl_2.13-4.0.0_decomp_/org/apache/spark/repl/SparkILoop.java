package org.apache.spark.repl;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;
import scala.tools.nsc.GenericRunnerSettings;
import scala.tools.nsc.Settings;
import scala.tools.nsc.interpreter.shell.ILoop;
import scala.tools.nsc.reporters.Reporter;
import scala.util.Properties.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rb\u0001B\n\u0015\u0001uA\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006I!\f\u0005\nk\u0001\u0011\t\u0011)A\u0005meBQA\u000f\u0001\u0005\u0002mBQA\u000f\u0001\u0005\u0002\u0001Cq!\u0011\u0001C\u0002\u0013\u0005!\t\u0003\u0004W\u0001\u0001\u0006Ia\u0011\u0005\u0006/\u0002!\t\u0006\u0017\u0005\u00063\u0002!\tA\u0017\u0005\u0006?\u0002!\tE\u0017\u0005\u0006A\u0002!\t%\u0019\u0005\u0006W\u0002!\t\u0005\u001c\u0005\u0006_\u0002!\tEW\u0004\u0006aRA\t!\u001d\u0004\u0006'QA\tA\u001d\u0005\u0006u9!\tA\u001e\u0005\u0006o:!\t\u0001\u001f\u0005\n\u0003\u0007q\u0011\u0013!C\u0001\u0003\u000bAaa\u001e\b\u0005\u0002\u0005m!AC*qCJ\\\u0017\nT8pa*\u0011QCF\u0001\u0005e\u0016\u0004HN\u0003\u0002\u00181\u0005)1\u000f]1sW*\u0011\u0011DG\u0001\u0007CB\f7\r[3\u000b\u0003m\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u0010\u0011\u0005}QS\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013!B:iK2d'BA\u0012%\u0003-Ig\u000e^3saJ,G/\u001a:\u000b\u0005\u00152\u0013a\u00018tG*\u0011q\u0005K\u0001\u0006i>|Gn\u001d\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\t\u0002\u0006\u00132{w\u000e]\u0001\u0004S:\u0004\u0004C\u0001\u00184\u001b\u0005y#B\u0001\u00192\u0003\tIwNC\u00013\u0003\u0011Q\u0017M^1\n\u0005Qz#A\u0004\"vM\u001a,'/\u001a3SK\u0006$WM]\u0001\u0004_V$\bC\u0001\u00188\u0013\tAtFA\u0006Qe&tGo\u0016:ji\u0016\u0014\u0018BA\u001b+\u0003\u0019a\u0014N\\5u}Q\u0019AHP \u0011\u0005u\u0002Q\"\u0001\u000b\t\u000b1\u001a\u0001\u0019A\u0017\t\u000bU\u001a\u0001\u0019\u0001\u001c\u0015\u0003q\na#\u001b8ji&\fG.\u001b>bi&|gnQ8n[\u0006tGm]\u000b\u0002\u0007B\u0019A\tT(\u000f\u0005\u0015SeB\u0001$J\u001b\u00059%B\u0001%\u001d\u0003\u0019a$o\\8u}%\t\u0011&\u0003\u0002LQ\u00059\u0001/Y2lC\u001e,\u0017BA'O\u0005\r\u0019V-\u001d\u0006\u0003\u0017\"\u0002\"\u0001U*\u000f\u0005\u0015\u000b\u0016B\u0001*)\u0003\u0019\u0001&/\u001a3fM&\u0011A+\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005IC\u0013aF5oSRL\u0017\r\\5{CRLwN\\\"p[6\fg\u000eZ:!\u0003]Ig\u000e^3s]\u0006d'+\u001a9m\u0003V$xN];o\u0007>$W\rF\u0001D\u0003=Ig.\u001b;jC2L'0Z*qCJ\\G#A.\u0011\u0005qkV\"\u0001\u0015\n\u0005yC#\u0001B+oSR\fA\u0002\u001d:j]R<V\r\\2p[\u0016\f\u0001bY8n[\u0006tGm]\u000b\u0002EB\u0019AiY3\n\u0005\u0011t%\u0001\u0002'jgR\u0004\"AZ4\u000e\u0003\u0001I!\u0001[5\u0003\u00171{w\u000e]\"p[6\fg\u000eZ\u0005\u0003U\u0002\u0012A\u0002T8pa\u000e{W.\\1oIN\fAB]3tKR\u001cu.\\7b]\u0012$\"aW7\t\u000b9\\\u0001\u0019A(\u0002\t1Lg.Z\u0001\u0007e\u0016\u0004H.Y=\u0002\u0015M\u0003\u0018M]6J\u0019>|\u0007\u000f\u0005\u0002>\u001dM\u0011ab\u001d\t\u00039RL!!\u001e\u0015\u0003\r\u0005s\u0017PU3g)\u0005\t\u0018a\u0001:v]R\u0019q*_>\t\u000bi\u0004\u0002\u0019A(\u0002\t\r|G-\u001a\u0005\byB\u0001\n\u00111\u0001~\u0003\u0011\u0019X\r^:\u0011\u0005y|X\"\u0001\u0013\n\u0007\u0005\u0005AE\u0001\u0005TKR$\u0018N\\4t\u00035\u0011XO\u001c\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0001\u0016\u0004{\u0006%1FAA\u0006!\u0011\ti!a\u0006\u000e\u0005\u0005=!\u0002BA\t\u0003'\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005U\u0001&\u0001\u0006b]:|G/\u0019;j_:LA!!\u0007\u0002\u0010\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0015\u0007=\u000bi\u0002C\u0004\u0002 I\u0001\r!!\t\u0002\u000b1Lg.Z:\u0011\u0007\u0011\u001bw\n"
)
public class SparkILoop extends ILoop {
   private final Seq initializationCommands;

   public static Settings run$default$2() {
      return SparkILoop$.MODULE$.run$default$2();
   }

   public Seq initializationCommands() {
      return this.initializationCommands;
   }

   public Seq internalReplAutorunCode() {
      return this.initializationCommands();
   }

   public void initializeSpark() {
      if (!((Reporter)this.intp().reporter()).hasErrors()) {
         this.savingReplayStack((JFunction0.mcV.sp)() -> this.initializationCommands().foreach((x$2) -> this.intp().quietRun(x$2)));
      } else {
         throw new RuntimeException("Scala " + .MODULE$.versionString() + " interpreter encountered errors during initialization");
      }
   }

   public void printWelcome() {
      this.echo(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Welcome to\n      ____              __\n     / __/__  ___ _____/ /__\n    _\\ \\/ _ \\/ _ `/ __/  '_/\n   /___/ .__/\\_,_/_/ /_/\\_\\   version %s\n      /_/\n         "), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{org.apache.spark.package..MODULE$.SPARK_VERSION()})));
      String welcomeMsg = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Using Scala %s (%s, Java %s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{.MODULE$.versionString(), .MODULE$.javaVmName(), .MODULE$.javaVersion()}));
      this.echo(welcomeMsg);
      this.echo("Type in expressions to have them evaluated.");
      this.echo("Type :help for more information.");
   }

   public List commands() {
      return this.standardCommands();
   }

   public void resetCommand(final String line) {
      super.resetCommand(line);
      this.initializeSpark();
      this.echo("Note that after :reset, state of SparkSession and SparkContext is unchanged.");
   }

   public void replay() {
      this.initializeSpark();
      super.replay();
   }

   public SparkILoop(final BufferedReader in0, final PrintWriter out) {
      super(scala.tools.nsc.interpreter.shell.ShellConfig..MODULE$.apply(new GenericRunnerSettings(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final void apply(final String x$1) {
         }
      })), in0, out);
      this.initializationCommands = new scala.collection.immutable..colon.colon("\n    @transient val spark = if (org.apache.spark.repl.Main.sparkSession != null) {\n        org.apache.spark.repl.Main.sparkSession\n      } else {\n        org.apache.spark.repl.Main.createSparkSession()\n      }\n    @transient val sc = {\n      val _sc = spark.sparkContext\n      if (_sc.getConf.getBoolean(\"spark.ui.reverseProxy\", false)) {\n        val proxyUrl = _sc.getConf.get(\"spark.ui.reverseProxyUrl\", null)\n        if (proxyUrl != null) {\n          println(\n            s\"Spark Context Web UI is available at ${proxyUrl}/proxy/${_sc.applicationId}\")\n        } else {\n          println(s\"Spark Context Web UI is available at Spark Master Public URL\")\n        }\n      } else {\n        _sc.uiWebUrl.foreach {\n          webUrl => println(s\"Spark context Web UI available at ${webUrl}\")\n        }\n      }\n      println(\"Spark context available as 'sc' \" +\n        s\"(master = ${_sc.master}, app id = ${_sc.applicationId}).\")\n      println(\"Spark session available as 'spark'.\")\n      _sc\n    }\n    ", new scala.collection.immutable..colon.colon("import org.apache.spark.SparkContext._", new scala.collection.immutable..colon.colon("import spark.implicits._", new scala.collection.immutable..colon.colon("import spark.sql", new scala.collection.immutable..colon.colon("import org.apache.spark.sql.functions._", new scala.collection.immutable..colon.colon("import org.apache.spark.util.LogUtils.SPARK_LOG_SCHEMA", scala.collection.immutable.Nil..MODULE$))))));
   }

   public SparkILoop() {
      this((BufferedReader)null, new PrintWriter(scala.Console..MODULE$.out(), true));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
