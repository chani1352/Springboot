package com.rubypaper.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.log.Log;

public interface LogRepository extends JpaRepository<Log,Integer>{

}
