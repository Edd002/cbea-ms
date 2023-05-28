package com.pucminas.cbea.cbea.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecordRepository extends JpaRepository<Record, Long>, JpaSpecificationExecutor<Record> {
}