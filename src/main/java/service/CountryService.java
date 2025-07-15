package service;

import entity.Country;
import entity.Region;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CountryService {

    private final EntityManagerFactory emf;

    public CountryService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void createCountry(String countryId, String countryName, int regionId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Region region = em.find(Region.class, regionId);
            if (region != null) {
                Country country = new Country(countryId, countryName, region);
                region.addCountry(country); // maintain bidirectional relationship
                em.persist(country);
                System.out.println("Country created: " + countryName);
            } else {
                System.out.println("Region not found with ID: " + regionId);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error creating country: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Country readCountry(String countryId) {
        EntityManager em = emf.createEntityManager();
        try {
            Country country = em.find(Country.class, countryId);
            if (country != null) {
                System.out.println("Country: " + country.getCountryName() +
                        ", Region: " + country.getRegion().getRegionName());
            } else {
                System.out.println("Country not found with ID: " + countryId);
            }
            return country;
        } catch (Exception e) {
            System.err.println("Error reading country: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void updateCountry(String countryId, String newName, int newRegionId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Country country = em.find(Country.class, countryId);
            Region newRegion = em.find(Region.class, newRegionId);

            if (country != null && newRegion != null) {
                country.setCountryName(newName);
                country.setRegion(newRegion); // update relationship
                em.merge(country);
                System.out.println("Country updated: " + newName);
            } else {
                System.out.println("Country or Region not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error updating country: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void deleteCountry(String countryId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Country country = em.find(Country.class, countryId);
            if (country != null) {
                em.remove(country);
                System.out.println("Country deleted with ID: " + countryId);
            } else {
                System.out.println("Country not found with ID: " + countryId);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error deleting country: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}