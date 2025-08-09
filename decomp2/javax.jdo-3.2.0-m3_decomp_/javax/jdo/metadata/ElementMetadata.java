package javax.jdo.metadata;

import javax.jdo.AttributeConverter;
import javax.jdo.annotations.ForeignKeyAction;

public interface ElementMetadata extends Metadata {
   ElementMetadata setColumn(String var1);

   String getColumn();

   ElementMetadata setTable(String var1);

   String getTable();

   ElementMetadata setDeleteAction(ForeignKeyAction var1);

   ForeignKeyAction getDeleteAction();

   ElementMetadata setUpdateAction(ForeignKeyAction var1);

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

   ElementMetadata setConverter(AttributeConverter var1);

   Boolean getUseDefaultConversion();

   ElementMetadata setUseDefaultConversion(Boolean var1);
}
