package org.apache.spark.sql.hive;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.sql.catalyst.analysis.FunctionRegistry;
import org.apache.spark.sql.catalyst.analysis.TableFunctionRegistry;
import org.apache.spark.sql.catalyst.catalog.FunctionExpressionBuilder;
import org.apache.spark.sql.catalyst.catalog.FunctionResourceLoader;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog.;
import org.apache.spark.sql.catalyst.parser.ParserInterface;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194Q\u0001D\u0007\u0001\u001f]A\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!A\u0001\u0007\u0001BC\u0002\u0013\u0005\u0011\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00033\u0011!9\u0004A!A!\u0002\u0013A\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B \t\u0011\t\u0003!\u0011!Q\u0001\n\rC\u0011b\u0013\u0001\u0003\u0002\u0003\u0006I\u0001T)\t\u0011I\u0003!\u0011!Q\u0001\nMC\u0001B\u0016\u0001\u0003\u0002\u0003\u0006Ia\u0016\u0005\u00065\u0002!\ta\u0017\u0002\u0013\u0011&4XmU3tg&|gnQ1uC2|wM\u0003\u0002\u000f\u001f\u0005!\u0001.\u001b<f\u0015\t\u0001\u0012#A\u0002tc2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\n\u0003\u0001a\u0001\"!\u0007\u0010\u000e\u0003iQ!a\u0007\u000f\u0002\u000f\r\fG/\u00197pO*\u0011QdD\u0001\tG\u0006$\u0018\r\\=ti&\u0011qD\u0007\u0002\u000f'\u0016\u001c8/[8o\u0007\u0006$\u0018\r\\8h\u0003Y)\u0007\u0010^3s]\u0006d7)\u0019;bY><')^5mI\u0016\u00148\u0001\u0001\t\u0004G\u0019BS\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004CA\r*\u0013\tQ#DA\bFqR,'O\\1m\u0007\u0006$\u0018\r\\8h\u0003q9Gn\u001c2bYR+W\u000e\u001d,jK^l\u0015M\\1hKJ\u0014U/\u001b7eKJ\u00042a\t\u0014.!\tIb&\u0003\u000205\t)r\t\\8cC2$V-\u001c9WS\u0016<X*\u00198bO\u0016\u0014\u0018\u0001E7fi\u0006\u001cHo\u001c:f\u0007\u0006$\u0018\r\\8h+\u0005\u0011\u0004CA\u001a5\u001b\u0005i\u0011BA\u001b\u000e\u0005QA\u0015N^3NKR\f7\u000f^8sK\u000e\u000bG/\u00197pO\u0006\tR.\u001a;bgR|'/Z\"bi\u0006dwn\u001a\u0011\u0002!\u0019,hn\u0019;j_:\u0014VmZ5tiJL\bCA\u001d=\u001b\u0005Q$BA\u001e\u001d\u0003!\tg.\u00197zg&\u001c\u0018BA\u001f;\u0005A1UO\\2uS>t'+Z4jgR\u0014\u00180A\u000buC\ndWMR;oGRLwN\u001c*fO&\u001cHO]=\u0011\u0005e\u0002\u0015BA!;\u0005U!\u0016M\u00197f\rVt7\r^5p]J+w-[:uef\f!\u0002[1e_>\u00048i\u001c8g!\t!\u0015*D\u0001F\u0015\t1u)\u0001\u0003d_:4'B\u0001%\u0014\u0003\u0019A\u0017\rZ8pa&\u0011!*\u0012\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\rA\f'o]3s!\tiu*D\u0001O\u0015\tYE$\u0003\u0002Q\u001d\ny\u0001+\u0019:tKJLe\u000e^3sM\u0006\u001cW-\u0003\u0002L=\u00051b-\u001e8di&|gNU3t_V\u00148-\u001a'pC\u0012,'\u000f\u0005\u0002\u001a)&\u0011QK\u0007\u0002\u0017\rVt7\r^5p]J+7o\\;sG\u0016du.\u00193fe\u0006Ib-\u001e8di&|g.\u0012=qe\u0016\u001c8/[8o\u0005VLG\u000eZ3s!\tI\u0002,\u0003\u0002Z5\tIb)\u001e8di&|g.\u0012=qe\u0016\u001c8/[8o\u0005VLG\u000eZ3s\u0003\u0019a\u0014N\\5u}QQA,\u00180`A\u0006\u00147\rZ3\u0011\u0005M\u0002\u0001\"\u0002\u0011\f\u0001\u0004\u0011\u0003\"B\u0016\f\u0001\u0004a\u0003\"\u0002\u0019\f\u0001\u0004\u0011\u0004\"B\u001c\f\u0001\u0004A\u0004\"\u0002 \f\u0001\u0004y\u0004\"\u0002\"\f\u0001\u0004\u0019\u0005\"B&\f\u0001\u0004a\u0005\"\u0002*\f\u0001\u0004\u0019\u0006\"\u0002,\f\u0001\u00049\u0006"
)
public class HiveSessionCatalog extends SessionCatalog {
   private final HiveMetastoreCatalog metastoreCatalog;

   public HiveMetastoreCatalog metastoreCatalog() {
      return this.metastoreCatalog;
   }

   public HiveSessionCatalog(final Function0 externalCatalogBuilder, final Function0 globalTempViewManagerBuilder, final HiveMetastoreCatalog metastoreCatalog, final FunctionRegistry functionRegistry, final TableFunctionRegistry tableFunctionRegistry, final Configuration hadoopConf, final ParserInterface parser, final FunctionResourceLoader functionResourceLoader, final FunctionExpressionBuilder functionExpressionBuilder) {
      super(externalCatalogBuilder, globalTempViewManagerBuilder, functionRegistry, tableFunctionRegistry, hadoopConf, parser, functionResourceLoader, functionExpressionBuilder, .MODULE$.$lessinit$greater$default$9(), .MODULE$.$lessinit$greater$default$10(), .MODULE$.$lessinit$greater$default$11());
      this.metastoreCatalog = metastoreCatalog;
   }
}
