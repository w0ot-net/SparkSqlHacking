package org.datanucleus.api.jdo;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.jdo.Extent;
import javax.jdo.FetchGroup;
import javax.jdo.FetchPlan;
import javax.jdo.JDOException;
import javax.jdo.JDOQLTypedQuery;
import javax.jdo.ObjectState;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.jdo.datastore.JDOConnection;
import javax.jdo.datastore.Sequence;
import javax.jdo.listener.InstanceLifecycleListener;

public class JDOPersistenceManagerProxy implements PersistenceManager {
   protected JDOPersistenceManagerFactory pmf;

   public JDOPersistenceManagerProxy(JDOPersistenceManagerFactory pmf) {
      this.pmf = pmf;
   }

   protected PersistenceManager getPM() {
      return this.pmf.getPMProxyDelegate();
   }

   public void close() {
      this.pmf.clearPMProxyDelegate();
   }

   public void addInstanceLifecycleListener(InstanceLifecycleListener listener, Class... classes) {
      this.getPM().addInstanceLifecycleListener(listener, classes);
   }

   public void checkConsistency() {
      this.getPM().checkConsistency();
   }

   public Transaction currentTransaction() {
      return this.getPM().currentTransaction();
   }

   public void deletePersistent(Object obj) {
      this.getPM().deletePersistent(obj);
   }

   public void deletePersistentAll(Object... pcs) {
      this.getPM().deletePersistentAll(pcs);
   }

   public void deletePersistentAll(Collection pcs) {
      this.getPM().deletePersistentAll(pcs);
   }

   public Object detachCopy(Object pc) {
      return this.getPM().detachCopy(pc);
   }

   public Collection detachCopyAll(Collection pcs) {
      return this.getPM().detachCopyAll(pcs);
   }

   public Object[] detachCopyAll(Object... pcs) {
      return this.getPM().detachCopyAll(pcs);
   }

   public void evict(Object obj) {
      this.getPM().evict(obj);
   }

   public void evictAll() {
      this.getPM().evictAll();
   }

   public void evictAll(Object... pcs) {
      this.getPM().evictAll(pcs);
   }

   public void evictAll(Collection pcs) {
      this.getPM().evictAll(pcs);
   }

   public void evictAll(boolean subclasses, Class cls) {
      this.getPM().evictAll(subclasses, cls);
   }

   public void flush() {
      this.getPM().flush();
   }

   public boolean getCopyOnAttach() {
      return this.getPM().getCopyOnAttach();
   }

   public JDOConnection getDataStoreConnection() {
      return this.getPM().getDataStoreConnection();
   }

   public boolean getDetachAllOnCommit() {
      return this.getPM().getDetachAllOnCommit();
   }

   public Extent getExtent(Class pcClass) {
      return this.getPM().getExtent(pcClass);
   }

   public Extent getExtent(Class pcClass, boolean subclasses) {
      return this.getPM().getExtent(pcClass, subclasses);
   }

   public FetchGroup getFetchGroup(Class arg0, String arg1) {
      return this.getPM().getFetchGroup(arg0, arg1);
   }

   public FetchPlan getFetchPlan() {
      return this.getPM().getFetchPlan();
   }

   public boolean getIgnoreCache() {
      return this.getPM().getIgnoreCache();
   }

   public Set getManagedObjects() {
      return this.getPM().getManagedObjects();
   }

   public Set getManagedObjects(EnumSet states) {
      return this.getPM().getManagedObjects(states);
   }

   public Set getManagedObjects(Class... classes) {
      return this.getPM().getManagedObjects(classes);
   }

   public Set getManagedObjects(EnumSet states, Class... classes) {
      return this.getPM().getManagedObjects(states, classes);
   }

   public boolean getMultithreaded() {
      return this.getPM().getMultithreaded();
   }

   public Object getObjectById(Object id) {
      return this.getPM().getObjectById(id);
   }

   public Object getObjectById(Object id, boolean validate) {
      return this.getPM().getObjectById(id, validate);
   }

   public Object getObjectById(Class cls, Object key) {
      return this.getPM().getObjectById(cls, key);
   }

   public Object getObjectId(Object pc) {
      return this.getPM().getObjectId(pc);
   }

   public Class getObjectIdClass(Class cls) {
      return this.getPM().getObjectIdClass(cls);
   }

   public Collection getObjectsById(Collection oids) {
      return this.getPM().getObjectsById(oids);
   }

   public Object[] getObjectsById(Object... oids) {
      return this.getPM().getObjectsById(oids);
   }

   public Collection getObjectsById(Collection oids, boolean validate) {
      return this.getPM().getObjectsById(oids, validate);
   }

   public Object[] getObjectsById(Object[] oids, boolean validate) {
      return this.getPM().getObjectsById(validate, oids);
   }

   public Object[] getObjectsById(boolean validate, Object... oids) {
      return this.getPM().getObjectsById(validate, oids);
   }

   public PersistenceManagerFactory getPersistenceManagerFactory() {
      return this.getPM().getPersistenceManagerFactory();
   }

   public Sequence getSequence(String sequenceName) {
      return this.getPM().getSequence(sequenceName);
   }

   public Date getServerDate() {
      return this.getPM().getServerDate();
   }

   public Object getTransactionalObjectId(Object pc) {
      return this.getPM().getTransactionalObjectId(pc);
   }

   public Object getUserObject() {
      return this.getPM().getUserObject();
   }

   public Object getUserObject(Object key) {
      return this.getPM().getUserObject(key);
   }

   public boolean isClosed() {
      return this.getPM().isClosed();
   }

   public void makeNontransactional(Object pc) {
      this.getPM().makeNontransactional(pc);
   }

   public void makeNontransactionalAll(Object... pcs) {
      this.getPM().makeNontransactionalAll(pcs);
   }

   public void makeNontransactionalAll(Collection arg0) {
      this.getPM().makeNontransactionalAll(arg0);
   }

   public Object makePersistent(Object obj) {
      return this.getPM().makePersistent(obj);
   }

   public Object[] makePersistentAll(Object... arg0) {
      return this.getPM().makePersistentAll(arg0);
   }

   public Collection makePersistentAll(Collection arg0) {
      return this.getPM().makePersistentAll(arg0);
   }

   public void makeTransactional(Object arg0) {
      this.getPM().makeTransactional(arg0);
   }

   public void makeTransactionalAll(Object... arg0) {
      this.getPM().makeTransactionalAll(arg0);
   }

   public void makeTransactionalAll(Collection arg0) {
      this.getPM().makeTransactionalAll(arg0);
   }

   public void makeTransient(Object pc) {
      this.getPM().makeTransient(pc);
   }

   public void makeTransient(Object pc, boolean useFetchPlan) {
      this.getPM().makeTransient(pc, useFetchPlan);
   }

   public void makeTransientAll(Object... pcs) {
      this.getPM().makeTransientAll(pcs);
   }

   public void makeTransientAll(Collection pcs) {
      this.getPM().makeTransientAll(pcs);
   }

   public void makeTransientAll(Object[] pcs, boolean includeFetchPlan) {
      this.getPM().makeTransientAll(includeFetchPlan, pcs);
   }

   public void makeTransientAll(boolean includeFetchPlan, Object... pcs) {
      this.getPM().makeTransientAll(includeFetchPlan, pcs);
   }

   public void makeTransientAll(Collection pcs, boolean useFetchPlan) {
      this.getPM().makeTransientAll(pcs, useFetchPlan);
   }

   public Object newInstance(Class pc) {
      return this.getPM().newInstance(pc);
   }

   public Query newNamedQuery(Class cls, String filter) {
      return this.getPM().newNamedQuery(cls, filter);
   }

   public Object newObjectIdInstance(Class pcClass, Object key) {
      return this.getPM().newObjectIdInstance(pcClass, key);
   }

   public Query newQuery() {
      return this.getPM().newQuery();
   }

   public Query newQuery(Object obj) {
      return this.getPM().newQuery(obj);
   }

   public Query newQuery(String query) {
      return this.getPM().newQuery(query);
   }

   public Query newQuery(Class cls) {
      return this.getPM().newQuery(cls);
   }

   public Query newQuery(Extent cln) {
      return this.getPM().newQuery(cln);
   }

   public Query newQuery(String language, Object query) {
      return this.getPM().newQuery(language, query);
   }

   public Query newQuery(Class cls, Collection cln) {
      return this.getPM().newQuery(cls, cln);
   }

   public Query newQuery(Class cls, String filter) {
      return this.getPM().newQuery(cls, filter);
   }

   public Query newQuery(Extent cln, String filter) {
      return this.getPM().newQuery(cln, filter);
   }

   public Query newQuery(Class cls, Collection cln, String filter) {
      return this.getPM().newQuery(cls, cln, filter);
   }

   public JDOQLTypedQuery newJDOQLTypedQuery(Class cls) {
      return this.getPM().newJDOQLTypedQuery(cls);
   }

   public Object putUserObject(Object key, Object value) {
      return this.getPM().putUserObject(key, value);
   }

   public void refresh(Object obj) {
      this.getPM().refresh(obj);
   }

   public void refreshAll() {
      this.getPM().refreshAll();
   }

   public void refreshAll(Object... pcs) {
      this.getPM().refreshAll(pcs);
   }

   public void refreshAll(Collection pcs) {
      this.getPM().refreshAll(pcs);
   }

   public void refreshAll(JDOException exc) {
      this.getPM().refreshAll(exc);
   }

   public void removeInstanceLifecycleListener(InstanceLifecycleListener listener) {
      this.getPM().removeInstanceLifecycleListener(listener);
   }

   public Object removeUserObject(Object key) {
      return this.getPM().removeUserObject(key);
   }

   public void retrieve(Object pc) {
      this.getPM().retrieve(pc);
   }

   public void retrieve(Object pc, boolean fgOnly) {
      this.getPM().retrieve(pc, fgOnly);
   }

   public void retrieveAll(Collection pcs) {
      this.getPM().retrieveAll(pcs);
   }

   public void retrieveAll(Object... pcs) {
      this.getPM().retrieveAll(pcs);
   }

   public void retrieveAll(Collection pcs, boolean fgOnly) {
      this.getPM().retrieveAll(pcs, fgOnly);
   }

   public void retrieveAll(Object[] pcs, boolean fgOnly) {
      this.getPM().retrieveAll(fgOnly, pcs);
   }

   public void retrieveAll(boolean fgOnly, Object... pcs) {
      this.getPM().retrieveAll(fgOnly, pcs);
   }

   public void setCopyOnAttach(boolean flag) {
      this.getPM().setCopyOnAttach(flag);
   }

   public void setDetachAllOnCommit(boolean flag) {
      this.getPM().setDetachAllOnCommit(flag);
   }

   public void setIgnoreCache(boolean flag) {
      this.getPM().setIgnoreCache(flag);
   }

   public void setMultithreaded(boolean flag) {
      this.getPM().setMultithreaded(flag);
   }

   public void setUserObject(Object userObject) {
      this.getPM().setUserObject(userObject);
   }

   public Integer getDatastoreReadTimeoutMillis() {
      return this.getPM().getDatastoreReadTimeoutMillis();
   }

   public void setDatastoreReadTimeoutMillis(Integer intvl) {
      this.getPM().setDatastoreReadTimeoutMillis(intvl);
   }

   public Integer getDatastoreWriteTimeoutMillis() {
      return this.getPM().getDatastoreWriteTimeoutMillis();
   }

   public void setDatastoreWriteTimeoutMillis(Integer intvl) {
      this.getPM().setDatastoreWriteTimeoutMillis(intvl);
   }

   public Map getProperties() {
      return this.getPM().getProperties();
   }

   public Set getSupportedProperties() {
      return this.getPM().getSupportedProperties();
   }

   public void setProperty(String arg0, Object arg1) {
      this.getPM().setProperty(arg0, arg1);
   }
}
