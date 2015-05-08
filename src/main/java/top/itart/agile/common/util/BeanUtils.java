package top.itart.agile.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;

import top.itart.agile.common.annotation.Id;
import top.itart.agile.common.annotation.Table;

/**
 * @ClassName: BeanUtils.java
 * @Description: Copy properties , clone object etc
 * @author hymanz (www.itart.top)
 * @version V1.0
 * @Date Dec 25, 2014 1:20:45 PM
 */

public class BeanUtils {

    public static void copyProperties(Object dest, Object orig) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void copyProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException {

        BeanUtilsBean.getInstance().copyProperty(bean, name, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(Serializable template) {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ObjectOutputStream objOutput = null;
        try {
            objOutput = new ObjectOutputStream(byteOutput);
            objOutput.writeObject(template);
            ByteArrayInputStream byteInput = new ByteArrayInputStream(byteOutput.toByteArray());
            ObjectInputStream objInput = new ObjectInputStream(byteInput);
            return (T) objInput.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                byteOutput.close();
                if (null != objOutput) {
                    objOutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void serialize(Serializable obj, String path) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objOutput = null;
        try {
            fileOutput = new FileOutputStream(new File(path));
            objOutput = new ObjectOutputStream(fileOutput);
            objOutput.writeObject(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != fileOutput) {
                    fileOutput.close();
                }
                if (null != objOutput) {
                    objOutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ByteArrayOutputStream serialize(Serializable obj) {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ObjectOutputStream objOutput = null;
        try {
            objOutput = new ObjectOutputStream(byteOutput);
            objOutput.writeObject(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != objOutput) {
                    objOutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteOutput;
    }

    public static Object deserialize(File file) {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deserialize(String path) {
        return deserialize(new File(path));
    }
    
    /**
     * <p>Return the entire set of properties for which the specified bean
     * provides a read method.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose properties are to be extracted
     * @return Map of property descriptors
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#describe 
     */
    public static Map<?,?> describe(Object bean)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtilsBean.getInstance().describe(bean);
    }


    /**
     * <p>Return the value of the specified array property of the specified
     * bean, as a String array.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name Name of the property to be extracted
     * @return The array property value
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getArrayProperty 
     */
    public static String[] getArrayProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtilsBean.getInstance().getArrayProperty(bean, name);
    }


    /**
     * <p>Return the value of the specified indexed property of the specified
     * bean, as a String.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name <code>propertyname[index]</code> of the property value
     *  to be extracted
     * @return The indexed property's value, converted to a String
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getIndexedProperty(Object, String)
     */
    public static String getIndexedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        
        return BeanUtilsBean.getInstance().getIndexedProperty(bean, name);

    }


    /**
     * Return the value of the specified indexed property of the specified
     * bean, as a String.  The index is specified as a method parameter and
     * must *not* be included in the property name expression
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name Simple property name of the property value to be extracted
     * @param index Index of the property value to be extracted
     * @return The indexed property's value, converted to a String
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getIndexedProperty(Object, String, int)
     */
    public static String getIndexedProperty(Object bean,
                                            String name, int index)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtilsBean.getInstance().getIndexedProperty(bean, name, index);

    }


    /**
     * </p>Return the value of the specified indexed property of the specified
     * bean, as a String.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name <code>propertyname(index)</code> of the property value
     *  to be extracted
     * @return The mapped property's value, converted to a String
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getMappedProperty(Object, String)
     */
    public static String getMappedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtilsBean.getInstance().getMappedProperty(bean, name);

    }


    /**
     * </p>Return the value of the specified mapped property of the specified
     * bean, as a String.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name Simple property name of the property value to be extracted
     * @param key Lookup key of the property value to be extracted
     * @return The mapped property's value, converted to a String
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getMappedProperty(Object, String, String)
     */
    public static String getMappedProperty(Object bean,
                                           String name, String key)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtilsBean.getInstance().getMappedProperty(bean, name, key);

    }


    /**
     * <p>Return the value of the (possibly nested) property of the specified
     * name, for the specified bean, as a String.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name Possibly nested name of the property to be extracted
     * @return The nested property's value, converted to a String
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception IllegalArgumentException if a nested reference to a
     *  property returns null
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getNestedProperty
     */
    public static String getNestedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtilsBean.getInstance().getNestedProperty(bean, name);

    }


    /**
     * <p>Return the value of the specified property of the specified bean,
     * no matter which property reference format is used, as a String.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name Possibly indexed and/or nested name of the property
     *  to be extracted
     * @return The property's value, converted to a String
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getProperty
     */
    public static Object getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return PropertyUtils.getProperty(bean, name);

    }


    /**
     * <p>Return the value of the specified simple property of the specified
     * bean, converted to a String.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose property is to be extracted
     * @param name Name of the property to be extracted
     * @return The property's value, converted to a String
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#getSimpleProperty
     */
    public static String getSimpleProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtilsBean.getInstance().getSimpleProperty(bean, name);

    }


    /**
     * <p>Populate the JavaBeans properties of the specified bean, based on
     * the specified name/value pairs.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean JavaBean whose properties are being populated
     * @param properties Map keyed by property name, with the
     *  corresponding (String or String[]) value(s) to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @see BeanUtilsBean#populate
     */
    public static void populate(Object bean, Map<String, Object> properties)
        throws IllegalAccessException, InvocationTargetException {
        
        BeanUtilsBean.getInstance().populate(bean, properties);
    }


    /**
     * <p>Set the specified property value, performing type conversions as
     * required to conform to the type of the destination property.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean on which setting is to be performed
     * @param name Property name (can be nested/indexed/mapped/combo)
     * @param value Value to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @see BeanUtilsBean#setProperty
     */
    public static void setProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException {

        BeanUtilsBean.getInstance().setProperty(bean, name, value);
    }
    

    /**
     * <p>Clone a bean based on the available property getters and setters,
     * even if the bean class itself does not implement Cloneable.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean to be cloned
     * @return the cloned bean
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InstantiationException if a new instance of the bean's
     *  class cannot be instantiated
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#cloneBean
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneBean(T bean)
            throws IllegalAccessException, InstantiationException,
            InvocationTargetException, NoSuchMethodException {

        return (T) BeanUtilsBean.getInstance().cloneBean(bean);

    }
    
    public static Field[] getFields(Object obj) {
        List<Field> result = new ArrayList<>();

        Class<?> claze = obj.getClass();

        while (claze != Object.class) {
            Field[] subFields = claze.getDeclaredFields();
            for (Field subField : subFields) {
                result.add(subField);
            }
            claze = claze.getSuperclass();
        }
        return result.toArray(new Field[result.size()]);
    }

    public static String getTableNameByAnnotation(Object obj) {
        Table tableAnn = obj.getClass().getAnnotation(Table.class);
        return tableAnn.name();
    }
    
    public static Object getIdValue(Object model){
        Field[] fields = getFields(model);
        try {
            for (Field field : fields) {
                if(null != field.getAnnotation(Id.class)){
                   Method idMethod = model.getClass().getMethod("get"+ StringUtils.capitalize(field.getName()));
                   return idMethod.invoke(model);
                }
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
