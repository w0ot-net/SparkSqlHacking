package org.apache.spark.sql.catalog;

import java.util.Map;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015a!\u0002\u001c8\u0003\u0003\u0011\u0005\"B%\u0001\t\u0003Q\u0005\"B'\u0001\r\u0003q\u0005\"\u0002.\u0001\r\u0003Y\u0006\"B1\u0001\r\u0003\u0011\u0007\"B1\u0001\r\u0003Q\u0007\"B7\u0001\r\u0003q\u0007\"B7\u0001\r\u0003\u0019\b\"B7\u0001\r\u0003q\bbBA\u0003\u0001\u0019\u0005\u0011q\u0001\u0005\b\u0003\u000b\u0001a\u0011AA\t\u0011\u001d\t)\u0001\u0001D\u0001\u0003/Aq!a\b\u0001\r\u0003\t\t\u0003C\u0004\u0002 \u00011\t!!\u000e\t\u000f\u0005\u0005\u0003A\"\u0001\u0002D!9\u0011\u0011\n\u0001\u0007\u0002\u0005-\u0003bBA%\u0001\u0019\u0005\u0011\u0011\u000b\u0005\b\u00033\u0002a\u0011AA.\u0011\u001d\tI\u0006\u0001D\u0001\u0003OBq!a\u001d\u0001\r\u0003\t)\bC\u0004\u0002\u0000\u00011\t!!!\t\u000f\u0005}\u0004A\"\u0001\u0002\u0006\"9\u00111\u0012\u0001\u0007\u0002\u00055\u0005bBAF\u0001\u0019\u0005\u0011\u0011\u0013\u0005\b\u0003/\u0003A\u0011AAM\u0011\u001d\t\u0019\u000e\u0001D\u0001\u0003+Dq!a&\u0001\t\u0003\tY\u000eC\u0004\u0002T\u00021\t!a:\t\u000f\u0005]\u0005\u0001\"\u0001\u0002p\"9\u00111\u001b\u0001\u0005\u0002\t-\u0001bBAL\u0001\u0011\u0005!1\u0003\u0005\b\u0003'\u0004a\u0011\u0001B\u0011\u0011\u001d\t9\n\u0001C\u0001\u0005SAq!a5\u0001\t\u0003\u0011\u0019\u0005C\u0004\u0002T\u00021\tAa\u0014\t\u000f\u0005M\u0007\u0001\"\u0001\u0003Z!9\u0011q\u0013\u0001\u0005\u0002\t\r\u0004bBAj\u0001\u0019\u0005!q\u000e\u0005\b\u0003'\u0004A\u0011\u0001B=\u0011\u001d\t\u0019\u000e\u0001D\u0001\u0005\u000bCqA!%\u0001\r\u0003\u0011\u0019\nC\u0004\u0003\u001a\u00021\tAa'\t\u000f\t}\u0005A\"\u0001\u0003\"\"9!Q\u0015\u0001\u0007\u0002\t\u001d\u0006b\u0002BV\u0001\u0019\u0005!Q\u0016\u0005\b\u0005W\u0003a\u0011\u0001BY\u0011\u001d\u0011)\r\u0001D\u0001\u0005\u000fDqAa3\u0001\r\u0003\u0011i\rC\u0004\u0003P\u00021\tA!5\t\u000f\tU\u0007A\"\u0001\u0003X\"9!1\u001c\u0001\u0007\u0002\tu\u0007b\u0002Bp\u0001\u0019\u0005!\u0011\u001d\u0005\b\u0005O\u0004a\u0011\u0001Bu\u0011\u001d\u00119\u000f\u0001D\u0001\u0005g\u0014qaQ1uC2|wM\u0003\u00029s\u000591-\u0019;bY><'B\u0001\u001e<\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003yu\nQa\u001d9be.T!AP \u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0015aA8sO\u000e\u00011C\u0001\u0001D!\t!u)D\u0001F\u0015\u00051\u0015!B:dC2\f\u0017B\u0001%F\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012a\u0013\t\u0003\u0019\u0002i\u0011aN\u0001\u0010GV\u0014(/\u001a8u\t\u0006$\u0018MY1tKV\tq\n\u0005\u0002Q/:\u0011\u0011+\u0016\t\u0003%\u0016k\u0011a\u0015\u0006\u0003)\u0006\u000ba\u0001\u0010:p_Rt\u0014B\u0001,F\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001,\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Y+\u0015AE:fi\u000e+(O]3oi\u0012\u000bG/\u00192bg\u0016$\"\u0001X0\u0011\u0005\u0011k\u0016B\u00010F\u0005\u0011)f.\u001b;\t\u000b\u0001\u001c\u0001\u0019A(\u0002\r\u0011\u0014g*Y7f\u00035a\u0017n\u001d;ECR\f'-Y:fgR\t1\rE\u0002eK\u001el\u0011!O\u0005\u0003Mf\u0012q\u0001R1uCN,G\u000f\u0005\u0002MQ&\u0011\u0011n\u000e\u0002\t\t\u0006$\u0018MY1tKR\u00111m\u001b\u0005\u0006Y\u0016\u0001\raT\u0001\ba\u0006$H/\u001a:o\u0003)a\u0017n\u001d;UC\ndWm\u001d\u000b\u0002_B\u0019A-\u001a9\u0011\u00051\u000b\u0018B\u0001:8\u0005\u0015!\u0016M\u00197f)\tyG\u000fC\u0003a\u000f\u0001\u0007q\nK\u0002\bmr\u00042\u0001R<z\u0013\tAXI\u0001\u0004uQJ|wo\u001d\t\u0003IjL!a_\u001d\u0003#\u0005s\u0017\r\\=tSN,\u0005pY3qi&|g.I\u0001~\u0003]!\u0017\r^1cCN,\u0007\u0005Z8fg\u0002rw\u000e\u001e\u0011fq&\u001cH\u000f\u0006\u0003p\u007f\u0006\u0005\u0001\"\u00021\t\u0001\u0004y\u0005\"\u00027\t\u0001\u0004y\u0005f\u0001\u0005wy\u0006iA.[:u\rVt7\r^5p]N$\"!!\u0003\u0011\t\u0011,\u00171\u0002\t\u0004\u0019\u00065\u0011bAA\bo\tAa)\u001e8di&|g\u000e\u0006\u0003\u0002\n\u0005M\u0001\"\u00021\u000b\u0001\u0004y\u0005f\u0001\u0006wyR1\u0011\u0011BA\r\u00037AQ\u0001Y\u0006A\u0002=CQ\u0001\\\u0006A\u0002=C3a\u0003<}\u0003-a\u0017n\u001d;D_2,XN\\:\u0015\t\u0005\r\u00121\u0006\t\u0005I\u0016\f)\u0003E\u0002M\u0003OI1!!\u000b8\u0005\u0019\u0019u\u000e\\;n]\"1\u0011Q\u0006\u0007A\u0002=\u000b\u0011\u0002^1cY\u0016t\u0015-\\3)\t11\u0018\u0011G\u0011\u0003\u0003g\tA\u0003^1cY\u0016\u0004Cm\\3tA9|G\u000fI3ySN$HCBA\u0012\u0003o\tI\u0004C\u0003a\u001b\u0001\u0007q\n\u0003\u0004\u0002.5\u0001\ra\u0014\u0015\u0005\u001bY\fi$\t\u0002\u0002@\u0005\u0001C-\u0019;bE\u0006\u001cX\rI8sAQ\f'\r\\3!I>,7\u000f\t8pi\u0002*\u00070[:u\u0003-9W\r\u001e#bi\u0006\u0014\u0017m]3\u0015\u0007\u001d\f)\u0005C\u0003a\u001d\u0001\u0007q\nK\u0002\u000fmr\f\u0001bZ3u)\u0006\u0014G.\u001a\u000b\u0004a\u00065\u0003BBA\u0017\u001f\u0001\u0007q\n\u000b\u0003\u0010m\u0006EB#\u00029\u0002T\u0005U\u0003\"\u00021\u0011\u0001\u0004y\u0005BBA\u0017!\u0001\u0007q\n\u000b\u0003\u0011m\u0006u\u0012aC4fi\u001a+hn\u0019;j_:$B!a\u0003\u0002^!1\u0011qL\tA\u0002=\u000bABZ;oGRLwN\u001c(b[\u0016DC!\u0005<\u0002d\u0005\u0012\u0011QM\u0001\u0018MVt7\r^5p]\u0002\"w.Z:!]>$\b%\u001a=jgR$b!a\u0003\u0002j\u0005-\u0004\"\u00021\u0013\u0001\u0004y\u0005BBA0%\u0001\u0007q\n\u000b\u0003\u0013m\u0006=\u0014EAA9\u0003\r\"\u0017\r^1cCN,\u0007e\u001c:!MVt7\r^5p]\u0002\"w.Z:!]>$\b%\u001a=jgR\fa\u0002Z1uC\n\f7/Z#ySN$8\u000f\u0006\u0003\u0002x\u0005u\u0004c\u0001#\u0002z%\u0019\u00111P#\u0003\u000f\t{w\u000e\\3b]\")\u0001m\u0005a\u0001\u001f\u0006YA/\u00192mK\u0016C\u0018n\u001d;t)\u0011\t9(a!\t\r\u00055B\u00031\u0001P)\u0019\t9(a\"\u0002\n\")\u0001-\u0006a\u0001\u001f\"1\u0011QF\u000bA\u0002=\u000baBZ;oGRLwN\\#ySN$8\u000f\u0006\u0003\u0002x\u0005=\u0005BBA0-\u0001\u0007q\n\u0006\u0004\u0002x\u0005M\u0015Q\u0013\u0005\u0006A^\u0001\ra\u0014\u0005\u0007\u0003?:\u0002\u0019A(\u0002'\r\u0014X-\u0019;f\u000bb$XM\u001d8bYR\u000b'\r\\3\u0015\r\u0005m\u0015\u0011XA^!\u0011\ti*a-\u000f\t\u0005}\u0015q\u0016\b\u0005\u0003C\u000biK\u0004\u0003\u0002$\u0006-f\u0002BAS\u0003Ss1AUAT\u0013\u0005\u0001\u0015B\u0001 @\u0013\taT(\u0003\u0002;w%\u0019\u0011\u0011W\u001d\u0002\u000fA\f7m[1hK&!\u0011QWA\\\u0005%!\u0015\r^1Ge\u0006lWMC\u0002\u00022fBa!!\f\u0019\u0001\u0004y\u0005BBA_1\u0001\u0007q*\u0001\u0003qCRD\u0007f\u0003\r\u0002B\u0006\u001d\u0017\u0011ZAg\u0003\u001f\u00042\u0001RAb\u0013\r\t)-\u0012\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u0003\u0017\f\u0001$^:fA\r\u0014X-\u0019;f)\u0006\u0014G.\u001a\u0011j]N$X-\u00193/\u0003\u0015\u0019\u0018N\\2fC\t\t\t.A\u00033]Ir\u0003'A\u0006de\u0016\fG/\u001a+bE2,GCBAN\u0003/\fI\u000e\u0003\u0004\u0002.e\u0001\ra\u0014\u0005\u0007\u0003{K\u0002\u0019A(\u0015\u0011\u0005m\u0015Q\\Ap\u0003CDa!!\f\u001b\u0001\u0004y\u0005BBA_5\u0001\u0007q\n\u0003\u0004\u0002dj\u0001\raT\u0001\u0007g>,(oY3)\u0017i\t\t-a2\u0002J\u00065\u0017q\u001a\u000b\t\u00037\u000bI/a;\u0002n\"1\u0011QF\u000eA\u0002=Ca!!0\u001c\u0001\u0004y\u0005BBAr7\u0001\u0007q\n\u0006\u0005\u0002\u001c\u0006E\u00181_A{\u0011\u0019\ti\u0003\ba\u0001\u001f\"1\u00111\u001d\u000fA\u0002=Cq!a>\u001d\u0001\u0004\tI0A\u0004paRLwN\\:\u0011\r\u0005m(QA(P\u001b\t\tiP\u0003\u0003\u0002\u0000\n\u0005\u0011\u0001B;uS2T!Aa\u0001\u0002\t)\fg/Y\u0005\u0005\u0005\u000f\tiPA\u0002NCBD3\u0002HAa\u0003\u000f\fI-!4\u0002PRA\u00111\u0014B\u0007\u0005\u001f\u0011\t\u0002\u0003\u0004\u0002.u\u0001\ra\u0014\u0005\u0007\u0003Gl\u0002\u0019A(\t\u000f\u0005]X\u00041\u0001\u0002zRA\u00111\u0014B\u000b\u0005/\u0011I\u0002\u0003\u0004\u0002.y\u0001\ra\u0014\u0005\u0007\u0003Gt\u0002\u0019A(\t\u000f\u0005]h\u00041\u0001\u0003\u001cA)\u0001K!\bP\u001f&\u0019!qA-)\u0017y\t\t-a2\u0002J\u00065\u0017q\u001a\u000b\t\u00037\u0013\u0019C!\n\u0003(!1\u0011QF\u0010A\u0002=Ca!a9 \u0001\u0004y\u0005bBA|?\u0001\u0007!1\u0004\u000b\u000b\u00037\u0013YC!\f\u00030\t}\u0002BBA\u0017A\u0001\u0007q\n\u0003\u0004\u0002d\u0002\u0002\ra\u0014\u0005\b\u0005c\u0001\u0003\u0019\u0001B\u001a\u0003\u0019\u00198\r[3nCB!!Q\u0007B\u001e\u001b\t\u00119DC\u0002\u0003:e\nQ\u0001^=qKNLAA!\u0010\u00038\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005]\b\u00051\u0001\u0002z\"Z\u0001%!1\u0002H\u0006%\u0017QZAh))\tYJ!\u0012\u0003H\t%#Q\n\u0005\u0007\u0003[\t\u0003\u0019A(\t\r\u0005\r\u0018\u00051\u0001P\u0011\u0019\u0011Y%\ta\u0001\u001f\u0006YA-Z:de&\u0004H/[8o\u0011\u001d\t90\ta\u0001\u0003s$\"\"a'\u0003R\tM#Q\u000bB,\u0011\u0019\tiC\ta\u0001\u001f\"1\u00111\u001d\u0012A\u0002=CaAa\u0013#\u0001\u0004y\u0005bBA|E\u0001\u0007!1\u0004\u000b\u000b\u00037\u0013YF!\u0018\u0003`\t\u0005\u0004BBA\u0017G\u0001\u0007q\n\u0003\u0004\u0002d\u000e\u0002\ra\u0014\u0005\b\u0005c\u0019\u0003\u0019\u0001B\u001a\u0011\u001d\t9p\ta\u0001\u0003s$\"\"a'\u0003f\t\u001d$\u0011\u000eB6\u0011\u0019\ti\u0003\na\u0001\u001f\"1\u00111\u001d\u0013A\u0002=CqA!\r%\u0001\u0004\u0011\u0019\u0004C\u0004\u0002x\u0012\u0002\rAa\u0007)\u0017\u0011\n\t-a2\u0002J\u00065\u0017q\u001a\u000b\u000b\u00037\u0013\tHa\u001d\u0003v\t]\u0004BBA\u0017K\u0001\u0007q\n\u0003\u0004\u0002d\u0016\u0002\ra\u0014\u0005\b\u0005c)\u0003\u0019\u0001B\u001a\u0011\u001d\t90\na\u0001\u00057!B\"a'\u0003|\tu$q\u0010BA\u0005\u0007Ca!!\f'\u0001\u0004y\u0005BBArM\u0001\u0007q\nC\u0004\u00032\u0019\u0002\rAa\r\t\r\t-c\u00051\u0001P\u0011\u001d\t9P\na\u0001\u0003s$B\"a'\u0003\b\n%%1\u0012BG\u0005\u001fCa!!\f(\u0001\u0004y\u0005BBArO\u0001\u0007q\nC\u0004\u00032\u001d\u0002\rAa\r\t\r\t-s\u00051\u0001P\u0011\u001d\t9p\na\u0001\u00057\tA\u0002\u001a:paR+W\u000e\u001d,jK^$B!a\u001e\u0003\u0016\"1!q\u0013\u0015A\u0002=\u000b\u0001B^5fo:\u000bW.Z\u0001\u0013IJ|\u0007o\u00127pE\u0006dG+Z7q-&,w\u000f\u0006\u0003\u0002x\tu\u0005B\u0002BLS\u0001\u0007q*A\tsK\u000e|g/\u001a:QCJ$\u0018\u000e^5p]N$2\u0001\u0018BR\u0011\u0019\tiC\u000ba\u0001\u001f\u0006A\u0011n]\"bG\",G\r\u0006\u0003\u0002x\t%\u0006BBA\u0017W\u0001\u0007q*\u0001\u0006dC\u000eDW\rV1cY\u0016$2\u0001\u0018BX\u0011\u0019\ti\u0003\fa\u0001\u001fR)ALa-\u00036\"1\u0011QF\u0017A\u0002=CqAa..\u0001\u0004\u0011I,\u0001\u0007ti>\u0014\u0018mZ3MKZ,G\u000e\u0005\u0003\u0003<\n\u0005WB\u0001B_\u0015\r\u0011ylO\u0001\bgR|'/Y4f\u0013\u0011\u0011\u0019M!0\u0003\u0019M#xN]1hK2+g/\u001a7\u0002\u0019Ut7-Y2iKR\u000b'\r\\3\u0015\u0007q\u0013I\r\u0003\u0004\u0002.9\u0002\raT\u0001\u000bG2,\u0017M]\"bG\",G#\u0001/\u0002\u0019I,gM]3tQR\u000b'\r\\3\u0015\u0007q\u0013\u0019\u000e\u0003\u0004\u0002.A\u0002\raT\u0001\u000ee\u00164'/Z:i\u0005f\u0004\u0016\r\u001e5\u0015\u0007q\u0013I\u000e\u0003\u0004\u0002>F\u0002\raT\u0001\u000fGV\u0014(/\u001a8u\u0007\u0006$\u0018\r\\8h)\u0005y\u0015!E:fi\u000e+(O]3oi\u000e\u000bG/\u00197pOR\u0019ALa9\t\r\t\u00158\u00071\u0001P\u0003-\u0019\u0017\r^1m_\u001et\u0015-\\3\u0002\u00191L7\u000f^\"bi\u0006dwnZ:\u0015\u0005\t-\b\u0003\u00023f\u0005[\u00042\u0001\u0014Bx\u0013\r\u0011\tp\u000e\u0002\u0010\u0007\u0006$\u0018\r\\8h\u001b\u0016$\u0018\rZ1uCR!!1\u001eB{\u0011\u0015aW\u00071\u0001PQ\r\u0001!\u0011 \t\u0005\u0005w\u001c\t!\u0004\u0002\u0003~*\u0019!q`\u001e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0004\u0004\tu(AB*uC\ndW\r"
)
public abstract class Catalog {
   public abstract String currentDatabase();

   public abstract void setCurrentDatabase(final String dbName);

   public abstract Dataset listDatabases();

   public abstract Dataset listDatabases(final String pattern);

   public abstract Dataset listTables();

   public abstract Dataset listTables(final String dbName) throws AnalysisException;

   public abstract Dataset listTables(final String dbName, final String pattern) throws AnalysisException;

   public abstract Dataset listFunctions();

   public abstract Dataset listFunctions(final String dbName) throws AnalysisException;

   public abstract Dataset listFunctions(final String dbName, final String pattern) throws AnalysisException;

   public abstract Dataset listColumns(final String tableName) throws AnalysisException;

   public abstract Dataset listColumns(final String dbName, final String tableName) throws AnalysisException;

   public abstract Database getDatabase(final String dbName) throws AnalysisException;

   public abstract Table getTable(final String tableName) throws AnalysisException;

   public abstract Table getTable(final String dbName, final String tableName) throws AnalysisException;

   public abstract Function getFunction(final String functionName) throws AnalysisException;

   public abstract Function getFunction(final String dbName, final String functionName) throws AnalysisException;

   public abstract boolean databaseExists(final String dbName);

   public abstract boolean tableExists(final String tableName);

   public abstract boolean tableExists(final String dbName, final String tableName);

   public abstract boolean functionExists(final String functionName);

   public abstract boolean functionExists(final String dbName, final String functionName);

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String path) {
      return this.createTable(tableName, path);
   }

   public abstract Dataset createTable(final String tableName, final String path);

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String path, final String source) {
      return this.createTable(tableName, path, source);
   }

   public abstract Dataset createTable(final String tableName, final String path, final String source);

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final Map options) {
      return this.createTable(tableName, source, options);
   }

   public Dataset createTable(final String tableName, final String source, final Map options) {
      return this.createTable(tableName, source, .MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final scala.collection.immutable.Map options) {
      return this.createTable(tableName, source, options);
   }

   public abstract Dataset createTable(final String tableName, final String source, final scala.collection.immutable.Map options);

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final StructType schema, final Map options) {
      return this.createTable(tableName, source, schema, options);
   }

   public Dataset createTable(final String tableName, final String source, final String description, final Map options) {
      return this.createTable(tableName, source, description, .MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public abstract Dataset createTable(final String tableName, final String source, final String description, final scala.collection.immutable.Map options);

   public Dataset createTable(final String tableName, final String source, final StructType schema, final Map options) {
      return this.createTable(tableName, source, schema, .MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final StructType schema, final scala.collection.immutable.Map options) {
      return this.createTable(tableName, source, schema, options);
   }

   public abstract Dataset createTable(final String tableName, final String source, final StructType schema, final scala.collection.immutable.Map options);

   public Dataset createTable(final String tableName, final String source, final StructType schema, final String description, final Map options) {
      return this.createTable(tableName, source, schema, description, .MODULE$.MapHasAsScala(options).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public abstract Dataset createTable(final String tableName, final String source, final StructType schema, final String description, final scala.collection.immutable.Map options);

   public abstract boolean dropTempView(final String viewName);

   public abstract boolean dropGlobalTempView(final String viewName);

   public abstract void recoverPartitions(final String tableName);

   public abstract boolean isCached(final String tableName);

   public abstract void cacheTable(final String tableName);

   public abstract void cacheTable(final String tableName, final StorageLevel storageLevel);

   public abstract void uncacheTable(final String tableName);

   public abstract void clearCache();

   public abstract void refreshTable(final String tableName);

   public abstract void refreshByPath(final String path);

   public abstract String currentCatalog();

   public abstract void setCurrentCatalog(final String catalogName);

   public abstract Dataset listCatalogs();

   public abstract Dataset listCatalogs(final String pattern);
}
