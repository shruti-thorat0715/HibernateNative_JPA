package entity;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "country_id", length = 2)
    private String countryId;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetch for performance
    @JoinColumn(name = "region_id", nullable = false) // FK to regions.region_id
    private Region region;

    // No-arg constructor (required by JPA)
    public Country() {}

    // All-args constructor
    public Country(String countryId, String countryName, Region region) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.region = region;
    }

    // Getters and Setters
    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId='" + countryId + '\'' +
                ", countryName='" + countryName + '\'' +
                ", region=" + (region != null ? region.getRegionName() : "null") +
                '}';
    }
}
