package org.apache.spark.ml.stat;

import org.apache.spark.internal.Logging;
import org.apache.spark.sql.Column;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t%q!\u0002\u0011\"\u0011\u0003ac!\u0002\u0018\"\u0011\u0003y\u0003\"\u0002\u001f\u0002\t\u0003i\u0004\"\u0002 \u0002\t\u0003y\u0004\"B1\u0002\t\u0003\u0011\u0007\"B1\u0002\t\u0003q\u0007\"B9\u0002\t\u0003\u0011\b\"B9\u0002\t\u0003A\b\"B>\u0002\t\u0003a\bBB>\u0002\t\u0003\t\t\u0001C\u0004\u0002\b\u0005!\t!!\u0003\t\u000f\u0005\u001d\u0011\u0001\"\u0001\u0002\u0012!9\u0011qC\u0001\u0005\u0002\u0005e\u0001bBA\f\u0003\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003O\tA\u0011AA\u0015\u0011\u001d\t9#\u0001C\u0001\u0003cAq!a\u000e\u0002\t\u0003\tI\u0004C\u0004\u00028\u0005!\t!!\u0011\t\u000f\u0005\u001d\u0013\u0001\"\u0001\u0002J!9\u0011qI\u0001\u0005\u0002\u0005E\u0003bBA,\u0003\u0011\u0005\u0011\u0011\f\u0005\b\u0003/\nA\u0011AA1\u0011\u001d\t9'\u0001C\u0001\u0003SBq!a\u001a\u0002\t\u0003\t\t\bC\u0004\u0002x\u0005!I!!\u001f\t\u0011\u0005\r\u0015\u0001\"\u0001&\u0003\u000bC\u0001\"!%\u0002\t\u0003\u0019\u00131\u0013\u0005\u000b\u0003+\f\u0011\u0013!C\u0001G\u0005]\u0007BCAu\u0003E\u0005I\u0011A\u0012\u0002l\"A\u0011q^\u0001\u0005\u0002\u0015\n\t\u0010\u0003\u0006\u0003\u0002\u0005\t\n\u0011\"\u0001&\u0003/D!Ba\u0001\u0002#\u0003%\t!JAv\u0003)\u0019V/\\7be&TXM\u001d\u0006\u0003E\r\nAa\u001d;bi*\u0011A%J\u0001\u0003[2T!AJ\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005!J\u0013AB1qC\u000eDWMC\u0001+\u0003\ry'oZ\u0002\u0001!\ti\u0013!D\u0001\"\u0005)\u0019V/\\7be&TXM]\n\u0004\u0003A2\u0004CA\u00195\u001b\u0005\u0011$\"A\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0012$AB!osJ+g\r\u0005\u00028u5\t\u0001H\u0003\u0002:K\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002<q\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$h\bF\u0001-\u0003\u001diW\r\u001e:jGN$\"\u0001Q\"\u0011\u00055\n\u0015B\u0001\"\"\u00059\u0019V/\\7bef\u0014U/\u001b7eKJDQAP\u0002A\u0002\u0011\u00032!M#H\u0013\t1%G\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002\"\u0001S(\u000f\u0005%k\u0005C\u0001&3\u001b\u0005Y%B\u0001',\u0003\u0019a$o\\8u}%\u0011aJM\u0001\u0007!J,G-\u001a4\n\u0005A\u000b&AB*ue&twM\u0003\u0002Oe!\u001a1aU-\u0011\u0005Q;V\"A+\u000b\u0005Y+\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001,\u0016\u0002\u0006'&t7-Z\u0011\u00025\u0006)!GL\u001a/a!\u00121\u0001\u0018\t\u0003;~k\u0011A\u0018\u0006\u0003-JJ!\u0001\u00190\u0003\u000fY\f'/\u0019:hg\u0006!Q.Z1o)\r\u0019\u0017n\u001b\t\u0003I\u001el\u0011!\u001a\u0006\u0003M\u0016\n1a]9m\u0013\tAWM\u0001\u0004D_2,XN\u001c\u0005\u0006U\u0012\u0001\raY\u0001\u0004G>d\u0007\"\u00027\u0005\u0001\u0004\u0019\u0017!C<fS\u001eDGoQ8mQ\r!1+\u0017\u000b\u0003G>DQA[\u0003A\u0002\rD3!B*Z\u0003\r\u0019X/\u001c\u000b\u0004GN$\b\"\u00026\u0007\u0001\u0004\u0019\u0007\"\u00027\u0007\u0001\u0004\u0019\u0007f\u0001\u0004Tm\u0006\nq/A\u00034]Ar\u0003\u0007\u0006\u0002ds\")!n\u0002a\u0001G\"\u001aqa\u0015<\u0002\u0011Y\f'/[1oG\u0016$2aY?\u007f\u0011\u0015Q\u0007\u00021\u0001d\u0011\u0015a\u0007\u00021\u0001dQ\rA1+\u0017\u000b\u0004G\u0006\r\u0001\"\u00026\n\u0001\u0004\u0019\u0007fA\u0005T3\u0006\u00191\u000f\u001e3\u0015\u000b\r\fY!!\u0004\t\u000b)T\u0001\u0019A2\t\u000b1T\u0001\u0019A2)\u0007)\u0019f\u000fF\u0002d\u0003'AQA[\u0006A\u0002\rD3aC*w\u0003\u0015\u0019w.\u001e8u)\u0015\u0019\u00171DA\u000f\u0011\u0015QG\u00021\u0001d\u0011\u0015aG\u00021\u0001dQ\ra1+\u0017\u000b\u0004G\u0006\r\u0002\"\u00026\u000e\u0001\u0004\u0019\u0007fA\u0007T3\u0006Ya.^7O_:TVM]8t)\u0015\u0019\u00171FA\u0017\u0011\u0015Qg\u00021\u0001d\u0011\u0015ag\u00021\u0001dQ\rq1+\u0017\u000b\u0004G\u0006M\u0002\"\u00026\u0010\u0001\u0004\u0019\u0007fA\bT3\u0006\u0019Q.\u0019=\u0015\u000b\r\fY$!\u0010\t\u000b)\u0004\u0002\u0019A2\t\u000b1\u0004\u0002\u0019A2)\u0007A\u0019\u0016\fF\u0002d\u0003\u0007BQA[\tA\u0002\rD3!E*Z\u0003\ri\u0017N\u001c\u000b\u0006G\u0006-\u0013Q\n\u0005\u0006UJ\u0001\ra\u0019\u0005\u0006YJ\u0001\ra\u0019\u0015\u0004%MKFcA2\u0002T!)!n\u0005a\u0001G\"\u001a1cU-\u0002\r9|'/\u001c'2)\u0015\u0019\u00171LA/\u0011\u0015QG\u00031\u0001d\u0011\u0015aG\u00031\u0001dQ\r!2+\u0017\u000b\u0004G\u0006\r\u0004\"\u00026\u0016\u0001\u0004\u0019\u0007fA\u000bT3\u00061an\u001c:n\u0019J\"RaYA6\u0003[BQA\u001b\fA\u0002\rDQ\u0001\u001c\fA\u0002\rD3AF*Z)\r\u0019\u00171\u000f\u0005\u0006U^\u0001\ra\u0019\u0015\u0004/MK\u0016aD4fiNKgn\u001a7f\u001b\u0016$(/[2\u0015\u000f\r\fY(! \u0002\u0000!)!\u000e\u0007a\u0001G\")A\u000e\u0007a\u0001G\"1\u0011\u0011\u0011\rA\u0002\u001d\u000ba!\\3ue&\u001c\u0017AF2sK\u0006$XmU;n[\u0006\u0014\u0018N_3s\u0005V4g-\u001a:\u0015\t\u0005\u001d\u0015Q\u0012\t\u0004[\u0005%\u0015bAAFC\t\u00012+^7nCJL'0\u001a:Ck\u001a4WM\u001d\u0005\u0007\u0003\u001fK\u0002\u0019\u0001#\u0002\u0013I,\u0017/^3ti\u0016$\u0017\u0001G4fiJ+wM]3tg&|gnU;n[\u0006\u0014\u0018N_3sgRA\u0011QSAN\u0003o\u000b\t\rE\u00042\u0003/\u000b9)a\"\n\u0007\u0005e%G\u0001\u0004UkBdWM\r\u0005\b\u0003;S\u0002\u0019AAP\u0003%Ign\u001d;b]\u000e,7\u000f\u0005\u0004\u0002\"\u0006\u001d\u00161V\u0007\u0003\u0003GS1!!*&\u0003\r\u0011H\rZ\u0005\u0005\u0003S\u000b\u0019KA\u0002S\t\u0012\u0003B!!,\u000246\u0011\u0011q\u0016\u0006\u0004\u0003c\u001b\u0013a\u00024fCR,(/Z\u0005\u0005\u0003k\u000byK\u0001\u0005J]N$\u0018M\\2f\u0011%\tIL\u0007I\u0001\u0002\u0004\tY,\u0001\tbO\u001e\u0014XmZ1uS>tG)\u001a9uQB\u0019\u0011'!0\n\u0007\u0005}&GA\u0002J]RD\u0011\"a$\u001b!\u0003\u0005\r!a1\u0011\u000b\u0005\u0015\u0017qZ$\u000f\t\u0005\u001d\u00171\u001a\b\u0004\u0015\u0006%\u0017\"A\u001a\n\u0007\u00055''A\u0004qC\u000e\\\u0017mZ3\n\t\u0005E\u00171\u001b\u0002\u0004'\u0016\f(bAAge\u0005\u0011s-\u001a;SK\u001e\u0014Xm]:j_:\u001cV/\\7be&TXM]:%I\u00164\u0017-\u001e7uII*\"!!7+\t\u0005m\u00161\\\u0016\u0003\u0003;\u0004B!a8\u0002f6\u0011\u0011\u0011\u001d\u0006\u0004\u0003Gt\u0016!C;oG\",7m[3e\u0013\u0011\t9/!9\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0012hKR\u0014Vm\u001a:fgNLwN\\*v[6\f'/\u001b>feN$C-\u001a4bk2$HeM\u000b\u0003\u0003[TC!a1\u0002\\\u0006ar-\u001a;DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8Tk6l\u0017M]5{KJ\u001cH\u0003CAz\u0003w\fi0a@\u0011\u000fE\n9*a\"\u0002vB\u0019Q&a>\n\u0007\u0005e\u0018E\u0001\u000bNk2$\u0018n\u00117bgN\u001cV/\\7be&TXM\u001d\u0005\b\u0003;k\u0002\u0019AAP\u0011%\tI,\bI\u0001\u0002\u0004\tY\fC\u0005\u0002\u0010v\u0001\n\u00111\u0001\u0002D\u00061s-\u001a;DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8Tk6l\u0017M]5{KJ\u001cH\u0005Z3gCVdG\u000f\n\u001a\u0002M\u001d,Go\u00117bgNLg-[2bi&|gnU;n[\u0006\u0014\u0018N_3sg\u0012\"WMZ1vYR$3\u0007K\u0002\u0002'fC3\u0001A*Z\u0001"
)
public final class Summarizer {
   public static SummaryBuilder metrics(final String... metrics) {
      return Summarizer$.MODULE$.metrics(metrics);
   }

   public static Column normL2(final Column col) {
      return Summarizer$.MODULE$.normL2(col);
   }

   public static Column normL2(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.normL2(col, weightCol);
   }

   public static Column normL1(final Column col) {
      return Summarizer$.MODULE$.normL1(col);
   }

   public static Column normL1(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.normL1(col, weightCol);
   }

   public static Column min(final Column col) {
      return Summarizer$.MODULE$.min(col);
   }

   public static Column min(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.min(col, weightCol);
   }

   public static Column max(final Column col) {
      return Summarizer$.MODULE$.max(col);
   }

   public static Column max(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.max(col, weightCol);
   }

   public static Column numNonZeros(final Column col) {
      return Summarizer$.MODULE$.numNonZeros(col);
   }

   public static Column numNonZeros(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.numNonZeros(col, weightCol);
   }

   public static Column count(final Column col) {
      return Summarizer$.MODULE$.count(col);
   }

   public static Column count(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.count(col, weightCol);
   }

   public static Column std(final Column col) {
      return Summarizer$.MODULE$.std(col);
   }

   public static Column std(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.std(col, weightCol);
   }

   public static Column variance(final Column col) {
      return Summarizer$.MODULE$.variance(col);
   }

   public static Column variance(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.variance(col, weightCol);
   }

   public static Column sum(final Column col) {
      return Summarizer$.MODULE$.sum(col);
   }

   public static Column sum(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.sum(col, weightCol);
   }

   public static Column mean(final Column col) {
      return Summarizer$.MODULE$.mean(col);
   }

   public static Column mean(final Column col, final Column weightCol) {
      return Summarizer$.MODULE$.mean(col, weightCol);
   }

   public static SummaryBuilder metrics(final Seq metrics) {
      return Summarizer$.MODULE$.metrics(metrics);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Summarizer$.MODULE$.LogStringContext(sc);
   }
}
