package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.StringUtils;

public class PackageMetaData extends MetaData {
   private static final long serialVersionUID = 2129305063744686523L;
   protected List interfaces = null;
   protected List classes = null;
   protected Collection sequences = null;
   protected Collection tableGenerators = null;
   protected final String name;
   protected String catalog;
   protected String schema;

   PackageMetaData(String name) {
      this.name = name != null ? name : "";
   }

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.catalog == null && ((FileMetaData)this.parent).getCatalog() != null) {
         this.catalog = ((FileMetaData)this.parent).getCatalog();
      }

      if (this.schema == null && ((FileMetaData)this.parent).getSchema() != null) {
         this.schema = ((FileMetaData)this.parent).getSchema();
      }

      super.initialise(clr, mmgr);
   }

   public FileMetaData getFileMetaData() {
      return this.parent != null ? (FileMetaData)this.parent : null;
   }

   public String getName() {
      return this.name;
   }

   public String getCatalog() {
      return this.catalog;
   }

   public String getSchema() {
      return this.schema;
   }

   public int getNoOfInterfaces() {
      return this.interfaces != null ? this.interfaces.size() : 0;
   }

   public InterfaceMetaData getInterface(int i) {
      return (InterfaceMetaData)this.interfaces.get(i);
   }

   public InterfaceMetaData getInterface(String name) {
      for(InterfaceMetaData imd : this.interfaces) {
         if (imd.getName().equals(name)) {
            return imd;
         }
      }

      return null;
   }

   public int getNoOfClasses() {
      return this.classes != null ? this.classes.size() : 0;
   }

   public ClassMetaData getClass(int i) {
      return (ClassMetaData)this.classes.get(i);
   }

   public ClassMetaData getClass(String name) {
      for(ClassMetaData cmd : this.classes) {
         if (cmd.getName().equals(name)) {
            return cmd;
         }
      }

      return null;
   }

   public int getNoOfSequences() {
      return this.sequences != null ? this.sequences.size() : 0;
   }

   public SequenceMetaData[] getSequences() {
      return this.sequences == null ? null : (SequenceMetaData[])((SequenceMetaData[])this.sequences.toArray(new SequenceMetaData[this.sequences.size()]));
   }

   public SequenceMetaData getSequence(String name) {
      for(SequenceMetaData seqmd : this.sequences) {
         if (seqmd.getName().equals(name)) {
            return seqmd;
         }
      }

      return null;
   }

   public int getNoOfTableGenerators() {
      return this.tableGenerators != null ? this.tableGenerators.size() : 0;
   }

   public TableGeneratorMetaData[] getTableGenerators() {
      return this.tableGenerators == null ? null : (TableGeneratorMetaData[])((TableGeneratorMetaData[])this.tableGenerators.toArray(new TableGeneratorMetaData[this.tableGenerators.size()]));
   }

   public TableGeneratorMetaData getTableGenerator(String name) {
      for(TableGeneratorMetaData tgmd : this.tableGenerators) {
         if (tgmd.getName().equals(name)) {
            return tgmd;
         }
      }

      return null;
   }

   public ClassMetaData addClass(ClassMetaData cmd) {
      if (cmd == null) {
         return null;
      } else {
         if (this.classes == null) {
            this.classes = new ArrayList();
         } else {
            for(AbstractClassMetaData c : this.classes) {
               if (cmd.getName().equals(c.getName()) && c instanceof ClassMetaData) {
                  return (ClassMetaData)c;
               }
            }
         }

         this.classes.add(cmd);
         cmd.parent = this;
         return cmd;
      }
   }

   public void removeClass(AbstractClassMetaData cmd) {
      if (this.classes != null) {
         this.classes.remove(cmd);
      }

   }

   public ClassMetaData newClassMetadata(String className) {
      if (StringUtils.isWhitespace(className)) {
         throw new InvalidClassMetaDataException("044061", new Object[]{this.name});
      } else {
         ClassMetaData cmd = new ClassMetaData(this, className);
         return this.addClass(cmd);
      }
   }

   public InterfaceMetaData addInterface(InterfaceMetaData imd) {
      if (imd == null) {
         return null;
      } else {
         if (this.interfaces == null) {
            this.interfaces = new ArrayList();
         } else {
            for(AbstractClassMetaData c : this.interfaces) {
               if (imd.getName().equals(c.getName()) && c instanceof InterfaceMetaData) {
                  return (InterfaceMetaData)c;
               }
            }
         }

         this.interfaces.add(imd);
         imd.parent = this;
         return imd;
      }
   }

   public InterfaceMetaData newInterfaceMetadata(String intfName) {
      InterfaceMetaData imd = new InterfaceMetaData(this, intfName);
      return this.addInterface(imd);
   }

   public void addSequence(SequenceMetaData seqmd) {
      if (seqmd != null) {
         if (this.sequences == null) {
            this.sequences = new HashSet();
         }

         this.sequences.add(seqmd);
         seqmd.parent = this;
      }
   }

   public SequenceMetaData newSequenceMetadata(String seqName, String seqStrategy) {
      SequenceMetaData seqmd = new SequenceMetaData(seqName, seqStrategy);
      this.addSequence(seqmd);
      return seqmd;
   }

   public void addTableGenerator(TableGeneratorMetaData tabmd) {
      if (tabmd != null) {
         if (this.tableGenerators == null) {
            this.tableGenerators = new HashSet();
         }

         this.tableGenerators.add(tabmd);
         tabmd.parent = this;
      }
   }

   public TableGeneratorMetaData newTableGeneratorMetadata(String name) {
      TableGeneratorMetaData tgmd = new TableGeneratorMetaData(name);
      if (this.tableGenerators == null) {
         this.tableGenerators = new HashSet();
      }

      this.tableGenerators.add(tgmd);
      tgmd.parent = this;
      return tgmd;
   }

   public PackageMetaData setCatalog(String catalog) {
      this.catalog = StringUtils.isWhitespace(catalog) ? null : catalog;
      return this;
   }

   public PackageMetaData setSchema(String schema) {
      this.schema = StringUtils.isWhitespace(schema) ? null : schema;
      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<package name=\"" + this.name + "\"");
      if (this.catalog != null) {
         sb.append(" catalog=\"" + this.catalog + "\"");
      }

      if (this.schema != null) {
         sb.append(" schema=\"" + this.schema + "\"");
      }

      sb.append(">\n");
      if (this.interfaces != null) {
         Iterator int_iter = this.interfaces.iterator();

         while(int_iter.hasNext()) {
            sb.append(((InterfaceMetaData)int_iter.next()).toString(prefix + indent, indent));
         }
      }

      if (this.classes != null) {
         Iterator cls_iter = this.classes.iterator();

         while(cls_iter.hasNext()) {
            sb.append(((ClassMetaData)cls_iter.next()).toString(prefix + indent, indent));
         }
      }

      if (this.sequences != null) {
         Iterator seq_iter = this.sequences.iterator();

         while(seq_iter.hasNext()) {
            sb.append(((SequenceMetaData)seq_iter.next()).toString(prefix + indent, indent));
         }
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</package>\n");
      return sb.toString();
   }
}
