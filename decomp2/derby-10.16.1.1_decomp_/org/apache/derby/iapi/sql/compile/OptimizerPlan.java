package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.sql.StatementUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.UniqueTupleDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;

public abstract class OptimizerPlan {
   public static RowSource makeRowSource(UniqueTupleDescriptor var0, DataDictionary var1) throws StandardException {
      if (var0 == null) {
         return null;
      } else if (var0 instanceof ConglomerateDescriptor) {
         return new ConglomerateRS((ConglomerateDescriptor)var0, var1);
      } else {
         return var0 instanceof AliasDescriptor ? new TableFunctionRS((AliasDescriptor)var0) : null;
      }
   }

   public abstract void bind(DataDictionary var1, LanguageConnectionContext var2, CompilerContext var3) throws StandardException;

   public abstract boolean isBound();

   public abstract int countLeafNodes();

   public abstract OptimizerPlan leftmostLeaf();

   public abstract boolean isLeftPrefixOf(OptimizerPlan var1);

   public static final class Join extends OptimizerPlan {
      final JoinStrategy strategy;
      final OptimizerPlan leftChild;
      final OptimizerPlan rightChild;
      private boolean _isBound;
      private int _leafNodeCount = 0;

      public Join(JoinStrategy var1, OptimizerPlan var2, OptimizerPlan var3) {
         this.strategy = var1;
         this.leftChild = var2;
         this.rightChild = var3;
      }

      public void bind(DataDictionary var1, LanguageConnectionContext var2, CompilerContext var3) throws StandardException {
         if (!(this.rightChild instanceof RowSource)) {
            throw StandardException.newException("42ZCD", new Object[0]);
         } else {
            this.leftChild.bind(var1, var2, var3);
            this.rightChild.bind(var1, var2, var3);
            this._isBound = true;
         }
      }

      public boolean isBound() {
         return this._isBound;
      }

      public int countLeafNodes() {
         if (this._leafNodeCount <= 0) {
            this._leafNodeCount = this.leftChild.countLeafNodes() + this.rightChild.countLeafNodes();
         }

         return this._leafNodeCount;
      }

      public OptimizerPlan leftmostLeaf() {
         return this.leftChild.leftmostLeaf();
      }

      public boolean isLeftPrefixOf(OptimizerPlan var1) {
         if (!(var1 instanceof Join var2)) {
            return false;
         } else {
            int var3 = this.countLeafNodes();
            int var4 = var2.countLeafNodes();
            if (var3 > var4) {
               return false;
            } else {
               return var3 < var4 ? this.isLeftPrefixOf(var2.leftChild) : this.equals(var2);
            }
         }
      }

      public String toString() {
         String var10000 = this.leftChild.toString();
         return "( " + var10000 + " " + this.strategy.getOperatorSymbol() + " " + this.rightChild.toString() + " )";
      }

      public boolean equals(Object var1) {
         if (var1 == null) {
            return false;
         } else if (!(var1 instanceof Join)) {
            return false;
         } else {
            Join var2 = (Join)var1;
            if (!this.strategy.getOperatorSymbol().equals(var2.strategy.getOperatorSymbol())) {
               return false;
            } else {
               return this.leftChild.equals(var2.leftChild) && this.rightChild.equals(var2.rightChild);
            }
         }
      }
   }

   public static class DeadEnd extends OptimizerPlan {
      private String _name;

      public DeadEnd(String var1) {
         this._name = var1;
      }

      public void bind(DataDictionary var1, LanguageConnectionContext var2, CompilerContext var3) throws StandardException {
      }

      public boolean isBound() {
         return true;
      }

      public int countLeafNodes() {
         return 1;
      }

      public OptimizerPlan leftmostLeaf() {
         return this;
      }

      public boolean isLeftPrefixOf(OptimizerPlan var1) {
         return this.equals(var1.leftmostLeaf());
      }

      public String toString() {
         return this._name;
      }
   }

   public abstract static class RowSource extends OptimizerPlan {
      protected String _schemaName;
      protected String _rowSourceName;
      protected SchemaDescriptor _schema;
      protected UniqueTupleDescriptor _descriptor;

      public RowSource(String var1, String var2) {
         this._schemaName = var1;
         this._rowSourceName = var2;
      }

      protected RowSource() {
      }

      public UniqueTupleDescriptor getDescriptor() {
         return this._descriptor;
      }

      public void bind(DataDictionary var1, LanguageConnectionContext var2, CompilerContext var3) throws StandardException {
         if (this._schema == null) {
            this._schema = StatementUtil.getSchemaDescriptor(this._schemaName, true, var1, var2, var3);
            this._schemaName = this._schema.getSchemaName();
         }

      }

      public boolean isBound() {
         return this._descriptor != null;
      }

      public int countLeafNodes() {
         return 1;
      }

      public OptimizerPlan leftmostLeaf() {
         return this;
      }

      public boolean isLeftPrefixOf(OptimizerPlan var1) {
         return this.equals(var1.leftmostLeaf());
      }

      public String toString() {
         return IdUtil.mkQualifiedName(this._schemaName, this._rowSourceName);
      }

      public boolean equals(Object var1) {
         if (var1 == null) {
            return false;
         } else if (var1.getClass() != this.getClass()) {
            return false;
         } else {
            RowSource var2 = (RowSource)var1;
            if (this.isBound() && var2.isBound()) {
               return this._schemaName.equals(var2._schemaName) && this._rowSourceName.equals(var2._rowSourceName);
            } else {
               return false;
            }
         }
      }
   }

   public static final class ConglomerateRS extends RowSource {
      public ConglomerateRS(String var1, String var2) {
         super(var1, var2);
      }

      public ConglomerateRS(ConglomerateDescriptor var1, DataDictionary var2) throws StandardException {
         this._descriptor = var1;
         this._schema = var2.getSchemaDescriptor(var1.getSchemaID(), (TransactionController)null);
         this._schemaName = this._schema.getSchemaName();
         this._rowSourceName = var1.getConglomerateName();
      }

      public void bind(DataDictionary var1, LanguageConnectionContext var2, CompilerContext var3) throws StandardException {
         super.bind(var1, var2, var3);
         if (this._descriptor == null) {
            this._descriptor = var1.getConglomerateDescriptor(this._rowSourceName, this._schema, false);
         }

         if (this._descriptor == null) {
            throw StandardException.newException("42X65", new Object[]{this._schemaName + "." + this._rowSourceName});
         }
      }
   }

   public static final class TableFunctionRS extends RowSource {
      public TableFunctionRS(String var1, String var2) {
         super(var1, var2);
      }

      public TableFunctionRS(AliasDescriptor var1) {
         this._descriptor = var1;
         this._schemaName = var1.getSchemaName();
         this._rowSourceName = var1.getName();
      }

      public void bind(DataDictionary var1, LanguageConnectionContext var2, CompilerContext var3) throws StandardException {
         super.bind(var1, var2, var3);
         if (this._descriptor == null) {
            this._descriptor = var1.getAliasDescriptor(this._schema.getUUID().toString(), this._rowSourceName, 'F');
         }

         if (this._descriptor == null) {
            throw StandardException.newException("42X94", new Object[]{AliasDescriptor.getAliasType('F'), this._schemaName + "." + this._rowSourceName});
         }
      }

      public String toString() {
         return super.toString() + "()";
      }
   }
}
