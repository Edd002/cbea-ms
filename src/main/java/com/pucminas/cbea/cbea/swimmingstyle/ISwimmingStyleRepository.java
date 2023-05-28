package com.pucminas.cbea.cbea.swimmingstyle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ISwimmingStyleRepository extends JpaRepository<SwimmingStyle, Long>, JpaSpecificationExecutor<SwimmingStyle> {
}