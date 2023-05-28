package com.pucminas.cbea.cbea.swimmer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ISwimmerRepository extends JpaRepository<Swimmer, Long>, JpaSpecificationExecutor<Swimmer> {
}