package org.apache.spark.deploy.yarn;

import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055b\u0001\u0002\u000f\u001e\u0001!B\u0001b\f\u0001\u0003\u0006\u0004%\t\u0001\r\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005c!)\u0001\t\u0001C\u0001\u0003\"9Q\t\u0001a\u0001\n\u00031\u0005bB$\u0001\u0001\u0004%\t\u0001\u0013\u0005\u0007\u001d\u0002\u0001\u000b\u0015\u0002\u001b\t\u000f=\u0003\u0001\u0019!C\u0001\r\"9\u0001\u000b\u0001a\u0001\n\u0003\t\u0006BB*\u0001A\u0003&A\u0007C\u0004U\u0001\u0001\u0007I\u0011\u0001$\t\u000fU\u0003\u0001\u0019!C\u0001-\"1\u0001\f\u0001Q!\nQBq!\u0017\u0001A\u0002\u0013\u0005a\tC\u0004[\u0001\u0001\u0007I\u0011A.\t\ru\u0003\u0001\u0015)\u00035\u0011\u001dq\u0006\u00011A\u0005\u0002}Cq!\u001b\u0001A\u0002\u0013\u0005!\u000e\u0003\u0004m\u0001\u0001\u0006K\u0001\u0019\u0005\b[\u0002\u0001\r\u0011\"\u0001G\u0011\u001dq\u0007\u00011A\u0005\u0002=Da!\u001d\u0001!B\u0013!\u0004b\u0002:\u0001\u0001\u0004%\tA\u0012\u0005\bg\u0002\u0001\r\u0011\"\u0001u\u0011\u00191\b\u0001)Q\u0005i!)q\u000f\u0001C\u0005q\")a\u0010\u0001C\u0001\u007f\"I\u0011Q\u0003\u0001\u0012\u0002\u0013\u0005\u0011q\u0003\u0002\u001b\u0003B\u0004H.[2bi&|g.T1ti\u0016\u0014\u0018I]4v[\u0016tGo\u001d\u0006\u0003=}\tA!_1s]*\u0011\u0001%I\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001SA\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t1\u0011I\\=SK\u001a\fA!\u0019:hgV\t\u0011\u0007E\u0002+eQJ!aM\u0016\u0003\u000b\u0005\u0013(/Y=\u0011\u0005UbdB\u0001\u001c;!\t94&D\u00019\u0015\tIt%\u0001\u0004=e>|GOP\u0005\u0003w-\na\u0001\u0015:fI\u00164\u0017BA\u001f?\u0005\u0019\u0019FO]5oO*\u00111hK\u0001\u0006CJ<7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t#\u0005CA\"\u0001\u001b\u0005i\u0002\"B\u0018\u0004\u0001\u0004\t\u0014aB;tKJT\u0015M]\u000b\u0002i\u0005YQo]3s\u0015\u0006\u0014x\fJ3r)\tIE\n\u0005\u0002+\u0015&\u00111j\u000b\u0002\u0005+:LG\u000fC\u0004N\u000b\u0005\u0005\t\u0019\u0001\u001b\u0002\u0007a$\u0013'\u0001\u0005vg\u0016\u0014(*\u0019:!\u0003%)8/\u001a:DY\u0006\u001c8/A\u0007vg\u0016\u00148\t\\1tg~#S-\u001d\u000b\u0003\u0013JCq!\u0014\u0005\u0002\u0002\u0003\u0007A'\u0001\u0006vg\u0016\u00148\t\\1tg\u0002\nQ\u0002\u001d:j[\u0006\u0014\u0018\u0010U=GS2,\u0017!\u00059sS6\f'/\u001f)z\r&dWm\u0018\u0013fcR\u0011\u0011j\u0016\u0005\b\u001b.\t\t\u00111\u00015\u00039\u0001(/[7bef\u0004\u0016PR5mK\u0002\nA\u0002\u001d:j[\u0006\u0014\u0018P\u0015$jY\u0016\f\u0001\u0003\u001d:j[\u0006\u0014\u0018P\u0015$jY\u0016|F%Z9\u0015\u0005%c\u0006bB'\u000f\u0003\u0003\u0005\r\u0001N\u0001\u000eaJLW.\u0019:z%\u001aKG.\u001a\u0011\u0002\u0011U\u001cXM]!sON,\u0012\u0001\u0019\t\u0004C\u001a$dB\u00012e\u001d\t94-C\u0001-\u0013\t)7&A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dD'aA*fc*\u0011QmK\u0001\rkN,'/\u0011:hg~#S-\u001d\u000b\u0003\u0013.Dq!T\t\u0002\u0002\u0003\u0007\u0001-A\u0005vg\u0016\u0014\u0018I]4tA\u0005q\u0001O]8qKJ$\u0018.Z:GS2,\u0017A\u00059s_B,'\u000f^5fg\u001aKG.Z0%KF$\"!\u00139\t\u000f5#\u0012\u0011!a\u0001i\u0005y\u0001O]8qKJ$\u0018.Z:GS2,\u0007%A\u0007eSN$8)Y2iK\u000e{gNZ\u0001\u0012I&\u001cHoQ1dQ\u0016\u001cuN\u001c4`I\u0015\fHCA%v\u0011\u001diu#!AA\u0002Q\na\u0002Z5ti\u000e\u000b7\r[3D_:4\u0007%A\u0005qCJ\u001cX-\u0011:hgR\u0011\u0011*\u001f\u0005\u0006uf\u0001\ra_\u0001\nS:\u0004X\u000f^!sON\u00042!\u0019?5\u0013\ti\bN\u0001\u0003MSN$\u0018!\u00059sS:$Xk]1hK\u0006sG-\u0012=jiR)\u0011*!\u0001\u0002\f!9\u00111\u0001\u000eA\u0002\u0005\u0015\u0011\u0001C3ySR\u001cu\u000eZ3\u0011\u0007)\n9!C\u0002\u0002\n-\u00121!\u00138u\u0011%\tiA\u0007I\u0001\u0002\u0004\ty!\u0001\u0007v].twn\u001e8QCJ\fW\u000eE\u0002+\u0003#I1!a\u0005,\u0005\r\te._\u0001\u001caJLg\u000e^+tC\u001e,\u0017I\u001c3Fq&$H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005e!\u0006BA\b\u00037Y#!!\b\u0011\t\u0005}\u0011\u0011F\u0007\u0003\u0003CQA!a\t\u0002&\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003OY\u0013AC1o]>$\u0018\r^5p]&!\u00111FA\u0011\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class ApplicationMasterArguments {
   private final String[] args;
   private String userJar;
   private String userClass;
   private String primaryPyFile;
   private String primaryRFile;
   private Seq userArgs;
   private String propertiesFile;
   private String distCacheConf;

   public String[] args() {
      return this.args;
   }

   public String userJar() {
      return this.userJar;
   }

   public void userJar_$eq(final String x$1) {
      this.userJar = x$1;
   }

   public String userClass() {
      return this.userClass;
   }

   public void userClass_$eq(final String x$1) {
      this.userClass = x$1;
   }

   public String primaryPyFile() {
      return this.primaryPyFile;
   }

   public void primaryPyFile_$eq(final String x$1) {
      this.primaryPyFile = x$1;
   }

   public String primaryRFile() {
      return this.primaryRFile;
   }

   public void primaryRFile_$eq(final String x$1) {
      this.primaryRFile = x$1;
   }

   public Seq userArgs() {
      return this.userArgs;
   }

   public void userArgs_$eq(final Seq x$1) {
      this.userArgs = x$1;
   }

   public String propertiesFile() {
      return this.propertiesFile;
   }

   public void propertiesFile_$eq(final String x$1) {
      this.propertiesFile = x$1;
   }

   public String distCacheConf() {
      return this.distCacheConf;
   }

   public void distCacheConf_$eq(final String x$1) {
      this.distCacheConf = x$1;
   }

   private void parseArgs(final List inputArgs) {
      ArrayBuffer userArgsBuffer = new ArrayBuffer();
      List args = inputArgs;

      while(!args.isEmpty()) {
         boolean var5 = false;
         .colon.colon var6 = null;
         if (args instanceof .colon.colon) {
            var5 = true;
            var6 = (.colon.colon)args;
            String var8 = (String)var6.head();
            List var9 = var6.next$access$1();
            if ("--jar".equals(var8) && var9 instanceof .colon.colon) {
               .colon.colon var10 = (.colon.colon)var9;
               String value = (String)var10.head();
               List tail = var10.next$access$1();
               this.userJar_$eq(value);
               args = tail;
               BoxedUnit var49 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var13 = (String)var6.head();
            List var14 = var6.next$access$1();
            if ("--class".equals(var13) && var14 instanceof .colon.colon) {
               .colon.colon var15 = (.colon.colon)var14;
               String value = (String)var15.head();
               List tail = var15.next$access$1();
               this.userClass_$eq(value);
               args = tail;
               BoxedUnit var48 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var18 = (String)var6.head();
            List var19 = var6.next$access$1();
            if ("--primary-py-file".equals(var18) && var19 instanceof .colon.colon) {
               .colon.colon var20 = (.colon.colon)var19;
               String value = (String)var20.head();
               List tail = var20.next$access$1();
               this.primaryPyFile_$eq(value);
               args = tail;
               BoxedUnit var47 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var23 = (String)var6.head();
            List var24 = var6.next$access$1();
            if ("--primary-r-file".equals(var23) && var24 instanceof .colon.colon) {
               .colon.colon var25 = (.colon.colon)var24;
               String value = (String)var25.head();
               List tail = var25.next$access$1();
               this.primaryRFile_$eq(value);
               args = tail;
               BoxedUnit var46 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var28 = (String)var6.head();
            List var29 = var6.next$access$1();
            if ("--arg".equals(var28) && var29 instanceof .colon.colon) {
               .colon.colon var30 = (.colon.colon)var29;
               String value = (String)var30.head();
               List tail = var30.next$access$1();
               userArgsBuffer.$plus$eq(value);
               args = tail;
               BoxedUnit var45 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var33 = (String)var6.head();
            List var34 = var6.next$access$1();
            if ("--properties-file".equals(var33) && var34 instanceof .colon.colon) {
               .colon.colon var35 = (.colon.colon)var34;
               String value = (String)var35.head();
               List tail = var35.next$access$1();
               this.propertiesFile_$eq(value);
               args = tail;
               BoxedUnit var44 = BoxedUnit.UNIT;
               continue;
            }
         }

         if (var5) {
            String var38 = (String)var6.head();
            List var39 = var6.next$access$1();
            if ("--dist-cache-conf".equals(var38) && var39 instanceof .colon.colon) {
               .colon.colon var40 = (.colon.colon)var39;
               String value = (String)var40.head();
               List tail = var40.next$access$1();
               this.distCacheConf_$eq(value);
               args = tail;
               BoxedUnit var43 = BoxedUnit.UNIT;
               continue;
            }
         }

         this.printUsageAndExit(1, args);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (this.primaryPyFile() != null && this.primaryRFile() != null) {
         System.err.println("Cannot have primary-py-file and primary-r-file at the same time");
         System.exit(-1);
      }

      this.userArgs_$eq(userArgsBuffer.toList());
   }

   public void printUsageAndExit(final int exitCode, final Object unknownParam) {
      if (unknownParam != null) {
         System.err.println("Unknown/unsupported param " + unknownParam);
      }

      System.err.println(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n      |Usage: org.apache.spark.deploy.yarn.ApplicationMaster [options]\n      |Options:\n      |  --jar JAR_PATH       Path to your application's JAR file\n      |  --class CLASS_NAME   Name of your application's main class\n      |  --primary-py-file    A main Python file\n      |  --primary-r-file     A main R file\n      |  --arg ARG            Argument to be passed to your application's main class.\n      |                       Multiple invocations are possible, each will be passed in order.\n      |  --properties-file FILE Path to a custom Spark properties file.\n      ")));
      System.exit(exitCode);
   }

   public Object printUsageAndExit$default$2() {
      return null;
   }

   public ApplicationMasterArguments(final String[] args) {
      this.args = args;
      this.userJar = null;
      this.userClass = null;
      this.primaryPyFile = null;
      this.primaryRFile = null;
      this.userArgs = scala.collection.immutable.Nil..MODULE$;
      this.propertiesFile = null;
      this.distCacheConf = null;
      this.parseArgs(scala.Predef..MODULE$.wrapRefArray((Object[])args).toList());
   }
}
