package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }

    @Transactional
    public Entry updateEntry(Entry entry) {
        return entityManager.merge(entry);
    }

    @Transactional
    public void deleteEntry(int id) {
        var query = entityManager.createQuery("FROM Entry WHERE id=" + id, Entry.class);
        Entry entry = query.getSingleResult();
        entityManager.remove(entry);
    }
}
