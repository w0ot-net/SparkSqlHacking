package org.apache.spark.deploy.master;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.deploy.DriverDescription;
import org.apache.spark.util.Utils$;
import scala.Enumeration;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d!\u0002\u000e\u001c\u0001u)\u0003\u0002C\u001d\u0001\u0005\u000b\u0007I\u0011\u0001\u001e\t\u0011y\u0002!\u0011!Q\u0001\nmB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005\u0003\"A!\n\u0001BC\u0002\u0013\u00051\n\u0003\u0005Q\u0001\t\u0005\t\u0015!\u0003M\u0011!\t\u0006A!b\u0001\n\u0003\u0011\u0006\u0002C.\u0001\u0005\u0003\u0005\u000b\u0011B*\t\u000bq\u0003A\u0011A/\t\u000f\u0011\u0004\u0001\u0019!C\u0001K\"9a\u000e\u0001a\u0001\n\u0003y\u0007BB;\u0001A\u0003&a\rC\u0004{\u0001\u0001\u0007I\u0011A>\t\u0013\u0005\u0015\u0001\u00011A\u0005\u0002\u0005\u001d\u0001bBA\u0006\u0001\u0001\u0006K\u0001 \u0005\n\u0003\u001f\u0001\u0001\u0019!C\u0001\u0003#A\u0011\"a\u0007\u0001\u0001\u0004%\t!!\b\t\u0011\u0005\u0005\u0002\u0001)Q\u0005\u0003'A\u0011\"!\n\u0001\u0001\u0004%I!a\n\t\u0013\u0005m\u0002\u00011A\u0005\n\u0005u\u0002\u0002CA!\u0001\u0001\u0006K!!\u000b\t\u000f\u0005\r\u0003\u0001\"\u0003\u0002F!9\u0011q\u000b\u0001\u0005\n\u0005e\u0003bBA.\u0001\u0011\u0005\u0011Q\f\u0005\b\u0003G\u0002A\u0011AA\u0014\u0005)!%/\u001b<fe&sgm\u001c\u0006\u00039u\ta!\\1ti\u0016\u0014(B\u0001\u0010 \u0003\u0019!W\r\u001d7ps*\u0011\u0001%I\u0001\u0006gB\f'o\u001b\u0006\u0003E\r\na!\u00199bG\",'\"\u0001\u0013\u0002\u0007=\u0014xmE\u0002\u0001M1\u0002\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u0012a!\u00118z%\u00164\u0007CA\u00177\u001d\tqCG\u0004\u00020g5\t\u0001G\u0003\u00022e\u00051AH]8piz\u001a\u0001!C\u0001*\u0013\t)\u0004&A\u0004qC\u000e\\\u0017mZ3\n\u0005]B$\u0001D*fe&\fG.\u001b>bE2,'BA\u001b)\u0003%\u0019H/\u0019:u)&lW-F\u0001<!\t9C(\u0003\u0002>Q\t!Aj\u001c8h\u0003)\u0019H/\u0019:u)&lW\rI\u0001\u0003S\u0012,\u0012!\u0011\t\u0003\u0005\u001as!a\u0011#\u0011\u0005=B\u0013BA#)\u0003\u0019\u0001&/\u001a3fM&\u0011q\t\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015C\u0013aA5eA\u0005!A-Z:d+\u0005a\u0005CA'O\u001b\u0005i\u0012BA(\u001e\u0005E!%/\u001b<fe\u0012+7o\u0019:jaRLwN\\\u0001\u0006I\u0016\u001c8\rI\u0001\u000bgV\u0014W.\u001b;ECR,W#A*\u0011\u0005QKV\"A+\u000b\u0005Y;\u0016\u0001B;uS2T\u0011\u0001W\u0001\u0005U\u00064\u0018-\u0003\u0002[+\n!A)\u0019;f\u0003-\u0019XOY7ji\u0012\u000bG/\u001a\u0011\u0002\rqJg.\u001b;?)\u0015q\u0006-\u00192d!\ty\u0006!D\u0001\u001c\u0011\u0015I\u0014\u00021\u0001<\u0011\u0015y\u0014\u00021\u0001B\u0011\u0015Q\u0015\u00021\u0001M\u0011\u0015\t\u0016\u00021\u0001T\u0003\u0015\u0019H/\u0019;f+\u00051\u0007CA4k\u001d\ty\u0006.\u0003\u0002j7\u0005YAI]5wKJ\u001cF/\u0019;f\u0013\tYGNA\u0003WC2,X-\u0003\u0002nQ\tYQI\\;nKJ\fG/[8o\u0003%\u0019H/\u0019;f?\u0012*\u0017\u000f\u0006\u0002qgB\u0011q%]\u0005\u0003e\"\u0012A!\u00168ji\"9AoCA\u0001\u0002\u00041\u0017a\u0001=%c\u000511\u000f^1uK\u0002B#\u0001D<\u0011\u0005\u001dB\u0018BA=)\u0005%!(/\u00198tS\u0016tG/A\u0005fq\u000e,\u0007\u000f^5p]V\tA\u0010E\u0002({~L!A \u0015\u0003\r=\u0003H/[8o!\ri\u0013\u0011A\u0005\u0004\u0003\u0007A$!C#yG\u0016\u0004H/[8o\u00035)\u0007pY3qi&|gn\u0018\u0013fcR\u0019\u0001/!\u0003\t\u000fQt\u0011\u0011!a\u0001y\u0006QQ\r_2faRLwN\u001c\u0011)\u0005=9\u0018AB<pe.,'/\u0006\u0002\u0002\u0014A!q%`A\u000b!\ry\u0016qC\u0005\u0004\u00033Y\"AC,pe.,'/\u00138g_\u0006Qqo\u001c:lKJ|F%Z9\u0015\u0007A\fy\u0002\u0003\u0005u#\u0005\u0005\t\u0019AA\n\u0003\u001d9xN]6fe\u0002B#AE<\u0002\u0015}\u0013Xm]8ve\u000e,7/\u0006\u0002\u0002*A1!)a\u000bB\u0003_I1!!\fI\u0005\ri\u0015\r\u001d\t\u0005\u0003c\t9$\u0004\u0002\u00024)\u0019\u0011QG\u0010\u0002\u0011I,7o\\;sG\u0016LA!!\u000f\u00024\t\u0019\"+Z:pkJ\u001cW-\u00138g_Jl\u0017\r^5p]\u0006qqL]3t_V\u00148-Z:`I\u0015\fHc\u00019\u0002@!AA\u000fFA\u0001\u0002\u0004\tI#A\u0006`e\u0016\u001cx.\u001e:dKN\u0004\u0013A\u0003:fC\u0012|%M[3diR\u0019\u0001/a\u0012\t\u000f\u0005%c\u00031\u0001\u0002L\u0005\u0011\u0011N\u001c\t\u0005\u0003\u001b\n\u0019&\u0004\u0002\u0002P)\u0019\u0011\u0011K,\u0002\u0005%|\u0017\u0002BA+\u0003\u001f\u0012\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1n\u0003\u0011Ig.\u001b;\u0015\u0003A\fQb^5uQJ+7o\\;sG\u0016\u001cHc\u00019\u0002`!9\u0011\u0011\r\rA\u0002\u0005%\u0012!\u0001:\u0002\u0013I,7o\\;sG\u0016\u001c\b"
)
public class DriverInfo implements Serializable {
   private final long startTime;
   private final String id;
   private final DriverDescription desc;
   private final Date submitDate;
   private transient Enumeration.Value state;
   private transient Option exception;
   private transient Option worker;
   private Map _resources;

   public long startTime() {
      return this.startTime;
   }

   public String id() {
      return this.id;
   }

   public DriverDescription desc() {
      return this.desc;
   }

   public Date submitDate() {
      return this.submitDate;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public Option exception() {
      return this.exception;
   }

   public void exception_$eq(final Option x$1) {
      this.exception = x$1;
   }

   public Option worker() {
      return this.worker;
   }

   public void worker_$eq(final Option x$1) {
      this.worker = x$1;
   }

   private Map _resources() {
      return this._resources;
   }

   private void _resources_$eq(final Map x$1) {
      this._resources = x$1;
   }

   private void readObject(final ObjectInputStream in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         in.defaultReadObject();
         this.init();
      });
   }

   private void init() {
      this.state_$eq(DriverState$.MODULE$.SUBMITTED());
      this.worker_$eq(.MODULE$);
      this.exception_$eq(.MODULE$);
   }

   public void withResources(final Map r) {
      this._resources_$eq(r);
   }

   public Map resources() {
      return this._resources();
   }

   public DriverInfo(final long startTime, final String id, final DriverDescription desc, final Date submitDate) {
      this.startTime = startTime;
      this.id = id;
      this.desc = desc;
      this.submitDate = submitDate;
      this.state = DriverState$.MODULE$.SUBMITTED();
      this.exception = .MODULE$;
      this.worker = .MODULE$;
      this._resources = scala.Predef..MODULE$.Map().empty();
      this.init();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
