package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for MySQL/PostgreSQL
    @Column(name = "region_id")
    private Integer regionId;

    @Column(name = "region_name", nullable = false, unique = true) // Ensure region name is not null and unique
    private String regionName;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Country> countries = new ArrayList<>();

    // No-arg constructor (required by JPA)
    public Region() {}

    // All-args constructor
    public Region(String regionName) {
        this.regionName = regionName;
    }

    // Getters and Setters
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    // Helper method for bi-directional relationship
    public void addCountry(Country country) {
        countries.add(country);
        country.setRegion(this);
    }

    public void removeCountry(Country country) {
        countries.remove(country);
        country.setRegion(null);
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
