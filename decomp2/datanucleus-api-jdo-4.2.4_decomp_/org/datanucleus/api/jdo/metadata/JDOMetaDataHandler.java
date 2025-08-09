package org.datanucleus.api.jdo.metadata;

import javax.jdo.AttributeConverter;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.api.jdo.JDOTypeConverter;
import org.datanucleus.api.jdo.JDOTypeConverterUtils;
import org.datanucleus.api.jdo.NucleusJDOHelper;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractElementMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ArrayMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.FetchGroupMemberMetaData;
import org.datanucleus.metadata.FetchGroupMetaData;
import org.datanucleus.metadata.FetchPlanMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.ForeignKeyAction;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityMetaData;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.ImplementsMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.IndexedValue;
import org.datanucleus.metadata.InheritanceMetaData;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.InvalidClassMetaDataException;
import org.datanucleus.metadata.JoinMetaData;
import org.datanucleus.metadata.KeyMetaData;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.MetadataFileType;
import org.datanucleus.metadata.NullValue;
import org.datanucleus.metadata.OrderMetaData;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.metadata.QueryLanguage;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.metadata.ValueMetaData;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.metadata.xml.AbstractMetaDataHandler;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;

public class JDOMetaDataHandler extends AbstractMetaDataHandler {
   public JDOMetaDataHandler(MetaDataManager mgr, String filename, EntityResolver resolver) {
      super(mgr, filename, resolver);
      this.metadata = new FileMetaData();
      ((FileMetaData)this.metadata).setFilename(filename);
      ((FileMetaData)this.metadata).setMetaDataManager(mgr);
      this.pushStack(this.metadata);
   }

   protected ClassMetaData newClassObject(PackageMetaData pmd, Attributes attrs) {
      String name = this.getAttr(attrs, "name");
      if (StringUtils.isWhitespace(name)) {
         throw new InvalidClassMetaDataException("044061", new Object[]{pmd.getName()});
      } else {
         ClassMetaData cmd = new ClassMetaData(pmd, name);
         cmd.setTable(this.getAttr(attrs, "table"));
         cmd.setCatalog(this.getAttr(attrs, "catalog"));
         cmd.setSchema(this.getAttr(attrs, "schema"));
         cmd.setRequiresExtent(this.getAttr(attrs, "requires-extent"));
         String detachableStr = this.getAttr(attrs, "detachable");
         if (this.mgr.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.metadata.alwaysDetachable")) {
            cmd.setDetachable(true);
         } else {
            cmd.setDetachable(detachableStr);
         }

         String objIdCls = this.getAttr(attrs, "objectid-class");
         if (!StringUtils.isWhitespace(objIdCls)) {
            cmd.setObjectIdClass(NucleusJDOHelper.getObjectIdClassForInputIdClass(objIdCls));
         }

         cmd.setEmbeddedOnly(this.getAttr(attrs, "embedded-only"));
         cmd.setPersistenceModifier(ClassPersistenceModifier.getClassPersistenceModifier(this.getAttr(attrs, "persistence-modifier")));
         cmd.setIdentityType(IdentityType.getIdentityType(this.getAttr(attrs, "identity-type")));
         String cacheableAttr = this.getAttr(attrs, "cacheable");
         if (cacheableAttr != null) {
            cmd.setCacheable(!cacheableAttr.equalsIgnoreCase("false"));
         }

         String serializeReadAttr = this.getAttr(attrs, "serialize-read");
         if (serializeReadAttr != null) {
            cmd.setSerializeRead(serializeReadAttr.equalsIgnoreCase("true"));
         }

         String converterAttr = this.getAttr(attrs, "converter");
         String disableConverterAttr = this.getAttr(attrs, "use-default-conversion");
         if ((disableConverterAttr == null || !Boolean.getBoolean(disableConverterAttr)) && !StringUtils.isWhitespace(converterAttr)) {
         }

         return cmd;
      }
   }

   protected InterfaceMetaData newInterfaceObject(PackageMetaData pmd, Attributes attrs) {
      String name = this.getAttr(attrs, "name");
      if (StringUtils.isWhitespace(name)) {
         throw new InvalidClassMetaDataException("044061", new Object[]{pmd.getName()});
      } else {
         InterfaceMetaData imd = new InterfaceMetaData(pmd, name);
         imd.setTable(this.getAttr(attrs, "table"));
         imd.setCatalog(this.getAttr(attrs, "catalog"));
         imd.setSchema(this.getAttr(attrs, "schema"));
         String detachableStr = this.getAttr(attrs, "detachable");
         if (this.mgr.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.metadata.alwaysDetachable")) {
            imd.setDetachable(true);
         } else {
            imd.setDetachable(detachableStr);
         }

         imd.setRequiresExtent(this.getAttr(attrs, "requires-extent"));
         String objIdCls = this.getAttr(attrs, "objectid-class");
         if (!StringUtils.isWhitespace(objIdCls)) {
            imd.setObjectIdClass(NucleusJDOHelper.getObjectIdClassForInputIdClass(objIdCls));
         }

         imd.setEmbeddedOnly(this.getAttr(attrs, "embedded-only"));
         imd.setIdentityType(IdentityType.getIdentityType(this.getAttr(attrs, "identity-type")));
         imd.setPersistenceModifier(ClassPersistenceModifier.PERSISTENCE_CAPABLE);
         String cacheableAttr = this.getAttr(attrs, "cacheable");
         if (cacheableAttr != null) {
            imd.setCacheable(!cacheableAttr.equalsIgnoreCase("false"));
         }

         String converterAttr = this.getAttr(attrs, "converter");
         String disableConverterAttr = this.getAttr(attrs, "use-default-conversion");
         if ((disableConverterAttr == null || !Boolean.getBoolean(disableConverterAttr)) && !StringUtils.isWhitespace(converterAttr)) {
         }

         return imd;
      }
   }

   protected FieldMetaData newFieldObject(MetaData md, Attributes attrs) {
      FieldMetaData fmd = new FieldMetaData(md, this.getAttr(attrs, "name"));
      String modStr = this.getAttr(attrs, "persistence-modifier");
      FieldPersistenceModifier modifier = FieldPersistenceModifier.getFieldPersistenceModifier(modStr);
      if (modifier != null) {
         fmd.setPersistenceModifier(modifier);
      }

      fmd.setDeleteAction(this.getAttr(attrs, "delete-action"));
      String pkStr = this.getAttr(attrs, "primary-key");
      if (!StringUtils.isWhitespace(pkStr)) {
         fmd.setPrimaryKey(Boolean.valueOf(pkStr));
      }

      String dfgStr = this.getAttr(attrs, "default-fetch-group");
      if (!StringUtils.isWhitespace(dfgStr)) {
         fmd.setDefaultFetchGroup(Boolean.valueOf(dfgStr));
      }

      String embStr = this.getAttr(attrs, "embedded");
      if (!StringUtils.isWhitespace(embStr)) {
         fmd.setEmbedded(Boolean.valueOf(embStr));
      }

      String serStr = this.getAttr(attrs, "serialized");
      if (!StringUtils.isWhitespace(serStr)) {
         fmd.setSerialised(Boolean.valueOf(serStr));
      }

      String depStr = this.getAttr(attrs, "dependent");
      if (!StringUtils.isWhitespace(depStr)) {
         fmd.setDependent(Boolean.valueOf(depStr));
      }

      fmd.setNullValue(NullValue.getNullValue(this.getAttr(attrs, "null-value")));
      fmd.setMappedBy(this.getAttr(attrs, "mapped-by"));
      fmd.setColumn(this.getAttr(attrs, "column"));
      fmd.setIndexed(IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed")));
      fmd.setUnique(this.getAttr(attrs, "unique"));
      fmd.setTable(this.getAttr(attrs, "table"));
      fmd.setLoadFetchGroup(this.getAttr(attrs, "load-fetch-group"));
      fmd.setRecursionDepth(this.getAttr(attrs, "recursion-depth"));
      fmd.setValueStrategy(this.getAttr(attrs, "value-strategy"));
      fmd.setSequence(this.getAttr(attrs, "sequence"));
      fmd.setFieldTypes(this.getAttr(attrs, "field-type"));
      String cacheableAttr = this.getAttr(attrs, "cacheable");
      if (cacheableAttr != null) {
         fmd.setCacheable(!cacheableAttr.equalsIgnoreCase("false"));
      }

      String converterAttr = this.getAttr(attrs, "converter");
      String disableConverterAttr = this.getAttr(attrs, "use-default-conversion");
      if (disableConverterAttr != null && Boolean.getBoolean(disableConverterAttr)) {
         fmd.setTypeConverterDisabled();
      } else if (!StringUtils.isWhitespace(converterAttr)) {
         TypeManager typeMgr = this.mgr.getNucleusContext().getTypeManager();
         ClassLoaderResolver clr = this.mgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
         Class converterCls = clr.classForName(converterAttr);
         if (typeMgr.getTypeConverterForName(converterCls.getName()) == null) {
            AttributeConverter conv = (AttributeConverter)ClassUtils.newInstance(converterCls, (Class[])null, (Object[])null);
            Class attrType = JDOTypeConverterUtils.getAttributeTypeForAttributeConverter(converterCls, (Class)null);
            Class dbType = JDOTypeConverterUtils.getDatastoreTypeForAttributeConverter(converterCls, attrType, (Class)null);
            JDOTypeConverter typeConv = new JDOTypeConverter(conv, attrType, dbType);
            typeMgr.registerConverter(converterAttr, typeConv);
         }

         fmd.setTypeConverterName(converterAttr);
      }

      return fmd;
   }

   protected PropertyMetaData newPropertyObject(MetaData md, Attributes attrs) {
      PropertyMetaData pmd = new PropertyMetaData(md, this.getAttr(attrs, "name"));
      String modStr = this.getAttr(attrs, "persistence-modifier");
      FieldPersistenceModifier modifier = FieldPersistenceModifier.getFieldPersistenceModifier(modStr);
      if (modifier != null) {
         pmd.setPersistenceModifier(modifier);
      }

      pmd.setDeleteAction(this.getAttr(attrs, "delete-action"));
      String pkStr = this.getAttr(attrs, "primary-key");
      if (!StringUtils.isWhitespace(pkStr)) {
         pmd.setPrimaryKey(Boolean.valueOf(pkStr));
      }

      String dfgStr = this.getAttr(attrs, "default-fetch-group");
      if (!StringUtils.isWhitespace(dfgStr)) {
         pmd.setDefaultFetchGroup(Boolean.valueOf(dfgStr));
      }

      String embStr = this.getAttr(attrs, "embedded");
      if (!StringUtils.isWhitespace(embStr)) {
         pmd.setEmbedded(Boolean.valueOf(embStr));
      }

      String serStr = this.getAttr(attrs, "serialized");
      if (!StringUtils.isWhitespace(serStr)) {
         pmd.setSerialised(Boolean.valueOf(serStr));
      }

      String depStr = this.getAttr(attrs, "dependent");
      if (!StringUtils.isWhitespace(depStr)) {
         pmd.setDependent(Boolean.valueOf(depStr));
      }

      pmd.setNullValue(NullValue.getNullValue(this.getAttr(attrs, "null-value")));
      pmd.setMappedBy(this.getAttr(attrs, "mapped-by"));
      pmd.setColumn(this.getAttr(attrs, "column"));
      pmd.setIndexed(IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed")));
      pmd.setUnique(this.getAttr(attrs, "unique"));
      pmd.setTable(this.getAttr(attrs, "table"));
      pmd.setLoadFetchGroup(this.getAttr(attrs, "load-fetch-group"));
      pmd.setRecursionDepth(this.getAttr(attrs, "recursion-depth"));
      pmd.setValueStrategy(this.getAttr(attrs, "value-strategy"));
      pmd.setSequence(this.getAttr(attrs, "sequence"));
      pmd.setFieldTypes(this.getAttr(attrs, "field-type"));
      pmd.setFieldName(this.getAttr(attrs, "field-name"));
      String cacheableAttr = this.getAttr(attrs, "cacheable");
      if (cacheableAttr != null) {
         pmd.setCacheable(!cacheableAttr.equalsIgnoreCase("false"));
      }

      String converterAttr = this.getAttr(attrs, "converter");
      String disableConverterAttr = this.getAttr(attrs, "use-default-conversion");
      if (disableConverterAttr != null && Boolean.getBoolean(disableConverterAttr)) {
         pmd.setTypeConverterDisabled();
      } else if (!StringUtils.isWhitespace(converterAttr)) {
         TypeManager typeMgr = this.mgr.getNucleusContext().getTypeManager();
         ClassLoaderResolver clr = this.mgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
         Class converterCls = clr.classForName(converterAttr);
         if (typeMgr.getTypeConverterForName(converterCls.getName()) == null) {
            AttributeConverter conv = (AttributeConverter)ClassUtils.newInstance(converterCls, (Class[])null, (Object[])null);
            Class attrType = JDOTypeConverterUtils.getAttributeTypeForAttributeConverter(converterCls, (Class)null);
            Class dbType = JDOTypeConverterUtils.getDatastoreTypeForAttributeConverter(converterCls, attrType, (Class)null);
            JDOTypeConverter typeConv = new JDOTypeConverter(conv, attrType, dbType);
            typeMgr.registerConverter(converterAttr, typeConv);
         }

         pmd.setTypeConverterName(converterAttr);
      }

      return pmd;
   }

   public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
      if (this.charactersBuffer.length() > 0) {
         String currentString = this.getString().trim();
         if (this.getStack() instanceof QueryMetaData) {
            ((QueryMetaData)this.getStack()).setQuery(currentString.trim());
         }
      }

      if (localName.length() < 1) {
         localName = qName;
      }

      try {
         if (localName.equals("jdo")) {
            FileMetaData filemd = (FileMetaData)this.getStack();
            filemd.setType(MetadataFileType.JDO_FILE);
            filemd.setCatalog(this.getAttr(attrs, "catalog"));
            filemd.setSchema(this.getAttr(attrs, "schema"));
         } else if (localName.equals("orm")) {
            FileMetaData filemd = (FileMetaData)this.getStack();
            filemd.setType(MetadataFileType.JDO_ORM_FILE);
            filemd.setCatalog(this.getAttr(attrs, "catalog"));
            filemd.setSchema(this.getAttr(attrs, "schema"));
         } else if (localName.equals("jdoquery")) {
            FileMetaData filemd = (FileMetaData)this.getStack();
            filemd.setType(MetadataFileType.JDO_QUERY_FILE);
         } else if (localName.equals("fetch-plan")) {
            FileMetaData filemd = (FileMetaData)this.metadata;
            FetchPlanMetaData fpmd = filemd.newFetchPlanMetadata(this.getAttr(attrs, "name"));
            fpmd.setMaxFetchDepth(this.getAttr(attrs, "max-fetch-depth"));
            fpmd.setFetchSize(this.getAttr(attrs, "fetch-size"));
            this.pushStack(fpmd);
         } else if (localName.equals("package")) {
            FileMetaData filemd = (FileMetaData)this.getStack();
            PackageMetaData pmd = filemd.newPackageMetadata(this.getAttr(attrs, "name"));
            pmd.setCatalog(this.getAttr(attrs, "catalog"));
            pmd.setSchema(this.getAttr(attrs, "schema"));
            this.pushStack(pmd);
         } else if (localName.equals("class")) {
            PackageMetaData pmd = (PackageMetaData)this.getStack();
            ClassMetaData cmd = this.newClassObject(pmd, attrs);
            pmd.addClass(cmd);
            this.pushStack(cmd);
         } else if (localName.equals("interface")) {
            PackageMetaData pmd = (PackageMetaData)this.getStack();
            InterfaceMetaData imd = this.newInterfaceObject(pmd, attrs);
            pmd.addInterface(imd);
            this.pushStack(imd);
         } else if (localName.equals("primary-key")) {
            MetaData md = this.getStack();
            PrimaryKeyMetaData pkmd = new PrimaryKeyMetaData();
            pkmd.setName(this.getAttr(attrs, "name"));
            pkmd.setColumnName(this.getAttr(attrs, "column"));
            if (md instanceof AbstractClassMetaData) {
               ((AbstractClassMetaData)md).setPrimaryKeyMetaData(pkmd);
            } else if (md instanceof JoinMetaData) {
               ((JoinMetaData)md).setPrimaryKeyMetaData(pkmd);
            }

            this.pushStack(pkmd);
         } else if (localName.equals("implements")) {
            ClassMetaData cmd = (ClassMetaData)this.getStack();
            ImplementsMetaData imd = new ImplementsMetaData(this.getAttr(attrs, "name"));
            cmd.addImplements(imd);
            this.pushStack(imd);
         } else if (localName.equals("property")) {
            MetaData parent = this.getStack();
            if (parent instanceof AbstractClassMetaData) {
               AbstractClassMetaData acmd = (AbstractClassMetaData)parent;
               PropertyMetaData propmd = this.newPropertyObject(acmd, attrs);
               acmd.addMember(propmd);
               this.pushStack(propmd);
            } else if (parent instanceof EmbeddedMetaData) {
               EmbeddedMetaData emd = (EmbeddedMetaData)parent;
               PropertyMetaData propmd = this.newPropertyObject(emd, attrs);
               emd.addMember(propmd);
               this.pushStack(propmd);
            } else if (parent instanceof ImplementsMetaData) {
               ImplementsMetaData implmd = (ImplementsMetaData)parent;
               PropertyMetaData propmd = this.newPropertyObject(implmd, attrs);
               implmd.addProperty(propmd);
               this.pushStack(propmd);
            } else if (parent instanceof FetchGroupMetaData) {
               FetchGroupMetaData fgmd = (FetchGroupMetaData)parent;
               FetchGroupMemberMetaData fgmmd = new FetchGroupMemberMetaData(fgmd, this.getAttr(attrs, "name"));
               fgmmd.setRecursionDepth(this.getAttr(attrs, "recursion-depth"));
               fgmmd.setProperty();
               fgmd.addMember(fgmmd);
               this.pushStack(fgmmd);
            }
         } else if (localName.equals("datastore-identity")) {
            AbstractClassMetaData acmd = (AbstractClassMetaData)this.getStack();
            IdentityMetaData idmd = new IdentityMetaData();
            idmd.setColumnName(this.getAttr(attrs, "column"));
            idmd.setValueStrategy(IdentityStrategy.getIdentityStrategy(this.getAttr(attrs, "strategy")));
            idmd.setSequence(this.getAttr(attrs, "sequence"));
            acmd.setIdentityMetaData(idmd);
            this.pushStack(idmd);
         } else if (localName.equals("inheritance")) {
            MetaData parent = this.getStack();
            AbstractClassMetaData acmd = (AbstractClassMetaData)parent;
            InheritanceMetaData inhmd = new InheritanceMetaData();
            inhmd.setStrategy(this.getAttr(attrs, "strategy"));
            acmd.setInheritanceMetaData(inhmd);
            this.pushStack(inhmd);
         } else if (localName.equals("discriminator")) {
            MetaData md = this.getStack();
            if (md instanceof InheritanceMetaData) {
               InheritanceMetaData inhmd = (InheritanceMetaData)md;
               DiscriminatorMetaData dismd = inhmd.newDiscriminatorMetadata();
               dismd.setColumnName(this.getAttr(attrs, "column"));
               dismd.setValue(this.getAttr(attrs, "value"));
               dismd.setStrategy(this.getAttr(attrs, "strategy"));
               dismd.setIndexed(this.getAttr(attrs, "indexed"));
               this.pushStack(dismd);
            } else if (md instanceof EmbeddedMetaData) {
               EmbeddedMetaData embmd = (EmbeddedMetaData)md;
               DiscriminatorMetaData dismd = embmd.newDiscriminatorMetadata();
               dismd.setColumnName(this.getAttr(attrs, "column"));
               dismd.setValue(this.getAttr(attrs, "value"));
               dismd.setStrategy(this.getAttr(attrs, "strategy"));
               dismd.setIndexed(this.getAttr(attrs, "indexed"));
               this.pushStack(dismd);
            }
         } else if (localName.equals("query")) {
            MetaData emd = this.getStack();
            String name = this.getAttr(attrs, "name");
            String lang = this.getAttr(attrs, "language");
            if (!StringUtils.isWhitespace(lang)) {
               if (lang.equals("javax.jdo.query.JDOQL")) {
                  lang = QueryLanguage.JDOQL.toString();
               } else if (lang.equals("javax.jdo.query.SQL")) {
                  lang = QueryLanguage.SQL.toString();
               } else if (lang.equals("javax.jdo.query.JPQL")) {
                  lang = QueryLanguage.JPQL.toString();
               }
            }

            if (emd instanceof ClassMetaData) {
               ClassMetaData cmd = (ClassMetaData)emd;
               if (StringUtils.isWhitespace(name)) {
                  throw new InvalidClassMetaDataException("044154", new Object[]{cmd.getFullClassName()});
               }

               QueryMetaData qmd = new QueryMetaData(name);
               qmd.setScope(cmd.getFullClassName());
               qmd.setLanguage(lang);
               qmd.setUnmodifiable(this.getAttr(attrs, "unmodifiable"));
               qmd.setResultClass(this.getAttr(attrs, "result-class"));
               qmd.setUnique(this.getAttr(attrs, "unique"));
               qmd.setFetchPlanName(this.getAttr(attrs, "fetch-plan"));
               cmd.addQuery(qmd);
               this.pushStack(qmd);
            } else if (emd instanceof InterfaceMetaData) {
               InterfaceMetaData imd = (InterfaceMetaData)emd;
               if (StringUtils.isWhitespace(name)) {
                  throw new InvalidClassMetaDataException("044154", new Object[]{imd.getFullClassName()});
               }

               QueryMetaData qmd = new QueryMetaData(name);
               qmd.setScope(imd.getFullClassName());
               qmd.setLanguage(lang);
               qmd.setUnmodifiable(this.getAttr(attrs, "unmodifiable"));
               qmd.setResultClass(this.getAttr(attrs, "result-class"));
               qmd.setUnique(this.getAttr(attrs, "unique"));
               qmd.setFetchPlanName(this.getAttr(attrs, "fetch-plan"));
               imd.addQuery(qmd);
               this.pushStack(qmd);
            } else if (emd instanceof FileMetaData) {
               FileMetaData filemd = (FileMetaData)emd;
               QueryMetaData qmd = filemd.newQueryMetadata(name);
               qmd.setLanguage(lang);
               qmd.setUnmodifiable(this.getAttr(attrs, "unmodifiable"));
               qmd.setResultClass(this.getAttr(attrs, "result-class"));
               qmd.setUnique(this.getAttr(attrs, "unique"));
               qmd.setFetchPlanName(this.getAttr(attrs, "fetch-plan"));
               this.pushStack(qmd);
            }
         } else if (localName.equals("sequence")) {
            PackageMetaData pmd = (PackageMetaData)this.getStack();
            SequenceMetaData seqmd = pmd.newSequenceMetadata(this.getAttr(attrs, "name"), this.getAttr(attrs, "strategy"));
            seqmd.setFactoryClass(this.getAttr(attrs, "factory-class"));
            seqmd.setDatastoreSequence(this.getAttr(attrs, "datastore-sequence"));
            String seqSize = this.getAttr(attrs, "allocation-size");
            if (seqSize != null) {
               seqmd.setAllocationSize(seqSize);
            }

            String seqStart = this.getAttr(attrs, "initial-value");
            if (seqStart != null) {
               seqmd.setInitialValue(seqStart);
            }

            this.pushStack(seqmd);
         } else if (localName.equals("field")) {
            MetaData md = this.getStack();
            if (md instanceof FetchGroupMetaData) {
               FetchGroupMetaData fgmd = (FetchGroupMetaData)md;
               FetchGroupMemberMetaData fgmmd = new FetchGroupMemberMetaData(md, this.getAttr(attrs, "name"));
               fgmmd.setRecursionDepth(this.getAttr(attrs, "recursion-depth"));
               fgmd.addMember(fgmmd);
               this.pushStack(fgmmd);
               return;
            }

            FieldMetaData fmd = this.newFieldObject(md, attrs);
            if (md instanceof ClassMetaData) {
               ClassMetaData cmd = (ClassMetaData)md;
               cmd.addMember(fmd);
            } else if (md instanceof EmbeddedMetaData) {
               EmbeddedMetaData emd = (EmbeddedMetaData)md;
               emd.addMember(fmd);
            } else if (md instanceof ForeignKeyMetaData) {
               ForeignKeyMetaData fkmd = (ForeignKeyMetaData)md;
               fkmd.addMember(fmd.getName());
            } else if (md instanceof IndexMetaData) {
               IndexMetaData imd = (IndexMetaData)md;
               imd.addMember(fmd.getName());
            } else if (md instanceof UniqueMetaData) {
               UniqueMetaData umd = (UniqueMetaData)md;
               umd.addMember(fmd.getName());
            }

            this.pushStack(fmd);
         } else if (localName.equals("join")) {
            MetaData parent = this.getStack();
            String tableName = this.getAttr(attrs, "table");
            String columnName = this.getAttr(attrs, "column");
            String outer = this.getAttr(attrs, "outer");
            IndexedValue indexed = IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed"));
            String unique = this.getAttr(attrs, "unique");
            String deleteAction = this.getAttr(attrs, "delete-action");
            JoinMetaData joinmd = null;
            if (parent instanceof AbstractMemberMetaData) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)parent;
               joinmd = fmd.newJoinMetadata();
            } else if (parent instanceof AbstractClassMetaData) {
               AbstractClassMetaData cmd = (AbstractClassMetaData)parent;
               joinmd = new JoinMetaData();
               cmd.addJoin(joinmd);
            } else {
               if (!(parent instanceof InheritanceMetaData)) {
                  throw new NucleusUserException("Error processing JDO XML metadata. Found \"join\" with parent " + StringUtils.toJVMIDString(parent) + " - not supported");
               }

               InheritanceMetaData inhmd = (InheritanceMetaData)parent;
               joinmd = inhmd.newJoinMetadata();
            }

            joinmd.setTable(tableName);
            joinmd.setColumnName(columnName);
            joinmd.setOuter(MetaDataUtils.getBooleanForString(outer, false));
            joinmd.setIndexed(indexed);
            joinmd.setUnique(unique);
            joinmd.setDeleteAction(deleteAction);
            this.pushStack(joinmd);
         } else if (localName.equals("map")) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.getStack();
            MapMetaData mapmd = fmd.newMapMetaData();
            mapmd.setKeyType(this.getAttr(attrs, "key-type"));
            String embKeyStr = this.getAttr(attrs, "embedded-key");
            if (!StringUtils.isWhitespace(embKeyStr)) {
               mapmd.setEmbeddedKey(Boolean.valueOf(embKeyStr));
            }

            String serKeyStr = this.getAttr(attrs, "serialized-key");
            if (!StringUtils.isWhitespace(serKeyStr)) {
               mapmd.setSerializedKey(Boolean.valueOf(serKeyStr));
            }

            String depKeyStr = this.getAttr(attrs, "dependent-key");
            if (!StringUtils.isWhitespace(depKeyStr)) {
               mapmd.setDependentKey(Boolean.valueOf(depKeyStr));
            }

            mapmd.setValueType(this.getAttr(attrs, "value-type"));
            String embValStr = this.getAttr(attrs, "embedded-value");
            if (!StringUtils.isWhitespace(embValStr)) {
               mapmd.setEmbeddedValue(Boolean.valueOf(embValStr));
            }

            String serValStr = this.getAttr(attrs, "serialized-value");
            if (!StringUtils.isWhitespace(serValStr)) {
               mapmd.setSerializedValue(Boolean.valueOf(serValStr));
            }

            String depValStr = this.getAttr(attrs, "dependent-value");
            if (!StringUtils.isWhitespace(depValStr)) {
               mapmd.setDependentValue(Boolean.valueOf(depValStr));
            }

            this.pushStack(mapmd);
         } else if (localName.equals("array")) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.getStack();
            ArrayMetaData arrmd = fmd.newArrayMetaData();
            arrmd.setElementType(this.getAttr(attrs, "element-type"));
            String embElemStr = this.getAttr(attrs, "embedded-element");
            if (!StringUtils.isWhitespace(embElemStr)) {
               arrmd.setEmbeddedElement(Boolean.valueOf(embElemStr));
            }

            String serElemStr = this.getAttr(attrs, "serialized-element");
            if (!StringUtils.isWhitespace(serElemStr)) {
               arrmd.setSerializedElement(Boolean.valueOf(serElemStr));
            }

            String depElemStr = this.getAttr(attrs, "dependent-element");
            if (!StringUtils.isWhitespace(depElemStr)) {
               arrmd.setDependentElement(Boolean.valueOf(depElemStr));
            }

            this.pushStack(arrmd);
         } else if (localName.equals("collection")) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.getStack();
            CollectionMetaData collmd = fmd.newCollectionMetaData();
            collmd.setElementType(this.getAttr(attrs, "element-type"));
            String embElemStr = this.getAttr(attrs, "embedded-element");
            if (!StringUtils.isWhitespace(embElemStr)) {
               collmd.setEmbeddedElement(Boolean.valueOf(embElemStr));
            }

            String serElemStr = this.getAttr(attrs, "serialized-element");
            if (!StringUtils.isWhitespace(serElemStr)) {
               collmd.setSerializedElement(Boolean.valueOf(serElemStr));
            }

            String depElemStr = this.getAttr(attrs, "dependent-element");
            if (!StringUtils.isWhitespace(depElemStr)) {
               collmd.setDependentElement(Boolean.valueOf(depElemStr));
            }

            this.pushStack(collmd);
         } else if (localName.equals("column")) {
            MetaData md = this.getStack();
            ColumnMetaData colmd = new ColumnMetaData();
            colmd.setName(this.getAttr(attrs, "name"));
            colmd.setTarget(this.getAttr(attrs, "target"));
            colmd.setTargetMember(this.getAttr(attrs, "target-field"));
            colmd.setJdbcType(this.getAttr(attrs, "jdbc-type"));
            colmd.setSqlType(this.getAttr(attrs, "sql-type"));
            colmd.setLength(this.getAttr(attrs, "length"));
            colmd.setScale(this.getAttr(attrs, "scale"));
            colmd.setAllowsNull(this.getAttr(attrs, "allows-null"));
            colmd.setDefaultValue(this.getAttr(attrs, "default-value"));
            colmd.setInsertValue(this.getAttr(attrs, "insert-value"));
            String pos = this.getAttr(attrs, "position");
            if (pos != null) {
               colmd.setPosition(pos);
            }

            if (md instanceof AbstractMemberMetaData) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)md;
               fmd.addColumn(colmd);
            } else if (md instanceof AbstractElementMetaData) {
               AbstractElementMetaData elemd = (AbstractElementMetaData)md;
               elemd.addColumn(colmd);
            } else if (md instanceof JoinMetaData) {
               JoinMetaData jnmd = (JoinMetaData)md;
               jnmd.addColumn(colmd);
            } else if (md instanceof IdentityMetaData) {
               IdentityMetaData idmd = (IdentityMetaData)md;
               idmd.setColumnMetaData(colmd);
            } else if (md instanceof ForeignKeyMetaData) {
               ForeignKeyMetaData fkmd = (ForeignKeyMetaData)md;
               fkmd.addColumn(colmd);
            } else if (md instanceof IndexMetaData) {
               IndexMetaData idxmd = (IndexMetaData)md;
               idxmd.addColumn(colmd.getName());
            } else if (md instanceof UniqueMetaData) {
               UniqueMetaData unimd = (UniqueMetaData)md;
               unimd.addColumn(colmd.getName());
            } else if (md instanceof OrderMetaData) {
               OrderMetaData ormd = (OrderMetaData)md;
               ormd.addColumn(colmd);
            } else if (md instanceof DiscriminatorMetaData) {
               DiscriminatorMetaData dismd = (DiscriminatorMetaData)md;
               dismd.setColumnMetaData(colmd);
            } else if (md instanceof VersionMetaData) {
               VersionMetaData vermd = (VersionMetaData)md;
               vermd.setColumnMetaData(colmd);
            } else if (md instanceof AbstractClassMetaData) {
               AbstractClassMetaData cmd = (AbstractClassMetaData)md;
               cmd.addUnmappedColumn(colmd);
            } else if (md instanceof PrimaryKeyMetaData) {
               PrimaryKeyMetaData pkmd = (PrimaryKeyMetaData)md;
               pkmd.addColumn(colmd);
            }

            this.pushStack(colmd);
         } else if (localName.equals("element")) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.getStack();
            ElementMetaData elemmd = new ElementMetaData();
            elemmd.setTable(this.getAttr(attrs, "table"));
            elemmd.setColumnName(this.getAttr(attrs, "column"));
            elemmd.setDeleteAction(this.getAttr(attrs, "delete-action"));
            elemmd.setUpdateAction(this.getAttr(attrs, "update-action"));
            elemmd.setIndexed(IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed")));
            elemmd.setUnique(MetaDataUtils.getBooleanForString(this.getAttr(attrs, "unique"), false));
            String mappedBy = this.getAttr(attrs, "mapped-by");
            elemmd.setMappedBy(mappedBy);
            if (!StringUtils.isWhitespace(mappedBy) && fmd.getMappedBy() == null) {
               fmd.setMappedBy(mappedBy);
            }

            String converterAttr = this.getAttr(attrs, "converter");
            String disableConverterAttr = this.getAttr(attrs, "use-default-conversion");
            if ((disableConverterAttr == null || !Boolean.getBoolean(disableConverterAttr)) && !StringUtils.isWhitespace(converterAttr)) {
               TypeManager typeMgr = this.mgr.getNucleusContext().getTypeManager();
               ClassLoaderResolver clr = this.mgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
               Class converterCls = clr.classForName(converterAttr);
               if (typeMgr.getTypeConverterForName(converterCls.getName()) == null) {
                  AttributeConverter conv = (AttributeConverter)ClassUtils.newInstance(converterCls, (Class[])null, (Object[])null);
                  Class attrType = JDOTypeConverterUtils.getAttributeTypeForAttributeConverter(converterCls, (Class)null);
                  Class dbType = JDOTypeConverterUtils.getDatastoreTypeForAttributeConverter(converterCls, attrType, (Class)null);
                  JDOTypeConverter typeConv = new JDOTypeConverter(conv, attrType, dbType);
                  typeMgr.registerConverter(converterAttr, typeConv);
               }

               elemmd.addExtension("type-converter-name", converterAttr);
            }

            fmd.setElementMetaData(elemmd);
            this.pushStack(elemmd);
         } else if (localName.equals("key")) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.getStack();
            KeyMetaData keymd = new KeyMetaData();
            keymd.setTable(this.getAttr(attrs, "table"));
            keymd.setColumnName(this.getAttr(attrs, "column"));
            keymd.setDeleteAction(this.getAttr(attrs, "delete-action"));
            keymd.setUpdateAction(this.getAttr(attrs, "update-action"));
            keymd.setIndexed(IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed")));
            keymd.setUnique(MetaDataUtils.getBooleanForString(this.getAttr(attrs, "unique"), false));
            keymd.setMappedBy(this.getAttr(attrs, "mapped-by"));
            String converterAttr = this.getAttr(attrs, "converter");
            String disableConverterAttr = this.getAttr(attrs, "use-default-conversion");
            if ((disableConverterAttr == null || !Boolean.getBoolean(disableConverterAttr)) && !StringUtils.isWhitespace(converterAttr)) {
               TypeManager typeMgr = this.mgr.getNucleusContext().getTypeManager();
               ClassLoaderResolver clr = this.mgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
               Class converterCls = clr.classForName(converterAttr);
               if (typeMgr.getTypeConverterForName(converterCls.getName()) == null) {
                  AttributeConverter conv = (AttributeConverter)ClassUtils.newInstance(converterCls, (Class[])null, (Object[])null);
                  Class attrType = JDOTypeConverterUtils.getAttributeTypeForAttributeConverter(converterCls, (Class)null);
                  Class dbType = JDOTypeConverterUtils.getDatastoreTypeForAttributeConverter(converterCls, attrType, (Class)null);
                  JDOTypeConverter typeConv = new JDOTypeConverter(conv, attrType, dbType);
                  typeMgr.registerConverter(converterAttr, typeConv);
               }

               keymd.addExtension("type-converter-name", converterAttr);
            }

            fmd.setKeyMetaData(keymd);
            this.pushStack(keymd);
         } else if (localName.equals("value")) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.getStack();
            ValueMetaData valuemd = new ValueMetaData();
            valuemd.setTable(this.getAttr(attrs, "table"));
            valuemd.setColumnName(this.getAttr(attrs, "column"));
            valuemd.setDeleteAction(this.getAttr(attrs, "delete-action"));
            valuemd.setUpdateAction(this.getAttr(attrs, "update-action"));
            valuemd.setIndexed(IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed")));
            valuemd.setUnique(MetaDataUtils.getBooleanForString(this.getAttr(attrs, "unique"), false));
            valuemd.setMappedBy(this.getAttr(attrs, "mapped-by"));
            String converterAttr = this.getAttr(attrs, "converter");
            String disableConverterAttr = this.getAttr(attrs, "use-default-conversion");
            if ((disableConverterAttr == null || !Boolean.getBoolean(disableConverterAttr)) && !StringUtils.isWhitespace(converterAttr)) {
               TypeManager typeMgr = this.mgr.getNucleusContext().getTypeManager();
               ClassLoaderResolver clr = this.mgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
               Class converterCls = clr.classForName(converterAttr);
               if (typeMgr.getTypeConverterForName(converterCls.getName()) == null) {
                  AttributeConverter conv = (AttributeConverter)ClassUtils.newInstance(converterCls, (Class[])null, (Object[])null);
                  Class attrType = JDOTypeConverterUtils.getAttributeTypeForAttributeConverter(converterCls, (Class)null);
                  Class dbType = JDOTypeConverterUtils.getDatastoreTypeForAttributeConverter(converterCls, attrType, (Class)null);
                  JDOTypeConverter typeConv = new JDOTypeConverter(conv, attrType, dbType);
                  typeMgr.registerConverter(converterAttr, typeConv);
               }

               valuemd.addExtension("type-converter-name", converterAttr);
            }

            fmd.setValueMetaData(valuemd);
            this.pushStack(valuemd);
         } else if (localName.equals("fetch-group")) {
            MetaData md = this.getStack();
            FetchGroupMetaData fgmd = new FetchGroupMetaData(this.getAttr(attrs, "name"));
            String postLoadStr = this.getAttr(attrs, "post-load");
            if (!StringUtils.isWhitespace(postLoadStr)) {
               fgmd.setPostLoad(Boolean.valueOf(postLoadStr));
            }

            if (md instanceof FetchGroupMetaData) {
               FetchGroupMetaData fgmdParent = (FetchGroupMetaData)md;
               fgmdParent.addFetchGroup(fgmd);
            } else if (md instanceof AbstractClassMetaData) {
               AbstractClassMetaData cmd = (AbstractClassMetaData)md;
               cmd.addFetchGroup(fgmd);
            } else if (md instanceof FetchPlanMetaData) {
               FetchPlanMetaData fpmd = (FetchPlanMetaData)md;
               fpmd.addFetchGroup(fgmd);
            }

            this.pushStack(fgmd);
         } else if (localName.equals("extension")) {
            MetaData md = this.getStack();
            md.addExtension(this.getAttr(attrs, "vendor-name"), this.getAttr(attrs, "key"), this.getAttr(attrs, "value"));
         } else if (localName.equals("version")) {
            AbstractClassMetaData cmd = (AbstractClassMetaData)this.getStack();
            VersionMetaData vermd = cmd.newVersionMetadata();
            String strategy = this.getAttr(attrs, "strategy");
            if (!StringUtils.isWhitespace(strategy)) {
               vermd.setStrategy(strategy);
            }

            vermd.setColumnName(this.getAttr(attrs, "column"));
            vermd.setIndexed(IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed")));
            this.pushStack(vermd);
         } else if (localName.equals("index")) {
            MetaData md = this.getStack();
            IndexMetaData idxmd = new IndexMetaData();
            idxmd.setName(this.getAttr(attrs, "name"));
            idxmd.setTable(this.getAttr(attrs, "table"));
            String uniStr = this.getAttr(attrs, "unique");
            if (!StringUtils.isWhitespace(uniStr)) {
               idxmd.setUnique(Boolean.valueOf(uniStr));
            }

            if (md instanceof AbstractClassMetaData) {
               AbstractClassMetaData cmd = (AbstractClassMetaData)md;
               cmd.addIndex(idxmd);
            } else if (md instanceof AbstractMemberMetaData) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)md;
               fmd.setIndexMetaData(idxmd);
            } else if (md instanceof JoinMetaData) {
               JoinMetaData jmd = (JoinMetaData)md;
               jmd.setIndexMetaData(idxmd);
            } else if (md instanceof AbstractElementMetaData) {
               AbstractElementMetaData elmd = (AbstractElementMetaData)md;
               elmd.setIndexMetaData(idxmd);
            } else if (md instanceof OrderMetaData) {
               OrderMetaData omd = (OrderMetaData)md;
               omd.setIndexMetaData(idxmd);
            } else if (md instanceof VersionMetaData) {
               VersionMetaData vermd = (VersionMetaData)md;
               vermd.setIndexMetaData(idxmd);
            } else if (md instanceof DiscriminatorMetaData) {
               DiscriminatorMetaData dismd = (DiscriminatorMetaData)md;
               dismd.setIndexMetaData(idxmd);
            }

            this.pushStack(idxmd);
         } else if (localName.equals("unique")) {
            MetaData md = this.getStack();
            UniqueMetaData unimd = new UniqueMetaData();
            unimd.setName(this.getAttr(attrs, "name"));
            unimd.setTable(this.getAttr(attrs, "table"));
            String defStr = this.getAttr(attrs, "deferred");
            if (!StringUtils.isWhitespace(defStr)) {
               unimd.setDeferred(Boolean.valueOf(defStr));
            }

            if (md instanceof AbstractClassMetaData) {
               AbstractClassMetaData cmd = (AbstractClassMetaData)md;
               cmd.addUniqueConstraint(unimd);
            } else if (md instanceof AbstractMemberMetaData) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)md;
               fmd.setUniqueMetaData(unimd);
            } else if (md instanceof JoinMetaData) {
               JoinMetaData jmd = (JoinMetaData)md;
               jmd.setUniqueMetaData(unimd);
            } else if (md instanceof AbstractElementMetaData) {
               AbstractElementMetaData elmd = (AbstractElementMetaData)md;
               elmd.setUniqueMetaData(unimd);
            }

            this.pushStack(unimd);
         } else if (localName.equals("foreign-key")) {
            MetaData md = this.getStack();
            ForeignKeyMetaData fkmd = new ForeignKeyMetaData();
            fkmd.setName(this.getAttr(attrs, "name"));
            fkmd.setTable(this.getAttr(attrs, "table"));
            fkmd.setUnique(this.getAttr(attrs, "unique"));
            fkmd.setDeferred(this.getAttr(attrs, "deferred"));
            fkmd.setDeleteAction(ForeignKeyAction.getForeignKeyAction(this.getAttr(attrs, "delete-action")));
            fkmd.setUpdateAction(ForeignKeyAction.getForeignKeyAction(this.getAttr(attrs, "update-action")));
            if (md instanceof AbstractClassMetaData) {
               AbstractClassMetaData cmd = (AbstractClassMetaData)md;
               cmd.addForeignKey(fkmd);
            } else if (md instanceof AbstractMemberMetaData) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)md;
               fmd.setForeignKeyMetaData(fkmd);
            } else if (md instanceof JoinMetaData) {
               JoinMetaData jmd = (JoinMetaData)md;
               jmd.setForeignKeyMetaData(fkmd);
            } else if (md instanceof AbstractElementMetaData) {
               AbstractElementMetaData elmd = (AbstractElementMetaData)md;
               elmd.setForeignKeyMetaData(fkmd);
            }

            this.pushStack(fkmd);
         } else if (localName.equals("order")) {
            OrderMetaData ordmd = new OrderMetaData();
            ordmd.setIndexed(IndexedValue.getIndexedValue(this.getAttr(attrs, "indexed")));
            ordmd.setColumnName(this.getAttr(attrs, "column"));
            ordmd.setMappedBy(this.getAttr(attrs, "mapped-by"));
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)this.getStack();
            fmd.setOrderMetaData(ordmd);
            this.pushStack(ordmd);
         } else {
            if (!localName.equals("embedded")) {
               String message = Localiser.msg("044037", new Object[]{qName});
               NucleusLogger.METADATA.error(message);
               throw new RuntimeException(message);
            }

            MetaData md = this.getStack();
            EmbeddedMetaData embmd = new EmbeddedMetaData();
            embmd.setOwnerMember(this.getAttr(attrs, "owner-field"));
            embmd.setNullIndicatorColumn(this.getAttr(attrs, "null-indicator-column"));
            embmd.setNullIndicatorValue(this.getAttr(attrs, "null-indicator-value"));
            if (md instanceof AbstractMemberMetaData) {
               AbstractMemberMetaData fmd = (AbstractMemberMetaData)md;
               fmd.setEmbeddedMetaData(embmd);
            } else if (md instanceof KeyMetaData) {
               KeyMetaData kmd = (KeyMetaData)md;
               kmd.setEmbeddedMetaData(embmd);
            } else if (md instanceof ValueMetaData) {
               ValueMetaData vmd = (ValueMetaData)md;
               vmd.setEmbeddedMetaData(embmd);
            } else if (md instanceof ElementMetaData) {
               ElementMetaData elmd = (ElementMetaData)md;
               elmd.setEmbeddedMetaData(embmd);
            }

            this.pushStack(embmd);
         }

      } catch (RuntimeException ex) {
         NucleusLogger.METADATA.error(Localiser.msg("044042", new Object[]{qName, this.getStack(), uri}), ex);
         throw ex;
      }
   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (localName.length() < 1) {
         localName = qName;
      }

      String currentString = this.getString().trim();
      if (currentString.length() > 0) {
         MetaData md = this.getStack();
         if (localName.equals("query")) {
            ((QueryMetaData)md).setQuery(currentString);
         }
      }

      if (localName.equals("package") || localName.equals("fetch-plan") || localName.equals("class") || localName.equals("interface") || localName.equals("implements") || localName.equals("property") || localName.equals("datastore-identity") || localName.equals("inheritance") || localName.equals("primary-key") || localName.equals("version") || localName.equals("unmapped") || localName.equals("query") || localName.equals("sequence") || localName.equals("field") || localName.equals("map") || localName.equals("element") || localName.equals("embedded") || localName.equals("key") || localName.equals("value") || localName.equals("array") || localName.equals("collection") || localName.equals("join") || localName.equals("index") || localName.equals("unique") || localName.equals("foreign-key") || localName.equals("order") || localName.equals("fetch-group") || localName.equals("column") || localName.equals("discriminator")) {
         this.popStack();
      }

   }
}
