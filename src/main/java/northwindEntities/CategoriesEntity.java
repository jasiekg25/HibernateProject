package northwindEntities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "db_project", catalog = "")
public class CategoriesEntity {
    private int categoryId;
    private String categoryName;
    private String description;
    private byte[] picture;

    public CategoriesEntity(){};

    public CategoriesEntity(int categoryId, String categoryName, String description, byte[] picture ){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.picture = picture;
    }

    @Id
    @Column(name = "CategoryID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "CategoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Picture")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        return categoryId == that.categoryId &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(description, that.description) &&
                Arrays.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(categoryId, categoryName, description);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
