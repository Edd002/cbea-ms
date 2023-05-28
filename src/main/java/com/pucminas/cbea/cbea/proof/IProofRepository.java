package com.pucminas.cbea.cbea.proof;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IProofRepository extends JpaRepository<Proof, Long>, JpaSpecificationExecutor<Proof> {
}