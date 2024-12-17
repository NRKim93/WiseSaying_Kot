package com.example.ms.repository;

import com.example.ms.entity.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactionRepository extends JpaRepository<Faction,Integer> {
    // SELECT * FROM FACTION WHERE FACTION_NAME = ?;
    Optional<Faction> findByFactionName(String factionName);
}
