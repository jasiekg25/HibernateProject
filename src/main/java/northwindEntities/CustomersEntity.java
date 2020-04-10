package northwindEntities;

//import javax.persistence.*;
//import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "customers", schema = "db_project", catalog = "")
public class CustomersEntity {
    private String customerId;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    private byte[] image;
    private byte[] imageThumbnail;


        public CustomersEntity(
            String customerId,
            String companyName,
            String contactName,
            String contactTitle,
            String address,
            String city,
            String region,
            String postalCode,
            String country,
            String phone,
            String fax,
            byte[] image,
            byte[] imageThumbnail
    ) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
        this.image = image;
        this.imageThumbnail = imageThumbnail;
    }

    ;

    public CustomersEntity() {
    }

    @Id
    @Column(name = "CustomerID")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "ContactName")
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "ContactTitle")
    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "PostalCode")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "Fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "Image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "ImageThumbnail")
    public byte[] getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(byte[] imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersEntity that = (CustomersEntity) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(contactTitle, that.contactTitle) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(region, that.region) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(fax, that.fax) &&
                Arrays.equals(image, that.image) &&
                Arrays.equals(imageThumbnail, that.imageThumbnail);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(customerId, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax);
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + Arrays.hashCode(imageThumbnail);
        return result;
    }
}
