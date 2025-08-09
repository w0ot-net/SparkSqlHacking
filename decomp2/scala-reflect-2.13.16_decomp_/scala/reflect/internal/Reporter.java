package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t5a!\u0002\u001b6\u0003\u0003a\u0004\"B!\u0001\t\u0003\u0011\u0005BB#\u0001A\u0003&a\t\u0003\u0004J\u0001\u0001\u0006KAR\u0003\u0005\u0015\u0002\u00111\nC\u0004\u0002\n\u0001!)!a\u0004\t\u000f\u0005-\u0001\u0001\"\u0002\u0002\u0010!9\u0011Q\u0002\u0001\u0005\u0006\u0005=\u0001bBA\u0016\u0001\u0019E\u0011Q\u0006\u0005\b\u0003W\u0002A\u0011AA7\u0011\u001d\ti\t\u0001C\u0001\u0003\u001fCq!a&\u0001\t\u000b\tI\nC\u0004\u0002\u0018\u0002!)!!(\t\u0013\u0005\u0015\u0006!%A\u0005\u0006\u0005\u001d\u0006bBAZ\u0001\u0011\u0015\u0011Q\u0017\u0005\n\u0003{\u0003\u0011\u0013!C\u0003\u0003OCq!a0\u0001\t\u000b\t\t\rC\u0005\u0002J\u0002\t\n\u0011\"\u0002\u0002(\"9\u00111\u001a\u0001\u0005\n\u00055\u0007bBAl\u0001\u0011\u0005\u0011\u0011\u001c\u0005\u0007\u0003;\u0004A\u0011\u00012\t\r\u0005}\u0007\u0001\"\u0001c\u0011\u001d\t\t\u000f\u0001C\u0001\u0003GDq!!:\u0001\t\u0003\t\u0019\u000fC\u0004\u0002h\u0002!\t!!;\t\u000f\u0005-\b\u0001\"\u0001\u0002j\"9\u0011Q\u001e\u0001\u0005\u0002\u0005%\bbBAx\u0001\u0011\u0005\u0011\u0011_\u0004\u0006\u001bVB\tA\u0014\u0004\u0006iUB\ta\u0014\u0005\u0006\u0003v!\t\u0001\u0015\u0005\b#v\u0011\r\u0011\"\u0002S\u0011\u0019)V\u0004)A\u0007'\"9a+\bb\u0001\n\u000b9\u0006B\u0002.\u001eA\u00035\u0001\fC\u0004\\;\t\u0007IQ\u0001/\t\r}k\u0002\u0015!\u0004^\r\u0015QU$!\ta\u0011!\tWE!b\u0001\n\u0003\u0011\u0007\u0002C2&\u0005\u0003\u0005\u000b\u0011\u0002$\t\u0011\u0011,#Q1A\u0005B\u0015D\u0001\"]\u0013\u0003\u0002\u0003\u0006IA\u001a\u0005\u0006\u0003\u0016\"\tA]\u0004\u0007\u0003\u0013i\u0002\u0012A@\u0007\u000bql\u0002\u0012A?\t\u000b\u0005cC\u0011\u0001@\b\u000f\u0005-Q\u0004#\u0001\u0002\b\u00199\u0011\u0011A\u000f\t\u0002\u0005\r\u0001BB!0\t\u0003\t)a\u0002\u0004\u0002\u000euA\ta\u001f\u0004\u0006qvA\t!\u001f\u0005\u0006\u0003J\"\tA\u001f\u0002\t%\u0016\u0004xN\u001d;fe*\u0011agN\u0001\tS:$XM\u001d8bY*\u0011\u0001(O\u0001\be\u00164G.Z2u\u0015\u0005Q\u0014!B:dC2\f7\u0001A\n\u0003\u0001u\u0002\"AP \u000e\u0003eJ!\u0001Q\u001d\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t1\t\u0005\u0002E\u00015\tQ'A\u0006`KJ\u0014xN]\"pk:$\bC\u0001 H\u0013\tA\u0015HA\u0002J]R\fQbX<be:LgnZ\"pk:$(\u0001C*fm\u0016\u0014\u0018\u000e^=\u0011\u00051+cB\u0001#\u001d\u0003!\u0011V\r]8si\u0016\u0014\bC\u0001#\u001e'\tiR\bF\u0001O\u0003\u001d!\u0015n\u001d9mCf,\u0012aU\b\u0002)v\t\u0001!\u0001\u0005ESN\u0004H.Y=!\u0003\u0015\u0019u.\u001e8u+\u0005Av\"A-\u001e\u0003\u0005\taaQ8v]R\u0004\u0013\u0001C*vaB\u0014Xm]:\u0016\u0003u{\u0011AX\u000f\u0002\u0005\u0005I1+\u001e9qe\u0016\u001c8\u000fI\n\u0003Ku\n!!\u001b3\u0016\u0003\u0019\u000b1!\u001b3!\u0003!!xn\u0015;sS:<W#\u00014\u0011\u0005\u001dtgB\u00015m!\tI\u0017(D\u0001k\u0015\tY7(\u0001\u0004=e>|GOP\u0005\u0003[f\na\u0001\u0015:fI\u00164\u0017BA8q\u0005\u0019\u0019FO]5oO*\u0011Q.O\u0001\ni>\u001cFO]5oO\u0002\"2a];w!\t!X%D\u0001\u001e\u0011\u0015\t'\u00061\u0001G\u0011\u0015!'\u00061\u0001gS\u0011)#\u0007L\u0018\u0003\u000b\u0015\u0013&k\u0014*\u0014\u0005I\u001aH#A>\u0011\u0005Q\u0014$\u0001B%O\r>\u001b\"\u0001L:\u0015\u0003}\u0004\"\u0001\u001e\u0017\u0003\u000f]\u000b%KT%O\u000fN\u0011qf\u001d\u000b\u0003\u0003\u000f\u0001\"\u0001^\u0018\u0002\t%seiT\u0001\b/\u0006\u0013f*\u0013(H\u0003\u0015)%KU(S+\t\t\t\u0002E\u0002\u0002\u0014\u0011i\u0011\u0001\u0001\u0015\u0004\u000b\u0005]\u0001\u0003BA\r\u0003Gi!!a\u0007\u000b\t\u0005u\u0011qD\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\t:\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003K\tYBA\bv]\u000eDWmY6fIN#\u0018M\u00197fQ\r1\u0011q\u0003\u0015\u0004\u000f\u0005]\u0011!B5oM>\u0004DCCA\u0018\u0003k\t)%!\u0013\u0002NA\u0019a(!\r\n\u0007\u0005M\u0012H\u0001\u0003V]&$\bbBA\u001c\u0011\u0001\u0007\u0011\u0011H\u0001\u0004a>\u001c\b\u0003BA\u001e\u0003\u0003j!!!\u0010\u000b\u0007\u0005}R'\u0001\u0003vi&d\u0017\u0002BA\"\u0003{\u0011\u0001\u0002U8tSRLwN\u001c\u0005\u0007\u0003\u000fB\u0001\u0019\u00014\u0002\u00075\u001cx\rC\u0004\u0002L!\u0001\r!!\u0005\u0002\u0011M,g/\u001a:jifDq!a\u0014\t\u0001\u0004\t\t&A\u0003g_J\u001cW\rE\u0002?\u0003'J1!!\u0016:\u0005\u001d\u0011un\u001c7fC:D3\u0002CA-\u0003?\n\t'!\u001a\u0002hA\u0019a(a\u0017\n\u0007\u0005u\u0013H\u0001\u000beKB\u0014XmY1uK\u0012|e/\u001a:sS\u0012LgnZ\u0001\b[\u0016\u001c8/Y4fC\t\t\u0019'A)fqR,g\u000e\u001a\u0011tG\u0006d\u0017M\f;p_2\u001chF\\:d]I,\u0007o\u001c:uKJ\u001chFR5mi\u0016\u0014\u0018N\\4SKB|'\u000f^3sY\u0001\ng\u000e\u001a\u0011pm\u0016\u0014(/\u001b3fA\u0011|'+\u001a9peR\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017EAA5\u0003\u001d\u0011d&M\u001a/cI\n\u0001\u0002Z8SKB|'\u000f\u001e\u000b\u000b\u0003_\ty'!\u001d\u0002t\u0005U\u0004bBA\u001c\u0013\u0001\u0007\u0011\u0011\b\u0005\u0007\u0003\u000fJ\u0001\u0019\u00014\t\u000f\u0005-\u0013\u00021\u0001\u0002\u0012!9\u0011qO\u0005A\u0002\u0005e\u0014aB1di&|gn\u001d\t\u0007\u0003w\n\t)a\"\u000f\u0007y\ni(C\u0002\u0002\u0000e\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0004\u0006\u0015%\u0001\u0002'jgRT1!a :!\u0011\tY$!#\n\t\u0005-\u0015Q\b\u0002\u000b\u0007>$W-Q2uS>t\u0017A\u00024jYR,'\u000fF\u0004G\u0003#\u000b\u0019*!&\t\u000f\u0005]\"\u00021\u0001\u0002:!1\u0011q\t\u0006A\u0002\u0019Dq!a\u0013\u000b\u0001\u0004\t\t\"\u0001\u0003fG\"|G\u0003BA\u0018\u00037Ca!a\u0012\f\u0001\u00041G\u0003CA\u0018\u0003?\u000b\t+a)\t\u000f\u0005]B\u00021\u0001\u0002:!1\u0011q\t\u0007A\u0002\u0019D\u0011\"a\u001e\r!\u0003\u0005\r!!\u001f\u0002\u001d\u0015\u001c\u0007n\u001c\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011\u0011\u0016\u0016\u0005\u0003s\nYk\u000b\u0002\u0002.B!\u0011\u0011DAX\u0013\u0011\t\t,a\u0007\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0004xCJt\u0017N\\4\u0015\u0011\u0005=\u0012qWA]\u0003wCq!a\u000e\u000f\u0001\u0004\tI\u0004\u0003\u0004\u0002H9\u0001\rA\u001a\u0005\n\u0003or\u0001\u0013!a\u0001\u0003s\n\u0011c^1s]&tw\r\n3fM\u0006,H\u000e\u001e\u00134\u0003\u0015)'O]8s)!\ty#a1\u0002F\u0006\u001d\u0007bBA\u001c!\u0001\u0007\u0011\u0011\b\u0005\u0007\u0003\u000f\u0002\u0002\u0019\u00014\t\u0013\u0005]\u0004\u0003%AA\u0002\u0005e\u0014aD3se>\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0002\u0019\u0019LG\u000e^3sK\u0012LeNZ8\u0015\u0015\u0005=\u0012qZAi\u0003'\f)\u000eC\u0004\u00028I\u0001\r!!\u000f\t\r\u0005\u001d#\u00031\u0001g\u0011\u001d\tYE\u0005a\u0001\u0003#Aq!a\u001e\u0013\u0001\u0004\tI(A\u0005j]\u000e\u0014X-\\3oiR!\u0011qFAn\u0011\u001d\tYe\u0005a\u0001\u0003#\t!\"\u001a:s_J\u001cu.\u001e8u\u000319\u0018M\u001d8j]\u001e\u001cu.\u001e8u\u0003%A\u0017m]#se>\u00148/\u0006\u0002\u0002R\u0005Y\u0001.Y:XCJt\u0017N\\4t\u0003\u0015\u0011Xm]3u)\t\ty#A\u0003gYV\u001c\b.\u0001\u0004gS:L7\u000f[\u0001\u0011e\u0016\u0014XO\\,ji\"$U\r^1jYN$RAZAz\u0005\u0013Aq!!>\u001c\u0001\u0004\t90A\u0004tKR$\u0018N\\4\u0011\t\u0005e(Q\u0001\t\u0005\u0003w\u0014\t!\u0004\u0002\u0002~*\u0019\u0011q`\u001b\u0002\u0011M,G\u000f^5oONLAAa\u0001\u0002~\nyQ*\u001e;bE2,7+\u001a;uS:<7/\u0003\u0003\u0003\b\t\u0005!aB*fiRLgn\u001a\u0005\u0007\u0005\u0017Y\u0002\u0019\u00014\u0002\t9\fW.\u001a"
)
public abstract class Reporter {
   private int _errorCount = 0;
   private int _warningCount = 0;

   public static int Suppress() {
      Reporter$ var10000 = Reporter$.MODULE$;
      return 2;
   }

   public static int Count() {
      Reporter$ var10000 = Reporter$.MODULE$;
      return 1;
   }

   public static int Display() {
      Reporter$ var10000 = Reporter$.MODULE$;
      return 0;
   }

   public final Severity INFO() {
      return Reporter.INFO$.MODULE$;
   }

   public final Severity WARNING() {
      return Reporter.WARNING$.MODULE$;
   }

   public final Severity ERROR() {
      return Reporter.ERROR$.MODULE$;
   }

   public abstract void info0(final Position pos, final String msg, final Severity severity, final boolean force);

   public void doReport(final Position pos, final String msg, final Severity severity, final List actions) {
      this.info0(pos, msg, severity, false);
   }

   public int filter(final Position pos, final String msg, final Severity severity) {
      return 0;
   }

   public final void echo(final String msg) {
      this.echo(NoPosition$.MODULE$, msg, .MODULE$);
   }

   public final void echo(final Position pos, final String msg, final List actions) {
      if (this.filter(pos, msg, Reporter.INFO$.MODULE$) == 0) {
         this.doReport(pos, msg, Reporter.INFO$.MODULE$, actions);
      }
   }

   public final List echo$default$3() {
      return .MODULE$;
   }

   public final void warning(final Position pos, final String msg, final List actions) {
      this.filteredInfo(pos, msg, Reporter.WARNING$.MODULE$, actions);
   }

   public final List warning$default$3() {
      return .MODULE$;
   }

   public final void error(final Position pos, final String msg, final List actions) {
      this.filteredInfo(pos, msg, Reporter.ERROR$.MODULE$, actions);
   }

   public final List error$default$3() {
      return .MODULE$;
   }

   private void filteredInfo(final Position pos, final String msg, final Severity severity, final List actions) {
      int f = this.filter(pos, msg, severity);
      if (f < 2) {
         this.increment(severity);
      }

      if (f == 0) {
         this.doReport(pos, msg, severity, actions);
      }
   }

   public void increment(final Severity severity) {
      if (severity == Reporter.ERROR$.MODULE$) {
         ++this._errorCount;
      } else if (severity == Reporter.WARNING$.MODULE$) {
         ++this._warningCount;
      }
   }

   public int errorCount() {
      return this._errorCount;
   }

   public int warningCount() {
      return this._warningCount;
   }

   public boolean hasErrors() {
      return this.errorCount() > 0;
   }

   public boolean hasWarnings() {
      return this.warningCount() > 0;
   }

   public void reset() {
      this._errorCount = 0;
      this._warningCount = 0;
   }

   public void flush() {
   }

   public void finish() {
   }

   public String rerunWithDetails(final MutableSettings.SettingValue setting, final String name) {
      Object var3 = setting.value();
      return var3 instanceof Boolean && !BoxesRunTime.unboxToBoolean(var3) ? (new StringBuilder(26)).append("; re-run with ").append(name).append(" for details").toString() : (new StringBuilder(44)).append("; re-run enabling ").append(name).append(" for details, or try -help").toString();
   }

   public abstract static class Severity {
      private final int id;
      private final String toString;

      public int id() {
         return this.id;
      }

      public String toString() {
         return this.toString;
      }

      public Severity(final int id, final String toString) {
         this.id = id;
         this.toString = toString;
      }
   }

   public static class INFO$ extends Severity {
      public static final INFO$ MODULE$ = new INFO$();

      public INFO$() {
         super(0, "INFO");
      }
   }

   public static class WARNING$ extends Severity {
      public static final WARNING$ MODULE$ = new WARNING$();

      public WARNING$() {
         super(1, "WARNING");
      }
   }

   public static class ERROR$ extends Severity {
      public static final ERROR$ MODULE$ = new ERROR$();

      public ERROR$() {
         super(2, "ERROR");
      }
   }
}
