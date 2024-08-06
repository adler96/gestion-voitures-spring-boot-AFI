package com.afi.exemple.service;

import java.util.Optional;

import com.afi.exemple.model.Voiture;

public interface VoitureInterface {
	
	public void saveVoiture(Voiture vt);
	public void deleteVoiture(final Long id);
	public Iterable<Voiture> listerVoitures();
	public Optional<Voiture> rechercherParId(final Long id);
	public Iterable<Voiture> rechercherParMarque(String marque);
	public Iterable<Voiture> rechercherParProvenance(String provenance);
	public Iterable<Voiture> rechercherParPrix(int min, int max);
}
