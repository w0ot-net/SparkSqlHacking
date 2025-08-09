package org.apache.spark.deploy.worker;

import java.io.File;
import java.io.InputStream;
import org.apache.spark.SecurityManager;
import org.apache.spark.deploy.Command;
import org.apache.spark.internal.Logging;
import scala.Function1;
import scala.StringContext;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=rA\u0002\u0006\f\u0011\u0003iQC\u0002\u0004\u0018\u0017!\u0005Q\u0002\u0007\u0005\u0006K\u0005!\ta\n\u0005\u0006Q\u0005!\t!\u000b\u0005\bQ\u0006\t\n\u0011\"\u0001j\u0011\u001d!\u0018!%A\u0005\u0002UDQa^\u0001\u0005\naDQ\u0001`\u0001\u0005\nuD\u0001\"!\u0003\u0002#\u0003%I!\u001b\u0005\b\u0003\u0017\tA\u0011AA\u0007\u00031\u0019u.\\7b]\u0012,F/\u001b7t\u0015\taQ\"\u0001\u0004x_J\\WM\u001d\u0006\u0003\u001d=\ta\u0001Z3qY>L(B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0011\u0005Y\tQ\"A\u0006\u0003\u0019\r{W.\\1oIV#\u0018\u000e\\:\u0014\u0007\u0005Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A\rj\u0011!\t\u0006\u0003E=\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003I\u0005\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005)\u0012a\u00052vS2$\u0007K]8dKN\u001c()^5mI\u0016\u0014H\u0003\u0003\u00163qy\u001a\u0005+\u00161\u0011\u0005-\u0002T\"\u0001\u0017\u000b\u00055r\u0013\u0001\u00027b]\u001eT\u0011aL\u0001\u0005U\u00064\u0018-\u0003\u00022Y\tq\u0001K]8dKN\u001c()^5mI\u0016\u0014\b\"B\u001a\u0004\u0001\u0004!\u0014aB2p[6\fg\u000e\u001a\t\u0003kYj\u0011!D\u0005\u0003o5\u0011qaQ8n[\u0006tG\rC\u0003:\u0007\u0001\u0007!(A\u0006tK\u000e,(/\u001b;z\u001b\u001e\u0014\bCA\u001e=\u001b\u0005y\u0011BA\u001f\u0010\u0005=\u0019VmY;sSRLX*\u00198bO\u0016\u0014\b\"B \u0004\u0001\u0004\u0001\u0015AB7f[>\u0014\u0018\u0010\u0005\u0002\u001b\u0003&\u0011!i\u0007\u0002\u0004\u0013:$\b\"\u0002#\u0004\u0001\u0004)\u0015!C:qCJ\\\u0007j\\7f!\t1UJ\u0004\u0002H\u0017B\u0011\u0001jG\u0007\u0002\u0013*\u0011!JJ\u0001\u0007yI|w\u000e\u001e \n\u00051[\u0012A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001T\u000e\t\u000bE\u001b\u0001\u0019\u0001*\u0002'M,(m\u001d;jiV$X-\u0011:hk6,g\u000e^:\u0011\ti\u0019V)R\u0005\u0003)n\u0011\u0011BR;oGRLwN\\\u0019\t\u000fY\u001b\u0001\u0013!a\u0001/\u0006Q1\r\\1tgB\u000bG\u000f[:\u0011\u0007akVI\u0004\u0002Z7:\u0011\u0001JW\u0005\u00029%\u0011AlG\u0001\ba\u0006\u001c7.Y4f\u0013\tqvLA\u0002TKFT!\u0001X\u000e\t\u000f\u0005\u001c\u0001\u0013!a\u0001E\u0006\u0019QM\u001c<\u0011\t\r4W)R\u0007\u0002I*\u0011QmG\u0001\u000bG>dG.Z2uS>t\u0017BA4e\u0005\ri\u0015\r]\u0001\u001eEVLG\u000e\u001a)s_\u000e,7o\u001d\"vS2$WM\u001d\u0013eK\u001a\fW\u000f\u001c;%mU\t!N\u000b\u0002XW.\nA\u000e\u0005\u0002ne6\taN\u0003\u0002pa\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003cn\t!\"\u00198o_R\fG/[8o\u0013\t\u0019hNA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQDY;jY\u0012\u0004&o\\2fgN\u0014U/\u001b7eKJ$C-\u001a4bk2$HeN\u000b\u0002m*\u0012!m[\u0001\u0010EVLG\u000eZ\"p[6\fg\u000eZ*fcR!q+\u001f>|\u0011\u0015\u0019d\u00011\u00015\u0011\u0015yd\u00011\u0001A\u0011\u0015!e\u00011\u0001F\u0003E\u0011W/\u001b7e\u0019>\u001c\u0017\r\\\"p[6\fg\u000e\u001a\u000b\niy|\u0018\u0011AA\u0002\u0003\u000fAQaM\u0004A\u0002QBQ!O\u0004A\u0002iBQ!U\u0004A\u0002IC\u0001\"!\u0002\b!\u0003\u0005\raV\u0001\nG2\f7o\u001d)bi\"DQ!Y\u0004A\u0002\t\f1DY;jY\u0012dunY1m\u0007>lW.\u00198eI\u0011,g-Y;mi\u0012\"\u0014A\u0004:fI&\u0014Xm\u0019;TiJ,\u0017-\u001c\u000b\u0007\u0003\u001f\t)\"!\n\u0011\u0007i\t\t\"C\u0002\u0002\u0014m\u0011A!\u00168ji\"9\u0011qC\u0005A\u0002\u0005e\u0011AA5o!\u0011\tY\"!\t\u000e\u0005\u0005u!bAA\u0010]\u0005\u0011\u0011n\\\u0005\u0005\u0003G\tiBA\u0006J]B,Ho\u0015;sK\u0006l\u0007bBA\u0014\u0013\u0001\u0007\u0011\u0011F\u0001\u0005M&dW\r\u0005\u0003\u0002\u001c\u0005-\u0012\u0002BA\u0017\u0003;\u0011AAR5mK\u0002"
)
public final class CommandUtils {
   public static void redirectStream(final InputStream in, final File file) {
      CommandUtils$.MODULE$.redirectStream(in, file);
   }

   public static Map buildProcessBuilder$default$7() {
      return CommandUtils$.MODULE$.buildProcessBuilder$default$7();
   }

   public static Seq buildProcessBuilder$default$6() {
      return CommandUtils$.MODULE$.buildProcessBuilder$default$6();
   }

   public static ProcessBuilder buildProcessBuilder(final Command command, final SecurityManager securityMgr, final int memory, final String sparkHome, final Function1 substituteArguments, final Seq classPaths, final Map env) {
      return CommandUtils$.MODULE$.buildProcessBuilder(command, securityMgr, memory, sparkHome, substituteArguments, classPaths, env);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return CommandUtils$.MODULE$.LogStringContext(sc);
   }
}
