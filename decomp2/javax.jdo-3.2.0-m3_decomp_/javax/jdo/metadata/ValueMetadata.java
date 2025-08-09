package javax.jdo.metadata;

import javax.jdo.AttributeConverter;
import javax.jdo.annotations.ForeignKeyAction;

public interface ValueMetadata extends Metadata {
   ValueMetadata setColumn(String var1);

   String getColumn();

   ValueMetadata setTable(String var1);

   String getTable();

   ValueMetadata setDeleteAction(ForeignKeyAction var1);

   ForeignKeyAction getDeleteAction();

   ValueMetadata setUpdateAction(ForeignKeyAction var1);

   ForeignKeyAction getUpdateAction();

   ColumnMetadata[] getColumns();

   ColumnMetadata newColumnMetadata();

   EmbeddedMetadata newEmbeddedMetadata();

   EmbeddedMetadata getEmbeddedMetadata();

   IndexMetadata newIndexMetadata();

   IndexMetadata getIndexMetadata();

   UniqueMetadata newUniqueMetadata();

   UniqueMetadata getUniqueMetadata();

   ForeignKeyMetadata newForeignKeyMetadata();

   ForeignKeyMetadata getForeignKeyMetadata();

   AttributeConverter getConverter();

   ValueMetadata setConverter(AttributeConverter var1);

   Boolean getUseDefaultConversion();

   ValueMetadata setUseDefaultConversion(Boolean var1);
}
