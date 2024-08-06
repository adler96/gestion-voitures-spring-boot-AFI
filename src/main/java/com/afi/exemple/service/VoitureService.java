package com.afi.exemple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afi.exemple.model.Voiture;
import com.afi.exemple.repository.VoitureRepository;

import lombok.Data;

@Data
@Service
public class VoitureService implements VoitureInterface {
	
	@Autowired
	private VoitureRepository voitureRepository;
	
	public void saveVoiture(Voiture vt) {
		Optional<Voiture> toFind = null;
		
		if(vt.getId() != null) {
			// already exists
			System.out.println("Voiture already Exists");
			
			toFind = rechercherParId(vt.getId());
			
			if(toFind.isPresent()) {
				Voiture toSave = toFind.get();
				toSave.setMarque(vt.getMarque());
				toSave.setPrix(vt.getPrix());
				toSave.setCouleur(vt.getCouleur());
				toSave.setProvenance(vt.getProvenance());
				
				voitureRepository.save(toSave);
			} else {
				return;
			}
		} else {
			// does not exist yet
			System.out.println("Voiture doesn't Exist yet");
			voitureRepository.save(vt);
		}
		
	}
	
	public void deleteVoiture(final Long id) {
		voitureRepository.deleteById(id);
	}
	
	public Iterable<Voiture> listerVoitures() {
		return voitureRepository.findAll();
	}
	
	public Optional<Voiture> rechercherParId(final Long id) {
		return voitureRepository.findById(id);
	}
	
	public Iterable<Voiture> rechercherParMarque(String marque) {
		return voitureRepository.findByMarqueContains(marque);
	}

	@Override
	public Iterable<Voiture> rechercherParProvenance(String provenance) {
		return voitureRepository.findByProvenanceContains(provenance);
	}

	@Override
	public Iterable<Voiture> rechercherParPrix(int min, int max) {
		return voitureRepository.findByPrixBetween(min, max);
	}

}
