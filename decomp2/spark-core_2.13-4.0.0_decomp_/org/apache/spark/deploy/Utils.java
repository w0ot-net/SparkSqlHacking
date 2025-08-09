package org.apache.spark.deploy;

import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import org.apache.spark.ui.WebUI;
import scala.Option;
import scala.StringContext;
import scala.Tuple4;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}<aAC\u0006\t\u0002-\u0019bAB\u000b\f\u0011\u0003Ya\u0003C\u0003$\u0003\u0011\u0005Q\u0005C\u0004'\u0003\t\u0007I\u0011A\u0014\t\r-\n\u0001\u0015!\u0003)\u0011\u001da\u0013A1A\u0005\u00025BaAP\u0001!\u0002\u0013q\u0003\"B \u0002\t\u0003\u0001\u0005\"\u0002*\u0002\t\u0013\u0019\u0006\"B6\u0002\t\u0003a\u0017!B+uS2\u001c(B\u0001\u0007\u000e\u0003\u0019!W\r\u001d7ps*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014x\r\u0005\u0002\u0015\u00035\t1BA\u0003Vi&d7oE\u0002\u0002/u\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007C\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u000e\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0012 \u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002'\u0005iA)\u0012$B+2#vLQ-U\u000bN+\u0012\u0001\u000b\t\u00031%J!AK\r\u0003\u0007%sG/\u0001\bE\u000b\u001a\u000bU\u000b\u0014+`\u0005f#Vi\u0015\u0011\u0002'M+\u0006\u000bU(S)\u0016#u\fT(H?RK\u0006+R*\u0016\u00039\u00022a\f\u001b7\u001b\u0005\u0001$BA\u00193\u0003%IW.\\;uC\ndWM\u0003\u000243\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005U\u0002$aA*fiB\u0011q\u0007P\u0007\u0002q)\u0011\u0011HO\u0001\u0005Y\u0006twMC\u0001<\u0003\u0011Q\u0017M^1\n\u0005uB$AB*ue&tw-\u0001\u000bT+B\u0003vJ\u0015+F\t~cujR0U3B+5\u000bI\u0001\u0014C\u0012$'+\u001a8eKJdun\u001a%b]\u0012dWM\u001d\u000b\u0004\u0003\u0012c\u0005C\u0001\rC\u0013\t\u0019\u0015D\u0001\u0003V]&$\b\"B#\b\u0001\u00041\u0015\u0001\u00029bO\u0016\u0004\"a\u0012&\u000e\u0003!S!!S\u0007\u0002\u0005UL\u0017BA&I\u0005\u00159VMY+J\u0011\u0015iu\u00011\u0001O\u0003\u0011\u0019wN\u001c4\u0011\u0005=\u0003V\"A\u0007\n\u0005Ek!!C*qCJ\\7i\u001c8g\u0003%\u0011XM\u001c3fe2{w\rF\u0002U=*\u0004\"!\u0016/\u000f\u0005YS\u0006CA,\u001a\u001b\u0005A&BA-%\u0003\u0019a$o\\8u}%\u00111,G\u0001\u0007!J,G-\u001a4\n\u0005uj&BA.\u001a\u0011\u0015y\u0006\u00021\u0001a\u0003\u001d\u0011X-];fgR\u0004\"!\u00195\u000e\u0003\tT!a\u00193\u0002\t!$H\u000f\u001d\u0006\u0003K\u001a\fqa]3sm2,GOC\u0001h\u0003\u001dQ\u0017m[1si\u0006L!!\u001b2\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f\u001e\u0005\u0006\u001b\"\u0001\rAT\u0001\u0007O\u0016$Hj\\4\u0015\r5\u001cHO\u001e=~!\u0019Ab\u000e\u00169qa&\u0011q.\u0007\u0002\u0007)V\u0004H.\u001a\u001b\u0011\u0005a\t\u0018B\u0001:\u001a\u0005\u0011auN\\4\t\u000b5K\u0001\u0019\u0001(\t\u000bUL\u0001\u0019\u0001+\u0002\u00191|w\rR5sK\u000e$xN]=\t\u000b]L\u0001\u0019\u0001+\u0002\u000f1|w\rV=qK\")\u00110\u0003a\u0001u\u0006aqN\u001a4tKR|\u0005\u000f^5p]B\u0019\u0001d\u001f9\n\u0005qL\"AB(qi&|g\u000eC\u0003\u007f\u0013\u0001\u0007\u0001&\u0001\u0006csR,G*\u001a8hi\"\u0004"
)
public final class Utils {
   public static Tuple4 getLog(final SparkConf conf, final String logDirectory, final String logType, final Option offsetOption, final int byteLength) {
      return Utils$.MODULE$.getLog(conf, logDirectory, logType, offsetOption, byteLength);
   }

   public static void addRenderLogHandler(final WebUI page, final SparkConf conf) {
      Utils$.MODULE$.addRenderLogHandler(page, conf);
   }

   public static Set SUPPORTED_LOG_TYPES() {
      return Utils$.MODULE$.SUPPORTED_LOG_TYPES();
   }

   public static int DEFAULT_BYTES() {
      return Utils$.MODULE$.DEFAULT_BYTES();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Utils$.MODULE$.LogStringContext(sc);
   }
}
