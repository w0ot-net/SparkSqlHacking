package org.apache.derby.impl.store.access.conglomerate;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class OpenConglomerateScratchSpace implements DynamicCompiledOpenConglomInfo {
   private DataValueDescriptor[] row_for_export_template;
   private DataValueDescriptor[] scratch_template;
   private DataValueDescriptor[] scratch_row;
   private final int[] format_ids;
   private final int[] collation_ids;
   private final boolean hasCollatedTypes;
   private RowPosition scratch_row_position;

   public OpenConglomerateScratchSpace(int[] var1, int[] var2, boolean var3) {
      this.format_ids = var1;
      this.collation_ids = var2;
      this.hasCollatedTypes = var3;
   }

   public DataValueDescriptor[] get_row_for_export(Transaction var1) throws StandardException {
      if (this.row_for_export_template == null) {
         this.row_for_export_template = RowUtil.newTemplate(var1.getDataValueFactory(), (FormatableBitSet)null, this.format_ids, this.collation_ids);
      }

      return RowUtil.newRowFromTemplate(this.row_for_export_template);
   }

   public DataValueDescriptor[] get_scratch_row(Transaction var1) throws StandardException {
      if (this.scratch_row == null) {
         this.scratch_row = this.get_row_for_export(var1);
      }

      return this.scratch_row;
   }

   public DataValueDescriptor[] get_template(Transaction var1) throws StandardException {
      if (this.scratch_template == null) {
         this.scratch_template = TemplateRow.newRow(var1, (FormatableBitSet)null, this.format_ids, this.collation_ids);
      }

      return this.scratch_template;
   }

   public RowPosition get_scratch_row_position() {
      if (this.scratch_row_position == null) {
         this.scratch_row_position = new RowPosition();
      }

      return this.scratch_row_position;
   }

   public boolean hasCollatedTypes() {
      return this.hasCollatedTypes;
   }
}
