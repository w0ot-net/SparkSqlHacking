package org.datanucleus.metadata;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractMemberMetaData extends MetaData implements Comparable, ColumnMetaDataContainer {
   private static final long serialVersionUID = -7689828287704042919L;
   public static final boolean PERSIST_STATIC = false;
   public static final boolean PERSIST_FINAL = false;
   public static final boolean PERSIST_TRANSIENT = false;
   protected ColumnMetaData[] columnMetaData;
   protected ContainerMetaData containerMetaData;
   protected EmbeddedMetaData embeddedMetaData;
   protected JoinMetaData joinMetaData;
   protected ElementMetaData elementMetaData;
   protected KeyMetaData keyMetaData;
   protected ValueMetaData valueMetaData;
   protected IndexMetaData indexMetaData;
   protected IndexedValue indexed = null;
   protected UniqueMetaData uniqueMetaData;
   protected boolean uniqueConstraint = false;
   protected OrderMetaData orderMetaData;
   protected ForeignKeyMetaData foreignKeyMetaData;
   protected Boolean defaultFetchGroup;
   protected String column;
   protected String mappedBy;
   protected Boolean embedded;
   protected Boolean dependent;
   protected Boolean serialized;
   protected boolean cacheable = true;
   protected Boolean cascadePersist;
   protected Boolean cascadeUpdate;
   protected Boolean cascadeDelete;
   protected Boolean cascadeDetach;
   protected Boolean cascadeRefresh;
   protected boolean cascadeRemoveOrphans = false;
   protected String loadFetchGroup;
   public static final int DEFAULT_RECURSION_DEPTH = 1;
   public static final int UNDEFINED_RECURSION_DEPTH = 0;
   protected int recursionDepth = 0;
   protected final String name;
   protected NullValue nullValue;
   protected FieldPersistenceModifier persistenceModifier;
   protected Boolean primaryKey;
   protected String table;
   protected String catalog;
   protected String schema;
   protected IdentityStrategy valueStrategy;
   protected String valueGeneratorName;
   protected String sequence;
   protected String className;
   protected String fullFieldName;
   protected Class type;
   protected Member memberRepresented;
   protected int fieldId;
   protected RelationType relationType;
   protected AbstractMemberMetaData[] relatedMemberMetaData;
   protected boolean ordered;
   protected List columns;
   protected String targetClassName;
   protected boolean storeInLob;
   protected String mapsIdAttribute;
   protected String relationTypeString;
   protected byte persistenceFlags;

   public AbstractMemberMetaData(MetaData parent, AbstractMemberMetaData mmd) {
      super(parent, mmd);
      this.nullValue = NullValue.NONE;
      this.persistenceModifier = FieldPersistenceModifier.DEFAULT;
      this.className = null;
      this.fullFieldName = null;
      this.fieldId = -1;
      this.relationType = null;
      this.relatedMemberMetaData = null;
      this.ordered = false;
      this.columns = new ArrayList();
      this.targetClassName = null;
      this.storeInLob = false;
      this.mapsIdAttribute = null;
      this.relationTypeString = null;
      this.name = mmd.name;
      this.primaryKey = mmd.primaryKey;
      this.defaultFetchGroup = mmd.defaultFetchGroup;
      this.column = mmd.column;
      this.mappedBy = mmd.mappedBy;
      this.dependent = mmd.dependent;
      this.embedded = mmd.embedded;
      this.serialized = mmd.serialized;
      this.cascadePersist = mmd.cascadePersist;
      this.cascadeUpdate = mmd.cascadeUpdate;
      this.cascadeDelete = mmd.cascadeDelete;
      this.cascadeDetach = mmd.cascadeDetach;
      this.cascadeRefresh = mmd.cascadeRefresh;
      this.nullValue = mmd.nullValue;
      this.persistenceModifier = mmd.persistenceModifier;
      this.table = mmd.table;
      this.indexed = mmd.indexed;
      this.valueStrategy = mmd.valueStrategy;
      this.valueGeneratorName = mmd.valueGeneratorName;
      this.sequence = mmd.sequence;
      this.uniqueConstraint = mmd.uniqueConstraint;
      this.loadFetchGroup = mmd.loadFetchGroup;
      this.storeInLob = mmd.storeInLob;
      this.mapsIdAttribute = mmd.mapsIdAttribute;
      this.relationTypeString = mmd.relationTypeString;
      this.column = mmd.column;
      if (mmd.joinMetaData != null) {
         this.setJoinMetaData(new JoinMetaData(mmd.joinMetaData));
      }

      if (mmd.elementMetaData != null) {
         this.setElementMetaData(new ElementMetaData(mmd.elementMetaData));
      }

      if (mmd.keyMetaData != null) {
         this.setKeyMetaData(new KeyMetaData(mmd.keyMetaData));
      }

      if (mmd.valueMetaData != null) {
         this.setValueMetaData(new ValueMetaData(mmd.valueMetaData));
      }

      if (mmd.orderMetaData != null) {
         this.setOrderMetaData(new OrderMetaData(mmd.orderMetaData));
      }

      if (mmd.indexMetaData != null) {
         this.setIndexMetaData(new IndexMetaData(mmd.indexMetaData));
      }

      if (mmd.uniqueMetaData != null) {
         this.setUniqueMetaData(new UniqueMetaData(mmd.uniqueMetaData));
      }

      if (mmd.foreignKeyMetaData != null) {
         this.setForeignKeyMetaData(new ForeignKeyMetaData(mmd.foreignKeyMetaData));
      }

      if (mmd.embeddedMetaData != null) {
         this.setEmbeddedMetaData(new EmbeddedMetaData(mmd.embeddedMetaData));
      }

      if (mmd.containerMetaData != null) {
         if (mmd.containerMetaData instanceof CollectionMetaData) {
            this.setContainer(new CollectionMetaData((CollectionMetaData)mmd.containerMetaData));
         } else if (mmd.containerMetaData instanceof MapMetaData) {
            this.setContainer(new MapMetaData((MapMetaData)mmd.containerMetaData));
         } else if (mmd.containerMetaData instanceof ArrayMetaData) {
            this.setContainer(new ArrayMetaData((ArrayMetaData)mmd.containerMetaData));
         }
      }

      for(int i = 0; i < mmd.columns.size(); ++i) {
         this.addColumn(new ColumnMetaData((ColumnMetaData)mmd.columns.get(i)));
      }

   }

   public AbstractMemberMetaData(MetaData parent, String name) {
      super(parent);
      this.nullValue = NullValue.NONE;
      this.persistenceModifier = FieldPersistenceModifier.DEFAULT;
      this.className = null;
      this.fullFieldName = null;
      this.fieldId = -1;
      this.relationType = null;
      this.relatedMemberMetaData = null;
      this.ordered = false;
      this.columns = new ArrayList();
      this.targetClassName = null;
      this.storeInLob = false;
      this.mapsIdAttribute = null;
      this.relationTypeString = null;
      if (name == null) {
         throw new NucleusUserException(Localiser.msg("044041", "name", this.getClassName(true), "field"));
      } else {
         if (name.indexOf(46) >= 0) {
            this.className = name.substring(0, name.lastIndexOf(46));
            this.name = name.substring(name.lastIndexOf(46) + 1);
         } else {
            this.name = name;
         }

      }
   }

   public synchronized void populate(ClassLoaderResolver clr, Field field, Method method, ClassLoader primary, MetaDataManager mmgr) {
      if (!this.isPopulated() && !this.isInitialised()) {
         if (mmgr != null) {
            ApiAdapter apiAdapter = mmgr.getNucleusContext().getApiAdapter();
            if (this.cascadePersist == null) {
               this.cascadePersist = apiAdapter.getDefaultCascadePersistForField();
            }

            if (this.cascadeUpdate == null) {
               this.cascadeUpdate = apiAdapter.getDefaultCascadeUpdateForField();
            }

            if (this.cascadeDelete == null) {
               this.cascadeDelete = apiAdapter.getDefaultCascadeDeleteForField();
            }

            if (this.cascadeDetach == null) {
               this.cascadeDetach = false;
            }

            if (this.cascadeRefresh == null) {
               this.cascadeRefresh = apiAdapter.getDefaultCascadeRefreshForField();
            }
         }

         if (field == null && method == null) {
            NucleusLogger.METADATA.error(Localiser.msg("044106", this.getClassName(), this.getName()));
            throw new InvalidMemberMetaDataException("044106", new Object[]{this.getClassName(), this.getName()});
         } else {
            if (clr == null) {
               NucleusLogger.METADATA.warn(Localiser.msg("044067", this.name, this.getClassName(true)));
               clr = mmgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
            }

            this.memberRepresented = (Member)(field != null ? field : method);
            if (this.type == null) {
               if (field != null) {
                  this.type = field.getType();
               } else if (method != null) {
                  this.type = method.getReturnType();
               }
            }

            if (this.className != null) {
               Class thisClass = null;
               if (this.parent instanceof EmbeddedMetaData) {
                  MetaData superMd = this.parent.getParent();
                  thisClass = ((AbstractMemberMetaData)superMd).getType();
               } else {
                  try {
                     thisClass = clr.classForName(this.getAbstractClassMetaData().getPackageName() + "." + this.getAbstractClassMetaData().getName());
                  } catch (ClassNotResolvedException var19) {
                  }
               }

               Class fieldClass = null;

               try {
                  fieldClass = clr.classForName(this.className);
               } catch (ClassNotResolvedException cnre) {
                  try {
                     fieldClass = clr.classForName(this.getAbstractClassMetaData().getPackageName() + "." + this.className);
                     this.className = this.getAbstractClassMetaData().getPackageName() + "." + this.className;
                  } catch (ClassNotResolvedException var17) {
                     NucleusLogger.METADATA.error(Localiser.msg("044113", this.getClassName(), this.getName(), this.className));
                     NucleusException ne = new InvalidMemberMetaDataException("044113", new Object[]{this.getClassName(), this.getName(), this.className});
                     ne.setNestedException(cnre);
                     throw ne;
                  }
               }

               if (fieldClass != null && !fieldClass.isAssignableFrom(thisClass)) {
                  NucleusLogger.METADATA.error(Localiser.msg("044114", this.getClassName(), this.getName(), this.className));
                  throw new InvalidMemberMetaDataException("044114", new Object[]{this.getClassName(), this.getName(), this.className});
               }
            }

            if (this.primaryKey == null) {
               this.primaryKey = Boolean.FALSE;
            }

            if (this.primaryKey == Boolean.FALSE && this.embedded == null) {
               Class element_type = this.getType();
               if (element_type.isArray()) {
                  element_type = element_type.getComponentType();
                  if (mmgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(element_type)) {
                     this.embedded = Boolean.TRUE;
                  }
               } else if (mmgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(element_type)) {
                  this.embedded = Boolean.TRUE;
               }
            }

            if (this.embedded == null) {
               this.embedded = Boolean.FALSE;
            }

            if (FieldPersistenceModifier.DEFAULT.equals(this.persistenceModifier)) {
               boolean isPcClass = this.getType().isArray() ? this.isFieldArrayTypePersistable(mmgr) : mmgr.isFieldTypePersistable(this.type);
               if (!isPcClass) {
                  if (this.getType().isArray() && this.getType().getComponentType().isInterface()) {
                     isPcClass = mmgr.getMetaDataForClassInternal(this.getType().getComponentType(), clr) != null;
                  } else if (this.getType().isInterface()) {
                     isPcClass = mmgr.getMetaDataForClassInternal(this.getType(), clr) != null;
                  }
               }

               this.persistenceModifier = this.getDefaultFieldPersistenceModifier(this.getType(), this.memberRepresented.getModifiers(), isPcClass, mmgr);
            }

            if (this.defaultFetchGroup == null && this.persistenceModifier.equals(FieldPersistenceModifier.NONE)) {
               this.defaultFetchGroup = Boolean.FALSE;
            } else if (this.defaultFetchGroup == null && this.persistenceModifier.equals(FieldPersistenceModifier.TRANSACTIONAL)) {
               this.defaultFetchGroup = Boolean.FALSE;
            } else if (this.defaultFetchGroup == null) {
               this.defaultFetchGroup = Boolean.FALSE;
               if (!this.primaryKey.equals(Boolean.TRUE)) {
                  boolean foundGeneric = false;
                  TypeManager typeMgr = mmgr.getNucleusContext().getTypeManager();
                  if (Collection.class.isAssignableFrom(this.getType())) {
                     String elementTypeName = null;

                     try {
                        if (field != null) {
                           elementTypeName = ClassUtils.getCollectionElementType(field);
                        } else {
                           elementTypeName = ClassUtils.getCollectionElementType(method);
                        }
                     } catch (NucleusUserException var16) {
                     }

                     if (elementTypeName != null) {
                        Class elementType = clr.classForName(elementTypeName);
                        if (typeMgr.isDefaultFetchGroupForCollection(this.getType(), elementType)) {
                           foundGeneric = true;
                           this.defaultFetchGroup = Boolean.TRUE;
                        }
                     }
                  }

                  if (!foundGeneric && typeMgr.isDefaultFetchGroup(this.getType())) {
                     this.defaultFetchGroup = Boolean.TRUE;
                  }
               }
            }

            if (!this.persistenceModifier.equals(FieldPersistenceModifier.TRANSACTIONAL) && !this.persistenceModifier.equals(FieldPersistenceModifier.NONE) || this.defaultFetchGroup != Boolean.TRUE && this.primaryKey != Boolean.TRUE) {
               if (this.storeInLob) {
                  boolean useClob = false;
                  if (this.type == String.class || this.type.isArray() && this.type.getComponentType() == Character.class || this.type.isArray() && this.type.getComponentType() == Character.TYPE) {
                     useClob = true;
                     if (this.columns != null && this.columns.size() != 0) {
                        ColumnMetaData colmd = (ColumnMetaData)this.columns.get(0);
                        colmd.setJdbcType("CLOB");
                     } else {
                        ColumnMetaData colmd = new ColumnMetaData();
                        colmd.setName(this.column);
                        colmd.setJdbcType("CLOB");
                        this.addColumn(colmd);
                     }
                  }

                  if (!useClob) {
                     this.serialized = Boolean.TRUE;
                  }
               }

               if (this.containerMetaData == null) {
                  if (this.type.isArray()) {
                     Class arrayCls = this.type.getComponentType();
                     ArrayMetaData arrmd = new ArrayMetaData();
                     arrmd.setElementType(arrayCls.getName());
                     this.setContainer(arrmd);
                  } else if (Collection.class.isAssignableFrom(this.type)) {
                     if (this.targetClassName != null) {
                        CollectionMetaData collmd = new CollectionMetaData();
                        collmd.setElementType(this.targetClassName);
                        this.setContainer(collmd);
                     } else {
                        String elementType = null;
                        if (field != null) {
                           elementType = ClassUtils.getCollectionElementType(field);
                        } else {
                           elementType = ClassUtils.getCollectionElementType(method);
                        }

                        if (elementType != null) {
                           CollectionMetaData collmd = new CollectionMetaData();
                           collmd.setElementType(elementType);
                           this.setContainer(collmd);
                        } else {
                           CollectionMetaData collmd = new CollectionMetaData();
                           collmd.setElementType(Object.class.getName());
                           this.setContainer(collmd);
                           NucleusLogger.METADATA.debug(Localiser.msg("044003", this.getClassName(), this.getName()));
                        }
                     }
                  } else if (Map.class.isAssignableFrom(this.type)) {
                     if (this.targetClassName != null) {
                        MapMetaData mapmd = new MapMetaData();
                        mapmd.setValueType(this.targetClassName);
                        this.setContainer(mapmd);
                     } else {
                        String keyType = null;
                        String valueType = null;
                        if (field != null) {
                           keyType = ClassUtils.getMapKeyType(field);
                        } else {
                           keyType = ClassUtils.getMapKeyType(method);
                        }

                        if (field != null) {
                           valueType = ClassUtils.getMapValueType(field);
                        } else {
                           valueType = ClassUtils.getMapValueType(method);
                        }

                        if (keyType != null && valueType != null) {
                           MapMetaData mapmd = new MapMetaData();
                           mapmd.setKeyType(keyType);
                           mapmd.setValueType(valueType);
                           this.setContainer(mapmd);
                        } else {
                           if (keyType == null) {
                              keyType = Object.class.getName();
                           }

                           if (valueType == null) {
                              valueType = Object.class.getName();
                           }

                           MapMetaData mapmd = new MapMetaData();
                           mapmd.setKeyType(keyType);
                           mapmd.setValueType(valueType);
                           this.setContainer(mapmd);
                           NucleusLogger.METADATA.debug(Localiser.msg("044004", this.getClassName(), this.getName()));
                        }
                     }
                  }
               } else if (this.type.isArray()) {
                  if (this.getArray().element.type == null) {
                     Class arrayCls = this.type.getComponentType();
                     this.getArray().setElementType(arrayCls.getName());
                  }
               } else if (Collection.class.isAssignableFrom(this.type)) {
                  String elementType = null;
                  if (field != null) {
                     elementType = ClassUtils.getCollectionElementType(field);
                  } else {
                     elementType = ClassUtils.getCollectionElementType(method);
                  }

                  if (elementType == null) {
                     Type genericType = null;
                     if (field != null) {
                        genericType = field.getGenericType();
                     } else if (method != null) {
                        genericType = method.getGenericReturnType();
                     }

                     if (genericType != null && genericType instanceof ParameterizedType) {
                        ParameterizedType paramGenType = (ParameterizedType)genericType;
                        Type elemGenericType = paramGenType.getActualTypeArguments()[0];
                        if (elemGenericType instanceof TypeVariable) {
                           Type elemGenTypeBound = ((TypeVariable)elemGenericType).getBounds()[0];
                           if (elemGenTypeBound instanceof Class) {
                              elementType = ((Class)elemGenTypeBound).getName();
                           } else if (elemGenTypeBound instanceof ParameterizedType) {
                              ParameterizedType paramElemGenType = (ParameterizedType)elemGenTypeBound;
                              Type paramElemGenTypeRaw = paramElemGenType.getRawType();
                              if (paramElemGenTypeRaw != null && paramElemGenTypeRaw instanceof Class) {
                                 elementType = ((Class)paramElemGenTypeRaw).getName();
                              }
                           }
                        }
                     }
                  }

                  if (elementType != null && (this.getCollection().element.type == null || this.getCollection().element.type.equals(ClassNameConstants.Object))) {
                     this.getCollection().element.type = elementType;
                  }
               } else if (Map.class.isAssignableFrom(this.type)) {
                  String keyType = null;
                  String valueType = null;
                  if (field != null) {
                     keyType = ClassUtils.getMapKeyType(field);
                  } else {
                     keyType = ClassUtils.getMapKeyType(method);
                  }

                  if (field != null) {
                     valueType = ClassUtils.getMapValueType(field);
                  } else {
                     valueType = ClassUtils.getMapValueType(method);
                  }

                  if (keyType == null || valueType == null) {
                     Type genericType = null;
                     if (field != null) {
                        genericType = field.getGenericType();
                     } else if (method != null) {
                        genericType = method.getGenericReturnType();
                     }

                     if (genericType != null && genericType instanceof ParameterizedType) {
                        ParameterizedType paramGenType = (ParameterizedType)genericType;
                        if (keyType == null) {
                           Type keyGenericType = paramGenType.getActualTypeArguments()[0];
                           if (keyGenericType instanceof TypeVariable) {
                              Type keyGenTypeBound = ((TypeVariable)keyGenericType).getBounds()[0];
                              if (keyGenTypeBound instanceof Class) {
                                 keyType = ((Class)keyGenTypeBound).getName();
                              } else if (keyGenTypeBound instanceof ParameterizedType) {
                                 ParameterizedType paramKeyGenType = (ParameterizedType)keyGenTypeBound;
                                 Type paramKeyGenTypeRaw = paramKeyGenType.getRawType();
                                 if (paramKeyGenTypeRaw != null && paramKeyGenTypeRaw instanceof Class) {
                                    keyType = ((Class)paramKeyGenTypeRaw).getName();
                                 }
                              }
                           }
                        }

                        if (valueType == null) {
                           Type valueGenericType = paramGenType.getActualTypeArguments()[1];
                           if (valueGenericType instanceof TypeVariable) {
                              Type valueGenTypeBound = ((TypeVariable)valueGenericType).getBounds()[0];
                              if (valueGenTypeBound instanceof Class) {
                                 valueType = ((Class)valueGenTypeBound).getName();
                              } else if (valueGenTypeBound instanceof ParameterizedType) {
                                 ParameterizedType paramValGenType = (ParameterizedType)valueGenTypeBound;
                                 Type paramValGenTypeRaw = paramValGenType.getRawType();
                                 if (paramValGenTypeRaw != null && paramValGenTypeRaw instanceof Class) {
                                    valueType = ((Class)paramValGenTypeRaw).getName();
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (keyType != null && valueType != null) {
                     if (this.getMap().key.type == null || this.getMap().key.type.equals(ClassNameConstants.Object)) {
                        this.getMap().key.type = keyType;
                     }

                     if (this.getMap().value.type == null || this.getMap().value.type.equals(ClassNameConstants.Object)) {
                        this.getMap().value.type = valueType;
                     }
                  }
               }

               if (this.hasCollection() && this.ordered && this.orderMetaData == null) {
                  OrderMetaData ordmd = new OrderMetaData();
                  ordmd.setOrdering("#PK");
                  this.setOrderMetaData(ordmd);
               }

               if (!this.isSerialized() && !this.isEmbedded() && this.getTypeConverterName() == null && this.columnMetaData != null) {
                  if ((this.hasCollection() || this.hasArray()) && this.elementMetaData == null) {
                     ElementMetaData elemmd = new ElementMetaData();
                     this.setElementMetaData(elemmd);

                     for(int i = 0; i < this.columnMetaData.length; ++i) {
                        elemmd.addColumn(this.columnMetaData[i]);
                     }

                     this.columnMetaData = null;
                     this.columns.clear();
                     this.column = null;
                  } else if (this.hasMap() && this.valueMetaData == null) {
                     ValueMetaData valmd = new ValueMetaData();
                     this.setValueMetaData(valmd);

                     for(int i = 0; i < this.columnMetaData.length; ++i) {
                        valmd.addColumn(this.columnMetaData[i]);
                     }

                     this.columnMetaData = null;
                     this.columns.clear();
                     this.column = null;
                  }
               }

               if (this.containerMetaData != null && this.dependent != null) {
                  NucleusLogger.METADATA.error(Localiser.msg("044110", this.getClassName(), this.getName(), ((ClassMetaData)this.parent).getName()));
                  throw new InvalidMemberMetaDataException("044110", new Object[]{this.getClassName(), this.getName(), ((ClassMetaData)this.parent).getName()});
               } else {
                  if (this.elementMetaData != null) {
                     this.elementMetaData.populate(clr, primary, mmgr);
                  }

                  if (this.keyMetaData != null) {
                     this.keyMetaData.populate(clr, primary, mmgr);
                  }

                  if (this.valueMetaData != null) {
                     this.valueMetaData.populate(clr, primary, mmgr);
                  }

                  if (this.embedded == Boolean.TRUE && this.embeddedMetaData == null) {
                     AbstractClassMetaData memberCmd = mmgr.getMetaDataForClassInternal(this.getType(), clr);
                     if (memberCmd != null) {
                        this.embeddedMetaData = new EmbeddedMetaData();
                        this.embeddedMetaData.setParent(this);
                     }
                  }

                  if (this.embeddedMetaData != null) {
                     if (this.hasExtension("null-indicator-column")) {
                        this.embeddedMetaData.setNullIndicatorColumn(this.getValueForExtension("null-indicator-column"));
                        if (this.hasExtension("null-indicator-value")) {
                           this.embeddedMetaData.setNullIndicatorValue(this.getValueForExtension("null-indicator-value"));
                        }
                     }

                     this.embeddedMetaData.populate(clr, primary, mmgr);
                     this.embedded = Boolean.TRUE;
                  }

                  if (this.elementMetaData != null && this.elementMetaData.mappedBy != null && this.mappedBy == null) {
                     this.mappedBy = this.elementMetaData.mappedBy;
                  }

                  if (this.containerMetaData != null && this.persistenceModifier == FieldPersistenceModifier.PERSISTENT) {
                     if (this.containerMetaData instanceof CollectionMetaData) {
                        if (this.cascadeDelete) {
                           this.getCollection().element.dependent = Boolean.TRUE;
                        }

                        this.getCollection().populate(clr, primary, mmgr);
                     } else if (this.containerMetaData instanceof MapMetaData) {
                        String keyCascadeVal = this.getValueForExtension("cascade-delete-key");
                        if (this.cascadeDelete) {
                           this.getMap().key.dependent = Boolean.FALSE;
                           this.getMap().value.dependent = Boolean.TRUE;
                        }

                        if (keyCascadeVal != null) {
                           if (keyCascadeVal.equalsIgnoreCase("true")) {
                              this.getMap().key.dependent = Boolean.TRUE;
                           } else {
                              this.getMap().key.dependent = Boolean.FALSE;
                           }
                        }

                        this.getMap().populate(clr, primary, mmgr);
                     } else if (this.containerMetaData instanceof ArrayMetaData) {
                        if (this.cascadeDelete) {
                           this.getArray().element.dependent = Boolean.TRUE;
                        }

                        this.getArray().populate(clr, primary, mmgr);
                     }
                  }

                  if (mmgr.isFieldTypePersistable(this.type) && this.cascadeDelete) {
                     this.setDependent(true);
                  }

                  if (this.hasExtension("implementation-classes")) {
                     StringBuilder str = new StringBuilder();
                     String[] implTypes = this.getValuesForExtension("implementation-classes");

                     for(int i = 0; i < implTypes.length; ++i) {
                        String implTypeName = ClassUtils.createFullClassName(this.getAbstractClassMetaData().getPackageName(), implTypes[i]);
                        if (i > 0) {
                           str.append(",");
                        }

                        try {
                           clr.classForName(implTypeName);
                           str.append(implTypeName);
                        } catch (ClassNotResolvedException var15) {
                           try {
                              String langClassName = ClassUtils.getJavaLangClassForType(implTypeName);
                              clr.classForName(langClassName);
                              str.append(langClassName);
                           } catch (ClassNotResolvedException var14) {
                              throw new InvalidMemberMetaDataException("044116", new Object[]{this.getClassName(), this.getName(), implTypes[i]});
                           }
                        }
                     }

                     this.addExtension("datanucleus", "implementation-classes", str.toString());
                  }

                  byte serializable = 0;
                  if (Serializable.class.isAssignableFrom(this.getType()) || this.getType().isPrimitive()) {
                     serializable = 16;
                  }

                  if (FieldPersistenceModifier.NONE.equals(this.persistenceModifier)) {
                     this.persistenceFlags = 0;
                  } else if (FieldPersistenceModifier.TRANSACTIONAL.equals(this.persistenceModifier) && Modifier.isTransient(this.memberRepresented.getModifiers())) {
                     this.persistenceFlags = (byte)(4 | serializable);
                  } else if (this.primaryKey) {
                     this.persistenceFlags = (byte)(8 | serializable);
                  } else if (this.defaultFetchGroup) {
                     this.persistenceFlags = (byte)(5 | serializable);
                  } else if (!this.defaultFetchGroup) {
                     this.persistenceFlags = (byte)(10 | serializable);
                  } else {
                     this.persistenceFlags = 0;
                  }

                  if (this.persistenceModifier != FieldPersistenceModifier.PERSISTENT) {
                     this.relationType = RelationType.NONE;
                  } else if (this.containerMetaData == null && !mmgr.isFieldTypePersistable(this.type) && !this.type.getName().equals(ClassNameConstants.Object) && !this.type.isInterface()) {
                     this.relationType = RelationType.NONE;
                  }

                  this.setPopulated();
               }
            } else {
               throw new InvalidMemberMetaDataException("044109", new Object[]{this.getClassName(), this.name, this.getType().getName(), this.persistenceModifier.toString()});
            }
         }
      }
   }

   public String getPackageName() {
      return this.className.substring(0, this.className.lastIndexOf(46));
   }

   public final FieldPersistenceModifier getDefaultFieldPersistenceModifier(Class c, int modifier, boolean isPCclass, MetaDataManager mmgr) {
      if (Modifier.isFinal(modifier) && this instanceof FieldMetaData) {
         return FieldPersistenceModifier.NONE;
      } else if (Modifier.isStatic(modifier)) {
         return FieldPersistenceModifier.NONE;
      } else if (Modifier.isTransient(modifier)) {
         return FieldPersistenceModifier.NONE;
      } else if (isPCclass) {
         return FieldPersistenceModifier.PERSISTENT;
      } else if (c == null) {
         throw new NucleusException("class is null");
      } else if (c.isArray() && mmgr.getNucleusContext().getApiAdapter().isPersistable(c.getComponentType())) {
         return FieldPersistenceModifier.PERSISTENT;
      } else {
         return mmgr.getNucleusContext().getTypeManager().isDefaultPersistent(c) ? FieldPersistenceModifier.PERSISTENT : FieldPersistenceModifier.NONE;
      }
   }

   public synchronized void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.persistenceModifier == FieldPersistenceModifier.NONE) {
         this.setInitialised();
      } else {
         if (this.columns.size() == 0 && this.column != null) {
            this.columnMetaData = new ColumnMetaData[1];
            this.columnMetaData[0] = new ColumnMetaData();
            this.columnMetaData[0].setName(this.column);
            this.columnMetaData[0].parent = this;
            this.columnMetaData[0].initialise(clr, mmgr);
         } else if (this.columns.size() == 1 && this.column != null) {
            this.columnMetaData = new ColumnMetaData[1];
            this.columnMetaData[0] = (ColumnMetaData)this.columns.get(0);
            if (this.columnMetaData[0].getName() == null) {
               this.columnMetaData[0].setName(this.column);
            }

            this.columnMetaData[0].initialise(clr, mmgr);
         } else {
            this.columnMetaData = new ColumnMetaData[this.columns.size()];

            for(int i = 0; i < this.columnMetaData.length; ++i) {
               this.columnMetaData[i] = (ColumnMetaData)this.columns.get(i);
               this.columnMetaData[i].initialise(clr, mmgr);
            }
         }

         if (this.containerMetaData != null) {
            this.containerMetaData.initialise(clr, mmgr);
            if (this.containerMetaData instanceof CollectionMetaData) {
               CollectionMetaData collmd = (CollectionMetaData)this.containerMetaData;
               if (collmd.element.classMetaData != null && collmd.element.classMetaData.isEmbeddedOnly()) {
                  if (this.elementMetaData == null) {
                     this.elementMetaData = new ElementMetaData();
                     this.elementMetaData.parent = this;
                     this.elementMetaData.populate(clr, (ClassLoader)null, mmgr);
                  }

                  if (this.elementMetaData.getEmbeddedMetaData() == null) {
                     EmbeddedMetaData elemEmbmd = new EmbeddedMetaData();
                     elemEmbmd.parent = this.elementMetaData;
                     elemEmbmd.populate(clr, (ClassLoader)null, mmgr);
                     this.elementMetaData.setEmbeddedMetaData(elemEmbmd);
                     collmd.element.embedded = Boolean.TRUE;
                  }
               }
            } else if (this.containerMetaData instanceof MapMetaData) {
               MapMetaData mapmd = (MapMetaData)this.containerMetaData;
               if (mapmd.key.classMetaData != null && mapmd.key.classMetaData.isEmbeddedOnly()) {
                  if (this.keyMetaData == null) {
                     this.keyMetaData = new KeyMetaData();
                     this.keyMetaData.parent = this;
                     this.keyMetaData.populate(clr, (ClassLoader)null, mmgr);
                  }

                  if (this.keyMetaData.getEmbeddedMetaData() == null) {
                     EmbeddedMetaData keyEmbmd = new EmbeddedMetaData();
                     keyEmbmd.parent = this.keyMetaData;
                     keyEmbmd.populate(clr, (ClassLoader)null, mmgr);
                     this.keyMetaData.setEmbeddedMetaData(keyEmbmd);
                     mapmd.key.embedded = Boolean.TRUE;
                  }
               }

               if (mapmd.value.classMetaData != null && mapmd.value.classMetaData.isEmbeddedOnly()) {
                  if (this.valueMetaData == null) {
                     this.valueMetaData = new ValueMetaData();
                     this.valueMetaData.parent = this;
                     this.valueMetaData.populate(clr, (ClassLoader)null, mmgr);
                  }

                  if (this.valueMetaData.getEmbeddedMetaData() == null) {
                     EmbeddedMetaData valueEmbmd = new EmbeddedMetaData();
                     valueEmbmd.parent = this.valueMetaData;
                     valueEmbmd.populate(clr, (ClassLoader)null, mmgr);
                     this.valueMetaData.setEmbeddedMetaData(valueEmbmd);
                     mapmd.value.embedded = Boolean.TRUE;
                  }
               }
            }
         }

         if (this.embeddedMetaData != null) {
            this.embeddedMetaData.initialise(clr, mmgr);
         }

         if (this.joinMetaData != null) {
            this.joinMetaData.initialise(clr, mmgr);
         }

         if (this.elementMetaData != null) {
            this.elementMetaData.initialise(clr, mmgr);
         }

         if (this.keyMetaData != null) {
            this.keyMetaData.initialise(clr, mmgr);
         }

         if (this.valueMetaData != null) {
            this.valueMetaData.initialise(clr, mmgr);
         }

         if (this.indexMetaData == null && this.columnMetaData != null && this.indexed != null && this.indexed != IndexedValue.FALSE) {
            this.indexMetaData = new IndexMetaData();
            this.indexMetaData.setUnique(this.indexed == IndexedValue.UNIQUE);

            for(int i = 0; i < this.columnMetaData.length; ++i) {
               this.indexMetaData.addColumn(this.columnMetaData[i].getName());
            }
         } else if (this.indexed == IndexedValue.TRUE && this.indexMetaData != null) {
            this.indexMetaData = null;
         }

         if (this.uniqueMetaData == null && this.uniqueConstraint) {
            this.uniqueMetaData = new UniqueMetaData();
            this.uniqueMetaData.setTable(this.column);

            for(int i = 0; i < this.columnMetaData.length; ++i) {
               this.uniqueMetaData.addColumn(this.columnMetaData[i].getName());
            }
         }

         if (this.orderMetaData != null) {
            this.orderMetaData.initialise(clr, mmgr);
         }

         if (this.hasExtension("cascade-persist")) {
            String cascadeValue = this.getValueForExtension("cascade-persist");
            if (cascadeValue.equalsIgnoreCase("true")) {
               this.cascadePersist = true;
            } else if (cascadeValue.equalsIgnoreCase("false")) {
               this.cascadePersist = false;
            }
         }

         if (this.hasExtension("cascade-update")) {
            String cascadeValue = this.getValueForExtension("cascade-update");
            if (cascadeValue.equalsIgnoreCase("true")) {
               this.cascadeUpdate = true;
            } else if (cascadeValue.equalsIgnoreCase("false")) {
               this.cascadeUpdate = false;
            }
         }

         if (this.hasExtension("cascade-refresh")) {
            String cascadeValue = this.getValueForExtension("cascade-refresh");
            if (cascadeValue.equalsIgnoreCase("true")) {
               this.cascadeRefresh = true;
            } else if (cascadeValue.equalsIgnoreCase("false")) {
               this.cascadeRefresh = false;
            }
         }

         this.setInitialised();
      }
   }

   public boolean isFieldArrayTypePersistable(MetaDataManager mmgr) {
      if (!this.type.isArray()) {
         return false;
      } else {
         if (mmgr.isEnhancing()) {
            AbstractClassMetaData cmd = mmgr.readMetaDataForClass(this.type.getComponentType().getName());
            if (cmd != null && cmd instanceof ClassMetaData && cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
               return true;
            }
         }

         return mmgr.getNucleusContext().getApiAdapter().isPersistable(this.type.getComponentType());
      }
   }

   public boolean isStatic() {
      return this.isPopulated() && this.memberRepresented != null ? Modifier.isStatic(this.memberRepresented.getModifiers()) : false;
   }

   public boolean isFinal() {
      return this.isPopulated() && this.memberRepresented != null ? Modifier.isFinal(this.memberRepresented.getModifiers()) : false;
   }

   public boolean isTransient() {
      return this.isPopulated() && this.memberRepresented != null ? Modifier.isTransient(this.memberRepresented.getModifiers()) : false;
   }

   public boolean isPublic() {
      return this.isPopulated() && this.memberRepresented != null ? Modifier.isPublic(this.memberRepresented.getModifiers()) : false;
   }

   public boolean isProtected() {
      return this.isPopulated() && this.memberRepresented != null ? Modifier.isProtected(this.memberRepresented.getModifiers()) : false;
   }

   public boolean isPrivate() {
      return this.isPopulated() && this.memberRepresented != null ? Modifier.isPrivate(this.memberRepresented.getModifiers()) : false;
   }

   public boolean isAbstract() {
      return this.isPopulated() && this.memberRepresented != null ? Modifier.isAbstract(this.memberRepresented.getModifiers()) : false;
   }

   public IdentityStrategy getValueStrategy() {
      return this.valueStrategy;
   }

   public void setValueStrategy(IdentityStrategy valueStrategy) {
      this.valueStrategy = valueStrategy;
   }

   public void setValueStrategy(String strategy) {
      this.valueStrategy = strategy == null ? null : IdentityStrategy.getIdentityStrategy(strategy);
   }

   public String getValueGeneratorName() {
      return this.valueGeneratorName;
   }

   public String getSequence() {
      return this.sequence;
   }

   public void setSequence(String sequence) {
      this.sequence = StringUtils.isWhitespace(sequence) ? null : sequence;
   }

   public boolean isCacheable() {
      if (this.hasExtension("cacheable")) {
         return !this.getValueForExtension("cacheable").equalsIgnoreCase("false");
      } else {
         return this.cacheable;
      }
   }

   public void setCacheable(boolean cache) {
      this.cacheable = cache;
   }

   public String getLoadFetchGroup() {
      return this.loadFetchGroup;
   }

   public void setLoadFetchGroup(String loadFetchGroup) {
      this.loadFetchGroup = loadFetchGroup;
   }

   public String getTypeConverterName() {
      return this.hasExtension("type-converter-name") ? this.getValueForExtension("type-converter-name") : null;
   }

   public void setTypeConverterName(String name) {
      this.addExtension("type-converter-name", name);
   }

   public boolean isTypeConversionDisabled() {
      return this.hasExtension("type-converter-disabled");
   }

   public void setTypeConverterDisabled() {
      this.addExtension("type-converter-disabled", "true");
   }

   public int getRecursionDepth() {
      return this.recursionDepth;
   }

   public void setRecursionDepth(int depth) {
      this.recursionDepth = depth;
   }

   public void setRecursionDepth(String depth) {
      if (!StringUtils.isWhitespace(depth)) {
         try {
            this.recursionDepth = Integer.parseInt(depth);
         } catch (NumberFormatException var3) {
         }
      }

   }

   public boolean fetchFKOnly() {
      return this.hasExtension("fetch-fk-only") ? Boolean.valueOf(this.getValueForExtension("fetch-fk-only")) : false;
   }

   protected static MetaData getOverallParentClassMetaData(MetaData metadata) {
      if (metadata == null) {
         return null;
      } else {
         return metadata instanceof AbstractClassMetaData ? metadata : getOverallParentClassMetaData(metadata.getParent());
      }
   }

   public AbstractClassMetaData getAbstractClassMetaData() {
      if (this.parent == null) {
         return null;
      } else if (this.parent instanceof AbstractClassMetaData) {
         return (AbstractClassMetaData)this.parent;
      } else {
         return this.parent instanceof EmbeddedMetaData ? (AbstractClassMetaData)getOverallParentClassMetaData(this.parent.getParent().getParent()) : null;
      }
   }

   public final OrderMetaData getOrderMetaData() {
      return this.orderMetaData;
   }

   public String getName() {
      return this.name;
   }

   public String getFullFieldName() {
      if (this.fullFieldName == null) {
         if (this.className != null) {
            this.fullFieldName = this.className + "." + this.name;
         } else {
            this.fullFieldName = this.getClassName(true) + "." + this.name;
         }
      }

      return this.fullFieldName;
   }

   public boolean fieldBelongsToClass() {
      return this.className == null;
   }

   public String getClassName() {
      return this.getClassName(true);
   }

   void setClassName(String className) {
      this.className = className;
   }

   public String getClassName(boolean fully_qualified) {
      if (this.className != null) {
         return this.className;
      } else if (this.parent == null) {
         return null;
      } else if (this.parent instanceof AbstractClassMetaData) {
         AbstractClassMetaData cmd = (AbstractClassMetaData)this.parent;
         return fully_qualified ? cmd.getFullClassName() : cmd.getName();
      } else if (this.parent instanceof EmbeddedMetaData) {
         MetaData parentMd = ((EmbeddedMetaData)this.parent).getParent();
         String typeName = null;
         if (parentMd instanceof AbstractMemberMetaData) {
            typeName = ((AbstractMemberMetaData)parentMd).getTypeName();
         } else if (parentMd instanceof ElementMetaData) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)((ElementMetaData)parentMd).getParent();
            typeName = fmd.getCollection().getElementType();
         } else if (parentMd instanceof KeyMetaData) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)((KeyMetaData)parentMd).getParent();
            typeName = fmd.getMap().getKeyType();
         } else {
            if (!(parentMd instanceof ValueMetaData)) {
               return null;
            }

            AbstractMemberMetaData fmd = (AbstractMemberMetaData)((ValueMetaData)parentMd).getParent();
            typeName = fmd.getMap().getValueType();
         }

         return !fully_qualified && typeName.indexOf(46) > 0 ? typeName.substring(typeName.lastIndexOf(46) + 1) : typeName;
      } else {
         if (this.parent instanceof UniqueMetaData) {
            MetaData grandparent = ((UniqueMetaData)this.parent).getParent();
            if (grandparent instanceof AbstractClassMetaData) {
               String fullClassName = ((AbstractClassMetaData)grandparent).getFullClassName();
               if (!fully_qualified && fullClassName.indexOf(46) > 0) {
                  return fullClassName.substring(fullClassName.lastIndexOf(46) + 1);
               }

               return fullClassName;
            }
         }

         return null;
      }
   }

   public FieldPersistenceModifier getPersistenceModifier() {
      return this.persistenceModifier;
   }

   public void setPersistenceModifier(FieldPersistenceModifier modifier) {
      this.persistenceModifier = modifier;
   }

   public void setNotPersistent() {
      this.persistenceModifier = FieldPersistenceModifier.NONE;
   }

   public void setTransactional() {
      this.persistenceModifier = FieldPersistenceModifier.TRANSACTIONAL;
   }

   public boolean isDefaultFetchGroup() {
      return this.defaultFetchGroup == null ? false : this.defaultFetchGroup;
   }

   public void setDefaultFetchGroup(boolean dfg) {
      this.defaultFetchGroup = dfg;
   }

   public boolean isDependent() {
      return this.dependent == null ? false : this.dependent;
   }

   public void setDependent(boolean dependent) {
      this.dependent = dependent;
   }

   public boolean isEmbedded() {
      return this.embedded == null ? false : this.embedded;
   }

   public void setEmbedded(boolean val) {
      this.embedded = val;
   }

   public boolean isSerialized() {
      return this.serialized == null ? false : this.serialized;
   }

   public void setSerialised(boolean flag) {
      this.serialized = flag;
   }

   public String getMapsIdAttribute() {
      return this.mapsIdAttribute;
   }

   public boolean isCascadePersist() {
      return this.cascadePersist;
   }

   public boolean isCascadeUpdate() {
      return this.cascadeUpdate;
   }

   public boolean isCascadeDelete() {
      return this.cascadeDelete;
   }

   public boolean isCascadeDetach() {
      return this.cascadeDetach;
   }

   public boolean isCascadeRefresh() {
      return this.cascadeRefresh;
   }

   public boolean isCascadeRemoveOrphans() {
      return this.cascadeRemoveOrphans;
   }

   public boolean isPrimaryKey() {
      return this.primaryKey == null ? false : this.primaryKey;
   }

   public AbstractMemberMetaData setPrimaryKey(boolean flag) {
      this.primaryKey = flag;
      if (this.primaryKey) {
         this.defaultFetchGroup = Boolean.TRUE;
      }

      return this;
   }

   public AbstractMemberMetaData setColumn(String col) {
      this.column = StringUtils.isWhitespace(col) ? null : col;
      return this;
   }

   public String getTable() {
      return this.table;
   }

   public AbstractMemberMetaData setTable(String table) {
      this.table = StringUtils.isWhitespace(table) ? null : table;
      return this;
   }

   public String getCatalog() {
      return this.catalog;
   }

   public AbstractMemberMetaData setCatalog(String catalog) {
      this.catalog = StringUtils.isWhitespace(catalog) ? null : catalog;
      return this;
   }

   public String getSchema() {
      return this.schema;
   }

   public AbstractMemberMetaData setSchema(String schema) {
      this.schema = StringUtils.isWhitespace(schema) ? null : schema;
      return this;
   }

   public boolean isUnique() {
      return this.uniqueConstraint;
   }

   public AbstractMemberMetaData setUnique(String unique) {
      if (!StringUtils.isWhitespace(unique)) {
         this.uniqueConstraint = Boolean.parseBoolean(unique);
      }

      return this;
   }

   public AbstractMemberMetaData setUnique(boolean unique) {
      this.uniqueConstraint = unique;
      return this;
   }

   public IndexedValue getIndexed() {
      return this.indexed;
   }

   public AbstractMemberMetaData setIndexed(IndexedValue val) {
      this.indexed = val;
      return this;
   }

   public NullValue getNullValue() {
      return this.nullValue;
   }

   public AbstractMemberMetaData setNullValue(NullValue val) {
      this.nullValue = val;
      return this;
   }

   public int getFieldId() {
      return this.fieldId;
   }

   public final String[] getFieldTypes() {
      return this.getValuesForExtension("implementation-classes");
   }

   public void setFieldTypes(String types) {
      if (!StringUtils.isWhitespace(types)) {
         this.addExtension("implementation-classes", types);
      }

   }

   public int getAbsoluteFieldNumber() {
      return this.className == null ? this.fieldId + this.getAbstractClassMetaData().getNoOfInheritedManagedMembers() : this.getAbstractClassMetaData().getAbsolutePositionOfMember(this.name);
   }

   public Member getMemberRepresented() {
      return this.memberRepresented;
   }

   public Class getType() {
      return this.type;
   }

   public String getTypeName() {
      return this.type == null ? null : this.type.getName();
   }

   public ContainerMetaData getContainer() {
      return this.containerMetaData;
   }

   public ArrayMetaData getArray() {
      return this.containerMetaData != null && this.containerMetaData instanceof ArrayMetaData ? (ArrayMetaData)this.containerMetaData : null;
   }

   public CollectionMetaData getCollection() {
      return this.containerMetaData != null && this.containerMetaData instanceof CollectionMetaData ? (CollectionMetaData)this.containerMetaData : null;
   }

   public MapMetaData getMap() {
      return this.containerMetaData != null && this.containerMetaData instanceof MapMetaData ? (MapMetaData)this.containerMetaData : null;
   }

   public final String getMappedBy() {
      return this.mappedBy;
   }

   public void setMappedBy(String mappedBy) {
      this.mappedBy = StringUtils.isWhitespace(mappedBy) ? null : mappedBy;
   }

   public final ColumnMetaData[] getColumnMetaData() {
      return this.columnMetaData;
   }

   public final ElementMetaData getElementMetaData() {
      return this.elementMetaData;
   }

   public final KeyMetaData getKeyMetaData() {
      return this.keyMetaData;
   }

   public final ValueMetaData getValueMetaData() {
      return this.valueMetaData;
   }

   public final EmbeddedMetaData getEmbeddedMetaData() {
      return this.embeddedMetaData;
   }

   public void setDeleteAction(String action) {
      if (action != null) {
         this.foreignKeyMetaData = new ForeignKeyMetaData();
         this.foreignKeyMetaData.setDeleteAction(ForeignKeyAction.getForeignKeyAction(action));
      }

   }

   public final ForeignKeyMetaData getForeignKeyMetaData() {
      return this.foreignKeyMetaData;
   }

   public final IndexMetaData getIndexMetaData() {
      return this.indexMetaData;
   }

   public final UniqueMetaData getUniqueMetaData() {
      return this.uniqueMetaData;
   }

   public final JoinMetaData getJoinMetaData() {
      return this.joinMetaData;
   }

   public void addColumn(ColumnMetaData colmd) {
      this.columns.add(colmd);
      colmd.parent = this;
      this.columnMetaData = new ColumnMetaData[this.columns.size()];

      for(int i = 0; i < this.columnMetaData.length; ++i) {
         this.columnMetaData[i] = (ColumnMetaData)this.columns.get(i);
      }

   }

   public ColumnMetaData newColumnMetaData() {
      ColumnMetaData colmd = new ColumnMetaData();
      this.addColumn(colmd);
      return colmd;
   }

   public boolean hasContainer() {
      return this.containerMetaData != null;
   }

   public boolean hasArray() {
      return this.containerMetaData == null ? false : this.containerMetaData instanceof ArrayMetaData;
   }

   public boolean hasCollection() {
      return this.containerMetaData == null ? false : this.containerMetaData instanceof CollectionMetaData;
   }

   public boolean hasMap() {
      return this.containerMetaData == null ? false : this.containerMetaData instanceof MapMetaData;
   }

   public byte getPersistenceFlags() {
      return this.persistenceFlags;
   }

   public boolean isFieldToBePersisted() {
      if (this.isPopulated()) {
         if (this.isStatic()) {
            return false;
         }

         if (this.isFinal() && this instanceof FieldMetaData) {
            if (this.persistenceModifier == FieldPersistenceModifier.PERSISTENT) {
               throw new InvalidMetaDataException("044118", new Object[]{this.getClassName(), this.getName()});
            }

            return false;
         }
      }

      if (this.persistenceModifier == null) {
         return false;
      } else {
         return !this.persistenceModifier.equals(FieldPersistenceModifier.NONE);
      }
   }

   public void setOrdered() {
      this.ordered = true;
   }

   public void setTargetClassName(String target) {
      if (!StringUtils.isWhitespace(target)) {
         this.targetClassName = target;
      }

   }

   public void setStoreInLob() {
      this.storeInLob = true;
   }

   public void setMapsIdAttribute(String attr) {
      this.mapsIdAttribute = attr;
      if (this.mapsIdAttribute != null) {
         NucleusLogger.METADATA.warn("@MapsId specified on member " + this.getFullFieldName() + " yet not currently supported (" + this.mapsIdAttribute + ")");
      }

   }

   public void setRelationTypeString(String relType) {
      this.relationTypeString = relType;
   }

   public String getRelationTypeString() {
      return this.relationTypeString;
   }

   public void setCascadePersist(boolean cascade) {
      this.cascadePersist = cascade;
   }

   public void setCascadeUpdate(boolean cascade) {
      this.cascadeUpdate = cascade;
   }

   public void setCascadeDelete(boolean cascade) {
      this.cascadeDelete = cascade;
   }

   public void setCascadeDetach(boolean cascade) {
      this.cascadeDetach = cascade;
   }

   public void setCascadeRefresh(boolean cascade) {
      this.cascadeRefresh = cascade;
   }

   public void setCascadeRemoveOrphans(boolean cascade) {
      this.cascadeRemoveOrphans = cascade;
   }

   public void setValueGeneratorName(String generator) {
      if (StringUtils.isWhitespace(generator)) {
         this.valueGeneratorName = null;
      } else {
         this.valueGeneratorName = generator;
      }

   }

   public void setContainer(ContainerMetaData conmd) {
      this.containerMetaData = conmd;
      this.containerMetaData.parent = this;
   }

   public CollectionMetaData newCollectionMetaData() {
      CollectionMetaData collmd = new CollectionMetaData();
      this.setContainer(collmd);
      return collmd;
   }

   public ArrayMetaData newArrayMetaData() {
      ArrayMetaData arrmd = new ArrayMetaData();
      this.setContainer(arrmd);
      return arrmd;
   }

   public MapMetaData newMapMetaData() {
      MapMetaData mapmd = new MapMetaData();
      this.setContainer(mapmd);
      return mapmd;
   }

   public final void setElementMetaData(ElementMetaData elementMetaData) {
      this.elementMetaData = elementMetaData;
      this.elementMetaData.parent = this;
   }

   public ElementMetaData newElementMetaData() {
      ElementMetaData elemmd = new ElementMetaData();
      this.setElementMetaData(elemmd);
      return elemmd;
   }

   public final void setKeyMetaData(KeyMetaData keyMetaData) {
      this.keyMetaData = keyMetaData;
      this.keyMetaData.parent = this;
   }

   public KeyMetaData newKeyMetaData() {
      KeyMetaData keymd = new KeyMetaData();
      this.setKeyMetaData(keymd);
      return keymd;
   }

   public final void setValueMetaData(ValueMetaData valueMetaData) {
      this.valueMetaData = valueMetaData;
      this.valueMetaData.parent = this;
   }

   public ValueMetaData newValueMetaData() {
      ValueMetaData valuemd = new ValueMetaData();
      this.setValueMetaData(valuemd);
      return valuemd;
   }

   public final void setOrderMetaData(OrderMetaData orderMetaData) {
      this.orderMetaData = orderMetaData;
      this.orderMetaData.parent = this;
   }

   public OrderMetaData newOrderMetaData() {
      OrderMetaData ordermd = new OrderMetaData();
      this.setOrderMetaData(ordermd);
      return ordermd;
   }

   public final void setEmbeddedMetaData(EmbeddedMetaData embeddedMetaData) {
      this.embeddedMetaData = embeddedMetaData;
      this.embeddedMetaData.parent = this;
   }

   public EmbeddedMetaData newEmbeddedMetaData() {
      EmbeddedMetaData embmd = new EmbeddedMetaData();
      this.setEmbeddedMetaData(embmd);
      return embmd;
   }

   public final void setForeignKeyMetaData(ForeignKeyMetaData foreignKeyMetaData) {
      this.foreignKeyMetaData = foreignKeyMetaData;
      this.foreignKeyMetaData.parent = this;
   }

   public ForeignKeyMetaData newForeignKeyMetaData() {
      ForeignKeyMetaData fkmd = new ForeignKeyMetaData();
      this.setForeignKeyMetaData(fkmd);
      return fkmd;
   }

   public final void setIndexMetaData(IndexMetaData indexMetaData) {
      this.indexMetaData = indexMetaData;
      this.indexMetaData.parent = this;
   }

   public IndexMetaData newIndexMetaData() {
      IndexMetaData idxmd = new IndexMetaData();
      this.setIndexMetaData(idxmd);
      return idxmd;
   }

   public final void setUniqueMetaData(UniqueMetaData uniqueMetaData) {
      this.uniqueMetaData = uniqueMetaData;
      this.uniqueMetaData.parent = this;
   }

   public UniqueMetaData newUniqueMetaData() {
      UniqueMetaData unimd = new UniqueMetaData();
      this.setUniqueMetaData(unimd);
      return unimd;
   }

   public final void setJoinMetaData(JoinMetaData joinMetaData) {
      this.joinMetaData = joinMetaData;
      this.joinMetaData.parent = this;
   }

   public JoinMetaData newJoinMetaData() {
      JoinMetaData joinmd = new JoinMetaData();
      this.setJoinMetaData(joinmd);
      return joinmd;
   }

   public JoinMetaData newJoinMetadata() {
      JoinMetaData joinmd = new JoinMetaData();
      this.setJoinMetaData(joinmd);
      return joinmd;
   }

   void setFieldId(int field_id) {
      this.fieldId = field_id;
   }

   protected void setRelation(ClassLoaderResolver clr) {
      if (this.relationType == null) {
         MetaDataManager mmgr = this.getAbstractClassMetaData().getPackageMetaData().getFileMetaData().metaDataManager;
         AbstractClassMetaData otherCmd = null;
         if (this.hasCollection()) {
            otherCmd = mmgr.getMetaDataForClass(this.getCollection().getElementType(), clr);
            if (otherCmd == null) {
               Class elementCls = clr.classForName(this.getCollection().getElementType());
               if (ClassUtils.isReferenceType(elementCls)) {
                  try {
                     String[] implNames = MetaDataUtils.getInstance().getImplementationNamesForReferenceField(this, FieldRole.ROLE_COLLECTION_ELEMENT, clr, mmgr);
                     if (implNames != null && implNames.length > 0) {
                        otherCmd = mmgr.getMetaDataForClass(implNames[0], clr);
                     }
                  } catch (NucleusUserException jpe) {
                     if (!this.getCollection().isSerializedElement() && this.mappedBy != null) {
                        throw jpe;
                     }

                     NucleusLogger.METADATA.debug("Field " + this.getFullFieldName() + " is a collection of elements of reference type yet no implementation-classes are provided. Assuming they arent persistable");
                  }
               }
            }
         } else if (this.hasMap()) {
            otherCmd = ((MapMetaData)this.containerMetaData).getValueClassMetaData(clr, mmgr);
            if (otherCmd == null) {
               otherCmd = ((MapMetaData)this.containerMetaData).getKeyClassMetaData(clr, mmgr);
            }

            if (otherCmd == null) {
            }
         } else if (this.hasArray()) {
            otherCmd = ((ArrayMetaData)this.containerMetaData).getElementClassMetaData(clr, mmgr);
         } else if (this.getType().isInterface()) {
            try {
               String[] implNames = MetaDataUtils.getInstance().getImplementationNamesForReferenceField(this, FieldRole.ROLE_FIELD, clr, mmgr);
               if (implNames != null && implNames.length > 0) {
                  otherCmd = mmgr.getMetaDataForClass(implNames[0], clr);
               }
            } catch (NucleusUserException var11) {
               otherCmd = null;
            }
         } else if (this.getType().getName().equals(ClassNameConstants.Object) && this.getFieldTypes() != null) {
            otherCmd = mmgr.getMetaDataForClass(this.getFieldTypes()[0], clr);
         } else {
            otherCmd = mmgr.getMetaDataForClass(this.getType(), clr);
         }

         if (otherCmd == null) {
            if (this.hasArray() && this.getArray().mayContainPersistableElements()) {
               this.relatedMemberMetaData = null;
               this.relationType = RelationType.ONE_TO_MANY_UNI;
            } else {
               this.relatedMemberMetaData = null;
               this.relationType = RelationType.NONE;
            }
         } else if (this.mappedBy != null) {
            AbstractMemberMetaData otherMmd = otherCmd.getMetaDataForMember(this.mappedBy);
            if (otherMmd == null) {
               throw (new NucleusUserException(Localiser.msg("044115", this.getAbstractClassMetaData().getFullClassName(), this.name, this.mappedBy, otherCmd.getFullClassName()))).setFatal();
            }

            this.relatedMemberMetaData = new AbstractMemberMetaData[]{otherMmd};
            if (this.hasContainer() && this.relatedMemberMetaData[0].hasContainer()) {
               this.relationType = RelationType.MANY_TO_MANY_BI;
            } else if (this.hasContainer() && !this.relatedMemberMetaData[0].hasContainer()) {
               this.relationType = RelationType.ONE_TO_MANY_BI;
            } else if (!this.hasContainer() && this.relatedMemberMetaData[0].hasContainer()) {
               this.relationType = RelationType.MANY_TO_ONE_BI;
            } else {
               this.relationType = RelationType.ONE_TO_ONE_BI;
            }
         } else {
            int[] otherFieldNumbers = otherCmd.getAllMemberPositions();
            Set relatedFields = new HashSet();

            for(int i = 0; i < otherFieldNumbers.length; ++i) {
               AbstractMemberMetaData otherFmd = otherCmd.getMetaDataForManagedMemberAtAbsolutePosition(otherFieldNumbers[i]);
               if (otherFmd.getMappedBy() != null && otherFmd.getMappedBy().equals(this.name)) {
                  if (otherFmd.hasContainer()) {
                     if (otherFmd.hasCollection() && otherFmd.getCollection().getElementType().equals(this.getClassName(true)) || otherFmd.hasArray() && otherFmd.getArray().getElementType().equals(this.getClassName(true)) || otherFmd.hasMap() && otherFmd.getMap().getKeyType().equals(this.getClassName(true)) || otherFmd.hasMap() && otherFmd.getMap().getValueType().equals(this.getClassName(true))) {
                        relatedFields.add(otherFmd);
                        if (this.hasContainer()) {
                           this.relationType = RelationType.MANY_TO_MANY_BI;
                        } else {
                           this.relationType = RelationType.MANY_TO_ONE_BI;
                        }
                     } else {
                        String elementType = null;
                        if (otherFmd.hasCollection()) {
                           elementType = otherFmd.getCollection().getElementType();
                        } else if (otherFmd.hasArray()) {
                           elementType = otherFmd.getArray().getElementType();
                        }

                        if (elementType != null) {
                           Class elementCls = clr.classForName(elementType);
                           if (elementCls.isInterface()) {
                              Class thisCls = clr.classForName(this.getClassName(true));
                              if (elementCls.isAssignableFrom(thisCls)) {
                                 relatedFields.add(otherFmd);
                                 if (this.hasContainer()) {
                                    this.relationType = RelationType.MANY_TO_MANY_BI;
                                 } else {
                                    this.relationType = RelationType.MANY_TO_ONE_BI;
                                 }
                              }
                           }
                        }
                     }
                  } else {
                     Class cls = clr.classForName(this.getClassName(true));
                     if (otherFmd.getType().isAssignableFrom(cls) || cls.isAssignableFrom(otherFmd.getType())) {
                        relatedFields.add(otherFmd);
                        if (this.hasContainer()) {
                           this.relationType = RelationType.ONE_TO_MANY_BI;
                        } else {
                           this.relationType = RelationType.ONE_TO_ONE_BI;
                        }
                     }
                  }
               }
            }

            if (relatedFields.size() > 0) {
               this.relatedMemberMetaData = (AbstractMemberMetaData[])relatedFields.toArray(new AbstractMemberMetaData[relatedFields.size()]);
               relatedFields.clear();
               relatedFields = null;
            } else if (this.hasContainer()) {
               this.relationType = RelationType.ONE_TO_MANY_UNI;
            } else if (this.joinMetaData != null) {
               this.relationType = RelationType.MANY_TO_ONE_UNI;
            } else {
               this.relationType = RelationType.ONE_TO_ONE_UNI;
            }
         }

      }
   }

   public RelationType getRelationType(ClassLoaderResolver clr) {
      if (this.relationType == null) {
         this.setRelation(clr);
      }

      return this.relationType;
   }

   public boolean isPersistentInterface(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.hasCollection()) {
         if (mmgr.isPersistentInterface(this.getCollection().getElementType())) {
            return true;
         }
      } else if (this.hasMap()) {
         if (mmgr.isPersistentInterface(this.getMap().getKeyType())) {
            return true;
         }

         if (mmgr.isPersistentInterface(this.getMap().getValueType())) {
            return true;
         }
      } else if (this.hasArray()) {
         if (mmgr.isPersistentInterface(this.getArray().getElementType())) {
            return true;
         }
      } else if (this.getType().isInterface()) {
         if (mmgr.isPersistentInterface(this.getTypeName())) {
            return true;
         }

         String[] fieldTypes = this.getFieldTypes();
         if (fieldTypes != null && mmgr.isPersistentInterface(fieldTypes[0])) {
            return true;
         }
      }

      return false;
   }

   public boolean isRelationOwner(ClassLoaderResolver clr) {
      if (this.relationType == null) {
         this.setRelation(clr);
      }

      if (this.relationType == RelationType.NONE) {
         return true;
      } else if (this.relationType != RelationType.ONE_TO_MANY_UNI && this.relationType != RelationType.ONE_TO_ONE_UNI) {
         if (this.relationType != RelationType.MANY_TO_MANY_BI && this.relationType != RelationType.MANY_TO_ONE_BI && this.relationType != RelationType.ONE_TO_MANY_BI && this.relationType != RelationType.ONE_TO_ONE_BI) {
            return this.relationType == RelationType.MANY_TO_ONE_UNI;
         } else {
            return this.mappedBy == null;
         }
      } else {
         return true;
      }
   }

   public AbstractMemberMetaData[] getRelatedMemberMetaData(ClassLoaderResolver clr) {
      if (this.relationType == null) {
         this.setRelation(clr);
      }

      return this.relatedMemberMetaData;
   }

   public AbstractMemberMetaData getRelatedMemberMetaDataForObject(ClassLoaderResolver clr, Object thisPC, Object otherPC) {
      if (this.relationType == null) {
         this.setRelation(clr);
      }

      if (this.relatedMemberMetaData == null) {
         return null;
      } else {
         for(int i = 0; i < this.relatedMemberMetaData.length; ++i) {
            if (this.relationType == RelationType.ONE_TO_ONE_BI) {
               if (this.relatedMemberMetaData[i].getType().isAssignableFrom(thisPC.getClass()) && this.getType().isAssignableFrom(otherPC.getClass())) {
                  return this.relatedMemberMetaData[i];
               }
            } else if (this.relationType == RelationType.MANY_TO_ONE_BI) {
               if (this.relatedMemberMetaData[i].hasCollection()) {
                  Class elementType = clr.classForName(this.relatedMemberMetaData[i].getCollection().getElementType());
                  if (elementType.isAssignableFrom(thisPC.getClass()) && this.getType().isAssignableFrom(otherPC.getClass())) {
                     return this.relatedMemberMetaData[i];
                  }
               } else if (this.relatedMemberMetaData[i].hasMap()) {
                  Class valueType = clr.classForName(this.relatedMemberMetaData[i].getMap().getValueType());
                  if (valueType.isAssignableFrom(thisPC.getClass()) && this.getType().isAssignableFrom(otherPC.getClass())) {
                     return this.relatedMemberMetaData[i];
                  }

                  Class keyType = clr.classForName(this.relatedMemberMetaData[i].getMap().getKeyType());
                  if (keyType.isAssignableFrom(thisPC.getClass()) && this.getType().isAssignableFrom(otherPC.getClass())) {
                     return this.relatedMemberMetaData[i];
                  }
               }
            }
         }

         return null;
      }
   }

   void getReferencedClassMetaData(List orderedCMDs, Set referencedCMDs, ClassLoaderResolver clr, MetaDataManager mmgr) {
      AbstractClassMetaData type_cmd = mmgr.getMetaDataForClass(this.getType(), clr);
      if (type_cmd != null) {
         type_cmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
      }

      if (this.containerMetaData != null) {
         if (this.containerMetaData instanceof CollectionMetaData) {
            ((CollectionMetaData)this.containerMetaData).getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
         } else if (this.containerMetaData instanceof MapMetaData) {
            ((MapMetaData)this.containerMetaData).getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
         } else if (this.containerMetaData instanceof ArrayMetaData) {
            ((ArrayMetaData)this.containerMetaData).getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
         }
      }

   }

   public boolean calcIsSecondClassMutable(MetaDataManager mmgr) {
      if (this.hasExtension("is-second-class")) {
         String isSecondClass = this.getValueForExtension("is-second-class");
         if (isSecondClass.equalsIgnoreCase("true")) {
            return true;
         }

         if (isSecondClass.equalsIgnoreCase("false")) {
            return false;
         }

         if (!isSecondClass.equalsIgnoreCase("default")) {
            throw new InvalidMetaDataException("044002", new Object[]{"is-second-class", "true/false/default", isSecondClass});
         }
      }

      return mmgr.getNucleusContext().getTypeManager().isSecondClassMutableType(this.getTypeName());
   }

   public boolean isInsertable() {
      if (!this.hasCollection() && !this.hasArray()) {
         if (this.hasMap()) {
            return true;
         }

         if (this.columnMetaData != null && this.columnMetaData.length > 0) {
            return this.columnMetaData[0].getInsertable();
         }
      } else if (this.elementMetaData != null && this.elementMetaData.getColumnMetaData() != null && this.elementMetaData.getColumnMetaData().length > 0) {
         return this.elementMetaData.getColumnMetaData()[0].getInsertable();
      }

      return true;
   }

   public boolean isUpdateable() {
      if (!this.hasCollection() && !this.hasArray()) {
         if (this.hasMap()) {
            return true;
         }

         if (this.columnMetaData != null && this.columnMetaData.length > 0) {
            return this.columnMetaData[0].getUpdateable();
         }
      } else if (this.elementMetaData != null && this.elementMetaData.getColumnMetaData() != null && this.elementMetaData.getColumnMetaData().length > 0) {
         return this.elementMetaData.getColumnMetaData()[0].getUpdateable();
      }

      return true;
   }

   public String toString(String prefix, String indent) {
      return super.toString(prefix, indent);
   }

   public int compareTo(Object o) {
      if (o instanceof AbstractMemberMetaData) {
         AbstractMemberMetaData c = (AbstractMemberMetaData)o;
         return this.name.compareTo(c.name);
      } else if (o instanceof String) {
         return this.name.compareTo((String)o);
      } else if (o == null) {
         throw new ClassCastException("object is null");
      } else {
         throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
      }
   }
}
