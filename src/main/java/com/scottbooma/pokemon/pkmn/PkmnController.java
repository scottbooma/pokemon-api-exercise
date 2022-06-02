package com.scottbooma.pokemon.pkmn;

import java.util.Map;
import java.util.HashMap;

import com.scottbooma.pokemon.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("api/pokemon")
public class PkmnController {
  @Autowired
  private PkmnService pkmnService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Pkmn>> list(){
    Iterable<Pkmn> pkmns = pkmnService.list();
    return createHashPlural(pkmns);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Pkmn> read(@PathVariable Long id) {
    Pkmn pkmn = pkmnService
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("No resource with that ID"));
    return createHashSingular(pkmn);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Pkmn> create(@Validated @RequestBody Pkmn pkmn) {
    Pkmn createdPkmn = pkmnService.create(pkmn);
    return createHashSingular(createdPkmn);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Pkmn> update(@RequestBody Pkmn pkmn, @PathVariable Long id) {
    Pkmn updatedPkmn = pkmnService
      .update(pkmn)
      .orElseThrow(() -> new ResourceNotFoundException("No resource with that ID"));

    return createHashSingular(updatedPkmn);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    pkmnService.deleteById(id);
  }

  private Map<String, Pkmn> createHashSingular(Pkmn pkmn){
    Map<String, Pkmn> response = new HashMap<String, Pkmn>();
    response.put("someResource", pkmn);

    return response;
  }

  private Map<String, Iterable<Pkmn>> createHashPlural(Iterable<Pkmn> pkmns){
    Map<String, Iterable<Pkmn>> response = new HashMap<String, Iterable<Pkmn>>();
    response.put("pkmns", pkmns);

    return response;
  }
}