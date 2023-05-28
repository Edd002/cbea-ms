package com.pucminas.cbea.cbea.championship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IChampionshipRepository extends JpaRepository<Championship, Long>, JpaSpecificationExecutor<Championship> {
}