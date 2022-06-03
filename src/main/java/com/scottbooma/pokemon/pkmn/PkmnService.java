package com.scottbooma.pokemon.pkmn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PkmnService {
  @Autowired
  private PkmnRepository pkmnRepository;

  public Iterable<Pkmn> list(){
    return pkmnRepository.findAll();
  }

  public Optional<Pkmn> findById(Long id){
    return pkmnRepository.findById(id);
  }

  public Pkmn create(Pkmn pkmn) {
    return pkmnRepository.save(pkmn);
  }

  public Optional<Pkmn> update(Pkmn pkmn) {
    Optional<Pkmn> foundPkmn = pkmnRepository.findById(pkmn.getId());

    if (foundPkmn.isPresent()) {
        Pkmn updatedPkmn = foundPkmn.get();
        updatedPkmn.setName(pkmn.getName());
        updatedPkmn.setImageUrl(pkmn.getImageUrl());

        pkmnRepository.save(updatedPkmn);
        return Optional.of(updatedPkmn);
      } else {
        return Optional.empty();
      }
  }

  public void deleteById(Long id) {
    pkmnRepository.deleteById(id);
  }
}