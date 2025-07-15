package service;

import entity.Region;
import entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class RegionService {

    private final EntityManagerFactory emf;

    // Constructor to inject EntityManagerFactory
    public RegionService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void createRegion(String regionName) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Region region = new Region(regionName);
            em.persist(region);
            tx.commit();
            System.out.println("Region created: " + regionName);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error creating region: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Region readRegion(int regionId) {
        EntityManager em = emf.createEntityManager();
        try {
            Region region = em.find(Region.class, regionId);
            if (region != null) {
                System.out.println("Region: " + region.getRegionName());
                for (Country country : region.getCountries()) {
                    System.out.println("Country: " + country.getCountryName() +
                            " (" + country.getCountryId() + ")");
                }
            } else {
                System.out.println("Region not found with ID: " + regionId);
            }
            return region;
        } catch (Exception e) {
            System.err.println("Error reading region: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void updateRegion(int regionId, String newName) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Region region = em.find(Region.class, regionId);
            if (region != null) {
                region.setRegionName(newName);
                em.merge(region); // merge ensures updates if detached
                System.out.println("Region updated: " + newName);
            } else {
                System.out.println("Region not found with ID: " + regionId);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error updating region: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void deleteRegion(int regionId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Region region = em.find(Region.class, regionId);
            if (region != null) {
                em.remove(region);
                System.out.println("Region deleted with ID: " + regionId);
            } else {
                System.out.println("Region not found with ID: " + regionId);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error deleting region: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}