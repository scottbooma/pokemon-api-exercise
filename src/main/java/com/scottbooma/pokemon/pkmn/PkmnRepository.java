package com.scottbooma.pokemon.pkmn;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PkmnRepository extends CrudRepository<Pkmn, Long> {}