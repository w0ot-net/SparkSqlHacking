package org.apache.spark.repl;

import java.io.File;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SparkSession;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ur!\u0002\u000f\u001e\u0011\u00031c!\u0002\u0015\u001e\u0011\u0003I\u0003\"\u0002\u001c\u0002\t\u00039\u0004b\u0002\u001d\u0002\u0005\u0004%\t!\u000f\u0005\u0007}\u0005\u0001\u000b\u0011\u0002\u001e\t\u000f}\n!\u0019!C\u0001\u0001\"1A*\u0001Q\u0001\n\u0005Cq!T\u0001C\u0002\u0013\u0005a\n\u0003\u0004X\u0003\u0001\u0006Ia\u0014\u0005\n1\u0006\u0001\r\u00111A\u0005\u0002eC\u0011\"X\u0001A\u0002\u0003\u0007I\u0011\u00010\t\u0013\u0011\f\u0001\u0019!A!B\u0013Q\u0006\"C3\u0002\u0001\u0004\u0005\r\u0011\"\u0001g\u0011%i\u0017\u00011AA\u0002\u0013\u0005a\u000eC\u0005q\u0003\u0001\u0007\t\u0011)Q\u0005O\"I\u0011/\u0001a\u0001\u0002\u0004%\tA\u001d\u0005\nm\u0006\u0001\r\u00111A\u0005\u0002]D\u0011\"_\u0001A\u0002\u0003\u0005\u000b\u0015B:\t\u000fi\f\u0001\u0019!C\u0005w\"Aq0\u0001a\u0001\n\u0013\t\t\u0001C\u0004\u0002\u0006\u0005\u0001\u000b\u0015\u0002?\t\u0011\u0005\u001d\u0011\u00011A\u0005\nmD\u0011\"!\u0003\u0002\u0001\u0004%I!a\u0003\t\u000f\u0005=\u0011\u0001)Q\u0005y\"9\u0011\u0011C\u0001\u0005\n\u0005M\u0001bBA\r\u0003\u0011\u0005\u00111\u0004\u0005\t\u0003O\tA\u0011A\u000f\u0002*!9\u0011\u0011G\u0001\u0005\u0002\u0005M\u0012\u0001B'bS:T!AH\u0010\u0002\tI,\u0007\u000f\u001c\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sO\u000e\u0001\u0001CA\u0014\u0002\u001b\u0005i\"\u0001B'bS:\u001c2!\u0001\u00161!\tYc&D\u0001-\u0015\u0005i\u0013!B:dC2\f\u0017BA\u0018-\u0005\u0019\te.\u001f*fMB\u0011\u0011\u0007N\u0007\u0002e)\u00111gH\u0001\tS:$XM\u001d8bY&\u0011QG\r\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}Q\ta%\u0001\u0003d_:4W#\u0001\u001e\u0011\u0005mbT\"A\u0010\n\u0005uz\"!C*qCJ\\7i\u001c8g\u0003\u0015\u0019wN\u001c4!\u0003\u001d\u0011xn\u001c;ESJ,\u0012!\u0011\t\u0003\u0005&s!aQ$\u0011\u0005\u0011cS\"A#\u000b\u0005\u0019+\u0013A\u0002\u001fs_>$h(\u0003\u0002IY\u00051\u0001K]3eK\u001aL!AS&\u0003\rM#(/\u001b8h\u0015\tAE&\u0001\u0005s_>$H)\u001b:!\u0003%yW\u000f\u001e9vi\u0012K'/F\u0001P!\t\u0001V+D\u0001R\u0015\t\u00116+\u0001\u0002j_*\tA+\u0001\u0003kCZ\f\u0017B\u0001,R\u0005\u00111\u0015\u000e\\3\u0002\u0015=,H\u000f];u\t&\u0014\b%\u0001\u0007ta\u0006\u00148nQ8oi\u0016DH/F\u0001[!\tY4,\u0003\u0002]?\ta1\u000b]1sW\u000e{g\u000e^3yi\u0006\u00012\u000f]1sW\u000e{g\u000e^3yi~#S-\u001d\u000b\u0003?\n\u0004\"a\u000b1\n\u0005\u0005d#\u0001B+oSRDqa\u0019\u0006\u0002\u0002\u0003\u0007!,A\u0002yIE\nQb\u001d9be.\u001cuN\u001c;fqR\u0004\u0013\u0001D:qCJ\\7+Z:tS>tW#A4\u0011\u0005!\\W\"A5\u000b\u0005)|\u0012aA:rY&\u0011A.\u001b\u0002\r'B\f'o[*fgNLwN\\\u0001\u0011gB\f'o[*fgNLwN\\0%KF$\"aX8\t\u000f\rl\u0011\u0011!a\u0001O\u0006i1\u000f]1sWN+7o]5p]\u0002\na!\u001b8uKJ\u0004X#A:\u0011\u0005\u001d\"\u0018BA;\u001e\u0005)\u0019\u0006/\u0019:l\u00132{w\u000e]\u0001\u000bS:$XM\u001d9`I\u0015\fHCA0y\u0011\u001d\u0019\u0007#!AA\u0002M\fq!\u001b8uKJ\u0004\b%A\u0005iCN,%O]8sgV\tA\u0010\u0005\u0002,{&\u0011a\u0010\f\u0002\b\u0005>|G.Z1o\u00035A\u0017m]#se>\u00148o\u0018\u0013fcR\u0019q,a\u0001\t\u000f\r\u001c\u0012\u0011!a\u0001y\u0006Q\u0001.Y:FeJ|'o\u001d\u0011\u0002\u001d%\u001c8\u000b[3mYN+7o]5p]\u0006\u0011\u0012n]*iK2d7+Z:tS>tw\fJ3r)\ry\u0016Q\u0002\u0005\bGZ\t\t\u00111\u0001}\u0003=I7o\u00155fY2\u001cVm]:j_:\u0004\u0013\u0001E:dC2\fw\n\u001d;j_:,%O]8s)\ry\u0016Q\u0003\u0005\u0007\u0003/A\u0002\u0019A!\u0002\u00075\u001cx-\u0001\u0003nC&tGcA0\u0002\u001e!9\u0011qD\rA\u0002\u0005\u0005\u0012\u0001B1sON\u0004BaKA\u0012\u0003&\u0019\u0011Q\u0005\u0017\u0003\u000b\u0005\u0013(/Y=\u0002\r\u0011|W*Y5o)\u0015y\u00161FA\u0017\u0011\u001d\tyB\u0007a\u0001\u0003CAa!a\f\u001b\u0001\u0004\u0019\u0018aB0j]R,'\u000f]\u0001\u0013GJ,\u0017\r^3Ta\u0006\u00148nU3tg&|g\u000eF\u0001h\u0001"
)
public final class Main {
   public static SparkSession createSparkSession() {
      return Main$.MODULE$.createSparkSession();
   }

   public static void main(final String[] args) {
      Main$.MODULE$.main(args);
   }

   public static void interp_$eq(final SparkILoop x$1) {
      Main$.MODULE$.interp_$eq(x$1);
   }

   public static SparkILoop interp() {
      return Main$.MODULE$.interp();
   }

   public static void sparkSession_$eq(final SparkSession x$1) {
      Main$.MODULE$.sparkSession_$eq(x$1);
   }

   public static SparkSession sparkSession() {
      return Main$.MODULE$.sparkSession();
   }

   public static void sparkContext_$eq(final SparkContext x$1) {
      Main$.MODULE$.sparkContext_$eq(x$1);
   }

   public static SparkContext sparkContext() {
      return Main$.MODULE$.sparkContext();
   }

   public static File outputDir() {
      return Main$.MODULE$.outputDir();
   }

   public static String rootDir() {
      return Main$.MODULE$.rootDir();
   }

   public static SparkConf conf() {
      return Main$.MODULE$.conf();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Main$.MODULE$.LogStringContext(sc);
   }
}
