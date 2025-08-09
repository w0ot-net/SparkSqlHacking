package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md!\u0003\r\u001a!\u0003\r\t\u0001IA5\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u0015Q\u0003A\"\u0001,\u0011\u0015\u0001\u0004A\"\u00012\r\u001d!\u0004\u0001%A\u0002\u0002UBQ!\n\u0003\u0005\u0002\u0019BqA\u000e\u0003C\u0002\u0013\u0005q\u0007B\u0003:\u0001\t\u0005!\b\u0003\u0004\u0002\u0006\u00011\tb\u000e\u0004\u0006\u007f\u0001\t\t\u0001\u0011\u0005\u0006\u0003&!\tA\u0011\u0005\u0006\u0007&1\t\u0001\u0012\u0005\b]&\t\n\u0011\"\u0001p\u0011\u0019Q\u0018\u0002)Q\u0005w\")a0\u0003C\u0001\u007f\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001B\u0002@\u0001\t\u0003\ti\u0001C\u0004\u0002\u0012\u0001!\t!a\u0005\t\u000f\u0005%\u0002\u0001\"\u0001\u0002,!9\u0011\u0011\t\u0001\u0005\u0002\u0005\r\u0003bBA%\u0001\u0011\u0005\u00111\n\u0005\b\u0003#\u0001A\u0011AA(\u0011\u001d\tI\u0003\u0001C\u0001\u0003/Bq!!\u0011\u0001\t\u0003\t\tGA\u0005SKB|'\u000f^5oO*\u0011!dG\u0001\tS:$XM\u001d8bY*\u0011A$H\u0001\be\u00164G.Z2u\u0015\u0005q\u0012!B:dC2\f7\u0001A\n\u0003\u0001\u0005\u0002\"AI\u0012\u000e\u0003uI!\u0001J\u000f\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tq\u0005\u0005\u0002#Q%\u0011\u0011&\b\u0002\u0005+:LG/\u0001\u0005sKB|'\u000f^3s+\u0005a\u0003CA\u0017/\u001b\u0005I\u0012BA\u0018\u001a\u0005!\u0011V\r]8si\u0016\u0014\u0018AC2veJ,g\u000e\u001e*v]V\t!\u0007\u0005\u00024\t5\t\u0001A\u0001\u0007Sk:\u0014V\r]8si&twm\u0005\u0002\u0005C\u0005I!/\u001a9peRLgnZ\u000b\u0002qA\u00111g\u0002\u0002\u0010!\u0016\u0014(+\u001e8SKB|'\u000f^5oOF\u00111H\u0010\t\u0003EqJ!!P\u000f\u0003\u000f9{G\u000f[5oOB\u00111'\u0003\u0002\u0014!\u0016\u0014(+\u001e8SKB|'\u000f^5oO\n\u000b7/Z\n\u0003\u0013\u0005\na\u0001P5oSRtD#\u0001 \u0002%\u0011,\u0007O]3dCRLwN\\,be:Lgn\u001a\u000b\bO\u0015c\u0015lW/`\u0011\u001515\u00021\u0001H\u0003\r\u0001xn\u001d\t\u0003g!K!!\u0013&\u0003\u0011A{7/\u001b;j_:L!aS\r\u0003\u0013A{7/\u001b;j_:\u001c\b\"B'\f\u0001\u0004q\u0015aA7tOB\u0011qJ\u0016\b\u0003!R\u0003\"!U\u000f\u000e\u0003IS!aU\u0010\u0002\rq\u0012xn\u001c;?\u0013\t)V$\u0001\u0004Qe\u0016$WMZ\u0005\u0003/b\u0013aa\u0015;sS:<'BA+\u001e\u0011\u0015Q6\u00021\u0001O\u0003\u0015\u0019\u0018N\\2f\u0011\u0015a6\u00021\u0001O\u0003\u0011\u0019\u0018\u000e^3\t\u000by[\u0001\u0019\u0001(\u0002\r=\u0014\u0018nZ5o\u0011\u001d\u00017\u0002%AA\u0002\u0005\fq!Y2uS>t7\u000fE\u0002cK\"t!AI2\n\u0005\u0011l\u0012a\u00029bG.\fw-Z\u0005\u0003M\u001e\u0014A\u0001T5ti*\u0011A-\b\t\u0003S2l\u0011A\u001b\u0006\u0003Wf\tA!\u001e;jY&\u0011QN\u001b\u0002\u000b\u0007>$W-Q2uS>t\u0017\u0001\b3faJ,7-\u0019;j_:<\u0016M\u001d8j]\u001e$C-\u001a4bk2$HEN\u000b\u0002a*\u0012\u0011-]\u0016\u0002eB\u00111\u000f_\u0007\u0002i*\u0011QO^\u0001\nk:\u001c\u0007.Z2lK\u0012T!a^\u000f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002zi\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002#M,\b\u000f\u001d7f[\u0016tG/\u001a3FeJ|'\u000f\u0005\u0002#y&\u0011Q0\b\u0002\b\u0005>|G.Z1o\u0003Y\u0019X\u000f\u001d9mK6,g\u000e^#se>\u0014X*Z:tC\u001e,Gc\u0001(\u0002\u0002!1\u00111\u0001\bA\u00029\u000bA\"\u001a:s_JlUm]:bO\u0016\fq\u0002U3s%Vt'+\u001a9peRLgnZ\u0001\u0015gV\u0004\b\u000f\\3nK:$H+\u001f9feN#\u0018\r^3\u0015\u00079\u000bY\u0001\u0003\u0004\u0002\u0004=\u0001\rA\u0014\u000b\u0004\u001d\u0006=\u0001BBA\u0002!\u0001\u0007a*\u0001\u0004j]\u001a|'/\u001c\u000b\u0004O\u0005U\u0001\"B'\u0012\u0001\u0004q\u0005FC\t\u0002\u001a\u0005}\u0011\u0011\u0005.\u0002&A\u0019!%a\u0007\n\u0007\u0005uQD\u0001\u000beKB\u0014XmY1uK\u0012|e/\u001a:sS\u0012LgnZ\u0001\b[\u0016\u001c8/Y4fC\t\t\u0019#\u0001*UQ&\u001c\bEZ8so\u0006\u0014Hm\u001d\u0011u_\u0002\"\b.\u001a\u0011d_J\u0014Xm\u001d9p]\u0012Lgn\u001a\u0011nKRDw\u000e\u001a\u0011j]\u0002\u0012X\r]8si\u0016\u0014\b%L\u0017!_Z,'O]5eK\u0002\u0012X\r]8si\u0016\u0014\b%\u001b8ti\u0016\fG-\t\u0002\u0002(\u00051!GL\u00192]I\nqa^1s]&tw\rF\u0002(\u0003[AQ!\u0014\nA\u00029C#BEA\r\u0003?\t\tCWA\u0013Q)\u0011\u00121GA\u0010\u0003sQ\u0016Q\b\t\u0004E\u0005U\u0012bAA\u001c;\tQA-\u001a9sK\u000e\fG/\u001a3\"\u0005\u0005m\u0012AI+tK\u0002\u0002'/\u001e8SKB|'\u000f^5oO::\u0018M\u001d8j]\u001e\u0004\u0007%\u001b8ti\u0016\fG-\t\u0002\u0002@\u00051!GL\u00194]Q\n1b\u001a7pE\u0006dWI\u001d:peR\u0019q%!\u0012\t\u000b5\u001b\u0002\u0019\u0001()\u0015M\tI\"a\b\u0002\"i\u000b)#A\u0003bE>\u0014H\u000fF\u0002<\u0003\u001bBQ!\u0014\u000bA\u00029#RaJA)\u0003'BQAR\u000bA\u0002\u001dCQ!T\u000bA\u00029C#\"FA\r\u0003?\t\tCWA\u0013)\u00159\u0013\u0011LA.\u0011\u00151e\u00031\u0001H\u0011\u0015ie\u00031\u0001OQ)1\u0012\u0011DA\u0010\u0003CQ\u0016Q\u0005\u0015\u000b-\u0005M\u0012qDA\u001d5\u0006uB#B\u0014\u0002d\u0005\u0015\u0004\"\u0002$\u0018\u0001\u00049\u0005\"B'\u0018\u0001\u0004q\u0005FC\f\u0002\u001a\u0005}\u0011\u0011\u0005.\u0002&I1\u00111NA8\u0003c2a!!\u001c\u0001\u0001\u0005%$\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004CA\u0017\u0001!\ti#\n"
)
public interface Reporting {
   Reporter reporter();

   RunReporting currentRun();

   PerRunReportingBase PerRunReporting();

   // $FF: synthetic method
   static String supplementTyperState$(final Reporting $this, final String errorMessage) {
      return $this.supplementTyperState(errorMessage);
   }

   default String supplementTyperState(final String errorMessage) {
      return errorMessage;
   }

   // $FF: synthetic method
   static String supplementErrorMessage$(final Reporting $this, final String errorMessage) {
      return $this.supplementErrorMessage(errorMessage);
   }

   default String supplementErrorMessage(final String errorMessage) {
      return this.currentRun().reporting().supplementErrorMessage(errorMessage);
   }

   // $FF: synthetic method
   static void inform$(final Reporting $this, final String msg) {
      $this.inform(msg);
   }

   default void inform(final String msg) {
      this.inform(((Positions)this).NoPosition(), msg);
   }

   // $FF: synthetic method
   static void warning$(final Reporting $this, final String msg) {
      $this.warning(msg);
   }

   /** @deprecated */
   default void warning(final String msg) {
      this.warning(((Positions)this).NoPosition(), msg);
   }

   // $FF: synthetic method
   static void globalError$(final Reporting $this, final String msg) {
      $this.globalError(msg);
   }

   default void globalError(final String msg) {
      this.globalError(((Positions)this).NoPosition(), msg);
   }

   // $FF: synthetic method
   static Nothing abort$(final Reporting $this, final String msg) {
      return $this.abort(msg);
   }

   default Nothing abort(final String msg) {
      String augmented = this.supplementErrorMessage(msg);
      this.globalError(augmented);
      throw new FatalError(augmented);
   }

   // $FF: synthetic method
   static void inform$(final Reporting $this, final Position pos, final String msg) {
      $this.inform(pos, msg);
   }

   default void inform(final Position pos, final String msg) {
      Reporter qual$1 = this.reporter();
      if (qual$1 == null) {
         throw null;
      } else {
         List x$3 = .MODULE$;
         qual$1.echo(pos, msg, x$3);
      }
   }

   // $FF: synthetic method
   static void warning$(final Reporting $this, final Position pos, final String msg) {
      $this.warning(pos, msg);
   }

   /** @deprecated */
   default void warning(final Position pos, final String msg) {
      Reporter qual$1 = this.reporter();
      if (qual$1 == null) {
         throw null;
      } else {
         List x$3 = .MODULE$;
         qual$1.warning(pos, msg, x$3);
      }
   }

   // $FF: synthetic method
   static void globalError$(final Reporting $this, final Position pos, final String msg) {
      $this.globalError(pos, msg);
   }

   default void globalError(final Position pos, final String msg) {
      Reporter qual$1 = this.reporter();
      if (qual$1 == null) {
         throw null;
      } else {
         List x$3 = .MODULE$;
         qual$1.error(pos, msg, x$3);
      }
   }

   static void $init$(final Reporting $this) {
   }

   public interface RunReporting {
      void scala$reflect$internal$Reporting$RunReporting$_setter_$reporting_$eq(final PerRunReportingBase x$1);

      PerRunReportingBase reporting();

      // $FF: synthetic method
      Reporting scala$reflect$internal$Reporting$RunReporting$$$outer();

      static void $init$(final RunReporting $this) {
         $this.scala$reflect$internal$Reporting$RunReporting$_setter_$reporting_$eq($this.scala$reflect$internal$Reporting$RunReporting$$$outer().PerRunReporting());
      }
   }

   public abstract class PerRunReportingBase {
      private boolean supplementedError;
      // $FF: synthetic field
      public final Reporting $outer;

      public abstract void deprecationWarning(final Position pos, final String msg, final String since, final String site, final String origin, final List actions);

      public List deprecationWarning$default$6() {
         return .MODULE$;
      }

      public String supplementErrorMessage(final String errorMessage) {
         if (this.supplementedError) {
            return errorMessage;
         } else {
            this.supplementedError = true;
            return this.scala$reflect$internal$Reporting$PerRunReportingBase$$$outer().supplementTyperState(errorMessage);
         }
      }

      // $FF: synthetic method
      public Reporting scala$reflect$internal$Reporting$PerRunReportingBase$$$outer() {
         return this.$outer;
      }

      public PerRunReportingBase() {
         if (Reporting.this == null) {
            throw null;
         } else {
            this.$outer = Reporting.this;
            super();
            this.supplementedError = false;
         }
      }
   }
}
