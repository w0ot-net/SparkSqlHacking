package org.apache.spark.storage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.util.SparkErrorUtils$;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\ted\u0001B&M\u0001UC\u0001\u0002\u001a\u0001\u0003\u0002\u0004%I!\u001a\u0005\tY\u0002\u0011\t\u0019!C\u0005[\"A1\u000f\u0001B\u0001B\u0003&a\r\u0003\u0005u\u0001\t\u0005\r\u0011\"\u0003f\u0011!)\bA!a\u0001\n\u00131\b\u0002\u0003=\u0001\u0005\u0003\u0005\u000b\u0015\u00024\t\u0011e\u0004!\u00111A\u0005\n\u0015D\u0001B\u001f\u0001\u0003\u0002\u0004%Ia\u001f\u0005\t{\u0002\u0011\t\u0011)Q\u0005M\"Aa\u0010\u0001BA\u0002\u0013%Q\rC\u0005\u0000\u0001\t\u0005\r\u0011\"\u0003\u0002\u0002!I\u0011Q\u0001\u0001\u0003\u0002\u0003\u0006KA\u001a\u0005\u000b\u0003\u000f\u0001!\u00111A\u0005\n\u0005%\u0001BCA\t\u0001\t\u0005\r\u0011\"\u0003\u0002\u0014!Q\u0011q\u0003\u0001\u0003\u0002\u0003\u0006K!a\u0003\t\u000f\u0005e\u0001\u0001\"\u0003\u0002\u001c!9\u0011\u0011\u0004\u0001\u0005\n\u0005-\u0002bBA\r\u0001\u0011\u0005\u0011Q\u0007\u0005\u0007\u0003o\u0001A\u0011A3\t\r\u0005e\u0002\u0001\"\u0001f\u0011\u0019\tY\u0004\u0001C\u0001K\"1\u0011Q\b\u0001\u0005\u0002\u0015Dq!a\r\u0001\t\u0003\tI\u0001\u0003\u0005\u0002@\u0001!\tATA!\u0011\u001d\ty\u0005\u0001C!\u0003kAq!!\u0015\u0001\t\u0003\n\u0019\u0006\u0003\u0004\u0002`\u0001!\t!\u001a\u0005\b\u0003C\u0002A\u0011AA\u0005\u0011\u001d\t\u0019\u0007\u0001C!\u0003KBq!!\u001d\u0001\t\u0003\n\u0019\bC\u0004\u0002\u0000\u0001!I!!!\t\u000f\u0005M\u0005\u0001\"\u0011\u0002\u0016\"9\u0011Q\u0016\u0001\u0005B\u0005=\u0006bBAY\u0001\u0011\u0005\u00111W\u0004\b\u0003\u0007d\u0005\u0012AAc\r\u0019YE\n#\u0001\u0002H\"9\u0011\u0011\u0004\u0013\u0005\u0002\u0005U\u0007\"CAlI\t\u0007I\u0011AAm\u0011!\tY\u000e\nQ\u0001\n\u0005u\u0001\"CAoI\t\u0007I\u0011AAm\u0011!\ty\u000e\nQ\u0001\n\u0005u\u0001\"CAqI\t\u0007I\u0011AAm\u0011!\t\u0019\u000f\nQ\u0001\n\u0005u\u0001\"CAsI\t\u0007I\u0011AAm\u0011!\t9\u000f\nQ\u0001\n\u0005u\u0001\"CAuI\t\u0007I\u0011AAm\u0011!\tY\u000f\nQ\u0001\n\u0005u\u0001\"CAwI\t\u0007I\u0011AAm\u0011!\ty\u000f\nQ\u0001\n\u0005u\u0001\"CAyI\t\u0007I\u0011AAm\u0011!\t\u0019\u0010\nQ\u0001\n\u0005u\u0001\"CA{I\t\u0007I\u0011AAm\u0011!\t9\u0010\nQ\u0001\n\u0005u\u0001\"CA}I\t\u0007I\u0011AAm\u0011!\tY\u0010\nQ\u0001\n\u0005u\u0001\"CA\u007fI\t\u0007I\u0011AAm\u0011!\ty\u0010\nQ\u0001\n\u0005u\u0001\"\u0003B\u0001I\t\u0007I\u0011AAm\u0011!\u0011\u0019\u0001\nQ\u0001\n\u0005u\u0001\"\u0003B\u0003I\t\u0007I\u0011AAm\u0011!\u00119\u0001\nQ\u0001\n\u0005u\u0001\"\u0003B\u0005I\t\u0007I\u0011AAm\u0011!\u0011Y\u0001\nQ\u0001\n\u0005u\u0001b\u0002B\u0007I\u0011\u0005!q\u0002\u0005\b\u0005/!C\u0011\u0001B\r\u0011\u001d\u00119\u0002\nC\u0001\u0005OA\u0011Ba\r%#\u0003%\tA!\u000e\t\u000f\t]A\u0005\"\u0001\u0003J!9!q\u0003\u0013\u0005\u0002\tE\u0003B\u0003B,I\t\u0007I\u0011\u0001(\u0003Z!A!1\u000e\u0013!\u0002\u0013\u0011Y\u0006\u0003\u0005\u0003n\u0011\"\tA\u0014B8\u0011%\u0011)\bJI\u0001\n\u0013\u0011)\u0004C\u0005\u0003x\u0011\n\t\u0011\"\u0003\u0002\u0002\na1\u000b^8sC\u001e,G*\u001a<fY*\u0011QJT\u0001\bgR|'/Y4f\u0015\ty\u0005+A\u0003ta\u0006\u00148N\u0003\u0002R%\u00061\u0011\r]1dQ\u0016T\u0011aU\u0001\u0004_J<7\u0001A\n\u0004\u0001Ys\u0006CA,]\u001b\u0005A&BA-[\u0003\u0011a\u0017M\\4\u000b\u0003m\u000bAA[1wC&\u0011Q\f\u0017\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005}\u0013W\"\u00011\u000b\u0005\u0005T\u0016AA5p\u0013\t\u0019\u0007M\u0001\bFqR,'O\\1mSj\f'\r\\3\u0002\u0011}+8/\u001a#jg.,\u0012A\u001a\t\u0003O*l\u0011\u0001\u001b\u0006\u0002S\u0006)1oY1mC&\u00111\u000e\u001b\u0002\b\u0005>|G.Z1o\u00031yVo]3ESN\\w\fJ3r)\tq\u0017\u000f\u0005\u0002h_&\u0011\u0001\u000f\u001b\u0002\u0005+:LG\u000fC\u0004s\u0005\u0005\u0005\t\u0019\u00014\u0002\u0007a$\u0013'A\u0005`kN,G)[:lA\u0005Qq,^:f\u001b\u0016lwN]=\u0002\u001d}+8/Z'f[>\u0014\u0018p\u0018\u0013fcR\u0011an\u001e\u0005\be\u0016\t\t\u00111\u0001g\u0003-yVo]3NK6|'/\u001f\u0011\u0002\u0017}+8/Z(gM\"+\u0017\r]\u0001\u0010?V\u001cXm\u00144g\u0011\u0016\f\u0007o\u0018\u0013fcR\u0011a\u000e \u0005\be\"\t\t\u00111\u0001g\u00031yVo]3PM\u001aDU-\u00199!\u00035yF-Z:fe&\fG.\u001b>fI\u0006\tr\fZ3tKJL\u0017\r\\5{K\u0012|F%Z9\u0015\u00079\f\u0019\u0001C\u0004s\u0017\u0005\u0005\t\u0019\u00014\u0002\u001d}#Wm]3sS\u0006d\u0017N_3eA\u0005aqL]3qY&\u001c\u0017\r^5p]V\u0011\u00111\u0002\t\u0004O\u00065\u0011bAA\bQ\n\u0019\u0011J\u001c;\u0002!}\u0013X\r\u001d7jG\u0006$\u0018n\u001c8`I\u0015\fHc\u00018\u0002\u0016!A!ODA\u0001\u0002\u0004\tY!A\u0007`e\u0016\u0004H.[2bi&|g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0019\u0005u\u0011\u0011EA\u0012\u0003K\t9#!\u000b\u0011\u0007\u0005}\u0001!D\u0001M\u0011\u0015!\u0007\u00031\u0001g\u0011\u0015!\b\u00031\u0001g\u0011\u0015I\b\u00031\u0001g\u0011\u0015q\b\u00031\u0001g\u0011%\t9\u0001\u0005I\u0001\u0002\u0004\tY\u0001\u0006\u0004\u0002\u001e\u00055\u0012\u0011\u0007\u0005\b\u0003_\t\u0002\u0019AA\u0006\u0003\u00151G.Y4t\u0011\u001d\t\u0019$\u0005a\u0001\u0003\u0017\t1B]3qY&\u001c\u0017\r^5p]R\u0011\u0011QD\u0001\bkN,G)[:l\u0003%)8/Z'f[>\u0014\u00180\u0001\u0006vg\u0016|eM\u001a%fCB\fA\u0002Z3tKJL\u0017\r\\5{K\u0012\f!\"\\3n_JLXj\u001c3f+\t\t\u0019\u0005\u0005\u0003\u0002F\u0005-SBAA$\u0015\r\tIET\u0001\u0007[\u0016lwN]=\n\t\u00055\u0013q\t\u0002\u000b\u001b\u0016lwN]=N_\u0012,\u0017!B2m_:,\u0017AB3rk\u0006d7\u000fF\u0002g\u0003+Bq!a\u0016\u001b\u0001\u0004\tI&A\u0003pi\",'\u000fE\u0002h\u00037J1!!\u0018i\u0005\r\te._\u0001\bSN4\u0016\r\\5e\u0003\u0015!x.\u00138u\u000359(/\u001b;f\u000bb$XM\u001d8bYR\u0019a.a\u001a\t\u000f\u0005%T\u00041\u0001\u0002l\u0005\u0019q.\u001e;\u0011\u0007}\u000bi'C\u0002\u0002p\u0001\u0014Ab\u00142kK\u000e$x*\u001e;qkR\fAB]3bI\u0016CH/\u001a:oC2$2A\\A;\u0011\u001d\t9H\ba\u0001\u0003s\n!!\u001b8\u0011\u0007}\u000bY(C\u0002\u0002~\u0001\u00141b\u00142kK\u000e$\u0018J\u001c9vi\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\u00051\u0006&B\u0010\u0002\u0006\u0006E\u0005#B4\u0002\b\u0006-\u0015bAAEQ\n1A\u000f\u001b:poN\u00042aXAG\u0013\r\ty\t\u0019\u0002\f\u0013>+\u0005pY3qi&|gn\t\u0002\u0002\f\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\u0018B!\u0011\u0011TAT\u001d\u0011\tY*a)\u0011\u0007\u0005u\u0005.\u0004\u0002\u0002 *\u0019\u0011\u0011\u0015+\u0002\rq\u0012xn\u001c;?\u0013\r\t)\u000b[\u0001\u0007!J,G-\u001a4\n\t\u0005%\u00161\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\u0015\u0006.\u0001\u0005iCND7i\u001c3f)\t\tY!A\u0006eKN\u001c'/\u001b9uS>tWCAALQ\r\u0001\u0011q\u0017\t\u0005\u0003s\u000by,\u0004\u0002\u0002<*\u0019\u0011Q\u0018(\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002B\u0006m&\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017\u0001D*u_J\fw-\u001a'fm\u0016d\u0007cAA\u0010IM)A%!3\u0002PB\u0019q-a3\n\u0007\u00055\u0007N\u0001\u0004B]f\u0014VM\u001a\t\u0004?\u0006E\u0017bAAjA\na1+\u001a:jC2L'0\u00192mKR\u0011\u0011QY\u0001\u0005\u001d>sU)\u0006\u0002\u0002\u001e\u0005)aj\u0014(FA\u0005IA)S*L?>sE*W\u0001\u000b\t&\u001b6jX(O\u0019f\u0003\u0013a\u0003#J'.{vJ\u0014'Z?J\nA\u0002R%T\u0017~{e\nT-`e\u0001\n1\u0002R%T\u0017~{e\nT-`g\u0005aA)S*L?>sE*W04A\u0005YQ*R'P%f{vJ\u0014'Z\u00031iU)T(S3~{e\nT-!\u00035iU)T(S3~{e\nT-`e\u0005qQ*R'P%f{vJ\u0014'Z?J\u0002\u0013aD'F\u001b>\u0013\u0016lX(O\u0019f{6+\u0012*\u0002!5+Uj\u0014*Z?>sE*W0T\u000bJ\u0003\u0013!E'F\u001b>\u0013\u0016lX(O\u0019f{6+\u0012*`e\u0005\u0011R*R'P%f{vJ\u0014'Z?N+%k\u0018\u001a!\u0003=iU)T(S3~\u000be\nR0E\u0013N[\u0015\u0001E'F\u001b>\u0013\u0016lX!O\t~#\u0015jU&!\u0003EiU)T(S3~\u000be\nR0E\u0013N[uLM\u0001\u0013\u001b\u0016kuJU-`\u0003:#u\fR%T\u0017~\u0013\u0004%A\nN\u000b6{%+W0B\u001d\u0012{F)S*L?N+%+\u0001\u000bN\u000b6{%+W0B\u001d\u0012{F)S*L?N+%\u000bI\u0001\u0016\u001b\u0016kuJU-`\u0003:#u\fR%T\u0017~\u001bVIU03\u0003YiU)T(S3~\u000be\nR0E\u0013N[ulU#S?J\u0002\u0013\u0001C(G\r~CU)\u0011)\u0002\u0013=3ei\u0018%F\u0003B\u0003\u0013A\u00034s_6\u001cFO]5oOR!\u0011Q\u0004B\t\u0011\u001d\u0011\u0019\u0002\u0011a\u0001\u0003/\u000b\u0011a\u001d\u0015\u0004\u0001\u0006]\u0016!B1qa2LH\u0003DA\u000f\u00057\u0011iBa\b\u0003\"\t\r\u0002BBA\u001c\u0003\u0002\u0007a\r\u0003\u0004\u0002:\u0005\u0003\rA\u001a\u0005\u0007\u0003w\t\u0005\u0019\u00014\t\r\u0005u\u0012\t1\u0001g\u0011\u001d\t\u0019$\u0011a\u0001\u0003\u0017A3!QA\\))\tiB!\u000b\u0003,\t5\"q\u0006\u0005\u0007\u0003o\u0011\u0005\u0019\u00014\t\r\u0005e\"\t1\u0001g\u0011\u0019\tiD\u0011a\u0001M\"I\u00111\u0007\"\u0011\u0002\u0003\u0007\u00111\u0002\u0015\u0004\u0005\u0006]\u0016aD1qa2LH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\t]\"\u0006BA\u0006\u0005sY#Aa\u000f\u0011\t\tu\"QI\u0007\u0003\u0005\u007fQAA!\u0011\u0003D\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003{C\u0017\u0002\u0002B$\u0005\u007f\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f)\u0019\tiBa\u0013\u0003N!9\u0011q\u0006#A\u0002\u0005-\u0001bBA\u001a\t\u0002\u0007\u00111\u0002\u0015\u0004\t\u0006]F\u0003BA\u000f\u0005'Bq!a\u001eF\u0001\u0004\tI\bK\u0002F\u0003o\u000b\u0011c\u001d;pe\u0006<W\rT3wK2\u001c\u0015m\u00195f+\t\u0011Y\u0006\u0005\u0005\u0003^\t\u001d\u0014QDA\u000f\u001b\t\u0011yF\u0003\u0003\u0003b\t\r\u0014AC2p]\u000e,(O]3oi*\u0019!Q\r.\u0002\tU$\u0018\u000e\\\u0005\u0005\u0005S\u0012yFA\tD_:\u001cWO\u001d:f]RD\u0015m\u001d5NCB\f!c\u001d;pe\u0006<W\rT3wK2\u001c\u0015m\u00195fA\u0005)r-\u001a;DC\u000eDW\rZ*u_J\fw-\u001a'fm\u0016dG\u0003BA\u000f\u0005cBqAa\u001dI\u0001\u0004\ti\"A\u0003mKZ,G.A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u0001\roJLG/\u001a*fa2\f7-\u001a"
)
public class StorageLevel implements Externalizable {
   private boolean _useDisk;
   private boolean _useMemory;
   private boolean _useOffHeap;
   private boolean _deserialized;
   private int _replication;

   @DeveloperApi
   public static StorageLevel apply(final ObjectInput in) {
      return StorageLevel$.MODULE$.apply(in);
   }

   @DeveloperApi
   public static StorageLevel apply(final int flags, final int replication) {
      return StorageLevel$.MODULE$.apply(flags, replication);
   }

   public static int apply$default$4() {
      return StorageLevel$.MODULE$.apply$default$4();
   }

   @DeveloperApi
   public static StorageLevel apply(final boolean useDisk, final boolean useMemory, final boolean deserialized, final int replication) {
      return StorageLevel$.MODULE$.apply(useDisk, useMemory, deserialized, replication);
   }

   @DeveloperApi
   public static StorageLevel apply(final boolean useDisk, final boolean useMemory, final boolean useOffHeap, final boolean deserialized, final int replication) {
      return StorageLevel$.MODULE$.apply(useDisk, useMemory, useOffHeap, deserialized, replication);
   }

   @DeveloperApi
   public static StorageLevel fromString(final String s) {
      return StorageLevel$.MODULE$.fromString(s);
   }

   public static StorageLevel OFF_HEAP() {
      return StorageLevel$.MODULE$.OFF_HEAP();
   }

   public static StorageLevel MEMORY_AND_DISK_SER_2() {
      return StorageLevel$.MODULE$.MEMORY_AND_DISK_SER_2();
   }

   public static StorageLevel MEMORY_AND_DISK_SER() {
      return StorageLevel$.MODULE$.MEMORY_AND_DISK_SER();
   }

   public static StorageLevel MEMORY_AND_DISK_2() {
      return StorageLevel$.MODULE$.MEMORY_AND_DISK_2();
   }

   public static StorageLevel MEMORY_AND_DISK() {
      return StorageLevel$.MODULE$.MEMORY_AND_DISK();
   }

   public static StorageLevel MEMORY_ONLY_SER_2() {
      return StorageLevel$.MODULE$.MEMORY_ONLY_SER_2();
   }

   public static StorageLevel MEMORY_ONLY_SER() {
      return StorageLevel$.MODULE$.MEMORY_ONLY_SER();
   }

   public static StorageLevel MEMORY_ONLY_2() {
      return StorageLevel$.MODULE$.MEMORY_ONLY_2();
   }

   public static StorageLevel MEMORY_ONLY() {
      return StorageLevel$.MODULE$.MEMORY_ONLY();
   }

   public static StorageLevel DISK_ONLY_3() {
      return StorageLevel$.MODULE$.DISK_ONLY_3();
   }

   public static StorageLevel DISK_ONLY_2() {
      return StorageLevel$.MODULE$.DISK_ONLY_2();
   }

   public static StorageLevel DISK_ONLY() {
      return StorageLevel$.MODULE$.DISK_ONLY();
   }

   public static StorageLevel NONE() {
      return StorageLevel$.MODULE$.NONE();
   }

   private boolean _useDisk() {
      return this._useDisk;
   }

   private void _useDisk_$eq(final boolean x$1) {
      this._useDisk = x$1;
   }

   private boolean _useMemory() {
      return this._useMemory;
   }

   private void _useMemory_$eq(final boolean x$1) {
      this._useMemory = x$1;
   }

   private boolean _useOffHeap() {
      return this._useOffHeap;
   }

   private void _useOffHeap_$eq(final boolean x$1) {
      this._useOffHeap = x$1;
   }

   private boolean _deserialized() {
      return this._deserialized;
   }

   private void _deserialized_$eq(final boolean x$1) {
      this._deserialized = x$1;
   }

   private int _replication() {
      return this._replication;
   }

   private void _replication_$eq(final int x$1) {
      this._replication = x$1;
   }

   public boolean useDisk() {
      return this._useDisk();
   }

   public boolean useMemory() {
      return this._useMemory();
   }

   public boolean useOffHeap() {
      return this._useOffHeap();
   }

   public boolean deserialized() {
      return this._deserialized();
   }

   public int replication() {
      return this._replication();
   }

   public MemoryMode memoryMode() {
      return this.useOffHeap() ? MemoryMode.OFF_HEAP : MemoryMode.ON_HEAP;
   }

   public StorageLevel clone() {
      return new StorageLevel(this.useDisk(), this.useMemory(), this.useOffHeap(), this.deserialized(), this.replication());
   }

   public boolean equals(final Object other) {
      if (!(other instanceof StorageLevel var4)) {
         return false;
      } else {
         return var4.useDisk() == this.useDisk() && var4.useMemory() == this.useMemory() && var4.useOffHeap() == this.useOffHeap() && var4.deserialized() == this.deserialized() && var4.replication() == this.replication();
      }
   }

   public boolean isValid() {
      return (this.useMemory() || this.useDisk()) && this.replication() > 0;
   }

   public int toInt() {
      int ret = 0;
      if (this._useDisk()) {
         ret |= 8;
      }

      if (this._useMemory()) {
         ret |= 4;
      }

      if (this._useOffHeap()) {
         ret |= 2;
      }

      if (this._deserialized()) {
         ret |= 1;
      }

      return ret;
   }

   public void writeExternal(final ObjectOutput out) {
      SparkErrorUtils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         out.writeByte(this.toInt());
         out.writeByte(this._replication());
      });
   }

   public void readExternal(final ObjectInput in) {
      SparkErrorUtils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         byte flags = in.readByte();
         this._useDisk_$eq((flags & 8) != 0);
         this._useMemory_$eq((flags & 4) != 0);
         this._useOffHeap_$eq((flags & 2) != 0);
         this._deserialized_$eq((flags & 1) != 0);
         this._replication_$eq(in.readByte());
      });
   }

   private Object readResolve() throws IOException {
      return StorageLevel$.MODULE$.getCachedStorageLevel(this);
   }

   public String toString() {
      String disk = this.useDisk() ? "disk" : "";
      String memory = this.useMemory() ? "memory" : "";
      String heap = this.useOffHeap() ? "offheap" : "";
      String deserialize = this.deserialized() ? "deserialized" : "";
      Seq output = (Seq)(new .colon.colon(disk, new .colon.colon(memory, new .colon.colon(heap, new .colon.colon(deserialize, new .colon.colon(this.replication() + " replicas", scala.collection.immutable.Nil..MODULE$)))))).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$toString$1(x$1)));
      return "StorageLevel(" + output.mkString(", ") + ")";
   }

   public int hashCode() {
      return this.toInt() * 41 + this.replication();
   }

   public String description() {
      String result = "";
      result = result + (this.useDisk() ? "Disk " : "");
      if (this.useMemory()) {
         result = result + (this.useOffHeap() ? "Memory (off heap) " : "Memory ");
      }

      result = result + (this.deserialized() ? "Deserialized " : "Serialized ");
      result = result + this.replication() + "x Replicated";
      return result;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toString$1(final String x$1) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   public StorageLevel(final boolean _useDisk, final boolean _useMemory, final boolean _useOffHeap, final boolean _deserialized, final int _replication) {
      this._useDisk = _useDisk;
      this._useMemory = _useMemory;
      this._useOffHeap = _useOffHeap;
      this._deserialized = _deserialized;
      this._replication = _replication;
      super();
      scala.Predef..MODULE$.assert(this.replication() < 40, () -> "Replication restricted to be less than 40 for calculating hash codes");
   }

   public StorageLevel(final int flags, final int replication) {
      this((flags & 8) != 0, (flags & 4) != 0, (flags & 2) != 0, (flags & 1) != 0, replication);
   }

   public StorageLevel() {
      this(false, true, false, false, StorageLevel$.MODULE$.org$apache$spark$storage$StorageLevel$$$lessinit$greater$default$5());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
