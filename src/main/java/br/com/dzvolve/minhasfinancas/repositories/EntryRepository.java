package br.com.dzvolve.minhasfinancas.repositories;

import br.com.dzvolve.minhasfinancas.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
