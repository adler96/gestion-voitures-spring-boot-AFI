package com.afi.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afi.exemple.model.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
	public Iterable<Voiture> findByMarqueContains(String marque);
	public Iterable<Voiture> findByProvenanceContains(String provenance);
	public Iterable<Voiture> findByPrixBetween(int min, int max);
}
