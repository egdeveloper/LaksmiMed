package org.egdeveloper.data.model.customTypes;

import org.egdeveloper.attributes.DisplayName;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Arrays;

@DisplayName("Приложение к анализу")
@Embeddable
public class Attachment implements Serializable{

    @Column(name = "attachmentContentType")
    private String contentType;

    @Column(name = "attachmentName")
    private String name;

    @Column(name = "attachmentContent")
    private byte[] content;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * Default constructor
     */
    public Attachment(){}

    /**
     * Copy constructor
     * @param other other Attachment instance
     */
    public Attachment(Attachment other){
        this.setName(other.getName());
        this.setContentType(other.getContentType());
        this.setContent(other.getContent());
    }


    @Override
    public int hashCode() {
        int result = getContentType().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + Arrays.hashCode(getContent());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if(o == null)
            return false;
        if (!(o instanceof Attachment))
            return false;

        Attachment that = (Attachment) o;

        if (getContentType() != null ? !getContentType().equals(that.getContentType()) : that.getContentType() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        return Arrays.equals(getContent(), that.getContent());
    }
}
