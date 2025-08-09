package org.apache.spark.internal.config;

import scala.Enumeration;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]tAB\u001c9\u0011\u0003a$I\u0002\u0004Eq!\u0005A(\u0012\u0005\u0006\u0019\u0006!\tA\u0014\u0005\b\u001f\u0006\u0011\r\u0011\"\u0001Q\u0011\u0019y\u0016\u0001)A\u0005#\"9\u0001-\u0001b\u0001\n\u0003\t\u0007BB3\u0002A\u0003%!\rC\u0004g\u0003\t\u0007I\u0011\u0001)\t\r\u001d\f\u0001\u0015!\u0003R\u0011\u001dA\u0017A1A\u0005\u0002ACa![\u0001!\u0002\u0013\t\u0006b\u00026\u0002\u0005\u0004%\ta\u001b\u0005\u0007a\u0006\u0001\u000b\u0011\u00027\t\u000fE\f!\u0019!C\u0001C\"1!/\u0001Q\u0001\n\tDqa]\u0001C\u0002\u0013\u0005\u0011\r\u0003\u0004u\u0003\u0001\u0006IA\u0019\u0005\bk\u0006\u0011\r\u0011\"\u0001w\u0011\u0019Y\u0018\u0001)A\u0005o\"9A0\u0001b\u0001\n\u00031\bBB?\u0002A\u0003%q\u000fC\u0004\u007f\u0003\t\u0007I\u0011\u0001<\t\r}\f\u0001\u0015!\u0003x\u0011!\t\t!\u0001b\u0001\n\u00031\bbBA\u0002\u0003\u0001\u0006Ia\u001e\u0005\n\u0003\u000b\t!\u0019!C\u0001\u0003\u000fA\u0001\"!\u0005\u0002A\u0003%\u0011\u0011\u0002\u0005\n\u0003'\t!\u0019!C\u0001\u0003\u000fA\u0001\"!\u0006\u0002A\u0003%\u0011\u0011B\u0004\b\u0003/\t\u0001\u0012AA\r\r\u001d\ti\"\u0001E\u0001\u0003?Aa\u0001\u0014\u0010\u0005\u0002\u0005\u001d\u0002\"CA\u0015=\t\u0007I\u0011AA\u0016\u0011!\t)D\bQ\u0001\n\u00055\u0002\"CA\u001c=\t\u0007I\u0011AA\u0016\u0011!\tID\bQ\u0001\n\u00055\u0002\"CA\u001e=\t\u0007I\u0011AA\u0016\u0011!\tiD\bQ\u0001\n\u00055\u0002\"CA =\t\u0007I\u0011AA\u0016\u0011!\t\tE\bQ\u0001\n\u00055\u0002\"CA\"=\t\u0007I\u0011AA\u0016\u0011!\t)E\bQ\u0001\n\u00055\u0002\"CA$=\u0005\u0005I\u0011BA%\u0011!\tY&\u0001b\u0001\n\u0003\u0001\u0006bBA/\u0003\u0001\u0006I!\u0015\u0005\t\u0003?\n!\u0019!C\u0001m\"9\u0011\u0011M\u0001!\u0002\u00139\b\u0002CA2\u0003\t\u0007I\u0011\u0001<\t\u000f\u0005\u0015\u0014\u0001)A\u0005o\"I\u0011qM\u0001C\u0002\u0013\u0005\u0011\u0011\u000e\u0005\t\u0003[\n\u0001\u0015!\u0003\u0002l!A\u0011qN\u0001C\u0002\u0013\u0005\u0001\u000bC\u0004\u0002r\u0005\u0001\u000b\u0011B)\t\u0011\u0005M\u0014A1A\u0005\u0002ACq!!\u001e\u0002A\u0003%\u0011+\u0001\u0004EKBdw.\u001f\u0006\u0003si\naaY8oM&<'BA\u001e=\u0003!Ig\u000e^3s]\u0006d'BA\u001f?\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0004)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0003\u0006\u0019qN]4\u0011\u0005\r\u000bQ\"\u0001\u001d\u0003\r\u0011+\u0007\u000f\\8z'\t\ta\t\u0005\u0002H\u00156\t\u0001JC\u0001J\u0003\u0015\u00198-\u00197b\u0013\tY\u0005J\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t!)A\u0007S\u000b\u000e{e+\u0012*Z?6{E)R\u000b\u0002#B\u00191I\u0015+\n\u0005MC$aC\"p]\u001aLw-\u00128uef\u0004\"!\u0016/\u000f\u0005YS\u0006CA,I\u001b\u0005A&BA-N\u0003\u0019a$o\\8u}%\u00111\fS\u0001\u0007!J,G-\u001a4\n\u0005us&AB*ue&twM\u0003\u0002\\\u0011\u0006q!+R\"P-\u0016\u0013\u0016lX'P\t\u0016\u0003\u0013A\u0007*F\u0007>3VIU-`\u0007>k\u0005KU#T'&{ejX\"P\t\u0016\u001bU#\u00012\u0011\u0007\r\u001bG+\u0003\u0002eq\t\u0019r\n\u001d;j_:\fGnQ8oM&<WI\u001c;ss\u0006Y\"+R\"P-\u0016\u0013\u0016lX\"P\u001bB\u0013ViU*J\u001f:{6i\u0014#F\u0007\u0002\nQCU#D\u001fZ+%+W0N\u001f\u0012+uLR!D)>\u0013\u0016,\u0001\fS\u000b\u000e{e+\u0012*Z?6{E)R0G\u0003\u000e#vJU-!\u0003I\u0011ViQ(W\u000bJKv\fR%S\u000b\u000e#vJU-\u0002'I+5i\u0014,F%f{F)\u0013*F\u0007R{%+\u0017\u0011\u0002!I+5i\u0014,F%f{F+S'F\u001fV#V#\u00017\u0011\u0007\r\u001bW\u000e\u0005\u0002H]&\u0011q\u000e\u0013\u0002\u0005\u0019>tw-A\tS\u000b\u000e{e+\u0012*Z?RKU*R(V)\u0002\nQBW(P\u0017\u0016+\u0005+\u0012*`+Jc\u0015A\u0004.P\u001f.+U\tU#S?V\u0013F\nI\u0001\u00145>{5*R#Q\u000bJ{F)\u0013*F\u0007R{%+W\u0001\u00155>{5*R#Q\u000bJ{F)\u0013*F\u0007R{%+\u0017\u0011\u0002+I+E+Q%O\u000b\u0012{\u0016\t\u0015)M\u0013\u000e\u000bE+S(O'V\tq\u000fE\u0002D%b\u0004\"aR=\n\u0005iD%aA%oi\u00061\"+\u0012+B\u0013:+EiX!Q!2K5)\u0011+J\u001f:\u001b\u0006%\u0001\tS\u000bR\u000b\u0015JT#E?\u0012\u0013\u0016JV#S'\u0006\t\"+\u0012+B\u0013:+Ei\u0018#S\u0013Z+%k\u0015\u0011\u0002#I+\u0015\tU#S?&#VIU!U\u0013>s5+\u0001\nS\u000b\u0006\u0003VIU0J)\u0016\u0013\u0016\tV%P\u001dN\u0003\u0013\u0001F'B1~+\u0005,R\"V)>\u0013vLU#U%&+5+A\u000bN\u0003b{V\tW#D+R{%k\u0018*F)JKUi\u0015\u0011\u0002%M\u0003&+R!E?>+Fk\u0018#S\u0013Z+%kU\u000b\u0003\u0003\u0013\u0001Ba\u0011*\u0002\fA\u0019q)!\u0004\n\u0007\u0005=\u0001JA\u0004C_>dW-\u00198\u0002'M\u0003&+R!E?>+Fk\u0018#S\u0013Z+%k\u0015\u0011\u0002\u001fM\u0003&+R!E?>+FkX!Q!N\u000b\u0001c\u0015)S\u000b\u0006#ulT+U?\u0006\u0003\u0006k\u0015\u0011\u0002+]{'o[3s'\u0016dWm\u0019;j_:\u0004v\u000e\\5dsB\u0019\u00111\u0004\u0010\u000e\u0003\u0005\u0011QcV8sW\u0016\u00148+\u001a7fGRLwN\u001c)pY&\u001c\u0017pE\u0002\u001f\u0003C\u00012aRA\u0012\u0013\r\t)\u0003\u0013\u0002\f\u000b:,X.\u001a:bi&|g\u000e\u0006\u0002\u0002\u001a\u0005q1i\u0014*F'~3%+R#`\u0003N\u001bUCAA\u0017!\u0011\ty#!\r\u000e\u0003yIA!a\r\u0002$\t)a+\u00197vK\u0006y1i\u0014*F'~3%+R#`\u0003N\u001b\u0005%A\bD\u001fJ+5k\u0018$S\u000b\u0016{F)R*D\u0003A\u0019uJU#T?\u001a\u0013V)R0E\u000bN\u001b\u0005%A\bN\u000b6{%+W0G%\u0016+u,Q*D\u0003AiU)T(S3~3%+R#`\u0003N\u001b\u0005%\u0001\tN\u000b6{%+W0G%\u0016+u\fR#T\u0007\u0006\tR*R'P%f{fIU#F?\u0012+5k\u0011\u0011\u0002\u0013]{%kS#S?&#\u0015AC,P%.+%kX%EA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\n\t\u0005\u0003\u001b\n9&\u0004\u0002\u0002P)!\u0011\u0011KA*\u0003\u0011a\u0017M\\4\u000b\u0005\u0005U\u0013\u0001\u00026bm\u0006LA!!\u0017\u0002P\t1qJ\u00196fGR\fqcV(S\u0017\u0016\u0013vlU#M\u000b\u000e#\u0016j\u0014(`!>c\u0015jQ-\u00021]{%kS#S?N+E*R\"U\u0013>su\fU(M\u0013\u000eK\u0006%A\u0007E\u000b\u001a\u000bU\u000b\u0014+`\u0007>\u0013ViU\u0001\u000f\t\u00163\u0015)\u0016'U?\u000e{%+R*!\u0003-i\u0015\tW0E%&3VIU*\u0002\u00195\u000b\u0005l\u0018#S\u0013Z+%k\u0015\u0011\u0002#\u0005\u0003\u0006k\u0018(V\u001b\n+%kX'P\tVcu*\u0006\u0002\u0002lA\u00191i\u0019=\u0002%\u0005\u0003\u0006k\u0018(V\u001b\n+%kX'P\tVcu\nI\u0001\u0012\tJKe+\u0012*`\u0013\u0012{\u0006+\u0011+U\u000bJs\u0015A\u0005#S\u0013Z+%kX%E?B\u000bE\u000bV#S\u001d\u0002\na\"\u0011)Q?&#u\fU!U)\u0016\u0013f*A\bB!B{\u0016\nR0Q\u0003R#VI\u0015(!\u0001"
)
public final class Deploy {
   public static ConfigEntry APP_ID_PATTERN() {
      return Deploy$.MODULE$.APP_ID_PATTERN();
   }

   public static ConfigEntry DRIVER_ID_PATTERN() {
      return Deploy$.MODULE$.DRIVER_ID_PATTERN();
   }

   public static OptionalConfigEntry APP_NUMBER_MODULO() {
      return Deploy$.MODULE$.APP_NUMBER_MODULO();
   }

   public static ConfigEntry MAX_DRIVERS() {
      return Deploy$.MODULE$.MAX_DRIVERS();
   }

   public static ConfigEntry DEFAULT_CORES() {
      return Deploy$.MODULE$.DEFAULT_CORES();
   }

   public static ConfigEntry WORKER_SELECTION_POLICY() {
      return Deploy$.MODULE$.WORKER_SELECTION_POLICY();
   }

   public static ConfigEntry SPREAD_OUT_APPS() {
      return Deploy$.MODULE$.SPREAD_OUT_APPS();
   }

   public static ConfigEntry SPREAD_OUT_DRIVERS() {
      return Deploy$.MODULE$.SPREAD_OUT_DRIVERS();
   }

   public static ConfigEntry MAX_EXECUTOR_RETRIES() {
      return Deploy$.MODULE$.MAX_EXECUTOR_RETRIES();
   }

   public static ConfigEntry REAPER_ITERATIONS() {
      return Deploy$.MODULE$.REAPER_ITERATIONS();
   }

   public static ConfigEntry RETAINED_DRIVERS() {
      return Deploy$.MODULE$.RETAINED_DRIVERS();
   }

   public static ConfigEntry RETAINED_APPLICATIONS() {
      return Deploy$.MODULE$.RETAINED_APPLICATIONS();
   }

   public static OptionalConfigEntry ZOOKEEPER_DIRECTORY() {
      return Deploy$.MODULE$.ZOOKEEPER_DIRECTORY();
   }

   public static OptionalConfigEntry ZOOKEEPER_URL() {
      return Deploy$.MODULE$.ZOOKEEPER_URL();
   }

   public static OptionalConfigEntry RECOVERY_TIMEOUT() {
      return Deploy$.MODULE$.RECOVERY_TIMEOUT();
   }

   public static ConfigEntry RECOVERY_DIRECTORY() {
      return Deploy$.MODULE$.RECOVERY_DIRECTORY();
   }

   public static ConfigEntry RECOVERY_MODE_FACTORY() {
      return Deploy$.MODULE$.RECOVERY_MODE_FACTORY();
   }

   public static OptionalConfigEntry RECOVERY_COMPRESSION_CODEC() {
      return Deploy$.MODULE$.RECOVERY_COMPRESSION_CODEC();
   }

   public static ConfigEntry RECOVERY_MODE() {
      return Deploy$.MODULE$.RECOVERY_MODE();
   }

   public static class WorkerSelectionPolicy$ extends Enumeration {
      public static final WorkerSelectionPolicy$ MODULE$ = new WorkerSelectionPolicy$();
      private static final Enumeration.Value CORES_FREE_ASC;
      private static final Enumeration.Value CORES_FREE_DESC;
      private static final Enumeration.Value MEMORY_FREE_ASC;
      private static final Enumeration.Value MEMORY_FREE_DESC;
      private static final Enumeration.Value WORKER_ID;

      static {
         CORES_FREE_ASC = MODULE$.Value();
         CORES_FREE_DESC = MODULE$.Value();
         MEMORY_FREE_ASC = MODULE$.Value();
         MEMORY_FREE_DESC = MODULE$.Value();
         WORKER_ID = MODULE$.Value();
      }

      public Enumeration.Value CORES_FREE_ASC() {
         return CORES_FREE_ASC;
      }

      public Enumeration.Value CORES_FREE_DESC() {
         return CORES_FREE_DESC;
      }

      public Enumeration.Value MEMORY_FREE_ASC() {
         return MEMORY_FREE_ASC;
      }

      public Enumeration.Value MEMORY_FREE_DESC() {
         return MEMORY_FREE_DESC;
      }

      public Enumeration.Value WORKER_ID() {
         return WORKER_ID;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(WorkerSelectionPolicy$.class);
      }
   }
}
