package javax.jdo.metadata;

import javax.jdo.AttributeConverter;
import javax.jdo.annotations.ForeignKeyAction;

public interface KeyMetadata extends Metadata {
   KeyMetadata setColumn(String var1);

   String getColumn();

   KeyMetadata setTable(String var1);

   String getTable();

   KeyMetadata setDeleteAction(ForeignKeyAction var1);

   ForeignKeyAction getDeleteAction();

   KeyMetadata setUpdateAction(ForeignKeyAction var1);

   ForeignKeyAction getUpdateAction();

   ColumnMetadata[] getColumns();

   ColumnMetadata newColumnMetadata();

   int getNumberOfColumns();

   EmbeddedMetadata newEmbeddedMetadata();

   EmbeddedMetadata getEmbeddedMetadata();

   IndexMetadata newIndexMetadata();

   IndexMetadata getIndexMetadata();

   UniqueMetadata newUniqueMetadata();

   UniqueMetadata getUniqueMetadata();

   ForeignKeyMetadata newForeignKeyMetadata();

   ForeignKeyMetadata getForeignKeyMetadata();

   AttributeConverter getConverter();

   KeyMetadata setConverter(AttributeConverter var1);

   Boolean getUseDefaultConversion();

   KeyMetadata setUseDefaultConversion(Boolean var1);
}
