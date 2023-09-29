package org.test.blogricette.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.blogricette.model.Ricetta;

public interface RicettaRepository extends JpaRepository<Ricetta,Integer> {
}
