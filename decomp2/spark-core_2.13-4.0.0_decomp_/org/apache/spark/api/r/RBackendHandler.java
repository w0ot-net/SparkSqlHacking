package org.apache.spark.api.r;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.timeout.ReadTimeoutException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.R$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction1;

@Sharable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!B\u0005\u000b\u0001)!\u0002\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0019\t\u000bQ\u0002A\u0011A\u001b\t\u000ba\u0002A\u0011I\u001d\t\u000b\u0011\u0003A\u0011I#\t\u000b\u001d\u0003A\u0011\t%\t\u000ba\u0003A\u0011A-\t\u000by\u0004A\u0011A@\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0016\ty!KQ1dW\u0016tG\rS1oI2,'O\u0003\u0002\f\u0019\u0005\t!O\u0003\u0002\u000e\u001d\u0005\u0019\u0011\r]5\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c2\u0001A\u000b)!\r1RdH\u0007\u0002/)\u0011\u0001$G\u0001\bG\"\fgN\\3m\u0015\tQ2$A\u0003oKR$\u0018PC\u0001\u001d\u0003\tIw.\u0003\u0002\u001f/\tY2+[7qY\u0016\u001c\u0005.\u00198oK2LeNY8v]\u0012D\u0015M\u001c3mKJ\u00042\u0001I\u0012&\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#!B!se\u0006L\bC\u0001\u0011'\u0013\t9\u0013E\u0001\u0003CsR,\u0007CA\u0015-\u001b\u0005Q#BA\u0016\u000f\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0017+\u0005\u001daunZ4j]\u001e\faa]3sm\u0016\u00148\u0001\u0001\t\u0003cIj\u0011AC\u0005\u0003g)\u0011\u0001B\u0015\"bG.,g\u000eZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005Y:\u0004CA\u0019\u0001\u0011\u0015q#\u00011\u00011\u00031\u0019\u0007.\u00198oK2\u0014V-\u001931)\rQTH\u0011\t\u0003AmJ!\u0001P\u0011\u0003\tUs\u0017\u000e\u001e\u0005\u0006}\r\u0001\raP\u0001\u0004GRD\bC\u0001\fA\u0013\t\tuCA\u000bDQ\u0006tg.\u001a7IC:$G.\u001a:D_:$X\r\u001f;\t\u000b\r\u001b\u0001\u0019A\u0010\u0002\u00075\u001cx-A\ndQ\u0006tg.\u001a7SK\u0006$7i\\7qY\u0016$X\r\u0006\u0002;\r\")a\b\u0002a\u0001\u007f\u0005yQ\r_2faRLwN\\\"bk\u001eDG\u000fF\u0002;\u0013*CQAP\u0003A\u0002}BQaS\u0003A\u00021\u000bQaY1vg\u0016\u0004\"!T+\u000f\u00059\u001bfBA(S\u001b\u0005\u0001&BA)0\u0003\u0019a$o\\8u}%\t!%\u0003\u0002UC\u00059\u0001/Y2lC\u001e,\u0017B\u0001,X\u0005%!\u0006N]8xC\ndWM\u0003\u0002UC\u0005\u0001\u0002.\u00198eY\u0016lU\r\u001e5pI\u000e\u000bG\u000e\u001c\u000b\bui{\u0016n\u001b9z\u0011\u0015Yf\u00011\u0001]\u0003!I7o\u0015;bi&\u001c\u0007C\u0001\u0011^\u0013\tq\u0016EA\u0004C_>dW-\u00198\t\u000b\u00014\u0001\u0019A1\u0002\u000b=\u0014'.\u00133\u0011\u0005\t4gBA2e!\ty\u0015%\u0003\u0002fC\u00051\u0001K]3eK\u001aL!a\u001a5\u0003\rM#(/\u001b8h\u0015\t)\u0017\u0005C\u0003k\r\u0001\u0007\u0011-\u0001\u0006nKRDw\u000e\u001a(b[\u0016DQ\u0001\u001c\u0004A\u00025\fqA\\;n\u0003J<7\u000f\u0005\u0002!]&\u0011q.\t\u0002\u0004\u0013:$\b\"B9\u0007\u0001\u0004\u0011\u0018a\u00013jgB\u00111o^\u0007\u0002i*\u0011A$\u001e\u0006\u0002m\u0006!!.\u0019<b\u0013\tAHOA\bECR\f\u0017J\u001c9viN#(/Z1n\u0011\u0015Qh\u00011\u0001|\u0003\r!wn\u001d\t\u0003grL!! ;\u0003!\u0011\u000bG/Y(viB,Ho\u0015;sK\u0006l\u0017\u0001\u0003:fC\u0012\f%oZ:\u0015\r\u0005\u0005\u0011qBA\t!\u0011\u00013%a\u0001\u0011\t\u0005\u0015\u00111B\u0007\u0003\u0003\u000fQ1!!\u0003v\u0003\u0011a\u0017M\\4\n\t\u00055\u0011q\u0001\u0002\u0007\u001f\nTWm\u0019;\t\u000b1<\u0001\u0019A7\t\u000bE<\u0001\u0019\u0001:\u0002)\u0019Lg\u000eZ'bi\u000eDW\rZ*jO:\fG/\u001e:f)\u0019\t9\"!\b\u0002DA!\u0001%!\u0007n\u0013\r\tY\"\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005}\u0001\u00021\u0001\u0002\"\u00059\u0002/\u0019:b[\u0016$XM\u001d+za\u0016\u001cxJZ'fi\"|Gm\u001d\t\u0005A\r\n\u0019\u0003\u0005\u0003!G\u0005\u0015\u0002\u0007BA\u0014\u0003c\u0001RAYA\u0015\u0003[I1!a\u000bi\u0005\u0015\u0019E.Y:t!\u0011\ty#!\r\r\u0001\u0011a\u00111GA\u000f\u0003\u0003\u0005\tQ!\u0001\u00026\t\u0019q\fJ\u0019\u0012\t\u0005]\u0012Q\b\t\u0004A\u0005e\u0012bAA\u001eC\t9aj\u001c;iS:<\u0007c\u0001\u0011\u0002@%\u0019\u0011\u0011I\u0011\u0003\u0007\u0005s\u0017\u0010C\u0004\u0002F!\u0001\r!!\u0001\u0002\t\u0005\u0014xm\u001d\u0015\u0004\u0001\u0005%\u0003\u0003BA&\u0003;rA!!\u0014\u0002Z9!\u0011qJA,\u001d\u0011\t\t&!\u0016\u000f\u0007=\u000b\u0019&C\u0001\u001d\u0013\tQ2$\u0003\u0002\u00193%\u0019\u00111L\f\u0002\u001d\rC\u0017M\u001c8fY\"\u000bg\u000e\u001a7fe&!\u0011qLA1\u0005!\u0019\u0006.\u0019:bE2,'bAA./\u0001"
)
public class RBackendHandler extends SimpleChannelInboundHandler implements Logging {
   private final RBackend server;
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

   public void channelRead0(final ChannelHandlerContext ctx, final byte[] msg) {
      ByteArrayOutputStream bos;
      label59: {
         DataInputStream dis;
         DataOutputStream dos;
         boolean isStatic;
         String objId;
         String methodName;
         int numArgs;
         label61: {
            ByteArrayInputStream bis = new ByteArrayInputStream(msg);
            dis = new DataInputStream(bis);
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            isStatic = SerDe$.MODULE$.readBoolean(dis);
            objId = SerDe$.MODULE$.readString(dis);
            methodName = SerDe$.MODULE$.readString(dis);
            numArgs = SerDe$.MODULE$.readInt(dis);
            String var11 = "SparkRHandler";
            if (objId == null) {
               if (var11 != null) {
                  break label61;
               }
            } else if (!objId.equals(var11)) {
               break label61;
            }

            label50: {
               switch (methodName == null ? 0 : methodName.hashCode()) {
                  case -1631981518:
                     if ("stopBackend".equals(methodName)) {
                        SerDe$.MODULE$.writeInt(dos, 0);
                        SerDe$.MODULE$.writeType(dos, "void");
                        this.server.close();
                        break label50;
                     }
                     break;
                  case 3643:
                     if ("rm".equals(methodName)) {
                        try {
                           char t = SerDe$.MODULE$.readObjectType(dis);
                           .MODULE$.assert(t == 'c');
                           String objToRemove = SerDe$.MODULE$.readString(dis);
                           this.server.jvmObjectTracker().remove(new JVMObjectId(objToRemove));
                           SerDe$.MODULE$.writeInt(dos, 0);
                           SerDe$.MODULE$.writeObject(dos, (Object)null, this.server.jvmObjectTracker());
                        } catch (Exception var24) {
                           this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing ", " failed"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OBJECT_ID..MODULE$, objId)})))), var24);
                           SerDe$.MODULE$.writeInt(dos, -1);
                           SerDe$.MODULE$.writeString(dos, "Removing " + objId + " failed: " + var24.getMessage());
                        }
                        break label50;
                     }
                     break;
                  case 3107365:
                     if ("echo".equals(methodName)) {
                        Object[] args = this.readArgs(numArgs, dis);
                        .MODULE$.assert(numArgs == 1);
                        SerDe$.MODULE$.writeInt(dos, 0);
                        SerDe$.MODULE$.writeObject(dos, args[0], this.server.jvmObjectTracker());
                        break label50;
                     }
               }

               dos.writeInt(-1);
               SerDe$.MODULE$.writeString(dos, "Error: unknown method " + methodName);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
            break label59;
         }

         ScheduledExecutorService execService = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("SparkRKeepAliveThread");
         Runnable pingRunner = new Runnable(ctx) {
            private final ChannelHandlerContext ctx$1;

            public void run() {
               ByteArrayOutputStream pingBaos = new ByteArrayOutputStream();
               DataOutputStream pingDaos = new DataOutputStream(pingBaos);
               SerDe$.MODULE$.writeInt(pingDaos, 1);
               this.ctx$1.write(pingBaos.toByteArray());
            }

            public {
               this.ctx$1 = ctx$1;
            }
         };
         SparkConf conf = (SparkConf)scala.Option..MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$1) -> x$1.conf()).getOrElse(() -> new SparkConf());
         int heartBeatInterval = BoxesRunTime.unboxToInt(conf.get(R$.MODULE$.R_HEARTBEAT_INTERVAL()));
         int backendConnectionTimeout = BoxesRunTime.unboxToInt(conf.get(R$.MODULE$.R_BACKEND_CONNECTION_TIMEOUT()));
         int interval = Math.min(heartBeatInterval, backendConnectionTimeout - 1);
         execService.scheduleAtFixedRate(pingRunner, (long)interval, (long)interval, TimeUnit.SECONDS);
         this.handleMethodCall(isStatic, objId, methodName, numArgs, dis, dos);
         execService.shutdown();
         BoxesRunTime.boxToBoolean(execService.awaitTermination(1L, TimeUnit.SECONDS));
      }

      byte[] reply = bos.toByteArray();
      ctx.write(reply);
   }

   public void channelReadComplete(final ChannelHandlerContext ctx) {
      ctx.flush();
   }

   public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
      if (cause instanceof ReadTimeoutException) {
         this.logWarning((Function0)(() -> "Ignoring read timeout in RBackendHandler"));
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else {
         cause.printStackTrace();
         ctx.close();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void handleMethodCall(final boolean isStatic, final String objId, final String methodName, final int numArgs, final DataInputStream dis, final DataOutputStream dos) {
      Object obj = null;

      try {
         Class var10000;
         if (isStatic) {
            var10000 = Utils$.MODULE$.classForName(objId, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
         } else {
            obj = this.server.jvmObjectTracker().apply(new JVMObjectId(objId));
            var10000 = obj.getClass();
         }

         Class cls = var10000;
         Object[] args = this.readArgs(numArgs, dis);
         Method[] methods = cls.getMethods();
         Method[] selectedMethods = (Method[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])methods), (m) -> BoxesRunTime.boxToBoolean($anonfun$handleMethodCall$1(methodName, m)));
         if (selectedMethods.length > 0) {
            Option index = this.findMatchedSignature((Class[][])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])selectedMethods), (x$2) -> x$2.getParameterTypes(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Class.class))), args);
            if (index.isEmpty()) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"cannot find matching method "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ".", ". Candidates are:"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, cls), new MDC(org.apache.spark.internal.LogKeys.METHOD_NAME..MODULE$, methodName)}))))));
               scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])selectedMethods), (method) -> {
                  $anonfun$handleMethodCall$4(this, methodName, method);
                  return BoxedUnit.UNIT;
               });
               throw new Exception("No matched method found for " + cls + "." + methodName);
            }

            Object ret = selectedMethods[BoxesRunTime.unboxToInt(index.get())].invoke(obj, args);
            SerDe$.MODULE$.writeInt(dos, 0);
            SerDe$.MODULE$.writeObject(dos, ret, this.server.jvmObjectTracker());
         } else {
            String var14 = "<init>";
            if (methodName == null) {
               if (var14 != null) {
                  throw new IllegalArgumentException("invalid method " + methodName + " for object " + objId);
               }
            } else if (!methodName.equals(var14)) {
               throw new IllegalArgumentException("invalid method " + methodName + " for object " + objId);
            }

            Constructor[] ctors = cls.getConstructors();
            Option index = this.findMatchedSignature((Class[][])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])ctors), (x$3) -> x$3.getParameterTypes(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Class.class))), args);
            if (index.isEmpty()) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"cannot find matching constructor for ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, cls)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Candidates are:"})))).log(scala.collection.immutable.Nil..MODULE$))));
               scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])ctors), (ctor) -> {
                  $anonfun$handleMethodCall$8(this, cls, ctor);
                  return BoxedUnit.UNIT;
               });
               throw new Exception("No matched constructor found for " + cls);
            }

            Object obj = ctors[BoxesRunTime.unboxToInt(index.get())].newInstance(args);
            SerDe$.MODULE$.writeInt(dos, 0);
            SerDe$.MODULE$.writeObject(dos, obj, this.server.jvmObjectTracker());
            return;
         }
      } catch (Exception var19) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " on ", " failed"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.METHOD_NAME..MODULE$, methodName), new MDC(org.apache.spark.internal.LogKeys.OBJECT_ID..MODULE$, objId)})))), var19);
         SerDe$.MODULE$.writeInt(dos, -1);
         SerDe$.MODULE$.writeString(dos, Utils$.MODULE$.exceptionString(var19.getCause()));
      }

   }

   public Object[] readArgs(final int numArgs, final DataInputStream dis) {
      return scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), numArgs).map((x$4) -> $anonfun$readArgs$1(this, dis, BoxesRunTime.unboxToInt(x$4))).toArray(scala.reflect.ClassTag..MODULE$.Object());
   }

   public Option findMatchedSignature(final Class[][] parameterTypesOfMethods, final Object[] args) {
      Object var3 = new Object();

      Object var10000;
      try {
         int numArgs = args.length;
         scala.collection.ArrayOps..MODULE$.indices$extension(.MODULE$.refArrayOps((Object[])parameterTypesOfMethods)).foreach$mVc$sp((JFunction1.mcVI.sp)(index) -> {
            Class[] parameterTypes = parameterTypesOfMethods[index];
            if (parameterTypes.length == numArgs) {
               boolean argMatched = true;

               for(int i = 0; i < numArgs && argMatched; ++i) {
                  Class parameterType;
                  label102: {
                     parameterType = parameterTypes[i];
                     Class var10 = Seq.class;
                     if (parameterType == null) {
                        if (var10 != null) {
                           break label102;
                        }
                     } else if (!parameterType.equals(var10)) {
                        break label102;
                     }

                     if (args[i].getClass().isArray()) {
                        continue;
                     }
                  }

                  Class parameterWrapperType = parameterType;
                  if (parameterType.isPrimitive()) {
                     Class var21;
                     label92: {
                        label113: {
                           var21 = Integer.TYPE;
                           if (var21 == null) {
                              if (parameterType == null) {
                                 break label113;
                              }
                           } else if (var21.equals(parameterType)) {
                              break label113;
                           }

                           label114: {
                              var21 = Long.TYPE;
                              if (var21 == null) {
                                 if (parameterType == null) {
                                    break label114;
                                 }
                              } else if (var21.equals(parameterType)) {
                                 break label114;
                              }

                              label115: {
                                 var21 = Double.TYPE;
                                 if (var21 == null) {
                                    if (parameterType == null) {
                                       break label115;
                                    }
                                 } else if (var21.equals(parameterType)) {
                                    break label115;
                                 }

                                 label70: {
                                    var21 = Boolean.TYPE;
                                    if (var21 == null) {
                                       if (parameterType == null) {
                                          break label70;
                                       }
                                    } else if (var21.equals(parameterType)) {
                                       break label70;
                                    }

                                    var21 = parameterType;
                                    break label92;
                                 }

                                 var21 = Boolean.class;
                                 break label92;
                              }

                              var21 = Double.class;
                              break label92;
                           }

                           var21 = Integer.class;
                           break label92;
                        }

                        var21 = Integer.class;
                     }

                     parameterWrapperType = var21;
                  }

                  if ((parameterType.isPrimitive() || args[i] != null) && !parameterWrapperType.isInstance(args[i])) {
                     argMatched = false;
                  }
               }

               if (argMatched) {
                  Class[] parameterTypes = parameterTypesOfMethods[index];
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), numArgs).foreach$mVc$sp((JFunction1.mcVI.sp)(ix) -> {
                     Class var10000 = parameterTypes[ix];
                     Class var3 = Seq.class;
                     if (var10000 == null) {
                        if (var3 != null) {
                           return;
                        }
                     } else if (!var10000.equals(var3)) {
                        return;
                     }

                     if (args[ix].getClass().isArray()) {
                        args[ix] = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(args[ix]).toImmutableArraySeq();
                     }
                  });
                  throw new NonLocalReturnControl(var3, new Some(BoxesRunTime.boxToInteger(index)));
               }
            }
         });
         var10000 = scala.None..MODULE$;
      } catch (NonLocalReturnControl var6) {
         if (var6.key() != var3) {
            throw var6;
         }

         var10000 = (Option)var6.value();
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleMethodCall$1(final String methodName$1, final Method m) {
      boolean var3;
      label23: {
         String var10000 = m.getName();
         if (var10000 == null) {
            if (methodName$1 == null) {
               break label23;
            }
         } else if (var10000.equals(methodName$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$handleMethodCall$4(final RBackendHandler $this, final String methodName$1, final Method method) {
      $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "("})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.METHOD_NAME..MODULE$, methodName$1)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.METHOD_PARAM_TYPES..MODULE$, .MODULE$.wrapRefArray((Object[])method.getParameterTypes()).mkString(","))}))))));
   }

   // $FF: synthetic method
   public static final void $anonfun$handleMethodCall$8(final RBackendHandler $this, final Class cls$1, final Constructor ctor) {
      $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "("})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, cls$1)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.METHOD_PARAM_TYPES..MODULE$, .MODULE$.wrapRefArray((Object[])ctor.getParameterTypes()).mkString(","))}))))));
   }

   // $FF: synthetic method
   public static final Object $anonfun$readArgs$1(final RBackendHandler $this, final DataInputStream dis$1, final int x$4) {
      return SerDe$.MODULE$.readObject(dis$1, $this.server.jvmObjectTracker());
   }

   public RBackendHandler(final RBackend server) {
      this.server = server;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
