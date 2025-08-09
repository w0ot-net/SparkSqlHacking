package org.apache.spark.sql.hive.client;

import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2\u0001\u0002B\u0003\u0011\u0002G\u0005q!\u0005\u0005\u00061\u00011\tA\u0007\u0005\u0006G\u00011\t\u0001\n\u0005\u0006[\u00011\tA\f\u0002\r%\u0006<\b*\u001b<f)\u0006\u0014G.\u001a\u0006\u0003\r\u001d\taa\u00197jK:$(B\u0001\u0005\n\u0003\u0011A\u0017N^3\u000b\u0005)Y\u0011aA:rY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0002\u0001%A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\f\u0001B]1x)\u0006\u0014G.Z\u0002\u0001+\u0005Y\u0002C\u0001\u000f\"\u001b\u0005i\"B\u0001\u0010 \u0003\u0011a\u0017M\\4\u000b\u0003\u0001\nAA[1wC&\u0011!%\b\u0002\u0007\u001f\nTWm\u0019;\u0002\u001dQ|7)\u0019;bY><G+\u00192mKV\tQ\u0005\u0005\u0002'W5\tqE\u0003\u0002)S\u000591-\u0019;bY><'B\u0001\u0016\n\u0003!\u0019\u0017\r^1msN$\u0018B\u0001\u0017(\u00051\u0019\u0015\r^1m_\u001e$\u0016M\u00197f\u00039A\u0017N^3UC\ndW\r\u0015:paN$\u0012a\f\t\u0005a]R$H\u0004\u00022kA\u0011!\u0007F\u0007\u0002g)\u0011A'G\u0001\u0007yI|w\u000e\u001e \n\u0005Y\"\u0012A\u0002)sK\u0012,g-\u0003\u00029s\t\u0019Q*\u00199\u000b\u0005Y\"\u0002C\u0001\u0019<\u0013\ta\u0014H\u0001\u0004TiJLgn\u001a"
)
public interface RawHiveTable {
   Object rawTable();

   CatalogTable toCatalogTable();

   Map hiveTableProps();
}
