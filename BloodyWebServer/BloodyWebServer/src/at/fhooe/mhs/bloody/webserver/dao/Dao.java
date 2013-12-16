package at.fhooe.mhs.bloody.webserver.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import at.fhooe.mhs.bloody.webserver.model.BloodyData;

@SuppressWarnings("unchecked")
public enum Dao {
	INSTANCE;

	public static final long MILLISECONDS_OF_HOUR = 3600 * 1000;

	public List<BloodyData> listBloodyData() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from BloodyData m");
		List<BloodyData> bloodyData = q.getResultList();
		return bloodyData;
	}

	public void add(BloodyData bloodyData) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			bloodyData.setTimestamp();
			em.persist(bloodyData);
			em.close();
		}
	}

	public List<BloodyData> getBloodyData(String country) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from BloodyData t where t.country = :country");
		q.setParameter("country", country);
		List<BloodyData> bloodyData = q.getResultList();
		return bloodyData;
	}

	public void clearBloodyData() {
		Date timenow = Calendar.getInstance(
				TimeZone.getTimeZone("America/Los_Angeles"), Locale.US)
				.getTime();

		List<BloodyData> bloodyData = getBloodyDataAll();
		for (BloodyData data : bloodyData) {
			if ((timenow.getTime() - data.getTimestamp().getTime()) > MILLISECONDS_OF_HOUR) {
				remove(data.getId());
			}
		}
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		BloodyData bloodyData = em.find(BloodyData.class, id);
		em.remove(bloodyData);
		em.close();
	}

	public List<BloodyData> getBloodyDataAll() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from BloodyData t");
		List<BloodyData> bloodyData = q.getResultList();
		return bloodyData;
	}
}
