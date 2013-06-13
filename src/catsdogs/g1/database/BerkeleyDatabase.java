package catsdogs.g1.database;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sleepycat.je.CursorConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.Transaction;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import com.sleepycat.persist.StoreConfig;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.SecondaryKey;

public class BerkeleyDatabase<E> {
    
    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPERS = new HashMap<Class<?>, Class<?>>();
    
    static {
        PRIMITIVE_WRAPPERS.put(byte.class, Byte.class);
        PRIMITIVE_WRAPPERS.put(short.class, Short.class);
        PRIMITIVE_WRAPPERS.put(int.class, Integer.class);
        PRIMITIVE_WRAPPERS.put(long.class, Long.class);
        PRIMITIVE_WRAPPERS.put(float.class, Float.class);
        PRIMITIVE_WRAPPERS.put(double.class, Double.class);
        PRIMITIVE_WRAPPERS.put(char.class, Character.class);
        PRIMITIVE_WRAPPERS.put(boolean.class, Boolean.class);
        PRIMITIVE_WRAPPERS.put(void.class, Void.class);
    }
    
    private final EntityStore entityStore;
    private Class<?> primaryKeyClass;
    private final Map<String, Class<?>> secondaryKeyClasses = new HashMap<String, Class<?>>();
    @SuppressWarnings("rawtypes")
    private final PrimaryIndex primaryIndex;
    @SuppressWarnings("rawtypes")
    private final Map<String, SecondaryIndex> secondaryIndices =
            new HashMap<String, SecondaryIndex>();
    
    @SuppressWarnings("unchecked")
    public BerkeleyDatabase(Environment env, String dbName, Class<E> entityClass)
            throws DatabaseException {
        
        // Determine the primary key of the entity class and secondary keys if any
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.getAnnotation(PrimaryKey.class) != null) {
                primaryKeyClass = field.getType();
            } else if (field.getAnnotation(SecondaryKey.class) != null) {
                secondaryKeyClasses.put(field.getName(), field.getType());
            }
        }
        
        StoreConfig storeConfig = new StoreConfig();
        storeConfig.setReadOnly(env.getConfig().getReadOnly());
        storeConfig.setAllowCreate(env.getConfig().getAllowCreate());
        storeConfig.setTransactional(env.getConfig().getTransactional());
        
        entityStore = new EntityStore(env, dbName, storeConfig);
        
        if (primaryKeyClass.isPrimitive()) {
            primaryKeyClass = PRIMITIVE_WRAPPERS.get(primaryKeyClass);
        }
        primaryIndex = entityStore.getPrimaryIndex(primaryKeyClass,
                                                   entityClass);
        
        for (Entry<String, Class<?>> entry : secondaryKeyClasses.entrySet()) {
            if (entry.getValue().isPrimitive()) {
                entry.setValue(PRIMITIVE_WRAPPERS.get(entry.getValue()));
            }
            secondaryIndices.put(entry.getKey(),
                                 entityStore.getSecondaryIndex(primaryIndex,
                                                               entry.getValue(),
                                                               entry.getKey()));
        }
    }
    
    public void close() {
        entityStore.close();
    }
    
    public <PK> boolean containsPrimaryKey(PK pk) {
        return containsPrimaryKey(null, pk);
    }

    @SuppressWarnings("unchecked")
    public <PK> boolean containsPrimaryKey(Transaction txn, PK pk) {
        if (primaryKeyClass != pk.getClass()) {
            throw new IllegalArgumentException("Argument does not match PrimaryKey type of entity.");
        }
        return primaryIndex.contains(txn, pk, LockMode.DEFAULT);
    }
    
    public <SK> boolean containsSecondaryKey(String fieldName, SK sk) {
        return containsSecondaryKey(null, fieldName, sk);
    }
    
    @SuppressWarnings("unchecked")
    public <SK> boolean containsSecondaryKey(Transaction txn, String fieldName, SK sk) {
        if (!secondaryKeyClasses.containsKey(fieldName)) {
            throw new IllegalArgumentException("Field " + fieldName + " is not a secondary key of entity.");
        } else if (secondaryKeyClasses.get(fieldName) != sk.getClass()) {
            throw new IllegalArgumentException("Argument does not match SecondaryKey type of entity.");
        }
        return secondaryIndices.get(fieldName).contains(txn, sk, LockMode.DEFAULT);
    }
    
    public long count() {
        return primaryIndex.count();
    }
    
    public <PK> boolean deleteByPrimaryKey(PK pk) {
        return deleteByPrimaryKey(null, pk);
    }
    
    @SuppressWarnings("unchecked")
    public <PK> boolean deleteByPrimaryKey(Transaction txn, PK pk) {
        if (primaryKeyClass != pk.getClass()) {
            throw new IllegalArgumentException("Argument does not match PrimaryKey type of entity.");
       }
       return primaryIndex.delete(txn, pk);
    }
    
    public <SK> boolean deleteBySecondaryKey(String fieldName, SK sk) {
        return deleteBySecondaryKey(null, fieldName, sk);
    }
    
    @SuppressWarnings("unchecked")
    public <SK> boolean deleteBySecondaryKey(Transaction txn, String fieldName, SK sk) {
        if (!secondaryKeyClasses.containsKey(fieldName)) {
            throw new IllegalArgumentException("Field " + fieldName + " is not a secondary key of entity.");
        } else if (secondaryKeyClasses.get(fieldName) != sk.getClass()) {
            throw new IllegalArgumentException("Argument does not match SecondaryKey type of entity.");
        }
        return secondaryIndices.get(fieldName).delete(txn, sk);
    }
    
    public EntityCursor<E> entities() {
        return entities(null);
    }
    
    @SuppressWarnings("unchecked")
    public EntityCursor<E> entities(Transaction txn) {
        return (EntityCursor<E>) primaryIndex.entities(txn, null);
    }
    
    public <PK> E getByPrimaryKey(PK pk) {
        return getByPrimaryKey(null, pk);
    }
    
    @SuppressWarnings("unchecked")
    public <PK> E getByPrimaryKey(Transaction txn, PK pk) {
        if (primaryKeyClass != pk.getClass()) {
             throw new IllegalArgumentException("Argument does not match PrimaryKey type of entity.");
        }
        return (E) primaryIndex.get(txn, pk, LockMode.DEFAULT);
    }
    
    public <SK> EntityCursor<E> getBySecondaryKey(String fieldName, SK sk) {
        return getBySecondaryKey(null, fieldName, sk);
    }
    
    @SuppressWarnings("unchecked")
    public <SK> EntityCursor<E> getBySecondaryKey(Transaction txn, String fieldName, SK sk) {
        if (!secondaryKeyClasses.containsKey(fieldName)) {
            throw new IllegalArgumentException("Field " + fieldName + " is not a secondary key of entity.");
        } else if (secondaryKeyClasses.get(fieldName) != sk.getClass()) {
            throw new IllegalArgumentException("Argument does not match SecondaryKey type of entity.");
        }
        return (EntityCursor<E>) secondaryIndices.get(fieldName).entities(txn, sk, true, sk, true, CursorConfig.DEFAULT);
    }
    
    public E put(E entity) {
        return put(null, entity);
    }
    
    @SuppressWarnings("unchecked")
    public E put(Transaction txn, E entity) {
    	E e = (E) primaryIndex.put(txn, entity);
    	entityStore.sync();
        return e;
    }
}
