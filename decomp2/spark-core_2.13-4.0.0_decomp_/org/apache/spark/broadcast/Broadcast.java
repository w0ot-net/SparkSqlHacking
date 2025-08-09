package org.apache.spark.broadcast;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b!\u0002\f\u0018\u0003\u0003\u0001\u0003\u0002\u0003\u001c\u0001\u0005\u000b\u0007I\u0011A\u001c\t\u0011m\u0002!\u0011!Q\u0001\naB\u0001\u0002\u0010\u0001\u0003\u0004\u0003\u0006Y!\u0010\u0005\u0006\u001d\u0002!\ta\u0014\u0005\b+\u0002\u0001\r\u0011\"\u0003W\u0011\u001dQ\u0006\u00011A\u0005\nmCa!\u0019\u0001!B\u00139\u0006b\u00024\u0001\u0001\u0004%Ia\u001a\u0005\b]\u0002\u0001\r\u0011\"\u0003p\u0011\u0019\t\b\u0001)Q\u0005Q\")!\u000f\u0001C\u0001g\")A\u000f\u0001C\u0001k\")A\u000f\u0001C\u0001m\")\u0011\u0010\u0001C\u0001k\"1\u0011\u0010\u0001C\u00013iDa\u0001 \u0001\u0005\u0002e1\u0006\"B?\u0001\r#q\bBB@\u0001\r#\t\t\u0001C\u0004\u0002\u0006\u00011\t\"a\u0002\t\r\u0005-\u0001\u0001\"\u0005v\u0011\u001d\ti\u0001\u0001C!\u0003\u001f\u0011\u0011B\u0011:pC\u0012\u001c\u0017m\u001d;\u000b\u0005aI\u0012!\u00032s_\u0006$7-Y:u\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7\u0001A\u000b\u0003C\u0015\u001bB\u0001\u0001\u0012)aA\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0018\u000e\u0003)R!a\u000b\u0017\u0002\u0005%|'\"A\u0017\u0002\t)\fg/Y\u0005\u0003_)\u0012AbU3sS\u0006d\u0017N_1cY\u0016\u0004\"!\r\u001b\u000e\u0003IR!aM\r\u0002\u0011%tG/\u001a:oC2L!!\u000e\u001a\u0003\u000f1{wmZ5oO\u0006\u0011\u0011\u000eZ\u000b\u0002qA\u00111%O\u0005\u0003u\u0011\u0012A\u0001T8oO\u0006\u0019\u0011\u000e\u001a\u0011\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002?\u0003\u000ek\u0011a\u0010\u0006\u0003\u0001\u0012\nqA]3gY\u0016\u001cG/\u0003\u0002C\u007f\tA1\t\\1tgR\u000bw\r\u0005\u0002E\u000b2\u0001A!\u0002$\u0001\u0005\u00049%!\u0001+\u0012\u0005![\u0005CA\u0012J\u0013\tQEEA\u0004O_RD\u0017N\\4\u0011\u0005\rb\u0015BA'%\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005A#FCA)T!\r\u0011\u0006aQ\u0007\u0002/!)A\b\u0002a\u0002{!)a\u0007\u0002a\u0001q\u0005Aq,[:WC2LG-F\u0001X!\t\u0019\u0003,\u0003\u0002ZI\t9!i\\8mK\u0006t\u0017\u0001D0jgZ\u000bG.\u001b3`I\u0015\fHC\u0001/`!\t\u0019S,\u0003\u0002_I\t!QK\\5u\u0011\u001d\u0001g!!AA\u0002]\u000b1\u0001\u001f\u00132\u0003%y\u0016n\u001d,bY&$\u0007\u0005\u000b\u0002\bGB\u00111\u0005Z\u0005\u0003K\u0012\u0012\u0001B^8mCRLG.Z\u0001\r?\u0012,7\u000f\u001e:psNKG/Z\u000b\u0002QB\u0011\u0011\u000e\\\u0007\u0002U*\u00111\u000eL\u0001\u0005Y\u0006tw-\u0003\u0002nU\n11\u000b\u001e:j]\u001e\f\u0001c\u00183fgR\u0014x._*ji\u0016|F%Z9\u0015\u0005q\u0003\bb\u00021\n\u0003\u0003\u0005\r\u0001[\u0001\u000e?\u0012,7\u000f\u001e:psNKG/\u001a\u0011\u0002\u000bY\fG.^3\u0016\u0003\r\u000b\u0011\"\u001e8qKJ\u001c\u0018n\u001d;\u0015\u0003q#\"\u0001X<\t\u000bal\u0001\u0019A,\u0002\u0011\tdwnY6j]\u001e\fq\u0001Z3tiJ|\u0017\u0010\u0006\u0002]w\")\u0001p\u0004a\u0001/\u00069\u0011n\u001d,bY&$\u0017\u0001C4fiZ\u000bG.^3\u0015\u0003\r\u000b1\u0002Z8V]B,'o]5tiR\u0019A,a\u0001\t\u000ba\u0014\u0002\u0019A,\u0002\u0013\u0011|G)Z:ue>LHc\u0001/\u0002\n!)\u0001p\u0005a\u0001/\u0006Y\u0011m]:feR4\u0016\r\\5e\u0003!!xn\u0015;sS:<GCAA\t!\u0011\t\u0019\"!\t\u000f\t\u0005U\u0011Q\u0004\t\u0004\u0003/!SBAA\r\u0015\r\tYbH\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005}A%\u0001\u0004Qe\u0016$WMZ\u0005\u0004[\u0006\r\"bAA\u0010I\u0001"
)
public abstract class Broadcast implements Serializable, Logging {
   private final long id;
   private volatile boolean _isValid;
   private String _destroySite;
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

   public long id() {
      return this.id;
   }

   private boolean _isValid() {
      return this._isValid;
   }

   private void _isValid_$eq(final boolean x$1) {
      this._isValid = x$1;
   }

   private String _destroySite() {
      return this._destroySite;
   }

   private void _destroySite_$eq(final String x$1) {
      this._destroySite = x$1;
   }

   public Object value() {
      this.assertValid();
      return this.getValue();
   }

   public void unpersist() {
      this.unpersist(false);
   }

   public void unpersist(final boolean blocking) {
      this.assertValid();
      this.doUnpersist(blocking);
   }

   public void destroy() {
      this.destroy(false);
   }

   public void destroy(final boolean blocking) {
      this.assertValid();
      this._isValid_$eq(false);
      this._destroySite_$eq(Utils$.MODULE$.getCallSite(Utils$.MODULE$.getCallSite$default$1()).shortForm());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Destroying ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST..MODULE$, this.toString())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(from ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CALL_SITE_SHORT_FORM..MODULE$, this._destroySite())}))))));
      this.doDestroy(blocking);
   }

   public boolean isValid() {
      return this._isValid();
   }

   public abstract Object getValue();

   public abstract void doUnpersist(final boolean blocking);

   public abstract void doDestroy(final boolean blocking);

   public void assertValid() {
      if (!this._isValid()) {
         throw org.apache.spark.SparkException..MODULE$.internalError(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Attempted to use %s after it was destroyed (%s) "), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.toString(), this._destroySite()})), "BROADCAST");
      }
   }

   public String toString() {
      return "Broadcast(" + this.id() + ")";
   }

   public Broadcast(final long id, final ClassTag evidence$1) {
      this.id = id;
      Logging.$init$(this);
      this._isValid = true;
      this._destroySite = "";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
