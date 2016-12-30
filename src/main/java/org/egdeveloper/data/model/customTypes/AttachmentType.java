package org.egdeveloper.data.model.customTypes;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AttachmentType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.BLOB
        };
    }

    @Override
    public Class returnedClass() {
        return Attachment.class;
    }

    @Override
    public boolean equals(final Object o1, final Object o2) throws HibernateException {
        if(o1 == o2)
            return true;
        else if(o1 == null || o2 == null)
            return false;
        else
            return o1.equals(o2);
    }

    @Override
    public int hashCode(final Object o) throws HibernateException {
        if(o != null)
            return o.hashCode();
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        Attachment attachment = null;
        String contentType = resultSet.getString(names[0]);
        String name = resultSet.getString(names[1]);
        byte[] content = resultSet.getBytes(names[2]);
        if(contentType != null && name != null && content != null){
            attachment = new Attachment();
            attachment.setContentType(contentType);
            attachment.setName(name);
            attachment.setContent(content);
        }
        return attachment;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if(value != null) {
            Attachment attachment = (Attachment) value;
            preparedStatement.setString(index, attachment.getContentType());
            preparedStatement.setString(index + 1, attachment.getName());
            preparedStatement.setBytes(index + 2, attachment.getContent());
        }
        else{
            preparedStatement.setObject(index, null);
            preparedStatement.setObject(index + 1, null);
            preparedStatement.setObject(index + 2, null);
        }
    }

    @Override
    public Object deepCopy(final Object o) throws HibernateException {
        if(o == null)
            return null;
        else {
            final Attachment otherAttachment = (Attachment) o;
            return new Attachment(otherAttachment);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(final Object o) throws HibernateException {
        if(o instanceof Serializable)
            return (Serializable) deepCopy(o);
        return null;
    }

    @Override
    public Object assemble(final Serializable cashed, final Object owner) throws HibernateException {
        return deepCopy(cashed);
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return deepCopy(original);
    }
}
