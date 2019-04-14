package com.example.app.tester.db.repositories;

import com.example.app.tester.db.entities.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    List<Solution> findAllByTestId(long testId);
}
